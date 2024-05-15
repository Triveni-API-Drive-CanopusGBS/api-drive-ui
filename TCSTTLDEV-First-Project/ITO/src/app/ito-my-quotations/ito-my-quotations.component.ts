import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ITOcustomerDetailsService } from '../ito-customer-details/ito-customer-details.service';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOMyQuotPageService } from './ito-my-quotations.service';
import { ITOLoginService } from '../app.component.service';
//import * as jsPDF from 'jspdf';
import 'jspdf-autotable';
declare let jsPDF;
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ITOHomePageService } from '../ito-home-page/ito-home-page.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOScopeOfSupplyService } from '../ito-scope-of-supply/ito-scope-of-supply.service'; 

@Component({
  selector: 'app-ito-my-quotations',
  templateUrl: './ito-my-quotations.component.html',
  styleUrls: ['./ito-my-quotations.component.css']
})
export class ItoMyQuotationsComponent implements OnInit {

  sfdcCustData: any;
  opportunitySeqNO: any;
  usersList: Array<any> = [];
  displayedColumns: Array<string> = [];
  DataSource: any;
  DuplicateArray: any;
  title = "ITO tool";
  SearchedValue: any;
  CapacityArray: Array<any> = [];
  userId: any;
  quotSelected: boolean = false;
  user: string = 'userDetail';
  data: Array<any> = [];
  assignedTo: any;
  assignee: any;
  hideprogress: boolean = false;
  messageToUser: string;
  successMesg: string;
  messageToUserSaveAs: string;
  successMesgSaveAs: string;
  responsee: any;
  displaySaveAs: boolean = false;
  // ------------------
  customerReqrmnt: string = 'customerReq';
  endUserDetail: string = 'endUserDetail';
  custdetails: string = 'custdetails';
  scopeofsupp: string = 'scopeOfsup';
  addOnDetails: string = 'addOnDetail';
  packLocal: string = 'packLocal';
  transLocal: string = 'transLocal';
  ECData: string = 'ecKey';
  sparesLocal: string = 'sparesLocal';
  varCostLocal: string = 'varCostLocal';
  F2FSap: string = 'F2FSap';
  projectCostLocal: string = 'projectCostLocal';
  f2fCostData: string = 'f2fCostData';
  currentRoleId: string = 'selRoleId';
  usersDataList: string = 'usersDataList';
  dboEleFull: string = 'dboEleFull';
  F2FTurbine: string = 'F2FTurbine';
  dboMechLoc: string = 'dboMech';
  dboComSecALoc: string = 'comSecA'; // local storage value
  dboPerfLoc: string = 'dboPerf';
  dboMechAuxLoc: string = 'dboAuxMech';
  dboMechFull: string = 'dboMechFull';
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

  quotations: Array<any>;

  cols: Array<any> = [];
  revList: Array<any> = [];

  display: boolean = false;
  dispRev: boolean = false;
  assginOthers: boolean = false;

  RegionsList: Array<any> = [];

  users: Array<any> = [];
  tempUsers: Array<any> = [];

  selectedMyQuot: any;
  userDetails: any;
  userCurRoleName: any;
  completeUser: any;

