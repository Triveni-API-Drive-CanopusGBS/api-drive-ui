import { Component, OnInit, AfterViewChecked, AfterContentChecked } from '@angular/core';
import { DBOMechanicalComponentService } from './dbomechanical.service';
import { dboClass } from './dbomechanical';
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
import { Dialog } from 'primeng/dialog';

@Component({
  selector: 'app-dbomechanical',
  templateUrl: './dbomechanical.component.html',
  styleUrls: ['./dbomechanical.component.css']
})
export class DBOMechanicalComponent implements OnInit {

  subItemcst: number= 0;
  othCheck: boolean = false;
  comrrCheck: boolean = false; //boolean to check commercial remarks
  techCheckIn: boolean = false; //boolean to check techinal remarks
  dboFormData: any; //Storing respone from getDboFormData
  mechItemss: any; //Storing respone from getMechItems
  mechItemList: Array<any> = [];
  newArray: Array<any> = [];
  displayItemCompOthTable:boolean = false;
  panelList: any; //Storing response from getMechPanel
  questionsBean:Array<any> = []; 
  dispSCFM:boolean =false;
  alert:string="                     !!!!!ALERT!!!!";
  ibreakothers: number = 0;
  questionsBean1:Array<any> = [];
  AddOnFlag: Array<boolean> = []; // To enable ADdon button based on flag
  defaultValues: Array<any> = [];
  itemOthersAddonList:Array<any> = [];
  displayDialogLhsRhs:boolean = false;
  lhsRhsnewLine:boolean = false;
  lhsRhsItemSel:string = '';
  lhsRhsItemsList:Array<any> = [];
  subList1: Array<any> = [];
  sublistDup: Array<any> = [];
  dboMechFullArray3: Array<any> = []; //store complete data from getMechTechPrice
  mechItemId: number; //store unique id that is mechItemId on selection of item
  itemName: string; //to dispaly item name for dialog header
  subItemName: string; //store subItemName on selection of subItem
  itemId: any; //store itemId on selection of item
  subItemId: any; //store subItemId on selection of subItem
  displayMechSubList: boolean = false;
  displayF2f: boolean = false; 
  selectdEl: Array<boolean> = [];
  finalItem: string; //Dialog header
  qty: number = 1; //Quantity for panel
  discountPer: number = 0;
  mechItemTechRemarks: string; //Techinal remarks for inside the item panel
  mechItemComrRemarks: string //Commercial remarks for inside the item panel
  itemTechRmkDiv: boolean = false; //boolean to display input for techinal remaks inside the item panel
  itemComrRmkDiv: boolean = false; //boolean to display input for commercial remarks inside the panel
  dboClass: dboClass;
  SelectedExcelData: Array<any> = [];
  costNotAvailableError: any;
  errorArray: Array<any> =[];
  openOth:Array<boolean> = []; //To add Rhs for existing lhs
  dboCost: number; //basiccost from getMechTechPrice to display in UI Pannel cost
  dboBasicCost: number; //basiccost from getMechTechPrice
  techPriceResp:any; //storing respone from getMechTechPrice
  colValNmId: number; //store col Id
  addOnList: Array<any> = []; //store addon list
  addOnList1: Array<any> = []; //store addon list
  addOnList2: Array<any> = []; //store addon list
  dboMechPanelList:Array<any> = [];// store dboMechPanelList1 from getMechPanel
  itemOthersList:Array<any> = []; //store other attributes (new Lhs and Rhs) inside the panel
  tempitemOthersList:Array<any> = []; //store other attributes (new Lhs and Rhs) inside the panel
  displayCompOthTable: boolean = false; //open others attribute to add new LHS and RHS
  displayOthnewLine:boolean = false; //open others attribute to enter new LHS and RHS values
  selectedELIndex: number; //selected panel index to perform check and uncheck
  selectdEl1: Array<boolean> = [];
  addOnCheck: Array<boolean> = [];
  itemRemarkaddon: Array<boolean> = [];
  itemIdList: Array<number> = [];
  itemIdList1: Array<number> = [];
  hideprogressCost1: boolean = false;
  hideprogressCost: boolean = false;
  message: boolean = false; //To display message
  enableOverwriteDiv: boolean = false; //To enable overwritten cost
  OverWrittenfinalMechCost: number = 0; //overwritten cost
  totalMechCost: number = 0;
  remarks:string;
  itemRemarkDiv:boolean = false; //to Display item remarks inputfield to enter techinal remarks
  itemRemarksVal:any; //to store input data of item techinal remarks 
  itemCmrRemarksVal:any; //to store input dat of item commercial remarks
  itemComrRemarkDiv: boolean = false; //to display item remarks inputfiels to enter commercial remarks
  subItemRemarkDiv:boolean = false; //to display subItem remarks inputfield to enter techinal remarks
  subItemRmkValOut: any; //to store input data of subitem techinal remarks
  subItemCmrRemValOut: any; // to store input data of subitem commercial remarks
  subItemComrRemDiv: boolean = false; //to dispaly subitem remarks inputfield to enter commercial remarks
  successMsg: string = '';
  scopeofsupp: string = 'scopeOfsup';
  oneLineLoc: string = 'oneLineLoc';
  finalMechCost: number = 0;
  dboMechanicalLocal: Array<any> = [];
  dboMechLoc: string = 'dboMech'; // local storage value
  dboMechFull: string = 'dboMechFull'; // local storage value
  respSaveItem: any; //store respone from saveMechItem
  dboAddOnCost:any; //store mech addon cost from getMechTecPrice
  subMessage: boolean = false;
  messageVal:string;
  addedClassList: Array<any> = [];
  iFlag : number = 0;
  index: number = 0;
  count : number = 0;
  itemtempId : number = 0;
  dboMechPanelList2: Array<any> = [];
  flowRate: any;
  cep:any;
  power: any;
  subItemTchRem: boolean = false;
  subItemComRem: boolean = false;
 displayItmOthnewLine: boolean =  false;
newAddNameO:Array<any>=[];
newAddQtyO:Array<any>=[];
newAddCostO:Array<any>=[];
newAddRemrkO:Array<any>=[];
newAddComRemrkO:Array<any>=[];
othersCompCheck: boolean = false; //flag to make others check box (itemOthersList)
iItemindex: number = 0;
openOth1:Array<boolean> = []; //To add Rhs for existing lhs
itemSelectedListEdit:Array<any> = []; //To add Rhs for existing lhs
subitemSelectedListEdit:Array<any> = []; //To add Rhs for existing lhs
disableStatus:boolean=false;


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
tempaddonitem: Array<any> = [];
tempaddonsubitem: Array<any> = [];
tempaddonrhs: Array<any> = [];
newSet: Array<any> = [];
prevData: Array<any> = [];
othItmTechRemChk:boolean=false;
itemtechRemarkCheck:boolean=false;
othItmComrRemChk:boolean=false;
itemComrRemarkCheck:boolean=false;
othSubItmTechRemChk:boolean=false;
othSubItmComrRemChk:boolean=false;
temparray1: Array<any> = [];
rhs: Array<boolean> = [];
defaultValues1: Array<any> = [];
finalcostflag:boolean=false;
addonflg="0";
oneLineLocArray: any;
errDisplayPnl:  Array<boolean> = []; //To Display error msg div if error msg is not equal to null on hitting getEletechPrice
errDisplayRhsCost: Array<boolean> = []; //To Display error msg div if addOnflg is 1 on hitting getEletechPrice
errMsgRhsCost: Array<any> = []; //To display error msg if addonflg is 1 on hitting getEleTechPrice
itemCost:number=0;
backBtn: boolean = false;
saveBtColor:boolean=true;
buttonColor:string="rgb(213,120,23)";
finalCostBool: boolean = false;
disablelhs: Array<boolean> = [];
getPrice:boolean=true;
user: string = 'userDetail';
currentRole: string = 'selRole';
currentRoleId: string = 'selRoleId';
rewApp: boolean = false;
mainSave:boolean=true;
mainSave2:boolean=true;
appDisable: boolean = false;
diableitemname:boolean=true;
subItemSaveBt:boolean=true;


