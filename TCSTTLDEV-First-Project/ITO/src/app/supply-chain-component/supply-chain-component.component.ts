import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { NgForm } from '@angular/forms';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { SupplyChainServiceDetails } from './supply-chain-component.service';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';

@Component({
  selector: 'app-supply-chain-component',
  templateUrl: './supply-chain-component.component.html',
  styleUrls: ['./supply-chain-component.component.css']
})
export class SupplyChainComponentComponent implements OnInit {
  //variable to store cost details
  hidespinner: boolean = false;
  overWrittenCost: any;
  estimateCost: any;
  turbineCost: any;
  condensingCost: any;
  f2fCost: Array<any> = [];
  //vraiable to store turbine details
  turbineInstrumentList: Array<any> = [];
  //variable to store temp response
  tempRes1: any;
  //variable to store grand total
  grandTotal: number = 0;
  //variable to store temp response
  tempRes: any;
  //variable to store save basuc details
  saveBasic: any;
  //variable to add row group meta data
  rowGroupMetadata: any;
  //variable to add ubodata
  uboData: any;
  //variable to store excel cost
  ExlCost: any;
  //variable to store f2f data
  f2fData: any = [];
  //boolean variable to disable btn
  disableBtn: boolean = false;
  //variable to store remarks
  remarksVal: string = '';
  //boolean variable to display message
  message: boolean = false;
  //flag variable to hide progress bar
  hideprogress: boolean = false;
  //flag variable to show no cost msg
  showNoCostMsg: boolean = false;
  //flag variable to enable overwrite
  enableOverWrite: boolean = false;
  //falg variable to enable overwrite
  enableOverWrite1: boolean = false;
  //falg variable to saev div
  saveDiv: boolean = false;
  //flag to enable status
  enableStatus: boolean = true;
  // Array to store UBO list
  uboListArray: Array<any> = [];
  //Success msg display variable
  successMsg: any;
  //variable to store scope of supply
  scopeofsupp: string = 'scopeOfsup';
  //variable to store f2fcost data
  f2fCostData: string = 'f2fCostData';
  //variable to store total
  total: number;
  backBtn: boolean = false;
  mainSave:boolean=false;

  constructor(private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _SupplyChainServiceDetails: SupplyChainServiceDetails, private _Router: Router
    , private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOMyWorkListPageService: ITOMyWorkListPageService,
    private _ITOturbineConfigService: ITOturbineConfigService, private _ITOAddOnComponentsService: ITOAddOnComponentsService, private _ITOeditQoutService: ITOeditQoutService,
    private router: Router, private route: ActivatedRoute, private _ITOCostEstimationService: ITOCostEstimationService) {
    this.hideprogress = true;
    if(this._ITOeditQoutService.checkEdit == false){
      this.backBtn = true;
    }
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=true;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this._SupplyChainServiceDetails.getQuotFormData().subscribe(res => {

      this.tempRes = res;

      this._SupplyChainServiceDetails.getF2FUboData(this._ITOcustomerRequirementService.saveBasicDet).subscribe(f2fResp => {

        this.uboData = f2fResp.uboData;
        this.ExlCost = this.uboData.totalF2FCost;
        this.saveDiv = true;

        this.f2fCost[this.f2fCostData] = this.storage.get(this.f2fCostData);
        console.log(this.f2fCost[this.f2fCostData],this.ExlCost)
        if (this.f2fCost[this.f2fCostData]) {
          console.log(this.f2fCost[this.f2fCostData])
          if (this.f2fCost[this.f2fCostData].overwrittenPriceFlg) {
            this.ExlCost = this.uboData.totalF2FCost;
            this.enableOverWrite = true;
            this.enableOverWrite1 = false;
            this.overWrittenCost = this.f2fCost[this.f2fCostData].overwrittenPrice;
            this.uboData.overwrittenPriceFlg = true;
          }
        }
        // } else {
        //   this.ExlCost = this.uboData.totalF2FCost;
        // }
        this.hideprogress = false;
      })
    })
  }

