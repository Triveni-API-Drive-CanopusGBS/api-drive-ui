import { Component, OnInit, AfterViewChecked, OnDestroy } from '@angular/core';
import { ItoUpdateTransportationService } from './ito-update-price-transport.service';
import { vehiclePriceClass } from './ito-update-price-transport';
import { transportationFields } from '../ito-transportation/ito-transportation';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { delay } from 'rxjs/operator/delay';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-ito-update-price-transport',
  templateUrl: './ito-update-price-transport.component.html',
  styleUrls: ['./ito-update-price-transport.component.css']
})
export class ItoUpdatePriceTransportComponent implements OnInit, OnDestroy {
  hideprogress: boolean = false;
  //Array to store vehicles list
  vehicleslist: Array<any> = [];
  //variable to store assing display
  assignDisp: boolean = false;
  //Array to store users list
  usersList: Array<any> = [];
  //array to store list of price
  listOfPrice: Array<any> = [];
  //variable to store selected vehicle details
  selectedVehicle: any;
  //variable to store transporation field columns
  updateType: transportationFields;
  //variable to store basic details
  saveBasic: any;
  //variable to store login user details
  loginUserDetails: string = "userDetail";
  //variable to store current role
  currentRole: string = 'selRole';
  //bvariable to store current role id
  currentRoleId: string = 'selRoleId';
  //variable to store local storage values
  localStorageValues: Array<any> = [];
  //variable to store modified by
  modifiedBy: number;
  //variable to store selected range
  selectedRanges: Array<any> = [];
  //Array to store final range list
  finalRangeList: Array<any> = [];
  //Array to store final frame list
  finalFrameList: Array<any> = [];
  //variable to store temp resp
  tempResp: any;
  //Array to store success messages
  successMsg: Array<any> = [];
  //Array to store user roles
  userRoles: Array<any> = [];
  //flag to check edit mode
  transpEdit: boolean = false;
  //flag to check Approver mode
  transpApp: boolean = false;
  //flag to check reviewer mode
  transRev: boolean = false;
  //variable to store label name
  labelName: string = '';
  //variable to store new users list
  newUsersLilst: Array<any> = [];
  //variable to store selected UR
  selectedUR: any;
  //variable to store response temp
  reponseTemp: any;
  //Array to store previous remarks
  prevRemarks: Array<any> = [];
  //Array to store vehicle with unit price
  vehicleWithUnitP: Array<any> = [];
  //Variable to display dialog
  displayDialog: boolean;
  //variable to display dialog export
  displayDialogExport: boolean;
  //variable to store vehicle price class columns
  vehiclDetails: vehiclePriceClass;
  //variable to store selected range
  selectedRange: any;
  //variable to store selected frame
  selectedFrame: any;
  //Array to store frames with export details
  framesWithExportDetails: Array<any> = [];
  //Array to store vehicles with price
  vehiclesWithPrice: Array<any> = [];
  //Array to store new vehicle list
  newVehicleList: Array<any> = [];
  //variable to store previous unit price
  prevUnitPrice: any;
  //Array to store frames with export type details
  framesWithExportType: Array<any> = [];
  //Array to store new frame for export
  newFramesForExport: Array<any> = [];
  //variable to store cols
  cols: any[];
  //variable to store cols2
  cols2: any[];
  //variable to store new cols
  newCols: any[];
  //variale to store vehicle template
  vehicleTemplate = [];
  //Array to store new color
  newColor: Array<any> = [];
  //variable to display contains
  contains: boolean;
  //variable to store selected new vehicle
  selectedNewVehicle: any;
  //variable to store remarks
  remarkss: any;
  //variable to store vehocle selected
  vehicleSel: any = '';
  //variable to store select Dom
  selectDom: boolean = true;
  //variable to display Dom
  disableDom: boolean = false;
  selectEx: boolean = false;
  disableEx: boolean = false;
  selectNew: boolean = false;
  disableNew: boolean = false;
  selectNewEx: boolean = false;
  disableNewEx: boolean = false;
  FobSel: boolean = false;
  CifSel: boolean = false;
  displayCD: boolean = false;
  displayBP: boolean = false;
  ACCBP: number;
  ACCCD: number;
  WCCBP: number;
  WCCCD: number;
  BBP: number;
  BCD: number;
  //Array to store trans expot type
  transExportType: Array<any> = [];
  //Array to store condenser type
  condenserType: Array<any> = [];
  //variable to dispaly message
  displayMessage: boolean = false;
  //variable to store selected new frame
  selectedNewFrame: any;
  acceptedOnly: boolean = true;
  //variable to store selected range temp
  selectedRangeTemp: number;
  //Array to store vehicles with price temp
  vehiclesWithPriceTemp: Array<any> = [];
  //array to store frmaes with export details temp
  framesWithExportDeTemp: Array<any> = [];
  transSub: Subscription;
  adminForm:any;/// to send bulk data
  percentage:number=0;
  displayDialogBulk:boolean=false;


