import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { DBOElectricalComponentService } from '../dboelectrical/dboelectrical.service';
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
import { ItoControlInstrumentationService } from  "./ito-control-instrumentation.service";
import { Dialog } from 'primeng/dialog';


@Component({
  selector: 'app-ito-control-instrumentation',
  templateUrl: './ito-control-instrumentation.component.html',
  styleUrls: ['./ito-control-instrumentation.component.css']
})
//export class ItoControlInstrumentationComponent implements OnInit 



export class ItoControlInstrumentationComponent implements OnInit, AfterContentChecked {

  costQty: Array<number> = []; //To Store cost for dropdown specific input field when inputCostFlag is 1
  inptCstQty: Array<boolean> = []; //To enable or disable input field for quantity inside panle for specfic dropdown change if inputCostFlag is 1
  footerText :Array<any> = [];//To display panel and item footer
  headerText: Array<any> = []; // To display panel and item header
  errMsgPnl:  Array<any> = []; //To display error msg if error msg is not equal to null on hitting getEleTechPrice
  errDisplayPnl:  Array<boolean> = []; //To Display error msg div if error msg is not equal to null on hitting getEletechPrice
  errDisplayRhsCost: Array<boolean> = []; //To Display error msg div if addOnflg is 1 on hitting getEletechPrice
  errMsgRhsCost: Array<any> = []; //To display error msg if addonflg is 1 on hitting getEleTechPrice
  errMsgRed: Array<boolean> = []; //To display Addon cost in red color based on approxCostFlag on hitting getELeTechPrice
  errMsgBlue: Array<boolean> = []; //To display Addon cost in red color based on approxCostFlag on hitting getELeTechPrice
  errMsgGreen: Array<boolean> = []; //To display Addon cost in red color based on approxCostFlag on hitting getELeTechPrice
  eleItemId: number; //store unique id that is eleItemId on selection of item
  dboEleCIFullArray: Array<any> = []; //store complete data from getEleTechPrice
  dboCost: number; //basiccost from getEleTechPrice to display in UI Pannel cost
  tempitemOthersList:Array<any> = []; //store other attributes (new Lhs and Rhs) inside the panel
  dboAddOnCost:any; //store Ele addon cost from getEleTecPrice
 
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
  hideprogressCost1: boolean = false;
  itemRemarkaddon: Array<boolean> = [];
  iItemindex: number = 0;
  hideprogressCost: boolean = false;
  itemtempId : number = 0;
  iFlag : number = 0;
  index: number = 0;
  count : number = 0;
  itemIdList: Array<number> = [];
  itemIdListAux: Array<number> = [];
  techCheckIn: boolean = false; //boolean to check techinal remarks
  comrrCheck: boolean = false; //boolean to check commercial remarks
  itemIdList1: Array<number> = [];
  dboElePanelList2: Array<any> = []; //To store dboElePanelList2 data from getElePanel
  dboElePanelList1: Array<any> = []; //to store dboElePanelList1 data from getElePanel
  questionsBean: Array<any> = []; // to store filtered questionsBean data from questionsBean1
  questionsBean1: Array<any> =[]; //to store questionsBean data from getElePanel
  questionsBeantemp: Array<any> =[]; //to store questionsBean data from getElePanel

  questionsBean2: Array<any> =[]; //to store questionsBean data from getElePanel
  questionsBean3: Array<any> =[]; //to store questionsBean data from getElePanel
  defaultValues: Array<any> = [];  // To store default values of questionsBean list
  defaultValues2: Array<any> = [];  // To store default values of questionsBean list
  techPriceResp:any; //storing respone from getEleTechPrice
  colValNmId: number; //store col Id
  subMessage: boolean = false;
  displayEleSubList: boolean = false;
  itemName: string;
  messageVal:string;
  message: boolean = false; //To display message
  successMsg: string = ''; // To display success message
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
  itemOthersList1:Array<any> = []; //store other attributes (new Lhs and Rhs) inside the panel
  itemOthersList:Array<any> = []; //store other attributes (new Lhs and Rhs) inside the panel
vmsCounter:Array<any> = [];
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
  dboEleCIFull: string = 'dboEleCIFull';
  dboElectricalLocalCI: Array<any> = [];
  othItmTechRemChk:boolean=false;
  linkFlag:boolean=false;
  itemtechRemarkCheck:boolean=false;
  othItmComrRemChk:boolean=false;
  itemComrRemarkCheck:boolean=false;
  othSubItmTechRemChk:boolean=false;
  othSubItmComrRemChk:boolean=false;
  subItemTchRem: boolean = false;
  subItemComRem: boolean = false;
  displaySubItemOthTable:boolean = false; //To enable others table on check of other in creating new subitem(others)
  subItemOthAddonList: Array<any> = []; //To push new subitems from others
  lhsRhsSubItemsList: Array<any> = []; // To push LHS/RHS values for new subitem (others)
  dsplySubItmOthnewLine:boolean = false; //To enable and disable div for entering inputs in subitem others
  lhsRhsSubItemSel: string = ''; //To store header name of newely created subitem(others)
  ibreakothers: number = 0;
  dsplyDialogSubLhsRhs:boolean = false; //To disable or enable div for entering inputs for adding new LHS/RHS  table in subitem(others)
  lhsRhsSubnewLine: boolean = false; //To disable or enable div for entering inputs for adding new LHS/RHS in subitem(others)
  rhs: Array<boolean> = []; //for disabling dropdown inside panel
  groupName:Array<any> = [];
  groupNameNew:Array<any> = []; 
  groupNameNew2:Array<any> = []; 
  displayPanel:Array<boolean> = []; 
  tempform:Array<any>=[];
  childvalues:Array<any>=[];
  childvaluesnew:Array<any>=[];
  childvaluestemp:Array<any>=[];
  childvaluestemp2:Array<any>=[];
  newvalues:Array<any>=[];
  itemOthersAddonList:Array<any>=[];
  itemOthersAddonList1:Array<any>=[];

  lhsRhsItemsList:Array<any>=[];
  displayItemCompOthTable:boolean=false;
  displayItmOthnewLine:boolean=false;
  lhsRhsItemSel:string;
  displayDialogLhsRhs:boolean=false;
  lhsRhsnewLine:boolean=false;
  lhsRhsItemsList1:Array<any>=[];
  displayItemCompOthTabl1e:boolean=false;
  displayItmOthnewLine1:boolean=false;
  lhsRhsItemSel1:string;
  displayDialogLhsRhs1:boolean=false;
  lhsRhsnewLine1:boolean=false;
  //enableOverwriteDiv:number=0;
  remarks:string;
  OverWrittencICost:number=0;
  cICost:any='';
  totalDboCiCost: any = '';
  finalcostflag:boolean=false;
 enableOverwriteDiv:boolean=false;
 disableStatus:boolean=false;
 oneLineLoc: string = 'oneLineLoc';
 temparray:Array<any>=[];
 newvalues1:Array<any>=[];
 temparray1:Array<any> = []; //To store dboEleData 
itemSelectedListEdit:Array<any> = []; //To add Rhs for existing lhs
subitemSelectedListEdit:Array<any> = []; //To add Rhs for existing lhs
tempaddonsubitem: Array<any> = []; //To store itemOthersList in edit mode
tempaddonrhs: Array<any> = []; //To store addonlist in edit mode
defaultValues1: Array<any> = []; //To store default values in edit mode
newSet: Array<any> = []; // To store unique item names from temparray1
prevData: Array<any> = []; //To store filtered records from temparray1
oneLineLocArray: any;
othersCheck: boolean = false; // enable new item others table
othersSubCheck: boolean = false; //Enable new subitem table
childvaluestemp1:Array<any>=[];
 defaultValuesid:Array<any>=[];
 defaultlhs:Array<boolean>=[];
 addOnCost:Array<any>=[];
 itemOthersAddonListTemp: Array<any> = []; //To store newly created items from edit mode(others)
 lhsRhsItemsListTemp:Array<any> = []; // To store newly created items Lhs/Rhs from edit mode(others)
 defaultValuesid1:Array<any>=[];  // ALI
 displayCompOthTable:Array<boolean> = []; //open others attribute to add new LHS and RHS
 displayCompOthTable1:Array<boolean> = []; //open others attribute to add new LHS and RHS

 othersCompCheck:Array<boolean> = []; //flag to make others check box (itemOthersList)
 displayOthnewLine:Array<boolean> = [];//open others attribute to enter new LHS and RHS values
 displayOthnewLine1:Array<boolean> = [];//open others attribute to enter new LHS and RHS values
 othCheck:Array<boolean> = [];
 enablebutton:boolean=false;
 enablebuttonNew:boolean=false;
 tempArray:Array<any>=[];
 newArray12:Array<any>=[];
 dboEleFilterListNew:Array<any>=[];
 defaulVales1:Array<any>=[];
 newArryUpd:Array<any>=[];
 lhsCheck: Array<boolean> = []; //Boolean to check or un check LHS inside the panel 
 drpCheck: Array<boolean> = []; //Boolean to check or uncheck Rhs quantity inside the panel
 dropCheckQty: Array<boolean> = []; //Boolean to open or close quantity input field inside panel
 drpCheckqtyy: Array<any> = []; //To store dropDownCheckBox quantity
 drpCheckqtyyKey: Array<boolean> = []; //To store dropDownCheckBox key
 lhsNmCheck: Array<boolean> = []; //To store dropDownCheckBox key
 dboEleAuxFull: string = 'dboEleAuxFull';
newPanel:Array<boolean>=[];
newPanelBol:Array<boolean>=[];
newPanelval:Array<any>=[];
addonflg="0";
itemcost:number=0;
checkboolean:boolean=false;
enablebtn:boolean=false;
panelType:string="";
backBtn: boolean = false;
displaypanel12:boolean=false;
dboEleInstrList:Array<any>=[];
dboEleInstrListNew:Array<any>=[];
dboEleInstrListNew1:Array<any>=[];
dboEleInstrListNew2:Array<any>=[];
dboEleInstrListNew3:Array<any>=[];
dsplySubItmTypOthTable:boolean = false;
subItmTypOthList:Array<any> = [];
lhsRhsSubItmTypSel: string;
dsplySubItmTypOthnwLin:boolean = false;
dsplyDlgSubTypLhsRhs:boolean = false;  
lhsRhsSubItmTypList: Array<any> = [];
lhsRhsSubTypnewLine:boolean = false;
Cost: Array<any> = [];
othersSubItmTypChk: Array<boolean> = [];
othersSubTypCheck: Array<boolean> = [];
displayOthnewLine2:Array<boolean> = [];
instdata: Array<any> = [];
displayvms:boolean=false;
itemOthersList2:Array<any>=[];
check:Array<boolean>=[];
check2:Array<boolean>=[];
enablebtn1:boolean=false;
saveBtColor:boolean=true;
buttonColor:string="rgb(213,120,23)";
Ifapplicable:Array<any>=[];
specialnote:string=null;
enableFilterButton:boolean=false;
panelapplicable:boolean=false;
othersSubItemCheck:boolean=false;
deItemLevelOthers:Array<boolean>=[];
desItemNamList:Array<any>=[];
desSubItemNamList:Array<any>=[];
panelapplicablescm:boolean=false;
itemCd:string="";
colNumber:Array<any>=[];
Ifapplicabletemp:Array<any>=[];
alert:string="                     !!!!!ALERT!!!!";
defaultvalueshieght:Array<any>=[];
techFlagindicator: Array<boolean> = [];
diableitemname:boolean=true;
getPrice:boolean=true;
getPricestg:boolean=true;
getPricevms:boolean=true;


mainSave:boolean=true;
mainSaveVms:boolean=true;

mainSave2:boolean=true;
itemCode:string='';

///// desSubItemName123
othersDesSubCheck:boolean=false;
othersDesSubItemCheck:boolean=false;
displayDesSubItemOthTable:boolean = false; //To enable others table on check of other in creating new subitem(others)
  dessubItemOthAddonList: Array<any> = []; //To push new subitems from others
  lhsRhsDesSubItemsList: Array<any> = []; // To push LHS/RHS values for new subitem (others)
  dsplyDesSubItmOthnewLine:boolean = false; //To enable and disable div for entering inputs in subitem others
  lhsRhsDesSubItemSel: string = ''; //To store header name of newely created subitem(others)
  dsplyDialogDesSubLhsRhs:boolean = false; //To disable or enable div for entering inputs for adding new LHS/RHS  table in subitem(others)
  lhsRhsDesSubnewLine: boolean = false; //To disable or enable div for entering inputs for adding new LHS/RHS in subitem(others)
////
finalCostBool: boolean = false;


/////others lhs 
disablelhs: Array<boolean> = [];
disablelhssub: Array<boolean> = [];


lhsdes: Array<boolean> = [];
rhsdes: Array<boolean> = [];
lhsdes2: Array<boolean> = [];
rhsdes2: Array<boolean> = [];

/// edit vms
disablelhsvms: Array<boolean> = [];
////edit stg
disablelhstg: Array<boolean> = [];
disablerhstg: Array<boolean> = [];
scopeofsupp: string = 'scopeOfsup';

user: string = 'userDetail';
currentRole: string = 'selRole';
currentRoleId: string = 'selRoleId';
rewApp: boolean = false;
discountPer: number = 0;
AltMakeArray:Array<any>=[];
geararray:Array<any>=[];
typearray:Array<any>=[];
makearray:Array<any>=[];
AltMakeVms: string = '';
gearvms: string = '';
typevms: string = '';
makevms: string = '';
totalVmsList:Array<any>=[];
col:number=1908;
defaultVms:Array<any>=[];
appDisable: boolean=false;
makeButton:boolean=true;
counterstg:number=0;
refreshBoolean:boolean=true;
ciList:Array<any>=[];


