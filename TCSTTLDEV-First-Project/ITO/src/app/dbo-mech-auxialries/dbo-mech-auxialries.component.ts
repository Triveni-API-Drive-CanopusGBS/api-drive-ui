import { Component, OnInit, AfterViewChecked, AfterContentChecked } from '@angular/core';
import { DboMechAuxialriesService } from './dbo-mech-auxialries.service';
import { dboClass } from './dbo-mech-auxialries';
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
import { IfObservable } from 'rxjs/observable/IfObservable';
import { ThrowStmt, unescapeIdentifier } from '@angular/compiler';
import { Dialog } from 'primeng/dialog';

@Component({
  selector: 'app-dbo-mech-auxialries',
  templateUrl: './dbo-mech-auxialries.component.html',
  styleUrls: ['./dbo-mech-auxialries.component.css']
})
export class DboMechAuxialriesComponent implements OnInit {
  alert:string="                     !!!!!ALERT!!!!";
  dispSCFM:Boolean = false;
  moveItem: boolean = false;
  dboMechAuxLoc: string = 'dboAuxMech'; // local storage value
  openbtn: Array<boolean> = [];
  openbtnmain:Array<boolean>=[];
  openbtn2: Array<any> = [];
  openbtn1: boolean = false;
  othItmTechRemChk: boolean = false;
othItmComrRemChk: boolean = false;
  hideprogressCost1: boolean = false;
  itemtechRemarkCheck: boolean = false;
itemComrRemarkCheck: boolean = false;
  remarks:string;
  message: boolean = false; //To display message
  dboFormData: any; //Storing respone from getDboFormData
  mechItemAux: any; //Storing respone from getMechItems
  itemSelectedListEdit:Array<any> = []; //the list of selected items from edit
  mechItemListAux: Array<any> = [];
  newArray: Array<any> = [];
  selectdEl: Array<boolean> = [];
  temparray: Array<any> = [];
  successMsg: string = '';
  questionsBean:Array<any> = []; 
  questionsBean1:Array<any> = [];
  displayF2f: boolean = false; 
  addOnList17:Array<any> = [];
  costNotAvailableError: any;
  errorArray: Array<any> =[];
  addoncheck: Array<any> =[];
  counter_check:number=0;
  defaultValues: Array<any> = [];
  defaultValues2: Array<any> = [];
  defaultValues1: Array<any> = [];
  SelectedExcelData: Array<any> = [];
  dboMechFullArraytest1: Array<any> = [];
  dboMechFullArraytest3: Array<any> = [];
  tempdbo:Array<any> = [];
  dboMechFullArraytestpanel2: Array<any> = []; //store complete data from getMechTechPrice: Array<any> = []; //store complete data from getMechTechPrice
  dboClass: dboClass;
  dboCost: number; //basiccost from getMechTechPrice to display in UI Pannel cost
  dboCost1: number; //basiccost from getMechTechPrice to display in UI Pannel cost
  openOth:Array<boolean> = []; //To add Rhs for existing lhs
  openOth1:Array<boolean> = []; 
  dboMechPanelList2: Array<any> = [];
  dboMechPanelList: Array<any> = [];
  flowRate: any;
  itemId: any; //store itemId on selection of item
  mechItemId: any; //store itemId on selection of item
  itemidnew: any; //store itemId on selection of item
  subItemId: any; //store itemId on selection of item
  subItemId1: any; //store itemId on selection of item
  qty: number = 1; //Quantity for panel
  itemRemarkDiv: boolean = false; //boolean to display input for techinal remaks inside the item panel
  itemComrRemarkDiv: boolean = false; //boolean to display input for commercial remarks inside the panel
  itemTechRmkDiv: boolean = false;
  itemComrRemarkDivAux:boolean=false;
  newAddonbu:Array<boolean> = [];
  oneLineLocArray: any;
  openOth2:Array<boolean> = [];
  openOth3:Array<boolean> = [];
  addonboolean: boolean[][];
  openOth4:Array<boolean> = [];
  addOnList5:Array<any> = []; //store addon list
  addOnList14:Array<any> = []; //store addon list
  addOnList15:Array<any> = []; //store addon list
  addOnList6:Array<any> = []; //store addon list
  disableStatus:boolean=false;
  diableitemname:boolean=true;

  //openOth4:Array [][];
  itemCmrRemarksVal: string; //Techinal remarks for inside the item panel
  itemRemarksVal:string;//Cmmercial remarks for inside the item panel
  mechItemTechRemarks:string
  itemCmrRemarksValAux:string;
  respSaveItem: any; //store respone from saveMechItem
  counter:number=0;
  counter2:number=0;
  counter3:number;
  itemIdListN: Array<number> = [];
  itemIdList: Array<number> = [];
  //enableAdd :Array<boolean> = [];
  displayItmOthnewLine:boolean = false;
  displayItemCompOthTable:boolean = false;
  itemOthersAddonList:Array<any> = [];
  itemOthersAddonListNew:Array<any> = [];
  lhsRhsItemsList:Array<any> = [];
  ibreakothers: number = 0;
  lhsRhsItemSel:string = '';
  lhsRhsnewLine:boolean = false;
  displayDialogLhsRhs:boolean = false;
  newAddNameO:Array<any>=[];
  newAddNameO1:Array<any>=[];
newAddQtyO:Array<any>=[];
newAddCostO:Array<any>=[];
newAddRemrkO:Array<any>=[];
newAddComRemrkO:Array<any>=[];
newAddNameO2:Array<any>=[];
newAddQtyO2:Array<any>=[];
newAddCostO2:Array<any>=[];
newAddRemrkO2:Array<any>=[];
OverWrittenfinalMechCost: number = 0; //overwritten cost
newAddComRemrkO2:Array<any>=[];
newAddQtyO1:Array<any>=[];
newAddCostO1:Array<any>=[];
newAddRemrkO1:Array<any>=[];
newAddComRemrkO1:Array<any>=[];
itemName: string; //to dispaly item name for dialog header
addOnCheck:Array<boolean> = []; //To add Rhs for existing lhs
colValNmId: number; //store col Id
subBol:Array<boolean> = []; //To add Rhs for existing lhs
addOnList: Array<any> = []; //store addon list
addOnList1: Array<any> = []; //store addon list
addontemp: Array<any> = []; //store addon list
addOnList2: Array<any> = []; //store addon list
addOnList3: Array<any> = []; //store addon list
addOnList4: Array<any> = []; //store addon list
addOnList13: Array<any> = []; //store addon list
addOnList16: Array<any> = []; //store addon list
newDefaultValues: Array<any> = []; //exel default values
newd: Array<any> = []; //exel default values
dispNewComp: boolean = false;
newQty: number = 1;// by default setting quantity to 1
newdboCost: number = 0; // new dbo add-on cost
newdboAddOnCost: number = 0; // new dbo add-on cost
newRemarksAdd: string = ''; // remarks for additional cost
newNoLtrs: number;
enableAdd: boolean = true;
dboMechFullArray: Array<any> = []; //store complete data from getMechTechPrice
dboMechFullArraytest: Array<any> = []; //store complete data from getMechTechPrice
dboMechFullArraytest2: Array<any> = []; //store complete data from getMechTechPrice
addarray: Array<any> = []; //store complete data from getMechTechPrice
dboMechFullArray1: Array<any> = []; //store complete data from getMechTechPrice
dboMechFullArraytestpanel: Array<any> = []; //store complete data from getMechTechPrice: Array<any> = []; //store complete data from getMechTechPrice
dboAddOnCost:any; //store mech addon cost from getMechTecPrice
dboAddOnCost1:any; //store mech addon cost from getMechTecPrice
remarksAdd: string = ''; // remarks for additional cost
dispFirstPanel: boolean = true;
hideprogress: boolean = false;
mechid1:number;
counter1:number= 0;
oneLineLoc: string = 'oneLineLoc';
booleancheck: boolean = false;
panelList: any; //Storing response from getMechPanel
checksub:number;
finalMechCost: number = 0;
multfactor:number;
subid:number;
colid:number;
arraycheck:Array<any> = []; //To add Rhs for existing lhs
dboMechanicalAuxLocal: Array<any> = [];
checkbol:boolean;
scopeofsupp: string = 'scopeOfsup';
selectedELIndex:number;
addonlist7: Array<any> = []; //store complete data from getMechTechPrice
enableOverwriteDiv: boolean = false; //To enable overwritten cost
addonlist12:Array<any> = []; //To add Rhs for existing lhs
addonlist13:Array<any> = []; //To add Rhs for existing lhs
arraycheck1:Array<any> = []; //To add Rhs for existing lhs
lhsdisable:Array<any> = []; //To add Rhs for existing lhs
lhsdisableparent:Array<boolean> = []; //To add Rhs for existing lhs
newSet: Array<any> = [];
newSet3: Array<any> = [];
newSet4: Array<any> = [];
newSet2: Array<any> = [];
prevData: Array<any> = [];
addondata: Array<any> = [];
addOnList123:Array<any> = []; //To add Rhs for existing lhs
othersCheck:boolean =false; 
othersItemCompCheck:boolean =false; 
finalcostflag:boolean=false;
addonflg:string="0";
oilinput:number;
AddOnFlag:Array<boolean> = [];
enableAddOn:Array<boolean> = [];
uniqueCode:string;
AddOnFlagNew:Array<any>=[];
AddOnFlagNew2:Array<boolean>=[];
errDisplayPnl:Array<any>=[];
errMsgRhsCost:Array<any>=[];
errMsgRhsCost1:Array<any>=[];
errDisplayPnl1:Array<boolean>=[];
itemcost:number=0;
itemcost1:number=0;
backBtn: boolean =  false;
finalItem: string = '';
saveBtColor:boolean=true;
saveBtColorArray:Array<boolean>=[];

buttonColor:string="rgb(213,120,23)";
buttonEnable:boolean=false;
finalCostBool:boolean = false;
enableGetPriceBtn:boolean=false;
getPriceNew:boolean=true;
getPriceOld:Array<boolean>=[];
user: string = 'userDetail';
currentRole: string = 'selRole';
currentRoleId: string = 'selRoleId';
rewApp: boolean = false;
discountPer: number = 0;
newDiscountPer: number = 0;
mainSave:boolean=true;
mainSave2:boolean=true;
oilBoolean:boolean=false;
defaultValuesTemp:Array<any>=[];


  constructor(private _DboMechAuxialriesService: DboMechAuxialriesService, 
    private _ITOturbineConfigService: ITOturbineConfigService, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    private _ITOLoginService: ITOLoginService, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private _ITOeditQoutService: ITOeditQoutService,
    private _ITOCostEstimationService: ITOCostEstimationService, private router: Router) {
  //     this.arraycheck1[1]=true;
  //     this.arraycheck1[2]=false;
  //     this.arraycheck1[3]=true;
  //     this.arraycheck1[5]=true;

  // this.arraycheck[2]=this.arraycheck1;
  // // console.log(this.arraycheck);
  //  for(let j=0;j<30;j++)
  //  {
  //     for(let i=0;i<30;i++)
  //     {
  //       this.arraycheck[j][i]=false;
  //     }
  //  }
  this._ITOeditQoutService.button1=false;
  this._ITOeditQoutService.button2=false;
  this._ITOeditQoutService.button3=false;
  this._ITOeditQoutService.button4=false;
  this._ITOeditQoutService.button5=true;
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

  if(this._ITOeditQoutService.checkEdit == false){
    this.backBtn = true;
  }
 
 
  console.log(this._ITOeditQoutService.dboMechAuxData);
 this.temparray=[];
 if( this._ITOcustomerRequirementService.editFlag==true && this._ITOeditQoutService.dboMechAuxData!=undefined)
 {
 
  this.temparray=this._ITOeditQoutService.dboMechAuxData;
  
  }

 // this.temparray=this._ITOeditQoutService.dboMechAuxData;
  this.dboMechFullArray=[];
  this.dboMechFullArray1=[];
 this. dboMechFullArraytestpanel=[];
 this.addOnList=[];
 console.log(this._ITOcustomerRequirementService.editFlag);
 this.addarray=[];
 console.log( this.storage.get(this.dboMechAuxLoc));
 this.dboMechanicalAuxLocal[this.dboMechAuxLoc] = this.storage.get(this.dboMechAuxLoc);
 if(this.storage.get(this.dboMechAuxLoc)!=null&& this.storage.get(this.dboMechAuxLoc).dboMechFullArray.length==0 && this.storage.get(this.dboMechAuxLoc).itemIdList.length!=0 && this._ITOcustomerRequirementService.editFlag==true){
   if(this.storage.get(this.dboMechAuxLoc).itemIdList.length != 0){
  this.itemIdListN = this.storage.get(this.dboMechAuxLoc).itemIdList;
}
if(this.storage.get(this.dboMechAuxLoc).finalMechCost > 0){
  this.finalCostBool=true;
this.finalMechCost = this.storage.get(this.dboMechAuxLoc).finalMechCost;
}
 }
 if(this.storage.get(this.dboMechAuxLoc)!=null&& this.storage.get(this.dboMechAuxLoc).dboMechFullArray1.length!=0 && this._ITOeditQoutService.dboMechAuxData.length==0 && this._ITOcustomerRequirementService.editFlag==true)
 {
  this.mainSave2=false;
  this.mainSave=false;
     //to display new item creation other from local storage
     if(this.storage.get(this.dboMechAuxLoc).itemOthersAddonList.length != 0){
      this.itemOthersAddonList = this.storage.get(this.dboMechAuxLoc).itemOthersAddonList;
      this.othersCheck=true;
      this.othersItemCompCheck=true;
      this.displayItemCompOthTable=true;
    }
    // to dispaly new item creation other with lhs/rhs from local storage
    if(this.storage.get(this.dboMechAuxLoc).lhsRhsItemsList.length!=0){

      this.lhsRhsItemsList = this.storage.get(this.dboMechAuxLoc).lhsRhsItemsList;
      this.othersCheck=true;
      this.othersItemCompCheck=true;
      this.displayItemCompOthTable=true;
    }
  if(this.storage.get(this.dboMechAuxLoc).itemCmrRemarksVal != null && this.storage.get(this.dboMechAuxLoc).itemCmrRemarksVal != ""){
    this.itemCmrRemarksVal = this.storage.get(this.dboMechAuxLoc).itemCmrRemarksVal;
    this.othItmComrRemChk = true;
    this.itemComrRemarkDiv = true;
    this.othItmComrRemChk=true;
    this.itemComrRemarkCheck=true;
 
  }
  if(this.storage.get(this.dboMechAuxLoc).itemRemarksVal != null && this.storage.get(this.dboMechAuxLoc).itemRemarksVal != ""){
    this.itemRemarksVal = this.storage.get(this.dboMechAuxLoc).itemRemarksVal;
    this.itemRemarkDiv = true;
    this.othItmTechRemChk = true;
    this.othItmTechRemChk=true;
    this.itemtechRemarkCheck=true;
  }
 if(this.storage.get(this.dboMechAuxLoc).dboMechFullArray.length!=0){
       this.dboMechFullArray = this.storage.get(this.dboMechAuxLoc).dboMechFullArray;
       this.finalCostBool = true;
    }
     if(this.storage.get(this.dboMechAuxLoc).dboMechFullArray1.length!=0){
      this.dboMechFullArray1 = this.storage.get(this.dboMechAuxLoc).dboMechFullArray1;
      this.finalCostBool = true;
    }
    if(this.storage.get(this.dboMechAuxLoc).itemIdList.length != 0){
      this.itemIdListN = this.storage.get(this.dboMechAuxLoc).itemIdList;
    }
    if(this.storage.get(this.dboMechAuxLoc).addOnList!=null){
      this.addOnList = this.storage.get(this.dboMechAuxLoc).addOnList;
    
   }
   if(this.storage.get(this.dboMechAuxLoc).itemOthersAddonList!=null){
    this.addOnList = this.storage.get(this.dboMechAuxLoc).addOnList;
  
 }

    
     if(this.storage.get(this.dboMechAuxLoc).finalMechCost > 0){
       this.finalCostBool=true;
   this.finalMechCost = this.storage.get(this.dboMechAuxLoc).finalMechCost;
     }
   

   
  //  this.addOnList =  this.addOnList123.filter((x) => {
  //   return ((x.quotId == this._ITOcustomerRequirementService.saveBasicDet.quotId));
  //   })
         
         
  //       this.dboMechFullArray =  this.dboMechFullArraytest1.filter((x) => {
  //        return ((x.quotId ==this._ITOcustomerRequirementService.saveBasicDet.quotId));
  //      })
  //      this.dboMechFullArray1 =  this.dboMechFullArraytest3.filter((x) => {
  //        return ((x.quotId == this._ITOcustomerRequirementService.saveBasicDet.quotId));
  //        })
         
  }
 
  if(_ITOcustomerRequirementService.editFlag==true && this._ITOeditQoutService.dboMechAuxData.length!=0)
  {
    this.mainSave=false;
    this.mainSave2=false;
    this.oneLineLocArray = this.storage.get(this.oneLineLoc);
 
   if(this.oneLineLocArray != null){
     this.finalMechCost = this.oneLineLocArray.dboMechAuxCost;  
   }
    // this.itemOthersAddonList = this._ITOeditQoutService.dboMechAuxDataAddonData.reduce((acc, current) => {
    //   console.log(acc, current);
    //   const x = acc.find(item => item.itemName === current.itemName);
    //   if (!x) {
    //     return acc.concat([current]);
    //   } else {
    //     return acc;
    //   } 
    // }, []);
    if( this._ITOeditQoutService.dboMechAuxDataAddonData.length != 0){
    this.itemOthersAddonListNew = this._ITOeditQoutService.dboMechAuxDataAddonData;
 
    for(let c=0;c<this.itemOthersAddonListNew.length;c++)
    {
      this.dboClass = new dboClass();
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = 0;
          this.dboClass.itemName = this.itemOthersAddonListNew[c].itemName;
          this.dboClass.subItemId = this.itemOthersAddonListNew[c].subItemId;
          this.dboClass.subItemName = this.itemOthersAddonListNew[c].subItemName;
          this.dboClass.colId = 0;
          this.dboClass.colNm = this.itemOthersAddonListNew[c].colNm;
          this.dboClass.colValCd = this.itemOthersAddonListNew[c].colValCd;
          this.dboClass.quantity = this.itemOthersAddonListNew[c].quantity;
          this.dboClass.cost = this.itemOthersAddonListNew[c].basicCost;
          this.dboClass.techRemarks = this.itemOthersAddonListNew[c].techRemarks;
          this.dboClass.comrRemarks = this.itemOthersAddonListNew[c].comrRemarks;
          this.dboClass.addOnFlg = 1;
          this.dboClass.techFlag = 1;    
          this.dboClass.comrFlag = 1;
    
   
    this.itemOthersAddonList.push(this.dboClass);
    this.lhsRhsItemsList.push(this.dboClass);//satya
    }
     this.itemOthersAddonList = this.itemOthersAddonList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.itemName === current.itemName);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
   }
    if(this.itemOthersAddonList.length!=0)
    {
      this.displayItemCompOthTable=true;
      this.othersItemCompCheck=true;
    }
  }
 
