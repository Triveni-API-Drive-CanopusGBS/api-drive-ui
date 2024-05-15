import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { ITOeditQoutService } from './ito-edit-quot.service';
import { ITOHomePageService } from '../ito-home-page/ito-home-page.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOcustomerDetailsService } from '../ito-customer-details/ito-customer-details.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ITOendUserDetailsService } from '../ito-end-user-details/ito-end-user-details.service';
import { Inject } from '@angular/core';
import { ITOMyQuotPageService } from '../ito-my-quotations/ito-my-quotations.service';
import { TurbineDetails } from './ito-turbine-details';
import { ITOLoginService } from '../app.component.service';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { saveAs } from 'file-saver';
import * as fileSaver from 'file-saver';
import { ITOScopeOfSupplyService } from '../ito-scope-of-supply/ito-scope-of-supply.service';
import { ItoQuotCompleteService } from '../ito-quot-complete/ito-quot-complete.service';


@Component({
  selector: 'app-ito-edit-quot',
  templateUrl: './ito-edit-quot.component.html',
  styleUrls: ['./ito-edit-quot.component.css']
})
export class ItoEditQuotComponent implements OnInit, AfterContentChecked {
  
  message: any = '';
  succMsg: any = '';
  vailReq: any;
  temp2:string='';
  completeDialog: boolean = false;
  attachListCD: Array<any> = [];
  attachListTD: Array<any> = [];
  section2AData: Array<any> =[];
  section2Data: Array<any> = [];
  dispPR: boolean = false;
  dispVC: boolean = false;
  dispSS: boolean = false;
  dispTC: boolean = false;
  dispDR: boolean = false;
  dispPF: boolean = false;
  dispSSL: boolean = false;
  dispCD: boolean = false;
  dispEL: boolean = false;
  dispTP: boolean = false;
  dispQA: boolean = false;
  dispTD: boolean = false;
  dispPerf: boolean = false;
  dropdownddd: any;
  transpBool: boolean = false;
  errecBool: boolean = false;
  pckBool: boolean = false;
  totalCostBool: boolean = false;
  addOnCostBool: boolean = false;
  projectCostBool: boolean = false;
  sparesCostBool: boolean = false;
  variableCostBool: boolean = false;
  dboEleCostBool: boolean = false;
  dboMechCostBool: boolean = false;
  disableBtn: boolean = true;
  userList: Array<any> = [];
  public custData: any = [];
  scopeOfSupply: Array<any>;
  scopeOfSupplyList: Array<any>;
  ecBeanList: Array<any> = [];
  pkgBeanList: Array<any> = [];
  transDetailList: Array<any> = [];
  costSheetList: Array<any> = [];
  dboMechList: Array<any> = [];
  dboMechExtList: Array<any> = [];
  dboEleList: Array<any> = [];
  dboEleAuxList: Array<any> = [];
  dboCIList: Array<any> = [];
  dboCIAuxList: Array<any> = [];
  dboEleExtList: Array<any> = [];
  dboCIExtList: Array<any> = [];
  dboMechAuxList: Array<any> = [];
  f2fAddOnCostFlag: boolean= false;
  f2fAddOnList:Array<any>=[];
  totalF2fAddOnCost:number=0;
  f2fList: Array<any> = [];     // F2F Items List from one line bom
  addOnsCostList: Array<any> = [];
  submittedAddOnListCost: Array<any> = [];
  selectedAddOns: Array<any> = [];
  othersList: Array<any> = [];
  addonComps: Array<any> = [];
  hideDiv: Array<any> = [];
  varCostList: Array<any> = [];
  sparesCostList: Array<any> = [];
  projectCostList: Array<any> = [];
  newHide: boolean;
  enableOverWrite: Array<boolean> = [];
  quantity: Array<any> = [];
  quantityName: Array<any> = [];
  ExcelCost: Array<any> = [];
  maxCost: Array<any> = [];
  subType1: Array<any> = [];
  subtype2: Array<any> = [];
  makeOptions: Array<any> = [];
  remarks: Array<any> = [];
  addOnsNew: Array<any> = [];
  usersList: Array<any> = [];
  newUsersLilst: Array<any> = [];
  arry: Array<any> = [];
  subArry: Array<any> = [];
  subArry1: Array<any> = [];
  sfdcCustData : any;
  dispCompleteDialog: boolean = false;
  labelName: string; // review /approver label name
  notifyBtnStatus: string = 'none';
  custdetails: string = 'custdetails';
  endUserDetail: string = 'endUserDetail';
  customerReqrmnt: string = 'customerReq';
  generalInput: string = 'generalInputList';
  temp3: Array<any> = [];
  opportunitySeqNO: any='';
  scopeofsupp: string = 'scopeOfsup';
  ECData: string = 'ecKey';
  F2FSap: string = 'F2FSap';
  packLocal: string = 'packLocal';
  transLocal: string = 'transLocal';
  varCostLocal: string = 'varCostLocal';
  sparesLocal: string = 'sparesLocal';
  projectCostLocal: string = 'projectCostLocal';
  addOnDetails: string = 'addOnDetail';
  f2fCostData: string = 'f2fCostData';
  currentRole: string = 'selRole';
  oneLineLoc: string = 'oneLineLoc';
  currentRoleId: string = 'selRoleId';
  usersDataList: string = 'usersDataList';
  dboEleFull: string = 'dboEleFull';
  dboMechFull: string = 'dboMechFull';
  dboMechLoc: string = 'dboMech';
  dboComSecALoc: string = 'comSecA'; // local storage value
  dboPerfLoc: string = 'dboPerf';
  F2FTurbine: string = 'F2FTurbine';
  dboMechAuxLoc: string = 'dboAuxMech';
  MechExpScope: string = 'MechExpScope';
  EleExtdScope: string = 'EleExtdScope';
  EleCiExtdScope: string = 'EleCiExtdScope';
  dboEleCIAuxFull: string = 'dboEleCIAuxFull';
  dboEleCIFull: string = 'dboEleCIFull';
  dboEleAuxFull: string = 'dboEleAuxFull';
  exclusion: string = 'exclusion'; // local storage value
  quaility: string = 'quaility'; // local storage value
  scopeOf: string = 'scopeOf'; // local storage value
  supplier: string = 'supplier'; // local storage value
  tender: string = 'tender'; // local storage value
  terminal: string = 'terminal'; // local storage value
  tendrAttach: string = 'tendrAttach';//Local storaghe for tender drawing new
  clarifiAttach: string = 'clarifiAttach';// Local storage for clarifications and deviations
  attachmentDataList: any;
  hasConsultant: string;
  hasEndUser: string;
  regn: string;
  regnCd: string;
  newDev: string;
  statusQuot: string;
  //userRoleCode: string;

  CustInfo: TurbineDetails;

  saveBasicDetails: any;
  qRemarks: any;
  quotRemarks: any;
  ecBean: any;
  varCost: any;
  sparesCost: any;
  projectCost: any;
  packageBean: any;
  customerData: any;
  consultantData: any;
  endUserData: any;
  f2fQuesData: any;
  qotNumber: any;
  qotNumb: any;
  onLineBomExcel: any;
  otherCostsOneLine: any;
  quotform: any;
  f2fExcel: any;
  saveBasic: any;
  latestCProjectData: any;
  quotWorkFlowMsg: any;
  oldComments: any;
  revApprovName: any = '';

  transTotalCost: number = 0;
  multFactor: number;

  enableERA: boolean = false;
  enableRA: boolean = false;
  custCode: boolean = false;
  hideprogress: boolean = false;
  endUserAvailable: boolean = false;
  editMode: boolean = false;
  qotEdit: boolean = false;
  quotApp: boolean = false;
  quotRev: boolean = false;
  dispF2F: boolean = false;
  dispDboMech: boolean = false;
  dispDboEle: boolean = false;
  dispDboEleCI: boolean = false;
  dispSpares: boolean = false;
  editStatusDialog: boolean = false;
  costsheetSumDialog: boolean = false;
  editModebtn: boolean = false;
  enableEngDiv: boolean = false;
  dispCancel: boolean = false;
  dispPrevComments: boolean = false;
  finalAddOnCost: number = 0;
  overWrittenFinalAddOnCost: number = 0;
  typeofQuotation: string;
  filename: any;
  saveEleFilterList: Array<any> = [];
  qualitylist:Array<any> = [];
  scopeList:Array<any> = [];
  tenderList:Array<any> = [];
  terminalList:Array<any> = [];
  exclusionList:Array<any> = [];
  supplierList:Array<any> = [];
  hidespinner: boolean = false;
  hidespinner1: boolean = false;
  hidespinner2: boolean = false;
  genInputList: Array<any> =[];
  section2: string = 'section2'; // local storage value
  perfListBool: boolean = false;
  frameNm:string = '';
  capacity: number = 0;
  typeCust: string = '';
  disableComr: boolean = false;
  disableComrIcon: boolean = false;
  displaySaveAs: boolean = false;
  f2fBoolean:boolean=true;

