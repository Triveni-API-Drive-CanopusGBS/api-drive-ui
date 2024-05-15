import { Component, OnInit } from '@angular/core';
import { ITOBasicDetailsService } from './ito-basic-details.service';
import { itoAdminBasicDetails } from './ito-basic-details';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';

@Component({
  selector: 'app-ito-basic-details',
  templateUrl: './ito-basic-details.component.html',
  styleUrls: ['./ito-basic-details.component.css']
})
export class ItoBasicDetailsComponent implements OnInit {

  display: boolean = false;
  dispOrien: boolean = false;
  orienId: number;
  displayDialog: boolean = false;
  displayDialogadd: boolean = false;
  newFrame: boolean;
  FrameCreationData: NgForm;



  newArray: itoAdminBasicDetails;
  selectedIndex: number;
  selectedFrame: itoAdminBasicDetails;
  FrameArray: Array<any> = [];
  newFrameArray: itoAdminBasicDetails;
  newOrienArray: itoAdminBasicDetails;
  OrienArray: Array<any> = [];
  tempArray: Array<any> = [];
  cols: any[];
  colsOrien: any[];
  enableCT: boolean;
  showMsg: boolean = false;


  //added for addFrame
  addDiv: boolean = false;
  updateDiv: boolean = false;
  errorDiv: boolean = false;
  FrameArray1: Array<any> = [];
  TurbineTypeArray: Array<any> = [];
  condenserType: Array<any> = [];
  TurbineDesignArray: Array<any> = [];
  addFrameObj: itoAdminBasicDetails;
  turbineDesig: String;
  turbineType: String;
  tempFrameArray: Array<any> = [];
  minimPow: number = 0;
  maxPowVal: number = 0;

  //updated array list
  updatedArray: Array<any> = [];
  updatedOrienArray: Array<any> = [];
  existed: boolean;
  existed1: boolean;
  //add new Array list
  addNewArray: Array<any> = [];
  addNewOrienArray: Array<any> = [];

  //added for orientation
  oriendialog: boolean = false;
  addoriendialog: boolean = false;

  //added for userid
  user: string = 'userDetail';
  data: Array<any> = [];
  userId: any;
  assignee: any;
  msgVal: string;
  showMsgMax: boolean = false;
//Flag variable to display boolean flag
  dialogMsg:boolean= false;
  dialogErrorMsg:boolean = false;
  dialogMsgVal:string;
  dialogErrorMsgVal:string;

