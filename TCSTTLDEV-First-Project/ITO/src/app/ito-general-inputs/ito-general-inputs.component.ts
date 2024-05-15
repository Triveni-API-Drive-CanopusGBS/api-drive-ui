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
import { ITOGeneralInputsService } from './ito-general-inputs.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { NgForm } from '@angular/forms';
import { Directive, Input } from '@angular/core';
import { NG_VALIDATORS, Validator, FormControl } from '@angular/forms';
import { dboClass } from '../dbomechanical/dbomechanical';


@Component({
  selector: 'app-ito-general-inputs',
  templateUrl: './ito-general-inputs.component.html',
  styleUrls: ['./ito-general-inputs.component.css']
})
export class ItoGeneralInputsComponent implements OnInit {

  dboGovernerListLhs:Array<any>=[];
  dboGovernerList: Array<any>=[];
  enableEleBtn: boolean =  false;
  mechAuxCheck: Array<boolean> =[];
  auxCheck: Array<boolean> = [];
  senresp: any;
  dboFormDataaa: any;
  sampleScope: Array<any> = [];
  selValList: Array<any> = [];
  errorArray: Array<any> =[];
  f2fCache: any;
  f2fCacheList: Array<any> = [];
  f2fCacheFil: Array<any> = [];
  inputarray: Array<any> = [];
  quotId: number;
  quationType: string;
  hidespinner: boolean = true;
  defaultValues: Array<any> =[];
  defaulVales: Array<any> = []; //To store dropdowun value when default flag is true
  f2fCacheFilItNm: Array<any> =[];
  stdOffrflag: boolean = false;
  generalInput: string = 'generalInputList';
  generalInputList: Array<any> = [];
  genInputList: Array<any> = [];
  successMsg: string = '';
  GIArray: Array<any> = [];
  fwRt: number;
  scopeOfSuply: Array<any> =[];
  selSOSList: Array<any> = [];
  selSOSListNew: Array<any> = [];
  f2fCacheFilUpd: Array<any> = [];
  dboClass: dboClass;
  errMsg: Array<any> = [];
  errDisplay: Array<boolean> = [];
  message: boolean = false;
  errMsgEle: Array<any> = []; //To display error message for eleFilterList
  errDisplayEle: Array<boolean> = []; // To dispaly error msg div for eleFilterList
  enableUpdate: boolean = false; //To enable update button
  showEnable: boolean = false;
  eleFilterData: any; //To store response from getEleFilter
  dboEleFilterList: Array<any> = []; //To store dboEleFilterList from getEleFilter
  newArray: Array<any> = []; //To store if default flag true
  saveEleFilterList: Array<any> = []; //to store saveEleFilterList from saveELEFilter endpoint
  newArryUpd: Array<any> = []; //To store newArray temp
  generalEleFliterInputList: Array<any> = []; //to push data according to dboform input required
  dispFil: boolean = false;
  minVal: string; // To store min val
  maxVal: string; // To store maxVal
  enableSub: boolean = false; //To enable submit button for general inputs
  enableEleSub: boolean = false; //To enable submit button for eleFilter list
  childvaluesnew: Array<any> = [];
  childvalues: Array<any> = [];
  temparray: Array<any> = [];
  childaddon:Array<any>=[];
  typOfPanel: string; // To stoer Type of Panel value
  typeOfTurbineStart:string;// To store of typeOfTurbineStart
  make: string; //To store make value from get ele filter data
  dutySync: string; //To store suty sync datd from get ele fiter data
  dboEleFilterListNew: Array<any> = []; //To store filtered dboEleFilterList (filter based on subDropDown value)
  dboEleFull: string = 'dboEleFull';
  scopeOfSuplyChe:Array<any>=[];
  backBtn: boolean = false;
  checkQty: Array<boolean> = [];
  qotNumber: any;
  ecBeanList: Array<any> = [];
  ECData: string = 'ecKey';
  ecBean: any;
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
  exclusion: string = 'exclusion'; // local storage value
  quaility: string = 'quaility'; // local storage value
  scopeOf: string = 'scopeOf'; // local storage value
  supplier: string = 'supplier'; // local storage value
  tender: string = 'tender'; // local storage value
  terminal: string = 'terminal'; // local storage value
  dboEleCIAuxFull: string = 'dboEleCIAuxFull';
  dboEleCIFull: string = 'dboEleCIFull';
  dboEleAuxFull: string = 'dboEleAuxFull';
  dboPerfLoc: string = 'dboPerf';
  F2FTurbine: string = 'F2FTurbine';
  dboMechAuxLoc: string = 'dboAuxMech';
  MechExpScope: string = 'MechExpScope';
  EleExtdScope: string = 'EleExtdScope';
  EleCiExtdScope: string = 'EleCiExtdScope';
  dboMechFull: string = 'dboMechFull';
  dboMechLoc: string = 'dboMech';
  dboComSecALoc: string = 'comSecA'; // local storage value
  checkEnableUpd: boolean = false;
  varCost: any;
  sparesCost: any;
  projectCost: any;
  packageBean: any;
  customerData: any;
  consultantData: any;
  endUserData: any;
  f2fQuesData: any;
  transDetailList: Array<any> = [];
  custdetails: string = 'custdetails';
  currentRoleId: string = 'selRoleId';
  custCode: boolean = false;
  qotNumb: any;
  onLineBomExcel: any;
  otherCostsOneLine: any;
  F2F: boolean = false;
  MECH: boolean = false;
  ELE: boolean = false;
  CI: boolean = false;
  TECH: boolean = false;
  COMR: boolean = false;
  changeList:Array<any> = []; //To store general input list from changeGeneralList endpoint
  changeListLhs:Array<any> = []; //To store filterd general input list based on default value from changeGeneralList endpoint
  defaulValesNw: Array<any>= []; //To store the defaoukt value from change List Lhs
  defaulValesNww:Array<any>=[];
  sucessCode:number=0;
  errorMessage:string=null;
  succDisable: boolean = false; //To enable or disable generation offer button based on successcode
  section2: string = 'section2'; // local storage value
  selVal: string = '';
  mainSave:boolean=true;
  mainSave2:boolean=true;

