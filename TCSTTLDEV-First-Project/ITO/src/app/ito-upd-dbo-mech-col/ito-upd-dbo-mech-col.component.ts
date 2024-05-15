import { Component, OnInit } from '@angular/core';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { delay } from 'rxjs/operator/delay';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ItoUpdateCostDBOMechPrice } from '../update-cost-dbomech-price/update-cost-dbomech-price.service';
import { DBOMechPrice } from '../update-cost-dbomech-price/update-cost-dbomech-price';

@Component({
  selector: 'app-ito-upd-dbo-mech-col',
  templateUrl: './ito-upd-dbo-mech-col.component.html',
  styleUrls: ['./ito-upd-dbo-mech-col.component.css']
})
export class ItoUpdDboMechColComponent implements OnInit {
  cols: { field: string; header: string; }[];
  custTypeArray: { field: string; header: string; }[];

  turbTypeArray: Array<any> = [];
  finalRangeList: Array<any> = [];  // this list will have updated list of data from the grid
  localStorageValues: Array<any> = [];
  userRoles: Array<any> = [];  // list to store user roles
  newUsersLilst: Array<any> = [];  //users list
  prevRemarks: Array<any> = [];  // list to store previous remrks of engineer,reviewer and approver
  usersList: Array<any> = []; //users list
  successMsg: Array<any> = [];  // local variable to display success message
  data: Array<any> = [];
  colName: Array<any> = [];
  dBOMechanicalListArrayNew: Array<any> = [];   // electrical components list for add screen
  dBOMechanicalListArray: Array<any> = [];    // electrical components list for edit screen
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


