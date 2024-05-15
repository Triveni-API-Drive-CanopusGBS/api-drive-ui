import { Component, OnInit } from '@angular/core';
import { ITOUpdatePriceEcService } from '../ito-update-price-ec/ito-update-price-ec.service';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ItoUpdateTransportationService} from '../ito-update-price-transport/ito-update-price-transport.service';

@Component({
  selector: 'app-ito-update-price-ec',
  templateUrl: './ito-update-price-ec.component.html',
  styleUrls: ['./ito-update-price-ec.component.css']
})
export class ItoUpdatePriceEcComponent implements OnInit {

  adminForm:any;/// to send bulk data
  percentage:number=0;
  dispMessage: boolean = false;
  displayDialogBulk:boolean=false;
  //Array to store Ec price list
  ecPriceList: Array<any> = [];
  //Array to store turbine type list
  turbineTypesList: Array<any> = [];
  //Array to store turbine category list
  turbineCategoryList: Array<any> = [];
  //Array to store updated price list
  updatedPriceList: Array<any> = [];
  //Array to store users list
  usersList: Array<any> = [];
  //Array to store previous remarks
  prevRemarks: Array<any> = [];
  //Variable to store local storage values
  localStorageValues: Array<any> = [];
  //Array to store users list
  userRoles: Array<any> = [];
  //Array to store new users list
  newUsersList: Array<any> = [];
  //Array to store new frame with powers list
  newFrameWithPowersList: Array<any> = [];
  //Array to store framde list
  frameList: Array<any> = [];
  //Array to store new frame
  newFrame: Array<any> = [];
  //Array to store type of condensor
  typesOfCondensor: Array<any> = [];
  //Array to store success messages
  successMsg: Array<any> = [];

  enableCondDiv: boolean = false;
  //variable to display update EC- price dialog
  displayDialog: boolean = false;
  //variable to display condensing type
  dispCondTyp: boolean = false;
  //variable to display loding name
  dispUnloading: boolean = false;
  //flag to check EC edit 
  erecCommEdit: boolean = false;
  //flag to check EC Approver
  erecCommApprove: boolean = false;
  //flag to check EC reviewer
  erecCommReview: boolean = false;
  //variable to display request status dialog
  message: boolean = false;
  contains: boolean = false;
  displayAddDialog: boolean = false;
  //variable to disply save button
  enableSave: boolean = false;
  isDataSaved: boolean = false;
  //variable to display update cost tab
  exist: boolean = false;
  //variable to display Add new cost
  addNew: boolean = false;
  //variable to display progressSpinner
  hideprogress: boolean = true;
  enableSel: boolean = true;
  enableStatus: boolean = true;
  //variable to display update price ec form
  displayForm: boolean = true;

  selectedPriceReq: any = '';
  //variable to store modified by
  modifiedBy: number;
  //variable to store categoryDetID in DM
  custId: number;
  //variable to store categoryDetId in EC
  typOfChrgeIdEC: number;
  //bvariable to store categoryDetID in SUPR
  typOfChrgeIdSup: number;
  //variable to store categoryDetId in TUR
  loadingIdTurn: number;
  //variable to store categoryDetId in WTL
  loadingIdUnload: number;
  //variable to store categoryDetID in CS
  lodgingIdCust: number;
  //variable to store categoryDetID in TS
  lodgingIdTTL: number;
  //variable to store tab index
  tabIndex: number = 0;
  //variable to store categoryDetDesc in DM
  custType: string;
  //variable to store categoryDetDesc in EC
  typOfChrgeEC: string;
  //variable to store categoryDetDesc in SUPR
  typOfChrgeSup: string;
  //variable to store categoryDetDesc in TUR
  loadingTurn: string;
  //variable to store categoryDetDesc in WTL
  loadingUnload: string;
  //variable to store categoryDetDescin CS
  lodgingCust: string;
  //variable to store categoryDetDes in Ts
  lodgingTTL: string;
  //variable to store blade type
  bladeType: string;
  //variable to store login user details
  loginUserDetails: string = "userDetail";
  //variable to store label name
  labelName: string;
  //variable to store updated success message
  updSuccessMsg: string;
  //variable to store current role
  currentRole: string = 'selRole';
  //variable to store current role id
  currentRoleId:string = 'selRoleId';
  //variable to store cols
  cols: any[];
  //variable to store columns
  columns: any;
  //variable to store selected data
  selectedData: any;
  //variable to store quot form
  quotForm: any;
  //variable to store response temp 
  reponseTemp: any;
  //variable t store save basic
  saveBasic: any;
  //variable to store form
  form: any;
  //variable to store saved request quot form
  savedReqQuotForm: any;
  //variable to store ecForm
  ecform: any;