  constructor(private _Router: Router, private _ITOGeneralInputsService: ITOGeneralInputsService, private _ITOturbineConfigService: ITOturbineConfigService,  private ITOScopeOfSupplyService: ITOScopeOfSupplyService,
    private _ITOcustomerDetailsService: ITOcustomerDetailsService, private _ITOendUserDetailsService: ITOendUserDetailsService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOHomePageService: ITOHomePageService, private _ITOLoginService: ITOLoginService, private _ITOeditQoutService: ITOeditQoutService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService) {

      this._ITOGeneralInputsService.getDboFormData().subscribe(res => {
        console.log(res);
        this.dboFormDataaa = res;
      this.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.quationType=this._ITOcustomerRequirementService.typeOfQuotNm;
      
      console.log(this.quotId);
      console.log(this.storage.get(this.generalInput));
      this._ITOeditQoutService.button1=false;
      this._ITOeditQoutService.button2=true;
      this._ITOeditQoutService.button3=false;
      this._ITOeditQoutService.button4=false;
      this._ITOeditQoutService.button5=false;
      this._ITOeditQoutService.button6=false;
      this._ITOeditQoutService.button7=false;
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=false;
      if(this.storage.get(this.generalInput) != null){
       // this.mainSave2=false;
      this.generalInputList[this.generalInput] = this.storage.get(this.generalInput);      
      this.scopeOfSuply = this.storage.get(this.generalInput).scopeOfsupp;
      this.f2fCacheFil = this.storage.get(this.generalInput).genInputList;
      this.genInputList = this.storage.get(this.generalInput).genInputList;
      //Used when update button is implemented
      if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm != "Standard" || this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm != "Non Standard"){
      if(this.storage.get(this.generalInput).genInputList.length>0 || this.storage.get(this.generalInput).saveEleFilterList.length>0){
        this.checkEnableUpd = true;
      }
    }
      for(let l =0; l<this.f2fCacheFil.length; l++){        
        console.log(this.f2fCacheFil[l].genInNm, this.f2fCacheFil[l].categoryValName, this.f2fCacheFil[l].quantity);
        if(this.f2fCacheFil[l].genType == "INPUT"){
          this.f2fCacheFil[l].categoryValName = this.f2fCacheFil[l].categoryValCode;
        }
        if(this.f2fCacheFil[l].genType == "DROPDOWN"){
          this.f2fCacheFil[l].defaultFlagNew = 1;
        }
      }
  
      if(this._ITOeditQoutService.checkEdit == false){
        this.backBtn = true;
      }
      if(this._ITOcustomerRequirementService.editFlag == true){
        this.scopeOfSuplyChe = this.scopeOfSuply.map(item => item.scopeCode);
      }
      else
      {
        this.scopeOfSuplyChe = this.ITOScopeOfSupplyService.sampleScope.map(item => item.scopeCode);
      }
      if(this._ITOcustomerRequirementService.editFlag == true && this.scopeOfSuplyChe.length==0)
      {
        this.scopeOfSuplyChe = this.ITOScopeOfSupplyService.sampleScope.map(item => item.scopeCode);
      }
      // if(this.scopeOfSuplyChe.includes("ELE")){
        if(this.storage.get(this.generalInput).saveEleFilterList.length != 0){
          this.typOfPanel = '';
          this.enableEleBtn=true;
          this.newArray = this.storage.get(this.generalInput).saveEleFilterList;
          for(let n=0; n<this.f2fCacheFil.length; n++){
            if(this.f2fCacheFil[n].genInCd == 'DRIVE_TYP'){
              this.typOfPanel = this.f2fCacheFil[n].categoryValCode;
            }
          }
          this.getEleFil("111");
          this.mainSave2=false;
        }
    // }
    // else{
    //   if(this.storage.get(this.generalInput).saveEleFilterList.length != 0){
    //     this.enableEleBtn=false;
    //     this.storage.get(this.generalInput).saveEleFilterList = [];
    //   }
    // }
      
      console.log(this.f2fCacheFil);
      if(this.scopeOfSuply.length != 0){
        this.ITOScopeOfSupplyService.sampleScope = this.scopeOfSuply;
      }
    }
    console.log(this.scopeOfSuply);
  //   for(let n=0; n<this.scopeOfSuply.length; n++){
  //   this.selSOSList.push(this.scopeOfSuply[n].scopeCode);
  //  }    
  //   console.log(this.selSOSList);
      this._ITOGeneralInputsService.getGeneralInput(this.quotId).subscribe(reess => {
        console.log(reess);
          this.f2fCache = reess;
         this.f2fCacheList = reess.dropDownColumnvalues.f2fCacheList.F2fCacheType;
         console.log(this.f2fCacheList);
         for(let l=0;l<this.f2fCacheList.length; l++){
           if(this.f2fCacheList[l].genType == 'INPUT' && (this.f2fCacheList[l].categoryValName == "NULL " || this.f2fCacheList[l].categoryValName == "NULL")){
              if(this.f2fCacheList[l].genInCd == 'TYP_OF_DRIVEN_EQUIP'){
                this.f2fCacheList[l].categoryValName = 'Alternator';
                this.f2fCacheList[l].categoryValCode = 'Alternator';
              }
              else if(this.f2fCacheList[l].genInCd != 'AGENCY_COM')
              {
                this.f2fCacheList[l].categoryValCode = 0;
                this.f2fCacheList[l].categoryValName = 0; 
              }             
           }
         }
         for(let g=0; g<this.ITOScopeOfSupplyService.sampleScope.length; g++){
           for(let j=0; j<this.f2fCacheList.length; j++){
             if((this.ITOScopeOfSupplyService.sampleScope[g].scopeCode == this.f2fCacheList[j].groupCode) && (this.f2fCacheList[j].defaultFlagNew == 1 || this.f2fCacheList[j].genType == "INPUT")){
               this.f2fCacheFilItNm.push(this.f2fCacheList[j]);
             }
           }
         }
         console.log(this.f2fCacheFilItNm);
         // BEFORE
        //  for(let u=0; u< this.f2fCacheFilItNm.length; u++){
        //   this.selSOSList.push(this.f2fCacheFilItNm[u].groupDescription);
        // }
         //AFTER
         for(let u=0; u< this.f2fCacheList.length; u++){
           if(this.f2fCacheList[u].dispInd == 1){
          this.selSOSList.push(this.f2fCacheList[u].groupDescription);
           }
        }
        console.log(this.selSOSList);
        var unique = this.selSOSList.filter(function(elem, index, self) {
         return index === self.indexOf(elem);          
     })
     this.selSOSListNew = unique;
     console.log(this.selSOSListNew);

      if(this.f2fCacheFil.length == 0){
          for(let j=0;j< this.f2fCacheList.length; j++){
            console.log(this.f2fCacheList[j].defaultFlagNew,this.f2fCacheList[j].genInId, this.f2fCacheList[j].genInNo, this.f2fCacheList[j].genInNm, this.f2fCacheList[j].genInNo, this.f2fCacheList[j].categoryValName, this.f2fCacheList[j].dispInd);
            if(this.f2fCacheList[j].defaultFlagNew == 1 || this.f2fCacheList[j].genType == "INPUT"){
              this.f2fCacheFil.push(this.f2fCacheList[j]);
            }
          }
        }        
        else{
          this.f2fCacheFilUpd = this.f2fCacheFil;
          this.f2fCacheFil = [];
          //BEFORE
          // for(let k=0; k<this.f2fCacheFilItNm.length; k++){
          //     if(this.f2fCacheFilItNm[k].defaultFlagNew == 1 || this.f2fCacheFilItNm[k].genType == "INPUT"){
          //       this.f2fCacheFil.push(this.f2fCacheFilItNm[k]);
          //     }
          //  }
          //AFTER
          for(let k=0; k<this.f2fCacheList.length; k++){
            if(this.f2fCacheList[k].defaultFlagNew == 1 || this.f2fCacheList[k].genType == "INPUT"){
              this.f2fCacheFil.push(this.f2fCacheList[k]);
            }
         }
           for(let v=0;v<this.f2fCacheFilUpd.length; v++){
             for(let x=0; x<this.f2fCacheFil.length; x++){
               if(this.f2fCacheFilUpd[v].genInNm == this.f2fCacheFil[x].genInNm){
                this.f2fCacheFil[x] = this.f2fCacheFilUpd[v];
               }
             }
           }
        }
          console.log(this.f2fCacheFil);
          
          // if(this.selSOSListNew.includes("DBO Electrical")){
            if(this.storage.get(this.generalInput).saveEleFilterList.length == 0){
            this.typOfPanel = '';
            this.enableEleSub = false;
           // this.mainSave2=false;
          for(let j=0; j<this.f2fCacheFil.length; j++){
            if(this.f2fCacheFil[j].genInCd == "DRIVE_TYP"){
              this.typOfPanel = this.f2fCacheFil[j].categoryValCode;
            }
          }this.getEleFil("980");
        }
      // }else{
      //   this.enableEleSub = true;
      //   if(this.newArray.length != 0){
          
      //   }
      // }
    
          this.f2fCacheFilUpd = [];
          this.f2fCacheFilUpd = this.f2fCacheFil;
          this.mechAuxCheck=[];
          this.checkQty=[];
          this.auxCheck=[];
          console.log(this.f2fCacheFil);
          for(let m=0; m<this.f2fCacheFil.length; m++){
            if(this.f2fCacheFil[m].quantity > 0){
            for(let n=0;n<this.f2fCacheList.length; n++){
             if(this.f2fCacheFil[m].quantity > 0 && this.f2fCacheFil[m].categoryValName ==  this.f2fCacheList[n].categoryValName){
              this.checkQty[this.f2fCacheList[n].genInId] = true;
             this.mechAuxCheck[this.f2fCacheList[n].genInId] = true;
             this.auxCheck[this.f2fCacheList[n].genInId] = true;
              this.f2fCacheList[n].quantity = this.f2fCacheFil[m].quantity;
             }
            }
            }
          }
        console.log(this.checkQty);
              for(let f = 0; f< this.f2fCacheFil.length; f++){
                console.log(this.f2fCacheFil[f].categoryValCode, this.f2fCacheFil[f].categoryValName, this.f2fCacheFil[f].defaultFlagNew);
              if (this.f2fCacheFil[f].defaultFlagNew == 1 || this.f2fCacheFil[f].genType == "INPUT") {
                this.defaultValues[this.f2fCacheFil[f].genInId] = this.f2fCacheFil[f].categoryValName;
              }
              if(this.f2fCacheFil[f].categoryValName == null){
                this.enableSub = true;
                this.mainSave2=false;
              }else{
                this.enableSub = false;
              }
          }console.log(this.defaultValues);
          
          let checkCatValNm = this.f2fCacheFil.map(item => item.categoryValName);
          if(checkCatValNm.includes("NULL") || checkCatValNm.includes("") || checkCatValNm.includes("NULL ")){
            this.enableSub = false;
          }else{
            this.enableSub = true;
            this.mainSave2=false;


          }
        })
        this.saveInLocal(this.generalInput, { 
          genInputList:  this.genInputList, scopeOfsupp: this.scopeOfSuply, saveEleFilterList: this.newArray                   
          });
      }) 
    }

  ngOnInit() {
  }
 /**
   * 
   * @param options dropdown value
   * @param selVal seleced option
   * @param i index of the drop down
   */
  optionSel(selVal, selArr) {
    this.mainSave2=true;
    this.succDisable = true;
    console.log(selVal, selArr);
    this.defaultValues=[];
    this.typOfPanel = '';
    this.selVal = '';
    for(let m=0; m< this.f2fCacheList.length; m++){
      if((selArr.genInNm == this.f2fCacheList[m].genInNm) && (selVal == this.f2fCacheList[m].categoryValName)){
        for(let v=0; v<this.f2fCacheFilUpd.length; v++){
          if(this.f2fCacheFilUpd[v].genInNm == this.f2fCacheList[m].genInNm){
            this.f2fCacheFilUpd[v] = this.f2fCacheList[m];
            this.selVal = this.f2fCacheList[m].categoryValCode;
            console.log(this.f2fCacheFilUpd[v]);
          }
        }
      }
    }
    for(let c=0; c<this.f2fCacheFilUpd.length; c++){
      this.defaultValues[this.f2fCacheFilUpd[c].genInId] = this.f2fCacheFilUpd[c].categoryValName;
    }
   
      for(let j=0;j<this.f2fCacheList.length;j++){
        if(this.f2fCacheList[j].genInCd=="TYP_OF_DRIVEN_EQUIP")
        {
          if(this.selVal == "DT"){
          this.f2fCacheList[j].categoryValName = '';
          this.f2fCacheList[j].categoryValCode = '';
          this.enableSub=false;
        }else{
          this.f2fCacheList[j].categoryValName = 'Alternator';
          this.f2fCacheList[j].categoryValCode = 'Alternator';
           this.enableSub=true;
        }
      }
    }
      for(let j=0;j<this.f2fCacheFilUpd.length;j++)
      {
        if(this.f2fCacheFilUpd[j].genInCd=="TYP_OF_DRIVEN_EQUIP")
        {
          if(this.selVal == "DT"){
          this.f2fCacheFilUpd[j].categoryValName = '';
          this.f2fCacheFilUpd[j].categoryValCode = '';
        }else{
          this.f2fCacheFilUpd[j].categoryValName = 'Alternator';
          this.f2fCacheFilUpd[j].categoryValCode = 'Alternator';
        }
      }
      }
    if(this.selVal=="HT")
    {
      for(let j=0;j<this.newArray.length;j++)
      {
        if(this.newArray[j].filterCd=="EX_TYP")
        {
          this.newArray.splice(j,1);
        }
      }
      for(let j=0;j<this.newArryUpd.length;j++)
      {
        if(this.newArryUpd[j].filterCd=="EX_TYP")
        {
          this.newArryUpd.splice(j,1);
        }
      }
      this.changeList=[];
      this.changeListLhs=[];
    }

    if(this.selVal=="HT" || this.selVal=="LT" || this.selVal=="DT")
    {
      for(let j=0;j<this.newArray.length;j++)
      {
        if(this.newArray[j].filterCd=="GOV_TYP")
        {
          this.newArray.splice(j,1);
        }
      }
      for(let j=0;j<this.newArryUpd.length;j++)
      {
        if(this.newArryUpd[j].filterCd=="GOV_TYP")
        {
          this.newArryUpd.splice(j,1);
        }
      }
      this.dboGovernerList=[];
      this.dboGovernerListLhs=[];
    }
    
    if(selArr.genInCd == "DRIVE_TYP"){
      this.typOfPanel = this.selVal;
      this.errMsgEle = [];
      this.getEleFil("980");
    }   
    
   // let temp=this.childvaluesnew;

    //this.childvaluesnew=[];
    //this.childvaluesnew=temp;
  ///  this.childaddon=[];
    
   
    // for(let v=0; v<this.f2fCacheList.length; v++){
    //   if(this.f2fCacheList[v].categoryValName == selVal){
    //     this.selValList.push(this.f2fCacheList[v]);
    //   }
    // }   
    //  this.sampleScope = this.selValList.map(item => item.id);
    // let selIds = [];
    // selIds = this.selValList.map(item => item.ssId);
    // console.log(this.sampleScope);
        
  }

  optionSelNew(selVal1, selArr1){
    this.mainSave2=true;

    this.succDisable = true;
    console.log(selVal1, selArr1);
    let tempdustsync="";
    let tempid=0;

    for(let m=0; m< this.dboEleFilterList.length; m++){
      if((selArr1.itemName == this.dboEleFilterList[m].itemName) && (selVal1 == this.dboEleFilterList[m].colValNm)){
        tempdustsync=this.dboEleFilterList[m].subColValNm;
        this.temparray[this.dboEleFilterList[m].eleFilterId]=this.dboEleFilterList[m].subColValNm;

        for(let v=0; v<this.newArryUpd.length; v++){
          if(this.newArryUpd[v].itemName == this.dboEleFilterList[m].itemName){
            this.newArryUpd[v] = this.dboEleFilterList[m];
            console.log(this.newArryUpd[v]);
          }
        }
      }
    }

    if(selArr1.filterCd == "ALT_MAKE"){
      this.make = selVal1;
     
      this.getChange("100");
    }else if(selArr1.filterCd == "DT_GRID"){
      this.dutySync = tempdustsync;
      
      this.getChange("100");
      this.getGover("100");
    }else if(selArr1.filterCd == "TYP_TURB_START"){
      this.typeOfTurbineStart=selVal1;
      this.getGover("200");
    }
  }
  radioSel(radVal, nm){
    this.mainSave2=true;

    this.succDisable = true;
    console.log(radVal, nm);
    for(let f=0; f<this.f2fCacheFilUpd.length; f++){
      if(nm.genInNm == this.f2fCacheFilUpd[f].genInNm){
        this.f2fCacheFilUpd[f] = nm;
      }
    }
    console.log(this.f2fCacheFilUpd);
  }
  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.GIArray[key] = this.storage.get(key);
  }
  checkForChanges(value,i,valueArr) {
    this.mainSave2=true;

    this.succDisable = true;
    console.log(value,i, valueArr);
    
    if(valueArr.genInCd=="FLOW_RATE"){
      if(value>150){
        this.errMsg[i] = [];
        this.errDisplay[i] = true;
        this.errMsg[i] = "NOTE: Flow Rate should be less than or equal to 150";
        valueArr.categoryValCode=null;
        valueArr.categoryValName = null;    
          value=null;
      }else{
        this.errDisplay[i] = false;
        this.errMsg[i] = [];
      }
    }

    if(valueArr.genInCd=="NO_OF_BLEED"){
      if(value>6){
        this.errMsg[i] = [];
        this.errDisplay[i] = true;
        this.errMsg[i] = "NOTE: No of bleed should be 0-6";
        valueArr.categoryValCode=null;
        valueArr.categoryValName = null;    
          value=null;
      }else{
        this.errDisplay[i] = false;
        this.errMsg[i] = [];
      }
    }
    
    if(valueArr.genInCd=="AGENCY_COM"){
      if(value>10){
        this.errMsg[i] = [];
        this.errDisplay[i] = true;
        this.errMsg[i] = "NOTE: No of Agency should be 0-10";
        valueArr.categoryValCode=null;
        valueArr.categoryValName = null;    
          value=null;
      }else{
        this.errDisplay[i] = false;
        this.errMsg[i] = [];
      }
    }

    if(valueArr.genInCd=="CEP"){
      if(value>250){
        this.errMsg[i] = [];
        this.errDisplay[i] = true;
        this.errMsg[i] = "NOTE: No of CEP should be less than or equal to 250";
        valueArr.categoryValCode=null;
        valueArr.categoryValName = null;    
          value=null;
      }else{
        this.errDisplay[i] = false;
        this.errMsg[i] = [];
      }
    }

    for(let m=0; m< this.f2fCacheFilUpd.length; m++){
      if(valueArr.genInNm == this.f2fCacheFilUpd[m].genInNm){
        this.f2fCacheFilUpd[m] = valueArr;
        this.f2fCacheFilUpd[m].categoryValName = value;
        this.f2fCacheFilUpd[m].categoryValCode = value;
      }
    }
    console.log(this.f2fCacheFilUpd);
    let sampleCheck = this.f2fCacheFilUpd.map(item => item.categoryValName); 
    if(sampleCheck.includes("") || sampleCheck.includes("NULL") || sampleCheck.includes("NULL ") || sampleCheck.includes(null)){
      this.enableSub = false;
    }else{
      this.enableSub = true;
    }
  }
  saveGeneralInp(){
    // console.log(others.form.valid);
    this.sampleScope = [];
    console.log(this.sampleScope);
    for (let l = 0; l < this.f2fCacheFilUpd.length; l++) {    
      // this.sampleScope[l] = (this.f2fCacheFilUpd[l].genInId+","+ this.f2fCacheFilUpd[l].categoryValCode);
      this.dboClass = new dboClass();
      this.dboClass.genInId = this.f2fCacheFilUpd[l].genInId;
      this.dboClass.categoryValCode = this.f2fCacheFilUpd[l].categoryValCode;
      this.dboClass.quantity = 1;
      this.dboClass.defaultFlagNew = this.f2fCacheFilUpd[l].defaultFlagNew;
      this.dboClass.dispInd = this.f2fCacheFilUpd[l].dispInd;
      this.dboClass.errorMsg = this.f2fCacheFilUpd[l].errorMsg;
      this.sampleScope.push(this.dboClass);
    }
    console.log(this.sampleScope);
    this.dboFormDataaa.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById;
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.savedGeneralInputList = this.sampleScope;
    console.log(this.dboFormDataaa);
  //   for(let c=0; c<this.f2fCacheFilUpd.length; c++){
  //   if(this.f2fCacheFilUpd[c].categoryValCode == "NULL" || this.f2fCacheFilUpd[c].categoryValCode == 0 || this.f2fCacheFilUpd[c].categoryValCode == undefined){
  //     this.showEnable = true;
  //   }else{
  //     this.showEnable = false;
  //   }
  // }
    // if(this.showEnable == false){
    this.hidespinner = false
    this._ITOGeneralInputsService.saveGeneralInput(this.dboFormDataaa).subscribe(resppp =>{
      console.log(resppp);
      console.log(resppp.successMsg);
      // if(resppp.successCode != 0) {
      //   this._ITOLoginService.openSuccMsg(resppp.successMsg);
      // }
      // else
       if (resppp.successCode == 0) {
        this.message = true;
        this.successMsg = "Saved Successfully";
        this.mainSave2=false;
        //this.ITOScopeOfSupplyService.checkScopes(resppp.saveBasicDetails.scopeOfSupplyList);
       // this.saveInLocal(this.generalInput, this.ScopeOfSupply);
        //this._ITOcustomerRequirementService.saveBasicDet = resppp.saveBasicDetails;
        //console.log(this._ITOcustomerRequirementService.saveBasicDet);
        // this._ITOcustomerRequirementService.sendMessage(this.quotationNumber);
        // if()
       // this._ITOcustomerRequirementService.sendbtnStatus(true, false, true, false, false, false, false);
        // this.hidespinner = false;
        let sampleScoBut = this.ITOScopeOfSupplyService.sampleScope.map(item => item.scopeCode);
        this.genInputList = resppp.saveGeneralInputList;
        if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
          for(let k=0;k<this.genInputList.length; k++){
            if(this.genInputList[k].genInId == 9){
              this._ITOGeneralInputsService.enabelMechAux = false;
            }else if(this.genInputList[k].genInId == 8){
              this._ITOGeneralInputsService.enabelMechAux = true;
            }
          }
        }
          if((this.checkEnableUpd == true)){
            this.enableUpdate = true;
            // this.stdOffrflag = true;
          }
        else if(this.checkEnableUpd == false){         
          
          // if(sampleScoBut.includes('ELE')){
            // this.enableEleBtn = true;
            // if(this._ITOcustomerRequirementService.editFlag == true){
            //   this.stdOffrflag= true;
            // }
        //  this.getEleFil("980");
          // }else{
            this.navStd();
        // if (this._ITOcustomerRequirementService.editFlag == true) {
        //   this.stdOffrflag = true;  
        // }
        // }
        }   
        this.dboEleFil();
        this.saveInLocal(this.generalInput, { 
          genInputList: this.genInputList, scopeOfsupp: this.scopeOfSuply, saveEleFilterList: this.saveEleFilterList,newArray :this.newArryUpd             
          });
      }
     this.hidespinner = true;
    //  this.dboEleFil();
    });
  // }else{
  //   this._ITOLoginService.dialogMsgApp = true;
  //   this._ITOLoginService.dialogMsgVal = "Enter all required fields to proceed";
  // }
}

