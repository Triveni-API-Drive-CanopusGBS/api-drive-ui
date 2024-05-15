import { Component, OnInit } from '@angular/core';
import {updateVehicleNoDetails} from './ito-admin-update-vehicle-no';
import {ItoUpdateVehicleService} from './ito-admin-update-vehicle-no.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';


@Component({
  selector: 'app-ito-admin-update-vehicle-no',
  templateUrl: './ito-admin-update-vehicle-no.component.html',
  styleUrls: ['./ito-admin-update-vehicle-no.component.css']
})
export class ItoAdminUpdateVehicleNoComponent implements OnInit {
//variable to store selected component name
  selcompoName:any;
  //variable to store selected vehicle name
  selvehicleName:any;
  //variable to store selected place
  selfobplace:any;
  //variable to select number of vehicles
  selnumberOfVehicle:any;
  //array to store components details
  componentArray: Array<any> = [];
  //Array to store place array
  placeArray:Array<any> = [];
  //Array to store vehicle
  vehicleArray:Array<any> = [];
  //Variable to store data temp
  datatemp: any;
  //variable to store previous number of vehicles
  previousNumberVehicle: any;
  //variable to store cols
  cols: { field: string; header: string; }[];
  //Variable to store user
  user: string = 'userDetail';
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
  headingValue:string="Transportation - Domestic";
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
  updateType: any;
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

  constructor(private _ItoUpdateVehicleService: ItoUpdateVehicleService, private router: Router,
    private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOMyWorkListPageService: ITOMyWorkListPageService) {
      //Method to get the loggedin userID
      this.assignee = this.data[this.user] = this.storage.get(this.user);
     this.userId = this.data[this.user].userId;
  console.log("userIddddddd::"+this.userId);
  this._ItoUpdateVehicleService.getTransportationCache().subscribe(res => {
    console.log(res);
    this.componentArray = res.dropDownColumnvalues.componentTypes.TransportComponentTypeList;    
    this.placeArray = res.dropDownColumnvalues.placeList.placeList;
    this.vehicleArray = res.dropDownColumnvalues.vehiclesList.VEHICLE_LIST;
    console.log(this.vehicleArray);
    this.usersList = res.userDetailsList;
    console.log("UserLis");
    console.log(this.usersList);
      //});


  
  this.finalRangeList = [];
  this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
  this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
  console.log(this.userRoles);
  this._ItoUpdateVehicleService.addOrEditForm().subscribe(resp1 => {
      console.log(resp1);
  this._ItoUpdateVehicleService.getVehicleCache(resp1).subscribe(res => {
    console.log("nidhii check");
    console.log(res);
        //Filter records based on sub item Name
        // this.FrameVehicleUpdateArray = res.transportDetailsList.reduce((acc, current) => {
        //   console.log(acc, current);
        //   const x = acc.find(item => item.frameName === current.frameName);
        //   if (!x) {
        //     return acc.concat([current]);
        //   } else {
        //     return acc;
        //   } 
        // }, []);
    this.FrameVehicleUpdateArray = res.transportDetailsList; 
    this.saveBasic = res.saveBasicDetails;
    this.tempResp = res;
   
    console.log(this._ITOMyWorkListPageService.selectedUR);
    console.log(this._ITOMyWorkListPageService.selectedUR.updateCode);
    if(this._ITOMyWorkListPageService.selectedUR.updateCode=="UPD_NTR_NV"){
      this.headingValue="Newly Added Frames";
    }
    if (this._ITOMyWorkListPageService.selectedUR != '') {
      console.log(this._ITOMyWorkListPageService.responseTemp);
      this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
      this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
    console.log(this.reponseTemp);
   
        if (this.storage.get(this.currentRole) == "TRANS_EDIT") {
          console.log("Entered if");
         this.FrameVehicleUpdateArray = this._ITOMyWorkListPageService.responseTemp.unsavedUpdatePriceTransportListDomestic;
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListDomestic;
          this.saveBasic.updateRequestNumber = this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
          console.log(this.finalRangeList);
          console.log(this.FrameVehicleUpdateArray);
        }
        else if (this.storage.get(this.currentRole)!= "TRANS_EDIT") {
         this.FrameVehicleUpdateArray = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListDomestic;
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportListDomestic;
	   }
    
 this.cols = [
  { field: 'turbineType', header: 'Model' },
      { field: 'frameName', header: 'Frame Name' },
      // { field: 'maxPower', header: 'Frame Power' },
      { field: 'compoName', header: 'Component' },
      { field: 'fobplace', header: 'Place' },
      { field: 'vehicleName', header: 'Vehicle Name' },
      { field: 'numberOfVehicle', header: 'No.of Vehicles' }
    ];
      
      for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
        this.prevRemarks.push(this.reponseTemp.commentList[r]);
      }
      console.log("remarks");
this.cols = [
  { field: 'turbineType', header: 'Model' },
      { field: 'frameName', header: 'Frame Name' },
      // { field: 'maxPower', header: 'Frame Power' },
      { field: 'compoName', header: 'Component' },
      { field: 'prevCompoName', header: 'Prv Component' },
      { field: 'fobplace', header: 'Place' },
      { field: 'prevFOBPlace', header: 'Prv Place' },
      { field: 'vehicleName', header: 'Vehicle Name' },
      { field: 'prevVehicleName', header: 'Prv Vehicle Name' },
      { field: 'numberOfVehicle', header: 'No.of Vehicles' },
      { field: 'prevNoOfvehicles', header:'Prv No. of vehicles'}
    ];
this._ITOMyWorkListPageService.selectedUR = '';
      console.log(this.prevRemarks);
    } else {
       //Filter records based on sub item Name
      //  this.FrameVehicleUpdateArrayTemp = res.transportDetailsList.reduce((acc, current) => {
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
      { field: 'compoName', header: 'Component' },
      { field: 'fobplace', header: 'Place' },
      { field: 'vehicleName', header: 'Vehicle Name' },
     { field: 'numberOfVehicle', header: 'No.of Vehicles' }
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
    
  });
});
    });
 }