  constructor(private _ItoUpdateCostDBOMechPrice: ItoUpdateCostDBOMechPrice, private _ITOLoginService: ITOLoginService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOMyWorkListPageService: ITOMyWorkListPageService,
    private router: Router, private route: ActivatedRoute) {

    this.assignee = this.data[this.user] = this.storage.get(this.user);
    this.userId = this.data[this.user].userId;  // get logged in userId

    // fetch user details list to get reviewer and approver list 
    this._ItoUpdateCostDBOMechPrice.getFrameAndUserData().subscribe(res => {
      console.log(res);
      this.tempRes1 = res;  // assign the response to local variable for further use
      this.saveBasic = res.saveBasicDetails;  // assign the savebasicdetails bean to local variable for further use
      this.usersList = res.userDetailsList;   // assign userlist to local variable
      this.finalRangeList = [];
      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;   // assign stored rol list of logged in user to local list
      console.log(this.userRoles);
      console.log(this._ITOMyWorkListPageService.selectedUR);

      if (this._ITOMyWorkListPageService.selectedUR) {  // this will have the selected updatecode
        console.log(this._ITOMyWorkListPageService.responseTemp);
        this.dispDropdown = false;
        this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
        this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
        console.log(this.reponseTemp);
        this.DBOData = [];

        // if current role is enginner this if will be exceuted
        if (this.storage.get(this.currentRole) == "DBO_MECH_EDIT") {
          console.log("Entered if");
          this.panelCode = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList[0].panelCode;
          this.itemId = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList[0].itemId;
          this.itemName = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList[0].itemName;
          this.custCode = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList[0].custCode;  // change this after adding in db
          if (this.custCode == "DM") {
            this.custType = "Domestic";
          } else if (this.custCode == "EX") {
            this.custType = "Export";
          }

          // forming list of data otherthan selected ones
          for (let i = 0; i < this._ITOMyWorkListPageService.responseTemp.unsavedDboMechColList.length; i++) {
            for (let p = 0; p < this._ITOMyWorkListPageService.responseTemp.savedDboMechColList.length; p++) {
              if (this._ITOMyWorkListPageService.responseTemp.unsavedDboMechColList[i].itemId == this._ITOMyWorkListPageService.responseTemp.savedDboMechColList[p].itemId) {
                this.DBOData.push(this._ITOMyWorkListPageService.responseTemp.unsavedDboMechColList[i]);
              }
            }
          }
          // get selected data from savedDboMechColList
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList;
          // this.DBOData = this._ITOMyWorkListPageService.responseTemp.unsavedDboMechColList;

          // copy same request number for further process
          this.saveBasic.updateRequestNumber = this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
          console.log(this.finalRangeList);
          console.log(this.DBOData);
          if (this.DBOData.length > 0) {
            this.tableDiv = true;
          }
        }
        // if current role is Reviewer/Approver this else if will be exceuted
        else if (this.storage.get(this.currentRole) != "DBO_MECH_EDIT") {
          console.log("Entered else if");
          this.panelCode = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList[0].panelCode;
          this.itemId = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList[0].itemId;
          this.itemName = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList[0].itemName;
          this.custCode = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList[0].custCode;  // change this after adding in db
          if (this.custCode == "DM") {
            this.custType = "Domestic";
          } else if (this.custCode == "EX") {
            this.custType = "Export";
          }

          //for reviewer and Approver both display data and selected data will be same
          this.DBOData = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList;
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedDboMechColList;
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
          { field: 'colValCd', header: 'Value' },
          { field: 'prevDirectPrice', header: 'Previous DirectPrice' },
          { field: 'directPrice', header: 'Direct Price' },
          { field: 'prevPercent', header: 'Previous Percentage' },
          { field: 'percentage', header: 'Percentage' }
        ];
        this._ITOMyWorkListPageService.selectedUR = '';
        console.log(this.prevRemarks);
      } else {
        //setting columns for grid when prices are not updated
        this.cols = [
          { field: 'itemName', header: 'Item Name' },
          { field: 'colNm', header: 'Column Name' },
          { field: 'colValCd', header: 'Value' },
          { field: 'directPrice', header: 'Direct Price' },
          { field: 'percentage', header: 'Percentage' }
        ];
        if (this.storage.get(this.currentRole) == "DBO_MECH_EDIT") {
          this.saveBasic.updateRequestNumber = 0;
        }
      }

      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;

      console.log(this.localStorageValues[this.loginUserDetails]);
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
      console.log(this.storage.get(this.currentRole));

      switch (this.storage.get(this.currentRole)) {
        case "DBO_MECH_EDIT": {
          this.dboEleEdit = true;
          this.labelName = "Please Select the Reviewer";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "DBO_MECH_REVIWER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.saveBasic.loggedInUserCode = 0;
                this.saveBasic.statusId = 1;
              }
            }
          }
          break;
        }
        case "DBO_MECH_APPROVER": {
          console.log("Approverrr");
          this.dboEleApp = true;
          this.saveBasic.loggedInUserCode = 2;
          this.saveBasic.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
          break;

        }
        case "DBO_MECH_REVIWER": {
          this.dboEleRev = true;
          this.labelName = "Please Select the Approver";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "DBO_MECH_APPROVER") {
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

  ngOnInit() {
    // generating these lists in UI itself as data is not there in Database 
    this.turbTypeArray = [
      { field: 'BP', header: 'Back Pressure' },
      { field: 'CD', header: 'Condensing' }
    ];

    this.custTypeArray = [
      { field: 'DM', header: 'Domestic' },
      { field: 'EX', header: 'Export' }
    ];
  }

  //this s used to highlight the updated price data when engineer returns from myworkflow screen
  ngAfterViewChecked() {
    if (this.storage.get(this.currentRole) === "DBO_MECH_EDIT") {
      if (this.DBOData) {
        if (this.DBOData.length != 0) {
          for (let d = 0; d < this.DBOData.length; d++) {
            for (let f = 0; f < this.finalRangeList.length; f++) {
              //  if (this.finalRangeList[f].colValId) {
              if (this.DBOData[d].colValId == this.finalRangeList[f].colValId) {
                document.getElementById(this.DBOData[d].colValId).style.backgroundColor = "#0275d8";
                this.DBOData[d] = this.finalRangeList[f];
              }
              // }
            }
          }
        }
      }
    }
  }

  //getting components details for edit screen
  getItemDetails(val) {
    this.panelCode = val; // Turbine type- BP/CD
    this.dBOMechanicalListArray = [];
    console.log(val);
    this._ItoUpdateCostDBOMechPrice.getDBOMechanicalItems(val).subscribe(res => {
      console.log(res);
      this.tempRes = res;
      this.dBOMechanicalListArray = res.dBOMechanicalListExcel;
    })
  }

  enabelGetDataBtn(colNm) {
    console.log(colNm)
    this.disableBtn = false;
    this.columnName = colNm;
    for (let q = 0; q < this.colData.length; q++) {
      if (this.colData[q].colNm == colNm) {
        this.colId = this.colData[q].colId;
      }
    }
  }

  getColData(val) {
    this.colNames = [];
    this.tableDiv = false;
    console.log(val);
    this.tempRes.itemId = val.itemDetails;
    this.itemId = val.itemDetails;
    this.tempRes.custCode = val.custType;
    this.custCode = val.custType;
    this.tempRes.panelType = val.voltageGeneration;
    this.panelCode = val.voltageGeneration;
    console.log(this.tempRes);
    //getting grid data
    // inputs to this service methods are itemId,PanelCode,custCode
    this._ItoUpdateCostDBOMechPrice.getDBOMechUpdColumsData(this.tempRes).subscribe(resp => {
      console.log(resp);
      this.colData = resp.dBOMechanicalList;
      this.colNames = Array.from(new Set(resp.dBOMechanicalList.map((x) => {
        return x.colNm;
      })));
      console.log(this.colNames)
    })
  }

  getTableData(form) {
    this.DBOData = [];
    console.log(form)
    for (let n = 0; n < this.colData.length; n++) {
      if (this.colData[n].colNm == form.colName) {
        this.DBOData.push(this.colData[n]);
      }
    }

    if (this.DBOData.length > 0) {
      this.tableDiv = true;
      this.itemName = this.DBOData[0].itemName;
    }
  }

  // Edit row functionality
  onRowSelect(event) {
    console.log(event.data);

    this.displayDialog = true;
    this.selectedData = event.data;
  }

  // this is called when user updates the price and saves in the dialog
  save() {
    console.log(this.selectedData);
    if (this.newData) {
      this.finalRangeList.push(this.selectedData);
      this.DBOData.push(this.selectedData);
      this.newData = false;
    } else {
      if (this.finalRangeList.length != 0) {
        for (let s = 0; s < this.finalRangeList.length; s++) {
          if (this.finalRangeList[s].colValId === this.selectedData.colValId) {
            this.finalRangeList[s] = this.selectedData;
            this.contains = true;
            let butn = document.getElementById(this.finalRangeList[s].colValId).style.backgroundColor = "#0275d8";
            break;
          }
        }
        if (!this.contains) {
          this.finalRangeList.push(this.selectedData);
          for (let s = 0; s < this.finalRangeList.length; s++) {
            if (this.finalRangeList[s].colValId === this.selectedData.colValId) {
              let butn = document.getElementById(this.finalRangeList[s].colValId).style.backgroundColor = "#0275d8";
            }
          }
        }
        else {
          this.contains = false;
        }
      }
      else {
        this.finalRangeList.push(this.selectedData);
        for (let s = 0; s < this.finalRangeList.length; s++) {
          if (this.finalRangeList[s].colValId === this.selectedData.colValId) {
            let butn = document.getElementById(this.finalRangeList[s].colValId).style.backgroundColor = "#0275d8";
          }
        }
      }
    }
    //let butn = document.getElementById(this.selectedData.ecId).style.backgroundColor = "#0275d8";
    this.displayDialog = false;
    console.log(this.finalRangeList);
  }

  showDialogToAdd() {
    this.newData = true;
    let mechData = new DBOMechPrice();

    mechData.itemId = this.itemId;
    mechData.itemName = this.itemName;
    mechData.colNm = this.columnName;
    mechData.colId = this.colId;
    // mechData.panelCode = this.panelCode;
    mechData.active = true;
    mechData.colValId = 0;
    mechData.defaultVal = false;
    this.selectedData = mechData;
    this.displayDialog = true;
    console.log(this.selectedData)
  }

  //get the assgned user details
  assignedUser(assigne) {
    console.log(assigne);
    console.log(this.tempRes1.userDetailsList);
    this.saveBasic.assignedTo = assigne;
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
        if (this.DBOData[d].colValId == this.finalRangeList[f].colValId) {
          document.getElementById(this.DBOData[d].colValId).style.backgroundColor = "#0275d8";
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
      // if (this.finalRangeList[q].colValId > 0) {
      this.saveBasic.updateCode = "UPD_DBO_MECH_COL";
      // } else {
      //   this.saveBasic.updateCode = "UPD_DBO_MECH_COL_NEW";
      // }
    }
    this.saveBasic.custCode = this.custCode;
    this.saveBasic.modifiedById = this.modifiedBy;
    this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);

    this.tempRes1.saveBasicDetails = this.saveBasic;
    this.tempRes1.quotDboMechList = this.finalRangeList;
    console.log(this.tempRes1);

    //while saving as draft onlt request is created, workflow will not be initiated
    this._ItoUpdateCostDBOMechPrice.createDboMechUpdateRequestCol(this.tempRes1).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.saveBasic.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.tempRes1 = resp;

    });
    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedData = '';
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

    // if price id=0; it means that they are adding new price, so request code is UPD_DBO_MECH_PRICE_NEW
    for (let q = 0; q < this.finalRangeList.length; q++) {
      // if (this.finalRangeList[q].priceId > 0) {
      this.saveBasic.updateCode = "UPD_DBO_MECH_COL";
      // } else {
      //  this.saveBasic.updateCode = "UPD_DBO_MECH_PRICE_NEW";
      // }
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
    this.tempRes1.quotDboMechList = this.finalRangeList;
    console.log(this.tempRes1);

    // request will be created first and then workflow will be initiated
    this._ItoUpdateCostDBOMechPrice.createDboMechUpdateRequestCol(this.tempRes1).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "DBO_MECH_APPROVER") {
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
          this._ItoUpdateCostDBOMechPrice.saveUpdatedNoOfVehicles(respon).subscribe(respo => {
            console.log(respo);
            this.successMsg.push(respo.successMsg);
          })
        });

      } else {
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }

    });

    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedData = '';
    // for (let vv = 0; vv < this.DBOData.length; vv++) {
    //   let butn = document.getElementById(this.DBOData[vv].colValId).style.backgroundColor = "";
    // }
    console.log(this.successMsg)
  }


}