  constructor(private _ItoUpdateTransportationService: ItoUpdateTransportationService, private _ITOUpdatePriceEcService: ITOUpdatePriceEcService, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOMyWorkListPageService: ITOMyWorkListPageService, private router: Router, private route: ActivatedRoute) {
    

    this._ITOUpdatePriceEcService.getAdminForm().subscribe(res => {
      this._ITOUpdatePriceEcService.getECUpdatePriceData(res).subscribe(resp => {
        this.hideprogress = true;
        console.log(resp);
        this.usersList = resp.userProfileDetailsList;
        this.newFrameWithPowersList = resp.newFrameWithPowersList;
        this.typesOfCondensor = resp.dropDownColumnvalues.typesOfCondensor.CONDENSOR;
        console.log(this.newFrameWithPowersList)
        this.turbineTypesList = resp.dropDownColumnvalues.typesOfTurbine.TURBINE;
        this.turbineCategoryList = resp.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;

        console.log(this._ITOMyWorkListPageService.selectedUR)
        //if (this._ITOMyWorkListPageService.selectedUR != '') {
        if (this._ITOMyWorkListPageService.selectedUR) {
          console.log(this._ITOMyWorkListPageService.responseTemp);
          this.selectedPriceReq = this._ITOMyWorkListPageService.selectedUR;
          this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;

          if (this.storage.get(this.currentRole) == "EC_EDIT") {
            this.ecPriceList = this._ITOMyWorkListPageService.responseTemp.unsavedUpdateECPriceList;
            this.updatedPriceList = this._ITOMyWorkListPageService.responseTemp.savedUpdateECPriceList;

            for (let s = 0; s < this.ecPriceList.length; s++) {
              for (let q = 0; q < this.updatedPriceList.length; q++) {
                if (this.ecPriceList[s].ecId == this.updatedPriceList[q].ecId) {
                  this.ecPriceList[s].price = this.updatedPriceList[q].price;
                }
              }
            }
          }
          else if (this.storage.get(this.currentRole) != "EC_EDIT") {
            this.ecPriceList = this._ITOMyWorkListPageService.responseTemp.savedUpdateECPriceList;
            this.updatedPriceList = this._ITOMyWorkListPageService.responseTemp.savedUpdateECPriceList;
          }
          if (this.selectedPriceReq.updateCode == "UP_ECN") {
            this.columns = this.reponseTemp.savedUpdateECPriceList;
            this.newFrame = this.reponseTemp.savedUpdateECPriceList;
            console.log(this.newFrame)
            this.enableSel = false;
            this.exist = true;
            this.tabIndex = 1;

          } else if (this.selectedPriceReq.updateCode == "UP_EC") {
            this.addNew = true;
            this.tabIndex = 0
          }
          console.log(this.ecPriceList);
          for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
            this.prevRemarks.push(this.reponseTemp.commentList[r]);
          }
          this.cols = [
            { field: 'turbineType', header: 'Turbine Type' },
            { field: 'frameDesc', header: 'Frame' },
            { field: 'maxPower', header: 'Power' },
            { field: 'turbineDesign', header: 'Turbine Design' },
            { field: 'condensingType', header: 'Condensing Type' },
            // { field: 'ecType', header: 'EC Type' },
            // { field: 'typeOfCharge', header: 'Type of Charge' },
            { field: 'loadingName', header: 'Unloading' },
            { field: 'lodgingName', header: 'Lodging & Boarding ' },
            { field: 'price', header: 'Cost' },
            { field: 'previousPrice', header: 'Previous Cost' }
          ];
        }
        else {
          this._ITOUpdatePriceEcService.ecFramePriceList = resp.ecUpdatePriceList;
          this.ecPriceList = resp.ecUpdatePriceList;

          this.cols = [
            { field: 'turbineType', header: 'Turbine Type' },
            { field: 'frameDesc', header: 'Frame' },
            { field: 'maxPower', header: 'Power' },
            { field: 'turbineDesign', header: 'Turbine Design' },
            { field: 'condensingType', header: 'Condensing Type' },
            // { field: 'ecType', header: 'EC Type' },
            // { field: 'typeOfCharge', header: 'Type of Charge' },
            { field: 'loadingName', header: 'Unloading' },
            { field: 'lodgingName', header: 'Lodging & Boarding ' },
            { field: 'price', header: 'Cost' }
          ];
        }

          this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
          this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;
          console.log(this.localStorageValues[this.loginUserDetails]);
          this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
          switch (this.storage.get(this.currentRole)) {
            case "EC_EDIT": {
              this.erecCommEdit = true;
              this.labelName = "Please select the reviewer";
              this.saveBasic.loggedInUserCode = 0;
              this.saveBasic.statusId = 1;
              //if (this._ITOMyWorkListPageService.selectedUR != '') {
              if (this._ITOMyWorkListPageService.selectedUR) {
                this.saveBasic.updateRequestNumber = this._ITOMyWorkListPageService.selectedUR.updateRequestNumber;
              } else {
                this.saveBasic.updateRequestNumber = 0;
              }
              console.log(this.saveBasic.updateRequestNumber)

              for (let r = 0; r < this.usersList.length; r++) {
                for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
                  if (this.usersList[r].rolesCodeVal[s] == "EC_REVIWER") {
                    this.newUsersList.push(this.usersList[r]);
                  }
                }
              }
              console.log(this.newUsersList);
              break;
            }
            case "EC_APPROVER": {
              this.erecCommApprove = true;
              this.saveBasic.loggedInUserCode = 2;
              if (this._ITOMyWorkListPageService.selectedUR) {
                this.saveBasic.updateRequestNumber = this._ITOMyWorkListPageService.selectedUR.updateRequestNumber;
              }
              break;

            }
            case "EC_REVIWER": {
              this.erecCommReview = true;
              this.labelName = "Please select the approver";
              this.saveBasic.loggedInUserCode = 1;

              console.log(this.usersList);
              for (let r = 0; r < this.usersList.length; r++) {
                for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
                  if (this.usersList[r].rolesCodeVal[s] === "EC_APPROVER") {
                    this.newUsersList.push(this.usersList[r]);
                  }
                }
              }
              if (this._ITOMyWorkListPageService.selectedUR) {
                this.saveBasic.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
              }
              console.log(this.newUsersList);
              break;
            }
            default: {
              break;
            }
          }
          this._ITOMyWorkListPageService.selectedUR = '';
          this.hideprogress = false;
      })
      
    })
  }


  ngOnInit() {
    let quoId = 0;
    this._ITOUpdatePriceEcService.getErrectionCommCache(quoId).subscribe(res => {
      // this.hideprogress = true;
      console.log(res);
      this.saveBasic = res.saveBasicDetails;
      this.quotForm = res;
      console.log(this.quotForm)
      for (var i = 0; i < res.dropDownColumnvalues.enCustTypeList.ErectionCustTypes.length; i++) {
        if (res.dropDownColumnvalues.enCustTypeList.ErectionCustTypes[i].categoryDetCode === "DM") {
          this.custType = res.dropDownColumnvalues.enCustTypeList.ErectionCustTypes[i].categoryDetDesc;
          this.custId = res.dropDownColumnvalues.enCustTypeList.ErectionCustTypes[i].categoryDetId;
        }
      }

      for (var i = 0; i < res.dropDownColumnvalues.enCCharges.ErectionCommCharges.length; i++) {
        if (res.dropDownColumnvalues.enCCharges.ErectionCommCharges[i].categoryDetCode === "EC") {
          this.typOfChrgeEC = res.dropDownColumnvalues.enCCharges.ErectionCommCharges[i].categoryDetDesc;
          this.typOfChrgeIdEC = res.dropDownColumnvalues.enCCharges.ErectionCommCharges[i].categoryDetId;
        } else if (res.dropDownColumnvalues.enCCharges.ErectionCommCharges[i].categoryDetCode === "SUPR") {
          this.typOfChrgeSup = res.dropDownColumnvalues.enCCharges.ErectionCommCharges[i].categoryDetDesc;
          this.typOfChrgeIdSup = res.dropDownColumnvalues.enCCharges.ErectionCommCharges[i].categoryDetId;
        }
      }

      for (var i = 0; i < res.dropDownColumnvalues.enCLoadingDomestic.EnCLoadingListDomestic.length; i++) {
        if (res.dropDownColumnvalues.enCLoadingDomestic.EnCLoadingListDomestic[i].categoryDetCode === "TUR") {
          this.loadingTurn = res.dropDownColumnvalues.enCLoadingDomestic.EnCLoadingListDomestic[i].categoryDetDesc;
          this.loadingIdTurn = res.dropDownColumnvalues.enCLoadingDomestic.EnCLoadingListDomestic[i].categoryDetId;
        } else if (res.dropDownColumnvalues.enCLoadingDomestic.EnCLoadingListDomestic[i].categoryDetCode === "WTL") {
          this.loadingUnload = res.dropDownColumnvalues.enCLoadingDomestic.EnCLoadingListDomestic[i].categoryDetDesc;
          this.loadingIdUnload = res.dropDownColumnvalues.enCLoadingDomestic.EnCLoadingListDomestic[i].categoryDetId;
        }
      }

      for (var i = 0; i < res.dropDownColumnvalues.enCLodging.EnCLodgingList.length; i++) {
        if (res.dropDownColumnvalues.enCLodging.EnCLodgingList[i].categoryDetCode === "CS") {
          this.lodgingCust = res.dropDownColumnvalues.enCLodging.EnCLodgingList[i].categoryDetDesc;
          this.lodgingIdCust = res.dropDownColumnvalues.enCLodging.EnCLodgingList[i].categoryDetId;
        } else if (res.dropDownColumnvalues.enCLodging.EnCLodgingList[i].categoryDetCode === "TS") {
          this.lodgingTTL = res.dropDownColumnvalues.enCLodging.EnCLodgingList[i].categoryDetDesc;
          this.lodgingIdTTL = res.dropDownColumnvalues.enCLodging.EnCLodgingList[i].categoryDetId;
        }
      }
      //this.hideprogress = false;
    });
  }

  ngAfterViewChecked() {
    if (this.storage.get(this.currentRole) === "EC_EDIT") {
      for (let s = 0; s < this.ecPriceList.length; s++) {
        for (let q = 0; q < this.updatedPriceList.length; q++) {
          if (this.ecPriceList[s].ecId == this.updatedPriceList[q].ecId) {
            let butn = document.getElementById(this.ecPriceList[s].ecId).style.backgroundColor = "#0275d8";
          }
        }
      }
    }
  }
