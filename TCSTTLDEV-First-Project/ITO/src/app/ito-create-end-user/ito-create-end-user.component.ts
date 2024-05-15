import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcreateEndUserService } from './ito-create-end-user.service';
import { endUserDetials } from './ito-create-end-user';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';


@Component({
  selector: 'app-ito-create-end-user',
  templateUrl: './ito-create-end-user.component.html',
  styleUrls: ['./ito-create-end-user.component.css']
})
export class ItoCreateEndUserComponent implements OnInit {

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

  constructor(private _ITOcreateEndUserService: ITOcreateEndUserService,
    private _Router: Router, @Inject(LOCAL_STORAGE) private storage: WebStorageService) {

    this.loggedInUser = new userDetails('');
    this.profle[this.userDetail] = this.storage.get(this.userDetail);
    this.address[this.AddressInfo] = this.storage.get(this.AddressInfo);
    this.loggedInUser = this.profle[this.userDetail];
    this.modifiedById = this.loggedInUser.userId;
    this.hideprogress = false;
  

    this.Countries = this.address[this.AddressInfo].countryList.countryList;
    this.States = this.address[this.AddressInfo].stateList.stateList;
    this.Districts = this.address[this.AddressInfo].districtList.DistrictList;
    this.hideprogress = true;
  }

  ngOnInit() {
  }
  goback() {
    alert("Changes will not be saved");
    var win = window.open("", "_self");
    /* url = “” or “about:blank”; target=”_self” */
    win.close();
    this._Router.navigate(['/EndUserHome']);
  }
  createEndUser(endUserCreationForm) {
    this.hidespinner = false;
    console.log(endUserCreationForm)

    this._ITOcreateEndUserService.createEndUser(endUserCreationForm).subscribe(res => {
      console.log(res);
      this.hidespinner = true;
      if (res.successCode == 0 && res.successMsg != null) {
        alert("End User created");
        var win = window.open("", "_self");
        /* url = “” or “about:blank”; target=”_self” */
        win.close();
        this._Router.navigate(['/EndUserHome']);
      } else {
        alert("End User Creation Failed");
      }

    })
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
