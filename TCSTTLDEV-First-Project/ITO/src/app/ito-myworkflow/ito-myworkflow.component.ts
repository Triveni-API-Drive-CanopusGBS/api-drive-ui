import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOMyQuotPageService } from '../ito-my-quotations/ito-my-quotations.service';
import { ITOLoginService } from '../app.component.service';
import * as jsPDF from 'jspdf';
import 'jspdf-autotable';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ITOHomePageService } from '../ito-home-page/ito-home-page.service';
import { ITOMyWorkListPageService } from './ito-myWorkFlow.service';

@Component({
  selector: 'app-ito-myworkflow',
  templateUrl: './ito-myworkflow.component.html',
  styleUrls: ['./ito-myworkflow.component.css']
})
export class ItoMyworkflowComponent implements OnInit {
  userId: any;

  displayedColumns: Array<any> = [];

  coulmnsForUP: Array<any> = [];


  updatePriceList: Array<any> = [];
  updatePriceListBased: Array<any> = [];

  cols: Array<any> = [];

  colss: Array<any> = [];

  display: boolean = false;

  RegionsList: Array<any> = [];

  users: Array<any> = [];

  selectedMyQuot: any;
  user: string = 'userDetail';
  currentRoleDesc: string = 'selRoleDesc';
  data: Array<any> = [];
  saveBasicDet: any;
  userRoles: Array<any> = [];
  usersList: Array<any> = [];
  allreqTemp: Array<any> = [];
  allRequests: Array<any> = [];
  loggedInRole: string;
  currentRole: string = 'selRole';
  hideprogress: boolean = false;
  disbleCR: boolean = false;

