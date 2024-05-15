import { Component, OnInit } from '@angular/core';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { delay } from 'rxjs/operator/delay';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ItoUpdateCostF2FAddOnsService } from './ito-update-f2f-addon.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { dboClass } from '../ito-upd-dbo-ele-col/ito-upd-dbo-ele-col';
import { ThrowStmt } from '@angular/compiler';
import { ItoUpdateTransportationService } from '../ito-update-price-transport/ito-update-price-transport.service';

@Component({
  selector: 'app-ito-update-f2f-addon',
  templateUrl: './ito-update-f2f-addon.component.html',
  styleUrls: ['./ito-update-f2f-addon.component.css']
})
export class ItoUpdateF2fAddonComponent implements OnInit {

  DispMessage: boolean = false;
  shopConvPercent: number = 0;
  subContrPercent: number = 0;
  percentage: number = 0;
  adminForm: any;
  displayDialogBulk: boolean = false;
  dboClass:any;
  subItemTypTemp:Array<any>=[];
  subTempList:Array<any>=[];
  addOnDataTemp:Array<any>=[];
  mainList: Array<any> =[];
  defaultvaluepanel:string='';
  updGetF2fData: any;
  panels: Array<any> =[];
  tbcForm: any;
  subItemList: Array<any> =[];
  subItemDefault:string='';
  subItemId:number=0;
  subItemTypeList:Array<any>=[];
  subItemTypeDefault:string='';
  subItemTypeId:number=0;
  dboFormData:any;
  dropdowntype:Array<any>=['Main Price','AddOn Cost', 'Frame Specific Data'];
  dropdowndefault:string='Main Price';
  exist: boolean = false;   
  UBOWithPrice: any;
  selectedUR: any = '';
  modifiedBy: any;
  cols: any;
  framePowerName: any;
  tempRes: any;
  selectedData: any;
  saveBasic: any;
  savedReqQuotForm: any;
  reponseTemp: any;
  rowGroupMetadata: any;
  uboForm: any;
  userId: any;
  assigne: any;
  panelCode: string;  

  F2FApp: boolean = false;
  F2FRev: boolean = false;
  dispDropdown: boolean = true;
  isDataSaved: boolean = false;
  displayBleed: boolean = false;
  contains: boolean = false;
  message: boolean = false;
F2FEdit: boolean = false;
  enablePrevPrice: boolean = false;
  displayCond: boolean = false;
  hideprogress: boolean = false;
  showNoCostMsg: boolean = false;
  enableStatus: boolean = true;
  dispCondTyp: boolean = false;
  displayDialog: boolean = false;

  prevRemarks: Array<any> = [];
  framePowerArray: Array<any> = [];
  frameArray: Array<any> = [];
  frameDrpDowns: Array<any> = [];
  turbineTypeArray: Array<any> = [];
  turbineDesignArray: Array<any> = [];
  bleedTypArray: Array<any> = [];
  condensorTypes: Array<any> = [];
  tempFrameArray: Array<any> = [];
  frmPowerArray: Array<any> = [];
  addOnData: Array<any> = [];
  catListArray: Array<any> = [];
  indtotl: Array<any> = [];
  displayTable: Array<any> = [];
  localStorageValues: Array<any> = [];
  userRoles: Array<any> = [];
  catArrayLatest: Array<any> = [];
  updatedPriceList: Array<any> = [];
  usersList: Array<any> = [];
  newUsersList: Array<any> = [];
  newFrameWithPowersList: Array<any> = [];
  uboListforNewFrames: Array<any> = [];
  successMsg: Array<any> = [];
  data: Array<any> = [];

  turbineDesign: string;
  turbineType: string;
  bleedType: string;
  itemName: string = '';

  labelName: string;
  condName: string;
  loginUserDetails: string = "userDetail";
  currentRole: string = 'selRole';
  currentRoleId: string = 'selRoleId';
  user: string = 'userDetail'; 
  addNew: boolean = false;  
  itemId: any = '';  
  custType: any = '';  //Domestic or Export
  tableDiv: boolean = false;  
  uBOForm: any;

  framePwrID: number;
  bleedTypID: number;
  total: number;
  frmPower: number;
  condId: number = 0;
  tabIndex: number = 0;
  custCode: string = '';   
  enableSel:boolean= false;

