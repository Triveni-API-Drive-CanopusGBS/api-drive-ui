import { Component, OnInit } from '@angular/core';
import { ItoVariableCostService } from '../ito-variable-cost/ito-variable-cost.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ItoSapresService } from './ito-sapers-component.service';
import { ITOLoginService } from '../app.component.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';


@Component({
  selector: 'app-ito-sapres-component',
  templateUrl: './ito-sapres-component.component.html',
  styleUrls: ['./ito-sapres-component.component.css']
})
export class ItoSapresComponentComponent implements OnInit {

  remarksValue: string;
  sparesArray: Array<any> = [];
  sparesLocalStorage: Array<any> = [];

  sparesCst: any;
  tempResp: any;
  otherCostsBean: any;
  spareCostTotal: any;
  remarks: string = '';
  successMsg: string = '';
  sparesLocal: string = 'sparesLocal';
  scopeofsupp: string = 'scopeOfsup';

 
  orderBookingSpares: number;
  ovrheadTotSaleCost: number;
  sparesNetProfit: number;
  totalSparesCost: number;
  ovrheadSparesCost: number;
  overWrittenCost: number = 0;
  sparesNewCost: number;
  profitVal: number;

  spareSuc: boolean = false;
  message: boolean = false;
  enableOverwriteDiv: boolean = false;
  overWrittenCostFlag: boolean = false;

  constructor(private _ItoVariableCostService: ItoVariableCostService,
    private __ItoSapresService: ItoSapresService, @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOLoginService: ITOLoginService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOturbineConfigService: ITOturbineConfigService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private router: Router,
    private _ITOCostEstimationService: ITOCostEstimationService) {

    this.tempResp = this._ITOcustomerRequirementService.quotForm;
    this._ItoVariableCostService.getDBOMechanicalCache().subscribe(res => {

      this.sparesArray = res.sparesQuestionsBean;

      for (let r = 0; r < this.sparesArray.length; r++) {
        if (this.sparesArray[r].dropDownType.code == "PR") {

          let dropdownlist = this.sparesArray[r].dropDownValueList;
          for (let s = 0; s < dropdownlist.length; s++) {
            if (dropdownlist[s].dependKey == "SP" && dropdownlist[s].custCode == this._ITOcustomerRequirementService.saveBasicDet.custCode) {
              this.profitVal = dropdownlist[s].percentage;
              this.sparesArray.splice(r, 1);
            }
          }
        }

      }

    });
  }

