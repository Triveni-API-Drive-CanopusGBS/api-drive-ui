import { Component, OnInit, AfterViewChecked, AfterContentChecked } from '@angular/core';
import { dboEleExtdScopeService } from './dbo-ele-extd-scope.service';
import { dboClass } from '../mech-extended-scope/mech-extended-scope';
import { ITOLoginService } from '../app.component.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { NgForm } from '@angular/forms';
import { empty } from 'rxjs/Observer';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-dbo-ele-extd-scope',
  templateUrl: './dbo-ele-extd-scope.component.html',
  styleUrls: ['./dbo-ele-extd-scope.component.css']
})

export class DboEleExtdScopeComponent implements OnInit {


  othersSubItemCheck: boolean = false;
  subItmTypTchRem: boolean = false;
  subItmTypComrRem: boolean = false;
  subItemComRem: boolean = false;
  subItemTchRem: boolean = false;
  f2fItemId: number;
  totalPrice: number = 0;
  quotId: number;
  dboBasicCost: any;
  messageVal: string = "";
  subMessage: boolean = false;
  newAddRemrk: Array<any> = [];
  newAddComRemrk: Array<any> = [];
  hideprogressCost: boolean = false;
  newAddonCost: number;
  newAddCost: Array<any> =[];
  newAddRemrkO: Array<any> = [];
  newAddComRemrkO: Array<any> = [];
  enableOth: Array<any> = [];
  enableRow: Array<any> = [];
  eleOtherDisp: Array<boolean> = [];
  newAddOnListOpts: Array<any> = [];
  newAddNameO: Array<string> = [];
  newAddCostO: Array<any> = [];
  newAddQtyO: Array<any> = [];
  selectedNewAddsTemp: Array<any> = [];
  remarks:string;
  showMsg: boolean;
  msg: string;
  noAddOns: boolean = false;
  newAddQty: Array<any> = [];
  selNewAddOnList: Array<boolean> = [];
  selNewAddOnListOptsIdList: Array<any> = [];
  selNewAddOnListOptsList: Array<string> = [];
  newAddOnListOptsExp: Array<boolean> = [];//enabe disable addon expansion
  newAddOnListOptsList: Array<any> = [];
  newAddOnList:Array<any> =[];
  newAddOnResp: any;
  dboCost: number;
  dboClass: dboClass;
  errorArray: Array<any> =[];
  costNotAvailableError: any;
  regCols: { field: string; header: string; }[];
  questionEntity: any;
  displayDialog: boolean;
  selectedCno: any;
  sapData: any;
  quesForm: any;
  Counter: any = 5;
  EleExtdScope: string = 'EleExtdScope';
  tempCodeArray: Array<Array<any>> = [];
  matchedVarient: Array<number> = [];
  newAllC:Array<any>=[];
  allCnumbersUpdated:Array<any>=[];
  matchedCind:Array<number>=[];
  unknownArray:Array<any>=[];
  labelId: Array<any> = [];
  dropdownId: Array<any> = [];
  ansCode: Array<any> = [];
  matchedCProjectList: Array<any> = [];
  allCnumbers: Array<any> = [];
  Labels: Array<any> = [];
  Dropdowns: Array<any> = [];
  questionsBean: Array<any> = [];
  defaultValues: Array<any> = [];
  F2FSapLocalStorage: Array<any> = [];
  //selectedQuesDetails: turbineConfigValues;
 // ArrayOfSelected: Array<turbineConfigValues>;

  dispSaveBtn: boolean = false;
  message: boolean = false;
  enableCompleteBOM: boolean = false; // variable to control enable and disable the complete BOM
  hideprogress: boolean;
  hideprogressC: boolean;
  displayCno: boolean;
  enableBOM: boolean = false;
  matchedCProjectAvailable: boolean = true;
  successMsg: string = '';
  unmatchedMsg: string = ``;
  F2FTurbine: string = 'F2FTurbine';
  varientCode: string = '';
  CnumbersMessage: string = "";