  constructor(private _DBOMechanicalComponentService: DBOMechanicalComponentService, private _ITOLoginService: ITOLoginService,
    private _ITOturbineConfigService: ITOturbineConfigService, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private _ITOeditQoutService: ITOeditQoutService,
    private _ITOCostEstimationService: ITOCostEstimationService, private router: Router) {


      	
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
       this._ITOeditQoutService.button5=true;
       this._ITOeditQoutService.button6=false;
       this._ITOeditQoutService.button7=false;
       this._ITOeditQoutService.button8=false;
       this._ITOeditQoutService.button9=false;
      //To set to null in the beginning of the edit mode
      this.selectdEl = [];
      this.selectdEl1 = [];
      this.itemIdList = [];
      this.itemIdList1 = [];
      this.flowRate=0;
      this.cep=0;
      this.power=0;

      
      if(this._ITOeditQoutService.checkEdit == false){
        this.backBtn = true;
      }
      if (this._ITOcustomerRequirementService.saveBasicDet != undefined){
      if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
        this.appDisable=true;
      }else{
        this.appDisable=false;
      }
    }
       //get dbo form
        this.temparray1=[];
       console.log(this._ITOeditQoutService.dboMechData);
       
       if( this._ITOcustomerRequirementService.editFlag==true)
       {
        this.temparray1=this._ITOeditQoutService.dboMechData;
       }
if( this.temparray1.length!=0)
{
  this.mainSave=false;
  this.mainSave2=false;
}
       this._ITOturbineConfigService.getDboFormData().subscribe(responn => {
        console.log(responn);
        this.dboFormData = responn;
        console.log(this.storage.get(this.oneLineLoc));
        this.dboMechanicalLocal[this.dboMechLoc] = this.storage.get(this.dboMechLoc);
        console.log(this.storage.get(this.dboMechLoc));
         
          
      
        if (this.storage.get(this.dboMechLoc) == null) {
          this.saveInLocal(this.dboMechLoc, { 
            dboMechFullArray3: this.dboMechFullArray3, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
            itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
            selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
            itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
            subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
            mechItemTechRemarks: this.mechItemTechRemarks, mechItemComrRemarks: this.mechItemComrRemarks,
            finalMechCost: this.finalMechCost, subItemcst: this.subItemcst, totalMechCost: this.totalMechCost
          });
        }

        if(this.storage.get(this.dboMechLoc).dboMechFullArray3.length!=0){
          this.finalCostBool = true;
          this.dboMechFullArray3= this.storage.get(this.dboMechLoc).dboMechFullArray3;
          this.mainSave=false;
          this.mainSave2=false;
        
       }
       if(this.storage.get(this.dboMechLoc).addOnList.length!=0){
        this.addOnList= this.storage.get(this.dboMechLoc).addOnList;
      
     }
     if(this.storage.get(this.dboMechLoc).itemIdList.length!=0){
      this.itemIdList= this.storage.get(this.dboMechLoc).itemIdList;
    
   }
   if(this.storage.get(this.dboMechLoc).itemIdList1.length!=0){
    this.itemIdList1= this.storage.get(this.dboMechLoc).itemIdList1;
  
 }
 if(this.storage.get(this.dboMechLoc).selectdEl!=undefined){
  this.selectdEl= this.storage.get(this.dboMechLoc).selectdEl;

}
if(this.storage.get(this.dboMechLoc).selectdEl1!=undefined){
  this.selectdEl1= this.storage.get(this.dboMechLoc).selectdEl1;

}
     if(this.storage.get(this.dboMechLoc).itemRemarksVal!=null  && this.storage.get(this.dboMechLoc).itemRemarksVal != ""){
      this.itemRemarksVal= this.storage.get(this.dboMechLoc).itemRemarksVal;
      this.othItmTechRemChk=true;
     this.itemtechRemarkCheck=true;
     this.itemRemarkDiv=true;
    
   }
   if(this.storage.get(this.dboMechLoc).itemCmrRemarksVal!=null  && this.storage.get(this.dboMechLoc).itemCmrRemarksVal != ""){
    this.itemCmrRemarksVal= this.storage.get(this.dboMechLoc).itemCmrRemarksVal;
    this.othItmComrRemChk=true;
    this.itemComrRemarkCheck=true;
    this.itemComrRemarkDiv=true;
  
 }
 if(this.storage.get(this.dboMechLoc).subItemRmkValOut!=null  && this.storage.get(this.dboMechLoc).subItemRmkValOut != ""){
  this.subItemRmkValOut= this.storage.get(this.dboMechLoc).subItemRmkValOut;
  this.othSubItmTechRemChk=true;
  this.subItemTchRem=true;
  this.subItemRemarkDiv=true;

}
if(this.storage.get(this.dboMechLoc).subItemCmrRemValOut!=null && this.storage.get(this.dboMechLoc).subItemCmrRemValOut != ""){
this.subItemCmrRemValOut= this.storage.get(this.dboMechLoc).subItemCmrRemValOut;
this.othSubItmComrRemChk=true;
this.subItemComRem=true;
this.subItemComrRemDiv=true;
}

if(this.storage.get(this.oneLineLoc) != null){
this.oneLineLocArray = this.storage.get(this.oneLineLoc);
if(this.oneLineLocArray.finalMechCost != undefined){
this.finalMechCost = this.oneLineLocArray.finalMechCost;   
}else{
  this.finalMechCost = this.oneLineLocArray.dboMechCost;
}
this.totalMechCost = this.oneLineLocArray.totalMechCost;
}
if(this.oneLineLocArray.resOnline != null){
  this.finalMechCost = this.oneLineLocArray.resOnline.dboMechCost;
  this.totalMechCost = this.oneLineLocArray.resOnline.totalMechCost;
}
           //To set to null in the beginning of the edit mode
    //  this.selectdEl= [];
     // this.selectdEl1 = [];
      //this.selectdEl2 = [];
     // this.itemIdList = [];
      //this.itemIdList1 = [];
     

       //To write the logic to check the subitem ids in the main panel
     
    //  this.itemIdList2 = [];
       //this.dboFormData.framePowId = this._ITOcustomerRequirementService.saveBasicDet.framePowerId;
       this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;       
        //passing dbo form as input to getF2fItems
        this._DBOMechanicalComponentService.getMechItems(this.dboFormData).subscribe(repp => {
          console.log(repp);
          this.mechItemss = repp;
          this.mechItemList = this.mechItemss.dboMechItemList;
          //filter ItemNames
          this.newArray = this.mechItemList.reduce((acc, current) => {
            console.log(acc, current);
            const x = acc.find(item => item.itemName === current.itemName);
            if (!x) {
              return acc.concat([current]);
            } else {
              return acc;
            } 
          }, []);
          console.log(this.newArray);

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
            this.finalCostBool = true;
            this.itemIdList=[];
            this.itemIdList1=[];
            this.selectdEl1=[];
            this.selectdEl=[];
          }
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
          if(this.subitemSelectedListEdit.length != 0)
          {
             this.subList1=[];
             this.sublistDup =[];
               for (let s = 0; s < this.mechItemList.length; s++) {                
                 if(this.mechItemList[s].itemId && this.mechItemList[s].subItemName!=null){
                   this.sublistDup.push(this.mechItemList[s]);
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
                return ((x.lhsFlag ==1));
              })
              for(let j=0;j<this.tempaddonsubitem.length;j++)
              {
              this.dboClass = new dboClass();
              this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
              this.dboClass.itemId = this.tempaddonsubitem[j].itemId;
              this.dboClass.itemName = null;
              if(this.subItemId == 0 || this.subItemId == null){
                 this.dboClass.subItemId = 0;
                }else {
                  this.dboClass.subItemId =  this.tempaddonsubitem[j].subItemId;           
                }   
              this.dboClass.subItemName = null;
              this.dboClass.colId = 0;
              this.dboClass.colNm = this.tempaddonsubitem[j].colNm ;
              this.dboClass.colValCd =  this.tempaddonsubitem[j].colValCd; 
              this.dboClass.quantity =   this.tempaddonsubitem[j].rhsColQuantity;
              //uncomments after css changes
             // this.dboClass.quantity =this.newAddQtyO[index] ;         
              this.dboClass.cost =  this.tempaddonsubitem[j].rhsCost;          
              this.dboClass.techRemarks = this.tempaddonsubitem[j].rhsColTechcomments;
              this.dboClass.comrRemarks = this.tempaddonsubitem[j].rhsColComrcomments;
              this.dboClass.addOnFlg = 0;
              this.dboClass.techFlag = 1;
              this.dboClass.comrFlag = 1;
              this.itemOthersList.push(this.dboClass);
              }
              this.tempaddonrhs =  this.temparray1.filter((x) => {
                return ((x.addOnNewColFlag ==1));
              })
              for(let j=0;j<this.tempaddonrhs.length;j++)
              {
              this.dboClass = new dboClass();
              this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
              this.dboClass.itemId = this.tempaddonrhs[j].itemId;
              this.dboClass.itemName = null;
              if(this.subItemId == 0 || this.subItemId == null){
                 this.dboClass.subItemId =  this.tempaddonrhs[j].subItemId;
                }else {
                  this.dboClass.subItemId =  this.tempaddonrhs[j].subItemId;           
                }  
              this.dboClass.subItemName = null;
              this.dboClass.colId =  this.tempaddonrhs[j].colId;
              this.dboClass.colNm = null;
              this.dboClass.colValCd =  this.tempaddonrhs[j].colValCd; 
              this.dboClass.quantity = 1;
              //uncomments after css changes
             // this.dboClass.quantity =this.newAddQtyO[index] ;         
              this.dboClass.cost =  this.tempaddonrhs[j].rhsCost;          
              this.dboClass.techRemarks =  null;
              this.dboClass.comrRemarks =  this.tempaddonrhs[j].rhsColComrcomments;
              this.dboClass.addOnFlg = 1;
              this.dboClass.techFlag = 1;
              this.dboClass.comrFlag = 1;
              this.addOnList.push(this.dboClass);
              }
             if(this._ITOcustomerRequirementService.editFlag==true)
             {
              this.dboMechFullArray3 = [];
              this.newSet = [];
             //take the uniqe items names
              this.newSet = Array.from(new Set(this.temparray1.map((x) => {
                return x.subItemId;
              })));
              console.log(this.newSet);
              let newArr: Array<any> = [];
              console.log(this._ITOeditQoutService.dboMechData)
              for (let h = 0; h < this.newSet.length; h++) {
                for (let i = 0; i < this.temparray1.length; i++) {
                  if (this.newSet[h] == this.temparray1[i].subItemId) {
                    newArr.push(this.temparray1[i]);
                    break;
                  }
                }
              }
             // filtered array with all data
              console.log(newArr);
              if (newArr != []) {
                for (let m = 0; m < newArr.length; m++) {
                  this.errDisplayPnl=[];
                  this.errMsgRhsCost=[];
                    this.defaultValues1 = [];
                    if (this.temparray1.length != 0) {
                      this.prevData = this.temparray1.filter((x) => {
                        return ((x.itemId == newArr[m].itemId && x.subItemId == newArr[m].subItemId && x.dispInd==1 && x.addOnFlg==0 && x.lhsFlag==0));
                      })
                      for (let c = 0; c< this.prevData.length; c++) {
                        if( this.prevData[c].rhsCost == 1 && this.prevData[c].lhsFlag == 0){
                          this.errMsgRhsCost[this.prevData[c].colId] = "Actual Cost not Available. Get the same from SCM: " +this.prevData[c].rhsCost;
                     
                          this.errDisplayPnl[this.prevData[c].colId] = true;
                        }else{
                          if( this.prevData[c].rhsCost > 0 && this.prevData[c].lhsFlag == 0){
                            this.errMsgRhsCost[this.prevData[c].colId] = "AddOnCost: " +this.prevData[c].rhsCost;
                       
                            this.errDisplayPnl[this.prevData[c].colId] = true;
                          }
                        }
                       // this.errDisplayPnl[this.prevData[c].colId] = true;
                        console.log(this.prevData);
                      }
                      console.log(this.prevData);
                      if (this.prevData.length != 0) {
                        for (let d = 0; d < this.prevData.length; d++) {
                          this.defaultValues1.push(this.prevData[d].colValCd);
                        }
                      }
                      this.itemCost=this.prevData[0].itemCost;
                    }
               //pushing selected components data fromm storage to local variable
                  this.dboMechFullArray3.push({
                    id: newArr[m].mechItemId,
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
                    mechItemTechRemarks:  newArr[m].techRemarks,
                    mechItemComrRemarks:  newArr[m].comrRemarks,
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
                    errMsgRhsCost:this.errMsgRhsCost,
                    errDisplayPnl:this.errDisplayPnl,
                    itemCost:this.itemCost
                    
                  });
                  this.addedClassList.push(newArr[m].itemName);
                  console.log(this.dboMechFullArray3);
                  let savedMech: Array<any> = [];
                }
              }
            }
          }
          if(this._ITOcustomerRequirementService.editFlag==true)
          {
        
          
              if(this.dboMechFullArray3[0].techComments!=null)
              {
                this.itemRemarksVal= this.dboMechFullArray3[0].techComments;
                this.othItmTechRemChk=true;
               this.itemtechRemarkCheck=true;
               this.itemRemarkDiv=true;
              }
              if(this.dboMechFullArray3[0].comrComments!=null)
              {
                this.itemCmrRemarksVal= this.dboMechFullArray3[0].comrComments;
                this.othItmComrRemChk=true;
                this.itemComrRemarkCheck=true;
                this.itemComrRemarkDiv=true;
              }
              if(this.dboMechFullArray3[0].techRemarks!=null)
              {
                this.subItemRmkValOut= this.dboMechFullArray3[0].techRemarks;
                this.othSubItmTechRemChk=true;
                this.subItemTchRem=true;
                this.subItemRemarkDiv=true;
              }
              if(this.dboMechFullArray3[0].comrRemarks!=null)
              {
                this.subItemCmrRemValOut= this.dboMechFullArray3[0].comrRemarks;
                this.othSubItmComrRemChk=true;
                this.subItemComRem=true;
                this.subItemComrRemDiv=true;
              }
              
            
          
        }
        });
        
      });
  }
  cancelLinesOth(i) {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    // for(let j=0;j<  this.itemOthersList.length;j++)
    // {
    //   if(this.itemOthersList[j].itemId==this.itemId && this.itemOthersList[j].subItemId==this.subItemId )
    //   {
    //     this.itemOthersList[j]=null;
    //     this.itemOthersList.splice(j,1);
    //   }
    // }
    let name=this.itemOthersList[i].colValNm;
    let value=this.itemOthersList[i].colValCd;
    this.itemOthersList.splice(i, 1);
    this.disablelhs.splice(i, 1);
    for( let j=0;j<this.dboMechFullArray3.length;j++)
    {
      if(this.dboMechFullArray3[j].itemId==this.itemId && this.dboMechFullArray3[j].subItemId==this.subItemId)
      {
       
        if(this.dboMechFullArray3[j].itemOthersList.length!=0)
        {
          for(let i=0;i<this.dboMechFullArray3[j].itemOthersList.length;i++)
          {
            if(this.dboMechFullArray3[j].itemOthersList[i].itemId==this.itemId && this.dboMechFullArray3[j].itemOthersList[i].subItemId==this.subItemId && this.dboMechFullArray3[j].itemOthersList[i].colNm==name  && this.dboMechFullArray3[j].itemOthersList[i].colValCd==value )
            {
              this.dboMechFullArray3[j].itemOthersList[i]=null;
              this.dboMechFullArray3[j].itemOthersList.splice(i,1);
            }
          }
        }
      }
    }
  
 //   this.tempitemOthersList.splice(i, 1);
  }
  ngOnInit() {

  }
  dboMechItmList(event, itemId, i)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(event, itemId, i);
    if (!event.target.checked) { // on uncheck 
      if (this.itemIdList.includes(itemId)) {
        for(let j=0;j<this.itemIdList.length;j++)
        {
          if(this.itemIdList[j]==itemId)
          {
          this.itemIdList[this.itemIdList.indexOf(itemId)] = null;
          }
        }
        this.itemIdList[this.itemIdList.indexOf(itemId)] = null;
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
            }           
            break;
          }
        }
        this.selectdEl[i] = false; //To uncheck the selected item       
      }
      console.log(this.dboMechFullArray3);
      for(let k = 0; k < this.dboMechFullArray3.length; k++){
        if(this.dboMechFullArray3[k].itemId == itemId){
            this.dboMechFullArray3[k] = null;
            this.dboMechFullArray3.slice(k, 1);
          
        }
      }
      this.dboMechFullArray3 =  this.dboMechFullArray3.filter(n => n != null);
      console.log("after");
    console.log( this.dboMechFullArray3 );
      // for(let k = 0; k < this.tempitemOthersList.length; k++){
      //   if(this.tempitemOthersList[k].itemId == itemId){
      //       this.tempitemOthersList[k] = null;
      //       this.tempitemOthersList.splice(k, 1);
         
      //   }
      // }
      for(let k = 0; k < this.addOnList.length; k++){
        if(this.addOnList[k].itemId == itemId){
          this.rhs[ this.addOnList[k].colId]=false;
          this.errDisplayPnl[ this.addOnList[k].colId]=false;
            this.addOnList[k] = null;
            this.addOnList.slice(k, 1);
         
        }
      }
    }
    this.addOnList =  this.addOnList.filter(n => n != null);
    console.log("after");
    console.log(this.dboMechFullArray3)
    this.dboCost=0;
    this.selectdEl[i]=false;
  }
  //chech box for sub item
  dboF2fItmListSub(event, itemId, i){
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
            this.itemtempId = this.subList1[k].itemId;
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
              break;
            }
          }

        }
        
        // this.selectdEl2[i] = false;
      }
      for(let k=0; k<this.dboMechFullArray3.length; k++){
        if(this.dboMechFullArray3[k].subItemId == itemId){
          this.dboMechFullArray3[k] = null;
          this.dboMechFullArray3.splice(k,1);
          break;
        }
      }
    }
    console.log("after");
    console.log(this.dboMechFullArray3);
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
  dboMechSel(d,nm,i){
    this.diableitemname=false;
    this.getPrice=true;
    this.discountPer = 0;
    this.qty=1;
    this.disablelhs=[];
    this.saveBtColor=true;
    this.itemCost=0;
    this.techCheckIn=false;
    this.comrrCheck=false;
    this.itemTechRmkDiv=false;
    this.itemComrRmkDiv=false;
    this.addonflg="0";
    this.newAddNameO=[];
   // this.defaultValues=[];
    this.newAddRemrkO=[];
    this.newAddCostO=[];
    this.openOth=[];
    this.openOth1=[];
   // this.comrCheckkk=;
    this.comrrCheck=false;
   // this.techCheckkk=;
    this.techCheckIn=false;
    this.mechItemComrRemarks=null;
    this.mechItemTechRemarks=null;
   // this.subItemRmkValOut="";
    //this.subItemCmrRemValOut="";


    for (let k = 0; k < this.newArray.length; k++)
    {
      if(d.itemName == this.newArray[k].itemName)
      {
       this.iItemindex = k;          
        break;
      }
    }       
    console.log(this.selectdEl[i]);
    console.log(d,nm,i);
    this.displayCompOthTable=false;
    this.othCheck=false; 
    this.othersCompCheck=false;
  //  this.mechItemTechRemarks = '';
  //  this.mechItemComrRemarks = '';
   // this.subItemRmkValOut = '';
   // this.subItemCmrRemValOut = '';
   // this.techCheckIn = false;
   // this.comrrCheck = false;
   // this.itemTechRmkDiv = false;
    this.addOnCheck=[];
   // this.itemComrRmkDiv =  false;
    //this.subItemTchRem = false;
  //  this.subItemComRem = false;
  //  this.subItemRemarkDiv = false;
 //   this.subItemComrRemDiv = false;
    this.finalItem = nm;
    this.mechItemId = d.mechItemId;
    this.itemName = d.itemName;
    this.itemId = d.itemId;
    this.subItemId = d.subItemId;
    this.subItemName = d.subItemName;
    this.selectedELIndex=i;
    console.log(this.selectedELIndex);
    this.defaultValues = [];
    this.questionsBean = [];
   // this.itemIdList = [];
   // this.itemIdList1 = [];
  this.itemOthersList = [];
  this.dboCost = 0;
  this.dboAddOnCost=0;
 
//  for(let j=0;j<this.tempitemOthersList.length;j++)
//  {
//    if(this.tempitemOthersList[j].itemId==this.itemId && this.tempitemOthersList[j].subItemId==this.subItemId)
//    {
//     this.displayCompOthTable=true;
//     this.othCheck=true;
//     this.othersCompCheck=true;
//     this.itemOthersList.push(this.tempitemOthersList[j]);
//    }
//  }
 
  //  this.othCheck = false;
  //  this.othersCompCheck = false;
  //  this.displayCompOthTable = false;
  
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormData.itemId = d.itemId;
    this.dboFormData.subItemId = d.subItemId;
 
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
      this.subItemSaveBt=true;

      if(this.selectdEl1.includes(true))
{
  this.subItemSaveBt=false;

}

      this.subList1=[];
      this.sublistDup =[];
        for (let s = 0; s < this.mechItemList.length; s++) {                
          if(d.itemId == this.mechItemList[s].itemId && this.mechItemList[s].subItemName!=null){
            this.sublistDup.push(this.mechItemList[s]);
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
        this.displayMechSubList = true;
        if(this.storage.get(this.dboMechLoc).subItemcst != '' || this.storage.get(this.dboMechLoc).subItemcst != null){
          this.subItemcst = this.storage.get(this.dboMechLoc).subItemcst;
        }
        if(this.temparray1 != null)          {
          for(let v=0; v<this.temparray1.length; v++){
            if(this.temparray1[v].mechItemId == 0 && this.temparray1[v].itemId != 0 && this.temparray1[v].subItemId == 0){
              this.subItemcst = this.temparray1[v].itemCost;
            }
          }
        }
      }else{
        this.displayF2f = true;
        this.displayMechSubList = false;
      }
    }
    //To display panel dropdown list on click subitem list name
    if(nm == d.subItemName){
    
      this.subItemSaveBt=true;

      this.displayMechSubList = true;
      this.displayF2f = true;   
     
    }
    // if(this.storage.get(this.dboMechLoc).mechItemComrRemarks != null && this.storage.get(this.dboMechLoc).mechItemComrRemarks != ""){
    //   this.mechItemComrRemarks = this.storage.get(this.dboMechLoc).mechItemComrRemarks;
    //   this.comrrCheck = true;
    //   this.itemComrRmkDiv=true;
    // }   
    // if(this.storage.get(this.dboMechLoc).mechItemTechRemarks != null && this.storage.get(this.dboMechLoc).mechItemTechRemarks != ""){
    //   this.mechItemTechRemarks = this.storage.get(this.dboMechLoc).mechItemTechRemarks;
    //   this.techCheckIn = true;
    //   this.itemTechRmkDiv=true;
    // }        
  this._DBOMechanicalComponentService.getMechPanels(this.dboFormData).subscribe(respp => {
    console.log(respp);
    this.panelList = respp;
    this.questionsBean1 = respp.questionsBean;
    this.dboMechPanelList = respp.dboMechPanelList1;
    this.dboMechPanelList2 = respp.dboMechPanelList2;
    for(let g=0;g<this.dboMechPanelList.length; g++){
      for(let h=0;h<this.questionsBean1.length; h++){
        if(this.dboMechPanelList[g].orderId == this.questionsBean1[h].orderId){
          this.questionsBean1[h].addOnNewColFlag = this.dboMechPanelList[g].addOnNewColFlag;
        }
      }
    }
    for(let u = 0; u< this.dboMechPanelList2.length; u++){
      if(this.dboMechPanelList2[u].colNm == "Flow Rate (TPH)"){
        console.log("123");
        this.flowRate = this.dboMechPanelList2[u].colValCd;
        break;
      }

    }
    for(let u = 0; u< this.dboMechPanelList2.length; u++){
      if(this.dboMechPanelList2[u].colNm == "Power"){
        console.log("123");
        this.power = this.dboMechPanelList2[u].colValCd;
        break;
      }

    }
    for(let u = 0; u< this.dboMechPanelList2.length; u++){
      if(this.dboMechPanelList2[u].colNm == "CEP Capacity (TPH)"){
        console.log("123");
        this.cep = this.dboMechPanelList2[u].colValCd;
        break;
      }

    }
    
    console.log(this.flowRate);
    // for(let l = 0; l < respp.dboMechPanelList1.length; l++)
    //       {
    //         this.AddOnFlag[l] = respp.dboMechPanelList1[l].addOnNewColFlag;
    //         console.log(this.AddOnFlag[l]);
    //       } 

    for(let z = 0; z < respp.dboMechPanelList1.length; z++){
      for(let v = 0; v < this.questionsBean1.length; v++){
        if(respp.dboMechPanelList1[z].colNm == this.questionsBean1[v].dropDownType.value){
          this.questionsBean1[v].dispInd = respp.dboMechPanelList1[z].dispInd;
        }
      }
    }
    for(let k = 0; k < this.questionsBean1.length; k++){
      if(this.questionsBean1[k].dispInd == 1){
        this.questionsBean.push(this.questionsBean1[k]);
      }
    }
    console.log(this.questionsBean);
    for(let l = 0; l < this.questionsBean.length; l++)
    {
      this.AddOnFlag[l] = this.questionsBean[l].addOnNewColFlag;
      console.log(this.AddOnFlag[l]);
    } 

       //to display dropdown default values in ui screen
      for (let l = 0; l < this.questionsBean.length; l++) {
        for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
        //  this.openOth[l] = false;
          if (this.questionsBean[l].dropDownValueList[d].defaultFlag == true) {
            this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value);
          }
        }
      }
      console.log(this.defaultValues);
      if(this.dboMechFullArray3.length>0)
  {
  
  for( let j=0;j<this.dboMechFullArray3.length;j++)
  {
    if(this.dboMechFullArray3[j].itemId==this.itemId && this.dboMechFullArray3[j].subItemId==this.subItemId)
    {
      //this.defaultValues=[];
      //this.defaultValues1=[];


      // if(this.temparray1!=undefined)
      // {
      //   if(this.temparray1.length!=0)
      //   {
      //    for(let i=0;i<this.temparray1.length;i++)
      //    {

      //    }
      //   }
      // }
      if(this.dboMechFullArray3[j].mechItemTechRemarks!=null)
      {
        this.mechItemTechRemarks=this.dboMechFullArray3[j].mechItemTechRemarks;
        this.techCheckIn = true;
        this.itemTechRmkDiv=true;
      }
      if(this.dboMechFullArray3[j].mechItemComrRemarks!=null)
      {
        this.mechItemComrRemarks=this.dboMechFullArray3[j].mechItemComrRemarks;
        this.comrrCheck = true;
        this.itemComrRmkDiv=true;
      }
      //this.defaultValues=this.dboMechFullArray3[j].defaultValues;
this.errDisplayPnl=this.dboMechFullArray3[j].errDisplayPnl;
this.errMsgRhsCost=this.dboMechFullArray3[j].errMsgRhsCost;
      this.qty=this.dboMechFullArray3[j].qty;
      this.dboCost=this.dboMechFullArray3[j].dboCost;
      this.discountPer = this.dboMechFullArray3[j].discountPer;
      if(  this.dboCost==0)
            {
              this.saveBtColor=false
              this.buttonColor="red";
              this.dispSCFM = true;
            }
            else
            {
              this.saveBtColor=false         
               this.buttonColor="green";
              this.dispSCFM=false;
        
            }
      this.dboAddOnCost=this.dboMechFullArray3[j].dboAddOnCost;
      this.defaultValues = this.dboMechFullArray3[j].defaultValues;
      this.itemCost=this.dboMechFullArray3[j].itemCost;
      if(this.dboMechFullArray3[j].itemOthersList.length!=0)
      {
        for(let i=0;i<this.dboMechFullArray3[j].itemOthersList.length;i++)
        {
          if(this.dboMechFullArray3[j].itemOthersList[i].itemId==this.itemId && this.dboMechFullArray3[j].itemOthersList[i].subItemId==this.subItemId )
          {
            
            this.displayCompOthTable=true;
            this.othCheck=true;
            this.othersCompCheck=true;
            this.itemOthersList.push(this.dboMechFullArray3[j].itemOthersList[i]);
          }
        }
       
      }
    }
   
  }
}
      // for(let j=0;j<this.dboMechFullArray3.length;j++)
      // {
      //   if(this.dboMechFullArray3[j].componenet==this.itemName)
      //   {
      //     this.dboCost=this.dboMechFullArray3[j].dboCost;
      //   }
      // }
  
      // for(let j=0;j<this.dboMechFullArray3.length;j++)
      // {
      //   if(this.dboMechFullArray3[j].subItemId>0)
      //   {
      //     if(this.dboMechFullArray3[j].techComments!=null)
      //     {
      //       this.subItemRmkValOut= this.dboMechFullArray3[j].techComments;
      //          this.othSubItmTechRemChk=true;
      //          this.subItemTchRem=true;
      //          this.subItemRemarkDiv=true;
      //     }
      //     if()
      //     {

      //     }
      //     break;
      //   }
      // }
      this.diableitemname=true;

  });

  


}
// take min and max value
  /**
   * 
   * @param options dropdown value
   * @param selVal seleced option
   * @param i index of the drop down
   */
  optionSel(options, selVal, i) {
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
  getPriceExel(){
    this.getPrice=true;
    this.mainSave=true;
    this.mainSave2=true;
    if(this.disablelhs.includes(true) || this.openOth1.includes(true) )
    {
      this.getPrice=false;
    }
    if(this.getPrice )
    {
    this._ITOeditQoutService.dboMechData = [];
  this.SelectedExcelData = [];
    //this is used to uncheck others if there are no new items in others
   if(this.itemOthersList.length == 0)
       this.othCheck = false;
    //this is used to uncheck others if there are no new sub items in others
    //if(this.subItemOthAddonList.length == 0)
      //  this.othersSubCheck = false;
         //this is used to uncheck others if there are no new sub itemtype in others
    //if(this.subItmTypOthList.length == 0)
    //this.othersSubTypCheck = false;
    console.log(this.flowRate);
    
    for(let m = 0; m < this.questionsBean1.length; m++){
      this.dboClass = new dboClass();
      if(this.questionsBean1[m].dispInd == 0 && (this.questionsBean1[m].dropDownType.value == "Flow Rate (TPH)" || this.questionsBean1[m].dropDownType.value =="Power" || this.questionsBean1[m].dropDownType.value =="CEP Capacity (TPH)")){
        
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.itemId = this.itemId;
          this.dboClass.itemName = null;
          if(this.subItemId == 0 || this.subItemId == null){
            this.dboClass.subItemId = null;
          }else {
            this.dboClass.subItemId = this.subItemId;           
          }  
          this.dboClass.subItemName = null;
          this.dboClass.colId = this.questionsBean1[m].dropDownType.key;
          this.dboClass.colNm = null;
          if(this.questionsBean1[m].dropDownType.value == "Flow Rate (TPH)")
          {
            this.dboClass.colValCd = this.flowRate; 

          }
          else if(this.questionsBean1[m].dropDownType.value =="Power")
          {
            this.dboClass.colValCd = this.power; 

          }
          else if(this.questionsBean1[m].dropDownType.value =="CEP Capacity (TPH)")
          {
            this.dboClass.colValCd = this.cep; 
          }
          this.dboClass.quantity = null;         
          this.dboClass.cost = null;          
          this.dboClass.techRemarks = null;
          this.dboClass.comrRemarks = null;
          this.dboClass.addOnFlg = 0;
          this.dboClass.techFlag = this.questionsBean1[m].techFlag;
          this.dboClass.comrFlag = this.questionsBean1[m].comrFlag;
          this.SelectedExcelData.push(this.dboClass);        
        }
      
    }
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
          if(this.subItemId == 0 || this.subItemId == null){
            this.dboClass.subItemId = null;
          }else {
            this.dboClass.subItemId = this.subItemId;           
          }  
          this.dboClass.subItemName = null;
          this.dboClass.colId = this.questionsBean[l].dropDownType.key;
          this.dboClass.colNm = null;
          this.dboClass.colValCd = this.questionsBean[l].dropDownValueList[d].code; 
          this.dboClass.quantity = null;         
          this.dboClass.cost = null;          
          this.dboClass.techRemarks = null;
          this.dboClass.comrRemarks = null;
          this.dboClass.addOnFlg = 0;
          this.dboClass.techFlag = this.questionsBean[l].techFlag;
          this.dboClass.comrFlag = this.questionsBean[l].comrFlag;
          this.SelectedExcelData.push(this.dboClass);
        }
      }
    }
  //   for(let j=0;j<this.addOnList.length;j++)
  //   {
  //     if(this.addOnList[j].itemId==this.itemId && this.addOnList[j].subItemId==this.subItemId )
  //     {
  //       this.dboClass = new dboClass();
  //   this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  //   this.dboClass.itemId =  this.addOnList[j].itemId;
  //   this.dboClass.itemName = null;
  //   if( this.addOnList[j].subItemId == 0 ||  this.addOnList[j].subItemId == null){
  //      this.dboClass.subItemId = null;
  //     }else {
  //       this.dboClass.subItemId =  this.addOnList[j].subItemId;           
  //     }  
  //   this.dboClass.subItemName = null;
  //   this.dboClass.colId = this.addOnList[j].colId;
  //   this.dboClass.colNm = null;
  //   this.dboClass.colValCd = this.addOnList[j].colValCd; 
  //   this.dboClass.quantity = 1;
  //   //uncomments after css changes
  //  // this.dboClass.quantity =this.newAddQtyO[index] ;         
  //   this.dboClass.cost =this.addOnList[j].cost;          
  //   this.dboClass.techRemarks =  null;
  //   this.dboClass.comrRemarks =this.addOnList[j].comrRemarks;
  //   this.dboClass.addOnFlg = 1;
  //   this.dboClass.techFlag = 1;
  //   this.dboClass.comrFlag = 1;
  //   this.SelectedExcelData.push(this.dboClass);
  //     }
  //   }
  
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
    console.log(this.SelectedExcelData);
     this.dboFormData.quantity = this.qty;
    console.log(this.qty);
    console.log(this.discountPer);
    this.dboFormData.mechTechData = this.SelectedExcelData;
  this.dboFormData.quantity = this.qty;
  this.dboFormData.mechItemTechRemarks = this.mechItemTechRemarks;
  this.dboFormData.mechItemComrRemarks = this.mechItemComrRemarks;
  this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
  this.dboFormData.discountPer = this.discountPer;
  console.log(this.dboFormData);