//Method called on selection of type of blade
  turbineDesnSel(val) {
    console.log(val);
    this.bladeType = val;
  }
//Method called on selection of trubine type
  TOTSel(value) {
    console.log(value);
    console.log(this.newFrameWithPowersList)
    this.frameList = [];
    for (let j = 0; j < this.newFrameWithPowersList.length; j++) {
      if (this.newFrameWithPowersList[j].turbineCode == value && (this.newFrameWithPowersList[j].turbineDesignCd == this.bladeType)) {
        this.frameList.push(this.newFrameWithPowersList[j]);
      }
    }
    if (this.frameList.length == 0) {
      this.successMsg = [];
      this.displayForm = false;
      this.message = true;
      this.successMsg.push("No New Frames Found.");
    } else if (this.frameList.length > 0) {
      this.displayForm = true;
    }
  }
//Method on selection of frame
  FrameSel(frameId, form, formm: NgForm) {
    console.log(frameId);
    console.log(form, formm)
    this.form = formm;
    this.newFrame = [];

    for (let i = 0; i < this.newFrameWithPowersList.length; i++) {
      if (this.newFrameWithPowersList[i].frameId == frameId) {
        this.newFrame.push(this.newFrameWithPowersList[i]);
      }
    }
    console.log(this.newFrame);
    this.columns = [
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[0].categoryDetDesc, condensingTypeId: this.typesOfCondensor[0].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeEC, loadingName: this.loadingTurn, lodgingName: this.lodgingCust, price: '0', id: '1', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdEC, loadingId: this.loadingIdTurn, lodgingId: this.lodgingIdCust, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[0].categoryDetDesc, condensingTypeId: this.typesOfCondensor[0].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeEC, loadingName: this.loadingTurn, lodgingName: this.lodgingTTL, price: '0', id: '2', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdEC, loadingId: this.loadingIdTurn, lodgingId: this.lodgingIdTTL, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[0].categoryDetDesc, condensingTypeId: this.typesOfCondensor[0].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeEC, loadingName: this.loadingUnload, lodgingName: this.lodgingCust, price: '0', id: '3', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdEC, loadingId: this.loadingIdUnload, lodgingId: this.lodgingIdCust, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[0].categoryDetDesc, condensingTypeId: this.typesOfCondensor[0].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeEC, loadingName: this.loadingUnload, lodgingName: this.lodgingTTL, price: '0', id: '4', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdEC, loadingId: this.loadingIdUnload, lodgingId: this.lodgingIdTTL, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[0].categoryDetDesc, condensingTypeId: this.typesOfCondensor[0].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeSup, loadingName: '', lodgingName: this.lodgingCust, price: '0', id: '5', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdSup, loadingId: '', lodgingId: this.lodgingIdCust, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[0].categoryDetDesc, condensingTypeId: this.typesOfCondensor[0].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeSup, loadingName: '', lodgingName: this.lodgingTTL, price: '0', id: '6', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdSup, loadingId: '', lodgingId: this.lodgingIdTTL, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[1].categoryDetDesc, condensingTypeId: this.typesOfCondensor[1].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeEC, loadingName: this.loadingTurn, lodgingName: this.lodgingCust, price: '0', id: '1', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdEC, loadingId: this.loadingIdTurn, lodgingId: this.lodgingIdCust, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[1].categoryDetDesc, condensingTypeId: this.typesOfCondensor[1].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeEC, loadingName: this.loadingTurn, lodgingName: this.lodgingTTL, price: '0', id: '2', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdEC, loadingId: this.loadingIdTurn, lodgingId: this.lodgingIdTTL, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[1].categoryDetDesc, condensingTypeId: this.typesOfCondensor[1].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeEC, loadingName: this.loadingUnload, lodgingName: this.lodgingCust, price: '0', id: '3', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdEC, loadingId: this.loadingIdUnload, lodgingId: this.lodgingIdCust, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[1].categoryDetDesc, condensingTypeId: this.typesOfCondensor[1].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeEC, loadingName: this.loadingUnload, lodgingName: this.lodgingTTL, price: '0', id: '4', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdEC, loadingId: this.loadingIdUnload, lodgingId: this.lodgingIdTTL, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[1].categoryDetDesc, condensingTypeId: this.typesOfCondensor[1].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeSup, loadingName: '', lodgingName: this.lodgingCust, price: '0', id: '5', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdSup, loadingId: '', lodgingId: this.lodgingIdCust, ecId: 0 },
      { frameDesc: this.newFrame[0].frameDesc, maxPower: this.newFrame[0].maxPower, turbineDesign: this.newFrame[0].turbdesignName, turbineType: this.newFrame[0].turbType, condensingType: this.typesOfCondensor[1].categoryDetDesc, condensingTypeId: this.typesOfCondensor[1].categoryDetId, ecType: this.custType, typeOfCharge: this.typOfChrgeSup, loadingName: '', lodgingName: this.lodgingTTL, price: '0', id: '6', framePowerId: this.newFrame[0].framePowerId, ecTypeId: this.custId, typeOfChargeId: this.typOfChrgeIdSup, loadingId: '', lodgingId: this.lodgingIdTTL, ecId: 0 }

    ];
  }