  constructor(private _ITOBasicDetailsService: ITOBasicDetailsService, private _Router: Router
    , private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {

    // method call to get the logged in user ID
    this.assignee = this.data[this.user] = this.storage.get(this.user);
    this.userId = this.data[this.user].userId;
    console.log("userIddddddd::" + this.userId);

    //method call to get the frame power list
    this._ITOBasicDetailsService.getDataList().subscribe(res => {
      console.log(res);
      this.FrameArray=res.dropDownColumnvalues.frameWithPowerList.FRAMES_WITH_POWER;
      for(var i=0;i<this.FrameArray.length;i++){
        if(this.FrameArray[i].framePowerActive==true){
          this.FrameArray[i].framePowerActive="Active";
        }else if (this.FrameArray[i].framePowerActive==false){
          this.FrameArray[i].framePowerActive="InActive";
        }
        }
      this.OrienArray=res.dropDownColumnvalues.orientationTypes.ORIENTATION_TYPE;
        console.log(this.OrienArray);
    });
    this._ITOBasicDetailsService.getQuotationList1().subscribe(resp => {
      console.log(resp);
    //   for (var i = 0; i < resp.dropDownColumnvalues.orientationTypes.ORIENTATION_TYPE.length; i++) {

    //     this.newArray = new itoAdminBasicDetails();
    //     this.newArray.frameId = resp.dropDownColumnvalues.orientationTypes.ORIENTATION_TYPE[i].frameId;
    //     this.newArray.name = resp.dropDownColumnvalues.orientationTypes.ORIENTATION_TYPE[i].categoryDetDesc;
    //     if (resp.dropDownColumnvalues.orientationTypes.ORIENTATION_TYPE[i].turbineCode == 'BP') {
    //       this.newArray.turbineType = 'Back Pressure';
    //     } else {
    //       this.newArray.turbineType = 'Condensing';
    //     }
    //     this.OrienArray.push(this.newArray);
    //   }


      for (var i = 0; i < resp.dropDownColumnvalues.typesOfTurbine.TURBINE.length; i++) {
        this.TurbineTypeArray.push(resp.dropDownColumnvalues.typesOfTurbine.TURBINE[i]);
      }

      for (var i = 0; i < resp.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES.length; i++) {
        this.TurbineDesignArray.push(resp.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES[i]);
      }
      for (var i = 0; i < resp.dropDownColumnvalues.typesOfCondensor.CONDENSOR.length; i++) {
        this.condenserType.push(resp.dropDownColumnvalues.typesOfCondensor.CONDENSOR[i]);

      }
      for (var i = 0; i < resp.dropDownColumnvalues.frames.FRAMES.length; i++) {
        this.FrameArray1.push(resp.dropDownColumnvalues.frames.FRAMES[i]);
      }
    })
  }

  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
    this.dialogErrorMsg=false;
    this.dialogMsg=false;
   // list to display frames
    this.cols = [
      { field: 'frameDesc', header: 'Frame Name' },
      { field: 'framePowerActive', header: 'Status' },
      { field: 'maxPower', header: 'Capacity' },
      { field: 'turbineCode', header: 'Turbine Code' },
    ];


    // list to display orientation
    this.colsOrien = [
      { field: 'name', header: 'Orien Name' },
      { field: 'status', header: 'Status' },
      { field: 'turbineType', header: 'Turbine Type' }
    ]
  }

// cancel button function
  goback(val) {
    this._ITOLoginService.openSuccMsg("Changes will not be saved");
   // alert("Changes will not be saved");
    this.displayDialogadd = false;


  }

  // update frames list
  saveUpdated() {
    this.existed = false;
    for (var i = 0; i < this.FrameArray.length; i++) {
      if (this.FrameArray[i].name == this.newArray.name && this.FrameArray[i].capacity == this.newArray.capacity) {
        this.FrameArray[i].status = this.newArray.status;
        this.selectedIndex = i;
      }
    }
    console.log(this.updatedArray.length);

    for (var i = 0; i < this.updatedArray.length; i++) {
      if (this.updatedArray[i].name == this.FrameArray[this.selectedIndex].name && this.updatedArray[i].capacity == this.FrameArray[this.selectedIndex].capacity) {
        console.log('existed');
        this.updatedArray[i].status = this.FrameArray[this.selectedIndex].status;
        this.existed = true;
      }
    }
    if (!this.existed) {
      console.log('new');
      this.updatedArray.push(this.FrameArray[this.selectedIndex]);
    }
    console.log(this.updatedArray.length);

    this.displayDialog = false;
  }

  // update orientation list
  saveUpdatedOrien() {
    this.existed = false;
    console.log(this.updatedArray.length);

    this.OrienArray[this.selectedIndex].name = this.newOrienArray.name;
    this.OrienArray[this.selectedIndex].status = this.newOrienArray.status;
    for (var i = 0; i < this.updatedArray.length; i++) {
      if (this.updatedArray[i].name == this.newOrienArray.name) {
        this.existed = true;
        this.updatedArray[i].status == this.newOrienArray.status;
      }
    }
    if (!this.existed) {
      console.log('new');
      this.updatedArray.push(this.newOrienArray);
    }
    this.oriendialog = false;
  }

