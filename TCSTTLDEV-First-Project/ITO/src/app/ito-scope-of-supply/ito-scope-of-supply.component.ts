import { Component, OnInit } from '@angular/core';
import { ITOLoginService } from '../app.component.service';
import { ITOScopeOfSupplyService } from '../ito-scope-of-supply/ito-scope-of-supply.service';
import { ITOcustomerDetailsService } from '../ito-customer-details/ito-customer-details.service';
import { ITOendUserDetailsService } from '../ito-end-user-details/ito-end-user-details.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOHomePageService } from '../ito-home-page/ito-home-page.service';
import { RepositionScrollStrategy } from '@angular/cdk/overlay';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOGeneralInputsService } from '../ito-general-inputs/ito-general-inputs.service';
import { notEqual } from 'assert';


@Component({
  selector: 'app-ito-scope-of-supply',
  templateUrl: './ito-scope-of-supply.component.html',
  styleUrls: ['./ito-scope-of-supply.component.css']
})
export class ItoScopeOfSupplyComponent implements OnInit {

  currentRoleId: string = 'selRoleId';
  saveEleFilterList: any;
  onLineBomExcel: any;
  ecBean: any;
   projectCost: any;
   sparesCost: any;
   varCost: any;
   packageBean: any;
   transDetailList: any;
   customerData: any;
   custCode: boolean = false;
   f2fQuesData: any;
  genInputList: any;
  scopeOfSupplyList: any;
  scopeOfSupply: any;
  qotNumber: number = 0;
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
  custdetails: string = 'custdetails';
  dboFormDataaa: any;
  message: boolean = false;
  successMsg: string = "";
  outer: Array<any>=[];
  inner: Array<any> = [];
  enableAssignDiv: boolean = false;
  selectAllVal: boolean = false;
  isChecked: boolean = true;
  ScopeOfSupplyTmp: Array<any> = [];
  ScopeOfSupply: Array<any> = [];
  selScopes: Array<any> = [];
  selScopeOfSups: Array<any> = [];
  disabled: boolean = false;
  scopeArray: Array<any> = [];
  scopeofsupp: string = 'scopeOfsup';
  generalInput: string = 'generalInputList';
  addOnDetails: string = 'addOnDetail';
  packLocal: string = 'packLocal';
  transLocal: string = 'transLocal';
  ECData: string = 'ecKey';
  newCostEst: string = 'newCostEst';
  dboMechLoc: string = 'dboMech';
  dboPerfLoc: string = 'dboPerf';
  dboComSecALoc: string = 'comSecA'; // local storage value
  dboMechFull: string = 'dboMechFull'; 
  sparesLocal: string = 'sparesLocal';
  varCostLocal: string = 'varCostLocal';
  F2FSap: string = 'F2FSap';
  projectCostLocal: string = 'projectCostLocal';
  f2fCostData: string = 'f2fCostData';
  dboEleFull: string = 'dboEleFull';
  F2FTurbine: string = 'F2FTurbine';
  dboMechAuxLoc: string = 'dboAuxMech';
  MechExpScope: string = 'MechExpScope';
  EleExtdScope: string = 'EleExtdScope';
  EleCiExtdScope: string = 'EleCiExtdScope';
  dboEleCIAuxFull: string = 'dboEleCIAuxFull';
  dboEleCIFull: string = 'dboEleCIFull';
  dboEleAuxFull: string = 'dboEleAuxFull';
  quotationNumber: any = '';
  hidespinner: boolean = false;
  disabledArray: Array<any> = [];
  quotId: number;
  backBtn: boolean = false;
  enbSelAll: boolean = true;
  editFlagNew: number = 0;
  editFlagNewArr: Array<any> = ['id: number', 'Flg: number'];
  mainSave:boolean=true;
  F2F: boolean = false;
  MECH: boolean = false;
  ELE: boolean = false;
  CI: boolean = false;
  TECH: boolean = false;
  COMR: boolean = false;

