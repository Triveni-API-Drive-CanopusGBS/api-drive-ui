import { Component, OnInit } from '@angular/core';
import { ITOcustomerDetailsService } from './ito-customer-details.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcustInfoService } from '../ito-customer-information/ito-customer-information.service';
import { WindowRef } from './ito-customer-details.window.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOLoginService } from '../app.component.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';

@Component({
  selector: 'app-ito-customer-details',
  templateUrl: './ito-customer-details.component.html',
  styleUrls: ['./ito-customer-details.component.css']
})
export class ItoCustomerDetailsComponent implements OnInit {

  enableConsultantDiv: boolean = false;
  enableCreateCustomer: boolean = false;
  customersList: Array<any> = [];
  consultantList: Array<any> = [];
  consulList: Array<any> = [];
  contactpersonList: Array<any> = [];
  enableEndUser: boolean;
  enableCust: boolean;
  enableCR: boolean;
  sfdcCustData: any;
  custoName: string;
  SpocName: string;
  FirmName: string;
  consultAvailable: string;
  public custData: any = [];
  custdetails: string = 'custdetails';
  custTypeList: Array<any> =[];
  quotForm: any;
  savedDetails: any;
  hidespinner: boolean = true;
  customerData: Array<any> = [];
  getQuotFlag: boolean;
  enableCustDisp: boolean = false;
  quotCreated: boolean = false;
  enableConsultantDisp: boolean = false;
  custAdress: string;
  consultantAddress: string;
  oppSeqNo: string;
  typOfOfff: string;
  typOfCust: string;
  enqRer: string;
  enableNext: boolean = false;
  msgDiv: boolean = false;
  message: String = "";
  user: string = 'userDetail';
  data: Array<any> = [];
  userRegionsList: Array<any> = [];
 
  typeOfOfferArray: Array<any> = [];
  typeOfCustomerArray: Array<any> = [];
  endUserrrDetails: any;
  typOfCustomernm: string;
  enduser1 :any
  endUserNaae:any
  contactNum: string;
  emilAd:any;
  endDetails: any;
  countt: any;
  pincoood: any;
  endUserBoo: boolean = false;
  customerName : string;
  customerType : string;
  ContactPerson: string;
  customerstatus: string;
  CustomerEmail:string;
  CustomerAdd: string;
  Salesforcelink: string;
  endUserBut: boolean = false;
  isEndUserReq: number;

