import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOLoginService } from '../app.component.service';
import { DBOMechanicalComponentService } from '../dbomechanical/dbomechanical.service';
import { ITOScopeOfSupplyService } from '../ito-scope-of-supply/ito-scope-of-supply.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { saveAs } from 'file-saver';
import * as fileSaver from 'file-saver';

@Component({
  selector: 'app-ito-cost-estimation',
  templateUrl: './ito-cost-estimation.component.html',
  styleUrls: ['./ito-cost-estimation.component.css']
})
export class ItoCostEstimationComponent implements OnInit {

  toggleGrey = false;
  toggleOrange = true;
  togglegreen = false;

  toggle2Grey = true;
  toggle2Orange = false
  toggle2green = false;

  toggle3Grey = true;
  toggle3Orange = false
  toggle3Green = false;

  users: string = 'user';
  userDetail: string = 'userDetail';
  customerReqrmnt: string = 'customerReq';
  endUserDetail: string = 'endUserDetail';
  custdetails: string = 'custdetails';
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
  dboMechFull: string = 'dboMechFull';
  dboMechLoc: string = 'dboMech';
  dboComSecALoc: string = 'comSecA'; // local storage value
  dboPerfLoc: string = 'dboPerf';
  dboEleFull: string = 'dboEleFull';
  F2FTurbine: string = 'F2FTurbine';
  dboMechAuxLoc: string = 'dboAuxMech';
  MechExpScope: string = 'MechExpScope';
  EleExtdScope: string = 'EleExtdScope';
  EleCiExtdScope: string = 'EleCiExtdScope';
  generalInput: string = 'generalInputList';
  dboEleCIAuxFull: string = 'dboEleCIAuxFull';
  dboEleCIFull: string = 'dboEleCIFull';
  dboEleAuxFull: string = 'dboEleAuxFull';
  exclusion: string = 'exclusion'; // local storage value
  quaility: string = 'quaility'; // local storage value
  scopeOf: string = 'scopeOf'; // local storage value
  supplier: string = 'supplier'; // local storage value
  tender: string = 'tender'; // local storage value
  terminal: string = 'terminal'; // local storage value

  instructionsDialog: boolean = false;
  customerInfo: Array<any> = [];
  scopeArray: Array<any> = [];
  ScopeOfSupply: Array<any> = [];
  SOSbtn: boolean = false; //to set orange or grey color for scope of supply
  TDbtn: boolean = false; //to set orange or grey color for F2F Turbine
  Estbtn: boolean = false; 
  cmpltBn: boolean = false; //to set orange or grey color for Commercial
  TECHbtn: boolean = false; //to set orange or grey color for Techinal
  ELEbtn: boolean = false; //to set orange or grey color for Electrical
  CIbtn: boolean = false; //to set orange or grey color for CI
  MECHbtn: boolean = false; //to set orange or grey color for Mechanial
  SAPbtn: boolean = false; //to set orange or grey color for F2F SAP 
  editFlag: boolean = false; // added by kavya
  disableF2F: boolean = true;
  greenSOS: boolean = false; //to set green color for scope of supply
  disableComr: boolean = false; // To enable or disable commercial button
  disableIcon: boolean = false; // to enable or disable icons for download
  F2FTurGreen: boolean = false; //to Set green color for F2FTurbine button
  MECHGreen: boolean = false; //To set green color for Mechanical Button
  ELEGreen: boolean = false; //To set green color for Electrical button
  CIGreen: boolean = false; //To set green color for CI button
  TECHGreen: boolean = false; //To set green color for techinal Button
  COMRGreen: boolean =  false; //To set green color for commercial button
  quotform: any;
  filename: any;
  qotNumber: any;
  tendrAttach: string = 'tendrAttach';//Local storaghe for tender drawing new
  clarifiAttach: string = 'clarifiAttach';// Local storage for clarifications and deviations
  hidespinner: boolean =  false;
  capacity: number = 0;
  frameNm: string = '';
  typeCust: string = '';
  button1:boolean=false;
  button2:boolean=false;
  button3:boolean=false;
  button4:boolean=false;
  button5:boolean=false;
  button6:boolean=false;
  button7:boolean=false;
  button8:boolean=false;
  button9:boolean=false;

  