  constructor(private _ItoUpdateCostF2FAddOnsService: ItoUpdateCostF2FAddOnsService, private _Router: Router
    , private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    private _ITOturbineConfigService: ITOturbineConfigService,private _ItoUpdateTransportationService: ItoUpdateTransportationService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOMyWorkListPageService: ITOMyWorkListPageService,
    private router: Router, private route: ActivatedRoute) {
    this.hideprogress = true;

    this.assigne = this.data[this.user] = this.storage.get(this.user);
    this.userId = this.data[this.user].userId;  // get logged in userId

    // fetch user details list to get reviewer and approver list 
    this._ItoUpdateCostF2FAddOnsService.getFrameAndUserData().subscribe(res => {
      console.log(res);
      this.tempRes = res;  // assign the response to local variable for further use     
      this.saveBasic = res.saveBasicDetails;  // assign the savebasicdetails bean to local variable for further use
      this.usersList = res.userDetailsList;   // assign userlist to local variable
      this.updatedPriceList = [];
      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;   // assign stored rol list of logged in user to local list
      this.turbineDesignArray = res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
      this.turbineTypeArray = res.dropDownColumnvalues.typesOfTurbine.TURBINE;
      this.frameDrpDowns = res.dropDownColumnvalues.frameWithPowerList.FRAMES_WITH_POWER;
      console.log(this.userRoles);
      this._ITOturbineConfigService.getDboFormData().subscribe(resss => {
        console.log(resss);
        this.dboFormData =  resss;
        this.dboFormData.saveBasicDetails.updateRequestNumber=0;
      this.dboFormData.scopeCd = "F2F";
      this._ItoUpdateCostF2FAddOnsService.updateGetAddOn(this.dboFormData).subscribe(res => {
        console.log(res);
        this.updGetF2fData=res;
        let temp=null;
        this.addOnData =[];
        this.enableSel= true;
        this.hideprogress = false;
      //   this.addOnData=res.priceList;
      //   if(this.dropdowndefault=='Main Price'){
      //   this.mainList=res.priceList;
      // }
    //      temp =  res.priceList.reduce((acc, current) => {
    //       console.log(acc, current);
    //       const x = acc.find(item => item.itemName === current.itemName);
    //       if (!x) {
    //         return acc.concat([current]);
    //       } else {
    //         return acc;
    //       } 
    //     }, []);
    //     for(let j=0;j<temp.length;j++)
    //     {
    // this.panels.push(temp[j]);
    //     }
    //     this.defaultvaluepanel=this.panels[0].itemName;
    //     this.itemId=this.panels[0].itemId;

      console.log(this._ITOMyWorkListPageService.selectedUR);   // this will have the selected updatecode
      if (this._ITOMyWorkListPageService.selectedUR != '') {
        console.log(this._ITOMyWorkListPageService.responseTemp);
        this.dispDropdown = false;
        this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
        this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
        this.tempRes = this._ITOMyWorkListPageService.responseTemp;
        console.log(this.reponseTemp);
        //this.addOnData = [];

        // if (this.selectedUR.updateCode == "UPD_ADDON_PRICE_NEW") {
        //   this.exist = true;
        //   this.tabIndex = 1;
        //   this.addNew = false;
        // } else if (this.selectedUR.updateCode == "UPD_ADDON_PRICE") {
        //   this.addNew = true;
        //   this.exist = false;
        //   this.tabIndex = 0
        // }


       
        // if current role is enginner this if will be exceuted
        if (this.storage.get(this.currentRole) == "F2F_EDIT") {
          this.addOnData = this._ITOMyWorkListPageService.responseTemp.savedUpdateF2fPriceList;
          this.updatedPriceList = this._ITOMyWorkListPageService.responseTemp.savedUpdateF2fPriceList;

          for (let s = 0; s < this.addOnData.length; s++) {
            for (let q = 0; q < this.updatedPriceList.length; q++) {
              if (this.addOnData[s].priceId == this.updatedPriceList[q].priceId) {
                this.addOnData[s].price = this.updatedPriceList[q].price;
              }
            }
          }
          if(this.addOnData.length>0){
            this.tableDiv= true;
          }
        }
        // if current role is Reviewer/Approver this else if will be exceuted
        else if (this.storage.get(this.currentRole) != "F2F_EDIT") {
          console.log("Entered else if");
          this.addOnData = this._ITOMyWorkListPageService.responseTemp.savedUpdateF2fPriceList;
          this.updatedPriceList = this._ITOMyWorkListPageService.responseTemp.savedUpdateF2fPriceList;

         
          //for reviewer and Approver both display data and selected data will be same
          // this.addOnData = this._ITOMyWorkListPageService.responseTemp.savedAddonList;
          // this.updatedPriceList = this._ITOMyWorkListPageService.responseTemp.savedAddonList;
          // if (this.addOnData.length > 0) {
          //  this.tableDiv = true;
          // }

          console.log(this.addOnData);
          console.log(this.updatedPriceList);
          if(this.addOnData.length>0){
            this.tableDiv= true;
          }
        }
        if (this.selectedUR.updateCode == "F2F_UPD_NEW") {
         // this.columns = this.reponseTemp.savedUpdateECPriceList;
            //this.newFrame = this.reponseTemp.savedUpdateECPriceList;
         //   console.log(this.newFrame)
            this.addNew = false;
            this.exist = true;
            this.tabIndex = 1;
        } else if (this.selectedUR.updateCode == "F2F_UPD"){
          this.addNew = true;
          this.tabIndex = 0
        }
        console.log(this.addOnData);
        for (let r = 0; r < this.reponseTemp.commentList.length; r++) {
          this.prevRemarks.push(this.reponseTemp.commentList[r]);
        }
        //setting columns for grid when prices are updated
        if(this._ITOMyWorkListPageService.selectedUR.updateCode == 'F2F_PRICE_UPD'){
          this.dropdowndefault='Main Price';
        this.cols = [
          {field: 'turbCodeNm', header: 'Turbine Type'}, 
          { field: 'frameName', header: 'Frame Name' },            
          { field: 'itemName', header: 'Item Name' },
          { field: 'maxPower', header: 'Max Power'},
          { field: 'bleedTypeName', header: 'Bleed Type' },
          { field: 'prevApproxCostFlag', header: 'Pervious Approximate Flag'},  
          { field: 'approxCostFlag', header: 'Approximate Flag' },
          { field: 'prevPrice', header: 'Previous Price' },
          { field: 'price', header: 'Price' } ,   
          { field: 'totalPrice', header: 'Total Price' } ,
        // { field: 'subContrCost', header: 'Sub Contract' } ,      
         
        ];
      }else if(this._ITOMyWorkListPageService.selectedUR.updateCode == 'F2F_COL_VAL_UPD'){
        this.dropdowndefault='AddOn Cost';
        this.cols = [
          { field: 'category', header: 'Category'},
          { field: 'itemName', header: 'Item Name' },            
          { field: 'subItemName', header: 'Sub Item Name'},
          { field: 'subItemTypName', header: 'Sub Item Type Name'},
          { field: 'colNm', header: 'LHS'},
          { field: 'colValNm', header: 'RHS Value' },
          {field: 'prevAddOnFlag', header: 'Previous Addon Flag'},
          { field: 'addOnFlg', header: 'AddOn Flag' },
          { field: 'prevAddOnCostPer' , header: 'Previous Cost Percentage'},
          {field: 'addOnCostPer', header: 'Cost Percentage'},
          { field: 'prevAddOnDirCost', header: 'Previous Direct Cost'},
          { field: 'addOnDirCost', header: 'Direct Cost' },
          { field: 'prevApproxCostFlag', header: 'Pervious Approximate Flag'},
          { field: 'approxCostFlag', header: 'Approximate Flag' },         
        ];
      }else{
        this.dropdowndefault='Frame Specific Data';
        this.cols = [
          {field: 'turbCodeNm', header: 'Turbine Type'},   
          { field: 'frameName', header:'Frame Name'},        
          { field: 'itemName', header:'Item Name'},
          { field: 'colNm', header: 'LHS' },
          { field: 'prevColValCd', header: 'previous RHS Value'},
          { field: 'colValCd', header: 'RHS Value'},
          { field: 'prevMinVal', header: 'previous Min Val'},
          { field: 'minVal', header: 'Min Val' },
          { field: 'prevMaxVal', header: 'Previous Max Val'},
          { field: 'maxVal', header: 'Max Val' },
        ];
      }
        this._ITOMyWorkListPageService.selectedUR = '';
        console.log(this.prevRemarks);
      } 
      // else {
        //setting columns for grid when prices are not updated
        // this.cols = [
        //   { field: 'frameName', header: 'Frame Name' },
        //   { field: 'maxVal', header: 'Max Power'},
        //   { field: 'bleedTypeName', header: 'Bleed Type' },
        //   { field: 'condensingType', header: 'Condensing Type' },
        //   {field: 'turbCodeNm', header: 'Turbine Type'},
        //   { field: 'itemName', header: 'Item Name' },
        //   { field: 'approxCostFlag', header: 'approxCostFlag' },
        //   { field: 'price', header: 'Price' } ,
        // ];
        // if (this.storage.get(this.currentRole) == "F2F_EDIT") {
        //   this.saveBasic.updateRequestNumber = 0;
        // }
      // }

      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;

      console.log(this.localStorageValues[this.loginUserDetails]);
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
      console.log(this.storage.get(this.currentRole));

      switch (this.storage.get(this.currentRole)) {
        case "F2F_EDIT": {
          this.F2FEdit = true;
          this.labelName = "Please Select the Reviewer";
          this.dboFormData.saveBasicDetails.loggedInUserCode = 0;
          this.dboFormData.saveBasicDetails.statusId = 1;
          // if (this._ITOMyWorkListPageService.selectedUR != '') {
            this.dboFormData.saveBasicDetails.updateRequestNumber = this.tempRes.saveBasicDetails.updateRequestNumber;;
          // } else {
          //   this.dboFormData.saveBasicDetails.updateRequestNumber = 0;
          // }
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "F2F_REVIWER") {
                this.newUsersList.push(this.usersList[r]);
              }
            }
          }console.log(this.newUsersList);
          break;
        }
        case "F2F_APPROVER": {
          console.log("Approverrr");
          this.F2FApp = true;
          this.dboFormData.saveBasicDetails.loggedInUserCode = 2;
          this.dboFormData.saveBasicDetails.updateRequestNumber = this.tempRes.saveBasicDetails.updateRequestNumber;
          break;

        }
        case "F2F_REVIWER": {
          this.F2FRev = true;
          this.labelName = "Please Select the Approver";
          this.dboFormData.saveBasicDetails.loggedInUserCode = 1;
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "F2F_APPROVER") {
                this.newUsersList.push(this.usersList[r]);
              }
            }
          }
          // if (this._ITOMyWorkListPageService.selectedUR) {
            this.dboFormData.saveBasicDetails.updateRequestNumber = this.tempRes.saveBasicDetails.updateRequestNumber;
          // }
          console.log(this.saveBasic);
          break;
        }
        default: {

          break;
        }
      }
    });
  });
  });
  }

  ngOnInit() {
      this.cols = [
        {field: 'turbCodeNm', header: 'Turbine Type'},
        { field: 'frameName', header: 'Frame Name' },
        { field: 'bleedTypeName', header: 'Bleed Type' },
        { field: 'condensingType', header: 'Condensing Type' },
        { field: 'itemName', header: 'Item Name' },
        { field: 'approxCostFlag', header: 'Approximate Flag' },
        { field: 'price', header: 'Price' } ,       
      ];  
  }

  // ngAfterViewChecked() {
  //   if (this.storage.get(this.currentRole) === "F2F_EDIT") {
  //     if(this.dropdowndefault=="Main Price"){
  //     for (let s = 0; s < this.mainList.length; s++) {
  //       for (let q = 0; q < this.updatedPriceList.length; q++) {
  //         if (this.mainList[s].priceId == this.updatedPriceList[q].priceId) {
  //           let butn = document.getElementById(this.mainList[s].priceId).style.backgroundColor = "#0275d8";
  //         }
  //       }
  //     }
  //     }
  //     if(this.dropdowndefault=="AddOn Cost"){
  //       for (let s = 0; s < this.mainList.length; s++) {
  //         for (let q = 0; q < this.updatedPriceList.length; q++) {
  //           if (this.mainList[s].colValId == this.updatedPriceList[q].colValId) {
  //             let butn = document.getElementById(this.mainList[s].colValId).style.backgroundColor = "#0275d8";
  //           }
  //         }
  //       }
  //       }
  //       if(this.dropdowndefault=="Frame Specific Data"){
  //         for (let s = 0; s < this.mainList.length; s++) {
  //           for (let q = 0; q < this.updatedPriceList.length; q++) {
  //             if (this.mainList[s].id == this.updatedPriceList[q].id) {
  //               let butn = document.getElementById(this.mainList[s].id).style.backgroundColor = "#0275d8";
  //             }
  //           }
  //         }
  //         }
  //   }
  // }

  optionSel3(value){
    console.log(value);
    let temp=null;
    this.subTempList=[];
    this.subItemList=[];
    this.addOnData=[];
    for(let j=0;j<this.mainList.length;j++)
    {
      if(value==this.mainList[j].itemName)
      {
  temp=this.mainList[j];
  this.itemId=this.mainList[j].itemId;
      }
    }
    if(temp.subItemId!=0)
    {
      for(let j=0;j<this.mainList.length;j++)
      {
        if(temp.itemId==this.mainList[j].itemId)
        {
        this.subTempList.push(this.mainList[j]);
        }
      }   
    }
    this.subItemList =  this.subTempList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.subItemId === current.subItemId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    if(this.subItemList.length>0){
    this.subItemDefault=this.subItemList[0].subItemId;
    this.subItemId=this.subItemList[0].subItemId;
    this.itemId=this.subItemList[0].itemId;
    }
    console.log(this.subItemList);
  }

  optionSel4(value2){
    console.log(value2);
    let temp=null;
    this.addOnData=[];
    for(let j=0;j<this.mainList.length;j++)
    {
      if(value2==this.mainList[j].subItemName && this.itemId==this.mainList[j].itemId)
      {
  temp=this.mainList[j];
      }
    }
    if(temp.subItemTypeId!=0){
      for(let g=0;g<this.mainList.length;g++){
        if( temp.itemId==this.mainList[g].itemId && temp.subItemName == this.mainList[g].subItemName ){
          this.subItemTypTemp.push(this.mainList[g]);
        }
      }
    }
    this.subItemTypeList =  this.subItemTypTemp.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.subItemTypeId === current.subItemTypeId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    this.subItemId= this.subItemTypeList[0].subItemId;
    this.itemId= this.subItemTypeList[0].itemId;
    this.subItemTypeId= this.subItemTypeList[0].subItemTypeId;
  }
  optionSel5(Value5){{
    this.subItemTypeDefault=Value5;
    for(let g=0;g<this.subItemTypeList.length;g++){
      if(Value5==this.subItemTypeList[g].subItemTypeName){
        this.subItemTypeId=this.subItemTypeList[g].subItemTypeId;
      }
    }
  }

  }
  optionSel6(value)
  {
    this.tableDiv=false;
    this.mainList=[];
    this.panels=[];
    this.subItemList =[];
    this.subItemTypeList =[];
    this.itemId=0;
    this.subItemTypeId=0;
    this.subItemId = 0;
    this.defaultvaluepanel='';
    this.subItemTypeDefault='';
    this.subItemDefault='';
    this.dropdowndefault = value;
    this.selectedData=[];
    this.updatedPriceList=[];
    if(this.dropdowndefault=='Main Price'){
      this.mainList=this.updGetF2fData.priceList;
      this.addOnData = this.updGetF2fData.priceList;
    }else if(this.dropdowndefault == 'AddOn Cost'){
      this.mainList = this.updGetF2fData.addonPriceList;
      this.addOnData = this.updGetF2fData.addonPriceList;
    }else{
      this.mainList = this.updGetF2fData.frameOilList;
      this.addOnData = this.updGetF2fData.frameOilList;
    }
   
    if(this.dropdowndefault == 'Main Price'){
      this.cols = [
        {field: 'turbCodeNm', header: 'Turbine Type'}, 
        { field: 'frameName', header: 'Frame Name' },         
        { field: 'maxPower', header: 'Max Power'},
        { field: 'bleedTypeName', header: 'Bleed Type' },
        { field: 'itemName', header: 'Item Name' },
        { field: 'approxCostFlag', header: 'Approximate Flag' },
        { field: 'price', header: 'Price' } ,   
        { field: 'totalPrice', header: 'Total Price' } ,     
        // { field: 'shopConvCost', header: 'Shop Conversion' } ,
        // { field: 'subContrCost', header: 'Sub Contract' } ,       

      ];
    }else if(this.dropdowndefault=='AddOn Cost'){
      this.cols = [
        {field: 'category', header: 'Category'},
        { field: 'itemName', header: 'Item Name' },
          { field: 'subItemName', header: 'Sub Item Name'},
          { field: 'subItemTypName', header: 'Sub Item Type Name'},
          { field: 'colNm', header: 'LHS'},
          { field: 'colValNm', header: 'RHS Value' },
          { field: 'addOnFlg', header: 'AddOn Flag' },
          {field: 'addOnCostPer', header: 'Cost Percentage'},
          { field: 'addOnDirCost', header: 'Direct Cost' },
          { field: 'approxCostFlag', header: 'Approximate Flag' },    
      ];
    }else{
      this.cols = [
        {field: 'turbCodeNm', header: 'Turbine Type'}, 
      { field: 'frameName', header:'Frame Name'},      
      { field: 'itemName', header:'Item Name'},
      { field: 'colNm', header: 'LHS' },
      { field: 'colValCd', header: 'RHS Value'},
      { field: 'minVal', header: 'Min Val' },
      { field: 'maxVal', header: 'Max Val' },       
      ];
    }
  }

  getTable(){
    if(this.addOnData.length>0){
      this.tableDiv=true;
    }else{
      this.tableDiv= false;
    }
    // this.addOnDataTemp=[];
    // this.addOnData=[];
    //     console.log(this.itemId, this.subItemId, this.subItemTypeId);
    // if(this.itemId!=0 && this.subItemId==0 && this.subItemTypeId==0){
    // for(let p=0;p<this.mainList.length; p++){
    //   if(this.itemId==this.mainList[p].itemId && this.mainList[p].subItemId==0 && this.mainList[p].subItemTypeId ==0){
    //     this.addOnDataTemp.push(this.mainList[p]);
    //   }
    // }
    // }else if(this.itemId!=0 && this.subItemId!=0 && this.subItemTypeId==0){
    //   for(let p=0;p<this.mainList.length; p++){
    //     if(this.itemId==this.mainList[p].itemId && this.subItemId==this.mainList[p].subItemId && this.mainList[p].subItemTypeId ==0){
    //       this.addOnDataTemp.push(this.mainList[p]);
    //     }
    //   }
    // }else if(this.itemId!=0 && this.subItemId!=0 && this.subItemTypeId!=0){
    //   for(let p=0;p<this.mainList.length; p++){
    //     if(this.itemId==this.mainList[p].itemId && this.subItemId==this.mainList[p].subItemId && this.subItemTypeId==this.mainList[p].subItemTypeId){
    //       this.addOnDataTemp.push(this.mainList[p]);
    //     }
    //   }
    // }
  }