  endUserDet:boolean = false;
  domcust:boolean = false;
 // enableEnd: boolean = false;	
  endUser: string = "YES";
  statesList: Array<any> = []; 
  state: any;
  backBtn: boolean = false;
  disableNext: boolean = false;
mainSave:boolean=true;
  constructor(private _ITOcustomerDetailsService: ITOcustomerDetailsService,
    private router: Router, private _ActivatedRoute: ActivatedRoute, private _ITOcustInfoService: ITOcustInfoService,
    private _WindowRef: WindowRef, private domSanitizer: DomSanitizer, private _ITOLoginService: ITOLoginService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    @Inject(LOCAL_STORAGE) private storage1: WebStorageService, private _ITOeditQoutService: ITOeditQoutService) {
  
      console.log('kk');
      if(this._ITOeditQoutService.checkEdit == false){
        this.backBtn = true;
      }
      this._ITOeditQoutService.button1=true;
      this._ITOeditQoutService.button2=false;
      this._ITOeditQoutService.button3=false;
      this._ITOeditQoutService.button4=false;
      this._ITOeditQoutService.button5=false;
      this._ITOeditQoutService.button6=false;
      this._ITOeditQoutService.button7=false;
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=false;
      this._ITOcustomerRequirementService.getQuotationList().subscribe(respo =>{
        console.log(respo);
        this.quotForm = respo;
        this.typeOfOfferArray = respo.dropDownColumnvalues.typeOfOfferList.TYPE_OF_OFFER_LIST;
        this.typeOfCustomerArray = respo.dropDownColumnvalues.typeOfCustomerList.TYPE_OF_CUSTOMER_LIST;
      //  this.statesList = respo.dropDownColumnvalues.stateList.stateList;
      this.endUser = "YES"
        console.log(this.typeOfCustomerArray);
        //console.log(this.statesList);

      // customer data is saved, display it 
    this.custData[this.custdetails] = this.storage.get(this.custdetails);
    console.log(this.custData[this.custdetails])

    this.data[this.user] = this.storage.get(this.user);
    this.userRegionsList = this.data[this.user].userRegionsList;
    console.log(this.userRegionsList);
    
    if(this._ITOcustomerRequirementService.quotForm.saveBasicDetails.isEndUserReq==0)
    {
     this.endUser = "NO"
     this.domcust = true;
    }
    else
    {
      this.endUser = "YES"
     this.domcust = false;
    }
    if (this.custData[this.custdetails] == null) {
    this.oppSeqNo = this.custData[this.custdetails].oppSeqNo;

     this.typOfOfff = this.custData[this.custdetails].typeOfOffer;
      this.enqRer = this.custData[this.custdetails].enquiryReference;
     

    //  if (this.custData[this.custdetails].typOfCust == 'DM')
    //   { this.typOfCust = 'Domestic'; 
    //     this.domcust = true;
    // }
    //   else  if (this.custData[this.custdetails].typOfCust == 'EX')
    //   { this.typOfCust = 'Export'; 
        
    //   }
      

    this.enduser1=this._ITOcustomerRequirementService.quotForm.saveBasicDetails.isEndUserReq;
    console.log(this.enduser1);
    console.log(this._ITOcustomerDetailsService.customerDetailsForm.endUserName);
     
    if (this.enduser1 == 1)
{ this.endUser = "YES";


this.endUserDet = false;
this.endUserBoo = false;

 }
else if (this.enduser1 == 0)
{
   this.endUser = "NO"; 

if(this._ITOcustomerRequirementService.quotForm.saveBasicDetails.custCode=="DM")
{
  
  this.typOfCust="Domestic";
  this.domcust = true;
  this.endUserNaae=this._ITOcustomerRequirementService.quotForm.saveBasicDetails.endUserName;
  this.endDetails=this._ITOcustomerRequirementService.quotForm.saveBasicDetails.endUserAddress;
  if(this._ITOcustomerRequirementService.editFlag)
  {
    this.endUserNaae=this._ITOcustomerDetailsService.customerDetailsForm.endUserName;
  this.endDetails=this._ITOcustomerDetailsService.customerDetailsForm.endUserAddress;
  }
  if(this._ITOcustomerDetailsService.customerDetailsForm.editflag)
  {
    this.endUserNaae;
  this.endDetails
  }
}
else if(this._ITOcustomerRequirementService.quotForm.saveBasicDetails.custCode=="EX")
{
  this.typOfCust="Export";
  this.domcust = false;
  this.endUserNaae=this._ITOcustomerRequirementService.quotForm.saveBasicDetails.endUserName;
  this.endDetails=this._ITOcustomerRequirementService.quotForm.saveBasicDetails.endUserAddress;
  if(this._ITOcustomerRequirementService.editFlag)
  {
    this.endUserNaae=this._ITOcustomerDetailsService.customerDetailsForm.endUserName;
  this.endDetails=this._ITOcustomerDetailsService.customerDetailsForm.endUserAddress;
  }
}
this.endUserDet = true;
this.endUserBoo = true;

}

    
     //this.typOfCust = this.custData[this.custdetails].typOfCust;
     //this.typOfCustomer = this.custData[this.custdetails].typOfCust;

     this.isEndUserReq = this.custData[this.custdetails].isEndUserReq;
     //this.endUser = this.custData[this.custdetails].endUser;

     this.state = this.custData[this.custdetails].stateId;
    // this.stateNm = this.custData[this.custdetails].stateNm;

     //this.state = this.custData[this.custdetails].state;
// if(this._ITOcustomerRequirementService==undefined)
// {
//   if( this._ITOcustomerDetailsService.customerDetailsForm!=undefined)
//   {
   
  
//   if(this._ITOcustomerDetailsService.customerDetailsForm.custCode!=null)
//   {
//     this.typOfCust=
//     this.state
//     this.endUserNaae
//     this.endDetails
//     this.endUser="YES";
//   }
// }

  
//  }
    console.log(this.typOfCust);
    console.log(this.isEndUserReq);
    console.log(this.state);


     for (let typOfOfff of this.typeOfOfferArray) {
      if (typOfOfff.categoryDetDesc == this.typOfOfff) {
        typOfOfff.defaultValFlag = true;
      }
    }

    // if ( this.typOfCust == 'DM')
    // {    
    //   this.typOfCustomernm = 'Domestic';
    // }
    // else
    // {
    //   this.typOfCustomernm = 'Export';
    // }
    this.typOfCustomernm = this.typOfCust;

    for (let typOfCust of this.typeOfCustomerArray) {
          if (typOfCust.categoryDetDesc == this.typOfCustomernm) {
            typOfCust.defaultValFlag = true;
          }
        }  
          // console.log(typOfCust.defaultValFlag); 
          
          // if ( this.typOfCust == 'DM')
          //   {    
          //   this.domcust = true;
          //   }
          // else
          // {    
          //   this.domcust = false;
          //   }
   

   //console.log(typOfCust.defaultValFlag); 


    

console.log(this.isEndUserReq);

// if(this.enduser1==0)
// {
  
// }
    // if( this.custData[this.custdetails].custDet.endUserAddress != null ||
    //   this.custData[this.custdetails].custDet.endUserContactNumber != null ||
    //   this.custData[this.custdetails].custDet.endUserEmailId != null ||
    //   this.custData[this.custdetails].custDet.endUserName != null){
    // //this.endUserBoo = true;
    
    // this.endUserDet = true;


    // this.endUserNaae = this.custData[this.custdetails].custDet.endUserName;
    // this.contactNum = this.custData[this.custdetails].custDet.endUserContactNumber;
    // this.emilAd = this.custData[this.custdetails].custDet.endUserEmailId;
    //this.endDetails = this.custData[this.custdetails].custDet.endUserAddress;


   // }

    console.log('MkkM');
    //this.endUser="YES";
      
      this.sfdcCustData = this.custData[this.custdetails].custDet;
      if (this._ITOcustomerRequirementService.editFlag)
      {
       this.sfdcCustData=this._ITOcustomerRequirementService.saveBasicDet;
       console.log(this.sfdcCustData);
       console.log(this.sfdcCustData.contactPersonName);
       console.log(this.sfdcCustData.customerStatus);
       console.log(this.sfdcCustData.custName);
       this.sfdcCustData.customerStatus=this._ITOcustomerRequirementService.saveBasicDet.custStatus;
       this.sfdcCustData.contactPersonName=this._ITOcustomerRequirementService.saveBasicDet.custContactPersonName;
      this.sfdcCustData.contactNumber=this._ITOcustomerRequirementService.saveBasicDet.custContactNumber;
      this.sfdcCustData.emailId=this._ITOcustomerRequirementService.saveBasicDet.custEmailId;
      this.sfdcCustData.address=this._ITOcustomerRequirementService.saveBasicDet.custAddress;
      this.sfdcCustData.sfdcLink=this._ITOcustomerRequirementService.saveBasicDet.sfdcLink;
       console.log(this.sfdcCustData.customerStatus);
        if(this.sfdcCustData.custType == null){
          this.sfdcCustData.custType = this._ITOcustomerRequirementService.saveBasicDet.custCodeNm;
        }
      }
      if (this.sfdcCustData.custName != null) {
        if(this._ITOcustomerRequirementService.editFlag)
        {
          
          this._ITOcustomerDetailsService.oppSeqNo=this._ITOcustomerRequirementService.saveBasicDet.opportunitySeqNum;
          console.log( this._ITOcustomerDetailsService.oppSeqNo);

        }
        else
        {
        this._ITOcustomerDetailsService.oppSeqNo = this.custData[this.custdetails].oppSeqNo;
        }
        this.enableNext = true;
      }
    }

  });
  }