  constructor(private _http: Http,
    private router: Router, private route: ActivatedRoute, private _ITOHomePageService: ITOHomePageService,
    private _ITOMyQuotPageService: ITOMyQuotPageService, private _ITOeditQoutService: ITOeditQoutService,
    private _ITOScopeOfSupplyService: ITOScopeOfSupplyService, private _ITOcustomerDetailsService: ITOcustomerDetailsService,
    private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOcustomerRequirementService: ITOcustomerRequirementService) {
    this.displayedColumns = ['quotNumber', 'custName', 'statusName', 'frameName',
      'capacity', 'modifyDate', 'assignedTo'];

    // initializing the successmessges
    this.successMesgSaveAs = '';
    this.messageToUserSaveAs = '';
    //taking values from local storage
    this.assignee = this.data[this.user] = this.storage.get(this.user);
    this.RegionsList = this.data[this.user].userRegionsList;
    this.userId = this.data[this.user].userId;

    this.userDetails = this.data[this.user];
    this.userDetails.userRoleId = this.storage.get(this.currentRoleId);
    console.log(this.userDetails)
    //getting values from server 
    this._ITOMyQuotPageService.getQuotationList(this.userDetails).subscribe(res => {
      for (let i = 0; i < res.quotationHomeGrid.length; i++) {
        this.CapacityArray.push(res.quotationHomeGrid[i].capacity);
      }
      this.quotations = res.myQuotations;
      //storing the repose to local variable
      this.responsee = res;

      this._ITOMyQuotPageService.fetchCacheData().subscribe(res => {
        console.log(res.userDetailsList);
        this.completeUser = res.userDetailsList;
        for (let u = 0; u < res.userDetailsList.length; u++) {
          for (let s = 0; s < res.userDetailsList[u].rolesCodeVal.length; s++) {
            if (res.userDetailsList[u].rolesCodeVal[s] == "QUOT_EDIT") {
              this.users.push(res.userDetailsList[u]);
              this.tempUsers.push(res.userDetailsList[u]); //added by nidhi
            }
          }
        }
        this.saveInLocal(this.usersDataList, res.userDetailsList);
        console.log(this.users);
        this.hideprogress = true;
      })
    })
  }
  ngOnInit() {
    this.cols = [
      { field: 'quotNumber', header: 'Quot No' },
      { field: 'custName', header: 'Customer' },
      { field: 'statusName', header: 'Status' },
      { field: 'frameName', header: 'Frame' },
      { field: 'capacity', header: 'Capacity' },
      { field: 'region', header: 'Region' },
      { field: 'modifyDate', header: 'Modified Date' },
      { field: 'assignedTo', header: 'Assigned To' }
    ];

  }
  saveInLocal(key, val): void {
    this.storage.set(key, val);
  }