  framePowerIdd: number;
  dboFormData: any;
  f2fItemss: any;
  dboF2fItemList: Array<any> =[];
  enableItemDiv: boolean = false;
  dboF2fSubList: Array<any> = [];
  newArray: any;
  displayF2f: boolean = false;// diplay final dialog with dropdown values and fields
  subList1: Array<any> = [];
  displayF2fSubList1:boolean = false; //Display subitems dialog
  itemNm:string;
  subList2: Array<any> = [];
  displayF2fSubList2:boolean = false; //display subitemtype dialog
  sublistDup:Array<any> =[];
  subItmNm: string;
  panelList: any;
  dboF2fPanelList2: Array<any> = [];
  subItmTypNm: string;
  finalItem: string;
  qty: number = 1;
  SelectedExcelData: Array<any> = [];
  itemIdd: number;
  itemNamee: string;
  subItemNamee: string;
  subItemIdd: number;
  subItemTypeNamee: string;
  subItemTypeId: number;
  techPriceResp: any;
  addOnDivNew: boolean = false;
  selectedNewAdds: Array<any> = [];
  selectdEl: Array<boolean> = [];
  selectdEl1: Array<boolean> = [];
  selectdEl2: Array<boolean> = [];
  selectedELIndex: number;
  itemIdList: Array<number> = [];
  itemIdList1: Array<number> = [];
  itemIdList2: Array<number> = [];
  index: number = 0;
  iFlag : number = 0;
  count : number = 0;
  subitemtempId : number = 0;
  iSubmittedFlag : number  = 0;
  sucsMsg: string = '';
  dboAddOnCost: number = 0;
  dboEleFullArray: Array<any> = [];
  addedClassList: Array<any> = [];
  hideprogressCost1: boolean = false;
  enableOverwriteDiv: boolean = false;
  disableStatus: boolean = false;
  OverWrittenfinalEleCost: number = 0;
  scopeofsupp: string = 'scopeOfsup';
  oneLineLoc: string = 'oneLineLoc';
  oneLineLocArray: any;
  finalEleCost: number = 0;
  OthersFlag: Array<boolean> = [];
  displayCompOthTable: boolean = false;
  compOthersAddonList: Array<any> = [];
  displayOthnewLine:boolean = false;
  displayItmOthnewLine:boolean = false;
  itemRemarkDiv:boolean = false;
  itemRemarksVal:any = '';
  othersCompCheck: boolean = false;
  compOthers: any;
  subItemTechRmkDiv:boolean = false;
  subItemRemarksVal:any;
  subItemComrRmkDiv:boolean = false;
  subItemCmrRemVal: any;
  subItemCmrRemValOut: any;
  itemComrRemarkDiv:boolean = false;
  itemCmrRemarksVal: any = '';
  displayItemCompOthTable:boolean = false;
  itemOthersAddonList:Array<any> = [];
  displayDialogLhsRhs:boolean = false;
  lhsRhsnewLine:boolean = false;
  lhsRhsItemSel:string = '';
  lhsRhsItemsList:Array<any> = [];
  subItemRemarkDiv:boolean = false;
  subItemComrRemDiv:boolean = false;
  subItemRmkValOut: any;
  displaySubItemOthTable:boolean = false;
  subItemOthAddonList: Array<any> = [];
  dsplySubItmOthnewLine:boolean = false;
  lhsRhsSubItemSel: string = '';
  dsplyDialogSubLhsRhs:boolean = false;
  lhsRhsSubnewLine:boolean = false;
  lhsRhsSubItemsList:Array<any> = [];
  subItmTypRmkDiv:boolean = false;
  subItmTypRmkValOut:any;
  subItmTypComRemValOut: any;
  subItTypmComRemDiv:boolean = false;
  dsplySubItmTypOthTable:boolean = false;
  subItmTypOthList:Array<any> = [];
  lhsRhsSubItmTypSel: string;
  dsplySubItmTypOthnwLin:boolean = false;
  dsplyDlgSubTypLhsRhs:boolean = false;  
  lhsRhsSubItmTypList: Array<any> = [];
  lhsRhsSubTypnewLine:boolean = false;
  tempFinalEleCost: number = 0; // final cost copy to campare wfor changes
  comrCheckkk: boolean = false; //enable commercial remarks 
  techCheckkk: boolean = false; //enable techinal remarks
  othCheck: boolean = false; //enable others table 
  subItemtechCheck: boolean = false;
  subItemComrrCheck: boolean =false;
  othersCheck: boolean = false; // enable new item others table 
  othersSubCheck: boolean = false // enable new sub item others
  othersSubTypCheck : boolean = false //enable new sub item type others
  othItmTechRemChk: boolean = false // enable techinal comments above new item creation others
  othItmComrRemChk: boolean = false // enable Commercial comments above new item creation others
  othSubItmTechRemChk: boolean = false; //enable techinal comments above new sub item creation others
  othSubItmComrRemChk: boolean = false; // enable Commercial comments above new sub item creation others
  othSubItmTypComrRemChk: boolean; // enable Commercial comments above new sub item type creation others
  othSubItmTypTechRemChk: boolean; //enable techinal comments above new sub item type creation others