    if(this.temparray.length!=0)
  {
    this.finalCostBool = true;
    this.othersCheck=false;
  //  this.othersItemCompCheck=false;
 //   this.displayItemCompOthTable=false;
 
  this.itemCmrRemarksVal = "";
  this.othItmComrRemChk = false;
  this.itemComrRemarkDiv = false;
  this.othItmComrRemChk=false;
  this.itemComrRemarkCheck=false;


  this.itemRemarksVal = "";
  this.itemRemarkDiv = false;
  this.othItmTechRemChk = false;
  this.itemtechRemarkCheck=false;
    this.addOnList=[];
    this.dboMechFullArray=[];
    this.dboMechFullArray1=[];
  for(let j=0;j<this.temparray.length;j++)
  {
    if(this.temparray[j].subItemId==1 && this.temparray[j].addOnNewColFlag==0)
    {
    this.dboMechFullArray1.push(this.temparray[j]);
    }
    if(this.temparray[j].subItemId==1 && this.temparray[j].addOnNewColFlag==1)
    {
    this.dboMechFullArraytestpanel.push(this.temparray[j]);
    }

    if(this.temparray[j].subItemId>1 && this.temparray[j].addOnNewColFlag==0)
    {
      this.dboMechFullArray.push(this.temparray[j]);
    }
    if(this.temparray[j].subItemId>1 && this.temparray[j].addOnNewColFlag==1)
    {
      this.addarray.push(this.temparray[j]);
    }
  }
  

  if(this.dboMechFullArray1[0].comrComments!=null && this.dboMechFullArray1[0].comrComments!="")
  {
  this.itemCmrRemarksVal = this.dboMechFullArray1[0].comrComments;
  this.othItmComrRemChk = true;
  this.itemComrRemarkDiv = true;
  this.othItmComrRemChk=true;
  this.itemComrRemarkCheck=true;
  }
  if(this.dboMechFullArray1[0].techComments!=null && this.dboMechFullArray1[0].techComments!="" )
  {
  this.itemRemarksVal = this.dboMechFullArray1[0].techComments;
  this.itemRemarkDiv = true;
  this.othItmTechRemChk = true;
  this.itemtechRemarkCheck=true;
  }
  this.addOnList=  this.dboMechFullArraytestpanel;
  this.newSet = Array.from(new Set( this.dboMechFullArray.map((x) => {
    return x.itemName;
  })));
  this.newSet2 = Array.from(new Set( this.dboMechFullArray1.map((x) => {
    return x.itemName;
  })));
}



console.log(this.newSet);
let newArr: Array<any> = [];
      console.log(this.dboMechFullArray)
      for (let h = 0; h < this.newSet.length; h++) {
        for (let i = 0; i < this.dboMechFullArray.length; i++) {
          if (this.newSet[h] == this.dboMechFullArray[i].itemName) {
            newArr.push(this.dboMechFullArray[i]);
            break;
          }
        }
      }
      let newArr2: Array<any> = [];
      console.log(this.dboMechFullArray)
      for (let h = 0; h < this.newSet2.length; h++) {
        for (let i = 0; i < this.dboMechFullArray1.length; i++) {
          if (this.newSet2[h] == this.dboMechFullArray1[i].itemName) {
            newArr2.push(this.dboMechFullArray1[i]);
            break;
          }
        }
      }
      if (newArr.length!=0) {
        this.itemRemarksVal=newArr[0].techComments;
        this.itemCmrRemarksVal=newArr[0].comrComments;
       if(this.itemRemarksVal!=null)
       {
        this.othItmTechRemChk=true;
        this.itemRemarkDiv=true;
        this.itemtechRemarkCheck=true;
       
        
       }
       if(this.itemCmrRemarksVal!=null)
       {
       this. othItmComrRemChk=true;
        this.itemComrRemarkDiv=true;
        this. itemComrRemarkCheck=true;
       }

        
        
      }
      console.log(newArr);
      if (newArr.length>0 && this.temparray!=null) {
        for (let m = 0; m < newArr.length; m++) {
          this.defaultValues = [];
          this.addondata=[];
          this.prevData=[];
          this.itemcost=0;
          if ( this.dboMechFullArray.length != 0) {
            this.prevData =  this.dboMechFullArray.filter((x) => {
              return ((x.itemName == newArr[m].itemName) && (x.subItemId == newArr[m].subItemId));
            })
            console.log(this.prevData);
            if (this.prevData.length != 0) {
              for (let d = 0; d < this.prevData.length; d++) {
                this.defaultValues.push(this.prevData[d].colValCd);
              }
            }
          }
          this.itemcost=this.prevData[0].itemCost;
          if ( this.addarray.length != 0) {
            this.addondata =  this.addarray.filter((x) => {
              return ((x.itemName == newArr[m].itemName) && (x.subItemId == newArr[m].subItemId) && (x.addOnNewColFlag==1));
            })
          }
          // //pushing selected components data fromm storage to local variable
           this.dboMechFullArraytest.push({
             quotId:newArr[m].quotId,
            qty:newArr[m].quantity,
            discountPer: newArr[m].discountPer,
            id:newArr[m].mechItemId,
            componenet: newArr[m].itemName,
            defaultValues: this.defaultValues,
            dboCost: newArr[m].basicCost,
            dboAddOnCost: newArr[m].addOnCost,
            techComments: newArr[m].techComments,
            comrComments:newArr[m].comrComments,
            techRemarks: newArr[m].techRemarks,
            comrRemarks :newArr[m].rhsColComrcomments,
            mechItemTechRemarks: newArr[m].mechItemTechRemarks,
            mechItemComrRemarks: newArr[m].mechItemComrRemarks,
            addOnList:   this.addondata,
            itemOthersList: this.itemOthersAddonList,
            itemId: newArr[m].itemId,
            itemName: newArr[m].itemName,
            subItemId:newArr[m].subItemId,
            subItemName: null,
            itemcost:  this.itemcost
          });
          //this.addedClassList.push(newArr[m].itemName);
        //  console.log(this.dboMechFullArray, this.cmnPwr);
          let savedMech: Array<any> = [];
        }
      }
      if (newArr2.length!=0 && this.temparray!=null) {
        for (let m = 0; m < newArr2.length; m++) {
          this.itemcost=0;
          this.defaultValues = [];
          this.dboMechFullArraytestpanel2=[];
          this.prevData=[];
          if ( this.dboMechFullArray1.length != 0) {
            this.prevData =  this.dboMechFullArray1.filter((x) => {
              return ((x.itemName == newArr2[m].itemName) && (x.subItemId ==1));
            })
            console.log(this.prevData);
            if (this.prevData.length != 0) {
              for (let d = 0; d < this.prevData.length; d++) {
                this.defaultValues.push(this.prevData[d].colValCd);
              }
            }
          }
          this.itemcost=this.prevData[0].itemCost;
          if ( this.dboMechFullArraytestpanel.length != 0) {
            this.dboMechFullArraytestpanel2 =  this.dboMechFullArraytestpanel.filter((x) => {
              return ((x.itemName == newArr2[m].itemName) && (x.subItemId == newArr2[m].subItemId) && (x.addOnNewColFlag==1));
            })
          }
          // //pushing selected components data fromm storage to local variable
           this.dboMechFullArraytest2.push({
            quotId:newArr2[m].quotId,
            qty:newArr2[m].quantity,
            discountPer: newArr2[m].discountPer,
            id:newArr2[m].mechItemId,
            componenet: newArr2[m].itemName,
            defaultValues: this.defaultValues,
            dboCost: newArr2[m].basicCost,
            dboAddOnCost: newArr2[m].addOnCost,
            techComments: newArr2[m].techComments,
            comrComments:newArr2[m].comrComments,
            techRemarks: newArr2[m].techRemarks,
            comrRemarks :newArr2[m].rhsColComrcomments,
            mechItemTechRemarks: newArr2[m].mechItemTechRemarks,
            mechItemComrRemarks: newArr2[m].mechItemComrRemarks,
            addOnList:  this.dboMechFullArraytestpanel2,
            itemOthersList: this.itemOthersAddonList,
            itemId: newArr2[m].itemId,
            itemName: newArr2[m].itemName,
            subItemId:1,
            subItemName: null,
            itemcost:this.itemcost
          });
          //this.addedClassList.push(newArr[m].itemName);
        //  console.log(this.dboMechFullArray, this.cmnPwr);
          let savedMech: Array<any> = [];
          
        }
      }
      
      //  this.dboMechFullArray=[];
       // this.dboMechFullArray1=[];
       if(this.temparray.length!=0)
       {
        this.dboMechFullArray= this.dboMechFullArraytest;
        this.dboMechFullArray1= this.dboMechFullArraytest2
       }
    
    
    //   if(_ITOeditQoutService.dboMechAuxData==undefined)
    //   {
    //   if(this.storage.get(this.dboMechAuxLoc).dboMechFullArray!=null){
    //     this.dboMechFullArraytest1 = this.storage.get(this.dboMechAuxLoc).dboMechFullArray;
       
    //   }
    //   if(this.storage.get(this.dboMechAuxLoc).dboMechFullArray1!=null){
    //     this.dboMechFullArraytest3 = this.storage.get(this.dboMechAuxLoc).dboMechFullArray1;
       
    //   }
    //   this.dboMechFullArray =  this.dboMechFullArraytest1.filter((x) => {
    //     return ((x.quotId ==this._ITOcustomerRequirementService.saveBasicDet.quotId));
    //   })
    //   this.dboMechFullArray1 =  this.dboMechFullArraytest3.filter((x) => {
    //     return ((x.quotId == this._ITOcustomerRequirementService.saveBasicDet.quotId));
    //   })
       
     
    // }

      this.itemSelectedListEdit = this.dboMechFullArray1.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.itemName === current.itemName);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
    

  console.log( this.dboMechFullArray1);
  console.log( this.dboMechFullArray);
  // if(!(this.temparray==null))
  // {
  //  for(let j=0;j<this.dboMechFullArray.length;j++)
  //  {
  //    for(let i=0;i<this.temparray.length;i++)
  //    {
  //     if(this.temparray[j].subItemId==this.dboMechFullArray[j].subItemId && this.temparray[j].addOnNewColFlag==1)
  //     {
  //       this.dboMechFullArray[j].addOnList.push(this.temparray[j]);
  //     }
    
  //    }
  //  }
  // }
  // if(!(this.temparray==null))
  // {
  //  for(let j=0;j<this.dboMechFullArray1.length;j++)
  //  {
  //   for(let i=0;i<this.temparray.length;i++)
  //   {
  //    if(this.temparray[j].subItemId==this.dboMechFullArray1[j].subItemId && this.temparray[j].addOnNewColFlag==1)
  //    {
  //      this.dboMechFullArray1[j].addOnList.push(this.temparray[j]);
  //    }
   
  //   }

  //  }
  // }
 

  
