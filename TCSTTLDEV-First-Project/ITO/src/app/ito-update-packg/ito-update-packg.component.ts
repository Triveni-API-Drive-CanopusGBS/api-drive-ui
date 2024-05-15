import { Component, OnInit } from '@angular/core';
import { ItoUpdatePackgService } from './ito-update-packg.service';
import { packgBean } from './ito-update-packg';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ItoUpdateTransportationService} from '../ito-update-price-transport/ito-update-price-transport.service';


@Component({
  selector: 'app-ito-update-packg',
  templateUrl: './ito-update-packg.component.html',
  styleUrls: ['./ito-update-packg.component.css']
})
export class ItoUpdatePackgComponent implements OnInit {

  adminForm:any;/// to send bulk data
  percentage:number=0;
  displayDialogBulk:boolean=false;
  //Array to store frames with out power.
  framesWithoutPower: Array<any> = [];
  //Array to store frames with power.
  frames: Array<any> = [];
  //Array to store type of turbine
  typeOfTurbine: Array<any> = [];
  newTurbineType: Array<any> = [];
  //Array to store type of condenser
  condenserTypes: Array<any> = [];
  //Variable to save basic
  savBasicDet: any;
  newFrames: Array<any> = [];
  powerList: Array<any> = [];
  enableCT: boolean = false;
  enableUpdateSec: boolean = false;
  price: number = 0;
  //varable to store users list
  usersList: Array<any> = [];
  //variable to store cols
  cols: Array<any> = [];
  //Array to store packaging details with price list.
  packageDetailsWithPriceList: Array<any> = [];
  //Array to store final frame list
  finalFrameList: Array<any> = [];
  //variable to display dialog
  displayDialog: boolean;
  //variable to store selected frame
  selectedFrame: any;
  //variable to store temp resp
  tempResp: any;
  //Array to store succes message
  successMsg: Array<string> = [];
  //variable to assign display
  assignDisp: boolean;
  //variable to store package bean columns
  packgBeanType: packgBean;
  //variable to stre modified by
  modifiedBy: any;
  //variable to store local storage values
  localStorageValues: Array<any> = [];
  //variable to store login user details
  loginUserDetails: string = "userDetail";
  //variable to store user roles
  userRoles: Array<any> = [];
  //flag to check edit mode
  transpEdit: boolean;
  //flag to check approver mode
  transpApp: boolean;
  //flag to check reviewer mode
  transRev: boolean;
  //variable to store label name
  labelName: string;
  //variable to store new users list
  newUsersLilst: Array<any> = [];
  contains: boolean;
  //variable to store selected UR
  selectedUR: any;
  //variable to store temp esponse
  reponseTemp: any;
  //Array to stre previous remarks
  prevRemarks: Array<any> = [];
  //variable to store remarks
  remarkss: any;
  //variable to store current role
  currentRole: string = 'selRole';
  //variable to store current role id
  currentRoleId:string = 'selRoleId';
  //variable to show display message
  displayMessage: boolean = false;
  acceptedOnly: boolean = true;
  //variable to store packaging details with price list temp
  packageDetailsWithPriceListTemp: Array<any> = [];
  //variables to store selected frame temp.
  selectedFrameTemp: number;
  

