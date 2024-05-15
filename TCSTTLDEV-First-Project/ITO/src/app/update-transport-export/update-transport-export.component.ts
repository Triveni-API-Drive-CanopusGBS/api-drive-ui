import { Component, OnInit } from '@angular/core';
import {updateTransportExportService} from './update-transport-export.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import {updateTransportExportDetails} from './update-transport-export';
import { ITOcustomerRequirementService} from '../ito-customer-requirement/ito-customer-requirement.service';

@Component({
  selector: 'app-update-transport-export',
  templateUrl: './update-transport-export.component.html',
  styleUrls: ['./update-transport-export.component.css']
})
export class UpdateTransportExportComponent implements OnInit {
  hideprogress: boolean = false;
  //variable to store selected component name
  selcompoName:any;
  //variable to store selected vehicle name
  selvehicleName:any;
  //variable to store selected place
  selfobplace:any;
  //variable to select number of vehicles
  selPrice:any;
  //array to store components details
  componentArray: Array<any> = [];
  //Array to store place array
  placeArray:Array<any> = [];
  //Array to store vehicle
  vehicleArray:Array<any> = [];
  //Variable to store data temp
  datatemp: any;
  //variable to store previous number of vehicles
  previousPrice: any;
  //variable to store cols
  cols: { field: string; header: string; }[];
  //Variable to store user
  user: string = 'userDetail';
  currentRoleId: string = 'selRoleId';
  //Variable to store data
  data: Array<any> = [];
  //variable to store user ID
  userId:any;
//  variable to store assigne
  assignee: any;
  //Flag for display dialog
  displayDialog:boolean=false;
  //Flag for display add dialog
  displayAddDialog:boolean=false;
//Array to store frame vehicle update array
  FrameVehicleUpdateArray:Array<any>=[];
  //Array to store turbine design
  turbineDesignArray:Array<any>=[];
  //Array to store turbine type
  turbineTypeArray:Array<any>=[];
  //Array to store final range list
  finalRangeList: Array<any> = [];
  //variable to store selected range
  selectedRange:any;
  //heading value display
  headingValue:string="Transportation - Export";
//variable to store vehicle with price
  vehiclesWithPrice: Array<any> = [];
// flag to check edit mode
  transpEdit: boolean = false;
  //flag to check approver mode
  transpApp: boolean = false;
  //flag to check reviewer mode
  transRev: boolean = false;
  //flag to store local storage
  localStorageValues: Array<any> = [];
  //variable to store login details
  loginUserDetails: string = "userDetail";
  //variable to store user roles
  userRoles: Array<any> = [];
  //variable to store label name
  labelName: string = '';
  //variable to store new users list
  newUsersLilst: Array<any> = [];
  //variable to store selected UR
  selectedUR: any;
  //variable to store temporary response
  reponseTemp: any;
  //variable to store previous remarks
  prevRemarks: Array<any> = [];
  //variable to store user list
  usersList: Array<any> = [];
  //variable to save basic
  saveBasic: any;
  //variable to store temp resp
  tempResp: any;
  //variable to store modified by
  modifiedBy: any;
  //variable to store success message
  successMsg: Array<any> = [];
  //variable to assign display
  assignDisp: boolean;
  //variable to update type
  updateType: updateTransportExportDetails;
  //variable to remarks
  remarkss: any;
  //variable to store current role
  currentRole: string = 'selRole';
  //variable to display message
  displayMessage: boolean = false;
  // variable to display frame vehicle udate array temp
  FrameVehicleUpdateArrayTemp:Array<any> = [];
  // variable to select range temp
  selectedRangeTemp: Array<any> =[];
  framesWithExportDetails: Array<any> = [];
  framesWithExportType: Array<any> = [];
  custType: any;
  customerType: Array<any> = [];
  transportationType: Array<any> = [];
  newTransportationType: Array<any> = [];
  tempRespAd: any;
  gridDisp: boolean = false;
  transTypNm:string = ' ';
  dispDropdown:boolean = true;
  newarra:Array<any> = [];
  expCd: string ='';
  adminForm:any;/// to send bulk data
  percentage:number=0;
  displayDialogBulk:boolean=false;
  displayMessageBulk:boolean=false;

