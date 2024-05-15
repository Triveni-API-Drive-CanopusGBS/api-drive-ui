import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { ITOcustomerRequirementService } from './ito-customer-requirement.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcustomerDetailsService } from '../ito-customer-details/ito-customer-details.service';
import { ITOendUserDetailsService } from '../ito-end-user-details/ito-end-user-details.service';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOHomePageService } from '../ito-home-page/ito-home-page.service';
import { ArrayObservable } from 'rxjs/observable/ArrayObservable';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOScopeOfSupplyService }  from '../ito-scope-of-supply/ito-scope-of-supply.service';
import { ItoGeneralInputsComponent } from '../ito-general-inputs/ito-general-inputs.component';

@Component({
  selector: 'app-ito-customer-requirement',
  templateUrl: './ito-customer-requirement.component.html',
  styleUrls: ['./ito-customer-requirement.component.css']
})
export class ItoCustomerRequirementComponent implements OnInit {

  frmaessssss: Array<any> = [];
  bleedtypNwLst: Array<any> = [];
  scopeOfSupply: any;
  saveEleFilterList: any;
  scopeOfSupplyList: any;
  genInputList: any;
  custCode: boolean = false;
  f2fQuesData: any;
  customerData: any;
  transDetailList: any;
  projectCost: any;
  sparesCost: any;
  varCost: any;
  packageBean: any;
  ecBean: any;
  onLineBomExcel: any;
  typVarient: any;
  turbineTypesList: Array<any> = [];
  turbineTypeCode: string;
  bleedTypeCode: string;
  typeOfVarientList:Array<any> = [];
  typeOfVarientFil:Array<any> = [];
  // typeOfInjectionList: Array<any> =[];
  injectionCode: string;
  turbineCategoryList: Array<any> = [];
  typeOfQuotationList: Array<any> = [];
  typOfQuo: Array<any> = [];
  frameArrayList: Array<any> = [];
  frameList: Array<any> = [];
  condenserTypeList: Array<any> = [];
  // Orientation type commented
  // orientationType: Array<any> = [];
  // orientationTypeList: Array<any> = [];
  modelType: Array<any> = [];
  userRegionList: Array<any> = [];
  customerReq: Array<any> = [];
  bleedType: Array<any> = [];
  newFrames: Array<any> = [];
  tempFrames: Array<any> = [];
  tempFrameList: Array<any> = [];
  framesWithPwr: Array<any> = [];
  turbCategoryList: Array<any> = [];
  turbTypeList: Array<any> = [];
  orientTypesList: Array<any> = [];
  condenTypeList: Array<any> = [];
  frameNameList: Array<any> = [];
  modelTypeList: Array<any> = [];
  quotId: number;
  modBy: number;

  enableCT: boolean;
  enableBleed: boolean;
  enableExtraction: boolean;
  showMsg: boolean = false;
  hidespinner: boolean = true;
  enableMul: boolean = false;
  dialogMsgApp: boolean = false;
  errIcon: boolean = false;
  enablePerImp: boolean = false; //Boolean for percentage increment value
  maxPower: number;
  minPower: number;
  isFrameUpdated: number;
  condTypeId: number = 0;
  multFactor: number;

  customerReqrmnt: string = 'customerReq';
  userDetail: string = 'userDetail';
  custdetails: string = 'custdetails';
  some: string = "select";
  newDev: string = "NO";
  currentRoleId: string = 'selRoleId';
  selTOT: string = '';
  selFrame: string = '';
  selBt: string = '';
  framesMsg: string = '';
  regnCd: string;
  message: string;
  dialogMsgVal: string;
// Orientation type commented
  // orientationTyp: any;
  varientType :any;
  injectionType: any;
  typOfQuoot: string;
  turbineCat: any;
  capacit: any;
  CondenserTyp: any;
  bleedTyp: any;
  frame: any;
  typOffQuot: any;
  model: any;
  numberOfBleed: any;
  numberOfextractor: any;
  opportunitySNO: any = '';
  regn: any;
  BleedTyp: any;
  quotForm: any;
  turbineTyp: any;
  typeOfBlade: any = '';
  typeOfBladeCode: any = '';
  grpCd: any = '';
  typeOfbleed: any = '';
  adminFormmm: any;
  typeofQuotation: string;

  //removing local storage whenever frame/Capacity is changed starts

  scopeofsupp: string = 'scopeOfsup';
  addOnDetails: string = 'addOnDetail';
  packLocal: string = 'packLocal';
  transLocal: string = 'transLocal';
  ECData: string = 'ecKey';
  sparesLocal: string = 'sparesLocal';
  varCostLocal: string = 'varCostLocal';
  F2FSap: string = 'F2FSap';
  projectCostLocal: string = 'projectCostLocal';
  f2fCostData: string = 'f2fCostData';
  usersDataList: string = 'usersDataList';
  dboEleFull: string = 'dboEleFull';
  F2FTurbine: string = 'F2FTurbine';
  dboMechLoc: string = 'dboMech';
  dboComSecALoc: string = 'comSecA'; // local storage value
  dboPerfLoc: string = 'dboPerf';
  dboMechFull: string = 'dboMechFull';
  generalInput: string = 'generalInputList';
  dboMechAuxLoc: string = 'dboAuxMech';
  MechExpScope: string = 'MechExpScope';
  EleExtdScope: string = 'EleExtdScope';
  EleCiExtdScope: string = 'EleCiExtdScope';
  dboEleCIAuxFull: string = 'dboEleCIAuxFull';
  dboEleCIFull: string = 'dboEleCIFull';
  dboEleAuxFull: string = 'dboEleAuxFull';
  backBtn: boolean = false;
  exclusion: string = 'exclusion'; // local storage value
  oneLineLoc: string = 'oneLineLoc';
  quaility: string = 'quaility'; // local storage value
  scopeOf: string = 'scopeOf'; // local storage value
  supplier: string = 'supplier'; // local storage value
  tender: string = 'tender'; // local storage value
  terminal: string = 'terminal'; // local storage value
  tendrAttach: string = 'tendrAttach';//Local storaghe for tender drawing new
  clarifiAttach: string = 'clarifiAttach';// Local storage for clarifications and deviations
  section2: string = 'section2'; // local storage value
  fetchDataResp: Array<any> = []; //to store Othercost list data
  impPer: string = ''; //To store percentage increment 
  mainSave:boolean=true;//changing main save button color
  // removing local storage whenever frame/Capacity is changed ends
  qotNumber: number = 0;
  F2F: boolean = false;
  MECH: boolean = false;
  ELE: boolean = false;
  CI: boolean = false;
  TECH: boolean = false;