this._DBOMechanicalComponentService.getMechTechPrice(this.dboFormData).subscribe(res => {
  console.log(res);
  this.techPriceResp = res;
  this.dboCost = res.basicCost;
  if(  this.dboCost==0)
  {
    this.saveBtColor=false
    this.buttonColor="red";
    this.dispSCFM = true;
  }
  else
  {
    this.saveBtColor=false         
     this.buttonColor="green";
this.dispSCFM=false;

  }
  this.dboAddOnCost= res.mechAddOnCost;
  for(let b= 0; b<res.mechTechList.length; b++){
    if(res.mechTechList[b].itemId == res.mechTechData[0].itemId && res.mechTechList[b].subItemId == res.mechTechData[0].subItemId){
      this.itemCost= res.mechTechList[b].itemCost;
    }
  }
  
  if (res.successCode == 0) {
    this.subMessage = true;
    this.messageVal = "Cost Saved successfully";
    this._ITOcustomerRequirementService.sendmecBtnStatus(true);
    //f2fTechList contains the drop down default values
    // as wellas the new lhs and rhs values
    this._ITOeditQoutService.dboMechData = res.mechTechList;
    this.errDisplayPnl=[];
    this.errMsgRhsCost=[];
    for(let c=0;c<res.mechTechList.length;c++){
      if((res.mechTechList[c].rhsCost ==1 &&  res.mechTechList[c].lhsFlag==0 && res.mechTechList[c].addOnFlg==0 )){
        this.errMsgRhsCost[ res.mechTechList[c].colId] = "Actual Cost not Available. Get the same from SCM: " +res.mechTechList[c].rhsCost;
        this.errDisplayPnl[res.mechTechList[c].colId] = true;
      }else{
        if((res.mechTechList[c].rhsCost > 0 &&  res.mechTechList[c].lhsFlag==0 && res.mechTechList[c].addOnFlg==0 )){
          this.errMsgRhsCost[ res.mechTechList[c].colId] = "AddOnCost: " +res.mechTechList[c].rhsCost;
          this.errDisplayPnl[res.mechTechList[c].colId] = true;
        }
      }
    }
    console.log(this._ITOeditQoutService.dboMechData);
  } else {
    this.subMessage = true;
    this.messageVal = res.successMsg;
  }
  this.dboBasicCost = res.basicCost;
 
  // after sucessful response store the values to local variables
  if (this.dboMechFullArray3.length != 0) {
    for (let d = 0; d < this.dboMechFullArray3.length; d++) {
      let j = this.dboMechFullArray3.map(d => { return d.id }).indexOf(this.mechItemId);
      if (j != (-1)) {
        this.dboMechFullArray3[j] = {
          qty: this.qty,
          discountPer: this.discountPer,
          id: this.mechItemId,
          componenet: this.itemName,
          defaultValues: this.defaultValues,
          dboCost: res.basicCost,
          dboAddOnCost: res.mechAddOnCost,
          techComments: res.techComments,
          comrComments: res.comrComments,
          techRemarks: res.techRemarks,
          comrRemarks : res.comrRemarks,
          mechItemTechRemarks: res.mechItemTechRemarks,
          mechItemComrRemarks: res.mechItemComrRemarks,
          addOnList: this.addOnList,
          itemOthersList: this.itemOthersList,
          itemId: this.itemId,
          itemName: this.itemName,
          subItemId: this.subItemId,
          subItemName: this.subItemName,
          errMsgRhsCost:this.errMsgRhsCost,
          errDisplayPnl:this.errDisplayPnl,
          itemCost:this.itemCost
        };
        break;
      }
      else {
        this.dboMechFullArray3.push({
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
          comrRemarks : res.comrRemarks,
          mechItemTechRemarks: res.mechItemTechRemarks,
          mechItemComrRemarks: res.mechItemComrRemarks,
          addOnList: this.addOnList,
          itemOthersList: this.itemOthersList,
          itemId: this.itemId,
          itemName: this.itemName,
          subItemId: this.subItemId,
          subItemName: this.subItemName,
          errMsgRhsCost:this.errMsgRhsCost,
          errDisplayPnl:this.errDisplayPnl,
          itemCost:this.itemCost
        });
        break;
      }
    }
  }
  else {
    this.dboMechFullArray3.push({
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
      comrRemarks : res.comrRemarks,
      mechItemTechRemarks: res.mechItemTechRemarks,
      mechItemComrRemarks: res.mechItemComrRemarks,
      addOnList: this.addOnList,
      itemOthersList: this.itemOthersList,
      itemId: this.itemId,
      itemName: this.itemName,
      subItemId: this.subItemId,
      subItemName: this.subItemName,
      errMsgRhsCost:this.errMsgRhsCost,
      errDisplayPnl:this.errDisplayPnl,
      itemCost:this.itemCost
    });
  }
  console.log(this.dboMechFullArray3);
  this.addedClassList = [];
  for (let m = 0; m < this.dboMechFullArray3.length; m++) {
    this.addedClassList.push(this.dboMechFullArray3[m].componenet);
  }
  this.hideprogressCost = false;
  this.saveInLocal(this.dboMechLoc, { 
    dboMechFullArray3: this.dboMechFullArray3, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
    itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
    selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
    itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
    subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
    mechItemTechRemarks: this.mechItemTechRemarks, mechItemComrRemarks: this.mechItemComrRemarks,
    finalMechCost: this.finalMechCost, subItemcst: this.subItemcst, totalMechCost: this.totalMechCost
    });  
  // if(this.dboCost>0)
  // {
  //   this.selectdEl[this.selectedELIndex]=true;
  // }
})
    this.addedClassList = [];
    for (let m = 0; m < this.dboMechFullArray3.length; m++) {
      this.addedClassList.push(this.dboMechFullArray3[m].componenet);
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
      }
}
itmTechInRemarks(){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(this.mechItemTechRemarks);
}
itemComrInRemarks(){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
  console.log(this.mechItemComrRemarks);
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
    this.mechItemTechRemarks = '';
  }
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
    this.mechItemComrRemarks = '';
  }
  }
  newAddOn1(val, z){
    this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    console.log(val, z);
    // if(event.target.checked){
      this.addonflg="1";
      this.addOnCheck[z] = true;
      this.rhs[z]=true;
      this.openOth[z] =true;
      this.openOth1[z]=true;
    // }else if(!event.target.checked){
    //   this.addonflg="0";
    //   this.addOnCheck[z] = false;
    //   this.openOth[z] =false;
      //this.newAddNameO[z]=null;
     // this.newAddQtyO[z]=null;
    //  this.newAddCostO[z]=null;
     // this.newAddComRemrkO[z]=null;
      //this.newAddRemrkO[z]=null;
    // }
    
  }
  rhsOthersFormEdit(val,index){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.addonflg="0";
    this.openOth1[index]=true;
  } 
  rhsOthersForm(val, index){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.openOth1[index]=false;
    console.log(this.newAddNameO[index]);
    console.log(this.newAddQtyO[index]);
    console.log(this.newAddCostO[index]);
    console.log(this.newAddRemrkO[index]);
        for(let n = 0; n < this.dboMechPanelList.length; n++){
      if(val ==  this.dboMechPanelList[n].colNm){
        this.colValNmId = this.dboMechPanelList[n].colId;
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
    this.dboClass.itemName = null;
    if(this.subItemId == 0 || this.subItemId == null){
       this.dboClass.subItemId = 0;
      }else {
        this.dboClass.subItemId = this.subItemId;           
      }  
    this.dboClass.subItemName = null;
    this.dboClass.colId = index;
    this.dboClass.colNm = null;
    this.dboClass.colValCd = this.newAddNameO[index]; 
    this.dboClass.quantity = 1;
    //uncomments after css changes
   // this.dboClass.quantity =this.newAddQtyO[index] ;         
    this.dboClass.cost = this.newAddCostO[index];          
    this.dboClass.techRemarks =  null;
    this.dboClass.comrRemarks = this.newAddRemrkO[index];
    this.dboClass.addOnFlg = 1;
    this.dboClass.techFlag = 1;
    this.dboClass.comrFlag = 1;
    this.addOnList.push(this.dboClass);
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
    this.addonflg="0";
    // this.addOnList1=[];
    //this.addOnList[index].push(this.dboClass);
  }
  // opening new table for others
  openCompTable(event) {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if (event.target.checked) {
      this.displayCompOthTable = true;
    }
    else if (!event.target.checked) {
      this.displayCompOthTable = false;
      this.itemOthersList = [];
      this.disablelhs=[];
    }
  }
  compOthersForm(others, othersfrm: NgForm) {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(others);
    this.dboClass = new dboClass();
    this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboClass.itemId = this.itemId;
    this.dboClass.itemName = null;
    if(this.subItemId == 0 || this.subItemId == null){
       this.dboClass.subItemId = 0;
      }else {
        this.dboClass.subItemId = this.subItemId;           
      }  
    this.dboClass.subItemName = null;
    this.dboClass.colId = 0;
    this.dboClass.colNm = others.othItemName;
    this.dboClass.colValCd = others.othItemVal; 
    this.dboClass.quantity = null;         
    this.dboClass.cost = others.othCost;          
    this.dboClass.techRemarks = others.othTechRem;
    this.dboClass.comrRemarks = others.othComrRem;
    this.dboClass.addOnFlg = 0;
    this.dboClass.techFlag = 1;
    this.dboClass.comrFlag = 1;
    this.disablelhs[this.itemOthersList.length-1]=false;
    this.itemOthersList.push(this.dboClass);
    this.tempitemOthersList.push(this.dboClass);
    console.log(this.itemOthersList);
   
    othersfrm.reset();
    this.displayOthnewLine = false;
  }
   // adding new other lines
   addRowsOth() {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.displayOthnewLine = true;
  }
  cancelAddOn(key ,i)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.addOnCheck[key]=false;
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
    this.newAddNameO[key]="";
    this.newAddCostO[key]="";
    this.newAddRemrkO[key]=""; 
    this.addonflg="0";
  }
 
  //cancel new line
  cancelnewLineOth() {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.displayOthnewLine = false;
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
      this.itemRemarksVal="";
    }
  }
   //capturing of Item techinal  Remarks value
   checkRemarks(){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(this.itemRemarksVal)
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
    console.log(this.itemCmrRemarksVal)
  }
   /**
   * 
   * @param key key value to store in localstorage
   * @param val valueto be stored in localstorage
   */
  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.dboMechanicalLocal[key] = this.storage.get(key);
  }
  //calculate total function
  /**
   * calculte total
   */
  calcTotal() {

    this._ITOeditQoutService.dboMechData = [];
    this.hideprogressCost1 = true;
    this.saveCalcTotal();
    //this.finalMechCost = 0;
    // console.log(this.finalAddGenListArray, this.dboMechFullArray3, this.othersAddonList);
    // this.finalAddGenListArray = this.finalAddGenListArray.filter((x) => {
    //   return (x != null);
    // })
  }
  saveCalcTotal(){
    this._ITOeditQoutService.dboMechData = [];
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
    this.dboFormData.mechItemIdList = this.itemIdList.filter(n => n != null);
    console.log(this.itemIdList);
    console.log(this.dboFormData);
    this._DBOMechanicalComponentService.saveMechItem(this.dboFormData).subscribe(savedResp => {
      console.log(savedResp);
      this.respSaveItem = savedResp;
      if(savedResp.successCode == 0){
        this.finalMechCost=savedResp.totalPrice;
        this.finalCostBool = true;
        this.mainSave2=false;

      }
      this.saveInLocal(this.dboMechLoc, { 
        dboMechFullArray3: this.dboMechFullArray3, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
        itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
        selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
        itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
        subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
        mechItemTechRemarks: this.mechItemTechRemarks, mechItemComrRemarks: this.mechItemComrRemarks,
        finalMechCost: this.finalMechCost, subItemcst: this.subItemcst, totalMechCost: this.totalMechCost
      });
      
       //call one line BOM
       this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
        console.log(resOnline);
        if (resOnline.successCode == 0) {
          this.finalMechCost = resOnline.oneLineBomExcel.dboMechCost;
          this.totalMechCost = resOnline.oneLineBomExcel.totalMechCost;
        } 
        this.saveInLocal(this.dboMechLoc, { 
          dboMechFullArray3: this.dboMechFullArray3, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
          itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
          selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
          itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
          subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
          mechItemTechRemarks: this.mechItemTechRemarks, mechItemComrRemarks: this.mechItemComrRemarks,
          finalMechCost: this.finalMechCost, subItemcst: this.subItemcst, totalMechCost: this.totalMechCost
        });         
        this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
        this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, finalMechCost: this.finalMechCost, totalMechCost: this.totalMechCost});
        // if (this._ITOcustomerRequirementService.editFlag) {
        //   this._ITOcustomerRequirementService.editFlag = false;
        //   this.router.navigate(['/EditQuot']);
        // }
      });
      this.hideprogressCost1 = false;
    });
}
/**
   * save the DBO Mechanical cost 
   */
  savePriceExel() {
    this._ITOeditQoutService.dboMechData = [];
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
    this.dboFormData.mechItemIdList = this.itemIdList.filter(n => n != null);
    console.log(this.itemIdList);
    console.log(this.dboFormData);
    this._DBOMechanicalComponentService.saveMechItem(this.dboFormData).subscribe(savedResp => {
      console.log(savedResp);
      if (savedResp.successCode == 0) {
        this.mainSave=false;
        this.message = true;
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

        //Calling saveRemarks to save overwriten cost and comments
        this._ITOcustomerRequirementService.saveBasicDet.groupCode = 'OVERWRITE';
        this._ITOcustomerRequirementService.saveBasicDet.scopeCode = 'MECH';
        this._ITOcustomerRequirementService.saveBasicDet.overwrittenCost = this.OverWrittenfinalMechCost;
        this._ITOcustomerRequirementService.saveBasicDet.remarks = this.remarks;   
        console.log(this._ITOcustomerRequirementService.saveBasicDet);
        this._ITOAddOnComponentsService.saveRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(saveRem => {
          console.log(saveRem);
        })

        //call one line BOM
        this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
          console.log(resOnline);
          if (resOnline.successCode == 0) {
            this.finalMechCost = resOnline.oneLineBomExcel.dboMechCost;
            this.totalMechCost = resOnline.oneLineBomExcel.totalMechCost;
          } 
          this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
          this.saveInLocal(this.oneLineLoc,{ resOnline: resOnline.oneLineBomExcel, finalMechCost: this.finalMechCost, totalMechCost: this.totalMechCost});
          if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit!=false ) {
            this.router.navigate(['/CostEstimation/Mechanical/DboMechAuxialries']);
          }
          if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
            this._ITOcustomerRequirementService.editFlag = false;
            this.router.navigate(['/EditQuot']);
          }
          
        });
      } else {
        this.message = true;
        this.successMsg = savedResp.successMsg;       
      }
    });
    this.saveInLocal(this.dboMechLoc, { 
      dboMechFullArray3: this.dboMechFullArray3, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      mechItemTechRemarks: this.mechItemTechRemarks, mechItemComrRemarks: this.mechItemComrRemarks,
      finalMechCost: this.finalMechCost, subItemcst: this.subItemcst, totalMechCost: this.totalMechCost
    });
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
  //subItem save
  subItemSave(){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if (this.enableOverwriteDiv) {
      this.dboFormData.overwrittenPrice = this.OverWrittenfinalMechCost;
      this.dboFormData.overwrittenPriceFlag = 1;
      //this.dboFormData.remarks = this.remarks;
    } else if (this.OverWrittenfinalMechCost > 0) {
      this.dboFormData.overwrittenPrice = this.OverWrittenfinalMechCost;
      this.panelList.overwrittenPriceFlag = 1;
    }
    this.dboFormData.modifiedById = this._ITOLoginService.loggedUserDetails;
    this.dboFormData.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormData.itemId = this.itemId;
    this.itemIdList1.push(0);
    this.dboFormData.mechSubItemIdList = this.itemIdList1.filter(n => n != null);
    console.log(this.itemIdList1);

    this.dboFormData.techRemarks = this.subItemRmkValOut;
    this.dboFormData.comrRemarks = this.subItemCmrRemValOut;
    console.log(this.dboFormData) 
    this._DBOMechanicalComponentService.saveMechSubItem(this.dboFormData).subscribe(responess =>{
      console.log(responess);
      if (responess.successCode == 0) {
        this.subItemSaveBt=false;
        for(let c=0;c<responess.saveMechSubItemList.length; c++){
          if(responess.saveMechSubItemList[c].mechItemId == 0 && responess.saveMechSubItemList[c].itemId != 0 && responess.saveMechSubItemList[c].subItemId == 0){
            console.log(responess.saveMechSubItemList[c].itemCost);
            this.subItemcst = responess.saveMechSubItemList[c].itemCost;
          }
        }
      }

    });
    this.saveInLocal(this.dboMechLoc, { 
      dboMechFullArray3: this.dboMechFullArray3, addOnList: this.addOnList, itemOthersList: this.itemOthersList,
      itemIdList: this.itemIdList, itemIdList1: this.itemIdList1,
      selectdEl: this.selectdEl, selectdEl1: this.selectdEl1,      
      itemRemarksVal: this.itemRemarksVal, itemCmrRemarksVal: this.itemCmrRemarksVal,
      subItemRmkValOut: this.subItemRmkValOut, subItemCmrRemValOut: this.subItemCmrRemValOut,
      mechItemTechRemarks: this.mechItemTechRemarks, mechItemComrRemarks: this.mechItemComrRemarks,
      finalMechCost: this.finalMechCost, subItemcst: this.subItemcst, totalMechCost: this.totalMechCost
    });
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
    this.dboClass.subItemId = null;
    this.dboClass.subItemName = null;
    this.dboClass.colId = null;
    this.dboClass.colNm = null;
    this.dboClass.colValCd = null;
    this.dboClass.quantity = othersItem.itemQuantity;
    this.dboClass.cost = othersItem.itemPrice;
    this.dboClass.techRemarks = othersItem.itemTechRemarks;
    this.dboClass.comrRemarks = othersItem.itemComrRemr;
    this.dboClass.addOnFlg = 0;
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
        this.dboClass.subItemId = this.itemOthersAddonList[c].subItemId;
        this.dboClass.subItemName = this.itemOthersAddonList[c].subItemName;
        this.dboClass.colId = 0;
        this.dboClass.colNm = lhsRhsItem.lhsVal;
        this.dboClass.colValCd = lhsRhsItem.rhsVal;
        this.dboClass.quantity = this.itemOthersAddonList[c].quantity;
        this.dboClass.cost = this.itemOthersAddonList[c].cost;
        this.dboClass.techRemarks = this.itemOthersAddonList[c].techRemarks;
        this.dboClass.comrRemarks = this.itemOthersAddonList[c].comrRemarks;
        this.dboClass.addOnFlg = 0;
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





  addRowsSubLhsRhs(){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.lhsRhsSubnewLine = true;
  }
 cancelLinesSubLhs(i){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    this.lhsRhsSubItemsList.splice(i, 1);
    }
addSubLhsRhsForm(lhsRhsSubItem, lhsRhsSubItemfrm: NgForm){
  this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
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
        this.dboClass.colId = null;
        this.dboClass.colNm = lhsRhsSubItem.subLhsVal;
        this.dboClass.colValCd = lhsRhsSubItem.subRhsVal;
        this.dboClass.colValNm = null;
        this.dboClass.itemId = this.subItemOthAddonList[c].itemId;
        this.dboClass.itemName = this.subItemOthAddonList[c].itemName;
        this.dboClass.itemNm = this.subItemOthAddonList[c].itemName;
        this.dboClass.subItemId = this.subItemOthAddonList[c].subItemId;
        this.dboClass.subItemName = this.subItemOthAddonList[c].subItemName;
        this.dboClass.subItemTypeId = this.subItemOthAddonList[c].subItemTypeId;
        this.dboClass.subItemTypeName = this.subItemOthAddonList[c].subItemTypeNamee;
        this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        this.dboClass.quantity = this.subItemOthAddonList[c].quantity;
        this.dboClass.cost = this.subItemOthAddonList[c].cost;
        this.dboClass.techRemarks = this.subItemOthAddonList[c].techRemarks;
        this.dboClass.comrRemarks = this.subItemOthAddonList[c].comrRemarks;
        this.dboClass.techFlag = 1;
        this.dboClass.comrFlag = 1;
        this.dboClass.othColValFlag = 1;
        this.dboClass.addOnCostMeFlag = 0;       
        this.lhsRhsSubItemsList.push(this.dboClass);
      }
    }
    console.log(this.lhsRhsSubItemsList);
    lhsRhsSubItemfrm.reset();
    this.lhsRhsSubnewLine = false;
    this.dsplySubItmOthnewLine = false;
  }
 subItemsSave(){
  this.mainSave=true;
  this.mainSave2=true;
      this.saveBtColor=true;
    for(let b=0;b<this.subItemOthAddonList.length; b++){
      if (!this.lhsRhsSubItemsList.some((item) => item.subItemName == this.subItemOthAddonList[b].subItemName)) {
        this.lhsRhsSubItemsList.push(this.subItemOthAddonList[b]);
    }
    }console.log(this.lhsRhsSubItemsList);
  }

