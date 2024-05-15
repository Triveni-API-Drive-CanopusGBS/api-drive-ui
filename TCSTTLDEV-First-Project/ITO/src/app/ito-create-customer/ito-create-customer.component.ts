import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcreateCustomerService } from './ito-create-customer.service';
import { CustomerDetails } from './ito-create-customer';
import { NgForm } from '@angular/forms';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { ITOcustomerDetailsService } from '../ito-customer-details/ito-customer-details.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-ito-create-customer',
  templateUrl: './ito-create-customer.component.html',
  styleUrls: ['./ito-create-customer.component.css']
})
export class ItoCreateCustomerComponent implements OnInit {

  enableConsultantDiv: boolean = false;
  customerCreationData: NgForm;
  validateEmail: boolean = true;
  mobnumPattern = "^((\\+91-?)|0)?[0-9]{10}$";
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  emptyTextPattern=".*[^ ].*";
  CountryList: Array<any> = [];
  Countries: Array<any> = [];
  StatesList: Array<any> = [];
  States: Array<any> = [];
  DistrictsList: Array<any> = [];
  Districts: Array<any> = [];
  isDomestic: boolean = false;
  disableDropdownList: boolean = false;
  loggedInUser: userDetails;
  userDetail: string = 'userDetail';
  profle: Array<any> = [];
  modifiedById: number;
  hideprogress: boolean;
  hidespinner: boolean = true;
  AddressInfo: string = 'AddressInfo';
  address: Array<any> = [];

  constructor(private _ITOcreateCustomerService: ITOcreateCustomerService, private _Router: Router
    , private _ActivatedRoute: ActivatedRoute, private _ITOcustomerDetailsService: ITOcustomerDetailsService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    this.hideprogress = false;
    this.loggedInUser = new userDetails('');
    this.profle[this.userDetail] = this.storage.get(this.userDetail);
    this.address[this.AddressInfo] = this.storage.get(this.AddressInfo);
    this.loggedInUser = this.profle[this.userDetail];
    this.modifiedById = this.loggedInUser.userId;
  
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
    this._Router.navigate(['/CustHome']);
  }
  createCustomer(custCreationForm) {

    this.hidespinner = false;
    console.log(custCreationForm)
    this.customerCreationData = custCreationForm;
    console.log(this.customerCreationData.errors);
    //  this.customerCreationData.active=custCreationForm.active;
    //  this.customerCreationData.address=custCreationForm.address;
    //  this.customerCreationData.city=custCreationForm.city;
    //  this.customerCreationData.contactNumber=custCreationForm.contactNumber;
    //  this.customerCreationData.contactPersonName=custCreationForm.contactPersonName;
    //  this.customerCreationData.country=custCreationForm.country;
    //  this.customerCreationData.custCode=custCreationForm.custCode;
    //  this.customerCreationData.custName=custCreationForm.custName;
    //  this.customerCreationData.custType=custCreationForm.custType;
    //  this.customerCreationData.emailId=custCreationForm.emailId;
    //  this.customerCreationData.pincode=custCreationForm.pincode;
    //  this.customerCreationData.state=custCreationForm.state; 

    this._ITOcreateCustomerService.CreateCustomer(custCreationForm).subscribe(res => {
      console.log(res);
      this.hidespinner = true;
      if (res.successCode == 0 && res.successMsg != null) {
        alert("Customer created");
        var win = window.open("", "_self");
          win.close();
        this._Router.navigate(['/CustHome']);
      } else {
        alert("Customer Creation Failed");
      }
      this._ITOcustomerDetailsService.newWindow.close();

    })

  }

  selectedType(value) {
    console.log(value)
    this.CountryList = [];
    for (let i = 0; i < this.Countries.length; i++) {
      if (value == "Domestic") {
        this.disableDropdownList = false;
        if (this.Countries[i].locationId == 95) {
          this.CountryList.push(this.Countries[i]);

        }
      } else {
        this.disableDropdownList = true;
        this.CountryList.push(this.Countries[i]);
      }
    }
    if (value == "Export") {
      for (let j = 0; j < this.CountryList.length; j++) {
        if (this.CountryList[j].locationId == 95) {
          this.CountryList.splice(j, 1);
        }
      }
    }
  }

  selectedCountry(countryName) {
    for (let i = 0; i < this.Countries.length; i++) {
      if (this.Countries[i].locationName == countryName) {
        for (let j = 0; j < this.States.length; j++) {
          if (this.Countries[i].locationId == this.States[j].dependLocationId) {
            this.StatesList.push(this.States[j]);
          }
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
