import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { DboEleAuxialriesService } from './dbo-ele-auxialries.service';
import { dboClass } from '../dbomechanical/dbomechanical';
import { ITOLoginService } from '../app.component.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { MenuItem } from 'primeng/api';
import { LOCAL_STORAGE, SESSION_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ITOAddOnComponentsService } from "../add-on-components/add-on-components.service";
import { Inject } from '@angular/core';
import { dboSpclTools } from '../dboelectrical/dboelectrical';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
import { empty } from 'rxjs/Observer';
import { NgForm } from '@angular/forms';
import { CurrencyPipe } from '@angular/common';
import { Dialog } from 'primeng/dialog';
@Component({
  selector: 'app-dbo-ele-auxialries',
  templateUrl: './dbo-ele-auxialries.component.html',
  styleUrls: ['./dbo-ele-auxialries.component.css']
})
export class DboEleAuxialriesComponent implements OnInit {

  inputText: Array<any> = [];// To store text for dropdown specfic in colType INPUTERRORBOX
  costQty: Array<number> = []; //To Store cost for dropdown specific input field when inputCostFlag is 1
  inptCstQty: Array<boolean> = []; //To enable or disable input field for quantity inside panle for specfic dropdown change if inputCostFlag is 1
  inpText: Array<boolean> = []; //To enable or disable input field for text inside panle for specfic dropdown change if inputCostFlag is 1
  footerText: Array<any> = []; //To display panel and item footer
  headerText: Array<any> = []; // To display panel and item header
  errMsgPnl:  Array<any> = []; //To display error msg if error msg is not equal to null on hitting getEleTechPrice
  errDisplayPnl:  Array<boolean> = []; //To Display error msg div if error msg is not equal to null on hitting getEletechPrice
  errDisplayRhsCost: Array<boolean> = []; //To Display error msg div if addOnflg is 1 on hitting getEletechPrice
  errMsgRhsCost: Array<any> = []; //To display error msg if addonflg is 1 on hitting getEleTechPrice
  errMsgRed: Array<boolean> = [];//To display red color message if approxCostFLag is true on hitting getEleTechPrice
  errMsgBlue: Array<boolean> = [];//To display red color message if approxCostFLag is true on hitting getEleTechPrice
  errMsgGreen: Array<boolean> = [];//To display red color message if approxCostFLag is true on hitting getEleTechPrice
  eleItemId: number; //store unique id that is eleItemId on selection of item
  dboEleAuxFullArray: Array<any> = []; //store complete data from getEleTechPrice
  dboCost: number; //basiccost from getEleTechPrice to display in UI Pannel cost
  tempitemOthersList:Array<any> = []; //store other attributes (new Lhs and Rhs) inside the panel
  dboAddOnCost:any; //store Ele addon cost from getEleTecPrice
 message: boolean = false;
 successMsg: string = '';
  eleItemTechRemarks: string; //Techinal remarks for inside the item panel
  eleItemComrRemarks: string; //Commercial remarks for inside the item panel
 
  newAddRemrkO: Array<any> = []; //getting values from HTML for addon remarks
  newAddCostO: Array<any> = []; //getting values from HTML for addon Cost
 