  constructor(private _updateTransportExportService: updateTransportExportService, private _ITOcustomerRequirementService: ITOcustomerRequirementService, private router: Router,
    private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOMyWorkListPageService: ITOMyWorkListPageService) {
      this.hideprogress = true;
      //Method to get the loggedin userID
      this.assignee = this.data[this.user] = this.storage.get(this.user);
     this.userId = this.data[this.user].userId;
  console.log("userIddddddd::"+this.userId);
  this._updateTransportExportService.getTransportationCache().subscribe(res => {
    console.log(res);
    this.componentArray = res.dropDownColumnvalues.componentTypes.TransportComponentTypeList;    
    this.placeArray = res.dropDownColumnvalues.placeList.placeList;
    this.vehicleArray = res.dropDownColumnvalues.vehiclesList.VEHICLE_LIST;
    this.transportationType = res.dropDownColumnvalues.transportationType.TransportTypes;
    console.log(this.vehicleArray);
    this.usersList = res.userDetailsList;
    console.log("UserLis");
    console.log(this.usersList);
        
          for (let t = 0; t < this.transportationType.length; t++) {
            if (this.transportationType[t].dependentCode == 'EX') {
              this.newTransportationType.push(this.transportationType[t]);
            }
          }console.log(this.newTransportationType);

  
  this.finalRangeList = [];
  this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
  this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
  console.log(this.userRoles);
  this._updateTransportExportService.addOrEditForm().subscribe(resp1 => {
      console.log(resp1);
      this.tempRespAd = resp1;
  this._updateTransportExportService.getVehicleCache(resp1).subscribe(res => {
    console.log("nidhii check");
    console.log(res);
    //Filter records based on sub item Name
    // this.framesWithExportDetails = res.transportDetailsList.reduce((acc, current) => {
    //   console.log(acc, current);
    //   const x = acc.find(item => item.frameName === current.frameName);
    //   if (!x) {
    //     return acc.concat([current]);
    //   } else {
    //     return acc;
    //   } 
    // }, []);
    this.framesWithExportDetails = res.transportDetailsList;
    this.saveBasic = res.saveBasicDetails;
    this.tempResp = res;
   
    console.log(this._ITOMyWorkListPageService.selectedUR);
    console.log(this._ITOMyWorkListPageService.selectedUR.updateCode);
    if(this._ITOMyWorkListPageService.selectedUR.updateCode=="UPD_NTR_EX"){
      this.headingValue="Newly Added Frames";
    }
    if (this._ITOMyWorkListPageService.selectedUR != '') {
      this.dispDropdown = false;
      console.log(this._ITOMyWorkListPageService.responseTemp);
      this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
      this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
    console.log(this.reponseTemp);
       
        if (this.storage.get(this.currentRole) == "TRANS_EDIT") {
          console.log("Entered if");
          this.gridDisp = true;
        // this.FrameVehicleUpdateArray = this._ITOMyWorkListPageService.responseTemp.unsavedUpdatePriceTransportListExport;
          this.transTypNm = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListExport[0].transType;
          this.saveBasic.updateRequestNumber = this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
          
          for (let i = 0; i < this._ITOMyWorkListPageService.responseTemp.unsavedUpdatePriceTransportListExport.length; i++) {
            for (let p = 0; p < this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListExport.length; p++) {
              if (this._ITOMyWorkListPageService.responseTemp.unsavedUpdatePriceTransportListExport[i].transType == this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListExport[p].transType) {
                  this.FrameVehicleUpdateArray.push(this._ITOMyWorkListPageService.responseTemp.unsavedUpdatePriceTransportListExport[i]);
                }
            }
          }
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListExport;
          for (let s = 0; s < this.FrameVehicleUpdateArray.length; s++) {
            for (let q = 0; q < this.finalRangeList.length; q++) {
              if (this.FrameVehicleUpdateArray[s].transId == this.finalRangeList[q].transId) {
                this.FrameVehicleUpdateArray[s] = this.finalRangeList[q];
                this.newarra.push(this.FrameVehicleUpdateArray[s]);
              }
            }
          }console.log(this.newarra);
          console.log(this.FrameVehicleUpdateArray);
          console.log(this.finalRangeList);
        }
        else if (this.storage.get(this.currentRole)!= "TRANS_EDIT") {
          this.gridDisp = true;
         this.FrameVehicleUpdateArray = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListExport;
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListExport;
          this.transTypNm = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListExport[0].transType;
	   }
    
 this.cols = [
  { field: 'turbineType', header: 'Model' },
      { field: 'frameName', header: 'Frame Name' },
      // { field: 'maxPower', header: 'Frame Power' },
      { field: 'condensingTypeName', header: 'Sub Type' },
      { field: 'countryName', header: 'Country Name' },
      { field: 'portName', header: 'Port Name' },
      { field: 'price', header: 'Price' }
    ];
      
      for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
        this.prevRemarks.push(this.reponseTemp.commentList[r]);
      }
      console.log("remarks");
this.cols = [
  { field: 'turbineType', header: 'Model' },
      { field: 'frameName', header: 'Frame Name' },
      // { field: 'maxPower', header: 'Frame Power' },
      { field: 'condensingTypeName', header: 'Sub Type' },
      { field: 'countryName', header: 'Country Name' },
      { field: 'portName', header: 'Port Name' },
      { field: 'previousPrice', header: 'Previous Price' },
      { field: 'price', header: 'Price' }
    ];
this._ITOMyWorkListPageService.selectedUR = '';
      console.log(this.prevRemarks);
    } else {       
       //Filter records based on sub item Name
      //  this.FrameVehicleUpdateArray = res.transportDetailsList.reduce((acc, current) => {
      //   console.log(acc, current);
      //   const x = acc.find(item => item.frameName === current.frameName);
      //   if (!x) {
      //     return acc.concat([current]);
      //   } else {
      //     return acc;
      //   } 
      // }, []);
      this.FrameVehicleUpdateArray = res.transportDetailsList;
       this.cols = [
        { field: 'turbineType', header: 'Model' },
      { field: 'frameName', header: 'Frame Name' },
      // { field: 'maxPower', header: 'Frame Power' },
      { field: 'condensingTypeName', header: 'Sub Type' },
      { field: 'countryName', header: 'Country Name' },
      { field: 'portName', header: 'Port Name' },
      { field: 'price', header: 'Price' }
    ];
        if (this.storage.get(this.currentRole) == "TRANS_EDIT") {
          this.saveBasic.updateRequestNumber = 0;
        }
      }
 


    this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
    this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;
    console.log(this.localStorageValues[this.loginUserDetails]);
    this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
    console.log("Current Role");
   console.log(this.storage.get(this.currentRole));
      switch (this.storage.get(this.currentRole)) {
        case "TRANS_EDIT": {
          this.transpEdit = true;
          this.labelName = "Please select the reviewr";
          for (let r = 0; r < this.usersList.length; r++) {
           for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
            if (this.usersList[r].rolesCodeVal[s] == "TRANS_REVIWER") {
              this.newUsersLilst.push(this.usersList[r]);
              this.saveBasic.loggedInUserCode = 0;
              
              this.saveBasic.statusId = 1;
	      }
            }
          }
          break;
        }
        case "TRANS_APPROVER": {
          this.transpApp = true;
          this.saveBasic.loggedInUserCode = 2;
          this.saveBasic.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
          break;

        }
        case "TRANS_REVIWER": {
          this.transRev = true;
          this.labelName = "Please select the approver";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
            if (this.usersList[r].rolesCodeVal[s] == "TRANS_APPROVER") {
              this.newUsersLilst.push(this.usersList[r]);
              this.saveBasic.loggedInUserCode = 1;
              this.saveBasic.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
            }
            }
          }
          break;
        }
        default: {

          break;
        }
      }
this.hideprogress = false;    
  });
});
    });
 }
 expTypeSel(exp) {
  this.gridDisp = true;
  this.expCd="";
  this.FrameVehicleUpdateArray = [];
  console.log(this.framesWithExportDetails);
  console.log(exp); 
  for(let o =0; o<this.newTransportationType.length; o++){
    if(exp == this.newTransportationType[o].categoryDetDesc){
      this.expCd = this.newTransportationType[o].categoryDetCode;
    }
  }
  for (let e = 0; e < this.framesWithExportDetails.length; e++) {
    if (this.expCd == this.framesWithExportDetails[e].transTypeCode) {
      this.FrameVehicleUpdateArray.push(this.framesWithExportDetails[e]);
    }
  }console.log(this.FrameVehicleUpdateArray);
}

  ngOnInit() {
    this._updateTransportExportService.addOrEditForm().subscribe(resp1Temp => {
      this._updateTransportExportService.getVehicleCache(resp1Temp).subscribe(resTemp => {
        console.log(resTemp);
        //Filter records based on sub item Name
        // this.FrameVehicleUpdateArrayTemp = resTemp.transportDetailsList.reduce((acc, current) => {
        //   console.log(acc, current);
        //   const x = acc.find(item => item.frameName === current.frameName);
        //   if (!x) {
        //     return acc.concat([current]);
        //   } else {
        //     return acc;
        //   } 
        // }, []);
   
        this.FrameVehicleUpdateArrayTemp = resTemp.transportDetailsList;
    });
  });
    // list to display column names required for vehicle no update
   //this.finalRangeList = [];
  

    //list to display turbine design
    this.turbineDesignArray=[
      {turbineDesign:'Impulse',code:'IM'},
      {turbineDesign:'Reactive',code:'RN'}
    ];
    //list to display turbine type
    this.turbineTypeArray=[
      {turbineType:'Back Pressure',code:'BP'},
      {turbineType:'Condensing',code:'CD'}
    ];
  }
  ngAfterViewChecked() {
    if (this.storage.get(this.currentRole) == "TRANS_EDIT") {
    console.log("inside ngAfterview");
    console.log(this.FrameVehicleUpdateArray);
    console.log(this.finalRangeList);
    for (let d = 0; d < this.FrameVehicleUpdateArray.length; d++) {
      for (let f = 0; f < this.finalRangeList.length; f++) {
        if (this.FrameVehicleUpdateArray[d].transId == this.finalRangeList[f].transId ) {
      document.getElementById(this.FrameVehicleUpdateArray[d].transId).style.backgroundColor = "#0275d8";
          this.FrameVehicleUpdateArray[d] = this.finalRangeList[f];
          console.log(this.FrameVehicleUpdateArray[d]);
          }
        }
      }
    }
  }

  

  // Edit row functionality
  onRowSelect(event) {
   // console.log(this.vehicleWithUnitP);
    console.log(event.data);
    this.displayDialog = true;
    this.selectedRange = event.data;
    for (let v = 0; v < this.FrameVehicleUpdateArray.length; v++) {
      if (this.FrameVehicleUpdateArray[v].frameName == this.selectedRange.frameName && this.FrameVehicleUpdateArray[v].maxPower == this.selectedRange.maxPower) {
        this.previousPrice = this.FrameVehicleUpdateArray[v].price;
      }
    }
  }
    // save as method
    SaveAsDraft() {
    this.successMsg = [];
    this.tempResp.updatePriceTransportList = [];
    // console.log(updatePriceTrans);
    this.saveBasic.remarks = this.remarkss;
    this.assignDisp = true;
    console.log(this.remarkss);
    console.log(this.finalRangeList);
    for (let v = 0; v < this.finalRangeList.length; v++) {
      this.updateType = new updateTransportExportDetails();
      if (this.finalRangeList[v] != undefined) {
        console.log(this.finalRangeList[v]);
        this.updateType.condensingTypeId=this.finalRangeList[v].condensingTypeId;
          this.updateType.frameName = this.finalRangeList[v].frameName;
          this.updateType.frameId = this.finalRangeList[v].frameId;
          this.updateType.transId = this.finalRangeList[v].transId;
          this.updateType.transType = this.finalRangeList[v].transType;
          this.updateType.transTypeId = this.finalRangeList[v].transTypeId;
          this.updateType.transTypeNm = this.finalRangeList[v].transTypeNm;
          this.updateType.turbineCode = this.finalRangeList[v].turbineCode;
          this.updateType.turbineDesign = this.finalRangeList[v].turbineDesign;
          this.updateType.turbineDesignCode = this.finalRangeList[v].turbineDesignCode;
          this.updateType.turbineType = this.finalRangeList[v].turbineType;
          this.updateType.countryName = this.finalRangeList[v].countryName;
          this.updateType.portId = this.finalRangeList[v].portId;
          this.updateType.price = this.finalRangeList[v].price;
          this.saveBasic.updateCode = "UPD_TR_EX";
        this.saveBasic.custType=this.finalRangeList[v].custType;
        this.saveBasic.transportationType = this.finalRangeList[v].transId;
        this.saveBasic.condensingTypeId=this.finalRangeList[v].condensingTypeId;
        this.saveBasic.modifiedById = this.modifiedBy;
        this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
        this.tempResp.transportDetailsList = [];
        this.tempResp.saveBasicDetails = this.saveBasic;
        this.tempResp.updatePriceTransportList.push(this.updateType);     
        console.log(this.tempResp);
      }
    }
    this._updateTransportExportService.updatePriceTransport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.saveBasic.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.tempResp = resp;
    
    });
    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.FrameVehicleUpdateArray.length; vv++) {
      let butn = document.getElementById(this.FrameVehicleUpdateArray[vv].transId).style.backgroundColor = "";
    }
    }

  //Save edited row
  save() {
    console.log(this.selectedRange);
    for (let d = 0; d < this.FrameVehicleUpdateArrayTemp.length; d++) {
      if (this.FrameVehicleUpdateArrayTemp[d].transId == this.selectedRange.transId) {
         this.selPrice = this.FrameVehicleUpdateArrayTemp[d].price;
       }
      }
   if(this.selectedRange.price != this.selPrice){
    if (this.finalRangeList.length != 0) {
      for (let s = 0; s < this.finalRangeList.length; s++) {
        if (this.finalRangeList[s].transId == this.selectedRange.transId)  {
          this.finalRangeList[this.finalRangeList.indexOf(this.selectedRange)] == this.selectedRange;
        }
        else {
          this.finalRangeList.push(this.selectedRange);
        }
      }
    }
    else {
      this.finalRangeList.push(this.selectedRange);
    }
    this.displayDialog = false;
    console.log(this.finalRangeList);
  }else{
    this.displayDialog = false;
  }
  }
  
  //Add new Vehicle dialog
  CreateNewVehicle(){
    this.displayAddDialog=true;
  }
  //Set assigned user on change
  assignedUser(assigne) {
    console.log(assigne);
    console.log(this.tempResp.userDetailsList);
    this.saveBasic.assignedTo = assigne;

  }
