import { Component, OnInit } from '@angular/core';
import { ITOBasicDetailsService } from '../ito-basic-details/ito-basic-details.service';
import { itoAdminBasicDetails } from '../ito-basic-details/ito-basic-details';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ItoFramesPwrPriceService } from '../ito-admin-frames-pwr-price/ito-admin-frames-pwr-price.service';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';

@Component({
  selector: 'app-ito-f2f-component',
  templateUrl: './ito-f2f-component.component.html',
  styleUrls: ['./ito-f2f-component.component.css']
})
export class ItoF2fComponentComponent implements OnInit {


  quotForm: any;
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

  UBOApprove: boolean = false;
  UBOReview: boolean = false;
  dispDropdown: boolean = true;
  isDataSaved: boolean = false;
  displayBleed: boolean = false;
  contains: boolean = false;
  message: boolean = false;
  updMessage: boolean = false;
  UBOEdit: boolean = false;
  enablePrevPrice: boolean = false;
  displayCond: boolean = false;
  hideprogress: boolean = false;

  prevRemarks: Array<any> = [];
  framePowerArray: Array<any> = [];
  frameArray: Array<any> = [];
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

  turbineDesign: string;
  turbineType: string;
  bleedType: string;
  successMsg: string;
  labelName: string;
  updSuccessMsg: string;
  condName: string;
  loginUserDetails: string = "userDetail";
  currentRole: string = 'selRole';

  framePwrID: number;
  bleedTypID: number;
  total: number;
  frmPower: number;
  condId: number;

  constructor(private _ITOBasicDetailsService: ITOBasicDetailsService, private _ItoFramesPwrPriceService: ItoFramesPwrPriceService, private _Router: Router
    , private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOMyWorkListPageService: ITOMyWorkListPageService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService) {
    this.hideprogress = true;
    this._ITOcustomerRequirementService.getQuotationList().subscribe(res => {
      this.tempRes = res;
      console.log(this._ITOcustomerRequirementService.saveBasicDetails);
      this.tempRes.uboframeList.categoryDetId = this.bleedTypID;
      this.tempRes.uboframeList.framePowerId = this.framePwrID;
      this.tempRes.saveBasicDetails.condensingTypeId = this.condId;
      console.log(this.tempRes);
      this.getUBOCostData();
    })
  }

  ngOnInit() {

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


  getUBOCostData() {
    console.log(this.tempRes);
    this._ItoFramesPwrPriceService.framePwrPriceDetails(this.tempRes).subscribe(resp => {
      console.log(resp);

      this.uboListArray = resp.f2FUBOList;
      //this.uboListforNewFrames = resp.f2FUBOList;

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

  SaveAsDraft(updateUBOPriceForm) {
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
      this.saveBasic.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.message = true;
      this.successMsg = resp.successMsg;
    });
  }

  updateUBOPrice(uBOForm) {
    console.log(uBOForm);
    console.log(this.updatedUBOList);

    this.tempRes.f2FUBOsetterList = this.updatedUBOList;
    this.message = true;
    for (let q = 0; q < this.updatedUBOList.length; q++) {
      if (this.updatedUBOList[q].f2F_DET_ID == 0) {
        this.saveBasic.updateCode = "UPD_UBON";
      } else {
        this.saveBasic.updateCode = "UPD_UBO";
      }
    }

    this.saveBasic.modifiedById = this.modifiedBy;
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
    if (this.isDataSaved) {
      this.updateStatusAndSave(this.savedReqQuotForm);
    } else {
      this._ItoFramesPwrPriceService.createUBOPriceUpdateRequest(this.tempRes).subscribe(resp => {
        console.log(resp);
        this.saveBasic.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
        this.successMsg = resp.successMsg;
        this.message = true;
        this.updateStatusAndSave(resp);
      });
    }
  }

  updateStatusAndSave(resp) {

    if (this.storage.get(this.currentRole) === "UBO_APPROVER") {
      resp.saveBasicDetails.assignedTo = this.modifiedBy;
      this._ItoFramesPwrPriceService.updateStatus(resp).subscribe(respon => {
        console.log(respon);
        this._ItoFramesPwrPriceService.saveUBOUpdatedPrice(respon).subscribe(respo => {
          console.log(respo);
          this.message = true;
          this.successMsg = respo.successMsg;
        });
      });
    } else {
      this._ItoFramesPwrPriceService.updateStatus(resp).subscribe(respon => {
        console.log(respon);
        this.updMessage = true;
        this.updSuccessMsg = respon.successMsg;
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

}