//Method  to display table data
  onRowSelect(event) {
    // console.log(event.data);
    this.selectedData = event.data;
    this.displayDialog = true;
    console.log(this.selectedData.condensingTypeId)
    if (this.selectedData.condensingTypeId == 0) {
      this.dispCondTyp = false;
    }
    else {
      this.dispCondTyp = true;
    }

    if (this.selectedData.loadingId == 0)
      this.dispUnloading = false;
    else
      this.dispUnloading = true;
    // console.log(this.dispCondTyp + "- " + this.dispUnloading)
  }
// Method to save
  save() {
    console.log(this.selectedData);

    if (this.updatedPriceList.length != 0) {
      for (let s = 0; s < this.updatedPriceList.length; s++) {
        if (this.updatedPriceList[s].ecId === this.selectedData.ecId) {
          this.updatedPriceList[s] = this.selectedData;
          this.contains = true;
          let butn = document.getElementById(this.updatedPriceList[s].ecId).style.backgroundColor = "#0275d8";
          break;
        }
      }
      if (!this.contains) {
        this.updatedPriceList.push(this.selectedData);
        for (let s = 0; s < this.updatedPriceList.length; s++) {
          if (this.updatedPriceList[s].ecId === this.selectedData.ecId) {
            let butn = document.getElementById(this.updatedPriceList[s].ecId).style.backgroundColor = "#0275d8";
          }
        }
      }
      else {
        this.contains = false;
      }
    }
    else {
      this.updatedPriceList.push(this.selectedData);

      for (let s = 0; s < this.updatedPriceList.length; s++) {
        if (this.updatedPriceList[s].ecId === this.selectedData.ecId) {
          let butn = document.getElementById(this.updatedPriceList[s].ecId).style.backgroundColor = "#0275d8";
        }
      }
    }

    //let butn = document.getElementById(this.selectedData.ecId).style.backgroundColor = "#0275d8";
    this.displayDialog = false;
    this.displayAddDialog = false;
    console.log(this.updatedPriceList);
  }