  itemSelectedListEdit:Array<any> = []; //the list of selected items from edit
  subitemSelectedListEdit:Array<any> = []; //the list of selected sub items from edit
  subitemTypeSelectedListEdit:Array<any> = []; //the list of selected sub item types from edit
  itemtempId : number = 0;
  newSet: Array<any> = [];
  prevData: Array<any> = [];
  iItemindex: number = 0;
  othersItemCompCheck:boolean=false;
  addOns:boolean=false;
  dboEleExtScopeLocal: Array<any> = [];
  ibreakothers: number = 0;
  backBtn: boolean = false;
  itemComrRemarkCheck: boolean = false;
  itemtechRemarkCheck: boolean = false;
  ChecktoResetAddOnCost: number;  //Flag to check whether add on cost to be set
                                  //Zero on the lauch of panel
                                  rhsdes2:Array<boolean>=[];
  lhsdes:Array<boolean>=[];
  getPrice:boolean=true;

  mainSave:boolean=true
  mainSave2:boolean=true
  mainSave3:boolean=true;



  constructor(private _dboEleExtdScopeService: dboEleExtdScopeService, private _ITOLoginService: ITOLoginService,
    private _ITOturbineConfigService: ITOturbineConfigService, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private _ITOeditQoutService: ITOeditQoutService,
    private _ITOCostEstimationService: ITOCostEstimationService, private router: Router) {

      if(this._ITOeditQoutService.checkEdit == false){
        this.backBtn = true;
      }
      this._ITOeditQoutService.button1=false;
      this._ITOeditQoutService.button2=false;
      this._ITOeditQoutService.button3=false;
      this._ITOeditQoutService.button4=false;
      this._ITOeditQoutService.button5=false;
      this._ITOeditQoutService.button6=true;
      this._ITOeditQoutService.button7=false;
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=false;
      this._ITOturbineConfigService.getDboFormData().subscribe(responn => {
        console.log(responn);
        this.dboFormData = responn;
       //this.dboFormData.framePowId = this._ITOcustomerRequirementService.saveBasicDet.framePowerId;
       this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      });

      if (this.storage.get(this.EleExtdScope) == null) {
        this.saveInLocal(this.EleExtdScope, { 
          itemOthersAddonList:this.itemOthersAddonList,lhsRhsItemsList:this.lhsRhsItemsList, totalPrice: this.totalPrice,
          itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal
          });
      }

//eleExtScopeList
this.itemOthersAddonList=[];
this.lhsRhsItemsList=[];
console.log(this._ITOeditQoutService.eleExtScopeList);
      if(this._ITOeditQoutService.eleExtScopeList!=null && this._ITOeditQoutService.eleExtScopeList.length!=0){
        this.mainSave=false;
        this.mainSave2=false;
        this.mainSave3=false;
        console.log(this._ITOeditQoutService.eleExtScopeList);

        if(this._ITOeditQoutService.eleExtScopeList[0].eleComrComments != "" && this._ITOeditQoutService.eleExtScopeList[0].eleComrComments != null){
          this.itemCmrRemarksVal = this._ITOeditQoutService.eleExtScopeList[0].eleComrComments;
          this.othItmComrRemChk=true;
          this.itemComrRemarkCheck=true;
          this.itemComrRemarkDiv=true;
        }
        if(this._ITOeditQoutService.eleExtScopeList[0].eleTechComments != "" && this._ITOeditQoutService.eleExtScopeList[0].eleTechComments != null){
          this.itemRemarksVal = this._ITOeditQoutService.eleExtScopeList[0].eleTechComments;
          this.othItmTechRemChk=true;
          this.itemtechRemarkCheck=true;
          this.itemRemarkDiv=true;
        }

        if(this._ITOeditQoutService.eleExtScopeList[0].eleTotalExtCost != 0){
          this.totalPrice = this._ITOeditQoutService.eleExtScopeList[0].eleTotalExtCost;
        }

        this.othersCheck = true;
        this.addOns=true;

        this.othersItemCompCheck=true;
        this.displayItemCompOthTable = true;
        for(let c=0;c<this._ITOeditQoutService.eleExtScopeList.length;c++)
        {
          this.dboClass = new dboClass();
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = 0;
          this.dboClass.itemName = this._ITOeditQoutService.eleExtScopeList[c].itemName;
          this.dboClass.subItemId = this._ITOeditQoutService.eleExtScopeList[c].subItemId;
          this.dboClass.subItemName = this._ITOeditQoutService.eleExtScopeList[c].subItemName;
          this.dboClass.colId = 0;
          this.dboClass.colNm = this._ITOeditQoutService.eleExtScopeList[c].colNm;
          this.dboClass.colValCd = this._ITOeditQoutService.eleExtScopeList[c].colValCd;
          this.dboClass.quantity = this._ITOeditQoutService.eleExtScopeList[c].quantity;
          this.dboClass.cost = this._ITOeditQoutService.eleExtScopeList[c].extScopeCost;
          this.dboClass.techRemarks = this._ITOeditQoutService.eleExtScopeList[c].techRemarks;
          this.dboClass.comrRemarks = this._ITOeditQoutService.eleExtScopeList[c].comrRemarks;
          this.dboClass.addOnFlg = 1;
          this.dboClass.techFlag = 1;    
          this.dboClass.comrFlag = 1;
          this.itemOthersAddonList.push(this.dboClass);
          this.lhsRhsItemsList.push(this.dboClass);
        }
        this.itemOthersAddonList =  this.itemOthersAddonList.reduce((acc, current) => {
         console.log(acc, current);
         const x = acc.find(item => item.itemName === current.itemName);
         if (!x) {
           return acc.concat([current]);
         } else {
           return acc;
         } 
       }, []);
      //  for(let z=0; z<this.itemOthersAddonList.length; z++){
      //    if(this.itemOthersAddonList[z].othersCost != 0){
      //    this.itemOthersAddonList[z].cost = this.itemOthersAddonList[z].othersCost;
      //  }
      //  }
       console.log("checking");
      //  this.itemOthersAddonList =  this._ITOeditQoutService.eleExtScopeList.reduce((acc, current) => {
      //   console.log(acc, current);
      //   const x = acc.find(item => item.itemName === current.itemName);
      //   if (!x) {
      //     return acc.concat([current]);
      //   } else {
      //     return acc;
      //   } 
      // }, []);
      
      
      }
      else
      {
        if(this.storage.get(this.EleExtdScope).itemOthersAddonList.length !=0){
          this.othersCheck = true;
          this.addOns=true;
  
          this.othersItemCompCheck=true;
          this.displayItemCompOthTable = true;
          this.itemOthersAddonList = this.storage.get(this.EleExtdScope).itemOthersAddonList;
          this.mainSave=false;
          this.mainSave2=false;
          this.mainSave3=false;
        }
    
        // to dispaly new item creation other with lhs/rhs from local storage
        if(this.storage.get(this.EleExtdScope).lhsRhsItemsList.length!= 0){
          this.lhsRhsItemsList = this.storage.get(this.EleExtdScope).lhsRhsItemsList;
        }

        if(this.storage.get(this.EleExtdScope).itemCmrRemarksVal != null && this.storage.get(this.EleExtdScope).itemCmrRemarksVal != ""){
          this.itemCmrRemarksVal = this.storage.get(this.EleExtdScope).itemCmrRemarksVal;
          this.othItmComrRemChk=true;
          this.itemComrRemarkCheck=true;
          this.itemComrRemarkDiv=true;
        }
        if(this.storage.get(this.EleExtdScope).itemRemarksVal != null && this.storage.get(this.EleExtdScope).itemRemarksVal != ""){
          this.itemRemarksVal = this.storage.get(this.EleExtdScope).itemRemarksVal;
          this.othItmTechRemChk=true;
          this.itemtechRemarkCheck=true;
          this.itemRemarkDiv=true;
        }

        if(this.storage.get(this.EleExtdScope).totalPrice != 0){
          this.totalPrice = this.storage.get(this.EleExtdScope).totalPrice;
        }
      }
    }
     
