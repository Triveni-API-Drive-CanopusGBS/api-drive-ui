import { Component, OnInit } from '@angular/core';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { delay } from 'rxjs/operator/delay';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ItoUpdateCostDBOElecPrice } from '../update-cost-dboelect-price/update-cost-dboelect-price.service';
import { DBOElecPrice } from '../update-cost-dboelect-price/update-cost-dboelect-price';
import { DBOMechPrice } from '../update-cost-dbomech-price/update-cost-dbomech-price';
import {ItoUpdDboEleSpladdonService} from './ito-upd-dbo-ele-spladdon.service';

@Component({
  selector: 'app-ito-upd-dbo-ele-spladdon',
  templateUrl: './ito-upd-dbo-ele-spladdon.component.html',
  styleUrls: ['./ito-upd-dbo-ele-spladdon.component.css']
})
export class ItoUpdDboEleSpladdonComponent implements OnInit {

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
  newlyAddedData: Array<any> = [];   // dbo electrical columns data for add screen
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
  selectedRange: any;
  tempRes: any;
  tempRes1: any;
  columns: any;
  previousPrice: any;
  custType: any = '';  //Domestic or Export
  itemId: any = '';
  itemIdNew: any = '';  // variable to store itemId for add screen

  dboEleEdit: boolean = false;  // variable to check whether logged in user role is engineer
  dboEleApp: boolean = false;   // variable to check whether logged in user role is Approver
  dboEleRev: boolean = false;   // variable to check whether logged in user role is Reviewer
  displayMessage: boolean = false;  //to display success message
  displayDialog: boolean = false;   //to display success message
  tableDiv: boolean = false;        //to disabele comments section
  exist: boolean = false;           // to check whether edit screen data is present while navigating from Myworkflow screen
  dispDropdown: boolean = true;     // to check whether dropdowns should be present while navigating from Myworkflow screen
  addNew: boolean = false;          // to check whether Add screen data is present while navigating from Myworkflow screen     
  assignDisp: boolean;              // to check whether assign to field should be enabled/Disabled
  contains: boolean = false;
  isCostEx: boolean = true;

  currentRoleId: string = 'selRoleId';      // local storage key to get loggedin user role id
  currentRole: string = 'selRole';          // local storage key to get loggedin user role Code
  user: string = 'userDetail';              // local storage key to get loggedin UserDetails
  loginUserDetails: string = "userDetail";  // local storage key to get loggedin UserDetails
  panelCode: string;                        //LT or HT
  panelCdNew: string = '';                   //LT or HT for add screen
  labelName: string = '';
  custCode: string = '';                    //DM or EX
  itemName: string = '';
  itemNameNew: string = '';
  tabIndex: number = 0;

