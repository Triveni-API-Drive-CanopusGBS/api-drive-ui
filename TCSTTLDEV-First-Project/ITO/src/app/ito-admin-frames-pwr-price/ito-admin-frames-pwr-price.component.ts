import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ItoFramesPwrPriceService } from './ito-admin-frames-pwr-price.service';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';

@Component({
  selector: 'app-ito-admin-frames-pwr-price',
  templateUrl: './ito-admin-frames-pwr-price.component.html',
  styleUrls: ['./ito-admin-frames-pwr-price.component.css']
})
export class ItoAdminFramesPwrPriceComponent implements OnInit {

  UBOWithPrice: any;
  selectedUR: any = '';
  modifiedBy: any;
  cols: any;
  framePowerName: any;
  tempRes: any;
  selectedRange: any;
  saveBasic: any;
  savedReqQuotForm: any;
  reponseTemp: any;
  rowGroupMetadata: any;
  uboForm: any;

  UBOApprove: boolean = false;
  UBOReview: boolean = false;
  dispDropdown: boolean = true;
  isDataSaved: boolean = false;
  displayBleed: boolean = false;
  contains: boolean = false;
  message: boolean = false;
  UBOEdit: boolean = false;
  enablePrevPrice: boolean = false;
  displayCond: boolean = false;
  hideprogress: boolean = false;
  hidespinner:boolean = false;
  showNoCostMsg: boolean = false;
  enableStatus: boolean = true;

  prevRemarks: Array<any> = [];
  framePowerArray: Array<any> = [];
  frameArray: Array<any> = [];
  frameDrpDowns: Array<any> = [];
  turbineTypeArray: Array<any> = [];
  turbineDesignArray: Array<any> = [];
  bleedTypArray: Array<any> = [];
  condensorTypes: Array<any> = [];
  tempFrameArray: Array<any> = [];
  frmPowerArray: Array<any> = [];
  uboListArray: Array<any> = [];
  catListArray: Array<any> = [];
  indtotl: Array<any> = [];
  displayTable: Array<any> = [];
  localStorageValues: Array<any> = [];
  userRoles: Array<any> = [];
  catArrayLatest: Array<any> = [];
  updatedUBOList: Array<any> = [];
  usersList: Array<any> = [];
  newUsersList: Array<any> = [];
  newFrameWithPowersList: Array<any> = [];
  uboListforNewFrames: Array<any> = [];
  successMsg: Array<any> = [];

  turbineDesign: string;
  turbineType: string;
  bleedType: string;

  labelName: string;
  condName: string;
  loginUserDetails: string = "userDetail";
  currentRole: string = 'selRole';
  currentRoleId: string = 'selRoleId';

  framePwrID: number;
  bleedTypID: number;
  total: number;
  frmPower: number;
  condId: number=0;

