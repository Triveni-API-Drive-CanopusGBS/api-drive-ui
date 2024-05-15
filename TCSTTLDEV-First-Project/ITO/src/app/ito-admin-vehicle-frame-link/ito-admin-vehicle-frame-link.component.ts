import { Component, OnInit } from '@angular/core';
import { ItoTransportationDetailsService }  from '../ito-admin-transport-component/ito-admin-transport.service';
import { transportationDetails } from '../ito-admin-transport-component/ito-admin-transport';
import {updateVehicleNoDetails} from '../ito-admin-update-vehicle-no/ito-admin-update-vehicle-no';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import {ItoNoOfVehicleService} from './ito-admin-vehicle-frame-link.service';

@Component({
  selector: 'app-ito-admin-vehicle-frame-link',
  templateUrl: './ito-admin-vehicle-frame-link.component.html',
  styleUrls: ['./ito-admin-vehicle-frame-link.component.css']
})
export class ItoAdminVehicleFrameLinkComponent implements OnInit {

  condensingTypeName: string;
  framePowerId: any;
  power: any;
  turbType: any;
  turbdesignName: any;
  turbineDesignCd: any;
  frameId: any;
  componentArrayTotal: any;
  cols: { field: string; header: string; }[];
  FrameList:Array<any>=[];
  SelectedFrame:String;
  gridData:Array<any>=[];
  componentArray: Array<any> = [];
  vehicleArray: Array<any> = [];
  placeArray: Array<any> = [];
  scopeArray: Array<any> = [];
  newFrameVehicle:any;
  finalRangeList:Array<any>=[];

  EnabletransportDiv: Array<any> = [];
  vehicleNoType:Array<any>=[];

  user: string = 'userDetail';
  data: Array<any> = [];
  userId: any;
  assignee: any;

  displayCompValue:boolean=false;
  displayCD:boolean=false;
  displayTable:boolean=false;
  checkVal:boolean=false;

  currentRole: string = 'selRole';
  //finalRangeList: Array<any> = [];
  selectedRange:any;
  prevUnitPrice: any;
  localStorageValues: Array<any> = [];
  loginUserDetails: string = "userDetail";
  userRoles: Array<any> = [];
  labelName: string = '';
  newUsersLilst: Array<any> = [];
  selectedUR: any;
  reponseTemp: any;
  prevRemarks: Array<any> = [];
  usersList: Array<any> = [];
  saveBasic: any;
  tempResp: any;
  modifiedBy: any;
  successMsg: Array<any> = [];
  assignDisp: boolean;
  updateType: any;
  remarkss: any;
  transpEdit: boolean = false;
  transpApp: boolean = false;
  transRev: boolean = false;
  frameCode:String;
  FrameTypeList:Array<any>=[];