  constructor(private _ItoUpdateCostDBOElecPrice: ItoUpdateCostDBOElecPrice, private _ITOLoginService: ITOLoginService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOMyWorkListPageService: ITOMyWorkListPageService,private _ItoUpdDboEleSpladdonService:ItoUpdDboEleSpladdonService,
    private router: Router, private route: ActivatedRoute) {


    this.assignee = this.data[this.user] = this.storage.get(this.user);
    this.userId = this.data[this.user].userId;  // get logged in userId

    // fetch user details list to get reviewer and approver list 
    this._ItoUpdateCostDBOElecPrice.getFrameAndUserData().subscribe(res => {
      console.log(res);
      this.tempRes1 = res;  // assign the response to local variable for further use
      this.saveBasic = res.saveBasicDetails;  // assign the savebasicdetails bean to local variable for further use
      this.usersList = res.userDetailsList;   // assign userlist to local variable
      this.finalRangeList = [];
      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;   // assign stored rol list of logged in user to local list
      console.log(this.userRoles);
      console.log(this._ITOMyWorkListPageService.selectedUR);   // this will have the selected updatecode
      if (this._ITOMyWorkListPageService.selectedUR) {
        console.log(this._ITOMyWorkListPageService.responseTemp);
        this.dispDropdown = false;
        this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
        this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
        console.log(this.reponseTemp);
        this.DBOData = [];

        if (this.selectedUR.updateCode == "UPD_DBO_ELE_SPL_ADDON_PRICE_NEW") {
          this.exist = true;
          this.tabIndex = 1;
          this.addNew = false;
        } else if (this.selectedUR.updateCode == "UPD_DBO_ELE_SPL_ADDON_PRICE") {
          this.addNew = true;
          this.exist = false;
          this.tabIndex = 0
        }

        // if current role is enginner this if will be exceuted
        if (this.storage.get(this.currentRole) == "DBO_ELE_EDIT") {
          console.log("Entered if");
          this.panelCode = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].panelCode;
          this.itemId = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].itemId;
          this.itemName = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].itemName;
          this.custCode = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].custCode;  // change this after adding in db
          if (this.custCode == "DM") {
            this.custType = "Domestic";
          } else if (this.custCode == "EX") {
            this.custType = "Export";
          }

          // forming list of data otherthan selected ones
          for (let i = 0; i < this._ITOMyWorkListPageService.responseTemp.unsavedDboEleSplAddOnList.length; i++) {
            for (let p = 0; p < this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList.length; p++) {
              if (this._ITOMyWorkListPageService.responseTemp.unsavedDboEleSplAddOnList[i].itemId == this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[p].itemId) {
                this.DBOData.push(this._ITOMyWorkListPageService.responseTemp.unsavedDboEleSplAddOnList[i]);
              }
            }
          }
          // get selected data from savedDboEleList
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList;
          // this.DBOData = this._ITOMyWorkListPageService.responseTemp.unsavedDboEleList;

          // copy same request number for further process
          this.saveBasic.updateRequestNumber = this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
          console.log(this.finalRangeList);
          console.log(this.DBOData);
          if (this.DBOData.length > 0) {
            this.tableDiv = true;
          }
        }
        // if current role is Reviewer/Approver this else if will be exceuted
        else if (this.storage.get(this.currentRole) != "DBO_ELE_EDIT") {
          console.log("Entered else if");
          this.panelCode = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].panelCode;
          this.panelCdNew = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].panelCode;
          this.itemId = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].itemId;
          this.itemName = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].itemName;
          this.itemNameNew = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].itemName;
          this.custCode = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList[0].custCode;  // change this after adding in db
          if (this.custCode == "DM") {
            this.custType = "Domestic";
          } else if (this.custCode == "EX") {
            this.custType = "Export";
          }

          //for reviewer and Approver both display data and selected data will be same
          this.DBOData = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList;
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedDboEleSplAddOnList;
          if (this.DBOData.length > 0) {
            this.tableDiv = true;
          }

          console.log(this.DBOData);
          console.log(this.finalRangeList);
        }

        for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
          this.prevRemarks.push(this.reponseTemp.commentList[r]);
        }
        //setting columns for grid when prices are updated
        this.cols = [
          { field: 'itemName', header: 'Item Name' },
         
          { field: 'colNm', header: 'Column Name' },
          { field: 'prevPrice', header: 'Previous Price' },
          { field: 'price', header: 'Price' }
        ];
        this._ITOMyWorkListPageService.selectedUR = '';
        console.log(this.prevRemarks);
      } else {
        //setting columns for grid when prices are not updated
        this.cols = [
          { field: 'itemName', header: 'Item Name' },
         
          { field: 'colNm', header: 'Column Name' },
          { field: 'price', header: 'Price' }
        ];
        if (this.storage.get(this.currentRole) == "DBO_ELE_EDIT") {
          this.saveBasic.updateRequestNumber = 0;
        }
      }

      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;

      console.log(this.localStorageValues[this.loginUserDetails]);
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
      console.log(this.storage.get(this.currentRole));

      switch (this.storage.get(this.currentRole)) {
        case "DBO_ELE_EDIT": {
          this.dboEleEdit = true;
          this.labelName = "Please Select the Reviewer";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "DBO_ELE_REVIWER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.saveBasic.loggedInUserCode = 0;
                this.saveBasic.statusId = 1;
              }
            }
          }
          break;
        }
        case "DBO_ELE_APPROVER": {
          console.log("Approverrr");
          this.dboEleApp = true;
          this.saveBasic.loggedInUserCode = 2;
          this.saveBasic.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
          break;

        }
        case "DBO_ELE_REVIWER": {
          this.dboEleRev = true;
          this.labelName = "Please Select the Approver";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "DBO_ELE_APPROVER") {
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
    });

  }

  //when item id is selected this function willbe called
  getItemDetailsVal(val) {
    this.itemId = val;
    this.tempRes.itemId = val;
    // this.pa
  }

  //when customer type is selected this function willbe called
  getCustTypeVal(val) {
    this.custCode = val;

  }

  exCost(excost) {
    console.log(excost)
    if (excost != null || excost != '') {
      this.isCostEx = false;
    } else if (excost != 0) {
      this.isCostEx = true;
    } else {
      this.isCostEx = false;
    }

  }

  // this function is called to get grid data along with price
  getTableData(val) {
    this.tableDiv = false;
    console.log(val);
    this.tempRes.itemId = val.itemDetails;
    this.itemId = val.itemDetails;
    this.tempRes.custCode = val.custType;
    this.custCode = val.custType;
    this.tempRes.panelType = this.panelCode;
    this.tempRes.tableName = "SPLECIAL_ADDON";
    console.log(this.tempRes);
    //getting grid data
    // inputs to this service methods are itemId,PanelCode,custCode
    this._ItoUpdateCostDBOElecPrice.getDBOEleUpdatePriceData(this.tempRes).subscribe(resp => {
      console.log(resp);
      this.DBOData = resp.dBOElectricalData;
      if (this.DBOData.length > 0) {
        this.tableDiv = true;
      }

    })
  }

  //getting components details for edit screen
  getItemDetails(val) {
    console.log(val);
    this.panelCode = val; //LT or HT
    this._ItoUpdateCostDBOElecPrice.getDBOElectricalData(val).subscribe(res => {
      console.log(res);
      this.tempRes = res;
      
      for(let x=0;x<res.dBOElectricalList.length;x++){
        if(res.dBOElectricalList[x].splAddonFlag){
          this.dBOElectricalListArray.push(res.dBOElectricalList[x]);
        }
      }
    
      
    })
  }

  //getting components details for add screen
  getItemDet(panelCd) {
    console.log(panelCd);
    this.panelCdNew = panelCd; //LT or HT
    this._ItoUpdateCostDBOElecPrice.getDBOElectricalData(panelCd).subscribe(itemRes => {
      console.log(itemRes);
      this.tempRes = itemRes;
      this.dBOElectricalListArrayNew = itemRes.dBOElectricalList;
    })
  }


  // Edit row functionality
  onRowSelect(event) {
    console.log(event.data);
   
      this.displayDialog = true;
      this.selectedRange = event.data;
  }

  ngOnInit() {
    // generating these lists in UI itself as data is not there in Database 
    this.volGenerationArray = [
      { field: 'LT', header: 'LT' },
      { field: 'HT', header: 'HT' }
    ];

    this.custTypeArray = [
      { field: 'DM', header: 'Domestic' },
      { field: 'EX', header: 'Export' }
    ];
  }

  //this s used to highlight the updated price data when engineer returns from myworkflow screen
  ngAfterViewChecked() {
    console.log(this.tabIndex)
    if ((this.storage.get(this.currentRole) === "DBO_ELE_EDIT") && (this.tabIndex == 0)) {
      if (this.DBOData) {
        if (this.DBOData.length != 0) {
          for (let d = 0; d < this.DBOData.length; d++) {
            for (let f = 0; f < this.finalRangeList.length; f++) {
              if (this.DBOData[d].priceId == this.finalRangeList[f].priceId) {
                document.getElementById(this.DBOData[d].priceId).style.backgroundColor = "#0275d8";
                this.DBOData[d] = this.finalRangeList[f];
              }
            }
          }
        }
      }
    }
  }

  //get the assgned user details
  assignedUser(assigne) {
    console.log(assigne);
    console.log(this.tempRes1.userDetailsList);
    this.saveBasic.assignedTo = assigne;
  }

  // this is called when user updates the price and saves in the dialog
  save() {
    console.log(this.selectedRange);

    if (this.finalRangeList.length != 0) {
      for (let s = 0; s < this.finalRangeList.length; s++) {
        if (this.finalRangeList[s].priceId === this.selectedRange.priceId) {
          this.finalRangeList[s] = this.selectedRange;
          this.contains = true;
          let butn = document.getElementById(this.finalRangeList[s].priceId).style.backgroundColor = "#0275d8";
          break;
        }
      }
      if (!this.contains) {
        this.finalRangeList.push(this.selectedRange);
        for (let s = 0; s < this.finalRangeList.length; s++) {
          if (this.finalRangeList[s].priceId === this.selectedRange.priceId) {
            let butn = document.getElementById(this.finalRangeList[s].priceId).style.backgroundColor = "#0275d8";
          }
        }
      }
      else {
        this.contains = false;
      }
    }
    else {
      this.finalRangeList.push(this.selectedRange);
      for (let s = 0; s < this.finalRangeList.length; s++) {
        if (this.finalRangeList[s].priceId === this.selectedRange.priceId) {
          let butn = document.getElementById(this.finalRangeList[s].priceId).style.backgroundColor = "#0275d8";
        }
      }
    }

    //let butn = document.getElementById(this.selectedData.ecId).style.backgroundColor = "#0275d8";
    this.displayDialog = false;
    console.log(this.finalRangeList);
  }

  // closing the dialog after updating the price
  closeMessage() {
    this.displayMessage = false;
    this.finalRangeList = [];
    this.remarkss = '';
    this.successMsg = [];
    //this.acceptedOnly = true;
    this.navigateToMyWorkflow();
    for (let d = 0; d < this.DBOData.length; d++) {
      for (let f = 0; f < this.finalRangeList.length; f++) {
        if (this.DBOData[d].priceId == this.finalRangeList[f].priceId) {
          document.getElementById(this.DBOData[d].priceId).style.backgroundColor = "#0275d8";
          console.log(this.DBOData[d]);
        }
      }
    }

  }

  // navigating to my workflow screen
  navigateToMyWorkflow() {
    this.router.navigate(['MyWorkFlow']);
  }


  SaveAsDraft() {
    this.successMsg = [];
    this.saveBasic.remarks = this.remarkss;
    this.assignDisp = true;
    console.log(this.remarkss);
    console.log(this.finalRangeList);
    for (let q = 0; q < this.finalRangeList.length; q++) {
      if (this.finalRangeList[q].priceId > 0) {
        this.saveBasic.updateCode = "UPD_DBO_ELE_SPL_ADDON_PRICE";
      } else {
        this.saveBasic.updateCode = "UPD_DBO_ELE_SPL_ADDON_PRICE_NEW";
      }
    }
    this.saveBasic.custCode = this.custCode;
    this.saveBasic.modifiedById = this.modifiedBy;
    this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);

    this.tempRes1.saveBasicDetails = this.saveBasic;
    this.tempRes1.quotDboElectricalList = this.finalRangeList;
    console.log(this.tempRes1);

    //while saving as draft onlt request is created, workflow will not be initiated
    this._ItoUpdDboEleSpladdonService.createDboEleUpdateRequestPriceSplAddOn(this.tempRes1).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.saveBasic.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.tempRes1 = resp;

    });
    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.DBOData.length; vv++) {
      let butn = document.getElementById(this.DBOData[vv].unitId).style.backgroundColor = "";
    }
  }

  // function will be called when update button is clicked
  updateDboEle(form) {
    console.log(form);
    this.successMsg = [];
    this.saveBasic.remarks = form.coments;
    this.assignDisp = true;
    console.log(this.finalRangeList);

    // if price id=0; it means that they are adding new price, so request code is UPD_DBO_ELE_PRICE_NEW
    for (let q = 0; q < this.finalRangeList.length; q++) {
      if (this.finalRangeList[q].priceId > 0) {
        this.saveBasic.updateCode = "UPD_DBO_ELE_SPL_ADDON_PRICE";
      } else {
        this.saveBasic.updateCode = "UPD_DBO_ELE_SPL_ADDON_PRICE_NEW";
      }
    }
    this.saveBasic.custCode = this.custCode;
    this.saveBasic.modifiedById = this.modifiedBy;
    this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
    if (form.status == "Accept") {
      this.saveBasic.statusId = 1;
    }
    else if (form.status == "Reject") {
      this.saveBasic.statusId = 0;
    }
    this.tempRes1.saveBasicDetails = this.saveBasic;
    this.tempRes1.quotDboElectricalList = this.finalRangeList;
    console.log(this.tempRes1);

    // request will be created first and then workflow will be initiated
    this._ItoUpdDboEleSpladdonService.createDboEleUpdateRequestPriceSplAddOn(this.tempRes1).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "DBO_ELE_APPROVER") {
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateCostDBOElecPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
          this._ItoUpdateCostDBOElecPrice.saveUpdatedNoOfVehicles(respon).subscribe(respo => {
            console.log(respo);
            this.successMsg.push(respo.successMsg);
          })
        });

      } else {
        this._ItoUpdateCostDBOElecPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }

    });

    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.DBOData.length; vv++) {
      let butn = document.getElementById(this.DBOData[vv].unitId).style.backgroundColor = "";
    }
    console.log(this.successMsg)
  }

  //whenever tab is changed accordingly data has to reset
  handleChange(event) {
    var index = event.index;
    this.tabIndex = index;
    console.log(index)
    console.log(this.saveBasic)
    this.finalRangeList = [];
    this.DBOData = [];
    this.itemIdNew = '';
    this.itemId = '';
    this.custCode = '';
    this.panelCdNew = '';
    this.panelCode = '';
    this.dispDropdown = true;
    if (index == 0) {
      if (this.DBOData.length > 0) {
        this.tableDiv = true;
      }
    } else if (index == 1) {
      this.tableDiv = false;
      this.newlyAddedData = [];
    }
    //this.form.reset();
  }

  //getting data for add screen
  getNewData(newDataForm) {
    console.log(newDataForm);
    this.newlyAddedData = [];
    this.itemIdNew = newDataForm.item;
    this.panelCdNew = newDataForm.panelCd;
    this.custCode = newDataForm.custCd;
    this._ItoUpdateCostDBOElecPrice.getDBOEleUpdateColData(newDataForm.item, newDataForm.panelCd).subscribe(colRes1 => {
      this.newlyAddedData = colRes1.dBOElectricalColList;
      this.itemNameNew = this.newlyAddedData[0].itemName;
      this.questionsBean = colRes1.questionsBean;
      console.log(colRes1);
      this.dispDropdown = false;
    })
  }

  addCost(addCostForm) {
    this.tableDiv = false;
    console.log(addCostForm);
    let isExists = true;
    this.tempRes.itemId = this.itemIdNew;
    this.tempRes.custCode = null;
    this.tempRes.panelType = this.panelCdNew;
    this.tempRes.tableName = "MAIN";
    console.log(this.tempRes);
    //getting grid data
    // inputs to this service methods are itemId,PanelCode,custCode
    this._ItoUpdateCostDBOElecPrice.getDBOEleUpdatePriceData(this.tempRes).subscribe(resp1 => {
      console.log(resp1);

      var eleData = new DBOElecPrice();
      eleData.priceCode = "";
      for (var i = 0; i < this.questionsBean.length; i++) {
        if (eleData.priceCode == "") {
          eleData.priceCode = addCostForm[i];
        } else {
          eleData.priceCode = eleData.priceCode + "|" + addCostForm[i];
        }
      }
      let k = resp1.dBOElectricalData.map((x) => x.priceCode).includes((eleData.priceCode));
      console.log(k);
      if (k) {
        alert("Data Already Exists");
      }
      else {
        for (var i = 0; i < this.newlyAddedData.length; i++) {
          if (this.itemIdNew == this.newlyAddedData[i].itemId) {
            eleData.itemName = this.newlyAddedData[i].itemName;
          }
        }
        eleData.itemId = this.itemIdNew;
        eleData.price = addCostForm.newCostDom;
        eleData.custCode = "DM";
        eleData.custType = "Domestic";
        eleData.panelCode = this.panelCdNew;

        console.log(eleData.priceCode);
        eleData.priceId = 0;

        this.DBOData.push(eleData);
        this.finalRangeList.push(eleData);

        if (addCostForm.newCostEx) {
          var eleDataEx = new DBOMechPrice();
          console.log(eleDataEx)
          for (var i = 0; i < this.newlyAddedData.length; i++) {
            if (this.itemIdNew == this.newlyAddedData[i].itemId) {
              eleDataEx.itemName = this.newlyAddedData[i].itemName;
            }
          }
          eleDataEx.itemId = this.itemIdNew;
          eleDataEx.price = addCostForm.newCostEx;
          eleDataEx.custCode = "EX";
          eleDataEx.custType = "Export";
          eleDataEx.priceCode = "";
          eleDataEx.panelCode = this.panelCdNew;

          for (var i = 0; i < this.questionsBean.length; i++) {
            if (eleDataEx.priceCode == "") {
              eleDataEx.priceCode = addCostForm[i];
            } else {
              eleDataEx.priceCode = eleDataEx.priceCode + "|" + addCostForm[i];
            }
          }
          console.log(eleDataEx.priceCode);
          eleDataEx.priceId = 0;
          this.DBOData.push(eleDataEx);
          this.finalRangeList.push(eleDataEx);
        }

        console.log(this.finalRangeList)
        console.log(this.DBOData)
        if (this.DBOData.length > 0) {
          this.tableDiv = true;
        }
      }
    })
  }



}