  constructor(private _ITOMyQuotPageService: ITOMyQuotPageService, private _ITOeditQoutService: ITOeditQoutService, private _ITOHomePageService: ITOHomePageService,
    private router: Router, private route: ActivatedRoute, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    private _ITOcustomerDetailsService: ITOcustomerDetailsService, private _ITOendUserDetailsService: ITOendUserDetailsService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private _ITOLoginService: ITOLoginService,
    private ITOScopeOfSupplyService: ITOScopeOfSupplyService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ItoQuotCompleteService: ItoQuotCompleteService) {
    this.hideprogress = true;
if(this.storage.get(this.currentRole)=="QUOT_EDIT")    
{
  this.f2fBoolean=false;
}
      this.qotNumber = this._ITOHomePageService.selectedQuot.quotId;
   
      this._ITOeditQoutService.checkEdit=false;      
    //this.qotNumber = this._ITOcustomerRequirementService.saveBasicDet.quotId; 

    // setting DBO related data to null initially
    this._ITOeditQoutService.eleExtScopeList1 = [];
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
    this._ITOeditQoutService.attachmentDataList = [];
    this._ITOeditQoutService.mechExtScpList =[];
    this._ITOeditQoutService.eleCIExtScopeList = [];
    this._ITOeditQoutService.eleExtScopeList = [];
    this._ITOeditQoutService.dboDataOthers = [];
    this._ITOeditQoutService.dboEleSplAddOnList = [];
    this._ITOeditQoutService.dboEleOthers = [];
    this._ITOeditQoutService.dboEleDataAddOn = [];
    this._ITOeditQoutService.dboEleNewAddOns = [];    
    this.ITOScopeOfSupplyService.sampleScope = [];
    this._ITOeditQoutService.dboEleItmOthers = [];
    this._ITOeditQoutService.saveEleFilterList1 = [];
    this._ITOeditQoutService.saveVmsDataList = [];
    this._ITOeditQoutService.savePerformanceDataList1 = [];
    this._ITOeditQoutService.savePerformanceDataList2 = [];
    //Technical Components
    this._ITOeditQoutService.qualitiasurance = [];
    this._ITOeditQoutService.scopeofspares = [];
    this._ITOeditQoutService.tenderDraw = [];
    this._ITOeditQoutService.terminalpo = [];
    this._ITOeditQoutService.exclusionLi = [];
    this._ITOeditQoutService.subSuppliersList = [];
    this._ITOeditQoutService.otherGetDataList = [];
    this._ITOeditQoutService.savePerformanceDataList3 = [];
    this._ITOeditQoutService.cirListData = [];
    this._ITOeditQoutService.itemListData =[];
    this._ITOeditQoutService.identListData = [];
    this._ITOeditQoutService.fixedListData = [];    
    this._ITOeditQoutService.section2Data = [];  //ali  
    this._ITOeditQoutService.section2AData =[]; //Moulika
    this._ITOeditQoutService.completecomercialdata=[];//ali
    //getting data for the selected Quotation
    this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(res => {
      console.log(res);
      this.quotform = res;
      this.editModebtn = this._ITOeditQoutService.editMode;
      this.editMode = this._ITOeditQoutService.editMode;
      if(res.saveBasicDetails.statusName == "In Progress" || res.saveBasicDetails.statusName == "Canceled By Engineer"){
        this.disableComrIcon = false;
      }else{
        this.disableComrIcon = true;
      }
      if(res.saveBasicDetails.typeOfOfferNm == "Techno Commercial"){
        this.disableComr = true;
      }else{
        this.disableComr = false;
      }
      console.log(this.editModebtn)
      // status of Quotation is checked to enable editing of the data
      if ((this._ITOeditQoutService.editMode) && (res.saveBasicDetails.statusCode == 'PR' || res.saveBasicDetails.statusCode == 'PA'
        || res.saveBasicDetails.statusCode == 'IP' || res.saveBasicDetails.statusCode == 'RC' || res.saveBasicDetails.statusCode == 'SBE' || res.saveBasicDetails.statusCode == 'SBR'
        || res.saveBasicDetails.statusCode == 'SBA')) {
        this.editModebtn = true;
        // this.enableERA = false;
      } else {
        this.editModebtn = false;
      }
      if (res.oneLineBomExcel.pkgNewCostFlag) {
        this.pckBool = true;
      } else {
        this.pckBool = false;
      }
      if (res.oneLineBomExcel.ecNewCostFlag) {
        this.errecBool = true;
      } else {
        this.errecBool = false;
      }
      if (res.oneLineBomExcel.transNewCostFlag) {
        this.transpBool = true;
      } else {
        this.transpBool = false;
      }
      if (res.oneLineBomExcel.dboMechNewCostFlag) {
        this.dboMechCostBool = true;
      } else {
        this.dboMechCostBool = false;
      }
      if (res.oneLineBomExcel.dboEleNewCostFlag) {
        this.dboEleCostBool = true;
      } else {
        this.dboEleCostBool = false;
      }
      if (res.oneLineBomExcel.varNewCostFlag) {
        this.variableCostBool = true;
      } else {
        this.variableCostBool = false;
      }
      if (res.oneLineBomExcel.sparesNewCostFlag) {
        this.sparesCostBool = true;
      } else {
        this.sparesCostBool = false;
      }
      if (res.oneLineBomExcel.projectNewCostFlag) {
        this.projectCostBool = true;
      } else {
        this.projectCostBool = false;
      }
      if (res.oneLineBomExcel.addOnNewCostFlag) {
        this.addOnCostBool = true;
      } else {
        this.addOnCostBool = false;
      }
      if (res.oneLineBomExcel.f2fNewCostFlag) {
        this.totalCostBool = true;
      } else {
        this.totalCostBool = false;
      }

      this.userList = res.userDetailsList;
      this._ITOLoginService.isStandAlone = false;
      this._ITOcustomerRequirementService.quotForm = res;
      this.qotNumb = res.saveBasicDetails.quotNumber;
      this.frameNm = res.saveBasicDetails.frameName;
    this.capacity = res.saveBasicDetails.capacity;
    if(res.saveBasicDetails.custCode == "DM"){
      this.typeCust = "Domestic";
    }else{
      this.typeCust = "Export";
    }
      // this.orderStatus(res.saveBasicDetails.statusName);
      this.customerData = res.customerDetailsForm;
      this.saveInLocal(this.custdetails, {
        oppSeqNo: res.saveBasicDetails.opportunitySeqNum,
        custDet: res.customerDetailsForm,
        typeOfOffer: res.saveBasicDetails.typeOfOfferNm,
        typeOfCustomer:res.saveBasicDetails.typeOfCustomerNm,
        //typeOfCustomer: res.saveBasicDetails.custSapCode,
        isEndUserReq: res.saveBasicDetails.isEndUserReq,
        stateId: res.saveBasicDetails.stateId,
        enquiryReference: res.saveBasicDetails.enquiryReference
        //endUserType  : this.endUserDetail 
      });
      this._ITOcustomerDetailsService.typeOfOfferNm = res.saveBasicDetails.typeOfOfferNm;
      this._ITOcustomerDetailsService.typOfOfff = res.saveBasicDetails.typeOfOffer;
      this._ITOcustomerDetailsService.enquiryReference = res.saveBasicDetails.enquiryReference;
      //this._ITOcustomerDetailsService.typOfCust = res.saveBasicDetails.custSapCode;
      this._ITOcustomerDetailsService.typeOfCustomerNm = res.saveBasicDetails.typeOfCustomerNm;

      this._ITOcustomerDetailsService.isEndUserReq = res.saveBasicDetails.isEndUserReq;
     // this._ITOcustomerDetailsService.endUser = res.saveBasicDetails.endUser;

      this._ITOcustomerDetailsService.stateId = res.saveBasicDetails.stateId;
      this._ITOcustomerDetailsService.stateNm = res.saveBasicDetails.stateNm;

      // this._ITOcustomerDetailsService.customerDetailsForm.endUserName = res.saveBasicDetails.endUserName;
      // this._ITOcustomerDetailsService.customerDetailsForm.endUserAddress = res.saveBasicDetails.endUserAddress;
      this.sparesCostList = res.sparesCostList;

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
      this.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
     
      
    
      console.log(this.saveBasicDetails.statusCode);
      console.log(this.saveBasicDetails.custStatus);
      
     
      this.scopeOfSupplyList = res.scopeOfSupplyList;
      // based on scope of suplly divs has to be displayed
      this.scopeOfSupply = res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
      this.ITOScopeOfSupplyService.sampleScope=res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
      this.saveInLocal(this.scopeofsupp, this.scopeOfSupply);
      for (let s = 0; s < this.scopeOfSupply.length; s++) {
        for (let r = 0; r < this.scopeOfSupplyList.length; r++) {
          // console.log(this.scopeOfSupply[s].scopeName, this.scopeOfSupply[s].scopeCode);
          if (this.scopeOfSupply[s].ssId == this.scopeOfSupplyList[r].ssId) {

            switch (this.scopeOfSupply[s].scopeCode) {
              case "F2F": {

                this.dispF2F = true;
                break;
              }
              case "MECH": {
                this.dispDboMech = true;
                break;
              }
              case "ELE": {
                this.dispDboEle = true;
                break;
              }
              case "CI": {
                this.dispDboEleCI = true;
              }
              case "PHM": {
                this.dispPerf = true;
                break;
              }
              case "SP": {
                this.dispSpares = true;
                break;
              }
              case "TD": {
                this.dispTD = true;
                break;
              }
              case "QA": {
                this.dispQA = true;
                break;
              }
              case "TP": {
                this.dispTP = true;
                break;
              }
              case "EL": {
                this.dispEL = true;
                break;
              }
              case "CD": {
                this.dispCD = true;
                break;
              }
              case "SSL": {
                this.dispSSL = true;
                break;
              }
              case "PF": {
                this.dispPF = true;
                break;
              }
              case "DR": {
                this.dispDR = true;
                break;
              }
              case "TC": {
                this.dispTC = true;
                break;
              }
              case "SS": {
                this.dispSS = true;
                break;
              }
              case "VC": {
                this.dispVC = true;
                break;
              }
              case "PR": {
                this.dispPR = true;
                break;
              }
            }
          }
        }
      }
      console.log(this.scopeOfSupplyList);
      // storing data from DB to local storage or global variables so that it can be displayed in respective screens
      this.CustInfo = new TurbineDetails('');
      this.CustInfo.Orientation = res.saveBasicDetails.orientationType;
      this.CustInfo.capacity = res.saveBasicDetails.capacity;
      this.CustInfo.condenserType = res.saveBasicDetails.condensingTypeName;
      this.CustInfo.frames = res.saveBasicDetails.frameName;
      this.CustInfo.region = res.saveBasicDetails.region;
      this.CustInfo.turbineType = res.saveBasicDetails.typeOfTurbine;
      this.CustInfo.typeOfblade = res.saveBasicDetails.typOfBlade;
      if( res.saveBasicDetails.improvedImpulse==1)
      {
        this.CustInfo.typeOfblade="Improved Impulse";
      }
      this.CustInfo.bleedtype = res.saveBasicDetails.bleedType;
      this.CustInfo.typeOfInject = res.saveBasicDetails.typeOfInjectionNm;
      this.CustInfo.typeOfVarient = res.saveBasicDetails.typeOfVarientNm;
      this.regn = res.saveBasicDetails.region;
      this.regnCd = res.saveBasicDetails.regionCode;
      if (res.saveBasicDetails.isNewProject == 1)
        this.newDev = "YES";
      else if (res.saveBasicDetails.isNewProject == 0)
        this.newDev = "NO";
      this.multFactor = res.saveBasicDetails.percentageVariation;
      //this.CustInfo.turbineCategory=res.saveBasicDetails.typeOfTurbine;
      this.saveInLocal(this.customerReqrmnt, {
        CustInfo: this.CustInfo, RegionCode: this.regnCd, Region: this.regn,
        IsNewProject: this.newDev, multiplicationFactor: this.multFactor,typeofQuotation:res.saveBasicDetails.typeOfQuotNm
      });
      
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
      for (let r = 0; r < res.submittedAddOnList.length; r++) {
        if (res.submittedAddOnList[r].addOnCompoId < 18) {
          this.othersList.push(res.submittedAddOnList[r]);
        }
        this.selectedAddOns = res.questionsBean;
      }
      this._ITOeditQoutService.dboMechData = res.mechGeneralList;
      this._ITOeditQoutService.dboMechAuxData= res.mechAuxGeneralList;
      this._ITOeditQoutService.mechExtScpList=res.mechExtScpList;
      this._ITOeditQoutService.eleExtScopeList = res.eleExtScopeList;
      this._ITOeditQoutService.eleCIExtScopeList = res.eleCIExtScopeList;
      this._ITOeditQoutService.dboMechAuxDataAddonData=res.mechAuxGeneralListNew;
      this._ITOeditQoutService.dboDataOthers = res.quotDboMechOthersList;
      this._ITOeditQoutService.saveEleFilterList1 = res.saveEleFilterList1;
      this._ITOeditQoutService.saveVmsDataList = res.saveVmsDataList;
      this._ITOeditQoutService.savePerformanceDataList1 =res.savePerformanceDataList1;
      this._ITOeditQoutService.savePerformanceDataList2 =res.savePerformanceDataList2;
      this._ITOeditQoutService.savePerformanceDataList3 = res.savePerformanceList3;
      this._ITOeditQoutService.identListData = res.identListData;
      this._ITOeditQoutService.cirListData = res.cirListData;
      this._ITOeditQoutService.fixedListData = res.fixedListData;
      this._ITOeditQoutService.itemListData = res.itemListData;
      this._ITOeditQoutService.attachmentDataList = res.attachmentDataList;
      this._ITOeditQoutService.section2Data = res.savedComercialDataList2//ali
      this._ITOeditQoutService.section2AData = res.savedComercialDataList1;//Moulika
      this._ITOeditQoutService.completecomercialdata=res.comercialDataList;//ali
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
      if(this._ITOeditQoutService.qualitiasurance!=undefined)
      {
        this.qualitylist= this._ITOeditQoutService.qualitiasurance;
       
      }
      if(this._ITOeditQoutService.scopeofspares!=undefined)
      {
       
        this.scopeList= this._ITOeditQoutService.scopeofspares;
      
      } if(this._ITOeditQoutService.tenderDraw!=undefined)
      {
       
        this.tenderList=this._ITOeditQoutService.tenderDraw;
    
      }
      if(this._ITOeditQoutService.terminalpo!=undefined)
      {
       
        this.terminalList=this._ITOeditQoutService.terminalpo;
      
      }
      if(this._ITOeditQoutService.exclusionLi!=undefined)
      {
       
        this.exclusionList=this._ITOeditQoutService.exclusionLi;
      }
      if(this._ITOeditQoutService.subSuppliersList!=undefined)
      {
    
        this.supplierList=  this._ITOeditQoutService.subSuppliersList;
      }
      if(this._ITOeditQoutService.section2AData!=undefined){
        this.section2AData = this._ITOeditQoutService.section2AData;
      }
      if(this._ITOeditQoutService.section2Data!=undefined){
        this.section2Data = this._ITOeditQoutService.section2Data;
      }
     if(this._ITOeditQoutService.savePerformanceDataList1!=undefined || this._ITOeditQoutService.savePerformanceDataList2!= undefined ||
    this._ITOeditQoutService.savePerformanceDataList3!=undefined){
      if(this._ITOeditQoutService.savePerformanceDataList1.length != 0 || this._ITOeditQoutService.savePerformanceDataList2.length !=0 ||
        this._ITOeditQoutService.savePerformanceDataList3.length != 0){
      this.perfListBool = true;
        }
    }
    }
    if(this._ITOeditQoutService.attachmentDataList != undefined){
      this.attachmentDataList = this._ITOeditQoutService.attachmentDataList;
    }
    if(this.attachmentDataList.length>0){
      this.attachListCD = [];
      this.attachListTD = [];
      for(let m=0; m<this.attachmentDataList.length;m++){
        if(this.attachmentDataList[m].scopeNm == 'Clarifications / Deviations'){
        this.attachListCD.push(this.attachmentDataList[m]);
      }else if(this.attachmentDataList[m].scopeNm == 'Tender Drawings'){
        this.attachListTD.push(this.attachmentDataList[m]);
      }
    }
  }
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
     
      //this._ITOeditQoutService.dboEleData = res.eleGeneralList;
     
      this._ITOeditQoutService.eleExtScopeList1 = res.eleExtScopeList1;

      this._ITOeditQoutService.dboEleFilterData = res.eleGeneralSpecialList;
     // this._ITOeditQoutService.dboEleItmOthers = res.eleOtherList;
      //this._ITOeditQoutService.dboEleDataAddOn = res.addInstrList;
      if (this._ITOeditQoutService.dboEleDataAddOn.length > 0) {
        this._ITOeditQoutService.dboEleDataAddOn[0].dboEleType = "LT";  // remove this after fixing
      }
      //this._ITOeditQoutService.dboEleOthers = res.quotDboEleOthersList;
      //this._ITOeditQoutService.dboEleSplAddOnList = res.quotDboEleSplAddOnList;
      //this._ITOeditQoutService.dboEleNewAddOns = res.dboEleAddOnList;
      //F2f Data
      this.f2fExcel = res.f2fExcel;

      //F2F Turbine Details
      this._ITOeditQoutService.dboF2fData = res.f2fGeneralList;
      //this._ITOeditQoutService.dboF2fNewAddOns = res.f2fAddOnList1;
      this._ITOeditQoutService.f2fOthersItemList = res.f2fOthersItemList;
      this._ITOeditQoutService.f2fOthersSubItemList = res.f2fOthersSubItemList;
      this._ITOeditQoutService.f2fOthersSubItemTypeList = res.f2fOthersSubItemTypeList;

      this.saveInLocal(this.f2fCostData, { overwrittenPriceFlg: this.f2fExcel.overwrittenPriceFlg, overwrittenPrice: this.f2fExcel.overwrittenPrice });
      this._ITOAddOnComponentsService.getAddOnExcelPrice(res).subscribe(resExecel => {


        for (let q = 0; q < resExecel.questionsBean.length; q++) {
          for (let f = 0; f < res.questionsBean.length; f++) {
            if (resExecel.questionsBean[q].addOnValueList[0].addOnCompoId == res.questionsBean[f].addOnValueList[0].addOnCompoId) {
              this.hideDiv[q] = true;
              this.finalAddOnCost = res.submittedAddOnList[0].addOnTotal;
              if (res.submittedAddOnList[0].selectedCostFlag == 1) {
                this.overWrittenFinalAddOnCost = res.submittedAddOnList[0].selectedCost;
              }
              this.ExcelCost[q] = res.questionsBean[f].addOnValueList[0].newCompPrice;
              this.maxCost[q] = res.questionsBean[f].addOnValueList[0].excelCost;
              this.quantity[q] = res.questionsBean[f].addOnValueList[0].quantity;
              this.quantityName[q] = res.questionsBean[f].addOnValueList[0].quantityName;
              this.subType1[q] = res.questionsBean[f].addOnValueList[0].subtype1;
              this.subtype2[q] = res.questionsBean[f].addOnValueList[0].subtype2;
              this.makeOptions[q] = res.questionsBean[f].addOnValueList[0].make;
              if (res.questionsBean[f].addOnValueList[0].selectedCostFlag == 1) {
                this.enableOverWrite[q] = true;
              }
              this.remarks[q] = res.questionsBean[f].addOnValueList[0].remarks;
            }
          }
        }
        this.saveInLocal(this.addOnDetails, {
          hideDiv: this.hideDiv, ExcelCost: this.ExcelCost,
          maxCost: this.maxCost, subType1: this.subType1, subtype2: this.subtype2, makeOptions: this.makeOptions,
          quantity: this.quantity, quantityName: this.quantityName, addOndet: this.addonComps, othersAddonList: this.othersList, selAddOns: this.selectedAddOns,
          remarks: this.remarks, enableOverWrite: this.enableOverWrite, finalAddOnCost: this.finalAddOnCost, overWrittenFinalAddOnCost: this.overWrittenFinalAddOnCost
        });
      });

      this.ecBeanList = res.errectionCommList;
      this.pkgBeanList = res.packageDetailsListData;
      this.transDetailList = res.transportationDetailList;
      this.costSheetList = res.oneLineBomExcel.costSheetList;
      
      this.submittedAddOnListCost = res.submittedAddOnList;
      this.latestCProjectData = res.latestCProjectData;
      if (this.latestCProjectData.cNum) {
        this.saveInLocal(this.F2FSap, this.latestCProjectData);
      }

      this.onLineBomExcel = res.oneLineBomExcel;
   //   this.onLineBomExcel.cIExtCost= Number(this.onLineBomExcel.cIExtCost).toLocaleString('en-IN')
      this.addOnsCostList = res.oneLineBomExcel.addOnsList;
      this.dboMechList = res.oneLineBomExcel.dboMechList;
      this.dboMechAuxList = res.oneLineBomExcel.dboMechAuxList;
      this.dboMechExtList = res.oneLineBomExcel.dboMechExtList;
      this.dboEleList = res.oneLineBomExcel.dboEleList;
      this.dboEleAuxList = res.oneLineBomExcel.dboEleAuxList;
      this.dboCIList = res.oneLineBomExcel.dboCIList;
      this.dboCIAuxList = this._ITOeditQoutService.dboEleCIAuxData;
      this.dboEleExtList = res.oneLineBomExcel.dboEleExtList1;
      this.dboCIExtList = res.oneLineBomExcel.dboCIExtList;
      this.otherCostsOneLine = res.oneLineBomExcel.otherCostsBean;
     // for(let g=0; g<this.dboCIExtList.length; g++){
    //     if(this.dboCIExtList[g].price > 0){
    //     this.dboCIExtList[g].price= Number(this.dboCIExtList[g].price).toLocaleString('en-IN')
    //     }
    //   }
    //   for(let l=0;l<this.dboCIAuxList.length;l++){
    //     if(this.dboCIAuxList[l].price >0){
    //     this.dboCIAuxList[l].price= Number(this.dboCIAuxList[l].price).toLocaleString('en-IN')
    //     }
    //   }
    //   for(let a=0;a<this.dboCIList.length;a++){
    //     if( this.dboCIList[a].price > 0){
    //     this.dboCIList[a].price= Number(this.dboCIList[a].price).toLocaleString('en-IN')
    //   }
    // }
    //   for(let b=0; b<this.dboEleExtList.length;b++){
    //     if(this.dboEleExtList[b].price > 0){
    //       this.dboEleExtList[b].price=Number(this.dboEleExtList[b].price).toLocaleString('en-IN')
    //   }
    // }
    //   for(let c=0;c<this.dboEleAuxList.length;c++){
    //  if(this.dboEleAuxList[c].price > 0){
    //     this.dboEleAuxList[c].price=Number(this.dboEleAuxList[c].price).toLocaleString('en-IN')
    //  }
    //   }
    //   for(let d=0;d<this.dboEleList.length;d++){
    //     if(this.dboEleList[d].price > 0){
    //     this.dboEleList[d].price=Number(this.dboEleList[d].price).toLocaleString('en-IN');
    //   }
    // }
    //   for(let e=0;e<this.dboMechExtList.length;e++){
    //     if(this.dboMechExtList[e].price > 0){
    //     this.dboMechExtList[e].price=Number(this.dboMechExtList[e].price).toLocaleString('en-IN');
    //   }
    // }
    //   for(let f=0;f<this.dboMechAuxList.length; f++){
    //     if(this.dboMechAuxList[f].price > 0){
    //     this.dboMechAuxList[f].price=Number(this.dboMechAuxList[f].price).toLocaleString('en-IN');
    //   }
    // }
    //   for(let h=0; h<this.dboMechList.length; h++){
    //     if(this.dboMechList[h].price > 0){
    //     this.dboMechList[h].price=Number(this.dboMechList[h].price).toLocaleString('en-IN');
    //   }
    // }
    //   for(let i=0; i<this.f2fAddOnList.length;i++){
    //     if(this.f2fAddOnList[i].price > 0){
    //     this.f2fAddOnList[i].price=Number(this.f2fAddOnList[i].price).toLocaleString('en-IN');
    //   }
    // }
    //   for(let j=0;this.addOnsCostList.length; j++){
    //     if(this.addOnsCostList[j].selectedCost > 0){
    //     this.addOnsCostList[j].selectedCost=Number(this.addOnsCostList[j].selectedCost).toLocaleString('en-IN');
    //   }
    // }
    //   for(let k=0;k<this.f2fList.length;k++){
    //     if(this.f2fList[k].price > 0){
    //     this.f2fList[k].price=Number(this.f2fList[k].price).toLocaleString('en-IN');
    //   }
    // }
    //   for(let l=0;l<this.costSheetList.length;l++){
    //     if(this.costSheetList[l].price > 0){
    //     this.costSheetList[l].price= Number(this.costSheetList[l].price).toLocaleString('en-IN');
    //   }
    // }
    // if(this.onLineBomExcel.cIExtCost > 0){
    // this.onLineBomExcel.cIExtCost= Number(this.onLineBomExcel.cIExtCost).toLocaleString('en-IN');
    // }
    // if(this.onLineBomExcel.totalF2FCost > 0){
    //   this.onLineBomExcel.totalF2FCost=Number(this.onLineBomExcel.totalF2FCost).toLocaleString('en-IN');
    // }
    //   if(this.onLineBomExcel.turbineMaterialCost > 0){
    //   this.onLineBomExcel.turbineMaterialCost=Number(this.onLineBomExcel.turbineMaterialCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.shopCoverCost > 0){
    //   this.onLineBomExcel.shopCoverCost=Number(this.onLineBomExcel.shopCoverCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.addonCost > 0){
    //   this.onLineBomExcel.addonCost=Number(this.onLineBomExcel.addonCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.totalF2fAddOnCost > 0){
    //   this.onLineBomExcel.totalF2fAddOnCost=Number(this.onLineBomExcel.totalF2fAddOnCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.totalMechCost > 0){
    //   this.onLineBomExcel.totalMechCost=Number(this.onLineBomExcel.totalMechCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.dboMechCost > 0){
    //   this.onLineBomExcel.dboMechCost=Number(this.onLineBomExcel.dboMechCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.dboMechAuxCost > 0){
    //   this.onLineBomExcel.dboMechAuxCost=Number(this.onLineBomExcel.dboMechAuxCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.dboMechExtCost > 0){
    //   this.onLineBomExcel.dboMechExtCost=Number(this.onLineBomExcel.dboMechExtCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.totalDboEleCost > 0){
    //   this.onLineBomExcel.totalDboEleCost=Number(this.onLineBomExcel.totalDboEleCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.dboEleCost > 0){
    //   this.onLineBomExcel.dboEleCost=Number(this.onLineBomExcel.dboEleCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.dboEleAuxCost > 0){
    //   this.onLineBomExcel.dboEleAuxCost= Number(this.onLineBomExcel.dboEleAuxCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.dboEleExtCost > 0){
    //   this.onLineBomExcel.dboEleExtCost= Number(this.onLineBomExcel.dboEleExtCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.percentProfit > 0){
    //   this.otherCostsOneLine.percentProfit= Number(this.otherCostsOneLine.percentProfit).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.totOrderCostNetProfit > 0){
    //   this.otherCostsOneLine.totOrderCostNetProfit=Number(this.otherCostsOneLine.totOrderCostNetProfit).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.turbineSparesCost > 0){
    //   this.otherCostsOneLine.turbineSparesCost=Number(this.otherCostsOneLine.turbineSparesCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.turbineOrderBookCost > 0){
    //   this.otherCostsOneLine.turbineOrderBookCost=Number(this.otherCostsOneLine.turbineOrderBookCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.sparesNetProfit > 0){
    //   this.otherCostsOneLine.sparesNetProfit=Number(this.otherCostsOneLine.sparesNetProfit).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.orderBookingSpares > 0){
    //   this.otherCostsOneLine.orderBookingSpares=Number(this.otherCostsOneLine.orderBookingSpares).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.ovrheadTotSaleCost > 0){
    //   this.otherCostsOneLine.ovrheadTotSaleCost=Number(this.otherCostsOneLine.ovrheadTotSaleCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.ovrheadSparesCost > 0){
    //   this.otherCostsOneLine.ovrheadSparesCost=Number(this.otherCostsOneLine.totalSparesCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.totalSparesCost > 0){
    //   this.otherCostsOneLine.totalSparesCost=Number(this.otherCostsOneLine.ovrheadSparesCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.others > 0){
    //   this.otherCostsOneLine.others=Number(this.otherCostsOneLine.others).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.overRawMaterialCost > 0){
    //   this.otherCostsOneLine.overRawMaterialCost=Number(this.otherCostsOneLine.overRawMaterialCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.agencyCommCharges > 0){
    //   this.otherCostsOneLine.agencyCommCharges=Number(this.otherCostsOneLine.agencyCommCharges).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.engineCharges > 0){
    //   this.otherCostsOneLine.engineCharges=Number(this.otherCostsOneLine.engineCharges).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.dboContigency > 0){
    //   this.otherCostsOneLine.dboContigency=Number(this.otherCostsOneLine.dboContigency).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.salesExpenses > 0){
    //   this.otherCostsOneLine.salesExpenses=Number(this.otherCostsOneLine.salesExpenses).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.turbineContigency > 0){
    //   this.otherCostsOneLine.turbineContigency=Number(this.otherCostsOneLine.turbineContigency).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.travelExpenses > 0){
    //   this.otherCostsOneLine.travelExpenses=Number(this.otherCostsOneLine.travelExpenses).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.insurance > 0){
    //   this.otherCostsOneLine.insurance=Number(this.otherCostsOneLine.insurance).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.intrestCharges > 0){
    //   this.otherCostsOneLine.intrestCharges=Number(this.otherCostsOneLine.intrestCharges).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.warrantyCost > 0){
    //   this.otherCostsOneLine.warrantyCost=Number(this.otherCostsOneLine.warrantyCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.ovrheadSaleCost > 0){
    //   this.otherCostsOneLine.ovrheadSaleCost=Number(this.otherCostsOneLine.ovrheadSaleCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.bankingCharges2 > 0){
    //   this.otherCostsOneLine.bankingCharges2=Number(this.otherCostsOneLine.bankingCharges2).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.bankingCharges1 > 0){
    //   this.otherCostsOneLine.bankingCharges1=Number(this.otherCostsOneLine.bankingCharges1).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.totOrderCost > 0){
    //   this.otherCostsOneLine.totOrderCost=Number(this.otherCostsOneLine.totOrderCost).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.ldforLateDelivery > 0){
    //   this.otherCostsOneLine.ldforLateDelivery=Number(this.otherCostsOneLine.ldforLateDelivery).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.ldPerformance > 0){
    //   this.otherCostsOneLine.ldPerformance=Number(this.otherCostsOneLine.ldPerformance).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.contigencyGeneral > 0){
    //   this.otherCostsOneLine.contigencyGeneral=Number(this.otherCostsOneLine.contigencyGeneral).toLocaleString('en-IN');
    //   }
    //   if(this.otherCostsOneLine.totalVariableCost > 0){
    //   this.otherCostsOneLine.totalVariableCost=Number(this.otherCostsOneLine.totalVariableCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.cIAuxCost > 0){
    //   this.onLineBomExcel.cIAuxCost=Number(this.onLineBomExcel.cIAuxCost).toLocaleString('en-IN');
    //   }
    //   if(this.onLineBomExcel.cICost > 0){
    //  this.onLineBomExcel.cICost=Number(this.onLineBomExcel.cICost).toLocaleString('en-IN');
    //   }
    //  if(this.onLineBomExcel.totalDboCiCost > 0){
    //   this.onLineBomExcel.totalDboCiCost=Number(this.onLineBomExcel.totalDboCiCost).toLocaleString('en-IN');
    //  }
    
      this.varCostList = res.variableCostList;
      this.sparesCostList = res.sparesCostList;
      this.projectCostList = res.projectCostList;
      this.f2fAddOnCostFlag=res.oneLineBomExcel.f2fAddOnCostFlag;
      this.f2fAddOnList=res.oneLineBomExcel.f2fAddOnList;
      this.totalF2fAddOnCost=res.oneLineBomExcel.totalF2fAddOnCost;
      this.varCost = res.variableCostList[0];
      this.sparesCost = res.sparesCostList[0];
      this.projectCost = res.projectCostList[0];
      this.f2fList = res.oneLineBomExcel.f2fList;
      console.log(this.varCost)
      console.log(this.projectCost)
      this.ecBean = res.errectionCommList[0];
      this.saveInLocal(this.ECData, this.ecBean);
      this.packageBean = res.packageDetailsListData[0];
      this.saveInLocal(this.packLocal, this.packageBean);
      this.saveInLocal(this.varCostLocal, this.varCost);
      this.saveInLocal(this.sparesLocal, this.sparesCost);
      this.saveInLocal(this.projectCostLocal, this.projectCost);
      this.saveInLocal(this.transLocal, this.transDetailList);
      this.saveInLocal(this.oneLineLoc, this.onLineBomExcel);
      for (let t = 0; t < this.costSheetList.length; t++) {
        if (this.costSheetList[t].categoryDetCode == "Transportation Cost") {
          this.transTotalCost = this.costSheetList[t].price;
        }
      }

      // this._ITOeditQoutService.fetchUserData().subscribe(resUsr => {
      // this.usersList = resUsr.userDetailsList;

      // this is used for quotation work flow
      this.usersList = this.storage.get(this.usersDataList)
      console.log(this.usersList);
      console.log(this.storage.get(this.currentRole))
      switch (this.storage.get(this.currentRole)) {
        case "QUOT_EDIT": {
          this.qotEdit = true;
          // this.userRoleCode = "CREATER";
          this.enableRA = true;
          this.labelName = "Please select the Reviewer";
          if (this._ITOeditQoutService.editMode) {
            this.enableEngDiv = true;
          }
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "QUOT_REVIWER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.saveBasicDetails.loggedInUserCode = 0;
                this.saveBasicDetails.statusId = 1;
                this.saveBasicDetails.editFlag = false;
                this.saveBasicDetails.statusFlag = true;
                this.saveBasicDetails.submitFlag = true;
                this.saveBasicDetails.completeFlag = false;
              }
            }

          }
          console.log(this.newUsersLilst)
          break;
        }
        case "QUOT_APPROVER": {
          this.quotApp = true;
          //  this.userRoleCode = "APPROVER";
          this.saveBasicDetails.loggedInUserCode = 2;
          this.saveBasicDetails.editFlag = false;
          this.saveBasicDetails.statusFlag = true;
          this.saveBasicDetails.submitFlag = true;
          this.saveBasicDetails.completeFlag = false;
          this.saveBasicDetails.assignedTo = res.scopeOfSupplyList[0].createdById;
          break;

        }
        case "QUOT_REVIWER": {
          //  this.userRoleCode = "REVIEWER";
          this.quotRev = true;
          this.enableRA = true;
          this.saveBasicDetails.completeFlag = false;
          this.labelName = "Please select the Approver";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "QUOT_APPROVER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.saveBasicDetails.loggedInUserCode = 1;

                this.saveBasicDetails.editFlag = false;
                this.saveBasicDetails.statusFlag = true;
                this.saveBasicDetails.submitFlag = true;
                this.saveBasicDetails.completeFlag = false;
              }
            }
          }
          break;
        }
        default: {

          break;
        }
      }
      this.hideprogress = false;
      //   })

    })

  }

  ngOnInit() {

    this.arry = [
      { name: "userIndialog", value: false },
      { name: "custInfoDialog", value: false },
      { name: "custReqDialog", value: false },
      { name: "scopeOfSupDialog", value: false },
      { name: "GIDialog", value: false },
      { name: "f2fSapNewDialog", value: false },
      { name: "turbineDetDialog", value: true },
      { name: "estimateDialog", value: true },
      { name: "eleMainDialog", value: true},
      { name: "ciMainDialog", value: true},
      { name: "techMainDialog", value: true},
      { name: "comrMainDialog", value: true},
      { name: "costsheetSumDialog", value: false }
    ]
    this.subArry = [
      { name: "dboMechDialog", value: false },
      { name: "dboMechAuxDialog", value: false },
      { name: "dboEleDialog", value: false },
      { name: "errAndComDialog", value: false },
      { name: "transDialog", value: false },
      { name: "packgDialog", value: false },
      { name: "varCostDialog", value: false },
      { name: "sparesDialog", value: false },
      { name: "projectCostDialog", value: false },
    ]
    this.subArry1 = [
      { name: "f2fSapDialog", value: false },
      { name: "f2fExcelDialog", value: false },
      { name: "addOnCompdialog", value: false }
    ]
    this._ITOLoginService.dialogMsgApp = false;
    // this.storage.remove(this.dboEleFull);
    // this.storage.remove(this.dboMechFull);
    // this.storage.remove(this.dboMechLoc);
    // this.storage.remove(this.F2FTurbine);    
    // this.storage.remove(this.dboMechAuxLoc);

    this.storage.remove(this.customerReqrmnt);
    this.storage.remove(this.endUserDetail);
    this.storage.remove(this.custdetails);
    this.storage.remove(this.scopeofsupp);
    this.storage.remove(this.addOnDetails);
    this.storage.remove(this.packLocal);
    this.storage.remove(this.transLocal);
    this.storage.remove(this.ECData);
    this.storage.remove(this.sparesLocal);
    this.storage.remove(this.varCostLocal);
    this.storage.remove(this.projectCostLocal);
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
    this.storage.remove(this.oneLineLoc);
    this.storage.remove(this.dboEleFull);
    this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.dboEleAuxFull);
    this.storage.remove(this.f2fCostData);
    this.storage.remove(this.F2FSap);
    this.storage.remove(this.F2FTurbine);
    this.storage.remove(this.MechExpScope);
    this.storage.remove(this.EleCiExtdScope);
    this.storage.remove(this.EleExtdScope);
    this.storage.remove(this.dboMechAuxLoc);
    this.storage.remove(this.generalInput);
    this.storage.remove(this.clarifiAttach);
    this.storage.remove(this.tendrAttach);
    this.storage.remove(this.section2);//ali

  }

  ngAfterContentChecked() {
    if ((this._ITOeditQoutService.editMode) && (this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'CM'
      || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'PR' || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'PA'
      || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'SBE' || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'SBR'
      || this._ITOcustomerRequirementService.saveBasicDet.statusCode == 'SBA')) {
      this.enableERA = true;
      console.log(this.enableERA)
    }
    else {
      this.enableERA = false;
    }
  }

  downloadDoc(){
    console.log(this.qotNumber);
    console.log(this.quotform);
    this.hidespinner = true;
    this._ITOeditQoutService.getWord(this.quotform).subscribe(reponn => {
      console.log(reponn);
      const blob = new Blob([reponn], {type: 'text/docx'});
      if (reponn.type.includes('docx')){
        this.filename = this.qotNumber+" - "+"Technical Offer.docx";
      }
    fileSaver.saveAs(blob, this.filename);
    this.hidespinner = false;
    });
    
  }

  comrDownload(){
    console.log(this.qotNumber);
    console.log(this.quotform);
    this.hidespinner = true;
    this._ITOeditQoutService.getComercialWord(this.quotform).subscribe(reponn => {
      console.log(reponn);
      const blob = new Blob([reponn], {type: 'text/docx'});
      if (reponn.type.includes('docx')){
        this.filename = this.qotNumber+" - "+"Comercial Offer2A.docx";
      }
    fileSaver.saveAs(blob, this.filename);
    this.hidespinner = false;
    });
    
  }

  comrDownloadNew(){
    console.log(this.qotNumber);
    console.log(this.quotform);
    this.hidespinner = true;
    this._ITOeditQoutService.getComercialWordNew(this.quotform).subscribe(reponn => {
      console.log(reponn);
      const blob = new Blob([reponn], {type: 'text/docx'});
      if (reponn.type.includes('docx')){
        this.filename = this.qotNumber+" - "+"Comercial Offer2B.docx";
      }
    fileSaver.saveAs(blob, this.filename);
    this.hidespinner = false;
    });
    
  }
  assignedUser(assigne) {
    console.log(assigne);
    this.saveBasicDetails.assignedTo = assigne;
  }

  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.custData[key] = this.storage.get(key);
  }
  customerInfoTab(){
    console.log(this._ITOcustomerRequirementService.quotForm)
    this.saveInLocal(this.custdetails, {
      oppSeqNo: this._ITOcustomerRequirementService.quotForm.saveBasicDetails.opportunitySeqNum,
      custDet:this._ITOcustomerRequirementService.quotForm.customerDetailsForm,
      typeOfOffer: this._ITOcustomerRequirementService.quotForm.saveBasicDetails.typeOfOfferNm,
      
      //typOfCust: this._ITOcustomerRequirementService.quotForm.saveBasicDetails.custSapCode,
      typeOfCustomer: this._ITOcustomerRequirementService.quotForm.saveBasicDetails.typeOfCustomerNm,
      
      isEndUserReq: this._ITOcustomerRequirementService.quotForm.saveBasicDetails.isEndUserReq,
      //endUser: this._ITOcustomerRequirementService.quotForm.saveBasicDetails.endUser,

      stateId:this._ITOcustomerRequirementService.quotForm.saveBasicDetails.stateId,
      stateNm:this._ITOcustomerRequirementService.quotForm.saveBasicDetails.stateNm,
      enquiryReference:this._ITOcustomerRequirementService.quotForm.saveBasicDetails.enquiryReference
      // endUserName:this._ITOcustomerRequirementService.quotForm.saveBasicDetails.endUserName,
      // endUserAddress:this._ITOcustomerRequirementService.quotForm.saveBasicDetails.endUserAddress

      });
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['/CustomerDetails']);
  }
  customerReqTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['/CustomerRequirement']);
    // this.router.navigate(['/CostEstimation/CustomerInformation']);
  }
  SOSTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['ScopeOfSupply']);
  }
  GITab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['GeneralInputs']);
  }

  f2fSapNewTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['ItoF2fTurbineDetails']);
  }

  turbineConfigTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['/CostEstimation/CompleteTurbineDetails']);
    // this.router.navigate(['/AddOnComponents']);
  }

  erecCommTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['/ErrectionCommision']);
  }
  getPerf() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['/ItoPerformance']);
  }

  transTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['transport']);
  }

  pkgTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['packgFrwrd']);
  }
  varCostTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['variableCost']);
  }
  sparesTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['ScopeOfSpares']);
  }
  comSec1Tab(){
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['CommercialSec1']);
  }
  comSec2Tab(){
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['CommercialSec2']);
  }
  projectCostTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['quotComplt']);
  }

  F2FSapTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['turbineConfig']);
  }
  F2FTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['SupplyChain']);
  }
  addOnTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['AddOnComponents']);
  }
  dboMechTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['DBOMechanical']);
  }
  dboMechAuxTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['DboMechAuxialries']);
  }
  dboMechAuxExtTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['MechExtendedScope']);
  }
  
  dboEleTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['DBOElectrical']);
  }
  dboEleAuxTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['DboEleAuxialries']);
  }
  dboCITab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['ItoControlInstrumentation']);
  }
  dboCIAuxTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['ItoControlInstAux']);
  }

  dboCIExtTab(){
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['DboEleCiextdScope']);
  }

  dboEleExtTab(){
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['DboEleExtdScope']);
  }
  quality(){
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['QualityAssurance']);
  }
  // spares()
  // {
  //   console.log(this._ITOcustomerRequirementService.quotForm)
  //   this._ITOcustomerRequirementService.editFlag = true;
  //   this.router.navigate(['ScopeOfSpares']);
  
  // }
  Clarifi(){
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['ClarificationsDeviation']);
  }
  tendDr(){
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['TenderDrawingNew']);
  }
  exclusion1()
  {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['ExclusionList']);
  
  }
  terminal1()
  {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['TerminalPoints']);
  }
  tender1()
  {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['DrawingDocumentation']);
  }
  supplier1()
  {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['SubSupplierList']);
  }

  export() {
    this.hidespinner = true;
    this._ITOeditQoutService.quotExportPdf(this.quotform).subscribe(res => {
      console.log(res);
      saveAs(res, "CostSheet.pdf");
      this.hidespinner = false;
    })
  }

  
  addOnPdf() {
    this.hidespinner = true;
    console.log(this.quotform);
    this._ITOeditQoutService.quotExportAddOnPdf(this.quotform).subscribe(res => {
      console.log(res);
      saveAs(res, "AddOnCost.pdf");
      this.hidespinner = false;
    })
  }

  detailedpdf() {
    this.hidespinner = true;
    this._ITOeditQoutService.quotExportPdfdet(this.quotform).subscribe(res => {
      console.log(res);
      saveAs(res, "InputReport.pdf");
      this.hidespinner = false;
    })
  }
    gettechReport() {
      this.hidespinner = true;
      this._ITOeditQoutService.getTechReport(this.quotform).subscribe(res => {
        console.log(res);
        saveAs(res, "TechnialReportPdf.pdf");
        this.hidespinner = false;
      })
  
    }
    saveAsfn() {
      this.displaySaveAs = true;
  
    }
    closeRef(){
      this.displaySaveAs = false;
      // window.location.reload();
    }
   
  // clone function
  saveAsForm(fomrValue) {
    this.hidespinner2 = true;
    console.log(fomrValue);
    this._ITOLoginService.dialogMsgVal = '';
    this.opportunitySeqNO = fomrValue.opportunitySeqNO;
    this._ITOcustomerDetailsService.getCustData(this.opportunitySeqNO).subscribe(res => {
      console.log(res); 
      if(res.successCode == 0) {
        this.sfdcCustData = res;
        if(res.custName != null){
          if(this.sfdcCustData.custType != "Export" && this.sfdcCustData.custType != "Domestic"){
            this._ITOLoginService.dialogMsgApp = true;
            this._ITOLoginService.dialogMsgVal = "Customer Type is not defined in Sfdc";
          }else{
            if(this.sfdcCustData.custType == "Domestic" && this.sfdcCustData.oppContactStateName == null){
              this._ITOLoginService.dialogMsgApp = true;
              this._ITOLoginService.dialogMsgVal = "State is not defined in sfdc";
            }
            if(this.sfdcCustData.isEndUserAvailable == 'No' && this.sfdcCustData.endUserCustType != "Export" && this.sfdcCustData.endUserCustType != "Domestic"){
              this._ITOLoginService.dialogMsgApp = true;
              this._ITOLoginService.dialogMsgVal ="End User Customer type is not defined in sfdc";
            }
          }
        }else{   
            if(res.oppurtunitySeqNo == null){
              this._ITOLoginService.dialogMsgApp = true;
  this._ITOLoginService.dialogMsgVal ="Customer Details are not available";
              }else{
                this._ITOLoginService.dialogMsgApp = true;
                this._ITOLoginService.dialogMsgVal = "Incorrect customer Details;"
              }
          }
         
       if(this._ITOLoginService.dialogMsgVal == ""){
     
    this.quotform.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
     // capturing the values fromUI as a form
     this.quotform.saveBasicDetails.modifiedById = this._ITOLoginService.loggedUserDetails;
     this.quotform.saveBasicDetails.inQuotId = this.qotNumber;
     this.quotform.saveBasicDetails.assignedTo = this._ITOLoginService.loggedUserDetails;
     this.quotform.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
     this.quotform.saveBasicDetails.opportunitySeqNum= fomrValue.opportunitySeqNO;
     
     this.quotform.saveBasicDetails.regionCode = fomrValue.opportunitySeqNO.substring(0,3);
     this.quotform.customerDetailsForm.custName= this.sfdcCustData.custName;
     this.quotform.customerDetailsForm.oppName= this.sfdcCustData.oppName;
     this.quotform.customerDetailsForm.oppContactName= this.sfdcCustData.oppContactName;
     this.quotform.customerDetailsForm.oppContactEmail= this.sfdcCustData.oppContactEmail;
     this.quotform.customerDetailsForm.oppContactPhone= this.sfdcCustData.oppContactPhone;
     this.quotform.customerDetailsForm.oppContactAddress= this.sfdcCustData.oppContactAddress;
     this.quotform.customerDetailsForm.oppContactStateName= this.sfdcCustData.oppContactStateName;
     this.quotform.customerDetailsForm.custType= this.sfdcCustData.custType;
     this.quotform.customerDetailsForm.isEndUserAvailable= this.sfdcCustData.isEndUserAvailable;
     this.quotform.customerDetailsForm.endUserCustType= this.sfdcCustData.endUserCustType;
     this.quotform.customerDetailsForm.endUserName= this.sfdcCustData.endUserName;
     this.quotform.customerDetailsForm.endUserStateName= this.sfdcCustData.endUserStateName;
               
    console.log(this._ITOcustomerRequirementService.saveBasicDet)
    this._ITOMyQuotPageService.saveAsN(this.quotform).subscribe(resSaveAs => {
      this.hidespinner2= false;
      if (resSaveAs.successCode == 0) {
        this._ITOLoginService.dialogMsgApp = true;
        this._ITOLoginService.dialogMsgVal = 'Quotation is saved as QUOT NUMBER:' + ' ' + resSaveAs.saveBasicDetails.quotNumber;
      }
      else {
        this._ITOLoginService.dialogMsgApp = true;
        this._ITOLoginService.dialogMsgVal = resSaveAs.successMsg;
      }    
  });
}
}else{
  this._ITOLoginService.dialogMsgApp = true;
  this._ITOLoginService.dialogMsgVal = " Please enter valid Opportunity Sequence Number";
}
    });
  }

  assignQuotForm(assignQuot) {
    // window.close();
    this.hidespinner1 = true;
    console.log(assignQuot)
    this.saveBasicDetails.remarks = this.qRemarks;
    console.log(this.saveBasicDetails)
    this._ItoQuotCompleteService.quotWorkFlow(this.saveBasicDetails).subscribe(resp1 => {
      console.log(resp1);
      this.saveBasicDetails.statusName = resp1.statusName;
      this.editStatusDialog = true;
      this.quotWorkFlowMsg = resp1.successMsg;
      this.hidespinner1 = false;
    })
  }

  assignBack() {
    this.saveBasicDetails.editFlag = false;
    this.saveBasicDetails.statusFlag = false;
    this.saveBasicDetails.submitFlag = false;
    this.saveBasicDetails.completeFlag = false;
    this.saveBasicDetails.statusCode = 'RC';
    this.saveBasicDetails.remarks = this.quotRemarks;
    this._ItoQuotCompleteService.quotWorkFlow(this.saveBasicDetails).subscribe(resp5 => {
      console.log(resp5);
      this.saveBasicDetails.statusName = resp5.statusName;
      this.editStatusDialog = true;
      this.quotWorkFlowMsg = resp5.successMsg;
    })
  }

  assignQuotToOthers() {
    this.saveBasicDetails.editFlag = false;
    this.saveBasicDetails.statusFlag = false;
    this.saveBasicDetails.submitFlag = false;
    this.saveBasicDetails.completeFlag = false;
    // this.router.navigate(['MyQuot']);
    if (this.statusQuot == 'Won') {
      this.saveBasicDetails.statusCode = 'WN';
    } else if (this.statusQuot == 'Lost') {
      this.saveBasicDetails.statusCode = 'LS';
    } else if (this.statusQuot == 'Revise') {
      this.saveBasicDetails.statusCode = 'RV';
    }
    this.saveBasicDetails.remarks = this.quotRemarks;
    console.log(this.saveBasicDetails)
    this._ItoQuotCompleteService.quotWorkFlow(this.saveBasicDetails).subscribe(resp2 => {
      console.log(resp2);
      this.saveBasicDetails.statusName = resp2.statusName;
      this.editStatusDialog = true;
      this.quotWorkFlowMsg = resp2.successMsg;
    })
  }

  closeQuotation() {
    this.dispCancel = true;
  }

  proceedCancel() {
    this.dispCancel = false;
    this.saveBasicDetails.editFlag = false;
    this.saveBasicDetails.statusFlag = false;
    this.saveBasicDetails.submitFlag = true;
    this.saveBasicDetails.completeFlag = false;
    this.saveBasicDetails.statusCode = 'CNL';
    this.saveBasicDetails.remarks = this.quotRemarks;
    this._ItoQuotCompleteService.quotWorkFlow(this.saveBasicDetails).subscribe(resp3 => {
      console.log(resp3);
      this.saveBasicDetails.statusName = resp3.statusName;
      this.editStatusDialog = true;
      this.quotWorkFlowMsg = resp3.successMsg;
    })
  }

  submitToCust() {
    this.saveBasicDetails.editFlag = false;
    this.saveBasicDetails.statusFlag = false;
    this.saveBasicDetails.submitFlag = false;
    this.saveBasicDetails.completeFlag = false;
    this.saveBasicDetails.statusCode = 'SC';
    this.saveBasicDetails.remarks = this.quotRemarks;
    this._ItoQuotCompleteService.quotWorkFlow(this.saveBasicDetails).subscribe(resp4 => {
      console.log(resp4);
      this.saveBasicDetails.statusName = resp4.statusName;
      this.editStatusDialog = true;
      this.quotWorkFlowMsg = resp4.successMsg;
    })
  }

  checkRemarks() {
    console.log(this.quotRemarks)
    console.log(this.quotRemarks.trim())
    if (this.quotRemarks.trim() == null || this.quotRemarks.trim() == "") {
      this.disableBtn = true;
    } else {
      this.disableBtn = false;
    }
  }

  closeEditDialog() {
    this.editStatusDialog = false;
    this.router.navigate(['MyQuot']);
  }
  closeCancelDialog() {
    this.dispCancel = false;
  }
  userInfo() {
    this.collapseDivs("userIndialog");
  }
  customerInfo() {
    this.collapseDivs("custInfoDialog");
  }

  customerReq() {
    this.collapseDivs("custReqDialog")
  }
  scopeOfSupInfo() {
    this.collapseDivs("scopeOfSupDialog")
  }
  generalInputsInfo(){
    this.collapseDivs("GIDialog")
  }
  f2fSapNewInfo(){
    this.collapseDivs("f2fSapNewDialog")
  }

  f2fSap() {
    this.collapseSubAryDivs("f2fSapDialog");
  }
  f2fEx() {
    this.collapseSubAryDivs("f2fExcelDialog");

  }
  addOnInfo() {
    this.collapseSubAryDivs("addOnCompdialog");
  }
  estimateInfo() {
    this.collapseDivs("comrMainDialog");
  }
  turbineDetTab() {
    this.collapseDivs("turbineDetDialog");
  }
  mechMainTab(){
    this.collapseDivs("estimateDialog");
  }
  eleMainTab(){
    this.collapseDivs("eleMainDialog");
  }
  ciMainTab(){
    this.collapseDivs("ciMainDialog");
  }
  techMainTab(){
    this.collapseDivs("techMainDialog");
  }
  dboMechInfo() {
    this.collapseSubDivs("dboMechDialog");
  }
  dboMechAuxInfo() {
    this.collapseSubDivs("dboMechAuxDialog");
  }
  dboEleInfo() {
    this.collapseSubDivs("dboEleDialog");
  }
  dboEleAuxInfo()
  {
    this.collapseSubDivs("dboEleAuxDialog");
  }
  errAndComInfo() {
    this.collapseSubDivs("errAndComDialog");
  }
  transInfo() {
    this.collapseSubDivs("transDialog");
  }
  packgInfo() {
    this.collapseSubDivs("packgDialog");
  }
  varCostInfo() {
    this.collapseSubDivs("varCostDialog");
  }
  sparesInfo() {
    this.collapseSubDivs("sparesDialog");
  }
  projectCostInfo() {
    this.collapseSubDivs("projectCostDialog");
  }
  costSheetSumInfo() {
    this.collapseDivs("costsheetSumDialog");
  }

  collapseDivs(val) {
    console.log(val)
    for (let s = 0; s < this.arry.length; s++) {
      if (this.arry[s].name == val) {
        this.arry[s].value = !this.arry[s].value;
      }
      else {
        this.arry[s].value = true;
        this.arry[5].value = false;
      }
    }

  }

  collapseSubDivs(val) {
    console.log(val)
    for (let s = 0; s < this.subArry.length; s++) {
      if (this.subArry[s].name == val) {
        this.subArry[s].value = !this.subArry[s].value;
      }
      else {
        this.subArry[s].value = false;
      }
    }
  }

  collapseSubAryDivs(val) {
    console.log(val)
    for (let s = 0; s < this.subArry1.length; s++) {
      if (this.subArry1[s].name == val) {
        this.subArry1[s].value = !this.subArry1[s].value;
      }
      else {
        this.subArry1[s].value = false;
      }
    }
  }
  getPrevComments() {
    //this.saveBasicDetails.scopeCode = this.userRoleCode;
    this.saveBasicDetails.groupCode = "WORKFLOW";
    this._ITOAddOnComponentsService.getQuotRemarks(this.saveBasicDetails).subscribe(prevComRes => {
      console.log(prevComRes)
      if (prevComRes.remarksList.length > 0) {
        this.dispPrevComments = true;
        this.oldComments = prevComRes.remarksList;
      } else {
        this._ITOLoginService.openSuccMsg("No Previous Comments found");
      }
    });
  }
  proceedComplete() {
    this.dispCompleteDialog = true;
    this.hidespinner =true;
  }

  closeDialog() {
    this.dispPrevComments = false;
    this.oldComments = null;
  }
  closeDialog1() {
    this.dispCompleteDialog = false;
    this.hidespinner = false;
  }

  quotWorkFlow() {
    this.dispCompleteDialog = false;
    this.hidespinner = true;
    this._ItoQuotCompleteService.getScopeOfSupStatus(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(finscopeRes => {
      console.log(finscopeRes);
      let scoppeOfSupList = '';
      let rem = finscopeRes.scopeOfSupplyStatusList.filter((x) => {
        return x.status != "Completed";
      })
      console.log(rem)
      for (let g = 0; g < rem.length; g++) {
        scoppeOfSupList = rem[g].scopeName + ',' + scoppeOfSupList;
        console.log(scoppeOfSupList);
      }
      console.log(this._ITOcustomerRequirementService.saveBasicDet.quotId);
      this._ITOeditQoutService.getValidateFinalCost(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(qtreep => {
        console.log(qtreep);
        this.vailReq = qtreep;
        this.hidespinner = false;
        if(this.vailReq.successCode == 0){
        // if (rem.length == 0) {
          this.saveBasic = this._ITOcustomerRequirementService.saveBasicDet;
          this.saveBasic.quotStatusFlg = 1;
          this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
          this._ItoQuotCompleteService.quotStatusComplete(this.saveBasic).subscribe(quotresp => {
            console.log(quotresp)
            if (quotresp.successCode == 0) {
              this._ITOLoginService.openSuccMsg(quotresp.successMsg);
              this.router.navigate(['MyQuot']);
            } else {
              this._ITOLoginService.openSuccMsg(quotresp.successMsg);
            }
          })
        }else{
          this.succMsg = '';
          this.message = '';
          
          this.succMsg = this.vailReq.successMsg;
           
            let ReqList =  this.vailReq.dropDownColumnvalues.f2fCacheList.CompleteAndExit.map(item => item.genType); 
                
            console.log(ReqList)
            for(let  k = 0; k<ReqList.length; k++){
             this.message = ReqList[k] + ',' + this.message;
            console.log(this.message);
            }
          
          // this.message = this.vailReq.successMsg;
          this.disableComrIcon = false;
          this.completeDialog = true;
        }
        this.hidespinner = false;
      })
      
      
      // }
      // else {
      //   this._ITOLoginService.openSuccMsg("Please Save Costs for " + scoppeOfSupList + " to proceed Quotation WorkFlow.");
      // }

    })
  }
  closeDial(){
    this.completeDialog = false;
  }
}