// Edit row functionality
onRowSelect(event) {
 console.log(event.data);
 this.selectedData = event.data;
 this.displayDialog = true;
 console.log(this.selectedData.condensingTypeId)
 if (this.selectedData.condensingTypeId == 0) {
   this.dispCondTyp = false;
 }
 else {
   this.dispCondTyp = true;
 }

}

  savePrice(rowData) {
    //  this.updatedPriceList=[];
    this.selectedData = rowData;
    console.log(this.selectedData);
    if (this.updatedPriceList.length != 0) {
      for (let s = 0; s < this.updatedPriceList.length; s++) {
        if (this.updatedPriceList[s].priceId == this.selectedData.priceId) {
          this.updatedPriceList[s] = this.selectedData;
          this.contains = true;
          let butn = document.getElementById(this.updatedPriceList[s].priceId).style.backgroundColor = "#0275d8";
          break;
        }
      }
      if (!this.contains) {
        this.updatedPriceList.push(this.selectedData);
        for (let s = 0; s < this.updatedPriceList.length; s++) {
          let butn = document.getElementById(this.updatedPriceList[s].priceId).style.backgroundColor = "#0275d8";
        }
      }
      else {
        this.contains = false;
      }
    }
    else {
      this.updatedPriceList.push(this.selectedData);
      for (let s = 0; s < this.updatedPriceList.length; s++) {
        let butn = document.getElementById(this.updatedPriceList[s].priceId).style.backgroundColor = "#0275d8";
      }
    }
    console.log(this.updatedPriceList);
  }

  save() {
    console.log(this.selectedData);
    let temp=0;
    this.contains=false;
    if(this.dropdowndefault=="Frame Specific Data"){
for (let d = 0; d < this.addOnData.length; d++) {
  if (this.addOnData[d].id == this.selectedData.id) {
    temp = this.addOnData[d];
   }
}
console.log(temp);
console.log(this.selectedData.price);

if (this.updatedPriceList.length != 0) {
  for(let j=0;j<this.updatedPriceList.length;j++){
if(this.updatedPriceList[j].id==this.selectedData.id){
  this.updatedPriceList[j]=this.selectedData;
  this.contains = true;
}
}
if(this.contains==false){
  this.updatedPriceList.push(this.selectedData);
}
}
else{
  this.updatedPriceList.push(this.selectedData)
}
    }else if(this.dropdowndefault=="AddOn Cost"){
      for (let d = 0; d < this.addOnData.length; d++) {
        if (this.addOnData[d].colValId == this.selectedData.colValId) {
          temp = this.addOnData[d];
         }
      }
      console.log(temp);
      console.log(this.selectedData.price);
      
      if (this.updatedPriceList.length != 0) {
        for(let j=0;j<this.updatedPriceList.length;j++){
      if(this.updatedPriceList[j].colValId==this.selectedData.colValId){
        this.updatedPriceList[j]=this.selectedData;
        this.contains = true;
      }
      }
      if(this.contains==false){
        this.updatedPriceList.push(this.selectedData);
      }
      }else{
        this.updatedPriceList.push(this.selectedData)
      }
      
    }else{
      for (let d = 0; d < this.addOnData.length; d++) {
        if (this.addOnData[d].priceId == this.selectedData.priceId) {
          temp = this.addOnData[d];
         }
      }
      console.log(temp);
      console.log(this.selectedData.price);
      
      if (this.updatedPriceList.length != 0) {
        for(let j=0;j<this.updatedPriceList.length;j++){
      if(this.updatedPriceList[j].priceId==this.selectedData.priceId){
        this.updatedPriceList[j]=this.selectedData;
        this.contains = true;
      }
      }
      if(this.contains==false){
        this.updatedPriceList.push(this.selectedData);
      }
      }
      else{
        this.updatedPriceList.push(this.selectedData)
      }
      
    }
this.displayDialog=false;




// if(this.dropdowndefault=="Main-Price"){
//     if (this.updatedPriceList.length != 0) {
//       for (let s = 0; s < this.updatedPriceList.length; s++) {
//         if (this.updatedPriceList[s].priceId === this.selectedData.priceId) {
//           this.updatedPriceList[s] = this.selectedData;
//           this.contains = true;
//           let butn = document.getElementById(this.updatedPriceList[s].priceId).style.backgroundColor = "#0275d8";
//           break;
//         }
//       }
//       if (!this.contains) {
//         this.updatedPriceList.push(this.selectedData);
//         for (let s = 0; s < this.updatedPriceList.length; s++) {
//           if (this.updatedPriceList[s].priceId === this.selectedData.priceId) {
//             let butn = document.getElementById(this.updatedPriceList[s].priceId).style.backgroundColor = "#0275d8";
//           }
//         }
//       }
//       else {
//         this.contains = false;
//       }
//     }
//     else {
//       this.updatedPriceList.push(this.selectedData);

//       for (let s = 0; s < this.updatedPriceList.length; s++) {
//         if (this.updatedPriceList[s].priceId === this.selectedData.priceId) {
//           let butn = document.getElementById(this.updatedPriceList[s].priceId).style.backgroundColor = "#0275d8";
//         }
//       }
//     }

//     //let butn = document.getElementById(this.selectedData.addOnCompoId).style.backgroundColor = "#0275d8";
//     this.displayDialog = false;
//     console.log(this.updatedPriceList);
//   }else if(this.dropdowndefault=="AddOn Cost"){
//     if (this.updatedPriceList.length != 0) {
//       for (let s = 0; s < this.updatedPriceList.length; s++) {
//         if (this.updatedPriceList[s].colValId === this.selectedData.colValId) {
//           this.updatedPriceList[s] = this.selectedData;
//           this.contains = true;
//           let butn = document.getElementById(this.updatedPriceList[s].colValId).style.backgroundColor = "#0275d8";
//           break;
//         }
//       }
//       if (!this.contains) {
//         this.updatedPriceList.push(this.selectedData);
//         for (let s = 0; s < this.updatedPriceList.length; s++) {
//           if (this.updatedPriceList[s].colValId === this.selectedData.colValId) {
//             let butn = document.getElementById(this.updatedPriceList[s].colValId).style.backgroundColor = "#0275d8";
//           }
//         }
//       }
//       else {
//         this.contains = false;
//       }
//     }
//     else {
//       this.updatedPriceList.push(this.selectedData);

//       for (let s = 0; s < this.updatedPriceList.length; s++) {
//         if (this.updatedPriceList[s].id === this.selectedData.id) {
//           let butn = document.getElementById(this.updatedPriceList[s].id).style.backgroundColor = "#0275d8";
//         }
//       }
//     }

//     //let butn = document.getElementById(this.selectedData.addOnCompoId).style.backgroundColor = "#0275d8";
//     this.displayDialog = false;
//     console.log(this.updatedPriceList);
//   }else{
//     if (this.updatedPriceList.length != 0) {
//       for (let s = 0; s < this.updatedPriceList.length; s++) {
//         if (this.updatedPriceList[s].id === this.selectedData.id) {
//           this.updatedPriceList[s] = this.selectedData;
//           this.contains = true;
//           let butn = document.getElementById(this.updatedPriceList[s].id).style.backgroundColor = "#0275d8";
//           break;
//         }
//       }
//       if (!this.contains) {
//         this.updatedPriceList.push(this.selectedData);
//         for (let s = 0; s < this.updatedPriceList.length; s++) {
//           if (this.updatedPriceList[s].id === this.selectedData.id) {
//             let butn = document.getElementById(this.updatedPriceList[s].id).style.backgroundColor = "#0275d8";
//           }
//         }
//       }
//       else {
//         this.contains = false;
//       }
//     }
//     else {
//       this.updatedPriceList.push(this.selectedData);

//       for (let s = 0; s < this.updatedPriceList.length; s++) {
//         if (this.updatedPriceList[s].id === this.selectedData.id) {
//           let butn = document.getElementById(this.updatedPriceList[s].id).style.backgroundColor = "#0275d8";
//         }
//       }
//     }

//     //let butn = document.getElementById(this.selectedData.addOnCompoId).style.backgroundColor = "#0275d8";
//     this.displayDialog = false;
//     console.log(this.updatedPriceList);
//   }
  }

  SaveAsDraft(uBOForm, form: NgForm) {
    console.log(uBOForm);
    this.uboForm = form;
    this.dboFormData.saveBasicDetails.updatePriceF2fColVal=[];
    this.dboFormData.saveBasicDetails.updateF2fPrice=[];
    console.log(this.updatedPriceList);
    this.dboFormData.saveBasicDetails.modifiedById = this.modifiedBy;
    this.dboFormData.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
    this.dboFormData.saveBasicDetails.remarks = uBOForm.coments;

    if(this.storage.get(this.currentRole)=="F2F_EDIT")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=0;
  this.dboFormData.saveBasicDetails.statusId = 1;

}
else if(this.storage.get(this.currentRole)=="F2F_REVIWER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=1;
  if (uBOForm.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (uBOForm.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}
else if(this.storage.get(this.currentRole)=="F2F_APPROVER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=2;
  if (uBOForm.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (uBOForm.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}

    // if (uBOForm.status == "Accept") {
    //   this.saveBasic.statusId = 1;
    // }
    // else if (uBOForm.status == "Reject") {
    //   this.saveBasic.statusId = 0;
    // }
    // this.tempRes.saveBasicDetails = this.saveBasic;
    // this.tempRes.saveBasicDetails.condensingTypeId = this.condId;
    //previousCode
    //this.tempRes.createUpdateAddonList = this.updatedPriceList;
    
      for (let v = 0; v < this.updatedPriceList.length; v++) {
        if (this.updatedPriceList[v] != undefined) {
               this.dboClass = new dboClass();
               if(this.dropdowndefault=="Main Price"){
                this.dboClass.updateCode="F2F_PRICE_UPD";
                this.dboClass.priceId=this.updatedPriceList[v].priceId;
                this.dboClass.itemId=this.updatedPriceList[v].itemId;
                this.dboClass.subItemId=this.updatedPriceList[v].subItemId;
                this.dboClass.subItemTypeId=this.updatedPriceList[v].subItemTypeId;
                this.dboClass.framePowerId=this.updatedPriceList[v].framePowerId;
                this.dboClass.bleedTypeId=this.updatedPriceList[v].bleedTypeId;
                //this.dboClass.condTypeId=this.updatedPriceList[v].condTypeId;
                //this.dboClass.custType=this.updatedPriceList[v].custType;
                //this.dboClass.priceCode=this.updatedPriceList[v].priceCode;
                this.dboClass.price=this.updatedPriceList[v].price;
                this.dboClass.approxCostFlag=this.updatedPriceList[v].approxCostFlag;
                //this.dboClass.totalPrice=this.updatedPriceList[v].totalPrice;
                //this.dboClass.defaultFlagNew=this.updatedPriceList[v].defaultFlagNew;                
                this.dboClass.activeNew=this.updatedPriceList[v].activeNew;
                this.dboFormData.updateF2fPrice.push(this.dboClass);
    }else if(this.dropdowndefault=="AddOn Cost"){      
      this.dboClass.updateCode="F2F_COL_VAL_UPD";
      this.dboClass.colValId=this.updatedPriceList[v].colValId;
      this.dboClass.category=this.updatedPriceList[v].category;
      this.dboClass.itemId=this.updatedPriceList[v].itemId;
      this.dboClass.subItemId=this.updatedPriceList[v].subItemId;
      this.dboClass.subItemTypeId=this.updatedPriceList[v].subItemTypeId;
      this.dboClass.colId=this.updatedPriceList[v].colId;
      this.dboClass.colValCd=this.updatedPriceList[v].colValCd;
      this.dboClass.colValNm=this.updatedPriceList[v].colValNm;
      this.dboClass.calculateLinkFlag=this.updatedPriceList[v].calculateLinkFlag;
      this.dboClass.defaultFlagNew =this.updatedPriceList[v].defaultFlagNew;
      this.dboClass.dispInd=this.updatedPriceList[v].dispInd;
      this.dboClass.orderId=this.updatedPriceList[v].orderId;
      this.dboClass.addOnFlg=this.updatedPriceList[v].addOnFlg;
      this.dboClass.addOnCostPer=this.updatedPriceList[v].addOnCostPer;
      this.dboClass.addOnDirCost=this.updatedPriceList[v].addOnDirCost;
      this.dboClass.approxCostFlag=this.updatedPriceList[v].approxCostFlag;
      this.dboClass.techFlag=this.updatedPriceList[v].techFlag;
      this.dboClass.comrFlag=this.updatedPriceList[v].comrFlag;
      this.dboClass.activeNew=this.updatedPriceList[v].activeNew;
      this.dboFormData.updatePriceF2fColVal.push(this.dboClass);
    }else{
      this.dboClass.updateCode="F2F_FRM_SPEC_DATA_UPD";
      this.dboClass.id=this.updatedPriceList[v].id;
      this.dboClass.frameId=this.updatedPriceList[v].frameId;
      this.dboClass.itemId=this.updatedPriceList[v].itemId;              
      this.dboClass.subItemId=this.updatedPriceList[v].subItemId;
      this.dboClass.subItemTypeId=this.updatedPriceList[v].subItemTypeId;
      this.dboClass.colId=this.updatedPriceList[v].colId;
      this.dboClass.colValCd=this.updatedPriceList[v].colValCd;
      this.dboClass.minVal=this.updatedPriceList[v].minVal;
      this.dboClass.maxVal=this.updatedPriceList[v].maxVal;
      this.dboClass.activeNew=this.updatedPriceList[v].activeNew;
this.dboFormData.updateF2fFrameSpecData.push(this.dboClass);

    }
  }
    }
    
    // for (let q = 0; q < this.updatedPriceList.length; q++) {
    //   if (this.updatedPriceList[q].f2F_DET_ID == 0) {
    //     this.saveBasic.updateCode = "UPD_ADDON_NEW";
    //   } else {
    //     this.saveBasic.updateCode = "UPD_ADDON";
    //   }
    // }  
    console.log(this.dboFormData);
    // if (this.isDataSaved) {
    //   this.updateStatusAndSave(this.savedReqQuotForm);
    // } else {
      if(this.dropdowndefault=='Main Price'){ 

    this._ItoUpdateCostF2FAddOnsService.getUpdateCreateF2fPrice(this.dboFormData).subscribe(res => {
      console.log(res);
      this.successMsg.push(res.successMsg);  
      this.message = true;
    });
    }else if(this.dropdowndefault=='AddOn Cost'){
      this._ItoUpdateCostF2FAddOnsService.getUpdateCreateF2fColVal(this.dboFormData).subscribe(res => {
        console.log(res);
        this.successMsg.push(res.successMsg);     
        this.message = true; 
      });
    }else{
      this._ItoUpdateCostF2FAddOnsService.getUpdateCreateF2fFrameSpecData(this.dboFormData).subscribe(res => {
        console.log(res);
        this.successMsg.push(res.successMsg);
        this.message = true;
      });
    }
  // }
  }

  assignedUser(assigne) {
    console.log(assigne);
    this.dboFormData.saveBasicDetails.assignedTo = assigne;
  
  }

  updateAddOnPrice(uBOForm, form: NgForm) {
    console.log(uBOForm);
    this.uboForm = form;
    this.dboFormData.saveBasicDetails.updatePriceF2fColVal=[];
    this.dboFormData.saveBasicDetails.updateF2fPrice=[];
    console.log(this.updatedPriceList);
    this.dboFormData.saveBasicDetails.modifiedById = this.modifiedBy;
    this.dboFormData.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
    this.dboFormData.saveBasicDetails.remarks = uBOForm.coments;

    if(this.storage.get(this.currentRole)=="F2F_EDIT")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=0;
  this.dboFormData.saveBasicDetails.statusId = 1;

}
else if(this.storage.get(this.currentRole)=="F2F_REVIWER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=1;
  if (uBOForm.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (uBOForm.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}
else if(this.storage.get(this.currentRole)=="F2F_APPROVER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=2;
  if (uBOForm.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (uBOForm.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}

    // if (uBOForm.status == "Accept") {
    //   this.saveBasic.statusId = 1;
    // }
    // else if (uBOForm.status == "Reject") {
    //   this.saveBasic.statusId = 0;
    // }
    // this.tempRes.saveBasicDetails = this.saveBasic;
    // this.tempRes.saveBasicDetails.condensingTypeId = this.condId;
    //previousCode
    //this.tempRes.createUpdateAddonList = this.updatedPriceList;
    
      for (let v = 0; v < this.updatedPriceList.length; v++) {
        if (this.updatedPriceList[v] != undefined) {
               this.dboClass = new dboClass();
               if(this.dropdowndefault=="Main Price"){
                this.dboClass.updateCode="F2F_PRICE_UPD";
                this.dboClass.priceId=this.updatedPriceList[v].priceId;
                this.dboClass.itemId=this.updatedPriceList[v].itemId;
                this.dboClass.subItemId=this.updatedPriceList[v].subItemId;
                this.dboClass.subItemTypeId=this.updatedPriceList[v].subItemTypeId;
                this.dboClass.framePowerId=this.updatedPriceList[v].framePowerId;
                this.dboClass.bleedTypeId=this.updatedPriceList[v].bleedTypeId;
                //this.dboClass.condTypeId=this.updatedPriceList[v].condTypeId;
                //this.dboClass.custType=this.updatedPriceList[v].custType;
                //this.dboClass.priceCode=this.updatedPriceList[v].priceCode;
                this.dboClass.price=this.updatedPriceList[v].price;
                //this.dboClass.totalPrice=this.updatedPriceList[v].totalPrice;
                //this.dboClass.defaultFlagNew=this.updatedPriceList[v].defaultFlagNew;
                this.dboClass.approxCostFlag=this.updatedPriceList[v].approxCostFlag;
                this.dboClass.activeNew=this.updatedPriceList[v].activeNew;
                this.dboFormData.updateF2fPrice.push(this.dboClass);
    }else if(this.dropdowndefault=="AddOn Cost"){      
      this.dboClass.updateCode="F2F_COL_VAL_UPD";
      this.dboClass.colValId=this.updatedPriceList[v].colValId;
      this.dboClass.category=this.updatedPriceList[v].category;
      this.dboClass.itemId=this.updatedPriceList[v].itemId;
      this.dboClass.subItemId=this.updatedPriceList[v].subItemId;
      this.dboClass.subItemTypeId=this.updatedPriceList[v].subItemTypeId;
      this.dboClass.colId=this.updatedPriceList[v].colId;
      this.dboClass.colValCd=this.updatedPriceList[v].colValCd;
      this.dboClass.colValNm=this.updatedPriceList[v].colValNm;
      this.dboClass.calculateLinkFlag=this.updatedPriceList[v].calculateLinkFlag;
      this.dboClass.defaultFlagNew =this.updatedPriceList[v].defaultFlagNew;
      this.dboClass.dispInd=this.updatedPriceList[v].dispInd;
      this.dboClass.orderId=this.updatedPriceList[v].orderId;
      this.dboClass.addOnFlg=this.updatedPriceList[v].addOnFlg;
      this.dboClass.addOnCostPer=this.updatedPriceList[v].addOnCostPer;
      this.dboClass.addOnDirCost=this.updatedPriceList[v].addOnDirCost;
      this.dboClass.approxCostFlag=this.updatedPriceList[v].approxCostFlag;
      this.dboClass.techFlag=this.updatedPriceList[v].techFlag;
      this.dboClass.comrFlag=this.updatedPriceList[v].comrFlag;
      this.dboClass.activeNew=this.updatedPriceList[v].activeNew;
      this.dboFormData.updatePriceF2fColVal.push(this.dboClass);
    }else{
      this.dboClass.updateCode="F2F_FRM_SPEC_DATA_UPD";
      this.dboClass.id=this.updatedPriceList[v].id;
      this.dboClass.frameId=this.updatedPriceList[v].frameId;
      this.dboClass.itemId=this.updatedPriceList[v].itemId;              
      this.dboClass.subItemId=this.updatedPriceList[v].subItemId;
      this.dboClass.subItemTypeId=this.updatedPriceList[v].subItemTypeId;
      this.dboClass.colId=this.updatedPriceList[v].colId;
      this.dboClass.colValCd=this.updatedPriceList[v].colValCd;
      this.dboClass.minVal=this.updatedPriceList[v].minVal;
      this.dboClass.maxVal=this.updatedPriceList[v].maxVal;
      this.dboClass.activeNew=this.updatedPriceList[v].activeNew;
this.dboFormData.updateF2fFrameSpecData.push(this.dboClass);

    }
  }
    }
    
    // for (let q = 0; q < this.updatedPriceList.length; q++) {
    //   if (this.updatedPriceList[q].f2F_DET_ID == 0) {
    //     this.saveBasic.updateCode = "UPD_ADDON_NEW";
    //   } else {
    //     this.saveBasic.updateCode = "UPD_ADDON";
    //   }
    // }  
    console.log(this.dboFormData);
    // if (this.isDataSaved) {
    //   this.updateStatusAndSave(this.savedReqQuotForm);
    // } else {
      if(this.dropdowndefault=='Main Price'){ 

        this._ItoUpdateCostF2FAddOnsService.getUpdateCreateF2fPrice(this.dboFormData).subscribe(resp => {
          console.log(resp);
          this.message = true;
          this.successMsg.push(resp.successMsg);
          if (this.storage.get(this.currentRole) == "F2F_APPROVER") {
            resp.saveBasicDetails.assignedTo = this.modifiedBy;
            this._ItoUpdateCostF2FAddOnsService.updateStatus(resp).subscribe(respon => {
              console.log(respon);
              this._ItoUpdateCostF2FAddOnsService.saveData(respon).subscribe(respo => {
                this.message = true;
                this.successMsg.push(respo.successMsg);
              });
            });
          } else {
            this._ItoUpdateCostF2FAddOnsService.updateStatus(resp).subscribe(respon => {
              console.log(respon);
              this.message = true;
              this.successMsg.push(respon.successMsg);
            })
          }    
        });
    }else if(this.dropdowndefault=='AddOn Cost'){
      this._ItoUpdateCostF2FAddOnsService.getUpdateCreateF2fColVal(this.dboFormData).subscribe(res => {
        console.log(res);
        this.successMsg.push(res.successMsg);
        this._ItoUpdateCostF2FAddOnsService.updateStatus(res).subscribe(respon => {
          console.log(respon);
          this.message=true;
          this.successMsg.push(respon.successMsg);
          if(this.storage.get(this.currentRole)=="F2F_APPROVER")     {
            //  this._ItoUpdateCostDBOElecPrice.saveUpdatedPackagePrice(res).subscribe(respon => {
            // });
            this._ItoUpdateCostF2FAddOnsService.saveData(respon).subscribe(respo => {
              console.log(respo);
              this.message = true;
              this.successMsg.push(respo.successMsg);  
            });    
          }
        });
      });
    }else{
      this._ItoUpdateCostF2FAddOnsService.getUpdateCreateF2fFrameSpecData(this.dboFormData).subscribe(res => {
        console.log(res);
        this.successMsg.push(res.successMsg);
        this._ItoUpdateCostF2FAddOnsService.updateStatus(res).subscribe(respon => {
          console.log(respon);
          this.message=true;
          this.successMsg.push(respon.successMsg);
          if(this.storage.get(this.currentRole)=="F2F_APPROVER")     {
            //  this._ItoUpdateCostDBOElecPrice.saveUpdatedPackagePrice(res).subscribe(respon => {
            // });
            this._ItoUpdateCostF2FAddOnsService.saveData(respon).subscribe(respo => {
              console.log(respo);
              this.message = true;
              this.successMsg.push(respo.successMsg);  
            });    
          }
        });
      });
    }
  // }
  }

  updateStatusAndSave(resp) {

    if ((this.storage.get(this.currentRole) === "F2F_APPROVER") || this.storage.get(this.currentRole) === "F2F_REVIWER") {
      resp.saveBasicDetails.updateRequestNumber=this.saveBasic.updateRequestNumber;
    }

    if (this.storage.get(this.currentRole) === "F2F_APPROVER") {
      resp.saveBasicDetails.assignedTo = this.modifiedBy;
      this._ItoUpdateCostF2FAddOnsService.updateStatus(resp).subscribe(respon => {
        console.log(respon);
        this._ItoUpdateCostF2FAddOnsService.saveData(respon).subscribe(respo => {
          console.log(respo);
          this.message = true;
          this.successMsg.push(respo.successMsg);

        });
      });
    } else {
      this._ItoUpdateCostF2FAddOnsService.updateStatus(resp).subscribe(respon => {
        console.log(respon);
        this.message = true;
        this.successMsg.push(respon.successMsg);

      });
    }

    this.isDataSaved = false;
  }




  handleChange(event) {
    var index = event.index;
    console.log(index)
    this.turbineType = '';
    this.turbineDesign = '';
    this.bleedTypID = 0;
    this.framePwrID = 0;
  }

  statusSel(val) {
    if (val == "Accept")
      this.enableStatus = true;
    else if (val == "Reject")
      this.enableStatus = false;
  }

  closeMessage() {

    this.message = false;
    this.successMsg = [];    
    this.updatedPriceList = [];
    this.selectedData = '';
    this.uboForm.reset();
    if (!this.isDataSaved) {
      this.navigateToWorkFlow();
    }
  }
  navigateToWorkFlow() {
    this.router.navigate(['MyWorkFlow']);
  }
  displayMessage(){
    this.DispMessage = false;
    this.navigateToWorkFlow();
  }
  saveBulk()
  {
    this._ItoUpdateTransportationService.getAdminForm().subscribe(res => {
      console.log(res);
      this.adminForm = res;
      this.adminForm.percent=this.percentage;
      this.adminForm.subContrPercent = 0;
      this.adminForm.shopConvPercent = 0;
      // this.adminForm.shopConvPercent = this.shopConvPercent;
      // this.adminForm.subContrPercent = this.subContrPercent;
      // this.adminForm.shopConvPercent = this.shopConvPercent;
      this.adminForm.modifiedBy=this.modifiedBy;
    
    this._ItoUpdateCostF2FAddOnsService.getAdminPercentF2F(this.adminForm).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.displayDialogBulk=false;
      this.DispMessage = true;
    });
  });
  }
  bulkUpd()
  {
    this.percentage=0;
    this.displayDialogBulk=true;

  }
}


