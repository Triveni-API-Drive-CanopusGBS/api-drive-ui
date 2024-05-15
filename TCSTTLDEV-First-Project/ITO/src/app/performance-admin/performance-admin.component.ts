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
  selector: 'app-performance-admin',
  templateUrl: './performance-admin.component.html',
  styleUrls: ['./performance-admin.component.css']
})
export class PerformanceAdminComponent implements OnInit {
  cols: { field: string; header: string; }[];
  custTypeArray: { field: string; header: string; }[];

  volGenerationArray: Array<any> = [];
  finalRangeList: Array<any> = [];  // this list will have updated list of data from the grid
  localStorageValues: Array<any> = [];
  userRoles: Array<any> = [];  // list to store user roles
  newUsersLilst: Array<any> = [];  //users list
  prevRemarks: Array<any> = [];  // list to store previous remrks of engineer,reviewer and approver
  usersList: Array<any> = []; //users list
  successMsg: string = null;  // local variable to display success message
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
        console.log(resp);
        this.spinner=true;
        this.tableList=resp.dropDownColumnvalues.placeList8.placeList8;
        for(let j=0;j<this.tableList.length;j++)
        {


           this.cols = [
            { field: 'condTypeName', header: 'CondTypeName' },
            { field: 'startup', header: 'Startup' },
            { field: 'continuous', header: 'Continuous' },
            { field: 'colValCd', header: 'ColValCd' },
            { field: 'editFlag', header: 'EditFlag' },



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
let temp=false;
if (this.finalFrameList.length != 0) {
  for(let j=0;j<this.finalFrameList.length;j++)
  {
if(this.finalFrameList[j].auxId==this.selectedFrame.auxId)
{
  temp=true;
  this.finalFrameList[j]=this.selectedFrame;
}
  }

  if(temp==false)
{
  this.finalFrameList.push(this.selectedFrame);
}
}
else
{
  this.finalFrameList.push(this.selectedFrame);
}


this.displayDialog=false;
}
update()
{
  // bean.getId(),
  // bean.getFramePowerId(), 
  // bean.getBgmType(),
  //  bean.getBgmRating(),
  //  bean.getDefaultFlagNew(),
  // bean.getApproxCostFlag(), 
  // bean.getActiveNew()
  this.adminForm.adminBgmCalcList=[];
  for(let j=0;j<this.finalFrameList.length;j++)
  {
    this.dboClass = new dboClass();

  this.dboClass.auxId=this.finalFrameList[j].auxId;
  this.dboClass.itemId=this.finalFrameList[j].itemId;
  this.dboClass.subItemId=this.finalFrameList[j].subItemId;
  this.dboClass.condTypeId=this.finalFrameList[j].condTypeId;
  this.dboClass.frmId=this.finalFrameList[j].frmId;//error
  this.dboClass.custId=this.finalFrameList[j].consId;//error
  this.dboClass.startup=this.finalFrameList[j].startup;//error
  this.dboClass.continuous=this.finalFrameList[j].continuous;//error
  this.dboClass.colValCd=this.finalFrameList[j].colValCd;//error
  this.dboClass.editFlag=this.finalFrameList[j].editFlag;//error
  this.dboClass.activeNew=this.finalFrameList[j].activeNew;//not there
  // bean.setId(resultSetPlace4.getInt("ID"));
  // bean.setFramePowerId(resultSetPlace4.getInt("FRM_POW_ID"));
  // bean.setFrameName(resultSetPlace4.getString("FRM_NM"));
  // bean.setMaxPower(resultSetPlace4.getInt("MAX_POWER"));
  // bean.setBgmType(resultSetPlace4.getString("BGM_TYPE"));
  // bean.setBgmRating(resultSetPlace4.getFloat("BGM_RATING"));
  // bean.setDefaultFlag(resultSetPlace4.getInt("DEFLT_FLG"));
  this.adminForm.adminPerfAuxList.push(this.dboClass);
  }
  this._BgmratingsComponentService.getAdminPerfAux(this.adminForm).subscribe(res => {
    console.log(res);
    this.adminForm = res;
    this.sucessboolean=true;

if(res.successCode==0)
{
  this.successMsg=res.successMsg;
}
else
{
  this.successMsg="Something Went Wrong !!! Please try after some time...";

}
  });

}
closeDialog()
{
  this.sucessboolean=false;
  this.router.navigate(['defaultHome']);
  
}
  ngOnInit() {
  }

}
