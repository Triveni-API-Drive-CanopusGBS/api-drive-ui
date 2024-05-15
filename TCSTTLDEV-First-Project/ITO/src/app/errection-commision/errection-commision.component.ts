import { Component, OnInit } from '@angular/core';
import { ITOLoginService } from '../app.component.service';
import { dboClass } from '../ito-performance/ito-performance';
import { ITOerrectionCommisionService } from './errection-commision.service';
import { erectionCommissionBean } from './errection-comission';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOcustomerDetailsService } from '../ito-customer-details/ito-customer-details.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ItoPerformanceService } from '../ito-performance/ito-performance.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';

@Component({
  selector: 'app-errection-commision',
  templateUrl: './errection-commision.component.html',
  styleUrls: ['./errection-commision.component.css']
})
export class ErrectionCommisionComponent implements OnInit {

  enableOverWrite: boolean = false;     // boolean to enable overwite button
  enableLoadingDiv: boolean = false;    // boolean to enable/disabele Loading div in html
  enableLodingDiv: boolean = false;     // boolean to enable/disabele Lodging div in html
  enablePriceDiv: boolean = false;      // boolean to enable/disabele price div in html,if No E&C price as type of charge div should be hidden
  costDiv: boolean = false;
  custype: boolean = true;              // this is set to true if customer is Domestic, if customer is export it is set to false
  expManualDaysDiv: boolean = false;    // manual days should be shown for export customer
  expECDiv: boolean = false;
  message: boolean = false;
  saveDiv: boolean = false;
  disableOverWriteBtn: boolean = true;
  disableBtn: boolean = false;
  ChargerType: Array<any> = [];   // type of chrge list
  custTypeList: Array<any> = [];  // type of customer list
  loadingType: Array<any> = [];   // type of Loading type list
  lodgingList: Array<any> = [];   // type of Lodging list
  lodgingType: Array<any> = [];   // type of Lodging type list
  currencyList: Array<any> = [];  // type of currency list
  ecKey: Array<any> = [];         // local storage variable
  statesList: Array<any> = [];     // type of states list
  dboClass: dboClass;
  custTypeVal: String;
  typOfChargeCd: String;
  loadingCd: String;
  lodgingCd: String;
  successMsg: string = "";
  ECData: string = 'ecKey';
  scopeofsupp: string = 'scopeOfsup';
  dboFormDataaa: any;
  ExlCost: number;
  manDays: any;
  typOfChargeId: number;
  noCost: number = 0;
  overWrittenECCost: number;
  itemId: number;
  ssId: number;
  tempResp: any;
  ecBeanData: any;
  typOfCharge: any;
  loadingName: any;
  lodgingName: any;
  state: any;
  overWrittenCost: any;
  remarksVal: any;
  lumpsomeCost: any;
  quotId: any;
  backBtn: boolean = false;
  techremarks:string=null;
  disableData:boolean=false;
  disableDescription:boolean=false;
  noOfdays:number=0;
  noOfdaysboolean:boolean=false;
  mainSave:boolean=true;
  currentRole: string = 'selRole';
  rewApp: boolean  = false;
  description:string="";
  mainSave2:boolean=false;


