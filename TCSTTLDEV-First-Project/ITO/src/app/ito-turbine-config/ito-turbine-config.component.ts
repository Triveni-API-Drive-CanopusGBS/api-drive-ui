
import { Component, OnInit, EventEmitter, Input, Output, forwardRef} from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOturbineConfigService } from './ito-turbine-config.service';
import { turbineConfigValues } from './ito-turbine-config';
import { ITOcompleteTurbineService } from '../complete-turbine-details/complete-turbine-details.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOLoginService } from '../app.component.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { IfObservable } from 'rxjs/observable/IfObservable';
import { dboClass } from '../dbomechanical/dbomechanical';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
import { NgForm } from '@angular/forms';
import {DBOElectricalComponentService} from '../dboelectrical/dboelectrical.service';
import {ITOAddOnComponentsService} from '../add-on-components/add-on-components.service';
import { ThrowStmt, unescapeIdentifier } from '@angular/compiler';
import { zip } from 'rxjs/operators';
import { Dialog } from 'primeng/dialog';

@Component({
  selector: 'app-ito-turbine-config',
  templateUrl: './ito-turbine-config.component.html',
  styleUrls: ['./ito-turbine-config.component.css']
})
export class ItoTurbineConfigComponent implements OnInit {

  alert:string="                     !!!!!ALERT!!!!";
  enableQty: boolean = true;
  checkMBRBool: boolean = false;
  checkMBR: Array<any> = [];
  othersSubItemCheck: boolean = false;
  subItmTypTchRem: boolean = false;
  subItmTypComrRem: boolean = false;
  subItemComRem: boolean = false;
  subItemTchRem: boolean = false;
  f2fItemId: number;
  quotId: number;
  dboBasicCost: any;
  messageVal: string = "";
  subMessage: boolean = false;
  newAddRemrk: Array<any> = [];
  newAddComRemrk: Array<any> = [];
  hideprogressCost: boolean = false;
  openbtn: Array<boolean> = [];
  newAddonCost: number;
  newAddCost: Array<any> =[];
  newAddRemrkO: Array<any> = [];
  newAddComRemrkO: Array<any> = [];
  newAddComRemrkO1: Array<any> = [];
  enableOth: Array<any> = [];
  newAddCostO1: Array<any> = [];
  enableRow: Array<any> = [];
  eleOtherDisp: Array<boolean> = [];
  newAddOnListOpts: Array<any> = [];
 newAddNameO: Array<string> = [];
 newAddNameO1: Array<string> = [];
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
  dispSCFM:boolean =false; //Display SCFM Error message
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
  selectedQuesDetails: turbineConfigValues;
  ArrayOfSelected: Array<turbineConfigValues>;

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
  selectedELIndexM: number;
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
  totalF2fCost: number = 0;
  OthersFlag: Array<boolean> = [];
  displayCompOthTable: boolean = false;
  compOthersAddonList: Array<any> = [];
  compOthersAddonList1: Array<any> = [];
  displayOthnewLine:boolean = false;
  displayItmOthnewLine:boolean = false;
  itemRemarkDiv:boolean = false;
  itemRemarksVal:any;
  othersCompCheck: boolean = false;
  compOthers: any;
  subItemTechRmkDiv:boolean = false;
  subItemRemarksVal:any;
  subItemComrRmkDiv:boolean = false;
  subItemCmrRemVal: any;
  subItemCmrRemValOut: any;
  itemComrRemarkDiv:boolean = false;
  itemCmrRemarksVal: any;
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
  openOth: Array<boolean> = [];
  ibreakothers: number = 0;
  addOnList: Array<any> = [];
  addOnList1: Array<any> = [];
  ChecktoResetAddOnCost: number;  //Flag to check whether add on cost to be set
                                  //Zero on the lauch of panel
  rhs: Array<boolean> = [];
  finalcostflag:boolean=false;
  addonflg:string="0";
  itemOthersAddonListTemp: Array<any> = []; //to store temp of creation of new items(others) from edit mode
  lhsRhsItemsListTemp: Array<any> = []; //To store temp data of creation new item LHS/RHS (others) from edit mode
  lhsRhsSubItemsListTemp: Array<any> = []; //To store temp data of creation new sub item LHS/RHS (others)
  subItemOthAddonListTemp: Array<any> = []; //To store temp data of creation new sub item (others)
  lhsRhsSubItmTypListTemp: Array<any> = []; //To store temp data of creation of subitemtype name LHS/RHS(others)
  subItmTypOthListTemp: Array<any> = []; //To store temp data of creation od sub item type (others)
  AddOnFlag:Array<boolean> = [];
  AddOnFlagNew:Array<boolean> = [];
  errMsgPnl:  Array<any> = []; //To display error msg if error msg is not equal to null on hitting getEleTechPrice
  errDisplayPnl:  Array<boolean> = []; //To Display error msg div if error msg is not equal to null on hitting getEletechPrice
  errDisplayRhsCost: Array<boolean> = []; //To Display error msg div if addOnflg is 1 on hitting getEletechPrice
  errMsgRhsCost: Array<any> = []; //To display error msg if addonflg is 1 on hitting getEleTechPrice
  itemcost:number=0;
  backBtn:boolean = false;
  saveBtColor:boolean=true;
  buttonColor:string="rgb(213,120,23)";
  finalCostBool: boolean = false;  

  //others edit
disablelhs:Array<boolean>=[];
getPrice:boolean=true;
discountPer: number = 0;
discountPerS: number = 0;
subQuantity:number=1;
user: string = 'userDetail';
currentRole: string = 'selRole';
currentRoleId: string = 'selRoleId';
rewApp: boolean = false;
lubItemcost: number= 0;
appDisable: boolean = false;
  
  IsStandard:boolean=false;
  mainSave:boolean=true;
  mainSave2:boolean=true;
  diableitemname:boolean=true;
  subItembutton:boolean=true;
  subItemTypebutton:boolean=true;

  constructor(private _ITOturbineConfigService: ITOturbineConfigService,
    private router: Router, private route: ActivatedRoute,
    private _ITOcompleteTurbineService: ITOcompleteTurbineService,
    private _ITOeditQoutService: ITOeditQoutService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService,
    private _ITOCostEstimationService: ITOCostEstimationService,
    private _DBOElectricalComponentService: DBOElectricalComponentService,
    private _ITOLoginService: ITOLoginService, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService) {
      this._ITOeditQoutService.button1=false;
      this._ITOeditQoutService.button2=false;
      this._ITOeditQoutService.button3=false;
      this._ITOeditQoutService.button4=true;
      this._ITOeditQoutService.button5=false;
      this._ITOeditQoutService.button6=false;
      this._ITOeditQoutService.button7=false;
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=false;
     let userDetail = this.storage.get(this.user);
     let currentRole = this.storage.get(this.currentRole);
     let currentRoleId = this.storage.get(this.currentRoleId);
    console.log(userDetail);
    console.log(userDetail.userRoleId);
    console.log(currentRole);
    console.log(currentRoleId);   
    if(currentRole == "QUOT_REVIWER" || currentRole == "QUOT_APPROVER"){
            this.rewApp = true;
          }

    this._ITOcustomerRequirementService.sendMessage(this._ITOcustomerRequirementService.saveBasicDet.quotNumber);
    this.ArrayOfSelected = new Array<turbineConfigValues>();
    this.selectedQuesDetails = new turbineConfigValues('');
    this.itemSelectedListEdit = [];
    this.subitemSelectedListEdit = [];
    this.subitemTypeSelectedListEdit = [];
    //To set to null in the beginning of the edit mode
    this.selectdEl= [];
    this.selectdEl1 = [];
    this.selectdEl2 = [];
    this.itemIdList = [];
    this.itemIdList1 = [];
    this.itemIdList2 = [];
    this.dboEleFullArray = [];
if(  this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard")
{
  this.IsStandard=true;
}
    if(this._ITOeditQoutService.checkEdit == false){
      this.backBtn = true;
    }
    if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
      this.appDisable=true;
    }else{
      this.appDisable=false;
    }

    this._ITOturbineConfigService.getQuotForm().subscribe(quotForm => {
      if (this._ITOcustomerRequirementService.editFlag) {
        this._ITOcustomerRequirementService.saveBasicDet.cNewNumber = this._ITOcustomerRequirementService.saveBasicDet.cOldNumber;
      }
      // if(_ITOcustomerRequirementService.editFlag==false)
      // {
      //   this._ITOeditQoutService.dboF2fNewAddOns=[];
      // }
      quotForm.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
      console.log(this._ITOcustomerRequirementService.saveBasicDet.framePowerId);
      
      //get dbo form
      this._ITOturbineConfigService.getDboFormData().subscribe(responn => {
        console.log(responn);
        this.dboFormData = responn;
       //this.dboFormData.framePowId = this._ITOcustomerRequirementService.saveBasicDet.framePowerId;
       this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
       
        //passing dbo form as input to getF2fItems
        this._ITOturbineConfigService.getF2fItems(this.dboFormData).subscribe(repp => {
          console.log(repp);
          this.f2fItemss = repp;
          this.dboF2fItemList = this.f2fItemss.dboF2fItemList;
          //filter ItemNames
          this.newArray = this.dboF2fItemList.reduce((acc, current) => {
            console.log(acc, current);
            const x = acc.find(item => item.itemName === current.itemName);
            if (!x) {
              return acc.concat([current]);
            } else {
              return acc;
            } 
          }, []);
          console.log(this.newArray);
          this.checkMBR = this.newArray.map(item => item.itemId);
          if(this.checkMBR.includes(4)){
            this.checkMBRBool = true;
          }
          console.log(this.storage.get(this.oneLineLoc));
          this.F2FSapLocalStorage[this.F2FTurbine] = this.storage.get(this.F2FTurbine);
    this.sapData= this.F2FSapLocalStorage[this.F2FTurbine]
    console.log(this.F2FSapLocalStorage[this.F2FTurbine]);
    console.log(this.storage.get(this.F2FTurbine));

    //to display techina and commercial remarks above the new item creation others from local storage 
    if(this.storage.get(this.F2FTurbine).itemCmrRemarksVal != null && this.storage.get(this.F2FTurbine).itemCmrRemarksVal != ""){
      this.itemCmrRemarksVal = this.storage.get(this.F2FTurbine).itemCmrRemarksVal;
      this.othItmComrRemChk = true;
      this.itemComrRemarkDiv = true;
    }
    if(this.storage.get(this.F2FTurbine).itemRemarksVal != null && this.storage.get(this.F2FTurbine).itemRemarksVal != ""){
      this.itemRemarksVal = this.storage.get(this.F2FTurbine).itemRemarksVal;
      this.itemRemarkDiv = true;
      this.othItmTechRemChk = true;
    }
    //to display new item creation other from local storage
    if(this.storage.get(this.F2FTurbine).itemOthersAddonList.length != 0){
      this.itemOthersAddonList = this.storage.get(this.F2FTurbine).itemOthersAddonList;
    }
    if(this.storage.get(this.F2FTurbine).dboEleFullArray.length != 0){
      this.finalCostBool = true;
      this.dboEleFullArray = this.storage.get(this.F2FTurbine).dboEleFullArray;
      this.mainSave=false;
      this.mainSave2=false;
    }
    // to dispaly new item creation other with lhs/rhs from local storage
    if(this.storage.get(this.F2FTurbine).lhsRhsItemsList.length != 0){
      this.lhsRhsItemsList = this.storage.get(this.F2FTurbine).lhsRhsItemsList;
    }
    
    //to display new sub item creation other from local storage
    if(this.storage.get(this.F2FTurbine).subItemOthAddonList.length != 0){
      this.subItemOthAddonList = this.storage.get(this.F2FTurbine).subItemOthAddonList;
    }
  
    // to dispaly new sub item creation other with lhs/rhs from local storage
    if(this.storage.get(this.F2FTurbine).lhsRhsSubItemsList.length != 0){
      this.lhsRhsSubItemsList = this.storage.get(this.F2FTurbine).lhsRhsSubItemsList;
    }
    //to display new sub item type creation other from local storage
    if(this.storage.get(this.F2FTurbine).subItmTypOthList.length != 0){
      this.subItmTypOthList = this.storage.get(this.F2FTurbine).subItmTypOthList;
    }
    //to dispaly new sub item type creation other with lhs/rhs from local storage
    if(this.storage.get(this.F2FTurbine).lhsRhsSubItmTypList.length != 0){
      this.lhsRhsSubItmTypList = this.storage.get(this.F2FTurbine).lhsRhsSubItmTypList;
    }
    //to display turbine material cost from local storage
    if(this.storage.get(this.F2FTurbine).finalEleCost != 0){
      this.finalEleCost = this.storage.get(this.F2FTurbine).finalEleCost;
    }
    if(this.storage.get(this.F2FTurbine).totalF2fCost != 0){
      this.totalF2fCost = this.storage.get(this.F2FTurbine).totalF2fCost;
    }
    if(this.storage.get(this.F2FTurbine).addOnList.length !=0 ){
      this.addOnList = this.storage.get(this.F2FTurbine).addOnList;
    }
       if(_ITOcustomerRequirementService.editFlag==true)
       {
         this.addOnList1=[];
           this.addOnList1 =   this._ITOeditQoutService.dboF2fData.filter((x) => {
    return ((x.addOnNewColFlag == 1 && x.lhsFlag == 0 ));
    })

    if( this.addOnList1.length!=0)
    {
      this.finalCostBool = true;
      this.addOnList=[];
      // this.dboClass = new dboClass();
      // this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      // this.dboClass.itemId = this.tempaddonrhs[j].itemId;
      // this.dboClass.itemName = null;
      // if(this.subItemIdd == 0 || this.subItemIdd== null){
      //    this.dboClass.subItemId = 0;
      //   }else {
      //     this.dboClass.subItemId =  this.tempaddonrhs[j].subItemId;           
      //   }  
      // this.dboClass.subItemName = null;
      // this.dboClass.colId =  this.tempaddonrhs[j].colId;
      // this.dboClass.colNm = null;
      // this.dboClass.colValCd =  this.tempaddonrhs[j].colValCd; 
      // this.dboClass.quantity = 1;
      //uncomments after css changes
     // this.dboClass.quantity =this.newAddQtyO[index] ;         
      // this.dboClass.cost =  this.tempaddonrhs[j].colValCd;          
      // this.dboClass.techRemarks =  null;
      // this.dboClass.comrRemarks =  this.tempaddonrhs[j].colValCd;
      // this.dboClass.addOnFlg = 1;
      // this.dboClass.techFlag = 1;
      // this.dboClass.comrFlag = 1;
for(let j=0;j<this.addOnList1.length;j++)
{
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.addOnList1[j].itemId;
      this.dboClass.itemName = null;
      if(this.subItemIdd == 0 || this.subItemIdd == null){
         this.dboClass.subItemId = this.addOnList1[j].subItemId;
        }else {
          this.dboClass.subItemId = this.addOnList1[j].subItemId;           
        } 
        this.dboClass.subItemName = null;
        if(this.subItemTypeId == 0 || this.subItemTypeId == null){
         this.dboClass.subItemTypeId = this.addOnList1[j].subItemTypeId;
        }else {
          this.dboClass.subItemTypeId =  this.addOnList1[j].subItemTypeId;           
        }  
  
      this.dboClass.subItemTypeName=null;
      this.dboClass.colId = this.addOnList1[j].colId;
      this.dboClass.colNm = null;
      this.dboClass.colValCd =this.addOnList1[j].colValCd; 
      this.dboClass.quantity = 1;
      //uncomments after css changes
     // this.dboClass.quantity =this.newAddQtyO[index] ;         
      this.dboClass.cost =this.addOnList1[j].rhsCost;          
      this.dboClass.techComments =null ;
      this.dboClass.comrComments = this.addOnList1[j].rhsColComrcomments;
      this.dboClass.addOnNewColFlag = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
     
      this.addOnList.push(this.dboClass);
      }
    }
       }
    console.log(this._ITOeditQoutService.dboF2fData);

    // getting data from edit flow item creation (others)

    // if(this._ITOeditQoutService.f2fOthersItemList.length != 0){
    //   console.log(this._ITOeditQoutService.f2fOthersItemList);
    //   this.othersCheck = true;
    //   this.displayItemCompOthTable = true;
    //   this.itemOthersAddonList = this._ITOeditQoutService.f2fOthersItemList.reduce((acc, current) => {
    //    console.log(acc, current);
    //    const x = acc.find(item => item.itemName === current.itemName);
    //    if (!x) {
    //      return acc.concat([current]);
    //    } else {
    //      return acc;
    //    } 
    //  }, []);
    //  for(let z=0; z<this.itemOthersAddonList.length; z++){
    //    if(this.itemOthersAddonList[z].othersCost != 0){
    //    this.itemOthersAddonList[z].cost = this.itemOthersAddonList[z].othersCost;
    //  }
    //  }
     
    //   this.lhsRhsItemsList = this._ITOeditQoutService.f2fOthersItemList;
    // }
   
    if(this._ITOeditQoutService.f2fOthersItemList.length != 0){
      console.log(this._ITOeditQoutService.f2fOthersItemList);
      this.othersCheck = true;
      this.displayItemCompOthTable = true;
      this.itemOthersAddonListTemp = this._ITOeditQoutService.f2fOthersItemList.reduce((acc, current) => {
       console.log(acc, current);
       const x = acc.find(item => item.itemName === current.itemName);
       if (!x) {
         return acc.concat([current]);
       } else {
         return acc;
       } 
     }, []);
     for(let z=0; z<this.itemOthersAddonListTemp.length; z++){
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.itemOthersAddonListTemp[z].itemId;
      this.dboClass.itemName = this.itemOthersAddonListTemp[z].itemName; 
      this.dboClass.subItemId = null;
      this.dboClass.subItemName = null;
      this.dboClass.subItemTypeId = null;
      this.dboClass.subItemTypeName = null;
      this.dboClass.colId = null;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = null;
      this.dboClass.quantity = this.itemOthersAddonListTemp[z].quantity;
      if(this._ITOcustomerRequirementService.editFlag == true){
        this.dboClass.cost = this.itemOthersAddonListTemp[z].othersCost;
      }else{
        this.dboClass.cost = this.itemOthersAddonListTemp[z].cost;
      }
      this.dboClass.techComments = this.itemOthersAddonListTemp[z].techComments;
      this.dboClass.comrComments = this.itemOthersAddonListTemp[z].comrComments;
      this.dboClass.addOnNewColFlag = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
      this.itemOthersAddonList.push(this.dboClass);
     }     
      this.lhsRhsItemsListTemp = this._ITOeditQoutService.f2fOthersItemList;
      for(let p=0; p<this.lhsRhsItemsListTemp.length; p++){
        this.dboClass = new dboClass();
        this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        this.dboClass.itemId = null;
        this.dboClass.itemName = this.lhsRhsItemsListTemp[p].itemName; 
        this.dboClass.subItemId = null;
        this.dboClass.subItemName = null;
        this.dboClass.subItemTypeId = null;
        this.dboClass.subItemTypeName = null;
        this.dboClass.colId = this.lhsRhsItemsListTemp[p].colId;
        this.dboClass.colNm = this.lhsRhsItemsListTemp[p].colNm;
        this.dboClass.colValCd = this.lhsRhsItemsListTemp[p].colValCd;
        this.dboClass.quantity = this.lhsRhsItemsListTemp[p].quantity;
        if(this._ITOcustomerRequirementService.editFlag == true){
          this.dboClass.cost = this.lhsRhsItemsListTemp[p].othersCost;
        }else{
          this.dboClass.cost = this.lhsRhsItemsListTemp[p].cost;
        }
        this.dboClass.techComments = this.lhsRhsItemsListTemp[p].techComments;
        this.dboClass.comrComments = this.lhsRhsItemsListTemp[p].comrComments;
        this.dboClass.addOnNewColFlag = 1;
        this.dboClass.techFlag = 1;
        this.dboClass.comrFlag = 1;
        this.lhsRhsItemsList.push(this.dboClass);
       }   
    }

   // getting data from edit flow
  if (this._ITOeditQoutService.dboF2fData.length != 0) {
    this.mainSave=false;
    this.mainSave2=false;
    this.finalCostBool = true;
    if(this._ITOeditQoutService.dboF2fData[0].techComments != null){
      this.othItmTechRemChk = true;
      this.itemRemarkDiv = true;
      this.itemRemarksVal = this._ITOeditQoutService.dboF2fData[0].techComments;
    }
    if(this._ITOeditQoutService.dboF2fData[0].comrComments != null){
     this.othItmComrRemChk = true;
     this.itemComrRemarkDiv = true;
     this.itemCmrRemarksVal = this._ITOeditQoutService.dboF2fData[0].comrComments;
   }
        

        this.newSet = [];
       //take the uniqe items names
        this.newSet = Array.from(new Set(this._ITOeditQoutService.dboF2fData.map((x) => {
          return x.f2fItemId;
        })));
        console.log(this.newSet);
        let newArr: Array<any> = [];
        console.log(this._ITOeditQoutService.dboF2fData)
        for (let h = 0; h < this.newSet.length; h++) {
          for (let i = 0; i < this._ITOeditQoutService.dboF2fData.length; i++) {
            if (this.newSet[h] == this._ITOeditQoutService.dboF2fData[i].f2fItemId) {
              newArr.push(this._ITOeditQoutService.dboF2fData[i]);
              break;
            }
          }
        }
       // filtered array with all data
        console.log(newArr);
        if (newArr != []) {
          this.errMsgRhsCost=[];
               
          this.errDisplayPnl=[];
          for (let m = 0; m < newArr.length; m++) {
            this.itemcost=0;
              this.defaultValues = [];
              if (this._ITOeditQoutService.dboF2fData.length != 0) {
                this.prevData = this._ITOeditQoutService.dboF2fData.filter((x) => {
                  return ((x.f2fItemId == newArr[m].f2fItemId) && x.addOnNewColFlag==0 && x.lhsFlag==0 );
                })
                for (let c = 0; c< this.prevData.length; c++) {
                  if( this.prevData[c].rhsCost > 0 && this.prevData[c].lhsFlag == 0){
                    this.errMsgRhsCost[this.prevData[c].colId] = "AddOnCost: " +this.prevData[c].rhsCost;               
                    this.errDisplayPnl[this.prevData[c].colId] = true;
                   //  this.errDisplayPnl[this.prevData[c].colId] = true;
                  }else{
                    if( this.prevData[c].rhsCost == 1 && this.prevData[c].lhsFlag == 0){
                      this.errMsgRhsCost[this.prevData[c].colId] = "Actual Cost not Available. Get the same from SCM: " +this.prevData[c].rhsCost;               
                      this.errDisplayPnl[this.prevData[c].colId] = true;
                     //  this.errDisplayPnl[this.prevData[c].colId] = true;
                    }
                  }
                 
                  console.log(this.prevData);
                }
          
                if (this.prevData.length != 0) {
                  for (let d = 0; d < this.prevData.length; d++) {
                    this.defaultValues.push(this.prevData[d].colValCd);
                  }
                }
                
this.itemcost=this.prevData[0].itemCost;
              }

         //pushing selected components data fromm storage to local variable
            this.dboEleFullArray.push({
              id: newArr[m].f2fItemId,
              qty: newArr[m].quantity,
              componenet: newArr[m].itemName,
              defaultValues: this.defaultValues,
              dboCost: newArr[m].basicCost,
              dboAddOnCost: newArr[m].addOnCost,
              techComments: newArr[m].techComments,
              comrComments: newArr[m].comrComments,
              techRemarks: newArr[m].techRemarks,
              comrRemarks: newArr[m].comrRemarks,
              selectedNewAdds: this.selectedNewAdds,
              additionalCost: newArr[m].newAddonCost,
              compOthersAddonList: this.compOthersAddonList1,
              itemId: newArr[m].itemId,
              itemName: newArr[m].itemName,
              subItemId: newArr[m].subItemId,
              subItemName: newArr[m].subItemName,
              subItemTypeId: newArr[m].subItemTypeId,
              subItemTypeName: newArr[m].subItemTypeName,
              subItemTechRemarks: newArr[m].subItemTechRemarks,
              subItemComrRemarks: newArr[m].subItemComrRemarks,
              subItemTypeTechRemarks: newArr[m].subItemTypeTechRemarks,
              subItemTypeComrRemarks: newArr[m].subItemTypeComrRemarks,
              errMsgRhsCost:this.errMsgRhsCost,
              errDisplayPnl:this.errDisplayPnl,
              itemcost:this.itemcost
         
              
            });
            this.addedClassList.push(newArr[m].itemName);
            console.log(this.dboEleFullArray);
            let savedMech: Array<any> = [];
          }
        }
        for(let f=0; f<this.dboEleFullArray.length; f++){
          for(let c=0; c<this._ITOeditQoutService.dboF2fNewAddOns.length; c++){
            if(this.dboEleFullArray[f].itemId == this._ITOeditQoutService.dboF2fNewAddOns[c].itemId && this.dboEleFullArray[f].subItemId == this._ITOeditQoutService.dboF2fNewAddOns[c].subItemId &&
            this.dboEleFullArray[f].subItemTypeId == this._ITOeditQoutService.dboF2fNewAddOns[c].subItemTypeId){
              this.dboEleFullArray[f].selectedNewAdds.push(this._ITOeditQoutService.dboF2fNewAddOns[c]);
            }
          }
        }
        // console.log(this.dboEleFullArray);   
        console.log(this.errMsgRhsCost);
   
    //To get the unique list of items selected from edit mode
    this.itemSelectedListEdit = this._ITOeditQoutService.dboF2fData.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.itemName === current.itemName);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);

    //To get the unique list of sub items selected from edit mode
    this.subitemSelectedListEdit = this._ITOeditQoutService.dboF2fData.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.subItemName ===  current.subItemName);
      if (!x) {       
          return acc.concat([current]);        
        
      } else {
        return acc;
      } 
    }, []);
    console.log(this.subitemSelectedListEdit);
    
     //To get the unique list of sub item types selected from edit mode
     this.subitemTypeSelectedListEdit = this._ITOeditQoutService.dboF2fData.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.subItemTypeName === current.subItemTypeName);
      if (!x) {       
          return acc.concat([current]);       
      } else {
        return acc;
      } 
    }, []);
    console.log(this.subitemTypeSelectedListEdit);
   