  constructor(private _http: Http, private router: Router, private route: ActivatedRoute, private _ITOHomePageService: ITOHomePageService,
    private _ITOMyQuotPageService: ITOMyQuotPageService,
    private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOMyWorkListPageService: ITOMyWorkListPageService) {
    this.hideprogress = true;
    this.displayedColumns = ['quotNumber', 'custName', 'statusName', 'frameName',
      'capacity', 'modifyDate', 'assignedTo'];

    this.coulmnsForUP = ['displayReqNumber', 'createdBy', 'assignedToName',
      'modifiedDate', 'statusName'];



    //taking values from local storage
    this.data[this.user] = this.storage.get(this.user);
    this.data[this.currentRoleDesc] = this.storage.get(this.currentRoleDesc);
    if (this.data[this.currentRoleDesc].includes('Cost') || this.data[this.currentRoleDesc].includes('Admin')) {
      this.disbleCR = true;
    }
    else {
      this.disbleCR = false;
    }
    console.log(this.data[this.currentRoleDesc]);
    this.userId = this.data[this.user].userId;
    this.loggedInRole = this.storage.get(this.currentRoleDesc);
    console.log(this.data[this.user]);
    //getting values from server 
    this._ITOMyQuotPageService.fetchCacheData().subscribe(res => {
      console.log(res);
      this.usersList = res.userDetailsList;


      this._ITOMyWorkListPageService.getUpdatePriceReqGrid(this.userId).subscribe(resp => {
        console.log(resp);
        // for(let r=0;r<resp.updatePriceMyRequestGrid.length;r++){
        //   if(resp.updatePriceMyRequestGrid[r].statusName.includes(""))
        // }
        this.updatePriceListBased = resp.updatePriceMyRequestGrid;
        console.log(this.storage.get(this.currentRoleDesc));
        switch (this.storage.get(this.currentRoleDesc)) {
          case "F2F Engineer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Engineer") && (this.updatePriceListBased[r].updateCode == "F2F_FRM_SPEC_DATA_UPD" || this.updatePriceListBased[r].updateCode == "F2F_COL_VAL_UPD" || this.updatePriceListBased[r].updateCode == "F2F_PRICE_UPD" || this.updatePriceListBased[r].updateCode == "UPD_ADDON" || this.updatePriceListBased[r].updateCode == "UPD_ADDON_NEW" || this.updatePriceListBased[r].updateCode == "UPD_TI_SUBC" || this.updatePriceListBased[r].updateCode == "F2F_SHOP_CONV_UPD")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "F2F Reviewer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Reviewer" || this.updatePriceListBased[r].statusName == "Pending With Reviewer" ) && (this.updatePriceListBased[r].updateCode == "F2F_FRM_SPEC_DATA_UPD" || this.updatePriceListBased[r].updateCode == "F2F_COL_VAL_UPD" || this.updatePriceListBased[r].updateCode == "F2F_PRICE_UPD" || this.updatePriceListBased[r].updateCode == "UPD_ADDON"  || this.updatePriceListBased[r].updateCode == "UPD_ADDON_NEW" || this.updatePriceListBased[r].updateCode == "UPD_TI_SUBC" || this.updatePriceListBased[r].updateCode == "F2F_SHOP_CONV_UPD")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "F2F Approver": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Approver" || this.updatePriceListBased[r].statusName == "Pending With Approver" ) && (this.updatePriceListBased[r].updateCode == "F2F_FRM_SPEC_DATA_UPD" || this.updatePriceListBased[r].updateCode == "F2F_COL_VAL_UPD" || this.updatePriceListBased[r].updateCode == "F2F_PRICE_UPD" || this.updatePriceListBased[r].updateCode == "UPD_ADDON"  || this.updatePriceListBased[r].updateCode == "UPD_ADDON_NEW" || this.updatePriceListBased[r].updateCode == "UPD_TI_SUBC" || this.updatePriceListBased[r].updateCode == "F2F_SHOP_CONV_UPD")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Finance Engineer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Engineer") && (this.updatePriceListBased[r].updateCode == "UPD_UBO_OVRD")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Finance Reviewer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Reviewer" || this.updatePriceListBased[r].statusName == "Pending With Reviewer" ) && (this.updatePriceListBased[r].updateCode == "UPD_UBO_OVRD")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Finance Approver": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Approver" || this.updatePriceListBased[r].statusName == "Pending With Approver" ) && (this.updatePriceListBased[r].updateCode == "UPD_UBO_OVRD")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "UBO Engineer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Engineer") && (this.updatePriceListBased[r].updateCode == "UPD_UBON" || this.updatePriceListBased[r].updateCode == "UPD_UBO" || this.updatePriceListBased[r].updateCode == "UPD_SUBCO")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "UBO Reviewer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Reviewer" || this.updatePriceListBased[r].statusName == "Pending With Reviewer" ) && (this.updatePriceListBased[r].updateCode == "UPD_UBON" || this.updatePriceListBased[r].updateCode == "UPD_UBO" || this.updatePriceListBased[r].updateCode == "UPD_SUBCO")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "UBO Approver": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Approver" || this.updatePriceListBased[r].statusName == "Pending With Approver" ) && (this.updatePriceListBased[r].updateCode == "UPD_UBON" || this.updatePriceListBased[r].updateCode == "UPD_UBO" || this.updatePriceListBased[r].updateCode == "UPD_SUBCO")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Packaging & Forwarding Engineer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Engineer") && (this.updatePriceListBased[r].updateCode == "UP_PF")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Packaging & Forwarding Reviewer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Reviewer" || this.updatePriceListBased[r].statusName == "Pending With Reviewer" ) && (this.updatePriceListBased[r].updateCode == "UP_PF")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Packaging & Forwarding Approver": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Approver" || this.updatePriceListBased[r].statusName == "Pending With Approver" ) && (this.updatePriceListBased[r].updateCode == "UP_PF")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Transportation Engineer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Engineer") && (this.updatePriceListBased[r].updateCode == "UPD_TR_VH" || this.updatePriceListBased[r].updateCode == "UPD_NTR_NV" ||
              this.updatePriceListBased[r].updateCode == "UPD_TR_EX" || this.updatePriceListBased[r].updateCode == "UPD_NTR_EX" || this.updatePriceListBased[r].updateCode == "UPN_TR" || this.updatePriceListBased[r].updateCode == "UPD_TR")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Transportation Reviewer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Reviewer" || this.updatePriceListBased[r].statusName == "Pending With Reviewer" ) && (this.updatePriceListBased[r].updateCode == "UPD_TR_VH" || this.updatePriceListBased[r].updateCode == "UPD_NTR_NV" || 
              this.updatePriceListBased[r].updateCode == "UPD_TR_EX" || this.updatePriceListBased[r].updateCode == "UPD_NTR_EX" || this.updatePriceListBased[r].updateCode == "UPN_TR" || this.updatePriceListBased[r].updateCode == "UPD_TR")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Transportation Approver": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Approver" || this.updatePriceListBased[r].statusName == "Pending With Approver" ) && (this.updatePriceListBased[r].updateCode == "UPD_TR_VH" || this.updatePriceListBased[r].updateCode == "UPD_NTR_NV" || 
              this.updatePriceListBased[r].updateCode == "UPD_TR_EX" || this.updatePriceListBased[r].updateCode == "UPD_NTR_EX" || this.updatePriceListBased[r].updateCode == "UPN_TR" || this.updatePriceListBased[r].updateCode == "UPD_TR")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Erection & Commission Engineer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Engineer") && (this.updatePriceListBased[r].updateCode == "UP_ECN" || this.updatePriceListBased[r].updateCode == "UP_EC")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Erection & Commission Reviewer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Reviewer" || this.updatePriceListBased[r].statusName == "Pending With Reviewer" ) && (this.updatePriceListBased[r].updateCode == "UP_ECN" || this.updatePriceListBased[r].updateCode == "UP_EC")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "Erection & Commission Approver": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Approver" || this.updatePriceListBased[r].statusName == "Pending With Approver" ) && (this.updatePriceListBased[r].updateCode == "UP_ECN" || this.updatePriceListBased[r].updateCode == "UP_EC")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "DBO Mechanical Engineer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Engineer") && (this.updatePriceListBased[r].updateCode == "MECH_PRICE_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_PRICE_UPD" || this.updatePriceListBased[r].updateCode == "MECH_ADDON_COST_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_COL_VAL_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_OVER_TANK_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_FRM_SPEC_DATA_UPD")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "DBO Mechanical Reviewer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Reviewer" || this.updatePriceListBased[r].statusName == "Pending With Reviewer" ) && (this.updatePriceListBased[r].updateCode == "MECH_PRICE_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_PRICE_UPD" || this.updatePriceListBased[r].updateCode == "MECH_ADDON_COST_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_COL_VAL_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_OVER_TANK_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_FRM_SPEC_DATA_UPD")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "DBO Mechanical Approver": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Approver" || this.updatePriceListBased[r].statusName == "Pending With Approver" ) && (this.updatePriceListBased[r].updateCode == "MECH_PRICE_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_PRICE_UPD" || this.updatePriceListBased[r].updateCode == "MECH_ADDON_COST_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_COL_VAL_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_OVER_TANK_UPD" || this.updatePriceListBased[r].updateCode == "MECH_AUX_FRM_SPEC_DATA_UPD")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "DBO Electrical Engineer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Engineer") && (this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_ADDON_PRICE" || this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_ADDON_PRICE_NEW" || this.updatePriceListBased[r].updateCode =="UPD_DBO_ELE_COL" || this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_SPL_ADDON_PRICE" || this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_SPL_ADDON_PRICE_NEW"|| this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_PRICE" || this.updatePriceListBased[r].updateCode == "ELE_PRICE" ||  this.updatePriceListBased[r].updateCode == "INSTRUMENT_LIST2" ||  this.updatePriceListBased[r].updateCode == "INSTRUMENT_LIST1" || this.updatePriceListBased[r].updateCode == "ELE_COL_VAL" || this.updatePriceListBased[r].updateCode == "ELE_ADDON_COST_UPD"  || this.updatePriceListBased[r].updateCode == "VMS_FRM_LIST")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "DBO Electrical Reviewer": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Reviewer" || this.updatePriceListBased[r].statusName == "Pending With Reviewer" ) && (this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_ADDON_PRICE" || this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_ADDON_PRICE_NEW" || this.updatePriceListBased[r].updateCode =="UPD_DBO_ELE_COL" || this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_SPL_ADDON_PRICE" || this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_SPL_ADDON_PRICE_NEW"|| this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_PRICE" || this.updatePriceListBased[r].updateCode == "ELE_PRICE" ||  this.updatePriceListBased[r].updateCode == "INSTRUMENT_LIST2" ||  this.updatePriceListBased[r].updateCode == "INSTRUMENT_LIST1" || this.updatePriceListBased[r].updateCode == "ELE_COL_VAL" || this.updatePriceListBased[r].updateCode == "ELE_ADDON_COST_UPD"  || this.updatePriceListBased[r].updateCode == "VMS_FRM_LIST" )) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          case "DBO Electrical Approver": {
            for (let r = 0; r < this.updatePriceListBased.length; r++) {
              if ((this.updatePriceListBased[r].statusName == "Saved By Approver" || this.updatePriceListBased[r].statusName == "Pending With Approver" ) && (this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_ADDON_PRICE" || this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_ADDON_PRICE_NEW" || this.updatePriceListBased[r].updateCode =="UPD_DBO_ELE_COL" || this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_SPL_ADDON_PRICE" || this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_SPL_ADDON_PRICE_NEW"|| this.updatePriceListBased[r].updateCode == "UPD_DBO_ELE_PRICE" || this.updatePriceListBased[r].updateCode == "ELE_PRICE" || this.updatePriceListBased[r].updateCode == "ELE_COL_VAL" || this.updatePriceListBased[r].updateCode == "INSTRUMENT_LIST2" || this.updatePriceListBased[r].updateCode == "INSTRUMENT_LIST1" || this.updatePriceListBased[r].updateCode == "ELE_ADDON_COST_UPD"  || this.updatePriceListBased[r].updateCode == "VMS_FRM_LIST")) {
                this.updatePriceList.push(this.updatePriceListBased[r]);
              }
            }console.log(this.updatePriceList);
            break;
          }
          default: {

            break;
          }
        }
        this.allreqTemp = resp.updatePriceOthersRequestGrid;
        for (let a = 0; a < this.allreqTemp.length; a++) {

          this.allRequests.push(this.allreqTemp[a]);

        }
        this.saveBasicDet = resp.saveBasicDetails;
        this.hideprogress = false;
      });
    });
    this._ITOMyWorkListPageService.selectedUR = '';
  }

  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
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

    this.colss = [
      { field: 'displayReqNumber', header: 'Req No' },
      { field: 'createdBy', header: 'Created By' },
      { field: 'assignedToName', header: 'Assigned To' },
      { field: 'modifiedDate', header: 'Pending From' },
      { field: 'statusName', header: 'Status' }
    ];
  }


  navigatetoNextCost(selectedUR) {
    console.log(selectedUR.statusName.includes('Reviewer'));
    console.log(this.loggedInRole.includes('Reviewer'));
    this._ITOMyWorkListPageService.selectedUR = selectedUR;
    this.saveBasicDet.updateRequestNumber = selectedUR.updateRequestNumber;
    this.saveBasicDet.modifiedById = selectedUR.modifiedById;
    this.userRoles = this.data[this.user].userRolesList;
    console.log(selectedUR);
    switch (this.storage.get(this.currentRole)) {
      case "TRANS_EDIT": {
        // this.transpEdit = true;
        // this.labelName = "Please select the reviewr";
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "TRANS_REVIWER") {
            this.saveBasicDet.loggedInUserCode = 0;
          }
        }
        break;
      }
      case "TRANS_APPROVER": {
        // this.transpApp = true;
        this.saveBasicDet.loggedInUserCode = 2;
        break;

      }
      case "TRANS_REVIWER": {
        // this.transRev = true;
        // this.labelName = "Please select the approver";
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "TRANS_APPROVER") {
            this.saveBasicDet.loggedInUserCode = 1;
          }
        }
        break;
      } case "DBO_MECH_EDIT": {
        // this.transpEdit = true;
        // this.labelName = "Please select the reviewr";
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "DBO_MECH_REVIWER") {
            this.saveBasicDet.loggedInUserCode = 0;
          }
        }
        break;
      }
      case "DBO_MECH_APPROVER": {
        // this.transpApp = true;
        this.saveBasicDet.loggedInUserCode = 2;
        break;

      }
      case "DBO_MECH_REVIWER": {
        // this.transRev = true;
        // this.labelName = "Please select the approver";
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "DBO_MECH_APPROVER") {
            this.saveBasicDet.loggedInUserCode = 1;
          }
        }
        break;
      }case "DBO_ELE_EDIT": {
        // this.transpEdit = true;
        // this.labelName = "Please select the reviewr";
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "DBO_ELE_REVIWER") {
            this.saveBasicDet.loggedInUserCode = 0;
          }
        }
        break;
      }
      case "DBO_ELE_APPROVER": {
        // this.transpApp = true;
        this.saveBasicDet.loggedInUserCode = 2;
        break;

      }
      case "DBO_ELE_REVIWER": {
        // this.transRev = true;
        // this.labelName = "Please select the approver";
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "DBO_ELE_APPROVER") {
            this.saveBasicDet.loggedInUserCode = 1;
          }
        }
        break;
      }
      case "EC_EDIT": {
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "EC_REVIWER") {
            this.saveBasicDet.loggedInUserCode = 0;

          }
        }
        break;
      }
      case "EC_APPROVER": {
        this.saveBasicDet.loggedInUserCode = 2;
        break;
      }
      case "EC_REVIWER": {
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "EC_APPROVER") {
            this.saveBasicDet.loggedInUserCode = 1;
          }
        }
        break;
      }
      case "F2F_EDIT": {
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "F2F_REVIWER") {
            this.saveBasicDet.loggedInUserCode = 0;
          }
        }
        break;
      }
      case "F2F_APPROVER": {
        this.saveBasicDet.loggedInUserCode = 2;
        break;
      }
      case "F2F_REVIWER": {
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "F2F_APPROVER") {
            this.saveBasicDet.loggedInUserCode = 1;
          }
        }
        break;
      }
      case "FINANCE_EDIT": {
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "FINANCE_REVIWER") {
            this.saveBasicDet.loggedInUserCode = 0;
          }
        }
        break;
      }
      case "FINANCE_APPROVER": {
        this.saveBasicDet.loggedInUserCode = 2;
        break;
      }
      case "FINANCE_REVIWER": {
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "FINANCE_APPROVER") {
            this.saveBasicDet.loggedInUserCode = 1;
          }
        }
        break;
      }
      case "UBO_EDIT": {
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "UBO_REVIWER") {
            this.saveBasicDet.loggedInUserCode = 0;
          }
        }
        break;
      }
      case "UBO_APPROVER": {
        this.saveBasicDet.loggedInUserCode = 2;
        break;
      }
      case "UBO_REVIWER": {
        for (let r = 0; r < this.usersList.length; r++) {
          if (this.usersList[r].groupCd == "UBO_APPROVER") {
            this.saveBasicDet.loggedInUserCode = 1;
          }
        }
        break;
      }
      default: {

        break;
      }
    }

    //this.saveBasicDet.loggedInUserCode
    console.log(this.saveBasicDet);
    this._ITOMyWorkListPageService.getUpdatePriceData(this.saveBasicDet).subscribe(respo => {
      console.log(respo);
      this._ITOMyWorkListPageService.responseTemp = respo;
      if (this.loggedInRole.includes('Reviewer')) {
        if (selectedUR.statusName.includes('Reviewer')) {
          this.navigatePath(selectedUR);
        }
        else {
          this._ITOLoginService.openSuccMsg("Request pending with Engineer/Approver , please check the status of the request and login with repective role to proceed");
          //alert("Request pending with Engineer/Approver , please check the status of the request and login with repective role to proceed");
        }

      } else if (this.loggedInRole.includes('Engineer')) {
        if (selectedUR.statusName.includes('Engineer')) {
          this.navigatePath(selectedUR);
        }
        else {
          this._ITOLoginService.openSuccMsg("Request pending with Reviewr/Approver, please check the status of the request and login with repective role to proceed");
          //alert("Request pending with Reviewr/Approver, please check the status of the request and login with repective role to proceed");
        }

      } else if (this.loggedInRole.includes('Approver')) {
        if (selectedUR.statusName.includes('Approver')) {
          this.navigatePath(selectedUR);
        }
        else {
          this._ITOLoginService.openSuccMsg("Request pending with Engineer/Reviewer , please check the status of the request and login with repective role to proceed");
          //alert("Request pending with Engineer/Reviewer , please check the status of the request and login with repective role to proceed");
        }

      }


    })
  }

  navigatePath(selectedUR) {
    if (selectedUR.updateCode === "UPD_TR" || selectedUR.updateCode === "UPN_TR" ||
      selectedUR.updateCode === "UPX_TR" || selectedUR.updateCode === "UPNX_TR") {
      this.router.navigate(['/updatePriceTransport']);
    }
    else if (selectedUR.updateCode === "UP_PF") {
      this.router.navigate(['/updatePckg']);
    } else if (selectedUR.updateCode === "UP_EC" || selectedUR.updateCode === "UP_ECN") {
      this.router.navigate(['/UpdatePriceEC']);
    } else if (selectedUR.updateCode === "UPD_UBO" || selectedUR.updateCode === "UPN_UBO") {
      this.router.navigate(['/UpdateFramePwrPrice']);
    } else if (selectedUR.updateCode === "UPD_TR_VH" || selectedUR.updateCode === "UPD_NTR_NV") {
      this.router.navigate(['/updateVehTransportDm']);
    } else if (selectedUR.updateCode === "UPD_TR_EX" || selectedUR.updateCode === "UPD_NTR_EX" ) {
      this.router.navigate(['/UpdateTransportExport']);
    } else if (selectedUR.updateCode === "UPD_UBO_OVRD" || selectedUR.updateCode === "UPD_TI_SUBC" || selectedUR.updateCode === "UPD_SUBCO" || selectedUR.updateCode == "F2F_SHOP_CONV_UPD") {
      this.router.navigate(['/updateTurbineCost']);
    } else if (selectedUR.updateCode === "MECH_PRICE_UPD" || selectedUR.updateCode === "MECH_AUX_PRICE_UPD" || selectedUR.updateCode === "MECH_ADDON_COST_UPD" || selectedUR.updateCode === "MECH_AUX_COL_VAL_UPD" || selectedUR.updateCode === "MECH_AUX_OVER_TANK_UPD" || selectedUR.updateCode === "MECH_AUX_FRM_SPEC_DATA_UPD" ) {
      this.router.navigate(['/UpdateCostDbomechPrice']);
    }  else if (selectedUR.updateCode === "UPD_DBO_ELE_PRICE" || selectedUR.updateCode === "UPD_DBO_ELE_PRICE_NEW" ) {
      this.router.navigate(['/UpdateCostDboelectPrice']);
    } else if (selectedUR.updateCode === "ELE_PRICE" || selectedUR.updateCode === "UPD_DBO_ELE_COL_NEW" || selectedUR.updateCode === "ELE_ADDON_COST_UPD"  || selectedUR.updateCode === "ELE_COL_VAL"  || selectedUR.updateCode === "INSTRUMENT_LIST2"  || selectedUR.updateCode === "INSTRUMENT_LIST1" || selectedUR.updateCode === "VMS_FRM_LIST" ) {
      this.router.navigate(['/updDboEleCol']);
    } else if (selectedUR.updateCode === "UPD_DBO_ELE_SPL_ADDON_PRICE" || selectedUR.updateCode === "UPD_DBO_ELE_SPL_ADDON_PRICE_NEW" ) {
      this.router.navigate(['/ItoUpdDboEleSpladdonComponent']);
    }  else if (selectedUR.updateCode === "UPD_DBO_MECH_COL" || selectedUR.updateCode === "UPD_DBO_MECH_COL_NEW" ) {
      this.router.navigate(['/updDboMechCol']);
 }else if (selectedUR.updateCode === "UPD_DBO_ELE_ADDON_PRICE" || selectedUR.updateCode === "UPD_DBO_ELE_ADDON_PRICE_NEW" ) {
      this.router.navigate(['/updDboEleAddOn']);
    }else if (selectedUR.updateCode === "UPD_DBO_ELE_ADDINSTR_PRICE" || selectedUR.updateCode === "UPD_DBO_ELE_ADDINSTR_PRICE_NEW" ) {
      this.router.navigate(['/updDboEleAddInstr']);
    }else if (selectedUR.updateCode ==="F2F_FRM_SPEC_DATA_UPD" || selectedUR.updateCode ==="F2F_COL_VAL_UPD"  || selectedUR.updateCode ==="F2F_PRICE_UPD" || selectedUR.updateCode === "UPD_ADDON" || selectedUR.updateCode === "UPD_ADDON_NEW") {
      this.router.navigate(['/updDboF2FAddons']);
    }
    
  }

  navigatetoNextQuot(selectedUR) {

    this.router.navigate(['/EditQuot']);
    this._ITOHomePageService.selectedQuot = selectedUR;

  }
}