//Method to save new data
  saveNewData() {
    console.log(this.columns);
    this.updatedPriceList = this.columns;
    console.log(this.updatedPriceList);
  }
//Method save as draft
  saveAsDraft(ecForm, form: NgForm) {
    this.successMsg = [];
    console.log(ecForm);
    this.ecform = form;

    console.log(ecForm);
    console.log(this.updatedPriceList);
    this.isDataSaved = true;
    this.quotForm.errectionCommList = this.updatedPriceList;

    for (let q = 0; q < this.updatedPriceList.length; q++) {
      if (this.updatedPriceList[q].ecId == 0) {
        this.saveBasic.updateCode = "UP_ECN";
      } else {
        this.saveBasic.updateCode = "UP_EC";
      }
    }

    this.message = true;

    this.saveBasic.modifiedById = this.modifiedBy;
    this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
    this.saveBasic.remarks = ecForm.remarks;
    this.saveBasic.assignedTo = ecForm.assignee;

    if (ecForm.status == "Accept") {
      this.saveBasic.statusId = 1;
    }
    else if (ecForm.status == "Reject") {
      this.saveBasic.statusId = 0;
    }
    this.quotForm.saveBasicDetails = this.saveBasic;
    console.log(this.quotForm)

    this._ITOUpdatePriceEcService.createECPriceUpdateRequest(this.quotForm).subscribe(resp => {
      console.log(resp);
    // this.saveBasic.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
    this.saveBasic.updateRequestNumber = 0;
      this.savedReqQuotForm = resp;

      this.message = true;
      this.successMsg.push(resp.successMsg);
    });
  }