  constructor(private _ItoUpdateTransportationService: ItoUpdateTransportationService,private _ItoUpdatePackgService: ItoUpdatePackgService, private _ITOLoginService: ITOLoginService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOMyWorkListPageService: ITOMyWorkListPageService,
    private router: Router, private route: ActivatedRoute) {
    this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
    this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;
    this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;

    if (this._ITOMyWorkListPageService.selectedUR != '') {
      console.log(this._ITOMyWorkListPageService.responseTemp);
      this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
      this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
      this._ITOMyWorkListPageService.selectedUR = '';
      this.tempResp = this._ITOMyWorkListPageService.responseTemp;

      if (this.storage.get(this.currentRole) == "PKG_FW_EDIT") {
        this.packageDetailsWithPriceList = this._ITOMyWorkListPageService.responseTemp.unsavedUpdatePKGPriceList;
        this.finalFrameList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePKGPriceList;
       // this.savBasicDet.updateRequestNumber = this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;

      }
      else if (this.storage.get(this.currentRole) != "PKG_FW_EDIT") {
        this.packageDetailsWithPriceList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePKGPriceList;
        this.finalFrameList = this._ITOMyWorkListPageService.responseTemp.savedUpdatePKGPriceList;
      }

      console.log(this.packageDetailsWithPriceList);
      for (let v = 0; v < this.packageDetailsWithPriceList.length; v++) {
      }
      for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
        this.prevRemarks.push(this.reponseTemp.commentList[r]);
      }
      this.cols = [
        { field: 'frameName', header: 'Frame Name' },
        { field: 'turbDesgnName', header: 'Turbine Design' },
        { field: 'turbTypeName', header: 'Turbine Type' },
        { field: 'condensingType', header: 'Condensing Type' },
    //    { field: 'maxPower', header: 'Power' },
        { field: 'price', header: 'Cost' },
        { field: 'prevPrice', header: 'Previous Cost' }
      ];
    }
    else {
      this.cols = [
        { field: 'frameName', header: 'Frame Name' },
        { field: 'turbDesgnName', header: 'Turbine Design' },
        { field: 'turbTypeName', header: 'Turbine Type' },
        { field: 'condensingType', header: 'Condensing Type' },
       // { field: 'maxPower', header: 'Power' },
        { field: 'price', header: 'Unit Cost' }
      ];
      this._ItoUpdatePackgService.getPackageWithPriceList().subscribe(res => {
        console.log(res);
        this.tempResp = res;
        this.packageDetailsWithPriceList = res.packageDetailsWithPriceList;
        this.framesWithoutPower = res.dropDownColumnvalues.frames.FRAMES;
        this.frames = res.dropDownColumnvalues.frameWithPowerList.FRAMES_WITH_POWER;
        this.typeOfTurbine = res.dropDownColumnvalues.typesOfTurbine.TURBINE;
        this.newTurbineType = res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
        this.condenserTypes = res.dropDownColumnvalues.typesOfCondensor.CONDENSOR;
      });
    }


    this._ItoUpdatePackgService.quotUsers().subscribe(respp => {
      this.savBasicDet = respp.saveBasicDetails;
      console.log(respp)
      this.usersList = respp.userDetailsList;
      console.log(this.usersList);
      this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
      switch (this.storage.get(this.currentRole)) {
        case "PKG_FW_EDIT": {
          this.transpEdit = true;
          this.labelName = "Please select the reviewr";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "PKG_FW_REVIWER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.savBasicDet.loggedInUserCode = 0;
                this.savBasicDet.updateRequestNumber = 0;
                this.savBasicDet.statusId = 1;
              }
            }
          }
          break;
        }
        case "PKG_FW_APPROVER": {
          this.transpApp = true;
          this.savBasicDet.loggedInUserCode = 2;
          this.savBasicDet.updateRequestNumber = this.tempResp.saveBasicDetails.updateRequestNumber;
          break;

        }
        case "PKG_FW_REVIWER": {
          this.transRev = true;
          this.labelName = "Please select the approver";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "PKG_FW_APPROVER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.savBasicDet.loggedInUserCode = 1;
                this.savBasicDet.updateRequestNumber = this.tempResp.saveBasicDetails.updateRequestNumber;
              }
            }
          }
          break;
        }
        default: {

          break;
        }
      }

    })

  }

  ngOnInit() {
    this._ItoUpdatePackgService.getPackageWithPriceList().subscribe(resTemp => {
      this.packageDetailsWithPriceListTemp = resTemp.packageDetailsWithPriceList;
    });
    this._ItoUpdatePackgService.quotUsers().subscribe(users => {
      this.usersList = users.userDetailsList;
    });
  }
  ngAfterViewChecked() {
    if (this.storage.get(this.currentRole) == "PKG_FW_EDIT") {
      for (let d = 0; d < this.packageDetailsWithPriceList.length; d++) {
        for (let f = 0; f < this.finalFrameList.length; f++) {
          if (this.packageDetailsWithPriceList[d].pkgId == this.finalFrameList[f].pkgId) {
            document.getElementById(this.packageDetailsWithPriceList[d].pkgId).style.backgroundColor = "#0275d8";
            this.packageDetailsWithPriceList[d] = this.finalFrameList[f];
            //console.log(this.vehiclesWithPrice[d]);
          }
        } 
      }
    }
  }
