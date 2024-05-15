import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { consultantDetails } from '../ito-create-consultant/ito-create-consultant';
import { ItoConsultantHomeComponent } from '../ito-consultant-home/ito-consultant-home.component';
import { ITOconsultHomeService } from '../ito-consultant-home/ito-consultant-home.service';
import { ITOupdateConsultHomeService } from './ito-update-consultant.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';


@Component({
  selector: 'app-ito-update-consultant',
  templateUrl: './ito-update-consultant.component.html',
  styleUrls: ['./ito-update-consultant.component.css']
})
export class ItoUpdateConsultantComponent implements OnInit {

  selectedcnslnt: consultantDetails;
  displayMode: boolean;
  firmList: Array<any> = [];
  mobnumPattern = "^((\\+91-?)|0)?[0-9]{10}$";
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  emptyTextPattern=".*[^ ].*";
  loggedInUser: userDetails;
  userDetail: string = 'userDetail';
  profle: Array<any> = [];
  modifiedById: number;
  countries: Array<any> = [];
  StatesList: Array<any> = [];
  States: Array<any> = [];
  DistrictsList: Array<any> = [];
  Districts: Array<any> = [];
  disableDropdownList: boolean = true;
  disableDropdown: boolean = false;
  count: number = 1;
  StateLocationId: number;
  hideprogress: boolean;
  hidespinner: boolean = true;

  constructor(private _Http: Http, private _Router: Router, private _ActivatedRoute: ActivatedRoute,
    private _ITOconsultHomeService: ITOconsultHomeService, private _ITOupdateConsultHomeService: ITOupdateConsultHomeService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    this.loggedInUser = new userDetails('');
    this.profle[this.userDetail] = this.storage.get(this.userDetail);
    this.loggedInUser = this.profle[this.userDetail];
    this.modifiedById = this.loggedInUser.userId;
    this.hideprogress = false;
    this._ITOupdateConsultHomeService.getQuotationList().subscribe(res => {
      console.log(res);
      this.countries = res.dropDownColumnvalues.countryList.countryList;
      this.States = res.dropDownColumnvalues.stateList.stateList;
      this.Districts = res.dropDownColumnvalues.districtList.DistrictList;

      for (let d = 0; d < this.countries.length; d++) {
        if (this.countries[d].locationName.toLowerCase() === this.selectedcnslnt.country.toLowerCase()) {
          this.countries[d].defaultVal = true;
        }
      }

      if (this.selectedcnslnt.country != null) {
        if (this.selectedcnslnt.country.toLowerCase() === "india") {
          this.disableDropdown = false;
          this.disableDropdownList = true;

          for (let d = 0; d < this.States.length; d++) {
            if (this.selectedcnslnt.state != null) {
              if (this.States[d].locationName.toLowerCase() === this.selectedcnslnt.state.toLowerCase()) {
                this.States[d].defaultVal = true;
                this.StateLocationId = this.States[d].locationId;
              }
            }

            this.StatesList.push(this.States[d]);
          }

          for (let d = 0; d < this.Districts.length; d++) {
            if (this.selectedcnslnt.district != null) {
              if (this.Districts[d].locationName.toLowerCase() === this.selectedcnslnt.district.toLowerCase()) {
                this.Districts[d].defaultVal = true;
              }
            }
            if (this.StateLocationId == this.Districts[d].dependLocationId) {
              this.DistrictsList.push(this.Districts[d]);
            }
          }
        } else {
          this.disableDropdown = true;
          this.disableDropdownList = false;
        }
      }
      for (let f = 0; f < res.firmList.length; f++) {
        this.firmList.push(res.firmList[f]);
      }
      console.log(this.firmList);
      this.hideprogress = true;
    });
    console.log(this.selectedcnslnt);
    this.displayMode = true;
  }

  ngOnInit() {

    this.selectedcnslnt = this._ITOconsultHomeService.selectedConsult;

  }

  enablefields(displayMode) {
    switch (this.displayMode) {
      case true:
        this.displayMode = false;
        break;
      case false:
        this.displayMode = true;
        break;
      default:
        this.displayMode = true;
    }

  }
  goback() {
    alert("Changes will not be saved");
    this._Router.navigate(['/ConsultHome']);
  }

  updateConslt(updateConsultForm) {
    this.hidespinner = false;
    this._ITOupdateConsultHomeService.getQuotationList().subscribe(res => {
      console.log(res);
      for (let f = 0; f < res.customerProfileForm.consultantList.length; f++) {
        if (updateConsultForm.firmName == res.customerProfileForm.consultantList[f].firmName) {
          updateConsultForm.firmId = res.customerProfileForm.consultantList[f].firmId;
          break;
        }
      }
      // for(let g=0;g<res.customerProfileForm.consultantList.length;g++){
      //   if(updateConsultForm.spocName==res.customerProfileForm.consultantList[g].spocName){
      //     updateConsultForm.spocId=res.customerProfileForm.consultantList[g].spocId;
      //   }
      // }
      updateConsultForm.spocId = this._ITOconsultHomeService.selectedConsult.spocId;
      console.log(updateConsultForm);
      this._ITOupdateConsultHomeService.updateConsultant(updateConsultForm).subscribe(res => {
        console.log(res);
        this.hidespinner = true;
        if (res.successCode == 0 && res.successMsg != null) {
          alert("Consultant Information Updated");
          this._Router.navigate(['/ConsultHome']);
        } else {
          alert("Consultant Updation Failed");
        }
      })
    });
  }

  selectedCountry(countryName) {
    for (let i = 0; i < this.countries.length; i++) {
      if (countryName.toLowerCase() === "india") {
        if (this.count = 1) {
          this.disableDropdown = false;
          this.disableDropdownList = true;
          for (let j = 0; j < this.States.length; j++) {
            if (this.countries[i].locationId == this.States[j].dependLocationId) {
              this.StatesList.push(this.States[j]);
            }
          }
        }
      } else {
        this.selectedcnslnt.state = "";
        if (this.count = 1) {
          this.disableDropdown = true;
          this.disableDropdownList = false;
        }
      }
    }
  }
  selectedStates(state) {
    console.log(state)
    this.DistrictsList = [];
    for (let i = 0; i < this.States.length; i++) {
      if (this.States[i].locationName.toLowerCase() === state.toLowerCase()) {
        for (let j = 0; j < this.Districts.length; j++) {
          if (this.States[i].locationId == this.Districts[j].dependLocationId) {
            this.DistrictsList.push(this.Districts[j]);
          }
        }
      }
    }
  }

}
