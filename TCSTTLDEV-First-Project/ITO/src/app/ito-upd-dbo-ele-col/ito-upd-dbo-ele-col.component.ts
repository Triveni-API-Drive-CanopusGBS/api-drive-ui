import { Component, OnInit } from '@angular/core';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { delay } from 'rxjs/operator/delay';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ItoUpdateCostDBOElecPrice } from '../update-cost-dboelect-price/update-cost-dboelect-price.service';
import { dboClass } from './ito-upd-dbo-ele-col';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';


@Component({
  selector: 'app-ito-upd-dbo-ele-col',
  templateUrl: './ito-upd-dbo-ele-col.component.html',
  styleUrls: ['./ito-upd-dbo-ele-col.component.css']
})
export class ItoUpdDboEleColComponent implements OnInit {

  cols: { field: string; header: string; }[];
  custTypeArray: { field: string; header: string; }[];

  volGenerationArray: Array<any> = [];
  finalRangeList: Array<any> = [];  // this list will have updated list of data from the grid
  localStorageValues: Array<any> = [];
  userRoles: Array<any> = [];  // list to store user roles
  newUsersLilst: Array<any> = [];  //users list
  prevRemarks: Array<any> = [];  // list to store previous remrks of engineer,reviewer and approver
  usersList: Array<any> = []; //users list
  successMsg: Array<any> = [];  // local variable to display success message
  data: Array<any> = [];
  colName: Array<any> = [];
  dBOElectricalListArrayNew: Array<any> = [];   // electrical components list for add screen
  dBOElectricalListArray: Array<any> = [];    // electrical components list for edit screen
  colData: Array<any> = [];
  colNames: Array<any> = [];   // dbo electrical columns data 
  DBOData: Array<any> = [];

  userId: any;
  assignee: any;
  questionsBean: any;   // local variable to assign question bean
  remarkss: any;
  saveBasic: any;
  tempResp: any;
  modifiedBy: any;
  selectedUR: any;
  reponseTemp: any;
  selectedData: any;
  tempRes: any;
  tempRes1: any;
  columns: any;
  previousPrice: any;
  custType: any = '';  //Domestic or Export
  itemId: any = '';
  dboClass:any;


  dboEleEdit: boolean = false;  // variable to check whether logged in user role is engineer
  dboEleApp: boolean = false;   // variable to check whether logged in user role is Approver
  dboEleRev: boolean = false;   // variable to check whether logged in user role is Reviewer
  displayMessage: boolean = false;  //to display success message
  displayDialog: boolean = false;   //to display success message
  tableDiv: boolean = false;        //to disabele comments section
  dispDropdown: boolean = true;     // to check whether dropdowns should be present while navigating from Myworkflow screen
  assignDisp: boolean;              // to check whether assign to field should be enabled/Disabled
  contains: boolean = false;
  disableBtn: boolean = true;
  newData: boolean = false;

  currentRoleId: string = 'selRoleId';      // local storage key to get loggedin user role id
  currentRole: string = 'selRole';          // local storage key to get loggedin user role Code
  user: string = 'userDetail';              // local storage key to get loggedin UserDetails
  loginUserDetails: string = "userDetail";  // local storage key to get loggedin UserDetails
  panelCode: string;                        //LT or HT
  labelName: string = '';
  custCode: string = '';                    //DM or EX
  itemName: string = '';
  columnName: string = '';

  colId: number = 0;
  driveType:Array<any>=['HT','LT','DT'];

  defaultvalue:string="HT";
  typeofpanel:Array<any>=['Electrical','Control and Instrumentation'];
  defaultvaluetype:string="Electrical";
  subItemList:Array<any>=[];
  subItemDefault:string="";
  dboFormData: any; //To store dbo form response
  panels:Array<any>=[];
  mainList:Array<any>=[];
  defaultvaluepanel:string='';
  subItemId:number=0;  
  make:string='';
  makeList:Array<any>=[];
  dropdowntype:Array<any>=['Main Price',' Addon Price','Addon depending on turbine MW Price'];
  dropdowndefault:string='Main Price';
  tableList:Array<any>=[];
  selectedFrame: any;
  finalFrameList :Array<any>=[];
  transpEdit:boolean=false;
  acceptedOnly: boolean = true;
  itemNameTemp:string="";
  tableBoolean:boolean=false;
  checkBoolean:boolean=false;
  currentRoleNew:string='';
  hidespinner:boolean=true;
  percentage:number=0;

//   Main Price --'ELE_PRICE'
// Addon Price --- 'ELE_COL_VAL'
// Addon depending on turbine MW Price-- 'ELE_ADDON_COST'