//Edit functionality to update cost
  rowSelected(rowData) {
    console.log(this.usersList);
    console.log(rowData);
    this.displayDialog = true;
    this.selectedFrame = rowData;

  }
  //Method to edit packaging cost.
  save() {
    console.log(this.savBasicDet);
    for (let d = 0; d < this.packageDetailsWithPriceListTemp.length; d++) {
       if (this.packageDetailsWithPriceListTemp[d].pkgId == this.selectedFrame.pkgId) {
          this.selectedFrameTemp = this.packageDetailsWithPriceListTemp[d].price;
        }
    }
    console.log(this.selectedFrameTemp);
    console.log(this.selectedFrame.price);
    if(this.selectedFrameTemp != this.selectedFrame.price){
    if (this.finalFrameList.length != 0) {
      for (let s = 0; s < this.finalFrameList.length; s++) {
        if (this.finalFrameList[s].pkgId === this.selectedFrame.pkgId) {
          this.finalFrameList[this.finalFrameList.indexOf(this.finalFrameList[s])] = this.selectedFrame;
          let butn = document.getElementById(this.finalFrameList[s].pkgId).style.backgroundColor = "#0275d8";
          this.contains = true;
          break;
        }
      }
      if (!this.contains) {
        this.finalFrameList.push(this.selectedFrame);
        for (let s = 0; s < this.finalFrameList.length; s++) {
          let butn = document.getElementById(this.finalFrameList[s].pkgId).style.backgroundColor = "#0275d8";
        }
      }
      else {
        this.contains = false;
      }

    }
    else {
      this.finalFrameList.push(this.selectedFrame);
      for (let s = 0; s < this.finalFrameList.length; s++) {
        let butn = document.getElementById(this.finalFrameList[s].pkgId).style.backgroundColor = "#0275d8";
      }
    }
    this.displayDialog = false;
    console.log(this.finalFrameList);
    // let butn = document.getElementById(this.selectedFrame.pkgId).style.backgroundColor = "#0275d8";
  }else{
    this.displayDialog =false;
    let butn = document.getElementById(this.selectedFrame.pkgId).style.backgroundColor = "#FFFFFF";
  }
  }
  //Based on search criteria provided by the user, list of Packaging details will display.
  searchSet(dtd, value) {
    console.log(dtd.filteredValue, value, "keyUp");
    console.log(this.finalFrameList);
    if (dtd.filteredValue != null) {
      for (let d = 0; d < dtd.filteredValue.length; d++) {
        for (let f = 0; f < this.finalFrameList.length; f++) {
          if (dtd.filteredValue[d].pkgId == this.finalFrameList[f].pkgId) {
            let butn = document.getElementById(dtd.filteredValue[d].pkgId).style.backgroundColor = "#0275d8";
          }
        }
      }
    }
    else {
      for (let d = 0; d < this.packageDetailsWithPriceList.length; d++) {
        for (let f = 0; f < this.finalFrameList.length; f++) {
          if (this.packageDetailsWithPriceList[d].pkgId == this.finalFrameList[f].pkgId) {
            let butn = document.getElementById(this.packageDetailsWithPriceList[d].pkgId).style.backgroundColor = "#0275d8";
          }
        }
      }
    }

  }

//Close the dialog box once the success message displayed.
  closeMessage() {
    this.displayMessage = false;
    this.finalFrameList = [];
    this.remarkss = '';
    this.successMsg = [];
    this.acceptedOnly = true;
    this.navigateToMyWorkflow();
  }