  constructor(private _ActivatedRoute: ActivatedRoute, private router: Router,
    private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private ITOScopeOfSupplyService: ITOScopeOfSupplyService,
    private _ITOLoginService: ITOLoginService,public _ITOeditQoutService: ITOeditQoutService,
     private _DBOMechanicalComponentService: DBOMechanicalComponentService) {

    document.getElementsByTagName('body')[0].addEventListener("pageshow", function () {
      console.log('pageshow:');
    });    
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this.button1=false;
    this.button2=false;
    this.button3=false;
    this.button4=false;
    this.button5=false;
    this.button6=false;
    this.button7=false;
    this.button8=false;
    this.button9=false;


    if (!(this._ITOcustomerRequirementService.editFlag  )) {   // added by kavya
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
      this.storage.remove(this.F2FSap);
      this.storage.remove(this.f2fCostData);
      this.storage.remove(this.projectCostLocal);
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
      this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.dboEleAuxFull);
      this.storage.remove(this.dboEleFull);
      this.storage.remove(this.F2FTurbine);
      this.storage.remove(this.MechExpScope);
      this.storage.remove(this.EleCiExtdScope);
      this.storage.remove(this.EleExtdScope);
      this.storage.remove(this.dboMechAuxLoc);
      this.storage.remove(this.generalInput);
      this.storage.remove(this.clarifiAttach);
      this.storage.remove(this.tendrAttach);
      this._ITOcustomerRequirementService.saveBasicDet = '';
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
      this._ITOeditQoutService.dboMechData = [];
      this._ITOeditQoutService.qualitiasurance = [];
      this._ITOeditQoutService.scopeofspares = [];
      this._ITOeditQoutService.tenderDraw = [];
      this._ITOeditQoutService.terminalpo = [];
      this._ITOeditQoutService.exclusionLi = [];
      this._ITOeditQoutService.subSuppliersList = [];
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
      this.ITOScopeOfSupplyService.sampleScope = [];
      this.instructionsDialog = true;
      for (let a = 0; a < this._DBOMechanicalComponentService.addedClassList.length; a++) {
        this.storage.remove(this._DBOMechanicalComponentService.addedClassList[a]);
      }

    }
    else {
      this.instructionsDialog = false;
    }
    //this.editFlag = this._ITOcustomerRequirementService.editFlag;
    // if(!this.editFlag){   // added by kavya
    this.router.navigate(['/CostEstimation/CustomerInformation']);
this.frameNm=this._ITOLoginService.frameNm;
this.capacity=this._ITOLoginService.capacity;
this.typeCust = this._ITOLoginService.typeCust;
    // }