//Method to store or update values into DB
  updateECPrice(ecForm, form: NgForm) {
    this.successMsg = [];
    console.log(ecForm);
    this.ecform = form;
    console.log(this.updatedPriceList);
    this.quotForm.errectionCommList = this.updatedPriceList;

    this.message = true;
    //this.saveBasic.updateCode = "UP_EC";
    for (let q = 0; q < this.updatedPriceList.length; q++) {
      if (this.updatedPriceList[q].ecId == 0) {
        this.saveBasic.updateCode = "UP_ECN";
      } else {
        this.saveBasic.updateCode = "UP_EC";
      }
    }
    this.saveBasic.modifiedById = this.modifiedBy;
    this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
    this.saveBasic.remarks = ecForm.remarks;
    this.saveBasic.assignedTo = ecForm.assignee;

    if (ecForm.status == "Accept") {
      this.saveBasic.statusId = 1;
    }
    else if (ecForm.status == "Reject") {
      this.saveBasic.statusId = 0;
    }
    this.quotForm.saveBasicDetails = this.saveBasic;
    console.log(this.quotForm)
    // if (this.isDataSaved) {
    //   this.updateStatusAndSave(this.savedReqQuotForm);
    // } else {
      this._ITOUpdatePriceEcService.createECPriceUpdateRequest(this.quotForm).subscribe(resp => {
        console.log(resp);

        this.successMsg.push(resp.successMsg);
        this.message = true;
        this.updateStatusAndSave(resp);
      });
    //}

  }