//On click ok button in success message dialog box will navigate to my workflow page.
  navigateToMyWorkflow() {
    this.router.navigate(['MyWorkFlow']);
  }

  // final update method
  updatePricePckgForm(updatePriceTrans) {
    this.successMsg = [];
    this.tempResp.packageupdatedPriceList = [];
    console.log(updatePriceTrans);
    this.savBasicDet.remarks = updatePriceTrans.coments;
    this.assignDisp = true;

    console.log(this.finalFrameList);

    ///code here

    for (let v = 0; v < this.finalFrameList.length; v++) {
      this.packgBeanType = new packgBean();
      if (this.finalFrameList[v] != undefined) {
        console.log(this.finalFrameList[v]);
        this.packgBeanType.pkgId = this.finalFrameList[v].pkgId;
        this.packgBeanType.pkgTypeId = this.finalFrameList[v].pkgTypeId;
        this.packgBeanType.frameId = this.finalFrameList[v].frameId;
        this.packgBeanType.price = this.finalFrameList[v].price;
        this.packgBeanType.condenTypeId=this.finalFrameList[v].condenTypeId;
        this.savBasicDet.updateCode = "UP_PF";
        this.savBasicDet.modifiedById = this.modifiedBy;
        this.savBasicDet.userRoleId = this.storage.get(this.currentRoleId);
        if (updatePriceTrans.status == "Accept") {
          this.savBasicDet.statusId = 1;
        }
        else if (updatePriceTrans.status == "Reject") {
          this.savBasicDet.statusId = 0;
        }
        this.tempResp.saveBasicDetails = this.savBasicDet;
        this.tempResp.packageupdatedPriceList.push(this.packgBeanType);
        console.log(this.tempResp);
      }
    }
    this._ItoUpdatePackgService.updatePackagePrice(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.displayMessage = true;
      if (this.storage.get(this.currentRole) === "PKG_FW_APPROVER") {
        this._ItoUpdatePackgService.updateStatusAndSubmit(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
          this._ItoUpdatePackgService.saveUpdatedPackagePrice(respon).subscribe(respo => {
            console.log(respo);
            this.successMsg.push(respo.successMsg);
          })
        })

      } else {
        this._ItoUpdatePackgService.updateStatusAndSubmit(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }

    });

    this.finalFrameList = [];
    this.selectedFrame = '';
    for (let vv = 0; vv < this.packageDetailsWithPriceList.length; vv++) {
      let butn = document.getElementById(this.packageDetailsWithPriceList[vv].pkgId).style.backgroundColor = "";
    }
  }

  //save as method

  saveAsDraftPckg() {
    this.successMsg = [];
    this.tempResp.packageupdatedPriceList = [];
    this.assignDisp = true;
    this.savBasicDet.remarks = this.remarkss;
    console.log(this.finalFrameList);

    ///code here

    for (let v = 0; v < this.finalFrameList.length; v++) {
      this.packgBeanType = new packgBean();
      if (this.finalFrameList[v] != undefined) {
        console.log(this.finalFrameList[v]);
        this.packgBeanType.pkgId = this.finalFrameList[v].pkgId;
        this.packgBeanType.pkgTypeId = this.finalFrameList[v].pkgTypeId;
        this.packgBeanType.framePowId = this.finalFrameList[v].framePowId;
        this.packgBeanType.price = this.finalFrameList[v].price;
        this.savBasicDet.updateCode = "UP_PF";
        this.savBasicDet.modifiedById = this.modifiedBy;
        this.savBasicDet.userRoleId = this.storage.get(this.currentRoleId);
        // if (updatePriceTrans.status == "Accept") {
        //   this.savBasicDet.statusId = 1;
        // }
        // else if (updatePriceTrans.status == "Reject") {
        //   this.savBasicDet.statusId = 0;
        // }
        this.tempResp.saveBasicDetails = this.savBasicDet;
        this.tempResp.packageupdatedPriceList.push(this.packgBeanType);
        console.log(this.tempResp);
      }
    }
    this._ItoUpdatePackgService.updatePackagePrice(this.tempResp).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.savBasicDet.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.tempResp = resp;
      this.displayMessage = true;
      // for (let r = 0; r < this.userRoles.length; r++) {
      //   if (this.userRoles[r].code === "PKG_FW_APPROVER") {
      //     this._ItoUpdatePackgService.updateStatusAndSubmit(resp).subscribe(respon => {
      //       console.log(respon);
      //       this.successMsg = respon.successMsg;
      //       this._ItoUpdatePackgService.saveUpdatedPackagePrice(respon).subscribe(respo => {
      //         console.log(respo);
      //         this.successMsg = respo.successMsg;
      //       })
      //     })

      //   } else {
      //     this._ItoUpdatePackgService.updateStatusAndSubmit(resp).subscribe(respon => {
      //       console.log(respon);
      //       this.successMsg = respon.successMsg;
      //     })
      //   }
      // }
    });

    // this.finalFrameList = [];
    this.selectedFrame = '';
    for (let vv = 0; vv < this.packageDetailsWithPriceList.length; vv++) {
      let butn = document.getElementById(this.packageDetailsWithPriceList[vv].pkgId).style.backgroundColor = "";
    }
  }


  assignedUser(assigne) {
    console.log(assigne);
    console.log(this.tempResp.userDetailsList);
    this.savBasicDet.assignedTo = assigne;

  }
  saveBulk()
  {
    this._ItoUpdateTransportationService.getAdminForm().subscribe(res => {
      console.log(res);
      this.adminForm = res;
      this.adminForm.percent=this.percentage;
      this.adminForm.modifiedBy=this.modifiedBy;
    
    this._ItoUpdatePackgService.getAdminPercentPkg(this.adminForm).subscribe(resp => {
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