  exportPDF(dt) {
    console.log(dt);
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
  //added by nidhi
  assigntoOthers() {
    this.display = true;
    this.users = [];
    this.usersList = [];
    console.log(this.tempUsers);
    console.log(this.selectedMyQuot);
    if(this.userDetails.userRoleId == 1){
    for (var i = 0; i < this.completeUser.length; i++) {
      for (var j = 0; j < this.completeUser[i].regionsVal.length; j++) {
        if ((this.selectedMyQuot.region == this.completeUser[i].regionsVal[j]) && (this.userDetails.userRoleId == 1 ) && (this.completeUser[i].userId != this.userId)) {
          this.usersList.push(this.completeUser[i]);
          break;
        }
      }
    }
    for(var k = 0; k<this.usersList.length;k++){
      for(var m=0;m<this.usersList[k].rolesCodeVal.length ; m++){
        if (this.usersList[k].rolesCodeVal[m] == "QUOT_EDIT") {
          this.users.push(this.usersList[k]);
          break;
        }
      }
    }
  }else if(this.userDetails.userRoleId == 2){
    for (var i = 0; i < this.completeUser.length; i++) {
      for (var j = 0; j < this.completeUser[i].rolesVal.length; j++) {
        if ((this.completeUser[i].rolesVal[j] == "Cost Estimation Approver") && (this.completeUser[i].userId != this.userId)) {
          this.users.push(this.completeUser[i]);
          break;
        }
      }
    }
  }else if(this.userDetails.userRoleId == 3){
    for (var i = 0; i < this.completeUser.length; i++) {
      for (var j = 0; j < this.completeUser[i].rolesVal.length; j++) {
        if ((this.completeUser[i].rolesVal[j] == "Cost Estimation Reviewer") && (this.completeUser[i].userId != this.userId)) {
          this.users.push(this.completeUser[i]);
          break;
        }
        }
      }
    }
    console.log(this.users);
  }

  saveAsfn() {
    this.displaySaveAs = true;

  }
  closeRef(){
    this.displaySaveAs = false;
    window.location.reload();
  }
  editQuot() {
    this._ITOeditQoutService.editMode = true;
    this._ITOHomePageService.selectedQuot = this.selectedMyQuot;
    // this._ITOMyQuotPageService.getQuotRevNo(this.selectedMyQuot.quotId).subscribe(revNos => {
    //   console.log(revNos);
    //   this.revList = revNos.saveBasicDetails.revList;
    //   console.log(this.revList)
    //   if (this.revList.length > 0) {
    //     this.dispRev = true;
    //   } else {
    this.router.navigate(['/EditQuot']);
    // }
    // })

  }

  closeDialog() {
    this.dispRev = false;
  }

  viewRev(rev) {
    console.log(rev)
    this.dispRev = false;
    this.responsee.saveBasicDetails.quotId = this.selectedMyQuot.quotId;
    this._ITOHomePageService.selectedQuot = this.selectedMyQuot;
    this._ITOeditQoutService.editMode = true;
    this.responsee.saveBasicDetails.revNum = rev;
    this._ITOMyQuotPageService.getQuotRevData(this.responsee).subscribe(revNos => {
      console.log(revNos);
      if (revNos.successCode == 0) {
        this.router.navigate(['/EditQuot']);
      }
    })
  }
  saveAsForm(fomrValue) {
    console.log(fomrValue);
    this.successMesgSaveAs = '';
    this.opportunitySeqNO =  fomrValue.opportunitySeqNO;
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
    // this.responsee.saveBasicDetails.opportunitySeqNum = fomrValue.opportunitySeqNO;
    this.responsee.saveBasicDetails.inQuotId = this.selectedMyQuot.quotId;
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
					

    
    console.log(this.users)
    for (let a = 0; a < this.users.length; a++) {
      if (this.users[a].empName == fomrValue.cloneAssign) {
        this.responsee.saveBasicDetails.assignedTo = this.users[a].userId;
      }
    }
    console.log(this.responsee);
    this._ITOMyQuotPageService.saveAsN(this.responsee).subscribe(resSaveAs => {
      console.log(resSaveAs);
      if (resSaveAs.successCode == 0) {
        this.successMesgSaveAs = 'Quotation is saved as QUOT NUMBER:' + ' ' + resSaveAs.saveBasicDetails.quotNumber;
      }
      else {
        this.successMesgSaveAs = resSaveAs.successMsg;
      }
    })
  }
  }else{
    this.successMesgSaveAs = " Please enter valid Opportunity Sequence Number";
  }
});
  }

  assignTo(user) {
    this.messageToUser = '';
    this.successMesg = '';
    for (let a = 0; a < this.users.length; a++) {
      if (this.users[a].empName == user.userName) {
        console.log(this.users[a].userId,
          this.assignee.userId,
          this.selectedMyQuot.quotId);
        this._ITOMyQuotPageService.assignToOthers(this.selectedMyQuot.quotId, this.selectedMyQuot.quotNumber, this.users[a].userId, this.assignee.userId, this.storage.get(this.currentRoleId))
          .subscribe(res => {
            console.log(res);

            if (res.successCode == 0 && res.successMsg != null) {
              this.successMesg = "Selected Quotation got assigned to " + user.userName;
              this._ITOMyQuotPageService.getQuotationList(this.userDetails).subscribe(res => {
                this.quotations = res.myQuotations;
                //storing the repose to local variable
                this.responsee = res;
              });
            } else {
              this.successMesg = res.successMsg;
            }
          })
      }
    }
  }
  navigatetoNext(rowData) {
    this.assginOthers = true
      ;
    this.selectedMyQuot = rowData;
    this.quotSelected = true;
    // if (this.selectedMyQuot.statusName == "In Progress") {
    //   this.assginOthers = true;
    // }
  }

  newCostEstimation() {
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
    this.storage.remove(this.dboMechLoc);
    this.storage.remove(this.dboComSecALoc);
    this.storage.remove(this.dboPerfLoc);
    this.storage.remove(this.dboMechFull);
    this.storage.remove(this.exclusion);
	 this.storage.remove(this.quaility);
	  this.storage.remove(this.scopeOf);
	   this.storage.remove(this.supplier);
	    this.storage.remove(this.tender);
		 this.storage.remove(this.terminal);
    this.storage.remove(this.projectCostLocal);
    this.storage.remove(this.f2fCostData);
    this.storage.remove(this.F2FSap);
    this.storage.remove(this.dboEleFull);
    this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.dboEleAuxFull);
    this.storage.remove(this.F2FTurbine);
    this.storage.remove(this.MechExpScope);
    this.storage.remove(this.EleCiExtdScope);
    this.storage.remove(this.EleExtdScope);
    this.storage.remove(this.dboMechAuxLoc);
    this.storage.remove(this.generalInput);
    this.storage.remove(this.clarifiAttach);
    this.storage.remove(this.tendrAttach);
    this._ITOcustomerRequirementService.editFlag = false;
    this._ITOcustomerRequirementService.saveBasicDet = '';
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
    this._ITOeditQoutService.mechExtScpList = [];
    this._ITOeditQoutService.eleCIExtScopeList = [];
    this._ITOeditQoutService.eleExtScopeList = [];
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
    window.open('#/CostEstimation/CustomerInformation', 'costWindow', "status=1,toolbar=0");
  }
}