  ngOnInit() {

  }
  //methods to save in local
  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.f2fData[key] = this.storage.get(key);
  }
  //method to check remarks
  checkRemarks() {
    console.log(this.remarksVal)
    console.log(this.remarksVal.trim())
    if (this.remarksVal.trim() == null || this.remarksVal.trim() == "") {
      this.disableBtn = true;
    } else {
      this.disableBtn = false;
    }
  }
  //method to get detailed cost
  getDetailedCost() {
    this.hidespinner= true;
    this.tempRes.uboframeList.categoryDetId = this._ITOcustomerRequirementService.saveBasicDet.bleedTypeId;
    this.tempRes.uboframeList.framePowerId = this._ITOcustomerRequirementService.saveBasicDet.framePowerId;
    this.tempRes.saveBasicDetails.condensingTypeId = this._ITOcustomerRequirementService.saveBasicDet.condensingTypeId;

    this.getUBOCostData();
    this.hidespinner = false;
    // this._SupplyChainServiceDetails.getFrameForm().subscribe(resp => {

    //   this.tempRes1 = resp;
    //   this.turbineInstrumentList = resp.turbineInstrumentsWithFramelist;
    //   for (var i = 0; i < this.turbineInstrumentList.length; i++) {
    //     if (this.turbineInstrumentList[i].bleedTypId == this._ITOcustomerRequirementService.saveBasicDet.bleedTypeId
    //       && this.turbineInstrumentList[i].framePowerId == this._ITOcustomerRequirementService.saveBasicDet.framePowerId
    //       && this.turbineInstrumentList[i].condTypId == this._ITOcustomerRequirementService.saveBasicDet.condensingTypeId) {
    //       this.turbineCost = this.turbineInstrumentList[i].turbInstrCost;
    //       this.condensingCost = this.turbineInstrumentList[i].condInstrCost;
    //       this.estimateCost = this.turbineInstrumentList[i].estimateCost;
    //     }
    //   }

    // })
  }
  //method to get ubo cost
  getUBOCostData() {
    this.showNoCostMsg = false;
    this._SupplyChainServiceDetails.framePwrPriceDetails(this.tempRes).subscribe(resp => {
console.log(resp)
      this.uboListArray = resp.f2FUBOList;
      //this.uboListforNewFrames = resp.f2FUBOList;
      if (this.uboListArray.length == 0)
        this.showNoCostMsg = true;
      this.updateRowGroupMetaData();
      this.grandTotal = 0;
      for (var j = 0; j < this.uboListArray.length; j++) {
        this.total = 0;
        for (var i = 0; i < resp.f2FUBOList.length; i++) {
          if (this.uboListArray[j].cat_NM == resp.f2FUBOList[i].cat_NM) {
            this.total = this.total + resp.f2FUBOList[i].price;
            //this.grandTotal=this.grandTotal+resp.f2FUBOList[i].price;
          }
          this.uboListArray[j].matPrice = this.total;

        }
      }

      for (var k = 0; k < this.uboListArray.length; k++) {
        if (this.uboListArray[k].matPrice > 0) {
          this.grandTotal = this.grandTotal + this.uboListArray[k].price;
        }

      }

    });
  }

  //method to update row group data
  updateRowGroupMetaData() {

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
  //method to calculate total
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
  //method to enable overwrite
  OverWrite() {
    this.mainSave=true;
    this.enableOverWrite = true;
    this.enableOverWrite1 = true;
    this.disableBtn = true;
  }
  //method to cancel overwrite
  cancelOverWrite() {
    this.enableOverWrite = false;
    this.enableOverWrite1 = false;
    this.disableBtn = false;
    this.remarksVal = "";

  }
  // method to save F2F data
  savef2fTotal(form) {
    this.mainSave=false;
    console.log(form)
    this.message = false;

    if (this.enableOverWrite) {
      this.uboData.overwrittenPriceFlg = true;
      this.uboData.overwrittenPrice = form.updatedCost;
      // this.ExlCost = form.updatedCost;
      this.uboData.remarks = form.remarks;
    } else {
      this.uboData.overwrittenPriceFlg = false;
      this.uboData.overwrittenPrice = 0;
      this.uboData.remarks = null;
      //this.ExlCost=this.uboData.totalF2FCost;
      if (this.f2fCost[this.f2fCostData]) {
        this.f2fCost[this.f2fCostData].overwrittenPriceFlg = false;
      }
    }
    console.log("nidhiii");
    console.log(this.f2fCost[this.f2fCostData]);
    if (this.f2fCost[this.f2fCostData]) {
      if (this.f2fCost[this.f2fCostData].overwrittenPriceFlg) {
        this.uboData.overwrittenPrice = form.updatedCost;
        this.uboData.overwrittenPriceFlg = true;
      }
    }

    this._ITOcustomerRequirementService.saveBasicDet.uboData = this.uboData;
    console.log(this._ITOcustomerRequirementService.saveBasicDet.uboData)
    this._SupplyChainServiceDetails.saveF2FUboData(this._ITOcustomerRequirementService.saveBasicDet).subscribe(f2fResp => {
      this.saveInLocal(this.f2fCostData, { overwrittenPriceFlg: this.uboData.overwrittenPriceFlg, overwrittenPrice: this.uboData.overwrittenPrice });
      if (f2fResp.successCode == 0) {
        this._ITOcustomerRequirementService.sendturBtnStatus(true);
        this.message = true;
        this.successMsg = "Cost Saved Successfully.";

        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'F2F') {

            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._SupplyChainServiceDetails.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }

        this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOneLine => {
          this._ITOturbineConfigService.sendMessage(resOneLine.oneLineBomExcel);

          console.log(this._ITOcustomerRequirementService.editFlag)
          if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
            this._ITOcustomerRequirementService.editFlag = false;
            this.router.navigate(['/EditQuot']);
          } 
        });
      } else {
        this.message = true;
        this.successMsg = f2fResp.successMsg;
      }
    });

  }
  //method to get previous comments
  getPrevComments() {
    this.message = false;
    this.successMsg = [];
    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "F2F";
    this._ITOcustomerRequirementService.saveBasicDet.groupCode = "OVERWRITE";
    this._ITOAddOnComponentsService.getQuotRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(prevComRes => {

      if (prevComRes.remarksList.length > 0) {
        this._ITOAddOnComponentsService.dispPrevComments = true;
        this._ITOAddOnComponentsService.oldComms = prevComRes.remarksList;
      } else {
        this.message = true;
        this.successMsg = "No Previous Comments found";
      }
    });
  }
        //To navigate edit quotaion page on click of back button
        backButton(){
          this.router.navigate(['/EditQuot']);
        }
}