  // method to create new frame
  createFrame(val) {

    console.log(val)
    this.addFrameObj = new itoAdminBasicDetails();
    this.addFrameObj.frameDesc = "TST-"+val.frame;
    console.log(this.addFrameObj.frameDesc);
    console.log(val.frame.length >= 4);
    console.log(val.frame[4] == "1");
if(val.frame.length >= 4){
  
    this.addFrameObj.turbineDesignCd = val.turbineDesc;
    this.addFrameObj.turbineCode = val.turbineType;
    this.addFrameObj.frameId = 0;
    this.addFrameObj.framePowerId = 0;
    this.addFrameObj.frameDesc = "TST-"+val.frame;
    console.log(this.addFrameObj.frameDesc);
    this.addFrameObj.minPower = 0;
    this.addFrameObj.maxPower = val.maxPow;
    this.addFrameObj.frameActive = true;
    this.addFrameObj.framePowerActive = true;
  
    //this.addFrameObj.modifiedBy=this.userId;

    this._ITOBasicDetailsService.getFrameForm().subscribe(res => {
      res.frameWithPowerDetails = this.addFrameObj;
      res.modifiedBy = this.userId;
      console.log(res);
      this._ITOBasicDetailsService.addFrame(res).subscribe(resp => {
        console.log(resp);

        if (resp.successCode == 0 && resp.successMsg != null) {
          this._ITOLoginService.openSuccMsg("Frame Created");
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Frame Creation Failed");
        }
      });
    });
    this.addDiv = false;
    this.updateDiv = false;
    this.displayDialogadd = false;
  }else{
    this.showMsg= true;
    this.msgVal="Minium Length should be 4";
    this.displayDialogadd = true;
  }
  }

  // method to create new power for existing frame
  createUpdateFrame(val) {
    console.log(val);
    
    this.addFrameObj = new itoAdminBasicDetails();

    this.addFrameObj.turbineDesignCd = val.turbineDesign;
    this.addFrameObj.turbineCode = val.turbineType;
    this.addFrameObj.frameId = val.frame;
    this.addFrameObj.framePowerId = 0;
    for(let d=0;d<this.tempFrameArray.length;d++){
      if(this.tempFrameArray[d].frameId==val.frame){
         this.addFrameObj.frameDesc = this.tempFrameArray[d].frameDesc;
      }
    }
    this.addFrameObj.minPower = this.minimPow;
    this.addFrameObj.maxPower = val.maxPow;
    this.addFrameObj.frameActive = true;
    this.addFrameObj.framePowerActive = true;
   
    console.log( this.addFrameObj);

    this._ITOBasicDetailsService.getFrameForm().subscribe(res => {
      console.log(res);
      res.frameWithPowerDetails = this.addFrameObj;
      res.modifiedBy = this.userId;
      this._ITOBasicDetailsService.addFrame(res).subscribe(resp => {
        console.log(resp);

        if (resp.successCode == 0 && resp.successMsg != null) {
          this._ITOLoginService.openSuccMsg("Frame Created");
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Frame creation Failed");
        }
      });
    });
    this.addDiv = false;
    this.updateDiv = false;
    this.displayDialogadd = false;
  }

  // method to edit frame
  editFrame(val) {
    console.log(val)
    
    this.newFrameArray = val;
    this.displayDialog = true;
  }
// method to update existing frame
  UpdateExistingFrame(val) {
    console.log(this.newFrameArray);
    console.log(val);

    this.addFrameObj = new itoAdminBasicDetails();

    this.addFrameObj.turbineDesignCd = this.newFrameArray.turbineDesignCd;
    this.addFrameObj.turbineCode = this.newFrameArray.turbineCode;
    this.addFrameObj.frameId = this.newFrameArray.frameId;
    this.addFrameObj.framePowerId = this.newFrameArray.framePowerId;
    this.addFrameObj.frameDesc = this.newFrameArray.frameDesc;
    this.addFrameObj.minPower = this.newFrameArray.minPower;
    this.addFrameObj.maxPower = this.newFrameArray.maxPower;
    this.addFrameObj.frameActive = true;
    if (val.status == "Active") {
      this.addFrameObj.framePowerActive = true;
    } else if (val.status== "InActive") {
            this.addFrameObj.framePowerActive = false;
    }

    //this.addFrameObj.framePowerActive = true;
    console.log(this.addFrameObj)
    this._ITOBasicDetailsService.getFrameForm().subscribe(res => {
      res.frameWithPowerDetails = this.addFrameObj;
      res.modifiedBy = this.userId;
      console.log(res);

      this._ITOBasicDetailsService.addFrame(res).subscribe(resp => {
        console.log(resp);

        if (resp.successCode == 0 && resp.successMsg != null) {
         this._ITOLoginService.openSuccMsg("Frame Updated");
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Frame Updation Failed");
        }
      });
    });
    this.displayDialog = false;

  }
  