  constructor(private _ItoUpdateTransportationService: ItoUpdateTransportationService, private _ITOLoginService: ITOLoginService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOMyWorkListPageService: ITOMyWorkListPageService, private router: Router, private route: ActivatedRoute) {
      this.hideprogress = true;
    this.finalRangeList = [];
    this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
    this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
    this.transSub = this._ItoUpdateTransportationService.getTransportationCache().subscribe(res => {
      console.log(res);
      this.saveBasic = res.saveBasicDetails;
      this.tempResp = res;
      this.newVehicleList = res.dropDownColumnvalues.newlyAdded_vehiclesList.NewlyAdded_VehiclesList;
      this.transExportType = res.dropDownColumnvalues.transportationType.TransportTypes;
      this.condenserType = res.dropDownColumnvalues.typesOfCondensor.CONDENSOR;
      this.framesWithExportDetails = res.dropDownColumnvalues.exportTransportDetails.EXPORT_TRANS_DETAILS;
      this.newFramesForExport = res.dropDownColumnvalues.newframeWithPowerForExportTrans.NEW_FRAMES_FOR_EXPORT;
      console.log(this._ITOMyWorkListPageService.selectedUR);
      if (this._ITOMyWorkListPageService.selectedUR != '') {
        this.selectDom = false;
        this.disableDom = true;
        this.selectEx = false;
        this.disableEx = true;
        this.selectNew = false;
        this.disableNew = true;
        this.selectNewEx = false;
        this.disableNewEx = true;
        console.log(this._ITOMyWorkListPageService.responseTemp);
        this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
        this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;

        if (this.selectedUR.updateCode == "UPD_TR") {
          this.selectDom = true;
          this.disableDom = false;
        }
        else if (this.selectedUR.updateCode == "UPN_TR") {
          this.selectNew = true;
          this.disableNew = false;
        }
        else if (this.selectedUR.updateCode == "UPX_TR") {
          this.selectEx = true;
          this.disableEx = false;
        }
        else if (this.selectedUR.updateCode == "UPNX_TR") {
          this.selectNewEx = true;
          this.disableNewEx = false;
          this.finalFrameList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportList;
          for (let f = 0; f < this.finalFrameList.length; f++) {
            for (let n = 0; n < res.dropDownColumnvalues.newframeWithPowerForExportTrans.NEW_FRAMES_FOR_EXPORT.length; n++) {
              if (this.finalFrameList[0].frameId == res.dropDownColumnvalues.newframeWithPowerForExportTrans.NEW_FRAMES_FOR_EXPORT[n].frameId
                && this.finalFrameList[0].maxPower == res.dropDownColumnvalues.newframeWithPowerForExportTrans.NEW_FRAMES_FOR_EXPORT[n].power) {
                this.selectedNewFrame = res.dropDownColumnvalues.newframeWithPowerForExportTrans.NEW_FRAMES_FOR_EXPORT[n].framePowerDesc;
              }
            }
          }
          if (this.finalFrameList[0].turbineCode == "BP") {
            this.displayBP = true;
            this.BBP = this.finalFrameList[0].previousPrice;
            this.BCD = this.finalFrameList[1].previousPrice;
          }
          else if (this.finalFrameList[0].turbineCode == "CD") {
            this.displayCD = true;
            this.ACCBP = this.finalFrameList[0].previousPrice;
            this.ACCCD = this.finalFrameList[1].previousPrice;
            this.WCCBP = this.finalFrameList[2].previousPrice;
            this.WCCCD = this.finalFrameList[3].previousPrice;
          }
        }
        this.selectedUR.updateCode
        this.vehicleslist = res.dropDownColumnvalues.vehiclesList.VehiclesList;
        this.vehicleWithUnitP = res.dropDownColumnvalues.vehicleWithUnitPrice.vehicleWithUnitList;

        if (this.storage.get(this.currentRole) == "TRANS_EDIT") {
          this.vehiclesWithPrice = this._ITOMyWorkListPageService.responseTemp.unsavedUpdatePriceTransportList;
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportList;
          this.saveBasic.updateRequestNumber = this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
          this.framesWithExportType = this._ITOMyWorkListPageService.responseTemp.unsavedUpdatePriceTransportList;
          if (this.selectedUR.updateCode != "UPNX_TR") {
            this.finalFrameList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportList;
          }

          if (this._ITOMyWorkListPageService.responseTemp.unsavedUpdatePriceTransportList[0].transType == "FOB AT INDIAN PORT") {
            this.FobSel = true;
          }
          else {
            this.CifSel = true;
          }
        }
        else if (this.storage.get(this.currentRole) != "TRANS_EDIT") {
          this.vehiclesWithPrice = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportList;
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportList;
          this.framesWithExportType = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportList;
          this.finalFrameList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePriceTransportList;
        }

        this.newCols = [
          { field: 'minDistance', header: 'Minimum Distance' },
          { field: 'maxDistance', header: 'Maximum Distance' },
          { field: 'unitPrice', header: 'Unit Cost' }
        ];
        if (this.newVehicleList.length > 0) {
          for (let n = 0; n < this.newVehicleList.length; n++) {
            if (res.dropDownColumnvalues.newlyAdded_vehiclesList.NewlyAdded_VehiclesList[n].categoryDetId == this.finalRangeList[0].vehicleId) {
              res.dropDownColumnvalues.newlyAdded_vehiclesList.NewlyAdded_VehiclesList[n].defaultVal = true;
              break;
            }
          }
        }
        this.vehicleTemplate = [
          { minDistance: 0, maxDistance: 250, unitPrice: 0, vehicleId: this.finalRangeList[0].vehicleId },
          { minDistance: 251, maxDistance: 500, unitPrice: 0, vehicleId: this.finalRangeList[0].vehicleId },
          { minDistance: 501, maxDistance: 1000, unitPrice: 0, vehicleId: this.finalRangeList[0].vehicleId },
          { minDistance: 1001, maxDistance: 1500, unitPrice: 0, vehicleId: this.finalRangeList[0].vehicleId },
          { minDistance: 1501, maxDistance: 2000, unitPrice: 0, vehicleId: this.finalRangeList[0].vehicleId },
          { minDistance: 2001, maxDistance: 2600, unitPrice: 0, vehicleId: this.finalRangeList[0].vehicleId }
        ];
        for (let f = 0; f < this.finalRangeList.length; f++) {
          this.vehicleTemplate[f].unitPrice = this.finalRangeList[f].unitPrice;
          this.vehicleTemplate[f].vehicleId = this.finalRangeList[f].vehicleId;
        }
        console.log(this.vehiclesWithPrice);
        console.log(this.vehicleWithUnitP);
        for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
          this.prevRemarks.push(this.reponseTemp.commentList[r]);
        }
        this.cols = [
          { field: 'vehicleName', header: 'Vehicle Name' },
          { field: 'minDistance', header: 'Minimum Distance' },
          { field: 'maxDistance', header: 'Maximum Distance' },
          { field: 'unitPrice', header: 'Unit Cost' },
          { field: 'previousUnitPrice', header: 'Previous Cost' }
        ];
        this.cols2 = [
          { field: 'frameName', header: 'Frame Name' },
          { field: 'condensingTypeName', header: 'Condensing Type' },
          { field: 'price', header: 'Bangalore to Port Cost' },
          { field: 'previousPrice', header: 'Previous Cost' }
        ];
        this._ITOMyWorkListPageService.selectedUR = '';
      }
      else {
        this.vehicleslist = res.dropDownColumnvalues.vehiclesList.VehiclesList;
        this.vehiclesWithPrice = res.dropDownColumnvalues.vehicleWithUnitPrice.vehicleWithUnitList;
        this.cols = [
          { field: 'vehicleName', header: 'Vehicle Name' },
          { field: 'minDistance', header: 'Minimum Distance' },
          { field: 'maxDistance', header: 'Maximum Distance' },
          { field: 'unitPrice', header: 'Unit Cost' }
        ];
        this.cols2 = [
          { field: 'frameName', header: 'Frame Name' },
          // { field: 'turbineCode', header: 'Turbine Type' },
          { field: 'condensingTypeName', header: 'Condensing Type' },
          { field: 'price', header: 'Bangalore to Port Cost' }
        ];
        if (this.storage.get(this.currentRole) == "TRANS_EDIT") {
          this.saveBasic.updateRequestNumber = 0;
        }
      }
      console.log(this.vehicleWithUnitP);
      this.usersList = res.userDetailsList;
      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;
      console.log(this.localStorageValues[this.loginUserDetails]);
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
      switch (this.storage.get(this.currentRole)) {
        case "TRANS_EDIT": {
          this.transpEdit = true;
          this.labelName = "Please select the reviewr";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "TRANS_REVIWER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.saveBasic.loggedInUserCode = 0;
                this.saveBasic.statusId = 1;
              }
            }

          }
          break;
        }
        case "TRANS_APPROVER": {
          this.transpApp = true;
          this.saveBasic.loggedInUserCode = 2;
          this.saveBasic.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
          break;

        }
        case "TRANS_REVIWER": {
          this.transRev = true;
          this.labelName = "Please select the approver";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "TRANS_APPROVER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.saveBasic.loggedInUserCode = 1;
                this.saveBasic.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
              }
            }
          }
          break;
        }
        default: {

          break;
        }
      }
      this.hideprogress = false;
    });
  }


  ngOnInit() {
    this.finalRangeList = [];
    this._ItoUpdateTransportationService.getTransportationCache().subscribe(resTemp => {
      console.log(resTemp);
      this.vehiclesWithPriceTemp = resTemp.dropDownColumnvalues.vehicleWithUnitPrice.vehicleWithUnitList;
      this.framesWithExportDeTemp = resTemp.dropDownColumnvalues.exportTransportDetails.EXPORT_TRANS_DETAILS;
    });
  }

  ngOnDestroy() {
    this.transSub.unsubscribe();
  }

  ngAfterViewChecked() {

    if (this.storage.get(this.currentRole) == "TRANS_EDIT") {

      for (let d = 0; d < this.vehiclesWithPrice.length; d++) {
        for (let f = 0; f < this.finalRangeList.length; f++) {
          if (this.vehiclesWithPrice[d].unitId == this.finalRangeList[f].unitId) {
            document.getElementById(this.vehiclesWithPrice[d].unitId).style.backgroundColor = "#0275d8";
            this.vehiclesWithPrice[d] = this.finalRangeList[f];
            console.log(this.vehiclesWithPrice[d]);
          }
        }
      }
      for (let d = 0; d < this.framesWithExportType.length; d++) {
        for (let f = 0; f < this.finalFrameList.length; f++) {
          if (this.framesWithExportType[d].transId == this.finalFrameList[f].transId) {
            document.getElementById(this.framesWithExportType[d].transId + this.vehiclesWithPrice.length + 1).style.backgroundColor = "#0275d8";
            console.log(this.framesWithExportType[d]);
          }
        }
      }
    }
  }
