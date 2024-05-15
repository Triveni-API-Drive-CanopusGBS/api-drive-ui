import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { ITOViewQuotService } from './ito-view-quot.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOHomePageService } from '../ito-home-page/ito-home-page.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOcustomerDetailsService } from '../ito-customer-details/ito-customer-details.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ITOendUserDetailsService } from '../ito-end-user-details/ito-end-user-details.service';
import { Inject } from '@angular/core';
import { TurbineDetails } from '../ito-edit-quot/ito-turbine-details';
import { ITOLoginService } from '../app.component.service';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { saveAs } from 'file-saver';
import { ItoQuotCompleteService } from '../ito-quot-complete/ito-quot-complete.service';
import * as fileSaver from 'file-saver';

@Component({
  selector: 'app-ito-view-quot',
  templateUrl: './ito-view-quot.component.html',
  styleUrls: ['./ito-view-quot.component.css']
})
export class ItoViewQuotComponent implements OnInit, AfterContentChecked {


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
  dboEleList: Array<any> = [];
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

  currentRoleId: string = 'selRoleId';
  dboEleFull: string = 'dboEleFull';
  dboMechFull: string = 'dboMechFull';
  dboMechLoc: string = 'dboMech';
  dboComSecALoc: string = 'comSecA'; // local storage value
  dboPerfLoc: string = 'dboPerf';
  dboEleCIAuxFull: string = 'dboEleCIAuxFull';
  dboEleCIFull: string = 'dboEleCIFull';
  dboEleAuxFull: string = 'dboEleAuxFull';
  MechExpScope: string = 'MechExpScope';
  EleExtdScope: string = 'EleExtdScope';
  EleCiExtdScope: string = 'EleCiExtdScope';
  exclusion: string = 'exclusion'; // local storage value
  quaility: string = 'quaility'; // local storage value
  scopeOf: string = 'scopeOf'; // local storage value
  supplier: string = 'supplier'; // local storage value
  tender: string = 'tender'; // local storage value
  terminal: string = 'terminal'; // local storage value
  tendrAttach: string = 'tendrAttach';//Local storaghe for tender drawing new
  clarifiAttach: string = 'clarifiAttach';// Local storage for clarifications and deviations
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
  dispSpares: boolean = false;
  costsheetSumDialog: boolean = false;
  editModebtn: boolean = false;
  enableEngDiv: boolean = false;
  dispPrevComments: boolean = false;
  finalAddOnCost: number = 0;
  overWrittenFinalAddOnCost: number = 0;
  hidespinner: boolean = false;
  filename: any;