  ngOnInit() {
    //once opurtunity seq no is saved for a particular quotation in db, it should not be edited
    //again, that is handled below
    if (this._ITOcustomerRequirementService.editFlag == true) {
      this._ITOLoginService.costEst = false;
    }
    else {
      this._ITOLoginService.costEst = true;
    }
    console.log(this._ITOLoginService.costEst);
    if (this._ITOcustomerRequirementService.saveBasicDet) {
      console.log(this._ITOcustomerRequirementService.saveBasicDet)
      this.quotCreated = true;
    }

    this._ITOcustomerDetailsService.getStatesData().subscribe(res => {
      console.log(res);
      this.statesList = res.dropDownColumnvalues.stateList.stateList;
        console.log('mm');
  console.log(this.statesList);
    });

  }



  typOfOffer(val){
    console.log(val);
    this._ITOcustomerRequirementService.typeOfOfferNm = val;
    this._ITOcustomerDetailsService.typeOfOfferNm = val;
    for (let o = 0; o < this.typeOfOfferArray.length; o++) {
      if (this.typeOfOfferArray[o].categoryDetDesc == val) {
        this._ITOcustomerRequirementService.typOfOfff = this.typeOfOfferArray[o].categoryDetId;
        this._ITOcustomerDetailsService.typOfOfff = this.typeOfOfferArray[o].categoryDetId;
      }
    }console.log(this._ITOcustomerRequirementService.typOfOfff);
  }