//Maethod called on selection of expot type
  expTypeSel(exp) {
    this.framesWithExportType = [];
    console.log(exp);
    for (let e = 0; e < this.framesWithExportDetails.length; e++) {
      if (exp == this.framesWithExportDetails[e].transType) {
        this.framesWithExportType.push(this.framesWithExportDetails[e]);
      }
    }
  }
//Method called on selection of new frame
  newFrameSel(newFrame) {
    this.displayBP = false;
    this.displayCD = false;
    this.finalFrameList = [];
    console.log(newFrame);
    for (let n = 0; n < this.newFramesForExport.length; n++) {
      if (this.newFramesForExport[n].framePowerDesc === newFrame) {
        if (this.newFramesForExport[n].turbineCode == "BP") {
          this.displayBP = true;
        }
        else if (this.newFramesForExport[n].turbineCode == "CD") {
          this.displayCD = true;
        }
        this.finalFrameList.push(this.newFramesForExport[n]);
      }
    }
  }
//Method called on tab change
  onTabChange(event) {
    console.log(event);
    this.finalRangeList = [];
    this.finalFrameList = [];
    this.remarkss = '';
    if (event.index == 0) {
      this.selectDom = true;
      this.selectEx = false;
      this.selectNew = false;
      this.selectNewEx = false;
    }
    else if (event.index == 1) {
      this.selectDom = false;
      this.selectEx = true;
      this.selectNew = false;
      this.selectNewEx = false;
    }
    else if (event.index == 2) {
      this.selectDom = false;
      this.selectEx = false;
      this.selectNew = true;
      this.selectNewEx = false;
    }
    else if (event.index == 3) {
      this.selectDom = false;
      this.selectEx = false;
      this.selectNew = false;
      this.selectNewEx = true;
    }
    console.log(this.disableDom, this.disableEx, this.disableNew);
  }