    openItemCompTable(event){
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=true;

      if(event.target.checked){
        this.displayItemCompOthTable = true;
      }else if(!event.target.checked){
        this.displayItemCompOthTable = false;
        //to clear the items list and lhs rhs item list when we uncheck others in the main item
        //panel
        this.itemOthersAddonList = [];  //ADDED SATYA
        this.lhsRhsItemsList = []; //ADDED SATYA
        this.lhsdes=[];
        this.rhsdes2=[];
      }
    }

    //remarks for items and opening text area for remarks
 itemRmrkCheck(event){
  this.mainSave=true;
  this.mainSave2=true;
  this.mainSave3=true;

  console.log(event);
  if(event.target.checked){
    this.itemRemarkDiv = true;
  }else if(!event.target.checked){
    this.itemRemarkDiv = false;
    this.itemRemarksVal = "";
  }
}

    //capturing of Item techinal  Remarks value
checkRemarks(){
  this.mainSave=true;
  this.mainSave2=true;
  this.mainSave3=true;

  console.log(this.itemRemarksVal);
}

//Commercial remarks for items and opening text area for remarks
itmComrRmrk(event){
  this.mainSave=true;
  this.mainSave2=true;
  this.mainSave3=true;

  if(event.target.checked){
    this.itemComrRemarkDiv = true;
  }else if(!event.target.checked){
    this.itemComrRemarkDiv = false;
    this.itemCmrRemarksVal="";
  }
}

//capturing of Item techinal  Remarks value
checkItemComrRmrks(){
  this.mainSave=true;
  this.mainSave2=true;
  this.mainSave3=true;

  console.log(this.itemCmrRemarksVal);
}