//submit button based on inputs
subFinal(){
  // if(this.selSOSListNew.includes("DBO Electrical")){
    this.hidespinner=true;

    this.saveGeneralInp();
    // this.dboEleFil();

    this.hidespinner=false;
//   }else{
//     this.saveGeneralInp();
// }
}

//TO update general inputs
updateGI(){
  if(this.storage.get(this.generalInput) != null){
    if(this.storage.get(this.generalInput).genInputList.length>0 || this.storage.get(this.generalInput).saveEleFilterList.length>0){
      this.dboFormDataaa.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById;
        this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        console.log(this.dboFormDataaa);
        this.hidespinner = false; 
        this._ITOGeneralInputsService.getUpdateGeneralInput(this.dboFormDataaa).subscribe(responn =>{
          console.log(responn);
          if(responn.successCode == 0){
            this.message = true;
            this.successMsg = "Updated Successfully";
            if(this._ITOeditQoutService.checkEdit==false)
   {
    this._Router.navigate(['/EditQuot']);    
   }else{
            if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard" || this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Non Standard"){
              this.navigateee();
            }else{
              this.navStd(); 
            }
          }
          }
          this.hidespinner = true; 
        });
      }
    }
}

//To navigation which page and standard offer button enable
navStd(){
  
  if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard" || this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Non Standard"){
    if(this.checkEnableUpd == false){
    this.stdOffrflag = true;
    }
  }
  else
  {
    console.log(this.ITOScopeOfSupplyService.sampleScope)
    let m: Array<any> = this.ITOScopeOfSupplyService.sampleScope;
    if(this._ITOcustomerRequirementService.editFlag == true)
    {
      this._ITOcustomerRequirementService.editFlag = false;
      this._Router.navigate(['/EditQuot']);
    }else{
      let sampleScopeBut = this.ITOScopeOfSupplyService.sampleScope.map(item => item.scopeCode);      
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
        this.TECH = true;
      }
      if(sampleScopeBut.includes('TC') || sampleScopeBut.includes('PF') || sampleScopeBut.includes('VC') || sampleScopeBut.includes('PR')){
        this.COMR = true;
      }
      this._ITOcustomerRequirementService.sendbtnStatus(true, true, this.F2F, this.MECH, this.ELE, this.CI, this.TECH, this.COMR);
      if(this.F2F == true){
        this._Router.navigate(['CostEstimation/CompleteTurbineDetails/turbineConfig']);
      }else if(this.MECH == true){
        this._Router.navigate(['CostEstimation/Mechanical/DBOMechanical']);
      }else if(this.ELE ==  true){
        this._Router.navigate(['CostEstimation/Electrical/DBOElectrical']);
      }else if(this.CI == true){
        this._Router.navigate(['CostEstimation/ControlInstrumentation/ItoControlInstrumentation']);
      }    
    }
  
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
  }
}
stdOffer(){
  this._ITOcustomerRequirementService.getAdminForm().subscribe(resp => {
    console.log(resp);
    resp.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    resp.modBy = this._ITOcustomerRequirementService.saveBasicDet.modifiedById;
    //resp.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById;
    console.log(resp);
    this.senresp = resp;
    this.hidespinner = false;
    this._ITOcustomerRequirementService.getStdOffer(this.senresp).subscribe(respon => {
      console.log(respon);
      this.message = true;
      this.mainSave=false;
      this.successMsg = "Submitted successfully";

      if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
        this.successMsg = "Submitted successfully";

      }
        if(this._ITOeditQoutService.checkEdit==false)
   {
    this._Router.navigate(['/EditQuot']);    
   }
   else{
     this.navigateee();  
   } 
this.hidespinner = true; 
  });
  });
    
}