  constructor(private _ItoNoOfVehicleService: ItoNoOfVehicleService, private _ItoTransportationService: ItoTransportationDetailsService, private _Router: Router,
    private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) { 
   
    this.assignee = this.data[this.user] = this.storage.get(this.user);
    this.userId = this.data[this.user].userId;
    console.log("userIddddddd::" + this.userId);
    this._ItoNoOfVehicleService.getNewFrameCache().subscribe(res => {
      console.log(res);
      this.FrameList=res.newFrameWithPowersList;
    });

    // method to get the details from DB for componnet,place and vehicles
    this._ItoTransportationService.getTransportationCache().subscribe(res => {
      console.log(res);
      this.tempResp = res;
      this.componentArrayTotal = res.dropDownColumnvalues.componentTypes.TransportComponentTypeList;
      this.placeArray = res.dropDownColumnvalues.placeList.placeList;
      this.vehicleArray = res.dropDownColumnvalues.vehiclesList.VEHICLE_LIST;
      this.scopeArray = res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
      
    this.finalRangeList = [];
    this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
    this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
    console.log(this.userRoles);

    this.usersList = res.userDetailsList;
    this.saveBasic = res.saveBasicDetails;
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

    

  }

  ngOnInit() {
    this.cols = [
      { field: 'frameName', header: 'Frame Name' },
      { field: 'compoName', header: 'Component' },
      { field: 'FOBPlace', header: 'Place' },
      { field: 'vehicleName', header: 'Vehicle Name' },
      { field: 'numberOfVehicle', header: 'No.of Vehicles' }
    ];
    this.FrameTypeList=[
      {name: 'Air Cooled Condensor', value:'ACC'},
      {name: 'Water Cooled Condensor', value:'WCC'}
    ];
  }

  newFrame(val){
    console.log(val);
    console.log(this.componentArray); 
  }

  FrameSeltype(val){
      console.log(val);
      this.condensingTypeName=val;
  }

  FrameSel(val){
    console.log(val);
    this.SelectedFrame=val.frameDesc;
    this.finalRangeList=[];
    this.EnabletransportDiv=[];
    console.log(this.componentArrayTotal);
    this.frameCode=val.turbineCode;
    this.frameId=val.frameId;
    this.framePowerId=val.framePowerId;
    this.power=val.power;
    this.turbType=val.turbType;
    this.turbdesignName=val.turbdesignName;
    this.turbineDesignCd=val.turbineDesignCd;
    this.componentArray=[];
    if(this.frameCode=="CD"){
      this.displayCD=true;
    }else{
      this.displayCD=false;
    }

    for(var i=0;i<this.componentArrayTotal.length;i++){
      if(val.turbineCode==this.componentArrayTotal[i].turbineCode){
      {
        this.componentArray.push(this.componentArrayTotal[i]);
      }
      
      }
    }
      for(var i=0;i<this.componentArray.length;i++){
      this.componentArray[i].defaultVal=false;
      }
    
    if(val==''){
      this.displayCompValue=false;
    }else{
      this.displayCompValue=true;
    }
    if(this.frameCode=="CD"){
      this.displayCD=true;
    }else{
      this.displayCD=false;
    }
    if(this.finalRangeList.length!=0){
      this.displayTable=true;
    }else{
      this.displayTable=false;
    }
  }
  transportSel(val1,val2,val3){
    
    if (val1.target.checked) {
      this.EnabletransportDiv[val3] = true;  
    }else{
    
      this.EnabletransportDiv[val3] = false; 
      const index=this.finalRangeList.length;
      for(var i=0;i<index;i++){
        if(val2.categoryDetDesc==this.finalRangeList[i].compoName){
          this.finalRangeList.splice(i,1);
        }
      }
      if(this.finalRangeList.length!=0){
        this.displayTable=true;
      }else{
        this.displayTable=false;
      }
    }
  }

  vehicleTypeVal(val,val1){
    console.log(val);
    console.log(val1);
    this.vehicleNoType[val1]=val;
  }
  addNewComponent(val1,val2,val3,val4){
    console.log(val1);
    console.log(val2.viewModel);
    console.log(val3.viewModel);
    console.log(val4.categoryDetDesc);
    this.newFrameVehicle=new updateVehicleNoDetails();
    this.newFrameVehicle.frameName=this.SelectedFrame;
    this.newFrameVehicle.compoName=val4.categoryDetDesc;
    this.newFrameVehicle.vehicleName=val2.viewModel;
    this.newFrameVehicle.FOBPlace=val3.viewModel;
    this.newFrameVehicle.numberOfVehicle=val1;

    this.finalRangeList.push(this.newFrameVehicle);
    console.log(this.finalRangeList.length);
    if(this.finalRangeList.length!=0){
      this.displayTable=true;
    }else{
      this.displayTable=false;
    }
  }
  removeRow(val){
    console.log(val);
    for(var i=0;i< this.finalRangeList.length;i++){
      if(this.finalRangeList[i].compoName==val.compoName &&
        this.finalRangeList[i].vehicleName==val.vehicleName &&
        this.finalRangeList[i].FOBPlace==val.FOBPlace &&
        this.finalRangeList[i].numberOfVehicle==val.numberOfVehicle )
      {
        this.finalRangeList.splice(i,1);
        break;
      }
    }
    if(this.finalRangeList.length!=0){
      this.displayTable=true;
    }else{
      this.displayTable=false;
    }
  }

   // save as method
   SaveAsDraft() {
     console.log(this.finalRangeList);
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
      console.log(this.placeArray);
        this.updateType.unitId = this.finalRangeList[v].unitId;
        
        
        this.updateType.custType='DM';
        this.updateType.compoName = this.finalRangeList[v].compoName;
        for(var i=0;i<this.componentArrayTotal.length;i++){
          if(this.componentArrayTotal[i].categoryDetDesc==this.finalRangeList[v].compoName){
         this.updateType.compPrice = 0;
          this.updateType.compoId = this.componentArrayTotal[i].categoryDetId;
          }
        }
       
        this.updateType.fobplace = this.finalRangeList[v].FOBPlace;
        for(var i=0;i<this.placeArray.length;i++){
          if(this.placeArray[i].fobplace== this.finalRangeList[v].FOBPlace){
          this.updateType.fobplaceCode = this.placeArray[i].categoryDetCode;
          this.updateType.placeId=this.placeArray[i].placeId;
          }
        }

       
        this.updateType.frameName = this.finalRangeList[v].frameName;
        this.updateType.framePowerId = this.framePowerId;
        this.updateType.frameId=this.frameId;
        this.updateType.isupdatedflag = true;
        this.updateType.numberOfVehicle = this.finalRangeList[v].numberOfVehicle;
        this.updateType.transId = 0;
        this.updateType.transType = "FOR AT SITE";
        this.updateType.turbineCode = this.frameCode;
        this.updateType.turbineDesign = this.turbdesignName;
        this.updateType.turbineDesignCode = this.turbineDesignCd;
        this.updateType.turbineType = this.turbType;
        
        this.updateType.vehicleName = this.finalRangeList[v].vehicleName;
        for(var i=0;i<this.vehicleArray.length;i++){
          if(this.vehicleArray[i].categoryDetDesc== this.finalRangeList[v].vehicleName){
            this.updateType.vehicleId = this.vehicleArray[i].categoryDetId;
          }

        }
       
        this.saveBasic.updateCode = "UPD_NTR_NV";
        this.saveBasic.custType="DM";
        this.saveBasic.transportationType = 8;
        if(this.frameCode=="BP"){
          this.saveBasic.condensingTypeId=0;
        }else{
          if(this.condensingTypeName=="ACC"){
            this.saveBasic.condensingTypeId=35;
          }else{
            this.saveBasic.condensingTypeId=34;
          }
        }
        
        this.saveBasic.modifiedById = this.modifiedBy;
     
      this.saveBasic.modifiedById = this.modifiedBy;
      this.tempResp.saveBasicDetails = this.saveBasic;
      this.tempResp.updatePriceTransportList.push(this.updateType);
      console.log(this.tempResp);
    }
  }

  this._ItoNoOfVehicleService.updatePriceTransport(this.tempResp).subscribe(resp => {
    console.log(resp);
    this.successMsg = resp.successMsg;
    this.saveBasic.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
    this.tempResp = resp;
  
  });
  }