  constructor(private _DBOElectricalComponentService: DBOElectricalComponentService, private _ITOLoginService: ITOLoginService,
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
    this._ITOeditQoutService.button1=false;
    this._ITOeditQoutService.button2=false;
    this._ITOeditQoutService.button3=false;
    this._ITOeditQoutService.button4=false;
    this._ITOeditQoutService.button5=false;
    this._ITOeditQoutService.button6=false;
    this._ITOeditQoutService.button7=true;
    this._ITOeditQoutService.button8=false;
    this._ITOeditQoutService.button9=false;
    if(currentRole == "QUOT_REVIWER" || currentRole == "QUOT_APPROVER"){
            this.rewApp = true;
          }
for(let j=0;j<1000;j++)
{
  this.ciList.push(j);
}
    //To set to null in the beginning of the edit mode
       this.selectdEl = [];
       this.selectdEl1 = [];
       this.temparray1 = [];
       if (this.storage.get(this.dboEleCIFull) == null) {
        this.saveInLocal(this.dboEleCIFull, {
          dboEleCIFullArray: this.dboEleCIFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
          itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
          selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
          itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
          subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
          eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
          cICost: this.cICost, totalDboCiCost: this.totalDboCiCost, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList, itemOthersList1: this.itemOthersList1, itemOthersList2: this.itemOthersList2,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
        });
      }
       console.log(this._ITOeditQoutService.dboEleCiData);
       if(this._ITOeditQoutService.dboEleCiData.length>0){
         this.finalCostBool = true;
         this.mainSave2=false;
  this.mainSave=false;
       }
       console.log(this._ITOeditQoutService.dboEleItmOthers);
       if(this._ITOeditQoutService.checkEdit == false){
        this.backBtn = true;
      }
      if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
        this.appDisable=true;
      }else{
        this.appDisable=false;
      }
      this.instdata= this._ITOeditQoutService.saveEleFilterList1;
      console.log(this._ITOeditQoutService.saveEleFilterList1);
      if(this.storage.get(this.dboEleCIFull).itemOthersList1.length != 0){
        this.itemOthersList1 = this.storage.get(this.dboEleCIFull).itemOthersList1;
      }
      if(this.storage.get(this.dboEleCIFull).itemOthersList2.length != 0){
        this.itemOthersList2= this.storage.get(this.dboEleCIFull).itemOthersList2;
      }
      if(this.storage.get(this.dboEleCIFull).subItemOthAddonList.length!=0 ){
        this.lhsRhsSubItemsList= this.storage.get(this.dboEleCIFull).lhsRhsSubItemsList;
          this.subItemOthAddonList=this.storage.get(this.dboEleCIFull).subItemOthAddonList;
        }
        if(this.storage.get(this.dboEleCIFull).dessubItemOthAddonList.length!=0 ){
          this.lhsRhsDesSubItemsList= this.storage.get(this.dboEleCIFull).lhsRhsDesSubItemsList;
          this.dessubItemOthAddonList=this.storage.get(this.dboEleCIFull).dessubItemOthAddonList;
        
          }
      if(this._ITOcustomerRequirementService.editFlag==true && this._ITOeditQoutService.checkEdit==false && this._ITOeditQoutService.saveVmsDataList.length!=0)
      {
        this.itemOthersList2=[];
        for(let h=0;h<this._ITOeditQoutService.saveVmsDataList.length;h++)
        {
          if((this._ITOeditQoutService.saveVmsDataList[h].addPrbFlag==1 ) )
          {

          
            this.dboClass = new dboClass();
            this.dboClass.type=this._ITOeditQoutService.saveVmsDataList[h].type;
            this.dboClass.addPrbFlag=this._ITOeditQoutService.saveVmsDataList[h].addPrbFlag;
            this.dboClass.orderBy=this._ITOeditQoutService.saveVmsDataList[h].orderBy;
            this.dboClass.tagNum=this._ITOeditQoutService.saveVmsDataList[h].tagNum;
            this.dboClass.equipment=this._ITOeditQoutService.saveVmsDataList[h].equipment;
            this.dboClass.application=this._ITOeditQoutService.saveVmsDataList[h].application;
            this.dboClass.location=this._ITOeditQoutService.saveVmsDataList[h].location;
            this.dboClass.quantity=this._ITOeditQoutService.saveVmsDataList[h].quantity;
            this.dboClass.cost=this._ITOeditQoutService.saveVmsDataList[h].cost;
              this.dboClass.newColValFlag=this._ITOeditQoutService.saveVmsDataList[h].newColValFlag;

           
            this.itemOthersList2.push(this.dboClass);
          }
          if((this._ITOeditQoutService.saveVmsDataList[h].addPrbFlag==0 ) && (this._ITOeditQoutService.saveVmsDataList[h].newColValFlag==1)   )
          {

          
            this.dboClass = new dboClass();
            this.dboClass.type=this._ITOeditQoutService.saveVmsDataList[h].type;
            this.dboClass.addPrbFlag=this._ITOeditQoutService.saveVmsDataList[h].addPrbFlag;
            this.dboClass.orderBy=this._ITOeditQoutService.saveVmsDataList[h].orderBy;
            this.dboClass.tagNum=this._ITOeditQoutService.saveVmsDataList[h].tagNum;
            this.dboClass.equipment=this._ITOeditQoutService.saveVmsDataList[h].equipment;
            this.dboClass.application=this._ITOeditQoutService.saveVmsDataList[h].application;
            this.dboClass.location=this._ITOeditQoutService.saveVmsDataList[h].location;
            this.dboClass.quantity=this._ITOeditQoutService.saveVmsDataList[h].quantity;
            this.dboClass.cost=this._ITOeditQoutService.saveVmsDataList[h].cost;
            this.dboClass.newColValFlag=this._ITOeditQoutService.saveVmsDataList[h].newColValFlag;
            this.itemOthersList2.push(this.dboClass);
          }
        }
      }
      if(this.instdata!=undefined && this._ITOcustomerRequirementService.editFlag==true && this._ITOeditQoutService.checkEdit==false)
      {

        if(this.instdata.length!=0 && this._ITOcustomerRequirementService.editFlag==true )
        {
  
      for(let l=0;l<this.instdata.length;l++)
      {

         if(this.instdata[l].rhsFlag==true || this.instdata[l].addOnNewColFlag==1   )
         {
          this.dboClass = new dboClass();
          this.dboClass.itemId =this.instdata[l].itemId;
          this.dboClass.itemNm =null;
          if(this.instdata[l].addOnNewColFlag==1)
          {
            this.dboClass.colId =this.instdata[l].colId;
          }
          else
          {
            this.dboClass.colId =this.instdata[l].colId;
          }
          if(this.instdata[l].addOnNewColFlag==1)
          {
            this.dboClass.lhsColNm =this.instdata[l].colNm;
          }
          else
          {
            this.dboClass.lhsColNm =null;
          }
         
          this.dboClass.colValCd =this.instdata[l].colValCd;
          this.dboClass.subColValCode =this.instdata[l].subColValCode;
          this.dboClass.quantity =this.instdata[l].rhsColQuantity;
        //  this.dboClass.cost =others.othcost;

            this.dboClass.cost=this.instdata[l].rhsCost;
          
            
         
          if(this.instdata[l].addOnNewColFlag==1)
          {
            this.dboClass.addOnNewColFlag =1;
          }
          else
          {
            this.dboClass.addOnNewColFlag =0;
          }
          if(this.instdata[l].addOnNewColFlag==1)
          {
            this.dboClass.otherItemInstrFlag =0;
          }
          else
          {
            this.dboClass.otherItemInstrFlag =0;
          }
      
          this.itemOthersList1.push(this.dboClass);
      
         }
        
      }
    }
    }
       if( this._ITOcustomerRequirementService.editFlag==true)
       {
        this.temparray1=this._ITOeditQoutService.dboEleCiData;

       }
       if( this.temparray1.length>0)
       {
        this.mainSave2=false;
        this.mainSave=false;
       }

       
    this._ITOturbineConfigService.getDboFormData().subscribe(ress => {
      console.log(ress);
      this.dboFormData = ress;
      this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboElectricalLocalCI[this.dboEleCIFull] = this.storage.get(this.dboEleCIFull);
      console.log(this.storage.get(this.dboEleCIFull));
     

    if(this.storage.get(this.dboEleCIFull).dboEleCIFullArray.length!=0){
      this.finalCostBool = true;
      this.dboEleCIFullArray= this.storage.get(this.dboEleCIFull).dboEleCIFullArray;
      this.mainSave2=false;
      this.mainSave=false;
    
   }
   if(this.storage.get(this.dboEleCIFull).addOnList.length!=0){
    this.addOnList= this.storage.get(this.dboEleCIFull).addOnList;
  
 }
 if(this.storage.get(this.dboEleCIFull).itemIdList.length!=0){
  this.itemIdList= this.storage.get(this.dboEleCIFull).itemIdList;

}
if(this.storage.get(this.dboEleCIFull).itemIdList1.length!=0){
this.itemIdList1= this.storage.get(this.dboEleCIFull).itemIdList1;

}
if(this.storage.get(this.dboEleCIFull).selectdEl!=undefined){
this.selectdEl= this.storage.get(this.dboEleCIFull).selectdEl;

}
//To get selectedEl1 from local storage
if(this.storage.get(this.dboEleCIFull).selectdEl1!=undefined){
this.selectdEl1= this.storage.get(this.dboEleCIFull).selectdEl1;
}
//to display new item creation other from local storage
if(this.storage.get(this.dboEleCIFull).itemOthersAddonList.length != 0){
  this.itemOthersAddonList = this.storage.get(this.dboEleCIFull).itemOthersAddonList;
}
 // to dispaly new item creation other with lhs/rhs from local storage
 if(this.storage.get(this.dboEleCIFull).lhsRhsItemsList.length != 0){
  this.lhsRhsItemsList = this.storage.get(this.dboEleCIFull).lhsRhsItemsList;
}
 if(this.storage.get(this.dboEleCIFull).itemRemarksVal!=null  && this.storage.get(this.dboEleCIFull).itemRemarksVal != ""){
  this.itemRemarksVal= this.storage.get(this.dboEleCIFull).itemRemarksVal;
  this.othItmTechRemChk=true;
 this.itemtechRemarkCheck=true;
 this.itemRemarkDiv=true;

}
if(this.storage.get(this.dboEleCIFull).itemCmrRemarksVal!=null  && this.storage.get(this.dboEleCIFull).itemCmrRemarksVal != ""){
this.itemCmrRemarksVal= this.storage.get(this.dboEleCIFull).itemCmrRemarksVal;
this.othItmComrRemChk=true;
this.itemComrRemarkCheck=true;
this.itemComrRemarkDiv=true;

}
if(this.storage.get(this.dboEleCIFull).subItemRmkValOut!=null  && this.storage.get(this.dboEleCIFull).subItemRmkValOut != ""){
this.subItemRmkValOut= this.storage.get(this.dboEleCIFull).subItemRmkValOut;
this.othSubItmTechRemChk=true;
this.subItemTchRem=true;
this.subItemRemarkDiv=true;

}
if(this.storage.get(this.dboEleCIFull).subItemCmrRemValOut!=null && this.storage.get(this.dboEleCIFull).subItemCmrRemValOut != ""){
this.subItemCmrRemValOut= this.storage.get(this.dboEleCIFull).subItemCmrRemValOut;
this.othSubItmComrRemChk=true;
this.subItemComRem=true;
this.subItemComrRemDiv=true;
}
this.oneLineLocArray=this.storage.get(this.oneLineLoc);
// if( this._ITOcustomerRequirementService.editFlag==true && this._ITOeditQoutService.checkEdit == false){
  if(this.oneLineLocArray != null){
    this.cICost = this.oneLineLocArray.cICost;  
    this.totalDboCiCost = this.oneLineLocArray.totalDboCiCost;
  // }
  if(this.oneLineLocArray.resOnline != null){
    this.cICost = this.oneLineLocArray.resOnline.cICost;
    this.totalDboCiCost = this.oneLineLocArray.resOnline.totalDboCiCost;
  }
  }else{
    if(this.storage.get(this.dboEleCIFull).cICost > 0){
  this.cICost = this.storage.get(this.dboEleCIFull).cICost;
}
if(this.storage.get(this.dboEleCIFull).totalDboCiCost > 0){
  this.totalDboCiCost = this.storage.get(this.dboEleCIFull).totalDboCiCost;
}

  }
console.log(this.dboFormData);
    this._DBOElectricalComponentService.getEleItems(this.dboFormData).subscribe(respon => {
      console.log(respon);
      for(let o=0; o<respon.dboEleItemList.length; o++){
        if(respon.dboEleItemList[o].eleType == "CI"){
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
          this.desItemNamList=[];
          this.desSubItemNamList=[];


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
            const x = acc.find(item => item.desItemName === current.desItemName && item.itemId === current.itemId);
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
            const x = acc.find(item => item.desSubItemName === current.desSubItemName && item.itemId === current.itemId);
            if (!x) {
              return acc.concat([current]);
            } else {
              return acc;
            } 
          }, []);
         
         if(this._ITOcustomerRequirementService.editFlag==true)
         {
          this.dboEleCIFullArray = [];
          this.newSet = [];
         //take the uniqe items names
          this.newSet = Array.from(new Set(this.temparray1.map((x) => {
            return x.itemId;
          })));
          console.log(this.newSet);
          let newArr: Array<any> = [];
          console.log(this._ITOeditQoutService.dboEleCiData)
          for (let h = 0; h < this.newSet.length; h++) {
            for (let i = 0; i < this.temparray1.length; i++) {
              if (this.newSet[h] == this.temparray1[i].itemId) {
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
         let temptechflag=[];

          console.log(newArr);
          if (newArr != []) {
            for (let m = 0; m < newArr.length; m++) {
                this.defaultValues1 = [];
                if (this.temparray1.length != 0) {
                  this.prevData = this.temparray1.filter((x) => {
                    return ((x.itemId == newArr[m].itemId && x.dispInd==1 && x.addOnNewColFlag ==0 &&  x.lhsFlag ==0 ));
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
                  this.errMsgRed = [];
                  this.errMsgBlue = [];
                  this.errMsgGreen = [];
                

                  this.errMsgPnl=[];
                  this.errDisplayPnl=[];
                  for (let c = 0; c< this.prevData.length; c++) {
                  if((this.prevData[c].lhsFlag == 0 && this.prevData[c].addOnFlg == 1 && this.prevData[c].inputCostFlag==0)  ){
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
                  if(( this.prevData[c].rhsCost == 0) && (this.prevData[c].lhsFlag==0) && (this.prevData[c].addOnNewColFlag==0) &&(this.prevData[c].errorMsg!=null) && (this.prevData[c].errorMsg!="") && this.prevData[c].errorMsg!="NULL"  && this.prevData[c].inputCostFlag==0)
                  {
                    this.errMsgRhsCost[this.prevData[c].colId] = "";
                    this.errMsgPnl[this.prevData[c].colId] = "      Note:" +this.prevData[c].errorMsg;
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
                 this.checkboolean=false;
                
                  for(let j=0;j<this.prevData.length;j++)
                  {
                    if(this.prevData[j].colType=="CHECKBOX")
                    {
                      this.lhsCheck[this.prevData[j].orderId]=true;
                      this.lhsNmCheck[this.prevData[j].orderId]=true;
                    
                    }
                    if(this.prevData[j].colType=="DROPDOWNCHECKBOX")
                    {
                      if(this.prevData[j].rhsColQuantity==0)
                      {
                        this.checkboolean=true
                      }
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
                  }
                  this.panelapplicable=true;
                  this.panelapplicablescm=true;
                  this.Ifapplicabletemp=[];

                  
                  for(let i=0;i<this.prevData.length;i++)
                  {
                     if(this.prevData[i].itemApproxCostFlag==2)
                     {
                      this.Ifapplicabletemp.push(this.prevData[i].itemErrMessage);

                      this.panelapplicable=false;
                     }
                  }
                  for(let i=0;i<this.prevData.length;i++)
                  {
                     if(this.prevData[i].itemApproxCostFlag==1)
                     {
                      this.Ifapplicabletemp.push(this.prevData[i].itemErrMessage);

                      this.panelapplicablescm=false;

                     }
                  }
                  this.colNumber=[];
                  for(let ji=0;ji<this.prevData.length;ji++)
                  {
                    this.colNumber[this.prevData[ji].orderId]=this.prevData[ji].desColOrderId;
                  }
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
                  console.log(this.temparray);

                }
           //pushing selected components data fromm storage to local variable
              this.dboEleCIFullArray.push({
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
                checkboolean:  this.checkboolean,
                panelapplicable:this.panelapplicable,
                panelapplicablescm:this.panelapplicablescm,
                colNumber:this.colNumber,
                Ifapplicabletemp: this.Ifapplicabletemp,
                tempflagsave:temptechflag
                
              });
              this.addedClassList.push(newArr[m].itemName);
              console.log(this.dboEleCIFullArray);
              let savedEle: Array<any> = [];
            }
          }
        }
      }
      if(this._ITOcustomerRequirementService.editFlag==true)
      {
    
      
          if(this.dboEleCIFullArray[0].techComments!=null)
          {
            this.itemRemarksVal= this.dboEleCIFullArray[0].techComments;
            this.othItmTechRemChk=true;
           this.itemtechRemarkCheck=true;
           this.itemRemarkDiv=true;
          }
          if(this.dboEleCIFullArray[0].comrComments!=null)
          {
            this.itemCmrRemarksVal= this.dboEleCIFullArray[0].comrComments;
            this.othItmComrRemChk=true;
            this.itemComrRemarkCheck=true;
            this.itemComrRemarkDiv=true;
          }
          if(this.dboEleCIFullArray[0].techRemarks!=null)
          {
            this.subItemRmkValOut= this.dboEleCIFullArray[0].techRemarks;
            this.othSubItmTechRemChk=true;
            this.subItemTchRem=true;
            this.subItemRemarkDiv=true;
          }
          if(this.dboEleCIFullArray[0].comrRemarks!=null)
          {
            this.subItemCmrRemValOut= this.dboEleCIFullArray[0].comrRemarks;
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

  ngOnInit() { 
  }

  
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
  getActualData()
  {
    console.log("hello");
  }
  defaultnewadd(selVal, options,value)
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
        if (this.questionsBean3[l].dropDownValueList[d].value == this.defaultValues[l] || this.questionsBean3[l].colType=="EDIT_TEXT" || this.questionsBean3[l].colType=="TEXT") {
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
             if( this.questionsBean3[l].dropDownType.value==selVal[0].quesDesc)
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
    this._DBOElectricalComponentService.refreshEle(this.dboFormData).subscribe(respons1 => {
        console.log(respons1);
        //this.questionsBean1=respons1.eleRefreshTechList;
       this.questionsBean=[];
       
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
     
     if(respons1.eleRefreshTechList[j].colNm==this.questionsBean1[i].dropDownType.value)
     {
   
      this.questionsBean1[i].dispInd=respons1.eleRefreshTechList[j].dispInd;
     // this.questionsBean1[i].desColOrderId=respons1.eleRefreshTechList[j].desColOrderId;
     if(respons1.eleRefreshTechList[j].desColOrderId==null)
     {
      this.colNumber[respons1.eleRefreshTechList[j].orderId]="0";

     }else
     {
      this.colNumber[respons1.eleRefreshTechList[j].orderId]=respons1.eleRefreshTechList[j].desColOrderId;

     }
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
  //   if(this.questionsBean[l].dropDownType.value==selVal[0].quesDesc)
  //   {
  //     console.log("world");
  //     for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
  //       //  this.openOth[l] = false;
        
  //       this.questionsBean[l].dropDownValueList[d].defaultFlag=false;
  //       if(   this.questionsBean[l].dropDownValueList[d].value==options)
  //       {
  //         console.log("check");
  //         this.questionsBean[l].dropDownValueList[d].defaultFlag=true;
  //       }
      
          
  //       }
  //   }
   
 // }
  console.log( this.questionsBean);

  // for (let l = 0; l < this.questionsBean.length; l++) {
  //             for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
  //             //  this.openOth[l] = false;
  //               if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
  //                 this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
  //               }
  //             }
  //           }
//   if(this.defaulVales1[0]=="TDPS" && this.panelType=="LT")
//   {
//     for(let j=0;j<this.questionsBean.length;j++)
//     {
//       if(this.questionsBean[j].dropDownType.value=="Ingress Protection (IP) Rating" && options=="SPDP")
//       {
//         this.questionsBean[j].dropDownValueList=[];
//        for(let k=0;k<this.questionsBeantemp[j].dropDownValueList.length;k++)
//        {
//         this.questionsBean[j].dropDownValueList.push(this.questionsBeantemp[j].dropDownValueList[k]);
//        }
//       }
//       if(this.questionsBean[j].dropDownType.value=="Ingress Protection (IP) Rating" && options!="SPDP")
//       {
//         console.log("monettest");
//         this.questionsBean[j].dropDownValueList=[];
//        for(let k=0;k<this.questionsBeantemp[j].dropDownValueList.length;k++)
//        {
//        // console.log("temp123");
//        console.log( this.questionsBean);
//        console.log(this.defaulVales1[0]);
  
//        if(this.questionsBeantemp[j].dropDownValueList[k].value!="IP-23")
//        {
//         this.questionsBean[j].dropDownValueList.push(this.questionsBeantemp[j].dropDownValueList[k]);

//        }
//        console.log("temp");
//        console.log(j);

//      } 
//     }

//   }
// }
//Gear Box Make
//High speed - 8 Probe Vibration monitoring system
//High speed - 10 Probe Vibration monitoring system
//High speed - 10 Probe Vibration monitoring system
//Gear Box Make
//Type
  this.defaultValues1=[];
  if(selVal[0].quesDesc=="Gear Box Make" && options=="TTL Make Gear Box")
  {
   
    for(let j=0;j<this.questionsBean.length;j++)
    {
     
     
        console.log("monettest");
        if(this.questionsBean[j].dropDownType.value=="Type")
               {
        this.questionsBean[j].dropDownValueList=[];
       for(let k=0;k<this.questionsBeantemp[j].dropDownValueList.length;k++)
       {
       // console.log("temp123");
       console.log( this.questionsBean);
       console.log(this.defaulVales1[0]);
  
       if(this.questionsBeantemp[j].dropDownValueList[k].value!="Full speed - 18 Probe Vibration monitoring system"  && this.questionsBeantemp[j].dropDownValueList[k].value!="10 Probe Vibration monitoring system"  && this.questionsBeantemp[j].dropDownValueList[k].value!="High speed - 10 Probe Vibration monitoring system")
       {
        this.questionsBean[j].dropDownValueList.push(this.questionsBeantemp[j].dropDownValueList[k]);

       }
       console.log("temp");
       console.log(j);

     } 
    }

  }
}
else if(selVal[0].quesDesc=="Gear Box Make" && options!="TTL Make Gear Box")

{
 
   
   
  for(let j=0;j<this.questionsBean.length;j++)
  {
   
   
      console.log("monettest");
      if(this.questionsBean[j].dropDownType.value=="Type")
      {
      this.questionsBean[j].dropDownValueList=[];
     for(let k=0;k<this.questionsBeantemp[j].dropDownValueList.length;k++)
     {
     // console.log("temp123");
     console.log( this.questionsBean);
     console.log(this.defaulVales1[0]);

    
      this.questionsBean[j].dropDownValueList.push(this.questionsBeantemp[j].dropDownValueList[k]);

     
     console.log("temp");
     console.log(j);
     }
   } 
 

}

   
 
}

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
      if(this.questionsBean[k].dropDownValueList[p].value==respons1.eleRefreshTechList[j].colValCd || respons1.eleRefreshTechList[j].colType=="EDIT_TEXT"  || respons1.eleRefreshTechList[j].colType=="TEXT" )
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
  for(let j=0;j<300;j++)
  {
    this.defaultValues1.push("12$7");
  }
  for(let j=0;j<this.questionsBean.length;j++)

  {
   
      for(let i=0;i<this.questionsBean[j].dropDownValueList.length;i++)
      {
        if(this.questionsBean[j].dropDownValueList[i].defaultFlag==true || this.questionsBean[j].colType=="CHECKBOX"   )
        {
          this.defaultValues1[j]=this.questionsBean[j].dropDownValueList[i].value;
        }
     
      
    }

  }
         for(let k = 0; k < respons1.eleRefreshTechList.length; k++){
          if(respons1.eleRefreshTechList[k].colType!="EDIT_TEXT")
          {
    this.defaultValues1[respons1.eleRefreshTechList[k].orderId-1]=respons1.eleRefreshTechList[k].colValCd;
          }
  }  
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
 if(this.groupNameNew[j].desItemName==this.questionsBean1[i].desItemName && this.questionsBean1[i].desSubItemName!="NULL" && this.questionsBean1[i].desSubItemName!=null  )
 {
   counter=counter+1;

 }
}
if(counter==0)
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
this.groupNameNew2=[];
this.groupNameNew2 = this.questionsBean1.reduce((acc, current) => {
  console.log(acc, current);
  const x = acc.find(item => item.desSubItemId === current.desSubItemId);
  if (!x) {
    return acc.concat([current]);
  } else {
    return acc;
  } 
}, []);
   
this.groupNameNew2 =  this.groupNameNew2.filter((x) => {
  return ((x.desSubItemId !=0 ));
  })
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
    if(this.itemCode=="VMS")
    {
       this.displayvms=true;
      this.itemOthersList2=[];
      this.check=[];
      this.check2=[];
      this.displayOthnewLine2=[];
      this.othersSubTypCheck=[];
      this.othersSubItmTypChk=[];
    }
    if(this.itemCode=="ALT" || this.itemCode=="AVR" || this.itemCode=="LSP"|| this.itemCode=="GRP"|| this.itemCode=="GACB"|| this.itemCode=="HVGCB"|| this.itemCode=="TCP"|| this.itemCode=="MCC" || this.itemCode=="HVB"|| this.itemCode=="LVB"|| this.itemCode=="HVC"|| this.itemCode=="LT_CAB"|| this.itemCode=="IC"|| this.itemCode=="GCP"|| this.itemCode=="ACB"|| this.itemCode=="MCSP"|| this.itemCode=="TGP")
   { 
     this.defaultnewadd(selVal, options,0)
   }
    //let tempcounter=0;
  //  for(let j=0;j<this.childvaluesnew.length;j++)
  
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
  this.OverWrittencICost = 0;
}
 //to save in localstorage
 saveInLocal(key, val): void {
  console.log('recieved= key:' + key + 'value:' + val);
  this.storage.set(key, val);
  this.dboElectricalLocalCI[key] = this.storage.get(key);
}

// take min and max value
  /**
   * 
   * @param options dropdown value
   * @param selVal seleced option
   * @param i index of the drop down
   */
  optionSelItem(options, selVal, i) {
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
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
    this._DBOElectricalComponentService.getEleItems(this.dboFormData).subscribe(respon => {
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
    this.enablebtn=true;
    if(this.dboEleCIFullArray.length>0)
    {
    for( let j=0;j<this.dboEleCIFullArray.length;j++)
    {
      if(this.dboEleCIFullArray[j].itemId==this.itemId && this.dboEleCIFullArray[j].subItemId==this.subItemId)
      {
        this.dboEleCIFullArray.splice(j,1);
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
    if(this.addOnList[k].itemId == this.itemId){
        this.addOnList[k] = null;
     
    }
  }

this.addOnList = this.addOnList.filter(n => n != null);
for(let x=0;x<this.subItemOthAddonList.length;x++)
{
  if(this.itemId==this.subItemOthAddonList[x].itemId)
  {
    this.subItemOthAddonList[x]=null;  
  }
}
for(let x=0;x<this.lhsRhsSubItemsList.length;x++)
{
  if(this.itemId==this.lhsRhsSubItemsList[x].itemId)
  {
    this.lhsRhsSubItemsList[x]=null;  
  }
}
this.subItemOthAddonList = this.subItemOthAddonList.filter(n => n != null);
this.lhsRhsSubItemsList = this.lhsRhsSubItemsList.filter(n => n != null);
for(let x=0;x<this.dessubItemOthAddonList.length;x++)
{
  if(this.itemId==this.dessubItemOthAddonList[x].itemId)
  {
    this.dessubItemOthAddonList[x]=null;  
  }
}
for(let x=0;x<this.lhsRhsDesSubItemsList.length;x++)
{
  if(this.itemId==this.lhsRhsDesSubItemsList[x].itemId)
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
    this._DBOElectricalComponentService.saveEleFilter(this.dboFormData).subscribe(res => {
      this.specialnote=null;
      console.log(res);
      for(let j=0;j<this.dboEleFilterListNew.length;j++)
      {
if(this.dboEleFilterListNew[j].colValCd==this.newArryUpd[0].colValCd)
{
  if(this.dboEleFilterListNew[j].note!=null && this.dboEleFilterListNew[j].note!="NULL"   )
  {

  
  this.specialnote=this.dboEleFilterListNew[j].note;
  }
}
      }

    //   for(let j=0;j<res.saveEleSpecialFilterList.length;j++)
    //   {
    //   if(res.saveEleSpecialFilterList[j].altError==1 && res.saveEleSpecialFilterList[j].colValCd==this.dboFormData.colValCd )
    //   {
    //     this.enablebtn1=true;
    //     this.enablebtn=true;

    //   }
    // }
    this.mainSave=true;
    this.mainSave2=true;

      if(res.successCode==0)
      {
     this.enablebutton=false;
        this.dboEleSel( this.tempArray, this.itemName,this.selectedELIndex,  this.enablebutton);
        this.makeButton=false;

      }

    });
   
  }
  //On click of item Name
  optionSelNew(colvalcd,item)
  {
  
    // this.mainSave=true;
    // this.mainSave2=true;
    console.log("asd");
    this.makeButton=true;
       // this.saveBtColor=true;
   
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
  checkForQuantity1(value,z)
  {
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    console.log(this.defaultValues[z]);
    let stringToSplit = this.defaultValues[z];
    let x = stringToSplit.split("\n");
    let temp=x.length;

    console.log(x.length);
    console.log(x);
  
    for(let y=0;y<x.length;y++)
    {

    
    let a=x[y].length/33;
    if(a>1)
    {
      var b=Math.floor(a);
      temp=temp+b;
    }
  }
    console.log(x.length);
    console.log(x);
    this.defaultvalueshieght[z]=temp;

    this.questionsBean[z].dropDownValueList[0].value=value;
  
      this.questionsBean1[z].dropDownValueList[0].value=value;
    

  }
  createRange(number){
    var items: number[] = [];
    for(var i = 1; i <= number; i++){
       items.push(i);
    }
    return items;
  }


  dboEleSel(d,nm,i,enable){
    this.rhs=[];
    this.newAddNameO=[];
    this.newAddCostO=[];
    this.newAddRemrkO=[];
   this.refreshBoolean=true;
this.makeButton=true;
    this.vmsCounter=[];
    this.AltMakeArray=[];
    this.geararray=[];
    this.typearray=[];
    this.makearray=[];
    this.AltMakeVms='';
    this.gearvms='';
    this.typevms='';
    this.makevms='';
    this.techFlagindicator=[];
    this.itemCode=d.itemCd;
    this.discountPer=0;
    this.qty=1;
    this.getPrice=true;
    this.getPricevms=true;
    this.getPricestg=true;
    this.disablelhstg=[];
    this.disablelhsvms=[];
    this.disablelhs= [];
    this.disablelhssub = [];
    
    
    this.lhsdes= [];
    this.rhsdes= [];
    this.lhsdes2= [];
    this.rhsdes2= [];
    this.diableitemname=false;

    this.itemCd=d.itemCd;
    this.deItemLevelOthers=[];

    this.displaySubItemOthTable=false;
    this.othersSubCheck=false;
    this.othersSubItemCheck=false;
    this.displayDesSubItemOthTable=false;
    this.othersDesSubCheck=false;
    this.othersDesSubItemCheck=false;
    this.Ifapplicable=[];
    this.saveBtColor=true;
    this.mainSaveVms=true;

    this.check=[];
    this.check2=[];
    this.enablebtn1=false;
    this.displayvms=false;
    this.linkFlag=d.linkFlag;
    this.Cost=[];
    this.dboEleInstrListNew=[];
    this.displaypanel12=false;
    this.dboEleInstrList=[];
    this.lhsCheck=[];
    this.drpCheck=[];
    this.dropCheckQty=[];
     this.drpCheckqtyy=[];
     this.drpCheckqtyyKey=[];
this.displayOthnewLine1=[];
this.displayOthnewLine2=[];
 this.othersSubTypCheck=[];
this.othersSubItmTypChk=[];
    this.questionsBeantemp=[];
    this.questionsBean1=[];
    this.checkboolean=false;
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
    this.enablebtn=false;
    this.panelType=d.typeOfPanel;
    //this.dboFormData.subItemId = d.subItemId;
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.enablebutton=enable;
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
    this.othersCompCheck=[];
    this.selectedELIndex=i;
    console.log(this.selectedELIndex);
    this.defaultValues = [];
    this.questionsBean = [];
    this.questionsBean1 = [];
    this.questionsBeantemp = [];
    this.itemOthersList = [];
    this.dboCost = 0;
    this.dboAddOnCost = 0;
    for(let k=0;k<this.lhsRhsSubItemsList.length;k++)
    {
 this.rhsdes2[k]=false;
    }
    
    for(let k=0;k<this.lhsRhsDesSubItemsList.length;k++)
    {
 this.rhsdes[k]=false;
    }
   if(d.enabled==false)
   {
    this.specialnote=null;

   }
   
    if(enable==true)
    {
      this.defaulVales1=[];
      if(this.dboEleCIFullArray.length>0)
      {
      for( let j=0;j<this.dboEleCIFullArray.length;j++)
      {
        if(this.dboEleCIFullArray[j].itemId==this.itemId && this.dboEleCIFullArray[j].subItemId==this.subItemId)
        {
          enable=false;
        }
      }
    }
    this._DBOElectricalComponentService.eleFilter(this.dboFormData).subscribe(res => {
      if(res.successCode==0)
      {
     
        this.dboEleFilterListNew=res.dboEleSpecialFilterList;
        if(  this.dboEleFilterListNew.length==0)
        {
          this.enablebtn=true;
          this.enablebtn1=true;
          this.diableitemname=true;

        }
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
      if(this.dboEleCIFullArray.length>0)
      {
      for( let j=0;j<this.dboEleCIFullArray.length;j++)
      {
        if(this.dboEleCIFullArray[j].itemId==this.itemId && this.dboEleCIFullArray[j].subItemId==this.subItemId)
        {
          this.newArray12=[];
  this.newArryUpd=[];
          enable=false;
          this.enablebutton=enable;
         this.defaulVales1=this.dboEleCIFullArray[j].defaulVales1;
        for(let j=0;j<res.dboEleSpecialFilterList.length;j++)
        {
if(res.dboEleSpecialFilterList[j].colValCd==this.defaulVales1[0])
{
  this.specialnote=null;
  if(res.dboEleSpecialFilterList[j].note!=null && res.dboEleSpecialFilterList[j].note!="NULL" )
  {
    this.specialnote=res.dboEleSpecialFilterList[j].note;
  }

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
  //   if(this.dboEleCIFullArray.length>0)
  //   {
  //   for( let j=0;j<this.dboEleCIFullArray.length;j++)
  //   {
  //     if(this.dboEleCIFullArray[j].itemId==this.itemId && this.dboEleCIFullArray[j].subItemId==this.subItemId)
  //     {
  //       this.enablebutton=false;
  //     }
  //   }
  // }
    
    if(nm == d.itemName){
      this.subList1=[];
      this.sublistDup =[];
        for (let s = 0; s < this.dboEleItemList.length; s++) {                
          if(d.itemId == this.dboEleItemList[s].itemId && this.dboEleItemList[s].subItemName!=null){
            this.sublistDup.push(this.dboEleItemList[s]);
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
    console.log(d,nm, i);
    
    if(enable==false)
   {

     this.enableFilterButton=true;
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
    
  
    this._DBOElectricalComponentService.getElePanels(this.dboFormData).subscribe(respons => {

      console.log(respons);
      console.log(respons.dboElePanelList2);
      this.questionsBean1 = respons.questionsBean;
      this.dboElePanelList1 = respons.dboElePanelList1;
      this.dboElePanelList2 = respons.dboElePanelList2;
      this.colNumber=[];
     for(let k=0;k<respons.questionsBean1.length;k++)
     {
       this.questionsBeantemp.push(respons.questionsBean1[k]);
     }
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
     if(this.groupNameNew[j].desItemName==this.questionsBean1[i].desItemName && this.questionsBean1[i].desSubItemName!="NULL" && this.questionsBean1[i].desSubItemName!=null  )
     {
       counter=counter+1;
    
     }
    }
    if(counter==0)
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
    
 this.groupNameNew2 =  this.groupNameNew2.filter((x) => {
    return ((x.desSubItemId !=0 ));
    })


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

for(let z = 0; z < respons.dboElePanelList1.length; z++){
  for(let v = 0; v < this.questionsBean1.length; v++){
    if(respons.dboElePanelList1[z].colNm == this.questionsBean1[v].dropDownType.value){
      this.questionsBean1[v].dispInd = respons.dboElePanelList1[z].dispInd;
    }
  }
}
for(let z = 0; z < respons.dboElePanelList2.length; z++){
  for(let v = 0; v < this.questionsBean1.length; v++){
    if(respons.dboElePanelList2[z].colNm == this.questionsBean1[v].dropDownType.value && this.questionsBean1[v].colType!="COST_INPUT" ){
      {
   for(let k=0;k<this.questionsBean1[v].dropDownValueList.length;k++)
   {
    if(respons.dboElePanelList2[z].colValCd==this.questionsBean1[v].dropDownValueList[k].value  )
    {
if(respons.dboElePanelList2[z].rhsDispInd==0)
{
  this.questionsBean1[v].dropDownValueList.splice(k,1)
}
    }
   }
  }
  }
  }
}

for(let k = 0; k < this.questionsBean1.length; k++){
  
    this.questionsBean.push(this.questionsBean1[k]);
  
}
for(let j=0;j<this.questionsBean.length;j++)
{
  if(this.questionsBean[j].desItemName=="Multifunctional digital generator protection relay shall have following protections features")
  {
    this.newPanel[this.questionsBean[j].orderId]=true;
    break;
  }

}
for(let j=0;j<this.questionsBean.length;j++)
{
  if(this.questionsBean[j].desItemName=="Multifunctional digital generator protection relay shall have following protections features")
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
      if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true || this.questionsBean[l].colType=="CHECKBOX" ) {
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

      
    this.lhsCheck[j] = true;
    this.lhsNmCheck[j] = true;
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
  this.defaultvalueshieght=[];
  for(let jp=0;jp<this.defaultValues.length;jp++)
  {
    console.log(jp);

    let stringToSplit = this.defaultValues[jp];
    let x = stringToSplit.split("\n");
   let temp=x.length;

    console.log(x.length);
    console.log(x);
  
    for(let y=0;y<x.length;y++)
    {

    
    let a=x[y].length/33;
    if(a>1)
    {
      var b=Math.floor(a);
      temp=temp+b;
    }
  }

    this.defaultvalueshieght.push(temp);
  }
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
  let tempVms=false;
  if(this.dboEleCIFullArray.length!=0)
  {
  for( let j=0;j<this.dboEleCIFullArray.length;j++)
  {
    if(this.dboEleCIFullArray[j].itemId==this.itemId && this.dboEleCIFullArray[j].subItemId==this.subItemId)
    {
      tempVms=true;
this.defaultValues=[];
this.newPanelval=[];
this.newPanelval=this.dboEleCIFullArray[j].newPanelval1;
  for(let ik=0;ik<this.questionsBean.length;ik++)
  {
    let countertemp=0;
    for(let k=0;k<this.dboEleCIFullArray[j].defaultValuesid.length;k++)
    {
      if(this.dboEleCIFullArray[j].defaultValuesid[k]==this.questionsBean[ik].orderId || this.questionsBean[ik].colType=="CHECKBOX" || this.questionsBean[ik].colType=="DROPDOWNCHECKBOX" )
      {
        if(this.questionsBean[ik].colType!="COST_INPUT")
        {

        
        countertemp=1;
        }
      }
    }
    if(countertemp==0)
    {
      this.questionsBean[ik].dispInd=0;
    }
    if(countertemp==1)
    {
      this.questionsBean[ik].dispInd=1;
    }



    
  }
  for(let j=0;j<this.subItemOthAddonList.length;j++)
{
if(this.subItemOthAddonList[j].itemId==this.itemId)
{
  this.displaySubItemOthTable=true;
  this.othersSubCheck=true;
  this.othersSubItemCheck=true;
  this.lhsdes2[j]=false;

}
}
for(let j=0;j<this.dessubItemOthAddonList.length;j++)
  {
  if(this.dessubItemOthAddonList[j].itemId==this.itemId)
  {
    this.displayDesSubItemOthTable=true;
    this.othersDesSubCheck=true;
    this.othersDesSubItemCheck=true;
    this.lhsdes[j]=false;

  
  }
  }
  this.techFlagindicator=[];
  this.techFlagindicator=this.dboEleCIFullArray[j].tempflagsave;
  this.inptCstQty=[];
  this.costQty=[];
  this.inptCstQty=this.dboEleCIFullArray[j].inptCstQty;
  this.costQty=this.dboEleCIFullArray[j].costQty;
  
  this.lhsCheck=[];
  this.lhsNmCheck==[];
  this.colNumber=[];
  this.colNumber= this.dboEleCIFullArray[j].colNumber;
  this.lhsNmCheck= this.dboEleCIFullArray[j].lhsNmCheck;
  this.lhsCheck= this.dboEleCIFullArray[j].lhsCheck;
  this.lhsNmCheck= this.dboEleCIFullArray[j].lhsNmCheck;
  this.drpCheck=[];
  this.dropCheckQty=[];
   this.drpCheckqtyy=[];
   this.drpCheckqtyyKey=[];
   this.checkboolean=this.dboEleCIFullArray[j].checkboolean;
   ////innstrument list others 
   this.displayOthnewLine1=[];
this.displayOthnewLine2=[];
 this.othersSubTypCheck=[];
this.othersSubItmTypChk=[];
this.makeButton=false;

this.Cost=[];
this.displaypanel12=false;
if(this.dboEleCIFullArray[j].componenet=="Set of STG Instruments List")
{

  //this.instdata
  //this.dboCost = 0;
 // this.dboAddOnCost= this.dboEleCIFullArray[j].dboAddOnCost;
  this.displaypanel12=true;
  this._DBOElectricalComponentService.getEleInstr(this.dboFormData).subscribe(res => {
    console.log(res);
    this.Cost=[];
    this.dboEleInstrList=[];
    this.dboEleInstrList=res.dboEleInstrList;
    // for(let jk=0;jk< this.dboEleInstrList.length;jk++)
    // {
    //  if(this.dboEleInstrList[jk].rhsFlag==1 || this.dboEleInstrList[jk].addOnNewColFlag==1 )
    //  {
    //   this.dboEleInstrList[jk]=null;
    //  }
    // }
    // this.dboEleInstrList = this.dboEleInstrList.filter(n => n != null);

    this.dboEleInstrListNew =  this.dboEleInstrList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.instrCode === current.instrCode);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    this.dboEleInstrListNew =  this.dboEleInstrList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.instrCode === current.instrCode);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    if(this.buttonColor!="blue")
    {
    
      this.Ifapplicable=[];
          for(let h=0;h<this.dboEleInstrListNew.length;h++)
    
          {
            if(this.dboEleInstrListNew[h].approxFlag==1)
            {
              this.panelapplicablescm=false;
              this.Ifapplicable.push("Get the Basic Cost From Scm");
      
      
              this.saveBtColor=false;
              this.buttonColor="blue";
            }
          }
        }
    for(let j=0;j<this.dboEleInstrListNew.length;j++)
    {
      let count=0;
      for(let h=0;h<this.dboEleInstrList.length;h++)
      {
        if(this.dboEleInstrList[h].instrCode==this.dboEleInstrListNew[j].instrCode)
        {
          count=count+1;
          this.ciList[h]=count;
        }
      }
    }
    for(let k=0;k<this.dboEleInstrListNew.length;k++)
{
  this.Cost[this.dboEleInstrListNew[k].itemId]=this.dboEleInstrListNew[k].cost;
}
    this.dboEleInstrListNew2=[];
    this.dboEleInstrListNew3=[];
    this.dboEleInstrListNew2 =  this.dboEleInstrList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.addInstrId === current.addInstrId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    this.dboEleInstrListNew1 =  this.dboEleInstrList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.addInstrId === current.addInstrId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    this.dboEleInstrListNew3 =  this.dboEleInstrList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.addInstrId === current.addInstrId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
     this.dboEleInstrListNew1 =  this.dboEleInstrListNew3.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.instrTypeNm === current.instrTypeNm);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    
for(let k=0;k<this.dboEleInstrListNew1.length;k++)
{
if(this.dboEleInstrListNew1[k].addInstrId==0)
{
this.dboEleInstrListNew1.splice(k,1);
}
}
    if(res.dboEleInstrList.length!=0)
    {
      this.displaypanel12=true;
    }
  })
  for(let j=0;j<this.itemOthersList1.length;j++)
  {
    if(this.itemOthersList1[j].addOnNewColFlag==1)
    {

      this.displayOthnewLine2[this.itemOthersList1[j].itemId]=true;
      this.displayOthnewLine1[this.itemOthersList1[j].itemId]=false;
      this.othersSubTypCheck[this.itemOthersList1[j].itemId]=true;
      this.othersSubItmTypChk[this.itemOthersList1[j].itemId]=true;
    }
    else
    {
      this.displayOthnewLine1[this.itemOthersList1[j].colId]=false;

    }
    this.disablelhstg[j]=false;
  }
}
this.check=[];
this.check2=[];
if(this.dboEleCIFullArray[j].componenet=="Turbine Vibration Monitoring System (VMS)")
{
  //this._ITOeditQoutService.saveVmsDataList
  // this.dboCost = 0;
  // this.dboAddOnCost= 0;
  this.disablelhsvms=[];

  this.Cost=[];
  this.vmsCounter=[];
  this.dboEleInstrList=[];
  this._DBOElectricalComponentService.getEleVms(this.dboFormData).subscribe(res => {
    console.log(res);
    this.Cost=[];
    this.dboEleInstrList=[];
    this.dboEleInstrListNew=[];
    this.dboEleInstrList=res.dboEleVmsList;
    console.log(this.dboEleInstrList);
    let counter=0;
    for(let i=0;i<this.dboEleInstrList.length;i++)
    {
      if(this.dboEleInstrList[i].addPrbFlag==1)
      {
        counter=counter+1;
        this.vmsCounter.push(counter);

      }
      else
      {
        this.vmsCounter.push(i);

      }

    }
    this.dboEleInstrListNew =  this.dboEleInstrList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.addPrbFlag === current.addPrbFlag);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);

    for(let k=0;k<this.dboEleInstrListNew.length;k++)
{
  this.Cost[this.dboEleInstrListNew[k].id]=this.dboEleInstrListNew[k].cost;
}
    this.displayvms=true;
    for(let j=0;j<this.itemOthersList2.length;j++)
  {
    this.disablelhsvms[j]=false;

if(this.itemOthersList2[j].newColValFlag==0)
{
  this.check[this.itemOthersList2[j].orderBy]=true;
      this.check2[this.itemOthersList2[j].orderBy]=true;
}
else
{
  this.displayOthnewLine2[this.itemOthersList2[j].addPrbFlag]=true;
  this.displayOthnewLine1[this.itemOthersList2[j].addPrbFlag]=false;
  this.othersSubTypCheck[this.itemOthersList2[j].addPrbFlag]=true;
  this.othersSubItmTypChk[this.itemOthersList2[j].addPrbFlag]=true;
}
      
    
    
  }
  });
}
//////instrument listothers end
  // this.drpCheck=this.dboEleCIFullArray[j].drpCheck;
   //this.dropCheckQty=this.dboEleCIFullArray[j].dropCheckQty;
    //this.drpCheckqtyy=this.dboEleCIFullArray[j].drpCheckqtyy;
    //this.drpCheckqtyyKey=this.dboEleCIFullArray[j].drpCheckqtyyKey;
    for(let ji=0;ji<this.dboEleCIFullArray[j].drpCheckqtyyKey.length;ji++)
    {
      if(this.dboEleCIFullArray[j].drpCheckqtyyKey[ji]!=null)
      {
        this.drpCheckqtyyKey[ji]=this.dboEleCIFullArray[j].drpCheckqtyyKey[ji];
      }
    }
   
    let tempar=[];
    if( this.drpCheckqtyyKey.length!=0)
    {
	if(this._ITOcustomerRequirementService.editFlag==true && this.dboEleCIFullArray[j].counter==0)
{
   for(let value1 in this.drpCheckqtyyKey)
   {
     let value2=Number(value1);
 for(let value in this.dboEleCIFullArray[j].drpCheckqtyy)
 {
 
   for(let k=0;k<this.questionsBean[value2-1].dropDownValueList.length;k++)
   {

    if(value==this.questionsBean[value2-1].dropDownValueList[k].value)
    {
       this.drpCheck[k]=true;
       this.dropCheckQty[k]=true;
       this.drpCheckqtyy[k]=this.dboEleCIFullArray[j].drpCheckqtyy[value];
    }
   }
 }
   }
  }
  else
{
  this.drpCheck=this.dboEleCIFullArray[j].drpCheck;
   this.dropCheckQty=this.dboEleCIFullArray[j].dropCheckQty;
    this.drpCheckqtyy=this.dboEleCIFullArray[j].drpCheckqtyy;
   this.drpCheckqtyyKey=this.dboEleCIFullArray[j].drpCheckqtyyKey;
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
     
        // this.drpCheck=this.dboEleCIFullArray[j].drpCheck;
        // this.dropCheckQty=this.dboEleCIFullArray[j].dropCheckQty;
        //  this.drpCheckqtyy=this.dboEleCIFullArray[j].drpCheckqtyy;
        //  this.drpCheckqtyyKey=this.dboEleCIFullArray[j].drpCheckqtyyKey;
      this.itemcost=this.dboEleCIFullArray[j].itemcost;
       this.lhsCheck= this.dboEleCIFullArray[j].lhsCheck;
       this.lhsNmCheck= this.dboEleCIFullArray[j].lhsNmCheck;
       this.errMsgRhsCost=this.dboEleCIFullArray[j].errMsgRhsCost;
       this.errMsgRed=this.dboEleCIFullArray[j].errMsgRed;
       this.errMsgBlue=this.dboEleCIFullArray[j].errMsgBlue;
       this.errMsgGreen=this.dboEleCIFullArray[j].errMsgGreen;
       this.errMsgPnl=this.dboEleCIFullArray[j].errMsgPnl;
       this.errDisplayPnl=this.dboEleCIFullArray[j].errDisplayPnl;
       this.newPanelval=this.dboEleCIFullArray[j].newPanelval1;;
 //this.defaultValuesid=this.dboEleCIFullArray[j].errDisplayPnl1;;
        if(this.dboEleCIFullArray[j].eleItemTechRemarks!=null)
        {
          this.eleItemTechRemarks=this.dboEleCIFullArray[j].eleItemTechRemarks;
          this.techCheckIn = true;
          this.itemTechRmkDiv=true;
        }
        if(this.dboEleCIFullArray[j].eleItemComrRemarks!=null)
        {
          this.eleItemComrRemarks=this.dboEleCIFullArray[j].eleItemComrRemarks;
          this.comrrCheck = true;
          this.itemComrRmkDiv=true;
        }
        this.defaultValues=this.dboEleCIFullArray[j].defaultValues;
        this.qty=this.dboEleCIFullArray[j].qty;
        this.dboCost=this.dboEleCIFullArray[j].dboCost;
        this.discountPer = this.dboEleCIFullArray[j].discountPer;
        if(this.dboEleCIFullArray[j].panelapplicable==false )
        {
          this.saveBtColor=false;
          this.buttonColor="red";

        }
        else if(this.dboEleCIFullArray[j].panelapplicable==true && this.dboEleCIFullArray[j].panelapplicablescm==true )
        {
          this.saveBtColor=false;
          this.buttonColor="green";


        }
        else if(this.dboEleCIFullArray[j].panelapplicablescm==false )
        {
          this.saveBtColor=false;
          this.buttonColor="blue";
        }
      this.Ifapplicable=[];
      if(this.dboEleCIFullArray[j].Ifapplicabletemp.length!=0)
      {
        for(let l=0;l<this.dboEleCIFullArray[j].Ifapplicabletemp.length;l++)
{
        this.Ifapplicable.push(this.dboEleCIFullArray[j].Ifapplicabletemp[l]);
}
      }
      if(this.buttonColor!="blue")
      {
      
        this.Ifapplicable=[];
            for(let h=0;h<this.dboEleInstrListNew.length;h++)
      
            {
              if(this.dboEleInstrListNew[h].approxFlag==1)
              {
                this.panelapplicablescm=false;
                this.Ifapplicable.push("Get the Basic Cost From Scm");
        
        
                this.saveBtColor=false;
                this.buttonColor="blue";
              }
            }
          }
        this.dboAddOnCost = this.dboEleCIFullArray[j].dboAddOnCost;
        this.othCheck=[];
        this.displayCompOthTable=[];
        this.othersCompCheck=[];

      if(this.dboEleCIFullArray[j].itemOthersList.length!=0)
      {
        for(let i=0;i<this.dboEleCIFullArray[j].itemOthersList.length;i++)
        {
          if(this.dboEleCIFullArray[j].itemOthersList[i].itemId==this.itemId && this.dboEleCIFullArray[j].itemOthersList[i].subItemId==this.subItemId )
          {            
           // this.displayCompOthTable=true;
         
            //this.othersCompCheck=true;
            this.itemOthersList.push(this.dboEleCIFullArray[j].itemOthersList[i]);
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
  this.defaultValues=this.dboEleCIFullArray[j].defaultValues;
  if( this.defaultValues[1]=="TTL Make Gear Box")
  {
   
    for(let j=0;j<this.questionsBean.length;j++)
    {
     
     
        console.log("monettest");
        if(this.questionsBean[j].dropDownType.value=="Type")
               {
        this.questionsBean[j].dropDownValueList=[];
       for(let k=0;k<this.questionsBeantemp[j].dropDownValueList.length;k++)
       {
       // console.log("temp123");
       console.log( this.questionsBean);
       console.log(this.defaulVales1[0]);
  
       if(this.questionsBeantemp[j].dropDownValueList[k].value!="Full speed - 18 Probe Vibration monitoring system"  && this.questionsBeantemp[j].dropDownValueList[k].value!="10 Probe Vibration monitoring system"  && this.questionsBeantemp[j].dropDownValueList[k].value!="High speed - 10 Probe Vibration monitoring system")
       {
        this.questionsBean[j].dropDownValueList.push(this.questionsBeantemp[j].dropDownValueList[k]);

       }
       console.log("temp");
       console.log(j);

     } 
    }

  }
}
  if(this.defaulVales1[0]=="TDPS" && this.panelType=="LT")
  {
    for(let j=0;j<this.questionsBean.length;j++)
    {
      if(this.questionsBean[j].dropDownType.value=="Ingress Protection (IP) Rating" && this.defaultValues[27]=="SPDP")
      {
        let counter=0;
        for(let x=0;x<this.defaultValues.length;x++)
        {
          if(this.defaultValues[x]=="SPDP")
          {
counter=1;
          }
        }
        if(counter==1)
        {

        
        this.questionsBean[j].dropDownValueList=[];
       for(let k=0;k<this.questionsBeantemp[j].dropDownValueList.length;k++)
       {
        this.questionsBean[j].dropDownValueList.push(this.questionsBeantemp[j].dropDownValueList[k]);
       }
      }
      }
      if(this.questionsBean[j].dropDownType.value=="Ingress Protection (IP) Rating" && this.defaultValues[27]!="SPDP")
      {
        let counter1=0;
        for(let x=0;x<this.defaultValues.length;x++)
        {
          if(this.defaultValues[x]=="SPDP")
          {
counter1=1;
          }
        }
        if(counter1==0)
        {
        this.questionsBean[j].dropDownValueList=[];
       for(let k=0;k<this.questionsBeantemp[j].dropDownValueList.length;k++)
       {
         if(this.questionsBeantemp[j].dropDownValueList[k].value!="IP-23")
        this.questionsBean[j].dropDownValueList.push(this.questionsBeantemp[j].dropDownValueList[k]);
       } 
      }
    }
    }
  }
  for(let k=0;k<this.dboEleCIFullArray[j].defaultValuesid.length;k++)
  {
    for(let x=0;x<this.questionsBean.length;x++)
    {
      if(this.questionsBean[x].orderId==this.dboEleCIFullArray[j].defaultValuesid[k])
      {
        for(let p=0;p<this.questionsBean[x].dropDownValueList.length;p++)
        {
          if(this.questionsBean[x].dropDownValueList[p].value==this.dboEleCIFullArray[j].defaultValues[k])
          {
            this.questionsBean[x].dropDownValueList[p].defaultFlag=true;

          }
          else
          {
            this.questionsBean[x].dropDownValueList[p].defaultFlag=false;
          }
        }
      }
    }
  }
  let temp=[];
  for (let l = 0; l < this.questionsBean.length; l++) {
    for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
    //  this.openOth[l] = false;
      if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
        temp.push(this.questionsBean[l].dropDownValueList[d].value);
        //this.defaultvaluesid.push(this.questionsBean1[k].dispInd)
      }
    }
  }
console.log(temp);
  if(this._ITOcustomerRequirementService.editFlag==true && this.dboEleCIFullArray[j].counter==0 )
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
        if(this.questionsBean[j].dropDownValueList[i].defaultFlag==true || this.questionsBean[j].colType=="CHECKBOX"   )
        {
          this.defaultValues1[j]=this.questionsBean[j].dropDownValueList[i].value;
        }
     
      
    }

  }
 for(let j=0;j<this.questionsBean.length;j++)
 {
   console.log(this.questionsBean[j].orderId);
 }
  for(let j=0;j<this._ITOeditQoutService.dboEleCiData.length;j++)
  {
    if(this._ITOeditQoutService.dboEleCiData[j].itemId==this.itemId && this._ITOeditQoutService.dboEleCiData[j].dispInd==1 && this._ITOeditQoutService.dboEleCiData[j].addOnNewColFlag ==0 &&  this._ITOeditQoutService.dboEleCiData[j].lhsFlag ==0 )
    {
      this.defaultValues1[this._ITOeditQoutService.dboEleCiData[j].orderId-1]=this._ITOeditQoutService.dboEleCiData[j].colValCd;
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
if(this.itemCode=="VMS" && this.panelType!="DT")
{
  this.AltMakeVms=this.defaultValues[0];
  this.gearvms=this.defaultValues[1];
  this.typevms=this.defaultValues[2];
  this.makevms=this.defaultValues[3];
}
else
{
  this.gearvms=this.defaultValues[0];
  this.typevms=this.defaultValues[1];
  this.makevms=this.defaultValues[2];
}
  console.log(this.dboEleCIFullArray[j].defaultValues);
  this.defaultValuesid=[];
  for(let k = 0; k < this.questionsBean.length; k++){
    if(this.questionsBean[k].dispInd==1)
    {

    
    this.defaultValuesid.push(this.questionsBean[k].orderId);
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
  this.defaultvalueshieght=[];
  for(let jp=0;jp<this.defaultValues.length;jp++)
  {
    let stringToSplit = this.defaultValues[jp];
    let x = stringToSplit.split("\n");
    let temp=x.length;

    console.log(x.length);
    console.log(x);
  
    for(let y=0;y<x.length;y++)
    {

    
    let a=x[y].length/33;
    if(a>1)
    {
      var b=Math.floor(a);
      temp=temp+b;
    }
  }
    console.log(x.length);
    console.log(x);
    this.defaultvalueshieght.push(temp);
  }
  this.temparray=this.dboEleCIFullArray[j].temparray;
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
this.enableFilterButton=false;
this.diableitemname=true;

if(this.itemCode=="VMS" && this.panelType!="DT")
{
  this._DBOElectricalComponentService.getVmsCache(this.dboFormData).subscribe(res => {
    console.log(res);
   this.totalVmsList =  res.dboVmsList
    this.AltMakeArray = this.totalVmsList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.altMake === current.altMake);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    
    if(tempVms==false)
    {
      let temp12=[];
      for(let j=0;j<this.AltMakeArray.length;j++)
      {
        if(this.AltMakeArray[j].altMakeDefaultFlag==1)
        {
          this.AltMakeVms=this.AltMakeArray[j].altMake;
          temp12.push(this.AltMakeArray[j].altMake);
          break;
        }
       
      }
      for(let j=0;j<this.AltMakeArray.length;j++)
{
  if(this.AltMakeArray[j].altMake!=this.AltMakeVms)
  {
    temp12.push(this.AltMakeArray[j].altMake);

  }
}
this.AltMakeArray=temp12;
let temp3=[];
let temp2=[];
let temp=[];

      this.geararray = this.totalVmsList.filter((x) => {
        return ((x.altMake==this.AltMakeVms));
      })
      for(let j=0;j<this.geararray.length;j++)
      {
        if(this.geararray[j].gearBoxDefaultFlag==1)
        {
          this.gearvms=this.geararray[j].gearbox;
          temp3.push(this.geararray[j].gearbox);
          break;
        }
       
      }
      this.geararray = this.geararray.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.gearbox ===  current.gearbox);
        if (!x) {       
            return acc.concat([current]);        
          
        } else {
          return acc;
        } 
      }, []);
      for(let j=0;j<this.geararray.length;j++)
{
  if(this.geararray[j].gearbox!=this.gearvms)
  {
    temp3.push(this.geararray[j].gearbox);

  }
}
this.geararray=temp3;
      this.typearray = this.totalVmsList.filter((x) => {
        return ((x.altMake==this.AltMakeVms) && (x.gearbox==this.gearvms));
      })
      for(let j=0;j<this.typearray.length;j++)
      {
        if(this.typearray[j].typeDefaultFlag==1)
        {
          this.typevms=this.typearray[j].type;
          temp2.push(this.typearray[j].type);

          break;
        }
      }
      this.typearray = this.typearray.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.type ===  current.type);
        if (!x) {       
            return acc.concat([current]);        
          
        } else {
          return acc;
        } 
      }, []);
      for(let j=0;j<this.typearray.length;j++)
{
  if(this.typearray[j].type!=this.typevms)
  {
    temp2.push(this.typearray[j].type);

  }
}
this.typearray=temp2;
      this.makearray = this.totalVmsList.filter((x) => {
        return ((x.altMake==this.AltMakeVms) && (x.gearbox==this.gearvms) && (x.type==this.typevms)   );
      })
      for(let j=0;j<this.makearray.length;j++)
      {
      
        if(this.makearray[j].makeDefaultFlag==1)
        {
          this.makevms=this.makearray[j].make;
          temp.push(this.makearray[j].make);

          break;
        }
      }
      for(let j=0;j<this.makearray.length;j++)
{
  if(this.makearray[j].make!=this.makevms)
  {
    temp.push(this.makearray[j].make);

  }
}
this.makearray=temp;
    }
    else
    {
      let temp12=[];
    temp12.push(this.AltMakeVms);
    for(let j=0;j<this.AltMakeArray.length;j++)
    {
      if(this.AltMakeArray[j].altMake!=this.AltMakeVms)
      {
        temp12.push(this.AltMakeArray[j].altMake);
    
      }
    }
    this.AltMakeArray=temp12;
      this.geararray = this.totalVmsList.filter((x) => {
        return ((x.altMake==this.AltMakeVms));
      })

      this.typearray = this.totalVmsList.filter((x) => {
        return ((x.altMake==this.AltMakeVms) && (x.gearbox==this.gearvms));
      })

      this.makearray = this.totalVmsList.filter((x) => {
        return ((x.altMake==this.AltMakeVms) && (x.gearbox==this.gearvms) && (x.type==this.typevms)   );
      })
      this.typearray = this.typearray.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.type ===  current.type);
        if (!x) {       
            return acc.concat([current]);        
          
        } else {
          return acc;
        } 
      }, []);
      this.geararray = this.geararray.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.gearbox ===  current.gearbox);
        if (!x) {       
            return acc.concat([current]);        
          
        } else {
          return acc;
        } 
      }, []);
      let temp=[];
      temp.push(this.makevms);
for(let j=0;j<this.makearray.length;j++)
{
  if(this.makearray[j].make!=this.makevms)
  {
    temp.push(this.makearray[j].make);

  }
}
this.makearray=temp;





  let temp2=[];

      temp2.push(this.typevms);

for(let j=0;j<this.typearray.length;j++)
{
  if(this.typearray[j].type!=this.typevms)
  {
    temp2.push(this.typearray[j].type);

  }
}
this.typearray=temp2;


let temp3=[];

    temp3.push(this.gearvms);


for(let j=0;j<this.geararray.length;j++)
{
  if(this.geararray[j].gearbox!=this.gearvms)
  {
    temp3.push(this.geararray[j].gearbox);

  }
}
this.geararray=temp3;
    }

    



   
  })


}
else
{
  this._DBOElectricalComponentService.getVmsCache(this.dboFormData).subscribe(res => {
    console.log(res);
   this.totalVmsList =  res.dboVmsList
   let temp3=[];

    this.geararray = this.totalVmsList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.gearbox === current.gearbox);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);

    if(tempVms==false)
    {
      for(let j=0;j<this.geararray.length;j++)
      {
        if(this.geararray[j].gearBoxDefaultFlag==1)
        {
          this.gearvms=this.geararray[j].gearbox;
          temp3.push(this.geararray[j].gearbox);

          break;
        }
       
      }
      for(let j=0;j<this.geararray.length;j++)
{
  if(this.geararray[j].gearbox!=this.gearvms)
  {
    temp3.push(this.geararray[j].gearbox);

  }
}
this.geararray=temp3;
///////
let temp2=[];

      this.typearray = this.totalVmsList.filter((x) => {
        return ( (x.gearbox==this.gearvms));
      })
      for(let j=0;j<this.typearray.length;j++)
      {
        if(this.typearray[j].typeDefaultFlag==1)
        {
          this.typevms=this.typearray[j].type;
          temp2.push(this.typearray[j].type);

          break;
        }
      }
      this.typearray = this.typearray.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.type ===  current.type);
        if (!x) {       
            return acc.concat([current]);        
          
        } else {
          return acc;
        } 
      }, []);


      for(let j=0;j<this.typearray.length;j++)
{
  if(this.typearray[j].type!=this.typevms)
  {
    temp2.push(this.typearray[j].type);

  }
}
this.typearray=temp2;
      ///////
      let temp=[];

      this.makearray = this.totalVmsList.filter((x) => {
        return ( (x.gearbox==this.gearvms) && (x.type==this.typevms)   );
      })
      for(let j=0;j<this.makearray.length;j++)
      {
      
        if(this.makearray[j].makeDefaultFlag==1)
        {
          this.makevms=this.makearray[j].make;
          temp.push(this.makearray[j].make);
          break;
        }
      }
      for(let j=0;j<this.makearray.length;j++)
{
  if(this.makearray[j].make!=this.makevms)
  {
    temp.push(this.makearray[j].make);

  }
}
this.makearray=temp;
    }
    else
    {
      temp3.push(this.gearvms);
      for(let j=0;j<this.geararray.length;j++)
      {
        if(this.geararray[j].gearbox!=this.gearvms)
        {
          temp3.push(this.geararray[j].gearbox);
      
        }
      }
      this.geararray=temp3;
      this.typearray = this.totalVmsList.filter((x) => {
        return ( (x.gearbox==this.gearvms));
      })
      this.typearray = this.typearray.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.type ===  current.type);
        if (!x) {       
            return acc.concat([current]);        
          
        } else {
          return acc;
        } 
      }, []);
      let temp2=[];

      temp2.push(this.typevms);

for(let j=0;j<this.typearray.length;j++)
{
  if(this.typearray[j].type!=this.typevms)
  {
    temp2.push(this.typearray[j].type);

  }
}
this.typearray=temp2;
      this.makearray = this.totalVmsList.filter((x) => {
        return ((x.gearbox==this.gearvms) && (x.type==this.typevms)   );
      })
      let temp=[];
      temp.push(this.makevms);
for(let j=0;j<this.makearray.length;j++)
{
  if(this.makearray[j].make!=this.makevms)
  {
    temp.push(this.makearray[j].make);

  }
}
this.makearray=temp;
    }
  });
}
    });
  
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
    this.mainSave=true;
    this.mainSave2=true;
    this.getPrice=true;
    if(this.lhsdes.includes(true) || this.openOth1.includes(true) || this.disablelhs.includes(true) || this.lhsdes2.includes(true) || this.rhsdes.includes(true) || this.rhsdes2.includes(true) )
    {
      this.getPrice=false;
    }
    if(this.getPrice)
    {
    this._ITOeditQoutService.dboEleCiData=[];
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
      if(this.itemCode!="VMS")
      {
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
              this.dboClass.techFlag = this.questionsBean[j].techFlag;
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
            if(this.questionsBean[l].colType == "TEXT" || this.questionsBean[l].colType == "DROPDOWN"){
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
            this.dboClass.techRemarks = null;
            this.dboClass.comrRemarks = null;
            
            this.dboClass.addOnNewColFlag = 0;
           
            this.dboClass.orderId=this.questionsBean[l].orderId;
            this.dboClass.defaultFlagNew=1;
            this.dboClass.dispInd=1;
            this.dboClass.techFlag = this.questionsBean[l].dropDownValueList[d].techFlag;
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
      let tempcounter=0;
   for(let j=0;j<this.questionsBean.length;j++)
   {
     if(this.questionsBean[j].colType == "DROPDOWNCHECKBOX"){
for(let i=0;i<this.questionsBean[j].dropDownValueList.length;i++)
{
  if(this.dropCheckQty[i] == true && this.drpCheckqtyyKey[this.questionsBean[j].orderId] == true)
  {
    tempcounter=1;
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
            if(this.questionsBean[j].dropDownValueList[i].value=="Not Required")
            {
              this.dboClass.quantity =null; 
            }
            else
            {
              this.dboClass.quantity = this.drpCheckqtyy[i]; 
            }
                  
            this.dboClass.cost = null;          
            this.dboClass.techRemarks = null;
            this.dboClass.comrRemarks = null;
            this.dboClass.addOnNewColFlag = 0;
            this.dboClass.orderId= this.questionsBean[j].orderId;
            this.dboClass.defaultFlagNew=1;
            this.dboClass.dispInd=1;
            this.dboClass.techFlag =  this.questionsBean[j].dropDownValueList[i].techFlag;
            this.dboClass.comrFlag = this.questionsBean[j].comrFlag;
            //this.test123.push( this.dboClass);
            this.SelectedExcelData.push(this.dboClass);
  }
}
     }
     else if (this.questionsBean[j].colType == "EDIT_TEXT"){
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
   if(tempcounter==0)
   {

   }
  }
  else
  {
    for(let j = 0; j< this.questionsBean.length; j++){
      //Type of Model
     // Alternator Rating (KW)
      if(this.questionsBean[j].dispInd == 1)
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
            if(this.questionsBean[j].dropDownType.value=="Alternator Make")
            {
              this.dboClass.colValCd =this.AltMakeVms; 

            }
            if(this.questionsBean[j].dropDownType.value=="Gear Box Make")
            {
              this.dboClass.colValCd =this.gearvms; 

            }
            if(this.questionsBean[j].dropDownType.value=="Type")
            {
              this.dboClass.colValCd =this.typevms; 

            }
            if(this.questionsBean[j].dropDownType.value=="Make")
            {
              this.dboClass.colValCd =this.makevms; 

            }
            this.dboClass.subColValCode=null;
            this.dboClass.quantity = null;         
            this.dboClass.cost = null;          
            this.dboClass.techRemarks = null;
            this.dboClass.comrRemarks = null;
            this.dboClass.addOnNewColFlag = 0;
            this.dboClass.orderId= this.questionsBean[j].orderId;
            this.dboClass.defaultFlagNew=1;
            this.dboClass.dispInd=1;
            this.dboClass.techFlag = this.questionsBean[j].techFlag;
            this.dboClass.comrFlag = this.questionsBean[j].comrFlag;
            //this.test123.push( this.dboClass);
            this.SelectedExcelData.push(this.dboClass);    
       
      }
    }
  }
   //this.SelectedExcelData.sort();
  // let values = ["B_Value", "C_Value", "A_Value"];

// Ascending
//this.SelectedExcelData.sort((a,b) => a.orderId.localeCompare(b.orderId));

//console.log(values);
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
        if(this.lhsRhsSubItemsList[g].itemId==this.itemId && this.lhsRhsSubItemsList[g].colNm!=null)
        {
        this.SelectedExcelData.push(this.lhsRhsSubItemsList[g]);
        }
      }
      for(let h=0;h<this.lhsRhsDesSubItemsList.length;h++)
      {
        if(this.lhsRhsDesSubItemsList[h].itemId==this.itemId && this.lhsRhsDesSubItemsList[h].colNm!=null)
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
  this._DBOElectricalComponentService.getEleTechPrice(this.dboFormData).subscribe(res => {
    console.log(res);
      this.panelapplicable=true;
      this.panelapplicablescm=true;
      this.Ifapplicabletemp=[];
      this.Ifapplicable=[];


    for(let j=0;j<res.eleTechList.length;j++)
    {
      if(res.eleTechList[j].itemApproxCostFlag==2 )
      {
        this.panelapplicable=false;
        this.saveBtColor=false;
        this.buttonColor="red";

        this.Ifapplicabletemp.push(res.eleTechList[j].itemErrMessage);
        this.Ifapplicable.push(res.eleTechList[j].itemErrMessage);

      }
      if(res.eleTechList[j].itemApproxCostFlag==1 )
      {
        this.panelapplicablescm=false;
        this.Ifapplicabletemp.push(res.eleTechList[j].itemErrMessage);
        this.Ifapplicable.push(res.eleTechList[j].itemErrMessage);


        this.saveBtColor=false;
        this.buttonColor="blue";

      }

     
    }
    if(this.panelapplicable && this.panelapplicablescm ){
      this.saveBtColor=false;
      this.buttonColor="green";
    }
   
    this.addOnCost=[];
    this.itemcost=res.eleTechList[0].itemCost;
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
      this._ITOcustomerRequirementService.sendCIBtnStatus(true);
      //f2fTechList contains the drop down default values
      // as wellas the new lhs and rhs values
      //this._ITOeditQoutService.dboEleCiData = res.eleTechList;
      //error msg and rhs costdispaly on panel
      this.errDisplayPnl=[];
      for(let c=0;c<res.eleTechList.length;c++){
        if(( res.eleTechList[c].addOnFlg == 1) && (res.eleTechList[c].lhsFlag==0) && (res.eleTechList[c].addOnNewColFlag==0) && (res.eleTechList[c].inputCostFlag==0)  ){
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
         if(( res.eleTechList[c].rhsCost == 0) && (res.eleTechList[c].lhsFlag==0) && (res.eleTechList[c].addOnNewColFlag==0) &&(res.eleTechList[c].errorMsg!=null) && (res.eleTechList[c].errorMsg!="") && res.eleTechList[c].errorMsg!="NULL" && (res.eleTechList[c].inputCostFlag==0) )
        {
          this.errMsgRhsCost[res.eleTechList[c].colId] = "";
          this.errMsgPnl[res.eleTechList[c].colId] = "      Note:" +res.eleTechList[c].errorMsg;
          this.errDisplayPnl[res.eleTechList[c].colId] = true;
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
      
      console.log(this._ITOeditQoutService.dboEleCiData);
    } else {
      this.subMessage = true;
      this.messageVal = res.successMsg;
    }
    this.dboBasicCost = res.basicCost;
    console.log(this.newPanelval);
    if(this.itemCode=="VMS" && this.panelType!="DT")
    {
      this.defaultValues=[];
      this.defaultValues.push(this.AltMakeVms);
      this.defaultValues.push(this.gearvms);
      this.defaultValues.push(this.typevms);
      this.defaultValues.push(this.makevms);

    }
    else if(this.itemCode=="VMS" && this.panelType=="DT")
    {
      this.defaultValues=[];
      this.defaultValues.push(this.gearvms);
      this.defaultValues.push(this.typevms);
      this.defaultValues.push(this.makevms);
    }
    // after sucessful response store the values to local variables
    if (this.dboEleCIFullArray.length != 0) {
      for (let d = 0; d < this.dboEleCIFullArray.length; d++) {
        let j = this.dboEleCIFullArray.map(d => { return d.id }).indexOf(this.eleItemId);
        if (j != (-1)) {
          this.dboEleCIFullArray[j] = {
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
      itemcost:this.itemcost,
      checkboolean:this.checkboolean,
      panelapplicable:this.panelapplicable,
      panelapplicablescm:this.panelapplicablescm,
      colNumber:this.colNumber,
      Ifapplicabletemp:this.Ifapplicabletemp,
      tempflagsave:this.techFlagindicator

      

          };
          break;
        }
        else {
          this.dboEleCIFullArray.push({
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
      itemcost:this.itemcost,
      checkboolean:this.checkboolean,
      panelapplicable:this.panelapplicable,
      panelapplicablescm:this.panelapplicablescm,
      colNumber:this.colNumber,
      Ifapplicabletemp:this.Ifapplicabletemp,
      tempflagsave:this.techFlagindicator





          });
          break;
        }
      }
    }
    else {
      this.dboEleCIFullArray.push({
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
      itemcost:this.itemcost,
      checkboolean:this.checkboolean,
      panelapplicable:this.panelapplicable,
      panelapplicablescm:this.panelapplicablescm,
      colNumber:this.colNumber,
      Ifapplicabletemp:this.Ifapplicabletemp,
      tempflagsave:this.techFlagindicator





      });
    }
       
    console.log(this.dboEleCIFullArray);
    this.addedClassList = [];
if(this.itemName=="Set of STG Instruments List")

{


    this._DBOElectricalComponentService.getEleInstr(this.dboFormData).subscribe(res => {
      console.log(res);
      this.Cost=[];
      this.dboEleInstrList=res.dboEleInstrList;
      // for(let jk=0;jk< this.dboEleInstrList.length;jk++)
      // {
      //  if(this.dboEleInstrList[jk].rhsFlag==1 || this.dboEleInstrList[jk].addOnNewColFlag==1 )
      //  {
      //   this.dboEleInstrList[jk]=null;
      //  }
      // }
      // this.dboEleInstrList = this.dboEleInstrList.filter(n => n != null);

      this.dboEleInstrListNew =  this.dboEleInstrList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.instrCode === current.instrCode);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      this.dboEleInstrListNew =  this.dboEleInstrList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.instrCode === current.instrCode);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      if(this.buttonColor!="blue")
{

  this.Ifapplicable=[];
      for(let h=0;h<this.dboEleInstrListNew.length;h++)

      {
        if(this.dboEleInstrListNew[h].approxFlag==1)
        {
          this.panelapplicablescm=false;
          this.Ifapplicable.push("Get the Basic Cost From Scm");
  
  
          this.saveBtColor=false;
          this.buttonColor="blue";
        }
      }
    }
      for(let j=0;j<this.dboEleInstrListNew.length;j++)
      {
        let count=0;
        for(let h=0;h<this.dboEleInstrList.length;h++)
        {
          if(this.dboEleInstrList[h].instrCode==this.dboEleInstrListNew[j].instrCode)
          {
            count=count+1;
            this.ciList[h]=count;
          }
        }
      }
      for(let k=0;k<this.dboEleInstrListNew.length;k++)
  {
    this.Cost[this.dboEleInstrListNew[k].itemId]=this.dboEleInstrListNew[k].cost;
  }
      this.dboEleInstrListNew2=[];
      this.dboEleInstrListNew3=[];
      this.dboEleInstrListNew2 =  this.dboEleInstrList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.addInstrId === current.addInstrId);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      this.dboEleInstrListNew1 =  this.dboEleInstrList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.addInstrId === current.addInstrId);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      this.dboEleInstrListNew3 =  this.dboEleInstrList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.addInstrId === current.addInstrId);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
       this.dboEleInstrListNew1 =  this.dboEleInstrListNew3.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.instrTypeNm === current.instrTypeNm);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      
  for(let k=0;k<this.dboEleInstrListNew1.length;k++)
  {
if(this.dboEleInstrListNew1[k].addInstrId==0)
{
this.dboEleInstrListNew1.splice(k,1);
}
  }
      if(res.dboEleInstrList.length!=0)
      {
        this.displaypanel12=true;
      }
     // this.itemOthersList1=[];
     // this.displayOthnewLine2=[];
      //this.othersSubTypCheck=[];
     // this.othersSubItmTypChk=[];
     this.getPriceExelCI();
    });
  }
  if(this.itemName=="Turbine Vibration Monitoring System (VMS)")

{


    this._DBOElectricalComponentService.getEleVms(this.dboFormData).subscribe(res => {
      console.log(res);
      this.Cost=[];
      this.dboEleInstrList=[];
      this.dboEleInstrListNew=[];
      this.vmsCounter=[];
      this.dboEleInstrList=res.dboEleVmsList;
      console.log(this.dboEleInstrList);
      let counter=0;
      for(let i=0;i<this.dboEleInstrList.length;i++)
      {
        if(this.dboEleInstrList[i].addPrbFlag==1)
        {
          counter=counter+1;
          this.vmsCounter.push(counter);
  
        }
        else
        {
          this.vmsCounter.push(i);
  
        }
      }
      this.dboEleInstrListNew =  this.dboEleInstrList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.addPrbFlag === current.addPrbFlag);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      for(let k=0;k<this.dboEleInstrListNew.length;k++)
  {
    this.Cost[this.dboEleInstrListNew[k].id]=this.dboEleInstrListNew[k].cost;
  }
       this.displayvms=true;
      // this.itemOthersList2=[];
      // this.check=[];
      // this.check2=[];
      // this.displayOthnewLine2=[];
      // this.othersSubTypCheck=[];
      // this.othersSubItmTypChk=[];
if(this.dboEleInstrList.length!=0)
{
       this.getPriceExelVMS();
}

    });
     
    }
    for (let m = 0; m < this.dboEleCIFullArray.length; m++) {
      this.addedClassList.push(this.dboEleCIFullArray[m].componenet);
    }
    this.hideprogressCost = false;

  
      this.addedClassList = [];
      for (let m = 0; m < this.dboEleCIFullArray.length; m++) {
        this.addedClassList.push(this.dboEleCIFullArray[m].componenet);
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
       this.saveInLocal(this.dboEleCIFull, { 
        dboEleCIFullArray: this.dboEleCIFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
        itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
        selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
        itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
        subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
        eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
        cICost: this.cICost, totalDboCiCost: this.totalDboCiCost, itemOthersList1: this.itemOthersList1, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList, itemOthersList2: this.itemOthersList2,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList    
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
  addLhsRhsItem1(i){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsItemSel1 = '';
    this.ibreakothers = i;
    console.log(i); 
    //later 
    this.lhsRhsItemSel1 = this.itemOthersList1[i].itemName;
    ////later
    this.lhsRhsItemSel1="shanti";
   //// remove

   ///
    console.log(this.lhsRhsItemSel1);
      this.displayDialogLhsRhs1 = true;
     // this.displayOthnewLine1 = false;
      
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
  addLhsRhsForm1(lhsRhsItem, lhsRhsItemfrm: NgForm){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    console.log(lhsRhsItem);
   // this.ibreakothers = 0;
       for(let k = 0; k < this.lhsRhsItemsList1.length; k++)
       {
        if(this.lhsRhsItemsList1[k].itemName == this.itemOthersList1[this.ibreakothers].itemName && this.lhsRhsItemsList1[k].colId == null && this.lhsRhsItemsList1[k].colNm == null){
          this.lhsRhsItemsList1.splice(k,1);
          this.ibreakothers = 1;
          break;          
        }
      }
      this.ibreakothers = 0;
    //this.lhsRhsItemsList = [];
    for(let c=0; c<this.itemOthersList1.length; c++){
      if(this.lhsRhsItemSel == this.itemOthersList1[c].itemName){
        this.dboClass = new dboClass();
        this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        this.dboClass.itemId = 0;
        this.dboClass.itemName = this.itemOthersList1[c].itemName;
        this.dboClass.desSubItemId=null;
        this.dboClass.desSubItemName=null;
        this.dboClass.subItemId = this.itemOthersList1[c].subItemId;
        this.dboClass.subItemName = this.itemOthersList1[c].subItemName;
        this.dboClass.colId = 0;
        this.dboClass.colNm = lhsRhsItem.lhsVal;
        this.dboClass.colValCd = lhsRhsItem.rhsVal;
        this.dboClass.subColValCode=null;
        this.dboClass.quantity = this.itemOthersList1[c].quantity;
        this.dboClass.cost = this.itemOthersList1[c].cost;
        this.dboClass.techRemarks = this.itemOthersList1[c].techRemarks;
        this.dboClass.comrRemarks = this.itemOthersList1[c].comrRemarks;
        this.dboClass.addOnNewColFlag = 1;
        this.dboClass.dispInd=1;
        this.dboClass.techFlag = 1;
        this.dboClass.comrFlag = 1;
       
        this.lhsRhsItemsList1.push(this.dboClass);



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
  cancelLinesLhs1(i){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsnewLine1 = false;
    this.lhsRhsItemsList1.splice(i, 1);
  }
  cancelnewLineLhs(){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsnewLine = false;//added by megha
  }
  cancelnewLineLhs1(){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsnewLine1 = false;//added by megha
  }
  addRowsLhsRhs(){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsnewLine = true;
  }
  addRowsLhsRhs1(){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsnewLine1= true;
  }

calcTotal()
{
  this._ITOeditQoutService.dboEleCiData=[];
  if (this.enableOverwriteDiv) {
    this.dboFormData.overwrittenPrice = this.OverWrittencICost;
    this.dboFormData.overwrittenPriceFlag = 1;
    this.dboFormData.remarks = this.remarks;
  } else if (this.OverWrittencICost > 0) {
    this.dboFormData.overwrittenPrice = this.OverWrittencICost;
  //  this.panelList.overwrittenPriceFlag = 1;
  }
 // this.dboFormData.modBy = this._ITOLoginService.loggedUserDetails;
  this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails; 
  this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboFormData.scopeCd = "CI";
  this.dboFormData.eleItemTechComments = this.itemRemarksVal;
  this.dboFormData.eleItemComrComments = this.itemCmrRemarksVal;
  this.dboFormData.eleItemIdList = this.itemIdList.filter(n => n != null);
  console.log(this.itemIdList)
  if(this.storage.get(this.dboEleAuxFull)!=null)
  {
    if(this.storage.get(this.dboEleAuxFull).itemIdList.length!=0){
      this.itemIdListAux= this.storage.get(this.dboEleAuxFull).itemIdList;
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
  this._DBOElectricalComponentService.saveEleItem(this.dboFormData).subscribe(savedResp => {
    console.log(savedResp);
  this.cICost=savedResp.totalPrice;
  this._ITOeditQoutService.dboEleItmOthers = savedResp.saveEleList;
  if(savedResp.successCode == 0){
    this.mainSave2=false;
    this.finalCostBool = true;
    this.message = true;
    this.messageVal = "Cost Saved Successfully"
  }else{
    this.message = true;
    this.successMsg = savedResp.successMsg;
  }
  this.saveInLocal(this.dboEleCIFull, {
    dboEleCIFullArray: this.dboEleCIFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
    itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
    selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
    itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
    eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
    cICost: this.cICost, totalDboCiCost: this.totalDboCiCost, itemOthersList1: this.itemOthersList1, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList, itemOthersList2: this.itemOthersList2,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
  });
  //call one line BOM
  this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
    console.log(resOnline);
    if (resOnline.successCode == 0) {
      this.cICost = resOnline.oneLineBomExcel.cICost;
      this.totalDboCiCost = resOnline.oneLineBomExcel.totalDboCiCost;
    } 
    this.saveInLocal(this.dboEleCIFull, {
      dboEleCIFullArray: this.dboEleCIFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
      cICost: this.cICost, totalDboCiCost: this.totalDboCiCost, itemOthersList1: this.itemOthersList1, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList, itemOthersList2: this.itemOthersList2,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
    });     
    this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
    this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, cICost: this.cICost, totalDboCiCost: this.totalDboCiCost});
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
  this._ITOeditQoutService.dboEleCiData=[];
  if (this.enableOverwriteDiv) {
    this.dboFormData.overwrittenPrice = this.OverWrittencICost;
    this.dboFormData.overwrittenPriceFlag = 1;
    this.dboFormData.remarks = this.remarks;
  } else if (this.OverWrittencICost > 0) {
    this.dboFormData.overwrittenPrice = this.OverWrittencICost;
  //  this.panelList.overwrittenPriceFlag = 1;
  }
 // this.dboFormData.modBy = this._ITOLoginService.loggedUserDetails;
 this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails; 
  this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboFormData.scopeCd = "CI";
  this.dboFormData.eleItemTechComments = this.itemRemarksVal;
  this.dboFormData.eleItemComrComments = this.itemCmrRemarksVal;
  this.dboFormData.eleItemIdList = this.itemIdList.filter(n => n != null);
  if(this.storage.get(this.dboEleAuxFull)!=null)
  {
    if(this.storage.get(this.dboEleAuxFull).itemIdList.length!=0){
      this.itemIdListAux= this.storage.get(this.dboEleAuxFull).itemIdList;
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
  this._DBOElectricalComponentService.saveEleItem(this.dboFormData).subscribe(savedResp => {
    console.log(savedResp);
  this.cICost=savedResp.totalPrice;
  this._ITOeditQoutService.dboEleItmOthers = savedResp.saveEleList;
  if(savedResp.successCode == 0){
    this.message = true;
    this.mainSave=false;

    this.messageVal = "Cost Saved Successfully";

    let sos = this.storage.get(this.scopeofsupp);
    for (let s = 0; s < sos.length; s++) {
      if (sos[s].scopeCode == 'CI') {
        this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
        this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
        this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
          console.log(res);
        })
      }
    }

    //Calling saveRemarks to save overwriten cost and comments
   this._ITOcustomerRequirementService.saveBasicDet.groupCode = 'OVERWRITE';
   this._ITOcustomerRequirementService.saveBasicDet.scopeCode = 'CI';
   this._ITOcustomerRequirementService.saveBasicDet.overwrittenCost = this.OverWrittencICost;
   this._ITOcustomerRequirementService.saveBasicDet.remarks = this.remarks;   
   console.log(this._ITOcustomerRequirementService.saveBasicDet);
   this._ITOAddOnComponentsService.saveRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(saveRem => {
     console.log(saveRem);
   })


  }else{
    this.message = true;
    this.successMsg = savedResp.successMsg;
  }
  this.saveInLocal(this.dboEleCIFull, {
    dboEleCIFullArray: this.dboEleCIFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
    itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
    selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
    itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
    eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
    cICost: this.cICost, totalDboCiCost: this.totalDboCiCost, itemOthersList1: this.itemOthersList1, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList, itemOthersList2: this.itemOthersList2,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
  });  
  //call one line BOM
  this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
    console.log(resOnline);
    if (resOnline.successCode == 0) {
      this.cICost = resOnline.oneLineBomExcel.cICost;
      this.totalDboCiCost = resOnline.oneLineBomExcel.totalDboCiCost;
    } 
    this.saveInLocal(this.dboEleCIFull, {
      dboEleCIFullArray: this.dboEleCIFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
      cICost: this.cICost, totalDboCiCost: this.totalDboCiCost, itemOthersList1: this.itemOthersList1, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList, itemOthersList2: this.itemOthersList2,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList
    });     
    this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
    this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, cICost: this.cICost, totalDboCiCost: this.totalDboCiCost});
    if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
      this._ITOcustomerRequirementService.editFlag = false;
      this.router.navigate(['/EditQuot']);
    }else{
      this.router.navigate(['/CostEstimation/ControlInstrumentation/ItoControlInstAux']);
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
  dboEleItmList(event, itemId, i,name)
  {
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
      console.log(this.dboEleCIFullArray);
      for(let k = 0; k < this.dboEleCIFullArray.length; k++){
        if(this.dboEleCIFullArray[k].itemId == itemId){
            this.dboEleCIFullArray[k] = null;
            this.dboEleCIFullArray.splice(k, 1);
            
          break;
        }
      }
      if(name=="Set of STG Instruments List")
      {
this.itemOthersList1=[];
      }
      if(name=="Turbine Vibration Monitoring System (VMS)")
      {
        this.check=[];
        this.check2=[];

        this.itemOthersList2=[];
      }
      for(let k = 0; k < this.tempitemOthersList.length; k++){
        if(this.tempitemOthersList[k].itemId == itemId){
            this.tempitemOthersList[k] = null;
            this.tempitemOthersList.splice(k, 1);
         
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
        if(itemId==this.subItemOthAddonList[x].itemId)
        {
          this.subItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsSubItemsList.length;x++)
      {
        if(itemId==this.lhsRhsSubItemsList[x].itemId)
        {
          this.lhsRhsSubItemsList[x]=null;  
        }
      }
      this.subItemOthAddonList = this.subItemOthAddonList.filter(n => n != null);
      this.lhsRhsSubItemsList = this.lhsRhsSubItemsList.filter(n => n != null);
      for(let x=0;x<this.dessubItemOthAddonList.length;x++)
      {
        if(itemId==this.dessubItemOthAddonList[x].itemId)
        {
          this.dessubItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsDesSubItemsList.length;x++)
      {
        if(itemId==this.lhsRhsDesSubItemsList[x].itemId)
        {
          this.lhsRhsDesSubItemsList[x]=null;  
        }
      }
      this.dessubItemOthAddonList = this.dessubItemOthAddonList.filter(n => n != null);
      this.lhsRhsDesSubItemsList = this.lhsRhsDesSubItemsList.filter(n => n != null);
  
    }
   
    console.log(this.dboEleCIFullArray)
    this.dboCost=0;
    this.selectdEl[i]=false;
  }

  //check box for sub items
  dboMechItmListSub(event, itemId, i){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    console.log(event, itemId, i);
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
      for(let k=0; k<this.dboEleCIFullArray.length; k++){
        if(this.dboEleCIFullArray[k].subItemId == itemId){
          this.dboEleCIFullArray[k] = null;
          this.dboEleCIFullArray.splice(k,1);
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
    }
    console.log("after");
    console.log(this.dboEleCIFullArray);
  }
//Open others table to enter data to create new subitem others
  
  

  // opening new table for others
  openCompTable(event,index,type) {
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
     this.disablelhs= this.disablelhs.filter(n => n != null);

    }
  }
  openCompTable1(event,index) {
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    if (event.target.checked) {
      this.displayCompOthTable1[index] = true;
    }
    else if (!event.target.checked) {
      this.displayCompOthTable1[index] = false;
     for(let j=0;j<this.itemOthersList1.length;j++)
     {
if(this.itemOthersList1[j].instrItemId==index)
{
  this.itemOthersList1[j]=null;
}
     }
     this.itemOthersList1= this.itemOthersList1.filter(n => n != null);

    }
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
  ////new others for table
  compOthersForm1(others, othersfrm: NgForm,id,name) {
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    console.log(others);
    console.log(name);
   
    this.dboClass = new dboClass();
    this.dboClass.itemId =id.itemId;
    this.dboClass.itemNm =null;
    if(name=="lhs")
    {
      this.dboClass.colId =0;
    }
    else
    {
      this.dboClass.colId =id.addInstrId;
    }
    if(name=="lhs")
    {
      this.dboClass.lhsColNm =others.othItemName;
    }
    else
    {
      this.dboClass.lhsColNm =null;
    }
   
    this.dboClass.colValCd =others.othdes;
    this.dboClass.subColValCode =others.othmoun;
    this.dboClass.quantity =others.othqty;
  //  this.dboClass.cost =others.othcost;
    if(name=="lhs")
    {
      this.dboClass.cost=others.othcost;
    }
    else
    {
      this.dboClass.cost =id.cost*others.othqty;
    }
    if(name=="lhs")
    {
      this.dboClass.addOnNewColFlag =1;

    }
    else
    {
      this.dboClass.addOnNewColFlag =0;
    }
    if(name=="lhs")
    {
      this.dboClass.otherItemInstrFlag =0;

    }
    else
    {
      this.dboClass.otherItemInstrFlag =0;
    }

    this.itemOthersList1.push(this.dboClass);

    this.tempitemOthersList.push(this.dboClass);
    this.disablelhstg[this.itemOthersList1.length-1]=false;
    console.log(this.itemOthersList1);

   
    othersfrm.reset();
   if(name=="lhs")
   {
    this.displayOthnewLine1[id.itemId] = false;
   }
   else
   {
    this.displayOthnewLine1[id.addInstrId] = false;
   }
     
    
   
  }
  compOthersForm2(others, othersfrm: NgForm,id,name) {
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    console.log(others);
    console.log(name);
   
    this.dboClass = new dboClass();
    this.dboClass.type=id.type;
    this.dboClass.addPrbFlag=id.addPrbFlag;
    this.dboClass.orderBy=0;
    this.dboClass.tagNum=others.othItemName;
    this.dboClass.equipment=others.othdes;
    this.dboClass.application=others.othmoun;
    this.dboClass.location=others.othqty;
    this.dboClass.quantity=others.othcost1;
    this.dboClass.cost=others.othcost;
    this.dboClass.newColValFlag=1;
  //   bean.getType(),
  //   bean.isAddPrbFlag(),
  //   bean.getOrderBy(),
  //   bean.getTagNo(),
  //  bean.getEquipment(),
  //   bean.getApplication(),
  //   bean.getLocation(),
  //    bean.getQuantity(),
  //  bean.getCost(),
  //  bean.isNewColValFlag()

    this.itemOthersList2.push(this.dboClass);
    this.tempitemOthersList.push(this.dboClass);
    this.disablelhsvms[this.itemOthersList2.length-1]=false;
    console.log(this.itemOthersList2);
   
    othersfrm.reset();
   
    this.displayOthnewLine1[id.addPrbFlag] = false;
   
    
   
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
     
if(  this.itemOthersList[j].desItemId==id )
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
  cancelnewLineOth34(id, othersfrm: NgForm) {
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
     
if(this.itemOthersList[j].desSubItemId==id  )
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

  cancelnewLineOth1(id,othersfrm: NgForm) {
    othersfrm.reset();
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.displayOthnewLine1[id] = false;
if(this.itemOthersList1.length!=0)
{
  let counter=0;

    for(let j=0;j<this.itemOthersList1.length;j++)
    {
     
if(this.itemOthersList1[j].desSubItemId==id)
{
counter=1;
}

    }
    if(counter==0)
    {
      this.displayCompOthTable1[id]=false;
     // this.othersCompCheck[id]=false;
    }
  }
  }
  cancelnewLineOth2(id,othersfrm: NgForm) {
    othersfrm.reset();
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.displayOthnewLine1[id] = false;
  }
    // adding new other lines
    addRowsOth(id) {
      this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
      this.displayOthnewLine[id] = true;
    }
    addRowsOth1(id) {
      this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
      this.displayOthnewLine1[id] = true;
    }
    cancelLinesOth1(i,id,type) {
      
      this.mainSave=true;
      this.mainSave2=true;
          this.saveBtColor=true;

      this.itemOthersList1.splice(i, 1);
      this.disablelhstg.splice(i, 1);


    }
    cancelLinesOth2(i,id) {
      this.mainSave=true;
      this.mainSave2=true;
          this.saveBtColor=true;
      this.itemOthersList2.splice(i, 1);
      this.disablelhsvms.splice(i, 1);


    }
    cancelLinesOth(i,id,id2) {
      this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
      let name=this.itemOthersList[i].colValNm;
      let value=this.itemOthersList[i].colValCd;
      this.disablelhs.splice(i,1);

      this.itemOthersList.splice(i, 1);  
      let counter=0;

      if(this.itemOthersList.length!=0)
{

    for(let j=0;j<this.itemOthersList.length;j++)
    {
     
if(this.itemOthersList[j].desSubItemId==id2 && this.itemOthersList[j].desItemId==id)
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
      for( let j=0;j<this.dboEleCIFullArray.length;j++)
      {
        if(this.dboEleCIFullArray[j].itemId==this.itemId && this.dboEleCIFullArray[j].subItemId==this.subItemId)
        {
         
          if(this.dboEleCIFullArray[j].itemOthersList.length!=0)
          {
            for(let i=0;i<this.dboEleCIFullArray[j].itemOthersList.length;i++)
            {
              if(this.dboEleCIFullArray[j].itemOthersList[i].itemId==this.itemId && this.dboEleCIFullArray[j].itemOthersList[i].subItemId==this.subItemId && this.dboEleCIFullArray[j].itemOthersList[i].colNm==name  && this.dboEleCIFullArray[j].itemOthersList[i].colValCd==value )
              {
                this.dboEleCIFullArray[j].itemOthersList[i]=null;
                this.dboEleCIFullArray[j].itemOthersList.splice(i,1);
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
    this.checkboolean=false;
    console.log(arrIn, arr, event);
    console.log(c);
    if(event.target.checked){
    this.drpCheck[c] = true;
    if(arrIn=="Not Required")
    {
      this.drpCheckqtyy=[];
      this.drpCheck=[];
      this.dropCheckQty = [];
      this.drpCheckqtyyKey[arr.orderId]=true;
      this.checkboolean=true;
      this.drpCheckqtyy[c]=null;
     
    }
  else
  {
    this.drpCheckqtyy[c]=0;
  }
      this.dropCheckQty[c] = true;

  
    
    }else if(!event.target.checked){
      this.drpCheck[c] = true;
      if(arrIn=="Not Required")
    {
     
      this.drpCheckqtyy=[];
      this.drpCheck=[];
      this.dropCheckQty = [];
this.checkboolean=false;      
      console.log("Test");
     // this.dropCheckQty = [];
      //this.dropCheckQty[c]=false;
      this.drpCheckqtyyKey[arr.orderId]=false;
    }
    else
    {
      this.dropCheckQty[c] = false;
    }
      this.drpCheckqtyy[c]=0;
    }
    console.log(this.dropCheckQty[c],c);
  }
  getPriceExelVMS()
  {
    this.getPricevms=true;
    let count=this.dboEleInstrList.length;
    this.dboFormData.eleVmsData=[];

    if(this.disablelhsvms.includes(true))
    {
      this.getPricevms=false;
    }
    if(this.getPricevms )
    {
    this._ITOeditQoutService.dboEleCiData=[];

    this.dboFormData.eleVmsData=[];
    for(let x1=0;x1<this.itemOthersList2.length;x1++)
    {
      if(this.itemOthersList2[x1].newColValFlag==1)
      {
count=count+1;
this.itemOthersList2[x1].orderBy=count;
      }
    }
    for(let x1=0;x1<this.itemOthersList2.length;x1++)
    {
      if(this.itemOthersList2[x1].newColValFlag==0)
      {
this.itemOthersList2[x1]=null;;
      }
    }
    this.itemOthersList2= this.itemOthersList2.filter(n => n != null);

    for(let x1=0;x1<this.itemOthersList2.length;x1++)
    {
      this.dboFormData.eleVmsData.push(this.itemOthersList2[x1]);
    }
    if(this.check.length!=0)
    {
    
       for(let x=0;x<this.dboEleInstrList.length;x++)
       {

         if( this.check[this.dboEleInstrList[x].orderBy]==true)
         {
           let counter=0;
        for(let z=0;z<this.itemOthersList2.length;z++)
        {
          if(this.dboEleInstrList[x].orderBy==this.itemOthersList2[z].orderBy)
          {
counter=1;
          }
        }
        if(counter==0)
        {

        
          this.dboClass = new dboClass();
          
          this.dboClass.type=this.dboEleInstrList[x].type;
          this.dboClass.addPrbFlag=this.dboEleInstrList[x].addPrbFlag;
          this.dboClass.orderBy=this.dboEleInstrList[x].orderBy;
          this.dboClass.tagNum=this.dboEleInstrList[x].tagNum;
          this.dboClass.equipment=this.dboEleInstrList[x].equipment;
          this.dboClass.application=this.dboEleInstrList[x].application;
          this.dboClass.location=this.dboEleInstrList[x].location;
          this.dboClass.quantity=this.dboEleInstrList[x].quantity;
          this.dboClass.cost=this.dboEleInstrList[x].cost;
          this.dboClass.newColValFlag=0;
          this.dboFormData.eleVmsData.push(this.dboClass);
          this.itemOthersList2.push(this.dboClass);``
        }

         }
         
       }
    
  }

    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails; 
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  console.log( this.dboFormData.eleVmsData);
    this._DBOElectricalComponentService.saveEleVms(this.dboFormData).subscribe(savedResp => {
      console.log(savedResp);
      this.dboCost = savedResp.saveEleVmsList[0].basicCost;
      this.dboAddOnCost= savedResp.saveEleVmsList[0].addOnCost;
     this.itemcost =savedResp.saveEleVmsList[0].itemCost
 if(this.dboEleCIFullArray.length!=0)
      {
        for(let j=0;j<this.dboEleCIFullArray.length;j++)
        {
          if(this.dboEleCIFullArray[j].itemName=="Turbine Vibration Monitoring System (VMS)")
          {
            this.dboEleCIFullArray[j].dboCost= savedResp.saveEleVmsList[0].basicCost;
            this.dboEleCIFullArray[j].dboAddOnCost=savedResp.saveEleVmsList[0].addOnCost;
            this.dboEleCIFullArray[j].itemcost=savedResp.saveEleVmsList[0].itemCost;
          }
        }
      }
    });
  }
  
  }

  getPriceExelCI()
  {
    this.getPricestg=true;
    let count=0;
    count=this.dboEleInstrListNew3.length;
    if(this.disablelhstg.includes(true))
    {
      this.getPricestg=false;
    }
    for(let x=0;x<this.itemOthersList1.length;x++)
    {
      if(this.itemOthersList1[x].addOnNewColFlag==1)
      {
        this.itemOthersList1[x].colId=count;
        count=count+1;
      }
    }
    if(this.getPricestg )
    {
    this._ITOeditQoutService.dboEleCiData=[];

    this.dboFormData.savedAddInstrList=[];
   
    for(let x=0;x<this.itemOthersList1.length;x++)
    {
      this.dboFormData.savedAddInstrList.push(this.itemOthersList1[x]);
    }
    // for(let jij=0;jij<this.dboEleInstrListNew.length;jij++)
    // {
    //   if(this.dboEleInstrListNew[jij].instrNm!="Additional Instruments")
    //   {
    //     this.dboClass = new dboClass();
    //     this.dboClass.itemId =this.dboEleInstrListNew[jij].itemId;
    //     this.dboClass.itemNm =null;
    //     this.dboClass.colId =null;
    //     this.dboClass.lhsColNm =null;
       
       
    //     this.dboClass.colValCd =null;
    //     this.dboClass.subColValCode =null;
    //     this.dboClass.quantity =this.dboEleInstrListNew[jij].quantityLogic;
    //     this.dboClass.cost =this.dboEleInstrListNew[jij].cost;
    //     this.dboClass.addOnNewColFlag =0;
    //     this.dboClass.otherItemInstrFlag=1;
      
         
       
    
       
         
    
    //     this.dboFormData.savedAddInstrList.push(this.dboClass);
    //   }
    // }
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails; 
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  ;
    this._DBOElectricalComponentService.saveAdditionalInstrumentation(this.dboFormData).subscribe(savedResp => {
      console.log(savedResp);
      this.dboCost = savedResp.saveAddInstrList[0].basicCost;
      this.dboAddOnCost= savedResp.saveAddInstrList[0].addOnCost;
       this.itemcost= savedResp.saveAddInstrList[0].totalAddInstrCost;
      if(this.dboEleCIFullArray.length!=0)
      {
        for(let j=0;j<this.dboEleCIFullArray.length;j++)
        {
          if(this.dboEleCIFullArray[j].itemName=="Set of STG Instruments List")
          {
            this.dboEleCIFullArray[j].dboCost= savedResp.saveAddInstrList[0].basicCost;
            this.dboEleCIFullArray[j].dboAddOnCost=savedResp.saveAddInstrList[0].addOnCost;
            this.dboEleCIFullArray[j].itemcost=savedResp.saveAddInstrList[0].totalAddInstrCost;

          }
        }
      }
      this.saveInLocal(this.dboEleCIFull, { 
        dboEleCIFullArray: this.dboEleCIFullArray, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
        itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
        selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
        itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
        subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
        eleItemTechRemarks: this.eleItemTechRemarks, eleItemComrRemarks: this.eleItemComrRemarks,
        cICost: this.cICost, totalDboCiCost: this.totalDboCiCost, itemOthersList1: this.itemOthersList1, lhsRhsItemsList: this.lhsRhsItemsList, itemOthersAddonList: this.itemOthersAddonList, itemOthersList2: this.itemOthersList2,subItemOthAddonList:this.subItemOthAddonList,dessubItemOthAddonList:this.dessubItemOthAddonList,lhsRhsSubItemsList:this.lhsRhsSubItemsList,lhsRhsDesSubItemsList:this.lhsRhsDesSubItemsList    
        });  
    });
  }
  }
  drpCheckQty(value, z, rhsValue,c){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    console.log(value);
    this.drpCheckqtyy[c]=value;
   this.drpCheckqtyyKey[z]=true;
  }
  //////CI
  openSubItmTypTbl(event,id){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    let counter=0;
    if(event.target.checked){
      this.dsplySubItmTypOthTable = true;
      this.displayOthnewLine2[id]=true;
      this.othersSubItmTypChk[id]=true;
      this.othersSubTypCheck[id]=true;
    }else if(!event.target.checked){
      this.dsplySubItmTypOthTable = false;
      this.subItmTypOthList = [];
      this.lhsRhsSubItmTypList=[];
      this.displayOthnewLine2[id]=false;
      this.othersSubItmTypChk[id]=false;
      this.othersSubTypCheck[id]=false;
      if(this.itemOthersList1.length!=0)
      {

      
      for(let j=0;j<this.itemOthersList1.length;j++)
      {
if(this.itemOthersList1[j].itemId==id && this.itemOthersList1[j].addOnNewColFlag==1)
{
 
  this.itemOthersList1[j]=null; 
   this.disablelhstg[j]=null;

  
}

      }
      this.itemOthersList1 = this.itemOthersList1.filter(n => n != null);
      this.disablelhstg = this.disablelhstg.filter(n => n != null);

    }
   
    }
  }
  openSubItmTypTbl1(event,id){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    let counter=0;
    if(event.target.checked){
      this.dsplySubItmTypOthTable = true;
      this.displayOthnewLine2[id]=true;
      this.othersSubItmTypChk[id]=true;
      this.othersSubTypCheck[id]=true;
    }else if(!event.target.checked){
      this.dsplySubItmTypOthTable = false;
      this.subItmTypOthList = [];
      this.lhsRhsSubItmTypList=[];
      this.displayOthnewLine2[id]=false;
      this.othersSubItmTypChk[id]=false;
      this.othersSubTypCheck[id]=false;
      if(this.itemOthersList2.length!=0)
      {

      
      for(let j=0;j<this.itemOthersList2.length;j++)
      {
if( this.itemOthersList2[j].addPrbFlag==id)
{
 
  this.itemOthersList2[j]=null;
this.disablelhsvms[j]=null;
}
      }
      this.itemOthersList2 = this.itemOthersList2.filter(n => n != null);
      this.disablelhsvms = this.disablelhsvms.filter(n => n != null);

    }
   
    }
    console.log(this.disablelhsvms);
  }


  //cancel the line from others subitemstype list
  openvms(event,id,pdb){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    if(event.target.checked){
    this.check[id]=true;
    this.check2[id]=true;
    }else if(!event.target.checked){
      this.check[id]=false;
    this.check2[id]=false;
    

      }
      console.log(this.check);
    }
  cncleLinSubItmTypOth(i){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
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
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
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
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.dsplySubItmTypOthnwLin = true;
  }
  
  //cancel row for subitemstype others table
  canclnwLinSubItmTyp(){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.dsplySubItmTypOthnwLin = false;
  }

  //Add others subitemType to list
  subItmTypOthForm(othSubItmTyp, othSubItmTypFrm: NgForm){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    let temp=false;
    console.log(othSubItmTyp);
    for(let j=0;j<this.subItmTypOthList.length;j++)
    {
      if(this.subItmTypOthList[j].subItemTypeName==othSubItmTyp.subItemTypeName)
      {
temp=true;
      }
    }
    if(temp==false)
    {

    
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = null;
      this.dboClass.itemName = null;        
      this.dboClass.subItemId = null;
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
    else
    {
      this.dsplySubItmTypOthnwLin = true;
    }
  }

  //cancel line others sub item type name Lhs/Rhs values from list
  cnclLinSubtypLhs(i){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsSubItmTypList.splice(i, 1);
  }

  //add LHS/RHS to others subitemtype list
  addSubTypLhsRhsForm(lhsRhsSubItmTyp, lhsRhsSubItmTypFrm: NgForm){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
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
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    for(let b=0;b<this.subItmTypOthList.length; b++){
      if (!this.lhsRhsSubItmTypList.some((item) => item.subItemTypeName == this.subItmTypOthList[b].subItemTypeName)) {
        this.lhsRhsSubItmTypList.push(this.subItmTypOthList[b]);
    }
    }console.log(this.lhsRhsSubItmTypList);
  }

  //add Rows to others sub item type LHS/RHS
  addRowSubTypLhsRhs(){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsSubTypnewLine = true;
  }
backButton(){
  this.router.navigate(['/EditQuot']);
}
  //cancel row from others table subItemType 
  cnclnwLineSubTypLhs(){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsSubTypnewLine = false;
  }

////////// desItemName123
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
   if(this.lhsRhsSubItemsList[k].desItemName == this.subItemOthAddonList[this.ibreakothers].desItemName && this.lhsRhsSubItemsList[k].colId == 0 && this.lhsRhsSubItemsList[k].colNm == null   && this.lhsRhsSubItemsList[k].itemId==this.itemId){
     this.lhsRhsSubItemsList.splice(k,1);
     this.ibreakothers = 1;
     break;          
   }
 }
 this.ibreakothers = 0;
  for(let c=0; c<this.subItemOthAddonList.length; c++){
    if(this.lhsRhsSubItemSel == this.subItemOthAddonList[c].desItemName    && this.subItemOthAddonList[c].itemId==this.itemId){
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
    if(this.subItemOthAddonList[i].desItemName == this.lhsRhsSubItemsList[j].desItemName && this.subItemOthAddonList[i].itemId == this.lhsRhsSubItemsList[j].itemId)
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
cancelnewLineSubLhs( othersfrm: NgForm){
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
        if(this.itemId==this.subItemOthAddonList[x].itemId)
        {
          this.subItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsSubItemsList.length;x++)
      {
        if(this.itemId==this.lhsRhsSubItemsList[x].itemId)
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
 
//////   


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
cancelnewLineDesSubItem( othersfrm: NgForm) {
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
   if(this.lhsRhsDesSubItemsList[k].desSubItemName == this.dessubItemOthAddonList[this.ibreakothers].desSubItemName && this.lhsRhsDesSubItemsList[k].colId == 0 && this.lhsRhsDesSubItemsList[k].colNm == null  && this.lhsRhsDesSubItemsList[k].itemId==this.itemId){
     this.lhsRhsDesSubItemsList.splice(k,1);
     this.ibreakothers = 1;
     break;          
   }
 }
 this.ibreakothers = 0;
  for(let c=0; c<this.dessubItemOthAddonList.length; c++){
    if(this.lhsRhsSubItemSel == this.dessubItemOthAddonList[c].desSubItemName  && this.dessubItemOthAddonList[c].itemId==this.itemId){
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
    if(this.dessubItemOthAddonList[i].desItemName == this.lhsRhsDesSubItemsList[j].desItemName && this.lhsRhsDesSubItemsList[j].itemId==this.itemId )
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
        if(this.itemId==this.dessubItemOthAddonList[x].itemId)
        {
          this.dessubItemOthAddonList[x]=null;  
        }
      }
      for(let x=0;x<this.lhsRhsDesSubItemsList.length;x++)
      {
        if(this.itemId==this.lhsRhsDesSubItemsList[x].itemId)
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

/**
   * to get previous comments
   */
  lengthcheck(value,key)
  {
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    var numberValue = Number(value);
    if(numberValue<1 || isNaN(numberValue) )
    {
      this.newAddNameO[key]="";

    }
  }
  clearerror()
  {
    this.Ifapplicable=[];

  }
  getPrevComments() {
    this.message = false;
    this.successMsg = '';
    console.log(this._ITOcustomerRequirementService.saveBasicDet);
    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "CI";
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


  /// other lhs edit
  editlhs(i,type)
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

  savelhsdessub(i,value,name,item,type)
  {
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    if(name=='lhs')
    {
      this.checkForQuantity1223(i,value,name,item,type);
      this.lhsdes[i]=false;


    }
    else
    {
      this.rhsdes[i]=false;

    }

  }


  //////
/// other lhsvms edit
editlhsvms(i)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.disablelhsvms[i]=true;

  
}
savelhsvms(i)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.disablelhsvms[i]=false;

  
}

/// other lhsvms edit
editlhsstg(i)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.disablelhstg[i]=true;

  
}
savelhsstg(i,value,cost)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
console.log("hello");
    this.disablelhstg[i]=false;

    if(value=="rhs")
    {
      let temp=this.itemOthersList1[i].quantity;
      let temp2=cost;
      this.itemOthersList1[i].cost=temp*temp2;

    }
    


  
}

/// other lhsvms edit
editlhsstgrhs(i)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.disablerhstg[i]=true;

  
}
savelhsstgrhs(i)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.disablerhstg[i]=false;

  
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
optionSelAltMake(value,rhs)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(this.itemCode=="VMS")
    {
       this.displayvms=true;
      this.itemOthersList2=[];
      this.check=[];
      this.check2=[];
      this.displayOthnewLine2=[];
      this.othersSubTypCheck=[];
      this.othersSubItmTypChk=[];
    }
  this.makevms='';
  this.typevms='';
  this.gearvms='';
  this.makearray=[];
  this.typearray=[];
  this.geararray=[];
console.log(value);
this.geararray = this.totalVmsList.filter((x) => {
  return ((x.altMake==value));
})
let temp3=[];
for(let j=0;j<this.geararray.length;j++)
{
  if(this.geararray[j].gearBoxDefaultFlag==1)
  {
    this.gearvms=this.geararray[j].gearbox;
    temp3.push(this.geararray[j].gearbox);

    break;
  }
 
}
this.geararray = this.geararray.reduce((acc, current) => {
  console.log(acc, current);
  const x = acc.find(item => item.gearbox ===  current.gearbox);
  if (!x) {       
      return acc.concat([current]);        
    
  } else {
    return acc;
  } 
}, []);


for(let j=0;j<this.geararray.length;j++)
{
  if(this.geararray[j].gearbox!=this.gearvms)
  {
    temp3.push(this.geararray[j].gearbox);

  }
}
this.geararray=temp3;
this.typearray = this.totalVmsList.filter((x) => {
  return ((x.altMake==value) && (x.gearbox== this.gearvms));
})
let temp2=[];

for(let j=0;j<this.typearray.length;j++)
{
  if(this.typearray[j].typeDefaultFlag==1)
  {
    this.typevms=this.typearray[j].type;
    temp2.push(this.typearray[j].type);

    break;
  }
}
this.typearray = this.typearray.reduce((acc, current) => {
  console.log(acc, current);
  const x = acc.find(item => item.type ===  current.type);
  if (!x) {       
      return acc.concat([current]);        
    
  } else {
    return acc;
  } 
}, []);

for(let j=0;j<this.typearray.length;j++)
{
  if(this.typearray[j].type!=this.typevms)
  {
    temp2.push(this.typearray[j].type);

  }
}
this.typearray=temp2;
this.makearray = this.totalVmsList.filter((x) => {
  return ((x.altMake==value) && (x.gearbox== this.gearvms) && (x.type==this.typevms)   );
})
let temp=[];

for(let j=0;j<this.makearray.length;j++)
{

  if(this.makearray[j].makeDefaultFlag==1)
  {
    this.makevms=this.makearray[j].make;
    temp.push(this.makearray[j].make);

    break;
  }
}

for(let j=0;j<this.makearray.length;j++)
{
  if(this.makearray[j].make!=this.makevms)
  {
    temp.push(this.makearray[j].make);

  }
}
this.makearray=temp;
}
optionSelGear(value)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(this.itemCode=="VMS")
    {
       this.displayvms=true;
      this.itemOthersList2=[];
      this.check=[];
      this.check2=[];
      this.displayOthnewLine2=[];
      this.othersSubTypCheck=[];
      this.othersSubItmTypChk=[];
    }
if(this.panelType!="DT")
{
  this.makevms='';
  this.typevms='';
  this.typearray = this.totalVmsList.filter((x) => {
    return ((x.altMake==this.AltMakeVms) && (x.gearbox==value));
  })
  let temp2=[];

  for(let j=0;j<this.typearray.length;j++)
  {
    if(this.typearray[j].typeDefaultFlag==1)
    {
      this.typevms=this.typearray[j].type;
      temp2.push(this.typearray[j].type);


      break;
    }
  }
  this.typearray = this.typearray.reduce((acc, current) => {
    console.log(acc, current);
    const x = acc.find(item => item.type ===  current.type);
    if (!x) {       
        return acc.concat([current]);        
      
    } else {
      return acc;
    } 
  }, []);

for(let j=0;j<this.typearray.length;j++)
{
  if(this.typearray[j].type!=this.typevms)
  {
    temp2.push(this.typearray[j].type);

  }
}
this.typearray=temp2;

  this.makearray = this.totalVmsList.filter((x) => {
    return ((x.altMake==this.AltMakeVms) && (x.gearbox==value) && (x.type==this.typevms)   );
  })
  let temp=[];
  for(let j=0;j<this.makearray.length;j++)
  {

    if(this.makearray[j].makeDefaultFlag==1)
    {
      this.makevms=this.makearray[j].make;
      temp.push(this.makearray[j].make);

      break;
    }
  }
  

for(let j=0;j<this.makearray.length;j++)
{
  if(this.makearray[j].make!=this.makevms)
  {
    temp.push(this.makearray[j].make);

  }
}
this.makearray=temp;
}
}
optionSelType(value)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(this.itemCode=="VMS")
    {
       this.displayvms=true;
      this.itemOthersList2=[];
      this.check=[];
      this.check2=[];
      this.displayOthnewLine2=[];
      this.othersSubTypCheck=[];
      this.othersSubItmTypChk=[];
    }
  this.makevms='';
 if(this.panelType!="DT")
 {
  this.makearray = this.totalVmsList.filter((x) => {
    return ((x.altMake==this.AltMakeVms) && (x.gearbox==this.gearvms) && (x.type==value)   );
  })
  let temp=[];

  for(let j=0;j<this.makearray.length;j++)
  {

    if(this.makearray[j].makeDefaultFlag==1)
    {
      this.makevms=this.makearray[j].make;
      temp.push(this.makearray[j].make);

      break;
    }
  }

for(let j=0;j<this.makearray.length;j++)
{
  if(this.makearray[j].make!=this.makevms)
  {
    temp.push(this.makearray[j].make);
    

  }
}
this.makearray=temp;
  

 }
 else
 {
  this.makearray = this.totalVmsList.filter((x) => {
    return ( (x.gearbox==this.gearvms) && (x.type==value)   );
  })
  let temp=[];

  for(let j=0;j<this.makearray.length;j++)
  {

    if(this.makearray[j].makeDefaultFlag==1)
    {
      this.makevms=this.makearray[j].make; 
       temp.push(this.makearray[j].make);

      break;
    }
  }

for(let j=0;j<this.makearray.length;j++)
{
  if(this.makearray[j].make!=this.makevms)
  {
    temp.push(this.makearray[j].make);

  }
}
this.makearray=temp;
  
 }
  
  
}
optionSelMake(value)
{
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  if(this.itemCode=="VMS")
    {
       this.displayvms=true;
      this.itemOthersList2=[];
      this.check=[];
      this.check2=[];
      this.displayOthnewLine2=[];
      this.othersSubTypCheck=[];
      this.othersSubItmTypChk=[];
    }
 console.log(value); 
}
showDialogMaximized(event, dialog: Dialog){
  dialog.maximized = false;
  dialog.toggleMaximize(event);
}

  }