  newAddNameO: Array<any> = []; //getting values from HTML for addon Name
  addOnCheck: Array<boolean> = []; // check or uncheck for
  ratedVoltage: any; //To store ratedVoltage values from dboElePanelList2 
  AddOnFlag: Array<boolean> = []; // To enable ADdon button based on flag
  dboBasicCost: number; //basiccost from getEleTechPrice
  subItemName: string; //store subItemName on selection of subItem
  selectedELIndex: number; //selected panel index to perform check and uncheck
  displayEle: boolean = false; // to Display dialog box of panel dropdown data and others and remarks
  hideprogressCost1: boolean = false;  // to Display dialog box of panel dropdown data and others and remarks
  itemRemarkaddon: Array<boolean> = []; // new rhs remarks
  iItemindex: number = 0;
  hideprogressCost: boolean = false; // progress bar for submit button
  itemtempId : number = 0; // to store temporary item id 
  iFlag : number = 0; // to keeep account of the selected item is item or subitem.
  index: number = 0; // item index number from db 
  count : number = 0; // to check if all the subitem items are unchecked or not
  itemIdList: Array<number> = []; // store the item id's of the items selected.
  itemIdListAux: Array<number> = [];  // store the id's of the items selected.
  techCheckIn: boolean = false; //boolean to check techinal remarks
  comrrCheck: boolean = false; //boolean to check commercial remarks
  itemIdList1: Array<number> = [];  // store the sub - item id's of the items selected.
  dboElePanelList2: Array<any> = []; //To store dboElePanelList2 data from getElePanel
  dboElePanelList1: Array<any> = []; //to store dboElePanelList1 data from getElePanel
  questionsBean: Array<any> = []; // to store filtered questionsBean data from questionsBean1
  questionsBean1: Array<any> =[]; //to store questionsBean data from getElePanel
  questionsBean2: Array<any> =[]; //to store questionsBean data from getElePanel
  questionsBean3: Array<any> =[]; //to store questionsBean data from getElePanel
  defaultValues: Array<any> = [];  // To store default values of questionsBean list
  defaultValues2: Array<any> = [];  // To store default values of questionsBean list
  techPriceResp:any; //storing respone from getEleTechPrice
  colValNmId: number; //store col Id
  subMessage: boolean = false; // to show cost saved successfully
  displayEleSubList: boolean = false; // to show if an item contains sub item or not .
  itemName: string; // item name od the selected item.
  messageVal:string; // contains the success mesage.
  sublistDup: Array<any> = [];
  subList1: Array<any> = [];
  dboFormData: any; //store response from getCBOFormData endpoint
  selectdEl: Array<boolean> = [];
  selectdEl1: Array<boolean> = [];
  generalEleFliterInputList: Array<any> = []; //to push data according to dboform input required
  dboClass: dboClass;
  defaulVales: Array<any> = []; // store values to display newarray list values
  saveEleFilterList: Array<any> = []; //to store saveEleFilterList from saveELEFilter endpoint
  dboEleItemList: Array<any> = []; // to store DboEleItemList list from getEleItem
  newArrayItm: Array<any> = []; //to filter and store dboEleItemList
  ratedOutput: any; //To store ratedOutput values from dboElePanelList2 
  addedClassList: Array<any> = [];
  costNotAvailableError: any;
  errorArray: Array<any> =[];
  openOth:Array<boolean> = []; //To add Rhs for existing lhs
  openOth1:Array<boolean> = []; //To add Rhs for existing lhs
  addOnList: Array<any> = []; //store addon list
  itemId: number; //To store item Id from dboEleSel arguments
  subItemId: number; //To store subItemId from dboEleSel arguments
  SelectedExcelData: Array<any> = [];
  test123: Array<any> = [];
  itemOthersList:Array<any> = []; //store other attributes (new Lhs and Rhs) inside the panel
  qty: number = 1; //Quantity for panel
  itemTechRmkDiv: boolean = false; //boolean to display input for techinal remaks inside the item panel
  itemRemarksVal:any; //to store input data of item techinal remarks 
  itemComrRemarkDiv: boolean = false; //to display
  itemCmrRemarksVal:any; //to store input dat of item commercial remarks item remarks inputfiels to enter commercial remarks
  itemRemarkDiv:boolean = false; //to Display item remarks inputfield to enter techinal remarks
  subItemRemarkDiv:boolean = false; //to display subItem remarks inputfield to enter techinal remarks
  subItemRmkValOut: any; //to store input data of subitem techinal remarks
  subItemComrRemDiv: boolean = false; //to dispaly subitem remarks inputfield to enter commercial remarks
  subItemCmrRemValOut: any; // to store input data of subitem commercial remarks
  itemComrRmkDiv: boolean = false; //boolean to display input for commercial remarks inside the panel
  dboEleFull: string = 'dboEleFull';
  dboElectricalAuxLocal: Array<any> = [];
  othItmTechRemChk:boolean=false; // to check or uncheck item remarks
  itemtechRemarkCheck:boolean=false;  // to check or uncheck item remarks
  othItmComrRemChk:boolean=false;  // to check or uncheck item remarks
  itemComrRemarkCheck:boolean=false;  // to check or uncheck item remarks
  othSubItmTechRemChk:boolean=false;  // to check or uncheck sub item remarks
  othSubItmComrRemChk:boolean=false; // to check or uncheck sub item remarks
  subItemTchRem: boolean = false;     // to check or uncheck sub item remarks
  subItemComRem: boolean = false;      // to check or uncheck sub item remarks
  displaySubItemOthTable:boolean = false; //To enable others table on check of other in creating new subitem(others)
  subItemOthAddonList: Array<any> = []; //To push new subitems from others
  lhsRhsSubItemsList: Array<any> = []; // To push LHS/RHS values for new subitem (others)
  dsplySubItmOthnewLine:boolean = false; //To enable and disable div for entering inputs in subitem others
  lhsRhsSubItemSel: string = ''; //To store header name of newely created subitem(others)
  ibreakothers: number = 0;     // to keep account if the othet item contains new lhs and rhs.
  dsplyDialogSubLhsRhs:boolean = false; //To disable or enable div for entering inputs for adding new LHS/RHS  table in subitem(others)
  lhsRhsSubnewLine: boolean = false; //To disable or enable div for entering inputs for adding new LHS/RHS in subitem(others)
  rhs: Array<boolean> = []; //for disabling dropdown inside panel
  groupName:Array<any> = []; // Des item name of the item 
  groupNameNew:Array<any> = []; // temporary store of   Des item name of the item  
  groupNameNew2:Array<any> = [];  // temporary store of   Des item name of the item
  displayPanel:Array<boolean> = []; // to display panel data
  childvalues:Array<any>=[]; // to store child values if an lhs consist
  childvaluesnew:Array<any>=[];  // to store temporary child values if an lhs consist
  childvaluestemp:Array<any>=[];  // to store temporary child values if an lhs consist
  childvaluestemp2:Array<any>=[];  // to store temporary child values if an lhs consist
  newvalues:Array<any>=[]; // 
  itemOthersAddonList:Array<any>=[]; // to store new lhs and rha 
  lhsRhsItemsList:Array<any>=[]; // store new rhs (of the new lhs)
  displayItemCompOthTable:boolean=false; // to check or un check new lhs check box
  displayItmOthnewLine:boolean=false; // to check or un check new lhs check box
  lhsRhsItemSel:string; // to store item name of the new lhs.
  displayDialogLhsRhs:boolean=false; // to display new rhs with its fields.
  lhsRhsnewLine:boolean=false; // to display new rhs lines(to add more than one rhs)
  //enableOverwriteDiv:number=0;
  remarks:string; // if cost is over written then remarks need to be added.
  OverWrittenfinalEleAuxCost:number=0; // overwritten final cost of the complete panel.
  finalEleAuxCost:any=''; // total eleaux cost 
  finalcostflag:boolean=false; // to show total ele aux cost
 enableOverwriteDiv:boolean=false; // to show or not over written cost
 disableStatus:boolean=false; // to show or not over written cost
 oneLineLoc: string = 'oneLineLoc'; // one line bom
 temparray:Array<any>=[]; // temporay storage of data from edit mode.
 newvalues1:Array<any>=[]; // to store child values if the child data is selected.
 temparray1:Array<any> = []; //To store dboEleData 
itemSelectedListEdit:Array<any> = []; //To add Rhs for existing lhs
subitemSelectedListEdit:Array<any> = []; //To add Rhs for existing lhs
tempaddonsubitem: Array<any> = []; //To store itemOthersList in edit mode
tempaddonrhs: Array<any> = []; //To store addonlist in edit mode
defaultValues1: Array<any> = []; //To store default values in edit mode
newSet: Array<any> = []; // To store unique item names from temparray1
prevData: Array<any> = []; //To store filtered records from temparray1
oneLineLocArray: any; // to store one line bom.
othersCheck: boolean = false; // enable new item others table
othersSubCheck: boolean = false; //Enable new subitem table
childvaluestemp1:Array<any>=[]; // to temporary store child values.
 defaultValuesid:Array<any>=[]; // to store order id of the lhs's.
 defaultlhs:Array<boolean>=[];
 addOnCost:Array<any>=[]; // add on cost of the paricular item.
 itemOthersAddonListTemp: Array<any> = []; //To store newly created items from edit mode(others)
 lhsRhsItemsListTemp:Array<any> = []; // To store newly created items Lhs/Rhs from edit mode(others)
 defaultValuesid1:Array<any>=[];  // to temporary store order id's.
 displayCompOthTable:Array<boolean> = []; //open others attribute to add new LHS and RHS
 othersCompCheck:Array<boolean> = []; //flag to make others check box (itemOthersList)
 displayOthnewLine:Array<boolean> = [];//open others attribute to enter new LHS and RHS values
 othCheck:Array<boolean> = []; // main panel check or un check of remarks.
 enablebutton:boolean=false;
 enablebuttonNew:boolean=false;
 tempArray:Array<any>=[]; // to store edit mode data.
 newArray12:Array<any>=[]; // to temporary store filter data from edit mode.
 dboEleFilterListNew:Array<any>=[]; // filter panel data
 defaulVales1:Array<any>=[]; // to store childvlues default values.
 newArryUpd:Array<any>=[]; // to store changed filter data.
 lhsCheck: Array<boolean> = []; //Boolean to check or un check LHS inside the panel 
 drpCheck: Array<boolean> = []; //Boolean to check or uncheck Rhs quantity inside the panel
 dropCheckQty: Array<boolean> = []; //Boolean to open or close quantity input field inside panel
 drpCheckqtyy: Array<any> = []; //To store dropDownCheckBox quantity
 drpCheckqtyyKey: Array<boolean> = []; //To store dropDownCheckBox key
 lhsNmCheck: Array<boolean> = []; //To store dropDownCheckBox key
 dboEleAuxFull: string = 'dboEleAuxFull'; // ; local storage name for electrical auxilaries.
newPanel:Array<boolean>=[]; // to show table of the Multifunctional digital generator protection relay shall have following protections features des item.
newPanelBol:Array<boolean>=[]; // to show table of the Multifunctional digital generator protection relay shall have following protections features des item.
newPanelval:Array<any>=[]; // to show table of the Multifunctional digital generator protection relay shall have following protections features des item.
addonflg="0"; // to enable or disable new rhs fields
itemcost:number=0; // to store total item cost
tempboolean:Array<boolean>=[];
backBtn: boolean = false; // to enable or disable back button.
linkFlag:boolean=false; // link flags for cables.
saveBtColor:boolean=true; // save button to check if submit button is pressed or not
buttonColor:string="rgb(213,120,23)"; // submit button colour
panelapplicable:boolean=false; // to show the dialogue box if any error msg are present for any lhs
othersSubItemCheck:boolean=false; // to check or uncheck sub item remarks
deItemLevelOthers:Array<boolean>=[]; // to store true values for particular item if (groupname and groupname2)
desItemNamList:Array<any>=[]; // to store des itemname's present for the item
desSubItemNamList:Array<any>=[]; // to store des subitemname's present for the item
panelapplicablescm:boolean=false; // to show the dialogue box if any error msg are present for any lhs
itemNameCurrent:string=""; // current item name selected
colNumber:Array<any>=[]; // to store col number of the lhs
alert:string="                     !!!!!ALERT!!!!"; // alert message if any lhs is not applicable for the item
Ifapplicabletemp:Array<any>=[]; // to store error message if any lhs is not applicable for the item
Ifapplicable: Array<any> = []; // to store error message if any lhs is not applicable for the item
techFlagindicator: Array<boolean> = []; // to store tech flags of all lhs
diableitemname:boolean=true;
getPrice:boolean=true;
subItemButton:boolean=true;




///// desSubItemName123
othersDesSubCheck:boolean=false; // to store if the des subitem if checked or un checked
othersDesSubItemCheck:boolean=false;  // to store if the des subitem if checked or un checked
displayDesSubItemOthTable:boolean = false; //To enable others table on check of other in creating new subitem(others)
  dessubItemOthAddonList: Array<any> = []; //To push new subitems from others
  lhsRhsDesSubItemsList: Array<any> = []; // To push LHS/RHS values for new subitem (others)
  dsplyDesSubItmOthnewLine:boolean = false; //To enable and disable div for entering inputs in subitem others
  lhsRhsDesSubItemSel: string = ''; //To store header name of newely created subitem(others)
  dsplyDialogDesSubLhsRhs:boolean = false; //To disable or enable div for entering inputs for adding new LHS/RHS  table in subitem(others)
  lhsRhsDesSubnewLine: boolean = false; //To disable or enable div for entering inputs for adding new LHS/RHS in subitem(others)
////
finalCostBool: boolean = false; // to show cost of the panel
 ///
 /////others lhs 
disablelhs: Array<boolean> = [];
disablelhssub: Array<boolean> = [];


lhsdes: Array<boolean> = [];
rhsdes: Array<boolean> = [];
lhsdes2: Array<boolean> = [];
rhsdes2: Array<boolean> = [];
itemCode:string='';
user: string = 'userDetail';
currentRole: string = 'selRole';
currentRoleId: string = 'selRoleId';
rewApp: boolean = false;
discountPer: number = 0;
mainSave:boolean=true;
mainSave2:boolean=true;
appDisable: boolean = false;
saveSubCost:number=0;
refreshBoolean:boolean=true;


constructor(private _DboEleAuxialriesService: DboEleAuxialriesService, private _ITOLoginService: ITOLoginService,
  private _ITOturbineConfigService: ITOturbineConfigService, private _ITOAddOnComponentsService: ITOAddOnComponentsService,
  private _ActivatedRoute: ActivatedRoute, private router: Router, private _ITOeditQoutService: ITOeditQoutService,
  private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOCostEstimationService: ITOCostEstimationService,
  private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    
    	
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
       this._ITOeditQoutService.button1=false;
       this._ITOeditQoutService.button2=false;
       this._ITOeditQoutService.button3=false;
       this._ITOeditQoutService.button4=false;
       this._ITOeditQoutService.button5=false;
       this._ITOeditQoutService.button6=true;
       this._ITOeditQoutService.button7=false;
       this._ITOeditQoutService.button8=false;
       this._ITOeditQoutService.button9=false;
    //To set to null in the beginning of the edit mode
       this.selectdEl = [];
       this.selectdEl1 = [];
       this.temparray1 = [];
       //Multifunctional digital generator protection relay shall have following protections features
       //Multifunctional digital generator protection relay shall have following protections features
      // this._ITOeditQoutService.dboEleAuxData
       console.log( this._ITOeditQoutService.dboEleAuxData);
       if(this._ITOeditQoutService.dboEleAuxData.length >0){
         this.finalCostBool = true;
         this.mainSave2=false;
  this.mainSave=false;
       }
       console.log(this._ITOeditQoutService.dboEleItmOthers);
       if( this._ITOcustomerRequirementService.editFlag==true)
       {
        this.temparray1= this._ITOeditQoutService.dboEleAuxData;
       }
       if(this._ITOeditQoutService.checkEdit == false){
        this.backBtn = true;
      }
      if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
        this.appDisable=true;
      }else{
        this.appDisable=false;
      }
    this._ITOturbineConfigService.getDboFormData().subscribe(ress => {
      console.log(ress);
      this.dboFormData = ress;
      this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboElectricalAuxLocal[this.dboEleAuxFull] = this.storage.get(this.dboEleAuxFull);
      console.log(this.storage.get(this.dboEleAuxFull));
      if (this.storage.get(this.dboEleAuxFull) == null) {
      this.saveInLocal(this.dboEleAuxFull, {
        dboEleAuxFullArray: this.dboEleAuxFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
        itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
        selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
        itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
        subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
        eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
        finalEleAuxCost: this.finalEleAuxCost, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList,saveSubCost:this.saveSubCost
      });
    }
      this.saveSubCost= this.storage.get(this.dboEleAuxFull).saveSubCost;
    
   
    if(this.storage.get(this.dboEleAuxFull).dboEleAuxFullArray.length!=0){
      this.dboEleAuxFullArray= this.storage.get(this.dboEleAuxFull).dboEleAuxFullArray;
    this.finalCostBool = true;
    this.mainSave2=false;
  this.mainSave=false;
   }
   if(this.storage.get(this.dboEleAuxFull).addOnList.length!=0){
    this.addOnList= this.storage.get(this.dboEleAuxFull).addOnList;
  
 }
 if(this.storage.get(this.dboEleAuxFull).itemIdList.length!=0){
  this.itemIdList= this.storage.get(this.dboEleAuxFull).itemIdList;

}
if(this.storage.get(this.dboEleAuxFull).itemIdList1.length!=0){
this.itemIdList1= this.storage.get(this.dboEleAuxFull).itemIdList1;

}
if(this.storage.get(this.dboEleAuxFull).selectdEl!=undefined){
this.selectdEl= this.storage.get(this.dboEleAuxFull).selectdEl;

}
//To get selectedEl1 from local storage
if(this.storage.get(this.dboEleAuxFull).selectdEl1!=undefined){
this.selectdEl1= this.storage.get(this.dboEleAuxFull).selectdEl1;
}
//to display new item creation other from local storage
if(this.storage.get(this.dboEleAuxFull).itemOthersAddonList.length != 0){
  this.itemOthersAddonList = this.storage.get(this.dboEleAuxFull).itemOthersAddonList;
}
 // to dispaly new item creation other with lhs/rhs from local storage
 if(this.storage.get(this.dboEleAuxFull).lhsRhsItemsList.length != 0){
  this.lhsRhsItemsList = this.storage.get(this.dboEleAuxFull).lhsRhsItemsList;
}
 if(this.storage.get(this.dboEleAuxFull).itemRemarksVal!=null  && this.storage.get(this.dboEleAuxFull).itemRemarksVal != ""){
  this.itemRemarksVal= this.storage.get(this.dboEleAuxFull).itemRemarksVal;
  this.othItmTechRemChk=true;
 this.itemtechRemarkCheck=true;
 this.itemRemarkDiv=true;

}
if(this.storage.get(this.dboEleAuxFull).itemCmrRemarksVal!=null  && this.storage.get(this.dboEleAuxFull).itemCmrRemarksVal != ""){
this.itemCmrRemarksVal= this.storage.get(this.dboEleAuxFull).itemCmrRemarksVal;
this.othItmComrRemChk=true;
this.itemComrRemarkCheck=true;
this.itemComrRemarkDiv=true;

}
if(this.storage.get(this.dboEleAuxFull).subItemRmkValOut!=null  && this.storage.get(this.dboEleAuxFull).subItemRmkValOut != ""){
this.subItemRmkValOut= this.storage.get(this.dboEleAuxFull).subItemRmkValOut;
this.othSubItmTechRemChk=true;
this.subItemTchRem=true;
this.subItemRemarkDiv=true;

}
if(this.storage.get(this.dboEleAuxFull).subItemCmrRemValOut!=null && this.storage.get(this.dboEleAuxFull).subItemCmrRemValOut != ""){
this.subItemCmrRemValOut= this.storage.get(this.dboEleAuxFull).subItemCmrRemValOut;
this.othSubItmComrRemChk=true;
this.subItemComRem=true;
this.subItemComrRemDiv=true;
}
if(this.storage.get(this.dboEleAuxFull).subItemOthAddonList.length!=0 ){
  this.lhsRhsSubItemsList= this.storage.get(this.dboEleAuxFull).lhsRhsSubItemsList;
    this.subItemOthAddonList=this.storage.get(this.dboEleAuxFull).subItemOthAddonList;
  }
  if(this.storage.get(this.dboEleAuxFull).dessubItemOthAddonList.length!=0 ){
    this.lhsRhsDesSubItemsList= this.storage.get(this.dboEleAuxFull).lhsRhsDesSubItemsList;
    this.dessubItemOthAddonList=this.storage.get(this.dboEleAuxFull).dessubItemOthAddonList;
  
    }
this.oneLineLocArray = this.storage.get(this.oneLineLoc);
if( this._ITOcustomerRequirementService.editFlag==true && this._ITOeditQoutService.checkEdit == false){
  if(this.oneLineLocArray != null){
  this.finalEleAuxCost = this.oneLineLocArray.dboEleAuxCost;  
  }
  }else{
    if(this.storage.get(this.dboEleAuxFull).finalEleAuxCost > 0){
    this.finalEleAuxCost = this.storage.get(this.dboEleAuxFull).finalEleAuxCost;
  }
}
console.log(this.dboFormData);
    this._DboEleAuxialriesService.getEleItems(this.dboFormData).subscribe(respon => {
      console.log(respon);
      for(let o=0; o<respon.dboEleItemList.length; o++){
        if(respon.dboEleItemList[o].eleType == "ELE_AUX"){
          this.dboEleItemList.push(respon.dboEleItemList[o]);
        }
      }
      console.log(this.dboEleItemList);
       //filter dboEleFilterList
       this.newArrayItm = this.dboEleItemList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.itemName === current.itemName);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      console.log(this.newArrayItm);

      this.itemSelectedListEdit = this.temparray1.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.itemName === current.itemName);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
  
      //To get the unique list of sub items selected from edit mode
      this.subitemSelectedListEdit = this.temparray1.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.subItemName ===  current.subItemName);
        if (!x) {       
            return acc.concat([current]);        
          
        } else {
          return acc;
        } 
      }, []);
      if(this.temparray1.length!=0)
      {
        if(this._ITOeditQoutService.eleExtScopeList1!=undefined)
        {
          if(this._ITOeditQoutService.eleExtScopeList1!=null)
          {
            if(this._ITOeditQoutService.eleExtScopeList1.length!=0)
          {
            this.saveSubCost=this._ITOeditQoutService.eleExtScopeList1[0].itemCost

          }
          }
        }
        this.itemIdList=[];
        this.itemIdList1=[];
        this.selectdEl1=[];
        this.selectdEl=[];
      }
      for(let m = 0; m < this.itemSelectedListEdit.length; m++)
      {
        for (let k = 0; k < this.newArrayItm.length; k++)
        {
          if(this.itemSelectedListEdit[m].itemName == this.newArrayItm[k].itemName )
          {
            //To check the item in display list
            this.selectdEl[k] = true;
            this.itemIdList[k] = this.itemSelectedListEdit[m].itemId;
          }

        }
      }
      if(this.subitemSelectedListEdit.length != 0)
      {
         this.subList1=[];
         this.sublistDup =[];
           for (let s = 0; s < this.dboEleItemList.length; s++) {                
             if(this.dboEleItemList[s].itemId && this.dboEleItemList[s].subItemName!=null){
               this.sublistDup.push(this.dboEleItemList[s]);
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
          //  this.tempaddonitem =  this._ITOeditQoutService.dboMechData.filter((x) => {
          //   return ((x.addOnNewColFlag ==1));
          // }) 
          this.tempaddonsubitem = this.temparray1.filter((x) => {
            return ((x.lhsFlag ==1)&& (x.newDesItemFlag==0) && (x.newDesSubItemFlag==0));
          })
          for(let j=0;j<this.tempaddonsubitem.length;j++)
          {
          this.dboClass = new dboClass();
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = this.tempaddonsubitem[j].itemId;
         
            this.dboClass.subItemId = this.tempaddonsubitem[j].subItemId;           
          
          this.dboClass.desItemId=this.tempaddonsubitem[j].desItemId;
          this.dboClass.desItemName=null;

          this.dboClass.desSubItemId=this.tempaddonsubitem[j].desSubItemId;
          this.dboClass.desSubItemName=null;
          this.dboClass.colId = 0;
          this.dboClass.colNm = this.tempaddonsubitem[j].colNm ;
          this.dboClass.colValCd =  this.tempaddonsubitem[j].colValCd; 
          this.dboClass.subColValCode =null;
          this.dboClass.quantity =  null;

          //uncomments after css changes
         // this.dboClass.quantity =this.newAddQtyO[index] ;         
          this.dboClass.cost =  this.tempaddonsubitem[j].rhsCost;          
          this.dboClass.techRemarks =   this.tempaddonsubitem[j].rhsColTechcomments;
          this.dboClass.comrRemarks =    this.tempaddonsubitem[j].rhsColComrcomments;
          this.dboClass.addOnNewColFlag = 1;
          this.dboClass.orderId=0;
          this.dboClass.defaultFlagNew=1;
          this.dboClass.dispInd=1;
          this.dboClass.techFlag = 1;
          this.dboClass.comrFlag = 1;
          this.itemOthersList.push(this.dboClass);
          }
          this.tempaddonrhs =  this.temparray1.filter((x) => {
            return ((x.addOnNewColFlag ==1) && (x.lhsFlag ==0));
          })
          for(let j=0;j<this.tempaddonrhs.length;j++)
          {
          this.dboClass = new dboClass();
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = this.tempaddonrhs[j].itemId;

            this.dboClass.subItemId = this.tempaddonrhs[j].subItemId;           
        
          this.dboClass.desItemId=this.tempaddonrhs[j].desItemId;
          this.dboClass.desItemName=null;

          this.dboClass.desSubItemId=this.tempaddonrhs[j].desSubItemId;
          this.dboClass.desSubItemName=null;
          this.dboClass.colId =  this.tempaddonrhs[j].colId;
          this.dboClass.colNm = null;
          this.dboClass.colValCd =  this.tempaddonrhs[j].colValCd; 
          this.dboClass.subColValCode =null;

          this.dboClass.quantity = null;
          //uncomments after css changes
         // this.dboClass.quantity =this.newAddQtyO[index] ;         
          this.dboClass.cost =  this.tempaddonrhs[j].rhsCost;          
          this.dboClass.techRemarks =  null;
          this.dboClass.comrRemarks =  this.tempaddonrhs[j].rhsColComrcomments;
          this.dboClass.addOnNewColFlag = 1;
          this.dboClass.orderId=this.tempaddonrhs[j].orderId;
          this.dboClass.defaultFlagNew=1;
          this.dboClass.dispInd=1;
          this.dboClass.techFlag = 1;
          this.dboClass.comrFlag = 1;
          this.addOnList.push(this.dboClass);
          }

          this.desItemNamList = this.temparray1.filter((x) => {
            return ((x.newDesItemFlag ==1));
          })
          for(let j=0;j<this.desItemNamList.length;j++)
          {
          this.dboClass = new dboClass();
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = this.desItemNamList[j].itemId;
         
            this.dboClass.subItemId = this.desItemNamList[j].subItemId;           
       
          this.dboClass.desItemId=this.desItemNamList[j].desItemId;
          this.dboClass.desItemName=this.desItemNamList[j].desItemName;

          this.dboClass.desSubItemId=this.desItemNamList[j].desSubItemId;
          this.dboClass.desSubItemName=this.desItemNamList[j].desSubItemName;
          this.dboClass.colId = 0;
          this.dboClass.colNm = this.desItemNamList[j].colNm ;
          this.dboClass.colValCd =  this.desItemNamList[j].colValCd; 
          this.dboClass.subColValCode =null;

          this.dboClass.quantity =  null;
          //uncomments after css changes
         // this.dboClass.quantity =this.newAddQtyO[index] ;         
          this.dboClass.cost =  this.desItemNamList[j].rhsCost;          
          this.dboClass.techRemarks =   null;
          this.dboClass.comrRemarks =    null;
          this.dboClass.addOnNewColFlag = 1;
          this.dboClass.orderId=0;
          this.dboClass.defaultFlagNew=1;
          this.dboClass.dispInd=1;
          this.dboClass.techFlag = 1;
          this.dboClass.comrFlag = 1;
          this.lhsRhsSubItemsList.push(this.dboClass);
          }
          this.subItemOthAddonList=[];

          this.subItemOthAddonList = this.lhsRhsSubItemsList.reduce((acc, current) => {
            console.log(acc, current);
            const x = acc.find(item => item.desItemName === current.desItemName && item.itemId === current.itemId && item.subItemId === current.subItemId);
            if (!x) {
              return acc.concat([current]);
            } else {
              return acc;
            } 
          }, []);

          this.desSubItemNamList = this.temparray1.filter((x) => {
            return ((x.newDesSubItemFlag ==1));
          })
          for(let j=0;j<this.desSubItemNamList.length;j++)
          {
          this.dboClass = new dboClass();
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = this.desSubItemNamList[j].itemId;
         
            this.dboClass.subItemId = this.desSubItemNamList[j].subItemId;           
       
          this.dboClass.desItemId=this.desSubItemNamList[j].desItemId;
          this.dboClass.desItemName=null;

          this.dboClass.desSubItemId=this.desSubItemNamList[j].desSubItemId;
          this.dboClass.desSubItemName=this.desSubItemNamList[j].desSubItemName;
          this.dboClass.colId = 0;
          this.dboClass.colNm = this.desSubItemNamList[j].colNm ;
          this.dboClass.colValCd =  this.desSubItemNamList[j].colValCd; 
          this.dboClass.subColValCode =null;

          this.dboClass.quantity =  null;
          //uncomments after css changes
         // this.dboClass.quantity =this.newAddQtyO[index] ;         
          this.dboClass.cost =  this.desSubItemNamList[j].rhsCost;          
          this.dboClass.techRemarks =   null;
          this.dboClass.comrRemarks =    null;
          this.dboClass.addOnNewColFlag = 1;
          this.dboClass.orderId=0;
          this.dboClass.defaultFlagNew=1;
          this.dboClass.dispInd=1;
          this.dboClass.techFlag = 1;
          this.dboClass.comrFlag = 1;
          this.lhsRhsDesSubItemsList.push(this.dboClass);
          }
          this.dessubItemOthAddonList=[];
          this.dessubItemOthAddonList = this.lhsRhsDesSubItemsList.reduce((acc, current) => {
            console.log(acc, current);
            const x = acc.find(item => item.desSubItemName === current.desSubItemName && item.itemId === current.itemId && item.subItemId === current.subItemId);
            if (!x) {
              return acc.concat([current]);
            } else {
              return acc;
            } 
          }, []);
         
         if(this._ITOcustomerRequirementService.editFlag==true)
         {
          this.dboEleAuxFullArray = [];
          this.newSet = [];
         //take the uniqe items names
          this.newSet = Array.from(new Set(this.temparray1.map((x) => {
            return x.eleItemId;
          })));
          console.log(this.newSet);
          let newArr: Array<any> = [];
          console.log( this._ITOeditQoutService.dboEleAuxData)
          for (let h = 0; h < this.newSet.length; h++) {
            for (let i = 0; i < this.temparray1.length; i++) {
              if (this.newSet[h] == this.temparray1[i].eleItemId) {
                newArr.push(this.temparray1[i]);
                break;
              }
            }
          }
          //Display errmsg in edit mode
          // for(let c=0;c<this.temparray1.length;c++){
          //   if((this.temparray1[c].addOnFlg == 1 && this.temparray1[c].rhsCost > 0) || this.temparray1[c].errorMsg != null){
          //     this.errMsgRhsCost[c] = "AddOnCost: " +this.temparray1[c].rhsCost;
          //     this.errMsgPnl[c] = "    Note: " +this.temparray1[c].errorMsg;
          //     this.errDisplayPnl[c] = true;
          //   }else{
          //     this.errMsgRhsCost[c] = [];
          //     this.errMsgPnl[c] = [];
          //     this.errDisplayPnl[c] = false;
          //   }
          // }
         // filtered array with all data
          console.log(newArr);
          let temptechflag=[];

          if (newArr != []) {
            for (let m = 0; m < newArr.length; m++) {
                this.defaultValues1 = [];
                if (this.temparray1.length != 0) {
                  this.prevData = this.temparray1.filter((x) => {
                    return ((x.eleItemId == newArr[m].eleItemId && x.dispInd==1 && x.addOnNewColFlag ==0 &&  x.lhsFlag ==0 ));
                  })
                  console.log(this.prevData);
                  if (this.prevData.length != 0) {
                    for (let d = 0; d < this.prevData.length; d++) {
                      this.defaultValues1.push(this.prevData[d].colValCd);
                    }
                  }
                  this.defaultValuesid1=[];
                  if (this.prevData.length != 0) {
                    for (let d = 0; d < this.prevData.length; d++) {
                      this.defaultValuesid1.push(this.prevData[d].orderId);
                    }
                  }
                  if(this._ITOeditQoutService.dboEleFilterData.length.length!=0)
                  {
this.newArryUpd=[];
this.defaulVales1=[];
                  
                  for(let j=0;j<this._ITOeditQoutService.dboEleFilterData.length;j++)
                  {
                    if(newArr[m].itemId==this._ITOeditQoutService.dboEleFilterData[j].itemId && newArr[m].subItemId==0 )
                    {
                      this.defaulVales1.push(this._ITOeditQoutService.dboEleFilterData[j].colValCd);
                      this.newArryUpd=this._ITOeditQoutService.dboEleFilterData[j];
                    }
                  }
                }
                  this.errMsgRhsCost=[];
                  this.errMsgRed =[];
                  this.errMsgBlue =[];
                  this.errMsgGreen =[];

                  this.errMsgPnl=[];
                  this.errDisplayPnl=[];
                  for (let c = 0; c< this.prevData.length; c++) {
                  if((this.prevData[c].lhsFlag == 0 && this.prevData[c].addOnFlg == 1) ){
                    this.errMsgRhsCost[this.prevData[c].colId] = "AddOnCost: " +this.prevData[c].rhsCost;
                    if(this.prevData[c].approxCostFlag == 0){
                      this.errMsgGreen[this.prevData[c].colId] = true;
                    }
                    if(this.prevData[c].approxCostFlag == 1){
                      this.errMsgBlue[this.prevData[c].colId] = true;
                    }
                    if(this.prevData[c].approxCostFlag == 2){
                      this.errMsgRed[this.prevData[c].colId] = true;
                    }
                    if(this.prevData[c].errorMsg=="NULL" || this.prevData[c].errorMsg=="" || this.prevData[c].errorMsg=="null" || this.prevData[c].errorMsg==null)
                    {
                      this.errMsgPnl[this.prevData[c].colId] ="";
                    }
                    else
                    {
                      this.errMsgPnl[this.prevData[c].colId] = "             Note: " +this.prevData[c].errorMsg;
                    }
                    this.errDisplayPnl[this.prevData[c].colId] = true;
                  }
                }
                  this.temparray=[];
                  for(let j=0;j<100;j++)
                  {
                    this.temparray.push("1212");
                  }
                  for(let i=0;i<this.prevData.length;i++)
                  {
                    if(this.prevData[i].subColValCode!=null)
                    {
                      this.temparray[this.prevData[i].orderId]=this.prevData[i].subColValCode;
                    }
                  }
                 this.lhsCheck=[];
                 this.lhsNmCheck=[];
                 this.drpCheck=[];
                this.dropCheckQty=[];
                 this.drpCheckqtyy=[];
                 this.drpCheckqtyyKey=[];
                 this.inptCstQty=[];
                 this.costQty=[];
                 this.inputText = [];
                
                  for(let j=0;j<this.prevData.length;j++)
                  {
                    if(this.prevData[j].colType=="CHECKBOX")
                    {
                      this.lhsCheck[this.prevData[j].orderId]=true;
                      this.lhsNmCheck[this.prevData[j].orderId]=true;
                    
                    }
                    if(this.prevData[j].colType=="DROPDOWNCHECKBOX")
                    {
                      this.drpCheck[this.prevData[j].colValCd]=true;
                      this.dropCheckQty[this.prevData[j].colValCd]=true;
                       this.drpCheckqtyy[this.prevData[j].colValCd]=this.prevData[j].rhsColQuantity;
                       this.drpCheckqtyyKey[this.prevData[j].orderId]=true;
                    }
                  }
                  for(let i=0;i<this.prevData.length;i++)
                  {
                     if(this.prevData[i].desItemName=="Multifunctional digital generator protection relay shall have following protections features")
                     {
                      this.newPanelval[this.prevData[i].orderId]=this.prevData[i].colValCd;
                     }
                  }
                  this.itemcost=0;
                  this.itemcost=this.prevData[0].itemCost;
                  for(let i=0;i<this.prevData.length;i++)
                  {
                     if(this.prevData[i].rhsCost>0 && this.prevData[i].rhsCost!=null && this.prevData[i].inputCostFlag==1 )
                     {
                     this.inptCstQty[this.prevData[i].orderId]=true;
                     this.costQty[this.prevData[i].orderId]=this.prevData[i].rhsCost;
                     }
                     if(this.prevData[i].colType == "INPUTERRORBOX"){
                       this.inputText[this.prevData[i].orderId] = this.prevData[i].rhsColTechcomments;
                     }
                  }
                  this.panelapplicable=true;
                  this.panelapplicablescm=true; 
 this.Ifapplicabletemp=[];

                  for(let i=0;i<this.prevData.length;i++)
                  {
                     if(this.prevData[i].itemApproxCostFlag==2)
                     {
                      this.panelapplicable=false;
                      this.Ifapplicabletemp.push(this.prevData[i].itemErrMessage);
                     }
                  }
                  for(let i=0;i<this.prevData.length;i++)
                  {
                     if(this.prevData[i].itemApproxCostFlag==1)
                     {
                      this.panelapplicablescm=false;
                      this.Ifapplicabletemp.push(this.prevData[i].itemErrMessage);

                     }
                  }
                  this.Ifapplicabletemp = this.Ifapplicabletemp.filter(n => n != null);

                  this.colNumber=[];
                  for(let ji=0;ji<this.prevData.length;ji++)
                  {
                    this.colNumber[this.prevData[ji].orderId]=this.prevData[ji].desColOrderId;
                  }
                  console.log(this.temparray);
                  temptechflag=[];
                  for(let j=0;j<this.prevData.length;j++)
                  {
                  if(this.prevData[j].techFlag==1)
                  {
                    temptechflag[this.prevData[j].orderId]=true;
              
                  }
              else
              {
                temptechflag[this.prevData[j].orderId]=false;
              
              }
                }

                }
           //pushing selected components data fromm storage to local variable
              this.dboEleAuxFullArray.push({
                counter:0,
                id: newArr[m].eleItemId,
                qty: newArr[m].quantity,
                discountPer: newArr[m].discountPer,
                componenet: newArr[m].itemName,
                defaultValues: this.defaultValues1,
                dboCost: newArr[m].basicCost,
                dboAddOnCost: newArr[m].addOnCost,
                techComments: newArr[m].techComments,
                comrComments: newArr[m].comrComments,
                techRemarks: newArr[m].subItemTechRemarks,
                comrRemarks: newArr[m].subItemComrRemarks,
                eleItemTechRemarks:  newArr[m].techRemarks,
                eleItemComrRemarks:  newArr[m].comrRemarks,
                additionalCost: newArr[m].newAddonCost,
                itemOthersList: this.itemOthersList,
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
                defaultValuesid:this.defaultValuesid1,  // ALI
                temparray:this.temparray,  // ALI
                errMsgRhsCost : this.errMsgRhsCost,
                errMsgRed: this.errMsgRed,
                errMsgBlue: this.errMsgBlue,
                errMsgGreen: this.errMsgGreen,
                errMsgPnl: this.errMsgPnl,
                errDisplayPnl: this.errDisplayPnl,
                defaulVales1:this.defaulVales1,
                newArryUpd:this.newArryUpd,
                lhsCheck: this.lhsCheck,
                drpCheck: this.drpCheck,
                dropCheckQty: this.dropCheckQty,
                drpCheckqtyy: this.drpCheckqtyy,
                drpCheckqtyyKey:this.drpCheckqtyyKey,
                lhsNmCheck:this.lhsNmCheck,
                newPanelval1:this.newPanelval,
                itemcost:this.itemcost,
                inptCstQty:this.inptCstQty,
                costQty: this.costQty,
                inputText: this.inputText,
                panelapplicable:this.panelapplicable,
                panelapplicablescm:this.panelapplicablescm,
                colNumber:  this.colNumber,
                Ifapplicable:this.Ifapplicabletemp,
                tempflagsave:temptechflag
                
              });
              this.addedClassList.push(newArr[m].itemName);
              console.log(this.dboEleAuxFullArray);
              let savedEle: Array<any> = [];
            }
          }
        }
      }
      if(this._ITOcustomerRequirementService.editFlag==true)
      {
    
      
          if(this.dboEleAuxFullArray[0].techComments!=null)
          {
            this.itemRemarksVal= this.dboEleAuxFullArray[0].techComments;
            this.othItmTechRemChk=true;
           this.itemtechRemarkCheck=true;
           this.itemRemarkDiv=true;
          }
          if(this.dboEleAuxFullArray[0].comrComments!=null)
          {
            this.itemCmrRemarksVal= this.dboEleAuxFullArray[0].comrComments;
            this.othItmComrRemChk=true;
            this.itemComrRemarkCheck=true;
            this.itemComrRemarkDiv=true;
          }
          if(this.dboEleAuxFullArray[0].techRemarks!=null)
          {
            this.subItemRmkValOut= this.dboEleAuxFullArray[0].techRemarks;
            this.othSubItmTechRemChk=true;
            this.subItemTchRem=true;
            this.subItemRemarkDiv=true;
          }
          if(this.dboEleAuxFullArray[0].comrRemarks!=null)
          {
            this.subItemCmrRemValOut= this.dboEleAuxFullArray[0].comrRemarks;
            this.othSubItmComrRemChk=true;
            this.subItemComRem=true;
            this.subItemComrRemDiv=true;
          }       
    }
    // getting data from edit flow item creation (others)
   
    if(this._ITOeditQoutService.dboEleItmOthers.length != 0){
      console.log(this._ITOeditQoutService.dboEleItmOthers);
      this.othersCheck = true;
      this.displayItemCompOthTable = true;
      this.itemOthersAddonListTemp = this._ITOeditQoutService.dboEleItmOthers.reduce((acc, current) => {
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
      this.dboClass.desSubItemId=null;
      this.dboClass.desSubItemName=null;
      this.dboClass.subItemId = null;
      this.dboClass.subItemName = null;
      this.dboClass.colId = null;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = null;
      this.dboClass.subColValCode=null;
      this.dboClass.quantity = this.itemOthersAddonListTemp[z].quantity;
       if(this.itemOthersAddonListTemp[z].basicCost != 0){
        this.dboClass.cost = this.itemOthersAddonListTemp[z].basicCost;
      }
      this.dboClass.techRemarks = this.itemOthersAddonListTemp[z].techRemarks;
      this.dboClass.comrRemarks = this.itemOthersAddonListTemp[z].comrRemarks;
      this.dboClass.addOnNewColFlag = 1;
      this.dboClass.dispInd=1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
      this.itemOthersAddonList.push(this.dboClass);
     }     
      this.lhsRhsItemsListTemp = this._ITOeditQoutService.dboEleItmOthers;
      for(let p=0; p<this.lhsRhsItemsListTemp.length; p++){
        this.dboClass = new dboClass();
        this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        this.dboClass.itemId = null;
        this.dboClass.itemName = this.lhsRhsItemsListTemp[p].itemName; 
        this.dboClass.desSubItemId=null;
        this.dboClass.desSubItemName=null;
        this.dboClass.subItemId = null;
        this.dboClass.subItemName = null;
        this.dboClass.colId = this.lhsRhsItemsListTemp[p].colId;
        this.dboClass.colNm = this.lhsRhsItemsListTemp[p].colNm;
        this.dboClass.colValCd = this.lhsRhsItemsListTemp[p].colValCd;
        this.dboClass.subColValCode=null;
        this.dboClass.quantity = this.lhsRhsItemsListTemp[p].quantity;
         if(this.lhsRhsItemsListTemp[p].basicCost != 0){
          this.dboClass.cost = this.lhsRhsItemsListTemp[p].basicCost;
        }
        this.dboClass.techRemarks = this.lhsRhsItemsListTemp[p].techRemarks;
        this.dboClass.comrRemarks = this.lhsRhsItemsListTemp[p].comrRemarks;
        this.dboClass.addOnNewColFlag = 1;
        this.dboClass.dispInd=1;
        this.dboClass.techFlag = 1;
        this.dboClass.comrFlag = 1;
        this.lhsRhsItemsList.push(this.dboClass);
       }   
    }
for(let j=0;j<100;j++)
{
  this.temparray.push("1212");
}
    })
    });
    
  }

  ngOnInit() { }

  
  //filtering array so that null values will be removed
  ngAfterContentChecked() {
    
  }
  ngAfterViewChecked() {
  }
  // take min and max value
  /**
   * 
   * @param options dropdown value
   * @param selVal seleced option
   * @param i index of the drop down
   */
  defaultnewadd(selVal, options,value, z)
  {
    this.refreshBoolean=false;
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;    
    this.childvaluestemp=[];
    this.childvaluestemp2=[];
    this.newvalues=[];
    if(value==0)
    {
    this.childvaluestemp = this.childvalues.filter((x) => {
      return ((x.colNm ==selVal[0].quesDesc));
    })
  }
  else 
  {
    this.childvaluestemp=[];
  }
  this.defaultlhs=[];
for(let k=0;k<this.defaultValuesid.length;k++)
{
  this.defaultlhs[this.defaultValuesid[k]]=true;
}
      for(let j=0;j<this.defaultValues.length;j++)
      {
        for(let i=0;i<this.childvaluesnew.length;i++)
        {
          if(this.defaultValues[j]==this.childvaluesnew[i].colValCd && j+1==this.childvaluesnew[i].orderId && this.defaultlhs[this.childvaluesnew[i].orderId]==true    )
          {
            
                    this.newvalues.push(this.childvaluesnew[i]);
                 
            
          }
        }
      }
console.log(this.newvalues);
// for(let j=0;j<this.defaultValues.length;j++)
// {
//   for(let j=0;j<this.childvalues.length;j++)
// }
//     this.childvaluestemp2 = selVal.filter((x) => {
//       return ((x.colValCd !=undefined));
//     })
//     let countertemp=0;
//  for(let j=0;j<this.childvalues.length;j++)
//  {
//    if(this.childvalues[j].subColValName==options)
//    {
//      countertemp=1;
//    }
//  }
    
    this.SelectedExcelData=[];
    this.questionsBean2=[];
    this.questionsBean3=[];
    console.log(this.questionsBean1);
    console.log(this.questionsBean);
    if(this.questionsBean.length!=this.questionsBean1.length)
    {
      this.questionsBean3=this.questionsBean;
      for(let i=0;i<this.questionsBean1.length;i++)
      {
        let counter=0;
        for(let j=0;j<this.questionsBean.length;j++)
        {
          if(this.questionsBean[j].dropDownType.value==this.questionsBean1[i].dropDownType.value)
          {
            counter=1;
          }
        }
        if(counter==0)
        {
          this.questionsBean2.push(this.questionsBean1[i]);
        }
      }
      for(let j=0;j<this.questionsBean2.length;j++)
      {
       this.questionsBean3.push(this.questionsBean2[j]);
      }
    
    }
    else
    {
      this.questionsBean3=this.questionsBean;
    }
    console.log( this.questionsBean3);
    for (let l = 0; l < this.questionsBean3.length; l++) {
      this.dboClass = new dboClass();
      //this.questionsBean2=[];
    //  this.questionsBean3=[];
      //To push the selected dropdown values in the panel
      //OtherColValFlag and addoncostmeflag will be zero
      //As the default values will be set in the dropdown

    
      for (let d = 0; d < this.questionsBean3[l].dropDownValueList.length; d++) {
        if (this.questionsBean3[l].dropDownValueList[d].value == this.defaultValues[l] || this.questionsBean3[l].colType=="EDIT_TEXT" || this.questionsBean3[l].colType=="TEXT" ) {
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = this.itemId;
          if(this.subItemId == 0 || this.subItemId == null){
            this.dboClass.subItemId = 0;
          }else {
            this.dboClass.subItemId = this.subItemId;           
          } 
          this.dboClass.desItemId=this.questionsBean3[l].desItemId;
          this.dboClass.desItemName=null;
        
          this.dboClass.desSubItemId=this.questionsBean3[l].desSubItemId;
          this.dboClass.desSubItemName=null;
          this.dboClass.colId = this.questionsBean3[l].dropDownType.key;
          this.dboClass.colNm = null;
          if(value ==1)
          {
            console.log("back");
            if(value==1 &&  this.questionsBean3[l].dropDownType.value==selVal.colNm )
            {
              console.log("value 0");
              this.dboClass.colValCd = selVal.colValCd; 
            }
            else  if(value==1 &&  this.questionsBean3[l].dropDownType.value!=selVal.colNm )
            {
              this.dboClass.colValCd =  this.questionsBean3[l].dropDownValueList[d].code; 
              console.log("value 1");
           
            }
            else  if( this.questionsBean3[l].dropDownType.value==selVal[0].quesDesc && value==0)
            {
              console.log("value 2");
              this.dboClass.colValCd =  selVal[0].colValCd; 
           
            }
            let counter1=0;
// for(let j=0;j<this.newvalues.length;j++)
// {
 
//   if(this.questionsBean3[l].dropDownType.key==this.newvalues[j].colId)
//   {
//     counter1=1;
//     this.dboClass.subColValCode=this.newvalues[j].dropDownValueList[0];
//     break;
//   }
 
// }
// if(counter1==0)
// {
//   this.dboClass.subColValCode=null;
// }

          }
          else
          {
            console.log("next");
             if( this.questionsBean3[l].dropDownType.value==selVal[0].quesDesc && this.questionsBean3[l].orderId == z.orderId)
            {
              console.log("value 2");
              this.dboClass.colValCd = options; 
            //  this.dboClass.subColValCode=options;
           
            }
            else
            {
              this.dboClass.colValCd =  this.questionsBean3[l].dropDownValueList[d].code; 
            //  this.dboClass.subColValCode=options;
            }

          }
          let counter1=0;
for(let j=0;j<this.newvalues.length;j++)
{
 
  if(this.questionsBean3[l].dropDownType.key==this.newvalues[j].colId)
  {
    counter1=1;
    this.dboClass.subColValCode=this.newvalues[j].dropDownValueList[0];
    break;
  }
 
}
if(counter1==0)
{
  this.dboClass.subColValCode=null;
}
          if(value==1 &&  this.questionsBean3[l].dropDownType.value==selVal.colNm)
          {
            this.dboClass.subColValCode=options;
          }
          // else if(value==1)
          // {
          //   if(this.dboClass.subColValCode==null)
          //   {
          //     this.dboClass.subColValCode=null;
          //   }
            
          // }
         
          this.dboClass.quantity = null;          
          this.dboClass.cost = null;          
          this.dboClass.techRemarks = null;
          this.dboClass.comrRemarks = null;
          this.dboClass.addOnNewColFlag = 0;
          this.dboClass.orderId= this.questionsBean3[l].orderId;
            this.dboClass.defaultFlagNew=1;
          this.dboClass.dispInd = this.questionsBean3[l].dispInd;
          this.dboClass.techFlag =   this.questionsBean3[l].dropDownValueList[d].techFlag;
          this.dboClass.comrFlag = 1;
          this.SelectedExcelData.push(this.dboClass);
          
        }
      }
    }
   
    console.log( this.SelectedExcelData);
    this.dboFormData.eleRefreshTechData= this.SelectedExcelData;
    this.dboFormData.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById;
    this._DboEleAuxialriesService.refreshEle(this.dboFormData).subscribe(respons1 => {
        console.log(respons1);
        //this.questionsBean1=respons1.eleRefreshTechList;
       this.questionsBean=[];

        respons1.eleRefreshTechList = respons1.eleRefreshTechList.filter((x) => {
        return ((x.itemId ==this.itemId && x.subItemId ==this.subItemId ));
      })
  for(let j=0;j<respons1.eleRefreshTechList.length;j++)
  {
    if(respons1.eleRefreshTechList[j].techFlag==1)
    {
      this.techFlagindicator[respons1.eleRefreshTechList[j].orderId]=true;

    }
else
{
  this.techFlagindicator[respons1.eleRefreshTechList[j].orderId]=false;

}
    for(let i=0;i<this.questionsBean1.length;i++)
    {
     
     if(respons1.eleRefreshTechList[j].colNm==this.questionsBean1[i].dropDownType.value && respons1.eleRefreshTechList[j].orderId==this.questionsBean1[i].orderId)
     {
      if(respons1.eleRefreshTechList[j].desColOrderId==null)
      {
       this.colNumber[respons1.eleRefreshTechList[j].orderId]="0";

      }else
      {
       this.colNumber[respons1.eleRefreshTechList[j].orderId]=respons1.eleRefreshTechList[j].desColOrderId;

      }
      this.questionsBean1[i].dispInd=respons1.eleRefreshTechList[j].dispInd;
      //this.questionsBean1[i].desColOrderId=respons1.eleRefreshTechList[j].desColOrderId;
      console.log(respons1.eleRefreshTechList[j].desColOrderId);
     }
    }
  }
  console.log(  this.questionsBean1);
        for(let k = 0; k < this.questionsBean1.length; k++){
  
      this.questionsBean.push(this.questionsBean1[k]);
  
  }
  console.log(  this.questionsBean1);
  let questionsBean123=[];
  for(let k = 0; k < this.questionsBean1.length; k++){
if(this.questionsBean1[k].dispInd == 0){
questionsBean123.push(this.questionsBean1[k]);
}
console.log(questionsBean123);
}
  this.defaultValues=[];
  // for (let l = 0; l < this.questionsBean.length; l++) {
  //   if(this.questionsBean[l].dropDownType.value==selVal[0].quesDesc && this.questionsBean[l].orderId==selVal[0].orderId)
  //   {
  //     console.log("world");
  //     for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
  //       //  this.openOth[l] = false;
        
  //       this.questionsBean[l].dropDownValueList[d].defaultFlag=false;
  //       if(   this.questionsBean[l].dropDownValueList[d].value==options  )
  //       {
  //         console.log("check");
  //         this.questionsBean[l].dropDownValueList[d].defaultFlag=true;
  //       }
      
          
  //       }
  //   }
   
  // }
  for(let k=0;k<this.questionsBean.length;k++)
  {
for(let j=0;j< respons1.eleRefreshTechList.length;j++)
{
  if(this.questionsBean[k].orderId== respons1.eleRefreshTechList[j].orderId)
  {
    console.log("testtemp");

    for(let p=0;p<this.questionsBean[k].dropDownValueList.length;p++)
    {
      console.log("what");
      if(this.questionsBean[k].dropDownValueList[p].value==respons1.eleRefreshTechList[j].colValCd || respons1.eleRefreshTechList[j].colType=="EDIT_TEXT" ||  respons1.eleRefreshTechList[j].colType=="TEXT")
      {
        console.log("answer");
this.questionsBean[k].dropDownValueList[p].defaultFlag=true;
this.questionsBean[k].dropDownValueList[p].techFlag=respons1.eleRefreshTechList[j].techFlag;
      }
      else
      {
        this.questionsBean[k].dropDownValueList[p].defaultFlag=false;

      }
    }
  }
}
  }
  console.log( this.questionsBean);

  // for (let l = 0; l < this.questionsBean.length; l++) {
  //             for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
  //             //  this.openOth[l] = false;
  //               if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
  //                 this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
  //               }
  //             }
  //           }
  this.defaultValues1=[];
  for(let j=0;j<300;j++)
  {
    this.defaultValues1.push("12$7");
  }
  
  for(let j=0;j<this.questionsBean.length;j++)

  {
   
      for(let i=0;i<this.questionsBean[j].dropDownValueList.length;i++)
      {
        if(this.questionsBean[j].dropDownValueList[i].defaultFlag==true || this.questionsBean[j].colType=="CHECKBOX"  )
        {
          this.defaultValues1[j]=this.questionsBean[j].dropDownValueList[i].value;
        }
     
      
    }

  }
  console.log( this.defaultValues1);

         for(let k = 0; k < respons1.eleRefreshTechList.length; k++){
if(respons1.eleRefreshTechList[k].colType!="EDIT_TEXT")
{


    this.defaultValues1[respons1.eleRefreshTechList[k].orderId-1]=respons1.eleRefreshTechList[k].colValCd;

}
  } 
  console.log( this.defaultValues1);
 
  for(let j=0;j<this.defaultValues1.length;j++)
  {
    if(this.defaultValues1[j]!="12$7")
    {
      this.defaultValues.push(this.defaultValues1[j]);
    }
  }
  //this.questionsBean=  this.questionsBean2
  console.log( this.questionsBean);
  console.log(this.defaultValues);
  this.defaultValuesid=[];
  for(let k = 0; k < respons1.eleRefreshTechList.length; k++){
if( respons1.eleRefreshTechList[k].dispInd==1)
{
  this.defaultValuesid.push(respons1.eleRefreshTechList[k].orderId);
}
    
    
  }
  this.groupNameNew=[];
  // for(let j=0;j< this.questionsBean1.length;j++)
  // {
  //     this.groupName.push(this.questionsBean1[j].desItemName);
  // }
//   var unique = this.groupName.filter(function(elem, index, self) {
//     return index === self.indexOf(elem);          
// })
// this.groupNameNew = this.questionsBean1.reduce((acc, current) => {
//   console.log(acc, current);
//   const x = acc.find(item => item.desItemName === current.desItemName);
//   if (!x) {
//     return acc.concat([current]);
//   } else {
//     return acc;
//   } 
// }, []);
//this.displayPanel=[];
// for(let j=0;j<this.groupNameNew.length;j++)
// {

// let counter=0;
// for(let i=0;i<this.questionsBean1.length;i++)
// {
//  if(this.groupNameNew[j].desItemName==this.questionsBean1[i].desItemName && this.questionsBean1[i].desSubItemName!="NULL" && this.questionsBean1[i].desSubItemName!=null  )
//  {
//    counter=counter+1;

//  }
// }
// if(counter==0)
// {
//   this.displayPanel[this.groupNameNew[j].desItemId]=true;
// }
// }
//   this.groupNameNew2=[];
// for(let j=0;j<this.groupNameNew.length;j++)
// {
//  let temp_name=[];
//  let temp2=[];
//  for(let i=0;i<this.questionsBean1.length;i++)
//  {
//    if(this.groupNameNew[j].desItemName==this.questionsBean1[i].desItemName)
//    {
//      temp_name.push(this.questionsBean1[i]);
//    }
//    if(temp_name!=null)
//    {
//     temp_name = temp_name.reduce((acc, current) => {
//       console.log(acc, current);
//       const x = acc.find(item => item.desSubItemId === current.desSubItemId);
//       if (!x) {
//         return acc.concat([current]);
//       } else {
//         return acc;
//       } 
//     }, []);
 
//    }

//  }
// }
// this.groupNameNew2=[];
// this.groupNameNew2 = this.questionsBean1.reduce((acc, current) => {
//   console.log(acc, current);
//   const x = acc.find(item => item.desSubItemId === current.desSubItemId);
//   if (!x) {
//     return acc.concat([current]);
//   } else {
//     return acc;
//   } 
// }, []);

// if(temp==false)
// {
//   this.groupNameNew2 =  this.groupNameNew2.filter((x) => {
//     return ((x.desSubItemId !=0 ));
//     })
// }

  //this.defaultValues=[];
  // for (let l = 0; l < this.questionsBean.length; l++) {
  //             for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
  //             //  this.openOth[l] = false;
  //               if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
  //                 this.defaultValues2.push(this.questionsBean[l].dropDownValueList[d].value);
  //               }
  //             }
  //           }
  //           this.defaultValues=this.defaultValues2;
  //           console.log(   this.defaultValues);
  this.groupName=[];
      this.groupNameNew=[];
      for(let j=0;j< this.questionsBean1.length;j++)
      {
          this.groupName.push(this.questionsBean1[j].desItemName);
      }
    //   var unique = this.groupName.filter(function(elem, index, self) {
    //     return index === self.indexOf(elem);          
    // })
    this.groupNameNew = this.questionsBean1.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.desItemName === current.desItemName);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    this.displayPanel=[];
    for(let j=0;j<this.groupNameNew.length;j++)
  {
   
 let counter=0;
   for(let i=0;i<this.questionsBean1.length;i++)
   {
     if(this.groupNameNew[j].desItemName==this.questionsBean1[i].desItemName && (this.questionsBean1[i].desSubItemName=="NULL" || this.questionsBean1[i].desSubItemName==null ) )
     {
       counter=counter+1;
    
     }
    }
    if(counter>0)
    {

      this.displayPanel[this.groupNameNew[j].desItemId]=true;
    }
  }
  //   this.groupNameNew2=[];
  // for(let j=0;j<this.groupNameNew.length;j++)
  // {
  //  let temp_name=[];
  //  let temp2=[];
  //  for(let i=0;i<this.questionsBean1.length;i++)
  //  {
  //    if(this.groupNameNew[j].desItemName==this.questionsBean1[i].desItemName)
  //    {
  //      temp_name.push(this.questionsBean1[i]);
  //    }
  //    if(temp_name!=null)
  //    {
  //     temp_name = temp_name.reduce((acc, current) => {
  //       console.log(acc, current);
  //       const x = acc.find(item => item.desSubItemId === current.desSubItemId);
  //       if (!x) {
  //         return acc.concat([current]);
  //       } else {
  //         return acc;
  //       } 
  //     }, []);
     
  //    }
    
  //  }
  // }
    this.groupNameNew2 = this.questionsBean1.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.desSubItemId === current.desSubItemId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    let temp=false;

// for(let k=0;k<this.groupNameNew.length;k++)
// {
//   if(this.displayPanel[this.groupNameNew[k].desItemId]!=true)
//   {
//     this.tempboolean[this.groupNameNew[k].desItemId]=true
//   }
// }
///this.tempboolean=true;

  
    this.groupNameNew2 =  this.groupNameNew2.filter((x) => {
      return ((x.desSubItemId !=0 ));
      })
      for(let j=0;j<this.groupNameNew2.length;j++)
      {
        for(let k=0;k<this.groupNameNew.length;k++)
        {
          if(this.groupNameNew2[j].desItemId==this.groupNameNew[k].desItemId)
          {
            if(this.displayPanel[this.groupNameNew[k].desItemId]==true)
            {

              this.tempboolean[this.groupNameNew2[j].desItemId]=true;
            }
          }
        }
      }
      this.deItemLevelOthers=[];
      if( this.groupNameNew2.length!=0)
      {
        for(let j=0;j<this.groupNameNew.length;j++)
      {
        
        for(let k=0;k<this.groupNameNew2.length;k++)
        {
      if(this.groupNameNew[j].desItemName==this.groupNameNew2[k].desItemName)
      {
       this.deItemLevelOthers[this.groupNameNew[j].desItemId]=true;
      }
        }
      }
      
      }
      this.refreshBoolean=true;

    });
   
  
        // this.tempform=respons1.eleRefreshTechList;
       
        //  this.questionsBean1=[];
        //  
        // for(let j=0;j<this.questionsBean.length;j++)
        // {
        //   for(let i=0;i<this.questionsBean[j].dropDownValueList.length;i++)
        //   {
        //     for(let k=0;k<this.questionsBean[j].dropDownValueList[i].length;k++)
        //     {
        //       if(this.questionsBean[j].dropDownValueList[i].value==options)
        //       {
             
        //       }
        //     }
        //   }
        // }
  //       if(options=="CACA")
  //       {
  //         this.questionsBean=[];
  //         this.defaultValues=[];
  //         this.questionsBean1[31].dispInd=0;
  //         this.questionsBean1[32].dispInd=0;
  //         this.questionsBean1[33].dispInd=0;
  //         this.questionsBean1[34].dispInd=0;
  
  // for(let k = 0; k < this.questionsBean1.length; k++){
  //   if(this.questionsBean1[k].dispInd == 1){
  //     this.questionsBean.push(this.questionsBean1[k]);
  //   }
  // }
  //         for (let l = 0; l < this.questionsBean.length; l++) {
  //           for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
  //           //  this.openOth[l] = false;
  //             if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
  //               this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
  //             }
  //           }
  //         }
          
  //       }
  //       else
  //       {
  //         this.questionsBean=[];
  //         this.defaultValues=[];
  //         this.questionsBean1[31].dispInd=1;
  //         this.questionsBean1[32].dispInd=1;
  //         this.questionsBean1[33].dispInd=1;
  //         this.questionsBean1[34].dispInd=1;
   
  // for(let k = 0; k < this.questionsBean1.length; k++){
  //   if(this.questionsBean1[k].dispInd == 1){
  //     this.questionsBean.push(this.questionsBean1[k]);
  //   }
  // }
  //         for (let l = 0; l < this.questionsBean.length; l++) {
  //           for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
  //           //  this.openOth[l] = false;
  //             if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
  //               this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
  //             }
  //           }
  //         }
  //       }
     
        // for(let j=0;j< this.questionsBean.length;j++)
        //   {
          
        //       this.groupName.push(this.dboElePanelList2[j].subItemName);
           
        //   }
        //   var unique = this.groupName.filter(function(elem, index, self) {
        //     return index === self.indexOf(elem);          
        // })
    
        // this.groupNameNew = unique;
    
        // for(let k = 0; k < this.questionsBean.length; k++){
        //   if(this.questionsBean[k].dispInd == 1){
        //     this.questionsBean.push(this.questionsBean1[k]);
        //   }
        // }
        
        // console.log(this.questionsBean);
    //     this.defaultValues = [];
    //        //to display dropdown default values in ui screen
    //       for (let l = 0; l < this.questionsBean.length; l++) {
    //         for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
    //         //  this.openOth[l] = false;
    //           if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
    //             this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
    //           }
    //         }
    //       }
    //       for(let j=0;j< this.questionsBean1.length;j++)
    //       {
          
    //           this.groupName.push(this.dboElePanelList2[j].subItemName);
           
    //       }
    //       var unique = this.groupName.filter(function(elem, index, self) {
    //         return index === self.indexOf(elem);          
    //     })
    
    //     this.groupNameNew = unique;
        
    //     }
    // });
  
  
  }
  optionSel(selVal, options, z) {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if(this.itemCode=="ALT" || this.itemCode=="BBC" || this.itemCode=="AVR" || this.itemCode=="LSP"|| this.itemCode=="GRP"|| this.itemCode=="GACB"|| this.itemCode=="HVGCB"|| this.itemCode=="TCP"|| this.itemCode=="MCC" || this.itemCode=="HVB"|| this.itemCode=="LVB"|| this.itemCode=="HVC"|| this.itemCode=="LT_CAB"|| this.itemCode=="IC"|| this.itemCode=="GCP"|| this.itemCode=="ACB"|| this.itemCode=="MCSP"|| this.itemCode=="TGP")    {
    this.defaultnewadd(selVal, options,0, z)
    }
    //let tempcounter=0;
  //  for(let j=0;j<this.childvaluesnew.length;j++)
  if(z.colType == "INPUTERRORBOX"){
    for(let p=0; p<selVal.length; p++){
      if(options == selVal[p].value){
        this.inputText[z.orderId] = "";
        console.log(this.inputText[z.orderId]);
      }
    }
  }
  
 for(let k=0; k<selVal.length; k++){
   if(options == selVal[k].value && selVal[k].inputCostFlag == 1){
    
    this.inptCstQty[z.orderId] = true;
    this.costQty[z.orderId] = 0;
    console.log(this.costQty[z.orderId]);
   }
   if(options == selVal[k].value && selVal[k].inputCostFlag == 0){
    this.inptCstQty[z.orderId] = false;
     this.costQty[z.orderId] = 0;
   }
 }

}