//   if (this.storage.get(this.dboMechAuxLoc) != null) {
//     this.saveInLocal(this.dboMechAuxLoc, {
//       dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
// dboMechFullArray1: this.dboMechFullArray1,
// itemIdList: this.itemIdList, 
// itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
// finalMechCost: this.finalMechCost,
// lhsRhsItemsList:this.lhsRhsItemsList,
// itemOthersAddonList:this.itemOthersAddonList
//     });
//   }

      this._ITOturbineConfigService.getDboFormData().subscribe(responn => {
        console.log(responn);
        this.dboFormData = responn;
        
       
       //this.dboFormData.framePowId = this._ITOcustomerRequirementService.saveBasicDet.framePowerId;
       this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;       
        //passing dbo form as input to getF2fItems
        this._DboMechAuxialriesService.getMechAuxItems(this.dboFormData).subscribe(repp => {
          console.log(repp);
          this.mechItemAux = repp;
          this.mechItemListAux = this.mechItemAux.dboMechAuxItemList;
          //filter ItemNames
          this.newArray = this.mechItemListAux.reduce((acc, current) => {
            console.log(acc, current);
            const x = acc.find(item => item.itemName === current.itemName);
            if (!x) {
              return acc.concat([current]);
            } else {
              return acc;
            } 
          }, []);
          console.log(this.newArray);

          
          for(let m = 0; m < this.itemSelectedListEdit.length; m++)
          {
            for (let k = 0; k < this.newArray.length; k++)
            {
              if(this.itemSelectedListEdit[m].itemId == this.newArray[k].itemId )
              {
                //To check the item in display list
                this.selectdEl[k] = true;
                this.itemIdList[k] = this.itemSelectedListEdit[m].itemId;
              }
    
            }
          }
          for(let m=0; m<this.itemIdListN.length; m++){
          for (let k = 0; k < this.newArray.length; k++){
              if(this.itemIdListN[m] == this.newArray[k].itemId){
                this.selectdEl[k] =true;
              }
            }
          }
        });
        console.log(this.itemIdList);
      });

      if (this.storage.get(this.dboMechAuxLoc) == null) {
        this.saveInLocal(this.dboMechAuxLoc, {
          dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
    dboMechFullArray1: this.dboMechFullArray1,
    itemIdList: this.itemIdList, 
    itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    finalMechCost: this.finalMechCost,
    lhsRhsItemsList:this.lhsRhsItemsList,
    itemOthersAddonList:this.itemOthersAddonList,addonflg:this.addonflg
        });
      }
 

    }
    dboMechAuxselect(d, i)
    {
      this.selectdEl[i]=true;
      this.itemIdList.push(d.itemId);
    }
    dboMechItmAux(event, itemId, i)
    {
      this.mainSave=true;
      this.mainSave2=true;
      if(!event.target.checked)
      {
      for(let j=0;j<this.itemIdList.length;j++)
      {
        if (this.itemIdList.includes(itemId)) {
          this.itemIdList[this.itemIdList.indexOf(itemId)] = null;
         
          this.selectdEl[i] = false; //To uncheck the selected item       
        }
      }
   

        for(let k = 0; k < this.dboMechFullArray.length; k++){
          if(this.dboMechFullArray[k].itemId == itemId){
              this.dboMechFullArray[k] = null;
             // this.dboMechFullArray.splice(k, 1);
              
            
          }
        }
        for(let k = 0; k < this.dboMechFullArray1.length; k++){
          if(this.dboMechFullArray1[k].itemId == itemId){
            this.dboMechFullArray1[k] = null;
            //this.dboMechFullArray1.splice(k, 1);
          }
        }
        for(let k = 0; k < this.addOnList.length; k++){
          if(this.addOnList[k].itemId == itemId){
            this.addOnList[k] = null;
           // this.addOnList.splice(k, 1);
          }
        }
        this.dboMechFullArray1 = this.dboMechFullArray1.filter(n => n != null);
        this.dboMechFullArray = this.dboMechFullArray.filter(n => n != null);
        this.addOnList = this.addOnList.filter(n => n != null);
console.log(this.dboMechFullArray1);
console.log(this.dboMechFullArray);
    //     this.saveInLocal(this.dboMechAuxLoc, {
    //       dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
    // dboMechFullArray1: this.dboMechFullArray1,
    // itemIdList: this.itemIdList, 
    // itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    // finalMechCost: this.finalMechCost,
    // lhsRhsItemsList:this.lhsRhsItemsList,
    // itemOthersAddonList:this.itemOthersAddonList
    //     });
    this.openbtn2=[];
    for(let i=0;i<=30;i++)
    {
      this.openbtn2.push([0]);
    }
    for(let j=0;j<20;j++)
    {
      this.openbtn2[this.subItemId1][j]=0;
    }
        this.selectdEl[i]=false;
      }

    }
    addRowsLhsRhs(){
      this.lhsRhsnewLine = true;
    }
    dboMechAuxSel(d,nm,i)
    {
     
     
      this.saveBtColorArray=[];
      this.diableitemname=false;

      this.oilBoolean=false;
      this.getPriceOld=[];
      this.getPriceNew=true;
this.openbtn=[];
this.openbtnmain=[];
      this.saveBtColor=true;
      this.finalItem = d.itemName;
      this.itemcost=0;
      this.itemcost1=0;
      this.discountPer = 0;
      this.newDiscountPer =0;
      this.AddOnFlagNew2=[];
      this.addonflg="0";
      this.enableAdd=true;
      console.log(this.dboMechFullArray1);
      this.defaultValues=[];
      this.defaultValues2=[];
      this.enableGetPriceBtn=false;

      // this.dboMechFullArray=[];
      // this.dboMechFullArray1=[];
      
this.errMsgRhsCost1=[];
this.errDisplayPnl1=[];
      this.itemId=d.itemId;
      this.mechItemId = d.mechItemId;
      this.mechid1=d.mechItemId;
      this.openOth=[];
      console.log(this.arraycheck);
      console.log(d.mechItemId);
      this.itemidnew=this.itemId;
      this.itemName=d.itemName;
      this.uniqueCode=d.itemCd;
    this.dispNewComp=false;
      this.counter1=0;
      this.selectedELIndex=i;
      this.buttonEnable=false;

      if( this.uniqueCode=="FFRV" ||  this.uniqueCode=="NRV" || this.uniqueCode=="QCNRV"|| this.uniqueCode=="ISV" ||  this.uniqueCode=="FLOW_NOZZLE" ||  this.uniqueCode=="FLOW_ORIFICE"  )
      {
        this.buttonEnable=true;
      }
      // if(this.storage.get(this.dboMechAuxLoc).dboMechFullArray != null){
      //   this.dboMechFullArray = this.storage.get(this.dboMechAuxLoc).dboMechFullArray;
       
      // }
      // if(this.storage.get(this.dboMechAuxLoc).dboMechFullArray1 != null){
      //   this.dboMechFullArray1 = this.storage.get(this.dboMechAuxLoc).dboMechFullArray1;
       
      // }
      // if(this.storage.get(this.dboMechAuxLoc).addOnList != null){
      //   this.addOnList = this.storage.get(this.dboMechAuxLoc).addOnList;
       
      // }
      this.dboCost1=0;
             this.qty=1;
           
            this.dboAddOnCost1=0;
            this.remarksAdd="";
      if(this.dboMechFullArray1.length!=0)
      {
        for(let j=0;j<this.dboMechFullArray1.length;j++)
        {
          if(this.dboMechFullArray1[j].itemId==this.itemId )
          {
            this.qty=this.dboMechFullArray1[j].qty;
            this.dboCost1=this.dboMechFullArray1[j].dboCost;
            this.discountPer = this.dboMechFullArray1[j].discountPer;
            if(  this.dboCost1==0)
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
            this.dboAddOnCost1=this.dboMechFullArray1[j].dboAddOnCost
            this.remarksAdd=this.dboMechFullArray1[j]. comrRemarks;
            this.defaultValues2=this.dboMechFullArray1[j].defaultValues;
            this.itemcost=this.dboMechFullArray1[j].itemcost;
            if(this.dboMechFullArray1[j].itemName=="Initial Fill and Flushing Oil")
            {
              this.oilinput=  this.qty;
            }
            if(this.addOnList.length>0)
          {
           
            for(let i=0;i<this.addOnList.length;i++)
            {
              if(this.addOnList[i].itemId==this.itemId)
              {
                
                 this.openOth[this.addOnList[i].colId]=true;
                 this.lhsdisableparent[this.addOnList[i].colId]=true;
                 //let test=this.dboMechFullArray1[j].addOnList[this.dboMechFullArray1[j].addOnList[i].colId];
                 this.newAddNameO[this.addOnList[i].colId]=this.addOnList[i].colValCd;
                 if(this.addOnList[i].rhsCost==null && this.addOnList[i].rhsColComrcomments==null)
                 {
                  this.newAddCostO[this.addOnList[i].colId]=this.addOnList[i].cost;
                  this.newAddComRemrkO[this.addOnList[i].colId]=this.addOnList[i].comrRemarks;
                 }
                 else
                 {
                  this.newAddCostO[this.addOnList[i].colId]=this.addOnList[i].rhsCost;
                  this.newAddComRemrkO[this.addOnList[i].colId]=this.addOnList[i].rhsColComrcomments;
                 }
               
                 this.AddOnFlagNew2[this.addOnList[i].colId]=true;
            }
          }
          }
          }
        }
      }
      if(this.uniqueCode=="OIL_CENTRIFUGE" || this.uniqueCode=="OV_TANK" )
      {
      this.oilBoolean=true;
      }
      if(this.uniqueCode=="VACC_BVL" || this.uniqueCode=="FILL_FLU" || this.uniqueCode=="OIL_CENTRIFUGE" || this.uniqueCode=="OV_TANK" )
     {
       this.enableGetPriceBtn=false;
     }
    else if(this.remarksAdd==null || this.remarksAdd=="NULL" || this.remarksAdd==""  )

    {
      this.enableGetPriceBtn=true;
    }
    else if(this.remarksAdd!=null  && this.remarksAdd!="" )
    {
      this.enableGetPriceBtn=false;
    }
      let length_check=0;
      this.newSet3=[];
      this.newSet4=[];
      if(this.dboMechFullArray.length!=0)
      {
        this.newSet4 =  this.dboMechFullArray.filter((x) => {
          return ((x.itemId == this.itemId));
        })
        this.newSet3 = Array.from(new Set( this.newSet4.map((x) => {
          return x.subItemId;
        })));
        if( this.newSet3.length!=0)
        {
        const max =  this.newSet3.reduce((a, b) => Math.max(a, b));  // 5
        this.subItemId=max;
         this.subItemId1=this.subItemId;
        }
        else
        {
          this.subItemId=1;
          this.subItemId1=this.subItemId;
        }
        
      }
      else
      {
        this.subItemId=1;
        this.subItemId1=this.subItemId;
      }
    
 for(let i=0;i<=30;i++)
      {
        this.arraycheck.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.arraycheck[this.subItemId1][j]=0;
      }
      for(let i=0;i<=30;i++)
      {
        this.errDisplayPnl.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.errDisplayPnl[this.subItemId1][j]=0;
      }
      for(let i=0;i<=30;i++)
      {
        this.errMsgRhsCost.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.errMsgRhsCost[this.subItemId1][j]=0;
      }
      for(let i=0;i<=30;i++)
      {
        this.openbtn2.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.openbtn2[this.subItemId1][j]=0;
      }
      for(let i=0;i<=30;i++)
      {
        this.lhsdisable.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.lhsdisable[this.subItemId1][j]=0;
      }
      for(let j=0;j<100;j++)
      {
        this.defaultValuesTemp.push([0]);

      }
      for(let j=0;j<100;j++)
      {
        for(let i=0;i<100;i++)
        {
        this.defaultValuesTemp[j][i]="";


        }
      }

      for(let j=0;j<20;j++)
      {
        this.AddOnFlagNew.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.AddOnFlagNew[this.subItemId1][j]=0;
      }

      for(let j=0;j<100;j++)
      {
        this.newAddNameO2.push([0]);
        this.newAddQtyO2.push([0]);
        this.newAddCostO2.push([0]);
        this.newAddRemrkO2.push([0]);
        this.newAddComRemrkO2.push([0]);
      }
      for(let j=0;j<100;j++)
      {
        for(let i=0;i<100;i++)
        {
        this.newAddNameO2[j][i]="";
        this.newAddQtyO2[j][i]="";
        this.newAddCostO2[j][i]="";
        this.newAddRemrkO2[j][i]="";
        this.newAddComRemrkO2[j][i]="";

        }
      }
      
      
      for(let i=0;i<this.dboMechFullArray.length;i++)
      {

        if(this.dboMechFullArray[i].itemId==this.itemId)  
        {
          this.defaultValuesTemp[this.dboMechFullArray[i].subItemId]=[];
for(let  j=0;j<this.dboMechFullArray[i].defaultValues.length;j++)
{
  this.defaultValuesTemp[this.dboMechFullArray[i].subItemId].push(this.dboMechFullArray[i].defaultValues[j]);
}
          this.saveBtColorArray[this.dboMechFullArray[i].subItemId]=true; 
        if(this.dboMechFullArray[i].addOnList!=null){
        for(let j=0;j<this.dboMechFullArray[i].addOnList.length;j++)
        {
          this.subid=this.dboMechFullArray[i].addOnList[j].subItemId;
          this.colid=this.dboMechFullArray[i].addOnList[j].colId;
         this.arraycheck[this.subid][this.colid]=1;
         this.openbtn2[this.subid][this.colid]=1;
         this.AddOnFlagNew[this.subid][this.colid]="1";
       
         // console.log( this.openOth3);
          //console.log(this.colid*(this.subid+1));
          //this.openOth2[this.colid*(this.subid+1)];
          this.newAddNameO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].colValCd;
          this.newAddQtyO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].colValCd;
          this.newAddCostO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].rhsCost;
          if(this.newAddCostO2[this.subid][this.colid]==null)
          {
            this.newAddCostO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].cost;
          }
          this.newAddRemrkO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].colValCd;
          this.newAddComRemrkO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].rhsColComrcomments;
          if( this.newAddComRemrkO2[this.subid][this.colid]==null)
          {
            this.newAddComRemrkO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].comrRemarks;
          }
          
          
        }
      }
    }
      }
    
    
     console.log(this.dboMechFullArray);
    
      if(this.dboCost1>0 )
      {

        this.enableAdd=false;
      }
     
      this.questionsBean=[];
      //this.defaultValues=[];
     
      console.log(this.selectdEl[i]);
      console.log(d,nm,i);
      this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboFormData.itemId = d.itemId;
      //this.dboFormData.subItemId =null;
      console.log("hello");
      this._DboMechAuxialriesService.getMechAuxPanels(this.dboFormData).subscribe(respp => {
        console.log("world");
        console.log(respp);
        this.panelList = respp;
        this.displayF2f = true;
        this.panelList = respp;
        this.questionsBean1 = respp.questionsBean;
        this.dboMechPanelList = respp.dboMechAuxPanelList1;
        this.dboMechPanelList2 = respp.dboMechAuxPanelList2;
    for(let u = 0; u< this.dboMechPanelList2.length; u++){
      if(this.dboMechPanelList2[u].colNm == "Flow Rate"){
        console.log("123");
        this.flowRate = this.dboMechPanelList2[u].colValCd;
        break;
      }
    }
    for(let l = 0; l < respp.dboMechAuxPanelList1.length; l++)
    {
      this.AddOnFlag[l] =  respp.dboMechAuxPanelList1[l].addOnNewColFlag;
      console.log(this.AddOnFlag[l]);
    }
         
    for(let l = 0; l < respp.dboMechAuxPanelList1.length; l++)
    {
      this.enableAddOn[l] =  respp.dboMechAuxPanelList1[l].enableAddOn;
      console.log(this.AddOnFlag[l]);
    }
        for(let z = 0; z < respp.dboMechAuxPanelList1.length; z++){
          for(let v = 0; v < this.questionsBean1.length; v++){
            if(respp.dboMechAuxPanelList1[z].colNm == this.questionsBean1[v].dropDownType.value){
              this.questionsBean1[v].dispInd = respp.dboMechAuxPanelList1[z].dispInd;
            }
          }
        }

      for(let k = 0; k < this.questionsBean1.length; k++){
        if(this.questionsBean1[k].dispInd == 1){
          this.questionsBean.push(this.questionsBean1[k]);
         
        }
      }
     this.defaultValues=[];
      for (let l = 0; l < this.questionsBean.length; l++) {
        for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
          //this.openOth[l] = false;
          if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
            this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
          }
        }
      }
      if(this.defaultValues2.length>0)
      {
     this.defaultValues=[];
     this.defaultValues=this.defaultValues2;
      console.log(this.defaultValues);
      this.defaultValues1.push(this.defaultValues);
      }

      if (this.dboMechFullArray.length > 0) {
        let k = this.dboMechFullArray.map((x) => x.id).includes((this.itemId));
        console.log(k);
        if (k) {
          this.dispFirstPanel = false;
        }
        else {
          this.dispFirstPanel = true;
        }
      }
          this.diableitemname=true;

    });
      console.log(this.questionsBean);
      console.log(this.dboMechFullArray1);

    }
    addRowsItem() {
      this.displayItmOthnewLine = true;
    }
    removeItem(data) {
      console.log(data)
  
      this._DboMechAuxialriesService.removeDboMechItem(this._ITOcustomerRequirementService.saveBasicDet.quotId, data.itemId, data.subItemId).subscribe(removeResp => {
        console.log(removeResp);
        if (removeResp.successCode == 0) {
          for (let m = 0; m < this.dboMechFullArray.length; m++) {
            if ((this.dboMechFullArray[m].itemId == data.itemId) && (this.dboMechFullArray[m].subItemId == data.subItemId)) {
              this.dboMechFullArray[m] = null;
              this.dboMechFullArray.splice(m,1);
            }
          }
          console.log(this.dboMechFullArray)
        }
        
        
        this.saveInLocal(this.dboMechAuxLoc, {
          dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
    dboMechFullArray1: this.dboMechFullArray1,
    itemIdList: this.itemIdList, 
    itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    finalMechCost: this.finalMechCost,
    lhsRhsItemsList:this.lhsRhsItemsList,
    itemOthersAddonList:this.itemOthersAddonList,addonflg:this.addonflg
        });
      
      })
    }
    openItemCompTable(event){
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
    
    cancelnewLineItem(){
      this.displayItmOthnewLine = false;
    }
    cancelLinesLhs(i){
      this.lhsRhsnewLine = false;
      this.lhsRhsItemsList.splice(i, 1);
    }
    cancelLinesItemOth(i) {
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
    addLhsRhsForm(lhsRhsItem, lhsRhsItemfrm: NgForm){
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
          this.dboClass.subItemId = this.itemOthersAddonList[c].subItemId;
          this.dboClass.subItemName = this.itemOthersAddonList[c].subItemName;
          this.dboClass.colId = 0;
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
        }
      }
     console.log(this.lhsRhsItemsList);
     lhsRhsItemfrm.reset();
      this.lhsRhsnewLine = false;
      this.displayItmOthnewLine = false;
    }
    newAddOn12(val, z,event){
    
      this.checkbol=false;
      console.log(val, z);
      
      if(event.target.checked){
        this.addOnCheck[z] = true;
        this.openOth1[z] =true;
      }
      else if(!event.target.checked){
        this.addOnCheck[z] = false;
        this.openOth1[z] =false;
        this.newAddNameO[z]=null;
        this.newAddCostO[z]=null;
        this.newAddComRemrkO[z]=null;
       
      }
      
    }
    newAddOn123(value,d)
    {
    this.AddOnFlagNew2[value]=true;
    this.addonflg="1";
      this.lhsdisableparent[value]=true;
      // this.newAddNameO[value]="";
      // this.newAddCostO[value]="";
      // this.newAddComRemrkO[value]="";
      this.openOth[value]=true;
      this.openbtn[value]=true;
      this.saveBtColor=true;
      this.mainSave2=true;
      this.mainSave=true;



      // if(this.addOnList.length!=0)
      // {
      //   {
      //     for(let j=0;j<this.addOnList.length;j++)
      //     {
      //       if(this.addOnList[j].itemid==this.itemId && this.addOnList[j].subItemId==this.subItemId)
      //       {

      //       }
      //     }
      //   }
      // }

    }
    newAddOn1234(value,d)
    {
      this.openbtn1=true;
      this.openOth1[d]=true;
      this.booleancheck=true;
    }
    newAddOn12345(value,d,i)
    {
      this.saveBtColorArray[d]=false;
      this.mainSave2=true;
      this.mainSave=true;

      this.AddOnFlagNew[d][i]="1";
      console.log(d);
      console.log(i);
      this.addonflg="1";
      this.openbtn2[d][i]=0;
      //console.log(this.subItemId1);
      //this.subBol[this.subItemId1]=true;
      this.checksub=d;
      this.multfactor = 0;
      this.multfactor = (i+1)*(d+1);
      //this.arraycheck[d][i]="done";
      this.arraycheck1[i]=true;
      // var arr = [];
      // var rows = 1;
      // var columns = 4;
     
      this.arraycheck[d][i]=1;
      this.lhsdisable[d][i]=1;
        // arr.push([0])
        // arr.push([0]);
        // arr.push([0]);
        
      
        // for (var j = 0; j < columns; j++) {
        //     arr[2][j] = 1;
        // }
     // this.arraycheck[d]=this.arraycheck1[i];
     // console.log(this.arraycheck[d][i]);
     // console.log( this.checksub);
      //this.openOth3[d]=true;
      //this.openOth4[d]=true;
      //console.log( this.openOth3[d][i]);
      //this.openOth2[this.multfactor]=true;
      //console.log(this.multfactor);
      //this.addonboolean[d][i]=true;
     //console.log(this.addonboolean[d][i]);
    }
    rhsOthersFormEdit(val,index,data)
{
  this.saveBtColor=true;
  this.mainSave2=true;
  this.mainSave=true;

  this.openbtn[index]=true;
  this.openbtnmain[index]=true;

}
    rhsOthersForm(val,index,data){
      this.saveBtColor=true;
      this.mainSave2=true;
      this.mainSave=true;

      console.log(data);
     // this.addOnList=[];
      this.openbtn[index]=false;
      for(let j=0;j<this.addOnList.length;j++)
      {
        if(this.addOnList[j].subItemId== 1 && this.addOnList[j].colId== index )
        {
          this.addOnList.splice(j,1);
        }
      }
      console.log(this.newAddNameO[index]);
      console.log(this.newAddCostO[index]);
      console.log(this.newAddComRemrkO[index]);
          for(let n = 0; n < this.dboMechPanelList.length; n++){
        if(val ==  this.dboMechPanelList[n].colNm){
          this.colValNmId = this.dboMechPanelList[n].colId;
        }
      }
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.itemId;
      this.dboClass.itemName = val;
      if(this.subItemId == 0 || this.subItemId == null){
         this.dboClass.subItemId = 1;
        }else {
          this.dboClass.subItemId = 1;           
        }  
      this.dboClass.subItemName = null;
      this.dboClass.colId = this.colValNmId;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = this.newAddNameO[index]; 
      this.dboClass.quantity = 1;
      //uncomments after css changes
     // this.dboClass.quantity =this.newAddQtyO[index] ;         
      this.dboClass.cost = this.newAddCostO[index];          
      this.dboClass.techRemarks =null ;
      this.dboClass.comrRemarks = this.newAddComRemrkO[index];
      this.dboClass.addOnFlg = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
     
      this.addOnList.push(this.dboClass);
this.addonflg="0";
      // for(let j=0;j<this.dboMechFullArray1.length;j++)
      // {
      //   for(let i=0; i<this.addOnList.length;i++)
      //   {
      //     console.log(this.dboMechFullArray1[j].subItemId);
      //     console.log(this.addOnList[i].subItemId);
      //     if((this.dboMechFullArray1[j].subItemId==1) && (this.dboMechFullArray1[j].itemId==this.addOnList[i].itemId))
      //     {
      
      //    //this.addOnList4.push(this.addOnList2[i]);
         
      //   // console.log(this.addOnList4);
      //   //let length= this.dboMechFullArray[j].addOnList.length;
      //   let temp=[];
      //   if( this.dboMechFullArray1[j].addOnList.length!=0)
      //   {
      //     temp= this.dboMechFullArray1[j].addOnList;
      //     this.addOnList[temp.length]=temp;
      //   }
      
        
      //       this.dboMechFullArray1[j].addOnList=this.addOnList;
            
      //       break;
      //     }
      //   }
      // }
  //     this.saveInLocal(this.dboMechAuxLoc, {
  //       dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
  // dboMechFullArray1: this.dboMechFullArray1,
  // itemIdList: this.itemIdList, 
  // itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
  // finalMechCost: this.finalMechCost,
  // lhsRhsItemsList:this.lhsRhsItemsList,
  // itemOthersAddonList:this.itemOthersAddonList
  //     });
      // this.addOnList=[];


      //this.addOnList1.push(this.dboClass);
      //  if(index>0)
      //  {
      //    for(let j=0;j<index;j++)
      //    {
      //      this.addOnList.push(null);
      //    }
      //  }
      //this.addOnList[index].push(this.dboClass);
      console.log(this.addOnList);

      // this.addOnList1=[];
      //this.addOnList[index].push(this.dboClass);
    }
    rhsOthersForm12Edit(val, index,data){
      console.log(data);
      this.openbtn2[data.subItemId][index]=0;
      this.saveBtColorArray[data.subItemId]=false;
      this.mainSave2=true;
      this.mainSave=true;

    }
    rhsOthersForm12(val, index,data){
      this.saveBtColorArray[data.subItemId]=false;
      this.mainSave2=true;
      this.mainSave=true;


      console.log(data);

      this.openbtn2[data.subItemId][index]=1;
    



      console.log(this.newAddNameO2[index]);
      console.log(this.newAddCostO2[index]);
      console.log(this.newAddComRemrkO2[index]);
          for(let n = 0; n < this.dboMechPanelList.length; n++){
        if(val ==  this.dboMechPanelList[n].colNm){
          this.colValNmId = this.dboMechPanelList[n].colId;
        }
      }

      for(let j=0;j<data.addOnList.length;j++)
      {
        if(data.addOnList[j].subItemId==  data.subItemId && data.addOnList[j].colId== index )
        {
          data.addOnList.splice(j,1);
        }
      }
    
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.itemId;
      this.dboClass.itemName = val;
      this.dboClass.subItemId = data.subItemId;            
      this.dboClass.subItemName = null;
      this.dboClass.colId = index;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = this.newAddNameO2[data.subItemId][index]; 
      this.dboClass.quantity = null;
      //uncomments after css changes
     // this.dboClass.quantity =this.newAddQtyO[index] ;         
      this.dboClass.cost = this.newAddCostO2[data.subItemId][index];          
      this.dboClass.techRemarks =null ;
      this.dboClass.comrRemarks = this.newAddComRemrkO2[data.subItemId][index];
      this.dboClass.addOnFlg = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
      this.addOnList17.push(this.dboClass);
      if(data.addOnList.length!=0){
      this.addOnList6=data.addOnList;
      }
      for(let j=0;j<this.addOnList6.length;j++)
      {
        if(this.addOnList6[j].subItemId== data.subItemId && this.addOnList6[j].colId== index )
        {
          this.addOnList6.splice(j,1);
        }
      }
      this.addOnList6.push(this.dboClass);
      console.log( this.addOnList6);
      for(let j=0;j<this.dboMechFullArray.length;j++)
      {
        for(let i=0; i<this.addOnList6.length;i++)
        {
          console.log(this.dboMechFullArray[j].subItemId);
          console.log(this.addOnList6[i].subItemId);
          if((this.dboMechFullArray[j].subItemId==this.addOnList6[i].subItemId) && (this.dboMechFullArray[j].itemId==this.addOnList6[i].itemId))
          {
      
         //this.addOnList4.push(this.addOnList2[i]);
         
        // console.log(this.addOnList4);
        //let length= this.dboMechFullArray[j].addOnList.length;
            this.dboMechFullArray[j].addOnList=this.addOnList6;
            
            break;
          }
        }
      }
      //this.addOnList1.push(this.dboClass);
      //  if(index>0)
      //  {
      //    for(let j=0;j<index;j++)
      //    {
      //      this.addOnList.push(null);
      //    }
      //  }
      //this.addOnList[index].push(this.dboClass);
      console.log(this.dboMechFullArray);
    //   this.addOnList4=[];
      // for(let k=0;k<this.dboMechFullArray.length;k++)
      // {
      //   this.dboMechFullArray[k].addOnList.sort();
      // }
      console.log(this.addOnList6);
      this.addonflg="0";
  //     this.saveInLocal(this.dboMechAuxLoc, {
  //       dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
  // dboMechFullArray1: this.dboMechFullArray1,
  // itemIdList: this.itemIdList, 
  // itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
  // finalMechCost: this.finalMechCost,
  // lhsRhsItemsList:this.lhsRhsItemsList,
  // itemOthersAddonList:this.itemOthersAddonList
  //     });
      this.addOnList6=[];
      this.addOnList4=[];
      
      // this.addOnList1=[];
      //this.addOnList[index].push(this.dboClass);
    }
    rhsOthersForm1(val, index,data){
      console.log(data);
      console.log(this.newAddNameO1[index]);
      console.log(this.newAddCostO1[index]);
      console.log(this.newAddComRemrkO1[index]);
          for(let n = 0; n < this.dboMechPanelList.length; n++){
        if(val ==  this.dboMechPanelList[n].colNm){
          this.colValNmId = this.dboMechPanelList[n].colId;
        }
      }
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.itemId;
      this.dboClass.itemName = val;
      if(this.subItemId == 0 || this.subItemId == null){
         this.dboClass.subItemId = null;
        }else {
          this.dboClass.subItemId = this.subItemId;           
        }  
      this.dboClass.subItemName = null;
      this.dboClass.colId = this.colValNmId;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = this.newAddNameO1[index]; 
      this.dboClass.quantity = 1;
      //uncomments after css changes
     // this.dboClass.quantity =this.newAddQtyO[index] ;         
      this.dboClass.cost = this.newAddCostO1[index];          
      this.dboClass.techRemarks =null ;
      this.dboClass.comrRemarks = this.newAddComRemrkO1[index];
      this.dboClass.addOnFlg = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
      this.addOnList1.push(this.dboClass);
      //this.addOnList1.push(this.dboClass);
      //  if(index>0)
      //  {
      //    for(let j=0;j<index;j++)
      //    {
      //      this.addOnList.push(null);
      //    }
      //  }
      //this.addOnList[index].push(this.dboClass);
      console.log(this.addOnList1);
      // this.addOnList1=[];
      //this.addOnList[index].push(this.dboClass);
    }
    itemOthersForm(othersItem, othersItemfrm: NgForm) {
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
     
      this.itemOthersAddonList.push(this.dboClass);
      this.lhsRhsItemsList.push(this.dboClass);//satya
      console.log(this.itemOthersAddonList);
      othersItemfrm.reset();
      this.displayItmOthnewLine = false;
    }
    newAddOn1()
    {
     
      this.dispNewComp = true;
    this.newQty = 1;
    this.newDiscountPer = 0;
    this.newdboCost = 0;
    this.newdboAddOnCost = 0;
    this.newRemarksAdd = '';
    this.newDefaultValues = [];
    this.newNoLtrs = 0;
    //setting defaultvalues whwn add is clicked
    for (let l = 0; l < this.questionsBean.length; l++) {
      for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
        if (this.questionsBean[l].dropDownValueList[d].defaultFlag==true) {
          this.newDefaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
        }
      }

    }
    console.log(this.newDefaultValues)
    this.enableAdd = true;
   // this.checkDisplay();
      
    }
    itemRmrkCheck(event)
    {
      if(event.target.checked){
       this.itemRemarkDiv=true;
      }else if(!event.target.checked){
        this.itemRemarkDiv=false;
        this.itemRemarksVal="";
      }
    }
    checkDisplay() {
      console.log(this.itemIdList);
      console.log(this.dboMechFullArray);
      if (this.dboMechFullArray.length > 0) {
        this.dboMechFullArray = this.dboMechFullArray.filter((x) => {
          return x != null;
        })
        let k = this.dboMechFullArray.map((x) => x.id).includes((this.itemId));
        console.log(k);
        if (k) {
          this.dispFirstPanel = false;
        }
        else {
          this.dispFirstPanel = true;
          this.dboCost = 0;
          this.dboAddOnCost = 0;
          this.qty = 1;
          this.discountPer = 0;
          this.remarksAdd = '';
          console.log(this.itemIdList, this.itemId, this.mechItemListAux)
          if (this.itemIdList.includes(this.itemId)) {
            console.log("Test");
            this.itemIdList[this.itemIdList.indexOf(this.itemId)] = null;
  
            let i = this.mechItemListAux.map(function (item) {
              return item.id;
            }).indexOf(this.itemId);
            console.log(i)
            this.selectdEl[i] = false;
          }
          console.log(this.selectdEl)
        }
      } else {
        this.dispFirstPanel = true;
        this.dboCost = 0;
        this.dboAddOnCost = 0;
        this.qty = 1;
        this.discountPer = 0;
        this.remarksAdd = '';
      }
      if (this.dispFirstPanel) {
        this.enableAdd = true;
      }
      this.hideprogress = false;
      console.log(this.dboMechFullArray);
    }
    itemRmrkCheckIn(event)
    {
      if(event.target.checked){
        this.itemTechRmkDiv=true;
       }else if(!event.target.checked){
         this.itemTechRmkDiv=false;
         this.mechItemTechRemarks="";
       }
    }
    itmComrRmrk(event)
    {
      if(event.target.checked){
        this.itemComrRemarkDiv=true;
       }else if(!event.target.checked){
         this.itemComrRemarkDiv=false;
         this.itemCmrRemarksVal="";
       }
    }
    itmComrRmrkAux(event)
    {
      if(event.target.checked){
        this.itemComrRemarkDivAux=true;
       }else if(!event.target.checked){
         this.itemComrRemarkDivAux=false;
         this.itemCmrRemarksValAux="";
       }

      }

      cancelAddOn(key, z){
        this.saveBtColor=true;
        this.mainSave2=true;
        this.mainSave=true;
  
        this.AddOnFlagNew2[key]=false;
        this.addonflg="0";
        this.openbtn[key]=false;
        console.log(key, z)
        if(this.addOnList.length != 0) {
          for(let k = 0; k < this.addOnList.length; k ++){
            if(this.addOnList[k].colId == key){
              this.addOnList.splice(k,1);
            }
          }  
        }
        this.openOth[key] = false;
        this.newAddComRemrkO[key] = "";
        this.newAddCostO[key] = "";
        this.newAddNameO[key] = "";
        this.newAddRemrkO[key] = "";
        this.newAddQtyO[key] = "";
        this.lhsdisableparent[key]=false;
        for(let j=0;j<this.dboMechFullArray1.length;j++)
        {
          if(this.dboMechFullArray1[j].itemid==this.itemId && this.dboMechFullArray1[j].subItemId==1)
          {
            if(this.dboMechFullArray1[j].addonlist.length!=0)
            {
            for(let i=0;i<this.dboMechFullArray1[j].addonlist.length;i++)
            {
              if(this.dboMechFullArray1[j].addonlist[i].colId==key)
              {
                this.dboMechFullArray1[j].addonlist[i]=null;
                this.dboMechFullArray1[j].addonlist.splice(i,1);
              }
            }
           
            }
          }
        }
    //     this.saveInLocal(this.dboMechAuxLoc, {
    //       dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
    // dboMechFullArray1: this.dboMechFullArray1,
    // itemIdList: this.itemIdList, 
    // itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    // finalMechCost: this.finalMechCost,
    // lhsRhsItemsList:this.lhsRhsItemsList,
    // itemOthersAddonList:this.itemOthersAddonList
    //     });
        console.log(this.addOnList);
      }
      cancelAddOn12(key, z,data){
        this.saveBtColorArray[data.subItemId]=false;
        this.mainSave2=true;
        this.mainSave=true;
  
        this.addonflg="0";
        this.AddOnFlagNew[data.subItemId][key]="0";
        console.log(key, z);
       
        // if(this.addOnList2.length != 0) {
        //   for(let k = 0; k < this.addOnList2.length; k ++){
        //     if(this.addOnList2[k].colId == key){
        //       this.addOnList2.splice(k,1);
        //     }
        //   }  
        // }
        // this.openOth[z] = false;
        // this.newAddComRemrkO2[z] = "";
        // this.newAddCostO2[z] = "";
        // this.newAddNameO2[z] = "";
        // this.newAddRemrkO2[z] = "";
        // this.newAddQtyO2[z] = "";
        // console.log(this.addOnList2);
        this.arraycheck[data.subItemId][key]=0;
        this.newAddNameO2[data.subItemId][key]="";
        this.newAddQtyO2[data.subItemId][key]="";
        this.newAddCostO2[data.subItemId][key]="";
        this.newAddRemrkO2[data.subItemId][key]="";
        this.newAddComRemrkO2[data.subItemId][key]="";
        this.arraycheck[data.subItemId][key]=0;
        this.lhsdisable[data.subItemId][key]=0;
        this.openbtn2[data.subItemId][key]=0;
        for(let j=0;j<this.dboMechFullArray.length;j++)
        {
          if(this.dboMechFullArray[j].itemId==data.itemId && this.dboMechFullArray[j].subItemId==data.subItemId)
          {
            for(let i=0;i<this.dboMechFullArray[j].addOnList.length;i++)
            {
              if(this.dboMechFullArray[j].addOnList[i].colId==key)
              {
                this.dboMechFullArray[j].addOnList.splice(i,1);
                this.arraycheck[data.subItemId][key]=0;
                this.newAddNameO2[data.subItemId][key]="";
                this.newAddQtyO2[data.subItemId][key]="";
                this.newAddCostO2[data.subItemId][key]="";
                this.newAddRemrkO2[data.subItemId][key]="";
                this.newAddComRemrkO2[data.subItemId][key]="";
                this.arraycheck[data.subItemId][key]=0;
                this.lhsdisable[data.subItemId][key]=0;
                this.openbtn2[data.subItemId][key]=0;
               

              }
            }
          }
        }
    //     this.saveInLocal(this.dboMechAuxLoc, {
    //       dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
    // dboMechFullArray1: this.dboMechFullArray1,
    // itemIdList: this.itemIdList, 
    // itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    // finalMechCost: this.finalMechCost,
    // lhsRhsItemsList:this.lhsRhsItemsList,
    // itemOthersAddonList:this.itemOthersAddonList
    //     });
        console.log(this.dboMechFullArray);

      }
      cancelAddOn1(key, z){
        console.log(key, z)
        if(this.addOnList1.length != 0) {
          for(let k = 0; k < this.addOnList1.length; k ++){
            if(this.addOnList1[k].colId == key){
              this.addOnList1.splice(k,1);
            }
          }  
        }
        this.openOth[z] = false;
        this.newAddComRemrkO[z] = "";
        this.newAddCostO[z] = "";
        this.newAddNameO[z] = "";
        this.newAddRemrkO[z] = "";
        this.newAddQtyO[z] = "";
        console.log(this.addOnList1);
      }
      saveInLocal(key, val): void {
        this.storage.set(key, val);
        this.dboMechanicalAuxLocal[key] = this.storage.get(key);
      }
    getPriceExel()
    {
      this.mainSave=true;
      this.mainSave2=true;
      this.getPriceNew=true;
      if( this.openbtn.includes(true) )
      {
        this.getPriceNew=false;
      }
      if(this.getPriceNew )
      {
      this._ITOeditQoutService.dboMechAuxData=[];
      this._ITOeditQoutService.dboMechAuxDataAddonData=[];
this.addOnList16=[];
     this.SelectedExcelData=[];
     this.moveItem = false;
      this.dboCost1 = 0;
     //this.subItemId=1;
     this.addOnList14=[];
     this.addOnList15=[];

     let addonlist12;
    // for(let j=0;j<this.dboMechFullArray1.length;j++)
    // {
    //   if(this.dboMechFullArray1[j].subItemId==1 && this.dboMechFullArray1[j].itemId==this.itemId )
    //   {
    //     addonlist12=this.dboMechFullArray1[j].addOnList;
    //     break;
    //   }
    // }
  //   if(!(addonlist12==null))
  //   {
  //   for(let j=0;j<addonlist12.length;j++)
  //   {
  //     this.dboClass = new dboClass();
  //     this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  //     this.dboClass.itemId = addonlist12[j].itemId;
  //     this.dboClass.itemName = null;
  //     this.dboClass.subItemId = addonlist12[j].subItemId;            
  //     this.dboClass.subItemName = null;
  //     this.dboClass.colId = addonlist12[j].colId;
  //     this.dboClass.colNm = null;
  //     this.dboClass.colValCd = addonlist12[j].colValCd;
  //     this.dboClass.quantity = null;
  //     //uncomments after css changes
  //    // this.dboClass.quantity =this.newAddQtyO[index] ;         
  //     this.dboClass.cost = addonlist12[j].colValCd;          
  //     this.dboClass.techRemarks =null ;
  //     this.dboClass.comrRemarks = addonlist12[j].colValCd;
  //     this.dboClass.addOnFlg = 1;
  //     this.dboClass.techFlag = 1;
  //     this.dboClass.comrFlag = 1;
  //     this.SelectedExcelData.push(this.dboClass);
      
  //   }
  // }
  // this.addOnList16=[];
  // for(let j=0;j<this.dboMechFullArray1.length;j++)
  // {
  //   if(this.dboMechFullArray1[j].itemId==this.itemId)
  //   {
  //     if(this.dboMechFullArray1[j].addOnList.length!=0)
  //     {
  //       this.addOnList16=this.dboMechFullArray1[j].addOnList;
  //     }
  //   }
  // }
 
  if(this.addOnList.length!=0)
  {
  for(let j=0;j<this.addOnList.length;j++)
  {
    if(this.addOnList[j].subItemId==1 && this.addOnList[j].itemId==this.itemId )
    {
      this.addOnList16.push(this.addOnList[j]);
    }
  }
}

  if(!(this.addOnList16==null))
    {
    for(let j=0;j<this.addOnList16.length;j++)
    {
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.addOnList16[j].itemId;
      this.dboClass.itemName = null;
      this.dboClass.subItemId = this.addOnList16[j].subItemId;            
      this.dboClass.subItemName = null;
      this.dboClass.colId = this.addOnList16[j].colId;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = this.addOnList16[j].colValCd;
      this.dboClass.quantity = null;
      //uncomments after css changes
     // this.dboClass.quantity =this.newAddQtyO[index] ; 
     if( this.addOnList16[j].rhsCost==null)
     {
      this.dboClass.cost = this.addOnList16[j].cost; 
     }  
     else
     {
      this.dboClass.cost = this.addOnList16[j].rhsCost; 
     }      
             
      this.dboClass.techRemarks =null ;
      if( this.addOnList16[j].rhsColComrcomments==null)
      {
        this.dboClass.comrRemarks = this.addOnList16[j].comrRemarks;
      }
      else
      {
        this.dboClass.comrRemarks = this.addOnList16[j].rhsColComrcomments;
      }
     
      this.dboClass.addOnFlg = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
      this.SelectedExcelData.push(this.dboClass);
      
    }
  }
    console.log(addonlist12);
     this.SelectedExcelData.push();


      console.log( this.questionsBean.length);
      if(this.uniqueCode!="FILL_FLU")
      {
      for (let l = 0; l < this.questionsBean.length; l++) {
        this.dboClass = new dboClass();
        //To push the selected dropdown values in the panel
        //OtherColValFlag and addoncostmeflag will be zero
        //As the default values will be set in the dropdown
        for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
          if (this.questionsBean[l].dropDownValueList[d].value == this.defaultValues[l]) {
            this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
            this.dboClass.itemId = this.itemId;
            this.dboClass.itemName = null;
           this.dboClass.subItemId = 1;
            this.dboClass.subItemName = null;
            this.dboClass.colId = this.questionsBean[l].dropDownType.key;
            this.dboClass.colNm = null;
            this.dboClass.colValCd = this.questionsBean[l].dropDownValueList[d].code; 
            this.dboClass.quantity = null;         
            this.dboClass.cost = null;          
            this.dboClass.techRemarks = null;
            this.dboClass.comrRemarks = this.remarksAdd;
            this.dboClass.addOnFlg = 0;
            this.dboClass.techFlag = this.questionsBean[l].techFlag;
            this.dboClass.comrFlag = this.questionsBean[l].comrFlag;
            this.SelectedExcelData.push(this.dboClass);
          }
        }
      }
    }
    else if(this.itemName=="Initial Fill and Flushing Oil")
    {
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = this.itemId;
      this.dboClass.itemName = null;
     this.dboClass.subItemId = 1;
      this.dboClass.subItemName = null;
      this.dboClass.colId = this.questionsBean[0].dropDownType.key;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = "1"; 
      this.dboClass.quantity = this.oilinput;         
      this.dboClass.cost = null;          
      this.dboClass.techRemarks = null;
      this.dboClass.comrRemarks = this.remarksAdd;
      this.dboClass.addOnFlg = 0;
      this.dboClass.techFlag = this.questionsBean[0].techFlag;
      this.dboClass.comrFlag = this.questionsBean[0].comrFlag;
      this.SelectedExcelData.push(this.dboClass);
    } 
      this.dboFormData.mechAuxTechData = this.SelectedExcelData;
      this.dboFormData.quantity = this.qty;
      this.dboFormData.mechAuxItemTechRemarks = null;
      this.dboFormData.mechAuxItemComrRemarks = null;
      this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
      this.dboFormData.discountPer = this.discountPer;
      console.log(this.dboFormData);
      if(this.uniqueCode=="FILL_FLU")
      {
        this.qty=this.oilinput;
      }
    this._DboMechAuxialriesService.getMechAuxTechPrice(this.dboFormData).subscribe(res => {
      console.log(res);
      if(res.successCode == 0){
        this._ITOcustomerRequirementService.sendmecBtnStatus(true);
      }
     // this.techPriceResp = res;
      this.dboCost1 = res.basicCost;
      if(  this.dboCost1==0)
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
this.itemcost=res.mechAuxTechList[0].itemCost;
      if (res.successCode == 0 && this.subItemId==1 && this.dispNewComp==false) {
       
        this.enableAdd = false;
      }
      for(let j=0;j<res.mechAuxTechList.length;j++)
      {
        if(res.mechAuxTechList[j].addOnNewColFlag==1)
        {
          this.addOnList14.push(res.mechAuxTechList[j]);
        }
      }
     
      for(let j=0;j<res.mechAuxTechList.length;j++)
      {
        if(res.mechAuxTechList[j].addOnNewColFlag==0)
        {
          this.addOnList15.push(res.mechAuxTechList[j]);
        }
      }
      this.errMsgRhsCost1=[];
      this.errDisplayPnl1=[];
      for(let c=0;c< res.mechAuxTechList.length;c++){
        if((  res.mechAuxTechList[c].rhsCost > 0 && res.mechAuxTechList[c].lhsFlag == 0 && res.mechAuxTechList[c].addOnFlg == 0 )){
          this.errMsgRhsCost1[res.mechAuxTechList[c].colId] = "AddOnCost: " + res.mechAuxTechList[c].rhsCost;
          this.errDisplayPnl1[res.mechAuxTechList[c].colId] = true;
        }
      }
      
     
      if (this.dboMechFullArray1.length != 0) {
        for (let d = 0; d < this.dboMechFullArray1.length; d++) {
          let j = this.dboMechFullArray1.map(d => { return d.id }).indexOf(this.mechItemId);
          if (j != (-1)) {
             console.log("test123");
            this.dboMechFullArray1[j] = {
              quotId: res.quotId,
              qty: this.qty,
              discountPer: this.discountPer,
              id: this.mechItemId,
              componenet: this.itemName,
              defaultValues: this.defaultValues,
              dboCost: res.basicCost,
              dboAddOnCost: res.mechAuxAddOnCost,
              techComments: res.techComments,
              comrComments: res.comrComments,
              techRemarks: res.techRemarks,
              comrRemarks :  this.addOnList15[0].rhsColComrcomments,
              mechItemTechRemarks: res.mechItemTechRemarks,
              mechItemComrRemarks: res.mechItemComrRemarks,
              addOnList:  this.addOnList14,
              itemOthersList: this.itemOthersAddonList,
              itemId: this.itemId,
              itemName: this.itemName,
              subItemId: 1,
              subItemName: null,
              errMsgRhsCost1:this.errMsgRhsCost1,
              errDisplayPnl1:this.errDisplayPnl1,
              errMsgRhsCost:this.errMsgRhsCost,
              errDisplayPnl:this.errDisplayPnl,
              itemcost:this.itemcost
            };
            break;
          }
          else {
            console.log("test123oveitem");
            this.moveItem = true;
            break;
          }
        }
        if (this.moveItem == true) {
          console.log("test123notmoveitem");
          this.dboMechFullArray1.push({
            quotId: res.quotId,
            qty: this.qty,
            discountPer: this.discountPer,
            id: this.mechItemId,
            componenet: this.itemName,
            defaultValues: this.defaultValues,
            dboCost: res.basicCost,
            dboAddOnCost: this.dboAddOnCost,
            techComments: res.techComments,
            comrComments: res.comrComments,
            techRemarks: res.techRemarks,
            comrRemarks :this.addOnList15[0].rhsColComrcomments,
            mechItemTechRemarks: res.mechItemTechRemarks,
            mechItemComrRemarks: res.mechItemComrRemarks,
            addOnList: this.addOnList14,
            itemOthersList: this.itemOthersAddonList,
            itemId: this.itemId,
            itemName: this.itemName,
            subItemId: 1,
            subItemName: null,
            errMsgRhsCost1:this.errMsgRhsCost1,
              errDisplayPnl1:this.errDisplayPnl1,
              errMsgRhsCost:this.errMsgRhsCost,
              errDisplayPnl:this.errDisplayPnl,
              itemcost:this.itemcost
          });
        }
      }
      else {
        console.log("test123notmoveitemelse");
        //adding the values to dboMechFullArray if it wont contains particular item in its list
        this.dboMechFullArray1.push({
          quotId: res.quotId,
          qty: this.qty,
          discountPer: this.discountPer,
              id: this.mechItemId,
              componenet: this.itemName,
              defaultValues: this.defaultValues,
              dboCost: res.basicCost,
              dboAddOnCost: this.dboAddOnCost,
              techComments: res.techComments,
              comrComments: res.comrComments,
              techRemarks: res.techRemarks,
              comrRemarks : this.addOnList15[0].rhsColComrcomments,
              mechItemTechRemarks: res.mechItemTechRemarks,
              mechItemComrRemarks: res.mechItemComrRemarks,
              addOnList:  this.addOnList14,
              itemOthersList: this.itemOthersAddonList,
              itemId: this.itemId,
              itemName: this.itemName,
              subItemId: 1,
              subItemName: null,
              errMsgRhsCost1:this.errMsgRhsCost1,
              errDisplayPnl1:this.errDisplayPnl1,
              errMsgRhsCost:this.errMsgRhsCost,
              errDisplayPnl:this.errDisplayPnl,
              itemcost:this.itemcost
        });
      }
      console.log(this.dboMechFullArray1);
     this.dboAddOnCost1=res.mechAuxAddOnCost;
  
      
      this.itemIdList.push(this.itemId);
      console.log(this.itemIdList);
     // this.enableAdd=false;
      this.saveInLocal(this.dboMechAuxLoc, {
        dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
  dboMechFullArray1: this.dboMechFullArray1,
  itemIdList: this.itemIdList, 
  itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
  finalMechCost: this.finalMechCost,
  lhsRhsItemsList:this.lhsRhsItemsList,
  itemOthersAddonList:this.itemOthersAddonList,addonflg:this.addonflg
      });

      this.selectdEl[this.selectedELIndex]=true;


    });
      
    




    //this.enableAdd[this.itemId]=true;
   // this.newAddonbu[this.itemidnew]=false;



  









  }
  
  }

 
 

  getnewPriceExel()
  {
    this.mainSave=true;
    this.mainSave2=true;
    this._ITOeditQoutService.dboMechAuxData=[];
console.log(this.subItemId);
 console.log(this.addOnList1);
    this.checkbol=true;
    var arr = [];
var rows = 1;
var columns = 4;

  arr.push([0])
  arr.push([0]);
  arr.push([0]);
  

  for (var j = 0; j < columns; j++) {
      arr[2][j] = 0;
  }

    console.log(arr);
console.log(this.arraycheck);
  
    this.booleancheck=false;
   this.counter1=this.counter1+1;
   // this.dispNewComp=false;
   this.SelectedExcelData=[];
   this.moveItem = true;
   this.dboCost = 0;
  //this.dboMechFullArray=[]
  console.log(this.subItemId);
  
  
  this.subItemId1=this.subItemId+this.counter1;

  
      for(let i=0;i<=30;i++)
      {
        this.arraycheck.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.arraycheck[this.subItemId1][j]=0;
      }
      for(let i=0;i<=30;i++)
      {
        this.openbtn2.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.openbtn2[this.subItemId1][j]=0;
      }
      for(let i=0;i<=30;i++)
      {
        this.lhsdisable.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.lhsdisable[this.subItemId1][j]=0;
      }
      for(let j=0;j<100;j++)
      {
        this.newAddNameO2.push([0]);
        this.newAddQtyO2.push([0]);
        this.newAddCostO2.push([0]);
        this.newAddRemrkO2.push([0]);
        this.newAddComRemrkO2.push([0]);
      }

      for(let j=0;j<100;j++)
      {
        for(let i=0;i<100;i++)
        {
        this.newAddNameO2[j][i]="";
        this.newAddQtyO2[j][i]="";
        this.newAddCostO2[j][i]="";
        this.newAddRemrkO2[j][i]="";
        this.newAddComRemrkO2[j][i]="";

        }
      }
      for(let j=0;j<20;j++)
      {
        this.AddOnFlagNew.push([0]);
      }
      for(let j=0;j<20;j++)
      {
        this.AddOnFlagNew[this.subItemId1][j]=0;
      }
       for(let i=0;i<this.dboMechFullArray.length;i++)
     {
      if(this.dboMechFullArray[i].itemId==this.itemId)
      {
       if(this.dboMechFullArray[i].addOnList!=null){
       for(let j=0;j<this.dboMechFullArray[i].addOnList.length;j++)
       {
         this.subid=this.dboMechFullArray[i].addOnList[j].subItemId;
         console.log(this.subid);
         this.colid=this.dboMechFullArray[i].addOnList[j].colId;
         console.log(this.colid);
         console.log(this.arraycheck);
        this.arraycheck[this.subid][this.colid]=1;
        this.lhsdisable[this.subid][this.colid]=1;
        this.openbtn2[this.subid][this.colid]=1;
       
        // console.log( this.openOth3);
         //console.log(this.colid*(this.subid+1));
         //this.openOth2[this.colid*(this.subid+1)];

         this.newAddNameO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].colValCd;
         this.newAddQtyO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].colValCd;
         if(this.dboMechFullArray[i].addOnList[j].rhsColComrcomments==null || this.dboMechFullArray[i].addOnList[j].rhsColComrcomments=="")
         {
          this.newAddCostO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].cost;

         }
         else
         {
          this.newAddCostO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].rhsCost;

         }
         if(this.dboMechFullArray[i].addOnList[j].rhsColComrcomments==null || this.dboMechFullArray[i].addOnList[j].rhsColComrcomments=="")
         {
          this.newAddComRemrkO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].comrRemarks;

         }
         else
         {
          this.newAddComRemrkO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].rhsColComrcomments;

         }
         this.newAddRemrkO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].colValCd;
         
         
       }
     }
    }
     }
  //this.arraycheck[2][2]=false;
  //console.log(this.arraycheck[2][2]);
  //console.log(this.arraycheck);