  constructor(private _ItoUpdateCostDBOElecPrice: ItoUpdateCostDBOElecPrice, private _ITOLoginService: ITOLoginService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOMyWorkListPageService: ITOMyWorkListPageService,
    private router: Router, private route: ActivatedRoute) {
      console.log(this.storage.get(this.currentRole));
      this.currentRoleNew=this.storage.get(this.currentRole);
      this._ItoUpdateCostDBOElecPrice.getDboFormData().subscribe(res => {
        console.log(res);
        if (this.storage.get(this.currentRole) == "DBO_ELE_REVIWER") 
        {
        this.transpEdit=true;
        }
        else  if (this.storage.get(this.currentRole) == "DBO_ELE_APPROVER") 
        {
          this.transpEdit=true;
        
        }
        this.dboFormData = res;
        this.dboFormData.saveBasicDetails.updateRequestNumber=0;
        this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);

        this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;
       // this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;

        if (this._ITOMyWorkListPageService.selectedUR != '') {

          console.log(this._ITOMyWorkListPageService.responseTemp);
          this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
          this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
         // this._ITOMyWorkListPageService.selectedUR = '';
          this.tempResp = this._ITOMyWorkListPageService.responseTemp;
          this.dboFormData.saveBasicDetails.updateRequestNumber= this.reponseTemp.saveBasicDetails.updateRequestNumber;
          for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
            this.prevRemarks.push(this.reponseTemp.commentList[r]);
          }

          if (this.storage.get(this.currentRole) == "DBO_ELE_EDIT") {
            this.tableList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceElectricalList;
            this.finalFrameList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceElectricalList;
           // this.savBasicDet.updateRequestNumber = this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
    this.checkBoolean=true;
          }
          else if (this.storage.get(this.currentRole) != "DBO_ELE_EDIT") {
            this.tableList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceElectricalList;
            this.finalFrameList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceElectricalList;
          }
         // if this._ITOMyWorkListPageService.selectedUR.updateCode.includes('VMS_FRM_LIST') )) {

          for(let j=0;j<this.tableList.length;j++)
          {
            if(this._ITOMyWorkListPageService.selectedUR.updateCode=="ELE_PRICE")
            {
              this.dropdowndefault="Main Price";
             this.cols = [
              { field: 'price15', header: 'Price' },
              { field: 'priceCode', header: 'PriceCode' },
              { field: 'approxCostFlag', header: 'ApproxCostFlag' },
             ];
            }
            else if(this._ITOMyWorkListPageService.selectedUR.updateCode=="ELE_ADDON_COST_UPD")
            {
              this.dropdowndefault= "Addon depending on turbine MW Price"
             this.cols = [
              { field: 'colNm', header: 'Lhs' },
              { field: 'colValCd', header: 'Rhs' },
              { field: 'addOnDirCost', header: 'Price' },
  
              { field: 'minVal', header: 'MinVal' },
              { field: 'maxVal', header: 'MaxVal' },
              { field: 'addOnCostCol', header: 'AddOnCostCol' },
  
              { field: 'errorMsg', header: 'ErrorMsg' },
              { field: 'approxCostFlag', header: 'ApproxCostFlag' },
             ];
            }
            else if(this._ITOMyWorkListPageService.selectedUR.updateCode=="ELE_COL_VAL")
            {
              this.dropdowndefault= "Addon Price"
             this.cols = [

              { field: 'colNm', header: 'Lhs' },
              { field: 'colValCd', header: 'Rhs' },
              { field: 'addOnDirCost', header: 'Price' },
              { field: 'minVal', header: 'MinVal' },
              { field: 'maxVal', header: 'MaxVal' },
              { field: 'techFlag', header: 'TechFlag' },
              { field: 'addOnCostPer', header: 'AddOnCostPer' },
              { field: 'addOnFlg', header: 'AddOnFlag' },
              { field: 'addOnDiffDs', header: 'AddOnDiffDs' },
              { field: 'approxCostFlag', header: 'ApproxCostFlag' },
              { field: 'addOnCostPer', header: 'AddOnCostPer' },
             ];
         
            }
          
            else if(this._ITOMyWorkListPageService.selectedUR.updateCode=="INSTRUMENT_LIST1")
            {
              this.dropdowndefault="Set of STG Instruments List";
             this.cols = [
              { field: 'typeOfInstr', header: 'TypeOfInstr' },
              { field: 'instrDesc', header: 'InstrDesc' },
              { field: 'instrMounting', header: 'InstrMounting' },
              { field: 'quantity1001Logic', header: 'Qty1001Logic' },
              { field: 'quantity1002Logic', header: 'Qty1002Logic' },
              { field: 'quantity2003Logic', header: 'Qty2003Logic' },
               
     
             ];
            }
            else if(this._ITOMyWorkListPageService.selectedUR.updateCode=="INSTRUMENT_LIST2")
            {

              this.dropdowndefault="Set of STG Instruments List(Auto Spray)";
             this.cols = [
              { field: 'frameName', header: 'frameName' },
              { field: 'typeOfInstr', header: 'typeOfInstr' },
     
             ];
            }
            else if(this._ITOMyWorkListPageService.selectedUR.updateCode=="INSTRUMENT_LIST3")
            {

              this.dropdowndefault="AdditionalProbes";
             this.cols = [
              { field: 'addInstrNm', header: 'addInstrNm' },
              { field: 'cost', header: 'cost' },
     
             ];
            }
           else if(this._ITOMyWorkListPageService.selectedUR.updateCode=="VMS_FRM_LIST")
           {
          
             this.itemNameTemp="Turbine Vibration Monitoring System (VMS)"
              this.cols = [
                { field: 'type', header: 'Type' },
                { field: 'altMake', header: 'AltMake' },
                { field: 'make', header: 'Make' },
                { field: 'gearbox', header: 'Gearbox' },
                { field: 'cost', header: 'Cost' },
                { field: 'note', header: 'Note' },
                { field: 'approxCostFlag', header: 'ApproxCostFlag' },
             
   
           ];
           }
          }
          
        }
        this._ItoUpdateCostDBOElecPrice.getFrameAndUserData().subscribe(res => {
          console.log(res);
          this.tempRes1 = res;  // assign the response to local variable for further use
          this.saveBasic = res.saveBasicDetails;  // assign the savebasicdetails bean to local variable for further use
          this.usersList = res.userDetailsList;   
          if (this.storage.get(this.currentRole) == "DBO_ELE_EDIT") {
            this.labelName="Please Select Reviewver"

          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
          if (this.usersList[r].rolesCodeVal[s] == "DBO_ELE_REVIWER") {
            this.newUsersLilst.push(this.usersList[r]);
          }
        }
      }
    }
    if (this.storage.get(this.currentRole) == "DBO_ELE_REVIWER") {
this.labelName="Please Select Approver"
      for (let r = 0; r < this.usersList.length; r++) {
        for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
      if (this.usersList[r].rolesCodeVal[s] == "DBO_ELE_APPROVER") {
        this.newUsersLilst.push(this.usersList[r]);
      }
    }
  }
}
        });
      });
    }
    bulkUpdate()
    {
      
this.dboFormData.percent=this.percentage;
this.dboFormData.modifiedBy = this.modifiedBy; 

     this._ItoUpdateCostDBOElecPrice.getAdminPercentEle(this.dboFormData).subscribe(res => {
       console.log(res);
        this.displayMessage = true;
        this.successMsg.push(res.successMsg);
      });


    }
    
    getPanels()
    {
      this.tableBoolean=true;
      this.makeList=[];
      this.finalFrameList=[];
      this.mainList=[];
      this.tableList=[];
      this.panels=[];
      this.subItemList=[];
      console.log(this.storage.get(this.currentRole));
      this.make="";
      this.makeList=[];
      this.panels=[];
      this.dboFormData.typeOfPanel=this.defaultvalue;
      if(this.defaultvaluetype=="Electrical")
      {
        this.dboFormData.eleType="ELE";

      }
      else
      {
        this.dboFormData.eleType="CI";

      }
      this._ItoUpdateCostDBOElecPrice.eletricalDetails(this.dboFormData).subscribe(res => {
        console.log(res);
        let temp=null;
      // this.panels= res.electricalDetailsList;
      this.mainList= res.dBOElectricalData;;
    temp =  res.dBOElectricalData.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.itemName === current.itemName);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      for(let j=0;j<temp.length;j++)
      {
  this.panels.push(temp[j].itemName);
      }
      this.defaultvaluepanel=this.panels[0];
      if(temp[0].isEnable==1 )
    {
      if(this.defaultvalue=="HT" || this.defaultvalue=="LT" )
      {
        if(temp[0].panelDependFlag==0)
        {
          this.dboFormData.typeOfPanel="CM";
      }
      else
      {
        this.dboFormData.typeOfPanel=this.defaultvalue;
  
      }
      }
      else
      {
        this.dboFormData.typeOfPanel="DT";
  
      }
      this.dboFormData.itemId=temp[0].itemId;
      this.dboFormData.subItemId=temp[0].subItemId;
      if(temp[0].itemName=="AC Synchronous Generator")
      {
       this. dropdowntype=['Main Price',' Addon Price','Addon depending on turbine MW Price'];
        this.dropdowndefault='Main Price';
      }
      else if(temp[0].itemName=="Set of STG Instruments List")
      {
       this. dropdowntype=['Set of STG Instruments List','Set of STG Instruments List(Auto Spray)','AdditionalProbes'];
       this.dropdowndefault='Main Price';
       
      }
      else
      {
        this. dropdowntype=['Main Price',' Addon Price'];
        this.dropdowndefault='Main Price';
      }
      this._ItoUpdateCostDBOElecPrice.getMake(this.dboFormData).subscribe(res => {
        console.log(res);
        for(let j=0;j<res.dBOMechanicalList.length;j++)
        {
          this.makeList.push(res.dBOMechanicalList[j].make);
          if(j==0)
          {
            
            this.make=this.makeList[0];
          }
        }    
      });
    }
    else
    {
      this.make="CM";
    }
      });   

    }
    rowSelected(rowData) {
      console.log(this.usersList);
      console.log(rowData);
      this.displayDialog = true;
      this.selectedFrame = rowData;
  
    }
    getTable()
    {
      this.hidespinner=false;
      this.tableList=[];
      this.finalFrameList=[];
      let temp=null;
      for(let j=0;j<this.mainList.length;j++)
      {
        if(this.mainList[j].itemName==this.defaultvaluepanel)
        {      
          this.dboFormData.itemId=this.mainList[j].itemId;
          temp=this.mainList[j];
          break;

        }
      }
      if(this.subItemList.length!=0)
      {
      for(let j=0;j<this.mainList.length;j++)
      {
        if(this.mainList[j].subItemName==this.subItemDefault)
        {      
          this.dboFormData.subItemId=this.mainList[j].subItemId;

          break;
          
        }
      }
    }
    else
    {
      this.dboFormData.subItemId=0;

    }
    //  input:itemId,subItemId, typeOfpanel,make,tableName-- use DBOForm to send inputs
    if(temp.panelDependFlag==0)
    {
      if(this.defaultvalue!="DT")
      {
        this.dboFormData.typeOfPanel="CM";

      }
      else
      {
        this.dboFormData.typeOfPanel="DT";

      }
    }
    else
    {
      this.dboFormData.typeOfPanel=this.defaultvalue;

    }
      this.dboFormData.make=this.make;

//       ain Price --'ELE_PRICE'
// Addon Price --- 'ELE_COL_VAL'
// Addon depending on turbine MW Price-- 'ELE_ADDON_COST'
 if(this.itemNameTemp=="Turbine Vibration Monitoring System (VMS)")
{
  this.dboFormData.tableName="VMS_FRM_LIST";

}
 else if(this.dropdowndefault=="Set of STG Instruments List")
{
  this.dboFormData.tableName="INSTRUMENT_LIST1";

}
else if(this.dropdowndefault=="Set of STG Instruments List(Auto Spray)")
{
  this.dboFormData.tableName="INSTRUMENT_LIST2";

}
else if(this.dropdowndefault=="AdditionalProbes")
{
  this.dboFormData.tableName="INSTRUMENT_LIST3";

}

else if(this.dropdowndefault=="Main Price")
{
  this.dboFormData.tableName="ELE_PRICE";

}
else if(this.dropdowndefault=="Addon Price")
{
  this.dboFormData.tableName="ELE_COL_VAL";

}
else if(this.dropdowndefault=="Addon depending on turbine MW Price")
{
  this.dboFormData.tableName="ELE_ADDON_COST";

}


      this._ItoUpdateCostDBOElecPrice.getDBOEleUpdatePriceData(this.dboFormData).subscribe(res => {
        this.hidespinner=true;
       console.log(res);
       this.tableList=res.dBOElectricalData;
       console.log( this.storage.get(this.currentRole));
       for(let j=0;j<res.dBOElectricalData.length;j++)
       {
         if(this.itemNameTemp=="Turbine Vibration Monitoring System (VMS)")
        {
           this.cols = [
          { field: 'type', header: 'Type' },
          { field: 'altMake', header: 'AltMake' },
          { field: 'make', header: 'Make' },
          { field: 'gearbox', header: 'Gearbox' },
          { field: 'cost', header: 'Cost' },
          { field: 'note', header: 'Note' },
          { field: 'approxCostFlag', header: 'ApproxCostFlag' },
 
          
 
        ];
        }
         else if(this.dropdowndefault=="Set of STG Instruments List")
        {
         this.cols = [
           { field: 'typeOfInstr', header: 'TypeOfInstr' },
           { field: 'instrDesc', header: 'InstrDesc' },
           { field: 'instrMounting', header: 'InstrMounting' },
           { field: 'quantity1001Logic', header: 'Qty1001Logic' },
           { field: 'quantity1002Logic', header: 'Qty1002Logic' },
           { field: 'quantity2003Logic', header: 'Qty2003Logic' },

           
 
         ];
        }
      else  if( this.dropdowndefault=="Set of STG Instruments List(Auto Spray)")
       {
             this.cols = [
              { field: 'frameName', header: 'frameName' },
              { field: 'typeOfInstr', header: 'typeOfInstr' },

     
             ];
            }
            else  if( this.dropdowndefault=="AdditionalProbes")
            {
                  this.cols = [
                   { field: 'addInstrNm', header: 'addInstrNm' },
                   { field: 'cost', header: 'cost' },
     
          
                  ];
                 }

        else if(this.dropdowndefault=="Main Price")
         {
          this.cols = [

            { field: 'price15', header: 'Price' },
            { field: 'priceCode', header: 'PriceCode' },
            { field: 'approxCostFlag', header: 'ApproxCostFlag' },
          ];
         }
         else if(this.dropdowndefault=="Addon depending on turbine MW Price")
         {
          this.cols = [
            { field: 'colNm', header: 'Lhs' },
            { field: 'colValCd', header: 'Rhs' },
            { field: 'addOnDirCost', header: 'Price' },
            { field: 'minVal', header: 'MinVal' },
            { field: 'maxVal', header: 'MaxVal' },
            { field: 'addOnCostCol', header: 'AddOnCostCol' },
            { field: 'errorMsg', header: 'ErrorMsg' },
            { field: 'approxCostFlag', header: 'ApproxCostFlag' },
          ];
         }
         else if(this.dropdowndefault=="Addon Price")
         {
          this.cols = [

            { field: 'colNm', header: 'Lhs' },
            { field: 'colValCd', header: 'Rhs' },
            { field: 'addOnDirCost', header: 'Price' },
            { field: 'minVal', header: 'MinVal' },
            { field: 'maxVal', header: 'MaxVal' },
            { field: 'techFlag', header: 'TechFlag' },
            { field: 'addOnCostPer', header: 'AddOnCostPer' },
            { field: 'addOnFlg', header: 'AddOnFlag' },
            { field: 'addOnDiffDs', header: 'AddOnDiffDs' },
            { field: 'approxCostFlag', header: 'ApproxCostFlag' },
            { field: 'addOnCostPer', header: 'AddOnCostPer' },
  
          ];
      
         }
        
         

       }
       this.hidespinner=true;

      });
    }
    optionSel1(value)
    {
     this.makeList=[];
     this.finalFrameList=[];
     this.mainList=[];
     this.tableList=[];
     this.panels=[];
     this.subItemList=[];
     this.itemNameTemp="";
     this.tableBoolean=false;
     
    }
    optionSel2(value)
    {
      this.tableBoolean=false;
      this.itemNameTemp="";

     this.makeList=[];
     this.finalFrameList=[];
     this.mainList=[];
     this.tableList=[];
     this.panels=[];
     this.subItemList=[];


    }
    optionSel5(value)
    {
     this.finalFrameList=[];
     this.tableList=[];
    }
    optionSel6(value)
    {
     this.finalFrameList=[];
     this.tableList=[];

    }