costCheckQty(valueee, quesBeanRec){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(valueee, quesBeanRec);  
      this.costQty[quesBeanRec.orderId] = valueee;
}

inpCheckTxt(valu, quesBnRec){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(valu, quesBnRec);  
      this.inputText[quesBnRec.orderId] = valu;
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
  this.OverWrittenfinalEleAuxCost = 0;
}
 //to save in localstorage
 saveInLocal(key, val): void {
  console.log('recieved= key:' + key + 'value:' + val);
  this.storage.set(key, val);
  this.dboElectricalAuxLocal[key] = this.storage.get(key);
}

// take min and max value
  /**
   * 
   * @param options dropdown value
   * @param selVal seleced option
   * @param i index of the drop down
   */
  optionSelItem(options, selVal, i) {
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

  getELEItemsList(){
    console.log(this.dboFormData);
    this._DboEleAuxialriesService.getEleItems(this.dboFormData).subscribe(respon => {
      console.log(respon);
      this.dboEleItemList = respon.dboEleItemList;
      console.log(this.dboEleItemList);
       //filter dboEleFilterList
       this.newArrayItm = this.dboEleItemList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.itemName === current.itemName);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      console.log(this.newArrayItm);
    })
  }
  getFilterData()
  {
    // QuotId
    // ItemId
    // EleSpecialId
    // ColValCd
    // ItemApplInd
    // ModifiedById
    if(this.dboEleAuxFullArray.length>0)
    {
    for( let j=0;j<this.dboEleAuxFullArray.length;j++)
    {
      if(this.dboEleAuxFullArray[j].itemId==this.itemId && this.dboEleAuxFullArray[j].subItemId==this.subItemId)
      {
        this.dboEleAuxFullArray.splice(j,1);
      }
    }
  }
  if (this.itemIdList.includes(this.itemId)) {
    this.itemIdList[this.itemIdList.indexOf(this.itemId)] = null;
    for(let x=0;x<this.itemIdList.length;x++)
    {
      if (this.itemIdList.includes(this.itemId)) {
        this.itemIdList[this.itemIdList.indexOf(this.itemId)] = null;
      }
    }
    this.selectdEl[this.selectedELIndex]=false;

  }
  for(let k = 0; k < this.addOnList.length; k++){
    if(this.addOnList[k].itemId == this.itemId && this.addOnList[k].subItemId==this.subItemId){
        this.addOnList[k] = null;
     
    }
  }
  this.addOnList = this.addOnList.filter(n => n != null);
  for(let x=0;x<this.subItemOthAddonList.length;x++)
  {
    if(this.itemId==this.subItemOthAddonList[x].itemId && this.subItemId==this.subItemOthAddonList[x].subItemId)
    {
      this.subItemOthAddonList[x]=null;  
    }
  }
  for(let x=0;x<this.lhsRhsSubItemsList.length;x++)
  {
    if(this.itemId==this.lhsRhsSubItemsList[x].itemId && this.subItemId==this.lhsRhsSubItemsList[x].subItemId)
    {
      this.lhsRhsSubItemsList[x]=null;  
    }
  }
  this.subItemOthAddonList = this.subItemOthAddonList.filter(n => n != null);
  this.lhsRhsSubItemsList = this.lhsRhsSubItemsList.filter(n => n != null);
  for(let x=0;x<this.dessubItemOthAddonList.length;x++)
      {
        if(this.itemId==this.dessubItemOthAddonList[x].itemId && this.subItemId==this.dessubItemOthAddonList[x].subItemId )
        {
          this.dessubItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsDesSubItemsList.length;x++)
      {
        if(this.itemId==this.lhsRhsDesSubItemsList[x].itemId && this.subItemId==this.lhsRhsDesSubItemsList[x].subItemId )
        {
          this.lhsRhsDesSubItemsList[x]=null;  
        }
      }
      this.dessubItemOthAddonList = this.dessubItemOthAddonList.filter(n => n != null);
      this.lhsRhsDesSubItemsList = this.lhsRhsDesSubItemsList.filter(n => n != null);

    
    this.dboFormData.itemId = this.itemId;
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.eleSpecialId = this.newArryUpd[0].eleSpecialId;
    this.dboFormData.colValCd = this.newArryUpd[0].colValCd;
    this.dboFormData.itemApplInd = this.newArryUpd[0].itemApplInd;
    this._DboEleAuxialriesService.saveEleFilter(this.dboFormData).subscribe(res => {
      console.log(res);
      if(res.successCode==0)
      {
     this.enablebutton=false;
        this.dboEleSel( this.tempArray, this.itemName,this.selectedELIndex,  this.enablebutton,"abc");
      }
    });
   
  }
  //On click of item Name
  optionSelNew(colvalcd,item)
  {
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  
   
    for(let j=0;j<this.dboEleFilterListNew.length;j++)
    {
      if(this.dboEleFilterListNew[j].colNm==item.colNm && this.dboEleFilterListNew[j].colValCd==colvalcd)
      {
       for(let i=0;i<this.newArray12.length;i++)
       {
         if(this.newArryUpd[i].colNm==item.colNm)
         {
          this.newArryUpd[i]=this.dboEleFilterListNew[j];
          break;
         }
       }
      }
    }
  }
  dboEleSel(d,nm,i,enable,value){
    this.rhs=[];
    this.newAddNameO=[];
    this.newAddCostO=[];
    this.newAddRemrkO=[];
    this.refreshBoolean=true;
    this.subItemButton=true;
    this.techFlagindicator=[];
    this.discountPer=0;
    this.qty=1;
    this.itemCode=d.itemCd
    this.getPrice=true;
    this.disablelhs= [];
this.disablelhssub = [];


this.lhsdes= [];
this.rhsdes= [];
this.lhsdes2= [];
this.rhsdes2= [];
    this.diableitemname=false;
    this.Ifapplicable=[];
    this.deItemLevelOthers=[];
    this.displaySubItemOthTable=false;
    this.othersSubCheck=false;
    this.othersSubItemCheck=false;
    this.displayDesSubItemOthTable=false;
    this.othersDesSubCheck=false;
    this.othersDesSubItemCheck=false;
    this.saveBtColor=true;
    this.linkFlag=d.linkFlag;
    this.lhsCheck=[];
    this.lhsNmCheck=[];
    this.drpCheck=[];
    this.dropCheckQty=[];
     this.drpCheckqtyy=[];
     this.drpCheckqtyyKey=[];

    this.techCheckIn=false;
    this.itemTechRmkDiv=false;
    this.displayCompOthTable=[];
    this.itemComrRmkDiv=false;
    this.comrrCheck=false;
    this.itemcost=0;
    this.eleItemTechRemarks=null;
    this.eleItemComrRemarks=null;
    this.inptCstQty = [];
    this.addonflg="0";
    this.dboFormData.itemId = d.itemId;
    this.newPanelval=[];
    //this.dboFormData.subItemId = d.subItemId;
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.questionsBean=[];
    this.enablebutton=enable;
   for(let k=0;k<this.lhsRhsSubItemsList.length;k++)
   {
this.rhsdes2[k]=false;
   }
   
   for(let k=0;k<this.lhsRhsDesSubItemsList.length;k++)
   {
this.rhsdes[k]=false;
   }
    if(enable==true)
    {
      this.defaulVales1=[];
    this._DboEleAuxialriesService.eleFilter(this.dboFormData).subscribe(res => {
      if(res.successCode==0)
      {
     
        this.dboEleFilterListNew=res.dboEleSpecialFilterList;
        for(let j=0;j< res.dboEleSpecialFilterList.length;j++)
        {
          if(res.dboEleSpecialFilterList[j].defaultFlag==true)
          {
          this.defaulVales1.push(res.dboEleSpecialFilterList[j].colValCd);
          }
        }
      }
      this.newArray12 =this.dboEleFilterListNew.filter((x) => {
        return ((x.defaultFlag ==true));
      })
      this.newArryUpd=this.dboEleFilterListNew.filter((x) => {
        return ((x.defaultFlag ==true));
      })
      if(this.dboEleAuxFullArray.length>0)
      {
      for( let j=0;j<this.dboEleAuxFullArray.length;j++)
      {
        if(this.dboEleAuxFullArray[j].itemId==this.itemId && this.dboEleAuxFullArray[j].subItemId==this.subItemId)
        {
          this.newArray12=[];
  this.newArryUpd=[];
          enable=false;
          this.enablebutton=enable;
         this.defaulVales1=this.dboEleAuxFullArray[j].defaulVales1;
        for(let j=0;j<res.dboEleSpecialFilterList.length;j++)
        {
if(res.dboEleSpecialFilterList[j].colValCd==this.defaulVales1[0])
{
  this.newArray12.push(res.dboEleSpecialFilterList[j]);
  this.newArryUpd.push(res.dboEleSpecialFilterList[j]);
  break;
        }
        
        }
      }
    }
  }
  this.diableitemname=true;

      console.log(res);});
    }
   

    this.dboFormData.itemId = d.itemId;
    this.dboFormData.subItemId = d.subItemId;
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    if(this.dboFormData.subItemId != 0 && this.iFlag == 0)
    {
      if(nm==d.itemName)
      {

      
      if(this.selectdEl1.includes(true))
{
  this.subItemButton=false;      

}
      }
      else if(nm==d.subItemName)
      {
        this.subItemButton=true;      

      }
       for (let k = 0; k < this.newArrayItm.length; k++)
       {
         if(d.itemName == this.newArrayItm[k].itemName)
         {
           this.iItemindex = k;   
           break;
         }
       }
    }  
 
  //   if(this.dboEleAuxFullArray.length>0)
  //   {
  //   for( let j=0;j<this.dboEleAuxFullArray.length;j++)
  //   {
  //     if(this.dboEleAuxFullArray[j].itemId==this.itemId && this.dboEleAuxFullArray[j].subItemId==this.subItemId)
  //     {
  //       this.enablebutton=false;
  //     }
  //   }
  // }
    this.tempArray=d;
    
    this.errMsgRhsCost=[];
    this.errMsgRed =[];
    this.errMsgBlue =[];
    this.errMsgGreen =[];
    this.errMsgPnl=[];
    this.errDisplayPnl=[];
    this.temparray=[];
    this.footerText = [];
    this.headerText = [];
    if(d.headerText != "NULL"){
    this.headerText = d.headerText;
    }
    if(d.footerText != "NULL"){
    this.footerText = d.footerText;
    }
    for(let j=0;j<100;j++)
    {
      this.temparray.push("1212");
    }
    
    this.enablebuttonNew=d.enabled;
    this.openOth=[];
    this.openOth1=[];
    this.addOnCheck = [];
    console.log(this.selectdEl[i]);
    this.eleItemId = d.eleItemId;
    this.itemId = d.itemId;
    this.itemName = d.itemName
    this.subItemId = d.subItemId;
    this.subItemName = d.subItemName;
    if(this.subItemName!=null)
    {
      this.itemNameCurrent=this.subItemName;
    }
    else
    {
      this.itemNameCurrent=this.itemName;

    }
    this.othersCompCheck=[];
    this.selectedELIndex=i;
    console.log(this.selectedELIndex);
    this.defaultValues = [];
    this.questionsBean = [];
    this.itemOthersList = [];
    this.dboCost = 0;
    this.dboAddOnCost = 0;
    console.log(d,nm, i);
    if(this.addOnList.length!=0)
    {
    for(let j=0;j<this.addOnList.length;j++)
    {
      if(this.addOnList[j].itemId==this.itemId && this.addOnList[j].subItemId==this.subItemId)
      {
        this.openOth[this.addOnList[j].colId]=true;
        this.openOth1[this.addOnList[j].colId]=false;
        this.rhs[this.addOnList[j].colId]=true;
        this.itemRemarkaddon[this.addOnList[j].colId]=true;
        this.addOnCheck[this.addOnList[j].colId]=true;
        this.newAddNameO[this.addOnList[j].colId]=this.addOnList[j].colValCd;
        this.newAddCostO[this.addOnList[j].colId]=this.addOnList[j].cost;
        this.newAddRemrkO[this.addOnList[j].colId]=this.addOnList[j].comrRemarks;
      }
    }
  }
    if(nm == d.itemName){
      this.subList1=[];
      this.sublistDup =[];
        for (let s = 0; s < this.dboEleItemList.length; s++) {  
              
          if(d.itemId == this.dboEleItemList[s].itemId && this.dboEleItemList[s].subItemName!=null){
            this.sublistDup.push(this.dboEleItemList[s]);
          
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
      }
        console.log(this.subList1);
      }      
      //To display subitem list and panel with drop down values
      if(this.subList1.length>0){
        this.itemName = nm;
        this.displayEleSubList = true;
      }else{
        this.displayEle = true;
        this.displayEleSubList = false;
      }
    }
    //To display panel dropdown list on click subitem list name
    if(nm == d.subItemName){
      this.displayEleSubList = true;
      this.displayEle = true;             
    }
      if(value!="abc" || this.subItemName!="LT Power Cable" )
{
    this._DboEleAuxialriesService.getElePanels(this.dboFormData).subscribe(respons => {

      console.log(respons);
      console.log(respons.dboElePanelList2);
     
      this.questionsBean1 = respons.questionsBean;
      this.dboElePanelList1 = respons.dboElePanelList1;
      this.dboElePanelList2 = respons.dboElePanelList2;
      this.colNumber=[];
      for(let j=0;j<this.questionsBean1.length;j++)
      {
        if(this.questionsBean1[j].desColOrderId==null)
        {
          this.colNumber[this.questionsBean1[j].orderId]=0;

        }
        else
        {
          this.colNumber[this.questionsBean1[j].orderId]=this.questionsBean1[j].desColOrderId;

        }
      }
     

      // for(let j=0;j<this.childvalues.length;j++)
      // {
      //   for(let i=0;i<this.childvalues.length;i++)
      //   {
      //     if(this.childvalues[j].colNm==this.childvalues[i].colNm)
      //     {
            
      //     }
      //   }
      // }
    
     
      console.log(this.childvalues);
    


      this.groupName=[];
      this.groupNameNew=[];
      for(let j=0;j< this.questionsBean1.length;j++)
      {
          this.groupName.push(this.questionsBean1[j].desItemName);
      }
    //   var unique = this.groupName.filter(function(elem, index, self) {
    //     return index === self.indexOf(elem);          
    // })
    this.groupNameNew = this.questionsBean1.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.desItemName === current.desItemName);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    this.displayPanel=[];
    for(let j=0;j<this.groupNameNew.length;j++)
  {
   
 let counter=0;
   for(let i=0;i<this.questionsBean1.length;i++)
   {
     if(this.groupNameNew[j].desItemName==this.questionsBean1[i].desItemName && (this.questionsBean1[i].desSubItemName=="NULL" || this.questionsBean1[i].desSubItemName==null ) )
     {
       counter=counter+1;
    
     }
    }
    if(counter>0)
    {

      this.displayPanel[this.groupNameNew[j].desItemId]=true;
    }
  }
  //   this.groupNameNew2=[];
  // for(let j=0;j<this.groupNameNew.length;j++)
  // {
  //  let temp_name=[];
  //  let temp2=[];
  //  for(let i=0;i<this.questionsBean1.length;i++)
  //  {
  //    if(this.groupNameNew[j].desItemName==this.questionsBean1[i].desItemName)
  //    {
  //      temp_name.push(this.questionsBean1[i]);
  //    }
  //    if(temp_name!=null)
  //    {
  //     temp_name = temp_name.reduce((acc, current) => {
  //       console.log(acc, current);
  //       const x = acc.find(item => item.desSubItemId === current.desSubItemId);
  //       if (!x) {
  //         return acc.concat([current]);
  //       } else {
  //         return acc;
  //       } 
  //     }, []);
     
  //    }
    
  //  }
  // }
    this.groupNameNew2 = this.questionsBean1.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.desSubItemId === current.desSubItemId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    let temp=false;

// for(let k=0;k<this.groupNameNew.length;k++)
// {
//   if(this.displayPanel[this.groupNameNew[k].desItemId]!=true)
//   {
//     this.tempboolean[this.groupNameNew[k].desItemId]=true
//   }
// }
///this.tempboolean=true;

  
    this.groupNameNew2 =  this.groupNameNew2.filter((x) => {
      return ((x.desSubItemId !=0 ));
      })
      for(let j=0;j<this.groupNameNew2.length;j++)
      {
        for(let k=0;k<this.groupNameNew.length;k++)
        {
          if(this.groupNameNew2[j].desItemId==this.groupNameNew[k].desItemId)
          {
            if(this.displayPanel[this.groupNameNew[k].desItemId]==true)
            {

              this.tempboolean[this.groupNameNew2[j].desItemId]=true;
            }
          }
        }
      }
      this.deItemLevelOthers=[];
      if( this.groupNameNew2.length!=0)
      {
        for(let j=0;j<this.groupNameNew.length;j++)
      {
        
        for(let k=0;k<this.groupNameNew2.length;k++)
        {
      if(this.groupNameNew[j].desItemName==this.groupNameNew2[k].desItemName)
      {
       this.deItemLevelOthers[this.groupNameNew[j].desItemId]=true;
      }
        }
      }
      
      }
   // this.groupNameNew = unique;

    // this.questionsBean = this.dboElePanelList2.reduce((acc, current) => {
    //   console.log(acc, current);
    //   const x = acc.find(item => item.orderId === current.orderId);
    //   if (!x) {
    //     return acc.concat([current]);
    //   } else {
    //     return acc;
    //   } 
    // }, []);
      for(let u = 0; u< this.dboElePanelList2.length; u++){
        if(this.dboElePanelList2[u].colNm == "Rated Output (KW)"){
          this.ratedOutput = this.dboElePanelList2[u].colValCd;
          break;
        }
        if(this.dboElePanelList2[u].colNm == "Rated Voltage (KV)"){
          this.ratedVoltage = this.dboElePanelList2[u].colValCd;
          break;
        }
      }
      for(let l = 0; l < respons.dboElePanelList1.length; l++)
      {
        this.AddOnFlag[l] = respons.dboElePanelList1[l].addOnNewColFlag;
        console.log(this.AddOnFlag[l]);
      } 

for(let z = 0; z < respons.dboElePanelList2.length; z++){
  for(let v = 0; v < this.questionsBean1.length; v++){
    if(respons.dboElePanelList2[z].colNm == this.questionsBean1[v].dropDownType.value){
      this.questionsBean1[v].dispInd = respons.dboElePanelList2[z].dispInd;
    }
  }
}
for(let k = 0; k < this.questionsBean1.length; k++){
  
    this.questionsBean.push(this.questionsBean1[k]);
  
}
for(let j=0;j<this.questionsBean.length;j++)
{
  if(this.questionsBean[j].desSubItemName=="Multifunctional digital generator protection relay shall have following protections features")
  {
    this.newPanel[this.questionsBean[j].orderId]=true;
    break;
  }

}
for(let j=0;j<this.questionsBean.length;j++)
{
  if(this.questionsBean[j].desSubItemName=="Multifunctional digital generator protection relay shall have following protections features")
  {
   this.newPanelval[this.questionsBean[j].orderId]=this.questionsBean[j].dropDownValueList[0].value;
   this.newPanelBol[this.questionsBean[j].orderId]=true;
  }

}
console.log(this.questionsBean);
this.defaultValues = [];
   //to display dropdown default values in ui screen
  for (let l = 0; l < this.questionsBean.length; l++) {
    for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
    //  this.openOth[l] = false;
      if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true  || this.questionsBean[l].colType=="CHECKBOX") {
        this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
        //this.defaultvaluesid.push(this.questionsBean1[k].dispInd)
      }
    }
  }
  for(let j=0;j<this.questionsBean.length;j++)
  {
    if(this.questionsBean[j].colType=="CHECKBOX")
    {
      if(this.questionsBean[j].dropDownValueList[0].defaultFlag==true)
      {

      
    this.lhsCheck[this.questionsBean[j].orderId] = true;
    this.lhsNmCheck[this.questionsBean[j].orderId] = true;
      }
    }
  }
  this.defaultValuesid=[];
  for(let k = 0; k < this.questionsBean.length; k++){
if(this.questionsBean[k].dispInd==1)
{
    this.defaultValuesid.push(this.questionsBean[k].orderId);
}
    
  }
  for (let l = 0; l < this.questionsBean.length; l++) {
    for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
    //  this.openOth[l] = false;
     
       // this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
       this.questionsBean[l].dropDownValueList =this.questionsBean[l].dropDownValueList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.value === current.value);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc; 
        } 
      }, []);
        //this.defaultvaluesid.push(this.questionsBean1[k].dispInd)
     
    }
  }
  this.childvalues=[];
  this.childvaluesnew=[];
  for(let j=0;j< this.dboElePanelList2.length;j++)
  {
    if(this.dboElePanelList2[j].subColValFlag==true)
    { 
      this.childvalues.push(this.dboElePanelList2[j]);
    }
  }
  this.childvaluesnew =  this.childvalues.reduce((acc, current) => {
    console.log(acc, current);
    const x = acc.find(item => item.colNm === current.colNm);
    if (!x) {
      return acc.concat([current]);
    } else {
      return acc;
    } 
  }, []);

  // this.childvaluesnew = this.childvalues.filter((x) => {
  //       //return ((x.defaultFlag ==true));
  //       return ((x.subColValFlag == true))
  //     })
  console.log( this.childvaluesnew);
  for(let j=0;j<this.childvaluesnew.length;j++)
  {
    for(let i=0;i<this.childvalues.length;i++)
    {
      if(this.childvaluesnew[j].colNm==this.childvalues[i].colNm)
      {
        this.childvaluesnew[j].dropDownValueList.push(this.childvalues[i].subColValName);
      }
    }
  }
  for(let j=0;j<  this.childvaluesnew.length;j++)
  {
    this.temparray[ this.childvaluesnew[j].orderId]=this.childvaluesnew[j].dropDownValueList[0];
  }
 
  console.log(this.defaultValues);
  console.log(this.questionsBean);

  if(this.dboEleAuxFullArray.length>0)
  {
  for( let j=0;j<this.dboEleAuxFullArray.length;j++)
  {
    if(this.dboEleAuxFullArray[j].itemId==this.itemId && this.dboEleAuxFullArray[j].subItemId==this.subItemId)
    {
this.defaultValues=[];
this.newPanelval=[];
this.newPanelval=this.dboEleAuxFullArray[j].newPanelval1;

  for(let i=0;i<this.questionsBean.length;i++)
  {
    let countertemp=0;
    for(let k=0;k<this.dboEleAuxFullArray[j].defaultValuesid.length;k++)
    {
      if(this.dboEleAuxFullArray[j].defaultValuesid[k]==this.questionsBean[i].orderId || this.questionsBean[i].colType=="CHECKBOX" || this.questionsBean[i].colType=="DROPDOWNCHECKBOX" )
      {
        if(this.questionsBean[i].colType!="COST_INPUT")
        {

        
        countertemp=1;
        }
      }
    }
    if(countertemp==0)
    {
      this.questionsBean[i].dispInd=0;
    }  
    if(countertemp==1)
    {
      this.questionsBean[i].dispInd=1;
    }
  }
  for(let j=0;j<this.subItemOthAddonList.length;j++)
  {
  if(this.subItemOthAddonList[j].itemId==this.itemId && this.subItemOthAddonList[j].subItemId==this.subItemId)
  {
    this.displaySubItemOthTable=true;
    this.othersSubCheck=true;
    this.othersSubItemCheck=true;
    this.lhsdes2[j]=false;
  
  }
  }
  for(let j=0;j<this.dessubItemOthAddonList.length;j++)
  {
  if(this.dessubItemOthAddonList[j].itemId==this.itemId && this.dessubItemOthAddonList[j].subItemId==this.subItemId)
  {
    this.displayDesSubItemOthTable=true;
    this.othersDesSubCheck=true;
    this.othersDesSubItemCheck=true;
    this.lhsdes[j]=false;


  
  }
  }
  this.techFlagindicator=[];
   this.techFlagindicator=this.dboEleAuxFullArray[j].tempflagsave;
  
  this.inptCstQty=[];
  this.colNumber=[];
  this.colNumber=this.dboEleAuxFullArray[j].colNumber;
  this.costQty=[];
  this.inptCstQty=this.dboEleAuxFullArray[j].inptCstQty;
  this.costQty=this.dboEleAuxFullArray[j].costQty;
  this.inputText=[];
  this.inputText=this.dboEleAuxFullArray[j].inputText;
  this.lhsCheck=[];
  this.lhsNmCheck==[];
  this.lhsCheck= this.dboEleAuxFullArray[j].lhsCheck;
  this.lhsNmCheck= this.dboEleAuxFullArray[j].lhsNmCheck;
  this.drpCheck=[];
  this.dropCheckQty=[];
   this.drpCheckqtyy=[];
   this.drpCheckqtyyKey=[];
  // this.drpCheck=this.dboEleAuxFullArray[j].drpCheck;
   //this.dropCheckQty=this.dboEleAuxFullArray[j].dropCheckQty;
    //this.drpCheckqtyy=this.dboEleAuxFullArray[j].drpCheckqtyy;
    //this.drpCheckqtyyKey=this.dboEleAuxFullArray[j].drpCheckqtyyKey;
    for(let ji=0;ji<this.dboEleAuxFullArray[j].drpCheckqtyyKey.length;ji++)
    {
      if(this.dboEleAuxFullArray[j].drpCheckqtyyKey[ji]!=null)
      {
        this.drpCheckqtyyKey[ji]=this.dboEleAuxFullArray[j].drpCheckqtyyKey[ji];
      }
    }
   
    let tempar=[];
    if( this.drpCheckqtyyKey.length!=0)
    {
	if(this._ITOcustomerRequirementService.editFlag==true && this.dboEleAuxFullArray[j].counter==0)
{
   for(let value1 in this.drpCheckqtyyKey)
   {
     let value2=Number(value1);
 for(let value in this.dboEleAuxFullArray[j].drpCheckqtyy)
 {
 
   for(let k=0;k<this.questionsBean[value2-1].dropDownValueList.length;k++)
   {

    if(value==this.questionsBean[value2-1].dropDownValueList[k].value)
    {
       this.drpCheck[k]=true;
       this.dropCheckQty[k]=true;
       this.drpCheckqtyy[k]=this.dboEleAuxFullArray[j].drpCheckqtyy[value];
    }
   }
 }
   }
  }
  else
{
  this.drpCheck=this.dboEleAuxFullArray[j].drpCheck;
   this.dropCheckQty=this.dboEleAuxFullArray[j].dropCheckQty;
    this.drpCheckqtyy=this.dboEleAuxFullArray[j].drpCheckqtyy;
   this.drpCheckqtyyKey=this.dboEleAuxFullArray[j].drpCheckqtyyKey;
}
}


    
    // for(let j=0;j<this.drpCheckqtyyKey.length;j++)
    // {
    //   if(this.drpCheckqtyyKey[j]==true)
    //   {
    //     tempar.push(j);
    //   }
    // }

   
    this.errMsgRhsCost=[];
    this.errMsgRed = [];
    this.errMsgBlue = [];
    this.errMsgGreen = [];
       this.errMsgPnl=[];
       this.errDisplayPnl=[];
       this.lhsCheck=[];
       this.lhsNmCheck==[];
      
        this.newPanelval=[];
        if(this.dboEleAuxFullArray[j].panelapplicable==false )
        {
          this.saveBtColor=false;
          this.buttonColor="red";

        }
        else if(this.dboEleAuxFullArray[j].panelapplicable==true && this.dboEleAuxFullArray[j].panelapplicablescm==true )
        {
          this.saveBtColor=false;
          this.buttonColor="green";


        }
        else if(this.dboEleAuxFullArray[j].panelapplicablescm==false )
        {
          this.saveBtColor=false;
          this.buttonColor="blue";
        }
				  this.Ifapplicable=[];
				          this.Ifapplicable=this.dboEleAuxFullArray[j].Ifapplicable;
        // this.drpCheck=this.dboEleAuxFullArray[j].drpCheck;
        // this.dropCheckQty=this.dboEleAuxFullArray[j].dropCheckQty;
        //  this.drpCheckqtyy=this.dboEleAuxFullArray[j].drpCheckqtyy;
        //  this.drpCheckqtyyKey=this.dboEleAuxFullArray[j].drpCheckqtyyKey;
      this.itemcost=this.dboEleAuxFullArray[j].itemcost;
       this.lhsCheck= this.dboEleAuxFullArray[j].lhsCheck;
       this.lhsNmCheck= this.dboEleAuxFullArray[j].lhsNmCheck;
       this.errMsgRhsCost=this.dboEleAuxFullArray[j].errMsgRhsCost;
       this.errMsgRed=this.dboEleAuxFullArray[j].errMsgRed;
       this.errMsgBlue=this.dboEleAuxFullArray[j].errMsgBlue;
       this.errMsgGreen=this.dboEleAuxFullArray[j].errMsgGreen;
       this.errMsgPnl=this.dboEleAuxFullArray[j].errMsgPnl;
       this.errDisplayPnl=this.dboEleAuxFullArray[j].errDisplayPnl;
       this.newPanelval=this.dboEleAuxFullArray[j].newPanelval1;;
 //this.defaultValuesid=this.dboEleAuxFullArray[j].errDisplayPnl1;;
 
        if(this.dboEleAuxFullArray[j].eleItemTechRemarks!=null)
        {
          this.eleItemTechRemarks=this.dboEleAuxFullArray[j].eleItemTechRemarks;
          this.techCheckIn = true;
          this.itemTechRmkDiv=true;
        }
        if(this.dboEleAuxFullArray[j].eleItemComrRemarks!=null)
        {
          this.eleItemComrRemarks=this.dboEleAuxFullArray[j].eleItemComrRemarks;
          this.comrrCheck = true;
          this.itemComrRmkDiv=true;
        }
        this.defaultValues=this.dboEleAuxFullArray[j].defaultValues;
        this.qty=this.dboEleAuxFullArray[j].qty;
        this.dboCost=this.dboEleAuxFullArray[j].dboCost;
        this.discountPer = this.dboEleAuxFullArray[j].discountPer;
        if(this.dboEleAuxFullArray[j].panelapplicable==false )
        {
          this.saveBtColor=false;
          this.buttonColor="red";

        }
        else if(this.dboEleAuxFullArray[j].panelapplicable==true && this.dboEleAuxFullArray[j].panelapplicablescm==true )
        {
          this.saveBtColor=false;
          this.buttonColor="green";


        }
        else if(this.dboEleAuxFullArray[j].panelapplicablescm==false )
        {
          this.saveBtColor=false;
          this.buttonColor="blue";
        }
        this.dboAddOnCost = this.dboEleAuxFullArray[j].dboAddOnCost;
        this.othCheck=[];
        this.displayCompOthTable=[];
        this.othersCompCheck=[];

      if(this.dboEleAuxFullArray[j].itemOthersList.length!=0)
      {
        for(let i=0;i<this.dboEleAuxFullArray[j].itemOthersList.length;i++)
        {
          if(this.dboEleAuxFullArray[j].itemOthersList[i].itemId==this.itemId && this.dboEleAuxFullArray[j].itemOthersList[i].subItemId==this.subItemId )
          {            
           // this.displayCompOthTable=true;
         
            //this.othersCompCheck=true;
            this.itemOthersList.push(this.dboEleAuxFullArray[j].itemOthersList[i]);
            this.disablelhs[i]=false;
          }
        }   
        for(let j=0;j<this.itemOthersList.length;j++)
        {
          if(this.itemOthersList[j].desSubItemId==0 || this.itemOthersList[j].desSubItemId==null )
          {
            this.othCheck[this.itemOthersList[j].desItemId]=true;
            this.displayCompOthTable[this.itemOthersList[j].desItemId]=true;
            this.othersCompCheck[this.itemOthersList[j].desItemId]=true;
          }
          else
          {
            this.othCheck[this.itemOthersList[j].desSubItemId]=true;
            this.displayCompOthTable[this.itemOthersList[j].desSubItemId]=true;
            this.othersCompCheck[this.itemOthersList[j].desSubItemId]=true;
          }
       
        
        }    
      }
      this.defaultValues=[];
  this.questionsBean = this.questionsBean.filter(n => n != null);
  for (let l = 0; l < this.questionsBean.length; l++) {
    for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
    //  this.openOth[l] = false;
      if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
        this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
        //this.defaultvaluesid.push(this.questionsBean1[k].dispInd)
      }
    }
  }
  this.defaultValues=[];
  this.defaultValues=this.dboEleAuxFullArray[j].defaultValues;
  if(this._ITOcustomerRequirementService.editFlag==true && this.dboEleAuxFullArray[j].counter==0 &&  this._ITOeditQoutService.dboEleAuxData.length!=0)
 { 
  this.defaultValues=[];
  this.defaultValues1=[];
  for(let j=0;j<300;j++)
  {
    this.defaultValues1.push("12$7");
  }
  for(let j=0;j<this.questionsBean.length;j++)

  {
   
      for(let i=0;i<this.questionsBean[j].dropDownValueList.length;i++)
      {
        if(this.questionsBean[j].dropDownValueList[i].defaultFlag==true || this.questionsBean[j].colType=="CHECKBOX"    )
        {
          this.defaultValues1[j]=this.questionsBean[j].dropDownValueList[i].value;
        }
     
      
    }

  }
 for(let j=0;j<this.questionsBean.length;j++)
 {
   console.log(this.questionsBean[j].orderId);
 }
  for(let j=0;j< this._ITOeditQoutService.dboEleAuxData.length;j++)
  {
    if( this._ITOeditQoutService.dboEleAuxData[j].itemId==this.itemId &&  this._ITOeditQoutService.dboEleAuxData[j].dispInd==1 &&  this._ITOeditQoutService.dboEleAuxData[j].addOnNewColFlag ==0 &&   this._ITOeditQoutService.dboEleAuxData[j].lhsFlag ==0)
    {
      this.defaultValues1[ this._ITOeditQoutService.dboEleAuxData[j].orderId-1]= this._ITOeditQoutService.dboEleAuxData[j].colValCd;
    }
  }
  for(let j=0;j<this.defaultValues1.length;j++)
  {
    if(this.defaultValues1[j]!="12$7")
    {
      this.defaultValues.push(this.defaultValues1[j]);
    }
  }
}
  console.log(this.dboEleAuxFullArray[j].defaultValues);
  this.defaultValuesid=[];
  for(let k = 0; k < this.questionsBean.length; k++){
    if(this.questionsBean[k].dispInd==1)
    {
    this.defaultValuesid.push(this.questionsBean1[k].orderId);
    }
  }
  if(this.techFlagindicator.length!=0)
  {
  for(let f=0;f<this.defaultValuesid.length;f++)
  {
    for(let n=0;n<this.questionsBean[this.defaultValuesid[f]-1].dropDownValueList.length;n++)
    {
      if(this.questionsBean[this.defaultValuesid[f]-1].dropDownValueList[n].value==this.defaultValues[this.defaultValuesid[f]-1] || this.questionsBean[this.defaultValuesid[f]-1].colType=="TEXT" || this.questionsBean[this.defaultValuesid[f]-1].colType=="EDIT_TEXT")
      {
        if(this.techFlagindicator[this.defaultValuesid[f]])
        {
          this.questionsBean[this.defaultValuesid[f]-1].dropDownValueList[n].techFlag=1;

        }
        else
        {
          this.questionsBean[this.defaultValuesid[f]-1].dropDownValueList[n].techFlag=0;

        }
      }

    }
  }
}
  this.temparray=this.dboEleAuxFullArray[j].temparray;
  for(let j=0;j<this.childvaluesnew.length;j++)
  {
    
   let colid=this.childvaluesnew[j].orderId;
   if(this.temparray[colid]=="1212")
   {
     this.temparray[colid]=this.childvaluesnew[j].dropDownValueList[0];
   }
  }
  
  for (let l = 0; l < this.questionsBean.length; l++) {
    for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
    //  this.openOth[l] = false;
     
       // this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
       this.questionsBean[l].dropDownValueList = this.questionsBean[l].dropDownValueList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => this.questionsBean[l].dropDownValueList.value === current.value);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
        //this.defaultvaluesid.push(this.questionsBean1[k].dispInd)
     
    }
  }
}
  }
}
this.diableitemname=true;

    });
  
  }
     
      
    
    
}
checkForQuantity1(value,id,lhs,maxvalue)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(value,id);
  var numberValue = Number(value);
 
  if(lhs=="Busduct Running Length in Meters (M)")
  {
    if(numberValue<1 || isNaN(numberValue) || numberValue>maxvalue)
    {
      this.questionsBean[id].dropDownValueList[0].value="";
      this.defaultValues[id]="";
      this.questionsBean1[id].dropDownValueList[0].value=value;

    }
    else
    {
      this.questionsBean[id].dropDownValueList[0].value=value;
      this.questionsBean1[id].dropDownValueList[0].value=value;
    }
  }
  else
  {
    this.questionsBean[id].dropDownValueList[0].value=value;
    this.questionsBean1[id].dropDownValueList[0].value=value;
  }

}
checkForQuantity(value,orderid)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(value==null || value =="")
  {
    value="";
  }
  console.log(value);
  console.log(orderid);