      //To set to null in the beginning of the edit mode
      this.selectdEl= [];
      this.selectdEl1 = [];
      this.selectdEl2 = [];
      this.itemIdList = [];
      this.itemIdList1 = [];
      this.itemIdList2 = [];

      //To write the logic to check the item ids in the main panel
      for(let m = 0; m < this.itemSelectedListEdit.length; m++)
      {
        for (let k = 0; k < this.newArray.length; k++)
        {
          if(this.itemSelectedListEdit[m].itemName == this.newArray[k].itemName )
          {
            //To check the item in display list
            this.selectdEl[k] = true;
            this.itemIdList[k] = this.itemSelectedListEdit[m].itemId;
          }

        }
      }

       //To write the logic to check the subitem ids in the main panel
       if(this.subitemSelectedListEdit.length != 0)
       {
          this.subList1=[];
          this.sublistDup =[];
            for (let s = 0; s < this.dboF2fItemList.length; s++) {                
              if(this.dboF2fItemList[s].itemId && this.dboF2fItemList[s].subItemName!=null){
                this.sublistDup.push(this.dboF2fItemList[s]);
              }
            }
              //Filter records based on sub item Name
              this.subList1 = this.sublistDup.reduce((acc, current) => {
              console.log(acc, current);
              const x = acc.find(item => item.subItemName === current.subItemName);
              if (!x) {
                return acc.concat([current]);
              } else {
                return acc;
              } 
            }, []);
            for(let m = 0; m < this.subitemSelectedListEdit.length; m++)
            {
              for (let k = 0; k < this.subList1.length; k++)
              {
                if(this.subitemSelectedListEdit[m].subItemId == this.subList1[k].subItemId )
                {
                  //To check the item in display list
                  this.selectdEl1[k] = true;                 
                  this.itemIdList1[k] = this.subitemSelectedListEdit[m].subItemId;
                }
      
              }
            }
       }
       //Edit mode
       if(this.subitemTypeSelectedListEdit.length != 0)
       { 
        this.subList2=[];
        for (let s = 0; s < this.dboF2fItemList.length; s++) {
          if(this.dboF2fItemList[s].subItemId && this.dboF2fItemList[s].subItemTypeName!=null){
              this.subList2.push(this.dboF2fItemList[s]);
            }
          }          
           //To write the logic to check the subitem ids in the sub panel
           for(let m = 0; m < this.subitemTypeSelectedListEdit.length; m++)
           {
             for (let k = 0; k < this.subList2.length; k++)
             {
               if(this.subitemTypeSelectedListEdit[m].subItemTypeId == this.subList2[k].subItemTypeId )
               {
                 //To check the sub item in display list
                 this.selectdEl2[k] = true;
                 this.itemIdList2[k] = this.subitemTypeSelectedListEdit[m].subItemTypeId;
                 
               }

             }
           }  
       } 
       if(_ITOcustomerRequirementService.editFlag==false)
{
  if(this.storage.get(this.F2FTurbine).selectdEl.length != 0){
    this.selectdEl = this.storage.get(this.F2FTurbine).selectdEl;
  }
  if(this.storage.get(this.F2FTurbine).selectdEl1.length != 0){
    this.selectdEl1 = this.storage.get(this.F2FTurbine).selectdEl1;
  }
  if(this.storage.get(this.F2FTurbine).selectdEl2.length != 0){
    this.selectdEl2 = this.storage.get(this.F2FTurbine).selectdEl2;
  }
  if(this.storage.get(this.F2FTurbine).itemIdList.length != 0){
    this.itemIdList = this.storage.get(this.F2FTurbine).itemIdList;
  }
  if(this.storage.get(this.F2FTurbine).itemOthersAddonList.length != 0){
    this.itemOthersAddonList = this.storage.get(this.F2FTurbine).itemOthersAddonList;
  }
  if(this.storage.get(this.F2FTurbine).itemIdList1.length != 0){
    this.itemIdList1 = this.storage.get(this.F2FTurbine).itemIdList1;
  }
  if(this.storage.get(this.F2FTurbine).itemIdList2.length != 0){
    this.itemIdList2 = this.storage.get(this.F2FTurbine).itemIdList2;
  }
  if(this.storage.get(this.F2FTurbine).dboEleFullArray.length != 0){
    this.dboEleFullArray = this.storage.get(this.F2FTurbine).dboEleFullArray;
  }
  // if(this.storage.get(this.F2FTurbine).compOthersAddonList.length != 0){
  //   this.compOthersAddonList = this.storage.get(this.F2FTurbine).compOthersAddonList;
  // }
}  
      //this.finalEleCost = this._ITOeditQoutService.dboF2fData[0].totalPrice;
      //this.tempFinalEleCost = this._ITOeditQoutService.dboF2fData[0].totalPrice;  
      this.oneLineLocArray = this.storage.get(this.oneLineLoc);
      this.finalEleCost = this.oneLineLocArray.f2fCost;
      this.totalF2fCost = this.oneLineLocArray.totalF2fCost;
  }  
  if(_ITOcustomerRequirementService.editFlag==false ||  this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard" ||  this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Non Standard")
  {
    if(this.storage.get(this.F2FTurbine).selectdEl.length != 0){
      this.selectdEl = this.storage.get(this.F2FTurbine).selectdEl;
    }
    if(this.storage.get(this.F2FTurbine).selectdEl1.length != 0){
      this.selectdEl1 = this.storage.get(this.F2FTurbine).selectdEl1;
    }
    if(this.storage.get(this.F2FTurbine).selectdEl2.length != 0){
      this.selectdEl2 = this.storage.get(this.F2FTurbine).selectdEl2;
    }
    if(this.storage.get(this.F2FTurbine).itemIdList.length != 0){
      this.itemIdList = this.storage.get(this.F2FTurbine).itemIdList;
    }
    if(this.storage.get(this.F2FTurbine).itemOthersAddonList.length != 0){
      this.itemOthersAddonList = this.storage.get(this.F2FTurbine).itemOthersAddonList;
    }
    if(this.storage.get(this.F2FTurbine).itemIdList1.length != 0){
      this.itemIdList1 = this.storage.get(this.F2FTurbine).itemIdList1;
    }
    if(this.storage.get(this.F2FTurbine).itemIdList2.length != 0){
      this.itemIdList2 = this.storage.get(this.F2FTurbine).itemIdList2;
    }
    if(this.storage.get(this.F2FTurbine).dboEleFullArray.length != 0){
      this.dboEleFullArray = this.storage.get(this.F2FTurbine).dboEleFullArray;
    }
    // if(this.storage.get(this.F2FTurbine).compOthersAddonList.length != 0){
    //   this.compOthersAddonList = this.storage.get(this.F2FTurbine).compOthersAddonList;
    // }
  }  
          });
      });
      console.log(this.oneLineLocArray);
      if (this.storage.get(this.F2FTurbine) == null) {
        this.saveInLocal(this.F2FTurbine, { 
      dboEleFullArray: this.dboEleFullArray, compOthersAddonList: this.compOthersAddonList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1, itemIdList2: this.itemIdList2,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1, selectdEl2: this.selectdEl2,
      lhsRhsSubItemsList: this.lhsRhsSubItemsList, subItemOthAddonList: this.subItemOthAddonList,
      lhsRhsSubItmTypList: this.lhsRhsSubItmTypList, subItmTypOthList: this.subItmTypOthList,
      lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,      
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      subItmTypRmkValOut: this.subItmTypRmkValOut, subItmTypComRemValOut: this.subItmTypComRemValOut,
      finalEleCost: this.finalEleCost, totalF2fCost: this.totalF2fCost, addOnList:this.addOnList,addonflg:this.addonflg,subQuantity:this.subQuantity, 
      lubItemcost: this.lubItemcost, discountPerS: this.discountPerS
        });
      }

       //call one line BOM
       this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
        console.log(resOnline);
        this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
        this.saveInLocal(this.oneLineLoc, resOnline.oneLineBomExcel);
      });
     
    })
  }
  dbof2f(i)
  {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;

this.selectdEl[i]=true;
  }
  ngOnInit() {
    if (this.F2FSapLocalStorage[this.F2FTurbine]) {
      let turbConfigVal = new turbineConfigValues('');
      turbConfigVal.cNum=this.sapData.cNum;
      turbConfigVal.variantCode=this.sapData.variantCode;
      turbConfigVal.defaultFlag=true;
      turbConfigVal.custName=this.sapData.custName;
     this.matchedCProjectList.push(turbConfigVal);
     this.displayCno=true;
     this.dispSaveBtn=true;
    }
    this.regCols = [
      { field: 'questionCode', header: 'Question Code' },
      { field: 'questionDesc', header: 'Question Description' },
      { field: 'answerCd', header: 'Answer Code' },
      { field: 'answerDesc', header: 'Answer Description' },
    ];
  }

  //filtering array so that null values will be removed
  ngAfterContentChecked() {
    this.dboEleFullArray = this.dboEleFullArray.filter((x) => {
      return x != null;
    })

    if (this.finalEleCost != 0) {
      if (this.finalEleCost != this.tempFinalEleCost) {
        // this.OverWrittenfinalEleCost = 0;
        this.tempFinalEleCost = 0;
      }
    }
  
  }
  cancelAddOn(key,z) 
  {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.AddOnFlagNew[key]=false;
    this.openbtn[key]=false;
    this.rhs[key]=false;
    console.log(key, z)
    if(this.addOnList.length != 0) {
      for(let k = 0; k < this.addOnList.length; k ++){
        if(this.addOnList[k].colId == key){
          this.addOnList.splice(k,1);
        }
      }  
    }
    this.openOth[key] = false;
    this.newAddComRemrkO1[key] = "";
    this.newAddCostO1[key] = "";
    this.newAddNameO1[key] = "";
    this.addonflg="0";
    // this.newAddRemrkO[key] = "";
    // this.newAddQtyO[key] = "";
  //  this.lhsdisableparent[key]=false;

  }
  rhsOthersFormEdit(val,index,data){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.addonflg="0";
    this.openbtn[index]=true;
  } 
  rhsOthersForm(val,index,data)

  {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(data);
    // this.addOnList=[];
     this.openbtn[index]=false;
     //this.AddOnFlagNew[index]=true;
     console.log(this.newAddNameO[index]);
     console.log(this.newAddCostO[index]);
     console.log(this.newAddComRemrkO[index]);
    //      for(let n = 0; n < this.dboMechPanelList.length; n++){
    //    if(val ==  this.dboMechPanelList[n].colNm){
    //      this.colValNmId = this.dboMechPanelList[n].colId;
    //    }
    //  }
    for(let j=0;j<this.addOnList.length;j++)
    {
      if(this.addOnList[j].colId==index)
      {
        this.addOnList.splice(j,1);
        break;
      }
    }
     this.dboClass = new dboClass();
     this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
     this.dboClass.itemId = this.itemIdd;
     this.dboClass.itemName = null;
     if(this.subItemIdd == 0 || this.subItemIdd == null){
        this.dboClass.subItemId = 0;
       }else {
         this.dboClass.subItemId = this.subItemIdd;           
       } 
       this.dboClass.subItemName = null;
       if(this.subItemTypeId == 0 || this.subItemTypeId == null){
        this.dboClass.subItemTypeId = 0;
       }else {
         this.dboClass.subItemTypeId =  this.subItemTypeId;           
       }  
  
     this.dboClass.subItemTypeName=null;
     this.dboClass.colId = index;
     this.dboClass.colNm = null;
     this.dboClass.colValCd = this.newAddNameO1[index]; 
     this.dboClass.quantity = 1;
     //uncomments after css changes
    // this.dboClass.quantity =this.newAddQtyO[index] ;         
     this.dboClass.cost = this.newAddCostO1[index];          
     this.dboClass.techComments =null ;
     this.dboClass.comrComments = this.newAddComRemrkO1[index];
     this.dboClass.addOnNewColFlag = 1;
     this.dboClass.techFlag = 1;
     this.dboClass.comrFlag = 1;
    
     this.addOnList.push(this.dboClass);
     this.addonflg="0";

  }
  ngAfterViewChecked() {
    if (this.oneLineLocArray != null) {
      let savedMech: Array<any> = [];
      for (let d = 0; d < this.oneLineLocArray.f2fList.length; d++) {
        savedMech.push(this.oneLineLocArray.f2fList[d].categoryDetDesc);
        
      }
      // this.changeBgColor(this.addedClassList, this.completeList);
    }
  }
  
  //Check box click
  dboF2fItmList(event, itemId, i){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(event, itemId, i);
    
    if (!event.target.checked) { // on uncheck 
      if(itemId == 6){
        this.lubItemcost=0;
        this.discountPerS=0;
        this.subQuantity=1;
      }
      if (this.itemIdList.includes(itemId)) {
        for(let j=0;j<this.itemIdList.length;j++)
        {
          if(this.itemIdList[j]==itemId)
          {
          this.itemIdList[this.itemIdList.indexOf(itemId)] = null;
          }
        }
    
        for (let k = 0; k < this.newArray.length; k++)
        {
          if(itemId == this.newArray[k].itemId)
          {
            //If the selected item is having a sub item
            // Need to clear the subitems also (For ex inside Lubeoil)
            if(this.newArray[k].subItemId != 0)
            {
              for(let m = 0; m < this.selectdEl1.length; m++)
              {
                this.selectdEl1[m] = false;
                this.itemIdList1[m] = null;
              }
              //The bearings also will be unchecked in this proocess
              // Nedd to clear the subitemtypedid also (Ex: Journal bearings)
              for(let m = 0; m < this.selectdEl2.length; m++)
              {
                this.selectdEl2[m] = false;
                this.itemIdList2[m] = null;
              }                         
            }           
            break;
          }
        }
        this.selectdEl[i] = false; //To uncheck the selected item    
        if(itemId == 3 && this.checkMBRBool == true){
          this.selectdEl[i+1] = false;
        }
      }
      for(let k = 0; k < this.dboEleFullArray.length; k++){
        if(this.dboEleFullArray[k].itemId == itemId){
            this.dboEleFullArray[k] = null;
            this.dboEleFullArray.slice(k, 1);
          
        }
      }
      for(let k = 0; k < this._ITOeditQoutService.dboF2fData.length; k++){
        if(this._ITOeditQoutService.dboF2fData[k].itemId == itemId){
          this._ITOeditQoutService.dboF2fData[k] = null;
          this._ITOeditQoutService.dboF2fData.slice(k, 1);
          
        }
      }


    }
    this._ITOeditQoutService.dboF2fData = this._ITOeditQoutService.dboF2fData.filter(n => n != null);
    for(let k = 0; k < this.addOnList.length; k++){
      if(this.addOnList[k].itemId == itemId){
          this.addOnList[k] = null;
          this.addOnList.slice(k, 1);
       
      }
    }
  
  this.addOnList =  this.addOnList.filter(n => n != null);
    console.log("after");
    console.log(this.dboEleFullArray)
  }

  dboF2fItmListSub(event, itemId, i){
    this.subItembutton=true;
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(event, itemId, i);
    if(this.itemIdList.includes(6)){
      this.enableQty=true;
    }else{
      this.enableQty = false;
      this.subQuantity=1;
      this.discountPerS=0;
    }
    if (!event.target.checked) { // on uncheck 
      if (this.itemIdList1.includes(itemId)) {
        this.itemIdList1[this.itemIdList1.indexOf(itemId)] = null;
        this.selectdEl1[i] = false;
        this.count =0;
        for (let k = 0; k < this.subList1.length; k++)
        {
          if(itemId == this.subList1[k].subItemId)
          {
            //If we uncheck bearings need to uncheck the
            // journal and thrust bearings
            this.itemtempId = this.subList1[k].itemId;
            if(this.subList1[k].subItemTypeId != 0)
            {
              for(let m = 0; m < this.selectdEl2.length; m++)
              {
                this.selectdEl2[m] = false;
                this.itemIdList2[m] = null;
              }              
            }
            break; 
          }
        }
        //Need to uncheck lubeoil also if all the subitems are unchecked
        //count the number of subitems arechecked
        for (let k = 0; k < this.subList1.length; k++)
        {
          if(this.selectdEl1[k] == true)
          {
              this.count = this.count + 1;
          }
        }
        if(this.count == 0)
        {
          for (let k = 0; k < this.newArray.length; k++)
          {
            if(this.itemtempId == this.newArray[k].itemId)
            {
              this.selectdEl[k] = false;
              this.itemIdList[k] = null;
              this.enableQty=false;
              this.subQuantity=1;
              break;
            }
          }

        }
        
        // this.selectdEl2[i] = false;
      }
      for(let k=0; k<this.dboEleFullArray.length; k++){
        if(this.dboEleFullArray[k].subItemId == itemId){
          this.dboEleFullArray[k] = null;
          this.dboEleFullArray.slice(k,1);
          
        }
      }
      for(let k = 0; k < this._ITOeditQoutService.dboF2fData.length; k++){
        if(this._ITOeditQoutService.dboF2fData[k].subItemId == itemId){
          this._ITOeditQoutService.dboF2fData[k] = null;
          this._ITOeditQoutService.dboF2fData.slice(k, 1);
          
        }
      }
    }
    this._ITOeditQoutService.dboF2fData = this._ITOeditQoutService.dboF2fData.filter(n => n != null);
    for(let k = 0; k < this.addOnList.length; k++){
      if(this.addOnList[k].subItemId == itemId){
          this.addOnList[k] = null;
          this.addOnList.slice(k, 1);
       
      }
    }
  
  this.addOnList =  this.addOnList.filter(n => n != null);
    console.log("after");
    console.log(this.dboEleFullArray);
    
  }

  dboF2fItmListsubType(event, itemId, i){
    this.subItemTypebutton=true;
    this.subItembutton=true;
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(event, itemId, i);
    if (!event.target.checked) { // on uncheck 
      if (this.itemIdList2.includes(itemId)) {
        this.itemIdList2[this.itemIdList2.indexOf(itemId)] = null;
        this.selectdEl2[i] = false;
      
      this.count = 0;

      //it is required to check all the sub item types are unchecked
      //For ex (journal and thrust bearings)
      //Need to uncheck bearings also
      for (let k = 0; k < this.subList2.length; k++)
      {
        this.subitemtempId = this.subList2[k].subItemId;
        this.itemtempId = this.subList2[k].itemId
          if(this.selectdEl2[k] == true)
          {
            this.count = this.count + 1;
          }
      }
      //If all the items are uncheckes then count will be zero
      //Need to uncheck bearings
      if(this.count == 0)
      {
        for (let k = 0; k < this.subList1.length; k++)
        {          
          if(this.subitemtempId == this.subList1[k].subItemId)
          {
            this.selectdEl1[k] = false;
            this.itemIdList1[k] = null;
            break;
          }
        }
        //Need to uncheck Lube oil also if bearings is unchecked
        //and Also all the subitems are unchecked under lube oil
        this.count = 0;
        for (let k = 0; k < this.subList1.length; k++)
        {
          if(this.selectdEl1[k] == true)
          {
            this.count = this.count + 1;
          }
        }
        if(this.count == 0)
        {
          for (let k = 0; k < this.newArray.length; k++)
          {
            if(this.itemtempId == this.newArray[k].itemId)
            {
              this.selectdEl[k] = false;
              this.itemIdList[k] = null;
              break;
            }
          }
        }
      }      
    }
    for(let k=0; k<this.dboEleFullArray.length; k++){
      if(this.dboEleFullArray[k].subItemTypeId == itemId){
        this.dboEleFullArray[k] = null;
        this.dboEleFullArray.slice(k, 1);
        
      }
    }
    for(let k = 0; k < this._ITOeditQoutService.dboF2fData.length; k++){
      if(this._ITOeditQoutService.dboF2fData[k].subItemTypeId == itemId){
        this._ITOeditQoutService.dboF2fData[k] = null;
        this._ITOeditQoutService.dboF2fData.slice(k, 1);
        
      }
    }
  }
  this._ITOeditQoutService.dboF2fData = this._ITOeditQoutService.dboF2fData.filter(n => n != null);
  for(let k = 0; k < this.addOnList.length; k++){
    if(this.addOnList[k].subItemTypeId == itemId){
        this.addOnList[k] = null;
        this.addOnList.slice(k, 1);
     
    }
  }

this.addOnList =  this.addOnList.filter(n => n != null);
    console.log("after");
  console.log(this.dboEleFullArray);
}

  //On click item name
  dboElementSel(d,nm,i){
    this.diableitemname=false;
    this.disablelhs=[];
this.getPrice=true;
    this.saveBtColor=true;
    this.discountPer = 0;
    this.subQuantity = 1;
    this.lubItemcost=0;
    this.qty = 1;
    this.itemcost=0;
    this.rhs=[];
    console.log(d,nm,i);
    console.log(this.selectdEl[i]);
    this.noAddOns = false;
    this.ChecktoResetAddOnCost = 0;
    this.dboAddOnCost = 0; 
    this.addonflg="0";
    this.subItemTechRmkDiv = false;
    this.subItemRemarksVal = '';
    this.subItemComrRmkDiv = false;
    this.subItemCmrRemVal = '';
    this.subItemtechCheck = false;
   this.subItemComrrCheck = false;
    this.othersCompCheck = false;
    this.displayCompOthTable = false;
    this.compOthersAddonList = [];
    this.lhsRhsSubItemsList = [];
    this.eleOtherDisp = [];
    this.displaySubItemOthTable = false;
    this.othersSubItemCheck = false;
    this.subItemOthAddonList = [];  
    this.finalItem = nm;
    this.defaultValues = [];
    this.newAddOnList = [];
    this.dboCost = 0;
    this.dboFormData.itemId = d.itemId;
    this.dboFormData.subItemTypeId = d.subItemTypeId;
    this.dboFormData.subItemId = d.subItemId;
    this.itemIdd = d.itemId;
    this.itemNamee = d.itemName;
    this.subItemIdd = d.subItemId;
    this.subItemNamee = d.subItemName;
    this.subItemTypeId = d.subItemTypeId;
    this.f2fItemId = d.f2fItemId; 
    this.openOth=[];
    this.openbtn=[]; 
    this.AddOnFlagNew=[];
    console.log(this.discountPerS);
    if(this.finalItem === this.itemNamee){
      this.discountPerS = 0;
      this.discountPerS=1;
    }
    if(this.storage.get(this.F2FTurbine).subItemCmrRemValOut != null && this.storage.get(this.F2FTurbine).subItemCmrRemValOut != ""){
      this.subItemCmrRemValOut = this.storage.get(this.F2FTurbine).subItemCmrRemValOut;
      this.subItemComRem = true;
      this.subItemComrRemDiv = true;
    }
    if(this.storage.get(this.F2FTurbine).subItemRmkValOut != null && this.storage.get(this.F2FTurbine).subItemRmkValOut != ""){
      this.subItemRmkValOut = this.storage.get(this.F2FTurbine).subItemRmkValOut;
      this.subItemTchRem = true;
      this.subItemRemarkDiv = true;
    }
      //added 13thnov
      //If there are no addons for all the panels make it zero
      for(let j=0;j<this.addOnList.length;j++)
      {
        if(this.addOnList[j].itemId==this.itemIdd && this.addOnList[j].subItemId==this.subItemIdd && this.addOnList[j].subItemTypeId==this.subItemTypeId )
        {
          this.openOth[this.addOnList[j].colId]=true;
          this.openbtn[this.addOnList[j].colId]=false;
          this.rhs[this.addOnList[j].colId]=true;
          this.newAddComRemrkO1[this.addOnList[j].colId]=this.addOnList[j].comrComments;
          this.newAddNameO1[this.addOnList[j].colId]=this.addOnList[j].colValCd;
          this.newAddCostO1[this.addOnList[j].colId]=this.addOnList[j].cost;
          this.AddOnFlagNew[this.addOnList[j].colId]=true;
        }
      }
      if(this._ITOeditQoutService.dboF2fNewAddOns.length != 0)
      {
        for(let k = 0; k < this._ITOeditQoutService.dboF2fNewAddOns.length; k++ )
        {
          if(d.f2fItemId == this._ITOeditQoutService.dboF2fNewAddOns[k].f2fItemId)
                    this.ChecktoResetAddOnCost = 1;
        }
        if(this.ChecktoResetAddOnCost == 0)
                this.dboAddOnCost = 0;
      }       
   //To display other in sub items panel 

  //   if(this._ITOeditQoutService.f2fOthersSubItemList.length != 0){
  //   this.othersSubCheck = true;
  //   this.displaySubItemOthTable = true;
  //   this.subItemOthAddonList = this._ITOeditQoutService.f2fOthersSubItemList.reduce((acc, current) => {
  //    console.log(acc, current);
  //    const x = acc.find(item => item.subItemName === current.subItemName);
  //    if (!x) {
  //      return acc.concat([current]);
  //    } else {
  //      return acc;
  //    } 
  //  }, []);
  //  for(let j=0; j<this.subItemOthAddonList.length; j++){
  //    if(this.subItemOthAddonList[j].othersCost != 0){
  //    this.subItemOthAddonList[j].cost = this.subItemOthAddonList[j].othersCost;
  //    }
  //  }
  //   this.lhsRhsSubItemsList = this._ITOeditQoutService.f2fOthersSubItemList;    
  // }


  if(this._ITOeditQoutService.f2fOthersSubItemList.length != 0){
    console.log(this._ITOeditQoutService.f2fOthersSubItemList);
    this.othersSubCheck = true;
    this.displaySubItemOthTable = true;
    this.subItemOthAddonListTemp = this._ITOeditQoutService.f2fOthersSubItemList.reduce((acc, current) => {
     console.log(acc, current);
     const x = acc.find(item => item.subItemName === current.subItemName);
     if (!x) {
       return acc.concat([current]);
     } else {
       return acc;
     } 
   }, []);
   for(let z=0; z<this.subItemOthAddonListTemp.length; z++){
    this.dboClass = new dboClass();
    this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboClass.itemId = this.subItemOthAddonListTemp[z].itemId;
    this.dboClass.itemName = null; 
    this.dboClass.subItemId = null;
    this.dboClass.subItemName = this.subItemOthAddonListTemp[z].subItemName;
    this.dboClass.subItemTypeId = null;
    this.dboClass.subItemTypeName = null;
    this.dboClass.colId = null;
    this.dboClass.colNm = null;
    this.dboClass.colValCd = null;
    this.dboClass.quantity = this.subItemOthAddonListTemp[z].quantity;
    if(this._ITOcustomerRequirementService.editFlag == true){
      this.dboClass.cost = this.subItemOthAddonListTemp[z].othersCost;
    }else{
      this.dboClass.cost = this.subItemOthAddonListTemp[z].cost;
    }
    this.dboClass.techComments = this.subItemOthAddonListTemp[z].techComments;
    this.dboClass.comrComments = this.subItemOthAddonListTemp[z].comrComments;
    this.dboClass.addOnNewColFlag = 1;
    this.dboClass.techFlag = 1;
    this.dboClass.comrFlag = 1;
    this.subItemOthAddonList.push(this.dboClass);
   }     
    this.lhsRhsSubItemsListTemp = this._ITOeditQoutService.f2fOthersSubItemList;
    for(let p=0; p<this.lhsRhsSubItemsListTemp.length; p++){
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.lhsRhsSubItemsListTemp[p].itemId;
      this.dboClass.itemName = null; 
      this.dboClass.subItemId = null;
      this.dboClass.subItemName = this.lhsRhsSubItemsListTemp[p].subItemName;
      this.dboClass.subItemTypeId = null;
      this.dboClass.subItemTypeName = null;
      this.dboClass.colId = this.lhsRhsSubItemsListTemp[p].colId;
      this.dboClass.colNm = this.lhsRhsSubItemsListTemp[p].colNm;
      this.dboClass.colValCd = this.lhsRhsSubItemsListTemp[p].colValCd;
      this.dboClass.quantity = this.lhsRhsSubItemsListTemp[p].quantity;
      if(this._ITOcustomerRequirementService.editFlag == true){
        this.dboClass.cost = this.lhsRhsSubItemsListTemp[p].othersCost;
      }else{
        this.dboClass.cost = this.lhsRhsSubItemsListTemp[p].cost;
      }
      this.dboClass.techComments = this.lhsRhsSubItemsListTemp[p].techComments;
      this.dboClass.comrComments = this.lhsRhsSubItemsListTemp[p].comrComments;
      this.dboClass.addOnNewColFlag = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
      this.lhsRhsSubItemsList.push(this.dboClass);
     }   
  }
    
      // this.index = 0;
      this.iFlag = 0;
      this.iItemindex = 0;
      //there are no subitems
      if(this.dboFormData.subItemId == 0)
      {
        this.index = i;      
      }

    //this is the first time selection of subitemtype(Ex:Bearings)

     if(this.subItemTypeId != 0 && this.iFlag == 0)
     {
      this.subItemTypebutton=true;

      if(this.selectdEl2.length!=0)
      {
        if(this.selectdEl2.includes(true))

{
     this.subItemTypebutton=false;

}
      }       for (let k = 0; k < this.subList1.length; k++)
       {
         if(d.subItemName == this.subList1[k].subItemName)
         {
           this.index = k;
           this.iFlag = 1;
           break;
         }
       }  
       for (let k = 0; k < this.newArray.length; k++)
       {
         if(d.itemName == this.newArray[k].itemName)
         {
          this.iItemindex = k;          
           break;
         }
       }       
     }
     if(this.dboFormData.subItemId != 0 && this.iFlag == 0)
     {
      this.subItembutton=true;

      if(this.selectdEl1.length!=0)
      {
        if(this.selectdEl1.includes(true))

{
     this.subItembutton=false;

}


      }
        for (let k = 0; k < this.newArray.length; k++)
        {
          if(d.itemName == this.newArray[k].itemName)
          {
            this.index = k;         
            break;
          }
        }
     }    
    
      this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.iFlag = 0;
      this.subItemTypeNamee = d.subItemTypeName;
      this.selectedELIndex = i;
      if(d.itemName == "Step Down Gear Box" && this.checkMBRBool == true){
        this.selectedELIndexM = i+1;
      }
      console.log(this.dboFormData);
          if(nm == d.itemName){
            this.subList1=[];
            this.sublistDup =[];
              for (let s = 0; s < this.dboF2fItemList.length; s++) {                
                if(d.itemId == this.dboF2fItemList[s].itemId && this.dboF2fItemList[s].subItemName!=null){
                  this.sublistDup.push(this.dboF2fItemList[s]);
                }
                //Filter records based on sub item Name
                this.subList1 = this.sublistDup.reduce((acc, current) => {
                console.log(acc, current);
                const x = acc.find(item => item.subItemName === current.subItemName);
                if (!x) {
                  return acc.concat([current]);
                } else {
                  return acc;
                } 
              }, []);
              console.log(this.subList1);
            }
            
            if(this.subList1.length>0){
              this.itemNm = nm;
              this.displayF2fSubList1 = true;
            }else{
              this.displayF2f = true;
              this.displayF2fSubList1 = false;
            }
          }
                  
           if(nm == d.subItemName){
              this.displayF2fSubList1 =true;
              this.displayF2f = true;             
            }else if(nm == d.subItemTypeName){
              this.displayF2fSubList1 = true;
              this.displayF2fSubList2 = true;
              this.displayF2f = true;              
            }
                   
       //set all required values in dbo form and pass as inputs to get dropdown values ans field names
       if(!this.selectdEl[i] && this.subItemIdd==0){
        this._ITOturbineConfigService.getF2fPanels(this.dboFormData).subscribe(respp => {
          console.log(respp);
          this.panelList = respp;
          this.newAddOnResp = respp;
          this.questionsBean = respp.questionsBean;
          this.dboF2fPanelList2 = respp.dboF2fPanelList1;
          for(let l = 0; l < this.dboF2fPanelList2.length; l++)
          {
            this.OthersFlag[l] = this.dboF2fPanelList2[l].othersFlag;
            console.log(this.OthersFlag[l]);
          }
          for(let l = 0; l <respp.dboF2fPanelList1.length; l++)
          {
            this.AddOnFlag[l] = respp.dboF2fPanelList1[l].addOnNewColFlag;
            console.log(this.AddOnFlag[l]);
          } 
          
             //to display dropdown default values in ui screen
            for (let l = 0; l < this.questionsBean.length; l++) {
              for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
                if (respp.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
                  this.defaultValues.push(respp.questionsBean[l].dropDownValueList[d].value.trim());
                }
              }
            }
        });
       }else{
        if (this._ITOeditQoutService.dboF2fNewAddOns.length > 0) {
          let sampl = this._ITOeditQoutService.dboF2fNewAddOns.filter((x) => (x.itemId == d.itemId) && (x.subItemId == d.subItemId) && (x.subItemTypeId == d.subItemTypeId));
          if (sampl.length != 0) {
            this.dboAddOnCost = 0;
            for (let m = 0; m < sampl.length; m++) {
              //added 13thnov
              this.dboAddOnCost = sampl[m].othersCost;
            }
            console.log(this.dboAddOnCost);
            this.AddOns();
          }
        }       
        this._ITOturbineConfigService.getF2fPanels(this.dboFormData).subscribe(respp => {
          console.log(respp);
          this.panelList = respp;
          this.newAddOnResp = respp;
          this.questionsBean = respp.questionsBean;
          this.dboF2fPanelList2 = respp.dboF2fPanelList1;
          for(let l = 0; l < this.dboF2fPanelList2.length; l++)
          {
            this.OthersFlag[l] = this.dboF2fPanelList2[l].othersFlag;
            console.log(this.OthersFlag[l]);
          }       
          for(let l = 0; l <respp.dboF2fPanelList1.length; l++)
          {
            this.AddOnFlag[l] = respp.dboF2fPanelList1[l].addOnNewColFlag;
            console.log(this.AddOnFlag[l]);
          }    
             //to display dropdown default values in ui screen
            for (let l = 0; l < this.questionsBean.length; l++) {
              for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
                if (respp.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
                  this.defaultValues.push(respp.questionsBean[l].dropDownValueList[d].value.trim());
                }
              }
            }
          });

            if (this._ITOeditQoutService.dboF2fNewAddOns.length > 0) {              
              for (let m = 0; m < this._ITOeditQoutService.dboF2fNewAddOns.length; m++) {
                if (this._ITOeditQoutService.dboF2fNewAddOns[m].itemId == d.itemId && this._ITOeditQoutService.dboF2fNewAddOns[m].subItemId == d.subItemId &&
                  this._ITOeditQoutService.dboF2fNewAddOns[m].subItemTypeId == d.subItemTypeId) {
                    this.getSels(d.itemId, d.subItemId, d.subItemTypeId);
                    console.log("step1");
                }
              }    
           }             
            
            //To enter the dbocost of the item in edit mode
            if (this._ITOeditQoutService.dboF2fData.length != 0)
            {
              if(d.itemId != 0 && d.subItemId == 0)
              {                 //To write the logic to check the item ids in the main panel
                for(let m = 0; m < this.itemSelectedListEdit.length; m++)
                {               
                    if(this.itemSelectedListEdit[m].itemName == d.itemName )
                    {
                      //To set the cost
                    //  this.dboCost = this.itemSelectedListEdit[m].basicCost;
                      break;                    
                    }                
                }
              }
              if(d.subItemId != 0 && d.subItemTypeId == 0 )
              {
                for(let m = 0; m < this.subitemSelectedListEdit.length; m++)
                {                  
                    if(this.subitemSelectedListEdit[m].subItemName == d.subItemName )
                    {
                      //To set the cost
                for(let m = 0; m < this.subitemSelectedListEdit.length; m++)
                   //   this.dboCost = this.subitemSelectedListEdit[m].basicCost;
                      break;
                    }                  
                }
                for(let g=0;g<this.subitemSelectedListEdit.length; g++){
                  if(this.subitemSelectedListEdit[g].itemId == 6 && this.itemIdList.includes(6)){
                    this.discountPerS = this.subitemSelectedListEdit[g].discountPer;
                    this.lubItemcost = this.subitemSelectedListEdit[g].itemCost;
                    this.subQuantity= this.subitemSelectedListEdit[g].quantity;
                    this.enableQty=true;
                    if(this.storage.get(this.F2FTurbine).subQuantity>1)
                    {
                     this.subQuantity=this.storage.get(this.F2FTurbine).subQuantity;
                    }
                    if(this.storage.get(this.F2FTurbine).discountPerS>1)
           {
            this.discountPerS=this.storage.get(this.F2FTurbine).discountPerS;
           }
                    break;
                  }
                }   
              }           
            } else if(this.itemIdList.includes(6)){
              this.enableQty=true
               if(this.storage.get(this.F2FTurbine).subQuantity>1)
           {
            this.subQuantity=this.storage.get(this.F2FTurbine).subQuantity;
           }
           if(this.storage.get(this.F2FTurbine).lubItemcost >0){
             this.lubItemcost = this.storage.get(this.F2FTurbine).lubItemcost;
           }
           if(this.storage.get(this.F2FTurbine).discountPerS>1)
           {
            this.discountPerS=this.storage.get(this.F2FTurbine).discountPerS;
           }
            }   
         
                 
            
            //storing techinal remarks and commercial remarks and others inside the panel
            this.othFinalPanel(d, nm);
            let otherCompList = this._ITOeditQoutService.dboF2fData.filter((x) => (x.f2fItemId == d.f2fItemId) && (x.lhsFlag == true));
            console.log(otherCompList)
            let othersList = [];
            if (otherCompList.length > 0) {
              for (let j = 0; j < otherCompList.length; j++) {
                this.displayCompOthTable = true;
                this.othersCompCheck = true;
                this.compOthers = new dboClass();
                this.compOthers.quotId  = this._ITOcustomerRequirementService.saveBasicDet.quotId;
                this.compOthers.itemId = otherCompList[j].itemId;
                this.compOthers.itemName=null;
                this.compOthers.subItemId = otherCompList[j].subItemId;
                this.compOthers.subItemName=null;
                this.compOthers.subItemTypeId = otherCompList[j].subItemTypeId;
                this.compOthers.subItemTypeName=null;
                this.compOthers.colId = 0;
                this.compOthers.colNm = otherCompList[j].colNm;
                this.compOthers.colValCd = otherCompList[j].colValCd;
                this.compOthers.quantity = otherCompList[j].rhsColQuantity;
                this.compOthers.cost = otherCompList[j].rhsCost;
                this.compOthers.techComments = otherCompList[j].rhsColTechcomments;
                this.compOthers.comrComments = otherCompList[j].rhsColComrcomments;
           
                this.compOthers.addOnNewColFlag = 1;
                //this.compOthers.othColValFlag = 1;
                this.compOthers.techFlag = 1;
                this.compOthers.comrFlag = 1;
              
                othersList.push(this.compOthers);

              }
              console.log(othersList)
              this.compOthersAddonList = othersList;

              console.log(this.compOthersAddonList);
              //storing others inside panel to local variables
            } else {
              if(this.compOthersAddonList.length!=0)
              {
                this.displayCompOthTable = true;
                this.othersCompCheck = true;
              }
              else
              {

              
              this.displayCompOthTable = false;
              this.othersCompCheck = false;      
            }        
            }        
            
            
            
    //to display techina and commercial remarks above the new sub item creation others from local storage 
    if(this.storage.get(this.F2FTurbine).subItemCmrRemValOut != null && this.storage.get(this.F2FTurbine).subItemCmrRemValOut != ""){
      this.subItemCmrRemValOut = this.storage.get(this.F2FTurbine).subItemCmrRemValOut;
      this.subItemComrRemDiv = true;
      this.othSubItmComrRemChk = true;
    }
    if(this.storage.get(this.F2FTurbine).subItemRmkValOut != null && this.storage.get(this.F2FTurbine).subItemRmkValOut != ""){
      this.subItemRmkValOut = this.storage.get(this.F2FTurbine).subItemRmkValOut;
      this.subItemRemarkDiv = true;
      this.othSubItmTechRemChk = true;
    }    
    //storing others inside panel to local variables
            console.log(this._ITOeditQoutService.dboF2fData);
            if (this._ITOeditQoutService.dboF2fData != undefined) {
            if (this._ITOeditQoutService.dboF2fData.length != 0) {
                for(let p=0; p<this._ITOeditQoutService.dboF2fData.length; p++){
                  if(d.f2fItemId == this._ITOeditQoutService.dboF2fData[p].f2fItemId){
                    this.qty = this._ITOeditQoutService.dboF2fData[p].quantity;
                    this.dboCost = this._ITOeditQoutService.dboF2fData[p].basicCost;
                    this.itemcost= this._ITOeditQoutService.dboF2fData[p].itemCost;
                    this.discountPer = this._ITOeditQoutService.dboF2fData[p].discountPer;
                    if(this._ITOeditQoutService.dboF2fData[p].othersCost != 0){
                    this.dboAddOnCost = this._ITOeditQoutService.dboF2fData[p].f2FAddOnCost;
                    }
                    if(this._ITOeditQoutService.dboF2fData[p].techRemarks != null && this._ITOeditQoutService.dboF2fData[p].techRemarks != ""){
                      this.techCheckkk = true;
                      this.subItemtechCheck = true;
                      this.subItemTechRmkDiv = true;
                      this.subItemRemarksVal = this._ITOeditQoutService.dboF2fData[p].techRemarks;
                    }
                    if(this._ITOeditQoutService.dboF2fData[p].comrRemarks != null && this._ITOeditQoutService.dboF2fData[p].comrRemarks != ""){
                     this.comrCheckkk = true;
                     this.subItemComrrCheck = true;
                     this.subItemComrRmkDiv = true;
                     this.subItemCmrRemVal = this._ITOeditQoutService.dboF2fData[p].comrRemarks;
                    }
                  }
                  if(d.itemName == this._ITOeditQoutService.dboF2fData[p].itemName){
                     if(this._ITOeditQoutService.dboF2fData[p].subItemTechRemarks != "" && this._ITOeditQoutService.dboF2fData[p].subItemTechRemarks != null){
                        this.othSubItmTechRemChk = true;
                        this.subItemRemarkDiv = true;
                        this.subItemTchRem = true;
                        this.subItemRmkValOut = this._ITOeditQoutService.dboF2fData[p].subItemTechRemarks;
                    }
                    if(this._ITOeditQoutService.dboF2fData[p].subItemComrRemarks != "" && this._ITOeditQoutService.dboF2fData[p].subItemComrRemarks != null){
                     this.othSubItmComrRemChk = true;
                     this.subItemComrRemDiv = true;
                     this.subItemComRem = true;
                     this.subItemCmrRemValOut = this._ITOeditQoutService.dboF2fData[p].subItemComrRemarks;
                    }
                 }
                 if(d.subItemName == this._ITOeditQoutService.dboF2fData[p].subItemName){
                 if(this._ITOeditQoutService.dboF2fData[p].subItemTypeTechRemarks != null && this._ITOeditQoutService.dboF2fData[p].subItemTypeTechRemarks != ""){
                  this.othSubItmTypTechRemChk = true;
                  this.subItmTypRmkDiv = true;
                  this.subItmTypTchRem = true;
                  this.subItmTypRmkValOut = this._ITOeditQoutService.dboF2fData[p].subItemTypeTechRemarks;
                }
                if(this._ITOeditQoutService.dboF2fData[p].subItemTypeComrRemarks != null && this._ITOeditQoutService.dboF2fData[p].subItemTypeComrRemarks != ""){
                 this.othSubItmTypComrRemChk = true;
                 this.subItTypmComRemDiv = true;
                 this.subItmTypComrRem = true;
                 this.subItmTypComRemValOut = this._ITOeditQoutService.dboF2fData[p].subItemTypeComrRemarks;
                    }
                  }
                }              
            }
          } else{
          console.log(this.dboCost);
          console.log(this.dboAddOnCost);
         // chances of going  to this else is very rare since we are using edit data everytime
          if (this.dboEleFullArray != null) {
            console.log(this.dboEleFullArray)
            let k = this.dboEleFullArray.map((x) => x.id).includes((d.f2fItemId));
            console.log(k);
            if (k) {
              for (let p = 0; p < this.dboEleFullArray.length; p++) {
                if (this.dboEleFullArray[p].id == d.f2fItemId) {
                  this.dboCost = this.dboEleFullArray[p].dboCost;
                  if(  this.dboCost==0)
    {
      this.saveBtColor=false
      this.buttonColor="red";
      this.dispSCFM=true
    }
    else
    {
      this.saveBtColor=false         
       this.buttonColor="green";
      this.dispSCFM=false;

    }
                  this.defaultValues = this.dboEleFullArray[p].defaultValues;
                  this.qty = this.dboEleFullArray[p].qty;
                  this.itemcost=this.dboEleFullArray[p].itemcost;
                  this.dboAddOnCost = this.dboEleFullArray[p].dboAddOnCost;
                  this.discountPer = this.dboEleFullArray[p].discountPer;
                  this.itemRemarksVal = this.dboEleFullArray[p].techComments;
                  this.errDisplayPnl=this.dboEleFullArray[p].errDisplayPnl;
                  this.errMsgRhsCost=this.dboEleFullArray[p].errMsgRhsCost;
                  this.itemCmrRemarksVal = this.dboEleFullArray[p].comrComments;
                  this.selectedNewAdds = this.dboEleFullArray[p].selectedNewAdds;
                  this.selectedNewAdds = this.selectedNewAdds.filter((x) => {
                    return x != null;
                  })
                  console.log(this.selectedNewAdds);
                  this.newAddonCost = this.dboEleFullArray[p].dboAddOnCost;
                  this.compOthersAddonList = [];
                 for(let y=0;y< this.dboEleFullArray[p].compOthersAddonList.length;y++)
                 {
                  if(this.dboEleFullArray[p].compOthersAddonList[y].itemId== d.itemId && this.dboEleFullArray[p].compOthersAddonList[y].subItemTypeId== d.subItemTypeId &&   this.dboEleFullArray[p].compOthersAddonList[y].subItemId== d.subItemId )
                  {
                  this.compOthersAddonList.push(this.dboEleFullArray[p].compOthersAddonList[y]);
                   } 
                 }
                  console.log(this.compOthersAddonList)
                  if (this.compOthersAddonList) {
                    if (this.compOthersAddonList.length > 0) {
                      this.displayCompOthTable = true;
                      this.othersCompCheck = true;
                    } else {
                      this.displayCompOthTable = false;
                      this.othersCompCheck = false;
                    }
                  }
                  for (let m = 0; m < this.selectedNewAdds.length; m++) {
                    if (this.selectedNewAdds[m].itemId == d.itemId && this.selectedNewAdds[m].subItemId == d.subItemId &&
                    this.selectedNewAdds[m].subItemTypeId == d.subItemTypeId) {
                      this.newAddOnsSel(d.itemId, d.subItemId, d.subItemTypeId);
                    }
                  }
                }
              }
            }          
          }
        }
      }    
      this.diableitemname=true;
    
   }

   // storing to local varibales when value is coming from edit flow
  getSels(itemId, subItemId, subItemTypeId) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(itemId, subItemId, subItemTypeId);
    //added 13thnov
    this.selectedNewAddsTemp = [];
    console.log(this._ITOeditQoutService.dboF2fNewAddOns, this.selectedNewAddsTemp, itemId)
    if (this._ITOeditQoutService.dboF2fNewAddOns != null) {
      //All the selected addonlist for the quotation
      this.selectedNewAddsTemp = this._ITOeditQoutService.dboF2fNewAddOns;
      console.log(this.selectedNewAddsTemp);
      this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      // combining both saved listand unsaved list of addons
      this._ITOturbineConfigService.getF2fAddOn(this.dboFormData).subscribe(newAddOns2 => {
        console.log(newAddOns2);
        this.newAddOnList = [];
        for(let l = 0; l < newAddOns2.f2fAddOnColumns.length; l++){
          if((newAddOns2.f2fAddOnColumns[l].othersFlag == true) &&
           (newAddOns2.f2fAddOnColumns[l].itemId == itemId) &&
             (newAddOns2.f2fAddOnColumns[l].subItemId == subItemId) && 
             (newAddOns2.f2fAddOnColumns[l].subItemTypeId == subItemTypeId)){
            this.newAddOnList.push(newAddOns2.f2fAddOnColumns[l]);            
          }
        }
          console.log(this.newAddOnList);
          
          //The total addon options available for particular panel
        this.newAddOnListOptsList = newAddOns2.f2fAddOnColData;
        console.log(this.selectedNewAddsTemp)
        console.log(this.newAddOnListOptsList)
        //From the added addon find the attribute (Column name) in which the addon
        //values are added
        for (let k=0; k<this.selectedNewAddsTemp.length; k++) {
          for (let p=0; p<this.newAddOnList.length; p++) {
            if ((this.newAddOnList[p].colNm == this.selectedNewAddsTemp[k].colNm) && (this.selectedNewAddsTemp[k].itemId == itemId)) {
              console.log(this.selectedNewAddsTemp)
              //If the newly added is not in Others column
              if (!this.selectedNewAddsTemp[k].othColValFlag) {
                console.log(this.selectedNewAddsTemp)
                this.selNewAddOnList[p] = true;
                this.eleOtherDisp[p] = true;  //added by nidhi;
                this.newAddOnListOptsExp[p] = true;
                this.selNewAddOnListOptsList[p] = this.selectedNewAddsTemp[k].colValCd;
                this.selNewAddOnListOptsIdList[p] = this.selectedNewAddsTemp[k].colId;
                this.selectedNewAdds[p] = this.selectedNewAddsTemp[k];
                if (this.selectedNewAddsTemp[k].addOnCostMeFlag) {
                  this.selectedNewAdds[p].cost = this.selectedNewAddsTemp[k].rhsCost;
                  this.selectedNewAdds[p].quantity = this.selectedNewAddsTemp[k].rhsColQuantity;
                  this.selectedNewAdds[p].addOnCostMeFlag = 1;
                  this.selectedNewAdds[p].techRemarks = this.selectedNewAddsTemp[k].rhsColTechcomments;
                  this.selectedNewAdds[p].comrRemarks = this.selectedNewAddsTemp[k].rhsColComrcomments;
                }
                console.log(this.newAddOnList[p])
                this.onSelAddOnNew(this.selNewAddOnList[p], this.newAddOnList[p], p);
                break;
              } else {
                this.selNewAddOnList[p] = true;
                this.newAddOnListOptsExp[p] = true;
                this.enableOth[p] = true;
                this.selNewAddOnListOptsList[p] = this.selectedNewAddsTemp[k].colValCd;
                this.newAddNameO[p] = this.selectedNewAddsTemp[k].colValCd;
                this.newAddQtyO[p] = this.selectedNewAddsTemp[k].rhsColQuantity;
                this.newAddCostO[p] = this.selectedNewAddsTemp[k].rhsCost;
                this.newAddRemrkO[p] = this.selectedNewAddsTemp[k].rhsColTechcomments;
                this.newAddComRemrkO[p] = this.selectedNewAddsTemp[k].rhsColComrcomments;
                this.eleOtherDisp[p] = true;
                this.selectedNewAdds[p] = this.selectedNewAddsTemp[k];
               if(this.selectedNewAddsTemp[k].addOnCostMeFlag)
                {
                  this.selectedNewAdds[p].cost = this.selectedNewAddsTemp[k].rhsCost;
                  this.selectedNewAdds[p].quantity = this.selectedNewAddsTemp[k].rhsColQuantity;
                  this.selectedNewAdds[p].addOnCostMeFlag = 1;
                  this.selectedNewAdds[p].othColValFlag = 1;
                  this.selectedNewAdds[p].techRemarks = this.selectedNewAddsTemp[k].rhsColTechcomments;
                  this.selectedNewAdds[p].comrRemarks = this.selectedNewAddsTemp[k].rhsColComrcomments;
                  this.selectedNewAdds[p].colValCd = this.selectedNewAddsTemp[k].colValCd;
                  // quantity: this.selectedNewAddsTemp[s].othQuantity,
                  // colId: this.selectedNewAddsTemp[s].colId,
                  // cost: this.selectedNewAddsTemp[s].price,
                  // othColValFlag: 1,
                  // addOnCostMeFlag: 1,
                  // newAddRemrkO: this.selectedNewAddsTemp[s].newAddRemrkO,
                  // newAddComRemrkO: this.selectedNewAdds[s].newAddRemrkO,
                  // colValCd: this.selectedNewAddsTemp[s].colValCd
               }
                if (this.selNewAddOnList[p]) {
                  this.newAddOnListOpts[p] = this.newAddOnListOptsList.filter((x) => {
                    return x.colNm == this.newAddOnList[p].colNm;
                  })
                  console.log(this.newAddOnListOpts[p])
                  console.log(this.selectedNewAddsTemp)
                  if (this.newAddOnListOpts[p].length > 0) {
                    this.newAddOnListOptsExp[p] = true;
                    if (this.selectedNewAddsTemp.length != 0) {
                      for (let s = 0; s < this.selectedNewAddsTemp.length; s++) {
                        for (let v = 0; v < this.newAddOnListOpts[p].length; v++) {
                          if (this.selectedNewAddsTemp[s].colValCd == this.newAddOnListOpts[p][v].colValCd) {  //nioted this lineee
                            this.newAddCost[p] = this.selectedNewAddsTemp[s].rhsCost;
                            this.newAddRemrk[p] = this.selectedNewAddsTemp[s].rhsColTechcomments;
                            this.newAddComRemrk[p] = this.selectedNewAddsTemp[s].rhsColComrcomments;
                            this.newAddQty[p] = this.selectedNewAddsTemp[s].rhsColQuantity;
                            console.log(this.newAddOnListOpts[p][v])
                            if (this.selectedNewAddsTemp[s].addOnCostMeFlag) {
                              this.radioSel(p, v, this.selectedNewAddsTemp[s]);
                            } else {
                              this.radioSel(p, v, this.newAddOnListOpts[p][v]);
                            }
                            break;
                          }
                        }
                      }
                    }

                    for (let k = 0; k < this.newAddOnListOptsExp.length; k++) {
                      this.enableRow[k] = false;
                    }
                  }
                }
                else {
                  this.newAddOnListOptsExp[p] = false;
                  this.selectedNewAdds[p] = null;
                  this.selNewAddOnListOptsList[p] = '';
                  this.selNewAddOnListOptsIdList[p] = null;
                }
                console.log(this.newAddOnListOpts);
                break;
              }
            }
          }
        }
        console.log(this.selNewAddOnListOptsList)
        console.log(this.selectedNewAdds);
        console.log(this.selectedNewAddsTemp)
        this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      })
    }
  }

   // storing values on opening new component panel
   newAddOnsSel(itemId, subItemId, subItemTypeId) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.hideprogress = true;
    console.log(this.selectedNewAdds)
    this.addOnDivNew = true;
    if (this.selectedNewAdds != null) {
      this.selectedNewAdds = this.selectedNewAdds.filter((x) => {
        return x != null;
      })
      let newSet = Array.from(new Set(this.selectedNewAdds.map((x) => {
        return x;
      })));
      this.selectedNewAddsTemp = newSet;
      this._ITOturbineConfigService.getF2fAddOn(this.dboFormData).subscribe(newAddOns => {
        console.log(newAddOns);
        this.newAddOnList = [];
        for(let l = 0; l < newAddOns.f2fAddOnColumns.length; l++){
          if((newAddOns.f2fAddOnColumns[l].othersFlag == true) &&
           (newAddOns.f2fAddOnColumns[l].itemId == itemId) && 
           (newAddOns.f2fAddOnColumns[l].subItemId == subItemId) &&
            (newAddOns.f2fAddOnColumns[l].subItemTypeId == subItemTypeId)){
            this.newAddOnList.push(newAddOns.f2fAddOnColumns[l]);
          }
        }
        this.newAddOnListOptsList = newAddOns.f2fAddOnColData;
        console.log(this.selectedNewAddsTemp);
        for (let s = 0; s < this.selectedNewAddsTemp.length; s++) {
          for (let p = 0; p < this.newAddOnList.length; p++) {
            if (this.selectedNewAddsTemp[s]) {
              if ((!this.selectedNewAddsTemp[s].addOnOthersFlag) && (this.newAddOnList[p].colNm == this.selectedNewAddsTemp[s].colNm) && (this.selectedNewAddsTemp[s].itemId == itemId)) {
                console.log(this.selectedNewAddsTemp)
                this.selNewAddOnList[p] = true;
                this.newAddOnListOptsExp[p] = true;
                this.selNewAddOnListOptsList[p] = this.selectedNewAddsTemp[s].colValCd;
                this.selNewAddOnListOptsIdList[p] = this.selectedNewAddsTemp[s].colId;
                this.onSelNewAddon(this.selNewAddOnList[p], this.newAddOnList[p], p);
                break;
              } else if ((this.selectedNewAddsTemp[s].addOnCostMeFlag) && (this.newAddOnList[p].colNm == this.selectedNewAddsTemp[s].colNm)) {
                this.selNewAddOnList[p] = true;
                this.newAddOnListOptsExp[p] = true;
                this.enableOth[p] = true;
                this.selNewAddOnListOptsList[p] = this.selectedNewAddsTemp[s].colValCd;
                this.newAddNameO[p] = this.selectedNewAddsTemp[s].colValCd;
                this.newAddQtyO[p] = this.selectedNewAddsTemp[s].rhsColQuantity;
                this.newAddCostO[p] = this.selectedNewAddsTemp[s].rhsCost;
                this.newAddRemrkO[p] = this.selectedNewAddsTemp[s].rhsColTechcomments;
                this.newAddComRemrkO[p] = this.selectedNewAddsTemp[s].rhsColTechcomments;
                break;
              }
            }
          }
        }
        this.selectedNewAdds = this.selectedNewAdds.filter((x) => {
          return x != null;
        })
        let newselectAddons = Array.from(new Set(this.selectedNewAdds.map((x) => {
          return x;
        })));
        this.selectedNewAdds = newselectAddons;
        console.log(this.selectedNewAdds);
        console.log(this.selNewAddOnListOptsList)
        this.hideprogress = false;
      })
    }
  }

 /**
   * selecting saved add on from edit flow
   * @param event 
   * @param addI item deatils
   * @param g index of the item
   */
  onSelAddOnNew(event, addI, g) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(event, addI, g, this.newAddOnListOptsList);
    // this.elemSel[g]=true;
    //filter to select column values
    if (event) {
      this.newAddOnListOpts[g] = this.newAddOnListOptsList.filter((x) => {
        return x.colNm == addI.colNm;
      })
      this.enableOth[g] = false;
      console.log(this.newAddOnListOpts[g])
      console.log(this.selectedNewAddsTemp)
      if (this.newAddOnListOpts[g].length > 0) {
        this.newAddOnListOptsExp[g] = true;
        if (this.selectedNewAddsTemp.length != 0) {
          for (let s = 0; s < this.selectedNewAddsTemp.length; s++) {
            for (let v = 0; v < this.newAddOnListOpts[g].length; v++) {
              if ((this.selectedNewAddsTemp[s].colValCd == this.newAddOnListOpts[g][v].colValCd) &&
              (this.selectedNewAddsTemp[s].itemId == this.newAddOnListOpts[g][v].itemId) && 
              (this.selectedNewAddsTemp[s].subItemId == this.newAddOnListOpts[g][v].subItemId) &&
            (this.selectedNewAddsTemp[s].subItemTypeId == this.newAddOnListOpts[g][v].subItemTypeId)) {
                this.newAddCost[g] = this.selectedNewAddsTemp[s].rhsCost;
                this.newAddQty[g] = this.selectedNewAddsTemp[s].rhsColQuantity;
                this.newAddRemrk[g] = this.selectedNewAddsTemp[s].rhsColTechcomments;
                this.newAddComRemrk[g] = this.selectedNewAddsTemp[s].rhsColComrcomments;
                console.log(this.newAddOnListOpts[g][v])
                //selecting the radio button of the saved option
                if (this.selectedNewAddsTemp[s].addOnCostMeFlag) {
                  this.radioSel(g, v, this.selectedNewAddsTemp[s]);
                } else {
                  this.radioSel(g, v, this.newAddOnListOpts[g][v]);
                }
                break;
              } else {
                this.newAddNameO[g] = null;
                this.newAddQtyO[g] = "";
                this.newAddCostO[g] = "";
                this.newAddRemrkO[g] = "";
                this.newAddComRemrkO[g] = '';
              }
            }
          }
        }
        for (let k = 0; k < this.newAddOnListOptsExp.length; k++) {
          this.enableRow[k] = false;
        }
      }
    }
    else {
      this.newAddOnListOptsExp[g] = false;
      this.selectedNewAdds[g] = null;
      this.selNewAddOnListOptsList[g] = '';
      this.selNewAddOnListOptsIdList[g] = null;
    }
    console.log(this.newAddOnListOpts);
  }


   //On click subitem name
   dboElementSelSub(sd, subNm, i){
     this.subItembutton=true;
     this.subItemTypebutton=true;

     if(this.selectdEl2.length!=0)
     {
       if(this.selectdEl2.includes(true))

{
    this.subItemTypebutton=false;

}
     }
     this.diableitemname=false;
     console.log(sd, subNm, i);    
     this.openOth=[];
     this.openbtn=[]; 
     //if label has subitems it will display dialog with subitem type list names or else it will hit the dboElementSel function to diplay final dropdownvalues and fields.   
     if(sd.subItemName == subNm){
       this.lhsRhsSubItmTypList = [];
       this.subItmTypOthList=[];
      // this.subItmTypRmkValOut = '';
      // this.subItmTypComRemValOut = '';
     //  this.subItTypmComRemDiv = false;
     //  this.subItmTypTchRem = false;
    //   this.subItmTypRmkDiv = false;
      // this.subItmTypComrRem = false;
       for(let j=0;j<this.addOnList.length;j++)
       {
         if(this.addOnList[j].itemId==this.itemIdd && this.addOnList[j].subItemId==this.subItemIdd && this.addOnList[j].subItemTypeId==this.subItemTypeId )
         {
           this.openOth[this.addOnList[j].colId]=true;
           this.openbtn[this.addOnList[j].colId]=false;
           this.rhs[this.addOnList[j].colId]=true;
           this.newAddComRemrkO1[this.addOnList[j].colId]=this.addOnList[j].comrComments;
           this.newAddNameO1[this.addOnList[j].colId]=this.addOnList[j].colValCd;
           this.newAddCostO1[this.addOnList[j].colId]=this.addOnList[j].cost;
         }
       }
       //this.othFinalPanel(sd, subNm);
       //to display techina and commercial remarks above the new sub item type creation others from local storage 
    if(this.storage.get(this.F2FTurbine).subItmTypComRemValOut != null && this.storage.get(this.F2FTurbine).subItmTypComRemValOut != ""){
      this.subItmTypComRemValOut = this.storage.get(this.F2FTurbine).subItmTypComRemValOut;
      this.subItTypmComRemDiv = true;
      this.othSubItmTypComrRemChk = true;
    }
    if(this.storage.get(this.F2FTurbine).subItmTypRmkValOut != null && this.storage.get(this.F2FTurbine).subItmTypRmkValOut != ""){
      this.subItmTypRmkValOut = this.storage.get(this.F2FTurbine).subItmTypRmkValOut;
      this.subItmTypRmkDiv = true;
      this.othSubItmTypTechRemChk = true;
    }
   
       //InEdit flow on click of sub item panel will open sub item typ panel, in that panel if data exists in edit flow it will dispaly the values
       if (this._ITOeditQoutService.dboF2fData != undefined) {
        if (this._ITOeditQoutService.dboF2fData.length != 0) {
            for(let p=0; p<this._ITOeditQoutService.dboF2fData.length; p++){              
             if(sd.subItemName == this._ITOeditQoutService.dboF2fData[p].subItemName){
             if(this._ITOeditQoutService.dboF2fData[p].subItemTypeTechRemarks != null && this._ITOeditQoutService.dboF2fData[p].subItemTypeTechRemarks != ""){
              this.othSubItmTypTechRemChk = true;
              this.subItmTypRmkDiv = true;
              this.subItmTypTchRem = true;
              this.subItmTypRmkValOut = this._ITOeditQoutService.dboF2fData[p].subItemTypeTechRemarks;
            }
            if(this._ITOeditQoutService.dboF2fData[p].subItemTypeComrRemarks != null && this._ITOeditQoutService.dboF2fData[p].subItemTypeComrRemarks != ""){
             this.othSubItmTypComrRemChk = true;
             this.subItTypmComRemDiv = true;
             this.subItmTypComrRem = true;
             this.subItmTypComRemValOut = this._ITOeditQoutService.dboF2fData[p].subItemTypeComrRemarks;
           }
          }
            }
          }
        }
    //    if(this._ITOeditQoutService.f2fOthersSubItemTypeList.length != 0){
    //    this.othersSubTypCheck = true;
    //  this.dsplySubItmTypOthTable = true;
    //  this.subItmTypOthList = this._ITOeditQoutService.f2fOthersSubItemTypeList.reduce((acc, current) => {
    //   console.log(acc, current);
    //   const x = acc.find(item => item.subItemTypeName === current.subItemTypeName);
    //   if (!x) {
    //     return acc.concat([current]);
    //   } else {
    //     return acc;
    //   } 
    // }, []);
    // for(let l=0; l<this.subItmTypOthList.length; l++){
    //   if(this.subItmTypOthList[l].othersCost != 0){
    //   this.subItmTypOthList[l].cost = this.subItmTypOthList[l].othersCost;
    //   }
    // }
    //  this.lhsRhsSubItmTypList = this._ITOeditQoutService.f2fOthersSubItemTypeList;
    // }
// To show newly added subitemtype list from edit mode
    if(this._ITOeditQoutService.f2fOthersSubItemTypeList.length != 0){
      console.log(this._ITOeditQoutService.f2fOthersSubItemTypeList);
      this.othersSubTypCheck = true;
      this.dsplySubItmTypOthTable = true;
      this.subItmTypOthListTemp = this._ITOeditQoutService.f2fOthersSubItemTypeList.reduce((acc, current) => {
       console.log(acc, current);
       const x = acc.find(item => item.subItemTypeName === current.subItemTypeName);
       if (!x) {
         return acc.concat([current]);
       } else {
         return acc;
       } 
     }, []);
     for(let z=0; z<this.subItmTypOthListTemp.length; z++){
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.subItmTypOthListTemp[z].itemId;
      this.dboClass.itemName = null; 
      this.dboClass.subItemId = this.subItmTypOthListTemp[z].subItemId;
      this.dboClass.subItemName = null;
      this.dboClass.subItemTypeId = this.subItmTypOthListTemp[z].subItemTypeId;
      this.dboClass.subItemTypeName = this.subItmTypOthListTemp[z].subItemTypeName;
      this.dboClass.colId = null;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = null;
      this.dboClass.quantity = this.subItmTypOthListTemp[z].quantity;
      if(this._ITOcustomerRequirementService.editFlag == true){
      this.dboClass.cost = this.subItmTypOthListTemp[z].othersCost;
      }else{
        this.dboClass.cost = this.subItmTypOthListTemp[z].cost;
      }
      this.dboClass.techComments = this.subItmTypOthListTemp[z].techComments;
      this.dboClass.comrComments = this.subItmTypOthListTemp[z].comrComments;
      this.dboClass.addOnNewColFlag = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
      this.subItmTypOthList.push(this.dboClass);
     }     
      this.lhsRhsSubItmTypListTemp = this._ITOeditQoutService.f2fOthersSubItemTypeList;
      for(let p=0; p<this.lhsRhsSubItmTypListTemp.length; p++){
        this.dboClass = new dboClass();
        this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        this.dboClass.itemId = this.lhsRhsSubItmTypListTemp[p].itemId;
        this.dboClass.itemName = null; 
        this.dboClass.subItemId = this.lhsRhsSubItmTypListTemp[p].subItemId;
        this.dboClass.subItemName = null;
        this.dboClass.subItemTypeId = this.lhsRhsSubItmTypListTemp[p].subItemTypeId;
        this.dboClass.subItemTypeName = this.lhsRhsSubItmTypListTemp[p].subItemTypeName;
        this.dboClass.colId = this.lhsRhsSubItmTypListTemp[p].colId;
        this.dboClass.colNm = this.lhsRhsSubItmTypListTemp[p].colNm;
        this.dboClass.colValCd = this.lhsRhsSubItmTypListTemp[p].colValCd;
        this.dboClass.quantity = this.lhsRhsSubItmTypListTemp[p].quantity;
        if(this._ITOcustomerRequirementService.editFlag == true){
          this.dboClass.cost = this.lhsRhsSubItmTypListTemp[p].othersCost;
          }else{
            this.dboClass.cost = this.lhsRhsSubItmTypListTemp[p].cost;
          }
        this.dboClass.techComments = this.lhsRhsSubItmTypListTemp[p].techComments;
        this.dboClass.comrComments = this.lhsRhsSubItmTypListTemp[p].comrComments;
        this.dboClass.addOnNewColFlag = 1;
        this.dboClass.techFlag = 1;
        this.dboClass.comrFlag = 1;
        this.lhsRhsSubItmTypList.push(this.dboClass);
       }   
    }

       this.subList2=[];
      for (let s = 0; s < this.dboF2fItemList.length; s++) {
        if(sd.subItemId == this.dboF2fItemList[s].subItemId && this.dboF2fItemList[s].subItemTypeName!=null){
            this.subList2.push(this.dboF2fItemList[s]);
          }
        }           
        this.subItmNm = subNm;
          if(this.subList2.length>0){
            this.displayF2fSubList2 = true;
            // this.index = 0;
          }else{
            this.displayF2fSubList1 = true;
            this.displayF2fSubList2 = false;
            this.dboElementSel(sd, subNm, i);
          }   
      }else{        
        this.dboElementSel(sd, subNm, i);
      }         
      this.diableitemname=true;

   }
   //Panel others and remarks call once data is stored
   othFinalPanel(d, nm){
    this.othersCompCheck = false;
    this.displayCompOthTable = false;
    this.compOthersAddonList = [];
    this.subItemComrrCheck = false;
    this.subItemComrRmkDiv = false;
    this.subItemCmrRemVal = '';
    this.subItemtechCheck = false;
    this.subItemTechRmkDiv = false;
    this.subItemRemarksVal = '';
    this.defaultValues=[];
    // if(this.storage.get(this.F2FTurbine).subItemCmrRemVal != null && this.storage.get(this.F2FTurbine).subItemCmrRemVal != ""){
    //   this.subItemCmrRemVal = this.storage.get(this.F2FTurbine).subItemCmrRemVal;
    //   this.subItemComrrCheck = true;
    //   this.subItemComrRmkDiv = true;
    // }
    // if(this.storage.get(this.F2FTurbine).subItemRemarksVal != null && this.storage.get(this.F2FTurbine).subItemRemarksVal != ""){
    //   this.subItemRemarksVal = this.storage.get(this.F2FTurbine).subItemRemarksVal;
    //   this.subItemtechCheck = true;
    //   this.subItemTechRmkDiv = true;
    // }
         //storing techinal remarks and commercial remarks and others inside the panel
         if(this.dboEleFullArray.length != 0){
          for(let k=0; k<this.dboEleFullArray.length; k++){
          if (this.dboEleFullArray[k].comrRemarks != null && this.dboEleFullArray[k].comrRemarks != "" && this.dboEleFullArray[k].itemId == d.itemId &&
          this.dboEleFullArray[k].subItemId == d.subItemId && this.dboEleFullArray[k].subItemTypeId == d.subItemTypeId) {
            this.subItemComrrCheck = true;
            this.subItemComrRmkDiv = true;
            this.subItemCmrRemVal = this.dboEleFullArray[k].comrRemarks;
          }
          if (this.dboEleFullArray[k].techRemarks != null && this.dboEleFullArray[k].techRemarks != ""  && this.dboEleFullArray[k].itemId == d.itemId &&
            this.dboEleFullArray[k].subItemId == d.subItemId && this.dboEleFullArray[k].subItemTypeId == d.subItemTypeId) {
            this.subItemtechCheck =true;
            this.subItemTechRmkDiv = true;
            this.subItemRemarksVal = this.dboEleFullArray[k].techRemarks;
          }
          //storing dbo cost inside the pannel
          if(this.dboEleFullArray[k].itemId == d.itemId &&
            this.dboEleFullArray[k].subItemId == d.subItemId && this.dboEleFullArray[k].subItemTypeId == d.subItemTypeId){
              this.dboCost = this.dboEleFullArray[k].dboCost;
              if(  this.dboCost==0)
    {
      this.saveBtColor=false
      this.buttonColor="red";
      this.dispSCFM=true;
    }
    else
    {
      this.saveBtColor=false         
       this.buttonColor="green";
      this.dispSCFM=false;

    }
              this.qty = this.dboEleFullArray[k].qty;
              this.discountPer = this.dboEleFullArray[k].discountPer;
              this.dboAddOnCost = this.dboEleFullArray[k].dboAddOnCost;
              this.compOthersAddonList = [];
              for(let y=0;y<  this.dboEleFullArray[k].compOthersAddonList.length;y++)
              {
                if(this.dboEleFullArray[k].compOthersAddonList[y].itemId== d.itemId && this.dboEleFullArray[k].compOthersAddonList[y].subItemTypeId== d.subItemTypeId &&   this.dboEleFullArray[k].compOthersAddonList[y].subItemId== d.subItemId )
                {
               this.compOthersAddonList.push( this.dboEleFullArray[k].compOthersAddonList[y]); 
                }
              }
             
              this.itemcost= this.dboEleFullArray[k].itemcost;
              this.errDisplayPnl=this.dboEleFullArray[k].errDisplayPnl;
              this.errMsgRhsCost=this.dboEleFullArray[k].errMsgRhsCost;
              this.defaultValues = this.dboEleFullArray[k].defaultValues;
              if(this.compOthersAddonList.length > 0){
                this.othersCompCheck= true;
                this.displayCompOthTable = true;
              }else{              
                this.othersCompCheck= false;
                this.displayCompOthTable = false;
              }
            }       
        }
      }                     
        console.log(this.compOthersAddonList);
   }
   // take min and max value
  /**
   * 
   * @param options dropdown value
   * @param selVal seleced option
   * @param i index of the drop down
   */
  optionSel(options, selVal, i) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(options, selVal, i);
    this.costNotAvailableError = '';
    for (let opt of options) {
      if (opt.value === selVal) {
        if (opt.stdPriceFlag == false && opt.percentage == 0) {
          this.costNotAvailableError = `Cost is not availbale for option ${selVal}`;
          this.errorArray[i] = this.costNotAvailableError;
          console.log(selVal);
        } else {
          if (this.errorArray[i] != null) {
            this.errorArray.splice(i, 1);
          }
        }
      }
    }
  }
   //getting addons data and display addon
   AddOns() {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.addOnDivNew = true;
    this.selectedNewAdds = [];
    this.newAddOnList = [];
    //console.log(this.respAddOn);
    console.log(this.dboFormData);
    this._ITOturbineConfigService.getF2fAddOn(this.dboFormData).subscribe(newAddOns1 => {
      console.log(newAddOns1);
      this.newAddOnResp = newAddOns1;
      //this.newAddOnList = newAddOns1.f2fAddOnColumns;
      this.newAddOnListOptsList = newAddOns1.f2fAddOnColData;
      for(let l=0; l<newAddOns1.f2fAddOnColumns.length; l++){
        if((newAddOns1.f2fAddOnColumns[l].othersFlag == true) &&
        (newAddOns1.f2fAddOnColumns[l].itemId == this.dboFormData.itemId) &&
         (newAddOns1.f2fAddOnColumns[l].subItemId == this.dboFormData.subItemId) &&
         (newAddOns1.f2fAddOnColumns[l].subItemTypeId == this.dboFormData.subItemTypeId)){
          this.newAddOnList.push(newAddOns1.f2fAddOnColumns[l]);
        }
      }
        console.log(this.newAddOnList)
        if(this.newAddOnList.length == 0){
          this.noAddOns = true;
          this.msg = "Add Ons Not Applicable";
        }
      for (let t = 0; t < this.newAddOnList.length; t++) {
        this.newAddOnListOptsExp[t] = false;   // check whether to display options or not
        this.selNewAddOnListOptsList[t] = '';
        this.selNewAddOnListOptsIdList[t] = null;
        this.selNewAddOnList[t] = false;   // checked value is set to false
      }
    })
  }

  /**
   * on selecting manual entry
   * @param g index of the item
   * @param addI other selection for optin(manual entry)
   */
  radioSelEx(event, g, addI) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    for (let k = 0; k < this.newAddOnListOptsExp.length; k++) {
      this.enableRow[k] = false;
    }
    if (event) {
      this.enableOth[g] = true;

    }
    else {
      this.enableOth[g] = false;
    }
  }

  /**
   * tab change mode
   * @param g index of the item 
   * @param h index of the option
   * @param addIo option selected
   */
  newAddonRadioSel(g, h, addIo) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(this.selectedNewAdds);
    console.log(g, h, addIo);
    for (let k = 0; k < this.newAddOnListOptsExp.length; k++) {
      if (k == h) {
        this.enableRow[k] = true;
        break;
      }
      else {
        this.enableRow[k] = false;
      }
    }
    this.selectedNewAdds[g] = addIo;
    this.selectedNewAdds[g].cost = addIo.cost;
    if (addIo.addOnCostMeFlag) {
      this.selectedNewAdds[g].quantity = addIo.quantity;
    } else {
      this.selectedNewAdds[g].quantity = 1;
    }

    console.log(this.selectedNewAdds);
    console.log(this.enableRow);
  }

  /**
   * on seleting new add on in tab switch mode
   * @param event event on clicking the checkbox
   * @param addI item clicked
   * @param g index of the item
   */
  onSelNewAddon(event, addI, g) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(event, addI, g);
    console.log(this.newAddOnListOptsList)
    if (event) {
      this.newAddOnListOpts[g] = this.newAddOnListOptsList.filter((x) => {
        return x.colNm == addI.colNm;
      })
      this.enableOth[g] = false;
      console.log(this.newAddOnListOpts[g])
      console.log(this.selectedNewAddsTemp)
      if (this.newAddOnListOpts[g].length > 0) {
        this.newAddOnListOptsExp[g] = true;
        if (this.selectedNewAddsTemp.length != 0) {
          for (let s = 0; s < this.selectedNewAddsTemp.length; s++) {
            for (let v = 0; v < this.newAddOnListOpts[g].length; v++) {
              if ((this.selectedNewAddsTemp[s].colValCd == this.newAddOnListOpts[g][v].colValCd) &&
              (this.selectedNewAddsTemp[s].itemId == this.newAddOnListOpts[g][v].itemId) && 
              (this.selectedNewAddsTemp[s].subItemId == this.newAddOnListOpts[g][v].subItemId) &&
              (this.selectedNewAddsTemp[s].subItemTypeId == this.newAddOnListOpts[g][v].subItemTypeId)) {
                this.newAddCost[g] = this.selectedNewAddsTemp[s].cost;
                this.newAddRemrk[g] = this.selectedNewAddsTemp[s].techRemarks;
                this.newAddComRemrk[g] = this.selectedNewAddsTemp[s].comrRemarks;
                this.newAddQty[g] = this.selectedNewAddsTemp[s].quantity;
                console.log(this.newAddOnListOpts[g][v])
                if (this.selectedNewAddsTemp[s].addOnCostMeFlag) {
                  this.newAddonRadioSel(g, v, this.selectedNewAddsTemp[s]);
                } else {
                  this.newAddonRadioSel(g, v, this.newAddOnListOpts[g][v]);
                }
                break;
              }
            }
          }
        }
        for (let k = 0; k < this.newAddOnListOptsExp.length; k++) {
          this.enableRow[k] = false;
        }
      }
    }
    else {
      this.newAddOnListOptsExp[g] = false;
      this.selectedNewAdds[g] = null;
      this.selNewAddOnListOptsList[g] = '';
      this.selNewAddOnListOptsIdList[g] = null;
    }
    console.log(this.newAddOnListOpts);
  }

  //on selecting checkbox, this method will be called from html
  onSelAddOnNew1(event, addI, g) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(event, addI, g);
    this.newAddQty[g] = "";      // setting others field quantity to empty
    this.newAddCost[g] = "";     // setting others field cost to empty
    this.newAddRemrk[g] = "";    // setting others field techinal remakrs to empty
    this.newAddComRemrk[g] = ""; // setting others field commercial remarks to empty
    // this.elemSel[g]=true;
    //if check box is unchecked hide others field
    if (!event) {
      this.eleOtherDisp[g] = false;
    }
    //if check box is checked form the list of dropdowns for that particular column
    if (event) {
      this.newAddOnListOpts[g] = this.newAddOnListOptsList.filter((x) => {
        return x.colNm == addI.colNm;
      })
      this.eleOtherDisp[g] = true;  // displaying others div
      this.enableOth[g] = false;       // displaying others field
      console.log(this.newAddOnListOpts[g])
      console.log(this.selectedNewAddsTemp)
      if (this.newAddOnListOpts[g].length > 0) {
        this.newAddOnListOptsExp[g] = true;
        if (this.selectedNewAddsTemp.length != 0) {
          for (let s = 0; s < this.selectedNewAddsTemp.length; s++) {
            for (let v = 0; v < this.newAddOnListOpts[g].length; v++) {
              if ((this.selectedNewAddsTemp[s].colValCd == this.newAddOnListOpts[g][v].colValCd) && 
              (this.selectedNewAddsTemp[s].itemId == this.newAddOnListOpts[g][v].itemId) && 
            (this.selectedNewAddsTemp[s].subItemId == this.newAddOnListOpts[g][v].subItemId) &&
          (this.selectedNewAddsTemp[s].subItemTypeId == this.newAddOnListOpts[g][v].subItemTypeId)) {
                this.newAddCost[g] = this.selectedNewAddsTemp[s].cost;
                this.newAddQty[g] = this.selectedNewAddsTemp[s].quantity;
                this.newAddRemrk[g] = this.selectedNewAddsTemp[s].techRemarks;
                this.newAddComRemrk[g] = this.selectedNewAddsTemp[s].comrRemarks;
                console.log(this.newAddOnListOpts[g][v])
                if (this.selectedNewAddsTemp[s].addOnCostMeFlag) {
                  this.radioSel(g, v, this.selectedNewAddsTemp[s]);
                } else {
                  this.radioSel(g, v, this.newAddOnListOpts[g][v]);
                }
                break;
              } else {
                this.newAddNameO[g] = null;
                this.newAddQtyO[g] = "";
                this.newAddCostO[g] = "";
                this.newAddRemrkO[g] = "";
                this.newAddComRemrkO[g] = "";
              }
            }
          }
        }
        for (let k = 0; k < this.newAddOnListOptsExp.length; k++) {
          this.enableRow[k] = false;
        }
      }
    }
    else {
      this.newAddOnListOptsExp[g] = false;
      this.selectedNewAdds[g] = null;
      this.selNewAddOnListOptsList[g] = '';
      this.selNewAddOnListOptsIdList[g] = null;
    }
    console.log(this.newAddOnListOpts);
  }
  
  /**
   * adding others to the item
   * @param g index of the item
   * @param addI added new option
   */
  addToListOth(g, addI) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    //this.selNewAddOnListOptsList[g]="";
    this.newAddQty[g] = "";
    this.newAddCost[g] = "";
    this.newAddRemrk[g] = "";
    this.newAddComRemrk[g] = "";
    console.log(this.newAddNameO[g]);
    console.log(this.newAddQtyO[g]);
    console.log(this.newAddCostO[g]);
    console.log(this.newAddRemrkO[g]);
    console.log(this.newAddComRemrkO[g]);
    if (this.newAddNameO[g] == "" || this.newAddQtyO[g] == "" || this.newAddCostO[g] == "" || this.newAddRemrkO[g] == "" || this.newAddComRemrkO[g] == ""
      || this.newAddNameO[g] == undefined || this.newAddQtyO[g] == undefined || this.newAddCostO[g] == undefined || this.newAddRemrkO[g] == undefined || this.newAddComRemrkO == undefined) {
      this.showMsg = true;
      this.msg = "Please enter mandatory fields (*)";
    } else {
      let selAddOn = this.newAddOnListOpts[g];
      if (this.newAddOnListOpts[g].length > 0) {
        this.selectedNewAdds[g] = {
          quantity: this.newAddQtyO[g],
          colId: selAddOn[0].colId,
          colNm: selAddOn[0].colNm,
          addOnCostMeFlag: 1,
          techRemarks: this.newAddRemrkO[g],
          comrRemarks: this.newAddComRemrkO[g],
          colValCd: this.newAddNameO[g],
          itemId: this.itemIdd,
          subItemId: this.subItemIdd,
          subItemTypeId: this.subItemTypeId,
          quotId: this._ITOcustomerRequirementService.saveBasicDet.quotId,
          cost: this.newAddCostO[g],
          techFlag: 1,
          comrFlag: 1,
          othColValFlag: 1,
        }
      } else {
        this.selectedNewAdds[g] = {
          quantity: this.newAddQtyO[g],
          colId: addI.colId,
          colNm: selAddOn.colNm,
          cost: this.newAddCostO[g],
          addOnCostMeFlag: 1,
          techRemarks: this.newAddRemrkO[g],
          comrRemarks: this.newAddComRemrkO[g],
          colValCd: this.newAddNameO[g],
          itemId: this.itemIdd,
          subItemId: this.subItemIdd,
          subItemTypeId: this.subItemTypeId,
          techFlag: 1,
          comrFlag: 1,
          othColValFlag: 1,
          quotId: this._ITOcustomerRequirementService.saveBasicDet.quotId
        }
      }
      this.showMsg = false;
    }
    console.log(this.selectedNewAdds);
    console.log(this.showMsg);
  }
  
  // Getting cost for add ons 
  getAddOnCost() {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.hideprogressCost = true;
    this.newAddonCost = 0;
    this.dboAddOnCost = 0;
    this.selectedNewAddsTemp = [];
    //this.addOnData = [];
    if(this.dboBasicCost != 0){
    console.log(this.selectedNewAdds, this._ITOeditQoutService.dboF2fNewAddOns);
    console.log(this.selectedNewAdds)
     this.selectedNewAddsTemp = this.selectedNewAdds.filter((x) => x != null);
     console.log(this.selectedNewAdds, this.selectedNewAddsTemp, this._ITOeditQoutService.dboF2fNewAddOns);
    console.log(this.selectedNewAdds, this.selectedNewAddsTemp);
    }else{
      this.selectedNewAddsTemp = [];
    }
    //if cost is equal to 0 then that record is coming from dboF2fNewAddOns list, so we have to set values according to required Input
    for(let s=0; s<this.selectedNewAddsTemp.length; s++){
      if(this.selectedNewAddsTemp[s].cost == 0){
        this.selectedNewAddsTemp[s].cost = this.selectedNewAddsTemp[s].rhsCost;
        this.selectedNewAddsTemp[s].quantity = this.selectedNewAddsTemp[s].rhsColQuantity;
        this.selectedNewAddsTemp[s].techRemarks = this.selectedNewAddsTemp[s].rhsColTechcomments;
        this.selectedNewAddsTemp[s].comrRemarks = this.selectedNewAddsTemp[s].rhsColComrcomments;
        this.selectedNewAddsTemp[s].quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      }
    }
    console.log(this.selectedNewAddsTemp);
    this.newAddOnResp.f2fSelectedPriceData = this.selectedNewAddsTemp;
    this.newAddOnResp.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.newAddOnResp.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    console.log(this.newAddOnResp);
    this._ITOturbineConfigService.getF2fPriceAddOn(this.newAddOnResp).subscribe(addOnsCostRes => {
      console.log(addOnsCostRes);
      if (addOnsCostRes.successCode == 0) {
        this.subMessage = true;
        this.messageVal = "Cost Saved successfully";
        this._ITOeditQoutService.dboF2fNewAddOns = addOnsCostRes.f2fAddOnList;
      } else {
        this.subMessage = true;
        this.messageVal = addOnsCostRes.successMsg;
      }
      for(let k = 0; k < addOnsCostRes.f2fAddOnList.length; k++)
        {
          if(this.itemIdd == addOnsCostRes.f2fAddOnList[k].itemId && this.subItemIdd == addOnsCostRes.f2fAddOnList[k].subItemId &&
          this.subItemTypeId == addOnsCostRes.f2fAddOnList[k].subItemTypeId)         
       {           
       this.newAddonCost = addOnsCostRes.f2fAddOnList[k].othersCost;
       this.dboAddOnCost = addOnsCostRes.f2fAddOnList[k].othersCost;
       break;            
    }    
  }
      for (let p = 0; p < this.dboEleFullArray.length; p++) {
        for(let q = 0; q < addOnsCostRes.f2fAddOnList.length; q++){
        if (this.dboEleFullArray[p].itemId == addOnsCostRes.f2fAddOnList[q].itemId && this.dboEleFullArray[p].subItemId == addOnsCostRes.f2fAddOnList[q].subItemId &&
        this.dboEleFullArray[p].subItemTypeId == addOnsCostRes.f2fAddOnList[q].subItemTypeId) {          
          this.dboEleFullArray[p].selectedNewAdds = [];
          this.dboEleFullArray[p].dboCost = this.dboEleFullArray[p].dboCost;
          if(addOnsCostRes.f2fAddOnCost != 0){
          this.dboEleFullArray[p].dboAddOnCost = addOnsCostRes.f2fAddOnCost;
          }
          this.dboEleFullArray[p].selectedNewAdds.push(addOnsCostRes.f2fAddOnList[q]);
        }
        }
      }
        this.hideprogressCost = false;
        console.log(this.dboEleFullArray);
    });
  }

  /**
   * 
   * @param g index of the item 
   * @param h index of the option
   * @param addIo option selected
   * checking radio butto, based on values
   */
  radioSel(g, h, addIo) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(g, addIo);
    this.enableOth[g] = false;
    for (let k = 0; k < this.newAddOnListOptsExp.length; k++) {
      if (k == h) {
        this.enableRow[k] = true;
        break;
      }
      else {
        this.enableRow[k] = false;
      }
    }
    this.selectedNewAdds[g] = addIo;
    this.selectedNewAdds[g].cost = addIo.price;
    if (addIo.addOnCostMeFlag) {
      this.selectedNewAdds[g].quantity = addIo.quantity;
      this.selectedNewAdds[g].addOnCostMeFlag = 1;
    } else {
      this.selectedNewAdds[g].quantity = 1;
    }
    console.log(this.selectedNewAdds);
    console.log(this.enableRow);
  }

  // on selecting the add On option this function will be called
  radioSel1(g, h, addIo) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.newAddQty[g] = "";
    this.newAddCost[g] = "";
    this.newAddRemrk[g] = "";
    this.newAddComRemrk[g] = "";
    this.selNewAddOnListOptsList[g] = addIo.colValCd;
    console.log(g, addIo);
    this.enableOth[g] = false;
    for (let k = 0; k < this.newAddOnListOptsExp.length; k++) {
      if (k == h) {
        this.enableRow[k] = true;
        break;
      }
      else {
        this.enableRow[k] = false;
      }
    }
    if (addIo.price != 0) {
      this.selectedNewAdds[g] = addIo;
      this.selectedNewAdds[g].cost = addIo.price;
      this.selectedNewAdds[g].itemId = this.itemIdd;
      if(this.subItemIdd == 0){
        this.selectedNewAdds[g].subItemId = null;
      }else{
      this.selectedNewAdds[g].subItemId = this.subItemIdd;
      }
      if(this.subItemTypeId == 0){
        this.selectedNewAdds[g].subItemTypeId = null;
      }else{
        this.selectedNewAdds[g].subItemTypeId = this.subItemTypeId;
      }
      this.selectedNewAdds[g].quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.selectedNewAdds[g].comrFlag = 1;
      this.selectedNewAdds[g].techFlag = 1;
      this.selectedNewAdds[g].othColValFlag = 0;
      this.selectedNewAdds[g].addOnCostMeFlag = 0;
      if (addIo.addOnCostMeFlag) {
        this.selectedNewAdds[g].quantity = addIo.rhsColQuantity;
        this.selectedNewAdds[g].addOnCostMeFlag = true;
      } else {
        this.selectedNewAdds[g].quantity = 1;
      }
    }
    console.log(this.selectedNewAdds);
    console.log(this.enableRow);
  }

  /**
   * adding manual cost for existing option
   * @param g index of the item
   * @param addI option selected
   */
  addToList(g, addI) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.selectedNewAdds[g] = addI;
    console.log(this.newAddCost, this.enableRow[g])
    console.log(addI)
    if (this.newAddCost[g] == "" || this.newAddQty[g] == "" || this.newAddRemrk[g] == "" || this.newAddComRemrk[g] == ""||
      this.newAddCost[g] == undefined || this.newAddQty[g] == undefined || this.newAddRemrk[g] == undefined || this.newAddComRemrk[g] == undefined) {
      this.showMsg = true;
      this.msg = "Please enter mandatory fields (*)";
    } else {
      this.selectedNewAdds[g].cost = this.newAddCost[g];
      this.selectedNewAdds[g].quantity = this.newAddQty[g];
      this.selectedNewAdds[g].techRemarks = this.newAddRemrk[g];
      this.selectedNewAdds[g].comrRemarks = this.newAddComRemrk[g];
      this.selectedNewAdds[g].itemId = this.itemIdd;
      if(this.subItemIdd == 0){
        this.selectedNewAdds[g].subItemId = null;
      }else{
      this.selectedNewAdds[g].subItemId = this.subItemIdd;
      }
      if(this.subItemTypeId == 0){
        this.selectedNewAdds[g].subItemTypeId = null;
      }else{
        this.selectedNewAdds[g].subItemTypeId = this.subItemTypeId;
      }
      this.selectedNewAdds[g].colId = addI.colId;
      this.selectedNewAdds[g].colValCd = addI.colValCd;
      this.selectedNewAdds[g].colNm = addI.colNm;
      this.selectedNewAdds[g].quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.selectedNewAdds[g].techFlag = 1;
      this.selectedNewAdds[g].comrFlag = 1;
      this.selectedNewAdds[g].addOnCostMeFlag = 1;
      this.selectedNewAdds[g].othColValFlag = 0;
      this.showMsg = false;
    }
    console.log(this.selectedNewAdds[g]);
    console.log(this.showMsg);
  }

   getPriceExel()
   {
    this.getPrice=true;
    if(this.disablelhs.includes(true) || this.openbtn.includes(true) )
    {
      this.getPrice=false;
    }
    if(this.getPrice )
    {
    this.dboCost=0;
        this.SelectedExcelData = [];
    //this is used to uncheck others if there are no new items in others
    if(this.itemOthersAddonList.length == 0)
        this.othersCheck = false;
    //this is used to uncheck others if there are no new sub items in others
    if(this.subItemOthAddonList.length == 0)
        this.othersSubCheck = false;
         //this is used to uncheck others if there are no new sub itemtype in others
    if(this.subItmTypOthList.length == 0)
    this.othersSubTypCheck = false;
    for (let l = 0; l < this.questionsBean.length; l++) {
      this.dboClass = new dboClass();
      //To push the selected dropdown values in the panel
      //OtherColValFlag and addoncostmeflag will be zero
      //As the default values will be set in the dropdown
      for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
        if (this.questionsBean[l].dropDownValueList[d].value.trim() == this.defaultValues[l]) {
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = this.itemIdd;
          this.dboClass.itemName = null;
          if(this.subItemIdd == 0 || this.subItemIdd == null){
            this.dboClass.subItemId = null;
          }else {
            this.dboClass.subItemId = this.subItemIdd;           
          }
          this.dboClass.subItemName = null;
          if(this.subItemTypeId == 0 || this.subItemTypeId == null){
            this.dboClass.subItemTypeId = null;
          }else{
            this.dboClass.subItemTypeId = this.subItemTypeId;            
          }
          this.dboClass.subItemTypeName = this.subItemTypeNamee;
          this.dboClass.colId = this.questionsBean[l].dropDownType.key;
          this.dboClass.colNm = null;
          this.dboClass.colValCd = this.questionsBean[l].dropDownValueList[d].code.trim();
          this.dboClass.quantity=null;
          this.dboClass.cost=null;
          this.dboClass.techComments=null;
          this.dboClass.comrComments=null;
          this.dboClass.addOnNewColFlag = 0;
         // this.dboClass.othColValFlag = 0;
          this.dboClass.techFlag = this.questionsBean[l].techFlag;
          this.dboClass.comrFlag = this.questionsBean[l].comrFlag;
         
          this.SelectedExcelData.push(this.dboClass);
        // this.dboClass.colId = this.questionsBean[l].dropDownType.key;
        // this.dboClass.colValCd = this.questionsBean[l].dropDownValueList[d].code.trim();
        // this.dboClass.itemId = this.itemIdd;
        // if(this.subItemIdd == 0 || this.subItemIdd == null){
        //   this.dboClass.subItemId = null;
        // }else {
        //   this.dboClass.subItemId = this.subItemIdd;           
        // }
        // if(this.subItemTypeId == 0 || this.subItemTypeId == null){
        //   this.dboClass.subItemTypeId = null;
        // }else{
        //   this.dboClass.subItemTypeId = this.subItemTypeId;            
        // }
        // this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        // this.dboClass.othColValFlag = 0;
        // this.dboClass.techFlag = 1;
        // this.dboClass.comrFlag = 1;
        // this.dboClass.addOnCostMeFlag = 0;
        // this.SelectedExcelData.push(this.dboClass);
        }
      }
    }
    //To push the other attribute (new lhs and rhs)
    //lhsflag has set to be true and colvalFlag has to be 1
    for(let c=0; c<this.compOthersAddonList.length; c++){
      this.SelectedExcelData.push(this.compOthersAddonList[c]);
    }
    for(let j=0;j<this.addOnList.length;j++)
    {
      if(this.addOnList[j].itemId==this.itemIdd && this.addOnList[j].subItemId==this.subItemIdd && this.addOnList[j].subItemTypeId==this.subItemTypeId)
      {
        this.SelectedExcelData.push(this.addOnList[j]);
      }
    }
    console.log(this.SelectedExcelData);
     this.dboFormData.quantity = this.qty;
    console.log(this.qty);
    console.log(this.discountPer);
    this.dboFormData.f2fTechData = this.SelectedExcelData;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    if(this.finalItem === this.itemNamee){
      this.dboFormData.discountPer = this.discountPer;
      this.enableQty=true;
    }else{
      this.dboFormData.discountPer = this.discountPerS;
    }
    //To store the commercial and Technical remarks in the column
    //Which is defined above others
    this.dboFormData.f2fItemComrRemarks = this.subItemCmrRemVal;
    this.dboFormData.f2fItemTechRemarks = this.subItemRemarksVal;
     console.log(this.dboFormData);
     this._ITOturbineConfigService.getF2fTechPrice(this.dboFormData).subscribe( responne => {
      console.log(responne);
      this.techPriceResp = responne;
      this.dboCost = responne.f2FBasicCost;
      if(  this.dboCost==0)
    {
      this.saveBtColor=false
      this.buttonColor="red";
      this.dispSCFM=true;
    }
    else
    {
      this.saveBtColor=false         
       this.buttonColor="green";
       this.dispSCFM=false;

    }
      this.dboAddOnCost=responne.f2FAddOnCost;
      //this.itemcost=responne.f2fTechList[0].itemCost;
      for(let b= 0; b<responne.f2fTechList.length; b++){
        if(responne.f2fTechList[b].itemId == responne.f2fTechData[0].itemId && responne.f2fTechData[b].subItemId == responne.f2fTechData[0].subItemId){
          this.itemcost= responne.f2fTechList[b].itemCost;
        }
      }
    
      // if(responne.f2fTechData.length != 0){
      // for(let m = 0; m < responne.f2fTechList.length; m++){
      //   if(responne.f2fTechData[0].itemId == responne.f2fTechList[m].itemId && responne.f2fTechData[0].subItemId == responne.f2fTechList[m].subItemId &&
      //     responne.f2fTechData[0].subItemTypeId == responne.f2fTechList[m].subItemTypeId){
      //       if(responne.f2fTechList[m].othersCost != 0){
      //     this.dboAddOnCost = responne.f2fTechList[m].othersCost;
      //     break;
      //       }
      //   }
      //   }
      // }
      if (responne.successCode == 0) {
        this.subMessage = true;
        this.messageVal = "Cost Saved successfully";
        this._ITOcustomerRequirementService.sendturBtnStatus(true);
        //f2fTechList contains the drop down default values
        // as wellas the new lhs and rhs values
        this._ITOeditQoutService.dboF2fData = responne.f2fTechList;
        this.errDisplayPnl=[];
        this.errMsgRhsCost=[];
        for(let c=0;c< responne.f2fTechList.length;c++){
          if((  responne.f2fTechList[c].rhsCost == 1 && responne.f2fTechList[c].lhsFlag == 0 && responne.f2fTechList[c].addOnNewColFlag==0 )){
            this.errMsgRhsCost[ responne.f2fTechList[c].colId] = "Actual Cost not Available. Get the same from SCM: " + responne.f2fTechList[c].rhsCost;
            this.errDisplayPnl[responne.f2fTechList[c].colId] = true;
          }else{
            if(responne.f2fTechList[c].rhsCost > 0 && responne.f2fTechList[c].lhsFlag == 0 && responne.f2fTechList[c].addOnNewColFlag==0 ){
              this.errMsgRhsCost[ responne.f2fTechList[c].colId] = "AddOnCost: " + responne.f2fTechList[c].rhsCost;
            this.errDisplayPnl[responne.f2fTechList[c].colId] = true;
            }
          }
        }
        console.log(this._ITOeditQoutService.dboF2fData);
      } else {
        this.subMessage = true;
        this.messageVal = responne.successMsg;
      }
      this.dboCost=responne.f2FBasicCost
      this.dboBasicCost = responne.basicCost;
      // after sucessful response store the values to local variables
      if (this.dboEleFullArray.length != 0) {
        for (let d = 0; d < this.dboEleFullArray.length; d++) {
          let j = this.dboEleFullArray.map(d => { return d.id }).indexOf(this.f2fItemId);
          if (j != (-1)) {
            this.dboEleFullArray[j] = {
              qty: this.qty,
              discountPer: this.discountPer,
              id: this.f2fItemId,
              componenet: this.itemNamee,
              defaultValues: this.defaultValues,
              dboCost: responne.f2FBasicCost,
              dboAddOnCost: responne.f2FAddOnCost,
              techComments: responne.techComments,
              comrComments: responne.comrComments,
              techRemarks: responne.f2fItemTechRemarks,
              comrRemarks: responne.f2fItemComrRemarks,
              selectedNewAdds: this.selectedNewAdds,
              compOthersAddonList: this.compOthersAddonList,
              itemId: this.itemIdd,
              itemName: this.itemNamee,
              subItemId: this.subItemIdd,
              subItemName: this.subItemNamee,
              subItemTypeId: this.subItemTypeId,
              subItemTypeName: this.subItemTypeNamee,
              errMsgRhsCost : this.errMsgRhsCost,
              errMsgPnl: this.errMsgPnl,
              errDisplayPnl: this.errDisplayPnl,
              itemcost:this.itemcost
            };
            break;
          }
          else {
            this.dboEleFullArray.push({
              qty: this.qty,
              discountPer: this.discountPer,
              id: this.f2fItemId,
              componenet: this.itemNamee,
              defaultValues: this.defaultValues,
              dboCost: responne.f2FBasicCost,
              dboAddOnCost: responne.f2FAddOnCost,
              techComments: responne.techComments,
              comrComments: responne.comrComments,
              techRemarks: responne.f2fItemTechRemarks,
              comrRemarks: responne.f2fItemComrRemarks,
              selectedNewAdds: this.selectedNewAdds,
              compOthersAddonList: this.compOthersAddonList,
              itemId: this.itemIdd,
              itemName: this.itemNamee,
              subItemId: this.subItemIdd,
              subItemName: this.subItemNamee,
              subItemTypeId: this.subItemTypeId,
              subItemTypeName: this.subItemTypeNamee,
              errMsgRhsCost : this.errMsgRhsCost,
              errMsgPnl: this.errMsgPnl,
              errDisplayPnl: this.errDisplayPnl,
              itemcost:this.itemcost
            });
            break;
          }
        }
      }
      else {
        this.dboEleFullArray.push({
          qty: this.qty,
          discountPer:this.discountPer,
          id: this.f2fItemId,
          componenet: this.itemNamee,
          defaultValues: this.defaultValues,
          dboCost: responne.f2FBasicCost,
          dboAddOnCost: responne.f2FAddOnCost,
          techComments: responne.techComments,
          comrComments: responne.comrComments,
          techRemarks: responne.f2fItemTechRemarks,
          comrRemarks: responne.f2fItemComrRemarks,
          selectedNewAdds: this.selectedNewAdds,
          compOthersAddonList: this.compOthersAddonList,
          itemId: this.itemIdd,
          itemName: this.itemNamee,
          subItemId: this.subItemIdd,
          subItemName: this.subItemNamee,
          subItemTypeId: this.subItemTypeId,
          subItemTypeName: this.subItemTypeNamee,
          errMsgRhsCost : this.errMsgRhsCost,
          errMsgPnl: this.errMsgPnl,
          errDisplayPnl: this.errDisplayPnl,
          itemcost:this.itemcost
        });
      }
      this._ITOeditQoutService.dboF2fData = [];
      //this._ITOeditQoutService.dboF2fNewAddOns = res.f2fAddOnList1;
      this._ITOeditQoutService.f2fOthersItemList = [];
      this._ITOeditQoutService.f2fOthersSubItemList = [];
      this._ITOeditQoutService.f2fOthersSubItemTypeList = [];
      console.log(this.dboEleFullArray);
      //satya
      this.addedClassList = [];

      for (let m = 0; m < this.dboEleFullArray.length; m++) {
        this.addedClassList.push(this.dboEleFullArray[m].componenet);
      }
      if (this._ITOeditQoutService.dboF2fNewAddOns.length > 0 || this.selectedNewAdds.length > 0) {
        this.getAddOnCost();
      }
      // this.hideprogress = false;
      this.hideprogressCost = false;
      //  this.calcTotal();
      this._ITOturbineConfigService.selected = true;
      //satya
      this.addedClassList = [];
      for (let m = 0; m < this.dboEleFullArray.length; m++) {
        this.addedClassList.push(this.dboEleFullArray[m].componenet);
      }
       this.iFlag = 0;
       if(this.subItemIdd == 0)
       {
          this.selectdEl[this.selectedELIndex] = true;
          if(this.itemNamee == "Step Down Gear Box" && this.checkMBRBool == true){
            this.selectdEl[this.selectedELIndexM] = true;
          }
          this.itemIdList.push(this.itemIdd);
          this.iFlag = 1;
       }
  
       if(this.iFlag == 0)
       {
          if(this.subItemTypeId != 0)
          {
            this.selectdEl1[this.index] = true;
            if (!this.itemIdList1.includes(this.subItemIdd))
            {
              this.itemIdList1.push(this.subItemIdd);
              this.itemIdList.push(this.itemIdd);
            }
  
            this.selectdEl[this.iItemindex] = true;
           
            this.selectdEl2[this.selectedELIndex] = true;
            this.itemIdList2.push(this.subItemTypeId);
            this.index = 0;        
          }
          else
          {
            this.selectdEl[this.index] = true;
            if (!this.itemIdList.includes(this.itemIdd))
            {
                 this.itemIdList.push(this.itemIdd);
            }
            this.selectdEl1[this.selectedELIndex] = true;
            this.itemIdList1.push(this.subItemIdd);
            this.index = 0;
          }
       }
        this.iFlag = 0;
       this.saveInLocal(this.F2FTurbine, { 
        dboEleFullArray: this.dboEleFullArray, compOthersAddonList: this.compOthersAddonList,
        itemIdList: this.itemIdList, itemIdList1: this.itemIdList1, itemIdList2: this.itemIdList2,
        selectdEl: this.selectdEl, selectdEl1: this.selectdEl1, selectdEl2: this.selectdEl2,
        lhsRhsSubItemsList: this.lhsRhsSubItemsList, subItemOthAddonList: this.subItemOthAddonList,
        lhsRhsSubItmTypList: this.lhsRhsSubItmTypList, subItmTypOthList: this.subItmTypOthList,
        lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,
        itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,      
        subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
        subItmTypRmkValOut: this.subItmTypRmkValOut, subItmTypComRemValOut: this.subItmTypComRemValOut,
        finalEleCost: this.finalEleCost, totalF2fCost: this.totalF2fCost, addOnList:this.addOnList,addonflg:this.addonflg,subQuantity:this.subQuantity, 
        lubItemcost: this.lubItemcost, discountPerS: this.discountPerS
          });
      
    })
  }
         
   }
   /**
   * 
   * @param key key value to store in localstorage
   * @param val valueto be stored in localstorage
   */
  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.F2FSapLocalStorage[key] = this.storage.get(key);
  }

  /**
   * enable over write
   */
  newAddOn123(value)
  {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.AddOnFlagNew[value]=true;
    this.addonflg="1";
    this.openbtn[value]=true;
   // this.lhsdisableparent[value]=true;
  
    this.newAddNameO1[value]="";
    this.newAddCostO1[value]="";
    this.newAddComRemrkO1[value]="";
  
    
   
    this.openOth[value]=true;
    this.rhs[value]=true;
  }
  enableOverWrite() {
    this.finalcostflag=true;
    this.enableOverwriteDiv = true;
    this.dboFormData.overwrittenPriceFlag = 1;
    this.dboFormData.overwrittenPrice = this.dboCost;
    this.disableStatus = true;
  }
   /**
   * disable over write on cancel
   */
  disableOverWrite() {
    this.finalcostflag=false;
    this.enableOverwriteDiv = false;
    this.dboFormData.overwrittenPriceFlag = 0;
    this.dboFormData.price = this.dboCost;
    this.disableStatus = false;
    this.remarks = "";
    this.OverWrittenfinalEleCost = 0;
  }

  /**
   * save the f2f cost 
   */
  savePriceExel() {
    this.message = false;
    console.log(this.f2fItemss);
    if (this.enableOverwriteDiv) {
      this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleCost;
      this.dboFormData.overwrittenPriceFlag = 1;
      this.dboFormData.remarks = this.remarks;
    } else if (this.OverWrittenfinalEleCost > 0) {
      this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleCost;
      this.panelList.overwrittenPriceFlag = 1;
    }
    this.dboFormData.modBy = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormData.techComments = this.itemRemarksVal;
    this.dboFormData.comrComments = this.itemCmrRemarksVal;
    this.dboFormData.itemIdList = this.itemIdList.filter(n => n != null);
    console.log(this.itemIdList);
    this.dboFormData.savedF2fDataList = this.lhsRhsItemsList;
    this.dboFormData.savedF2fSubDataList = this.lhsRhsSubItemsList;
    this.dboFormData.savedF2fSubTypeDataList = this.lhsRhsSubItmTypList;
    console.log(this.dboFormData);
    this._ITOturbineConfigService.saveF2fItem(this.dboFormData).subscribe(savedResp => {
      console.log(savedResp);
      if (savedResp.successCode == 0) {
        this.mainSave=false;
        this.message = true;
        this.successMsg = "Cost Saved successfully";
        // this._ITOeditQoutService.f2fOthersItemList = savedResp.savedF2fDataList;
        // this._ITOeditQoutService.f2fOthersSubItemList = savedResp.savedF2fSubDataList;
        // this._ITOeditQoutService.f2fOthersSubItemTypeList = savedResp.savedF2fSubTypeDataList;
        // calling scope of supply complete endpoint to update that dbo electrical data has been saved
        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'F2F') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }
           //Calling saveRemarks to save overwriten cost and comments
   this._ITOcustomerRequirementService.saveBasicDet.groupCode = 'OVERWRITE';
   this._ITOcustomerRequirementService.saveBasicDet.scopeCode = 'F2F';
   this._ITOcustomerRequirementService.saveBasicDet.overwrittenCost = this.OverWrittenfinalEleCost;
   this._ITOcustomerRequirementService.saveBasicDet.remarks = this.remarks;   
   console.log(this._ITOcustomerRequirementService.saveBasicDet);
   this._ITOAddOnComponentsService.saveRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(saveRem => {
     console.log(saveRem);
   })
        //call one line BOM
        this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
          console.log(resOnline);
          if (resOnline.successCode == 0) {
            this.finalEleCost = resOnline.oneLineBomExcel.f2fCost;
            this.totalF2fCost = resOnline.oneLineBomExcel.totalF2fCost;
          } 
          this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
          this.saveInLocal(this.oneLineLoc, resOnline.oneLineBomExcel);
          if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
            this._ITOcustomerRequirementService.editFlag = false;
            this.router.navigate(['/EditQuot']);
          }else{
            if(!(this.storage.get('selRoleDesc') === 'Cost Estimation Engineer')){
              this.router.navigate(['/CostEstimation/CompleteTurbineDetails/SupplyChain']);
            }
            
          }
        });
      } else {
        this.message = true;
        this.successMsg = savedResp.successMsg;
      }
    });
    this.saveInLocal(this.F2FTurbine, {
      dboEleFullArray: this.dboEleFullArray, compOthersAddonList: this.compOthersAddonList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1, itemIdList2: this.itemIdList2,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1, selectdEl2: this.selectdEl2,
      lhsRhsSubItemsList: this.lhsRhsSubItemsList, subItemOthAddonList: this.subItemOthAddonList,
      lhsRhsSubItmTypList: this.lhsRhsSubItmTypList, subItmTypOthList: this.subItmTypOthList,
      lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,      
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      subItmTypRmkValOut: this.subItmTypRmkValOut, subItmTypComRemValOut: this.subItmTypComRemValOut,
      finalEleCost: this.finalEleCost, totalF2fCost: this.totalF2fCost, addOnList:this.addOnList,addonflg:this.addonflg,subQuantity:this.subQuantity,
       lubItemcost: this.lubItemcost, discountPerS: this.discountPerS
    });
  }