//method to updtae details in to dB
  updatePriceTransForm(updatePriceTrans) {
    console.log(updatePriceTrans);
    this.successMsg = [];
    this.tempResp.updatePriceTransportList = [];
    // console.log(updatePriceTrans);
    this.saveBasic.remarks = updatePriceTrans.coments;
    this.assignDisp = true;
    console.log(this.finalRangeList);
    this.updateType = new updateTransportExportDetails();
    console.log(this.updateType);
    console.log(this.finalRangeList);
    for (let v = 0; v < this.finalRangeList.length; v++) {
      console.log(this.finalRangeList[v]);
      if (this.finalRangeList[v] != undefined) {
        console.log(this.finalRangeList[v]);
        
        this.updateType.frameName = this.finalRangeList[v].frameName;
        this.updateType.frameId = this.finalRangeList[v].frameId;
        this.updateType.condensingTypeId=this.finalRangeList[v].condensingTypeId;
        this.updateType.condensingTypeName = this.finalRangeList[v].condensingTypeName;
        this.updateType.transId = this.finalRangeList[v].transId;
        this.updateType.transType = this.finalRangeList[v].transType;
        this.updateType.transTypeId = this.finalRangeList[v].transTypeId;
        this.updateType.transTypeCode = this.finalRangeList[v].transTypeCode;
        this.updateType.transTypeNm = this.finalRangeList[v].transTypeNm;
        this.updateType.turbineCode = this.finalRangeList[v].turbineCode;
        this.updateType.turbineDesign = this.finalRangeList[v].turbineDesign;
        this.updateType.turbineDesignCode = this.finalRangeList[v].turbineDesignCode;
        this.updateType.turbineType = this.finalRangeList[v].turbineType;
        this.updateType.countryName = this.finalRangeList[v].countryName;
        this.updateType.portId = this.finalRangeList[v].portId;
        this.updateType.portName = this.finalRangeList[v].portName;
        this.updateType.price = this.finalRangeList[v].price;
        console.log(this.updateType);
              
        this.saveBasic.updateCode = "UPD_TR_EX";
        this.saveBasic.custType=this.finalRangeList[v].custType;
        this.saveBasic.transportationType = this.finalRangeList[v].transId;
        this.saveBasic.condensingTypeId=this.finalRangeList[v].condensingTypeId;
        this.saveBasic.modifiedById = this.modifiedBy;
        this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
        this.tempResp.transportDetailsList = [];
        if (updatePriceTrans.status == "Accept") {
          this.saveBasic.statusId = 1;
        }
        else if (updatePriceTrans.status == "Reject") {
          this.saveBasic.statusId = 0;
        }
        this.tempResp.saveBasicDetails = this.saveBasic;
        this.tempResp.updatePriceTransportList.push(this.updateType);
        console.log(this.tempResp);
      }
    }
    console.log("nidhi");
    console.log(this.tempResp);
    console.log("end");
    this._updateTransportExportService.updatePriceTransport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "TRANS_APPROVER") {
          this._updateTransportExportService.updateStatus(resp).subscribe(respon => {
            console.log(respon);
          this.successMsg.push(respon.successMsg);
            this._updateTransportExportService.saveTransportPrice(respon).subscribe(respo => {
              console.log(respo);
            this.successMsg.push(respo.successMsg);
            })
        });

        } else {
          this._updateTransportExportService.updateStatus(resp).subscribe(respon => {
            console.log(respon);
          this.successMsg.push(respon.successMsg);
          })
        }

    });

    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedRange = '';
    // for (let vv = 0; vv < this.FrameVehicleUpdateArray.length; vv++) {
    //   let butn = document.getElementById(this.FrameVehicleUpdateArray[vv].transId).style.backgroundColor = "";
    // }
    console.log(this.successMsg)
  }
  //Method called on clisck of close on dialog open
  closeMessage() {
    this.displayMessage = false;
    this.finalRangeList = [];
    this.remarkss = '';
    this.successMsg = [];
    //this.acceptedOnly = true;
    this.navigateToMyWorkflow();
    for (let d = 0; d < this.FrameVehicleUpdateArray.length; d++) {
      for (let f = 0; f < this.finalRangeList.length; f++) {
        if (this.FrameVehicleUpdateArray[d].transId == this.finalRangeList[f].transId) {
          document.getElementById(this.FrameVehicleUpdateArray[d].transId).style.backgroundColor = "#0275d8";
          console.log(this.FrameVehicleUpdateArray[d]);
        }
      }
    }

  } 
  navigateToMyWorkflow() {
    this.router.navigate(['MyWorkFlow']);
  }
  saveBulk()
  {
    this._updateTransportExportService.getAdminForm().subscribe(res => {
      console.log(res);
      this.adminForm = res;
      this.adminForm.percent=this.percentage;
      this.adminForm.modifiedBy=this.modifiedBy;
    
    this._updateTransportExportService.getAdminPercentTrnsDm(this.adminForm).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.displayDialogBulk=false;
      this.displayMessageBulk = true;


    });
  });
  }
  bulkUpd()
  {
    this.percentage=0;
    this.displayDialogBulk=true;

  }
}