  constructor(private _ItoFramesPwrPriceService: ItoFramesPwrPriceService, private _Router: Router
    , private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOMyWorkListPageService: ITOMyWorkListPageService,
    private router: Router, private route: ActivatedRoute) {
    this.hideprogress = true;

    this._ItoFramesPwrPriceService.getFrameAndUserData().subscribe(res => {
      console.log(res);
      this.tempRes = res;
      this.saveBasic = res.saveBasicDetails;
      this.usersList = res.userDetailsList;

      this.framePowerArray = res.dropDownColumnvalues.frames.FRAMES;
      this.frameArray = res.dropDownColumnvalues.typeOfNewList.FRAMES_WITH_POWER;
      this.turbineTypeArray = res.dropDownColumnvalues.typesOfTurbine.TURBINE;
      this.turbineDesignArray = res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
      this.bleedTypArray = res.dropDownColumnvalues.typesOfCondensor.BLEED_TYPE_LIST;
      this.condensorTypes = res.dropDownColumnvalues.typesOfCondensor.CONDENSOR;

      //this.catListArray = res.dropDownColumnvalues.categoryList.CAT_DET_LIST;

      console.log(this.bleedTypArray)
      console.log(this.framePowerArray)
      console.log(this._ITOMyWorkListPageService.selectedUR);
      console.log(this.storage.get(this.currentRoleId),this.storage.get(this.currentRole), this.condId, this.condName)
      //if (this._ITOMyWorkListPageService.selectedUR != '') {
      if (this._ITOMyWorkListPageService.selectedUR != '') {
        console.log(this._ITOMyWorkListPageService.responseTemp);
        this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
        this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
        //this._ITOMyWorkListPageService.selectedUR = '';
        console.log(this.selectedUR)
        this.dispDropdown = false;
        this.enablePrevPrice = true;
        this.condId = this._ITOMyWorkListPageService.responseTemp.f2FUboSavedList[0].condensingTypeId;
        this.condName = this._ITOMyWorkListPageService.responseTemp.f2FUboSavedList[0].condensingTypeName;
        

        if (this.storage.get(this.currentRole) == "UBO_EDIT") {
          this.uboListArray = this._ITOMyWorkListPageService.responseTemp.f2FUboUnsavedList;
          this.updatedUBOList = this._ITOMyWorkListPageService.responseTemp.f2FUboSavedList;
          this.updateRowGroupMetaData();
          this.calculateTotal();
          console.log(this.updatedUBOList)
          console.log(this.uboListArray)

          for (let s = 0; s < this.uboListArray.length; s++) {
            for (let q = 0; q < this.updatedUBOList.length; q++) {
              if (this.updatedUBOList[q].f2F_DET_ID == this.uboListArray[s].f2F_DET_ID) {
                this.uboListArray[s].price = this.updatedUBOList[q].price;
              }
            }
          }

        }
        else if (this.storage.get(this.currentRole) != "UBO_EDIT") {
          this.uboListArray = this._ITOMyWorkListPageService.responseTemp.f2FUboSavedList;
          this.updatedUBOList = this._ITOMyWorkListPageService.responseTemp.f2FUboSavedList;
          this.updateRowGroupMetaData();
          this.calculateTotal();
        }

        for (let m = 0; m < this.framePowerArray.length; m++) {
          if (this.framePowerArray[m].frameId == this.updatedUBOList[0].frm_POW_ID) {
            this.framePowerName = this.framePowerArray[m].frameDesc;
            this.turbineDesign = this.framePowerArray[m].turbdesignName;
            this.turbineType = this.framePowerArray[m].turbType;
            this.frmPower = this.framePowerArray[m].maxPower
          }
        }
        for (let m = 0; m < this.bleedTypArray.length; m++) {
          if (this.bleedTypArray[m].categoryDetId == this.updatedUBOList[0].bleed_TYP_ID) {
            this.bleedType = this.bleedTypArray[m].categoryDetDesc;

          }
        }

        console.log(this.framePowerName)
        console.log(this.uboListArray)

        for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
          this.prevRemarks.push(this.reponseTemp.commentList[r]);
        }
        console.log("remarks");
        console.log(this.prevRemarks);

        for (let s = 0; s < this.uboListArray.length; s++) {
          for (let q = 0; q < this.updatedUBOList.length; q++) {
            if (this.updatedUBOList[q].f2F_DET_ID == this.uboListArray[s].f2F_DET_ID) {
              //this.updatedUBOList[s].cat_ID = this.uboListArray[q].cat_ID;
            }
          }
        }
      }
      //this.tempRes = res;

      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;
      console.log(this.localStorageValues[this.loginUserDetails]);
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;

      console.log(this._ITOMyWorkListPageService.selectedUR)


      switch (this.storage.get(this.currentRole)) {
        case "UBO_EDIT": {
          this.UBOEdit = true;
          this.labelName = "Please select the reviewer";
          this.saveBasic.loggedInUserCode = 0;
          this.saveBasic.statusId = 1;

          console.log(this._ITOMyWorkListPageService.selectedUR);
          // if (this._ITOMyWorkListPageService.selectedUR != '') {
          if (this._ITOMyWorkListPageService.selectedUR) {
            this.saveBasic.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
          } else {
            this.saveBasic.updateRequestNumber = 0;
          }

          console.log(this.saveBasic.updateRequestNumber)
          console.log(this.usersList);
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "UBO_REVIWER") {
                // if (this.usersList[r].empId == 2584) {
                this.newUsersList.push(this.usersList[r]);
              }
            }
          }
          console.log(this.newUsersList);
          break;
        }
        case "UBO_APPROVER": {
          this.UBOApprove = true;
          this.saveBasic.loggedInUserCode = 2;
          if (this._ITOMyWorkListPageService.selectedUR) {
            this.saveBasic.updateRequestNumber = this._ITOMyWorkListPageService.selectedUR.updateRequestNumber;
          }

          break;

        }
        case "UBO_REVIWER": {
          this.UBOReview = true;
          this.labelName = "Please select the approver";
          this.saveBasic.loggedInUserCode = 1;
          if (this._ITOMyWorkListPageService.selectedUR) {
            this.saveBasic.updateRequestNumber = this._ITOMyWorkListPageService.selectedUR.updateRequestNumber;
          }
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "UBO_APPROVER") {
                this.newUsersList.push(this.usersList[r]);
              }
            }
          }
          console.log(this.newUsersList);
          break;

        }
        default: {
          break;
        }
      }

      this._ITOMyWorkListPageService.selectedUR = '';
      this.hideprogress = false;
    });
  }

  ngOnInit() {
    // this.cols = [
    //   { field: 'cat_NM', header: 'Category Name' },
    //   { field: 'mtrl_NM', header: 'Material Name' },
    //   { field: 'price', header: 'Price' }
    // ];
    this._ItoFramesPwrPriceService.getNewFramesForUBO().subscribe(resp => {
      this.newFrameWithPowersList = resp.newFrameWithPowersList;
      console.log(this.newFrameWithPowersList);
    });
  }

  ngAfterViewChecked() {
    if (this.storage.get(this.currentRole) === "UBO_EDIT") {
      for (let s = 0; s < this.uboListArray.length; s++) {
        for (let q = 0; q < this.updatedUBOList.length; q++) {
          if (this.uboListArray[s].f2F_DET_ID == this.updatedUBOList[q].f2F_DET_ID) {
            let butn = document.getElementById(this.uboListArray[s].f2F_DET_ID).style.backgroundColor = "#0275d8";
          }
        }
      }
    }
  }

  TDSel(val) {

    this.tempFrameArray = [];
    this.frmPowerArray = [];
    this.frameDrpDowns = [];
    this.turbineDesign = val;
    console.log(this.turbineDesign);
  }

  TOTSel(val) {
    console.log(val);
    this.tempFrameArray = [];
    this.frmPowerArray = [];
    this.frameDrpDowns = [];
    this.turbineType = val;
    // this.displayBleed = false;
    // this.displayBleed = true;

    for (var i = 0; i < this.framePowerArray.length; i++) {
      if (this.turbineType == this.framePowerArray[i].turbineCode && this.turbineDesign == this.framePowerArray[i].turbineDesignCd) {
        this.tempFrameArray.push(this.framePowerArray[i]);
      }
    }
    if (this.turbineType == "BP") {
      this.displayCond = false;
      this.condId=0;
      this.frameDrpDowns = this.tempFrameArray;
    } else if (this.turbineType == "CD") {
      this.displayCond = true;
    }

    console.log(this.frameDrpDowns);
  }

  CondSel(val) {
    this.frameDrpDowns = [];
    this.condId = val;
    console.log(this.condId);
    for (var i = 0; i < this.tempFrameArray.length; i++) {
      if (this.condId == this.tempFrameArray[i].condTypId) {
        this.frameDrpDowns.push(this.tempFrameArray[i]);
      }
    }
    this.tempFrameArray = [];
    this.tempFrameArray = this.frameDrpDowns;
    console.log(this.frameDrpDowns);
  }



  bleedTypSelChange(val) {
    console.log(val);
    this.bleedTypID = val;

    console.log(this.frameDrpDowns);
    console.log(this.tempFrameArray);
    this.frameDrpDowns = [];
    for (var i = 0; i < this.tempFrameArray.length; i++) {
      if (this.bleedTypID == this.tempFrameArray[i].bleedTypId) {
        this.frameDrpDowns.push(this.tempFrameArray[i]);
      }
    }
    console.log(this.frameDrpDowns)
    this.frameDrpDowns = this.frameDrpDowns.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.frameId === current.frameId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    console.log(this.frameDrpDowns);
  }

  FrameSelChange(val) {
    console.log(val);
    this.framePwrID = val;
    this.uboListArray = [];
    this.frmPowerArray = [];
    for (var i = 0; i < this.frameArray.length; i++) {
      if (val == this.frameArray[i].frameId) {
        this.frmPowerArray.push(this.frameArray[i]);
      }
    }
    console.log(this.frmPowerArray);
    // this.tempRes.uboframeList.categoryDetId = this.bleedTypID;
    // this.tempRes.uboframeList.framePowerId = this.framePwrID;
    // this.tempRes.saveBasicDetails.condensingTypeId = this.condId;
    // console.log(this.tempRes);
    // this.getUBOCostData();
  }

  focusFunction() {
    this.frameDrpDowns=[];
    console.log(this.bleedTypID,this.turbineType,this.turbineDesign,this.condId);
    for (var i = 0; i < this.framePowerArray.length; i++) {
      if (this.bleedTypID == this.framePowerArray[i].bleedTypId &&
         this.turbineType == this.framePowerArray[i].turbineCode &&
          this.turbineDesign == this.framePowerArray[i].turbineDesignCd &&
           this.condId == this.framePowerArray[i].condTypId) {
        this.frameDrpDowns.push(this.framePowerArray[i]);
      }
    }
    console.log(this.frameDrpDowns)
    this.frameDrpDowns = this.frameDrpDowns.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.frameId === current.frameId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    console.log(this.frameDrpDowns);
  }

  getFrameDet(val) {
    console.log(val);
    this.framePwrID = val;

    console.log(this.tempRes);
    this.tempRes.uboframeList.categoryDetId = this.bleedTypID;
    this.tempRes.uboframeList.framePowerId = this.framePwrID;
    this.getUBOCostData();
  }

  TurbineTypeSel(val) {
    console.log(val);
    this.tempFrameArray = [];
    this.turbineType = val;

    // this.displayBleed = false;
    // this.displayBleed = true;
    console.log()
    for (var i = 0; i < this.newFrameWithPowersList.length; i++) {
      if (this.turbineType == this.newFrameWithPowersList[i].turbineCode && this.turbineDesign == this.newFrameWithPowersList[i].turbineDesignCd) {
        this.tempFrameArray.push(this.newFrameWithPowersList[i]);
      }
    }
    //onsole.log(this.tempFrameArray);
  }

  FramePwrSelChange(val) {
    console.log(val);
    this.uboListArray = [];
    this.framePwrID = val;

    console.log(this.condId);
    this.tempRes.uboframeList.categoryDetId = this.bleedTypID;
    this.tempRes.uboframeList.framePowerId = this.framePwrID;
    this.tempRes.saveBasicDetails.condensingTypeId = this.condId;
    console.log(this.tempRes);
    this.getUBOCostData();
  }

  getUBOCostData() {
    this.showNoCostMsg = false;
    this.hidespinner = true;
    console.log(this.tempRes);
    this._ItoFramesPwrPriceService.framePwrPriceDetails(this.tempRes).subscribe(resp => {
      console.log(resp);

      this.uboListArray = resp.f2FUBOList;
      //this.uboListforNewFrames = resp.f2FUBOList;
      if (this.uboListArray.length == 0)
        this.showNoCostMsg = true;
      this.updateRowGroupMetaData();
      for (var j = 0; j < this.uboListArray.length; j++) {
        this.total = 0;
        for (var i = 0; i < resp.f2FUBOList.length; i++) {
          if (this.uboListArray[j].cat_NM == resp.f2FUBOList[i].cat_NM) {
            this.total = this.total + resp.f2FUBOList[i].price;
          }
          this.uboListArray[j].matPrice = this.total;
        }
      }
      this.hidespinner = false;
    });
  }

  savePrice(rowData) {
    //  this.updatedUBOList=[];
    this.selectedRange = rowData;
    console.log(this.selectedRange);
    if (this.updatedUBOList.length != 0) {
      for (let s = 0; s < this.updatedUBOList.length; s++) {
        if (this.updatedUBOList[s].f2F_DET_ID == this.selectedRange.f2F_DET_ID) {
          this.updatedUBOList[s] = this.selectedRange;
          this.contains = true;
          let butn = document.getElementById(this.updatedUBOList[s].f2F_DET_ID).style.backgroundColor = "#0275d8";
          break;
        }
      }
      if (!this.contains) {
        this.updatedUBOList.push(this.selectedRange);
        for (let s = 0; s < this.updatedUBOList.length; s++) {
          let butn = document.getElementById(this.updatedUBOList[s].f2F_DET_ID).style.backgroundColor = "#0275d8";
        }
      }
      else {
        this.contains = false;
      }
    }
    else {
      this.updatedUBOList.push(this.selectedRange);
      for (let s = 0; s < this.updatedUBOList.length; s++) {
        let butn = document.getElementById(this.updatedUBOList[s].f2F_DET_ID).style.backgroundColor = "#0275d8";
      }
    }
    console.log(this.updatedUBOList);
  }

  save() {
    console.log(this.uboListArray)
    console.log(this.updatedUBOList);
    for (var j = 0; j < this.catListArray.length; j++) {
      this.total = 0;
      for (var i = 0; i < this.uboListArray.length; i++) {
        if (this.catListArray[j].cat_NM == this.uboListArray[i].cat_NM) {
          this.total = this.total + this.uboListArray[i].price;
        }
        this.indtotl[j] = this.total;
      }
      console.log(this.indtotl[j]);
    }
  }

  SaveAsDraft(updateUBOPriceForm, form: NgForm) {
    console.log(updateUBOPriceForm);
    this.uboForm = form;
    console.log(updateUBOPriceForm);
    console.log(this.updatedUBOList);
    this.isDataSaved = true;
    this.message = true;
    console.log(this.condId)

    this.tempRes.f2FUBOsetterList = this.updatedUBOList;

    for (let q = 0; q < this.updatedUBOList.length; q++) {
      if (this.updatedUBOList[q].f2F_DET_ID == 0) {
        this.saveBasic.updateCode = "UPD_UBON";
      } else {
        this.saveBasic.updateCode = "UPD_UBO";
      }
    }

    this.saveBasic.modifiedById = this.modifiedBy;
    this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
    this.saveBasic.remarks = updateUBOPriceForm.remarks;
    this.saveBasic.assignedTo = updateUBOPriceForm.assignee;

    if (updateUBOPriceForm.status == "Accept") {
      this.saveBasic.statusId = 1;
    }
    else if (updateUBOPriceForm.status == "Reject") {
      this.saveBasic.statusId = 0;
    }
    this.tempRes.saveBasicDetails = this.saveBasic;
    this.tempRes.saveBasicDetails.condensingTypeId = this.condId;
    console.log(this.tempRes)

    this._ItoFramesPwrPriceService.createUBOPriceUpdateRequest(this.tempRes).subscribe(resp => {
      console.log(resp);
      this.savedReqQuotForm = resp;
      // this.saveBasic.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;

      this.message = true;
      this.successMsg.push(resp.successMsg);

    });
  }

  updateUBOPrice(uBOForm, form: NgForm) {
    console.log(uBOForm);
    this.uboForm = form;
    console.log(this.updatedUBOList);

    this.tempRes.f2FUBOsetterList = this.updatedUBOList;

    for (let q = 0; q < this.updatedUBOList.length; q++) {
      if (this.updatedUBOList[q].f2F_DET_ID == 0) {
        this.saveBasic.updateCode = "UPD_UBON";
      } else {
        this.saveBasic.updateCode = "UPD_UBO";
      }
    }

    this.saveBasic.modifiedById = this.modifiedBy;
    this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
    this.saveBasic.remarks = uBOForm.remarks;
    this.saveBasic.assignedTo = uBOForm.assignee;

    if (uBOForm.status == "Accept") {
      this.saveBasic.statusId = 1;
    }
    else if (uBOForm.status == "Reject") {
      this.saveBasic.statusId = 0;
    }
    this.tempRes.saveBasicDetails = this.saveBasic;
    this.tempRes.saveBasicDetails.condensingTypeId = this.condId;
    console.log(this.tempRes)
    // if (this.isDataSaved) {
    //   this.updateStatusAndSave(this.savedReqQuotForm);
    // } else {
    this._ItoFramesPwrPriceService.createUBOPriceUpdateRequest(this.tempRes).subscribe(resp => {
      console.log(resp);

      this.successMsg.push(resp.successMsg);
      this.message = true;
      this.updateStatusAndSave(resp);
    });
    // }
  }

  updateStatusAndSave(resp) {

    if (this.storage.get(this.currentRole) === "UBO_APPROVER") {
      resp.saveBasicDetails.assignedTo = this.modifiedBy;
      this._ItoFramesPwrPriceService.updateStatus(resp).subscribe(respon => {
        console.log(respon);
        this._ItoFramesPwrPriceService.saveUBOUpdatedPrice(respon).subscribe(respo => {
          console.log(respo);
          this.message = true;
          this.successMsg.push(respo.successMsg);

        });
      });
    } else {
      this._ItoFramesPwrPriceService.updateStatus(resp).subscribe(respon => {
        console.log(respon);
        this.message = true;
        this.successMsg.push(respon.successMsg);

      });
    }

    this.isDataSaved = false;
  }


  updateRowGroupMetaData() {
    console.log(this.uboListArray)
    this.uboListArray = this.uboListArray.sort(function (obj1, obj2) {
      return obj1.cat_ID - obj2.cat_ID;
    })
    this.rowGroupMetadata = {};
    if (this.uboListArray) {
      for (let i = 0; i < this.uboListArray.length; i++) {
        let rowData = this.uboListArray[i];
        let cat_ID = rowData.cat_ID;
        if (i == 0) {
          this.rowGroupMetadata[cat_ID] = { index: 0, size: 1 };
        }
        else {
          let previousRowData = this.uboListArray[i - 1];
          let previousRowGroup = previousRowData.cat_ID;
          if (cat_ID === previousRowGroup)
            this.rowGroupMetadata[cat_ID].size++;
          else
            this.rowGroupMetadata[cat_ID] = { index: i, size: 1 };
        }
      }
    }
  }

  calculateTotal() {
    let tempArry = this.uboListArray;
    for (var j = 0; j < this.uboListArray.length; j++) {
      this.total = 0;
      for (var i = 0; i < tempArry.length; i++) {
        if (this.uboListArray[j].cat_NM == tempArry[i].cat_NM) {
          this.total = this.total + tempArry[i].previousPrice;
        }
        this.uboListArray[j].matPrice = this.total;
      }
    }
  }

  handleChange(event) {
    var index = event.index;
    console.log(index)
    this.turbineType = '';
    this.turbineDesign = '';
    this.bleedTypID = 0;
    this.framePwrID = 0;
  }

  statusSel(val) {
    if (val == "Accept")
      this.enableStatus = true;
    else if (val == "Reject")
      this.enableStatus = false;
  }

  closeMessage() {

    this.message = false;
    this.successMsg = [];
    console.log(this.uboListArray)
    for (let s = 0; s < this.uboListArray.length; s++) {
      for (let q = 0; q < this.updatedUBOList.length; q++) {
        if (this.uboListArray[s].f2F_DET_ID == this.updatedUBOList[q].f2F_DET_ID) {
          let butn = document.getElementById(this.uboListArray[s].f2F_DET_ID).style.backgroundColor = "";
        }
      }
    }
    this.updatedUBOList = [];
    this.selectedRange = '';
    this.uboForm.reset();
    if (!this.isDataSaved) {
      this.navigateToWorkFlow();
    }
  }
  navigateToWorkFlow() {
    this.router.navigate(['MyWorkFlow']);
  }

}