// opening new table for item others
  openItemCompTable(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
      this.displayItemCompOthTable = true;
    }else if(!event.target.checked){
      this.displayItemCompOthTable = false;
      //to clear the items list and lhs rhs item list when we uncheck others in the main item
      //panel
      this.itemOthersAddonList = [];  //ADDED SATYA
      this.lhsRhsItemsList = []; //ADDED SATYA
    }
  }

  // adding new item other lines
  addRowsItem() {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.displayItmOthnewLine = true;
  }

  //cancel new line
  cancelnewLineItem(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.displayItmOthnewLine = false;
  }

  //delete the added line for item others
  cancelLinesItemOth(i) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    for(let j = 0; j < this.lhsRhsItemsList.length; j++)
    {
      //Need to remove the lhsrhsitemlist also
      if(this.itemOthersAddonList[i].itemName == this.lhsRhsItemsList[j].itemName)
      {
        //this.lhsRhsItemsList[j] = null;  // ADDED SATYA
       this.lhsRhsItemsList.splice(j, 1);
       j = j - 1;
      }
    }
    this.itemOthersAddonList.splice(i, 1);
  }

  // On Click of save  button to create new item others.
  itemOthersForm(othersItem, othersItemfrm: NgForm) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(othersItem);
    this.dboClass = new dboClass();
    this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboClass.itemId = 0;
    this.dboClass.itemName = othersItem.itemCompName;       
    this.dboClass.subItemId = null;
    this.dboClass.subItemName = null; 
    this.dboClass.subItemTypeId = null;
    this.dboClass.subItemTypeName = null;
    this.dboClass.colId = 0;
    this.dboClass.colNm = null;
    this.dboClass.colValCd =null;
    this.dboClass.quantity = othersItem.itemQuantity;
    this.dboClass.cost = othersItem.itemPrice;
    this.dboClass.techComments = othersItem.itemTechRemarks;
    this.dboClass.comrComments = othersItem.itemComrRemr;
    this.dboClass.addOnNewColFlag = 1;
    this.dboClass.techFlag = 1;
    this.dboClass.comrFlag = 1;
         
        
         // this.dboClass.addOnCostMeFlag = 0;
   
    this.itemOthersAddonList.push(this.dboClass);
    this.lhsRhsItemsList.push(this.dboClass);//satya
    console.log(this.itemOthersAddonList);
    othersItemfrm.reset();
    this.displayItmOthnewLine = false;
  }

  //display dialog to Add LHS/RHS for others item
  addLhsRhsItem(i){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
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

  //Add LHS/RHS to other item
  addLhsRhsForm(lhsRhsItem, lhsRhsItemfrm: NgForm){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
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
        this.dboClass.subItemTypeId = this.itemOthersAddonList[c].subItemTypeId;
        this.dboClass.subItemTypeName = this.itemOthersAddonList[c].subItemTypeName;
    
        this.dboClass.colId = 0;
        this.dboClass.colNm = lhsRhsItem.lhsVal;
        this.dboClass.colValCd = lhsRhsItem.rhsVal;
    
        this.dboClass.quantity = this.itemOthersAddonList[c].quantity;
        this.dboClass.cost = this.itemOthersAddonList[c].cost;
        this.dboClass.techComments = this.itemOthersAddonList[c].techComments;
        this.dboClass.comrComments = this.itemOthersAddonList[c].comrComments;
        this.dboClass.addOnNewColFlag = 1;
        this.dboClass.techFlag = 1;
        this.dboClass.comrFlag = 1;
        
      
       // this.dboClass.othColValFlag = 0;       
        this.lhsRhsItemsList.push(this.dboClass);
      }
    }
   console.log(this.lhsRhsItemsList);
   lhsRhsItemfrm.reset();
    this.lhsRhsnewLine = false;
    this.displayItmOthnewLine = false;
  }

  // opening new table for sub Item others
  openSubItemTable(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
      this.displaySubItemOthTable = true;
    }else if(!event.target.checked){
      this.displaySubItemOthTable = false;
      this.subItemOthAddonList = [];
      this.lhsRhsSubItemsList=[];
    }
  }

   // others inside subitems  panel
  subItemOthForm(othersSubItem, othersSubItemfrm: NgForm){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(othersSubItem);
    this.dboClass = new dboClass();
    this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboClass.itemId = this.itemIdd;
    this.dboClass.itemName = null;          
    this.dboClass.subItemId = null;
    this.dboClass.subItemName = othersSubItem.subItemName
    this.dboClass.subItemTypeId =null;
    this.dboClass.subItemTypeName = null;
          this.dboClass.colId = 0;
          this.dboClass.colNm = null;
          this.dboClass.colValCd = null;
         // this.dboClass.colValNm = null;
          this.dboClass.quantity = othersSubItem.subItemQty;
          this.dboClass.cost = othersSubItem.subItemPrice;
          this.dboClass.techComments = othersSubItem.subItemTechRem;
          this.dboClass.comrComments = othersSubItem.subItemComrRemr;
          this.dboClass.addOnNewColFlag = 1;  
          this.dboClass.techFlag = 1;
          this.dboClass.comrFlag = 1;
        //  this.dboClass.othColValFlag = 0;
         
    this.subItemOthAddonList.push(this.dboClass);
    this.lhsRhsSubItemsList.push(this.dboClass);//satya
    console.log(this.subItemOthAddonList);
    othersSubItemfrm.reset();
    this.dsplySubItmOthnewLine = false;
  }

  addLhsRhsSubItem(i){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsSubItemSel = ''; 
    this.ibreakothers = i;
    console.log(i);
    this.lhsRhsSubItemSel = this.subItemOthAddonList[i].subItemName;
    console.log(this.lhsRhsSubItemSel);
      this.dsplyDialogSubLhsRhs = true;
      this.dsplySubItmOthnewLine = false;
  }
   
  //addding new line for LHS and RHS in subitem others
  addRowsSubLhsRhs(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsSubnewLine = true;
  }

