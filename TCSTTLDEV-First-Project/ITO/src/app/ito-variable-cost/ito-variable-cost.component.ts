import { Component, OnInit } from '@angular/core';
import { ItoVariableCostService } from './ito-variable-cost.service';
import { NgForm } from '@angular/forms';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { ITOLoginService } from '../app.component.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';


@Component({
  selector: 'app-ito-variable-cost',
  templateUrl: './ito-variable-cost.component.html',
  styleUrls: ['./ito-variable-cost.component.css']
})
export class ItoVariableCostComponent implements OnInit {

  variableCost: Array<any> = [];
  variableCArray: Array<any> = [];
  wrntyCostList: Array<any> = [];
  varCostLocalStorage: Array<any> = [];
  profitDrpDownList: Array<any> = [];

  noOfVisits: number;
  splProvision: number;
  travelExps: string = "NO";
  travelExps1: any = 0;
  ticketCostV: number;
  cTurbines: number = 0;
  cDbos: number = 0;
  cOtherss: number = 0;
  saless: number = 0;
  enggs: number = 0;
  interests: number = 0;
  otherss: number = 0;
  profitVal: number = 0;
  ldLateDelVal: number = 0;
  ldPerVal: number = 0;
  intrestPercentage: number = 0;
  insurance: number = 0;
  BG1Val: number;
  BG2Val: number;
  //agencyComm: number = 0;
  totalVariableCost: number = 0;
  overWrittenCost: number;

  varCostLocal: string = 'varCostLocal';
  scopeofsupp: string = 'scopeOfsup';
  wrntyLabel: string = '';
  ProfitLabel: string = '';
  ldLateDelLabel: string = '';
  BG1Label: string = '';
  BG2Label: string = '';
  ldPerLabel: string = '';
  remarksVal: string = '';
  successMsg: string = "";

  enableOverwriteDiv: boolean = false;
  overWrittenCostFlag: boolean = false;
  enableRemarks: boolean = false;
  message: boolean = false;
  varSuccess: boolean = false;
  disableBtn: boolean = false;
  backBtn: boolean = false;
  otherRemarks: any;
  varCostStg: any;
  quotFormResp: any;
  otherCostBean: any;
  varRes: any;
  warantyPeriod: number = 0;
  travelVisit: boolean =  false; //To display input fields on selection of travel Expenses field
  appDisable: boolean=false;
mainSave:boolean=true;
currentRole: string = 'selRole';
rewApp: boolean  = false;

