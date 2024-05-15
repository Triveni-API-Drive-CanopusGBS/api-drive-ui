import { Component, OnInit, Input } from '@angular/core';
import { ITOcreateConsultHomeService } from './ito-create-consultant.service';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { consultantDetails } from './ito-create-consultant';
import { ItoConsultantHomeComponent } from '../ito-consultant-home/ito-consultant-home.component';
import { ITOconsultHomeService } from '../ito-consultant-home/ito-consultant-home.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';

@Component({
  selector: 'app-ito-create-consultant',
  templateUrl: './ito-create-consultant.component.html',
  styleUrls: ['./ito-create-consultant.component.css']
})
export class ItoCreateConsultantComponent implements OnInit {

  displayMode: boolean;
  firmList: Array<any> = [];
  firmId: number;
  spocId: number;
  mobnumPattern = "^((\\+91-?)|0)?[0-9]{10}$";
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  emptyTextPattern=".*[^ ].*";
  loggedInUser: userDetails;
  userDetail: string = 'userDetail';
  profle: Array<any> = [];
  modifiedById: number;
  Countries: Array<any> = [];
  StatesList: Array<any> = [];
  States: Array<any> = [];
  DistrictsList: Array<any> = [];
  Districts: Array<any> = [];
  disableDropdownList: boolean = true;
  disableDropdown: boolean = false;
  count: number = 1;
  hideprogress: boolean;
  hidespinner: boolean = true;
  AddressInfo: string = 'AddressInfo';
  address: Array<any> = [];


  constructor(private _Http: Http, private _Router: Router, private _ActivatedRoute: ActivatedRoute,
    private _ITOcreateConsultHomeService: ITOcreateConsultHomeService, private _ITOconsultHomeService: ITOconsultHomeService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    this.loggedInUser = new userDetails('');
    this.profle[this.userDetail] = this.storage.get(this.userDetail);
    this.address[this.AddressInfo] = this.storage.get(this.AddressInfo);
    this.loggedInUser = this.profle[this.userDetail];
    this.modifiedById = this.loggedInUser.userId;
    this.hideprogress = false;
    this._ITOcreateConsultHomeService.getQuotationList().subscribe(res => {
      console.log(res);
      this.Countries = res.dropDownColumnvalues.countryList.countryList;
      this.States = res.dropDownColumnvalues.stateList.stateList;
      this.Districts = res.dropDownColumnvalues.districtList.DistrictList;

      for (let f = 0; f < res.firmList.length; f++) {
        this.firmList.push(res.firmList[f]);
      }
      console.log(this.firmList);
      this.hideprogress = true;
    });
    this.Countries = this.address[this.AddressInfo].countryList.countryList;
    this.States = this.address[this.AddressInfo].stateList.stateList;
    this.Districts = this.address[this.AddressInfo].districtList.DistrictList;
    this.hideprogress = true;
    this.displayMode = true;
  }

  ngOnInit() {

  }
  goback() {
    alert("Changes will not be saved");
    var win = window.open("", "_self");
    /* url = “” or “about:blank”; target=”_self” */
    win.close();
    this._Router.navigate(['/ConsultHome']);
  }
  createConslt(createConsultForm) {
    this.hidespinner = false;
    this._ITOcreateConsultHomeService.getQuotationList().subscribe(res => {
      console.log(res);
      for (let f = 0; f < res.customerProfileForm.consultantList.length; f++) {
        if (createConsultForm.firmName == res.customerProfileForm.consultantList[f].firmName) {
          createConsultForm.firmId = res.customerProfileForm.consultantList[f].firmId;
          break; //exit loop if matching is found
        }
        else {
          createConsultForm.firmId = 0;
        }
      }
      // for(let g=0;g<res.customerProfileForm.consultantList.length;g++){
      //   if(createConsultForm.spocName==res.customerProfileForm.consultantList[g].spocName){
      //     createConsultForm.spocId=res.customerProfileForm.consultantList[g].spocId;
      //   }
      // }
      createConsultForm.spocId = 0;
      console.log(createConsultForm);

      this._ITOcreateConsultHomeService.createConsultant(createConsultForm).subscribe(res => {
        console.log(res.successMsg);
        this.hidespinner = true;
        if (res.successCode == 0 && res.successMsg != null) {
          alert("Consultant created");
          var win = window.open("", "_self");
          /* url = “” or “about:blank”; target=”_self” */
          win.close();
          this._Router.navigate(['/ConsultHome']);
        } else {
          alert("Consultant Creation Failed");
        }

      });
    });
  }

  selectedCountry(countryName) {
    for (let i = 0; i < this.Countries.length; i++) {
      if (countryName == "INDIA") {
        for (let j = 0; j < this.States.length; j++) {
          if (this.Countries[i].locationId == this.States[j].dependLocationId) {
            this.StatesList.push(this.States[j]);
          }
        }
        if (this.count = 1) {
          this.disableDropdown = false;
          this.disableDropdownList = true;
        }
      } else {
        if (this.count = 1) {
          this.disableDropdown = true;
          this.disableDropdownList = false;
        }
      }
    }
  }
  selectedStates(state) {
    this.DistrictsList = [];
    for (let i = 0; i < this.States.length; i++) {
      if (this.States[i].locationName == state) {
        for (let j = 0; j < this.Districts.length; j++) {
          if (this.States[i].locationId == this.Districts[j].dependLocationId) {
            this.DistrictsList.push(this.Districts[j]);
          }
        }
      }
    }
  }

}