    this._ITOcustomerRequirementService.getbtnStatus().subscribe(res => {
      console.log(res);
      this.SOSbtn = res.SOS;
      this.SAPbtn = res.SAP;
      this.TDbtn = res.TUR;
      this.MECHbtn = res.MECH;
      this.ELEbtn = res.ELE;
      this.CIbtn = res.CI;
      this.TECHbtn = res.TECH;
      //this.Estbtn = res.EC;
      this.cmpltBn = res.CMPLT;
      // this.Estbtn = true;
      //greenSOS will be true on going to next panel from general inputs to other next panel(F2F Turbine)
      if(this.TDbtn == true || this.MECHbtn == true || this.ELEbtn == true || this.CIbtn == true){
        this.greenSOS = true;
      }
      console.log(this._ITOcustomerRequirementService.saveBasicDet.typeOfOfferNm);
      this.frameNm = this._ITOcustomerRequirementService.saveBasicDet.frameName;
      this.capacity = this._ITOcustomerRequirementService.saveBasicDet.capacity;
      // this.typeCust=this._ITOcustomerRequirementService.saveBasicDet.custType;
      // if(this.typeCust==null || this.typeCust==''){
        // this.typeCust=this._ITOcustomerRequirementService.saveBasicDet.custType;
        if(this._ITOcustomerRequirementService.saveBasicDet.custCode == 'DM'){
            this.typeCust = 'Domestic';
          }else  if(this._ITOcustomerRequirementService.saveBasicDet.custCode == 'EX'){
            this.typeCust = 'Export';
          }
      // }
      // if(this._ITOcustomerRequirementService.saveBasicDet.custType == 'Domestic'){
      //   this._ITOcustomerRequirementService.saveBasicDet.custCode = 'DM';
      // }else if(this._ITOcustomerRequirementService.saveBasicDet.custType == 'Export'){
      //   this._ITOcustomerRequirementService.saveBasicDet.custCode == 'EX';
      // }
      // if(this._ITOcustomerRequirementService.saveBasicDet.custCode == 'DM'){
      //   this.typeCust = 'Domestic';
      // }else{
      //   this.typeCust = 'Export';
      // }
      if(this._ITOcustomerRequirementService.saveBasicDet.typeOfOfferNm == "Techno Commercial"){
        this.disableComr = true;
      }else{
        this.disableComr = false;
      }
      if(this._ITOcustomerRequirementService.saveBasicDet.quotId > 0){
         //getting data for the selected Quotation
    this.qotNumber = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(respppp => {
      console.log(respppp);
      if(respppp.savedList.length >0 && respppp.saveEleFilterList.length>0)  {  
        this.disableIcon = true;   
      }else{
        this.disableIcon = false;
      }
    });
  }
    })  