  constructor(private _ItoVariableCostService: ItoVariableCostService, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOLoginService: ITOLoginService
    , private _ITOturbineConfigService: ITOturbineConfigService,
    private _ITOCostEstimationService: ITOCostEstimationService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private router: Router,private _ITOeditQoutService:ITOeditQoutService) {

      if(this._ITOeditQoutService.checkEdit == false){
        this.backBtn = true;
      }
      this._ITOeditQoutService.button1=false;
      this._ITOeditQoutService.button2=false;
      this._ITOeditQoutService.button3=false;
      this._ITOeditQoutService.button4=false;
      this._ITOeditQoutService.button5=false;
      this._ITOeditQoutService.button6=false;
      this._ITOeditQoutService.button7=false;
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=true;
      let currentRole = this.storage.get(this.currentRole);
      if(currentRole == "QUOT_REVIWER" || currentRole == "QUOT_APPROVER"){
        this.rewApp = true;
      }
      if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
        this.appDisable=true;
      }else{
        this.appDisable=false;
      }
    this.quotFormResp = this._ITOcustomerRequirementService.quotForm;
    console.log(this.quotFormResp)
    this._ItoVariableCostService.fetchCacheData().subscribe(res => {
      console.log(res);
      this.variableCost = res.varQuestionsBean;
      console.log(this.variableCost)
      for (let r = 0; r < this.variableCost.length; r++) {
        if (this.variableCost[r].dropDownType.code == "WR") {
          this.wrntyLabel = this.variableCost[r].dropDownType.value;
          this.wrntyCostList = this.variableCost[r].dropDownValueList;
          //this.variableCost.splice(r, 1);
        }

        if (this.variableCost[r].dropDownType.code == "INST_CHR") {
          this.intrestPercentage = this.variableCost[r].dropDownValueList[0].percentage;
          //this.variableCost.splice(r, 1);
        }
        if (this.variableCost[r].dropDownType.code == "BG_CHR2") {

          this.BG2Label = this.variableCost[r].dropDownType.value;
          this.BG2Val = this.variableCost[r].dropDownValueList[0].percentage;

          //this.variableCost.splice(r, 1);
        }

        if (this.variableCost[r].dropDownType.code == "LD_DL") {

          this.ldLateDelLabel = this.variableCost[r].dropDownType.value;
          this.ldLateDelVal = this.variableCost[r].dropDownValueList[0].percentage;

          //this.variableCost.splice(r, 1);
        }

        if (this.variableCost[r].dropDownType.code == "LD_PER") {

          this.ldPerLabel = this.variableCost[r].dropDownType.value;
          this.ldPerVal = this.variableCost[r].dropDownValueList[0].percentage;
          //this.variableCost.splice(r, 1);
        }
        if (this.variableCost[r].dropDownType.code == "PR") {
          this.profitDrpDownList = this.variableCost[r].dropDownValueList;
          this.variableCost.splice(r, 1);
        }
        if (this.variableCost[r].dropDownType.code == "BG_CHR1") {
          this.BG1Label = this.variableCost[r].dropDownType.value;
          this.BG1Val = this.variableCost[r].dropDownValueList[0].percentage;
          //this.variableCost.splice(r, 1);
        }


      }


      for (let s = 0; s < this.profitDrpDownList.length; s++) {
        if (this.profitDrpDownList[s].dependKey == "VR" && this.profitDrpDownList[s].regionId == this._ITOcustomerRequirementService.saveBasicDet.regionId) {
          this.profitVal = this.profitDrpDownList[s].percentage;

        }
      }

      this.varCostLocalStorage[this.varCostLocal] = this.storage.get(this.varCostLocal);
      console.log(this.storage.get(this.varCostLocal));
      if (this.varCostLocalStorage[this.varCostLocal]) {
this.mainSave=false;
        this._ItoVariableCostService.getVarCostDet(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(varCost => {
          console.log(varCost)
          this.quotFormResp = varCost;
          if (varCost.variableCostList.length > 0) {
            this.varCostStg = varCost.variableCostList[0];
            //this.saveInLocal(this.varCostLocal, this.varCostStg);

            this.varSuccess = true;
            this.otherCostBean = this.varCostLocalStorage[this.varCostLocal];
            console.log(this.otherCostBean);
            this.quotFormResp.otherCostsBean = this.otherCostBean;
            this.varRes = this.quotFormResp;
            this.totalVariableCost = this.otherCostBean.totalVariableCost;
            this.travelExps1 = this.otherCostBean.inTravelExpensesReq;
            if(this.travelExps1== 0){
              this.travelExps = "NO";
              this.travelVisit =  false;
            }else{
              this.travelExps = "YES";
              this.travelVisit = true;
              this.noOfVisits = this.otherCostBean.inNoOfVisit;
              this.ticketCostV = this.otherCostBean.inCostForEachVisit;
            }
            
            this.splProvision = this.otherCostBean.splProvision;
            this.cTurbines = this.otherCostBean.turbineContigency;
            this.cDbos = this.otherCostBean.dboContigency;
            this.saless = this.otherCostBean.salesExpenses;
           // this.agencyComm = this.otherCostBean.inpAgencyCommission;
            this.enggs = this.otherCostBean.engineCharges;
            this.interests = this.otherCostBean.intrestNoOfMonths;
            this.otherss = this.otherCostBean.others;
            this.warantyPeriod = this.otherCostBean.warrantyPeriod;
            this.profitVal = this.otherCostBean.varProfit;
            this.ldLateDelVal = this.otherCostBean.inpLdforLateDelivery;
            this.ldPerVal = this.otherCostBean.inpLdPerformance;
            this.intrestPercentage = this.otherCostBean.intrestPercentage;
            this.insurance = this.otherCostBean.insurance;
            this.BG1Val = this.otherCostBean.inpBG1;
            this.BG2Val = this.otherCostBean.inpBG2;

            if (this.otherCostBean.varNewFlag) {
              this.overWrittenCost = this.otherCostBean.varNewCost;
            } else {
              this.overWrittenCost = 0;
            }
            if (this.otherss > 0)
              this.enableRemarks = true;
            this.otherRemarks = this.otherCostBean.othersRemarks;
          }
        });
      }


    });
  }

