import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOupdateEndUserHomeService } from './ito-update-end-user.service';
import { endUserDetials } from '../ito-create-end-user/ito-create-end-user';
import { ITOEndUserHomeService } from '../ito-end-user-home/ito-end-user-home.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';

@Component({
  selector: 'app-ito-update-end-user',
  templateUrl: './ito-update-end-user.component.html',
  styleUrls: ['./ito-update-end-user.component.css']
})
export class ItoUpdateEndUserComponent implements OnInit {

  selectedEndUser: endUserDetials;
  displayMode: boolean;
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
    private _ITOupdateEndUserHomeService: ITOupdateEndUserHomeService, private _ITOEndUserHomeService: ITOEndUserHomeService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    this.loggedInUser = new userDetails('');
    this.profle[this.userDetail] = this.storage.get(this.userDetail);
    this.loggedInUser = this.profle[this.userDetail];
    this.modifiedById = this.loggedInUser.userId;
    this.hideprogress = false;
    this._ITOupdateEndUserHomeService.getQuotationList().subscribe(res => {
      this.countries = res.dropDownColumnvalues.countryList.countryList;
      this.States = res.dropDownColumnvalues.stateList.stateList;
      this.Districts = res.dropDownColumnvalues.districtList.DistrictList;
      console.log(this.selectedEndUser.country)
      console.log(this.countries)


      for (let d = 0; d < this.countries.length; d++) {
        if (this.selectedEndUser.country != null) {
          if (this.countries[d].locationName.toLowerCase() === this.selectedEndUser.country.toLowerCase()) {
            this.countries[d].defaultVal = true;
          }
        }
      }
      if (this.selectedEndUser.country != null) {
        if (this.selectedEndUser.country.toLowerCase() === "india") {
          this.disableDropdown = false;
          this.disableDropdownList = true;

          for (let d = 0; d < this.States.length; d++) {
            if (this.selectedEndUser.state != null) {
              if (this.States[d].locationName.toLowerCase() === this.selectedEndUser.state.toLowerCase()) {
                this.States[d].defaultVal = true;
                this.StateLocationId = this.States[d].locationId;
              }
            }
            this.StatesList.push(this.States[d]);
          }

          for (let d = 0; d < this.Districts.length; d++) {
            if (this.selectedEndUser.district != null) {
              if (this.Districts[d].locationName.toLowerCase() === this.selectedEndUser.district.toLowerCase()) {
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
      this.hideprogress = true;
    })
    this.displayMode = true;

  }

  ngOnInit() {
    this.selectedEndUser = this._ITOEndUserHomeService.selectedEndUser;
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
    this._Router.navigate(['/EndUserHome']);
  }
  updateEndUser(endUserUpdateForm) {
    this.hidespinner = false;
    this._ITOupdateEndUserHomeService.updateEndUser(endUserUpdateForm).subscribe(res => {
      console.log(res);
      this.hidespinner = true;
      if (res.successCode == 0 && res.successMsg != null) {
        alert("End user Information updated");
        this._Router.navigate(['/EndUserHome']);
      } else {
        alert("End user Updation Failed");
      }
    })
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
        this.selectedEndUser.state = "";
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