  constructor(private _Router: Router, private ITOScopeOfSupplyService: ITOScopeOfSupplyService,
    private _ITOcustomerDetailsService: ITOcustomerDetailsService, private _ITOGeneralInputsService: ITOGeneralInputsService, private _ITOendUserDetailsService: ITOendUserDetailsService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOeditQoutService:ITOeditQoutService, private _ITOHomePageService: ITOHomePageService, private _ITOLoginService: ITOLoginService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService) {

    this.quotationNumber = this._ITOcustomerRequirementService.saveBasicDet.quotNumber;
    this.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    console.log(this.quotId);
    if(this._ITOeditQoutService.checkEdit == false){
      this.backBtn = true;
    }
    if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
      this.enbSelAll = false;
    }
    this.scopeArray[this.scopeofsupp] = this.storage.get(this.scopeofsupp);
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=true;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=false;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    console.log(this.scopeArray[this.scopeofsupp])
    if (this.scopeArray[this.scopeofsupp] != null) {
      if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
        this.ScopeOfSupplyTmp = this.storage.get(this.scopeofsupp);
        for(let b=0; b<this.ScopeOfSupplyTmp.length; b++){
          if(this.ScopeOfSupplyTmp[b].scopeCode != "CD" && this.ScopeOfSupplyTmp[b].scopeCode != "TD"){
            this.ScopeOfSupply.push(this.ScopeOfSupplyTmp[b]);
          }
        }
      }else{
      this.ScopeOfSupply = this.storage.get(this.scopeofsupp);
      }
      // for (let s = 0; s < this.ScopeOfSupply.length; s++) {
      //   if (this.ScopeOfSupply[s].scopeCode == "TC") {
      //     this.ScopeOfSupply[s].defaultValue = true;
      //     this.disabledArray[s] = true;
      //     this.selScopes.push(this.ScopeOfSupply[s]);
      //   }
      //   if (this.ScopeOfSupply[s].scopeCode == "PF") {
      //     this.ScopeOfSupply[s].defaultValue = true;
      //     this.disabledArray[s] = true;
      //     this.selScopes.push(this.ScopeOfSupply[s]);
      //   }
      //   if (this.ScopeOfSupply[s].scopeCode == "VC") {
      //     this.ScopeOfSupply[s].defaultValue = true;
      //     this.disabledArray[s] = true;
      //     this.selScopes.push(this.ScopeOfSupply[s]);
      //   }
      //   if (this.ScopeOfSupply[s].scopeCode == "EC") {
      //     this.ScopeOfSupply[s].defaultValue = true;
      //     this.disabledArray[s] = true;
      //     this.selScopes.push(this.ScopeOfSupply[s]);
      //   }
      //   if (this.ScopeOfSupply[s].scopeCode == "PR") {
      //     this.ScopeOfSupply[s].defaultValue = true;
      //     this.disabledArray[s] = true;
      //     this.selScopes.push(this.ScopeOfSupply[s]);
      //   }
      // }
      for (let s = 0; s < this.ScopeOfSupply.length; s++) {
        if (this.ScopeOfSupply[s].defaultValue == true) {
          if (this.ScopeOfSupply[s].scopeCode == "PHM" || this.ScopeOfSupply[s].scopeCode == "SS" ||
            this.ScopeOfSupply[s].scopeCode == "TP" || this.ScopeOfSupply[s].scopeCode == "EL" ||
            this.ScopeOfSupply[s].scopeCode == "TC" || this.ScopeOfSupply[s].scopeCode == "PF"||
            this.ScopeOfSupply[s].scopeCode == "VC" || this.ScopeOfSupply[s].scopeCode == "PR") {
            this.disabledArray[s] = true;
          }         
          this.selScopes.push(this.ScopeOfSupply[s]);          
        }
      }
      //when it is standard offer then call select all function to select all the scope of supply list
      //When standard off is also editable remove this function
      if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
        for (let s = 0; s < this.ScopeOfSupply.length; s++) {
           this.disabledArray[s] = true;
        }
        this.selectAll(true);
      }else{
        if(this.selScopes.length==0){
        for (let s = 0; s < this.ScopeOfSupply.length; s++) {
          if (this.ScopeOfSupply[s].scopeCode == "PHM" || this.ScopeOfSupply[s].scopeCode == "SS" ||
          this.ScopeOfSupply[s].scopeCode == "TP" || this.ScopeOfSupply[s].scopeCode == "EL" ||
          this.ScopeOfSupply[s].scopeCode == "TC" || this.ScopeOfSupply[s].scopeCode == "PF" ||
            this.ScopeOfSupply[s].scopeCode == "VC" || this.ScopeOfSupply[s].scopeCode == "PR") {
          this.disabledArray[s] = true;
            }
       }
        this.selectAllNStd(true);
      }
      }
      this.ITOScopeOfSupplyService.checkScopes(this.selScopes);
      if (this.selScopes.length == 0) {
        this.disabled = false;
      }
      else if (this.selScopes.length > 0) {
        this.disabled = true;
      }
      for (let s = 0; s < this.ScopeOfSupply.length; s++) {
        if (this.ScopeOfSupply[s].scopeCode == "PHM" && this.ScopeOfSupply[s].defaultValue== false) {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "SS" && this.ScopeOfSupply[s].defaultValue== false) {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "TP" && this.ScopeOfSupply[s].defaultValue== false) {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "EL" && this.ScopeOfSupply[s].defaultValue== false) {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "TC" && this.ScopeOfSupply[s].defaultValue== false) {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "PF" && this.ScopeOfSupply[s].defaultValue== false) {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "VC" && this.ScopeOfSupply[s].defaultValue== false) {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "PR" && this.ScopeOfSupply[s].defaultValue== false) {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
      }
      this.mainSave=false;
      if (this.selScopes.length == (this.ScopeOfSupply.length + 4)) {
        this.selectAllVal = true;
      } else if (this.selScopes.length == this.ScopeOfSupply.length) {
        this.selectAllVal = true;
      } else {
        this.selectAllVal = false;
      }
    } else if (this.scopeArray[this.scopeofsupp] == null) {
      this._ITOcustomerRequirementService.sendbtnStatus(true, true, false, false, false, false, false, false);
      this.isChecked = false;
      this.ITOScopeOfSupplyService.getQuotationList().subscribe(res => {
        console.log(res);
        if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
          this.ScopeOfSupplyTmp = res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
        for(let b=0; b<this.ScopeOfSupplyTmp.length; b++){
          if(this.ScopeOfSupplyTmp[b].scopeCode != "CD" && this.ScopeOfSupplyTmp[b].scopeCode != "TD"){
            this.ScopeOfSupply.push(this.ScopeOfSupplyTmp[b]);
          }
        }
      }else{
        this.ScopeOfSupply = res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
      }
      console.log(this.ScopeOfSupply);
        for (let s = 0; s < this.ScopeOfSupply.length; s++) {
          if (this.ScopeOfSupply[s].scopeCode == "PHM") {
            this.ScopeOfSupply[s].defaultValue = true;
            this.disabledArray[s] = true;
            // this.selScopes.push(this.ScopeOfSupply[s]);
          }
          if (this.ScopeOfSupply[s].scopeCode == "SS") {
            this.ScopeOfSupply[s].defaultValue = true;
            this.disabledArray[s] = true;
            // this.selScopes.push(this.ScopeOfSupply[s]);
          }
          if (this.ScopeOfSupply[s].scopeCode == "TP") {
            this.ScopeOfSupply[s].defaultValue = true;
            this.disabledArray[s] = true;
            // this.selScopes.push(this.ScopeOfSupply[s]);
          }
          if (this.ScopeOfSupply[s].scopeCode == "EL") {
            this.ScopeOfSupply[s].defaultValue = true;
            this.disabledArray[s] = true;
            // this.selScopes.push(this.ScopeOfSupply[s]);
          }
          if (this.ScopeOfSupply[s].scopeCode == "TC") {
            this.ScopeOfSupply[s].defaultValue = true;
            this.disabledArray[s] = true;
            // this.selScopes.push(this.ScopeOfSupply[s]);
          }
          if (this.ScopeOfSupply[s].scopeCode == "PF") {
            this.ScopeOfSupply[s].defaultValue = true;
            this.disabled = true;
            this.disabledArray[s] = true;
            // this.selScopes.push(this.ScopeOfSupply[s]);
          }
          if (this.ScopeOfSupply[s].scopeCode == "VC") {
            this.ScopeOfSupply[s].defaultValue = true;
            this.disabled = true;
            this.disabledArray[s] = true;
            // this.selScopes.push(this.ScopeOfSupply[s]);
          }
          // if (this.ScopeOfSupply[s].scopeCode == "EC") {
          //   this.ScopeOfSupply[s].defaultValue = true;
          //   this.disabled = true;
          //   this.disabledArray[s] = true;
          //   this.selScopes.push(this.ScopeOfSupply[s]);
          // }
          if (this.ScopeOfSupply[s].scopeCode == "PR") {
            this.ScopeOfSupply[s].defaultValue = true;
            this.disabled = true;
            this.disabledArray[s] = true;
            // this.selScopes.push(this.ScopeOfSupply[s]);
          }
        }
        console.log(this.ScopeOfSupply);
        //when it is standard offer then call select all function to select all the scope of supply list
        if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
          for (let s = 0; s < this.ScopeOfSupply.length; s++) {
             this.disabledArray[s] = true;
          }          
        }
       
        if (this.selScopes.length == (this.ScopeOfSupply.length + 4)) {
          this.selectAllVal = true;
        } else if (this.selScopes.length == this.ScopeOfSupply.length) {
          this.selectAllVal = true;
        } else {
          this.selectAllVal = false;
        }

        if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
          this.selectAll(true);
        }else{
          this.selectAllNStd(true);
        }

      })

    }    
  }
  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
    // this.storage.remove(this.generalInput);
  }

  checkedScopes(e, item, ind) {
    this.mainSave=true;
    this.message=false;
    console.log(item);
    console.log(this.selScopes);
    if (e.target.checked) {
      this.selScopes.push(item);
      this.ScopeOfSupply[ind].defaultValue = true;
      this.disabled = true;
    }
    if (!e.target.checked) {
      let i = this.selScopes.indexOf(item);
      this.selScopes.splice(i, 1);
      this.ScopeOfSupply[ind].defaultValue = false;
    }
    console.log(this.selScopes);
    console.log(this.selScopes.length);
    console.log(this.ScopeOfSupply.length);
    if(this.selScopes.length < 5){
      this.disabled = false;
    }
    if (this.selScopes.length == (this.ScopeOfSupply.length + 4)) {
      this.selectAllVal = true;
    } else if (this.selScopes.length == this.ScopeOfSupply.length) {
      this.selectAllVal = true;
    } else {
      this.selectAllVal = false;
    }
    // for(let k=0;k<this.selScopes.length;k++){
    //   if(this.selScopes[k]==24){
    //     this.disabled=true;
    //   }
    //   else{
    //     this.disabled=false;
    //   }
    // }     
  }

  selectAllNStd(event) {
    this.mainSave=true;

    console.log(event);
    if(event != true){
      console.log(event.target.checked);
    }   
    if (event == true || event.target.checked == true) {
      this.selScopes = [];
      this.disabled = true;
      for (let s = 0; s < this.ScopeOfSupply.length; s++) {
        console.log(this.ScopeOfSupply);
        if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Non Standard"){
        if(this.ScopeOfSupply[s].scopeCode == "CD" || this.ScopeOfSupply[s].scopeCode == "TD"){
          this.ScopeOfSupply[s].defaultValue = false;
        }else{
          this.ScopeOfSupply[s].defaultValue = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
      }
      }
    }
    else if (event.target.checked == false) {
      this.selScopes = [];
      this.disabled = false;
      console.log(this.selScopes);
      for (let s = 0; s < this.ScopeOfSupply.length; s++) {
        this.ScopeOfSupply[s].defaultValue = false;
        if (this.ScopeOfSupply[s].scopeCode == "PHM") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "SS") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "TP") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "EL") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "TC") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "PF") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "VC") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        // if (this.ScopeOfSupply[s].scopeCode == "EC") {
        //   this.ScopeOfSupply[s].defaultValue = true;
        //   this.disabledArray[s] = true;
        //   this.selScopes.push(this.ScopeOfSupply[s]);
        // }
        if (this.ScopeOfSupply[s].scopeCode == "PR") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        // if (this.ScopeOfSupply[s].defaultValue == true) {
        //   this.selScopes.push(this.ScopeOfSupply[s]);
        // }
      }
    }console.log(this.selScopes);
  }


  selectAll(event) {
    this.mainSave=true;
    this.message = false;
    console.log(event);
    if(event != true){
      console.log(event.target.checked);
    }   
    if (event == true || event.target.checked == true) {
      this.selScopes = [];
      this.disabled = true;
      for (let s = 0; s < this.ScopeOfSupply.length; s++) {
        console.log(this.ScopeOfSupply);
        this.ScopeOfSupply[s].defaultValue = true;
        this.selScopes.push(this.ScopeOfSupply[s]);
      }
    }
    else if (event.target.checked == false) {
      this.selScopes = [];
      this.disabled = false;
      console.log(this.selScopes);
      for (let s = 0; s < this.ScopeOfSupply.length; s++) {
        this.ScopeOfSupply[s].defaultValue = false;
        if (this.ScopeOfSupply[s].scopeCode == "PHM") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "SS") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "TP") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "EL") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "TC") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "PF") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        if (this.ScopeOfSupply[s].scopeCode == "VC") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        // if (this.ScopeOfSupply[s].scopeCode == "EC") {
        //   this.ScopeOfSupply[s].defaultValue = true;
        //   this.disabledArray[s] = true;
        //   this.selScopes.push(this.ScopeOfSupply[s]);
        // }
        if (this.ScopeOfSupply[s].scopeCode == "PR") {
          this.ScopeOfSupply[s].defaultValue = true;
          this.disabledArray[s] = true;
          this.selScopes.push(this.ScopeOfSupply[s]);
        }
        // if (this.ScopeOfSupply[s].defaultValue == true) {
        //   this.selScopes.push(this.ScopeOfSupply[s]);
        // }
      }
    }console.log(this.selScopes);
  }
  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.scopeArray[key] = this.storage.get(key);
  }

  send() {
    this.disabled =false;
    console.log(this.selScopes);    
    this.hidespinner = true;
    let sampleScope = this.selScopes.map(item => item.scopeCode);
    let selIds = [];
    this.inner =[];
    this.outer = [];
    selIds = this.selScopes.map(item => item.ssId);
    console.log(sampleScope);
    if (sampleScope.includes('F2F') || sampleScope.includes('MECH') || sampleScope.includes('ELE') || sampleScope.includes('CI')) {
      this.ITOScopeOfSupplyService.getQuotationList().subscribe(res => {
        res.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
        res.saveBasicDetails.scopeOfSupply = selIds;
         // if list length is lesser = inner loop if more = outer loop
         for(let m=0; m<this.ITOScopeOfSupplyService.sampleScope.length; m++){
         console.log(this.ITOScopeOfSupplyService.sampleScope[m].ssId, this.ITOScopeOfSupplyService.sampleScope[m].defaultValue);
         if(this.ITOScopeOfSupplyService.sampleScope[m].defaultValue == true){
           this.outer.push(this.ITOScopeOfSupplyService.sampleScope[m].ssId);
         }
         }
         this.inner = selIds;
         if(this.inner.length> this.outer.length){
          for(let o=0; o<this.inner.length; o++){
            if(this.outer.includes(this.inner[o])){
              this.editFlagNewArr[o] = 0;
            }else{
              this.editFlagNewArr[o] = 1;
            }
          }
         }else{
          for(let o=0; o<this.outer.length; o++){                        
               if(this.inner.includes(this.outer[o])){
                this.editFlagNewArr[o] = 0;
                  }  else{
                    this.editFlagNewArr[o] = 1;
                  }           
            
          }
         }
        // if(this.ITOScopeOfSupplyService.sampleScope != null || this.storage.get(this.scopeofsupp)!=null && this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm != "Standard"){
        //   if(this.ITOScopeOfSupplyService.sampleScope.length>=this.selScopes.length){
        //     this.outer = this.ITOScopeOfSupplyService.sampleScope.map(item => item.ssId);
        //     this.inner = selIds;
        //   }else if(this.ITOScopeOfSupplyService.sampleScope.length<=this.selScopes.length){
        //     this.inner = this.ITOScopeOfSupplyService.sampleScope.map(item => item.ssId);
        //     this.outer = selIds;
        //   }
        // }
          
         
          if(this.editFlagNewArr.includes(1)){
          this.editFlagNew = 1;
          }else{
            this.editFlagNew = 0;
          }
        
        res.saveBasicDetails.editFlagNew = this.editFlagNew;
        console.log(res);
        this.ITOScopeOfSupplyService.saveScopeOfSupply(res).subscribe(resp => {
          console.log(resp);
          if (resp.successCode == 0) {
            this.message = true;
            this.successMsg = "Saved Successfully";
            this.mainSave=false;
            this.ITOScopeOfSupplyService.createScopeOfSupplyNew(this.quotId).subscribe(repon => {
              console.log(repon);
            this.ITOScopeOfSupplyService.checkScopes(repon.scopeList);
            this.saveInLocal(this.scopeofsupp, this.ScopeOfSupply);
            this._ITOcustomerRequirementService.saveBasicDet = resp.saveBasicDetails;
            console.log(this._ITOcustomerRequirementService.saveBasicDet);
            this._ITOcustomerRequirementService.sendMessage(this.quotationNumber);
            this._ITOcustomerRequirementService.sendbtnStatus(true, true, false, false, false, false, false, false);
          
            console.log(repon.scopeList);
            this.ITOScopeOfSupplyService.sampleScope = [];
           // this.storage.remove(this.generalInput);
              this.ITOScopeOfSupplyService.sampleScope = repon.scopeList;
              let sampleScopeBut = this.ITOScopeOfSupplyService.sampleScope.map(item => item.scopeCode);
              console.log(sampleScopeBut);
              if(this.storage.get(this.generalInput) != null){
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
      if(sampleScopeBut.includes('TC') || sampleScopeBut.includes('PF') || sampleScopeBut.includes('VC') || sampleScopeBut.includes('PR')){
        this.COMR = true;
      }
    }
            for(let c=0; c<this.ScopeOfSupply.length; c++){
              let counter=0;
              for(let b=0; b<this.ITOScopeOfSupplyService.sampleScope.length; b++){
                if(this.ScopeOfSupply[c].scopeCode == this.ITOScopeOfSupplyService.sampleScope[b].scopeCode)
                {
                 counter=1;
              }
            }
            if(counter==0)
            {
              if(this.ScopeOfSupply[c].scopeCode == "ELE"){
                this.storage.remove(this.dboEleFull);
                this.storage.remove(this.dboEleAuxFull);  
                this.storage.remove(this.EleExtdScope);
                this.ELE=false;
              }
              if(this.ScopeOfSupply[c].scopeCode == "MECH"){
                this.storage.remove(this.MechExpScope);
                this.storage.remove(this.dboMechAuxLoc);
                this.storage.remove(this.dboMechLoc);
                this.MECH = false;
              }
              if(this.ScopeOfSupply[c].scopeCode == "F2F"){
                this.storage.remove(this.F2FTurbine);
                this.F2F = false;
              }
              if(this.ScopeOfSupply[c].scopeCode == "CI"){
                this.storage.remove(this.EleCiExtdScope);
                this.storage.remove(this.dboEleCIAuxFull);
                this.storage.remove(this.dboEleCIFull);
                this.CI = false;
              }
              if(this.ScopeOfSupply[c].scopeCode == "PHM"){
                this.storage.remove(this.dboPerfLoc);
              }
            }
            }
            this._ITOcustomerRequirementService.sendbtnStatus(true, true, this.F2F, this.MECH, this.ELE, this.CI, this.TECH, this.COMR);
              console.log(this.ITOScopeOfSupplyService.sampleScope);
              this.hidespinner = false;
              //TO update general inputs
              if(this.storage.get(this.generalInput) != null){
                if(this.storage.get(this.generalInput).genInputList.length>0 || this.storage.get(this.generalInput).saveEleFilterList.length>0){
              this._ITOGeneralInputsService.getDboFormData().subscribe(res => {
                console.log(res);
                this.dboFormDataaa = res;
      this.dboFormDataaa.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById;
        this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        console.log(this.dboFormDataaa);
        this.hidespinner = true; 
        this._ITOGeneralInputsService.getUpdateGeneralInput(this.dboFormDataaa).subscribe(responn =>{
          console.log(responn);
          if(responn.successCode == 0){
            this.message = true;
            this.successMsg = "Updated Successfully";
          }        

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
      this._ITOeditQoutService.dboEleDataAddOn = []; 
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
        if (this._ITOcustomerRequirementService.editFlag &&  this._ITOeditQoutService.checkEdit==false) {
          this._ITOcustomerRequirementService.editFlag = false;
          this._Router.navigate(['/EditQuot']);
        } else {
          this._Router.navigate(['CostEstimation/ScopeOfsupplyCstEst/GeneralInputs']);
          
        }
        this.hidespinner = false;
      });  
    });
  });
    }else{
      if (this._ITOcustomerRequirementService.editFlag &&  this._ITOeditQoutService.checkEdit==false) {
        this._ITOcustomerRequirementService.editFlag = false;
        this._Router.navigate(['/EditQuot']);
      } else {
        this._Router.navigate(['CostEstimation/ScopeOfsupplyCstEst/GeneralInputs']);
      }
    }
  }else{
            if (this._ITOcustomerRequirementService.editFlag &&  this._ITOeditQoutService.checkEdit==false) {
              this._ITOcustomerRequirementService.editFlag = false;
              this._Router.navigate(['/EditQuot']);
            } else {
              this._Router.navigate(['CostEstimation/ScopeOfsupplyCstEst/GeneralInputs']);
              // let m: Array<any> = repon.scopeList;
              // if (m.map((s) => {
              //   return s.scopeCode
              // }).includes('F2F') == true) {
              //   //this._Router.navigate(['CostEstimation/CompleteTurbineDetails']);
              //   this._Router.navigate(['CostEstimation/ScopeOfsupplyCstEst/GeneralInputs']);
              // }
              // else {
              //   this._Router.navigate(['CostEstimation/EstimateCost']);
              // }
            }
          }
          })
          } else {
            this._ITOLoginService.openSuccMsg(resp.successMsg);
            //alert(resp.successMsg);
          }          
        })
      })
    }
    else {
      this._ITOLoginService.openSuccMsg("Flange to flange /DBO MEchanical /DBO Electrical/Control & Instrumentation is mandatory option, please select that to proceed ..");
      //alert("Flange to flange is mandotory option, please select that to proceed ..");
      this.hidespinner = true;
    }
  }
//To navigate edit quotaion page on click of back button
backButton(){
  this._Router.navigate(['/EditQuot']);
} 

}