  ngOnInit() {
    this._ItoUpdateVehicleService.addOrEditForm().subscribe(resp1Temp => {
      this._ItoUpdateVehicleService.getVehicleCache(resp1Temp).subscribe(resTemp => {
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
        // this.FrameVehicleUpdateArrayTemp = resTemp.transportDetailsList.filter((x) => {
        //   return ((x.lhsFlag ==1)&& (x.newDesItemFlag==0) && (x.newDesSubItemFlag==0));
        // })
        this.FrameVehicleUpdateArrayTemp = resTemp.transportDetailsList
        console.log(this.FrameVehicleUpdateArrayTemp);
    });
  });
    // list to display column names required for vehicle no update
   this.finalRangeList = [];
  

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
    if (this.storage.get(this.currentRole) === "TRANS_EDIT") {
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
        this.previousNumberVehicle = this.FrameVehicleUpdateArray[v].numberOfVehicle;
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
      this.updateType = new updateVehicleNoDetails();
      if (this.finalRangeList[v] != undefined) {
        console.log(this.finalRangeList[v]);
        this.updateType.condensingTypeId=this.finalRangeList[v].condensingTypeId;
          this.updateType.unitId = this.finalRangeList[v].unitId;
          this.updateType.placeId=this.finalRangeList[v].placeId;
         // this.updateType.vehicleId = this.finalRangeList[v].vehicleId;
          this.updateType.custType=this.finalRangeList[v].custType;
          this.updateType.compPrice = this.finalRangeList[v].compPrice;
         // this.updateType.compoId = this.finalRangeList[v].compoId;
          this.updateType.compoName = this.finalRangeList[v].compoName;
          for(var i=0;i<this.componentArray.length;i++){
            if(this.componentArray[i].categoryDetDesc==this.finalRangeList[v].compoName){
            this.updateType.compoId = this.componentArray[i].categoryDetId;
            }
          }
          this.updateType.fobplace = this.finalRangeList[v].fobplace;
          this.updateType.fobId = this.finalRangeList[v].fobId;
          for(var i=0;i<this.placeArray.length;i++){
            if(this.placeArray[i].fobplace== this.finalRangeList[v].fobplace){
            this.updateType.fobplaceCode = this.placeArray[i].categoryDetCode;
            this.updateType.placeId=this.placeArray[i].placeId;
            }
          }
          this.updateType.fobplaceCode = this.finalRangeList[v].fobplaceCode;
          this.updateType.frameName = this.finalRangeList[v].frameName;
          this.updateType.frameId = this.finalRangeList[v].frameId;
          this.updateType.isupdatedflag = true;
          this.updateType.numberOfVehicle = this.finalRangeList[v].numberOfVehicle;
          this.updateType.transId = this.finalRangeList[v].transId;
          this.updateType.transType = this.finalRangeList[v].transType;
          this.updateType.turbineCode = this.finalRangeList[v].turbineCode;
          this.updateType.turbineDesign = this.finalRangeList[v].turbineDesign;
          this.updateType.turbineDesignCode = this.finalRangeList[v].turbineDesignCode;
          this.updateType.turbineType = this.finalRangeList[v].turbineType;
          //this.updateType.vehicleId = this.finalRangeList[v].vehicleId;
          this.updateType.vehicleName = this.finalRangeList[v].vehicleName;
          this.updateType.vehicleName = this.finalRangeList[v].vehicleName;
          for(var i=0;i<this.vehicleArray.length;i++){
            if(this.vehicleArray[i].categoryDetDesc== this.finalRangeList[v].vehicleName){
              this.updateType.vehicleId = this.vehicleArray[i].categoryDetId;
            }
  
          }
         
          this.saveBasic.updateCode = "UPD_TR_VH";
          this.saveBasic.custType=this.finalRangeList[v].custType;
          this.saveBasic.transportationType = this.finalRangeList[v].transId;
          this.saveBasic.condensingTypeId=this.finalRangeList[v].condensingTypeId;
          this.saveBasic.modifiedById = this.modifiedBy;
       
        this.saveBasic.modifiedById = this.modifiedBy;
        this.tempResp.saveBasicDetails = this.saveBasic;
        this.tempResp.updatePriceTransportList.push(this.updateType);
        console.log(this.tempResp);
      }
    }
    this._ItoUpdateVehicleService.updatePriceTransport(this.tempResp).subscribe(resp => {
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
         this.selcompoName = this.FrameVehicleUpdateArrayTemp[d].compoName;
         this.selfobplace = this.FrameVehicleUpdateArrayTemp[d].fobplace;
         this.selvehicleName = this.FrameVehicleUpdateArrayTemp[d].vehicleName;
         this.selnumberOfVehicle = this.FrameVehicleUpdateArrayTemp[d].numberOfVehicle;
       }
      }
   console.log(this.selcompoName);
   console.log(this.selfobplace);
   console.log(this.selvehicleName);
   console.log(this.selnumberOfVehicle);
   if(this.selectedRange.compoName != this.selcompoName || this.selectedRange.fobplace != this.selfobplace
    || this.selectedRange.vehicleName != this.selvehicleName || this.selectedRange.numberOfVehicle != this.selnumberOfVehicle){
    if (this.finalRangeList.length != 0) {
      for (let s = 0; s < this.finalRangeList.length; s++) {
        if (this.finalRangeList[s].frameName == this.selectedRange.frameName && this.finalRangeList[s].maxPower == this.selectedRange.maxPower )  {
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
   // this.vehiclDetails = new vehiclePriceClass();
    console.log(this.finalRangeList);
    for (let v = 0; v < this.finalRangeList.length; v++) {
      this.updateType = new updateVehicleNoDetails();
      if (this.finalRangeList[v] != undefined) {
        console.log(this.finalRangeList[v]);
        this.updateType.condensingTypeId=this.finalRangeList[v].condensingTypeId;
        this.updateType.unitId = this.finalRangeList[v].unitId;
        //this.updateType.placeId=this.finalRangeList[v].placeId;
        this.updateType.vehicleId = this.finalRangeList[v].vehicleId;
        this.updateType.custType=this.finalRangeList[v].custType;
        this.updateType.compPrice = this.finalRangeList[v].compPrice;
        //this.updateType.compoId = this.finalRangeList[v].compoId;
        this.updateType.compoName = this.finalRangeList[v].compoName;
        for(var i=0;i<this.componentArray.length;i++){
          if(this.componentArray[i].categoryDetDesc==this.finalRangeList[v].compoName){
          this.updateType.compoId = this.componentArray[i].categoryDetId;
          }
        }
        this.updateType.fobplace = this.finalRangeList[v].fobplace;
        this.updateType.fobId = this.finalRangeList[v].fobId;
        for(var i=0;i<this.placeArray.length;i++){
          if(this.placeArray[i].fobplace== this.finalRangeList[v].fobplace){
          this.updateType.fobplaceCode = this.placeArray[i].categoryDetCode;
          this.updateType.placeId=this.placeArray[i].placeId;
          }
        }
        this.updateType.fobplaceCode = this.finalRangeList[v].fobplaceCode;
        this.updateType.frameName = this.finalRangeList[v].frameName;
        this.updateType.frameId = this.finalRangeList[v].frameId;
        this.updateType.isupdatedflag = true;
        this.updateType.numberOfVehicle = this.finalRangeList[v].numberOfVehicle;
        this.updateType.transId = this.finalRangeList[v].transId;
        this.updateType.transType = this.finalRangeList[v].transType;
        this.updateType.turbineCode = this.finalRangeList[v].turbineCode;
        this.updateType.turbineDesign = this.finalRangeList[v].turbineDesign;
        this.updateType.turbineDesignCode = this.finalRangeList[v].turbineDesignCode;
        this.updateType.turbineType = this.finalRangeList[v].turbineType;
        //this.updateType.vehicleId = this.finalRangeList[v].vehicleId;
        this.updateType.vehicleName = this.finalRangeList[v].vehicleName;
        for(var i=0;i<this.vehicleArray.length;i++){
          if(this.vehicleArray[i].categoryDetDesc== this.finalRangeList[v].vehicleName){
            this.updateType.vehicleId = this.vehicleArray[i].categoryDetId;
          }

        }
       
        this.saveBasic.updateCode = "UPD_TR_VH";
        this.saveBasic.custType=this.finalRangeList[v].custType;
        this.saveBasic.transportationType = this.finalRangeList[v].transId;
        this.saveBasic.condensingTypeId=this.finalRangeList[v].condensingTypeId;
        this.saveBasic.modifiedById = this.modifiedBy;
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
    this._ItoUpdateVehicleService.updatePriceTransport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "TRANS_APPROVER") {
          this._ItoUpdateVehicleService.updateStatus(resp).subscribe(respon => {
            console.log(respon);
          this.successMsg.push(respon.successMsg);
            this._ItoUpdateVehicleService.saveTransportPrice(respon).subscribe(respo => {
              console.log(respo);
            this.successMsg.push(respo.successMsg);
            })
        });

        } else {
          this._ItoUpdateVehicleService.updateStatus(resp).subscribe(respon => {
            console.log(respon);
          this.successMsg.push(respon.successMsg);
          })
        }

    });

    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.FrameVehicleUpdateArray.length; vv++) {
      let butn = document.getElementById(this.FrameVehicleUpdateArray[vv].transId).style.backgroundColor = "";
    }
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

  } navigateToMyWorkflow() {
    this.router.navigate(['MyWorkFlow']);
  }
}