    saveInLocal(key, val): void {
      this.storage.set(key, val);
      this.dboEleExtScopeLocal[key] = this.storage.get(key);
    }
    addRowsItem() {
      this.mainSave=true;
      this.mainSave2=true;
      this.displayItmOthnewLine = true;
    }
    itemOthersForm(othersItem, othersItemfrm: NgForm) {
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=true;

      console.log(othersItem);
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = null;
      this.dboClass.itemName = othersItem.itemCompName;       
      this.dboClass.subItemId = null;
      this.dboClass.subItemName = null;
      this.dboClass.colId = null;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = null;
      this.dboClass.quantity = othersItem.itemQuantity;
      this.dboClass.cost = othersItem.itemPrice;
      this.dboClass.techRemarks = othersItem.itemTechRemarks;
      this.dboClass.comrRemarks = othersItem.itemComrRemr;
      this.dboClass.addOnFlg = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
     
          
           
     

          // QuotId -- 10
					// 	ItemId -- 39
					// 	ItemName - NULL
					// 	SubItemId - NULL 
					// 	SubItemName -NULL
					// 	ColId		- 2
					// 	ColNm        - NULL
					// 	ColValCd  - Condensate
					// 	Quantity  - NULL
					// 	Cost	--- NULL
					// 	TechRemarks --NULL
					// 	ComrRemarks -- NULL
					// 	AddOnFlg -- 0
					// 	TechFlag -- 1
					// 	ComrFlag -- 1
					// 	colId: null
      this.itemOthersAddonList.push(this.dboClass);
      this.lhsRhsItemsList.push(this.dboClass);//satya
      console.log(this.itemOthersAddonList);
      othersItemfrm.reset();
      this.displayItmOthnewLine = false;
    }
  