this.newPanelval[orderid]=value;
}
 
  optionSel12(selVal, options)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    //this.defaultnewadd(selVal, options,1)
    console.log(selVal, options);
    //this.temparray[selVal.colId]=selVal.subColValCode;
    if(options=="")
    {
      
    }
    
  }
  cancelAddOn(key ,i)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.addonflg="0";
    this.openOth1[key]=false;
    this.rhs[key]=false;
    for(let j=0;j<this.addOnList.length;j++)
    {
      if(this.addOnList[j].itemId==this.itemId && this.addOnList[j].subItemId==this.subItemId && this.addOnList[j].colId==key)
      {
        this.addOnList.splice(j,1);
      }
    }
    this.openOth[key]=false;
    this.addOnCheck[key] = false;
    this.newAddNameO[key]="";
    this.newAddCostO[key]="";
    this.newAddRemrkO[key]=""; 
  }
  //on click of check or uncheck to create addons inside panel
  newAddOn1(val, z){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.addonflg="1";
    console.log(val, z);
    // if(event.target.checked){
      this.addOnCheck[z] = true;
      this.rhs[z]=true;
      this.openOth[z] =true;
      this.openOth1[z]=true;
    // }else if(!event.target.checked){
      // this.addOnCheck[z] = false;
      // this.openOth[z] =false;
      // this.rhs[z]=false;
    // }
  }

  getPriceExel(){
    this._ITOeditQoutService.eleExtScopeList1=[];
    this.mainSave=true;
    this.mainSave2=true;
    this.getPrice=true;
    if(this.lhsdes.includes(true) ||this.disablelhs.includes(true) || this.openOth1.includes(true) ||  this.lhsdes2.includes(true) || this.rhsdes.includes(true) || this.rhsdes2.includes(true) )
    {
      this.getPrice=false;
    }
    if(this.getPrice )
    {
    this._ITOeditQoutService.dboEleAuxData=[];
    this.newvalues1=[];
    this.SelectedExcelData = [];
      //this is used to uncheck others if there are no new items in others
    if(this.itemOthersAddonList.length == 0)
    this.othersCheck = false;
//this is used to uncheck others if there are no new sub items in others
if(this.subItemOthAddonList.length == 0)
    this.othersSubCheck = false;
     //this is used to uncheck others if there are no new sub itemtype in others
// if(this.subItmTypOthList.length == 0)
// this.othersSubTypCheck = false;
this.defaultlhs=[];
for(let k=0;k<this.defaultValuesid.length;k++)
{
  this.defaultlhs[this.defaultValuesid[k]]=true;
}
      for(let j=0;j<this.defaultValues.length;j++)
      {
        for(let i=0;i<this.childvaluesnew.length;i++)
        {
          if(this.defaultValues[j]==this.childvaluesnew[i].colValCd && j+1==this.childvaluesnew[i].orderId && this.defaultlhs[this.childvaluesnew[i].orderId]==true    )
          {
            
                    this.newvalues1.push(this.childvaluesnew[i]);
                 
            
          }
        }
      }
      let counter123=0;
      // for(let j=0;j<this.newvalues1.length;j++)
      // {
      //   if(this.newvalues1[j].dropDownValueList[0]!=this.temparray[this.newvalues1[j].colId])
      //   {
      //     counter123=1;
      //    this.temparray[this.newvalues1[j].colId]=this.newvalues1[j].dropDownValueList[0];
      //   }
      // }
      console.log(this.ratedOutput);
      console.log(this.ratedVoltage);
      for(let j = 0; j< this.questionsBean.length; j++){
        //Type of Model
       // Alternator Rating (KW)
        if(this.questionsBean[j].dispInd == 0 && this.questionsBean[j].colType == "COST_INPUT"){
          this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
              this.dboClass.itemId = this.itemId;
              if(this.subItemId == 0 || this.subItemId == null){
                this.dboClass.subItemId = 0;
              }else {
                this.dboClass.subItemId = this.subItemId;           
              } 
              this.dboClass.desItemId=this.questionsBean[j].desItemId;
              this.dboClass.desItemName=null;

              this.dboClass.desSubItemId=this.questionsBean[j].desSubItemId;
              this.dboClass.desSubItemName=null;
              this.dboClass.colId = this.questionsBean[j].dropDownType.key;
              this.dboClass.colNm = null;
              this.dboClass.colValCd =this.defaultValues[this.questionsBean[j].orderId-1]; 
              this.dboClass.subColValCode=null;
              this.dboClass.quantity = null;         
              this.dboClass.cost = null;          
              this.dboClass.techRemarks = null;
              this.dboClass.comrRemarks = null;
              this.dboClass.addOnNewColFlag = 0;
              this.dboClass.orderId= this.questionsBean[j].orderId;
              this.dboClass.defaultFlagNew=1;
              this.dboClass.dispInd=0;
              this.dboClass.techFlag = 0;
              this.dboClass.comrFlag = this.questionsBean[j].comrFlag;
              //this.test123.push( this.dboClass);
              this.SelectedExcelData.push(this.dboClass);    
          }        
      }
      for (let l = 0; l < this.questionsBean.length; l++) {
        this.dboClass = new dboClass();
        //To push the selected dropdown values in the panel
        //OtherColValFlag and addoncostmeflag will be zero
        //As the default values will be set in the dropdown
        for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
          if (this.questionsBean[l].dropDownValueList[d].value == this.defaultValues[l] && this.questionsBean[l].dispInd==1 ) {
            if( this.questionsBean[l].colType == "DROPDOWN" || this.questionsBean[l].colType == "INPUTERRORBOX" ){
              console.log(this.questionsBean[l].colType, this.questionsBean[l].orderId);
              console.log( this.questionsBean[l].orderId);
              this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
              this.dboClass.itemId = this.itemId;
              if(this.subItemId == 0 || this.subItemId == null){
                this.dboClass.subItemId = 0;
              }else {
                this.dboClass.subItemId = this.subItemId;           
              } 
              this.dboClass.desItemId=this.questionsBean[l].desItemId;
              this.dboClass.desItemName=null;

              this.dboClass.desSubItemId=this.questionsBean[l].desSubItemId;
              this.dboClass.desSubItemName=null;
            this.dboClass.colId = this.questionsBean[l].dropDownType.key;
            this.dboClass.colNm = null;
           
          
              this.dboClass.colValCd = this.questionsBean[l].dropDownValueList[d].code;
            
           
            let counter =0;
            for(let j=0;j<this.newvalues1.length;j++)
{
 
  if(this.questionsBean[l].orderId==this.newvalues1[j].orderId)
  {
  if(this.temparray[this.questionsBean[l].orderId]=="1212")
  {
    counter=1;
    this.dboClass.subColValCode=this.newvalues1[j].dropDownValueList[0];
  }
  else if(this.temparray[this.questionsBean[l].orderId]!="1212")
  {
    counter=1;
    this.dboClass.subColValCode=this.temparray[this.questionsBean[l].orderId];
  }
  else
  {
    this.dboClass.subColValCode=null;
  }
  break;
    
  }

 
 
}
if(counter==0)
{
  this.dboClass.subColValCode=null;
}
           // this.dboClass.subColValCode=null;

            this.dboClass.quantity = null;    
            if(this.inptCstQty[this.questionsBean[l].orderId] == true){
              this.dboClass.cost = this.costQty[this.questionsBean[l].orderId];
            }else{
            this.dboClass.cost = null; 
            }   
            if(this.questionsBean[l].colType == "INPUTERRORBOX")      {
              this.dboClass.techRemarks = this.inputText[this.questionsBean[l].orderId];
            }else{
            this.dboClass.techRemarks = null;
            }
            this.dboClass.comrRemarks = null;
            
            this.dboClass.addOnNewColFlag = 0;
           
            this.dboClass.orderId=this.questionsBean[l].orderId;
            this.dboClass.defaultFlagNew=1;
            this.dboClass.dispInd=1;
            this.dboClass.techFlag =this.questionsBean[l].dropDownValueList[d].techFlag;
            this.dboClass.comrFlag = this.questionsBean[l].comrFlag;
            this.SelectedExcelData.push(this.dboClass);
}else if(this.questionsBean[l].colType=="CHECKBOX" && this.lhsCheck[this.questionsBean[l].orderId]==true) {
  console.log(this.questionsBean[l].colType, this.questionsBean[l].orderId);
  this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboClass.itemId = this.itemId;
  if(this.subItemId == 0 || this.subItemId == null){
    this.dboClass.subItemId = 0;
  }else {
    this.dboClass.subItemId = this.subItemId;           
  } 
  this.dboClass.desItemId=this.questionsBean[l].desItemId;
  this.dboClass.desItemName=null;

  this.dboClass.desSubItemId=this.questionsBean[l].desSubItemId;
  this.dboClass.desSubItemName=null;
            this.dboClass.colId = this.questionsBean[l].dropDownType.key;
            this.dboClass.colNm = null;
            this.dboClass.colValCd = this.questionsBean[l].dropDownValueList[d].code; 
            this.dboClass.subColValCode=null;
            this.dboClass.quantity = null;         
            this.dboClass.cost = null;          
            this.dboClass.techRemarks = null;
            this.dboClass.comrRemarks = null;
            this.dboClass.addOnNewColFlag = 0;
            this.dboClass.orderId= this.questionsBean[l].orderId;
            this.dboClass.defaultFlagNew=1;
            this.dboClass.dispInd=1;
            this.dboClass.techFlag = this.questionsBean[l].dropDownValueList[d].techFlag;
            this.dboClass.comrFlag = this.questionsBean[l].comrFlag;
            this.SelectedExcelData.push(this.dboClass);
            }
           
          }
        }
      }
   for(let j=0;j<this.questionsBean.length;j++)
   {
     if(this.questionsBean[j].colType == "DROPDOWNCHECKBOX"){
for(let i=0;i<this.questionsBean[j].dropDownValueList.length;i++)
{
  if(this.dropCheckQty[i] == true && this.drpCheckqtyyKey[this.questionsBean[j].orderId] == true)
  {
    this.dboClass = new dboClass();
    this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
              this.dboClass.itemId = this.itemId;
              if(this.subItemId == 0 || this.subItemId == null){
                this.dboClass.subItemId = 0;
              }else {
                this.dboClass.subItemId = this.subItemId;           
              } 
              this.dboClass.desItemId=this.questionsBean[j].desItemId;
              this.dboClass.desItemName=null;

              this.dboClass.desSubItemId=this.questionsBean[j].desSubItemId;
              this.dboClass.desSubItemName=null;
            this.dboClass.colId = this.questionsBean[j].dropDownType.key;
            this.dboClass.colNm = null;
            this.dboClass.colValCd = this.questionsBean[j].dropDownValueList[i].code; 
            this.dboClass.subColValCode=null;
            this.dboClass.quantity = this.drpCheckqtyy[i];         
            this.dboClass.cost = null;          
            this.dboClass.techRemarks = null;
            this.dboClass.comrRemarks = null;
            this.dboClass.addOnNewColFlag = 0;
            this.dboClass.orderId= this.questionsBean[j].orderId;
            this.dboClass.defaultFlagNew=1;
            this.dboClass.dispInd=1;
            this.dboClass.techFlag = this.questionsBean[j].dropDownValueList[i].techFlag;
            this.dboClass.comrFlag = this.questionsBean[j].comrFlag;
            //this.test123.push( this.dboClass);
            this.SelectedExcelData.push(this.dboClass);
  }
}
     }
     else if (this.questionsBean[j].colType == "EDIT_TEXT" || this.questionsBean[j].colType == "TEXT"){
       if(this.questionsBean[j].dispInd==1)
       {

       
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.itemId;
      if(this.subItemId == 0 || this.subItemId == null){
        this.dboClass.subItemId = 0;
      }else {
        this.dboClass.subItemId = this.subItemId;           
      } 
      this.dboClass.desItemId=this.questionsBean[j].desItemId;
      this.dboClass.desItemName=null;

      this.dboClass.desSubItemId=this.questionsBean[j].desSubItemId;
      this.dboClass.desSubItemName=null;
              this.dboClass.colId = this.questionsBean[j].dropDownType.key;
              this.dboClass.colNm = null;
              this.dboClass.colValCd =this.defaultValues[this.questionsBean[j].orderId-1]; 
              this.dboClass.subColValCode=null;
              this.dboClass.quantity = null;         
              this.dboClass.cost = null;          
              this.dboClass.techRemarks = null;
              this.dboClass.comrRemarks = null;
              this.dboClass.addOnNewColFlag = 0;
              this.dboClass.orderId= this.questionsBean[j].orderId;
              this.dboClass.defaultFlagNew=1;
              this.dboClass.dispInd=1;
              this.dboClass.techFlag = this.questionsBean[j].dropDownValueList[0].techFlag;
              this.dboClass.comrFlag = this.questionsBean[j].comrFlag;
              //this.test123.push( this.dboClass);
              this.SelectedExcelData.push(this.dboClass);
    }
     }
   }
   this.SelectedExcelData.sort((a,b) => a.orderId-b.orderId);

  // console.log(this.test123);
      // for (let l = 0; l < this.questionsBean.length; l++) {
      //   this.dboClass = new dboClass();
      //   //To push the selected dropdown values in the panel
      //   //OtherColValFlag and addoncostmeflag will be zero
      //   //As the default values will be set in the dropdown
      //   for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
      //     if (this.questionsBean[l].dropDownValueList[d].value == this.defaultValues[l]) {
      //       this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      //       this.dboClass.itemId = this.itemId;
      //       this.dboClass.itemName = null;
      //       if(this.subItemId == 0 || this.subItemId == null){
      //         this.dboClass.subItemId = null;
      //       }else {
      //         this.dboClass.subItemId = this.subItemId;           
      //       }  
      //       this.dboClass.subItemName = null;
      //       this.dboClass.colId = this.questionsBean[l].dropDownType.key;
      //       this.dboClass.colNm = null;
      //       this.dboClass.colValCd = this.questionsBean[l].dropDownValueList[d].code; 
      //       this.dboClass.quantity = null;         
      //       this.dboClass.cost = null;          
      //       this.dboClass.techRemarks = null;
      //       this.dboClass.comrRemarks = null;
      //       this.dboClass.addOnFlg = 0;
      //       this.dboClass.techFlag = 1;
      //       this.dboClass.comrFlag = 1;
      //       this.SelectedExcelData.push(this.dboClass);
      //     }
      //   }
      // }
     // for()
      //To push the other addon attribute (new rhs)
      //addOnFlg has set to be 1
      if(this.addOnList.length!=0)
      {
      for(let c=0; c<this.addOnList.length; c++){
        if(this.addOnList[c].itemId==this.itemId && this.addOnList[c].subItemId==this.subItemId){
        this.SelectedExcelData.push(this.addOnList[c]);
        }
      }
    }
      //To push the other attribute (new lhs and rhs)
      //colId should be 0 and addOnFlg has to be 0
      for(let p=0; p<this.itemOthersList.length; p++){
        this.SelectedExcelData.push(this.itemOthersList[p]);
      }
      for(let g=0;g<this.lhsRhsSubItemsList.length;g++)
      {
        if(this.lhsRhsSubItemsList[g].itemId==this.itemId && this.lhsRhsSubItemsList[g].colNm!=null && this.lhsRhsSubItemsList[g].subItemId==this.subItemId)
        {
        this.SelectedExcelData.push(this.lhsRhsSubItemsList[g]);
        }
      }
      for(let h=0;h<this.lhsRhsDesSubItemsList.length;h++)
      {
        if(this.lhsRhsDesSubItemsList[h].itemId==this.itemId && this.lhsRhsDesSubItemsList[h].colNm!=null && this.lhsRhsDesSubItemsList[h].subItemId==this.subItemId)
        {
        this.SelectedExcelData.push(this.lhsRhsDesSubItemsList[h]);
        }
      }
      console.log(this.SelectedExcelData);
       this.dboFormData.quantity = this.qty;
      console.log(this.qty);
      this.dboFormData.eleTechData = this.SelectedExcelData;
    this.dboFormData.quantity = this.qty;
    this.dboFormData.eleItemTechRemarks = this.eleItemTechRemarks;
    this.dboFormData.eleItemComrRemarks = this.eleItemComrRemarks;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.discountPer = this.discountPer;
    console.log(this.dboFormData);
  this._DboEleAuxialriesService.getEleTechPrice(this.dboFormData).subscribe(res => {
    console.log(res);
    this.panelapplicable=true;
    this.panelapplicablescm=true;
    this.Ifapplicable=[];
    // res.eleTechList =  res.eleTechList.filter((x) => {
    //   return ((x.itemId ==1) && (x.subItemId ==0));
    // })
    
    for(let j=0;j<res.eleTechList.length;j++)
    {

      if(res.eleTechList[j].itemApproxCostFlag==2  )
      {
        this.panelapplicable=false;
        this.saveBtColor=false;
        this.buttonColor="red";

        this.Ifapplicable.push(res.eleTechList[j].itemErrMessage);
      }
      if(res.eleTechList[j].itemApproxCostFlag==1 )
      {
        this.panelapplicablescm=false;
        this.Ifapplicable.push(res.eleTechList[j].itemErrMessage);
        this.saveBtColor=false;
        this.buttonColor="blue";



      }

     
    }

   // this.Ifapplicabletemp = this.Ifapplicabletemp.filter(n => n != null);
    if(this.panelapplicable && this.panelapplicablescm ){
      this.saveBtColor=false;
      this.buttonColor="green";
    }


    this.addOnCost=[];
    //this.itemcost=res.eleTechList[0].itemCost;
    for(let b= 0; b<res.eleTechList.length; b++){
      if(res.eleTechList[b].itemId == this.itemId && res.eleTechList[b].subItemId == this.subItemId){
        this.itemcost= res.eleTechList[b].itemCost;
        break;
      }
    }
    for(let j=0;j<res.eleTechList.length;j++)
    {
      if(res.eleTechList[j].addOnFlg==1 && res.eleTechList[j].addOnNewColFlag==0)
      {
      this.addOnCost.push(res.eleTechList[j]);
      }
    }
    
    





    this.techPriceResp = res;
    this.dboCost = res.basicCost;

    
    this.dboAddOnCost= res.eleAddOnCost;
    if (res.successCode == 0) {
      this.subMessage = true;
      this.messageVal = "Cost Saved successfully";
      this._ITOcustomerRequirementService.sendeleBtnStatus(true);
      //f2fTechList contains the drop down default values
      // as wellas the new lhs and rhs values
      // this._ITOeditQoutService.dboEleAuxData = res.eleTechList;
      //error msg and rhs costdispaly on panel
      this.errDisplayPnl=[];
      for(let c=0;c<res.eleTechList.length;c++){
        if(( res.eleTechList[c].addOnFlg == 1) && (res.eleTechList[c].lhsFlag==0) && (res.eleTechList[c].addOnNewColFlag==0)){
          this.errMsgRhsCost[res.eleTechList[c].colId] = "AddOnCost: " +res.eleTechList[c].rhsCost;
          if(res.eleTechList[c].approxCostFlag == 1){
            this.errMsgBlue[res.eleTechList[c].colId] = true;
          }
          if(res.eleTechList[c].approxCostFlag == 2){
            this.errMsgRed[res.eleTechList[c].colId] = true;
          }
		  if(res.eleTechList[c].approxCostFlag == 0){
            this.errMsgGreen[res.eleTechList[c].colId] = true;
          }
          if(res.eleTechList[c].errorMsg==null ||  res.eleTechList[c].errorMsg=="" || res.eleTechList[c].errorMsg=="NULL" )
          {
            this.errMsgPnl[res.eleTechList[c].colId]="";
            this.errDisplayPnl[res.eleTechList[c].colId] = true;
          }
          else
          {
          this.errMsgPnl[res.eleTechList[c].colId] = "      Note:" +res.eleTechList[c].errorMsg;
          this.errDisplayPnl[res.eleTechList[c].colId] = true;
       
          }
        }
      }
      // for(let j=0;j< res.eleTechList.length;j++)
      // {
      //   if(res.eleTechList[j].subColValFlag==true)
      //   { 
      //     this.childvaluestemp1.push(res.eleTechList[j]);
      //   }
      // }
      // this.childvaluestemp1=[];
      // for(let j=0;  this.childvaluestemp1.length;j++)
      // {
      //   for(let i=0;i<this.childvaluesnew.length;i++)
      //   {
      //     if(this.childvaluestemp1[j].colId==this.childvaluesnew[i].colId)
      //     {
      //       let test=[];
      //       test=this.childvaluesnew[i].dropDownValueList;
      //       this.childvaluesnew[i].dropDownValueList=[];
      //       this.childvaluesnew[i].dropDownValueList.push( this.childvaluestemp1[j].subColValCode);
      //       for(let k=0;k<test.length;k++)
      //       {
      //         if(test[k]!=this.childvaluestemp1[j].subColValCode)
      //         {
      //           this.childvaluesnew[i].dropDownValueList.push(test[k]);
      //         }
      //       }
      //     }
      //   }
      // }
      
      console.log( this._ITOeditQoutService.dboEleAuxData);
    } else {
      this.subMessage = true;
      this.messageVal = res.successMsg;
    }
    this.dboBasicCost = res.basicCost;
    console.log(this.newPanelval);
   
    // after sucessful response store the values to local variables
    if (this.dboEleAuxFullArray.length != 0) {
      for (let d = 0; d < this.dboEleAuxFullArray.length; d++) {
        let j = this.dboEleAuxFullArray.map(d => { return d.id }).indexOf(this.eleItemId);
        if (j != (-1)) {
          this.dboEleAuxFullArray[j] = {
            counter:1,
            qty: this.qty,
            discountPer: this.discountPer,
            id: this.eleItemId,
            componenet: this.itemName,
            defaultValues: this.defaultValues,
            dboCost: res.basicCost,
            dboAddOnCost: res.eleAddOnCost,
            techComments: res.techComments,
            comrComments: res.comrComments,
            techRemarks: res.techRemarks,
            comrRemarks : res.comrRemarks,
            eleItemTechRemarks: res.eleItemTechRemarks,
            eleItemComrRemarks: res.eleItemComrRemarks,
            addOnList: this.addOnList,
            itemOthersList: this.itemOthersList,
            itemId: this.itemId,
            itemName: this.itemName,
            subItemId: this.subItemId,
            subItemName: this.subItemName,
            childvalues :this.childvaluesnew,
            temparray:this.temparray,
            defaultValuesid:this.defaultValuesid,
            errMsgRhsCost:    this.errMsgRhsCost,
            errMsgRed: this.errMsgRed,
            errMsgBlue: this.errMsgBlue,
            errMsgGreen: this.errMsgGreen,
            errMsgPnl: this.errMsgPnl,
            errDisplayPnl: this.errDisplayPnl,
            defaulVales1:this.defaulVales1,
            newArray12:this.defaulVales1,
            lhsCheck: this.lhsCheck,
            drpCheck: this.drpCheck,
            dropCheckQty: this.dropCheckQty,
            drpCheckqtyy: this.drpCheckqtyy,
            drpCheckqtyyKey:this.drpCheckqtyyKey,
            lhsNmCheck:this.lhsNmCheck,
            newPanelval1:this.newPanelval,
            inptCstQty:this.inptCstQty,
            costQty:this.costQty,
            inputText: this.inputText,
      itemcost:this.itemcost,
      panelapplicable:this.panelapplicable,
      panelapplicablescm:this.panelapplicablescm,
      colNumber:this.colNumber,
      Ifapplicable:this.Ifapplicable,
      tempflagsave:this.techFlagindicator

          };
          break;
        }
        else {
          this.dboEleAuxFullArray.push({
            counter:1,
            qty: this.qty,
            discountPer: this.discountPer,
            id: this.eleItemId,
            componenet: this.itemName,
            defaultValues: this.defaultValues,
            dboCost: res.basicCost,
            dboAddOnCost: this.dboAddOnCost,
            techComments: res.techComments,
            comrComments: res.comrComments,
            techRemarks: res.techRemarks,
            comrRemarks : res.comrRemarks,
            eleItemTechRemarks: res.eleItemTechRemarks,
            eleItemComrRemarks: res.eleItemComrRemarks,
            addOnList: this.addOnList,
            itemOthersList: this.itemOthersList,
            itemId: this.itemId,
            itemName: this.itemName,
            subItemId: this.subItemId,
            subItemName: this.subItemName,
            childvalues :this.childvaluesnew,
            temparray:this.temparray,
            defaultValuesid:this.defaultValuesid,
            errMsgRhsCost:    this.errMsgRhsCost,
            errMsgRed: this.errMsgRed,
            errMsgBlue: this.errMsgBlue,
            errMsgGreen: this.errMsgGreen,
            errMsgPnl: this.errMsgPnl,
            errDisplayPnl: this.errDisplayPnl,
            defaulVales1:this.defaulVales1,
            newArray12:this.defaulVales1,
            lhsCheck: this.lhsCheck,
            drpCheck: this.drpCheck,
            dropCheckQty: this.dropCheckQty,
            drpCheckqtyy: this.drpCheckqtyy,
            drpCheckqtyyKey:this.drpCheckqtyyKey,
            lhsNmCheck:this.lhsNmCheck,
            newPanelval1:this.newPanelval,
            inptCstQty:this.inptCstQty,
            costQty:this.costQty,
            inputText: this.inputText,
      itemcost:this.itemcost,
      panelapplicable:this.panelapplicable,
      panelapplicablescm:this.panelapplicablescm,
      colNumber:this.colNumber,
      Ifapplicable:this.Ifapplicable,
      tempflagsave:this.techFlagindicator





          });
          break;
        }
      }
    }
    else {
      this.dboEleAuxFullArray.push({
        counter:1,
        qty: this.qty,
        discountPer: this.discountPer,
        id: this.eleItemId,
        componenet: this.itemName,
        defaultValues: this.defaultValues,
        dboCost: res.basicCost,
        dboAddOnCost: this.dboAddOnCost,
        techComments: res.techComments,
        comrComments: res.comrComments,
        techRemarks: res.techRemarks,
        comrRemarks : res.comrRemarks,
        eleItemTechRemarks: res.eleItemTechRemarks,
        eleItemComrRemarks: res.eleItemComrRemarks,
        addOnList: this.addOnList,
        itemOthersList: this.itemOthersList,
        itemId: this.itemId,
        itemName: this.itemName,
        subItemId: this.subItemId,
        subItemName: this.subItemName,
        childvalues :this.childvaluesnew,
        temparray:this.temparray,
        defaultValuesid:this.defaultValuesid,
        errMsgRhsCost:    this.errMsgRhsCost,
        errMsgRed: this.errMsgRed,
        errMsgBlue: this.errMsgBlue,
        errMsgGreen: this.errMsgGreen,
        errMsgPnl: this.errMsgPnl,
        errDisplayPnl: this.errDisplayPnl,
        defaulVales1:this.defaulVales1,
        newArray12:this.defaulVales1,
        lhsCheck: this.lhsCheck,
        drpCheck: this.drpCheck,
        dropCheckQty: this.dropCheckQty,
        drpCheckqtyy: this.drpCheckqtyy,
        drpCheckqtyyKey:this.drpCheckqtyyKey,
        lhsNmCheck:this.lhsNmCheck,
        newPanelval1:this.newPanelval,
        inptCstQty:this.inptCstQty,
        costQty:this.costQty,
            inputText: this.inputText,
      itemcost:this.itemcost,
      panelapplicable:this.panelapplicable,
      panelapplicablescm:this.panelapplicablescm,
      colNumber:this.colNumber,
      Ifapplicable:this.Ifapplicable,
      tempflagsave:this.techFlagindicator





      });
    }
 
    console.log(this.dboEleAuxFullArray);
    this.addedClassList = [];
    for (let m = 0; m < this.dboEleAuxFullArray.length; m++) {
      this.addedClassList.push(this.dboEleAuxFullArray[m].componenet);
    }
    this.hideprogressCost = false;

  
      this.addedClassList = [];
      for (let m = 0; m < this.dboEleAuxFullArray.length; m++) {
        this.addedClassList.push(this.dboEleAuxFullArray[m].componenet);
      }
      this.iFlag = 0;
       if(this.subItemId == 0){
          this.selectdEl[this.selectedELIndex] = true;
          this.itemIdList.push(this.itemId);
          this.iFlag = 1;
       }
      else
      {
        //this.selectdEl[this.selectedELIndex] = true;
      }
          
       if(this.iFlag == 0){
          this.selectdEl[this.iItemindex] = true;
            if (!this.itemIdList.includes(this.itemId)){
                 this.itemIdList.push(this.itemId);
            }
            this.selectdEl1[this.selectedELIndex] = true;
            this.itemIdList1.push(this.subItemId);
            this.index = 0;        
          }
       this.iFlag = 0;  
       this.saveInLocal(this.dboEleAuxFull, { 
        dboEleAuxFullArray: this.dboEleAuxFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
        itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
        selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
        itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
        subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
        eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
        finalEleAuxCost: this.finalEleAuxCost,saveSubCost:this.saveSubCost, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList     
        }); 
      })     
    }
  }
  openItemCompTable(event){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
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
  addRowsItem() {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.displayItmOthnewLine = true;
  }
  itemOthersForm(othersItem, othersItemfrm: NgForm) {
      this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    console.log(othersItem);
    this.dboClass = new dboClass();
    this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboClass.itemId = null;
    this.dboClass.itemName = othersItem.itemCompName; 
    this.dboClass.desSubItemId=null;
    this.dboClass.desSubItemName=null;
    this.dboClass.subItemId = null;
    this.dboClass.subItemName = null;
    this.dboClass.colId = null;
    this.dboClass.colNm = null;
    this.dboClass.colValCd = null;
    this.dboClass.subColValCode=null;
    this.dboClass.quantity = othersItem.itemQuantity;
    this.dboClass.cost = othersItem.itemPrice;
    this.dboClass.techRemarks = othersItem.itemTechRemarks;
    this.dboClass.comrRemarks = othersItem.itemComrRemr;
    this.dboClass.addOnNewColFlag = 1;
    this.dboClass.orderId=0;
    this.dboClass.defaultFlagNew=1;
    this.dboClass.dispInd=1;
    this.dboClass.techFlag = 1;
    this.dboClass.comrFlag = 1;
    this.itemOthersAddonList.push(this.dboClass);
    this.lhsRhsItemsList.push(this.dboClass);//satya
    console.log(this.itemOthersAddonList);
    othersItemfrm.reset();
    this.displayItmOthnewLine = false;
  }


  cancelLinesItemOth(i) {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
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
  addLhsRhsItem(i){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
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
        this.saveBtColor=true;
    this.displayItmOthnewLine = false;
  }
  itemsSubmit(){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    for(let z=0; z<this.itemOthersAddonList.length; z++){
      if (!this.lhsRhsItemsList.some((item) => item.itemName == this.itemOthersAddonList[z].itemName)) {
        this.lhsRhsItemsList.push(this.itemOthersAddonList[z]);
    }
    }console.log(this.lhsRhsItemsList);
  }
  addLhsRhsForm(lhsRhsItem, lhsRhsItemfrm: NgForm){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
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
        this.dboClass.itemId = 0;
        this.dboClass.itemName = this.itemOthersAddonList[c].itemName;
        this.dboClass.desSubItemId=null;
        this.dboClass.desSubItemName=null;
        this.dboClass.subItemId = this.itemOthersAddonList[c].subItemId;
        this.dboClass.subItemName = this.itemOthersAddonList[c].subItemName;
        this.dboClass.colId = 0;
        this.dboClass.colNm = lhsRhsItem.lhsVal;
        this.dboClass.colValCd = lhsRhsItem.rhsVal;
        this.dboClass.subColValCode=null;
        this.dboClass.quantity = this.itemOthersAddonList[c].quantity;
        this.dboClass.cost = this.itemOthersAddonList[c].cost;
        this.dboClass.techRemarks = this.itemOthersAddonList[c].techRemarks;
        this.dboClass.comrRemarks = this.itemOthersAddonList[c].comrRemarks;
        this.dboClass.addOnNewColFlag = 1;
        this.dboClass.dispInd=1;
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
        this.saveBtColor=true;
    this.lhsRhsnewLine = false;
    this.lhsRhsItemsList.splice(i, 1);
  }
  cancelnewLineLhs(){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.lhsRhsnewLine = false;//added by megha
  }
  addRowsLhsRhs(){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.lhsRhsnewLine = true;
  }

calcTotal()
{
  this._ITOeditQoutService.eleExtScopeList1=[];
  this._ITOeditQoutService.dboEleAuxData=[];
  this.message = false;
  if (this.enableOverwriteDiv) {
    this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleAuxCost;
    this.dboFormData.overwrittenPriceFlag = 1;
    this.dboFormData.remarks = this.remarks;
  } else if (this.OverWrittenfinalEleAuxCost > 0) {
    this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleAuxCost;
  //  this.panelList.overwrittenPriceFlag = 1;
  }
 // this.dboFormData.modBy = this._ITOLoginService.loggedUserDetails;
  this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails; 
  this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboFormData.scopeCd = "ELE_AUX";
  this.dboFormData.eleItemTechComments = this.itemRemarksVal;
  this.dboFormData.eleItemComrComments = this.itemCmrRemarksVal;
  this.dboFormData.eleItemIdList = this.itemIdList.filter(n => n != null);
  console.log(this.itemIdList)
  if(this.storage.get(this.dboEleFull)!=null)
  {
    if(this.storage.get(this.dboEleFull).itemIdList.length!=0){
      this.itemIdListAux= this.storage.get(this.dboEleFull).itemIdList;
    for(let j=0;j<this.itemIdListAux.length;j++)
    {
      if(this.itemIdListAux[j]!=null)
      {

      
      this.dboFormData.eleItemIdList.push(this.itemIdListAux[j]);
      }
    }
    }
  }
  
  this.dboFormData.savedEleDataList = this.lhsRhsItemsList;
  this._DboEleAuxialriesService.saveEleItem(this.dboFormData).subscribe(savedResp => {
    console.log(savedResp);
  this.finalEleAuxCost=savedResp.totalPrice;
  this._ITOeditQoutService.dboEleItmOthers = savedResp.saveEleList;
  if(savedResp.successCode == 0){
    this.finalCostBool = true;
    this.mainSave2=false;
    this.message = true;
    this.successMsg = "Cost Saved successfully";
  }else{
    this.message = true;
    this.successMsg = savedResp.successMsg;
  }
  this.saveInLocal(this.dboEleAuxFull, {
    dboEleAuxFullArray: this.dboEleAuxFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
    itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
    selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
    itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
    eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
    finalEleAuxCost: this.finalEleAuxCost,saveSubCost:this.saveSubCost, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
  });
  //call one line BOM
  this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
    console.log(resOnline);
    // if (resOnline.successCode == 0) {
    //   this.finalMechCost = resOnline.oneLineBomExcel.turbineMaterialCost;
    // } 
    this.saveInLocal(this.dboEleAuxFull, {
      dboEleAuxFullArray: this.dboEleAuxFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
      finalEleAuxCost: this.finalEleAuxCost,saveSubCost:this.saveSubCost, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
    });     
    this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
    this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, finalEleAuxCost: this.finalEleAuxCost});
    // if (this._ITOcustomerRequirementService.editFlag) {
    //   this._ITOcustomerRequirementService.editFlag = false;
    //   this.router.navigate(['/EditQuot']);
    // }
  });
  this.hideprogressCost1 = false;
  });
  
}
savePriceExel()
{
  this._ITOeditQoutService.eleExtScopeList1=[];

  this._ITOeditQoutService.dboEleAuxData=[];
  this.message = false;
  if (this.enableOverwriteDiv) {
    this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleAuxCost;
    this.dboFormData.overwrittenPriceFlag = 1;
    this.dboFormData.remarks = this.remarks;
  } else if (this.OverWrittenfinalEleAuxCost > 0) {
    this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleAuxCost;
  //  this.panelList.overwrittenPriceFlag = 1;
  }
 // this.dboFormData.modBy = this._ITOLoginService.loggedUserDetails;
 this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails; 
  this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboFormData.eleItemTechComments = this.itemRemarksVal;
  this.dboFormData.scopeCd = "ELE_AUX";
  this.dboFormData.eleItemComrComments = this.itemCmrRemarksVal;
  this.dboFormData.eleItemIdList = this.itemIdList.filter(n => n != null);
  if(this.storage.get(this.dboEleFull)!=null)
  {
    if(this.storage.get(this.dboEleFull).itemIdList.length!=0){
      this.itemIdListAux= this.storage.get(this.dboEleFull).itemIdList;
    for(let j=0;j<this.itemIdListAux.length;j++)
    {
      if(this.itemIdListAux[j]!=null)
      {

      
      this.dboFormData.eleItemIdList.push(this.itemIdListAux[j]);
      }
    }
    }
  }
  console.log(this.itemIdList)
  this.dboFormData.savedEleDataList = this.lhsRhsItemsList;
  this._DboEleAuxialriesService.saveEleItem(this.dboFormData).subscribe(savedResp => {
    console.log(savedResp);
  this.finalEleAuxCost=savedResp.totalPrice;
  this._ITOeditQoutService.dboEleItmOthers = savedResp.saveEleList;
  if(savedResp.successCode == 0){
    this.mainSave=false;
    this.message = true;
    this.successMsg = "Cost Saved successfully";
  }else{
    this.message = true;
    this.successMsg = savedResp.successMsg;
  }
  this.saveInLocal(this.dboEleAuxFull, {
    dboEleAuxFullArray: this.dboEleAuxFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
    itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
    selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
    itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
    eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
    finalEleAuxCost: this.finalEleAuxCost,saveSubCost:this.saveSubCost, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
  });  
  //call one line BOM
  this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
    console.log(resOnline);
    // if (resOnline.successCode == 0) {
    //   this.finalMechCost = resOnline.oneLineBomExcel.turbineMaterialCost;
    // } 
    this.saveInLocal(this.dboEleAuxFull, {
      dboEleAuxFullArray: this.dboEleAuxFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
      finalEleAuxCost: this.finalEleAuxCost,saveSubCost:this.saveSubCost, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
    });     
    this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
    this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, finalEleAuxCost: this.finalEleAuxCost});
    if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
      this._ITOcustomerRequirementService.editFlag = false;
      this.router.navigate(['/EditQuot']);
    }else{
      this.router.navigate(['/CostEstimation/Electrical/DboEleExtdScope']);
    }
  });
  this.hideprogressCost1 = false;
  });
 
}
rhsOthersFormEdit(val,index,ques){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.addonflg="0";
  this.openOth1[index]=true;
}
  rhsOthersForm(val, index,ques){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.addonflg="0";
    this.openOth1[index]=false;
    console.log(this.newAddNameO[index]);
    console.log(this.newAddCostO[index]);
    console.log(this.newAddRemrkO[index]);
        for(let n = 0; n < this.dboElePanelList1.length; n++){
      if(val ==  this.dboElePanelList1[n].colNm){
        this.colValNmId = this.dboElePanelList1[n].colId;
      }
    }
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
    this.dboClass.itemId = this.itemId;
    if(this.subItemId == 0 || this.subItemId == null){
      this.dboClass.subItemId = 0;
    }else {
      this.dboClass.subItemId = this.subItemId;           
    } 
    this.dboClass.desItemId=ques.desItemId;
    this.dboClass.desItemName=null;

    this.dboClass.desSubItemId=ques.desSubItemId;
    this.dboClass.desSubItemName=null;
    this.dboClass.colId = index;
    this.dboClass.colNm = null;
    this.dboClass.colValCd = this.newAddNameO[index]; 
    this.dboClass.subColValCode = null;
    this.dboClass.quantity = 1;
    this.dboClass.cost = this.newAddCostO[index];      
    this.dboClass.techRemarks = null;
    this.dboClass.comrRemarks = this.newAddRemrkO[index];
    this.dboClass.addOnNewColFlag = 1;
    this.dboClass.orderId=ques.orderId;
    this.dboClass.defaultFlagNew=1;
    this.dboClass.dispInd=1;
    this.dboClass.techFlag = 1;
    this.dboClass.comrFlag = 1;
    this.addOnList.push(this.dboClass);
    console.log(this.addOnList);
  }
 //remarks for items and opening text area for remarks
 itemRmrkCheck(event){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(event);
  if(event.target.checked){
    this.itemRemarkDiv = true;
  }else if(!event.target.checked){
    this.itemRemarkDiv = false;
  }
}
//capturing of Item techinal  Remarks value
checkRemarks(){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(this.itemRemarksVal);
}
//Commercial remarks for items and opening text area for remarks
itmComrRmrk(event){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
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
      this.saveBtColor=true;
  console.log(this.itemCmrRemarksVal);
}

