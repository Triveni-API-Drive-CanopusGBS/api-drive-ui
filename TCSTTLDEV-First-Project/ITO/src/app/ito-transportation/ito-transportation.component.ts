import { Component, OnInit, AfterViewChecked, AfterContentChecked } from '@angular/core';
import { ItoTransportationService } from './ito-transportation.service';
import { transportationFields } from './ito-transportation';
import { ITOLoginService } from '../app.component.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ThrowStmt } from '@angular/compiler';


@Component({
  selector: 'app-ito-transportation',
  templateUrl: './ito-transportation.component.html',
  styleUrls: ['./ito-transportation.component.css']
})
export class ItoTransportationComponent implements OnInit {

  hideprogressCost: boolean= false;
  hideprogress: boolean = false;
  overWrittenTotalTxt: string;
  condNewId: number = 0;
  remarkVal: string = "";
  overwriteVal: string = "";
  components: Array<any> = [];
  placeList: Array<any> = [];
  customerType: Array<any> = [];
  frames: Array<any> = [];
  typeOfTurbine: Array<any> = [];
  transportationType: Array<any> = [];
  newTurbineType: Array<any> = [];
  newFrames: Array<any> = [];
  place: any;
  condenserTypes: Array<any> = [];
  enableCT: boolean;
  newComponents: Array<any> = [];
  newTransportationType: Array<any> = [];
  EnabletransportDiv: Array<any> = [];
  selectedComps: Array<any> = [];
  savBasicDet: any;
  calCulatePrice: Array<any> = [];
  enableOverwriteDiv: boolean = false;
  disableStatus: Array<boolean> = [];
  showMsg: Array<boolean> = [];
  fcalCulatePrice: Array<any> = [];
  distance: Array<any> = [];
  address: Array<any> = [];
  vehicleWithUnitPrice: Array<any> = [];
  unitPriceArray: Array<any> = [];
  stdCostArray: Array<any> = [];
  overWrittenCostArray: Array<any> = [];
  stdCostFlagArray: Array<any> = [];
  overWrittenCostFlagArray: Array<any> = [];
  addressArray: Array<any> = [];
  transportationFieldsfull: transportationFields;
  transportationFieldsfullArray: Array<transportationFields> = [];
  remarksArray: string;
  isDom: boolean = false;
  isExp: boolean = false;
  isExWork: boolean = false;
  submtBtn: boolean = true;
  typeOfTr: string; //type of transportatin 
  turbineType: any;
  frameName: any;
  custType: any;
  isStndLone: boolean = true;  // if isStndLone=true then its is in quotation flow / if its false  its under utilities
  condensId: number;
  condensType: any;
  total: number = 0;
  FOBcost: any;
  FOBChennaicost: any;
  FBcostCopy: any;
  enableOverwriteDivPP: boolean = false;;
  enableOverwriteDivBP: boolean = false;
  disableStatusPP: boolean;
  disableStatusBP: boolean;
  PPenable: boolean = false;
  IPenable: boolean = false;
  expPriceBP: number = 0;
  expPriceCP: number = 0;
  expPricePP: number = 0;
  framesWithoutPower: Array<any> = [];
  powerList: Array<any> = [];
  selectedFrame: any;
  submitBtnStatus: boolean;
  basicDet: any;
  quotationNumber: any;
  transDetailsList: Array<any> = [];
  successMsg: string = "";
  message: boolean = false;
  transLocalStorage: Array<any> = [];
  transLocal: string = 'transLocal';
  oneLineLoc: string = 'oneLineLoc';
  scopeofsupp: string = 'scopeOfsup';
  tempResp: any;
  addressVal: any;
  typeOfBlade: any;
  showMsgd: boolean;
  sampleCost: Array<any> = [];
  overWrittenTotal: any;
  overWrittenTotalCost: any;
  oneLineBomTransCost: Array<any> = [];
  totalCcost: number;
  CIFcost: number = 0;
  submtBtnEx: boolean = true;
  countryList: Array<any> = [];
  portAndCountry: Array<any> = [];
  portArray: Array<any> = [];
  selectedPort: any;
  showCost: boolean = false;
  remarkEx: string = '';
  typOfCond: string = '';
  tempTotal: number = 0; // to campare the total for any changes in edit mode
  countryName: string;
  port: string;
  typeOfBladeSel: string;
  typeOfTransportss: any;
  backBtn: boolean = false;
  frameID: number;
  appDisable: boolean = false;
  mainSave:boolean=true;
  currentRole: string = 'selRole';
  rewApp: boolean  = false;