navigateee(){
  if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard" || this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Non Standard"){
    if(this.checkEnableUpd == false){
    this.stdOffrflag = true;
    }
  }
   if(this._ITOeditQoutService.checkEdit==false)
   {
    this._Router.navigate(['/EditQuot']);    
   }
    console.log(this.ITOScopeOfSupplyService.sampleScope)
    let m: Array<any> = this.ITOScopeOfSupplyService.sampleScope;
    this.storage.remove(this.ECData);
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
    this.qotNumber = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this._ITOeditQoutService.getQuotation(this.qotNumber).subscribe(res => {
      console.log(res);
      this._ITOcustomerRequirementService.editFlag=true;
     
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
      let sampleScopeBut = this.ITOScopeOfSupplyService.sampleScope.map(item => item.scopeCode);
      if(this._ITOcustomerRequirementService.editFlag==true &&  this.ITOScopeOfSupplyService.sampleScope.length==0 ) 
      {
        this.scopeOfSuplyChe = this.scopeOfSuply.map(item => item.scopeCode);
      }     
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
      if(sampleScopeBut.includes('TC') || sampleScopeBut.includes('PF') || sampleScopeBut.includes('VC') || sampleScopeBut.includes('PR')){
        this.COMR = true;
      }
      this._ITOcustomerRequirementService.sendbtnStatus(true, true, this.F2F, this.MECH, this.ELE, this.CI, this.TECH, this.COMR);
      if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
        this._Router.navigate(['/CostEstimation/Mechanical/DboMechAuxialries']);
      }else{
      if(this.F2F == true){
        this._Router.navigate(['CostEstimation/CompleteTurbineDetails/turbineConfig']);
      }else if(this.MECH == true){
        this._Router.navigate(['/CostEstimation/Mechanical/DboMechAuxialries']);
      }else if(this.ELE ==  true){
        this._Router.navigate(['CostEstimation/Electrical/DBOElectrical']);
      }else if(this.CI == true){
        this._Router.navigate(['CostEstimation/ControlInstrumentation/ItoControlInstrumentation']);
      }else if(sampleScopeBut.includes('PHM')){
        this._Router.navigate(['CostEstimation/Techinal/ItoPerformance']);
      } 
    }    
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

checkForEleChanges(valu,i,valueAr) {
  this.mainSave2=true;

this.succDisable = true;
  console.log(valu,i, valueAr);
  this.minVal = valueAr.minVal;
  this.maxVal = valueAr.maxVal;
  let min1=0;
  let min2=0;
  let max1=0;
  let max2=0;
  for(let m=0; m< this.newArryUpd.length; m++){
    if(this.newArryUpd[m].filterCd=='ALT_RT')
    {
min1=this.newArryUpd[m].minVal;
max1=this.newArryUpd[m].maxVal;


    }
   
    if(this.newArryUpd[m].filterCd=='ALT_VOLT_K')
    {
      min2=this.newArryUpd[m].minVal;
      max2=this.newArryUpd[m].maxVal;
    }
    if(this.newArryUpd[m].filterCd=='ALT_VOLT_V')
    {
      min2=this.newArryUpd[m].minVal;
      max2=this.newArryUpd[m].maxVal;
    }
    
  }
  if(valueAr.itemName=="Rated Voltage (KV)" || valueAr.itemName=="Rated Voltage (V)" || valueAr.itemName=="Alternator Voltage (KV)" || valueAr.itemName == "Alternator Voltage (V)"){
    if(this.minVal > valu || valu > this.maxVal){
      console.log("test");
      console.log(valu);
      this.errMsgEle[i] = [];
      this.errDisplayEle[i] = true;
      this.errMsgEle[i] = "NOTE: Alternator Rated Voltage should be range accessible " + valueAr.minVal + " - " + valueAr.maxVal;
     // valueAr.colValCd=null;
      //valueAr.colValNm = null;   
     // valu = null; 
      console.log(valu); 
            
    }else{
      this.errDisplayEle[i] = false;
      this.errMsgEle[i] = [];
    }
  }else if(valueAr.filterCd=='ALT_RT' || valueAr.filterCd=='ALT_RT' ){
    if(this.minVal > valu || valu > this.maxVal){
      console.log("test12");
       console.log(valu);
      this.errMsgEle[i] = [];
      this.errDisplayEle[i] = true;
      this.errMsgEle[i] = "NOTE: Alternator Rated Output should be range accessible " + valueAr.minVal + " - " + valueAr.maxVal;
     // valueAr.colValCd=null;
      //valueAr.colValNm=null;  
      //valu = null; 
      console.log(valu); 
       
       }else{
        this.errDisplayEle[i] = false;
      this.errMsgEle[i] = [];
    }
  }
  for(let m=0; m< this.newArryUpd.length; m++){
    if(valueAr.itemName == this.newArryUpd[m].itemName){
      this.newArryUpd[m] = valueAr;
      this.newArryUpd[m].colValCd = valu;
      this.newArryUpd[m].colValNm = valu;
      console.log( this.newArryUpd[m]);
    }
  }
  let counter =0;
  let counter1=0;
  
        
  console.log(this.newArryUpd);
  for(let m=0; m< this.newArryUpd.length; m++){
    if(this.newArryUpd[m].filterCd=='ALT_RT' &&  this.newArryUpd[m].colValCd!="NULL")
    {
      if(this.newArryUpd[m].colValCd!=null &&  this.newArryUpd[m].colValCd!="")
      {
        let var1=0;
        var1=Number(this.newArryUpd[m].colValCd);
        if(var1 >= min1  && var1 <= max1   )
  { 
    
      counter=1;
   
  
  }
  
      }

    }
    if(this.newArryUpd[m].filterCd=='ALT_VOLT_K')
    {
      if(this.newArryUpd[m].colValCd!=null &&  this.newArryUpd[m].colValCd!="" &&  this.newArryUpd[m].colValCd!="NULL")
      {
        let var2=0;
        var2=Number(this.newArryUpd[m].colValCd);
        if(var2 >= min2 && var2 <= max2)
  { 
    
      counter1=1;
   
    }
   
  
      }

    }
    if(this.newArryUpd[m].filterCd=='ALT_VOLT_V')
    {
      if(this.newArryUpd[m].colValCd!=null &&  this.newArryUpd[m].colValCd!="" &&  this.newArryUpd[m].colValCd!="NULL")
      {
        let var2=0;
        var2=Number(this.newArryUpd[m].colValCd);
        if(var2 >= min2 && var2 <= max2)
  { 
    
      counter1=1;
   
    }
   
  
      }

    }
  }
  if(counter==1 && counter1==1)
  { 
    this.enableEleSub = true;
  }
  else
  {
    this.enableEleSub = false;
  }
  // let sampleCheckEle = this.newArryUpd.map(item => item.colValCd); 
  // console.log(sampleCheckEle);
  //   if(sampleCheckEle.includes("") || sampleCheckEle.includes("NULL") || sampleCheckEle.includes("NULL ") || sampleCheckEle.includes(null)){
  //     this.enableEleSub = false;
  //   }else{
  //     this.enableEleSub = true;
  //   }
}
optionSel12(selVal1, selArr1)
{
  this.mainSave2=true;

  console.log(selArr1);
 this.succDisable = true;
  //console.log(tempva);
  console.log( this.dboEleFilterList);
  for(let m=0; m< this.dboEleFilterList.length; m++){
    if((selVal1.itemName == this.dboEleFilterList[m].itemName) && (selArr1 == this.dboEleFilterList[m].subColValNm) && (selVal1.colValNm==this.dboEleFilterList[m].colValNm)){
      for(let v=0; v<this.newArryUpd.length; v++){
        if(this.newArryUpd[v].itemName == this.dboEleFilterList[m].itemName){
          this.newArryUpd[v] = this.dboEleFilterList[m];
         
          console.log(this.newArryUpd[v]);
        }
      }
    }
  }
  console.log(selVal1);
  console.log(this.newArryUpd);
// this.temparray[tempva]=selArr1;
  console.log(this.temparray);
  this.dutySync=selArr1;
  
  this.getChange("100");
  this.getGover("100");
 // this.childaddon=selVal1;

}
  //submit on selection of dboEleFilter list dropdown values
  dboEleFil(){
    this.succDisable = false;
    if(this._ITOcustomerRequirementService.editFlag == true){
    this.saveEleFilterList = this.storage.get(this.generalInput).saveEleFilterList;
    for(let k=0; k<this.saveEleFilterList.length; k++){
      for(let m=0; m<this.newArryUpd.length; m++){
        if(this.saveEleFilterList[k].eleFilterId == this.newArryUpd[m].eleFilterId){
          if(this.saveEleFilterList[k].colValCd != this.newArryUpd[m].colValCd){
            this._ITOeditQoutService.dboEleData = [];
            this._ITOeditQoutService.dboEleItmOthers = [];
          }
        }
      }
    }
  }
    //console.log(this.newArryUpd);
    this.saveEleFilterList = [];
    this.generalEleFliterInputList = [];
    this.dboFormDataaa.savedGeneralEleFliterInputList = [];
    for(let g=0; g<this.newArryUpd.length; g++){
      console.log(this.newArryUpd[g].subColValNm);
      this.dboClass = new dboClass();
      this.dboClass.genInId = this.newArryUpd[g].eleFilterId;
      this.dboClass.categoryValCode = this.newArryUpd[g].colValCd;      
      this.dboClass.defaultFlagNew = this.newArryUpd[g].defaultFlagNew;
      this.dboClass.dispInd = this.newArryUpd[g].dispInd;
      this.dboClass.errorMsg = this.newArryUpd[g].errorMsg;
      this.generalEleFliterInputList.push(this.dboClass);
    }
    this.dboFormDataaa.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById;
    this.dboFormDataaa.savedGeneralEleFliterInputList = this.generalEleFliterInputList;
    console.log(this.dboFormDataaa);
   // this.hidespinner = false;
    this._ITOGeneralInputsService.saveEleFilter(this.dboFormDataaa).subscribe(respo => {
      console.log(respo);
      console.log(respo.successMsg, respo.successCode);
      this.saveEleFilterList = respo.saveEleFilterList;
      if(this.saveEleFilterList.length>0 && this.saveEleFilterList[0].altError != null){
        this.errorMessage=this.saveEleFilterList[0].altError;
        this.succDisable= true;
        this.enableUpdate = false;
        this._ITOLoginService.openSuccMsg(this.errorMessage);
        this.hidespinner=true;    
        this.saveInLocal(this.generalInput, { 
          genInputList: this.genInputList, scopeOfsupp: this.scopeOfSuply, saveEleFilterList: this.saveEleFilterList  ,newArray :this.newArryUpd        
          });      
      }else if(respo.successCode == 0){
        this.errorMessage=null;
        this.message = true;
       this.successMsg = "Saved Successfully";     
      console.log(this.saveEleFilterList);
      this.storage.remove(this.dboEleFull);
      if(this.checkEnableUpd == true){
        this.enableUpdate = true;
        // this.stdOffrflag=true;
        this.updateGI();
        this.saveInLocal(this.generalInput, { 
          genInputList: this.genInputList, scopeOfsupp: this.scopeOfSuply, saveEleFilterList: this.saveEleFilterList  ,newArray :this.newArryUpd        
          }); 
          if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
            //this.navStd();
          }
      }else{
      this.saveInLocal(this.generalInput, { 
        genInputList: this.genInputList, scopeOfsupp: this.scopeOfSuply, saveEleFilterList: this.saveEleFilterList  ,newArray :this.newArryUpd        
        });    
      //this.navStd();
      }
      // this.stdOffer();

      }     
         
    //  this.hidespinner = true;
    })
    
  }
  //Get Ele Filter data on click of Submit
  getEleFil(val){
    if(val=="980")
    {
      this.newArray=[];
      this.changeListLhs=[];
      this.dboGovernerListLhs=[];
      this.stdOffrflag = false;
    }
    this.temparray=[];
    for(let j=0;j<100;j++)
    {
      this.temparray.push("1212");
    }
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.typeOfPanel = this.typOfPanel;
    this._ITOGeneralInputsService.getEleFilter(this.dboFormDataaa).subscribe(responnn => {
      console.log(responnn);
      this.dispFil = true;
      this.eleFilterData = responnn;
      this.dboEleFilterList = responnn.dboEleFilterList;
      this.dboEleFilterListNew =[];
      
      for(let j=0;j< responnn.dboEleFilterList.length;j++)
      {
        console.log(this.dboEleFilterList[j].itemName, this.dboEleFilterList[j].filterCd, this.dboEleFilterList[j].colValNm, this.dboEleFilterList[j].defaultFlagNew);
        if(this.dboFormDataaa.typeOfPanel == "LT"){
          if(responnn.dboEleFilterList[j].filterCd == "ALT_RT"){
            responnn.dboEleFilterList[j].colValCd = '';
            responnn.dboEleFilterList[j].colValNm = '';
          }
        }
        this.dboEleFilterListNew.push(responnn.dboEleFilterList[j]);
        
      }     
    
     this.dboFormDataaa.typeOfPanel=this.typOfPanel;
     this.dboFormDataaa.quotId=this._ITOcustomerRequirementService.saveBasicDet.quotId;

      
      console.log(this.newArray.length);
      this.childvalues=[];
      this.childvaluesnew=[];
     
      for(let j=0;j<this.dboEleFilterList.length;j++)
      {
        if(  this.dboEleFilterList[j].filterCd=="DT_GRID")
        { 
          this.childvalues.push(responnn.dboEleFilterList[j]);
        }
      }
      this.childvalues.reverse();
      this.childvaluesnew =  this.childvalues.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.colValNm === current.colValNm);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      if(this.newArray.length!=0)
      {
       for(let j=0;j<this.childvaluesnew.length;j++)
       {
         for(let i=0;i<this.newArray.length;i++)
         {
           if( this.newArray[i].itemName==this.childvaluesnew[j].itemName && this.newArray[i].colValNm=="No" &&    this.childvaluesnew[j].colValNm!="Yes" )
           {
            
             this.childvaluesnew[j]=this.newArray[i];
             
           }
         }
       }
      }

      if(this.newArray.length == 0){
        for(let j=0;j< this.dboEleFilterList.length; j++){
          if(this.dboEleFilterList[j].genType == 'INPUT'){
          if(this.dboEleFilterList[j].itemName == 'Alternator Voltage (KV)'){
            this.dboEleFilterList[j].colValCd = 11;
            this.dboEleFilterList[j].colValNm = 11;
          }else if(this.dboEleFilterList[j].itemName == 'Alternator Voltage (V)'){
            this.dboEleFilterList[j].colValCd = 415;
            this.dboEleFilterList[j].colValNm = 415;
          }
        }
          if(this.dboEleFilterList[j].defaultFlagNew == 1){
            this.newArray.push(this.dboEleFilterList[j]);
          }
        }
      }
      this.defaulVales=[];
     // itemName: "Duty (Synchronization with Grid/ other TG'S/Other DG's)"
      for(let f = 0; f< this.newArray.length; f++){   
        if(this.newArray[f].filterCd != "EX_TYP"){
        this.defaulVales.push(this.newArray[f].colValNm);
        }
        if(this.newArray[f].colValCd == null){
          this.enableEleSub = true;
        }
        if(this.typOfPanel == "LT"){
          for(let f=0; f<this.newArray.length; f++){
          if(this.newArray[f].filterCd == "ALT_MAKE"){
            this.make = this.newArray[f].colValCd;
          }
          if(this.newArray[f].filterCd == "DT_GRID")
          this.dutySync = this.newArray[f].subColValNm;
        }
      }
      } console.log(this.defaulVales);
      for(let j=0;j<this.newArray.length;j++)
      {
        let counter=0;
       for(let i=0;i<this.dboEleFilterListNew.length;i++)
       {
        
         if(this.newArray[j].itemName==this.dboEleFilterListNew[i].itemName)
         {
          
           if(this.dboEleFilterListNew[i].subColValNm!=null && this.dboEleFilterListNew[i].subColValNm!="NULL" && this.dboEleFilterList[i].subColValNm!="" )
           {
             counter=counter+1;
           }
         }
         if(counter==2)
         {
          this.dboEleFilterListNew[i]=null;
         }
       }
       this.dboEleFilterListNew =this.dboEleFilterListNew.filter(n => n != null);
      
      }
      
      // for(let i=0;i<this.dboEleFilterListNew.length;i++){
      //   if(this.dboEleFilterListNew[i].subColValNm!=null && this.dboEleFilterListNew[i].subColValNm!="NULL" && this.dboEleFilterList[i].subColValNm!= "" )
      //  {
      //   this.dboEleFilterListNew[i]=null;
      //  }

      // }
      this.dboEleFilterListNew =this.dboEleFilterListNew.filter(n => n != null);
      let checEleFil = this.newArray.map(item => item.colValCd); 
      if(checEleFil.includes("NULL") || checEleFil.includes("") || checEleFil.includes("NULL ") || checEleFil.includes(null) ){
        this.enableEleSub = false;
      }else{
        this.enableEleSub = true;
    }
  this.newArryUpd = this.newArray;
 
 
  for(let j=0;j<this.childvaluesnew.length;j++)
 {

 
  this.temparray[this.childvaluesnew[j].eleFilterId]=this.childvaluesnew[j].subColValNm;
 
  

 }
 
  
console.log(this.childvaluesnew);
  // this.childvaluesnew = this.childvalues.filter((x) => {
  //       //return ((x.defaultFlagNew ==1));
  //       return ((x.subColValFlag == true))
  //     })
  console.log( this.childvaluesnew);
  for(let j=0;j<this.childvaluesnew.length;j++)
  {
    for(let i=0;i<this.childvalues.length;i++)
    {
      if(this.childvaluesnew[j].colValNm==this.childvalues[i].colValNm)
      {
        this.childvaluesnew[j].dropDownValueList.push(this.childvalues[i].subColValNm);
      }
    }
  }
  // for(let j=0;j<this.childvalues.length;j++)
  // {
  //   for(let i=0;i<this.childvalues.length;i++)
  //   {
  //     if(this.childvalues[j].colValNm==this.childvalues[i].colValNm)
  //     {
  //       this.childvalues[j].dropDownValueList.push(this.childvalues[i].subColValNm);
  //     }
  //   }
  // }
 
 if(this.newArray.length != 0 && this._ITOcustomerRequirementService.editFlag==true){
  // for(let j=0;j<this.childvalues.length;j++)
  // {
  //   for(let i=0;i<this.childvalues.length;i++)
  //   {
  //     if(this.childvalues[j].colValNm==this.childvalues[i].colValNm)
  //     {
  //       this.childvalues[j].dropDownValueList.push(this.childvalues[i].subColValNm);
  //     }
  //   }
  // }
  // for (let l = 0; l < this.childvalues.length; l++) {
  //   for (let d = 0; d < this.childvalues[l].dropDownValueList.length; d++) {
  //   //  this.openOth[l] = false;
     
  //      // this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
  //      this.childvalues[l].dropDownValueList =this.childvalues[l].dropDownValueList.reduce((acc, current) => {
  //       console.log(acc, current);
  //       const x = acc.find(item => item === current);
  //       if (!x) {
  //         return acc.concat([current]);
  //       } else {
  //         return acc; 
  //       } 
  //     }, []);
     
  //       //this.defaultvaluesid.push(this.questionsBean1[k].dispInd)
     
  //   }
  // }
//    for(let j=0;j<this.newArray.length;j++)
//    {
// for(let i=0;i<this.temparray.length;i++)
// {
//   if(this.temparray[this.newArray[j].eleFilterId]!="1212" && i==this.newArray[j].eleFilterId)
//   {
//     console.log(this.newArray[j].eleFilterId);
//     this.temparray[this.newArray[j].eleFilterId]=this.newArray[j].subColValNm;
//     break;
//   }
// }
//    }
//    for(let j=0;j<this.childvaluesnew.length;j++)
//    {
//      for(let i=0;i<this.newArray.length;i++)
//      {
// if(this.childvaluesnew[j].itemName == this.newArray[i].itemName && this.childvaluesnew[j].colValNm ==  this.newArray[i].colValNm)
// {
//   this.childvaluesnew[j].eleFilterId=this.newArray[i].eleFilterId;


// }

//      }
//    }
 }
 console.log(this.temparray);
  console.log(this.childvaluesnew);
  this.getChange("400");
  this.dutySync='';
  this.typeOfTurbineStart ='';
  if(this.newArryUpd.length == 0){
    for(let c=0;c<this.dboEleFilterList.length;c++){
      if(this.dboEleFilterList[c].filterCd == "TYP_TURB_START" && this.dboEleFilterList[c].defaultFlagNew == 1){
        this.typeOfTurbineStart=this.dboEleFilterList[c].colValNm;
      }
    }
    for(let c=0;c<this.dboEleFilterList.length;c++){
      if(this.dboEleFilterList[c].filterCd == "DT_GRID" && this.dboEleFilterList[c].defaultFlagNew == 1){
        this.dutySync=this.dboEleFilterList[c].subColValNm;
      }
    }
  }else{
    for(let f = 0; f< this.newArryUpd.length; f++){   
      if(this.newArryUpd[f].filterCd == "DT_GRID"){
        this.dutySync=this.newArryUpd[f].subColValNm;
      }
      if(this.newArryUpd[f].filterCd == "TYP_TURB_START"){
        this.typeOfTurbineStart = this.newArryUpd[f].colValNm;
      }
    }
  }
  this.getGover("400");
 })   
 
 }    

 //Governer list
 getGover(Valu){
   if(Valu!="400")
   {
  this.mainSave2=true;
   }
  if(Valu=="100")
  {
   for(let j=0;j<this.newArray.length;j++)
   {
     if(this.newArray[j].filterCd=="GOV_TYP")
     {
       this.newArray.splice(j,1);
     }
   }
   for(let j=0;j<this.newArryUpd.length;j++)
   {
     if(this.newArryUpd[j].filterCd=="GOV_TYP")
     {
       this.newArryUpd.splice(j,1);
     }
   }
   
  }
  if(Valu=="200"){
    for(let j=0;j<this.newArray.length;j++)
    {
      if(this.newArray[j].filterCd=="GOV_TYP")
      {
        this.newArray.splice(j,1);
      }
    }
    for(let j=0;j<this.newArryUpd.length;j++)
    {
      if(this.newArryUpd[j].filterCd=="GOV_TYP")
      {
        this.newArryUpd.splice(j,1);
      }
    }
   
  }
  this.dboFormDataaa.dutySync=this.dutySync;
  this.dboFormDataaa.typeOfPanel=this.typOfPanel;
  this.dboFormDataaa.typeOfTurbineStart=this.typeOfTurbineStart;
  console.log(this.dboFormDataaa);
  this._ITOGeneralInputsService.getGoverner(this.dboFormDataaa).subscribe(ress => {
    console.log(ress);   
 
    this.dboGovernerList = [];
    this.defaulValesNww = [];
    this.dboGovernerListLhs = [];
    this.dboGovernerList=ress.dboGovernerList;
    if(this.dboGovernerList.length > 0){
    let checkNewArrayy = this.newArray.map(item => item.filterCd);
    if(checkNewArrayy.includes("GOV_TYP")){
      for(let l=0; l<this.newArray.length; l++){
        if(this.newArray[l].filterCd == "GOV_TYP"){
          this.dboGovernerListLhs.push(this.newArray[l]);
        }
      }
    }else{
      for(let k=0; k<this.dboGovernerList.length; k++){
        if(this.dboGovernerList[k].defaultFlagNew == 1){
          this.dboGovernerListLhs.push(this.dboGovernerList[k]);
        }
      }
      for(let n=0; n<this.dboGovernerListLhs.length; n++){
        this.newArryUpd.push(this.dboGovernerListLhs[n]);
      }
      
  }
    for(let n=0; n<this.dboGovernerListLhs.length; n++){
      this.defaulValesNww.push(this.dboGovernerListLhs[n].colValNm);
      }
    console.log(this.dboGovernerListLhs);
    console.log(this.dboGovernerList);
    console.log(this.defaulValesNww);
    console.log(this.newArryUpd);
    console.log(this.newArray);
 
  }
 
  })
 }

 //Change General inputs
 getChange(value){
   if(value!="400")
   {
  this.mainSave2=true;
   }
   if(value=="100")
   {
    for(let j=0;j<this.newArray.length;j++)
    {
      if(this.newArray[j].filterCd=="EX_TYP")
      {
        this.newArray.splice(j,1);
      }
    }
    for(let j=0;j<this.newArryUpd.length;j++)
    {
      if(this.newArryUpd[j].filterCd=="EX_TYP")
      {
        this.newArryUpd.splice(j,1);
      }
    }
   }
  if(this.typOfPanel == "LT"){
    if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
      this.dboFormDataaa.typeOfQuotation = "STD";
    }else{
      this.dboFormDataaa.typeOfQuotation = "NONSTD";
    }
      console.log(this.typOfPanel);
      console.log(this.make);
      console.log(this.dutySync);
      
      this.dboFormDataaa.typeOfPanel=this.typOfPanel
      this.dboFormDataaa.make= this.make;
      this.dboFormDataaa.dutySync= this.dutySync;
      this._ITOGeneralInputsService.changeGeneralInput(this.dboFormDataaa).subscribe(respN => {
        console.log(respN);
        this.changeList = [];
        this.defaulValesNw = [];
        this.changeListLhs = [];
        this.changeList = respN.dboAvrList;
        if(this.changeList.length > 0){
        let checkNewArray = this.newArray.map(item => item.filterCd);
        if(checkNewArray.includes("EX_TYP")){
          for(let l=0; l<this.newArray.length; l++){
            if(this.newArray[l].filterCd == "EX_TYP"){
              this.changeListLhs.push(this.newArray[l]);
            }
          }
        }else{
        for(let k=0; k<this.changeList.length; k++){
          if(this.changeList[k].defaultFlagNew == 1){
            this.changeListLhs.push(this.changeList[k]);
          }
        }
        for(let v=0; v<this.changeListLhs.length; v++){
          this.newArryUpd.push(this.changeListLhs[v]);
        }
      }
        for(let n=0; n<this.changeListLhs.length; n++){
          this.defaulValesNw.push(this.changeListLhs[n].colValNm);
        }
        console.log(this.changeListLhs);
        console.log(this.changeList);
        console.log(this.newArryUpd);
        console.log(this.newArray);

      }
      });  
    }
 }

 optionSelNewLst(selV, selAr1){
  this.mainSave2=true;

   this.succDisable = true;
  console.log(selV, selAr1);
  for(let m=0; m< this.changeList.length; m++){
    if((selAr1.itemName == this.changeList[m].itemName) && (selV == this.changeList[m].colValNm)){
      for(let v=0; v<this.newArryUpd.length; v++){
        if(this.newArryUpd[v].itemName == this.changeList[m].itemName){
          this.newArryUpd[v] = this.changeList[m];
          console.log(this.newArryUpd[v]);
        }
      }
    }
  }
}