  ngOnInit() {
    this.sparesLocalStorage[this.sparesLocal] = this.storage.get(this.sparesLocal);
    console.log(this.sparesLocalStorage[this.sparesLocal]);
    if (this.sparesLocalStorage[this.sparesLocal]) {

      this.otherCostsBean = this.sparesLocalStorage[this.sparesLocal];
      this.tempResp.otherCostsBean = this.otherCostsBean;
      this.spareSuc = true;
      this.sparesCst = this.otherCostsBean.sparesCost;
      this.totalSparesCost = this.otherCostsBean.totalSparesCost;
      this.orderBookingSpares = this.otherCostsBean.orderBookingSpares;
      this.ovrheadTotSaleCost = this.otherCostsBean.ovrheadTotSaleCost;
      this.sparesNetProfit = this.otherCostsBean.sparesNetProfit;
      this.ovrheadSparesCost = this.otherCostsBean.ovrheadSparesCost;
      this.profitVal = this.otherCostsBean.inpSparesProfit;
      if (this.otherCostsBean.sparesNewFlag) {
        this.overWrittenCost = this.otherCostsBean.sparesNewCost;
        this.sparesNewCost = this.otherCostsBean.sparesNewCost;
      } else {
        this.overWrittenCost = 0;
      }
    }
  }

  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.sparesLocalStorage[key] = this.storage.get(key);
  }

  getSpareCost() {
    this.successMsg = '';
    this.overWrittenCost = 0;
    this.tempResp.otherCostsBean.sparesNewFlag = false;
    this.tempResp.otherCostsBean.sparesNewCost = 0;
    console.log(this.tempResp);
    this.spareSuc = false;
    this.tempResp.otherCostsBean.sparesCost = this.sparesCst;
    this.tempResp.otherCostsBean.inpSparesProfit = this.profitVal;
    this.__ItoSapresService.getSparesCost(this.tempResp).subscribe(spareCstRes => {
      if (spareCstRes.successCode == 0) {
        this.spareSuc = true;
        this.tempResp = spareCstRes;
        this.orderBookingSpares = spareCstRes.otherCostsBean.orderBookingSpares;
        this.ovrheadTotSaleCost = spareCstRes.otherCostsBean.ovrheadTotSaleCost;
        this.sparesNetProfit = spareCstRes.otherCostsBean.sparesNetProfit;
        this.totalSparesCost = spareCstRes.otherCostsBean.totalSparesCost;
        this.ovrheadSparesCost = spareCstRes.otherCostsBean.ovrheadSparesCost;
      } else {
        this.message = true;
        this.successMsg=spareCstRes.successMsg;
        //this._ITOLoginService.openSuccMsg(spareCstRes.successMsg);
      }


    })
  }
  enableOverWrite() {
    this.enableOverwriteDiv = true;
    this.spareSuc = true;
  }
  disableOverWrite() {
    this.enableOverwriteDiv = false;
    this.overWrittenCostFlag = false;
    this.overWrittenCost=0;
    this.sparesNewCost =  0;
    this.remarks = null;
    this.remarksValue="";
    this.tempResp.otherCostsBean.sparesNewFlag = false;
    this.tempResp.otherCostsBean.sparesNewCost = 0;
  }

  saveSparesCost(form) {

    if (this.overWrittenCost > 0) {
      this.tempResp.otherCostsBean.sparesNewFlag = true;
      this.tempResp.otherCostsBean.sparesNewCost = this.overWrittenCost;
    }
    if (this.enableOverwriteDiv) {
      this.tempResp.otherCostsBean.sparesNewFlag = true;
      this.tempResp.otherCostsBean.sparesNewCost = form.updatedCost;
      this.tempResp.otherCostsBean.sparesRemarks = form.remarks;
    }
    // else {
    //   this.tempResp.otherCostsBean.sparesNewFlag = false;
    //   this.tempResp.otherCostsBean.sparesNewCost = 0;
    // }
    this.__ItoSapresService.saveSparesCost(this.tempResp).subscribe(savedRes => {

      this.saveInLocal(this.sparesLocal, savedRes.otherCostsBean); //testing

      if (savedRes.successCode == 0) {
        //this._ITOLoginService.openSuccMsg("Cost Saved Successfully");
        this.message=true;
        this.successMsg = "Cost Saved successfully";

        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'SP') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }

          this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resAdd => {
            console.log(resAdd);
            this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);
          });
        if (this._ITOcustomerRequirementService.editFlag) {
          this._ITOcustomerRequirementService.editFlag = false;
          this.router.navigate(['/EditQuot']);
        }
      } else {

        this._ITOLoginService.openSuccMsg(savedRes.successMsg);
      }
    })
  }

  getPrevComments() {

    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "SPARES_COST";
    this._ITOcustomerRequirementService.saveBasicDet.groupCode = "OVERWRITE";
    this._ITOAddOnComponentsService.getQuotRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(prevComRes => {

      if (prevComRes.remarksList.length > 0) {
        this._ITOAddOnComponentsService.dispPrevComments = true;
        this._ITOAddOnComponentsService.oldComms = prevComRes.remarksList;
      } else {
        //this._ITOLoginService.openSuccMsg("No Previous Comments found");
	 this.message=true;
        this.successMsg = "No Previous Comments found";
      }
    });
  }
}