  assignedUser(assigne) {
    console.log(assigne);
    console.log(this.tempResp.userDetailsList);
    this.saveBasic.assignedTo = assigne;

  }

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
        this.updateType.unitId = this.finalRangeList[v].unitId;
        
        
        this.updateType.custType='DM';
        this.updateType.compoName = this.finalRangeList[v].compoName;
        for(var i=0;i<this.componentArrayTotal.length;i++){
          if(this.componentArrayTotal[i].categoryDetDesc==this.finalRangeList[v].compoName){
         this.updateType.compPrice = 0;
          this.updateType.compoId = this.componentArrayTotal[i].categoryDetId;
          }
        }
       
        this.updateType.fobplace = this.finalRangeList[v].FOBPlace;
        for(var i=0;i<this.placeArray.length;i++){
          if(this.placeArray[i].categoryDetDesc== this.finalRangeList[v].FOBPlace){
          this.updateType.fobplaceCode = this.placeArray[i].categoryDetCode;
          this.updateType.placeId=this.placeArray[i].categoryDetId;
          }
        }

       
        this.updateType.frameName = this.finalRangeList[v].frameName;
        this.updateType.framePowerId = this.framePowerId;
        this.updateType.frameId=this.frameId;
        this.updateType.isupdatedflag = true;
        this.updateType.numberOfVehicle = this.finalRangeList[v].numberOfVehicle;
        this.updateType.transId = 0;
        this.updateType.transType = "FOR AT SITE";
        this.updateType.turbineCode = this.frameCode;
        this.updateType.turbineDesign = this.turbdesignName;
        this.updateType.turbineDesignCode = this.turbineDesignCd;
        this.updateType.turbineType = this.turbType;
        
        this.updateType.vehicleName = this.finalRangeList[v].vehicleName;
        for(var i=0;i<this.vehicleArray.length;i++){
          if(this.vehicleArray[i].categoryDetDesc== this.finalRangeList[v].vehicleName){
            this.updateType.vehicleId = this.vehicleArray[i].categoryDetId;
          }

        }
       
        this.saveBasic.updateCode = "UPD_NTR_NV";
        this.saveBasic.custType="DM";
        this.saveBasic.transportationType = 8;
        if(this.frameCode=="BP"){
          this.saveBasic.condensingTypeId=0;
        }else{
          if(this.condensingTypeName=="ACC"){
            this.saveBasic.condensingTypeId=35;
          }else{
            this.saveBasic.condensingTypeId=34;
          }
        }
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
    this._ItoNoOfVehicleService.updatePriceTransport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg = resp.successMsg;
      if (this.storage.get(this.currentRole) == "TRANS_APPROVER") {
        this._ItoNoOfVehicleService.updateStatus(resp).subscribe(respon => {
          console.log(respon);
        this.successMsg.push(respon.successMsg);
          this._ItoNoOfVehicleService.saveTransportPrice(respon).subscribe(respo => {
            console.log(respo);
          this.successMsg.push(respo.successMsg);
          })
      });
        } else {
          this._ItoNoOfVehicleService.updateStatus(resp).subscribe(respon => {
            console.log(respon);
            this.successMsg = respon.successMsg;
          })
        }
      
    });

    this.finalRangeList = [];
    this.selectedRange = '';
    // for (let vv = 0; vv < this.vehiclesWithPrice.length; vv++) {
    //   let butn = document.getElementById(this.vehiclesWithPrice[vv].unitId).style.backgroundColor = "";
    // }
  }
}