  ngOnInit() {

  }

  enableyes(){
    this.mainSave=true;

    this.travelVisit = true;
      this.travelExps1 = 1;
  }

  enableNo(){
    this.mainSave=true;

    this.travelVisit = false;
      this.travelExps1 = 0;
  }

  getVarCost(variableC) {
    this.mainSave=true;

    this.varSuccess = false;
    this.overWrittenCost = 0;
    this.quotFormResp.saveBasicDetails.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId
    this.quotFormResp.otherCostsBean.inTravelExpensesReq = this.travelExps1;
    this.quotFormResp.otherCostsBean.inNoOfVisit = this.noOfVisits;
    this.quotFormResp.otherCostsBean.inCostForEachVisit = this.ticketCostV;
    this.quotFormResp.otherCostsBean.splProvision = this.splProvision;
    this.quotFormResp.otherCostsBean.turbineContigency = this.cTurbines;
    this.quotFormResp.otherCostsBean.dboContigency = this.cDbos;
   // this.quotFormResp.otherCostsBean.inpAgencyCommission = this.agencyComm;
    this.quotFormResp.otherCostsBean.salesExpenses = this.saless;    
    this.quotFormResp.otherCostsBean.engineCharges = this.enggs;
    this.quotFormResp.otherCostsBean.intrestNoOfMonths = this.interests;
    this.quotFormResp.otherCostsBean.others = this.otherss;
    this.quotFormResp.otherCostsBean.otherRemarks = this.otherRemarks;
    this.quotFormResp.otherCostsBean.warrantyPeriod = this.warantyPeriod;
    this.quotFormResp.otherCostsBean.varProfit = this.profitVal;
    this.quotFormResp.otherCostsBean.inpLdforLateDelivery = this.ldLateDelVal;
    this.quotFormResp.otherCostsBean.inpLdPerformance = this.ldPerVal;
    this.quotFormResp.otherCostsBean.inpBG1 = this.BG1Val;
    this.quotFormResp.otherCostsBean.inpBG2 = this.BG2Val;
    this.quotFormResp.otherCostsBean.intrestPercentage = this.intrestPercentage;
    this.quotFormResp.otherCostsBean.insurance = this.insurance;
    console.log(this.quotFormResp);
     console.log(this.quotFormResp.saveBasicDetails.quotId);
      console.log(this.quotFormResp.otherCostsBean.inTravelExpensesReq);
        console.log(this.quotFormResp.otherCostsBean.inNoOfVisit);
          console.log(this.quotFormResp.otherCostsBean.inCostForEachVisit);
            console.log(this.quotFormResp.otherCostsBean.splProvision);
              console.log(this.quotFormResp.otherCostsBean.insurance);
                console.log(this.quotFormResp.otherCostsBean.turbineContigency);
                  console.log(this.quotFormResp.otherCostsBean.dboContigency);
                 //   console.log(this.quotFormResp.otherCostsBean.inpAgencyCommission);
                      console.log(this.quotFormResp.otherCostsBean.salesExpenses);
                        console.log(this.quotFormResp.otherCostsBean.engineCharges);
                          console.log(this.quotFormResp.otherCostsBean.intrestNoOfMonths);
                            console.log(this.quotFormResp.otherCostsBean.others);
                              console.log(this.quotFormResp.otherCostsBean.warrantyPeriod);
                                console.log(this.quotFormResp.otherCostsBean.varProfit);
                                  console.log(this.quotFormResp.otherCostsBean.inpLdforLateDelivery);
                                    console.log(this.quotFormResp.otherCostsBean.inpLdPerformance);
                                      console.log(this.quotFormResp.otherCostsBean.intrestPercentage);
                                        console.log(this.quotFormResp.otherCostsBean.inpBG1);
    this._ItoVariableCostService.getVariableCost(this.quotFormResp).subscribe(varRes => {
      console.log(varRes)
      this.varRes = varRes;
      if (varRes.successCode == 0) {
        this.otherCostBean = varRes.otherCostsBean;
        this.totalVariableCost = this.otherCostBean.totalVariableCost;
        this.varSuccess = true;
        this._ITOcustomerRequirementService.sendcomBtnStatus(true);
      } else {
        this._ITOLoginService.openSuccMsg(varRes.successMsg);
      }

    });
  }

