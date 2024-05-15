import { Component, OnInit } from '@angular/core';
import { ITOturbineConfigService } from  '../ito-turbine-config/ito-turbine-config.service'
import { BgmratingsComponentService } from '../bgmratings/bgmratings.service';

import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { DomSanitizer } from '@angular/platform-browser';
import { ITOLoginService } from '../app.component.service';
import { saveAs } from 'file-saver';
//import * as jsPDF from 'jspdf';
import { Pipe, PipeTransform } from '@angular/core';
import 'rxjs/Rx' ;
declare let jsPDF;
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOScopeOfSupplyService } from '../ito-scope-of-supply/ito-scope-of-supply.service';
import { ItoUserManualService } from '../ito-user-manual/ito-user-manual.service';
import { sample } from 'rxjs/operator/sample';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
import { dboClass } from '../bgmratings/bgmratings';


@Component({
  selector: 'app-electrical-header-footer',
  templateUrl: './electrical-header-footer.component.html',
  styleUrls: ['./electrical-header-footer.component.css']
})
export class ElectricalHeaderFooterComponent implements OnInit {




cols: { field: string; header: string; }[];
  custTypeArray: { field: string; header: string; }[];

  volGenerationArray: Array<any> = [];
  finalRangeList: Array<any> = [];  // this list will have updated list of data from the grid
  localStorageValues: Array<any> = [];
  userRoles: Array<any> = [];  // list to store user roles
  newUsersLilst: Array<any> = [];  //users list
  prevRemarks: Array<any> = [];  // list to store previous remrks of engineer,reviewer and approver
  usersList: Array<any> = []; //users list
  successMsg: string= null;  // local variable to display success message
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
  adminForm:any;
  sucessboolean:boolean=false;
  spinner:boolean=false;
  constructor(private _ITOturbineConfigService: ITOturbineConfigService, private _ITOScopeOfSupplyService: ITOScopeOfSupplyService,
    private _BgmratingsComponentService: BgmratingsComponentService, private router: Router,
    private _ITOCostEstimationService: ITOCostEstimationService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ItoUserManualService: ItoUserManualService,
    private _ITOeditQoutService: ITOeditQoutService,  @Inject(LOCAL_STORAGE) private storage: WebStorageService,) {
     
      this._BgmratingsComponentService.getAdminCacheWithAIList().subscribe(resp => {
        this.spinner=true;
        console.log(resp);
        this.tableList=resp.dropDownColumnvalues.placeList6.placeList6;
        for(let j=0;j<this.tableList.length;j++)
        {
          
          
          
          this.cols = [
            { field: 'typeOfPanel', header: 'Type of Panel' },
            { field: 'eleType', header: 'Electrical Type' },
            { field: 'itemName', header: 'Item Name' },
            { field: 'subItemName', header: 'SubItem Name' },
            { field: 'headerText', header: 'Header Text' },
            { field: 'footerText', header: 'Footer Text' },
            { field: 'exclusionNm', header: 'Exclusion Name' },
  
           ];
          }
          this._BgmratingsComponentService.getAdminForm().subscribe(res => {
            console.log(res);
            this.adminForm = res;

          });
      });


     }
     rowSelected(rowData) {
      console.log(this.usersList);
      console.log(rowData);
      this.displayDialog = true;
      this.selectedFrame = rowData;
  
    }
    save()
{

console.log(this.selectedFrame);

if (this.finalFrameList.length != 0) {
  for(let j=0;j<this.finalFrameList.length;j++)
  {
if(this.finalFrameList[j].eleItemId==this.selectedFrame.eleItemId)
{
  this.finalFrameList[j]=this.selectedFrame;
}
  }

  
}
else
{
  this.finalFrameList.push(this.selectedFrame)
}

this.displayDialog=false;
}
update()
{
  // bean.setEleItemId(resultSetPlace6.getInt("ELE_ITEM_ID"));
  // bean.setTypeOfPanel(resultSetPlace6.getString("TYPE_OF_PANEL"));
  // bean.setEleType(resultSetPlace6.getString("ELE_TYPE"));
  // bean.setItemId(resultSetPlace6.getInt("ITEM_ID"));
  // bean.setItemName(resultSetPlace6.getString("ITEM_NM"));
  // bean.setSubItemId(resultSetPlace6.getInt("SUB_ITEM_ID"));
  // bean.setItemName(resultSetPlace6.getString("ITEM_NM"));
  // bean.setHeaderText(resultSetPlace6.getString("HEADER_TEXT"));
  // bean.setFooterText(resultSetPlace6.getString("FOOTER_TEXT"));
  // bean.setHeaderNm(resultSetPlace6.getString("HEARER_NM"));
  // bean.setExclusionNm(resultSetPlace6.getString("EXCLUSION_NM"));
  this.adminForm.adminEleItemList=[];
  for(let j=0;j<this.finalFrameList.length;j++)
  {
    this.dboClass = new dboClass();

  this.dboClass.eleItemId=this.finalFrameList[j].eleItemId;
  this.dboClass.typeOfPanel=this.finalFrameList[j].typeOfPanel;
  this.dboClass.eleType=this.finalFrameList[j].eleType;
  this.dboClass.itemId=this.finalFrameList[j].itemId;
  this.dboClass.subItemId=this.finalFrameList[j].subItemId;
  this.dboClass.headerText=this.finalFrameList[j].headerText;
  this.dboClass.footerText=this.finalFrameList[j].footerText;
  this.dboClass.headerNm=this.finalFrameList[j].headerNm;
  this.dboClass.exclusionNm=this.finalFrameList[j].exclusionNm;
  // this.dboClass.custTypeDependFlag=this.finalFrameList[j].custTypeDependFlag; // not there
  // this.dboClass.dtFrameFlag=this.finalFrameList[j].dtFrameFlag;// not thete
  this.adminForm.adminEleItemList.push(this.dboClass);
  }
  this._BgmratingsComponentService.getAdminEleItems(this.adminForm).subscribe(res => {
    console.log(res);
    this.adminForm = res;
    this.sucessboolean=true;
if(res.successCode==0)
{
  this.successMsg="Success";
}
else
{
  this.successMsg="Something Went Wrong !!! Please try after some time...";

}
  });
  // bean.getEleItemId(),
	// 					bean.getTypeOfPanel(), 
	// 					bean.getEleType(),
	// 					 bean.getItemId(),
	// 					 bean.getSubItemId(),
	// 					bean.getHeaderText(), 
	// 					bean.getFooterText(),
	// 					bean.getHeaderNm(),
	// 					bean.getExclusionNm(),
	// 					bean.getCustTypeDependFlag(),
  //           bean.getDtFrameFlag()
}
closeDialog()
{
  this.sucessboolean=false;
  this.router.navigate(['defaultHome']);

}
  ngOnInit() {
  }

}