//techinal remakrs for subitem and opening text area for remarks outhside others
subItemTechRem(event){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(event.target.checked){
    this.subItemRemarkDiv = true;
  }else if(!event.target.checked){
    this.subItemRemarkDiv = false;
    this.subItemRmkValOut="";
  }
}

//capturing of subitems techinal Remarks value outside others
checkSubItemRem(){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(this.subItemRmkValOut);
}

//Commercial remakrs for subitem and opening text area for remarks outhside others
subItemComrRem(event){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(event.target.checked){
    this.subItemComrRemDiv = true;
  }else if(!event.target.checked){
    this.subItemComrRemDiv = false;
    this.subItemCmrRemValOut="";

  }
}
//capturing of subitems Commercial Remarks value outside others
subChkItemComrRmrks(){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(this.subItemCmrRemValOut);
}

//Techinal remarks for items and opening text area for remarks
itemRmrkCheckIn(event){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(event.target.checked){
    this.itemTechRmkDiv = true;
  }else if(!event.target.checked){
    this.itemTechRmkDiv = false;
    this.eleItemTechRemarks = '';
  }
}

itmTechInRemarks(){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(this.eleItemTechRemarks);
}

//Commercial remarks for item and openiong the text area for remarks
itemComrCheckIn(event){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(event.target.checked){
    this.itemComrRmkDiv =  true;
  }else if(!event.target.checked){
    this.itemComrRmkDiv = false;
    this.eleItemComrRemarks = '';
  }
  }

  itemComrInRemarks(){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(this.eleItemComrRemarks);
  }

  //check box form items
  dboEleItmList(event, itemId, i,subitemid,code)
  {
    if(code=="LT_CAB")
    {
      this.saveSubCost=0;
    }
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(event, itemId, i);
    if (!event.target.checked) { // on uncheck 
      if (this.itemIdList.includes(itemId)) {
        this.itemIdList[this.itemIdList.indexOf(itemId)] = null;
        for(let x=0;x<this.itemIdList.length;x++)
        {
          if (this.itemIdList.includes(itemId)) {
            this.itemIdList[this.itemIdList.indexOf(itemId)] = null;
          }
        }
        for (let k = 0; k < this.newArrayItm.length; k++)
        {
          if(itemId == this.newArrayItm[k].itemId)
          {
            //If the selected item is having a sub item
            // Need to clear the subitems also (For ex inside Lubeoil)
            if(this.newArrayItm[k].subItemId != 0)
            {
              for(let m = 0; m < this.selectdEl1.length; m++)
              {
                this.selectdEl1[m] = false;
                this.itemIdList1[m] = null;
             }              
            }           
            break;
          }
        }
        this.selectdEl[i] = false; //To uncheck the selected item       
      }
      console.log(this.dboEleAuxFullArray);
      for(let k = 0; k < this.dboEleAuxFullArray.length; k++){
        if(this.dboEleAuxFullArray[k].itemId == itemId){
            this.dboEleAuxFullArray[k] = null;

        }
      }
      // for(let k = 0; k < this.tempitemOthersList.length; k++){
      //   if(this.tempitemOthersList[k].itemId == itemId){
      //       this.tempitemOthersList[k] = null;
      //       this.tempitemOthersList.splice(k, 1);
         
      //   }
      // }
      for(let k = 0; k < this.addOnList.length; k++){
        if(this.addOnList[k].itemId == itemId){
            this.addOnList[k] = null;
         
        }
      }
      this.dboEleAuxFullArray = this.dboEleAuxFullArray.filter(n => n != null);

      this.addOnList = this.addOnList.filter(n => n != null);
      for(let x=0;x<this.subItemOthAddonList.length;x++)
      {
        if(itemId==this.subItemOthAddonList[x].itemId && subitemid==this.subItemOthAddonList[x].subItemId)
        {
          this.subItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsSubItemsList.length;x++)
      {
        if(itemId==this.lhsRhsSubItemsList[x].itemId && subitemid==this.lhsRhsSubItemsList[x].subItemId)
        {
          this.lhsRhsSubItemsList[x]=null;  
        }
      }
      this.subItemOthAddonList = this.subItemOthAddonList.filter(n => n != null);
      this.lhsRhsSubItemsList = this.lhsRhsSubItemsList.filter(n => n != null);
      for(let x=0;x<this.dessubItemOthAddonList.length;x++)
      {
        if(itemId==this.dessubItemOthAddonList[x].itemId && subitemid==this.dessubItemOthAddonList[x].subItemId )
        {
          this.dessubItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsDesSubItemsList.length;x++)
      {
        if(itemId==this.lhsRhsDesSubItemsList[x].itemId && subitemid==this.lhsRhsDesSubItemsList[x].subItemId )
        {
          this.lhsRhsDesSubItemsList[x]=null;  
        }
      }
      this.dessubItemOthAddonList = this.dessubItemOthAddonList.filter(n => n != null);
      this.lhsRhsDesSubItemsList = this.lhsRhsDesSubItemsList.filter(n => n != null);

  
  
    }
    console.log(this.dboEleAuxFullArray)
    this.dboCost=0;
    this.selectdEl[i]=false;
  }

  //check box for sub items
  dboEleItmListSub(event, itemId, i){
    this.subItemButton=true;
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(event, itemId, i);
    if (!event.target.checked) { // on uncheck 
      if (this.itemIdList1.includes(itemId)) {
        this.itemIdList1[this.itemIdList1.indexOf(itemId)] = null;
        for(let x=0;x<this.itemIdList1.length;x++)
        {
          if (this.itemIdList1.includes(itemId)) {
            this.itemIdList1[this.itemIdList1.indexOf(itemId)] = null;
          }
        }
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
              for(let m = 0; m < this.selectdEl1.length; m++)
              {
                this.selectdEl1[m] = false;
                this.itemIdList1[m] = null;
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
          for (let k = 0; k < this.newArrayItm.length; k++)
          {
            if(this.itemtempId == this.newArrayItm[k].itemId)
            {
              this.selectdEl[k] = false;
              this.itemIdList[k] = null;
              break;
            }
          }

        }
        
        // this.selectdEl2[i] = false;
      }
      for(let k=0; k<this.dboEleAuxFullArray.length; k++){
        if(this.dboEleAuxFullArray[k].subItemId == itemId){
          this.dboEleAuxFullArray[k] = null;
          this.dboEleAuxFullArray.splice(k,1);
          break;
        }
      }
      // for(let k = 0; k < this.tempitemOthersList.length; k++){
      //   if(this.tempitemOthersList[k].itemId == itemId){
      //       this.tempitemOthersList[k] = null;
      //       this.tempitemOthersList.splice(k, 1);
         
      //   }
      // }
      for(let k = 0; k < this.addOnList.length; k++){
        if(this.addOnList[k].subItemId == itemId){
            this.addOnList[k] = null;
            this.addOnList.splice(k, 1);
         
        }
      }
      for(let k = 0; k < this.addOnList.length; k++){
        if(this.addOnList[k].itemId == itemId){
            this.addOnList[k] = null;
         
        }
      }
      this.addOnList = this.addOnList.filter(n => n != null);
      for(let x=0;x<this.subItemOthAddonList.length;x++)
      {
        if(itemId==this.subItemOthAddonList[x].subItemId)
        {
          this.subItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsSubItemsList.length;x++)
      {
        if(itemId==this.lhsRhsSubItemsList[x].subItemId)
        {
          this.lhsRhsSubItemsList[x]=null;  
        }
      }
      this.subItemOthAddonList = this.subItemOthAddonList.filter(n => n != null);
      this.lhsRhsSubItemsList = this.lhsRhsSubItemsList.filter(n => n != null);
      for(let x=0;x<this.dessubItemOthAddonList.length;x++)
      {
        if(itemId==this.dessubItemOthAddonList[x].subItemId)
        {
          this.dessubItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsDesSubItemsList.length;x++)
      {
        if(itemId==this.lhsRhsDesSubItemsList[x].subItemId)
        {
          this.lhsRhsDesSubItemsList[x]=null;  
        }
      }
      this.dessubItemOthAddonList = this.dessubItemOthAddonList.filter(n => n != null);
      this.lhsRhsDesSubItemsList = this.lhsRhsDesSubItemsList.filter(n => n != null);

  
  
    }
    console.log("after");
    console.log(this.dboEleAuxFullArray);
  }
//Open others table to enter data to create new subitem others
  //Add Lhs/Rhs for sub item others
 
  // opening new table for others
  openCompTable(event,index,type) {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if (event.target.checked) {
      this.displayCompOthTable[index] = true;
    }
    else if (!event.target.checked) {
      this.displayCompOthTable[index] = false;
      if(type=="sub")
      {
        for(let j=0;j<this.itemOthersList.length;j++)
        {
   if(this.itemOthersList[j].desSubItemId==index)
   {
     this.itemOthersList[j]=null;  
       this.disablelhs[j]=null;
   
   }
        }
      }
      else if(type=="notsub")
      {
        for(let j=0;j<this.itemOthersList.length;j++)
        {
   if(this.itemOthersList[j].desItemId==index)
   {
     this.itemOthersList[j]=null;  
       this.disablelhs[j]=null;
   
   }
        }
      }
     this.itemOthersList= this.itemOthersList.filter(n => n != null);

    }
    this.disablelhs= this.disablelhs.filter(n => n != null);
  }

  //adding new LHS/RHS inside item panel (others)
  compOthersForm(others, othersfrm: NgForm,id,name,array) {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(others);
    this.dboClass = new dboClass();
    this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboClass.itemId = this.itemId;
    if(this.subItemId == 0 || this.subItemId == null){
      this.dboClass.subItemId = 0;
    }else {
      this.dboClass.subItemId = this.subItemId;           
    } 
    this.dboClass.desItemId=id;
    this.dboClass.desItemName=null;

    this.dboClass.desSubItemId=name;
    this.dboClass.desSubItemName=null;
    this.dboClass.colId = 0;
    this.dboClass.colNm = others.othItemName;
    this.dboClass.colValCd = others.othItemVal;
    this.dboClass.subColValCode=null;
    this.dboClass.quantity = null; 
    this.dboClass.cost = others.othCost;          
    this.dboClass.techRemarks = others.othTechRem;
    this.dboClass.comrRemarks = others.othComrRem;
    this.dboClass.addOnNewColFlag = 1;
    this.dboClass.orderId=0;
    this.dboClass.defaultFlagNew=1;
    this.dboClass.dispInd=1;
    this.dboClass.techFlag = 1;
    this.dboClass.comrFlag = 1;
    this.itemOthersList.push(this.dboClass);
    this.disablelhs[this.itemOthersList.length-1]=false;
    this.tempitemOthersList.push(this.dboClass);
    console.log(this.itemOthersList);
   
    othersfrm.reset();
    if(name==0)
    {
      this.displayOthnewLine[id] = false;
    }
    else
    {
      this.displayOthnewLine[name] = false;
    }
   
  }

   //cancel new line
   cancelnewLineOth(id, othersfrm: NgForm) {
    othersfrm.reset();

    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.displayOthnewLine[id] = false;
    let counter=0;

if(this.itemOthersList.length!=0)
{

    for(let j=0;j<this.itemOthersList.length;j++)
    {
     
if(this.itemOthersList[j].desSubItemId==id)
{
counter=1;
}

    }
   
  }
  if(counter==0)
  {
    this.displayCompOthTable[id]=false;
    this.othersCompCheck[id]=false;
  }
  }
  cancelnewLineOth22(id, othersfrm: NgForm) {
    othersfrm.reset();

    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
   
    this.displayOthnewLine[id] = false;
    let counter=0;

if(this.itemOthersList.length!=0)
{

    for(let j=0;j<this.itemOthersList.length;j++)
    {
     
if(this.itemOthersList[j].desItemId==id)
{
counter=1;
}

    }
   
  }
  if(counter==0)
  {
    this.displayCompOthTable[id]=false;
    this.othersCompCheck[id]=false;
  }
  }


    // adding new other lines
    addRowsOth(id) {
      this.mainSave=true;
      this.mainSave2=true;
          this.saveBtColor=true;
      this.displayOthnewLine[id] = true;
    }

    cancelLinesOth(i,id) {
      this.mainSave=true;
      this.mainSave2=true;
          this.saveBtColor=true;
      
      let name=this.itemOthersList[i].colValNm;
      let value=this.itemOthersList[i].colValCd;
      this.itemOthersList.splice(i, 1);
      let counter=0;
      this.disablelhs.splice(i,1);

      if(this.itemOthersList.length!=0)
{

    for(let j=0;j<this.itemOthersList.length;j++)
    {
     
if( this.itemOthersList[j].desItemId==id )
{
counter=1;
}

    }
   
  }
  if(counter==0)
  {
    this.displayCompOthTable[id]=false;
    this.othersCompCheck[id]=false;
  }
      for( let j=0;j<this.dboEleAuxFullArray.length;j++)
      {
        if(this.dboEleAuxFullArray[j].itemId==this.itemId && this.dboEleAuxFullArray[j].subItemId==this.subItemId)
        {
         
          if(this.dboEleAuxFullArray[j].itemOthersList.length!=0)
          {
            for(let i=0;i<this.dboEleAuxFullArray[j].itemOthersList.length;i++)
            {
              if(this.dboEleAuxFullArray[j].itemOthersList[i].itemId==this.itemId && this.dboEleAuxFullArray[j].itemOthersList[i].subItemId==this.subItemId && this.dboEleAuxFullArray[j].itemOthersList[i].colNm==name  && this.dboEleAuxFullArray[j].itemOthersList[i].colValCd==value )
              {
                this.dboEleAuxFullArray[j].itemOthersList[i]=null;
                this.dboEleAuxFullArray[j].itemOthersList.splice(i,1);
              }
            }
          }
        }
      }
    }
    cancelLinesOth1(i,id) {
      this.mainSave=true;
      this.mainSave2=true;
          this.saveBtColor=true;
      let name=this.itemOthersList[i].colValNm;
      let value=this.itemOthersList[i].colValCd;
      this.itemOthersList.splice(i, 1);
      let counter=0;

      if(this.itemOthersList.length!=0)
{

    for(let j=0;j<this.itemOthersList.length;j++)
    {
     
if(this.itemOthersList[j].desSubItemId==id )
{
counter=1;
}

    }
   
  }
  if(counter==0)
  {
    this.displayCompOthTable[id]=false;
    this.othersCompCheck[id]=false;
  }
      for( let j=0;j<this.dboEleAuxFullArray.length;j++)
      {
        if(this.dboEleAuxFullArray[j].itemId==this.itemId && this.dboEleAuxFullArray[j].subItemId==this.subItemId)
        {
         
          if(this.dboEleAuxFullArray[j].itemOthersList.length!=0)
          {
            for(let i=0;i<this.dboEleAuxFullArray[j].itemOthersList.length;i++)
            {
              if(this.dboEleAuxFullArray[j].itemOthersList[i].itemId==this.itemId && this.dboEleAuxFullArray[j].itemOthersList[i].subItemId==this.subItemId && this.dboEleAuxFullArray[j].itemOthersList[i].colNm==name  && this.dboEleAuxFullArray[j].itemOthersList[i].colValCd==value )
              {
                this.dboEleAuxFullArray[j].itemOthersList[i]=null;
                this.dboEleAuxFullArray[j].itemOthersList.splice(i,1);
              }
            }
          }
        }
      }
    }

    //on click of check or uncheck to adding Lhs or Removing Lhs inside panel
   lhsReqCheck(valu, k,event){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(valu, k);
    //this.checkKey = k;
    if(event.target.checked){
      this.lhsCheck[k] = true;
      this.lhsNmCheck[k] = true;

    }else if(!event.target.checked){
      this.lhsCheck[k] = false;
      this.lhsNmCheck[k] = false;
    }
  }

  //on click of check or uncheck to adding Rhs qunatity
  drpCheckNe(arrIn, c,arr, event ){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(arrIn, arr, event);
    console.log(c);
    if(event.target.checked){
    this.drpCheck[c] = true;
    this.dropCheckQty[c] = true;
    this.drpCheckqtyy[c]=0;
    }else if(!event.target.checked){
      this.drpCheck[c] = true;
      this.dropCheckQty[c] = false;
      this.drpCheckqtyy[c]=0;
    }
    console.log(this.dropCheckQty[c],c);
  }

  drpCheckQty(value, z, rhsValue,c){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(value);
    this.drpCheckqtyy[c]=value;
   this.drpCheckqtyyKey[z]=true;
  }
  //To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}  
/////////////////desSubItemName123

DessubItemOthForm(othersSubItem, othersSubItemfrm: NgForm,array){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(othersSubItem);
  this.dboClass = new dboClass();
  this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboClass.itemId = this.itemId;
  if(this.subItemId == 0 || this.subItemId == null){
    this.dboClass.subItemId = 0;
  }else {
    this.dboClass.subItemId = this.subItemId;           
  } 
  this.dboClass.desItemId=array.desItemId;
  this.dboClass.desItemName=null;

  this.dboClass.desSubItemId=null;
  this.dboClass.desSubItemName=othersSubItem.subItemName;
  this.dboClass.colId = 0;
  this.dboClass.colNm = null;
  this.dboClass.colValCd = null;
  this.dboClass.subColValCode=null;
  this.dboClass.quantity = null;         
  this.dboClass.cost = othersSubItem.subItemPrice;          
  this.dboClass.techRemarks = null;
  this.dboClass.comrRemarks = null;
  this.dboClass.addOnNewColFlag = 1;
  this.dboClass.orderId=0;
  this.dboClass.defaultFlagNew=1;
  this.dboClass.dispInd=1;
  this.dboClass.techFlag = 1;
  this.dboClass.comrFlag = 1;
 
  this.dessubItemOthAddonList.push(this.dboClass);
  this.lhsRhsDesSubItemsList.push(this.dboClass);//satya
  console.log(this.dessubItemOthAddonList);
  othersSubItemfrm.reset();
  this.dsplyDesSubItmOthnewLine = false;
}

 addLhsRhsDesSubItem(i){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.lhsRhsSubItemSel = ''; 
  this.ibreakothers = i;
  console.log(i);
  this.lhsRhsSubItemSel = this.dessubItemOthAddonList[i].desSubItemName;
  console.log(this.lhsRhsSubItemSel);
    this.dsplyDialogDesSubLhsRhs = true;
    this.dsplyDesSubItmOthnewLine = false;
}
//Canceling subItem others row
cancelnewLineDesSubItem(othersfrm: NgForm) {
  othersfrm.reset();
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.dsplyDesSubItmOthnewLine = false;
}
//Adding row for subitems others
addRowsDesSubItem() {
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.dsplyDesSubItmOthnewLine = true;
}
// submiting LHS and RHS recored for particular subitem (others)
addDesSubLhsRhsForm(lhsRhsSubItem, lhsRhsSubItemfrm: NgForm){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(lhsRhsSubItem);
  for(let k = 0; k < this.lhsRhsDesSubItemsList.length; k++)
  {
   if(this.lhsRhsDesSubItemsList[k].desSubItemName == this.dessubItemOthAddonList[this.ibreakothers].desSubItemName && this.lhsRhsDesSubItemsList[k].colId == 0 && this.lhsRhsDesSubItemsList[k].colNm == null  && this.lhsRhsDesSubItemsList[k].itemId==this.itemId && this.lhsRhsDesSubItemsList[k].subItemId==this.subItemId){
     this.lhsRhsDesSubItemsList.splice(k,1);
     this.ibreakothers = 1;
     break;          
   }
 }
 this.ibreakothers = 0;
  for(let c=0; c<this.dessubItemOthAddonList.length; c++){
    if(this.lhsRhsSubItemSel == this.dessubItemOthAddonList[c].desSubItemName  && this.dessubItemOthAddonList[c].itemId==this.itemId && this.dessubItemOthAddonList[c].subItemId==this.subItemId){
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.dessubItemOthAddonList[c].itemId;
  
        this.dboClass.subItemId = this.dessubItemOthAddonList[c].subItemId;           
      
      this.dboClass.desItemId=this.dessubItemOthAddonList[c].desItemId;
      this.dboClass.desItemName=this.dessubItemOthAddonList[c].desItemName;
  
      this.dboClass.desSubItemId=0;
      this.dboClass.desSubItemName=this.dessubItemOthAddonList[c].desSubItemName;
      this.dboClass.colId = 0;
      this.dboClass.colNm = lhsRhsSubItem.subLhsVal;
      this.dboClass.colValCd = lhsRhsSubItem.subRhsVal;
      this.dboClass.subColValCode=null;
      this.dboClass.quantity = null;         
      this.dboClass.cost = this.dessubItemOthAddonList[c].cost;          
      this.dboClass.techRemarks = null;
      this.dboClass.comrRemarks = null;
      this.dboClass.addOnNewColFlag = 1;
      this.dboClass.orderId=0;
      this.dboClass.defaultFlagNew=1;
      this.dboClass.dispInd=1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
    
            
      this.lhsRhsDesSubItemsList.push(this.dboClass);
    }
  }
  console.log(this.lhsRhsDesSubItemsList);
  lhsRhsSubItemfrm.reset();
  this.lhsRhsDesSubnewLine = false;
  this.dsplyDesSubItmOthnewLine = false;
}
cancelLinesDesSubItemOth(i) {
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  for(let j = 0; j < this.lhsRhsDesSubItemsList.length; j++)
  {
    //Need to remove the lhsrhsitemlist also
    if(this.dessubItemOthAddonList[i].desItemName == this.lhsRhsDesSubItemsList[j].desItemName && this.lhsRhsDesSubItemsList[j].itemId==this.itemId && this.lhsRhsDesSubItemsList[j].subItemId==this.subItemId )
    {
      //this.lhsRhsItemsList[j] = null;  // ADDED SATYA
     this.lhsRhsDesSubItemsList.splice(j, 1);
     j = j - 1;
     this.rhsdes=[];
    }
  }
  this.dessubItemOthAddonList.splice(i, 1);
  this.lhsdes.splice(i,1);

}
// ADd row for LHS/RHs for new subitem (others)
addRowsDesSubLhsRhs(){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.lhsRhsDesSubnewLine = true;
}
// To cancel LHS/RHS record for new  subitem (others)
cancelnewLineDesSubLhs(othersfrm: NgForm){
  othersfrm.reset();
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.lhsRhsDesSubnewLine = false;

}
cancelLinesDesSubLhs(i){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.lhsRhsDesSubItemsList.splice(i, 1);
  this.rhsdes.splice(i,1);
  }
  openDesSubItemTable(event){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if(event.target.checked){
      this.displayDesSubItemOthTable = true;
    }else if(!event.target.checked){
      this.displayDesSubItemOthTable = false;
      for(let x=0;x<this.dessubItemOthAddonList.length;x++)
      {
        if(this.itemId==this.dessubItemOthAddonList[x].itemId && this.subItemId==this.dessubItemOthAddonList[x].subItemId )
        {
          this.dessubItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsDesSubItemsList.length;x++)
      {
        if(this.itemId==this.lhsRhsDesSubItemsList[x].itemId && this.subItemId==this.lhsRhsDesSubItemsList[x].subItemId )
        {
          this.lhsRhsDesSubItemsList[x]=null;  
        }
      }
      this.dessubItemOthAddonList = this.dessubItemOthAddonList.filter(n => n != null);
      this.lhsRhsDesSubItemsList = this.lhsRhsDesSubItemsList.filter(n => n != null);

    }
    this.lhsdes=[];
    this.rhsdes=[];
  }

/////////////

////desItemName123
subItemOthForm(othersSubItem, othersSubItemfrm: NgForm){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(othersSubItem);
  this.dboClass = new dboClass();
  this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboClass.itemId = this.itemId;
  if(this.subItemId == 0 || this.subItemId == null){
    this.dboClass.subItemId = 0;
  }else {
    this.dboClass.subItemId = this.subItemId;           
  } 
  this.dboClass.desItemId=0;
  this.dboClass.desItemName=othersSubItem.subItemName;

  this.dboClass.desSubItemId=null;
  this.dboClass.desSubItemName=null;
  this.dboClass.colId = 0;
  this.dboClass.colNm = null;
  this.dboClass.colValCd = null;
  this.dboClass.subColValCode=null;
  this.dboClass.quantity = null;         
  this.dboClass.cost = othersSubItem.subItemPrice;          
  this.dboClass.techRemarks = null;
  this.dboClass.comrRemarks = null;
  this.dboClass.addOnNewColFlag = 1;
  this.dboClass.orderId=0;
  this.dboClass.defaultFlagNew=1;
  this.dboClass.dispInd=1;
  this.dboClass.techFlag = 1;
  this.dboClass.comrFlag = 1;
 
  this.subItemOthAddonList.push(this.dboClass);
  this.lhsRhsSubItemsList.push(this.dboClass);//satya
  console.log(this.subItemOthAddonList);
  othersSubItemfrm.reset();
  this.dsplySubItmOthnewLine = false;
}

 addLhsRhsSubItem(i){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.lhsRhsSubItemSel = ''; 
  this.ibreakothers = i;
  console.log(i);
  this.lhsRhsSubItemSel = this.subItemOthAddonList[i].desItemName;
  console.log(this.lhsRhsSubItemSel);
    this.dsplyDialogSubLhsRhs = true;
    this.dsplySubItmOthnewLine = false;
}
//Canceling subItem others row
cancelnewLineSubItem(othersfrm: NgForm) {
  othersfrm.reset();

  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.dsplySubItmOthnewLine = false;
}
//Adding row for subitems others
addRowsSubItem() {
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.dsplySubItmOthnewLine = true;
}
// submiting LHS and RHS recored for particular subitem (others)
addSubLhsRhsForm(lhsRhsSubItem, lhsRhsSubItemfrm: NgForm){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(lhsRhsSubItem);
  for(let k = 0; k < this.lhsRhsSubItemsList.length; k++)
  {
   if(this.lhsRhsSubItemsList[k].desItemName == this.subItemOthAddonList[this.ibreakothers].desItemName && this.lhsRhsSubItemsList[k].colId == 0 && this.lhsRhsSubItemsList[k].colNm == null &&     this.lhsRhsSubItemsList[k].itemId==this.itemId &&     this.lhsRhsSubItemsList[k].subItemId==this.subItemId){
     this.lhsRhsSubItemsList.splice(k,1);
     this.ibreakothers = 1;
     break;          
   }
 }
 this.ibreakothers = 0;
  for(let c=0; c<this.subItemOthAddonList.length; c++){
    if(this.lhsRhsSubItemSel == this.subItemOthAddonList[c].desItemName   && this.subItemOthAddonList[c].itemId==this.itemId && this.subItemOthAddonList[c].subItemId==this.subItemId){
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.subItemOthAddonList[c].itemId;
  
        this.dboClass.subItemId = this.subItemOthAddonList[c].subItemId;           
      
      this.dboClass.desItemId=0;
      this.dboClass.desItemName=this.subItemOthAddonList[c].desItemName;
  
      this.dboClass.desSubItemId=0;
      this.dboClass.desSubItemName=null;
      this.dboClass.colId = 0;
      this.dboClass.colNm = lhsRhsSubItem.subLhsVal;
      this.dboClass.colValCd = lhsRhsSubItem.subRhsVal;
      this.dboClass.subColValCode=null;
      this.dboClass.quantity = null;         
      this.dboClass.cost = this.subItemOthAddonList[c].cost;          
      this.dboClass.techRemarks = null;
      this.dboClass.comrRemarks = null;
      this.dboClass.addOnNewColFlag = 1;
      this.dboClass.orderId=0;
      this.dboClass.defaultFlagNew=1;
      this.dboClass.dispInd=1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
    
            
      this.lhsRhsSubItemsList.push(this.dboClass);
    }
  }
  console.log(this.lhsRhsSubItemsList);
  lhsRhsSubItemfrm.reset();
  this.lhsRhsSubnewLine = false;
  this.dsplySubItmOthnewLine = false;
}
cancelLinesSubItemOth(i) {
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  for(let j = 0; j < this.lhsRhsSubItemsList.length; j++)
  {
    //Need to remove the lhsrhsitemlist also
    if(this.subItemOthAddonList[i].desItemName == this.lhsRhsSubItemsList[j].desItemName && this.subItemOthAddonList[i].itemId == this.lhsRhsSubItemsList[j].itemId && this.subItemOthAddonList[i].subItemId == this.lhsRhsSubItemsList[j].subItemId )
    {
      //this.lhsRhsItemsList[j] = null;  // ADDED SATYA
     this.lhsRhsSubItemsList.splice(j, 1);

     j = j - 1;
     this.rhsdes2=[];
    }
  }
  this.subItemOthAddonList.splice(i, 1);
  this.lhsdes2.splice(i,1);

}
// ADd row for LHS/RHs for new subitem (others)
addRowsSubLhsRhs(){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.lhsRhsSubnewLine = true;
}
// To cancel LHS/RHS record for new  subitem (others)
cancelnewLineSubLhs(othersfrm: NgForm){
  othersfrm.reset();
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.lhsRhsSubnewLine = false;

}
cancelLinesSubLhs(i){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  this.lhsRhsSubItemsList.splice(i, 1);
  this.rhsdes2.splice(i,1);

  }

 openSubItemTable(event){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(event.target.checked){
    this.displaySubItemOthTable = true;
  }else if(!event.target.checked){
    this.displaySubItemOthTable = false;
    for(let x=0;x<this.subItemOthAddonList.length;x++)
    {
      if(this.itemId==this.subItemOthAddonList[x].itemId && this.subItemId==this.subItemOthAddonList[x].subItemId)
      {
        this.subItemOthAddonList[x]=null;  
      }
    }
    for(let x=0;x<this.lhsRhsSubItemsList.length;x++)
    {
      if(this.itemId==this.lhsRhsSubItemsList[x].itemId && this.subItemId==this.lhsRhsSubItemsList[x].subItemId  )
      {
        this.lhsRhsSubItemsList[x]=null;  
      }
    }
    this.subItemOthAddonList = this.subItemOthAddonList.filter(n => n != null);
    this.lhsRhsSubItemsList = this.lhsRhsSubItemsList.filter(n => n != null);

  }
  this.lhsdes2=[];
  this.rhsdes2=[];
}
lengthcheck(value,key,maxvalue)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  var numberValue = Number(value);
  if(numberValue<1 || isNaN(numberValue)  )
  {
    this.newAddNameO[key]="";


  }
  if(this.itemCode=='HVB' || this.itemCode=='LVB')
  {
    if(numberValue<1 || isNaN(numberValue) || numberValue>maxvalue)
    {
     
      this.newAddNameO[key]="";

    }  }



 
 
   
 
}
clearerror()
{
  this.Ifapplicable=[];

}
/////



editlhs(i)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
      this.disablelhs[i]=true;

    
  }
  savelhs(i,type)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
      this.disablelhs[i]=false;

    
  }

  /// new des item edit
  editlhsdes(i,value)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if(value=='lhs')
    {
      this.lhsdes2[i]=true;

    }
    else
    {
      this.rhsdes2[i]=true;

    }


  }
  savelhsdes(i,value,name,item,type)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if(name=='lhs')
    {
      this.newdesitem(i,value,name,item,type);
      this.lhsdes2[i]=false;


    }
    else
    {
      this.rhsdes2[i]=false;

    }

  }
  newdesitem(i,value,name,item,type)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if(name=='lhs')
    {
      this.lhsdes2[i]=true;
      this.subItemOthAddonList[i].cost=value;
      for(let j=0;j<this.lhsRhsSubItemsList.length;j++)
      {
        if(this.lhsRhsSubItemsList[j].desItemName==item)
        {
          this.lhsRhsSubItemsList[j].cost=value;
        }
      }
    }
    else{
      this.rhsdes2[i]=true;
      
        if(this.lhsRhsSubItemsList[i].desItemName==item)
        {
          if(type=='colNm')
          {
            this.lhsRhsSubItemsList[i].colNm=value;

          }
          else
          {
            this.lhsRhsSubItemsList[i].colValCd=value;

          }
        }
      
    }

   
  }


  /// des sub item edit

  checkForQuantity1223(i,value,name,item,type)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if(name=='lhs')
    {
      this.lhsdes[i]=true;
      this.dessubItemOthAddonList[i].cost=value;
      for(let j=0;j<this.lhsRhsDesSubItemsList.length;j++)
      {
        if(this.lhsRhsDesSubItemsList[j].desSubItemName==item)
        {
          this.lhsRhsDesSubItemsList[j].cost=value;
        }
      }
    }
    else{
      this.rhsdes[i]=true;
      
        if(this.lhsRhsDesSubItemsList[i].desSubItemName==item)
        {
          if(type=='colNm')
          {
            this.lhsRhsDesSubItemsList[i].colNm=value;

          }
          else
          {
            this.lhsRhsDesSubItemsList[i].colValCd=value;

          }
        }
      
    }

   
  }

  editlhsdessub(i,value)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if(value=='lhs')
    {
      this.lhsdes[i]=true;

    }
    else
    {
      this.rhsdes[i]=true;

    }


  }
  subItemSave()
  {

    // if (this.enableOverwriteDiv) {
    //   this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleAuxCost;
    //   this.dboFormData.overwrittenPriceFlag = 1;
    //   this.dboFormData.remarks = this.remarks;
    // } else if (this.OverWrittenfinalEleAuxCost > 0) {
    //   this.dboFormData.overwrittenPrice = this.OverWrittenfinalEleAuxCost;
    // //  this.panelList.overwrittenPriceFlag = 1;
    // }
   // this.dboFormData.modBy = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.itemId = this.itemId;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails; 
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormData.eleItemTechComments = this.subItemRmkValOut;
    this.dboFormData.eleItemComrComments = this.subItemCmrRemValOut;
    this.dboFormData.eleSubItemIdList = this.itemIdList1.filter(n => n != null);
    console.log(this.itemIdList)
    if(this.storage.get(this.dboEleFull)!=null)
    {
      if(this.storage.get(this.dboEleFull).itemIdList1.length!=0){
        this.itemIdListAux= this.storage.get(this.dboEleFull).itemIdList1;
      for(let j=0;j<this.itemIdListAux.length;j++)
      {
        if(this.itemIdListAux[j]!=null)
        {
  
        
        this.dboFormData.eleSubItemIdList.push(this.itemIdListAux[j]);
        }
      }
      }
    }
    
   // this.dboFormData.savedEleDataList = this.lhsRhsItemsList;
    this._DboEleAuxialriesService.saveEleSubItem(this.dboFormData).subscribe(savedResp => {
      console.log(savedResp);
   this.saveSubCost=savedResp.totalPrice;
    this._ITOeditQoutService.dboEleItmOthers = savedResp.saveEleList;
    this.subItemButton=false;
    if(savedResp.successCode == 0){
      this.finalCostBool = true;
      this.message = true;
      this.successMsg = "Cost Saved successfully";
    }else{
      this.message = true;
      this.successMsg = savedResp.successMsg;
    }
    this.saveInLocal(this.dboEleAuxFull, {
      dboEleAuxFullArray: this.dboEleAuxFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
      finalEleAuxCost: this.finalEleAuxCost,saveSubCost:this.saveSubCost, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
    });
  });
  }

  savelhsdessub(i,value,name,item,type)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if(name=='lhs')
    {      this.checkForQuantity1223(i,value,name,item,type);

      this.lhsdes[i]=false;

    }
    else
    {
      this.rhsdes[i]=false;

    }

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
showDialogMaximized(event, dialog: Dialog){
  dialog.maximized = false;
  dialog.toggleMaximize(event);
}
  }