optionSel3(value)
{ 
  this.itemNameTemp=value;
  this.makeList=[];
  this.finalFrameList=[];
  this.tableList=[];
  this.subItemList=[];
  this.make="";
  this.makeList=[];

  let temp=null;;
this.subItemList=[];
this.subItemDefault='';
this.subItemId=0;
  for(let j=0;j<this.mainList.length;j++)
  {
    if(value==this.mainList[j].itemName)
    {
temp=this.mainList[j];
    }
  }
  if(value=="AC Synchronous Generator")
  {
   this. dropdowntype=['Main Price',' Addon Price','Addon depending on turbine MW Price'];
    this.dropdowndefault='Main Price';
  }
  else if(value=="Set of STG Instruments List")
  {
    this. dropdowntype=['Set of STG Instruments List','Set of STG Instruments List(Auto Spray)','AdditionalProbes'];
    this.dropdowndefault='Set of STG Instruments List';
  }
  else
  {
    this. dropdowntype=['Main Price',' Addon Price'];
    this.dropdowndefault='Main Price';
  }
  if(value=="Turbine Vibration Monitoring System (VMS)")
  {
    this.tableBoolean=false;

  }
  else
  {
    this.tableBoolean=true;
  }
  if(temp.subItemId!=0)
  {
    for(let j=0;j<this.mainList.length;j++)
    {
      if(temp.itemId==this.mainList[j].itemId)
      {
      this.subItemList.push(this.mainList[j].subItemName);
     
        this.subItemDefault=this.mainList[j].subItemName;
        this.subItemId=this.mainList[j].subItemId;
      
      }
    }
    this.make="CM";
    


  }
  else
  {
    if(temp.isEnable==1 && temp.itemName!="Turbine Vibration Monitoring System (VMS)" ||  temp.itemName=="Turbine Governing System" )
    {
      if(this.defaultvalue=="HT" || this.defaultvalue=="LT" )
      {
        if(temp.panelDependFlag==0)
        {
          this.dboFormData.typeOfPanel="CM";
      }
      else
      {
        this.dboFormData.typeOfPanel=this.defaultvalue;
  
      }
      }
      else
      {
        this.dboFormData.typeOfPanel="DT";
  
      }
      this.dboFormData.itemId=temp.itemId;
      this.dboFormData.subItemId=temp.subItemId;
      
      this._ItoUpdateCostDBOElecPrice.getMake(this.dboFormData).subscribe(res => {
        console.log(res);
        for(let j=0;j<res.dBOMechanicalList.length;j++)
        {
          this.makeList.push(res.dBOMechanicalList[j].make);
          if(j==0)
          {
            
            this.make=this.makeList[0];
          }
        }    
      });
    }
    else
    {
      this.make="CM";
    }
  }

}
optionSel4(value)
{
  this.makeList=[];
  this.finalFrameList=[];
  this.tableList=[];

//   for(let j=0;j<this.mainList.length;j++)
//   {
//     if(value==this.mainList[j].subItemName)
//     {
// temp=this.mainList[j];
//     }
//   }
  // if(temp.isEnable==1)
  // {
  //   if(this.defaultvalue=="HT" || this.defaultvalue=="LT" )
  //   {
  //     if(temp.panelDependFlag==0)
  //     {
  //       this.dboFormData.typeOfPanel="CM";
  //   }
  //   else
  //   {
  //     this.dboFormData.typeOfPanel=this.defaultvalue;

  //   }
  //   }
  //   else
  //   {
  //     this.dboFormData.typeOfPanel="DT";

  //   }
  //   this.dboFormData.itemId=temp.itemId;
  //   this.dboFormData.subItemId=temp.subItemId;

  //   this._ItoUpdateCostDBOElecPrice.getMake(this.dboFormData).subscribe(res => {
  //     console.log(res);
  //     for(let j=0;j<res.makeList.length;j++)
  //     {
  //       this.makeList.push(res.makeList[j].make);
  //       if(j==0)
  //       {
  //         this.make=this.makeList[0].make;
  //       }
  //     }
  //   });
  // }
  // else
  // {
  //   this.make="CM";

  // }

}
save()
{
  let temp=0;
  let tempboleean=false;

for (let d = 0; d < this.tableList.length; d++) {
  if (this.tableList[d].itemId == this.selectedFrame.itemId && this.tableList[d].subItemId == this.selectedFrame.subItemId ) {
    temp = this.tableList[d].price15;
   }
}
console.log(temp);
console.log(this.selectedFrame.price);
if(this.itemNameTemp=="Turbine Vibration Monitoring System (VMS)")

{
  let tempboleean=false;
  if (this.finalFrameList.length != 0) {
    for(let j=0;j<this.finalFrameList.length;j++)
    {
  if(this.finalFrameList[j].vmsId==this.selectedFrame.vmsId  )
  {
    this.finalFrameList[j]=this.selectedFrame;
     tempboleean=true;

  }
    }
  
    if(tempboleean==false)
    {
      this.finalFrameList.push(this.selectedFrame)

    }
  }
  else
  {
    this.finalFrameList.push(this.selectedFrame)
  }
}
else if(this.dropdowndefault=="Set of STG Instruments List")

{
  if (this.finalFrameList.length != 0) {
    for(let j=0;j<this.finalFrameList.length;j++)
    {
  if(this.finalFrameList[j].instrId==this.selectedFrame.instrId)
  {
    this.finalFrameList[j]=this.selectedFrame;
    tempboleean=true;

  }
    }
  
    if(tempboleean==false)
    {
      this.finalFrameList.push(this.selectedFrame)

    }
  }
  else
  {
    this.finalFrameList.push(this.selectedFrame)
  }
}
else if(this.dropdowndefault=="Set of STG Instruments List(Auto Spray)")

{
  if (this.finalFrameList.length != 0) {
    for(let j=0;j<this.finalFrameList.length;j++)
    {
  if(this.finalFrameList[j].id==this.selectedFrame.id)
  {
    this.finalFrameList[j]=this.selectedFrame;
    tempboleean=true;

  }
    }
  
    if(tempboleean==false)
    {
      this.finalFrameList.push(this.selectedFrame)

    }
  }
  else
  {
    this.finalFrameList.push(this.selectedFrame)
  }
}
else if(this.dropdowndefault=="AdditionalProbes")

{
  if (this.finalFrameList.length != 0) {
    for(let j=0;j<this.finalFrameList.length;j++)
    {
  if(this.finalFrameList[j].addInstrId==this.selectedFrame.addInstrId)
  {
    this.finalFrameList[j]=this.selectedFrame;
    tempboleean=true;

  }
    }
  
    if(tempboleean==false)
    {
      this.finalFrameList.push(this.selectedFrame)

    }
  }
  else
  {
    this.finalFrameList.push(this.selectedFrame)
  }
}

else if(this.dropdowndefault=="Main Price")
{
  if (this.finalFrameList.length != 0) {
    for(let j=0;j<this.finalFrameList.length;j++)
    {
  if(this.finalFrameList[j].priceId==this.selectedFrame.priceId)
  {
    this.finalFrameList[j]=this.selectedFrame;
    tempboleean=true;

  }
    }
    if(tempboleean==false)
    {
      this.finalFrameList.push(this.selectedFrame)

    }
    
  }
  else
  {
    this.finalFrameList.push(this.selectedFrame)
  }
}
else if(this.dropdowndefault=="Addon depending on turbine MW Price")
{
  if (this.finalFrameList.length != 0) {
    for(let j=0;j<this.finalFrameList.length;j++)
    {
  if(this.finalFrameList[j].priceId==this.selectedFrame.priceId)
  {
    this.finalFrameList[j]=this.selectedFrame;
    tempboleean=true;
  }
    }
    if(tempboleean==false)
    {
      this.finalFrameList.push(this.selectedFrame)

    }
    
  }
  else
  {
    this.finalFrameList.push(this.selectedFrame)
  }
}
else if(this.dropdowndefault=="Addon Price")

{
  if (this.finalFrameList.length != 0) {
    for(let j=0;j<this.finalFrameList.length;j++)
    {
  if(this.finalFrameList[j].colValId==this.selectedFrame.colValId )
  {
    this.finalFrameList[j]=this.selectedFrame;
    tempboleean=true;

  }
    }
  
    if(tempboleean==false)
    {
      this.finalFrameList.push(this.selectedFrame)

    }
  }
  else
  {
    this.finalFrameList.push(this.selectedFrame)
  }
}





this.displayDialog=false;
}
update(form)
{
  this.successMsg = [];

  this.dboFormData.updatePriceElectricalList=[];
  this.dboFormData.updatePriceElectricalAddOnList=[];
  this.dboFormData.updatePriceElectricalColVal=[];
  this.dboFormData.updatePriceElectricalInstr=[];
  this.dboFormData.updatePriceElectricalInstr3=[];

  this.dboFormData.updatePriceElectricalVms=[];


//   bean.getUpdateRequestNumber(), 
// bean.getUpdateCode(),
// bean.getPriceId(),
// bean.getItemId(),
// bean.getSubItemId(),
// bean.getTypeOfPanel(),
// bean.getMake(),
// bean.getCustType(), 
// bean.getPriceCode(), 
// bean.getPrice(),
// bean.getApproxCostFlag(),
// bean.getItemErrMessage(),
// bean.isActive())
this.dboFormData.saveBasicDetails.modifiedById = this.modifiedBy; 
this.dboFormData.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
this.dboFormData.saveBasicDetails.remarks = form.coments; 
console.log(this.dboFormData.modifiedById);
console.log(this.dboFormData.userRoleId);
console.log(this.dboFormData.loggedInUserCode);



if(this.storage.get(this.currentRole)=="DBO_ELE_EDIT")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=0;
  this.dboFormData.saveBasicDetails.statusId = 1;

}
else if(this.storage.get(this.currentRole)=="DBO_ELE_REVIWER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=1;
  if (form.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (form.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}
else if(this.storage.get(this.currentRole)=="DBO_ELE_APPROVER")
{
  this.dboFormData.saveBasicDetails.assignedTo = this.modifiedBy;
  this.dboFormData.saveBasicDetails.loggedInUserCode=2;
  if (form.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (form.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}

//   Main Price --'ELE_PRICE'
// Addon Price --- 'ELE_COL_VAL'
// Addon depending on turbine MW Price-- 'ELE_ADDON_COST'
for (let v = 0; v < this.finalFrameList.length; v++) {
    if (this.finalFrameList[v] != undefined) {
           this.dboClass = new dboClass();
           if(this.itemNameTemp=="Turbine Vibration Monitoring System (VMS)")
           {
   
           
           this.dboClass.updateCode = "VMS_FRM_LIST";
           this.dboClass.vmsId = this.finalFrameList[v].vmsId;
           this.dboClass.typeOfPanel = this.finalFrameList[v].typeOfPanel;
           this.dboClass.frameId = this.finalFrameList[v].frameId;
           this.dboClass.addPrbFlag = this.finalFrameList[v].addPrbFlag;
           this.dboClass.type = this.finalFrameList[v].type;
           this.dboClass.make = this.finalFrameList[v].make;
           this.dboClass.altMake = this.finalFrameList[v].altMake;
           this.dboClass.gearbox = this.finalFrameList[v].gearbox;
           this.dboClass.cost = this.finalFrameList[v].cost;
           this.dboClass.defaultFlag = this.finalFrameList[v].defaultFlag;
           this.dboClass.note = this.finalFrameList[v].note;
           this.dboClass.approxCostFlag = this.finalFrameList[v].approxCostFlag;
           this.dboClass.activeNew = this.finalFrameList[v].activeNew;
           this.dboFormData.updatePriceElectricalVms.push(this.dboClass);
           // colValId(resultSetMsg.getInt("VMS_ID"));//error
           // typeOfPanel(resultSetMsg.getString("TYPE_OF_PANEL"));
           // frameId(resultSetMsg.getInt("FRM_ID"));
           // frameName(resultSetMsg.getString("FRM_NM"));
           // addPrbFlag(resultSetMsg.getInt("ADD_PRB_FLG"));
           // type(resultSetMsg.getString("TYPE"));
           // make(resultSetMsg.getString("MAKE"));
           // altMake(resultSetMsg.getString("ALT_MAKE"));
           // gearbox(resultSetMsg.getString("GEAR_BOX"));
           // cost(Math.round(resultSetMsg.getFloat("COST")));
           // defaultFlagNew(resultSetMsg.getInt("DEFLT_FLG"));//errror
           // note(resultSetMsg.getString("NOTE"));
           // approxCostFlag(resultSetMsg.getInt("APPROX_COST_FLG"));
           //activeNew// not there
   
           }
           else  if(this.dropdowndefault=="Set of STG Instruments List")
        {

        
        this.dboClass.updateCode = "INSTRUMENT_LIST1";
        this.dboClass.instrId = this.finalFrameList[v].instrId;
        this.dboClass.category =this.finalFrameList[v].category ;	
        this.dboClass.make = this.finalFrameList[v].make;
        this.dboClass.typeOfGov = this.finalFrameList[v].typeOfGov;
        this.dboClass.itemId = this.finalFrameList[v].itemId ;
        this.dboClass.turbCode = this.finalFrameList[v].turbCode ;
        this.dboClass.condensingTypeId = this.finalFrameList[v].condensingTypeId;
        this.dboClass.typeOfInstr = this.finalFrameList[v].typeOfInstr
        this.dboClass.instrTypeNm = this.finalFrameList[v].instrTypeNm;
        this.dboClass.instrDesc = this.finalFrameList[v].instrDesc;
        this.dboClass.instrMounting = this.finalFrameList[v].instrMounting;
        this.dboClass.quantity1001Logic = this.finalFrameList[v].quantity1001Logic;
        this.dboClass.quantity1002Logic = this.finalFrameList[v].quantity1002Logic;
        this.dboClass.quantity2003Logic = this.finalFrameList[v].quantity2003Logic;
        this.dboClass.cost1001 = this.finalFrameList[v].cost1001;
        this.dboClass.approx1001Flag = this.finalFrameList[v].approx1001Flag;
        this.dboClass.cost1002 = this.finalFrameList[v].cost1002;
        this.dboClass.approx1002Flag = this.finalFrameList[v].approx1002Flag;
        this.dboClass.cost2003 = this.finalFrameList[v].cost2003;
        this.dboClass.approx20003Flag = this.finalFrameList[v].approx20003Flag;
        this.dboClass.costCe1001 = this.finalFrameList[v].costCe1002;
        this.dboClass.approx1001CeFlag = this.finalFrameList[v].approx1002CeFlag;
        this.dboClass.costCe1002 = this.finalFrameList[v].costCe1002;
        this.dboClass.approx1002CeFlag = this.finalFrameList[v].approx1002CeFlag;
        this.dboClass.costCe2003 = this.finalFrameList[v].costCe2003;
        this.dboClass.approx20003CeFlag = this.finalFrameList[v].approx20003CeFlag;
        this.dboClass.activeNew =  this.finalFrameList[v].activeNew; 


        // instrId(resultSetMsg.getInt("INSTR_ID"));
        // categoryNm(resultSetMsg.getString("CATEGORY"));// error
        // make(resultSetMsg.getString("MAKE"));
        // typeOfGov(resultSetMsg.getString("TYPE_OF_GOV"));
        // itemId(resultSetMsg.getInt("ITEM_ID"));
        // itemName(resultSetMsg.getString("ITEM_NAME"));
        // turbCode(resultSetMsg.getString("TURB_CD"));
        // turbCodeNm(resultSetMsg.getString("TURB_CD_NAME"));
        // condensingTypeId(resultSetMsg.getInt("COND_TYP_ID"));
        // condensingTypeName(resultSetMsg.getString("COND_TYP_NAME"));
        // typeOfInstr(resultSetMsg.getString("TYPE_OF_INSTR"));
        // instrTypeNm(resultSetMsg.getString("INSTR_TYPE_NM"));
        // instrDesc(resultSetMsg.getString("INSTR_DESC"));
        // instrMounting(resultSetMsg.getString("INSTR_MOUNTING"));
        // quantity1001Logic(resultSetMsg.getInt("QTY_1001_LOGIC"));
        // quantity1002Logic(resultSetMsg.getInt("QTY_1002_LOGIC"));
        // quantity2003Logic(resultSetMsg.getInt("QTY_2003_LOGIC"));
        // cost1001(Math.round(resultSetMsg.getInt("COST_1001")));
        // approx1001Flag(resultSetMsg.getInt("APPROX_1001_FLG"));
        // cost1002(Math.round(resultSetMsg.getInt("COST_1002")));
        // approx1002Flag(resultSetMsg.getInt("APPROX_1002_FLG"));
        // cost2003(Math.round(resultSetMsg.getInt("COST_2003")));
        // approx20003Flag(resultSetMsg.getInt("APPROX_2003_FLG"));
        // costCe1001(Math.round(resultSetMsg.getInt("COST_CE_1001")));
        // approx1001CeFlag(resultSetMsg.getInt("APPROX_1001_CE_FLG"));
        // costCe1002(resultSetMsg.getInt("COST_CE_1002"));
        // approx1002CeFlag(resultSetMsg.getInt("APPROX_1002_CE_FLG"));
        // costCe2003(Math.round(resultSetMsg.getInt("COST_CE_2003")));
        // approx20003CeFlag(resultSetMsg.getInt("APPROX_2003_CE_FLG"));
       // active new not there 
        this.dboFormData.updatePriceElectricalInstr.push(this.dboClass);
        }
        
        else if(this.dropdowndefault=="Set of STG Instruments List(Auto Spray)")
        {

          this.dboClass.updateCode = "INSTRUMENT_LIST2";
          this.dboClass.instrId = this.finalFrameList[v].id;
          this.dboClass.category = null ;	
          this.dboClass.make = null;
          this.dboClass.typeOfGov = null;
          this.dboClass.itemId = this.finalFrameList[v].itemId ;
          this.dboClass.turbCode = null ;
          this.dboClass.condensingTypeId = this.finalFrameList[v].frameId;
          this.dboClass.typeOfInstr = this.finalFrameList[v].typeOfInstr
          this.dboClass.instrTypeNm = null;
          this.dboClass.instrDesc = null;
          this.dboClass.instrMounting = null;
          this.dboClass.quantity1001Logic = null;
          this.dboClass.quantity1002Logic = null;
          this.dboClass.quantity2003Logic = null;
          this.dboClass.cost1001 = this.finalFrameList[v].cost1001;
          this.dboClass.approx1001Flag = this.finalFrameList[v].approx1001Flag;
          this.dboClass.cost1002 = this.finalFrameList[v].cost1002;
          this.dboClass.approx1002Flag = this.finalFrameList[v].approx1002Flag;
          this.dboClass.cost2003 = this.finalFrameList[v].cost2003;
          this.dboClass.approx20003Flag = this.finalFrameList[v].approx20003Flag;
          this.dboClass.costCe1001 = this.finalFrameList[v].costCe1002;
          this.dboClass.approx1001CeFlag = this.finalFrameList[v].approx1002CeFlag;
          this.dboClass.costCe1002 = this.finalFrameList[v].costCe1002;
          this.dboClass.approx1002CeFlag = this.finalFrameList[v].approx1002CeFlag;
          this.dboClass.costCe2003 = this.finalFrameList[v].costCe2003;
          this.dboClass.approx20003CeFlag = this.finalFrameList[v].approx20003CeFlag;
          this.dboClass.activeNew =  this.finalFrameList[v].activeNew;


        // instrId(resultSetMsg.getInt("INSTR_ID"));
        // categoryNm(resultSetMsg.getString("CATEGORY"));// error
        // make(resultSetMsg.getString("MAKE"));
        // typeOfGov(resultSetMsg.getString("TYPE_OF_GOV"));
        // itemId(resultSetMsg.getInt("ITEM_ID"));
        // itemName(resultSetMsg.getString("ITEM_NAME"));
        // turbCode(resultSetMsg.getString("TURB_CD"));
        // turbCodeNm(resultSetMsg.getString("TURB_CD_NAME"));
        // condensingTypeId(resultSetMsg.getInt("COND_TYP_ID"));
        // condensingTypeName(resultSetMsg.getString("COND_TYP_NAME"));
        // typeOfInstr(resultSetMsg.getString("TYPE_OF_INSTR"));
        // instrTypeNm(resultSetMsg.getString("INSTR_TYPE_NM"));
        // instrDesc(resultSetMsg.getString("INSTR_DESC"));
        // instrMounting(resultSetMsg.getString("INSTR_MOUNTING"));
        // quantity1001Logic(resultSetMsg.getInt("QTY_1001_LOGIC"));
        // quantity1002Logic(resultSetMsg.getInt("QTY_1002_LOGIC"));
        // quantity2003Logic(resultSetMsg.getInt("QTY_2003_LOGIC"));
        // cost1001(Math.round(resultSetMsg.getInt("COST_1001")));
        // approx1001Flag(resultSetMsg.getInt("APPROX_1001_FLG"));
        // cost1002(Math.round(resultSetMsg.getInt("COST_1002")));
        // approx1002Flag(resultSetMsg.getInt("APPROX_1002_FLG"));
        // cost2003(Math.round(resultSetMsg.getInt("COST_2003")));
        // approx20003Flag(resultSetMsg.getInt("APPROX_2003_FLG"));
        // costCe1001(Math.round(resultSetMsg.getInt("COST_CE_1001")));
        // approx1001CeFlag(resultSetMsg.getInt("APPROX_1001_CE_FLG"));
        // costCe1002(resultSetMsg.getInt("COST_CE_1002"));
        // approx1002CeFlag(resultSetMsg.getInt("APPROX_1002_CE_FLG"));
        // costCe2003(Math.round(resultSetMsg.getInt("COST_CE_2003")));
        // approx20003CeFlag(resultSetMsg.getInt("APPROX_2003_CE_FLG"));
       // active new not there 
        this.dboFormData.updatePriceElectricalInstr.push(this.dboClass);
        }
        else if(this.dropdowndefault=="AdditionalProbes")
        {

          this.dboClass.updateCode = "INSTRUMENT_LIST3";
          this.dboClass.instrId = this.finalFrameList[v].addInstrId;
          this.dboClass.category = null ;	
          this.dboClass.make = null;
          this.dboClass.typeOfGov = null;
          this.dboClass.itemId = this.finalFrameList[v].itemId ;
          this.dboClass.turbCode = null ;
          this.dboClass.condensingTypeId =null ;
          this.dboClass.typeOfInstr = this.finalFrameList[v].addInstrCd;
          this.dboClass.instrTypeNm = this.finalFrameList[v].addInstrNm;
          this.dboClass.instrDesc = null;
          this.dboClass.instrMounting = null;
          this.dboClass.quantity1001Logic = null;
          this.dboClass.quantity1002Logic = null;
          this.dboClass.quantity2003Logic = null;
          this.dboClass.cost1001 = this.finalFrameList[v].cost;
          this.dboClass.approx1001Flag = null;
          this.dboClass.cost1002 = null;
          this.dboClass.approx1002Flag = null;
          this.dboClass.cost2003 = null;
          this.dboClass.approx20003Flag = null;
          this.dboClass.costCe1001 = null;
          this.dboClass.approx1001CeFlag = null;
          this.dboClass.costCe1002 = null;
          this.dboClass.approx1002CeFlag = null;
          this.dboClass.costCe2003 = null;
          this.dboClass.approx20003CeFlag = null;
          this.dboClass.activeNew = null;


        // instrId(resultSetMsg.getInt("INSTR_ID"));
        // categoryNm(resultSetMsg.getString("CATEGORY"));// error
        // make(resultSetMsg.getString("MAKE"));
        // typeOfGov(resultSetMsg.getString("TYPE_OF_GOV"));
        // itemId(resultSetMsg.getInt("ITEM_ID"));
        // itemName(resultSetMsg.getString("ITEM_NAME"));
        // turbCode(resultSetMsg.getString("TURB_CD"));
        // turbCodeNm(resultSetMsg.getString("TURB_CD_NAME"));
        // condensingTypeId(resultSetMsg.getInt("COND_TYP_ID"));
        // condensingTypeName(resultSetMsg.getString("COND_TYP_NAME"));
        // typeOfInstr(resultSetMsg.getString("TYPE_OF_INSTR"));
        // instrTypeNm(resultSetMsg.getString("INSTR_TYPE_NM"));
        // instrDesc(resultSetMsg.getString("INSTR_DESC"));
        // instrMounting(resultSetMsg.getString("INSTR_MOUNTING"));
        // quantity1001Logic(resultSetMsg.getInt("QTY_1001_LOGIC"));
        // quantity1002Logic(resultSetMsg.getInt("QTY_1002_LOGIC"));
        // quantity2003Logic(resultSetMsg.getInt("QTY_2003_LOGIC"));
        // cost1001(Math.round(resultSetMsg.getInt("COST_1001")));
        // approx1001Flag(resultSetMsg.getInt("APPROX_1001_FLG"));
        // cost1002(Math.round(resultSetMsg.getInt("COST_1002")));
        // approx1002Flag(resultSetMsg.getInt("APPROX_1002_FLG"));
        // cost2003(Math.round(resultSetMsg.getInt("COST_2003")));
        // approx20003Flag(resultSetMsg.getInt("APPROX_2003_FLG"));
        // costCe1001(Math.round(resultSetMsg.getInt("COST_CE_1001")));
        // approx1001CeFlag(resultSetMsg.getInt("APPROX_1001_CE_FLG"));
        // costCe1002(resultSetMsg.getInt("COST_CE_1002"));
        // approx1002CeFlag(resultSetMsg.getInt("APPROX_1002_CE_FLG"));
        // costCe2003(Math.round(resultSetMsg.getInt("COST_CE_2003")));
        // approx20003CeFlag(resultSetMsg.getInt("APPROX_2003_CE_FLG"));
       // active new not there 
        this.dboFormData.updatePriceElectricalInstr3.push(this.dboClass);
        }
        else if(this.dropdowndefault=="Main Price")
        {
          this.dboClass.updateCode = "ELE_PRICE";
          this.dboClass.priceId = this.finalFrameList[v].priceId;
          this.dboClass.itemId = this.finalFrameList[v].itemId ;//45
          this.dboClass.subItemId =this.finalFrameList[v].subItemId ;//0
          this.dboClass.typeOfPanel = this.defaultvalue ;
          this.dboClass.make = this.finalFrameList[v].make;//TDPS
          this.dboClass.custType =this.finalFrameList[v].custType ;
          this.dboClass.priceCode = this.finalFrameList[v].priceCode;
          this.dboClass.price =Number(this.finalFrameList[v].price15);
          this.dboClass.approxCostFlag = this.finalFrameList[v].approxCostFlag;
          this.dboClass.itemErrMessage = this.finalFrameList[v].itemErrMessage;
          this.dboClass.activeNew =  this.finalFrameList[v].activeNew; 
          this.dboFormData.updatePriceElectricalList.push(this.dboClass);
          // bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
					// 			priceId(resultSetOtherDatam.getInt("PRICE_ID"));
					// 			itemId(resultSetOtherDatam.getInt("ITEM_ID"));
					// 			itemName(resultSetOtherDatam.getString("ITEM_NAME"));
					// 			subItemId(resultSetOtherDatam.getInt("SUB_ITEM_ID"));
					// 			subItemName(resultSetOtherDatam.getString("SUB_ITEM_NAME"));
					// 			typeOfPanel(resultSetOtherDatam.getString("TYPE_OF_PANEL"));
					// 			make(resultSetOtherDatam.getString("MAKE"));
					// 			custType(resultSetOtherDatam.getString("CUST_TYPE"));
					// 			priceCode(resultSetOtherDatam.getString("PRICE_CODE"));
					// 			approxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
					// 			itemErrMessage(resultSetOtherDatam.getString("ITEM_ERROR_MSG"));
					// 			price15(resultSetOtherDatam.getInt("PRICE_15"));
					// 			prevPrice(resultSetOtherDatam.getInt("PREV_PRICE"));
					// 			prevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
					// 			statusId(resultSetOtherDatam.getInt("STATUS_ID"));
					// 			statusName(resultSetOtherDatam.getString("STATUS_NAME"));
					// 			assingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
					// 			assingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
					// 			modifiedDate(resultSetOtherDatam.getString("MOD_DT"));
					// 			createdById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
					// 			createdBy(resultSetOtherDatam.getString("CREATED_BY"));
					// 			activeNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

        }
        else if(this.dropdowndefault=="Addon depending on turbine MW Price")
        {
          this.dboClass.updateCode = "ELE_ADDON_COST_UPD";
          this.dboClass.priceId = this.finalFrameList[v].priceId;
          this.dboClass.itemId = this.finalFrameList[v].itemId ;
          this.dboClass.typeOfPanel = this.defaultvalue ;
		      this.dboClass.custType =this.finalFrameList[v].custType ;
          this.dboClass.make = this.finalFrameList[v].make;
		      this.dboClass.colId = this.finalFrameList[v].colId;
          this.dboClass.colValCd = this.finalFrameList[v].colValCd;
          this.dboClass.subColValCd = this.finalFrameList[v].subColValCd;
          this.dboClass.addOnCostCol = this.finalFrameList[v].addOnCostCol;
          this.dboClass.minVal = this.finalFrameList[v].minVal;
          this.dboClass.maxVal = this.finalFrameList[v].maxVal;
          this.dboClass.addOnDirCost =this.finalFrameList[v].addOnDirCost ;
          this.dboClass.addOnCostPer = 0;
          this.dboClass.approxCostFlag = this.finalFrameList[v].approxCostFlag;
          this.dboClass.errorMsg = this.finalFrameList[v].errorMsg;
          this.dboClass.activeNew =  this.finalFrameList[v].activeNew; 
          this.dboFormData.updatePriceElectricalAddOnList3.push(this.dboClass);
          // priceId(resultSetMsg.getInt("PRICE_ID"));
					// itemId(resultSetMsg.getInt("ITEM_ID"));
					// itemName(resultSetMsg.getString("ITEM_NAME"));
					// typeOfOPanel(resultSetMsg.getString("TYPE_OF_PANEL"));///error
					// custType(resultSetMsg.getString("CUST_TYPE"));
					// make(resultSetMsg.getString("MAKE"));
					// colId(resultSetMsg.getInt("COL_ID"));
					// colValCd(resultSetMsg.getString("COL_VAL_CD"));
					// subColValCd(resultSetMsg.getString("SUB_COL_VAL_CD"));
					// addOnCostCol(resultSetMsg.getString("ADDON_COST_COL"));
					// minVal(resultSetMsg.getFloat("MIN_VAL"));
					// maxVal(resultSetMsg.getFloat("MAX_VAL"));

					// addOnDirCost(resultSetMsg.getFloat("ADDON_DIR_COST"));
					// approxCostFlag(resultSetMsg.getInt("APPROX_COST_FLG"));
					// errorMsg(resultSetMsg.getString("ERROR_MSG"));
					// activeNew(resultSetMsg.getInt("IS_ACTIVE"));
        }
        else if(this.dropdowndefault=="Addon Price")
        {

          this.dboClass.updateCode = "ELE_COL_VAL";
          this.dboClass.colValId = this.finalFrameList[v].colValId;
            this.dboClass.typeOfPanel =this.finalFrameList[v].typeOfPanel ;	 
            this.dboClass.itemId = this.finalFrameList[v].itemId ;
        this.dboClass.subItemId = this.finalFrameList[v].subItemId ;
            this.dboClass.make = this.finalFrameList[v].make;
        this.dboClass.colId = this.finalFrameList[v].colId;
        this.dboClass.colValCd= this.finalFrameList[v].colValCd;

        this.dboClass.colValNm = this.finalFrameList[v].colValNm;
            this.dboClass.colValCdSym = this.finalFrameList[v].colValCdSym;//string
            this.dboClass.subColValFlg = this.finalFrameList[v].subColValFlg; // interger
        this.dboClass.subColValNm = this.finalFrameList[v].subColValNm;//string
        this.dboClass.minVal = this.finalFrameList[v].minVal;//number
        this.dboClass.maxVal = this.finalFrameList[v].maxVal;//number
        this.dboClass.defaultFlag15 = this.finalFrameList[v].defaultFlag15;//int
        this.dboClass.defaultFlag30 = this.finalFrameList[v].defaultFlag30;//int
        this.dboClass.orderId = this.finalFrameList[v].orderId;//int
        this.dboClass.addOnFlg = this.finalFrameList[v].addOnFlg;//int
        this.dboClass.addOnDiffDs =this.finalFrameList[v].addOnDiffDs ;//int
          this.dboClass.addOnCostPer = this.finalFrameList[v].addOnCostPer;//int
        this.dboClass.addOnDirCost = this.finalFrameList[v].addOnDirCost;//int
            this.dboClass.approxCostFlag = this.finalFrameList[v].approxCostFlag;//int
            this.dboClass.calculateLinkFlag = this.finalFrameList[v].calculateLinkFlag;//int
            this.dboClass.techFlag = this.finalFrameList[v].techFlag;//int
            this.dboClass.comrFlag = this.finalFrameList[v].comrFlag;//int
        this.dboClass.dispInd = this.finalFrameList[v].dispInd;//int
            this.dboClass.disableColValCd = this.finalFrameList[v].disableColValCd;//string
            this.dboClass.delColFlag = this.finalFrameList[v].delColFlag;//int
            this.dboClass.inputCostFlag = this.finalFrameList[v].inputCostFlag;//int
            this.dboClass.activeNew =  this.finalFrameList[v].activeNew; //int
            this.dboFormData.updatePriceElectricalColVal.push(this.dboClass);

            // colValId(resultSetMsg.getInt("COL_VAL_ID"));
            // typeOfPanel(resultSetMsg.getString("TYPE_OF_PANEL"));
            // itemId(resultSetMsg.getInt("ITEM_ID"));
            // itemName(resultSetMsg.getString("ITEM_NAME"));
            // subItemId(resultSetMsg.getInt("SUB_ITEM_ID"));
            // subItemName(resultSetMsg.getString("SUB_ITEM_NAME"));
            // make(resultSetMsg.getString("MAKE"));
            // colId(resultSetMsg.getInt("COL_ID"));
            // colNm(resultSetMsg.getString("COL_NM"));
            // colValCd(resultSetMsg.getString("COL_VAL_CD"));
            // colValNm(resultSetMsg.getString("COL_VAL_NM"));
            // colValCdSym(resultSetMsg.getString("COL_VAL_CD_SYM"));
            // subColValFlg(resultSetMsg.getInt("SUB_COL_VAL_FLG")); 
            // subColValNm(resultSetMsg.getString("SUB_COL_VAL_NM"));
            // minVal(resultSetMsg.getFloat("MIN_VAL"));
            // maxVal(resultSetMsg.getFloat("MAX_VAL"));
            // defaultFlag15(resultSetMsg.getInt("DEFLT_FLG_15"));
            // defaultFlag30(resultSetMsg.getInt("DEFLT_FLG_30"));
            // orderId(resultSetMsg.getInt("ORDER_ID"));
            // addOnFlg(resultSetMsg.getInt("ADD_ON_FLG"));
            // addOnDiffDs(resultSetMsg.getInt("ADD_ON_DIFF_DS"));
            // addOnCostPer(resultSetMsg.getFloat("ADDON_COST_PER"));
            // addOnDirCost(resultSetMsg.getFloat("ADDON_DIR_COST"));
            // approxCostFlag(resultSetMsg.getInt("APPROX_COST_FLG"));
            // calculateLinkFlag(resultSetMsg.getInt("CALC_LINK_FLG") );
            // techFlag(resultSetMsg.getInt("TECH_FLG"));
            // comrFlag(resultSetMsg.getInt("COMR_FLG"));
            // dispInd(resultSetMsg.getInt("DISP_IND"));
            // disableColValCd(resultSetMsg.getString("DISABLE_COL_VA_CD"));
            // delColFlag(resultSetMsg.getInt("DEL_COL_FLG"));
            // inputCostFlag(resultSetMsg.getInt("INPUT_COST_FLG"));
            // activeNew(resultSetMsg.getInt("IS_ACTIVE")); 
        }
            
       
}
}
 if(this.itemNameTemp=="Turbine Vibration Monitoring System (VMS)")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleVms(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
    this._ItoUpdateCostDBOElecPrice.updateStatus(res).subscribe(respon => {
      console.log(respon);
      this.successMsg.push(respon.successMsg);
      if(this.storage.get(this.currentRole)=="DBO_ELE_APPROVER")     {
         this._ItoUpdateCostDBOElecPrice.saveUpdatedPackagePrice(res).subscribe(respon => {
        });
        this.successMsg.push(respon.successMsg);
  
      }
    });
  });
}
else if(this.dropdowndefault=="Set of STG Instruments List")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleInstr(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
    this._ItoUpdateCostDBOElecPrice.updateStatus(res).subscribe(respon => {
      console.log(respon);
      this.successMsg.push(respon.successMsg);
      if(this.storage.get(this.currentRole)=="DBO_ELE_APPROVER")     {
         this._ItoUpdateCostDBOElecPrice.saveUpdatedPackagePrice(res).subscribe(respon => {
        });
        this.successMsg.push(respon.successMsg);
  
      }
    });
  });
}
else if(this.dropdowndefault=="Set of STG Instruments List(Auto Spray)")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleInstr1(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
    this._ItoUpdateCostDBOElecPrice.updateStatus(res).subscribe(respon => {
      console.log(respon);
      this.successMsg.push(respon.successMsg);
      if(this.storage.get(this.currentRole)=="DBO_ELE_APPROVER")     {
         this._ItoUpdateCostDBOElecPrice.saveUpdatedPackagePrice(res).subscribe(respon => {
        });
        this.successMsg.push(respon.successMsg);
  
      }
    });
  });
}
else if(this.dropdowndefault=="AdditionalProbes")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleInstr3(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
    this._ItoUpdateCostDBOElecPrice.updateStatus(res).subscribe(respon => {
      console.log(respon);
      this.successMsg.push(respon.successMsg);
      if(this.storage.get(this.currentRole)=="DBO_ELE_APPROVER")     {
         this._ItoUpdateCostDBOElecPrice.saveUpdatedPackagePrice(res).subscribe(respon => {
        });
        this.successMsg.push(respon.successMsg);
  
      }
    });
  });
}
else if(this.dropdowndefault=="Main Price")
{
  this._ItoUpdateCostDBOElecPrice.updateCreateElePrice(this.dboFormData).subscribe(res => {
    this.displayMessage = true;
    console.log(res);
    this.successMsg.push(res.successMsg);

    this._ItoUpdateCostDBOElecPrice.updateStatus(res).subscribe(respon => {
      console.log(respon);
      this.successMsg.push(respon.successMsg);
      if(this.storage.get(this.currentRole)=="DBO_ELE_APPROVER")     {
       this._ItoUpdateCostDBOElecPrice.saveUpdatedPackagePrice(res).subscribe(respon => {
      });
      this.successMsg.push(respon.successMsg);


    }
    });
  });
}
else if(this.dropdowndefault=="Addon depending on turbine MW Price")
{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleAddOnCost(this.dboFormData).subscribe(res => {
    console.log(res); 
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
    this._ItoUpdateCostDBOElecPrice.updateStatus(res).subscribe(respon => {
      console.log(respon);
      this.successMsg.push(respon.successMsg);
      if(this.storage.get(this.currentRole)=="DBO_ELE_APPROVER")     {
         this._ItoUpdateCostDBOElecPrice.saveUpdatedPackagePrice(res).subscribe(respon => {
        });
        this.successMsg.push(respon.successMsg);
  
      }
    });
  });
}
else if(this.dropdowndefault=="Addon Price")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleColVal(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;
    this.successMsg.push(res.successMsg);
    this._ItoUpdateCostDBOElecPrice.updateStatus(res).subscribe(respon => {
      console.log(respon);
      this.successMsg.push(respon.successMsg);
      if(this.storage.get(this.currentRole)=="DBO_ELE_APPROVER")     {
         this._ItoUpdateCostDBOElecPrice.saveUpdatedPackagePrice(res).subscribe(respon => {
        });
        this.successMsg.push(respon.successMsg);
  
      }
    });
  });
}

}
SaveAsDraft()
{
  this.successMsg = [];

  this.dboFormData.updatePriceElectricalList=[];
  this.dboFormData.updatePriceElectricalAddOnList=[];
  this.dboFormData.updatePriceElectricalColVal=[];
  this.dboFormData.updatePriceElectricalInstr=[];
  this.dboFormData.updatePriceElectricalVms=[];


//   bean.getUpdateRequestNumber(), 
// bean.getUpdateCode(),
// bean.getPriceId(),
// bean.getItemId(),
// bean.getSubItemId(),
// bean.getTypeOfPanel(),
// bean.getMake(),
// bean.getCustType(), 
// bean.getPriceCode(), 
// bean.getPrice(),
// bean.getApproxCostFlag(),
// bean.getItemErrMessage(),
// bean.isActive())
this.dboFormData.saveBasicDetails.modifiedById = this.modifiedBy; 
this.dboFormData.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
this.dboFormData.saveBasicDetails.remarks = this.remarkss; 
console.log(this.dboFormData.modifiedById);
console.log(this.dboFormData.userRoleId);
console.log(this.dboFormData.loggedInUserCode);




//   Main Price --'ELE_PRICE'
// Addon Price --- 'ELE_COL_VAL'
// Addon depending on turbine MW Price-- 'ELE_ADDON_COST'
for (let v = 0; v < this.finalFrameList.length; v++) {
    if (this.finalFrameList[v] != undefined) {
           this.dboClass = new dboClass();
            if(this.itemNameTemp=="Turbine Vibration Monitoring System (VMS)")
           {
   
           
           this.dboClass.updateCode = "VMS_FRM_LIST";
           this.dboClass.vmsId = this.finalFrameList[v].vmsId;
           this.dboClass.typeOfPanel = this.finalFrameList[v].typeOfPanel;
           this.dboClass.frameId = this.finalFrameList[v].frameId;
           this.dboClass.addPrbFlag = this.finalFrameList[v].addPrbFlag;
           this.dboClass.type = this.finalFrameList[v].type;
           this.dboClass.typeDefaultFlag = this.finalFrameList[v].typeDefaultFlag;
           this.dboClass.make = this.finalFrameList[v].make;
           this.dboClass.makeDefaultFlag = this.finalFrameList[v].makeDefaultFlag;
           this.dboClass.altMake = this.finalFrameList[v].altMake;
           this.dboClass.altMakeDefaultFlag = this.finalFrameList[v].altMakeDefaultFlag;
           this.dboClass.gearbox = this.finalFrameList[v].gearbox;
           this.dboClass.gearBoxDefaultFlag = this.finalFrameList[v].gearBoxDefaultFlag;
           this.dboClass.cost = this.finalFrameList[v].cost;
           this.dboClass.note = this.finalFrameList[v].note;
           this.dboClass.approxCostFlag = this.finalFrameList[v].approxCostFlag;
           this.dboClass.activeNew = this.finalFrameList[v].activeNew;
           this.dboFormData.updatePriceElectricalVms.push(this.dboClass);
   
           }
            else if(this.dropdowndefault=="Set of STG Instruments List")
        {

        
        this.dboClass.updateCode = "INSTRUMENT_LIST1";
        this.dboClass.instrId = this.finalFrameList[v].instrId;
        this.dboClass.category =this.finalFrameList[v].category ;	
        this.dboClass.make = this.finalFrameList[v].make;
        this.dboClass.typeOfGov = this.finalFrameList[v].typeOfGov;
        this.dboClass.itemId = this.finalFrameList[v].itemId ;
        this.dboClass.turbCode = this.finalFrameList[v].turbCode ;
        this.dboClass.condensingTypeId = this.finalFrameList[v].condensingTypeId;
        this.dboClass.typeOfInstr = this.finalFrameList[v].typeOfInstr
        this.dboClass.instrTypeNm = this.finalFrameList[v].instrTypeNm;
        this.dboClass.instrDesc = this.finalFrameList[v].instrDesc;
        this.dboClass.instrMounting = this.finalFrameList[v].instrMounting;
        this.dboClass.quantity1001Logic = this.finalFrameList[v].quantity1001Logic;
        this.dboClass.quantity1002Logic = this.finalFrameList[v].quantity1002Logic;
        this.dboClass.quantity2003Logic = this.finalFrameList[v].quantity2003Logic;
        this.dboClass.cost1001 = this.finalFrameList[v].cost1001;
        this.dboClass.approx1001Flag = this.finalFrameList[v].approx1001Flag;
        this.dboClass.cost1002 = this.finalFrameList[v].cost1002;
        this.dboClass.approx1002Flag = this.finalFrameList[v].approx1002Flag;
        this.dboClass.cost2003 = this.finalFrameList[v].cost2003;
        this.dboClass.approx20003Flag = this.finalFrameList[v].approx20003Flag;
        this.dboClass.costCe1001 = this.finalFrameList[v].costCe1002;
        this.dboClass.approx1001CeFlag = this.finalFrameList[v].approx1002CeFlag;
        this.dboClass.costCe1002 = this.finalFrameList[v].costCe1002;
        this.dboClass.approx1002CeFlag = this.finalFrameList[v].approx1002CeFlag;
        this.dboClass.costCe2003 = this.finalFrameList[v].costCe2003;
        this.dboClass.approx20003CeFlag = this.finalFrameList[v].approx20003CeFlag;
        this.dboClass.activeNew =  this.finalFrameList[v].activeNew; 
        this.dboFormData.updatePriceElectricalInstr.push(this.dboClass);
        }
        else if(this.dropdowndefault=="Set of STG Instruments List(Auto Spray)")
        {

        
        this.dboClass.updateCode = "INSTRUMENT_LIST2";
        this.dboClass.instrId = this.finalFrameList[v].id;
        this.dboClass.category = null ;	
        this.dboClass.make = null;
        this.dboClass.typeOfGov = null;
        this.dboClass.itemId = this.finalFrameList[v].itemId ;
        this.dboClass.turbCode = null ;
        this.dboClass.condensingTypeId = this.finalFrameList[v].frameId;
        this.dboClass.typeOfInstr = this.finalFrameList[v].typeOfInstr
        this.dboClass.instrTypeNm = null;
        this.dboClass.instrDesc = null;
        this.dboClass.instrMounting = null;
        this.dboClass.quantity1001Logic = null;
        this.dboClass.quantity1002Logic = null;
        this.dboClass.quantity2003Logic = null;
        this.dboClass.cost1001 = this.finalFrameList[v].cost1001;
        this.dboClass.approx1001Flag = this.finalFrameList[v].approx1001Flag;
        this.dboClass.cost1002 = this.finalFrameList[v].cost1002;
        this.dboClass.approx1002Flag = this.finalFrameList[v].approx1002Flag;
        this.dboClass.cost2003 = this.finalFrameList[v].cost2003;
        this.dboClass.approx20003Flag = this.finalFrameList[v].approx20003Flag;
        this.dboClass.costCe1001 = this.finalFrameList[v].costCe1002;
        this.dboClass.approx1001CeFlag = this.finalFrameList[v].approx1002CeFlag;
        this.dboClass.costCe1002 = this.finalFrameList[v].costCe1002;
        this.dboClass.approx1002CeFlag = this.finalFrameList[v].approx1002CeFlag;
        this.dboClass.costCe2003 = this.finalFrameList[v].costCe2003;
        this.dboClass.approx20003CeFlag = this.finalFrameList[v].approx20003CeFlag;
        this.dboClass.activeNew =  this.finalFrameList[v].activeNew; 


        // instrId(resultSetMsg.getInt("INSTR_ID"));
        // categoryNm(resultSetMsg.getString("CATEGORY"));// error
        // make(resultSetMsg.getString("MAKE"));
        // typeOfGov(resultSetMsg.getString("TYPE_OF_GOV"));
        // itemId(resultSetMsg.getInt("ITEM_ID"));
        // itemName(resultSetMsg.getString("ITEM_NAME"));
        // turbCode(resultSetMsg.getString("TURB_CD"));
        // turbCodeNm(resultSetMsg.getString("TURB_CD_NAME"));
        // condensingTypeId(resultSetMsg.getInt("COND_TYP_ID"));
        // condensingTypeName(resultSetMsg.getString("COND_TYP_NAME"));
        // typeOfInstr(resultSetMsg.getString("TYPE_OF_INSTR"));
        // instrTypeNm(resultSetMsg.getString("INSTR_TYPE_NM"));
        // instrDesc(resultSetMsg.getString("INSTR_DESC"));
        // instrMounting(resultSetMsg.getString("INSTR_MOUNTING"));
        // quantity1001Logic(resultSetMsg.getInt("QTY_1001_LOGIC"));
        // quantity1002Logic(resultSetMsg.getInt("QTY_1002_LOGIC"));
        // quantity2003Logic(resultSetMsg.getInt("QTY_2003_LOGIC"));
        // cost1001(Math.round(resultSetMsg.getInt("COST_1001")));
        // approx1001Flag(resultSetMsg.getInt("APPROX_1001_FLG"));
        // cost1002(Math.round(resultSetMsg.getInt("COST_1002")));
        // approx1002Flag(resultSetMsg.getInt("APPROX_1002_FLG"));
        // cost2003(Math.round(resultSetMsg.getInt("COST_2003")));
        // approx20003Flag(resultSetMsg.getInt("APPROX_2003_FLG"));
        // costCe1001(Math.round(resultSetMsg.getInt("COST_CE_1001")));
        // approx1001CeFlag(resultSetMsg.getInt("APPROX_1001_CE_FLG"));
        // costCe1002(resultSetMsg.getInt("COST_CE_1002"));
        // approx1002CeFlag(resultSetMsg.getInt("APPROX_1002_CE_FLG"));
        // costCe2003(Math.round(resultSetMsg.getInt("COST_CE_2003")));
        // approx20003CeFlag(resultSetMsg.getInt("APPROX_2003_CE_FLG"));
       // active new not there 
        this.dboFormData.updatePriceElectricalInstr.push(this.dboClass);
        }
        else if(this.dropdowndefault=="AdditionalProbes")
        {

        
        this.dboClass.updateCode = "INSTRUMENT_LIST3";
        this.dboClass.instrId = this.finalFrameList[v].addInstrId;
        this.dboClass.category = null ;	
        this.dboClass.make = null;
        this.dboClass.typeOfGov = null;
        this.dboClass.itemId = this.finalFrameList[v].itemId ;
        this.dboClass.turbCode = null ;
        this.dboClass.condensingTypeId = this.finalFrameList[v].addInstrCd;
        this.dboClass.typeOfInstr = this.finalFrameList[v].addInstrNm
        this.dboClass.instrTypeNm = null;
        this.dboClass.instrDesc = null;
        this.dboClass.instrMounting = null;
        this.dboClass.quantity1001Logic = null;
        this.dboClass.quantity1002Logic = null;
        this.dboClass.quantity2003Logic = null;
        this.dboClass.cost1001 = this.finalFrameList[v].cost;
        this.dboClass.approx1001Flag = null;
        this.dboClass.cost1002 = null;
        this.dboClass.approx1002Flag = null;
        this.dboClass.cost2003 = null;
        this.dboClass.approx20003Flag = null;
        this.dboClass.costCe1001 = null;
        this.dboClass.approx1001CeFlag = null;
        this.dboClass.costCe1002 = null;
        this.dboClass.approx1002CeFlag = null;
        this.dboClass.costCe2003 = null;
        this.dboClass.approx20003CeFlag = null;
        this.dboClass.activeNew = null;


        // instrId(resultSetMsg.getInt("INSTR_ID"));
        // categoryNm(resultSetMsg.getString("CATEGORY"));// error
        // make(resultSetMsg.getString("MAKE"));
        // typeOfGov(resultSetMsg.getString("TYPE_OF_GOV"));
        // itemId(resultSetMsg.getInt("ITEM_ID"));
        // itemName(resultSetMsg.getString("ITEM_NAME"));
        // turbCode(resultSetMsg.getString("TURB_CD"));
        // turbCodeNm(resultSetMsg.getString("TURB_CD_NAME"));
        // condensingTypeId(resultSetMsg.getInt("COND_TYP_ID"));
        // condensingTypeName(resultSetMsg.getString("COND_TYP_NAME"));
        // typeOfInstr(resultSetMsg.getString("TYPE_OF_INSTR"));
        // instrTypeNm(resultSetMsg.getString("INSTR_TYPE_NM"));
        // instrDesc(resultSetMsg.getString("INSTR_DESC"));
        // instrMounting(resultSetMsg.getString("INSTR_MOUNTING"));
        // quantity1001Logic(resultSetMsg.getInt("QTY_1001_LOGIC"));
        // quantity1002Logic(resultSetMsg.getInt("QTY_1002_LOGIC"));
        // quantity2003Logic(resultSetMsg.getInt("QTY_2003_LOGIC"));
        // cost1001(Math.round(resultSetMsg.getInt("COST_1001")));
        // approx1001Flag(resultSetMsg.getInt("APPROX_1001_FLG"));
        // cost1002(Math.round(resultSetMsg.getInt("COST_1002")));
        // approx1002Flag(resultSetMsg.getInt("APPROX_1002_FLG"));
        // cost2003(Math.round(resultSetMsg.getInt("COST_2003")));
        // approx20003Flag(resultSetMsg.getInt("APPROX_2003_FLG"));
        // costCe1001(Math.round(resultSetMsg.getInt("COST_CE_1001")));
        // approx1001CeFlag(resultSetMsg.getInt("APPROX_1001_CE_FLG"));
        // costCe1002(resultSetMsg.getInt("COST_CE_1002"));
        // approx1002CeFlag(resultSetMsg.getInt("APPROX_1002_CE_FLG"));
        // costCe2003(Math.round(resultSetMsg.getInt("COST_CE_2003")));
        // approx20003CeFlag(resultSetMsg.getInt("APPROX_2003_CE_FLG"));
       // active new not there 
        this.dboFormData.updatePriceElectricalInstr3.push(this.dboClass);
        }
        else if(this.dropdowndefault=="Main Price")
        {
          this.dboClass.updateCode = "ELE_PRICE";
          this.dboClass.priceId = this.finalFrameList[v].priceId;
          this.dboClass.itemId = this.finalFrameList[v].itemId ;//45
          this.dboClass.subItemId =this.finalFrameList[v].subItemId ;//0
          this.dboClass.typeOfPanel = this.defaultvalue ;
          this.dboClass.make = this.finalFrameList[v].make;//TDPS
          this.dboClass.custType =this.finalFrameList[v].custType ;
          this.dboClass.priceCode = this.finalFrameList[v].priceCode;
          this.dboClass.price =Number(this.finalFrameList[v].price15);
          this.dboClass.approxCostFlag = this.finalFrameList[v].approxCostFlag;
          this.dboClass.itemErrMessage = this.finalFrameList[v].itemErrMessage;
          this.dboClass.activeNew =  this.finalFrameList[v].activeNew; 
          this.dboFormData.updatePriceElectricalList.push(this.dboClass);
        }
        else if(this.dropdowndefault=="Addon depending on turbine MW Price")
        {
          this.dboClass.updateCode = "ELE_ADDON_COST_UPD";
          this.dboClass.priceId = this.finalFrameList[v].priceId;
          this.dboClass.itemId = this.finalFrameList[v].itemId ;
          this.dboClass.typeOfPanel = this.defaultvalue ;
		      this.dboClass.custType =this.finalFrameList[v].custType ;
          this.dboClass.make = this.finalFrameList[v].make;
		      this.dboClass.colId = this.finalFrameList[v].colId;
          this.dboClass.colValCd = this.finalFrameList[v].colValCd;
          this.dboClass.subColValCd = this.finalFrameList[v].subColValCd;
          this.dboClass.addOnCostCol = this.finalFrameList[v].addOnCostCol;
          this.dboClass.minVal = this.finalFrameList[v].minVal;
          this.dboClass.maxVal = this.finalFrameList[v].maxVal;
          this.dboClass.addOnDirCost =this.finalFrameList[v].addOnDirCost ;
          this.dboClass.addOnCostPer = 0;
          this.dboClass.approxCostFlag = this.finalFrameList[v].approxCostFlag;
          this.dboClass.errorMsg = this.finalFrameList[v].errorMsg;
          this.dboClass.activeNew =  this.finalFrameList[v].activeNew; 
          this.dboFormData.updatePriceElectricalAddOnList.push(this.dboClass);
        }
        else if(this.dropdowndefault=="Addon Price")
        {

          this.dboClass.updateCode = "ELE_COL_VAL";
          this.dboClass.colValId = this.finalFrameList[v].colValId;
            this.dboClass.typeOfPanel =this.finalFrameList[v].typeOfPanel ;	 
            this.dboClass.itemId = this.finalFrameList[v].itemId ;
        this.dboClass.subItemId = this.finalFrameList[v].subItemId ;
            this.dboClass.make = this.finalFrameList[v].make;
        this.dboClass.colId = this.finalFrameList[v].colId;
        this.dboClass.colValCd= this.finalFrameList[v].colValCd;

        this.dboClass.colValNm = this.finalFrameList[v].colValNm;
            this.dboClass.colValCdSym = this.finalFrameList[v].colValCdSym;//string
            this.dboClass.subColValFlg = this.finalFrameList[v].subColValFlg; // interger
        this.dboClass.subColValNm = this.finalFrameList[v].subColValNm;//string
        this.dboClass.minVal = this.finalFrameList[v].minVal;//number
        this.dboClass.maxVal = this.finalFrameList[v].maxVal;//number
        this.dboClass.defaultFlag15 = this.finalFrameList[v].defaultFlag15;//int
        this.dboClass.defaultFlag30 = this.finalFrameList[v].defaultFlag30;//int
        this.dboClass.orderId = this.finalFrameList[v].orderId;//int
        this.dboClass.addOnFlg = this.finalFrameList[v].addOnFlg;//int
        this.dboClass.addOnDiffDs =this.finalFrameList[v].addOnDiffDs ;//int
          this.dboClass.addOnCostPer = this.finalFrameList[v].addOnCostPer;//int
        this.dboClass.addOnDirCost = this.finalFrameList[v].addOnDirCost;//int
            this.dboClass.approxCostFlag = this.finalFrameList[v].approxCostFlag;//int
            this.dboClass.calculateLinkFlag = this.finalFrameList[v].calculateLinkFlag;//int
            this.dboClass.techFlag = this.finalFrameList[v].techFlag;//int
            this.dboClass.comrFlag = this.finalFrameList[v].comrFlag;//int
        this.dboClass.dispInd = this.finalFrameList[v].dispInd;//int
            this.dboClass.disableColValCd = this.finalFrameList[v].disableColValCd;//string
            this.dboClass.delColFlag = this.finalFrameList[v].delColFlag;//int
            this.dboClass.inputCostFlag = this.finalFrameList[v].inputCostFlag;//int
            this.dboClass.activeNew =  this.finalFrameList[v].activeNew; //int
            this.dboFormData.updatePriceElectricalColVal.push(this.dboClass);
        }
            
       
}
}
 if(this.itemNameTemp=="Turbine Vibration Monitoring System (VMS)")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleVms(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
  
  });
}
else if(this.dropdowndefault=="Set of STG Instruments List")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleInstr(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
   
  });
}
else if(this.dropdowndefault=="Set of STG Instruments List(Auto Spray)")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleInstr1(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
   
  });
}
else if(this.dropdowndefault=="AdditionalProbes")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleInstr3(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
   
  });
}
else if(this.dropdowndefault=="Main Price")
{
  this._ItoUpdateCostDBOElecPrice.updateCreateElePrice(this.dboFormData).subscribe(res => {
    this.displayMessage = true;
    console.log(res);
    this.successMsg.push(res.successMsg);

   
  });
}
else if(this.dropdowndefault=="Addon depending on turbine MW Price")
{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleAddOnCost(this.dboFormData).subscribe(res => {
    console.log(res); 
    this.displayMessage = true;   
    this.successMsg.push(res.successMsg);
   
  });
}
else if(this.dropdowndefault=="Addon Price")

{
  this._ItoUpdateCostDBOElecPrice.updateCreateEleColVal(this.dboFormData).subscribe(res => {
    console.log(res);
    this.displayMessage = true;
    this.successMsg.push(res.successMsg);

  });
}

}
assignedUser(assigne) {
  console.log(assigne);
  this.dboFormData.saveBasicDetails.assignedTo = assigne;

}
closeMessage() {
  this.displayMessage = false;
  this.finalFrameList = [];
  this.remarkss = '';
  this.successMsg = [];
  this.acceptedOnly = true;
  this.navigateToMyWorkflow();
}
navigateToMyWorkflow() {
  this.router.navigate(['MyWorkFlow']);
}
    ngOnInit() {
    }
  }