//Method called on selection of vehicle
  newVehicleSel(newVehicle) {
    for (let n = 0; n < this.newVehicleList.length; n++) {
      if (this.newVehicleList[n].categoryDetDesc === newVehicle) {
        this.selectedNewVehicle = this.newVehicleList[n];
      }
    }
    this.newCols = [
      { field: 'minDistance', header: 'Minimum Distance' },
      { field: 'maxDistance', header: 'Maximum Distance' },
      { field: 'unitPrice', header: 'Unit Cost' }
    ];
    this.vehicleTemplate = [
      { minDistance: 0, maxDistance: 250, unitPrice: 0, vehicleId: this.selectedNewVehicle.categoryDetId },
      { minDistance: 251, maxDistance: 500, unitPrice: 0, vehicleId: this.selectedNewVehicle.categoryDetId },
      { minDistance: 501, maxDistance: 1000, unitPrice: 0, vehicleId: this.selectedNewVehicle.categoryDetId },
      { minDistance: 1001, maxDistance: 1500, unitPrice: 0, vehicleId: this.selectedNewVehicle.categoryDetId },
      { minDistance: 1501, maxDistance: 2000, unitPrice: 0, vehicleId: this.selectedNewVehicle.categoryDetId },
      { minDistance: 2001, maxDistance: 2600, unitPrice: 0, vehicleId: this.selectedNewVehicle.categoryDetId }
    ];

  }
  //Method to call on click save
  submitNewVehicleDetails() {
    console.log(this.vehicleTemplate);
    this.finalRangeList = this.vehicleTemplate;
  }

//Edit row functionality for transportation Domestic
  rowSelTransDom(rowData) {
    console.log(this.vehicleWithUnitP);
    console.log(rowData);
    this.displayDialog = true;
    this.selectedRange = rowData;
    for (let v = 0; v < this.vehicleWithUnitP.length; v++) {
      if (this.vehicleWithUnitP[v].unitId == this.selectedRange.unitId) {
        this.prevUnitPrice = this.vehicleWithUnitP[v].unitPrice;
      }
    }
  }
  //Edit row functionality for transportation export
  rowSelTransExp(rowdata) {
    console.log(rowdata);
    console.log(this.framesWithExportType);
    this.displayDialogExport = true;
    this.selectedFrame = rowdata;
    for (let v = 0; v < this.framesWithExportType.length; v++) {
      if (this.framesWithExportType[v].frameId == this.selectedFrame.frameId) {
        this.prevUnitPrice = this.framesWithExportType[v].price;
      }
    }
  }
  // disble dialog of succcess message
  closeMessage() {
    this.displayMessage = false;
    this.finalFrameList = [];
    this.finalRangeList = [];
    this.remarkss = '';
    this.successMsg = [];
    this.acceptedOnly = true;
    this.navigateToMyWorkflow();
    for (let d = 0; d < this.vehiclesWithPrice.length; d++) {
      for (let f = 0; f < this.finalRangeList.length; f++) {
        if (this.vehiclesWithPrice[d].unitId == this.finalRangeList[f].unitId) {
          document.getElementById(this.vehiclesWithPrice[d].unitId).style.backgroundColor = "#0275d8";
          console.log(this.vehiclesWithPrice[d]);
        }
      }
    }
    for (let d = 0; d < this.framesWithExportType.length; d++) {
      for (let f = 0; f < this.finalFrameList.length; f++) {
        if (this.framesWithExportType[d].transId == this.finalFrameList[f].transId) {
          document.getElementById(this.framesWithExportType[d].transId + this.vehiclesWithPrice.length + 1).style.backgroundColor = "#0275d8";
          console.log(this.framesWithExportType[d]);
        }
      }
    }
  }

  // save overwritten data for domestic transport 
  save() {
    console.log(this.vehiclesWithPriceTemp);
    console.log(this.selectedRange);
    for (let d = 0; d < this.vehiclesWithPriceTemp.length; d++) {
      if (this.vehiclesWithPriceTemp[d].unitId == this.selectedRange.unitId) {
        this.selectedRangeTemp = this.vehiclesWithPriceTemp[d].unitPrice;
      }
    }
    console.log(this.selectedRangeTemp);
    console.log(this.selectedRange.unitPrice);
    if (this.selectedRange.unitPrice != this.selectedRangeTemp) {
      if (this.finalRangeList.length != 0) {
        for (let s = 0; s < this.finalRangeList.length; s++) {
          if (this.finalRangeList[s].unitId === this.selectedRange.unitId) {
            this.finalRangeList[this.finalRangeList.indexOf(this.finalRangeList[s])] = this.selectedRange;
            let butn = document.getElementById(this.finalRangeList[s].unitId).style.backgroundColor = "#0275d8";
            this.contains = true;
            break;
          }
        }
        if (!this.contains) {
          this.finalRangeList.push(this.selectedRange);
          for (let s = 0; s < this.finalRangeList.length; s++) {
            let butn = document.getElementById(this.finalRangeList[s].unitId).style.backgroundColor = "#0275d8";

          }
        }
        else {
          this.contains = false;
        }

      }
      else {
        this.finalRangeList.push(this.selectedRange);
        for (let s = 0; s < this.finalRangeList.length; s++) {
          let butn = document.getElementById(this.finalRangeList[s].unitId).style.backgroundColor = "#0275d8";
        }
      }
      this.displayDialog = false;
      console.log(this.finalRangeList);
      //  let butn = document.getElementById(this.selectedRange.unitId).style.backgroundColor = "#0275d8";
    }
    else {
      this.displayDialog = false;
    }
  }