optionSelNewLstGov(selV, selAr1){
  this.mainSave2=true;
  this.succDisable = true;
 console.log(selV, selAr1);
 for(let m=0; m< this.dboGovernerList.length; m++){
   if((selAr1.itemName == this.dboGovernerList[m].itemName) && (selV == this.dboGovernerList[m].colValNm)){
     for(let v=0; v<this.newArryUpd.length; v++){
       if(this.newArryUpd[v].itemName == this.dboGovernerList[m].itemName){
         this.newArryUpd[v] = this.dboGovernerList[m];
         console.log(this.newArryUpd[v]);
       }
     }
   }
 }
}

  //on click of check or uncheck of mech aux input fields
  mechAuxCheckNe(val, z, valArr,event){
    console.log(val, z, valArr);
    if(event.target.checked){
      this.checkQty[z] =true;
      this.auxCheck[z]=true;
      this.f2fCacheFilUpd.push(valArr);
    }else if(!event.target.checked){
      this.checkQty[z] = false;
      this.auxCheck[z]=false;
      for(let j=0;j<this.f2fCacheList.length;j++)
      {
        if(this.f2fCacheList[j].genInId==z)
        {
          this.f2fCacheList[j].quantity=0;
        }
      }
      for(let a=0; a<this.f2fCacheFilUpd.length; a++){
        if(this.f2fCacheFilUpd[a].genInId == z && this.f2fCacheFilUpd[a].categoryValName == val){
          this.f2fCacheFilUpd[a].quantity = 0;
          this.f2fCacheFilUpd.splice(a,1);
        }
      }

    }
  }
//after filling quantity of mech aux
checkForQuantity(val, rec){
  this.mainSave2=true;
console.log(val, rec);
console.log(this.f2fCacheFilUpd);
for(let a=0; a<this.f2fCacheFilUpd.length; a++){
  if(this.f2fCacheFilUpd[a].genInId == rec.genInId && this.f2fCacheFilUpd[a].genInNm == rec.genInNm && this.f2fCacheFilUpd[a].categoryValName == rec.categoryValName){
    this.f2fCacheFilUpd[a].quantity = val;
  }
}
}

//To navigate edit quotaion page on click of back button
backButton(){
  this._Router.navigate(['/EditQuot']);
}
}

          