    cancelLinesItemOth(i) {
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=true;

      for(let j = 0; j < this.lhsRhsItemsList.length; j++)
      {
        //Need to remove the lhsrhsitemlist also
        if(this.itemOthersAddonList[i].itemName == this.lhsRhsItemsList[j].itemName)
        {
          //this.lhsRhsItemsList[j] = null;  // ADDED SATYA
         this.lhsRhsItemsList.splice(j, 1);
         j = j - 1;
         this.rhsdes2=[];

        }
      }
      this.itemOthersAddonList.splice(i, 1);
      this.lhsdes.splice(i,1);

    }
    addLhsRhsItem(i){
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=true;

      this.lhsRhsItemSel = '';
      this.ibreakothers = i;
      console.log(i);  
      this.lhsRhsItemSel = this.itemOthersAddonList[i].itemName;
      console.log(this.lhsRhsItemSel);
        this.displayDialogLhsRhs = true;
        this.displayItmOthnewLine = false;
        // for(let c=0; c<this.itemOthersAddonList.length; c++){
        //   if(this.lhsRhsItemSel == this.itemOthersAddonList[c].itemName){
            // this.lhsRhsItemsList.splice(i,1);
          // }
        // }
    }
    cancelnewLineItem(){
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=true;

      this.displayItmOthnewLine = false;
    }
    itemsSubmit(){
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=false;

      for(let z=0; z<this.itemOthersAddonList.length; z++){
        if (!this.lhsRhsItemsList.some((item) => item.itemName == this.itemOthersAddonList[z].itemName)) {
          this.lhsRhsItemsList.push(this.itemOthersAddonList[z]);
      }
      }console.log(this.lhsRhsItemsList);
    }
    addLhsRhsForm(lhsRhsItem, lhsRhsItemfrm: NgForm){
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=true;

      console.log(lhsRhsItem);
     // this.ibreakothers = 0;
         for(let k = 0; k < this.lhsRhsItemsList.length; k++)
         {
          if(this.lhsRhsItemsList[k].itemName == this.itemOthersAddonList[this.ibreakothers].itemName && this.lhsRhsItemsList[k].colId == null && this.lhsRhsItemsList[k].colNm == null){
            this.lhsRhsItemsList.splice(k,1);
            this.ibreakothers = 1;
            break;          
          }
        }
        this.ibreakothers = 0;
      //this.lhsRhsItemsList = [];
      for(let c=0; c<this.itemOthersAddonList.length; c++){
        if(this.lhsRhsItemSel == this.itemOthersAddonList[c].itemName){
          this.dboClass = new dboClass();
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = null;
          this.dboClass.itemName = this.itemOthersAddonList[c].itemName;
          this.dboClass.subItemId = this.itemOthersAddonList[c].subItemId;
          this.dboClass.subItemName = this.itemOthersAddonList[c].subItemName;
          this.dboClass.colId = null;
          this.dboClass.colNm = lhsRhsItem.lhsVal;
          this.dboClass.colValCd = lhsRhsItem.rhsVal;
          this.dboClass.quantity = this.itemOthersAddonList[c].quantity;
          this.dboClass.cost = this.itemOthersAddonList[c].cost;
          this.dboClass.techRemarks = this.itemOthersAddonList[c].techRemarks;
          this.dboClass.comrRemarks = this.itemOthersAddonList[c].comrRemarks;
          this.dboClass.addOnFlg = 1;
          this.dboClass.techFlag = 1;    
          this.dboClass.comrFlag = 1;
         
          this.lhsRhsItemsList.push(this.dboClass);



          // QuotId -- 10
					// 	ItemId -- 39
					// 	ItemName - NULL
					// 	SubItemId - NULL 
					// 	SubItemName -NULL
					// 	ColId		- 2
					// 	ColNm        - NULL
					// 	ColValCd  - Condensate
					// 	Quantity  - NULL
					// 	Cost	--- NULL
					// 	TechRemarks --NULL
					// 	ComrRemarks -- NULL
					// 	AddOnFlg -- 0
					// 	TechFlag -- 1
					// 	ComrFlag -- 1
					// 	colId: null
        }
      }
     console.log(this.lhsRhsItemsList);
     lhsRhsItemfrm.reset();
      this.lhsRhsnewLine = false;
      this.displayItmOthnewLine = false;
    }
    cancelLinesLhs(i){
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=true;

      this.lhsRhsnewLine = false;
      this.lhsRhsItemsList.splice(i, 1);
      this.rhsdes2.splice(i,1);

    }
    cancelnewLineLhs(){
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=true;

      this.lhsRhsnewLine = false;//added by megha
    }
    addRowsLhsRhs(){
      this.mainSave=true;
      this.mainSave2=true;
      this.mainSave3=true;

      this.lhsRhsnewLine = true;
    }