//Method to call on providing serch criteria
  searchSet(dtd, value) {
    console.log(dtd.filteredValue, value, "keyUp");
    console.log(this.finalRangeList);
    if (dtd.filteredValue != null) {
      for (let d = 0; d < dtd.filteredValue.length; d++) {
        for (let f = 0; f < this.finalRangeList.length; f++) {
          if (dtd.filteredValue[d].unitId == this.finalRangeList[f].unitId) {
            let butn = document.getElementById(dtd.filteredValue[d].unitId).style.backgroundColor = "#0275d8";
          }
        }
      }
    }
    else {
      for (let d = 0; d < this.vehiclesWithPrice.length; d++) {
        for (let f = 0; f < this.finalRangeList.length; f++) {
          if (this.vehiclesWithPrice[d].unitId == this.finalRangeList[f].unitId) {
            let butn = document.getElementById(this.vehiclesWithPrice[d].unitId).style.backgroundColor = "#0275d8";
          }
        }
      }
    }

  }
//save tranportation export 
  saveTransExp() {
    console.log(this.selectedFrame);
    console.log(this.framesWithExportDeTemp);
    for (let d = 0; d < this.framesWithExportDeTemp.length; d++) {
      if (this.framesWithExportDeTemp[d].transId == this.selectedRange.transId) {
        this.selectedRangeTemp = this.framesWithExportDeTemp[d].price;
      }
    }
    console.log(this.selectedRangeTemp);
    console.log(this.selectedRange.price);
    if (this.selectedRange.price != this.selectedRangeTemp) {
      if (this.finalFrameList.length != 0) {
        for (let s = 0; s < this.finalFrameList.length; s++) {
          if (this.finalFrameList[s].transId === this.selectedFrame.transId) {
            this.finalFrameList[s] = this.selectedFrame;
            let butn = document.getElementById(this.finalFrameList[s].transId + this.vehiclesWithPrice.length + 1).style.backgroundColor = "#0275d8";
            this.contains = true;
            break;
          }
          if (!this.contains) {
            this.finalFrameList.push(this.selectedFrame);
            for (let s = 0; s < this.finalFrameList.length; s++) {
              let butn = document.getElementById(this.finalFrameList[s].transId + this.vehiclesWithPrice.length + 1).style.backgroundColor = "#0275d8";

            }
          }
          else {
            this.contains = false;
          }
        }
      }
      else {
        this.finalFrameList.push(this.selectedFrame);
        for (let s = 0; s < this.finalFrameList.length; s++) {
          let butn = document.getElementById(this.finalFrameList[s].transId + this.vehiclesWithPrice.length + 1).style.backgroundColor = "#0275d8";
        }
      }
      this.displayDialogExport = false;
      console.log(this.finalFrameList);
    } else {
      this.displayDialogExport = false;
    }
  }