  constructor(private _ItoTransportationService: ItoTransportationService,
    private _ITOLoginService: ITOLoginService,
    private _ITOCostEstimationService: ITOCostEstimationService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    private _ITOturbineConfigService: ITOturbineConfigService, private router: Router,
    private _ITOeditQoutService:ITOeditQoutService,
    private _ActivatedRoute: ActivatedRoute, @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOAddOnComponentsService: ITOAddOnComponentsService) {
      this.hideprogress = true;
      this._ITOeditQoutService.button1=false;
      this._ITOeditQoutService.button2=false;
      this._ITOeditQoutService.button3=false;
      this._ITOeditQoutService.button4=false;
      this._ITOeditQoutService.button5=false;
      this._ITOeditQoutService.button6=false;
      this._ITOeditQoutService.button7=false;
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=true;
      if(this._ITOeditQoutService.checkEdit == false){
        this.backBtn = true;
      }
      let currentRole = this.storage.get(this.currentRole);
      if(currentRole == "QUOT_REVIWER" || currentRole == "QUOT_APPROVER"){
        this.rewApp = true;
      }
      if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
        this.appDisable=true;
      }else{
        this.appDisable=false;
      }
    this.transportationFieldsfull = new transportationFields();
    console.log(this._ITOcustomerRequirementService.saveBasicDet);
    if (this._ITOcustomerRequirementService.saveBasicDet) {
      this.typOfCond = this._ITOcustomerRequirementService.saveBasicDet.condensingTypeName;
      console.log(this.typOfCond, this._ITOcustomerRequirementService.saveBasicDet.condensingTypeName)
    }
    this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(oneLine => {
      this.oneLineBomTransCost = oneLine.oneLineBomExcel.costSheetList;
      if (this._ITOcustomerRequirementService.saveBasicDet.custCode == "EX") {
        this.isExp = true;
        for (let k = 0; k < this.oneLineBomTransCost.length; k++) {
          if (this.oneLineBomTransCost[k].categoryDetCode == "Transportation Cost") {
            this.totalCcost = this.oneLineBomTransCost[k].price;
          }
        }
      } else {
        this.isExp = false;
        for (let k = 0; k < this.oneLineBomTransCost.length; k++) {
          if (this.oneLineBomTransCost[k].categoryDetCode == "Transportation Cost") {
            this.total = this.oneLineBomTransCost[k].price;
            //this.submtBtn = true;
            this.showCost = true;
          }
        }
      }

      console.log(oneLine.oneLineBomExcel.costSheetList);
    });
    console.log(this.oneLineBomTransCost);
    this._ItoTransportationService.getQuotTransCache().subscribe(res => {
      console.log(res);
      this.tempResp = res;
      this.portAndCountry = res.dropDownColumnvalues.portDetails.PORT_DETAILS;
      this.countryList = Array.from(new Set(res.dropDownColumnvalues.portDetails.PORT_DETAILS.map(x => x.countryName)));
      // this.components = res.dropDownColumnvalues.componentTypes.TransportComponentTypeList;
      // this.placeList = res.dropDownColumnvalues.placeList.placeList;
      this.customerType = res.dropDownColumnvalues.customerType.CustomerType;
      this.transportationType = res.dropDownColumnvalues.transportationType.TransportTypes;
      this.vehicleWithUnitPrice = res.dropDownColumnvalues.vehicleWithUnitPrice.vehicleWithUnitList;
      this.savBasicDet = res.saveBasicDetails;

      if (!this._ITOLoginService.isStandAlone) {
        this._ITOcustomerRequirementService.sendMessage(this._ITOcustomerRequirementService.saveBasicDet.quotNumber);
        this._ITOcustomerRequirementService.getMessage().subscribe(qnum => {
          console.log(qnum);
          this.quotationNumber = qnum.qnum;
          console.log(this.quotationNumber);

        });
        this.savBasicDet = this._ITOcustomerRequirementService.saveBasicDet;
        if(this._ITOcustomerRequirementService.saveBasicDet.custCode == "DM"){
          this._ITOcustomerRequirementService.saveBasicDet.custCodeNm = "Domestic";
        }else{
          this._ITOcustomerRequirementService.saveBasicDet.custCodeNm = "Export"
        }
        this.custType = this._ITOcustomerRequirementService.saveBasicDet.custCodeNm;
        console.log(this.custType);
        for (let c = 0; c < this.customerType.length; c++) {
          if (this.customerType[c].categoryDetDesc == this._ITOcustomerRequirementService.saveBasicDet.custCodeNm) {
            for (let t = 0; t < this.transportationType.length; t++) {
              if (this.customerType[c].categoryDetCode == this.transportationType[t].dependentCode) {
                this.newTransportationType.push(this.transportationType[t]);
              }
            }
          }
        }
        for (let c = 0; c < this.customerType.length; c++) {
          if (this.customerType[c].categoryDetDesc === this.custType) {
            this.customerType[c].defaultVal = true;
          }
        }
        this.turbineType = this._ITOcustomerRequirementService.saveBasicDet.typeOfTurbine;
        for (let t = 0; t < this.typeOfTurbine.length; t++) {
          if (this.typeOfTurbine[t].categoryDetDesc === this.turbineType) {
            this.typeOfTurbine[t].defaultVal = true;
          }
        }
        this.frameName = this._ITOcustomerRequirementService.saveBasicDet.frameName;

        console.log(this.turbineType);

        this.typeOfBlade = this._ITOcustomerRequirementService.saveBasicDet.typOfBlade;
        for (let t = 0; t < this.newTurbineType.length; t++) {
          if (this.newTurbineType[t].categoryDetDesc === this._ITOcustomerRequirementService.saveBasicDet.typOfBlade) {
            this.newTurbineType[t].defaultVal = true;
          }
        }

        this.condensId = this._ITOcustomerRequirementService.saveBasicDet.condensingTypeId;
        if (this.turbineType == "Condensing") {
          this.enableCT = true;
          for (let c = 0; c < this.condenserTypes.length; c++) {
            if (this.condenserTypes[c].categoryDetId === this.condensId) {
              // this.savBasicDet.condensingTypeId = this._ITOcustomerRequirementService.saveBasicDet.condensingTypeId;
              this.condenserTypes[c].defaultVal = true;
              this.condensType = this.condenserTypes[c].categoryDetDesc;
            }
          }
          console.log(this.condenserTypes);
        }
        else if (this.turbineType == "Back Pressure") {
          this.enableCT = false;
        }
        for (let i = 0; i < this.typeOfTurbine.length; i++) {
          if (this.typeOfTurbine[i].categoryDetDesc == this.turbineType) {
            for (let j = 0; j < this.frames.length; j++) {
              if (this.typeOfTurbine[i].categoryDetCode == this.frames[j].turbineCode) {
                this.newFrames.push(this.frames[j]);
              }
            }
          }
        }
        console.log(this.newFrames, this.newComponents);

        for (let f = 0; f < this.newFrames.length; f++) {
          if (this.newFrames[f].frameDesc === this.frameName) {
            this.newFrames[f].defaultVal = true;
          }
        }

      }
      else if (this._ITOLoginService.isStandAlone) {
        this.isStndLone = false;
        this._ItoTransportationService.fetchCacheData().subscribe(resp => {
          console.log(resp);
          //Filter records based on sub item Name
          this.framesWithoutPower = resp.dropDownColumnvalues.frames.FRAMES.reduce((acc, current) => {
            console.log(acc, current);
            const x = acc.find(item => item.frameId === current.frameId);
            if (!x) {
              return acc.concat([current]);
            } else {
              return acc;
            } 
          }, []);
          console.log(this.framesWithoutPower);
          // updated as typeOfNewList in java
          // this.frames = resp.dropDownColumnvalues.frameWithPowerList.FRAMES_WITH_POWER;
          this.frames = resp.dropDownColumnvalues.typeOfNewList.FRAMES_WITH_POWER;
          this.typeOfTurbine = resp.dropDownColumnvalues.typesOfTurbine.TURBINE;
          this.newTurbineType = resp.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
          this.condenserTypes = resp.dropDownColumnvalues.typesOfCondensor.CONDENSOR;
        });
      }
      this.transLocalStorage[this.transLocal] = this.storage.get(this.transLocal);
      console.log(this.transLocalStorage[this.transLocal]);
      if (this.transLocalStorage[this.transLocal] != null && this.transLocalStorage[this.transLocal]!=[]  ) {
        // for (let k = 0; k < this.oneLineBomTransCost.length; k++) {
        //   if (this.oneLineBomTransCost[k].categoryDetCode == "Transportation Cost") {
        //     this.total = this.oneLineBomTransCost[k].price;
        //   }
        // }
        this.transDetailsList = this.transLocalStorage[this.transLocal];
        this.selectedComps = this.transLocalStorage[this.transLocal];
        this.typeOfTr = this.transDetailsList[0].transType;
        this.TOTransSelection(this.transDetailsList[0].transTypeCode);
        if (this.transDetailsList[0].transTypeCode == "EX") {
          this.isExWork = true;
          this.total = 0;
        }
        else {
          this.savBasicDet.transportationType = this.transDetailsList[0].transTypeId;
        }
        if (this._ITOcustomerRequirementService.saveBasicDet.custCode == "EX") {

          if (this.transDetailsList[0].transTypeCode == "CIF") {
            this.PPenable = true;
            this.FOBcost = this.transDetailsList[0].compPrice;
            this.CIFcost = this.transDetailsList[0].fobPrice;
            this.countryName = this.transDetailsList[0].countryName;
            this.countrySel(this.countryName);
            this.port = this.transDetailsList[0].portName;
            this.selectedPort = this.portAndCountry.filter((x) => {
              if (x.portName == this.port) {
                return x.portId;
              }
            });
            console.log(this.selectedPort);
          } else if (this.transDetailsList[0].transTypeCode == "FOB") {
            this.FOBcost = this.transDetailsList[0].priceFob;
            this.FOBChennaicost = this.transDetailsList[0].chennaiPrice;
            this.PPenable = false;
            this.IPenable = true;
          }
          this.totalCcost = this.transDetailsList[0].totalPrice;
          if (this.transDetailsList[0].overwrittenPriceFlag == 1) {
          this.overWrittenTotalCost = this.transDetailsList[0].overwrittenPrice;
          //this.remarkEx = this.transDetailsList[0].
          // for (let k = 0; k < this.oneLineBomTransCost.length; k++) {
          //   if (this.oneLineBomTransCost[k].categoryDetCode == "Transportation Cost") {
          //     this.totalCcost = this.oneLineBomTransCost[k].price;
          //     this.overWrittenTotalCost = this.oneLineBomTransCost[k].price;
          //   }
          // }
          this.enableOverwriteDivBP = true;
          }
          this.submtBtnEx = true;
        }
        else {
          for (let a = 0; a < this.newTransportationType.length; a++) {
            for (let t = 0; t < this.transDetailsList.length; t++) {
              if (this.transDetailsList[t].transTypeId == this.newTransportationType[a].categoryDetId) {
                this.calCulatePrice[a] = this.transDetailsList[t].compPrice;
                this.total = this.transDetailsList[0].totalPrice;
                this.tempTotal = this.transDetailsList[0].totalPrice;
                if (this.transDetailsList[0].overwrittenPriceFlag == 1) {
                  this.overWrittenTotal = this.transDetailsList[0].overwrittenPrice;
                  this.enableOverwriteDiv = true;
                }
              }
            }
            if (this.newTransportationType[a].categoryDetId == this.transDetailsList[0].transTypeId) {
              console.log(this.transDetailsList[0].transTypeId)
              this.newTransportationType[a].defaultVal = true;
            }
          }
          console.log(this.calCulatePrice);
        }
        console.log(this.newTransportationType);
        if(this._ITOcustomerRequirementService.saveBasicDet.custCode == "DM"){
          this._ITOcustomerRequirementService.saveBasicDet.custType = "DM";
        }else{
          this._ITOcustomerRequirementService.saveBasicDet.custType = "EX";
        }
        console.log(this._ITOcustomerRequirementService.saveBasicDet)
        this._ItoTransportationService.getTransportDataBasedOnFrame(this._ITOcustomerRequirementService.saveBasicDet).subscribe(resp => {
          console.log(resp);

          this.newComponents = resp.transportationDetailList;
          console.log(this.newComponents);

          if (this._ITOcustomerRequirementService.saveBasicDet.custCodeNm === "Export") {
            this.isExp = true;
            for (let l = 0; l < this.newComponents.length; l++) {
              this.FOBcost = this.newComponents[l].priceFob;
              this.FOBChennaicost = this.newComponents[l].chennaiPrice;
              // this.FBcostCopy = this.newComponents[l].price;
              this.stdCostFlagArray[0] = 1;
            }

          } else if (this._ITOcustomerRequirementService.saveBasicDet.custCodeNm === "Domestic") {
            this.isDom = true;
            this.isExp = false;
            for (let l = 0; l < this.newComponents.length; l++) {
              this.stdCostFlagArray[l] = 1;
              this.overWrittenCostFlagArray[l] = 0;
            }
          }
          for (let k = 0; k < this.newComponents.length; k++) {
            for (let j = 0; j < this.transDetailsList.length; j++) {
              if (this.newComponents[k].compoId == this.transDetailsList[j].compoId && this.newComponents[k].vehicleId == this.transDetailsList[j].vehicleId) {
                this.newComponents[k].defaultVal = true;
                this.EnabletransportDiv[k] = true;

                this.newComponents[k].fobplace = this.transDetailsList[j].fobplace;
                //this.address[k] = this.transDetailsList[j].toPlace;
                this.addressVal = this.transDetailsList[j].toPlace;
                this.distance[k] = this.transDetailsList[j].distance;
                this.remarksArray = this.transDetailsList[j].remarks;

                this.calCulatePrice[k] = this.transDetailsList[j].compPrice;
                
                this.newComponents[k].vehicleName = this.transDetailsList[j].vehicleName;
                this.newComponents[k].numberOfVehicle = this.transDetailsList[j].numberOfVehicle;
              }
            }
          }
          this.transport("", 0);
          this.mainSave=false;

        });
      }
      this.hideprogress = false;
    });
    console.log(this.frameName);
  }

  ngOnInit() {
    console.log(this._ITOLoginService.isStandAlone);

  }
  ngAfterViewChecked() {

  }

  ngAfterContentChecked() {
   // this.mainSave=true;

    this.sampleCost = [];
    for (let k = 0; k < this.distance.length; k++) {
      if (this.distance[k] != null) {
        this.sampleCost.push(this.distance[k]);
      }
    }
    console.log(this.sampleCost.length, this.selectedComps.length, this.newComponents.length, this.total, this.isExp);
    // this.totalCcost = this.CIFcost + this.FOBcost;
  }
  reset() {
    this.mainSave=true;

    this.selectedComps = [];
    this.distance = [];
    this.showCost = false;
    this.address = [];
    this.addressVal = '';
    this.calCulatePrice = [];
    this.fcalCulatePrice = [];
    this.showMsg = [];
    for (let k = 0; k < this.newComponents.length; k++) {
      this.newComponents[k].defaultVal = false;
      this.EnabletransportDiv[k] = false;
      this.enableOverwriteDiv = false;
    }
    this.storage.remove(this.transLocal);
  }
  resetTransDet(){
    this.mainSave=true;

    this.selectedComps = [];
    this.isExp = false;
    this.addressVal = '';
    for (let k = 0; k < this.newComponents.length; k++) {
      this.newComponents[k].defaultVal = false;
      this.EnabletransportDiv[k] = false;
      this.enableOverwriteDiv = false;
    }
    this.newComponents = [];
  }
  getPrevComments() {
    this.mainSave=true;

    this.message = false;
    this.successMsg = '';
    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "TRNS";
    this._ITOcustomerRequirementService.saveBasicDet.groupCode = "OVERWRITE";
    this._ITOAddOnComponentsService.getQuotRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(prevComRes => {
      console.log(prevComRes);
      if (prevComRes.remarksList.length > 0) {
        this._ITOAddOnComponentsService.dispPrevComments = true;
        this._ITOAddOnComponentsService.oldComms = prevComRes.remarksList;
      } else {
        this.message = true;
        this.successMsg = "No Previous Comments found";
      }
    });
  }
  TOBSel(typOfBlade) {
    this.mainSave=true;

    this.newFrames = [];
    this.resetTransDet();
    console.log(typOfBlade);
    this.typeOfBladeSel = typOfBlade;
    this.newFrames = [];
  }
  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.transLocalStorage[key] = this.storage.get(key);
  }
  /**
   * 
   * @param typeOfTrans {string} type of transporatation
   */
  TOTranssel(typeOfTrans) {
    this.mainSave=true;

    this.newFrames = [];
    this.resetTransDet();
    console.log("inside TOTranssel");
    if (typeOfTrans != "EX-WORKS") {
      this.isExWork = false;
      this.PPenable = false;
      this.IPenable=  false;
      if (typeOfTrans == "FOB at Indian port") {
        this.PPenable = false;
        this.IPenable = true;
      } else if (typeOfTrans == "Port to port") {
        this.PPenable = true;
        this.IPenable = false;
      }
    }
    this.enableOverwriteDivBP = false;
    this.remarkEx = null;
    this.FOBcost = 0;
    this.CIFcost = 0;
    this.showMsgd = false;
    this.message = false;
  }

  TOTransSelection(trnsCode) {
    this.mainSave=true;

    if (trnsCode != "EX") {
      this.isExWork = false;
      this.PPenable = false;
      this.IPenable = false;
      if (trnsCode == "CIF") {
        this.PPenable = true;
        this.IPenable = false;
      } else {
        this.PPenable = false;
        this.IPenable = true;
      }
    }
    this.enableOverwriteDivBP = false;
    this.remarkEx = null;
    this.FOBcost = 0;
    this.CIFcost = 0;
    this.showMsgd = false;
    this.message = false;
  }

  // calculate the price based on entered distance 

  calPrice(dist, i, comp) {
    this.mainSave=true;

    console.log(dist.target.value, i, comp);
    this.distance[i] = dist.target.value;
    if (this._ITOcustomerRequirementService.saveBasicDet) {
      this.tempResp.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    }
    if (dist.target.value > 2600) {
      this.tempResp.transBean.compoId = comp.compoId;
      this.distance[i] = 2600;
    }
    else {
      this.tempResp.transBean.compoId = comp.compoId;
    }
    this.tempResp.transBean.distance = this.distance[i];
    this.tempResp.transBean.frameId = this.frameID;
    this.tempResp.transBean.condensingTypeId = this.condNewId;
    this.tempResp.transBean.quotId = 0;
    console.log(this.tempResp)
    this._ItoTransportationService.getTransportPrice(this.tempResp).subscribe(costRes => {
      console.log(costRes);
      if (costRes.successCode == 0) {
        this.calCulatePrice[i] = costRes.transBean.price;
        this.fcalCulatePrice[i] = costRes.transBean.price;
      }
    })
  }

  enableOverWrite(i) {
    this.mainSave=true;

    console.log(i);
    this.enableOverwriteDiv = true;
    this.disableStatus[i] = true;
    this.remarksArray = null;
    console.log(this.disableStatus);
  }
  enableOverWriteBP() {
    this.mainSave=true;

    this.enableOverwriteDivBP = true;
    this.disableStatusBP = true;
    //this.submtBtnEx = false;

  }
  enableOverWritePP() {
    this.mainSave=true;

    this.enableOverwriteDivPP = true;
    this.disableStatusPP = true;
  }
  disableOverWritePP() {
    this.mainSave=true;

    this.enableOverwriteDivPP = false;
    this.disableStatusPP = false;

    this.overWrittenTotalCost = "";
  }
  disableOverWriteBP() {
    this.mainSave=true;

    this.overWrittenTotal = "";
    this.overWrittenTotalTxt = "";
    this.enableOverwriteDivBP = false;
    this.disableStatusBP = false;
    this.overWrittenCostFlagArray[0] = 0;
    this.stdCostFlagArray[0] = 1;
    this.submtBtnEx = true;
    this.remarkEx = null;
  }
  disableOverWrite(i) {
    this.mainSave=true;

    this.overWrittenTotal = "";
    this.enableOverwriteDiv = false;
    this.disableStatus[i] = false;
    this.showMsg[i] = false;
    this.overWrittenCostFlagArray[i] = 0;
    this.stdCostFlagArray[i] = 1;
    this.submtBtn = true;
  }
  enterPrice(event, i) {
    this.mainSave=true;

    console.log(event.target.value);
    console.log(this.enableOverwriteDiv);
    if (this.enableOverwriteDiv == true) {
      if (event.target.value <= this.fcalCulatePrice[i]) {
        this.showMsg[i] = true;
      }
      else if (event.target.value > this.fcalCulatePrice[i]) {
        this.showMsg[i] = false;
        console.log(this.showMsg && this.enableOverwriteDiv)
      }
    }

  }
  remarksForm(indForm, i) {
        this.mainSave=true;

    console.log(indForm, i);
    this.remarksArray = indForm.overWrite;
    this.overWrittenCostFlagArray[i] = 1;
    this.stdCostFlagArray[i] = 0;
    let butn = document.getElementsByName(i)[0].style.color = "green";
  }
  remarksFormBP(indForm, i) {
    this.mainSave=true;
    console.log(indForm, i);
    this.remarksArray = indForm.overWrite;
    this.overWrittenCostFlagArray[0] = 1;
    this.stdCostFlagArray[0] = 0;
    let butn = document.getElementsByName("btnBP")[0].style.color = "green";
  }
  remarksFormPP(indForm, i) {
    this.mainSave=true;
    console.log(indForm, i);
    this.remarksArray = indForm.overWrite;
    this.overWrittenCostFlagArray[i] = 1;
    this.stdCostFlagArray[i] = 0;
    let butn = document.getElementsByName("btnPP")[0].style.color = "green";
  }

  checkremrk(remrk) {
    this.mainSave=true;

    this.remarkVal = remrk;
    if (remrk.trim() == null || remrk.trim() == "") {
      this.submtBtn = false;
    } else {
      this.submtBtn = true;
    }
  }

  checkremrkEx(remrk) {
    this.mainSave=true;

    //this.remarkVal=remrk;
    if (remrk.trim() == null || remrk.trim() == "") {
      this.submtBtnEx = false;
    } else {
      this.submtBtnEx = true;
    }
  }

  portSel(portName) {
    this.mainSave=true;

    this.selectedPort = null;
    console.log(portName);
    this.selectedPort = this.portAndCountry.filter((x) => {
      if (x.portName == portName) {
        return x.portId;
      }
    });
    console.log(this.selectedPort);
  }
  countrySel(country) {
    this.mainSave=true;

    this.portArray = [];
    this.portArray = this.portAndCountry.filter((x) => {
      // console.log(x);
      return x.countryName == country;
    })
    // console.log(this.portArray);
  }
  transport(form, ind) {
    this.mainSave=true;

    this.total = 0;
    //this.overWrittenTotal = 0;
    console.log(this.selectedComps, this.distance);
    console.log(this.basicDet);
    this.transportationFieldsfullArray = [];
    for (let l = 0; l < this.newComponents.length; l++) {
      for (let s = 0; s < this.selectedComps.length; s++) {
        //  if (this.newComponents[l] == this.selectedComps[s]) {
        if (this.newComponents[l].compoId == this.selectedComps[s].compoId && this.newComponents[l].vehicleId == this.selectedComps[s].vehicleId) {
          this.transportationFieldsfull = new transportationFields();

          this.transportationFieldsfull.address = this.addressVal;
          this.transportationFieldsfull.maxDistance = this.distance[l];
          if (this.transLocalStorage[this.transLocal] != null) {
            // if (this.selectedComps[s].overwrittenPriceFlag == 1) {
            this.transportationFieldsfull.compPrice = this.calCulatePrice[l];
            // }
            // else {
            //   this.transportationFieldsfull.compPrice = this.fcalCulatePrice[s];
            // }
            // console.log(this.transportationFieldsfull.compPrice);
          }
          else {
            this.transportationFieldsfull.compPrice = this.fcalCulatePrice[l];
          }
          this.transportationFieldsfull.compPriceFlag = this.stdCostFlagArray[l];
          // this.transportationFieldsfull.overwrittenPrice = form[l + this.newComponents.length * 3];
          this.transportationFieldsfull.overwrittenPrice = this.calCulatePrice[l];
          this.transportationFieldsfull.overwrittenPriceFlag = this.overWrittenCostFlagArray[l];
          // this.transportationFieldsfull.remarks = this.remarksArray;
          this.transportationFieldsfull.compoId = this.newComponents[l].compoId;
          this.transportationFieldsfull.numberOfVehicle = this.newComponents[l].numberOfVehicle;
          this.transportationFieldsfull.vehicleId = this.newComponents[l].vehicleId;
          this.transportationFieldsfull.placeId = this.newComponents[l].placeId;
          this.transportationFieldsfull.fobplace = this.newComponents[l].fobplace;
          this.transportationFieldsfull.transId = this.newComponents[l].transId;
          this.transportationFieldsfull.unitPrice = this.unitPriceArray[l];
          this.transportationFieldsfullArray.push(this.transportationFieldsfull);
        }
      }
    }
    console.log(this.transportationFieldsfullArray);
    for (let t = 0; t < this.transportationFieldsfullArray.length; t++) {
      // if (this.transportationFieldsfullArray[t].overwrittenPriceFlag == 1) {
      //   this.total += (+this.transportationFieldsfullArray[t].overwrittenPrice);
      // }
      // else if (this.transportationFieldsfullArray[t].compPriceFlag == 1) {
      this.total = this.total + this.transportationFieldsfullArray[t].overwrittenPrice;
      // }
    }
    if (this.total !== this.tempTotal) {
      this.overWrittenTotal = 0;
    }
    console.log(this.total);
    if (this.savBasicDet.custType === "Domestic") {
      if (this.selectedComps.length > 0) {
        this.submitBtnStatus = true;
      }
      else {
        this.submitBtnStatus = false;
      }
    }
    this.savBasicDet.transportTotalPrice = this.total;
    this.savBasicDet.transportVehicalCostList = this.transportationFieldsfullArray;
    console.log(this.savBasicDet);
    this.showCost = true;
  }

  saveTransport() {
    if (this.enableOverwriteDiv) {
      this.savBasicDet.transportBean.overwrittenPrice = this.overWrittenTotal;
      this.savBasicDet.transportBean.overwrittenPriceFlag = 1;
      this.savBasicDet.transportBean.remarks = this.remarksArray;
    }
    else if (this.enableOverwriteDivBP) {
      this.savBasicDet.transportBean.overwrittenPrice = this.overWrittenTotalCost;
      this.savBasicDet.transportBean.overwrittenPriceFlag = 1;
      this.savBasicDet.transportBean.remarks = this.remarkEx;
    }

    this._ItoTransportationService.saveTransportationData(this.savBasicDet).subscribe(respTrans => {
      this.mainSave=false;

      console.log(respTrans);
      this.saveInLocal(this.transLocal, respTrans.transDetailList);
      if (respTrans.successCode == 0) {
        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'TC') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }
      }

      this._ITOturbineConfigService.getExcelCostSheetData(this.savBasicDet.quotId).subscribe(resAdd => {
        console.log(resAdd);
        this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);

        if (respTrans.successCode == 0) {
          this.message = true;
          this.successMsg = "Cost Saved Succefully.";
          console.log(this._ITOcustomerRequirementService.editFlag)
          if (this._ITOcustomerRequirementService.editFlag   && this._ITOeditQoutService.checkEdit==false) {
            this._ITOcustomerRequirementService.editFlag = false;
            this.router.navigate(['/EditQuot']);
          }
        } else {
          //this.router.navigate(['CostEstimation/ScopeOfSupply']);
          this.message = true;
          this.successMsg = respTrans.successMsg;
        }
      })
    });

  }

  // Export submission 
  transportExp() {
    this.total = 0;
    this.overWrittenTotal = 0;
    this.transportationFieldsfullArray = [];
    console.log(this.FOBcost);
    console.log(this.basicDet);
    console.log(this.FOBChennaicost);

    this.expPriceBP = this.FOBcost;
    this.expPriceCP = this.FOBChennaicost;
    console.log(this.stdCostFlagArray);
    this.transportationFieldsfull = new transportationFields();
    this.transportationFieldsfull.fobPrice = this.FOBcost;
    this.transportationFieldsfull.chennaiPrice = this.FOBChennaicost;
    if (this.PPenable) {
      this.transportationFieldsfull.compPrice = this.CIFcost;

    }

    this.transportationFieldsfullArray.push(this.transportationFieldsfull);
    this.savBasicDet.transportVehicalCostList = this.transportationFieldsfullArray;
    this.savBasicDet.transportTotalPrice = this.totalCcost;
    console.log(this.savBasicDet);
    this.saveTransport();

  }

  transPortExWorks() {
    this.transportationFieldsfull = new transportationFields();
    this.transportationFieldsfullArray.push(this.transportationFieldsfull);
    this.savBasicDet.transportVehicalCostList = this.transportationFieldsfullArray;
    this._ItoTransportationService.saveTransportationData(this.savBasicDet).subscribe(respTransp => {
      this.mainSave=false;

      console.log(respTransp);
      this.saveInLocal(this.transLocal, respTransp.transDetailList);
      if (respTransp.successCode == 0 && respTransp.successMsg != null) {
        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'TC') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
              console.log(this._ITOcustomerRequirementService.editFlag)
              if (this._ITOcustomerRequirementService.editFlag &&   this._ITOeditQoutService.checkEdit==false) {
                this._ITOcustomerRequirementService.editFlag = false;
                this.router.navigate(['/EditQuot']);
              } else {
                //this.router.navigate(['CostEstimation/ScopeOfSupply']);
                this.message = true;
                this.successMsg = "Cost Saved Successfully";
                this._ITOcustomerRequirementService.sendcomBtnStatus(true);
              }
            })
          }
        }

      }
    });
  }

  transportSel(event, comp, i) {
    this.mainSave=true;

    console.log(event, comp, i);
    if (event.target.checked) {
      this.EnabletransportDiv[i] = true;
      // for (let c = 0; c < this.selectedComps.length; c++) {
      //   if (!(this.selectedComps[c].compoId == comp.compoId && this.selectedComps[c].vehicleId == comp.vehicleId)) {
      this.selectedComps.push(comp);
      //   }
      // }

    }
    else if (!event.target.checked) {
      this.EnabletransportDiv[i] = false;
      this.distance[i] = null;
      // this.address[i] = null;
      this.calCulatePrice[i] = null;
      for (let s of this.selectedComps) {
        //if (s === comp) {
        if (s.compoId == comp.compoId && s.vehicleId == comp.vehicleId) {
          let ind = this.selectedComps.indexOf(s);
          this.selectedComps.splice(ind, 1);
        }
      }
    }
    console.log(this.selectedComps);
  }

  TOCsel(typeOfCus) {
    this.mainSave=true;

    this.newFrames = [];
    this.resetTransDet();
    this.newTransportationType = [];
    console.log(this.frameName);
    console.log(typeOfCus);
    if (typeOfCus === "Export") {
      this.submitBtnStatus = true;
    }
    for (let c = 0; c < this.customerType.length; c++) {
      if (this.customerType[c].categoryDetDesc == typeOfCus) {
        for (let t = 0; t < this.transportationType.length; t++) {
          if (this.customerType[c].categoryDetCode == this.transportationType[t].dependentCode) {
            this.newTransportationType.push(this.transportationType[t]);
          }
        }
      }
    }
  }

  TOTsel(typeOfTransport) {
    this.mainSave=true;

    this.newFrames = [];
    this.resetTransDet();
    console.log(typeOfTransport);
    this.typeOfTransportss = typeOfTransport;
    if (typeOfTransport == "Condensing") {
      this.enableCT = true;
    }
    else if (typeOfTransport == "Back Pressure") {
      this.enableCT = false;
    }
    for(let i=0; i<this.framesWithoutPower.length; i++){
      if(this.typeOfBladeSel == "Improved Impulse"){
      if(this.framesWithoutPower[i].turbdesignName == "Impulse" &&
         this.framesWithoutPower[i].turbType == typeOfTransport  &&
         this.framesWithoutPower[i].improvedImpId == "1"){
           this.newFrames.push(this.framesWithoutPower[i]);
         }
        }else{
          if(this.framesWithoutPower[i].turbdesignName == "Impulse" && this.framesWithoutPower[i].improvedImpId == "0"){
            console.log(this.framesWithoutPower[i].turbdesignName, this.framesWithoutPower[i].turbType,this.framesWithoutPower[i].improvedImpId);
          }
          if(this.framesWithoutPower[i].turbdesignName == this.typeOfBladeSel &&
            this.framesWithoutPower[i].turbType == typeOfTransport){
              this.newFrames.push(this.framesWithoutPower[i]);
            }
        }
    }
    // for (let i = 0; i < this.typeOfTurbine.length; i++) {
    //   if (this.typeOfTurbine[i].categoryDetDesc == typeOfTransport && this.typeOfBlade[]) {
    //     for (let j = 0; j < this.framesWithoutPower.length; j++) {
    //       if (this.typeOfTurbine[i].categoryDetCode == this.framesWithoutPower[j].turbineCode) {
    //         this.newFrames.push(this.framesWithoutPower[j]);

    //       }
    //     }
    //   }
    // }

    console.log(this.newFrames, this.newComponents);
  }


  condenserSel(typeOfCon) {
    this.mainSave=true;

    this.resetTransDet();
    this.TOTsel("Condensing");

    console.log(typeOfCon);
    console.log(this.newFrames);
    for(var k=0; k<this.condenserTypes.length; k++){
      if(this.condenserTypes[k].categoryDetDesc == typeOfCon){
        this.condNewId = this.condenserTypes[k].categoryDetId;
        console.log(this.condNewId);
      for (var i = 0; i < this.newFrames.length; i++) {
          if (this.newFrames[i].condTypId != this.condNewId) {
          this.newFrames.splice(i, 1);
        }
      }
        }
      }
    // if (typeOfCon == "Water cooled condensor") {
    //   this.condNewId = 51;
    //   for (var i = 0; i < this.newFrames.length; i++) {
    //     if (this.newFrames[i].condTypId == 50) {
    //       this.newFrames.splice(i, 1);
    //     }
    //   }
    // } else {
    //   this.condNewId = 50;
    //   for (var i = 0; i < this.newFrames.length; i++) {
    //     if (this.newFrames[i].condTypId == 51) {
    //       this.newFrames.splice(i, 1);
    //     }
    //   }
    // }
  }


  FrameSel(frme) {
    this.mainSave=true;

    this.powerList = [];
    this.newComponents =[];
    this.frameID = 0;
    console.log(frme);
    console.log(this.frames);
    console.log(this.typeOfTransportss);
    if(this.typeOfTransportss == "Back Pressure"){
      this.condNewId = 0;
    }
    for (let fw = 0; fw < this.frames.length; fw++) {
      if (this.frames[fw].frameDesc === frme && this.frames[fw].condTypId == this.condNewId) {
        this.powerList.push(this.frames[fw].maxPower);
        this.selectedFrame = frme;
      }
    }
    for(let f=0;f<this.newFrames.length; f++){
      if(this.newFrames[f].frameDesc == frme){
        this.frameID = this.newFrames[f].frameId;
      }
    }
  }
  PowSel(pwr) {
    this.mainSave=true;

    this.resetTransDet();
    console.log(pwr);
    for (let fw = 0; fw < this.frames.length; fw++) {
      if (this.frames[fw].maxPower === +pwr && this.frames[fw].frameDesc === this.selectedFrame) {
        this.savBasicDet.framePowerId = this.frames[fw].framePowerId;
        this.savBasicDet.power = this.frames[fw].maxPower;
      }
    }
    console.log(this.savBasicDet);
  }

  basicTransport(formValue) {
    this.mainSave=true;
    this.hideprogressCost = true;
    this.reset();
    this.selectedComps = [];
    console.log(formValue);
    this.total = 0;
    this.overWrittenTotal = 0;
    this.basicDet = formValue;
    if(formValue.typeOfCust == "Domestic"){
      formValue.typeOfCust = "DM";
    }else{
      formValue.typeOfCust = "EX";
    }
    console.log(this.savBasicDet);
    if (formValue.typeOfTransport === "EX-WORKS") {
      this.isExWork = true;
    } else if (formValue.typeOfTransport != "EX-WORKS") {
      this.isExWork = false;
      if (formValue.typeOfTransport == "FOB at Indian port") {
        this.PPenable = false;
        this.IPenable = true;
      } else if (formValue.typeOfTransport == "Port to port") {
        this.PPenable = true;
        this.IPenable = false;
        this.savBasicDet.portId = this.selectedPort[0].portId;
      }
    }

    if (!this._ITOLoginService.isStandAlone) {
      formValue.typeOfCust = this.savBasicDet.custCode;
    }

    for (let t = 0; t < this.transportationType.length; t++) {
      if (this.transportationType[t].categoryDetDesc === formValue.typeOfTransport) {
        for (let c = 0; c < this.customerType.length; c++) {
          if (this.customerType[c].categoryDetCode == formValue.typeOfCust &&
            this.customerType[c].categoryDetCode == this.transportationType[t].dependentCode) {
            this.savBasicDet.transportationType = this.transportationType[t].categoryDetId;
            this.savBasicDet.transTypeCode = this.transportationType[t].categoryDetCode;
          }
        }

        console.log(this.savBasicDet);

      }
    }
    for (let n = 0; n < this.newFrames.length; n++) {
      if (this.newFrames[n].frameDesc === formValue.frames) {
        // this.savBasicDet.framePowerId = this.newFrames[n].framePowerId;
        this.savBasicDet.quotId = 0;
        this.savBasicDet.custType = formValue.typeOfCust;
        this.savBasicDet.frameId = this.newFrames[n].frameId;
        console.log(this.savBasicDet);

        if (formValue.typeOfTrbine === "Condensing") {
          this.enableCT = true;
          for (let c = 0; c < this.condenserTypes.length; c++) {
            if (this.condenserTypes[c].categoryDetDesc === formValue.condenserType) {
              this.savBasicDet.condensingTypeId = this.condenserTypes[c].categoryDetId;
            }
          }
        }
        else if (formValue.typeOfTrbine === "Back Pressure") {
          this.enableCT = false;
          this.savBasicDet.condensingTypeId = 0;
        }

      }
    }
    this.savBasicDet.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.savBasicDet.custType = formValue.typeOfCust;

    console.log(this.savBasicDet);

    // arrange according to quotation number put formValue if quotion is 0 else take the custType from save basic det    
    this._ItoTransportationService.getTransportDataBasedOnFrame(this.savBasicDet).subscribe(resp => {
      console.log(resp);
      this.tempResp = resp;
      this.newComponents = resp.transportationDetailList;
      console.log(this.newComponents);

      if (resp.saveBasicDetails.custType === "EX" || resp.saveBasicDetails.custType === "Export") {
        this.isExp = true;

        if (formValue.typeOfTransport == "FOB at Indian port") {
          this.PPenable = false;
          this.IPenable = true;
          this.FOBcost = this.newComponents[0].priceFob;
          this.FOBChennaicost =  this.newComponents[0].chennaiPrice;
        } else if (formValue.typeOfTransport == "Port to port") {
          this.PPenable = true;
          this.IPenable = false;
          this.CIFcost = this.newComponents[0].cifExPrice;
          this.FOBcost = this.newComponents[1].cifDomPrice;
          this.savBasicDet.portId = this.selectedPort[0].portId;
        }


      } else if (resp.saveBasicDetails.custType === "DM" || resp.saveBasicDetails.custType === "Domestic") {
        this.isDom = true;
        this.isExp = false;
        for (let l = 0; l < this.newComponents.length; l++) {
          this.stdCostFlagArray[l] = 1;
          this.overWrittenCostFlagArray[l] = 0;

        }
      }
      this.hideprogressCost = false;
    });
    this.enableOverwriteDivBP = false;
  }
//To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}
}
