import { Component, OnInit } from '@angular/core';
import {ItoUpdateTurbineCostService} from './ito-update-turbine-cost.service';
import {turbineCostBean} from './ito-update-turbine-cost';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { BgmratingsComponentService } from '../bgmratings/bgmratings.service';
import { dboClass } from '../bgmratings/bgmratings';
import {ItoUpdateCostF2FAddOnsService} from '../ito-update-f2f-addon/ito-update-f2f-addon.service';
import { ItoUpdateTransportationService } from '../ito-update-price-transport/ito-update-price-transport.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';

@Component({
  selector: 'app-ito-update-turbine-cost',
  templateUrl: './ito-update-turbine-cost.component.html',
  styleUrls: ['./ito-update-turbine-cost.component.css']
})
export class ItoUpdateTurbineCostComponent implements OnInit {

  dboFormData: any;
  displayMessage: boolean = false
  shopConvPercent: number = 0;
  subContrPercent: number = 0;
  adminForm: any;
  displayDialogBulk: boolean = false;
  hideprogress: boolean = false;
  tbcForm: any;
  dboClass:any;
  form: any;
  dispCond: boolean=false;
  disConTyp:boolean=false;
  savedReqQuotForm: any;
  turbineCostBeanType: any;
  selectedFrame:any;
  modifiedBy: any;
  localStorageValues: Array<any> = [];
  loginUserDetails: string = "userDetail";
  userRoles: Array<any> = [];
  selectedUR: any;
  reponseTemp: any;
  tempResp: any;
  prevRemarks: Array<any> = [];
  cols: Array<any> = [];
  usersList: Array<any> = [];
  savBasicDet: any;
  turbineInstWithPriceList: Array<any> = [];
  finalFrameList: Array<any> = [];
  newUsersLilst: Array<any> = [];
  displayDialog:boolean=false;
  f2fEdit:boolean = false;
  financeEdit:boolean = false;
  uboEdit:boolean = false;
  contains: boolean;
  message: boolean = false;
  successMsg: Array<any> = [];
  labelName: string;
  assignDisp: boolean;
  remarkss: any;
  turbineCostReviewer: boolean = false;
  turbineCostApprover: boolean = false;
  currentRole: string = 'selRole';
  currentRoleId: string = 'selRoleId';
  headerString: string;
  f2fEditApp:boolean = false;
  financeEditApp:boolean = false;
  uboEditApp:boolean =false;
  f2fPrevCost: boolean = false;
  financePrevCost: boolean = false;
  uboPrevCost: boolean = false;