  createOrien(val) {
    
  }

  //method to display new dialog
  saveNew() {
    this.displayDialogadd = true;
    this.displayDialog = false;
  }
// method to display add
  enableAdd() {
    this.addDiv = true;
    this.updateDiv = false;
  }
  //method to display update
  enableUpdate() {
    this.addDiv = false;
    this.updateDiv = true;
  }
  // method to display frame valid
  FrameValid(val) {
    console.log(val);
    if (this.turbineType == "BP" && !val.startsWith("1")) {
      this.showMsg = true;
      this.msgVal = "Value should start with 1";
    } else if (this.turbineType == "CD" && !val.startsWith("2")) {
      this.showMsg = true;
      this.msgVal = "Value should start with 2";
    } else {
      this.showMsg = false;
      this.msgVal = "";
    }
  }
  // method to display frame list on select on change
  TOTSel(val) {
    this.tempFrameArray = [];
    console.log(val);
    if (val == "CD") {
      this.enableCT = true;
    }
    else if (val == "BP") {
      this.enableCT = false;
    }
    if (val == "BP") {
      this.turbineType = "BP";
    } else if (val == "CD") {
      this.turbineType = "CD";
    }

    console.log(this.turbineDesig);
    console.log(this.turbineType);
    for (var i = 0; i < this.FrameArray1.length; i++) {
      if (this.turbineType == this.FrameArray1[i].turbineCode && this.turbineDesig == this.FrameArray1[i].turbineDesignCd) {
        this.tempFrameArray.push(this.FrameArray1[i]);
      }
    }
    this.showMsg = false;
    this.msgVal = "";
  }
// method to display frame list on select
  TDSel(val) {
    this.tempFrameArray = [];
    if (val == "IM") {
      this.turbineDesig = "IM";
    } else if (val == "RN") {
      this.turbineDesig = "RN";
    }
    console.log(this.turbineDesig);
    console.log(this.turbineType);
    for (var i = 0; i < this.FrameArray1.length; i++) {
      if (this.turbineType == this.FrameArray1[i].turbineCode && this.turbineDesig == this.FrameArray1[i].turbineDesignCd) {
        this.tempFrameArray.push(this.FrameArray1[i]);
      }
    }
  }
  TOCSel(value) {
    console.log(value);
  }

  // method to fetch frame selected change
  FrameSelChange(val) {
    this.minimPow = 0;
    var temPow = 0;
    console.log(val);
    // console.log(this.FrameArray);
    for (var i = 0; i < this.FrameArray.length; i++) {
      if (val == this.FrameArray[i].frameId) {
        // console.log(this.FrameArray[i]);
        if (temPow < this.FrameArray[i].maxPower) {
          temPow = this.FrameArray[i].maxPower;
        }
      }
    }
    this.minimPow = 0.01 + temPow;
    this.maxPowVal = temPow;
    console.log(this.minimPow);
  }
  // method to show max pow val
  MaxPowValid(val) {
    if (val < this.maxPowVal) {
      this.showMsgMax = true;

    } else {
      this.showMsgMax = false;

    }
  }
  // method to display frame div
  displayFrame() {
    if (this.display == false) {
      this.display = true;
    } else {
      this.display = false;
    }
  }
  // method to display orientation
  displayOrientation() {
    if (this.dispOrien == false) {
      this.dispOrien = true;
    } else {
      this.dispOrien = false;
    }
  }

// edit orientation method
  editOrien(val) {
    this.oriendialog = true;
    console.log(val);
    for (var i = 0; i < this.OrienArray.length; i++) {
      if (this.OrienArray[i].name == val.name) {
        this.newArray = new itoAdminBasicDetails();
        this.newArray.status = this.OrienArray[i].status;
        this.newArray.name = this.OrienArray[i].name;
        this.newArray.turbineType = this.OrienArray[i].turbineType;
        this.newOrienArray = this.newArray;
        this.selectedIndex = i;
      }
    }
    this.oriendialog = true;
  }
// display add orien dialog
  addOrien() {
    this.addoriendialog = true;

  }


}