subItemOthForm(othersSubItem, othersSubItemfrm: NgForm){
  this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    console.log(othersSubItem);
    this.dboClass = new dboClass();
          this.dboClass.colId = null;
          this.dboClass.colNm = null;
          this.dboClass.colValCd = null;
          this.dboClass.colValNm = null;
          this.dboClass.itemId = this.itemId;
          this.dboClass.itemName = null;          
          this.dboClass.subItemId = null;
          this.dboClass.subItemName = othersSubItem.subItemName
          this.dboClass.subItemTypeId = null;
          this.dboClass.subItemTypeName =null;
          this.dboClass.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
          this.dboClass.quantity = othersSubItem.subItemQty;
          this.dboClass.cost = othersSubItem.subItemPrice;
          this.dboClass.techRemarks = othersSubItem.subItemTechRem;
          this.dboClass.comrRemarks = othersSubItem.subItemComrRemr;
          this.dboClass.techFlag = 1;
          this.dboClass.comrFlag = 1;
          this.dboClass.othColValFlag = 0;
          this.dboClass.addOnCostMeFlag = 0;   
    this.subItemOthAddonList.push(this.dboClass);
    this.lhsRhsSubItemsList.push(this.dboClass);//satya
    console.log(this.subItemOthAddonList);
    othersSubItemfrm.reset();
    this.dsplySubItmOthnewLine = false;
  }
  dboMechItmListSub(event, itemId, i){
    this.subItemSaveBt=true;
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
        
        // this.selectdEl2[i] = false;
      }
      for(let k=0; k<this.dboMechFullArray3.length; k++){
        if(this.dboMechFullArray3[k].subItemId == itemId){
          this.dboMechFullArray3[k] = null;
          this.dboMechFullArray3.slice(k,1);
          
        }
      }
      this.dboMechFullArray3 =  this.dboMechFullArray3.filter(n => n != null);
      // for(let k = 0; k < this.tempitemOthersList.length; k++){
      //   if(this.tempitemOthersList[k].itemId == itemId){
      //       this.tempitemOthersList[k] = null;
      //       this.tempitemOthersList.splice(k, 1);
         
      //   }
      // }
      for(let k = 0; k < this.addOnList.length; k++){
        if(this.addOnList[k].subItemId == itemId){
          this.errDisplayPnl[ this.addOnList[k].colId]=false;
          this.rhs[this.addOnList[k].colId]=false;
            this.addOnList[k] = null;
            this.addOnList.slice(k, 1);
         
        }
      }
    }
    this.addOnList =  this.addOnList.filter(n => n != null);
    console.log("after");
    console.log(this.dboMechFullArray3);
  }

    addLhsRhsSubItem(i){
      this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.lhsRhsSubItemSel = ''; 
    this.ibreakothers = i;
    console.log(i);
    this.lhsRhsSubItemSel = this.subItemOthAddonList[i].subItemName;
    console.log(this.lhsRhsSubItemSel);
      this.dsplyDialogSubLhsRhs = true;
      this.dsplySubItmOthnewLine = false;
  }
    cancelLinesSubItemOth(i) {
      this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
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
  openSubItemTable(event){
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    if(event.target.checked){
      this.displaySubItemOthTable = true;
    }else if(!event.target.checked){
      this.displaySubItemOthTable = false;
      this.subItemOthAddonList = [];
      this.lhsRhsSubItemsList=[];
    }
  }
   cancelnewLineSubItem() {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.dsplySubItmOthnewLine = false;
  }
   addRowsSubItem() {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
    this.dsplySubItmOthnewLine = true;
  }

  // delete the added subitem line for others
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
    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "MECH";
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
///others edit 
editlhs(i)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
      this.disablelhs[i]=true;

    console.log( this.disablelhs);
  }
  savelhs(i,type)
  {
    this.mainSave=true;
    this.mainSave2=true;
        this.saveBtColor=true;
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