  typOfCustomer(val){
    console.log(val);
    this._ITOcustomerRequirementService.typeOfCustomerNm = val;
    this._ITOcustomerDetailsService.typeOfCustomerNm = val;


    for (let o = 0; o < this.typeOfCustomerArray.length; o++) {
      if (this.typeOfCustomerArray[o].categoryDetDesc == val) {
        this._ITOcustomerRequirementService.typOfCust = this.typeOfCustomerArray[o].categoryDetCode; //added MM
        this._ITOcustomerDetailsService.typOfCust = this.typeOfCustomerArray[o].categoryDetCode; //added MM
      }
    }console.log(this._ITOcustomerRequirementService.typOfCust);
    
  console.log('MkkM2');
//  this.domcust = true;
    if ( this._ITOcustomerDetailsService.typOfCust == 'DM') {    
      this.domcust = true;
    }
    else
      this.domcust = false;


  }

  // onStateChange(val) {
  //   this.messagestate = false;
  //   this.ExlCost = 0;
  // }

//console.log(stateId);

  onStateChange(val){
    console.log(val);
   
    // this._ITOcustomerRequirementService.stateId = val;
    // this._ITOcustomerDetailsService.stateId = val;
    this.state = val;
    this._ITOcustomerRequirementService.stateId = this.state;
    this._ITOcustomerDetailsService.stateId = this.state;
    // for (let o = 0; o < this.statesList.length; o++) {
    //   if (this.statesList[o].locationName == val) {
    //     this._ITOcustomerRequirementService.stateId = this.statesList[o].locationId; //added MM
    //     this._ITOcustomerDetailsService.stateId = this.statesList[o].locationId; //added MM
    //   }
    // }
    console.log(this._ITOcustomerRequirementService.stateId);
  }