//Method to updated details into DB
  updatePriceTransForm(updatePriceTrans) {
    this.successMsg = [];
    console.log(this.tempResp);
    this.tempResp.updatePriceTransportList = [];
    console.log(updatePriceTrans);
    this.saveBasic.remarks = updatePriceTrans.coments;
    this.assignDisp = true;
    console.log(this.remarkss);
    this.vehiclDetails = new vehiclePriceClass();
    console.log(this.finalRangeList);
    for (let v = 0; v < this.finalRangeList.length; v++) {
      this.updateType = new transportationFields();
      if (this.finalRangeList[v] != undefined) {
        console.log(this.finalRangeList[v]);
        this.updateType.unitId = this.finalRangeList[v].unitId;
        this.updateType.vehicleId = this.finalRangeList[v].vehicleId;
        this.updateType.dimension = this.finalRangeList[v].dimension;
        this.updateType.length = this.finalRangeList[v].length;
        this.updateType.maxDistance = this.finalRangeList[v].maxDistance;
        this.updateType.minDistance = this.finalRangeList[v].minDistance;
        this.updateType.unitPrice = this.finalRangeList[v].unitPrice;
        if (this.selectDom == true) {
          this.saveBasic.updateCode = "UPD_TR";
        }
        else if (this.selectNew == true) {
          this.saveBasic.updateCode = "UPN_TR";
        }
        this.saveBasic.modifiedById = this.modifiedBy;
        this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
        if (updatePriceTrans.status == "Accept") {
          this.saveBasic.statusId = 1;
        }
        else if (updatePriceTrans.status == "Reject") {
          this.saveBasic.statusId = 0;
        }
        this.tempResp.saveBasicDetails = this.saveBasic;
        this.tempResp.updatePriceTransportList.push(this.updateType);
        console.log(this.tempResp);
      }
    }
    this._ItoUpdateTransportationService.updatePriceTransport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) === "TRANS_APPROVER") {
        this.saveBasic.assignedTo = this.modifiedBy;
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateTransportationService.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
          this._ItoUpdateTransportationService.saveTransportPrice(respon).subscribe(respo => {
            console.log(respo);
            this.successMsg.push(respo.successMsg);
          })
        })

      } else {
        this._ItoUpdateTransportationService.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }
    });
    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.vehiclesWithPrice.length; vv++) {
      let butn = document.getElementById(this.vehiclesWithPrice[vv].unitId).style.backgroundColor = "";
    }


  }
  // save as method
  SaveAsDraft() {
    this.successMsg = [];
    this.tempResp.updatePriceTransportList = [];
    // console.log(updatePriceTrans);
    this.saveBasic.remarks = this.remarkss;
    this.assignDisp = true;
    console.log(this.remarkss);
    this.vehiclDetails = new vehiclePriceClass();
    console.log(this.finalRangeList);
    for (let v = 0; v < this.finalRangeList.length; v++) {
      this.updateType = new transportationFields();
      if (this.finalRangeList[v] != undefined) {
        console.log(this.finalRangeList[v]);
        this.updateType.unitId = this.finalRangeList[v].unitId;
        this.updateType.vehicleId = this.finalRangeList[v].vehicleId;
        this.updateType.dimension = this.finalRangeList[v].dimension;
        this.updateType.length = this.finalRangeList[v].length;
        this.updateType.maxDistance = this.finalRangeList[v].maxDistance;
        this.updateType.minDistance = this.finalRangeList[v].minDistance;
        this.updateType.unitPrice = this.finalRangeList[v].unitPrice;
        if (this.selectDom == true) {
          this.saveBasic.updateCode = "UPD_TR";
        }
        else if (this.selectNew == true) {
          this.saveBasic.updateCode = "UPN_TR";
        }
        this.saveBasic.modifiedById = this.modifiedBy;
        this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
        // if (updatePriceTrans.status == "Accept") {
        //   this.saveBasic.statusId = 1;
        // }
        // else if (updatePriceTrans.status == "Reject") {
        //   this.saveBasic.statusId = 0;
        // }
        this.tempResp.saveBasicDetails = this.saveBasic;
        this.tempResp.updatePriceTransportList.push(this.updateType);
        console.log(this.tempResp);
      }
    }
    this._ItoUpdateTransportationService.updatePriceTransport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.saveBasic.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.tempResp = resp;
      // for (let r = 0; r < this.userRoles.length; r++) {
      //   if (this.userRoles[r].code === "TRANS_APPROVER") {
      //     this._ItoUpdateTransportationService.updateStatus(resp).subscribe(respon => {
      //       console.log(respon);
      //       this.successMsg = respon.successMsg;
      //       this._ItoUpdateTransportationService.saveTransportPrice(respon).subscribe(respo => {
      //         console.log(respo);
      //         this.successMsg = respo.successMsg;
      //       })
      //     })

      //   } else {
      //     this._ItoUpdateTransportationService.updateStatus(resp).subscribe(respon => {
      //       console.log(respon);
      //       this.successMsg = respon.successMsg;
      //     })
      //   }
      // }
    });
    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.vehiclesWithPrice.length; vv++) {
      let butn = document.getElementById(this.vehiclesWithPrice[vv].unitId).style.backgroundColor = "";
    }

  }

  // Update price transportation export

  updatePriceTransExp(updatePriceTransEx) {
    this.successMsg = [];
    console.log(this.tempResp);
    this.tempResp.updatePriceTransportList = [];
    console.log(updatePriceTransEx);
    this.saveBasic.remarks = updatePriceTransEx.coments;
    this.assignDisp = true;
    console.log(this.remarkss);
    this.vehiclDetails = new vehiclePriceClass();
    console.log(this.finalFrameList);
    for (let v = 0; v < this.finalFrameList.length; v++) {
      this.updateType = new transportationFields();
      if (this.finalFrameList[v] != undefined) {
        console.log(this.finalFrameList[v]);
        this.updateType.frameId = this.finalFrameList[v].frameId;
        this.updateType.condensingTypeId = this.finalFrameList[v].condensingTypeId;
        this.updateType.price = this.finalFrameList[v].price;
        this.updateType.transTypeId = this.finalFrameList[v].transTypeId;
        this.updateType.transId = this.finalFrameList[v].transId;
        this.saveBasic.custType = this.finalFrameList[v].custType;

        if (this.selectNew == true) {
          this.saveBasic.updateCode = "UPN_TR";
        }
        else if (this.selectEx == true) {
          this.saveBasic.updateCode = "UPX_TR";
        }
        this.saveBasic.modifiedById = this.modifiedBy;
        this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
        if (updatePriceTransEx.status == "Accept") {
          this.saveBasic.statusId = 1;
        }
        else if (updatePriceTransEx.status == "Reject") {
          this.saveBasic.statusId = 0;
        }
        this.tempResp.saveBasicDetails = this.saveBasic;
        this.tempResp.updatePriceTransportList.push(this.updateType);
        console.log(this.tempResp);
      }
    }
    this._ItoUpdateTransportationService.updatePriceTransport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) === "TRANS_APPROVER") {
        this.saveBasic.assignedTo = this.modifiedBy;
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateTransportationService.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
          this._ItoUpdateTransportationService.saveTransportPrice(respon).subscribe(respo => {
            console.log(respo);
            this.successMsg.push(respo.successMsg);
          })
        })

      } else {
        this._ItoUpdateTransportationService.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }
    });
    this.displayMessage = true;
    this.finalFrameList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.vehiclesWithPrice.length; vv++) {
      let butn = document.getElementById(this.vehiclesWithPrice[vv].unitId).style.backgroundColor = "";
    }
  }