    this.ITOScopeOfSupplyService.getCheckScopes().subscribe(scopes => {      
      console.log(scopes);
      let m: Array<any> = scopes;
      if (m.map((s) => {
        return s.scopeCode
      }).includes('F2F') == true) {
        this.disableF2F = true;
      }
      else {
        this.disableF2F = false;
      }
      console.log(this.disableF2F);
      if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard" || this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Non Standard"){
      for(let s=0;s<m.length; s++){
      switch (m[s].scopeCode) {
        case "F2F": {
          this._ITOcustomerRequirementService.sendturBtnStatus(true);
          }
          case "MECH": {
            this._ITOcustomerRequirementService.sendmecBtnStatus(true);
            }
            case ("ELE"): {
              this._ITOcustomerRequirementService.sendeleBtnStatus(true);
              }
              case ("CI"): {
                this._ITOcustomerRequirementService.sendCIBtnStatus(true);
                }
              case ("PHM" || "TD" || "QA" || "TP" || "EL" || "CD" || "SSL" || "DR" || "SS" || "SP"): {
                this._ITOcustomerRequirementService.sendtecBtnStatus(true);
                }
                case ("PF" || "VC" || "PR" || "TC"): {
                  this._ITOcustomerRequirementService.sendcomBtnStatus(true);
                  }
          break;
        }
      }
    }
   // else if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm != "Standard"){
    //   this._ITOcustomerRequirementService.sendturBtnStatus(false);
    //   this._ITOcustomerRequirementService.sendmecBtnStatus(false);
    //   this._ITOcustomerRequirementService.sendeleBtnStatus(false);
    //   this._ITOcustomerRequirementService.sendtecBtnStatus(false);
    //   this._ITOcustomerRequirementService.sendcomBtnStatus(false);
    // }
    });
    
    this._ITOcustomerRequirementService.getturBtnStatus().subscribe(respp =>{
      console.log(respp);
      this.F2FTurGreen = respp;
    })

    this._ITOcustomerRequirementService.getmecBtnStatus().subscribe(respo =>{
      console.log(respo);
      this.MECHGreen = respo;
    })

    this._ITOcustomerRequirementService.geteleBtnStatus().subscribe(respoo =>{
      console.log(respoo);
      this.ELEGreen = respoo;
    })

    this._ITOcustomerRequirementService.getCIBtnStatus().subscribe(respoo =>{
      console.log(respoo);
      this.CIGreen = respoo;
    })

    this._ITOcustomerRequirementService.gettecBtnStatus().subscribe( respon => {
      console.log(respon);
      this.TECHGreen = respon;
    })

    this._ITOcustomerRequirementService.getcomBtnStatus().subscribe( responn =>{
      console.log(responn);
      this.COMRGreen = responn;
    })

    this._ITOcustomerRequirementService.getclrBtnStatus().subscribe(repp => {
      console.log(repp);
      this.SOSbtn = repp.SOS;
      this.SAPbtn = repp.SAP;
      this.TDbtn = repp.TUR;
      this.MECHbtn = repp.MECH;
      this.ELEbtn = repp.ELE;
      this.CIbtn = repp.CI;
      this.TECHbtn = repp.TECH;
      this.cmpltBn = repp.CMPLT;
      this.F2FTurGreen = repp.F2FTUR;
      this.MECHGreen = repp.MECHbtnn;
      this.ELEGreen = repp.ELEbtnn;
      this.CIGreen = repp.CIbtnn;
      this.TECHGreen = repp.TECHbtnn;
      this.COMRGreen = repp.COMRbtnn;
      this.greenSOS = repp.SOS;
    })
    
  }

  ngOnInit() {
    this._ITOLoginService.isStandAlone = false;
    // window.addEventListener('pageshow', function (event) {
    //   console.log('pageshow:');
    //   console.log(event);
    // });
  }

  ngAfterContentChecked() {

  }

  enableDisableRule() {
    this.toggleOrange = true;
    this.toggleGrey = false;
    this.togglegreen = false;

    this.toggle3Orange = false;
    this.toggle3Grey = true;
    this.toggle3Green = false;

    this.toggle2Grey = true;
    this.toggle2Orange = false
    this.toggle2green = false;
    this._ITOeditQoutService.button1=true;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this.button1=true;
    this.button2=false;
    this.button3=false;
    this.button4=false;
    this.button5=false;
    this.button6=false;
    this.button7=false;
    this.button8=false;
    this.button9=false;
    

  }
  enableDisableRule2() {

    this.toggleOrange = false;
    this.toggleGrey = false;
    this.togglegreen = true;

    this.toggle3Orange = false;
    this.toggle3Grey = false;
    this.toggle3Green = true;

    this.toggle2Grey = false;
    this.toggle2Orange = true
    this.toggle2green = false;


  }
  enableDisableRule3() {
console.log( "hello");
    this.toggleOrange = false;
    this.toggleGrey = false;
    this.togglegreen = true;

    this.toggle3Orange = true;
    this.toggle3Grey = false;
    this.toggle3Green = false;

    this.toggle2Grey = true;
    this.toggle2Orange = false
    this.toggle2green = false;
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=true;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this.button1=false;
    this.button2=true;
    this.button3=false;
    this.button4=false;
    this.button5=false;
    this.button6=false;
    this.button7=false;
    this.button8=false;
    this.button9=false;



  }
  buttonmethod1()
  {
    this._ITOeditQoutService.button1=true;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this.button1=true;
    this.button2=false;
    this.button3=false;
    this.button4=false;
    this.button5=false;
    this.button6=false;
    this.button7=false;
    this.button8=false;
    this.button9=false;
  }
  buttonmethod3()
  {
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=true;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this.button1=false;
    this.button2=false;
    this.button3=true;
    this.button4=false;
    this.button5=false;
    this.button6=false;
    this.button7=false;
    this.button8=false;
    this.button9=false;
  }
  buttonmethod4()
  {
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=true;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this.button1=false;
    this.button2=false;
    this.button3=false;
    this.button4=true;
    this.button5=false;
    this.button6=false;
    this.button7=false;
    this.button8=false;
    this.button9=false;
  }
  buttonmethod5()
  {
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=true;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this.button1=false;
    this.button2=false;
    this.button3=false;
    this.button4=false;
    this.button5=true;
    this.button6=false;
    this.button7=false;
    this.button8=false;
    this.button9=false;
  }
  buttonmethod6()
  {
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=true;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this.button1=false;
    this.button2=false;
    this.button3=false;
    this.button4=false;
    this.button5=false;
    this.button6=true;
    this.button7=false;
    this.button8=false;
    this.button9=false;
  }
  buttonmethod7()
  {
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=true;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    this.button1=false;
    this.button2=false;
    this.button3=false;
    this.button4=false;
    this.button5=false;
    this.button6=false;
    this.button7=true;
    this.button8=false;
    this.button9=false;
  }
  buttonmethod8()
  {
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=true;
    this._ITOeditQoutService.button9=false;
    this.button1=false;
    this.button2=false;
    this.button3=false;
    this.button4=false;
    this.button5=false;
    this.button6=false;
    this.button7=false;
    this.button8=true;
    this.button9=false;
  }
  buttonmethod9()
  {
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=true;
    this.button1=false;
    this.button2=false;
    this.button3=false;
    this.button4=false;
    this.button5=false;
    this.button6=false;
    this.button7=false;
    this.button8=false;
    this.button9=true;
  }
  export() {
    this.qotNumber = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.hidespinner = true;

    //getting data for the selected Quotation
    this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(res => {
      console.log(res);
      this.quotform = res;
      this.quotform.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    console.log(this.qotNumber);
    console.log(this.quotform);
    this._ITOeditQoutService.quotExportPdf(this.quotform).subscribe(res => {
      console.log(res);
      saveAs(res, "CostSheet.pdf");
      this.hidespinner = false;
    });
  });
  }

  downloadDoc(){
    this.qotNumber = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.hidespinner = true;

    //getting data for the selected Quotation
    this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(res => {
      console.log(res);
      this.quotform = res;
      this.quotform.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    console.log(this.qotNumber);
    console.log(this.quotform);
    this._ITOeditQoutService.getWord(this.quotform).subscribe(reponn => {
      console.log(reponn);
      const blob = new Blob([reponn], {type: 'text/docx'});
      if (reponn.type.includes('docx')){
        this.filename = this.qotNumber + " - "+"Technical Offer.docx";
      }
    fileSaver.saveAs(blob, this.filename);
    this.hidespinner = false;
    });
  });
  }

  comrDownload(){
    this.qotNumber = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.hidespinner = true;

    //getting data for the selected Quotation
    this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(res => {
      console.log(res);
      this.quotform = res;
      this.quotform.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    console.log(this.qotNumber);
    console.log(this.quotform);
    this._ITOeditQoutService.getComercialWord(this.quotform).subscribe(reponn => {
      console.log(reponn);
      const blob = new Blob([reponn], {type: 'text/docx'});
      if (reponn.type.includes('docx')){
        this.filename = this.qotNumber + " - "+"Comercial Offer2A.docx";
      }
    fileSaver.saveAs(blob, this.filename);
    this.hidespinner = false;
    });
  });
  }

  comrDownloadNew(){
    this.qotNumber = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.hidespinner = true;

    //getting data for the selected Quotation
    this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(res => {
      console.log(res);
      this.quotform = res;
      this.quotform.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    console.log(this.qotNumber);
    console.log(this.quotform);
    this._ITOeditQoutService.getComercialWordNew(this.quotform).subscribe(reponn => {
      console.log(reponn);
      const blob = new Blob([reponn], {type: 'text/docx'});
      if (reponn.type.includes('docx')){
        this.filename = this.qotNumber + " - "+"Comercial Offer2B.docx";
      }
    fileSaver.saveAs(blob, this.filename);
    this.hidespinner = false;
    });
  });
  }



  detailedpdf() {
    this.qotNumber = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    //getting data for the selected Quotation
    this.hidespinner = true;

    this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(res => {
      console.log(res);
      this.quotform = res;
      this.quotform.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    this._ITOeditQoutService.quotExportPdfdet(this.quotform).subscribe(res => {
      console.log(res);
      saveAs(res, "InputReport.pdf");
      this.hidespinner = false;
    })
  });
}

addOnPdf() {
  this.hidespinner = true;
  
  this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(res => {
    console.log(res);
    this.quotform = res;
    this.quotform.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
  console.log(this.quotform);
  this._ITOeditQoutService.quotExportAddOnPdf(this.quotform).subscribe(res => {
    console.log(res);
    saveAs(res, "AddOnCost.pdf");
    this.hidespinner = false;
  })
});
}
}