//Method to update status
  updateStatusAndSave(resp) {

    if (this.storage.get(this.currentRole) === "EC_APPROVER") {
      resp.saveBasicDetails.assignedTo = this.modifiedBy;
      this._ITOUpdatePriceEcService.updateStatus(resp).subscribe(respon => {
        console.log(respon);
        this._ITOUpdatePriceEcService.saveECUpdatedPrice(respon).subscribe(respo => {
          console.log(respo);
          this.message = true;
          this.successMsg.push(respo.successMsg);

        });

      });
    } else {
      this._ITOUpdatePriceEcService.updateStatus(resp).subscribe(respon => {
        console.log(respon);

        this.message = true;
        this.successMsg.push(respon.successMsg);
      });
    }

    this.isDataSaved = false;
  }
//Method to handel tabview
  handleChange(event) {
    var index = event.index;
    console.log(index)
    console.log(this.saveBasic)
    this.updatedPriceList = [];
    this.enableSave = false;
    if (index == 0 && (this.ecPriceList.length > 0)) {
      this.displayForm = true;
    } else if (index == 1) {
      this.displayForm = false;
    }
    this.form.reset();
  }
//Method called on selection of input change
  onInputChange(event) {
    console.log(event)
    this.enableSave = true;
    console.log(this.enableSave)
  }
//Method called on selection of status
  statusSel(val){
    if(val=="Accept")
    this.enableStatus=true;
    else if(val=="Reject")
    this.enableStatus=false;
  }
//Based on search criteria provided by the user, list of Packaging details will display.
  searchSet(dtd, value) {
    if (dtd.filteredValue != null) {
      for (let d = 0; d < dtd.filteredValue.length; d++) {
        for (let f = 0; f < this.updatedPriceList.length; f++) {
          if (dtd.filteredValue[d].ecId == this.updatedPriceList[f].ecId) {
            let butn = document.getElementById(dtd.filteredValue[d].ecId).style.backgroundColor = "#0275d8";
          }
        }
      }
    }
    else {
      for (let d = 0; d < this.ecPriceList.length; d++) {
        for (let f = 0; f < this.updatedPriceList.length; f++) {
          if (this.ecPriceList[d].ecId == this.updatedPriceList[f].ecId) {
            let butn = document.getElementById(this.ecPriceList[d].ecId).style.backgroundColor = "#0275d8";
          }
        }
      }
    }
  }
//Close the dialog box once the success message displayed.
  closeMessage() {
    this.message = false;
    this.successMsg = [];
    
    for (let s = 0; s < this.ecPriceList.length; s++) {
      for (let q = 0; q < this.updatedPriceList.length; q++) {
        if (this.ecPriceList[s].ecId == this.updatedPriceList[q].ecId) {
          let butn = document.getElementById(this.ecPriceList[s].ecId).style.backgroundColor = "";
        }
      }
    }
    this.updatedPriceList = [];
    this.selectedData = '';
    this.ecform.reset();
    if (!this.isDataSaved) {
      this.navigateToWorkFlow();
    }
    this.navigateToWorkFlow();
  }

  displayMessage(){
    this.dispMessage = false;
    this.navigateToWorkFlow();
  }
  saveBulk()
  {
    this._ItoUpdateTransportationService.getAdminForm().subscribe(res => {
      console.log(res);
      this.adminForm = res;
      this.adminForm.percent=this.percentage;
      this.adminForm.modifiedBy=this.modifiedBy;
    
    this._ITOUpdatePriceEcService.getAdminPercentEc(this.adminForm).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.displayDialogBulk=false;
      this.dispMessage = true;
    });
  });
  }
  bulkUpd()
  {
    this.percentage=0;
    this.displayDialogBulk=true;

  }
//Method to navigate my workflow page
  navigateToWorkFlow() {
    this.router.navigate(['MyWorkFlow']);
  }

}
