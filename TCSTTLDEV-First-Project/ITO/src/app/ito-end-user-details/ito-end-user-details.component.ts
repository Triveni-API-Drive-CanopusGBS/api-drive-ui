import { Component, OnInit } from '@angular/core';
import {ITOendUserDetailsService} from './ito-end-user-details.service';
import {ITOcustomerDetailsService} from '../ito-customer-details/ito-customer-details.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import {WindowRef} from '../ito-customer-details/ito-customer-details.window.service';
import { DomSanitizer } from '@angular/platform-browser';
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service';
import { Inject} from '@angular/core';

@Component({
  selector: 'app-ito-end-user-details',
  templateUrl: './ito-end-user-details.component.html',
  styleUrls: ['./ito-end-user-details.component.css']
})
export class ItoEndUserDetailsComponent implements OnInit {

  endUserList:Array<any>=[];
  enableEndUserDiv:boolean=false;
  nativeWindow:any;
  public endUserData:any=[];
  endUserDetail:string='endUserDetail';
  endUserName:string;
  endUserAvail:string;
  hidespinner: boolean = true;
  getQuotFlag: boolean;

  constructor(private _ITOendUserDetailsService:ITOendUserDetailsService, private _ITOcustomerDetailsService:ITOcustomerDetailsService ,
  private _ActivatedRoute:ActivatedRoute ,private router:Router ,private _WindowRef:WindowRef ,
  private domSanitizer: DomSanitizer ,  @Inject(LOCAL_STORAGE) private storage: WebStorageService  ) {
    this.endUserAvail="YES"; 
   // console.log(this._ITOcustomerDetailsService.customerID);
    this.nativeWindow = _WindowRef.getNativeWindow();
    this.getQuotationList();
  }

  ngOnInit() {
  }

  getQuotationList(){
    this.hidespinner = false;
    this.endUserData[this.endUserDetail]=this.storage.get(this.endUserDetail);
    if(this.endUserData[this.endUserDetail]!=null){
      this.endUserAvail=this.endUserData[this.endUserDetail].EndUseravailable;
      console.log(this.endUserAvail);
      if(this.endUserAvail=="YES"){
        this.enableEndUserDiv=false;
      }
      else if(this.endUserAvail=="NO"){
        this.enableEndUserDiv=true;
      }
      console.log(this.enableEndUserDiv);
      this.endUserName=this.endUserData[this.endUserDetail].EndUserName;
    
    }
    this._ITOendUserDetailsService.getQuotationList().subscribe(res=>{
      console.log(res);
      this.endUserList=res.customerProfileForm.endUserList;
      this.hidespinner = true;
    })
  }

  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.endUserData[key]= this.storage.get(key);
   }
  enableEndUser(){
    this.enableEndUserDiv=true;
  }
  disableEndUser(){
    this.enableEndUserDiv=false;
  }
  openEndUserwindow(){
    this.getQuotFlag=true;
    //this._ITOcustomerDetailsService.newWindow = this.nativeWindow.open('/ITO/#/CreateEndUser');
  }
  endUserDetails(EndUserSelForm){
    this.hidespinner = false;
    console.log(EndUserSelForm);
    this._ITOendUserDetailsService.hasEndUser=EndUserSelForm.enableConsultant;
    if(EndUserSelForm.enableConsultant=="YES"){
      //this._ITOendUserDetailsService.endUserId=this._ITOcustomerDetailsService.customerID;
      //this._ITOendUserDetailsService.endUserName=this._ITOcustomerDetailsService.custName;
    }else if(EndUserSelForm.enableConsultant=="NO")
    {
      this._ITOendUserDetailsService.getQuotationList().subscribe(res=>{
        for(let i=0;i<res.customerProfileForm.endUserList.length;i++){
          if(res.customerProfileForm.endUserList[i].endUserName.trim()==EndUserSelForm.endUser.trim()){
            this._ITOendUserDetailsService.endUserId=res.customerProfileForm.endUserList[i].endUserId;
            this._ITOendUserDetailsService.endUserName=EndUserSelForm.endUser;
            break;
          }
        }
        
      })
    }
  
      this.saveInLocal(this.endUserDetail,{EndUseravailable:this._ITOendUserDetailsService.hasEndUser,
      EndUserName:this._ITOendUserDetailsService.endUserName});
      this.hidespinner = true;
      this.router.navigate(['CostEstimation/CustomerInformation/CustomerRequirement']);
  console.log( this._ITOendUserDetailsService.endUserName)
  }

  onFocus(){
      this.getQuotationList();
      this.getQuotFlag =false;
  }

}