  //getting customer data by sending oppurtunity sequence number to java
  getData(oppSeqNumb, typeOfffere,typOfCustmer,endUserDetail,stateId) {

    console.log(oppSeqNumb, typeOfffere,typOfCustmer,endUserDetail);
    this.message = " ";
    if (this.userRegionsList.length > 0) {
      for (let i = 0; i < this.userRegionsList.length; i++) {
        if (oppSeqNumb.includes(this.userRegionsList[i].code)) {
          this._ITOcustomerRequirementService.regionId = this.userRegionsList[i].key;
          this._ITOcustomerRequirementService.region = this.userRegionsList[i].value;
          this._ITOcustomerRequirementService.regionCode = this.userRegionsList[i].code;
          this.hidespinner = false;
          this.message = " ";
          this.msgDiv = false;
          this.oppSeqNo = oppSeqNumb;
          this.typOfOfff = typeOfffere;  
          this.typOfCust = typOfCustmer; //added MM
          //this.endUser = endUserDetail;
          this.state = stateId;
          this._ITOcustomerDetailsService.oppSeqNo = oppSeqNumb;
          this._ITOcustomerDetailsService.typeOfOfferNm = typeOfffere;
          this._ITOcustomerDetailsService.typOfCust = typOfCustmer; //added MM
          //this._ITOcustomerDetailsService.endUser = endUserdet;

          for(let l=0; l<this.typeOfOfferArray.length; l++){
            if(this.typeOfOfferArray[l].categoryDetDesc == typeOfffere){
          this._ITOcustomerDetailsService.typOfOfff = this.typeOfOfferArray[l].categoryDetId;
            }
          }

          //added MM
          for(let l=0; l<this.typeOfCustomerArray.length; l++){
            if(this.typeOfCustomerArray[l].categoryDetDesc == typOfCustmer){
          this._ITOcustomerDetailsService.typOfCust = this.typeOfCustomerArray[l].categoryDetCode;
            }

  console.log('MkkM3');
          }
          
           //added MM state
           for(let l=0; l<this.statesList.length; l++){
            if(this.statesList[l].locationName == stateId){
          this._ITOcustomerDetailsService.stateId = this.statesList[l].locationId;
            }
          }
          console.log('nn');
          console.log( this._ITOcustomerDetailsService.stateId );

          this._ITOcustomerDetailsService.getCustData(oppSeqNumb).subscribe(res => {
            console.log(res);   
            this.sfdcCustData= null;         
            if (res.custName != null) {
              this.sfdcCustData = res;
            console.log(this.sfdcCustData)
              this.enableNext = true;
              this.msgDiv = false;            
              this.message = " ";

              for (let typOfCust of this.typeOfCustomerArray) {
                if (typOfCust.categoryDetDesc == this.sfdcCustData.custType) {
                 typOfCust.defaultValFlag = true;
                }
              }
              this.disableNext = false;
              if(this.sfdcCustData.custType != "Export" && this.sfdcCustData.custType != "Domestic"){
                this._ITOLoginService.dialogMsgApp = true;
                this._ITOLoginService.dialogMsgVal = "Customer Type is not defined in Sfdc";
                this.disableNext = true;
              }else{
              if(this.sfdcCustData.custType == "Domestic"){
                if(this.sfdcCustData.oppContactStateName == null){
              this._ITOLoginService.dialogMsgApp = true;
              this._ITOLoginService.dialogMsgVal = "State is not defined in sfdc";
              this.disableNext = true;
            }
          }
            if(this.sfdcCustData.isEndUserAvailable == 'No'){
            if(this.sfdcCustData.endUserCustType != "Export" && this.sfdcCustData.endUserCustType != "Domestic"){
              this._ITOLoginService.dialogMsgApp = true;
              this._ITOLoginService.dialogMsgVal = "End User Customer type is not defined in sfdc";
              this.disableNext = true;
            }
          }
        }
          }
            else {
              if(res.oppurtunitySeqNo == null){
                this.enableNext = false;
                this.msgDiv = true;
                this.message = "Customer Details are not available";
                this.disableNext = true;
              }else{
              this.enableNext = false;
              this.msgDiv = true;
              this.message = "Incorrect customer Details;"
               this.disableNext = true;
              }
            }
            this.hidespinner = true;
          });
          break;
        }// if user dont have access to enterd oppurtunity sequence number, below error is shown
        else {
          this.msgDiv = true;
          this.message = "You dont have access to the entered region";

        }
      }
    } else {
      // if user is not assigned with any regions,  below error is shown
      this.msgDiv = true;
      this.message = "You dont have any regions assigned to you";
    }
  }

