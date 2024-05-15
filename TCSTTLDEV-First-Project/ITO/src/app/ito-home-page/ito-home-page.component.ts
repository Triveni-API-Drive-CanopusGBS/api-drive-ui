import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { ITOHomePageService } from './ito-home-page.service';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import * as jsPDF from 'jspdf';
import 'jspdf-autotable';
declare let jsPDF;
import { ITOLoginService } from '../app.component.service';
import { NgProgress } from 'ngx-progressbar';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { DBOMechanicalComponentService } from '../dbomechanical/dbomechanical.service';
import { ITOcustomerDetailsService } from '../ito-customer-details/ito-customer-details.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOMyQuotPageService } from '../ito-my-quotations/ito-my-quotations.service';
import { ITOScopeOfSupplyService } from '../ito-scope-of-supply/ito-scope-of-supply.service';

@Component({
  selector: 'app-ito-home-page',
  templateUrl: './ito-home-page.component.html',
  styleUrls: ['./ito-home-page.component.css']
})
export class ItoHomePageComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: Array<string> = [];
  DataSource: any;
  sfdcCustData: any;
  opportunitySeqNO: any;
  DuplicateArray: any;
  title = "ITO tool";
  SearchedValue: any;
  CapacityArray: Array<any> = [];
  quotations: Array<any> = [];
  allQuotations: Array<Array<any>> = [];
  tmpltarray: Array<any> = [];
  regions: Array<any>;
  hideprogress: boolean = false;
  districts: Array<any> = [];
  states: Array<any> = [];
  countries: Array<any> = [];
  AddressInfo: string = 'AddressInfo';
  addressData: Array<any> = [];
  quotSelected: boolean = false;
  displaySaveAs: boolean = false;
  responsee: any;
  user: string = 'userDetail';
  cols: Array<any> = [];
  completeUser: any;
  display: boolean = false;
  dispRev: boolean = false;

  colorFlag: boolean;

  selectedQuot: any;
  userId: number;
  RegionsList: Array<any>;
  data: Array<any> = [];
  users: Array<any> = [];
  revList: Array<any> = [];
  selectedQuotNum: any;
  successMesgSaveAs: any;
  userDetails: any;

  customerReqrmnt: string = 'customerReq';
  endUserDetail: string = 'endUserDetail';
  custdetails: string = 'custdetails';
  scopeofsupp: string = 'scopeOfsup';
  addOnDetails: string = 'addOnDetail';
  packLocal: string = 'packLocal';
  transLocal: string = 'transLocal';
  ECData: string = 'ecKey';
  dboMechFull: string = 'dboMechFull';
  dboMechLoc: string = 'dboMech';
  dboComSecALoc: string = 'comSecA'; // local storage value
  dboPerfLoc: string = 'dboPerf';
  dboMechAuxLoc: string = 'dboAuxMech';
  sparesLocal: string = 'sparesLocal';
  varCostLocal: string = 'varCostLocal';
  F2FSap: string = 'F2FSap';
  projectCostLocal: string = 'projectCostLocal';
  oneLineLoc: string = 'oneLineLoc';
  dboEleFull: string = 'dboEleFull';
  F2FTurbine: string = 'F2FTurbine';
  f2fCostData: string = 'f2fCostData';
  currentRoleId: string = 'selRoleId';
  generalInput: string = 'generalInputList';
  MechExpScope: string = 'MechExpScope';
  EleExtdScope: string = 'EleExtdScope';
  EleCiExtdScope: string = 'EleCiExtdScope';
  dboEleCIAuxFull: string = 'dboEleCIAuxFull';
  dboEleCIFull: string = 'dboEleCIFull';
  dboEleAuxFull: string = 'dboEleAuxFull';
  exclusion: string = 'exclusion'; // local storage value
  quaility: string = 'quaility'; // local storage value
  scopeOf: string = 'scopeOf'; // local storage value
  supplier: string = 'supplier'; // local storage value
  tender: string = 'tender'; // local storage value
  terminal: string = 'terminal'; // local storage value
  tendrAttach: string = 'tendrAttach';//Local storaghe for tender drawing new
  clarifiAttach: string = 'clarifiAttach';// Local storage for clarifications and deviations


  constructor(private _ITOHomePageService: ITOHomePageService, private _http: Http,
    private router: Router, private route: ActivatedRoute, private _ITOLoginService: ITOLoginService, private _DBOMechanicalComponentService: DBOMechanicalComponentService,
    private _ITOScopeOfSupplyService: ITOScopeOfSupplyService,
    private spinner: NgProgress, private domSanitizer: DomSanitizer, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOcustomerDetailsService: ITOcustomerDetailsService,
    private _ITOeditQoutService: ITOeditQoutService, private _ITOMyQuotPageService: ITOMyQuotPageService) {

      this._ITOeditQoutService.button1=false;
      this._ITOeditQoutService.button2=false;
      this._ITOeditQoutService.button3=false;
      this._ITOeditQoutService.button4=false;
      this._ITOeditQoutService.button5=false;
      this._ITOeditQoutService.button6=false;
      this._ITOeditQoutService.button7=false;
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=false;
    this.storage.remove(this.customerReqrmnt);
    this.storage.remove(this.endUserDetail);
    this.storage.remove(this.custdetails);
    this.storage.remove(this.scopeofsupp);
    this.storage.remove(this.addOnDetails);
    this.storage.remove(this.packLocal);
    this.storage.remove(this.transLocal);
    this.storage.remove(this.ECData);
    this.storage.remove(this.sparesLocal);
    this.storage.remove(this.varCostLocal);
    this.storage.remove(this.projectCostLocal);
    this.storage.remove(this.dboMechFull);
    this.storage.remove(this.exclusion);
	 this.storage.remove(this.quaility);
	  this.storage.remove(this.scopeOf);
	   this.storage.remove(this.supplier);
	    this.storage.remove(this.tender);
		 this.storage.remove(this.terminal);
    this.storage.remove(this.dboMechLoc);
    this.storage.remove(this.dboComSecALoc);
    this.storage.remove(this.dboPerfLoc);
    this.storage.remove(this.oneLineLoc);
    this.storage.remove(this.dboEleFull);
    this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.dboEleAuxFull);
    this.storage.remove(this.f2fCostData);
    this.storage.remove(this.F2FSap);
    this.storage.remove(this.F2FTurbine);
    this.storage.remove(this.MechExpScope);
    this.storage.remove(this.EleCiExtdScope);
    this.storage.remove(this.EleExtdScope);
    this.storage.remove(this.dboMechAuxLoc);
    this.storage.remove(this.generalInput);
    this.storage.remove(this.clarifiAttach);
    this.storage.remove(this.tendrAttach);
    this._ITOcustomerRequirementService.saveBasicDet = '';
    this._ITOcustomerDetailsService.oppSeqNo = '';
    this._ITOcustomerRequirementService.opportunitySeqNum = '';
    this._ITOcustomerRequirementService.editFlag = false;
    this._ITOeditQoutService.dboEleAuxData = [];
    this._ITOeditQoutService.dboEleAuxItmOthers = [];
    this._ITOeditQoutService.dboEleCiData = [];
    this._ITOeditQoutService.dboEleCIItmOthers = [];
    this._ITOeditQoutService.dboEleCIAuxData = [];
    this._ITOeditQoutService.dboEleCIAuxItmOthers = [];
    this._ITOeditQoutService.dboEleData = [];
    this._ITOeditQoutService.cirListData = [];
    this._ITOeditQoutService.fixedListData = [];
    this._ITOeditQoutService.identListData = [];
    this._ITOeditQoutService.itemListData = [];
    this._ITOeditQoutService.dboEleItmOthers = [];
    this._ITOeditQoutService.dboMechData = [];
    this._ITOeditQoutService.savePerformanceDataList1 = [];
      this._ITOeditQoutService.savePerformanceDataList2 = [];
      this._ITOeditQoutService.savePerformanceDataList3 = [];
      this._ITOeditQoutService.qualitiasurance = [];
      this._ITOeditQoutService.scopeofspares = [];
      this._ITOeditQoutService.tenderDraw = [];
      this._ITOeditQoutService.terminalpo = [];
      this._ITOeditQoutService.exclusionLi = [];
      this._ITOeditQoutService.subSuppliersList = [];
    this._ITOeditQoutService.mechExtScpList =[];
    this._ITOeditQoutService.eleExtScopeList = [];
    this._ITOeditQoutService.eleCIExtScopeList = [];
    this._ITOeditQoutService.dboDataOthers = [];
    this._ITOeditQoutService.dboEleSplAddOnList = [];
    this._ITOeditQoutService.dboEleOthers = [];
    this._ITOeditQoutService.dboEleDataAddOn = [];
    this._ITOeditQoutService.dboEleNewAddOns = [];
    this._ITOeditQoutService.dboF2fData = [];
    this._ITOeditQoutService.dboF2fNewAddOns = [];
    this._ITOeditQoutService.f2fOthersItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemTypeList = [];
    this._ITOScopeOfSupplyService.sampleScope = [];
    
    for (let a = 0; a < this._DBOMechanicalComponentService.addedClassList.length; a++) {
      this.storage.remove(this._DBOMechanicalComponentService.addedClassList[a]);
    }

    this.successMesgSaveAs = '';
    this.displayedColumns = ['quotNumber', 'custName', 'statusName', 'frameName',
      'capacity', 'modifyDate', 'assignedTo'];
    this.data[this.user] = this.storage.get(this.user);
    this.userId = this._ITOLoginService.loggedUserDetails;
    this.RegionsList = this.data[this.user].userRegionsList;
    this.userId = this.data[this.user].userId;
    this.data[this.user] = this.storage.get(this.user);
    console.log(this.data[this.user]);
    this.userDetails = this.data[this.user];
    console.log(this.userDetails)
  }

  ngOnInit() {
    this.userDetails.userRoleId = this.storage.get(this.currentRoleId);
    this._ITOHomePageService.getQuotationList(this.userDetails).subscribe(res => {
      console.log(res);
      //  this._ITOLoginService.userDetailsList = res.userDetailsList;
      this.responsee = res;
      this.quotations = res.quotationHomeGrid;
      this.regions = this.data[this.user].userRegionsList;
      // this.users = res.userDetailsList;

      this._ITOMyQuotPageService.fetchCacheData().subscribe(res => {
        this.completeUser = res.userDetailsList;
        for (let u = 0; u < res.userDetailsList.length; u++) {
          for (let s = 0; s < res.userDetailsList[u].rolesCodeVal.length; s++) {
            if (res.userDetailsList[u].rolesCodeVal[s] == "QUOT_EDIT") {
              this.users.push(res.userDetailsList[u]);
            }
          }
        }
      });
      
      this.saveInLocal(this.AddressInfo, res.dropDownColumnvalues);

      for (let j = 0; j < this.regions.length; j++) {
        for (let i = 0; i < res.quotationHomeGrid.length; i++) {
          if (res.quotationHomeGrid[i].region == this.regions[j].value) {
            this.tmpltarray.push(res.quotationHomeGrid[i]);
          }
        }
        this.allQuotations.push(this.tmpltarray);
        this.tmpltarray = [];
      }
      this.hideprogress = true;
    });



    this.cols = [
      { field: 'quotNumber', header: 'Quot No' },
      { field: 'custName', header: 'Customer' },
      { field: 'statusName', header: 'Status' },
      { field: 'frameName', header: 'Frame' },
      { field: 'capacity', header: 'Capacity' },
      { field: 'modifyDate', header: 'Modified Date' },
      { field: 'assignedTo', header: 'Assigned To' }
    ];

  }
  navigatetoNext(val) {
    this.selectedQuot = val;
    this.quotSelected = true;
    this._ITOeditQoutService.editMode = false;
  }
  saveAsfn() {
    this.displaySaveAs = true;

  }
  saveAsForm(fomrValue) {
console.log(fomrValue);
    this.successMesgSaveAs = '';
    this.opportunitySeqNO = fomrValue.opportunitySeqNO;
    this._ITOcustomerDetailsService.getCustData(this.opportunitySeqNO).subscribe(res => {
      console.log(res); 
      if(res.successCode == 0) {
        this.sfdcCustData = res;
        if(res.custName != null){
          if(this.sfdcCustData.custType != "Export" && this.sfdcCustData.custType != "Domestic"){
            this.successMesgSaveAs= "Customer Type is not defined in Sfdc";
          }else{
            if(this.sfdcCustData.custType == "Domestic" && this.sfdcCustData.oppContactStateName == null){
              this.successMesgSaveAs = "State is not defined in sfdc";
            }
            if(this.sfdcCustData.isEndUserAvailable == 'No' && this.sfdcCustData.endUserCustType != "Export" && this.sfdcCustData.endUserCustType != "Domestic"){
              this.successMesgSaveAs ="End User Customer type is not defined in sfdc";
            }
          }
        }else{   
            if(res.oppurtunitySeqNo == null){
              this.successMesgSaveAs = "Customer Details are not available";
              }else{
                this.successMesgSaveAs ="Incorrect customer Details;"
              }
          }
         
       if(this.successMesgSaveAs == ""){
    // capturing the values fromUI as a form
    this.responsee.saveBasicDetails.modifiedById = this.userId;
     this.responsee.saveBasicDetails.inQuotId = this.selectedQuot.quotId;
   //this.responsee.saveBasicDetails.assignedTo = fomrValue.cloneAssign;
    this.responsee.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
    this.responsee.saveBasicDetails.opportunitySeqNum= fomrValue.opportunitySeqNO;
    // for (let r = 0; r < this.RegionsList.length; r++) {
    //   if (this.RegionsList[r].value == this.selectedMyQuot.region) {
    //     this.responsee.saveBasicDetails.regionCode = this.RegionsList[r].code;
    //   }
    // }
    this.responsee.saveBasicDetails.regionCode = fomrValue.opportunitySeqNO.substring(0,3);
    this.responsee.customerDetailsForm.custName= this.sfdcCustData.custName;
    this.responsee.customerDetailsForm.oppName= this.sfdcCustData.oppName;
    this.responsee.customerDetailsForm.oppContactName= this.sfdcCustData.oppContactName;
    this.responsee.customerDetailsForm.oppContactEmail= this.sfdcCustData.oppContactEmail;
    this.responsee.customerDetailsForm.oppContactPhone= this.sfdcCustData.oppContactPhone;
    this.responsee.customerDetailsForm.oppContactAddress= this.sfdcCustData.oppContactAddress;
    this.responsee.customerDetailsForm.oppContactStateName= this.sfdcCustData.oppContactStateName;
    this.responsee.customerDetailsForm.custType= this.sfdcCustData.custType;
    this.responsee.customerDetailsForm.isEndUserAvailable= this.sfdcCustData.isEndUserAvailable;
    this.responsee.customerDetailsForm.endUserCustType= this.sfdcCustData.endUserCustType;
    this.responsee.customerDetailsForm.endUserName= this.sfdcCustData.endUserName;
    this.responsee.customerDetailsForm.endUserStateName= this.sfdcCustData.endUserStateName;

    for (let a = 0; a < this.users.length; a++) {
      if (this.users[a].empName == fomrValue.cloneAssign) {
        this.responsee.saveBasicDetails.assignedTo = this.users[a].userId;
      }
    }

    this._ITOMyQuotPageService.saveAsN(this.responsee).subscribe(resSaveAs => {
      console.log(resSaveAs)
      if (resSaveAs.successCode == 0) {
        this.successMesgSaveAs = 'Quotation is saved as QUOT NUMBER:' + ' ' + resSaveAs.saveBasicDetails.quotNumber;
      }
      else {
        this.successMesgSaveAs = resSaveAs.successMsg;
      }
    });
  }
}else{
  this.successMesgSaveAs = " Please enter valid Opportunity Sequence Number";
}
});
  }
  saveInLocal(key, val): void {

    this.storage.set(key, val);
    this.addressData[key] = this.storage.get(key);
  }

  editQuot() {
    this._ITOHomePageService.selectedQuot = this.selectedQuot;
    this._ITOMyQuotPageService.getQuotRevNo(this.selectedQuot.quotId).subscribe(revNos => {
      console.log(revNos);
      this.revList = revNos.saveBasicDetails.revList;
      if (this.revList.length > 0) {
        this.dispRev = true;
      } else if (this.revList.length == 0) {
        this.router.navigate(['/EditQuot']);
      }
    })
  }
  closeDialog() {
    this.dispRev = false;
  }
  closeRef(){
    this.displaySaveAs = false;
    window.location.reload();
  }
  viewRev(rev) {
    console.log(rev)
    this.dispRev = false;
    this._ITOeditQoutService.editMode = false;
    // this.responsee.saveBasicDetails.quotId = this.selectedQuot.quotId;
    this._ITOHomePageService.selectedQuot = this.selectedQuot;
    this._ITOHomePageService.revNum = rev;
    this.router.navigate(['/viewQuot']);
  }
  newCostEstimation() {
    this._ITOeditQoutService.checkEdit=true;
    this._ITOcustomerRequirementService.sendclrBtnStatus(false, false, false, false, false, false, false, false, false, false,false, false,false,false);
    // added by kavya
    this.storage.remove(this.customerReqrmnt);
    this.storage.remove(this.endUserDetail);
    this.storage.remove(this.custdetails);
    this.storage.remove(this.scopeofsupp);
    this.storage.remove(this.addOnDetails);
    this.storage.remove(this.packLocal);
    this.storage.remove(this.transLocal);
    this.storage.remove(this.ECData);
    this.storage.remove(this.sparesLocal);
    this.storage.remove(this.varCostLocal);
    this.storage.remove(this.projectCostLocal);
    this.storage.remove(this.dboMechFull);
    this.storage.remove(this.exclusion);
	 this.storage.remove(this.quaility);
	  this.storage.remove(this.scopeOf);
	   this.storage.remove(this.supplier);
	    this.storage.remove(this.tender);
		 this.storage.remove(this.terminal);
    this.storage.remove(this.dboMechLoc);
    this.storage.remove(this.dboComSecALoc);
    this.storage.remove(this.dboPerfLoc);
    this.storage.remove(this.oneLineLoc);
    this.storage.remove(this.dboEleFull);
    this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.dboEleAuxFull);
    this.storage.remove(this.F2FTurbine);
    this.storage.remove(this.MechExpScope);
    this.storage.remove(this.EleCiExtdScope);
    this.storage.remove(this.EleExtdScope);
    this.storage.remove(this.dboMechAuxLoc);
    this.storage.remove(this.f2fCostData);
    this.storage.remove(this.F2FSap);
    this.storage.remove(this.generalInput);
    this.storage.remove(this.clarifiAttach);
    this.storage.remove(this.tendrAttach);
    this._ITOcustomerRequirementService.editFlag = false;
    this._ITOcustomerRequirementService.saveBasicDet = '';
    // this._ITOLoginService.costEst 
    this._ITOeditQoutService.dboEleAuxData = [];
    this._ITOeditQoutService.dboEleAuxItmOthers = [];
    this._ITOeditQoutService.dboEleCiData = [];
    this._ITOeditQoutService.dboEleCIItmOthers = [];
    this._ITOeditQoutService.dboEleCIAuxData = [];
    this._ITOeditQoutService.dboEleCIAuxItmOthers = [];
    this._ITOeditQoutService.dboEleData = [];
    this._ITOeditQoutService.dboEleItmOthers = [];
    this._ITOeditQoutService.dboMechData = [];
    this._ITOeditQoutService.savePerformanceDataList1 = [];
      this._ITOeditQoutService.savePerformanceDataList2 = [];
      this._ITOeditQoutService.savePerformanceDataList3 = [];
      this._ITOeditQoutService.qualitiasurance = [];
      this._ITOeditQoutService.scopeofspares = [];
      this._ITOeditQoutService.tenderDraw = [];
      this._ITOeditQoutService.terminalpo = [];
      this._ITOeditQoutService.exclusionLi = [];
      this._ITOeditQoutService.subSuppliersList = [];
    this._ITOeditQoutService.eleCIExtScopeList = [];
    this._ITOeditQoutService.eleExtScopeList = [];
    this._ITOeditQoutService.mechExtScpList = [];
    this._ITOeditQoutService.dboDataOthers = [];
    this._ITOeditQoutService.dboEleSplAddOnList = [];
    this._ITOeditQoutService.dboEleOthers = [];
    this._ITOeditQoutService.dboEleDataAddOn = [];
    this._ITOeditQoutService.dboEleNewAddOns = [];
    this._ITOeditQoutService.dboF2fData = [];
    this._ITOeditQoutService.dboF2fNewAddOns = [];
    this._ITOeditQoutService.f2fOthersItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemTypeList = [];
    this._ITOScopeOfSupplyService.sampleScope = [];
    this._ITOeditQoutService.cirListData = [];
    this._ITOeditQoutService.fixedListData = [];
    this._ITOeditQoutService.identListData = [];
    this._ITOeditQoutService.itemListData = [];
    // window.open('#/CostEstimation/CustomerInformation', 'costWindow', "status=1,toolbar=0");
    this.router.navigate(['/CostEstimation/CustomerInformation']);
  }
  exportPDF(dt) {

    var doc = new jsPDF('landscape');
    var col = ['Quot Number', 'Customer Name', 'Status Name', 'Frame Name',
      'Capacity', 'Modify Date', 'assignedTo'];
    var rows = [];

    /* The following array of object as response from the API req  */

    let itemNew;
    if (dt.totalRecords != dt._value.length) {
      itemNew = dt.filteredValue;
    } else {
      itemNew = dt._value;
    }


    itemNew.forEach(element => {
      var temp = [element.quotNumber, element.custName, element.statusName, element.frameName,
      element.capacity, element.modifyDate, element.assignedTo];
      rows.push(temp);

    });
    doc.autoTable(col, rows, {
      styles: {
        //cellPadding: 5, // a number, array or object (see margin below)
        columnWidth: 'auto', // 'auto', 'wrap' or a number
        overflow: 'linebreak'
      }
    });

    doc.save('Quotations.pdf');

  }


}