//Method to update status
  statusUpdate(status) {
    if (status == "Reject") {
      this.acceptedOnly = false;
    }
    else {
      this.acceptedOnly = true;
    }
  }
//Save as draft for export
  saveAsDraftEx() {
    this.successMsg = [];
    console.log(this.tempResp);
    this.tempResp.updatePriceTransportList = [];
    this.saveBasic.remarks = this.remarkss;
    this.assignDisp = true;
    console.log(this.remarkss);
    this.vehiclDetails = new vehiclePriceClass();
    console.log(this.finalFrameList);
    for (let v = 0; v < this.finalFrameList.length; v++) {
      this.updateType = new transportationFields();
      if (this.finalFrameList[v] != undefined) {
        console.log(this.finalFrameList[v]);
        this.updateType.frameId = this.finalFrameList[v].frameId;
        this.updateType.condensingTypeId = this.finalFrameList[v].condensingTypeId;
        this.updateType.price = this.finalFrameList[v].price;
        this.updateType.transTypeId = this.finalFrameList[v].transTypeId;
        this.updateType.transId = this.finalFrameList[v].transId;
        this.saveBasic.custType = this.finalFrameList[v].custType;

        if (this.selectNew == true) {
          this.saveBasic.updateCode = "UPN_TR";
        }
        else if (this.selectEx == true) {
          this.saveBasic.updateCode = "UPX_TR";
        }
        this.saveBasic.modifiedById = this.modifiedBy;
        this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
        this.tempResp.saveBasicDetails = this.saveBasic;
        this.tempResp.updatePriceTransportList.push(this.updateType);
        console.log(this.tempResp);
      }
    }
    this._ItoUpdateTransportationService.updatePriceTransportExport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);

    });
    this.displayMessage = true;
    this.finalFrameList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.vehiclesWithPrice.length; vv++) {
      let butn = document.getElementById(this.vehiclesWithPrice[vv].unitId).style.backgroundColor = "";
    }
  }

  newFrameTransForm(newFrameDetails) {
    console.log(newFrameDetails);
  }