  // opening sfdc link
  openSFDC(sapAddress) {
    console.log(sapAddress);
    window.open(sapAddress, "mywindow", "status=1,toolbar=0");
  }

  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.custData[key] = this.storage.get(key);
  }

  enableyes(){
    
    this.endUserDet = false;
    console.log(this.sfdcCustData.endUserDetail)
   // this._ITOcustomerDetailsService.typOfCust = this.sfdcCustData.custCode
  //  console.log(this._ITOcustomerDetailsService.typOfCust)
  }
  enableNo(){
    this.endUserDet = true;
    console.log(this.sfdcCustData.endUserDetail)
  }
  // saving customer detail to local storage
  Customerdetails(custDet) {
    this.mainSave=false;
    console.log(custDet);
    this.endUserrrDetails = custDet;
    this.hidespinner = false;
    this._ITOcustomerDetailsService.enquiryReference = custDet.enqRerff;
    //this._ITOcustomerDetailsService.getQuotationList().subscribe(res => {

    //this._ITOcustomerDetailsService.quotForm=res;
    // this._ITOcustomerRequirementService.endUserName = custDet.endUserName;
    // this._ITOcustomerRequirementService.endUserContNo = custDet.contactNum;
    // this._ITOcustomerRequirementService.endUserdEmail = custDet.emailAdd;
    // this._ITOcustomerRequirementService.EndUserAddress = custDet.endUseAdd;
    // this.sfdcCustData.endUserDetail = this.endUser;
    // this.sfdcCustData.endUserAddress = this.endDetails;
    // this.sfdcCustData.endUserContactNumber = this.endUserrrDetails.contactNum;
    // this.sfdcCustData.endUserEmailId = this.endUserrrDetails.emailAdd;
    // this.sfdcCustData.endUserName = this.endUserNaae;
    // this.sfdcCustData.stateId = this.state;
    //this.sfdcCustData.endUserName = this.endUserrrDetails.endUserName;
    //this.sfdcCustData.customerDetailsForm = this.Customerdetails
    console.log(this.sfdcCustData);
//     let type="";
//     if(custDet.typeOfCustomer=="Export")
//     {
//       type="EX";
//     }
// else if(custDet.typeOfCustomer=="Domestic")
// {
//   type="DM";
// }
//     if (custDet.typeOfCustomer === "Domestic") {
//       this.sfdcCustData.custCode = "DM";
//     }
//     else if (custDet.typeOfCustomer === "Export") {
//       this.sfdcCustData.custCode = "EX";
//       this._ITOcustomerRequirementService.stateId = null;
//       this._ITOcustomerDetailsService.stateId = null;   
//     }
    // else
    // {
    //   this.sfdcCustData.custCode = "DM";
    // }

 


    if (this.sfdcCustData.endUserDetail == "YES")
    {
      this.quotForm.saveBasicDetails.isEndUserReq = 1;
      this._ITOcustomerRequirementService.isEndUserReq = 1;
      this._ITOcustomerDetailsService.isEndUserReq = 1;
      this.sfdcCustData.typOfCust = this.sfdcCustData.custCode;
      this._ITOcustomerRequirementService.typOfCust  = this.sfdcCustData.custCode;
      this._ITOcustomerDetailsService.typOfCust  = this.sfdcCustData.custCode;
      this._ITOcustomerDetailsService.stateId = null;
      //this._ITOcustomerDetailsService.customerDetailsForm.endUserName =  null;
      //this._ITOcustomerDetailsService.customerDetailsForm.endUserAddress = null;
      //this._ITOcustomerDetailsService.
      //this._ITOcustomerRequirementService.typeOfCustomer = this.sfdcCustData.custCode;

    }
    else if (this.sfdcCustData.endUserDetail == "NO")
    {
      this.quotForm.saveBasicDetails.isEndUserReq = 0;
    this._ITOcustomerRequirementService.isEndUserReq = 0;
    this._ITOcustomerDetailsService.isEndUserReq = 0;
    }
    // res.saveBasicDetails.opportunitySeqNum = this.oppSeqNo;
    // this._ITOcustomerDetailsService.oppSeqNo = this.oppSeqNo;
    // this._ITOcustomerRequirementService.opportunitySeqNum = this.oppSeqNo;
    // res.customerDetailsForm.custName = this.sfdcCustData.custName;
    // res.customerDetailsForm.custType = this.sfdcCustData.custType;
    // if (this.sfdcCustData.custType === "Domestic") {
    //   res.customerDetailsForm.custCode = "DM";
    // }
    // else if (this.sfdcCustData.custType === "Export") {
    //   res.customerDetailsForm.custCode = "EX";
    // }
    // res.customerDetailsForm.contactPersonName = this.sfdcCustData.contactPersonName;
    // res.customerDetailsForm.contactNumber = this.sfdcCustData.contactNumber;
    // res.customerDetailsForm.emailId = this.sfdcCustData.emailId;
    // res.customerDetailsForm.address = this.sfdcCustData.address;
    // res.customerDetailsForm.pincode = this.sfdcCustData.pincode;
    // res.customerDetailsForm.firmName = this.sfdcCustData.firmName;
    // res.customerDetailsForm.consultantName = this.sfdcCustData.consultantName;
    // res.customerDetailsForm.consultantContactNumber = this.sfdcCustData.consultantContactNumber;
    // res.customerDetailsForm.consultantEmailId = this.sfdcCustData.consultantEmailId;
    // res.customerDetailsForm.consultantAddress = this.sfdcCustData.consultantAddress;
    // res.customerDetailsForm.consultantName = this.sfdcCustData.consultantName;
    // res.customerDetailsForm.endUserName = this.sfdcCustData.endUserName;
    // res.customerDetailsForm.endUserEmailId = this.sfdcCustData.endUserEmailId;
    // res.customerDetailsForm.endUserAddress = this.sfdcCustData.endUserAddress;
    this.mainSave=false;
    console.log(this.sfdcCustData);
    // this._ITOcustomerDetailsService.customerDetailsForm = res.customerDetailsForm;
    this._ITOcustomerDetailsService.customerDetailsForm = this.sfdcCustData;
    console.log(this._ITOcustomerDetailsService.customerDetailsForm);

    this.saveInLocal(this.custdetails, {
      oppSeqNo: this.oppSeqNo,
      custDet: this.sfdcCustData,
      typeOfOffer: this.typOfOfff,
      typeOfCustomer: this.typOfCust,
      isEndUserReq: this.isEndUserReq,
      typOfCust: this.typOfCust,
      stateId: this.state,
      enquiryReference: this._ITOcustomerDetailsService.enquiryReference
      // spocName: this._ITOcustomerDetailsService.spocName,
      // hasConsultant: this._ITOcustomerDetailsService.hasConsultant
    });
    this.hidespinner = true;
    if ( this._ITOcustomerRequirementService.editFlag == true &&  this._ITOeditQoutService.checkEdit==false)
    {
      this._ITOcustomerRequirementService.editFlag = false;
      console.log(this._ITOcustomerRequirementService.quotForm);
        this.quotForm.saveBasicDetails.quotId = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.quotId;
      this.quotForm.customerDetailsForm = this._ITOcustomerDetailsService.customerDetailsForm;
      this.quotForm.saveBasicDetails.bleedTypeId = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.bleedTypeId;
      this.quotForm.saveBasicDetails.capacity = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.capacity;
      this.quotForm.saveBasicDetails.condensingTypeId = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.condensingTypeId;
      this.quotForm.saveBasicDetails.condensingTypeName = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.condensingTypeName;
      this.quotForm.saveBasicDetails.frameId = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.frameId;
      this.quotForm.saveBasicDetails.frameName = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.frameName;
      this.quotForm.saveBasicDetails.typeOfTurbine = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.typeOfTurbine;
      this.quotForm.saveBasicDetails.turbineCode = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.turbineCode;
      this.quotForm.saveBasicDetails.isFrameUpdated = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.isFrameUpdated;
      this.quotForm.saveBasicDetails.typOfBladeCode = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.typOfBladeCode;
      this.quotForm.saveBasicDetails.bladeTypeId = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.bladeTypeId;
      this.quotForm.saveBasicDetails.percentageVariation = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.percentageVariation;
      this.quotForm.saveBasicDetails.isNewProject = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.isNewProject;
      this.quotForm.saveBasicDetails.regionId = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.regionId;
    this.quotForm.saveBasicDetails.regionCode = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.regionCode;
    this.quotForm.saveBasicDetails.modifiedById = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.modifiedById;
    this.quotForm.saveBasicDetails.quotNumber = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.quotNumber;
    this.quotForm.saveBasicDetails.power = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.power;
    this.quotForm.saveBasicDetails.userRoleId = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.userRoleId;
      this.quotForm.saveBasicDetails.opportunitySeqNum = this.oppSeqNo;    
      this.quotForm.saveBasicDetails.oppContactStateName = this._ITOcustomerDetailsService.customerDetailsForm.oppContactStateName;
     // this.quotForm.saveBasicDetails.sfdcCustData = this.sfdcCustData;
     this.quotForm.saveBasicDetails.custName = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.custName;
    this.quotForm.saveBasicDetails.custType = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.custType;
    this.quotForm.saveBasicDetails.custCode = null;
    this.quotForm.saveBasicDetails.endUserName = custDet.endUserName;
    this.quotForm.saveBasicDetails.endUserContactNo = this.endUserrrDetails.contactNum;
    this.quotForm.saveBasicDetails.endUserEmail = this.endUserrrDetails.emailAdd;
    this.quotForm.saveBasicDetails.endUserAddress =custDet.endUseAdd;
    this.quotForm.saveBasicDetails.custAddress = this.sfdcCustData.address;
    this.quotForm.saveBasicDetails.custContactNumber = this.sfdcCustData.contactNumber;
    this.quotForm.saveBasicDetails.custContactPersonName = this.sfdcCustData.contactPersonName;
    this.quotForm.saveBasicDetails.custEmailId = this.sfdcCustData.emailId;
    this.quotForm.saveBasicDetails.custId = this.sfdcCustData.custId;
    this.quotForm.saveBasicDetails.typeOfOfferNm = this._ITOcustomerDetailsService.typeOfOfferNm;
    this.quotForm.saveBasicDetails.typeOfOffer = this._ITOcustomerDetailsService.typOfOfff;
    this.quotForm.saveBasicDetails.typeOfCustomer = this._ITOcustomerDetailsService.typOfCust;
    this.quotForm.saveBasicDetails.typOfCust = this._ITOcustomerDetailsService.typOfCust;
    this.quotForm.saveBasicDetails.typeOfCustomerNm = this._ITOcustomerDetailsService.typeOfCustomerNm;
    this.quotForm.saveBasicDetails.improvedImpulse = this._ITOcustomerRequirementService.improvedImpulse;
    this.quotForm.saveBasicDetails.stateId = custDet.stateId;
    //this.quotForm.saveBasicDetails.stateId = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.stateId;
    // if (this.endUser == "YES")
    // {
    //   this.quotForm.saveBasicDetails.isEndUserReq = 1;
    //  this._ITOcustomerDetailsService.isEndUserReq = 1;
    //  this._ITOcustomerRequirementService.isEndUserReq = 1;

    // }
    // else if (this.endUser == "NO")
    // {
    //   this.quotForm.saveBasicDetails.isEndUserReq = 0;
    //   this._ITOcustomerDetailsService.isEndUserReq = 0;
    //   this._ITOcustomerRequirementService.isEndUserReq = 0;

    // }

   // console.log(this.sfdcCustData.endUserDetail)
    // if (this.sfdcCustData.endUserDetail == "YES")
    // {
    //   this.quotForm.saveBasicDetails.isEndUserReq = 1;
    //   this._ITOcustomerRequirementService.isEndUserReq = 1;
    //   this._ITOcustomerDetailsService.isEndUserReq = 1;
    // }
    // else if (this.sfdcCustData.endUserDetail == "NO")
    // {
    //   this.quotForm.saveBasicDetails.isEndUserReq = 0;
    // this._ITOcustomerRequirementService.isEndUserReq = 0;
    // this._ITOcustomerDetailsService.isEndUserReq = 0;
    // }
    this.quotForm.saveBasicDetails.isEndUserReq = this._ITOcustomerRequirementService.isEndUserReq;
    this.quotForm.saveBasicDetails.typeOfQuot = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.typeOfQuot;
    this.quotForm.saveBasicDetails.typeOfQuotNm = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.typeOfQuotNm;
    // this.quotForm.saveBasicDetails.typeOfInjection = this._ITOcustomerRequirementService.typeOfInject;
    // this.quotForm.saveBasicDetails.typeOfInjectionNm = this._ITOcustomerRequirementService.typeOfInjectNm;
    this.quotForm.saveBasicDetails.typeOfVarient = this._ITOcustomerRequirementService.typeOfVarient;
    this.quotForm.saveBasicDetails.typeOfVarientNm = this._ITOcustomerRequirementService.typeOfVarientNm;
    this.quotForm.saveBasicDetails.orientationTypeId = this._ITOcustomerRequirementService.quotForm.saveBasicDetails.orientationTypeId;
    this.quotForm.saveBasicDetails.enquiryReference = this._ITOcustomerDetailsService.enquiryReference;
      console.log(this.quotForm.saveBasicDetails)
      this._ITOcustomerRequirementService.saveBasicDetails(this.quotForm).subscribe(resp => {
        console.log(resp);
        this._ITOcustomerRequirementService.saveBasicDet = resp.saveBasicDetails;
        this._ITOcustomerRequirementService.quotForm = resp;
        this._ITOcustomerRequirementService.quotNumber = resp.saveBasicDetails.quotNumber;
        //console.log(this._ITOcustomerRequirementService.saveBasicDet);
        this.hidespinner = false;    
        console.log(this._ITOcustomerDetailsService.customerDetailsForm );
        this.router.navigate(['/EditQuot']);
        
          });      
    
    }
    else
    this.router.navigate(['CostEstimation/CustomerInformation/CustomerRequirement']);

    // })
  }

//To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}

}
