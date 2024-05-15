import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOupdateCustomerHomeService } from './ito-update-customer.service';
import { CustomerDetails } from '../ito-create-customer/ito-create-customer';
import { ITOcustHomeService } from '../ito-customer-home/ito-customer-home.service';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-ito-update-customer',
  templateUrl: './ito-update-customer.component.html',
  styleUrls: ['./ito-update-customer.component.css']
})
export class ItoUpdateCustomerComponent implements OnInit {

  mobnumPattern = "^((\\+91-?)|0)?[0-9]{10}$";
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  emptyTextPattern=".*[^ ].*";
  selectedCustomer: CustomerDetails;
  displayMode: boolean;
  CountryList: Array<any> = [];
  countries: Array<any> = [];
  StatesList: Array<any> = [];
  States: Array<any> = [];
  DistrictsList: Array<any> = [];
  Districts: Array<any> = [];
  isDomestic: boolean = false;
  loggedInUser: userDetails;
  userDetail: string = 'userDetail';
  profle: Array<any> = [];
  modifiedById: number;
  disableDropdownList: boolean = true;
  count: number = 1;
  StateLocationId: number;
  hideprogress: boolean;
  hidespinner: boolean = true;

  constructor(private _Http: Http, private _Router: Router, private _ActivatedRoute: ActivatedRoute
    , private _ITOupdateCustomerHomeService: ITOupdateCustomerHomeService, private _ITOcustHomeService: ITOcustHomeService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    this.loggedInUser = new userDetails('');
    this.profle[this.userDetail] = this.storage.get(this.userDetail);
    this.loggedInUser = this.profle[this.userDetail];
    this.modifiedById = this.loggedInUser.userId;
    this.hideprogress = false;
    this._ITOupdateCustomerHomeService.getQuotationList().subscribe(res => {
      console.log(res);
      this.countries = res.dropDownColumnvalues.countryList.countryList;
      this.States = res.dropDownColumnvalues.stateList.stateList;
      this.Districts = res.dropDownColumnvalues.districtList.DistrictList;


      for (let d = 0; d < this.countries.length; d++) {
        if (this.selectedCustomer.custType.toLowerCase() === "domestic") {
          this.disableDropdownList = true;
          if (this.countries[d].locationId == 95) {
            this.countries[d].defaultVal = true;
            this.CountryList.push(this.countries[d]);
          }
        }
        else {
          this.disableDropdownList = false;
          if (this.selectedCustomer.country != null) {
            if (this.selectedCustomer.country.toLowerCase() === this.countries[d].locationName.toLowerCase()) {
              this.countries[d].defaultVal = true;
            }
          }
          this.CountryList.push(this.countries[d]);
        }
      }

      if (this.selectedCustomer.country != null) {
        if (this.selectedCustomer.country.toLowerCase() === "india") {
          for (let d = 0; d < this.States.length; d++) {
            if (this.selectedCustomer.state != null) {
              if (this.States[d].locationName.toLowerCase() == this.selectedCustomer.state.toLowerCase()) {
                this.States[d].defaultVal = true;
                this.StateLocationId = this.States[d].locationId;
              }
            }
            this.StatesList.push(this.States[d]);
          }

          for (let d = 0; d < this.Districts.length; d++) {
            if (this.selectedCustomer.district != null) {
              if (this.Districts[d].locationName.toLowerCase() === this.selectedCustomer.district.toLowerCase()) {
                this.Districts[d].defaultVal = true;
              }
            }
            if (this.StateLocationId == this.Districts[d].dependLocationId) {
              this.DistrictsList.push(this.Districts[d]);
            }
          }
        }
      }
      this.hideprogress = true;
    })

    console.log(this.selectedCustomer);
    this.displayMode = true;
  }

  ngOnInit() {
    this.selectedCustomer = this._ITOcustHomeService.selectedCustomer;
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
    this._Router.navigate(['/CustHome']);
  }
  updateCustomer(custUpdateForm) {
    this.hidespinner = false;
    console.log(custUpdateForm);
    this._ITOupdateCustomerHomeService.updateCustomer(custUpdateForm).subscribe(res => {
      console.log(res);
      this.hidespinner = true;
      if (res.successCode == 0 && res.successMsg != null) {
        alert("Customer Informaion updated");
        this._Router.navigate(['/CustHome']);
      } else {
        alert("Customer Updation Failed");
      }
    })

  }

  selectedType(value) {
    console.log(value)
    this.CountryList = [];
    for (let i = 0; i < this.countries.length; i++) {
      if (value == "Domestic") {
        this.selectedCustomer.country = "";
        this.disableDropdownList = true;
        if (this.countries[i].locationId == 95) {
          this.CountryList.push(this.countries[i]);
        }
      } else {
        this.disableDropdownList = false;
        this.selectedCustomer.country = "";
        this.CountryList.push(this.countries[i]);
        for (let j = 0; j < this.CountryList.length; j++) {
          if (this.CountryList[j].locationId == 95) {
            this.CountryList.splice(j, 1);
          }
        }
      }
    }
    //console.log(this.CountryList)
  }

  selectedCountry(countryName) {
    //// for(let i=0;i<this.countries.length;i++){
    //   for(let d=0;d<this.countries.length;d++){
    //     if(this.countries[d].locationName.toLowerCase()===this.selectedCustomer.country.toLowerCase()){
    //       this.countries[d].defaultVal=true;
    //     }
    // }   
    console.log("inside idccccccc" + countryName)
    //  if( countryName.toLowerCase()==="india" ){
    //    console.log("inside id")
    //   for(let j=0;j<this.States.length;j++){
    //     if(this.countries[i].locationId==this.States[j].dependLocationId){
    //       this.StatesList.push(this.States[j]);
    //        }
    // }
    for (let i = 0; i < this.countries.length; i++) {
      if (this.countries[i].locationName == countryName) {
        for (let j = 0; j < this.States.length; j++) {
          if (this.countries[i].locationId == this.States[j].dependLocationId) {
            this.StatesList.push(this.States[j]);
          }
        }
      } else {
        this.selectedCustomer.state = "";
        this.selectedCustomer.city = "";
      }
    }
    //    }
    //  }
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