//cancel subitem LHS/RHS new Line
  cancelLinesSubLhs(i){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsSubItemsList.splice(i, 1);
    }

  //cancel added new line sub items others  in LHs/Rhs
  cancelnewLineSubLhs(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsSubnewLine = false;
  }

  //adding LHS/RHs to subitems Other
  addSubLhsRhsForm(lhsRhsSubItem, lhsRhsSubItemfrm: NgForm){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(lhsRhsSubItem);
    for(let k = 0; k < this.lhsRhsSubItemsList.length; k++)
    {
     if(this.lhsRhsSubItemsList[k].subItemName == this.subItemOthAddonList[this.ibreakothers].subItemName && this.lhsRhsSubItemsList[k].colId == null && this.lhsRhsSubItemsList[k].colNm == null){
       this.lhsRhsSubItemsList.splice(k,1);
       this.ibreakothers = 1;
       break;          
     }
   }
   this.ibreakothers = 0;
    for(let c=0; c<this.subItemOthAddonList.length; c++){
      if(this.lhsRhsSubItemSel == this.subItemOthAddonList[c].subItemName){
        this.dboClass = new dboClass();
        this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        this.dboClass.itemId = this.subItemOthAddonList[c].itemId;
        this.dboClass.itemName = this.subItemOthAddonList[c].itemName;
      //  this.dboClass.itemNm = this.subItemOthAddonList[c].itemName;
        this.dboClass.subItemId = this.subItemOthAddonList[c].subItemId;
        this.dboClass.subItemName = this.subItemOthAddonList[c].subItemName;
        this.dboClass.subItemTypeId = this.subItemOthAddonList[c].subItemTypeId;
        this.dboClass.subItemTypeName = this.subItemOthAddonList[c].subItemTypeName;
        this.dboClass.colId = 0;
        this.dboClass.colNm = lhsRhsSubItem.subLhsVal;
        this.dboClass.colValCd = lhsRhsSubItem.subRhsVal;
       // this.dboClass.colValNm = null;
        this.dboClass.quantity = this.subItemOthAddonList[c].quantity;
        this.dboClass.cost = this.subItemOthAddonList[c].cost;
        this.dboClass.techComments = this.subItemOthAddonList[c].techComments;
        this.dboClass.comrComments = this.subItemOthAddonList[c].comrComments;
        this.dboClass.addOnNewColFlag = 1;  
        this.dboClass.techFlag = 1;
        this.dboClass.comrFlag = 1;
       // this.dboClass.othColValFlag = 1;
            
        this.lhsRhsSubItemsList.push(this.dboClass);
      }
    }
    console.log(this.lhsRhsSubItemsList);
    lhsRhsSubItemfrm.reset();
    this.lhsRhsSubnewLine = false;
    this.dsplySubItmOthnewLine = false;
  }

  //List of subitemOthers withand with out LHS/RHS
  subItemsSave(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    for(let b=0;b<this.subItemOthAddonList.length; b++){
      if (!this.lhsRhsSubItemsList.some((item) => item.subItemName == this.subItemOthAddonList[b].subItemName)) {
        this.lhsRhsSubItemsList.push(this.subItemOthAddonList[b]);
    }
    }console.log(this.lhsRhsSubItemsList);
  }