  constructor(private _ItoUpdateTurbineCostService: ItoUpdateTurbineCostService, private _ITOturbineConfigService: ITOturbineConfigService,
  private _ItoUpdateTransportationService: ItoUpdateTransportationService,  private _ItoUpdateCostF2FAddOnsService: ItoUpdateCostF2FAddOnsService,  private _BgmratingsComponentService: BgmratingsComponentService, private _ITOLoginService: ITOLoginService,  private router: Router,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOMyWorkListPageService: ITOMyWorkListPageService) {
this.hideprogress = true;
      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
      if(this.storage.get(this.currentRole) == "F2F_EDIT" || this.storage.get(this.currentRole) == "F2F_APPROVER" || this.storage.get(this.currentRole) == "F2F_REVIWER"){
        this.headerString = "Shop Conversion and Sub Contracting";
      }

      this._ItoUpdateTurbineCostService.quotUsers().subscribe(respp => {
        console.log(respp)
        this.tempResp=respp;
        this._ITOturbineConfigService.getDboFormData().subscribe(resss => {
          console.log(resss);
          this.dboFormData =  resss;
          this.dboFormData.saveBasicDetails.updateRequestNumber=0;
        this._BgmratingsComponentService.getAdminCacheWithAIList().subscribe(res => {
          console.log(res);
          this.turbineInstWithPriceList=res.dropDownColumnvalues.placeList12.placeList12;
          console.log(this.turbineInstWithPriceList);
       

      if (this._ITOMyWorkListPageService.selectedUR != '') {
        console.log(this._ITOMyWorkListPageService.responseTemp);
        console.log(this._ITOMyWorkListPageService.selectedUR);
        this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
        this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
       // this._ITOMyWorkListPageService.selectedUR = '';
        this.dboFormData = this._ITOMyWorkListPageService.responseTemp;
        console.log(this.dboFormData);
       if(this.storage.get(this.currentRole) == "F2F_EDIT"){
              console.log("inside if");
              // this.turbineInstWithPriceList = this._ITOMyWorkListPageService.responseTemp.unsavedUpdateF2fShopConvPriceList;
              this.turbineInstWithPriceList = this._ITOMyWorkListPageService.responseTemp.savedUpdateF2fShopConvPrcieList;
              this.finalFrameList = this._ITOMyWorkListPageService.responseTemp.savedUpdateF2fShopConvPrcieList;
              // for (let s = 0; s < this.turbineInstWithPriceList.length; s++) {
              //   for (let q = 0; q < this.finalFrameList.length; q++) {
              //     if (this.turbineInstWithPriceList[s].f2fMastId == this.finalFrameList[q].f2fMastId) {
              //       this.turbineInstWithPriceList[s] = this.finalFrameList[q];
              //     }
              //   }
              // }
              this.tempResp.saveBasicDetails.updateRequestNumber = this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
           this.cols = [
            { field: 'frmName', header: 'Frame' },
            { field: 'bleedTypeName', header: 'Bleed Type' },     
            { field: 'maxPower', header: 'Power'},
            { field: 'totalPrice', header: 'Total Price' }, 
            { field: 'subContrCost', header: 'SubContract Cost' },
            { field: 'shopConvCost', header: 'Shop Conversion Cost' },            
            { field: 'totalFTFCost', header: 'Total F2F Cost' },
              ];
            
            }
            else if(this.storage.get(this.currentRole) == "F2F_APPROVER" || this.storage.get(this.currentRole) == "F2F_REVIWER"){
              this.turbineInstWithPriceList = this._ITOMyWorkListPageService.responseTemp.savedUpdateF2fShopConvPrcieList;
              this.finalFrameList = this._ITOMyWorkListPageService.responseTemp.savedUpdateF2fShopConvPrcieList;
              this.cols = [
                { field: 'frmName', header: 'Frame' },
                { field: 'bleedTypeName', header: 'Bleed Type' },                
                { field: 'maxPower', header: 'Power'},
                // { field: 'prevTotalPrice', header: 'Prev Total Price' }, 
                { field: 'totalPrice', header: 'Total Price' }, 
                { field: 'prevSubContrCost', header: 'Prev SubContract Cost' },                 
            { field: 'subContrCost', header: 'SubContract Cost' },
            { field: 'prevShopConvCost', header: 'Prev ShopConversion Cost' },             
            { field: 'shopConvCost', header: 'Shop Conversion Cost' },            
            // { field: 'prevTotalF2fCost', header: 'Prev Total F2F Price' }, 
            { field: 'totalF2fCost', header: 'Total F2F Cost' },
              ];
            
            }           
             
        //console.log(this.packageDetailsWithPriceList);
        // for (let v = 0; v < this.turbineInstWithPriceList.length; v++) {
        // }
        for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
          this.prevRemarks.push(this.reponseTemp.commentList[r]);
        }
       
      }
      
      else
      {
      if(this.storage.get(this.currentRole) == "F2F_EDIT"){
        
        this.cols = [
          { field: 'frmName', header: 'Frame' },
            { field: 'bleedTypeName', header: 'Bleed Type' },            
            { field: 'maxPower', header: 'Power'},
            { field: 'totalPrice', header: 'Total Price' }, 
            { field: 'subContrCost', header: 'SubContract Cost' },
            { field: 'shopConvCost', header: 'Shop Conversion Cost' },            
            { field: 'totalFTFCost', header: 'Total F2F Cost' },
        ];
      }    
    
  }
   
      this.usersList = respp.userDetailsList;
      console.log(this.usersList);
      this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
      
        switch (this.storage.get(this.currentRole)) {
          case "F2F_EDIT": {
            this.f2fEdit = true;
            this.labelName = "Please select the reviewr";
            this.tempResp.saveBasicDetails.loggedInUserCode = 0;
            this.tempResp.saveBasicDetails.updateRequestNumber = 0;
            this.tempResp.saveBasicDetails.statusId = 1;
            for (let r = 0; r < this.usersList.length; r++) {
              for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
                  if (this.usersList[r].rolesCodeVal[s] === "F2F_REVIWER") {
                  this.newUsersLilst.push(this.usersList[r]);
                  console.log(this.newUsersLilst);
                  }
                }
              }
            break;
          }
          case "F2F_APPROVER": {
           this.f2fEditApp = true;
           this.f2fPrevCost = true;
            this.turbineCostApprover = true;
            this.turbineCostReviewer=false;
            this.tempResp.saveBasicDetails.loggedInUserCode = 2;
            this.tempResp.saveBasicDetails.updateRequestNumber =  this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
            break;

          }
          case "F2F_REVIWER": {
            this.f2fEdit = true;
            this.f2fPrevCost = true;
            this.turbineCostReviewer=true;
           this.labelName = "Please select the approver";
           this.tempResp.saveBasicDetails.loggedInUserCode = 1;
           this.tempResp.saveBasicDetails.updateRequestNumber =  this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
            for (let r = 0; r < this.usersList.length; r++) {
              for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
                  if (this.usersList[r].rolesCodeVal[s] === "F2F_APPROVER") {
                  this.newUsersLilst.push(this.usersList[r]);
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
  });
});
  }
  ngOnInit() {
   
  }
  // ngAfterViewChecked() {
  //   if (this.storage.get(this.currentRole) === "F2F_EDIT") {
  //   for (let s = 0; s < this.turbineInstWithPriceList.length; s++) {
  //     for (let q = 0; q < this.finalFrameList.length; q++) {
  //       if (this.turbineInstWithPriceList[s].f2fMastId == this.finalFrameList[q].f2fMastId) {
  //         let butn = document.getElementById(this.turbineInstWithPriceList[s].f2fMastId).style.backgroundColor = "#0275d8";
  //         }
  //       }
  //     }
  //   }
  // }
  rowSelected(rowData) {

    console.log(this.usersList);
    console.log(rowData);
    if(rowData.condTypId==0){
      this.dispCond=false;
      this.disConTyp=false;
    }else if(rowData.condTypId==44){
      this.dispCond=true;
      this.disConTyp=true;
    }else{
      this.dispCond=false;
      this.disConTyp=true;
    }
    this.displayDialog = true;
    this.selectedFrame = rowData;
  }
  save(){

    console.log(this.selectedFrame);
    let temp=false;
    if (this.finalFrameList.length != 0) {
      for(let j=0;j<this.finalFrameList.length;j++)
      {
    if(this.finalFrameList[j].f2fMastId==this.selectedFrame.f2fMastId)
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

  saveAsDraftTurbine(tbcForm, form: NgForm){
    this.successMsg = [];
    console.log(tbcForm);
    this.tbcForm = form;
    console.log(this.finalFrameList);
    this.assignDisp = true;
    this.dboFormData.saveBasicDetails.updateF2fShopConv=[];
    console.log(this.finalFrameList);
    this.dboFormData.saveBasicDetails.modifiedById = this.modifiedBy;
    this.dboFormData.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
    this.dboFormData.saveBasicDetails.remarks = tbcForm.remarks;
    this.dboFormData.saveBasicDetails.assignedTo = tbcForm.assignee;
    if(this.storage.get(this.currentRole)=="F2F_EDIT")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=0;
  this.dboFormData.saveBasicDetails.statusId = 1;

}
else if(this.storage.get(this.currentRole)=="F2F_REVIWER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=1;
  if (tbcForm.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (tbcForm.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}
else if(this.storage.get(this.currentRole)=="F2F_APPROVER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=2;
  if (tbcForm.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (tbcForm.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}

  this.dboFormData.updateF2fShopConv=[];
  
  for(let j=0;j<this.finalFrameList.length;j++)
  {
    this.dboClass = new dboClass();
this.dboClass.updateCode = "F2F_SHOP_CONV_UPD";
  this.dboClass.f2fMastId=this.finalFrameList[j].f2fMastId;
  this.dboClass.framePowerId=this.finalFrameList[j].framePowerId;
  //this.dboClass.condTypeId=this.finalFrameList[j].condTypeId;
  this.dboClass.bleedTypeId=this.finalFrameList[j].bleedTypeId;
  this.dboClass.price = this.finalFrameList[j].price;
  this.dboClass.approxCostFlag = this.finalFrameList[j].approxCostFlag;
  //this.dboClass.estimationCost=this.finalFrameList[j].estimationCost;//error
  //this.dboClass.turbInstrCost=this.finalFrameList[j].turbInstrCost;//error
  //this.dboClass.condInstrCost=this.finalFrameList[j].condInstrCost;//error
  this.dboClass.subContrCost=this.finalFrameList[j].subContrCost;//error
  this.dboClass.shopConvCost=this.finalFrameList[j].shopConvCost;//error
  this.dboClass.activeNew=this.finalFrameList[j].activeNew;//not there  
  this.dboFormData.updateF2fShopConv.push(this.dboClass);
  }
  console.log(this.dboFormData);


  this._ItoUpdateTurbineCostService.createUpdateF2fShopConv(this.dboFormData).subscribe(resp => {
    console.log(resp);
    this.savedReqQuotForm = resp;
    this.tempResp.saveBasicDetails.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
    this.message = true;
    //this.successMsg = resp.successMsg;
    this.successMsg.push(resp.successMsg);
  });
 // this.message = true;
  console.log(this.successMsg);

  
}
  
updateTurbineCostForm(tbcForm, form: NgForm){
console.log(tbcForm);
this.tbcForm = form;
    this.dboFormData.saveBasicDetails.updateF2fShopConv=[];
    console.log(this.finalFrameList);
    this.dboFormData.saveBasicDetails.modifiedById = this.modifiedBy;
    this.dboFormData.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
    this.dboFormData.saveBasicDetails.remarks = tbcForm.remarks;
    this.dboFormData.saveBasicDetails.assignedTo = tbcForm.assignee;
    if(this.storage.get(this.currentRole)=="F2F_EDIT")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=0;
  this.dboFormData.saveBasicDetails.statusId = 1;

}
else if(this.storage.get(this.currentRole)=="F2F_REVIWER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=1;
  if (tbcForm.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (tbcForm.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}
else if(this.storage.get(this.currentRole)=="F2F_APPROVER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=2;
  if (tbcForm.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (tbcForm.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}

  this.dboFormData.updateF2fShopConv=[];
  for(let j=0;j<this.finalFrameList.length;j++)
  {
    this.dboClass = new dboClass();
    this.dboClass.updateCode = "F2F_SHOP_CONV_UPD";
  this.dboClass.f2fMastId=this.finalFrameList[j].f2fMastId;
  this.dboClass.framePowerId=this.finalFrameList[j].framePowerId;
  // this.dboClass.condTypeId=this.finalFrameList[j].condTypeId;
  this.dboClass.bleedTypeId=this.finalFrameList[j].bleedTypeId;
  this.dboClass.price = this.finalFrameList[j].price;
  this.dboClass.approxCostFlag = this.finalFrameList[j].approxCostFlag;
  //this.dboClass.estimationCost=this.finalFrameList[j].estimationCost;//error
  //this.dboClass.turbInstrCost=this.finalFrameList[j].turbInstrCost;//error
  //this.dboClass.condInstrCost=this.finalFrameList[j].condInstrCost;//error
  this.dboClass.subContrCost=this.finalFrameList[j].subContrCost;//error
  this.dboClass.shopConvCost=this.finalFrameList[j].shopConvCost;//error
  this.dboClass.activeNew=this.finalFrameList[j].activeNew;//not there  
  this.dboFormData.updateF2fShopConv.push(this.dboClass);
  }
  console.log(this.dboFormData);
  if (this.assignDisp) {
    this.updateStatusAndSave(this.savedReqQuotForm);
  } else  {   
     if(this.storage.get(this.currentRole) == "F2F_EDIT" || this.storage.get(this.currentRole) == "F2F_APPROVER" || this.storage.get(this.currentRole) == "F2F_REVIWER"){
    
    this._ItoUpdateTurbineCostService.createUpdateF2fShopConv(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.tempResp.saveBasicDetails.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.successMsg.push(resp.successMsg);
      this.message = true;
      this.updateStatusAndSave(resp);
    });
  }
  
}
}

updateStatusAndSave(resp) {

    if (this.storage.get(this.currentRole) == "F2F_APPROVER") {
      resp.saveBasicDetails.assignedTo = this.modifiedBy;
      this._ItoUpdateTurbineCostService.updateStatus(resp).subscribe(respon => {
        console.log(respon);
        this._ItoUpdateTurbineCostService.updateFinalSave(respon).subscribe(respo => {
          console.log(respo);
          this.message = true;
          this.successMsg.push(respo.successMsg);
        });

      });
    } else {
      this._ItoUpdateTurbineCostService.updateStatus(resp).subscribe(respon => {
        console.log(respon);
        this.message = true;
      this.successMsg.push(respon.successMsg);
      });
    }
    this.assignDisp = false;
  }

  assignedUser(assigne) {
    console.log(assigne);
    console.log(this.tempResp.userDetailsList);
    console.log(this.newUsersLilst);
    this.dboFormData.saveBasicDetails.assignedTo = assigne;
  }
  closeMessage() {
    this.message = false;
    this.successMsg = [];
    // for (let s = 0; s < this.turbineInstWithPriceList.length; s++) {
    //   for (let q = 0; q < this.finalFrameList.length; q++) {
    //     if (this.turbineInstWithPriceList[s].f2fMastId == this.finalFrameList[q].f2fMastId) {
    //       let butn = document.getElementById(this.turbineInstWithPriceList[s].f2fMastId).style.backgroundColor = "";
    //     }
    //   }
    // }
    this.finalFrameList = [];
    this.selectedFrame = '';
    this.tbcForm.reset();
    if (!this.assignDisp) {
      this.navigateToWorkFlow();
    }
  }
  navigateToWorkFlow() {
    this.router.navigate(['MyWorkFlow']);
  }
  dispMessage(){
    this.displayMessage = false;
    this.navigateToWorkFlow();
  }
  
  saveBulk()
  {
    this._ItoUpdateTransportationService.getAdminForm().subscribe(res => {
      console.log(res);
      this.adminForm = res;
      // this.adminForm.percent=this.percentage;      
      this.adminForm.subContrPercent = this.subContrPercent;
      this.adminForm.shopConvPercent = this.shopConvPercent;
      this.adminForm.modifiedBy=this.modifiedBy;
    
    this._ItoUpdateCostF2FAddOnsService.getAdminPercentF2F(this.adminForm).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.displayDialogBulk=false;
      this.displayMessage = true;
    });
  });
  }
  bulkUpd()
  {
    this.shopConvPercent=0;
    this.subContrPercent=0;
    this.displayDialogBulk=true;

  }
}