  constructor(private _ITOerrectionCommisionService: ITOerrectionCommisionService,
    private _ITOcustomerDetailsService: ITOcustomerDetailsService, private _ITOCostEstimationService: ITOCostEstimationService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOeditQoutService:ITOeditQoutService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private router: Router,
    private _ActivatedRoute: ActivatedRoute, private _ITOturbineConfigService: ITOturbineConfigService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private _ITOLoginService: ITOLoginService,
  private _ItoPerformanceService: ItoPerformanceService) {

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
    this._ITOeditQoutService.button8=true;
    this._ITOeditQoutService.button9=false;
    let currentRole = this.storage.get(this.currentRole);
      if(currentRole == "QUOT_REVIWER" || currentRole == "QUOT_APPROVER"){
        this.rewApp = true;
      }
	  
    if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard")
    {
      this.disableData=true;
    }
    //setting customer Type to local variable from saveBasicDetails Bean
    if(this._ITOcustomerRequirementService.saveBasicDet.custType=="Domestic" || this._ITOcustomerRequirementService.saveBasicDet.custCodeNm=="Domestic")
    {
      this._ITOcustomerRequirementService.saveBasicDet.custCode = "DM";
    }
    else
    {
      this._ITOcustomerRequirementService.saveBasicDet.custCode = "EX";
    }
    if (this._ITOcustomerRequirementService.saveBasicDet.custCode == "DM") {
      this.custype = true;
    } else if (this._ITOcustomerRequirementService.saveBasicDet.custCode == "EX") {
      this.custype = false;
    }
    
    this.custTypeVal = this._ITOcustomerRequirementService.saveBasicDet.custType;
    if(this.custTypeVal==null)
    {
      this.custTypeVal = this._ITOcustomerRequirementService.saveBasicDet.custCodeNm;
    }
    this._ItoPerformanceService.getDboFormData().subscribe(res => {
      console.log(res);
      this.dboFormDataaa = res;
    });
    this.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    console.log(this.quotId);
    //get dropdownvalues from Database by calling below function
    this._ITOerrectionCommisionService.getErrectionCommCache(this.quotId).subscribe(res => {
      console.log(res);
      this.tempResp = res;
      this.ecKey[this.ECData] = this.storage.get(this.ECData);
      
      //this.custTypeList = res.dropDownColumnvalues.enCustTypeList.ErectionCustTypes;
      //this.ChargerType = res.dropDownColumnvalues.enCCharges.ErectionCommCharges;
      this.ChargerType = res.dropDownColumnvalues.enCustTypeList.ErectionCustTypes;
      this.loadingType = res.dropDownColumnvalues.enCLoadingDomestic.EnCLoadingListDomestic;
      this.currencyList = res.dropDownColumnvalues.usdollerList.USDollerList;
      this.manDays = res.noOfMandays;

      for (var i = 0; i < res.dropDownColumnvalues.enCLodging.EnCLodgingList.length; i++) {
        if ((res.dropDownColumnvalues.enCLodging.EnCLodgingList[i].categoryDetCode == "CS") ||
          (res.dropDownColumnvalues.enCLodging.EnCLodgingList[i].categoryDetCode == "TS")) {
          this.lodgingList.push(res.dropDownColumnvalues.enCLodging.EnCLodgingList[i]);
        }
        else {
          this.lodgingType.push(res.dropDownColumnvalues.enCLodging.EnCLodgingList[i]);
        }
      }
      this.onChangeChargers(this.ChargerType[0].categoryDetCode);
      this.mainSave2=false;
      // checking if local storage has data, if true, then update all he saved values
      console.log(this.ecKey[this.ECData]);
      if (this.ecKey[this.ECData] != null) {
        this.disableOverWriteBtn = false;
        this.ecBeanData = this.ecKey[this.ECData]
        this.techremarks=  this.ecBeanData.remarks;
        this.ecBeanData.ecTypeCd = this._ITOcustomerRequirementService.saveBasicDet.custCode;
        this.typOfCharge = this.ecBeanData.typeOfChargeId;
        this.typOfChargeCd = this.ecBeanData.typeOfChargeCd;
        this.loadingName = this.ecBeanData.loadingId;
        this.loadingCd = this.ecBeanData.loadingCd;
        this.lodgingName = this.ecBeanData.lodgingId;
        this.state = this.ecBeanData.stateId;
        this.lodgingCd = this.ecBeanData.lodgingCd;
        this.ExlCost = this.ecBeanData.price;
        this.description= this.ecBeanData.serviceRemarks;

        // for (let c = 0; c < this.custTypeList.length; c++) {
        //   if (this.ecBeanData.ecTypeCd == this.custTypeList[c].categoryDetCode) {
        //     this.tempResp.erectionCommissionBean.ecTypeId = this.custTypeList[c].categoryDetId;
        //     this.ecBeanData.ecTypeId = this.custTypeList[c].categoryDetId;;
        //   }
        // }
        if(this.typOfChargeCd=="SS_NA")
        {
          this.disableDescription=true;
        }
        if(this.typOfChargeCd=="SS_SUPER" && this._ITOcustomerRequirementService.saveBasicDet.custCode == "DM")
        {
          this.noOfdaysboolean=true;
          this.noOfdays= this.ecBeanData.noOfManDays;
        }
        this.tempResp.erectionCommissionBean.ecType = this._ITOcustomerRequirementService.saveBasicDet.custCode;
        this.ecBeanData.ecType = this._ITOcustomerRequirementService.saveBasicDet.custCode;
        //storing all data to local response , so that all data will be captured while saving the EC cost again in edit flow
        this.tempResp.erectionCommissionBean.framePowerId = this._ITOcustomerRequirementService.saveBasicDet.framePowerId;
        this.tempResp.erectionCommissionBean.stateId = this.ecBeanData.stateId;
        this.tempResp.erectionCommissionBean.condensingTypeId = this._ITOcustomerRequirementService.saveBasicDet.condensingTypeId;
        this.tempResp.erectionCommissionBean.typeOfChargeId = this.ecBeanData.typeOfChargeId;
        this.tempResp.erectionCommissionBean.loadingId = this.ecBeanData.loadingId;
        this.tempResp.erectionCommissionBean.lodgingId = this.ecBeanData.lodgingId;
        this.tempResp.erectionCommissionBean.noOfManDays = this.ecBeanData.noOfManDays;

        // if no cost option is selcted this if will be true
        if (this.typOfChargeCd == "SS_NA") {
          this.noCost = 0;
        }

        //if cost was overwritten, show overwritten cst and remarks button
        if (this.ecBeanData.overwrittenPriceFlag) {
          this.disableOverWriteBtn = false;
          this.enableOverWrite = false;
          this.overWrittenCost = this.ecBeanData.overwrittenPrice;
          this.overWrittenECCost = this.ecBeanData.overwrittenPrice;
          this.remarksVal = this.ecBeanData.remarks;
        }
        this.enableDivs(this.typOfChargeCd);    // this is used enable or disable divs based on dropdown values saved
        if (this.ecBeanData.ecTypeCd == "DM") {
          this.saveDiv = true;
        } else if (this.ecBeanData.ecTypeCd == "EX" && this.typOfChargeCd == "SS_EC") //Export Customer
        {
          this.expECDiv = true;
          this.lumpsomeCost = this.ecBeanData.price;
          this.saveDiv = false;
        } else if (this.ecBeanData.ecTypeCd == "EX" && this.typOfChargeCd == "SS_SUPER") {
          this.expManualDaysDiv = true;
          this.manDays = this.ecBeanData.noOfManDays;
          this.saveDiv = true;
        }
        this.mainSave=false;
        this.mainSave2=false;


      }
    });

  }
  CostCheck()
  {
    this.mainSave=true;
    this.mainSave2=true;

  }
  ngOnInit() {
    this._ITOerrectionCommisionService.getStatesData().subscribe(res => {
      this.statesList = res.dropDownColumnvalues.stateList.stateList;
    });
  }

  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.ecKey[key] = this.storage.get(key);
  }

  // enabling overwrte div
  OverWrite() {
    this.mainSave=true;
    this.mainSave2=true;


    this.enableOverWrite = true;
    this.remarksVal = "";
    this.disableBtn = true;
    // this.overWrittenCost = 0;
  }

  // check whether space or empty values are entered in remarks field while overwriting the cost
  checkRemarks() {
    this.mainSave=true;
    this.mainSave2=true;


    console.log(this.remarksVal)
    console.log(this.remarksVal.trim())
    if (this.remarksVal.trim() == null || this.remarksVal.trim() == "") {
      this.disableBtn = true;
    } else {
      this.disableBtn = false;
    }
  }

  // when cancel button is clicked,overwrite button should be hidden, flag and price are reset.
  cancelOverWrite() {
    this.mainSave=true;
    this.mainSave2=true;


    this.enableOverWrite = false;
    this.tempResp.erectionCommissionBean.overwrittenPriceFlag = false;
    this.tempResp.erectionCommissionBean.overwrittenPrice = 0;
  }

  // on changing type of chrge, decide which dropdowns should be shown / hidden
  onChangeChargers(chargeCd) {
    this.mainSave=true;
    this.mainSave2=true;

    // for (var i = 0; i < this.custTypeList.length; i++) {
    //   if (this.custTypeList[i].categoryDetCode == this._ITOcustomerRequirementService.saveBasicDet.custCode)
    //     this.tempResp.erectionCommissionBean.ecTypeId = this.custTypeList[i].categoryDetId;
    // }
    this.tempResp.erectionCommissionBean.ecType = this._ITOcustomerRequirementService.saveBasicDet.custCode;
    this.ExlCost = 0;
    this.message = false;
    this.enableOverWrite = false;
    this.disableOverWriteBtn = true;
    this.enableDivs(chargeCd);
  }

  onChangeLoadings(val) {
    this.mainSave=true;
    this.mainSave2=true;


    this.enableOverWrite = false;
    this.message = false;
    this.saveDiv = false;
    this.disableOverWriteBtn = true;
    this.ExlCost = 0;
  }

  onChangeLodgings(val) {
    this.mainSave=true;
    this.mainSave2=true;


    this.enableOverWrite = false;
    this.message = false;
    this.saveDiv = false;
    this.disableOverWriteBtn = true;
    this.ExlCost = 0;
  }

  onStateChange(val) {
    this.mainSave=true;

    this.message = false;
    this.ExlCost = 0;
  }
  onInputChange(val) {
    this.mainSave=true;
    this.mainSave2=true;


    this.enableOverWrite = false;
    this.message = false;
    this.saveDiv = false;
    this.disableOverWriteBtn = true;
    this.ExlCost = 0;
  }

  // onChangeCurrency(val) {
  //   console.log(val)
  //   this.currencyId = val;
  //   for (let n = 0; n < this.currencyList.length; n++) {
  //     if (val == this.currencyList[n].categoryDetId)

  //       this.currencyVal = this.currencyList[n].price;
  //   }

  // }

  enableDivs(chargeCd) {
    this.mainSave=true;
    this.mainSave2=true;


    this.typOfChargeCd = chargeCd;
    for (let s = 0; s < this.ChargerType.length; s++) {
      if (chargeCd == this.ChargerType[s].categoryDetCode) {
        this.typOfChargeId = this.ChargerType[s].categoryDetId;
        this.tempResp.erectionCommissionBean.typeOfChargeId = this.ChargerType[s].categoryDetId;
        this.tempResp.erectionCommissionBean.typeOfChargeCd = this.ChargerType[s].categoryDetCode;
      }
    }
    // this if is executed for Domestic Customer
    if (this.custype) {
      this.expECDiv = false;

      if (chargeCd == "SS_SUPER") {  //"Supervision for E&C"
        this.enableLoadingDiv = false;
        this.enablePriceDiv = true;
        this.enableLodingDiv = true;
        this.costDiv = false;
        this.saveDiv = false;
      } else if (chargeCd == "SS_EC") {  //"Errection & Commissing"
        this.enableLoadingDiv = true;
        this.enableLodingDiv = true;
        this.enablePriceDiv = true;
        this.costDiv = false;
        this.saveDiv = false;
      } else if (chargeCd == "SS_NA") {  //"E&C not in TTL Scope"
        this.enableLoadingDiv = false;
        this.enableLodingDiv = false;
        this.enablePriceDiv = false;
        this.costDiv = true;
        this.saveDiv = true;
      }
    }
    // this else is executed for Export Customer
    else {
      if (chargeCd == "SS_SUPER") {  //"Supervision for E&C"
        this.enableLoadingDiv = false;
        this.expECDiv = false;
        this.enableLodingDiv = true;
        this.enablePriceDiv = true;
        this.costDiv = false;
        this.expManualDaysDiv = true;
        this.saveDiv = false;
      } else if (chargeCd == "SS_EC") {  //"Errection & Commissing"
        this.enableLoadingDiv = false;
        this.enableLodingDiv = false;
        this.enablePriceDiv = false;
        this.expECDiv = true;
        this.costDiv = false;
        this.saveDiv = false;
        this.expManualDaysDiv = false;
      } else if (chargeCd == "SS_NA") {  //"E&C not in TTL Scope"
        this.enableLoadingDiv = false;
        this.enableLodingDiv = false;
        this.enablePriceDiv = false;
        this.expECDiv = false;
        this.expManualDaysDiv = false;
        this.saveDiv = true;
        this.costDiv = true;
      }
    }

  }

  //get cost for the selected dropdown values
  getDataValue(errectionForm) {
        this.mainSave=true;
        this.mainSave2=true;


    console.log(errectionForm);
    this.message = false;
    // for (var i = 0; i < this.custTypeList.length; i++) {
    //   if (this.custTypeList[i].categoryDetCode == this._ITOcustomerRequirementService.saveBasicDet.custCode)
    //     this.tempResp.erectionCommissionBean.ecTypeId = this.custTypeList[i].categoryDetId;
    // }
   // this.tempResp.erectionCommissionBean.ecType = this._ITOcustomerRequirementService.saveBasicDet.custCode;
    this.tempResp.erectionCommissionBean.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    //this.tempResp.erectionCommissionBean.framePowerId = this._ITOcustomerRequirementService.saveBasicDet.framePowerId;
   // this.tempResp.erectionCommissionBean.stateId = errectionForm.stateId;
   // this.tempResp.erectionCommissionBean.condensingTypeId = this._ITOcustomerRequirementService.saveBasicDet.condensingTypeId;
    // 
   // this.tempResp.erectionCommissionBean.typeOfChargeCd = this.typOfChargeCd
    this.tempResp.erectionCommissionBean.loadingId = errectionForm.loadingId;
    this.tempResp.erectionCommissionBean.lodgingId = errectionForm.lodgingId;
    if(errectionForm.manualDays != undefined){
      this.tempResp.erectionCommissionBean.noOfManDays = errectionForm.manualDays;
    }else{
      this.tempResp.erectionCommissionBean.noOfManDays = this.manDays;
    }
    
    //this.tempResp.erectionCommissionBean.currencyId = this.currencyId;
    console.log(this.tempResp);
    console.log(this.tempResp.erectionCommissionBean.quotId);
    console.log(this.tempResp.erectionCommissionBean.typeOfChargeId);
    console.log(this.tempResp.erectionCommissionBean.loadingId);
    console.log(this.tempResp.erectionCommissionBean.lodgingId);
    console.log(this.tempResp.erectionCommissionBean.noOfManDays);
    console.log(this.tempResp.erectionCommissionBean.ecType); 
    if (!this.expECDiv) {
      this._ITOerrectionCommisionService.getErecCommData(this.tempResp).subscribe(resp => {
        console.log(resp);
        if (resp.successCode == 0) {
          this.ExlCost = resp.price;
          this.itemId = resp.itemId;
          this.ssId = resp.ssId;
          this.tempResp.erectionCommissionBean.overwrittenPriceFlag = false;
          this.tempResp.erectionCommissionBean.overwrittenPrice = 0;
          this.saveDiv = true;
          this.disableOverWriteBtn = false;
        } else {
          this.message = true;
          this.successMsg = resp.successMsg;
        }
      });
    } else {
      // if "E&C not in TTL Scope" is selected then this function will be called ,since cost is 0 and cannot be overwritten
      this.saveECCost(errectionForm);
    }
this.mainSave2=false;
  }

  // once data is viewed it will be saved to db
  saveErecCommission(form) {

    this.message = false;
    this.saveOthers();
    // this.tempResp.erectionCommissionBean.remarks = null;
    // this.tempResp.erectionCommissionBean.overwrittenPriceFlag = false;
    // this.tempResp.erectionCommissionBean.overwrittenPrice = 0;
    if(this.expECDiv)
    {
      this.tempResp.erectionCommissionBean.price = this.lumpsomeCost;

    }
    else
    {
      this.tempResp.erectionCommissionBean.price = this.ExlCost;

    }
    // this.tempResp.erectionCommissionBean.currencyId = this.currencyId;

    // if cost was overwritten , this if will be true
    if (this.enableOverWrite) {
      this.tempResp.erectionCommissionBean.overwrittenPriceFlag = true;
      this.tempResp.erectionCommissionBean.overwrittenPrice = form.updatedCost;
      this.tempResp.erectionCommissionBean.remarks = form.remarks;
    }
    // else {
    //   this.tempResp.erectionCommissionBean.overwrittenPriceFlag = false;
    //   this.tempResp.erectionCommissionBean.overwrittenPrice = 0;
    // }
    if (this.overWrittenCost > 0) {
      this.tempResp.erectionCommissionBean.overwrittenPriceFlag = 1;
      this.tempResp.erectionCommissionBean.overwrittenPrice =  form.updatedCost;
    }
    if (this.tempResp.erectionCommissionBean.typeOfChargeCd == "SS_NA") {
      this.tempResp.erectionCommissionBean.loadingId = 0;
      this.tempResp.erectionCommissionBean.lodgingId = 0;
      this.tempResp.erectionCommissionBean.noOfManDays = 0;
    }

    this.tempResp.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    this.tempResp.erectionCommissionBean.remarks=this.techremarks;
    this.tempResp.erectionCommissionBean.serviceRemarks=this.description;
    
    this._ITOerrectionCommisionService.saveErecCommission(this.tempResp).subscribe(resp => {
this.mainSave=false;
this.mainSave2=true;

      this.saveInLocal(this.ECData, resp.erectionCommissionBean);
      if (resp.successCode == 0) {
        this.message = true;
        this.successMsg = "Cost Saved successfully";
        this._ITOcustomerRequirementService.sendtecBtnStatus(true);
        // update in db that EC cost has been saved for the respective quotation
        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'SS') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
              console.log(this._ITOcustomerRequirementService.editFlag)
              if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
                this._ITOcustomerRequirementService.editFlag = false;
                this.router.navigate(['/EditQuot']);
              } else {
                //this.router.navigate(['CostEstimation/ScopeOfSupply']);
              }
            })
          }
        }
             //Calling saveRemarks to save overwriten cost and comments
   this._ITOcustomerRequirementService.saveBasicDet.groupCode = 'OVERWRITE';
   this._ITOcustomerRequirementService.saveBasicDet.scopeCode = 'SS';
   this._ITOcustomerRequirementService.saveBasicDet.overwrittenCost = this.overWrittenECCost;
   this._ITOcustomerRequirementService.saveBasicDet.remarks = this.remarksVal;   
   console.log(this._ITOcustomerRequirementService.saveBasicDet);
   this._ITOAddOnComponentsService.saveRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(saveRem => {
     console.log(saveRem);
   })

      } else {
        this.message = true;
        this.successMsg = resp.successMsg;
      }
      this._ITOturbineConfigService.getExcelCostSheetData(this.tempResp.saveBasicDetails.quotId).subscribe(resAdd => {
        this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);
      })
    });

  }
  mainSaveColor()
  {
    this.mainSave=true;
    this.mainSave2=true;

  }
  saveECCost(form) {
    this.message = false;
    this.saveOthers();
    // for (var i = 0; i < this.custTypeList.length; i++) {
    //   if (this.custTypeList[i].categoryDetCode == this._ITOcustomerRequirementService.saveBasicDet.custCode)
    //     this.tempResp.erectionCommissionBean.ecTypeId = this.custTypeList[i].categoryDetId;
    // }
    this.tempResp.erectionCommissionBean.ecType = this._ITOcustomerRequirementService.saveBasicDet.custCode;
    this.tempResp.erectionCommissionBean.price = form.manualCost;
    this.tempResp.erectionCommissionBean.remarks = this.techremarks;
    this.tempResp.erectionCommissionBean.overwrittenPriceFlag = false;
    this.tempResp.erectionCommissionBean.overwrittenPrice = 0;
    this.tempResp.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    this._ITOerrectionCommisionService.saveErecCommission(this.tempResp).subscribe(resp => {
      this.saveInLocal(this.ECData, resp.erectionCommissionBean);
      if (resp.successCode == 0) {
        this.message = true;
        this.successMsg = "Cost Saved successfully";
        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'SS') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
              if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
              //  this._ITOcustomerRequirementService.editFlag = false;
               // this.router.navigate(['/EditQuot']);
              } else {
                //this.router.navigate(['CostEstimation/ScopeOfSupply']);
              }
            })
          }
        }

      }

      this._ITOturbineConfigService.getExcelCostSheetData(this.tempResp.saveBasicDetails.quotId).subscribe(resAdd => {
        this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);
      })
    });
  }

  // getting previous comments whenever price is overwritten
  getPrevComments() {
    this.message = false;
    this.successMsg = '';
    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "SS";
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

  saveOthers()
  {
   
      this._ITOeditQoutService.scopeofspares = [];
     
    this.dboFormDataaa.otherChapterData=[];
    this.dboClass=new dboClass();
      this.dboClass.seqNo=1;
      this.dboClass.ssId=this.ssId;
      this.dboClass.itemId=this.itemId;
      this.dboClass.subItemId=0;
      this.dboClass.information= null;
      this.dboClass.finalts=null;
      this.dboClass.subItemCd=null;
      this.dboClass.description= null;
      this.dboClass.equipment=null;
      this.dboClass.test = this.manDays;
      this.dboClass.equivalent=null;
      this.dboClass.panelType	=null;
      this.dboClass.custType=null;
      this.dboClass.quantity=null;
      this.dboClass.cost=null;
      this.dboClass.editFlag=0;
      this.dboClass.newColValFlag=0;
      this.dboClass.remarks=null;
      this.dboFormDataaa.otherChapterData.push(this.dboClass);
    
    console.log( this.dboFormDataaa.otherChapterData);
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.ssId= this.ssId;
    this.dboFormDataaa.modifiedById =  this._ITOLoginService.loggedUserDetails;


    this._ItoPerformanceService.saveOtherChapter(this.dboFormDataaa).subscribe(res => {
      console.log(res);
    });

  }
//To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}
}