  checkRemarks() {
    this.mainSave=true;
    console.log(this.remarksVal)
    console.log(this.remarksVal.trim())
    if (this.remarksVal.trim() == null || this.remarksVal.trim() == "") {
      this.disableBtn = true;
    } else {
      this.disableBtn = false;
    }
  }
  enableOverWrite() {
    this.mainSave=true;
    this.enableOverwriteDiv = true;
    this.varSuccess = true;
    this.disableBtn = true;
  }
  disableOverWrite() {
    this.mainSave=true;
    this.enableOverwriteDiv = false;
    this.overWrittenCostFlag = false;
    this.disableBtn = false;
    this.overWrittenCost = 0;
    this.remarksVal = null;
  }
  checkOthers() {
    this.mainSave=true;
    if (this.otherss)
      this.enableRemarks = true;
    else
      this.enableRemarks = false;
  }

  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.varCostLocalStorage[key] = this.storage.get(key);
  }

  SaveVarCost(form) {
    this.mainSave=false;
    this.varRes.otherCostsBean.othersRemarks = this.otherRemarks;
    if (this.enableOverwriteDiv) {
      this.varRes.otherCostsBean.varNewFlag = true;
      this.varRes.otherCostsBean.varNewCost = form.updatedCost;
      this.varRes.otherCostsBean.varRemarks = form.remarks;
    } else {
      this.varRes.otherCostsBean.varNewFlag = false;
      this.varRes.otherCostsBean.varNewCost = 0;
    }
    if (this.overWrittenCost > 0) {
      this.varRes.otherCostsBean.varNewFlag = true;
      this.varRes.otherCostsBean.varNewCost = this.overWrittenCost;
    }
    console.log(this.varRes);
    this._ItoVariableCostService.saveVariableCost(this.varRes).subscribe(varRes1 => {
      console.log(varRes1);
      this.saveInLocal(this.varCostLocal, varRes1.otherCostsBean);

      if (varRes1.successCode == 0) {
        // this._ITOLoginService.openSuccMsg("Cost Saved Successfully");
        this.message = true;
        this.successMsg = "Cost Saved successfully";

        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'VC') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }

        //Calling saveRemarks to save overwriten cost and comments
   this._ITOcustomerRequirementService.saveBasicDet.groupCode = 'OVERWRITE';
   this._ITOcustomerRequirementService.saveBasicDet.scopeCode = 'VC';
   this._ITOcustomerRequirementService.saveBasicDet.overwrittenCost = this.overWrittenCost;
   this._ITOcustomerRequirementService.saveBasicDet.remarks = this.remarksVal;   
   console.log(this._ITOcustomerRequirementService.saveBasicDet);
   this._ITOAddOnComponentsService.saveRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(saveRem => {
     console.log(saveRem);
   })

        this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resAdd => {
          console.log(resAdd);
          this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);
        });
        if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false   ) {
          this._ITOcustomerRequirementService.editFlag = false;
          this.router.navigate(['/EditQuot']);
        }
      } else {
        this._ITOLoginService.openSuccMsg(varRes1.successMsg);
      }
    });
  }

  getPrevComments() {

    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "VC";
    this._ITOcustomerRequirementService.saveBasicDet.groupCode = "OVERWRITE";
    this._ITOAddOnComponentsService.getQuotRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(prevComRes => {

      if (prevComRes.remarksList.length > 0) {
        this._ITOAddOnComponentsService.dispPrevComments = true;
        this._ITOAddOnComponentsService.oldComms = prevComRes.remarksList;
      } else {
        // this._ITOLoginService.openSuccMsg("No Previous Comments found");
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
