
import { Component, OnInit ,Input ,OnChanges } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import {ITOcustInfoService} from './ito-customer-information.service';
import { DomSanitizer } from '@angular/platform-browser';
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service';
import { Inject} from '@angular/core';

@Component({
  selector: 'app-ito-customer-information',
  templateUrl: './ito-customer-information.component.html',
  styleUrls: ['./ito-customer-information.component.css']
})
export class ItoCustomerInformationComponent implements OnChanges {

  enableCD:boolean;
  enableED:boolean;
  enableCR:boolean;
  users:string='user';
  userDetail:string='userDetail';
  customerReqrmnt:string='customerReq';
  endUserDetail:string='endUserDetail';
  custdetails:string='custdetails';
  customerInfo:Array<any>=[];

  disabl:boolean=false;
  constructor(private _ActivatedRoute:ActivatedRoute , private router:Router,
    private _ITOcustInfoService:ITOcustInfoService ,private domSanitizer: DomSanitizer , 
     @Inject(LOCAL_STORAGE) private storage: WebStorageService ) { 

    this.router.navigate(['CostEstimation/CustomerInformation/CustomerDetails']);
  }

  ngOnChanges(){
    // this.enableED=this._ITOcustInfoService.enableEndUser;
    // this.enableCR=this._ITOcustInfoService.enableCR;
    // this.enableCD=this._ITOcustInfoService.enableCust;
  }

}