//   if(this.addOnList1.length==0){
//   for(let j=0;j<this.addOnList1.length ;j++)
//   {
//  this.addOnList1[j].subItemId=this.subItemId1;
//   }
// }
// if(this.addOnList1!=null)
// {
//   for(let j=0;j<this.addOnList1.length;j++)
//   {
//     this.addOnList1[j].subItemId=this.subItemId1;
//   }
// }
console.log(this.addOnList1);
   console.log(this.subItemId1);
    console.log( this.questionsBean.length);
    for (let l = 0; l < this.questionsBean.length; l++) {
      this.dboClass = new dboClass();
      //To push the selected dropdown values in the panel
      //OtherColValFlag and addoncostmeflag will be zero
      //As the default values will be set in the dropdown
      for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
        if (this.questionsBean[l].dropDownValueList[d].value == this.newDefaultValues[l]) {
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = this.itemId;
          this.dboClass.itemName = null;
         this.dboClass.subItemId = this.subItemId1;
          this.dboClass.subItemName = null;
          this.dboClass.colId = this.questionsBean[l].dropDownType.key;
          this.dboClass.colNm = null;
          this.dboClass.colValCd = this.questionsBean[l].dropDownValueList[d].code; 
          this.dboClass.quantity = null;         
          this.dboClass.cost = null;          
          this.dboClass.techRemarks = null;
          this.dboClass.comrRemarks = this.newRemarksAdd;
          this.dboClass.addOnFlg = 0;
          this.dboClass.techFlag = 1;
          this.dboClass.comrFlag = 1;
          this.SelectedExcelData.push(this.dboClass);
        }
      }
    }
    let addOnList=this.addOnList1;
    console.log(addOnList);
    if(!(addOnList==null))
    {
    for(let c=0; c<addOnList.length; c++){
      if(addOnList[c] != null){
      this.SelectedExcelData.push(addOnList[c]);
      }
    }
  }
  
    this.dboFormData.mechAuxTechData = this.SelectedExcelData;
    this.dboFormData.quantity = this.newQty;
    this.dboFormData.mechAuxItemTechRemarks = null;
    this.dboFormData.mechAuxItemComrRemarks =  this.newRemarksAdd;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.discountPer = this.newDiscountPer;
    console.log(this.dboFormData);

    this._DboMechAuxialriesService.getMechAuxTechPrice(this.dboFormData).subscribe(res => {
    console.log(res);
   // this.techPriceResp = res;
    this.dboCost = res.basicCost;
    if(this.dboCost !=0){
      this.dispSCFM=false;
    }else{
      this.dispSCFM=true;
    }
    this.dboAddOnCost=res.mechAddOnCost;
    this.itemcost1=res.mechAuxTechList[0].itemCost;
    if (res.successCode == 0 || res.successCode == -1 ) {
     
      this.enableAdd = false;
    }

    console.log(this.addOnList1);
    for(let j=0;j<res.mechAuxTechList.length;j++)
    {
      if(res.mechAuxTechList[j].addOnNewColFlag==1)
      {
        this.addOnList3.push(res.mechAuxTechList[j]);
      }
    }
    this.errDisplayPnl=[];
    this.errMsgRhsCost=[];
    
    for(let i=0;i<=30;i++)
    {
      this.errMsgRhsCost.push([0]);
      this.errDisplayPnl.push([0]);
 
    }
    for(let j=0;j<20;j++)
    {
      this.errMsgRhsCost[this.subItemId1][j]=0;
      this.errDisplayPnl[this.subItemId1][j]=0;
    }
    for(let c=0;c< res.mechAuxTechList.length;c++){
      if((  res.mechAuxTechList[c].rhsCost > 0 && res.mechAuxTechList[c].lhsFlag == 0 && res.mechAuxTechList[c].addOnFlg == 0 )){
        this.errMsgRhsCost[res.mechAuxTechList[c].subItemId][res.mechAuxTechList[c].colId] = "AddOnCost: " + res.mechAuxTechList[c].rhsCost;
        this.errDisplayPnl[res.mechAuxTechList[c].subItemId][res.mechAuxTechList[c].colId] = 1;
      }
    }
    this.defaultValuesTemp[this.subItemId1]=[];
    for(let j=0;j< this.newDefaultValues.length;j++)
{
  this.defaultValuesTemp[this.subItemId1].push( this.newDefaultValues[j]);
}
    console.log(this.addOnList3);
    if (this.dboMechFullArray.length != 0) {
      for (let d = 0; d < this.dboMechFullArray.length; d++) {
        let j = this.dboMechFullArray.map(d => { return d.id }).indexOf(this.mechItemId);
        if (j != (-1) && (this.subItemId1 == 1)) {
          console.log("test123new");
          this.dboMechFullArray[j] = {
            quotId: res.quotId,
            qty: res.quantity,
            discountPer: res.discountPer,
            id: this.mechItemId,
            componenet: this.itemName,
            defaultValues: this.newDefaultValues,
            dboCost: res.basicCost,
            dboAddOnCost: this.dboAddOnCost,
            techComments: res.techComments,
            comrComments: res.comrComments,
            techRemarks: res.techRemarks,
            comrRemarks : res.mechAuxTechList[0].rhsColComrcomments,
            mechItemTechRemarks: res.mechItemTechRemarks,
            mechItemComrRemarks: res.mechItemComrRemarks,
            addOnList: [],
            itemOthersList: this.itemOthersAddonList,
            itemId: this.itemId,
            itemName: this.itemName,
            subItemId: this.subItemId1,
            subItemName: null,
            errMsgRhsCost:this.errMsgRhsCost,
            errDisplayPnl:this.errDisplayPnl,
            errMsgRhsCost1:this.errMsgRhsCost1,
            errDisplayPnl1:this.errDisplayPnl1,
            itemcost:this.itemcost1
          };
          break;
        }
        else {
          console.log("test123notnewinmoveitem");
          this.moveItem = true;
          break;
        }
      }
      if (this.moveItem == true) {
        console.log("test123notnewmoveitemistrue");
        this.dboMechFullArray.push({
          quotId: res.quotId,
          qty:res.quantity,
          discountPer: res.discountPer,
          id: this.mechItemId,
          componenet: this.itemName,
          defaultValues: this.newDefaultValues,
          dboCost: res.basicCost,
          dboAddOnCost: this.dboAddOnCost,
          techComments: res.techComments,
          comrComments: res.comrComments,
          techRemarks: res.techRemarks,
          comrRemarks : res.mechAuxTechList[0].rhsColComrcomments,
          mechItemTechRemarks: res.mechItemTechRemarks,
          mechItemComrRemarks: res.mechItemComrRemarks,
          addOnList: [],
          itemOthersList: this.itemOthersAddonList,
          itemId: this.itemId,
          itemName: this.itemName,
          subItemId: this.subItemId1,
          subItemName: null,
          errMsgRhsCost:this.errMsgRhsCost,
          errDisplayPnl:this.errDisplayPnl,
          errMsgRhsCost1:this.errMsgRhsCost1,
          errDisplayPnl1:this.errDisplayPnl1,
          itemcost:this.itemcost1
        });
      }
    }
    else {
      console.log("testinelse");
      //adding the values to dboMechFullArray if it wont contains particular item in its list
      this.dboMechFullArray.push({
        quotId: res.quotId,
        qty: res.quantity,
        discountPer: res.discountPer,
            id: this.mechItemId,
            componenet: this.itemName,
            defaultValues: this.newDefaultValues,
            dboCost: res.basicCost,
            dboAddOnCost: this.dboAddOnCost,
            techComments: res.techComments,
            comrComments: res.comrComments,
            techRemarks: res.techRemarks,
            comrRemarks : res.mechAuxTechList[0].comrRemarks,
            mechItemTechRemarks: res.mechItemTechRemarks,
            mechItemComrRemarks: res.mechItemComrRemarks,
            addOnList: [],
            itemOthersList: this.itemOthersAddonList,
            itemId: this.itemId,
            itemName: this.itemName,
            subItemId: this.subItemId1,
            subItemName: null,
            errMsgRhsCost:this.errMsgRhsCost,
            errDisplayPnl:this.errDisplayPnl,
            errMsgRhsCost1:this.errMsgRhsCost1,
            errDisplayPnl1:this.errDisplayPnl1,
            itemcost:this.itemcost1
      });
    }
    this.saveBtColorArray[this.subItemId1]=true;
    console.log(this.dboMechFullArray);
    // for(let j=0;j<this.dboMechFullArray.length;j++)
    // {
    //   if(this.dboMechFullArray[j].subItemId==this.subItemId1){
    //   for(let i=0;this.dboMechFullArray[j].addOnList;i++)
    //   {
    //     this.addontemp.push(this.dboMechFullArray[j].addOnList);
    //   }
    // }
    // }

   console.log(this.addontemp);
    //  this.newAddNameO2[this.subItemId1][d.]="";
    //  this.newAddQtyO2[this.subItemId1]="";
    //  this.newAddCostO2[this.subItemId1]="";
    //  this.newAddRemrkO2[this.subItemId1]="";
    //  this.newAddComRemrkO2[this.subItemId1]="";
   
    console.log(this.dboMechFullArray.length);
    //openOth2[(i+1)*(d.subItemId+1)] && openOth3[d.subItemId]
    // for(let i=0;i<this.dboMechFullArray.length;i++)
    // {
    //   if(this.dboMechFullArray[i].addOnList!=null){
    //   for(let j=0;j<this.dboMechFullArray[i].addOnList.length;j++)
    //   {
    //     this.subid=this.dboMechFullArray[i].addOnList[j].subItemId;
    //     this.colid=this.dboMechFullArray[i].addOnList[j].colId;
    //    this.arraycheck[this.subid][this.colid-1]=1;
    //    // console.log( this.openOth3);
    //     //console.log(this.colid*(this.subid+1));
    //     //this.openOth2[this.colid*(this.subid+1)];
    //     this.newAddNameO2[this.subid][this.colid-1]=this.dboMechFullArray[i].addOnList[j].colValCd;
    //     this.newAddQtyO2[this.subid][this.colid-1]=this.dboMechFullArray[i].addOnList[j].colValCd;
    //     this.newAddCostO2[this.subid][this.colid-1]=this.dboMechFullArray[i].addOnList[j].colValCd;
    //     this.newAddRemrkO2[this.subid][this.colid-1]=this.dboMechFullArray[i].addOnList[j].colValCd;
    //     this.newAddComRemrkO2[this.subid][this.colid-1]=this.dboMechFullArray[i].addOnList[j].colValCd;
        
        
    //   }
    // }
    // }
    console.log(this.subItemId1);
    
    

    this.saveInLocal(this.dboMechAuxLoc, {
      dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
dboMechFullArray1: this.dboMechFullArray1,
itemIdList: this.itemIdList, 
itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
finalMechCost: this.finalMechCost,
lhsRhsItemsList:this.lhsRhsItemsList,
itemOthersAddonList:this.itemOthersAddonList,addonflg:this.addonflg
    });
  
    this.itemIdList.push(this.itemId);

   
   // this.checkDisplay();
    this.dispNewComp = false;
  });
  this.mechid1=this.mechItemId;
  this.addOnList1=[];
  console.log( this.addOnList1);
  }
 


  getOldPriceExel(data)
  {
    this.mainSave=true;
    this.mainSave2=true;
    this.getPriceOld[data.subItemId]=false;
   for(let j=0;j<this.questionsBean.length;j++)
   {
    if(this.AddOnFlagNew[data.subItemId][this.questionsBean[j].dropDownType.key]=="1")
    { 
    if(this.openbtn2[data.subItemId][this.questionsBean[j].dropDownType.key]==0 || this.openbtn2[data.subItemId][this.questionsBean[j].dropDownType.key]=="0" )
  {
    this.getPriceOld[data.subItemId]=true;

  }
}
    }
    
    if(!this.getPriceOld[data.subItemId] )
    {
          this._ITOeditQoutService.dboMechAuxData=[];
     //this.enableAdd=false;
     this.addOnList13=[];
     this.SelectedExcelData=[];
   this.moveItem = false;
   this.dboCost = 0;
  //this.dboMechFullArray=[]
  // this.subItemId=this.subItemId+1;
    console.log( this.questionsBean.length);
    let addonlist12;
    for(let j=0;j<this.dboMechFullArray.length;j++)
    {
      if(this.dboMechFullArray[j].subItemId==data.subItemId && this.dboMechFullArray[j].itemId==data.itemId )
      {
        addonlist12=this.dboMechFullArray[j].addOnList;
        break;
      }
    }
    this.addoncheck=[];
  this.addoncheck =  addonlist12.filter((x) => {
    return ((x.addOnCost==undefined));
  })
  if(this.addoncheck.length>0)
  {
    this.SelectedExcelData=this.addoncheck;
  }
    if(!(addonlist12==null))
    {
    for(let j=0;j<addonlist12.length;j++)
    {
      if(addonlist12[j].addOnCost>0)
      {
      this.dboClass = new dboClass();
      this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboClass.itemId = addonlist12[j].itemId;
      this.dboClass.itemName = null;
      this.dboClass.subItemId = addonlist12[j].subItemId;            
      this.dboClass.subItemName = null;
      this.dboClass.colId = addonlist12[j].colId;
      this.dboClass.colNm = null;
      this.dboClass.colValCd = addonlist12[j].colValCd;
      this.dboClass.quantity = null;
      //uncomments after css changes
     // this.dboClass.quantity =this.newAddQtyO[index] ;         
      this.dboClass.cost = addonlist12[j].rhsCost;          
      this.dboClass.techRemarks =null ;
      this.dboClass.comrRemarks = addonlist12[j].rhsColComrcomments;
      this.dboClass.addOnFlg = 1;
      this.dboClass.techFlag = 1;
      this.dboClass.comrFlag = 1;
      this.SelectedExcelData.push(this.dboClass);
      }
      
    }
  }
  
    console.log(addonlist12);

   
   
  
  

let defaulttemp = [];
for(let j=0;j<this.defaultValuesTemp[data.subItemId].length;j++)
{
  defaulttemp.push(this.defaultValuesTemp[data.subItemId][j]);
}

    for (let l = 0; l < this.questionsBean.length; l++) {
      this.dboClass = new dboClass();
      //To push the selected dropdown values in the panel
      //OtherColValFlag and addoncostmeflag will be zero
      //As the default values will be set in the dropdown
      for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
        if (this.questionsBean[l].dropDownValueList[d].value == defaulttemp[l]) {
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = data.itemId;
          this.dboClass.itemName = null;
         this.dboClass.subItemId = data.subItemId;
          this.dboClass.subItemName = null;
          this.dboClass.colId = this.questionsBean[l].dropDownType.key;
          this.dboClass.colNm = null;
          this.dboClass.colValCd = this.questionsBean[l].dropDownValueList[d].code; 
          this.dboClass.quantity = data.qty;         
          this.dboClass.cost = null;          
          this.dboClass.techRemarks = null;
          this.dboClass.comrRemarks = data.comrRemarks;
          this.dboClass.addOnFlg = 0;
          this.dboClass.techFlag = 1;
          this.dboClass.comrFlag = 1;
          this.SelectedExcelData.push(this.dboClass);
        }
      }
    }

 
    
    this.dboFormData.mechAuxTechData = this.SelectedExcelData;
    this.dboFormData.quantity = data.qty;
    this.dboFormData.mechAuxItemTechRemarks =null;
    this.dboFormData.mechAuxItemComrRemarks = data.remrk;
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.discountPer = data.discountPer;
    console.log(this.dboFormData);
    this._DboMechAuxialriesService.getMechAuxTechPrice(this.dboFormData).subscribe(oldres => {
      console.log(oldres);
     // this.techPriceResp = res;
     data.addCost= oldres.mechAuxTechList[0].addOnCost;
      this.dboCost = oldres.basicCost;
      this.dboAddOnCost=oldres.mechAddOnCost;
      this.itemcost1=oldres.mechAuxTechList[0].itemCost;
      if (oldres.successCode == 0) {
       
        
      }
      for(let j=0;j<oldres.mechAuxTechList.length;j++)
      {
        if(oldres.mechAuxTechList[j].addOnNewColFlag==1)
        {
          this.addOnList5.push(oldres.mechAuxTechList[j]);
        }
      }
     
      for(let j=0;j<oldres.mechAuxTechList.length;j++)
      {
        if(oldres.mechAuxTechList[j].addOnNewColFlag==0)
        {
          this.addOnList13.push(oldres.mechAuxTechList[j]);
        }
      }

    //   if(!(this.addOnList5==null))
    //   {
    //   for(let i=0;i<this.dboMechFullArray.length;i++)
    //   {
    //     if(this.dboMechFullArray[i].subItemId==data.subItemId)
    //     {
    //       for(let j=0;j<this.dboMechFullArray[i].addOnList.length;j++)
    //       {
    //        for(let k=0;k<this.addOnList5.length;k++)
    //        {
    //          if(this.dboMechFullArray[i].addOnList[j].colId==this.addOnList5[k].colId)
    //          {
    //           this.dboMechFullArray[i].addOnList.splice(j,1);
          
    //          }
          
            
    //        }
    //       }
    //     }
    //   }
    // }



  //   if(!(this.addOnList5==null))
  //   {
  //   for(let i=0;i<this.dboMechFullArray.length;i++)
  //   {
  //     if(this.dboMechFullArray[i].subItemId==data.subItemId)
  //     {
  //       for(let j=0;j<this.dboMechFullArray[i].addOnList.length;j++)
  //       {
  //         if(this.dboMechFullArray[i].addOnList[j].addOnFlg==0)
  //         {
  //          this.dboMechFullArray[i].addOnList.splice(j,1);
       
  //         }
  //       }
  //     }
  //   }
  // }
  let defaultvaluestemp=[];
  for(let j=0;j<this.dboMechFullArray.length;j++)
  {
    if ((this.dboMechFullArray[j].itemId == data.itemId) && (this.dboMechFullArray[j].subItemId == data.subItemId)) 
      {
        for(let k=0;k<this.dboMechFullArray[j].defaultValues.length;k++)
        {
           defaultvaluestemp.push(this.dboMechFullArray[j].defaultValues[k]);
        }
      }
  }
      // moving item to dboMechFullArray
      this.errDisplayPnl=[];
    this.errMsgRhsCost=[];
    
    for(let i=0;i<=30;i++)
    {
      this.errMsgRhsCost.push([0]);
      this.errDisplayPnl.push([0]);
 
    }
    for(let j=0;j<20;j++)
    {
      this.errMsgRhsCost[this.subItemId1][j]=0;
      this.errDisplayPnl[this.subItemId1][j]=0;
    }
   
    for(let c=0;c< oldres.mechAuxTechList.length;c++){
      if((  oldres.mechAuxTechList[c].rhsCost > 0 && oldres.mechAuxTechList[c].lhsFlag == 0  && oldres.mechAuxTechList[c].addOnFlg == 0)){
        this.errMsgRhsCost[oldres.mechAuxTechList[c].subItemId][oldres.mechAuxTechList[c].colId] = "AddOnCost: " + oldres.mechAuxTechList[c].rhsCost;
        this.errDisplayPnl[oldres.mechAuxTechList[c].subItemId][oldres.mechAuxTechList[c].colId] = 1;
      }
    }
      if (this.dboMechFullArray.length != 0) {
        for (let d = 0; d < this.dboMechFullArray.length; d++) {
          // let j = this.dboMechFullArray.map(d => { return (d.componenet && d.subItemId) }).indexOf(data.subItemId);
          //  console.log(j, d)
          console.log(this.dboMechFullArray[d].componenet, data.componenet)
          console.log(this.dboMechFullArray[d].subItemId, data.subItemId)
          if ((this.dboMechFullArray[d].componenet == data.componenet) && (this.dboMechFullArray[d].subItemId == data.subItemId)) {
           // this.addOnList5.push(this.dboMechFullArray[d].addOnList);   
            // if (j != (-1)) {
            console.log(1111111111, oldres.basicCost, d)
            this.dboMechFullArray[d] = {
              quotId: oldres.quotId,
              qty: oldres.quantity,
              discountPer: oldres.discountPer,
              id: this.mechItemId,
              componenet: this.itemName,
              defaultValues: defaulttemp,
              dboCost: oldres.basicCost,
              dboAddOnCost: oldres.mechAuxAddOnCost,
              techComments: oldres.techComments,
              comrComments: oldres.comrComments,
              techRemarks: oldres.techRemarks,
              comrRemarks : this.addOnList13[0].rhsColComrcomments,
              mechItemTechRemarks: oldres.mechItemTechRemarks,
              mechItemComrRemarks: oldres.mechItemComrRemarks,
              addOnList: this.addOnList5,
              itemOthersList: this.itemOthersAddonList,
              itemId: data.itemId,
              itemName: data.itemName,
              subItemId: data.subItemId,
              subItemName: null,
              errMsgRhsCost:this.errMsgRhsCost,
              errDisplayPnl:this.errDisplayPnl,
              errMsgRhsCost1:this.errMsgRhsCost1,
              errDisplayPnl1:this.errDisplayPnl1,
              itemcost:this.itemcost1

            };
            break;
          }
          // else {
          //   this.moveItem = true;
          //   break;
          // }
        }
        // if (this.moveItem == true) {
        //   console.log(2222222222222)
        //   this.dboMechFullArray.push({
        //     qty: data.qty,
        //     id: data.id,
        //     componenet: data.componenet,
        //     defaultValues: data.defaultValues,
        //     dboCost: oldpriceResp.price,
        //     addCost: oldpriceResp.additionalCost,
        //     remrk: oldpriceResp.compRemarks,
        //     subItemId: data.subItemId
        //   });
        // }
      }
      else {
        this.dboMechFullArray.push({
          quotId: oldres.quotId,
          qty: oldres.quantity,
          discountPer: oldres.discountPer,
          id: this.mechItemId,
          componenet: this.itemName,
          defaultValues: defaulttemp,
          dboCost: oldres.basicCost,
          dboAddOnCost: oldres.mechAuxAddOnCost,
          techComments: oldres.techComments,
          comrComments: oldres.comrComments,
          techRemarks: oldres.techRemarks,
          comrRemarks : this.addOnList13[0].rhsColComrcomments,
          mechItemTechRemarks: oldres.mechItemTechRemarks,
          mechItemComrRemarks: oldres.mechItemComrRemarks,
          addOnList: this.addOnList5,
          itemOthersList: this.itemOthersAddonList,
          itemId: data.itemId,
          itemName: data.itemName,
          subItemId: data.subItemId,
          subItemName: null ,
          errMsgRhsCost:this.errMsgRhsCost,
          errDisplayPnl:this.errDisplayPnl,
          errMsgRhsCost1:this.errMsgRhsCost1,
          errDisplayPnl1:this.errDisplayPnl1,
          itemcost:this.itemcost1
          
        });
      
      }
      console.log(this.dboMechFullArray);
      this.saveInLocal(this.dboMechAuxLoc, {
        dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
  dboMechFullArray1: this.dboMechFullArray1,
  itemIdList: this.itemIdList, 
  itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
  finalMechCost: this.finalMechCost,
  lhsRhsItemsList:this.lhsRhsItemsList,
  itemOthersAddonList:this.itemOthersAddonList,addonflg:this.addonflg
      });

      this.itemIdList.push(this.itemId);
      
      this.saveBtColorArray[data.subItemId]=true;

      //this.checkDisplay();
      this.addOnList1=[];
     
      for(let i=0;i<this.dboMechFullArray.length;i++)
    {
      if(this.dboMechFullArray[i].itemId==this.itemId)  
      {
      if(this.dboMechFullArray[i].addOnList!=null){
      for(let j=0;j<this.dboMechFullArray[i].addOnList.length;j++)
      {
        this.subid=this.dboMechFullArray[i].addOnList[j].subItemId;
        this.colid=this.dboMechFullArray[i].addOnList[j].colId;
       this.arraycheck[this.subid][this.colid]=1;
      // this.openbtn2[this.subid][this.colid]=1;
       // console.log( this.openOth3);
        //console.log(this.colid*(this.subid+1));
        //this.openOth2[this.colid*(this.subid+1)];
        this.newAddNameO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].colValCd;
        this.newAddQtyO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].colValCd;
        if(this.dboMechFullArray[i].addOnList[j].rhsColComrcomments==null || this.dboMechFullArray[i].addOnList[j].rhsColComrcomments=="" )
        {
          this.newAddCostO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].cost;
          this.newAddComRemrkO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].comrRemarks;

        }
        else
        {
          this.newAddCostO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].rhsCost;
          this.newAddComRemrkO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].rhsColComrcomments;

        }

        this.newAddRemrkO2[this.subid][this.colid]=this.dboMechFullArray[i].addOnList[j].colValCd;
        
        
      }
    }
  }
    }
     // this.dispNewComp = false;
    
      if(this.dispNewComp==false)
      {
        this.enableAdd = false;
      }
     

     



    });
    //this.mechid1=this.mechItemId;
    this.addOnList5=[];
  }
  }












 calcTotal() {
   
  this.hideprogressCost1 = true;
 
  this.saveCalcTotal();
  //this.finalMechCost = 0;
  // console.log(this.finalAddGenListArray, this.dboMechFullArray, this.othersAddonList);
  // this.finalAddGenListArray = this.finalAddGenListArray.filter((x) => {
  //   return (x != null);
  // })
}
// disableOverWrite() {
//   this.enableOverwriteDiv = false;
//   this.dboFormData.overwrittenPriceFlag = 0;
//   this.dboFormData.price = this.dboCost;
//   this.disableStatus = false;
//   this.remarks = "";
//   this.OverWrittenfinalEleCost = 0;
// }
savePriceExel( othersItemfrm: NgForm,othersItem) {
  //this._ITOeditQoutService.dboMechAuxData=[];
  this.message = false;
  if (this.enableOverwriteDiv) {
    this.dboFormData.overwrittenPrice = this.OverWrittenfinalMechCost;
    this.dboFormData.overwrittenPriceFlag = 1;
    this.dboFormData.remarks = this.remarks;
  } else if (this.OverWrittenfinalMechCost > 0) {
    this.dboFormData.overwrittenPrice = this.OverWrittenfinalMechCost;
    this.panelList.overwrittenPriceFlag = 1;
  }
  this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
  this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboFormData.techComments = this.itemRemarksVal;
  this.dboFormData.comrComments = this.itemCmrRemarksVal;
  this.dboFormData.mechAuxilaryTechData=this.lhsRhsItemsList;
  this.dboFormData.mechAuxItemIdList = this.itemIdList.filter(n => n != null);
  this.dboFormData.quantity=null;
 // this.dboFormData.mechAuxilaryTechData=this.dboMechPanelList;
  console.log(this.itemIdList);
  console.log(this.dboFormData);
  this._DboMechAuxialriesService.saveMexhAuxItem(this.dboFormData).subscribe(savedResp => {
    console.log(savedResp);
    if (savedResp.successCode == 0) {
      this.message = true;
      this.mainSave=false
      this.successMsg = "Cost Saved successfully";
      // calling scope of supply complete endpoint to update that dbo electrical data has been saved
      let sos = this.storage.get(this.scopeofsupp);
      for (let s = 0; s < sos.length; s++) {
        if (sos[s].scopeCode == 'MECH') {
          this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
          this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
          this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
            console.log(res);
          })
        }
      }
      //call one line BOM
      this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
        console.log(resOnline);
        if (resOnline.successCode == 0) {
          this.finalMechCost = resOnline.oneLineBomExcel.dboMechAuxCost;
        } 
        this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
        this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, finalMechCost: this.finalMechCost});
      if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
          this._ITOcustomerRequirementService.editFlag = false;
          this.router.navigate(['/EditQuot']);
        }else{
        if(this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
          this.router.navigate(['/CostEstimation/Techinal/ItoPerformance']);
        }else{
          this.router.navigate(['/CostEstimation/Mechanical/MechExtendedScope']);
        }
      }
      });
    } else {
      this.message = true;
      this.successMsg = savedResp.successMsg;
    }
  });
  this.saveInLocal(this.dboMechAuxLoc, {
    dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
    dboMechFullArray1: this.dboMechFullArray1,
    itemIdList: this.itemIdList, 
    itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    finalMechCost: this.finalMechCost,
    lhsRhsItemsList:this.lhsRhsItemsList,
    itemOthersAddonList:this.itemOthersAddonList,addonflg:this.addonflg
  });

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
  this.OverWrittenfinalMechCost = 0;
}
saveCalcTotal(){
  //this._ITOeditQoutService.dboMechAuxData=[];
  console.log(this.itemIdList);
  if (this.enableOverwriteDiv) {
        this.dboFormData.overwrittenPrice = this.OverWrittenfinalMechCost;
        this.dboFormData.overwrittenPriceFlag = 1;
        this.dboFormData.remarks = this.remarks;
      } else if (this.OverWrittenfinalMechCost > 0) {
        this.dboFormData.overwrittenPrice = this.OverWrittenfinalMechCost;
        this.panelList.overwrittenPriceFlag = 1;
      }
      this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails; 
      this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      this.dboFormData.techComments = this.itemRemarksVal;
      this.dboFormData.comrComments = this.itemCmrRemarksVal;
      this.dboFormData.mechAuxilaryTechData=this.lhsRhsItemsList;
      this.dboFormData.mechAuxItemIdList = this.itemIdList.filter(n => n != null);
      console.log(this.itemIdList);
      console.log(this.dboFormData);
      this._DboMechAuxialriesService.saveMexhAuxItem(this.dboFormData).subscribe(savedResp => {
        console.log(savedResp);
        this.respSaveItem = savedResp;
        if(savedResp.successCode == 0){
          this.finalMechCost=savedResp.mechAuxTotalPrice;
          this.finalCostBool = true;
          this.mainSave2=false;

        }
        this.saveInLocal(this.dboMechAuxLoc, {
          dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
    dboMechFullArray1: this.dboMechFullArray1,
    itemIdList: this.itemIdList, 
    itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    finalMechCost: this.finalMechCost,
    lhsRhsItemsList:this.lhsRhsItemsList,
    itemOthersAddonList:this.itemOthersAddonList,addonflg:this.addonflg
        });
        
         //call one line BOM
         this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
          console.log(resOnline);
          if (resOnline.successCode == 0) {
            this.finalMechCost = resOnline.oneLineBomExcel.dboMechAuxCost;
          } 
          // this.finalMechCost= savedResp.totalPrice;
          this.saveInLocal(this.dboMechAuxLoc, {
            dboMechFullArray: this.dboMechFullArray, addOnList: this.addOnList, 
            dboMechFullArray1: this.dboMechFullArray1,
            itemIdList: this.itemIdList,
           
            itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
            
            finalMechCost: this.finalMechCost
            });            
          this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
       this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, finalMechCost: this.finalMechCost});
          // if (this._ITOcustomerRequirementService.editFlag) {
          //   this._ITOcustomerRequirementService.editFlag = false;
          //   this.router.navigate(['/EditQuot']);
          // }
        });
        this.hideprogressCost1 = false;
      });
  }
    optionSel(options, selVal, i,k) {
      if(k==1)
      {
        this.saveBtColor=true;

      }
      else
      {
        this.saveBtColorArray[k]=false; 

      }
      this.mainSave2=true;
      this.mainSave=true;
      console.log(options, selVal, i);
      this.newd=selVal;
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
  ngOnInit() {
  }
  onKeyData120(i)
  {
    this.saveBtColorArray[i]=false;
    this.mainSave2=true;
    this.mainSave=true;

  }
  onKeyData1(value,remark)
  {
    this.saveBtColor=true;
    this.mainSave2=true;
    this.mainSave=true;
    if(this.uniqueCode=="VACC_BVL" ||  this.uniqueCode=="FILL_FLU" || this.uniqueCode=="OIL_CENTRIFUGE" || this.uniqueCode=="OV_TANK" )

    {
      this.enableGetPriceBtn=false;
    }
    else if (remark==null || remark=="NULL" || remark==""  )
    {
      this.enableGetPriceBtn=true;
    }
    else if(remark!=null && remark!="NULL" && remark!="" )
    {
      this.enableGetPriceBtn=false;
    }
  }
 //To navigate edit quotaion page on click of back button
 backButton(){
  this.router.navigate(['/EditQuot']);
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