  constructor(private _ITOcustomerRequirementService: ITOcustomerRequirementService, private ITOScopeOfSupplyService: ITOScopeOfSupplyService,
     private router: Router,
    private _ActivatedRoute: ActivatedRoute,
    private _ITOHomePageService: ITOHomePageService, 
    private _ITOturbineConfigService: ITOturbineConfigService,
    private _ITOScopeOfSupplyService: ITOScopeOfSupplyService,
    private _ITOendUserDetailsService: ITOendUserDetailsService, 
    private _ITOLoginService: ITOLoginService,
    private domSanitizer: DomSanitizer,
     @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOeditQoutService: ITOeditQoutService,
    @Inject(LOCAL_STORAGE) private storage1: WebStorageService,
    private _ITOcustomerDetailsService: ITOcustomerDetailsService) {
    //get the oppurtunity Seq Num from service variable that was stored in customer Details screen
    this.opportunitySNO = this._ITOcustomerRequirementService.opportunitySeqNum;
    if(this._ITOeditQoutService.checkEdit == false){
      this.backBtn = true;
    }
    this._ITOeditQoutService.button1=true;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    //to get percent increment
    this._ITOcustomerRequirementService.fetchCacheData().subscribe(respon =>
    {
      console.log(respon);
      this.fetchDataResp = respon.otherCostData;
      for(let i=0; i<this.fetchDataResp.length; i++){
        if(this.fetchDataResp[i].catDetCd == "IMP"){
        this.impPer = this.fetchDataResp[i].percentage;
        }
      }      
    })
    // get all frame Related data
    this._ITOcustomerRequirementService.getQuotationList().subscribe(res => {
      console.log(res);
      this.quotForm = res;
      this._ITOcustomerRequirementService.quotResponse = res;
      this.customerReq[this.userDetail] = this.storage.get(this.userDetail);
      this.userRegionList = this.customerReq[this.userDetail].userRegionsList; //user regionlist from login storage
      this.customerReq[this.customerReqrmnt] = this.storage.get(this.customerReqrmnt);
      console.log(this.customerReq[this.customerReqrmnt]);
      
      //Assign values to lists
      this.frameArrayList = [];
      // Orientation type commented
      // this.orientationType = [];
      this.typeOfVarientFil =[];
      this.modelType = [];
      this.turbineCategoryList = res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
      this.turbCategoryList = res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
      this.typeOfQuotationList = res.dropDownColumnvalues.typeOfQuotationList.TYPE_OF_QUOTATION_LIST;
      // this.typeOfInjectionList = res.dropDownColumnvalues.typeOfInjectionList.TYPE_OF_INJECTION_LIST;
      this.typeOfVarientFil  = res.dropDownColumnvalues.typeOfVarientList.TYPE_OF_VARIENT_LIST;
      this.typOfQuo = res.dropDownColumnvalues.typeOfQuotationList.TYPE_OF_QUOTATION_LIST;
      this.turbineTypesList = res.dropDownColumnvalues.typesOfTurbine.TURBINE;
      this.turbTypeList = res.dropDownColumnvalues.typesOfTurbine.TURBINE;
      // Orientation type commented
      // this.orientationType = res.dropDownColumnvalues.orientationTypes.ORIENTATION_TYPE;
      // this.orientTypesList = res.dropDownColumnvalues.orientationTypes.ORIENTATION_TYPE;
      this.condenserTypeList = res.dropDownColumnvalues.typesOfCondensor.CONDENSOR;
      this.condenTypeList = res.dropDownColumnvalues.typesOfCondensor.CONDENSOR;
      console.log(this.condenserTypeList)
      this.bleedType = res.dropDownColumnvalues.typesOfCondensor.BLEED_TYPE_LIST;
      //this.frameArrayList = res.dropDownColumnvalues.frameWithPowerList.FRAMES_WITH_POWER;
      this.framesWithPwr = res.dropDownColumnvalues.frameWithPowerList.FRAMES_WITH_POWER;
      this.frameArrayList = res.dropDownColumnvalues.typeOfNewList1.TYPE_OF_NEW_LIST;
      this.frameNameList = res.dropDownColumnvalues.frames.FRAMES;

      this.modelType = res.dropDownColumnvalues.modelTypes.MODEL_TYPE;
      this.modelTypeList = res.dropDownColumnvalues.modelTypes.MODEL_TYPE;
      this.regn = this._ITOcustomerRequirementService.region;
      this.regnCd = this._ITOcustomerRequirementService.regionCode;
      console.log(this._ITOcustomerDetailsService.customerDetailsForm);
      if (this.customerReq[this.customerReqrmnt] != null) {
        // if data already stored in DB and Quotation has been created, display the stored values
        this.regn = this.customerReq[this.customerReqrmnt].Region;
        this.regnCd = this.customerReq[this.customerReqrmnt].RegionCode;
        this.newDev = this.customerReq[this.customerReqrmnt].IsNewProject;
        console.log(this.newDev);
        if (this.newDev == "YES")
          this.enableMul = true;
        else if (this.newDev == "NO")
          this.enableMul = false;

        this.multFactor = this.customerReq[this.customerReqrmnt].multiplicationFactor;
        
        this.frameArrayList = [];
        // Orientation type commented
        // this.orientationTypeList = [];
        this.modelType = [];
        this.turbineCategoryList = [];
        this.turbineTypesList = [];
        // Orientation type commented
        // this.orientationType = [];
        this.condenserTypeList = [];
        this.typeOfQuotationList = [];
        this.typOfQuo = [];
        // this.typeOfInjectionList =[];
        this.typeOfVarientFil=[];
        this.typeOfVarientList=[];

        this.turbineTyp = this.customerReq[this.customerReqrmnt].CustInfo.turbineType;
        this.turbineTypesList = res.dropDownColumnvalues.typesOfTurbine.TURBINE;
        this.turbineCategoryList = res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
        this.typeOfQuotationList = res.dropDownColumnvalues.typeOfQuotationList.TYPE_OF_QUOTATION_LIST;
        this.typOfQuo = res.dropDownColumnvalues.typeOfQuotationList.TYPE_OF_QUOTATION_LIST;
        this.typOfQuoot = this.customerReq[this.customerReqrmnt].typeofQuotation;
        for (let TypOffQuot of this.typeOfQuotationList) {
          if (TypOffQuot.categoryDetDesc == this.typOfQuoot) {
            TypOffQuot.defaultValFlag = true;
          }
        }
        this._ITOcustomerRequirementService.typeOfQuotNm = this.typOfQuoot;
        console.log(this.typeOfQuotationList);
        this.bleedType = res.dropDownColumnvalues.typesOfCondensor.BLEED_TYPE_LIST;
        this.turbineCat = this.customerReq[this.customerReqrmnt].CustInfo.typeOfblade;
        if(this.turbineCat == "Improved Impulse"){
          this.enablePerImp = true;
        }else{
          this.enablePerImp = false;
        }
        this.bleedTyp = this.customerReq[this.customerReqrmnt].CustInfo.bleedtype;
        for (let turbineCat of this.turbineCategoryList) {
          if (turbineCat.categoryDetDesc == this.turbineCat) {
            turbineCat.defaultValFlag = true;
            this.typeOfBlade = turbineCat.categoryDetDesc;
            this.typeOfBladeCode = turbineCat.categoryDetCode;
            this.grpCd = turbineCat.grpCd;
          }
        }
        // this.typeOfInjectionList = res.dropDownColumnvalues.typeOfInjectionList.TYPE_OF_INJECTION_LIST;
        // this.injectionType = this.customerReq[this.customerReqrmnt].CustInfo.typeOfInject;
        // for(let typeOfInject of this.typeOfInjectionList){
        //   if(typeOfInject.categoryDetDesc == this.injectionType){
        //     typeOfInject.defaultValFlag = true;
        //     this.injectionCode = typeOfInject.categoryDetCode;
        //   }
        // }
        // Orientation type commented
        // this.orientationTypeList = res.dropDownColumnvalues.orientationTypes.ORIENTATION_TYPE;
        // this.orientationType = res.dropDownColumnvalues.orientationTypes.ORIENTATION_TYPE;
        // this.orientationTyp = this.customerReq[this.customerReqrmnt].CustInfo.Orientation;
        // for (let orientation of this.orientationTypeList) {
        //   if (orientation.categoryDetDesc == this.orientationTyp) {
        //     orientation.defaultValFlag = true;
        //   }
        // }
        this.capacit = this.customerReq[this.customerReqrmnt].CustInfo.capacity;
        this.condenserTypeList = res.dropDownColumnvalues.typesOfCondensor.CONDENSOR;
        console.log(this.condenserTypeList)
        this.CondenserTyp = this.customerReq[this.customerReqrmnt].CustInfo.condenserType;
        //comparing value stored in db along with Complete list and setting the stored value in dropdow list
        for (let condenser of this.condenserTypeList) {
          if (condenser.categoryDetDesc == this.CondenserTyp) {
            condenser.defaultValFlag = true;
            this.condTypeId = condenser.categoryDetId;

          }
        }

        //comparing value stored in db along with Complete list and setting the stored value in dropdow list
        for (let turbinetype of this.turbineTypesList) {
          if (turbinetype.categoryDetDesc == this.turbineTyp) {
            turbinetype.defaultValFlag = true;
            this.selTOT = turbinetype.categoryDetDesc;
            this.turbineTypeCode = turbinetype.categoryDetCode;
            this.TOTSel(this.turbineTyp);
          }
        }
        
        this.frameArrayList = res.dropDownColumnvalues.typeOfNewList1.TYPE_OF_NEW_LIST;
        this.frameList = res.dropDownColumnvalues.frames.FRAMES;
        this.framesWithPwr = res.dropDownColumnvalues.frameWithPowerList.FRAMES_WITH_POWER;
        //comparing value stored in db along with Complete list and setting the stored value in dropdow list
        this.frame = this.customerReq[this.customerReqrmnt].CustInfo.frames;
        console.log(this.frame);
        for (let frame of this.frameList) {
          if (frame.frameDesc == this.frame) {
            console.log(frame.frameDesc);
            this.maxPower = frame.maxPower;
            this.minPower = frame.minPower;
            frame.defaultValFlag = true;
            this.frame = frame.frameDesc;
          }
        }
        this.focusFrame();
        this.mainSave=false;
        this.BleedTyp = this.customerReq[this.customerReqrmnt].CustInfo.bleedtype;
        this.bleedType = res.dropDownColumnvalues.typesOfCondensor.BLEED_TYPE_LIST;
        //comparing value stored in db along with Complete list and setting the stored value in dropdow list
        for (let bleed of this.bleedType) {
          if (bleed.categoryDetDesc == this.BleedTyp) {
            bleed.defaultValFlag = true;
            this.typeOfbleed = bleed.categoryDetId;
            this.bleedTypeCode = bleed.categoryDetCode;
          }
          this.frameVarBleed();
          this.TOBSel(this.BleedTyp);
        }
        this.typeOfVarientFil = res.dropDownColumnvalues.typeOfVarientList.TYPE_OF_VARIENT_LIST;
        this.typeOfVarientList = res.dropDownColumnvalues.typeOfVarientList.TYPE_OF_VARIENT_LIST;
        this.varientType = this.customerReq[this.customerReqrmnt].CustInfo.typeOfVarient;
        for(let typeOfVarient of this.typeOfVarientFil){
          if(typeOfVarient.categoryDetDesc == this.varientType){
            typeOfVarient.defaultValFlag = true;
            this.varientType = typeOfVarient.categoryDetDesc;
            this.typVarient = typeOfVarient.categoryDetId;
          }
        }this.TOBSel(this.BleedTyp);
      } else {

      }
      console.log(this.frameArrayList)

    });
    this._ITOcustomerRequirementService.getAdminForm().subscribe(resAdm => {
      console.log(resAdm);
      this.adminFormmm = resAdm;
    })


  }

  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
    this.dialogMsgApp = false;
  }

  close() {
    console.log("close");
    this._ITOLoginService.dialogMsgApp = false;
    if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false) {
      this._ITOcustomerRequirementService.editFlag = false;
      // this.dialogMsgApp = true;
      // this.dialogMsgVal = "Quotation generated successfully , Quatation Number is " + resp.saveBasicDetails.quotNumber;
      this.router.navigate(['/EditQuot']);
    } else {
      // this.dialogMsgApp = true;
      // this.dialogMsgVal = "Quotation generated successfully , Quatation Number is " + resp.saveBasicDetails.quotNumber;
      this.router.navigate(['CostEstimation/ScopeOfsupplyCstEst/ScopeOfSupply']);
    }
  }

  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.customerReq[key] = this.storage.get(key);
  }

  // on changing the frame, this function will be called
  frameSel(frame) {
    this.mainSave=true;
    this.capacit = '';

    console.log(frame);
    this.selFrame = frame;
    //this.orientationTyp = '';
    //console.log(this.frameArrayList)
    // getting the power list of the selected frame
    for (let j = 0; j < this.frameArrayList.length; j++) {
      if (this.frameArrayList[j].frameDesc == this.selFrame) {
        console.log(this.frameArrayList[j].power);
        this.maxPower = this.frameArrayList[j].maxPower;
        this.minPower = this.frameArrayList[j].minPower;
      }
    }
    console.log(this.maxPower);
    if(this.turbineTypeCode == "BP"){
  this.frameVarBleed();
    }
  }

  //type of injection
  // TOInjection(vall){
  //   console.log(vall);
  //   for(let inj=0; inj<this.typeOfInjectionList.length; inj++){
  //     if(this.typeOfInjectionList[inj].categoryDetDesc == vall){
  //       this.injectionCode = this.typeOfInjectionList[inj].categoryDetCode;
  //     }
  //   }console.log(this.injectionCode);
  //   this.TOBSel(this.bleedType);
  // }
  // type of turbine selection 

  frameVarBleed(){
    console.log(this.selFrame);
    console.log(this.frame);
    this.frmaessssss = [];
    this.bleedtypNwLst = [];
    for(let k = 0; k< this.frameNameList.length; k++){
      if(this.frameNameList[k].frameDesc == this.selFrame || this.frameNameList[k].frameDesc == this.frame){    
        this.frmaessssss.push(this.frameNameList[k]);
        console.log(this.frmaessssss);
      }
    }
    let sampBleedType = this.frmaessssss.map(item => item.bleedTypId);
    var mySet = new Set(sampBleedType);  
    sampBleedType=[];
    sampBleedType = Array.from(mySet);
    console.log(sampBleedType);
    for(let g=0; g<sampBleedType.length; g++){
    for(let l = 0; l<this.bleedType.length; l++){
      if(sampBleedType[g] == this.bleedType[l].categoryDetId){
        this.bleedtypNwLst.push(this.bleedType[l]);
      }
    }
  }
  }

  TOTSel(value) {
    this.mainSave=true;
    this.bleedtypNwLst = [];
    console.log(value);
    for(let tur=0; tur<this.turbineTypesList.length; tur++){
      if(this.turbineTypesList[tur].categoryDetDesc == value){
        this.turbineTypeCode = this.turbineTypesList[tur].categoryDetCode;
      }
    }console.log(this.turbineTypeCode);
    if (value == "Condensing") {
      this.enableCT = true;
    }
    else if (value == "Back Pressure") {
      this.enableCT = false;
      this.condTypeId = 0;
    }
    this.selTOT = value;
    this.tempFrameList = [];
    // Orientation type commented
    // this.orientationTypeList = [];
    this.modelType = [];
    this.frameList = [];
    this.frame = '';
// Orientation type commented
    // for (let i = 0; i < this.turbineTypesList.length; i++) {
    //   if (this.turbineTypesList[i].categoryDetDesc == this.selTOT) {
    //     for (let k = 0; k < this.orientationType.length; k++) {
    //       if (this.turbineTypesList[i].categoryDetCode == this.orientationType[k].turbineCode) {
    //         this.orientationTypeList.push(this.orientationType[k]);
    //       }
    //     }
    //   }
    // }
    // console.log(this.orientationTypeList);
    // this.TOBSel(this.bleedType);
    this.focusFrame();
  }

  TOBSel(bleedType) {
    this.mainSave=true;
    // this.frameList = [];
    this.typeOfVarientList =[];
    console.log(bleedType);
    this.selBt = bleedType;

    for (let bt = 0; bt < this.bleedType.length; bt++) {
      if (this.bleedType[bt].categoryDetDesc == this.selBt) {
        this.typeOfbleed = this.bleedType[bt].categoryDetId;
        this.bleedTypeCode = this.bleedType[bt].categoryDetCode;
      }
    }console.log(this.bleedTypeCode);
    // console.log(this.injectionCode, this.bleedTypeCode, this.turbineTypeCode);
    console.log(this.bleedTypeCode, this.turbineTypeCode);
    for(let v=0; v<this.typeOfVarientFil.length; v++){
      // if(this.typeOfVarientFil[v].dependentCode == this.injectionCode &&
      if(this.typeOfVarientFil[v].turbineCode == this.turbineTypeCode &&
    this.typeOfVarientFil[v].dependentCode == this.bleedTypeCode){
      this.typeOfVarientList.push(this.typeOfVarientFil[v]);
      }
    }
    // this.focusFrame();

    console.log(this.frameList);
  }

  TOVSel(typVarient){
    this.mainSave=true;
    console.log(typVarient);
    for(let k=0; k<this.typeOfVarientList.length; k++){
      if(this.typeOfVarientList[k].categoryDetDesc == typVarient){
        this.typVarient = this.typeOfVarientList[k].categoryDetId;
      }
    }
    console.log(this.typVarient);
    //this.focusFrame();
  }

  TOBladeSel(turbineCatgry) {
    this.mainSave=true;

    this.tempFrames = [];
    console.log(this.frameList);
    console.log(turbineCatgry);
    for(let l=0; l<this.turbineCategoryList.length; l++){
      if(this.turbineCategoryList[l].categoryDetDesc == turbineCatgry){
        this.typeOfBladeCode = this.turbineCategoryList[l].categoryDetCode;
        this.grpCd = this.turbineCategoryList[l].grpCd;
      }
    }
    this.typeOfBlade = turbineCatgry;
    this.frameList = [];
    this.frame = '';
    if(turbineCatgry == "Improved Impulse"){
      this.enablePerImp = true;
    }else{
      this.enablePerImp = false;
    }
    if(turbineCatgry != "Reaction"){
  this.focusFrame() 
  this.frameUpdate()
    }
    
  }

  // this function will be called whenevr there is change in any of the basic requirements, so as to form the new frame list
  focusFrame() {
    this.mainSave=true;

    this.frameList = [];

    console.log(this.frameArrayList);
    console.log(this.typeOfbleed, this.turbineTypeCode, this.selTOT, this.condTypeId, this.typeOfBlade, this.typVarient, this.typeOfBladeCode, this.grpCd);
    if(this.typeOfBlade == "Improved Impulse"){
      for (let p = 0; p < this.frameArrayList.length; p++) {
        if (this.frameArrayList[p].turbineCode == this.turbineTypeCode        
          // && this.frameArrayList[p].bleedTypId == this.typeOfbleed
          // && this.frameArrayList[p].condTypId == this.condTypeId
          // && this.frameArrayList[p].variantTypeId == this.typVarient
          && this.frameArrayList[p].turbdesignName == this.typeOfBladeCode
          && this.frameArrayList[p].improvedImpId == this.grpCd) {
          this.frameList.push(this.frameArrayList[p]);  
        }
        if (p == this.frameArrayList.length - 1) {
          if (this.frameList.length == 0) {
            this.framesMsg = "No frames for above combination";
          } else {
            this.framesMsg = '';
       
          }
        }
      }
    }else{
      for (let p = 0; p < this.frameArrayList.length; p++) {
        if (this.frameArrayList[p].turbineCode == this.turbineTypeCode   
          // &&this.frameArrayList[p].bleedTypId == this.typeOfbleed
          // && this.frameArrayList[p].condTypId == this.condTypeId
          // && this.frameArrayList[p].variantTypeId == this.typVarient
          && this.frameArrayList[p].turbdesignName == this.typeOfBladeCode
        ) {
          this.frameList.push(this.frameArrayList[p]);
  
        }
        if (p == this.frameArrayList.length - 1) {
          if (this.frameList.length == 0) {
            this.framesMsg = "No frames for above combination";
          } else {
            this.framesMsg = '';
          }
        }
      }
    }
    
    console.log(this.frameList)
    this.frameVarBleed();
  }

  frameUpdate(){
  if(this.typeOfBlade == "Improved Impulse" || this.typeOfBlade == "Impulse")
    {
      console.log("hello frames")
      for(let j=0;j<this.frameList.length;j++)
      {
        if(this.frameList[j].frameDesc==this.customerReq[this.customerReqrmnt].CustInfo.frames)
        {
          this.frame=this.customerReq[this.customerReqrmnt].CustInfo.frames;
          break;
        }
      }
    }
  }

  ModelSel(models) {
    this.mainSave=true;
    // this._ITOcustomerRequirementService.getQuotationList().subscribe(resk => {
    for (let l = 0; l < this.modelTypeList.length; l++) {
      if (this.modelTypeList[l].categoryDetDesc == models) {
        this.enableBleed = this.modelTypeList[l].bleedsFlag;
        this.enableExtraction = this.modelTypeList[l].extractFlag;
      }
    }
    //  })

  }
  //orientation type commented
  // orientaionSel()
  // {
  //   this.mainSave=true;

  // }
  onKeyMultyFactor(event)
  {
    this.mainSave=true;

  }
  quotSel()
  {
    this.mainSave=true;

  }
  onKey(event) {
    this.mainSave=true;
    console.log(event.target.value);
    this.showMsg = false;
    if (event.target.value > this.maxPower || event.target.value < this.minPower) {
      this.showMsg = true;
    } else {
      this.showMsg = false;
    }
  }

  TOCSel(value) {
        this.mainSave=true;
    // this.frameList = [];
    // this.frame = '';
    console.log(value);

    for (let l = 0; l < this.condenserTypeList.length; l++) {
      if (this.condenserTypeList[l].categoryDetDesc == value) {
        this.condTypeId = this.condenserTypeList[l].categoryDetId;
      }
    }
this.frameVarBleed();
  }

  // saving details to DB and getting the Quotation Number
  saveBasicDetails(CustInfoCE) {
    
    this.hidespinner = false;
    console.log(CustInfoCE);

    // whenever there is change in Frame or Capacity, Quotation has to be revised,hence making isFrameUpdated 1 or 0
    this._ITOcustomerRequirementService.capacity = CustInfoCE.capacity;
    // if (this.customerReq[this.customerReqrmnt] != null) {
    //  if( ((this.customerReq[this.customerReqrmnt].CustInfo.typeOfblade == "Improved Impulse") && (CustInfoCE.typeOfblade == "Impulse")) ||
    //  ((this.customerReq[this.customerReqrmnt].CustInfo.typeOfblade == "Impulse") && (CustInfoCE.typeOfblade == "Improved Impulse"))){
    //    this.isFrameUpdated = 0;
    //  }else{
    //    this.isFrameUpdated = 1;
    //  }
    //   if ((this.customerReq[this.customerReqrmnt].CustInfo.turbineType != CustInfoCE.turbineType) ||
    //      (this.customerReq[this.customerReqrmnt].CustInfo.capacity != CustInfoCE.capacity) || 
    //      (this.customerReq[this.customerReqrmnt].CustInfo.frames != CustInfoCE.frames) ||
    //      (this.customerReq[this.customerReqrmnt].CustInfo.typeOfVarient != CustInfoCE.typeOfVarient) ||
    //      ((this.customerReq[this.customerReqrmnt].typeofQuotation == "Non Standard") && (CustInfoCE.TypOffQuot == "Standard")) ) {
    //     this.isFrameUpdated = 1;
    //   }else{
    //     this.isFrameUpdated = 0;
    //   }
    // }
     
    if (this.customerReq[this.customerReqrmnt] != null) {
      if ((this.customerReq[this.customerReqrmnt].CustInfo.capacity != CustInfoCE.capacity) || 
      (this.customerReq[this.customerReqrmnt].CustInfo.frames != CustInfoCE.frames) ||
      ((this.customerReq[this.customerReqrmnt].typeofQuotation == "Non Standard") && (CustInfoCE.TypOffQuot == "Standard"))) {
        this.isFrameUpdated = 1;
      } else {
        this.isFrameUpdated = 0;
      }
    }
         
    this._ITOcustomerRequirementService.isNewProject = CustInfoCE.newDevelopment;
   // this._ITOcustomerRequirementService.

    this._ITOcustomerRequirementService.percentageVariation = CustInfoCE.mulipleFactor;
    this._ITOcustomerRequirementService.typeOfTurbine = CustInfoCE.turbineType;

    for (let t = 0; t < this.turbTypeList.length; t++) {
      if (this.turbCategoryList[t].categoryDetDesc == CustInfoCE.typeOfblade) {
        this._ITOcustomerRequirementService.turbineCat = this.turbCategoryList[t].categoryDetCode;
        this._ITOcustomerRequirementService.turbineCatId = this.turbCategoryList[t].categoryDetId;
        this._ITOcustomerRequirementService.turbineCatDesc = this.turbCategoryList[t].categoryDetDesc;
      }
    }
    for (let t = 0; t < this.turbTypeList.length; t++) {
      if (this.turbTypeList[t].categoryDetDesc == CustInfoCE.turbineType) {
        this._ITOcustomerRequirementService.turbineCode = this.turbTypeList[t].categoryDetCode;
      }
    }
    for (let k = 0; k < this.frameNameList.length; k++) {
      if (this.frameNameList[k].frameDesc == CustInfoCE.frames) {
        this._ITOcustomerRequirementService.frameId = this.frameNameList[k].frameId;
        this._ITOcustomerRequirementService.frameName = CustInfoCE.frames;
      }
    }
    for (let l = 0; l < this.condenTypeList.length; l++) {
      if (this.condenTypeList[l].categoryDetDesc == CustInfoCE.condenserType) {
        this._ITOcustomerRequirementService.condensingTypeId = this.condenTypeList[l].categoryDetId;
      }
    }
    console.log(this._ITOcustomerRequirementService.turbineCode)
    if (this._ITOcustomerRequirementService.turbineCode == "BP") {
      this._ITOcustomerRequirementService.condensingTypeId = 0;
    }
    for(let t = 0; t<this.typeOfQuotationList.length; t++){
      if(this.typeOfQuotationList[t].categoryDetDesc == CustInfoCE.TypOffQuot){
        this._ITOcustomerRequirementService.typOfQuote = this.typeOfQuotationList[t].categoryDetId;
        this._ITOcustomerRequirementService.typeOfQuotNm = this.typeOfQuotationList[t].categoryDetDesc;
      }
    }
    // for(let ij=0; ij<this.typeOfInjectionList.length; ij++){
    //   if(this.typeOfInjectionList[ij].categoryDetDesc == CustInfoCE.typeOfInject){
    //     this._ITOcustomerRequirementService.typeOfInject = this.typeOfInjectionList[ij].categoryDetId;
    //     this._ITOcustomerRequirementService.typeOfInjectNm = this.typeOfInjectionList[ij].categoryDetDesc;
    //     this._ITOcustomerRequirementService.typeOfInjectionCode = this.typeOfInjectionList[ij].categoryDetCode;
    //   }
    // }
    for(let va=0; va<this.typeOfVarientFil.length; va++){
      if(this.typeOfVarientFil[va].categoryDetDesc == CustInfoCE.typeOfVarient){
        this._ITOcustomerRequirementService.typeOfVarient = this.typeOfVarientFil[va].categoryDetId;
        this._ITOcustomerRequirementService.typeOfVarientNm = this.typeOfVarientFil[va].categoryDetDesc;
      }
    }
    this._ITOcustomerRequirementService.typOfOfff = this._ITOcustomerDetailsService.typOfOfff;
    //saving in local storage
    this.saveInLocal(this.customerReqrmnt, {
      CustInfo: CustInfoCE, RegionCode: this.regnCd, Region: this.regn,
      IsNewProject: this.newDev, multiplicationFactor: this.multFactor,typeofQuotation: this._ITOcustomerRequirementService.typeOfQuotNm,
    });

    console.log(this._ITOcustomerDetailsService.customerDetailsForm);
    this._ITOcustomerRequirementService.typeOfbleed = this.typeOfbleed;
    this._ITOcustomerRequirementService.condensingTypeName = CustInfoCE.condenserType;
    this._ITOcustomerRequirementService.isFrameUpdated = this.isFrameUpdated;
    this._ITOcustomerRequirementService.percentageVariation = CustInfoCE.mulFactor;
    this._ITOcustomerRequirementService.opportunitySeqNum = this.storage.get(this.custdetails).oppSeqNo;
    this.quotForm.customerDetailsForm = this._ITOcustomerDetailsService.customerDetailsForm;
    this.quotForm.saveBasicDetails.bleedTypeId = this.typeOfbleed;
    this.quotForm.saveBasicDetails.capacity = this._ITOcustomerRequirementService.capacity;
    console.log(this._ITOcustomerRequirementService.condensingTypeId)
    this.quotForm.saveBasicDetails.condensingTypeId = this._ITOcustomerRequirementService.condensingTypeId;
    this.quotForm.saveBasicDetails.condensingTypeName = CustInfoCE.condenserType;
    this.quotForm.saveBasicDetails.frameId = this._ITOcustomerRequirementService.frameId;
    this.quotForm.saveBasicDetails.frameName = this._ITOcustomerRequirementService.frameName;
    this.quotForm.saveBasicDetails.opportunitySeqNum = this.storage.get(this.custdetails).oppSeqNo;
    this.quotForm.saveBasicDetails.typeOfTurbine = this._ITOcustomerRequirementService.typeOfTurbine;
    this.quotForm.saveBasicDetails.turbineCode = this._ITOcustomerRequirementService.turbineCode;
    this.quotForm.saveBasicDetails.isFrameUpdated = this.isFrameUpdated;
    this.quotForm.saveBasicDetails.typOfBladeCode = this._ITOcustomerRequirementService.turbineCat;
    this.quotForm.saveBasicDetails.bladeTypeId = this._ITOcustomerRequirementService.turbineCatId;
    this.quotForm.saveBasicDetails.percentageVariation = CustInfoCE.mulFactor;
    if (CustInfoCE.newDevelop == "YES")
    {
      this.quotForm.saveBasicDetails.isNewProject = 1;
      this._ITOcustomerRequirementService.isNewProject = 1;
    }
    else if (CustInfoCE.newDevelop == "NO")
    {
      this.quotForm.saveBasicDetails.isNewProject = 0;
    }
    //Customer data 
    this.quotForm.saveBasicDetails.custName = this._ITOcustomerDetailsService.customerDetailsForm.custName;
    this.quotForm.saveBasicDetails.custType = this._ITOcustomerDetailsService.customerDetailsForm.custType;
    this.quotForm.saveBasicDetails.custCode = this._ITOcustomerDetailsService.customerDetailsForm.custCode;
    this.quotForm.saveBasicDetails.endUserName = this._ITOcustomerDetailsService.customerDetailsForm.endUserName;
    //Opp Data
    // this.quotForm.saveBasicDetails.oppName = this._ITOcustomerDetailsService.customerDetailsForm.oppName;
    // this.quotForm.saveBasicDetails.oppContactName = this._ITOcustomerDetailsService.customerDetailsForm.oppContactName;
    // this.quotForm.saveBasicDetails.oppContactEmail = this._ITOcustomerDetailsService.customerDetailsForm.oppContactEmail;
    // this.quotForm.saveBasicDetails.oppContactPhone = this._ITOcustomerDetailsService.customerDetailsForm.oppContactPhone;
    // this.quotForm.saveBasicDetails.oppContactAddress = this._ITOcustomerDetailsService.customerDetailsForm.oppContactAddress;
     this.quotForm.saveBasicDetails.oppContactStateName = this._ITOcustomerDetailsService.customerDetailsForm.oppContactStateName;
    // this.quotForm.saveBasicDetails.isEndUserAvailable = this._ITOcustomerDetailsService.customerDetailsForm.isEndUserAvailable;

    //Customer data setting ends

    // Orientation type commented
    // for (let o = 0; o < this.orientTypesList.length; o++) {
    //   if (this.orientTypesList[o].categoryDetDesc == CustInfoCE.Orientation) {
    //     this.quotForm.saveBasicDetails.orientationTypeId = this.orientTypesList[o].categoryDetId;
    //   }
    // }

    for (let h = 0; h < this.typOfQuo.length; h++) {
      if (this.typOfQuo[h].categoryDetDesc == CustInfoCE.TypOffQuot) {
        this.quotForm.saveBasicDetails.typeOfQuot = this.typOfQuo[h].categoryDetId;
      }
    }
    this._ITOcustomerRequirementService.typeOfOfferNm = this._ITOcustomerDetailsService.typeOfOfferNm;
    this.quotForm.saveBasicDetails.typeOfOffer = this._ITOcustomerRequirementService.typOfOfff;
    //this.quotForm.saveBasicDetails.typeOfCustomer = this._ITOcustomerRequirementService.typOfCust; // added MM
   // this.quotForm.saveBasicDetails.typeOfQuot = this._ITOcustomerRequirementService.typOfQuote;
    this.quotForm.saveBasicDetails.typeOfOfferNm = this._ITOcustomerRequirementService.typeOfOfferNm;
    this.quotForm.saveBasicDetails.typeOfQuotNm = this._ITOcustomerRequirementService.typeOfQuotNm;
    // this.quotForm.saveBasicDetails.typeOfInjection = this._ITOcustomerRequirementService.typeOfInject;
    // this.quotForm.saveBasicDetails.typeOfInjectionNm = this._ITOcustomerRequirementService.typeOfInjectNm;
    this.quotForm.saveBasicDetails.typeOfVarient = this._ITOcustomerRequirementService.typeOfVarient;
    this.quotForm.saveBasicDetails.typeOfVarientNm = this._ITOcustomerRequirementService.typeOfVarientNm;
    this.quotForm.saveBasicDetails.regionId = this._ITOcustomerRequirementService.regionId;
    this.quotForm.saveBasicDetails.regionCode = this._ITOcustomerRequirementService.regionCode;
    this.quotForm.saveBasicDetails.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.quotForm.saveBasicDetails.power = this.maxPower;
    this.quotForm.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
    //End user Details
    console.log(this._ITOcustomerDetailsService.customerDetailsForm);
    this.quotForm.saveBasicDetails.typeOfOffer =  this._ITOcustomerRequirementService.typOfOfff;
    this.quotForm.saveBasicDetails.enquiryReference = this._ITOcustomerDetailsService.enquiryReference;
   this.quotForm.saveBasicDetails.typeOfCustomer = null; //added MM
   this.quotForm.saveBasicDetails.isEndUserReq =  null; //added MM
   this.quotForm.saveBasicDetails.stateId = null;   

  //  if(this._ITOcustomerDetailsService.customerDetailsForm != null)
  //   {
  //     if (this.quotForm.saveBasicDetails.typeOfCustomer == "EX")
  //     {
  //       this.quotForm.saveBasicDetails.stateId = null;
  //     }
  //     else
  //   this.quotForm.saveBasicDetails.endUserName = this._ITOcustomerDetailsService.customerDetailsForm.endUserName;
  //   this.quotForm.saveBasicDetails.endUserAddress = this._ITOcustomerDetailsService.customerDetailsForm.endUserAddress;
  //   this.quotForm.saveBasicDetails.stateId = null; 
  //   }
    
    console.log(this.quotForm);
    console.log('xx');
    console.log(this.quotForm.saveBasicDetails.stateId);
    console.log(this.quotForm.saveBasicDetails.typeOfCustomer);
    
    if (this._ITOcustomerRequirementService.saveBasicDet) {
      this.quotForm.saveBasicDetails.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    }
    console.log(this.quotForm.saveBasicDetails)
    if(this.typeOfBladeCode == "IM" && this.grpCd == "1"){
      this.quotForm.saveBasicDetails.improvedImpulse = 1;
      this._ITOcustomerRequirementService.improvedImpulse = 1;
    }else{
      this.quotForm.saveBasicDetails.improvedImpulse = 0;
      this._ITOcustomerRequirementService.improvedImpulse = 0;
    }
    // if(this._ITOcustomerRequirementService.isEndUserReq==1)
    // {
    //   this.quotForm.saveBasicDetails.endUserAddress="";
    //   this.quotForm.saveBasicDetails.endUserAddress="";
    //   this.quotForm.saveBasicDetails.custCode="";
    //   this.quotForm.saveBasicDetails.custCodeNm="";
    // }
    this._ITOcustomerRequirementService.saveBasicDetails(this.quotForm).subscribe(resp => {
      console.log(resp);
      this._ITOcustomerRequirementService.saveBasicDet = resp.saveBasicDetails;
      this._ITOcustomerRequirementService.quotForm = resp;

      console.log(this._ITOcustomerRequirementService.saveBasicDet);
      this.hidespinner = false;
      if (resp.successCode == 0) {
        this.mainSave=false;
        // this._ITOLoginService.openSuccMsg("Quotation generated successfully");
        //alert("Quotation generated successfully , Quatation Number is " + resp.saveBasicDetails.quotNumber);
        this._ITOcustomerRequirementService.sendMessage(resp.saveBasicDetails.quotNumber);
        this._ITOcustomerRequirementService.quotNumber = resp.saveBasicDetails.quotNumber;
        this._ITOcustomerRequirementService.sendbtnStatus(true, false, false, false,false,false,false,false);
        this._ITOcustomerRequirementService.sendclrBtnStatus(false, false, false, false, false, false, false, false, false, false,false, false,false,false);
        this.dialogMsgApp = true;
        this.dialogMsgVal = "Quotation Number is " + resp.saveBasicDetails.quotNumber;
        console.log(this.adminFormmm);
        this.adminFormmm.quotId = resp.saveBasicDetails.quotId;
        this.adminFormmm.modBy = resp.saveBasicDetails.modifiedById;
        // this._ITOcustomerRequirementService.getStdOffer(this.adminFormmm).subscribe(resStdoff => {
        //   console.log(resStdoff);
        // });
        // if (this.isFrameUpdated == 1) {
          //if this condition is true,(means if frame or power is changed) clear all local storage values starts
if(this.isFrameUpdated == 1 || this.isFrameUpdated == undefined){
          this.storage.remove(this.scopeofsupp);
          this.storage.remove(this.addOnDetails);
          this.storage.remove(this.packLocal);
          this.storage.remove(this.transLocal);
          this.storage.remove(this.ECData);
          this.storage.remove(this.sparesLocal);
          this.storage.remove(this.varCostLocal);
          this.storage.remove(this.dboMechLoc);
          this.storage.remove(this.dboComSecALoc);
          this.storage.remove(this.dboPerfLoc);
          this.storage.remove(this.dboMechFull);
          this.storage.remove(this.exclusion);
	 this.storage.remove(this.quaility);
	  this.storage.remove(this.scopeOf);
	   this.storage.remove(this.supplier);
	    this.storage.remove(this.tender);
		 this.storage.remove(this.terminal);
          this.storage.remove(this.projectCostLocal);
          this.storage.remove(this.f2fCostData);
          this.storage.remove(this.F2FSap);
          this.storage.remove(this.dboEleFull);
          this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.dboEleAuxFull);
          this.storage.remove(this.F2FTurbine);
          this.storage.remove(this.MechExpScope);
          this.storage.remove(this.EleCiExtdScope);
          this.storage.remove(this.EleExtdScope);
          this.storage.remove(this.dboMechAuxLoc);
          this.storage.remove(this.generalInput);
          this.storage.remove(this.clarifiAttach);
          this.storage.remove(this.tendrAttach);
          this._ITOeditQoutService.dboEleAuxData = [];
    this._ITOeditQoutService.dboEleAuxItmOthers = [];
    this._ITOeditQoutService.dboEleCiData = [];
    this._ITOeditQoutService.dboEleCIItmOthers = [];
    this._ITOeditQoutService.dboEleCIAuxData = [];
    this._ITOeditQoutService.dboEleCIAuxItmOthers = [];
          this._ITOeditQoutService.dboEleData = [];
          this._ITOeditQoutService.cirListData = [];
    this._ITOeditQoutService.fixedListData = [];
    this._ITOeditQoutService.identListData = [];
    this._ITOeditQoutService.itemListData = [];
          this._ITOeditQoutService.dboEleItmOthers = [];
          this._ITOeditQoutService.qualitiasurance = [];
          this._ITOeditQoutService.scopeofspares = [];
          this._ITOeditQoutService.tenderDraw = [];
          this._ITOeditQoutService.terminalpo = [];
          this._ITOeditQoutService.exclusionLi = [];
          this._ITOeditQoutService.subSuppliersList = [];
          this._ITOeditQoutService.dboMechData = [];
          this._ITOeditQoutService.savePerformanceDataList1 = [];
      this._ITOeditQoutService.savePerformanceDataList2 = [];
      this._ITOeditQoutService.savePerformanceDataList3 = [];
          this._ITOeditQoutService.mechExtScpList = [];
          this._ITOeditQoutService.eleExtScopeList = [];
          this._ITOeditQoutService.eleCIExtScopeList = [];
          this._ITOeditQoutService.dboDataOthers = [];
          this._ITOeditQoutService.dboEleSplAddOnList = [];
          this._ITOeditQoutService.dboEleOthers = [];
          this._ITOeditQoutService.dboEleDataAddOn = [];
          this._ITOeditQoutService.dboEleNewAddOns = [];
          this._ITOeditQoutService.dboF2fData = [];
          this._ITOeditQoutService.dboF2fNewAddOns = [];
          this._ITOeditQoutService.f2fOthersItemList = [];
          this._ITOeditQoutService.f2fOthersSubItemList = [];
          this._ITOeditQoutService.f2fOthersSubItemTypeList = [];
          this._ITOScopeOfSupplyService.sampleScope = [];
          //if this condition is true,clear all local storage values ends

          this._ITOturbineConfigService.getExcelCostSheetData(resp.saveBasicDetails.quotId).subscribe(resAdd => {
            console.log(resAdd);
            this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);
            this._ITOcustomerRequirementService.sendbtnStatus(true,false,true,false,false,false,false,false);
        
          });
        }else{
          this.storage.remove(this.ECData);
          this.storage.remove(this.generalInput)
          this.storage.remove(this.packLocal);
          this.storage.remove(this.transLocal);
          this.storage.remove(this.sparesLocal);
          this.storage.remove(this.varCostLocal);
          this.storage.remove(this.projectCostLocal);
          this.storage.remove(this.oneLineLoc);
          this.storage.remove(this.custdetails);
          this.storage.remove(this.F2FTurbine);
          this.storage.remove(this.dboMechLoc);
          this.storage.remove(this.dboMechFull);
          this.storage.remove(this.dboMechAuxLoc);
          this.storage.remove(this.MechExpScope);
          this.storage.remove(this.dboEleAuxFull);
          this.storage.remove(this.EleExtdScope);
            this.storage.remove(this.dboEleFull);
            this.storage.remove(this.dboEleCIFull);
            this.storage.remove(this.dboEleCIAuxFull);
            this.storage.remove(this.EleCiExtdScope);
            this.storage.remove(this.dboPerfLoc);
         this.storage.remove(this.exclusion);
         this.storage.remove(this.quaility);
          this.storage.remove(this.scopeOf);
           this.storage.remove(this.supplier);
            this.storage.remove(this.tender);
           this.storage.remove(this.terminal);
           this.storage.remove(this.section2);//ali
           this.storage.remove(this.dboComSecALoc);
      
            this._ITOcustomerRequirementService.editFlag = true;
            this._ITOeditQoutService.dboEleAuxData = [];
          this._ITOeditQoutService.dboEleAuxItmOthers = [];
          this._ITOeditQoutService.dboEleCiData = [];
          this._ITOeditQoutService.dboEleCIItmOthers = [];
          this._ITOeditQoutService.dboEleCIAuxData = [];
          this._ITOeditQoutService.dboEleCIAuxItmOthers = [];
          this._ITOeditQoutService.dboEleData = [];
          this._ITOeditQoutService.dboEleItmOthers = [];
          this._ITOeditQoutService.dboF2fData = [];
          this._ITOeditQoutService.dboF2fNewAddOns = [];
          this._ITOeditQoutService.f2fOthersItemList = [];
          this._ITOeditQoutService.f2fOthersSubItemList = [];
          this._ITOeditQoutService.f2fOthersSubItemTypeList = [];
          this._ITOeditQoutService.dboMechData = [];
          this._ITOeditQoutService.mechExtScpList =[];
          this._ITOeditQoutService.eleExtScopeList = [];
          this._ITOeditQoutService.eleCIExtScopeList = [];
          this._ITOeditQoutService.dboDataOthers = [];
          this._ITOeditQoutService.dboEleSplAddOnList = [];
          this._ITOeditQoutService.dboEleOthers = [];
          this._ITOeditQoutService.dboEleDataAddOn = [];
          this._ITOeditQoutService.dboEleNewAddOns = [];  
          this._ITOeditQoutService.dboEleFilterData = [];
          this._ITOeditQoutService.dboEleItmOthers = [];
          ///MECH******* 
          this._ITOeditQoutService.dboMechData = [];
          this._ITOeditQoutService.mechExtScpList=[];
          this._ITOeditQoutService.dboMechAuxData=[];
          this._ITOeditQoutService.dboMechAuxDataAddonData=[];
          this._ITOeditQoutService.dboDataOthers = [];
          //****************** */
          //f2f*********
          this._ITOeditQoutService.dboF2fData = [];
          //this._ITOeditQoutService.dboF2fNewAddOns = res.f2fAddOnList1;
          this._ITOeditQoutService.f2fOthersItemList = [];
          this._ITOeditQoutService.f2fOthersSubItemList = [];
          this._ITOeditQoutService.f2fOthersSubItemTypeList = [];
          //**********
          this._ITOeditQoutService.savePerformanceDataList1 = [];
            this._ITOeditQoutService.savePerformanceDataList2 = [];
            this._ITOeditQoutService.savePerformanceDataList3 = [];
            this._ITOeditQoutService.cirListData = [];
          this._ITOeditQoutService.itemListData =[];
          this._ITOeditQoutService.identListData = [];
          this._ITOeditQoutService.fixedListData = [];
         // this._ITOScopeOfSupplyService.sampleScope = [];
          this._ITOeditQoutService.dboEleItmOthers = [];
            //Technical Components
            this._ITOeditQoutService.qualitiasurance = [];
            this._ITOeditQoutService.scopeofspares = [];
            this._ITOeditQoutService.tenderDraw = [];
            this._ITOeditQoutService.terminalpo = [];
            this._ITOeditQoutService.exclusionLi = [];
            this._ITOeditQoutService.subSuppliersList = [];
            
            //comerial section2
            this._ITOeditQoutService.section2Data = []; 
            this._ITOeditQoutService.section2AData = [];
            this._ITOeditQoutService.completecomercialdata=[];//ali
      
      
        //Technical Components
          this.qotNumber = resp.saveBasicDetails.quotId;

          this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(res => {
            console.log(res);
            this._ITOcustomerRequirementService.editFlag=true;
      
      this.scopeOfSupplyList = res.scopeOfSupplyList;
       // based on scope of suplly divs has to be displayed
       this.scopeOfSupply = res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
       this.ITOScopeOfSupplyService.sampleScope=res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
       this.saveInLocal(this.scopeofsupp, this.scopeOfSupply);
       this.genInputList =  res.savedList; 
       this.saveEleFilterList = res.saveEleFilterList;
 for(let n=0; n< this.genInputList.length; n++){
   if(this.genInputList[n].genType == "INPUT"){
   this.genInputList[n].categoryValName = this.genInputList[n].categoryValCode;
   }
 }
 for(let n=0; n< this.saveEleFilterList.length; n++){
   if(this.saveEleFilterList[n].genType == "INPUT"){
   this.saveEleFilterList[n].colValCd = this.saveEleFilterList[n].colValCd;
   }
 }
 this.saveInLocal(this.generalInput, {
   genInputList : this.genInputList, scopeOfsupp: this.scopeOfSupplyList, saveEleFilterList: this.saveEleFilterList,
 })
            if(res.eleGeneralList.length!=0)
            {
              for(let j=0;j<res.eleGeneralList.length;j++)
              {
                if(res.eleGeneralList[j].eleType=="ELE")
                {
                  this._ITOeditQoutService.dboEleData.push(res.eleGeneralList[j]);
                }
                if(res.eleGeneralList[j].eleType=="ELE_AUX")
                {
                  this._ITOeditQoutService.dboEleAuxData.push(res.eleGeneralList[j]);
                }
                if(res.eleGeneralList[j].eleType=="CI")
                {
                  this._ITOeditQoutService.dboEleCiData.push(res.eleGeneralList[j]);
                }
                if(res.eleGeneralList[j].eleType=="CI_AUX")
                {
                  this._ITOeditQoutService.dboEleCIAuxData.push(res.eleGeneralList[j]);
                }
              }
              
            }
            //mech*****************
            this._ITOeditQoutService.dboMechData = res.mechGeneralList;
            this._ITOeditQoutService.dboMechAuxData= res.mechAuxGeneralList;
            this._ITOeditQoutService.mechExtScpList=res.mechExtScpList;
            this._ITOeditQoutService.eleCIExtScopeList = res.eleCIExtScopeList;
            this._ITOeditQoutService.eleExtScopeList = res.eleExtScopeList;
            this._ITOeditQoutService.dboMechAuxDataAddonData=res.mechAuxGeneralListNew;
            this._ITOeditQoutService.dboDataOthers = res.quotDboMechOthersList;
            ////***************** */
            this._ITOeditQoutService.dboEleFilterData = res.eleGeneralSpecialList;
            this._ITOeditQoutService.dboEleItmOthers = res.eleOtherList;
            this._ITOeditQoutService.dboEleDataAddOn = res.addInstrList;
            //f2f ************
            this._ITOeditQoutService.dboF2fData = res.f2fGeneralList;
            //this._ITOeditQoutService.dboF2fNewAddOns = res.f2fAddOnList1;
            this._ITOeditQoutService.f2fOthersItemList = res.f2fOthersItemList;
            this._ITOeditQoutService.f2fOthersSubItemList = res.f2fOthersSubItemList;
            this._ITOeditQoutService.f2fOthersSubItemTypeList = res.f2fOthersSubItemTypeList;
            ////****************
            this._ITOeditQoutService.savePerformanceDataList1 = res.savePerformanceDataList1;
            this._ITOeditQoutService.savePerformanceDataList2 = res.savePerformanceDataList2;
            this._ITOeditQoutService.savePerformanceDataList3 = res.savePerformanceList3;
            this._ITOeditQoutService.identListData = res.identListData;
            this._ITOeditQoutService.cirListData = res.cirListData;
            this._ITOeditQoutService.itemListData = res.itemListData;
            this._ITOeditQoutService.fixedListData = res.fixedListData;
            console.log(res.otherGetDataList);
            if(res.saveOtherChapterList!=undefined)
            {      
            
            for(let x=0;x<res.saveOtherChapterList.length;x++)
            {
             if(res.saveOtherChapterList[x].scopeCd=="QA")
             {
              this._ITOeditQoutService.qualitiasurance.push(res.saveOtherChapterList[x]);
             }
             if(res.saveOtherChapterList[x].scopeCd=="SP")
             {
              this._ITOeditQoutService.scopeofspares.push(res.saveOtherChapterList[x]);
             }
             if(res.saveOtherChapterList[x].scopeCd=="DR")
             {
              this._ITOeditQoutService.tenderDraw.push(res.saveOtherChapterList[x]);
             }
             if(res.saveOtherChapterList[x].scopeCd=="TP")
             {
              this._ITOeditQoutService.terminalpo.push(res.saveOtherChapterList[x]);
      
             }
      if(res.saveOtherChapterList[x].scopeCd=="EL")
             {
              this._ITOeditQoutService.exclusionLi.push(res.saveOtherChapterList[x]);
             }
             if(res.saveOtherChapterList[x].scopeCd=="SSL")
             {
                 
              this._ITOeditQoutService.subSuppliersList.push(res.saveOtherChapterList[x]);
             }          
           
            }
           
          }
         
        this._ITOeditQoutService.eleExtScopeList1 = res.eleExtScopeList1;
            ////erection comisiion///
            this.onLineBomExcel = res.oneLineBomExcel;
            this.ecBean = res.errectionCommList[0];
            this.packageBean = res.packageDetailsListData[0];
            this.varCost = res.variableCostList[0];
            this.sparesCost = res.sparesCostList[0];
            this.projectCost = res.projectCostList[0];
            this.transDetailList = res.transportationDetailList;
      
            // comercial
            this._ITOeditQoutService.section2Data =  res.savedComercialDataList2;
            this._ITOeditQoutService.section2AData = res.savedComercialDataList1;
            this._ITOeditQoutService.completecomercialdata=res.comercialDataList;//ali
            this._ITOeditQoutService.attachmentDataList = res.attachmentDataList;
            this.saveInLocal(this.ECData, this.ecBean);
          
            this.saveInLocal(this.packLocal, this.packageBean);
            this.saveInLocal(this.varCostLocal, this.varCost);
            this.saveInLocal(this.sparesLocal, this.sparesCost);
            this.saveInLocal(this.projectCostLocal, this.projectCost);
            this.saveInLocal(this.transLocal, this.transDetailList);
            this.saveInLocal(this.oneLineLoc, this.onLineBomExcel);
            ////
            ////ito_cutomer_requirements
            this.customerData = res.customerDetailsForm;
            this.saveInLocal(this.custdetails, {
              oppSeqNo: res.saveBasicDetails.opportunitySeqNum,
              custDet: res.customerDetailsForm,
              typeOfOffer: res.saveBasicDetails.typeOfOfferNm,
              typeOfCustomer:res.saveBasicDetails.typeOfCustomerNm,
              //typeOfCustomer: res.saveBasicDetails.custSapCode,
              isEndUserReq: res.saveBasicDetails.isEndUserReq,
              stateId: res.saveBasicDetails.stateId,
              //endUserType  : this.endUserDetail 
              enquiryReference:res.saveBasicDetails.enquiryReference
            });
            this._ITOcustomerDetailsService.typeOfOfferNm = res.saveBasicDetails.typeOfOfferNm;
            this._ITOcustomerDetailsService.typOfOfff = res.saveBasicDetails.typeOfOffer;
            
            //this._ITOcustomerDetailsService.typOfCust = res.saveBasicDetails.custSapCode;
            this._ITOcustomerDetailsService.typeOfCustomerNm = res.saveBasicDetails.typeOfCustomerNm;
      
            this._ITOcustomerDetailsService.isEndUserReq = res.saveBasicDetails.isEndUserReq;
           // this._ITOcustomerDetailsService.endUser = res.saveBasicDetails.endUser;
      
            this._ITOcustomerDetailsService.stateId = res.saveBasicDetails.stateId;
            this._ITOcustomerDetailsService.stateNm = res.saveBasicDetails.stateNm;
      
            // this._ITOcustomerDetailsService.customerDetailsForm.endUserName = res.saveBasicDetails.endUserName;
            // this._ITOcustomerDetailsService.customerDetailsForm.endUserAddress = res.saveBasicDetails.endUserAddress;
      
      
            this._ITOcustomerDetailsService.customerDetailsForm = res.saveBasicDetails;
            console.log(this._ITOcustomerDetailsService.customerDetailsForm);
            this.f2fQuesData = res.selectedQuestionAnswerSet;
            this._ITOcustomerRequirementService.saveBasicDet = res.saveBasicDetails;
            this._ITOcustomerRequirementService.saveBasicDet.userRoleId = this.storage.get(this.currentRoleId);
            this._ITOcustomerRequirementService.saveBasicDet.editFlag = true;
            if (res.saveBasicDetails.custCode == "DM") {
              this.custCode = true; // setting true for domestic customer
            }
            this._ITOcustomerRequirementService.saveBasicDet.modifiedById = this._ITOLoginService.loggedUserDetails;
      
            // if ((this._ITOeditQoutService.editMode) && this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'CM'
            //   || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'PR' || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'PA'
            //   || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'SBE' || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'SBR'
            //   || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'SBA') {
            //   this.enableERA = true;
            //   this.editMode = true;
            //   if (this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'CM') {
            //     this.editModebtn = false
            //   }
            //   console.log(this.enableERA)
            // }
            // else {
            //   this.enableERA = false;
            // }
            ///
            let sampleScopeBut = res.scopeOfSupplyList.map(item => item.scopeCode);
             
            console.log(sampleScopeBut);
            if(sampleScopeBut.includes('F2F')){
              this.F2F = true;
            }
            if(sampleScopeBut.includes('MECH')){
              this.MECH = true;
            }
            if(sampleScopeBut.includes('ELE')){
              this.ELE = true;
            }
            if(sampleScopeBut.includes('CI')){
              this.CI = true;
            }
            if(sampleScopeBut.includes('PHM')||sampleScopeBut.includes('SP') || sampleScopeBut.includes('SS') || sampleScopeBut.includes('QA') ||
            sampleScopeBut.includes('TP') || sampleScopeBut.includes('EL') || sampleScopeBut.includes('SSL') ||
            sampleScopeBut.includes('DR') || sampleScopeBut.includes('TD') || sampleScopeBut.includes('CD')){
              this.TECH = true
            }
            this._ITOcustomerRequirementService.sendbtnStatus(true, true, this.F2F, this.MECH, this.ELE, this.CI, this.TECH, true);
            // if( this.genInputList ==  0  || this.scopeOfSupplyList == 0){
          // this.router.navigate(['CostEstimation/ScopeOfsupplyCstEst/ScopeOfSupply']);
          //   }else{
          //   if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
          //     this.router.navigate(['/CostEstimation/Mechanical/DboMechAuxialries']);
          //   }else{
          //   if(this.F2F == true){
          //     this.router.navigate(['CostEstimation/CompleteTurbineDetails/turbineConfig']);
          //   }else if(this.MECH == true){
          //     this.router.navigate(['/CostEstimation/Mechanical/DboMechAuxialries']);
          //   }else if(this.ELE ==  true){
          //     this.router.navigate(['CostEstimation/Electrical/DBOElectrical']);
          //   }else if(this.CI == true){
          //     this.router.navigate(['CostEstimation/ControlInstrumentation/ItoControlInstrumentation']);
          //   }else if(sampleScopeBut.includes('PHM')){
          //     this.router.navigate(['CostEstimation/Techinal/ItoPerformance']);
          //   } 
        //   } 
        // }   
          // }else if (m.map((s) => {
          //   return s.scopeCode
          // }).includes('F2F' && ('ELE' || 'CI')) == true) {
          //   this._ITOcustomerRequirementService.sendbtnStatus(true, false, true, false, true, true, true);
          //   this._Router.navigate(['CostEstimation/CompleteTurbineDetails/turbineConfig']);
          // }else if (m.map((s) => {
          //   return s.scopeCode
          // }).includes('MECH' && ('ELE' || 'CI')) == true) {
          //   this._ITOcustomerRequirementService.sendbtnStatus(true, false, false, true, true, true, true);
          //   this._Router.navigate(['CostEstimation/Mechanical/DBOMechanical']);
          // }else if (m.map((s) => {
          //     return s.scopeCode
          //   }).includes('F2F') == true) {
          //     this._ITOcustomerRequirementService.sendbtnStatus(true, false, true, false, false, true, true);
          //     this._Router.navigate(['CostEstimation/CompleteTurbineDetails/turbineConfig']);
          //   }else if(m.map((s) => {
          //     return s.scopeCode
          //   }).includes('MECH') == true) {
          //     this._ITOcustomerRequirementService.sendbtnStatus(true, false, false, true, false, true, true);
          //     this._Router.navigate(['CostEstimation/Mechanical/DBOMechanical']);
          //   }else if(m.map((s) => {
          //     return s.scopeCode
          //   }).includes('ELE') == true) {
          //     this._ITOcustomerRequirementService.sendbtnStatus(true, false, false, false, true, true, true);
          //     this._Router.navigate(['CostEstimation/Electrical/DBOElectrical']);
          //   }else if(m.map((s) => {
          //     return s.scopeCode
          //   }).includes('CI') == true) {
          //     this._ITOcustomerRequirementService.sendbtnStatus(true, false, false, false, true, true, true);
          //     this._Router.navigate(['CostEstimation/Electrical/ItoControlInstrumentation']);
          //   }
          });  
        // {
        //  if(this._ITOcustomerRequirementService.editFlag){
        //     this._ITOcustomerRequirementService.editFlag = false;
          // if(this._ITOHomePageService.selectedQuot != undefined && this._ITOHomePageService.selectedQuot != ''){
          //   this._ITOHomePageService.selectedQuot = null;
          // }
      
            //this._Router.navigate(['/EditQuot']);      
        //  }
        
      // }
        }
        // }
      } else {
        this._ITOLoginService.errdisplay = true;
        this._ITOLoginService.openSuccMsg(resp.successMsg);
        this.errIcon = true;
        //alert(resp.successMsg);
      }
      this.hidespinner = true;
    })
    //   });
  }
  disableMul() {
    this.mainSave=true;

    this.enableMul = false;
  }
  enableMult() {
    this.mainSave=true;

    this.enableMul = true;
  }
  //To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}
}