//final submit of  sub items list and sub items others list in DB
  subItemsSubmit(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormData.quantity=this.subQuantity;
    this.dboFormData.itemId = this.itemIdd;
    console.log(this.itemIdList1);
    this.dboFormData.subItemIdList = this.itemIdList1.filter(n => n != null);
    this.dboFormData.subItemIdList.push(0);
    this.dboFormData.savedF2fSubDataList = this.lhsRhsSubItemsList;
    this.dboFormData.subItemTechRemarks = this.subItemRmkValOut;
    this.dboFormData.subItemComrRemarks = this.subItemCmrRemValOut;
    this.dboFormData.modBy = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.discountPer = this.discountPerS;
    console.log(this.dboFormData);
    this._ITOturbineConfigService.saveF2fSubItem(this.dboFormData).subscribe(savedSubResp => {
      console.log(savedSubResp);
      if(savedSubResp.successCode == 0){
        this.subItembutton=false;
        this._ITOeditQoutService.f2fOthersSubItemList = savedSubResp.saveF2fSubList;
        this.lubItemcost = savedSubResp.totalPrice;
        this.discountPerS = savedSubResp.discountPer;
        this.discountPer = savedSubResp.discountPer;
      }
      this.saveInLocal(this.F2FTurbine, {
      dboEleFullArray: this.dboEleFullArray, compOthersAddonList: this.compOthersAddonList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1, itemIdList2: this.itemIdList2,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1, selectdEl2: this.selectdEl2,
      lhsRhsSubItemsList: this.lhsRhsSubItemsList, subItemOthAddonList: this.subItemOthAddonList,
      lhsRhsSubItmTypList: this.lhsRhsSubItmTypList, subItmTypOthList: this.subItmTypOthList,
      lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,      
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      subItmTypRmkValOut: this.subItmTypRmkValOut, subItmTypComRemValOut: this.subItmTypComRemValOut,
      finalEleCost: this.finalEleCost, totalF2fCost: this.totalF2fCost, addOnList:this.addOnList,addonflg:this.addonflg,subQuantity:this.subQuantity, 
      lubItemcost: this.lubItemcost, discountPerS: this.discountPerS
      });
    })
  }
  //cancel sub items new line
  cancelnewLineSubItem() {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.dsplySubItmOthnewLine = false;
  }

   // adding sub item new other lines
   addRowsSubItem() {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.dsplySubItmOthnewLine = true;
  }

  // delete the added subitem line for others
  cancelLinesSubItemOth(i) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    for(let j = 0; j < this.lhsRhsSubItemsList.length; j++)
    {
      //Need to remove the lhsrhsitemlist also
      if(this.subItemOthAddonList[i].itemName == this.lhsRhsSubItemsList[j].itemName)
      {
        //this.lhsRhsItemsList[j] = null;  // ADDED SATYA
       this.lhsRhsSubItemsList.splice(j, 1);
       j = j - 1;
      }
    }
    this.subItemOthAddonList.splice(i, 1);
  }

  //Dispaly text area to write techinal Comments Out Side sub Item Type 
  subItmTypTechRem(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
      this.subItmTypRmkDiv = true;
    }else if(!event.target.checked){
      this.subItmTypRmkDiv = false;
      this.subItmTypRmkValOut = '';
    }
  }

  //Capturing techinal comments out side sub item Type
  chkSubItmTypRem(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(this.subItmTypRmkValOut);
  }

  //Dispaly text area to write techinal Comments Out Side sub Item Type 
  subItmTypComRem(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
      this.subItTypmComRemDiv = true;
    }else if(!event.target.checked){
      this.subItTypmComRemDiv = false;
      this.subItmTypComRemValOut = '';
    }
  }

  //Capturing techinal comments out side sub item Type
  chkItmTypComrRem(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(this.subItmTypComRemValOut);
  }

  //saving of adding new items without and with lhs/Rhs
  itemsSubmit(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    for(let z=0; z<this.itemOthersAddonList.length; z++){
      if (!this.lhsRhsItemsList.some((item) => item.itemName == this.itemOthersAddonList[z].itemName)) {
        this.lhsRhsItemsList.push(this.itemOthersAddonList[z]);
    }
    }console.log(this.lhsRhsItemsList);
  }
  
  //addding new line for LHS and RHS
  addRowsLhsRhs(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsnewLine = true;
  }

  //cancel new line in LHS/RHS
  cancelLinesLhs(i){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsnewLine = false;
    this.lhsRhsItemsList.splice(i, 1);
  }

  // opening new table for others
  openCompTable(event) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if (event.target.checked) {
      this.displayCompOthTable = true;
    }
    else if (!event.target.checked) {
      this.displayCompOthTable = false;
      this.compOthersAddonList = [];
      this.disablelhs=[];
    }
  }

  //cancel new line
  cancelnewLineOth() {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.displayOthnewLine = false;
  }

   // adding new other lines
  addRowsOth() {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.displayOthnewLine = true;
  }

  // delete the added line for others
  cancelLinesOth(i) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.compOthersAddonList.splice(i, 1);
    this.disablelhs.splice(i, 1);

  }
  
  // others inside panel
  compOthersForm(others, othersfrm: NgForm) {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(others);
    this.dboClass = new dboClass();
    this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboClass.itemId = this.itemIdd;
    this.dboClass.itemName = null;
    if(this.subItemIdd == 0){
      this.dboClass.subItemId = 0;
    }else{
      this.dboClass.subItemId = this.subItemIdd;
    }
    this.dboClass.subItemName = null;
    if(this.subItemTypeId == 0){
      this.dboClass.subItemTypeId = 0;
      }else{
        this.dboClass.subItemTypeId = this.subItemTypeId;
      }
      this.dboClass.subItemTypeName = null;
           this.dboClass.colId = 0;
          this.dboClass.colNm = others.othCompName;
          this.dboClass.colValCd = others.othCompVal;    
          this.dboClass.quantity = null;
          this.dboClass.cost = others.othPrice;
          this.dboClass.techComments = others.othRemarks;
          this.dboClass.comrComments = others.othComeComments;
          this.dboClass.addOnNewColFlag = 1;
          this.dboClass.techFlag = 1;
          this.dboClass.comrFlag = 1;
         // this.dboClass.addOnCostMeFlag = 0;   
    this.compOthersAddonList.push(this.dboClass);
    this.disablelhs[this.compOthersAddonList.length-1]=false;
    console.log(this.compOthersAddonList);    
    console.log(this.dboEleFullArray);

    othersfrm.reset();
    this.displayOthnewLine = false;
  }
  //remarks for items and opening text area for remarks
  itemRmrkCheck(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(event);
    if(event.target.checked){
      this.itemRemarkDiv = true;
    }else if(!event.target.checked){
      this.itemRemarkDiv = false;
    }
  }

   //capturing of Item techinal  Remarks value
   checkRemarks(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(this.itemRemarksVal)
  }

  //Commercial remarks for items and opening text area for remarks
  itmComrRmrk(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
      this.itemComrRemarkDiv = true;
    }else if(!event.target.checked){
      this.itemComrRemarkDiv = false;
    }
  }

   //capturing of Item techinal  Remarks value
   checkItemComrRmrks(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(this.itemCmrRemarksVal)
  }

  //capturing of sub item techinal Remarks value
  checkSubItmTechRemarks(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(this.subItemRemarksVal);
  }

  ts
  //cancel added new line items others  in LHs/Rhs
  cancelnewLineLhs(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsnewLine = false;//added by megha
  }
  //Techinal remarks for sub items and opening text area for remarks
  subItemRmrkCheck(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
      this.subItemTechRmkDiv = true;
    }else if(!event.target.checked){
      this.subItemTechRmkDiv = false;
      this.subItemRemarksVal = '';//added by megha
    }
  }

  //Commercial remarks for sub items and opening text area for remarks
  subItemComrCheck(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
        this.subItemComrRmkDiv =true;
    }else if(!event.target.checked){
      this.subItemComrRmkDiv = false;
      this.subItemCmrRemVal = '';//added by megha
    }
  }

   //capturing of subItem commercial  Remarks value
   checkSubItemComrRemarks(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(this.subItemCmrRemVal);
  }

  //techinal remakrs for subitem and opening text area for remarks outhside others
  subItemTechRem(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
      this.subItemRemarkDiv = true;
    }else if(!event.target.checked){
      this.subItemRemarkDiv = false;
    }
  }

  //capturing of subitems techinal Remarks value outside others
  checkSubItemRem(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(this.subItemRmkValOut);
  }

  //Commercial remakrs for subitem and opening text area for remarks outhside others
  subItemComrRem(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
      this.subItemComrRemDiv = true;
    }else if(!event.target.checked){
      this.subItemComrRemDiv = false;
    }
  }

  //capturing of subitems Commercial Remarks value outside others
  subChkItemComrRmrks(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(this.subItemCmrRemValOut);
  }

  //Dispaly others tables to add new subitemtype list
  openSubItmTypTbl(event){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(event.target.checked){
      this.dsplySubItmTypOthTable = true;
    }else if(!event.target.checked){
      this.dsplySubItmTypOthTable = false;
      this.subItmTypOthList = [];
      this.lhsRhsSubItmTypList=[];
    }
  }

  //cancel the line from others subitemstype list
  cncleLinSubItmTypOth(i){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
   for(let j = 0; j < this.lhsRhsSubItmTypList.length; j++)
    {
      //Need to remove the lhsrhsitemlist also
      if(this.subItmTypOthList[i].subItemTypeName == this.lhsRhsSubItmTypList[j].subItemTypeName)
      {
        //this.lhsRhsSubItmTypList[j] = null;  // ADDED SATYA
       this.lhsRhsSubItmTypList.splice(j, 1);
       j = j - 1;
      }
    }
    this.subItmTypOthList.splice(i, 1);
  }

  //to display Lhs/Rhs table and subitemtype name
  addLhsRhsSubItmTyp(i){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsSubItmTypSel = ''; 
    this. ibreakothers = i;
    console.log(i);
    this.lhsRhsSubItmTypSel = this.subItmTypOthList[i].subItemTypeName;
    console.log(this.lhsRhsSubItmTypSel);
      this.dsplyDlgSubTypLhsRhs = true;
      this.dsplySubItmTypOthnwLin = false;
  }

  //adding row to other table for subitemsType
  addRowSubItmtyp(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.dsplySubItmTypOthnwLin = true;
  }
  
  //cancel row for subitemstype others table
  canclnwLinSubItmTyp(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.dsplySubItmTypOthnwLin = false;
  }

  //Add others subitemType to list
  subItmTypOthForm(othSubItmTyp, othSubItmTypFrm: NgForm){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(othSubItmTyp);
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.itemIdd;
      this.dboClass.itemName = null;        
      this.dboClass.subItemId = this.subItemIdd;
      this.dboClass.subItemName = null;
      this.dboClass.subItemTypeId =0;
      this.dboClass.subItemTypeName = othSubItmTyp.subItemTypeName;
      this.dboClass.colId = 0;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = null;
     // this.dboClass.colValNm = null;
      this.dboClass.quantity = othSubItmTyp.subItmTypQty;
      this.dboClass.cost = othSubItmTyp.subItmTypPric;
      this.dboClass.techComments = othSubItmTyp.subItmTypTechRem;
      this.dboClass.comrComments = othSubItmTyp.subItmTypComrRemr;
      this.dboClass.addOnNewColFlag = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
    
     
      //this.dboClass.othColValFlag = 0;   
    this.subItmTypOthList.push(this.dboClass);
    this.lhsRhsSubItmTypList.push(this.dboClass);//satya
    console.log(this.subItmTypOthList);
    othSubItmTypFrm.reset();
    this.dsplySubItmTypOthnwLin = false;
  }

  //cancel line others sub item type name Lhs/Rhs values from list
  cnclLinSubtypLhs(i){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsSubItmTypList.splice(i, 1);
  }

  //add LHS/RHS to others subitemtype list
  addSubTypLhsRhsForm(lhsRhsSubItmTyp, lhsRhsSubItmTypFrm: NgForm){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    console.log(lhsRhsSubItmTyp);
    for(let k = 0; k < this.lhsRhsSubItmTypList.length; k++)
    {
     if(this.lhsRhsSubItmTypList[k].subItemTypeName == this.subItmTypOthList[this.ibreakothers].subItemTypeName && this.lhsRhsSubItmTypList[k].colId == null && this.lhsRhsSubItmTypList[k].colNm == null){
       this.lhsRhsSubItmTypList.splice(k,1);
       this.ibreakothers = 1;
       break;          
     }
   }
   this.ibreakothers = 0;
    for(let c=0; c<this.subItmTypOthList.length; c++){
      if(this.lhsRhsSubItmTypSel == this.subItmTypOthList[c].subItemTypeName){
        this.dboClass = new dboClass();
        this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        this.dboClass.itemId = this.subItmTypOthList[c].itemId;
        this.dboClass.itemName = this.subItmTypOthList[c].itemName;
        this.dboClass.subItemId = this.subItmTypOthList[c].subItemId;
        this.dboClass.subItemName = this.subItmTypOthList[c].subItemName;
        this.dboClass.subItemTypeId = this.subItmTypOthList[c].subItemTypeId;
        this.dboClass.subItemTypeName = this.subItmTypOthList[c].subItemTypeName;
        this.dboClass.colId = 0;
        this.dboClass.colNm = lhsRhsSubItmTyp.subTypLhsVal;
        this.dboClass.colValCd = lhsRhsSubItmTyp.subTypRhsVal;
    
     
        this.dboClass.quantity = this.subItmTypOthList[c].quantity;
        this.dboClass.cost = this.subItmTypOthList[c].cost;
        this.dboClass.techComments = this.subItmTypOthList[c].techComments;
        this.dboClass.comrComments = this.subItmTypOthList[c].comrComments;
        this.dboClass.addOnNewColFlag = 1;  
        this.dboClass.techFlag = 1;
        this.dboClass.comrFlag = 1;
       
        //this.dboClass.othColValFlag = 0;
            
        this.lhsRhsSubItmTypList.push(this.dboClass);
      }
    }
    console.log(this.lhsRhsSubItmTypList);
    lhsRhsSubItmTypFrm.reset();
    this.lhsRhsSubTypnewLine = false;
    this.dsplySubItmTypOthnwLin = false;
  }

  //save others subitemtype with and without records in a list 
  subItmTypSave(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    for(let b=0;b<this.subItmTypOthList.length; b++){
      if (!this.lhsRhsSubItmTypList.some((item) => item.subItemTypeName == this.subItmTypOthList[b].subItemTypeName)) {
        this.lhsRhsSubItmTypList.push(this.subItmTypOthList[b]);
    }
    }console.log(this.lhsRhsSubItmTypList);
  }

  //add Rows to others sub item type LHS/RHS
  addRowSubTypLhsRhs(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsSubTypnewLine = true;
  }

  //cancel row from others table subItemType 
  cnclnwLineSubTypLhs(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.lhsRhsSubTypnewLine = false;
  }

  //save other subitemType in DB
  subItmTypSubmit(){
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormData.itemId = this.itemIdd;
    this.dboFormData.subItemId = this.subItemIdd;
    console.log(this.itemIdList2);
    this.dboFormData.subTypeItemIdList = this.itemIdList2.filter(n => n != null);
    this.dboFormData.savedF2fSubTypeDataList = this.lhsRhsSubItmTypList;
    this.dboFormData.subItemTypeTechRemarks = this.subItmTypRmkValOut;
    this.dboFormData.subItemTypeComrRemarks = this.subItmTypComRemValOut;
    this.dboFormData.modBy = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    console.log(this.dboFormData);
    this._ITOturbineConfigService.saveF2fSubItemType(this.dboFormData).subscribe(savedSubTypResp => {
      console.log(savedSubTypResp);
      if(savedSubTypResp.successCode == 0){
        this.subItemTypebutton=false;
        this._ITOeditQoutService.f2fOthersSubItemTypeList = savedSubTypResp.savedF2fSubTypeDataList;
      }
      this.saveInLocal(this.F2FTurbine, {
      dboEleFullArray: this.dboEleFullArray, compOthersAddonList: this.compOthersAddonList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1, itemIdList2: this.itemIdList2,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1, selectdEl2: this.selectdEl2,
      lhsRhsSubItemsList: this.lhsRhsSubItemsList, subItemOthAddonList: this.subItemOthAddonList,
      lhsRhsSubItmTypList: this.lhsRhsSubItmTypList, subItmTypOthList: this.subItmTypOthList,
      lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,      
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      subItmTypRmkValOut: this.subItmTypRmkValOut, subItmTypComRemValOut: this.subItmTypComRemValOut,
      finalEleCost: this.finalEleCost, totalF2fCost: this.totalF2fCost, addOnList:this.addOnList,addonflg:this.addonflg,subQuantity:this.subQuantity,
       lubItemcost: this.lubItemcost, discountPerS: this.discountPerS
      });
    })
  }

  // before calculating total, changes if any,are saved to Db so that total cost will be accurate
  saveCalcTotal() {
    
    if (this.enableOverwriteDiv) {
      this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleCost;
      this.dboFormData.overwrittenPriceFlag = 1;
      this.dboFormData.remarks = this.remarks;
    } else if (this.OverWrittenfinalEleCost > 0) {
      this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleCost;
      this.panelList.overwrittenPriceFlag = 1;
    }
    this.dboFormData.modBy = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails; 
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormData.f2fItemTechComments = this.itemRemarksVal;
    this.dboFormData.f2fItemComrComments = this.itemCmrRemarksVal;
    this.dboFormData.itemIdList = this.itemIdList.filter(n => n != null);
    console.log(this.itemIdList)
    this.dboFormData.savedF2fDataList = this.lhsRhsItemsList;
    this.dboFormData.savedF2fSubDataList = this.lhsRhsSubItemsList;
    this.dboFormData.savedF2fSubTypeDataList = this.lhsRhsSubItmTypList;
    console.log(this.dboFormData);
    this._ITOturbineConfigService.saveF2fItem(this.dboFormData).subscribe(savedResp => {
      console.log(savedResp);
      if(savedResp.successCode == 0){
        this.finalCostBool =  true;
        this.mainSave2=false;
        // this._ITOeditQoutService.f2fOthersItemList = savedResp.savedF2fDataList;
        // this._ITOeditQoutService.f2fOthersSubItemList = savedResp.savedF2fSubDataList;
        // this._ITOeditQoutService.f2fOthersSubItemTypeList = savedResp.savedF2fSubTypeDataList;
      }
      this.saveInLocal(this.F2FTurbine, {
      dboEleFullArray: this.dboEleFullArray, compOthersAddonList: this.compOthersAddonList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1, itemIdList2: this.itemIdList2,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1, selectdEl2: this.selectdEl2,
      lhsRhsSubItemsList: this.lhsRhsSubItemsList, subItemOthAddonList: this.subItemOthAddonList,
      lhsRhsSubItmTypList: this.lhsRhsSubItmTypList, subItmTypOthList: this.subItmTypOthList,
      lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,      
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      subItmTypRmkValOut: this.subItmTypRmkValOut, subItmTypComRemValOut: this.subItmTypComRemValOut,
      finalEleCost: this.finalEleCost, totalF2fCost: this.totalF2fCost, addOnList:this.addOnList,addonflg:this.addonflg,subQuantity:this.subQuantity,
       lubItemcost: this.lubItemcost, discountPerS:this.discountPerS
      });
       //call one line BOM
       this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
        console.log(resOnline);
        if (resOnline.successCode == 0) {
          this.finalEleCost = resOnline.oneLineBomExcel.f2fCost;
          this.totalF2fCost = resOnline.oneLineBomExcel.totalF2fCost;
        } 
        this.saveInLocal(this.F2FTurbine, {
          dboEleFullArray: this.dboEleFullArray, compOthersAddonList: this.compOthersAddonList,
          itemIdList: this.itemIdList, itemIdList1: this.itemIdList1, itemIdList2: this.itemIdList2,
          selectdEl: this.selectdEl, selectdEl1: this.selectdEl1, selectdEl2: this.selectdEl2,
          lhsRhsSubItemsList: this.lhsRhsSubItemsList, subItemOthAddonList: this.subItemOthAddonList,
          lhsRhsSubItmTypList: this.lhsRhsSubItmTypList, subItmTypOthList: this.subItmTypOthList,
          lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,
          itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,      
          subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
          subItmTypRmkValOut: this.subItmTypRmkValOut, subItmTypComRemValOut: this.subItmTypComRemValOut,
          finalEleCost: this.finalEleCost, totalF2fCost: this.totalF2fCost ,addOnList:this.addOnList,addonflg:this.addonflg,subQuantity:this.subQuantity,
           lubItemcost: this.lubItemcost, discountPerS: this.discountPerS
          });
            
        this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
        this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, finalEleCost: this.finalEleCost, totalF2fCost: this.totalF2fCost,});
        // if (this._ITOcustomerRequirementService.editFlag) {
        //   this._ITOcustomerRequirementService.editFlag = false;
        //   this.router.navigate(['/EditQuot']);
        // }
      });
      this.hideprogressCost1 = false;
    });
  }
  //calculate total function
  /**
   * calculte total
   */
  
  calcTotal() {
    this.hideprogressCost1 = true;
    this.saveCalcTotal();
    //this.finalEleCost = 0;
    // console.log(this.finalAddGenListArray, this.dboEleFullArray, this.othersAddonList);
    // this.finalAddGenListArray = this.finalAddGenListArray.filter((x) => {
    //   return (x != null);
    // })
  }
//To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
} 

/**
   * to get previous comments
   */
  getPrevComments() {
    this.message = false;
    this.successMsg = '';
    console.log(this._ITOcustomerRequirementService.saveBasicDet);
    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "F2F";
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
//// others edit 
editlhs(i)
  {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
      this.disablelhs[i]=true;

    
  }
  savelhs(i,type)
  {
    this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
      this.disablelhs[i]=false;

    
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
clearerror()
  {
    this.dispSCFM=false;

  }
  showDialogMaximized(event, dialog: Dialog){
    dialog.maximized = false;
    dialog.toggleMaximize(event);
  }
  }