//Method to updated details in to DB
  updatePriceTransExpNewFrameForm(updatePriceTransExpNewFrame) {
    console.log(updatePriceTransExpNewFrame);
    console.log(this.ACCBP, this.ACCCD, this.WCCBP, this.WCCCD, this.BBP, this.BCD);
    this.successMsg = [];
    console.log(this.tempResp);
    this.tempResp.updatePriceTransportList = [];
    console.log(updatePriceTransExpNewFrame);
    this.saveBasic.remarks = updatePriceTransExpNewFrame.coments;
    this.assignDisp = true;
    console.log(this.remarkss);
    this.vehiclDetails = new vehiclePriceClass();
    console.log(this.finalFrameList);
    for (let v = 0; v < this.finalFrameList.length; v++) {
      this.updateType = new transportationFields();
      if (this.finalFrameList[v] != undefined) {
        console.log(this.finalFrameList[v]);
        if (this.displayBP == true) {
          this.updateType.frameId = this.finalFrameList[v].frameId;
          this.updateType.condensingTypeId = 0;
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.BBP;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "FOB") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }

          this.tempResp.updatePriceTransportList.push(this.updateType);
          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          this.updateType.condensingTypeId = 0;
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.BCD;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "CIF") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);
        }
        if (this.displayCD == true) {
          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          for (let c = 0; c < this.condenserType.length; c++) {
            if (this.condenserType[c].categoryDetCode == "ACC") {
              this.updateType.condensingTypeId = this.condenserType[c].categoryDetId;
            }
          }
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.ACCBP;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "FOB") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);

          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          for (let c = 0; c < this.condenserType.length; c++) {
            if (this.condenserType[c].categoryDetCode == "ACC") {
              this.updateType.condensingTypeId = this.condenserType[c].categoryDetId;
            }
          }
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.ACCCD;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "CIF") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);
          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          for (let c = 0; c < this.condenserType.length; c++) {
            if (this.condenserType[c].categoryDetCode == "WCC") {
              this.updateType.condensingTypeId = this.condenserType[c].categoryDetId;
            }
          }
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.WCCBP;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "FOB") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);
          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          for (let c = 0; c < this.condenserType.length; c++) {
            if (this.condenserType[c].categoryDetCode == "WCC") {
              this.updateType.condensingTypeId = this.condenserType[c].categoryDetId;
            }
          }
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.WCCCD;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "CIF") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);

        }

        if (this.selectNew == true) {
          this.saveBasic.updateCode = "UPN_TR";
        }
        else if (this.selectNewEx == true) {
          this.saveBasic.updateCode = "UPNX_TR";
        }
        this.saveBasic.modifiedById = this.modifiedBy;
        this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
        if (updatePriceTransExpNewFrame.status == "Accept") {
          this.saveBasic.statusId = 1;
        }
        else if (updatePriceTransExpNewFrame.status == "Reject") {
          this.saveBasic.statusId = 0;
        }
        this.tempResp.saveBasicDetails = this.saveBasic;
        console.log(this.tempResp);
      }
    }
    this._ItoUpdateTransportationService.updatePriceTransportExport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) === "TRANS_APPROVER") {
        this.saveBasic.assignedTo = this.modifiedBy;
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateTransportationService.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
          this._ItoUpdateTransportationService.saveTransportPrice(respon).subscribe(respo => {
            console.log(respo);
            this.successMsg.push(respo.successMsg);
          })
        })

      } else {
        this._ItoUpdateTransportationService.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }
    });
    this.displayMessage = true;
    this.finalFrameList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.vehiclesWithPrice.length; vv++) {
      let butn = document.getElementById(this.vehiclesWithPrice[vv].unitId).style.backgroundColor = "";
    }

  }
  //Saev as draft for export's new frame
  saveAsDraftExNewFrame() {
    console.log(this.ACCBP, this.ACCCD, this.WCCBP, this.WCCCD, this.BBP, this.BCD);
    this.successMsg = [];
    console.log(this.tempResp);
    this.tempResp.updatePriceTransportList = [];
    this.saveBasic.remarks = this.remarkss;
    this.assignDisp = true;
    console.log(this.remarkss);
    this.vehiclDetails = new vehiclePriceClass();
    console.log(this.finalFrameList);
    for (let v = 0; v < this.finalFrameList.length; v++) {
      this.updateType = new transportationFields();
      if (this.finalFrameList[v] != undefined) {
        console.log(this.finalFrameList[v]);
        if (this.displayBP == true) {
          this.updateType.frameId = this.finalFrameList[v].frameId;
          this.updateType.condensingTypeId = 0;
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.BBP;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "FOB") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }

          this.tempResp.updatePriceTransportList.push(this.updateType);
          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          this.updateType.condensingTypeId = 0;
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.BCD;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "CIF") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);
        }
        if (this.displayCD == true) {
          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          for (let c = 0; c < this.condenserType.length; c++) {
            if (this.condenserType[c].categoryDetCode == "ACC") {
              this.updateType.condensingTypeId = this.condenserType[c].categoryDetId;
            }
          }
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.ACCBP;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "FOB") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);

          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          for (let c = 0; c < this.condenserType.length; c++) {
            if (this.condenserType[c].categoryDetCode == "ACC") {
              this.updateType.condensingTypeId = this.condenserType[c].categoryDetId;
            }
          }
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.ACCCD;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "CIF") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);
          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          for (let c = 0; c < this.condenserType.length; c++) {
            if (this.condenserType[c].categoryDetCode == "WCC") {
              this.updateType.condensingTypeId = this.condenserType[c].categoryDetId;
            }
          }
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.WCCBP;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "FOB") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);
          this.updateType = new transportationFields();
          this.updateType.frameId = this.finalFrameList[v].frameId;
          for (let c = 0; c < this.condenserType.length; c++) {
            if (this.condenserType[c].categoryDetCode == "WCC") {
              this.updateType.condensingTypeId = this.condenserType[c].categoryDetId;
            }
          }
          this.updateType.transId = 0;
          this.saveBasic.custType = "EX";
          this.updateType.price = this.WCCCD;
          for (let t = 0; t < this.transExportType.length; t++) {
            if (this.transExportType[t].categoryDetCode == "CIF") {
              this.updateType.transTypeId = this.transExportType[t].categoryDetId;
            }
          }
          this.tempResp.updatePriceTransportList.push(this.updateType);

        }

        if (this.selectNew == true) {
          this.saveBasic.updateCode = "UPN_TR";
        }
        else if (this.selectNewEx == true) {
          this.saveBasic.updateCode = "UPNX_TR";
        }
        this.saveBasic.modifiedById = this.modifiedBy;
        this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
        this.tempResp.saveBasicDetails = this.saveBasic;

        console.log(this.tempResp);
      }
    }
    this._ItoUpdateTransportationService.updatePriceTransportExport(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
    });
    this.displayMessage = true;
    this.finalFrameList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.vehiclesWithPrice.length; vv++) {
      let butn = document.getElementById(this.vehiclesWithPrice[vv].unitId).style.backgroundColor = "";
    }
  }
  //Set assigned user on change
  assignedUser(assigne) {
    console.log(assigne);
    console.log(this.tempResp.userDetailsList);
    this.saveBasic.assignedTo = assigne;

  }
  //Method called for navigate to work flow
  navigateToMyWorkflow() {
    this.router.navigate(['MyWorkFlow']);
  }
  saveBulk()
  {
    this._ItoUpdateTransportationService.getAdminForm().subscribe(res => {
      console.log(res);
      this.adminForm = res;
      this.adminForm.percent=this.percentage;
      this.adminForm.modifiedBy=this.modifiedBy;
    
    this._ItoUpdateTransportationService.getAdminPercentTrnsDm(this.adminForm).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.displayDialogBulk=false;
      this.displayMessage = true;


    });
  });
  }
  bulkUpd()
  {
    this.percentage=0;
    this.displayDialogBulk=true;

  }

}