  calcTotal()
  {
    this.getPrice=true;
    if(this.lhsdes.includes(true) || this.rhsdes2.includes(true) )
    {
      this.getPrice=false;
    }
    if(this.getPrice )
    {
    this.dboFormData.eleExtScopeTechComments = this.itemRemarksVal; 
    this.dboFormData.eleExtScopeComrComments = this.itemCmrRemarksVal;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormData.eleExtScopeData = this.lhsRhsItemsList;
    this._dboEleExtdScopeService.saveExtendedEle(this.dboFormData).subscribe(savedResp => {
      this.mainSave2=false;
      console.log(savedResp);
      this.totalPrice = savedResp.totalPrice;
      this.saveInLocal(this.EleExtdScope, { 
      itemOthersAddonList:this.itemOthersAddonList, lhsRhsItemsList:this.lhsRhsItemsList, totalPrice: this.totalPrice,
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal
      });
    
    });
  }
  }
  ngOnInit() {
  }
 //To navigate edit quotaion page on click of back button
 backButton(){
  this.router.navigate(['/EditQuot']);
}
saveExcel(){
  this.getPrice=true;
  if(this.lhsdes.includes(true) || this.rhsdes2.includes(true) )
  {
    this.getPrice=false;
  }
  if(this.getPrice )
  {
  //call one line BOM
  this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
    console.log(resOnline);
    if (resOnline.successCode == 0) {
      this.message = true;
      this.mainSave=false;
      this.successMsg = "Cost Saved successfully";
    } 
    this.saveInLocal(this.EleExtdScope, { 
      itemOthersAddonList:this.itemOthersAddonList,lhsRhsItemsList:this.lhsRhsItemsList, totalPrice: this.totalPrice,
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal    
     });
    this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
    this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, totalPrice: this.totalPrice});
    if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
      this._ITOcustomerRequirementService.editFlag = false;
      this.router.navigate(['/EditQuot']);
    }
  this.hideprogressCost1 = false;
});
  }
}
EditItem(i,value,name,item,type)
  {
    this.mainSave=true;
    this.mainSave2=true;
    this.mainSave3=true;

    if(name=='rhs')
    {
      //this.lhsdes2[i]=true;
      //this.itemOthersAddonList[i].cost=value;
      if(this.lhsRhsItemsList[i].itemName==item)
      {
     
    if(type=='colNm')
    {
      this.lhsRhsItemsList[i].colNm=value;

    }
    if(type=='colValCd')
    {
      this.lhsRhsItemsList[i].colValCd=value;

    }

  }
    }
    else{
      //this.rhsdes2[i]=true;
      

       
          if(type=='quantity')
          {
            this.itemOthersAddonList[i].quantity=value;

          }
          if(type=='cost')
          {
            this.itemOthersAddonList[i].cost=value;

          }
          if(type=='techRemarks')
          {
            this.itemOthersAddonList[i].techRemarks=value;

          }
          if(type=='comrRemarks')
          {
            this.itemOthersAddonList[i].comrRemarks=value;

          }

          for(let j=0;j<this.lhsRhsItemsList.length;j++)
          {
            if(this.lhsRhsItemsList[j].itemName==item)
            {
           
          if(type=='quantity')
          {
            this.lhsRhsItemsList[j].quantity=value;

          }
          if(type=='cost')
          {
            this.lhsRhsItemsList[j].cost=value;

          }
          if(type=='techRemarks')
          {
            this.lhsRhsItemsList[j].techRemarks=value;

          }
          if(type=='comrRemarks')
          {
            this.lhsRhsItemsList[j].comrRemarks=value;

          }

            }
          }

        
      
    }

   
  }


  editlhsdessub(i,type)
  {
    this.mainSave=true;
    this.mainSave2=true;
    this.mainSave3=true;

if(type=='lhs')
{
this.lhsdes[i]=true;
}
else
{
  this.rhsdes2[i]=true;

}
  }

  savelhsdessub(i,value,name,item,type)
  {
    this.mainSave=true;
    this.mainSave2=true;
    this.mainSave3=true;

if(name=='lhs')
{
  this.lhsdes[i]=false;
  this.EditItem(i,value,name,item,type)

}
else
{
  this.rhsdes2[i]=false;

}
console.log(this.itemOthersAddonList);
console.log(this.lhsRhsItemsList);
  }
  keyPress(event: KeyboardEvent,value,index) {
    let temp=true;
let temp2=[];

    const pattern = /[0-9]/;
    console.log(event.key);   
     console.log(value[0]);
     console.log(event.target);   
     console.log(event);   

     let numberValue=Number(event.key);
     if( isNaN(numberValue))
{
  if(value=="")
  {
  if(event.key=="-" && value=="")
  {
  
  }
  else
  {
    event.preventDefault();
    temp=false;
  
  }
}
  if(value!=""  )
  {
  if(event.key=="." && value!="" && value!="-" && index!=0  )
  {
   if(value.includes(".") )
   {

    event.preventDefault();
    temp=false;


   }
  }
  else
  {
    if(event.key=="-"  && index==0  )
    {
     if(value.includes("-") )
     {
  console.log(value[value.length])
      event.preventDefault();
      temp=false;

  
  
     }
    }
    else
    {
    event.preventDefault();
    temp=false;

    }
  
  }
}
}
else if(event.code=="Space")
{
  event.preventDefault();

}

}
}