  constructor(private _ITOViewQuotService: ITOViewQuotService, private _ITOHomePageService: ITOHomePageService,
    private router: Router, private route: ActivatedRoute, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    private _ITOcustomerDetailsService: ITOcustomerDetailsService, private _ITOendUserDetailsService: ITOendUserDetailsService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private _ITOLoginService: ITOLoginService,
    private _ITOeditQoutService: ITOeditQoutService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ItoQuotCompleteService: ItoQuotCompleteService) {
    this.hideprogress = true;
    this.qotNumber = this._ITOHomePageService.selectedQuot.quotId;
   console.log(this._ITOHomePageService.selectedQuot)
   this.qotNumb = this._ITOHomePageService.selectedQuot.quotNumber;
    this._ItoQuotCompleteService.getQuotFormData().subscribe(quotForm => {
     
      quotForm.saveBasicDetails.quotId = this._ITOHomePageService.selectedQuot.quotId;
      quotForm.saveBasicDetails.revNum = this._ITOHomePageService.revNum;
    
    this._ITOViewQuotService.getQuotRevData(quotForm).subscribe(res => {
      console.log(res);
      this.quotform = res;

      this.editModebtn = this._ITOeditQoutService.editMode;
      this.editMode = this._ITOeditQoutService.editMode;
      console.log(this.editModebtn)
      
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
      // this.qotNumb = res.saveBasicDetails.quotNumber;
      // this.orderStatus(res.saveBasicDetails.statusName);
      this.customerData = res.customerDetailsForm;
     

      this._ITOcustomerDetailsService.customerDetailsForm = res.customerDetailsForm;
      this.f2fQuesData = res.selectedQuestionAnswerSet;
      this._ITOcustomerRequirementService.saveBasicDet = res.saveBasicDetails;
      this._ITOcustomerRequirementService.saveBasicDet.userRoleId = this.storage.get(this.currentRoleId);
      this._ITOcustomerRequirementService.saveBasicDet.editFlag = true;
      if (res.saveBasicDetails.custCode == "DM") {
        this.custCode = true; // setting true for domestic customer
      }
      this._ITOcustomerRequirementService.saveBasicDet.modifiedById = this._ITOLoginService.loggedUserDetails;

      this.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
      console.log(this.saveBasicDetails.statusCode)
      this.scopeOfSupplyList = res.scopeOfSupplyList;

      this.scopeOfSupply = res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
   
      for (let s = 0; s < this.scopeOfSupply.length; s++) {
        for (let r = 0; r < this.scopeOfSupplyList.length; r++) {
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
              case "SP": {
                this.dispSpares = true;
                break;
              }
            }
          }
        }
      }
      this.CustInfo = new TurbineDetails('');
      this.CustInfo.Orientation = res.saveBasicDetails.orientationType;
      this.CustInfo.capacity = res.saveBasicDetails.capacity;
      this.CustInfo.condenserType = res.saveBasicDetails.condensingTypeName;
      this.CustInfo.frames = res.saveBasicDetails.frameName;
      this.CustInfo.region = res.saveBasicDetails.region;
      this.CustInfo.turbineType = res.saveBasicDetails.typeOfTurbine;
      this.CustInfo.typeOfblade = res.saveBasicDetails.typOfBlade;
      this.CustInfo.bleedtype = res.saveBasicDetails.bleedType;
      this.regn = res.saveBasicDetails.region;
      this.regnCd = res.saveBasicDetails.regionCode;
      if (res.saveBasicDetails.isNewProject == 1)
        this.newDev = "YES";
      else if (res.saveBasicDetails.isNewProject == 0)
        this.newDev = "NO";
      this.multFactor = res.saveBasicDetails.percentageVariation;

      for (let r = 0; r < res.submittedAddOnList.length; r++) {
        if (res.submittedAddOnList[r].addOnCompoId < 18) {
          this.othersList.push(res.submittedAddOnList[r]);
        }
        this.selectedAddOns = res.questionsBean;
      }
      this._ITOeditQoutService.dboMechData = res.quotDboMechList;
      this._ITOeditQoutService.dboDataOthers = res.quotDboMechOthersList;

      this._ITOeditQoutService.dboEleData = res.quotDboElectricalList;
      this._ITOeditQoutService.dboEleDataAddOn = res.addInstrList;
      if (this._ITOeditQoutService.dboEleDataAddOn.length>0){
        this._ITOeditQoutService.dboEleDataAddOn[0].dboEleType = "LT";  // remove this after fixing
      }
      this._ITOeditQoutService.dboEleOthers = res.quotDboEleOthersList;
      this._ITOeditQoutService.dboEleSplAddOnList = res.quotDboEleSplAddOnList;
      this._ITOeditQoutService.dboEleNewAddOns = res.dboEleAddOnList;
      //F2f Data
      this.f2fExcel = res.f2fExcel;

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
      
      });

      this.ecBeanList = res.errectionCommList;
      this.pkgBeanList = res.packageDetailsListData;
      this.transDetailList = res.transportationDetailList;
      this.costSheetList = res.oneLineBomExcel.costSheetList
      this.submittedAddOnListCost = res.submittedAddOnList;
      this.latestCProjectData = res.latestCProjectData;
     
      this.onLineBomExcel = res.oneLineBomExcel;
      this.addOnsCostList = res.oneLineBomExcel.addOnsList;
      this.dboMechList = res.oneLineBomExcel.dboMechList;
      this.dboEleList = res.oneLineBomExcel.dboEleList;
      this.otherCostsOneLine = res.oneLineBomExcel.otherCostsBean;
      this.varCostList = res.variableCostList;
      this.sparesCostList = res.sparesCostList;
      this.projectCostList = res.projectCostList;
      this.varCost = res.variableCostList[0];
      this.sparesCost = res.sparesCostList[0];
      this.projectCost = res.projectCostList[0];
      console.log(this.varCost)
      console.log(this.projectCost)
      this.ecBean = res.errectionCommList[0];
    
      this.packageBean = res.packageDetailsListData[0];
    
      for (let t = 0; t < this.costSheetList.length; t++) {
        if (this.costSheetList[t].categoryDetCode == "Transportation Cost") {
          this.transTotalCost = this.costSheetList[t].price;
        }
      }

      this.hideprogress = false;
    
    })
  });

  }

  ngOnInit() {

    this.arry = [
      { name: "userIndialog", value: false },
      { name: "custInfoDialog", value: false },
      { name: "custReqDialog", value: false },
      { name: "scopeOfSupDialog", value: false },
      { name: "turbineDetDialog", value: false },
      { name: "estimateDialog", value: false },
      { name: "costsheetSumDialog", value: false }
    ]
    this.subArry = [
      { name: "dboMechDialog", value: false },
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
    this.storage.remove(this.dboEleFull);
    this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.dboEleAuxFull);
    this.storage.remove(this.dboMechFull);
    this.storage.remove(this.exclusion);
	 this.storage.remove(this.quaility);
	  this.storage.remove(this.scopeOf);
	   this.storage.remove(this.supplier);
	    this.storage.remove(this.tender);
		 this.storage.remove(this.terminal);
    this.storage.remove(this.dboMechLoc);
    this.storage.remove(this.dboComSecALoc);
    this.storage.remove(this.dboPerfLoc);
    this.storage.remove(this.MechExpScope);
    this.storage.remove(this.EleCiExtdScope);
    this.storage.remove(this.EleExtdScope);
    this.storage.remove(this.clarifiAttach);
    this.storage.remove(this.tendrAttach);
  }

  ngAfterContentChecked() {
  
  }
  addOnPdf() {
    this.hidespinner = true;
    console.log(this.quotform);
    this._ITOeditQoutService.quotExportAddOnPdfRev(this.quotform).subscribe(res => {
      console.log(res);
      saveAs(res, "AddOnCost.pdf");
      this.hidespinner = false;
    })
  }


  exportRev() {
    this.hidespinner = true;
    this._ITOeditQoutService.quotExportPdfRev(this.quotform).subscribe(res => {
      console.log(res);
      saveAs(res, "CostSheet.pdf");
      this.hidespinner = false;
    })
  }
  
  comrDownload(){
    console.log(this.qotNumber);
    console.log(this.quotform);
    this.hidespinner = true;
    this._ITOeditQoutService.getComercialWordRev(this.quotform).subscribe(reponn => {
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
    this._ITOeditQoutService.getComercialWordNewRev(this.quotform).subscribe(reponn => {
      console.log(reponn);
      const blob = new Blob([reponn], {type: 'text/docx'});
      if (reponn.type.includes('docx')){
        this.filename = this.qotNumber+" - "+"Comercial Offer2B.docx";
      }
    fileSaver.saveAs(blob, this.filename);
    this.hidespinner = false;
    });
    
  }
  
  
  downloadDoc(){
    console.log(this.qotNumber);
    console.log(this.quotform);
    this.hidespinner = true;
    this._ITOeditQoutService.getWordRev(this.quotform).subscribe(reponn => {
      console.log(reponn);
      const blob = new Blob([reponn], {type: 'text/docx'});
      if (reponn.type.includes('docx')){
        this.filename = this.qotNumber+" - "+"Technical Offer.docx";
      }
    fileSaver.saveAs(blob, this.filename);
    this.hidespinner = false;
    });
    
  }
  assignedUser(assigne) {
    console.log(assigne);
    this.saveBasicDetails.assignedTo = assigne;
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
    this.router.navigate(['sparesCost']);
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
  dboEleTab() {
    console.log(this._ITOcustomerRequirementService.quotForm)
    this._ITOcustomerRequirementService.editFlag = true;
    this.router.navigate(['DBOElectrical']);
  }
  export() {
    this._ITOViewQuotService.quotExportPdf(this.quotform).subscribe(res => {
      console.log(res);
      saveAs(res, "CostSheet.pdf");
    })
  }

  saveAsfn() {
    this.quotform.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    console.log(this._ITOcustomerRequirementService.saveBasicDet)
    this._ITOHomePageService.saveAs(this.quotform).subscribe(resSaveAs => {
      if (resSaveAs.successCode == 0) {
        this._ITOLoginService.dialogMsgApp = true;
        this._ITOLoginService.dialogMsgVal = 'Quotation is saved as QUOT NUMBER:' + ' ' + resSaveAs.saveBasicDetails.quotNumber;
      }
      else {
        this._ITOLoginService.dialogMsgApp = true;
        this._ITOLoginService.dialogMsgVal = resSaveAs.successMsg;
      }
    })
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
    this.collapseDivs("estimateDialog");
  }
  turbineDetTab() {
    this.collapseDivs("turbineDetDialog");
  }
  dboMechInfo() {
    this.collapseSubDivs("dboMechDialog");
  }
  dboEleInfo() {
    this.collapseSubDivs("dboEleDialog");
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
        this.arry[s].value = false;
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

}
