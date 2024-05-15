import { Component, OnInit } from '@angular/core';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { delay } from 'rxjs/operator/delay';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ItoUpdateCostDBOMechPrice } from './update-cost-dbomech-price.service';
import { DBOMechPrice } from './update-cost-dbomech-price';
import { ItoUpdateCostF2FAddOnsService } from '../ito-update-f2f-addon/ito-update-f2f-addon.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { dboClass } from '../ito-upd-dbo-ele-col/ito-upd-dbo-ele-col';
import { ItoUpdateTransportationService } from '../ito-update-price-transport/ito-update-price-transport.service';

@Component({
  selector: 'app-update-cost-dbomech-price',
  templateUrl: './update-cost-dbomech-price.component.html',
  styleUrls: ['./update-cost-dbomech-price.component.css']
})
export class UpdateCostDbomechPriceComponent implements OnInit {

  tableName: string = '';
  adminForm:any;/// to send bulk data
  displayDialogBulk:boolean=false;
  percentage:number=0;
  typpanel:string='';
  dboClass:any;
  dboFormData:any;
  mainList: Array<any> =[];
  cols: { field: string; header: string; }[];
  custTypeArray: { field: string; header: string; }[];
  //Mech AUx COlVal is colValId is unique ,
  typeofpanel:Array<any>=['Price','Auxilaries Price' , 'AddOn Cost', 'Auxilaries AddOn', 'Auxilaries Over Head', 'Auxilaries Initial Fill'];
  defaultvaluetype:string="Price";
  turbTypeArray: Array<any> = [];
  finalRangeList: Array<any> = [];
  localStorageValues: Array<any> = [];
  userRoles: Array<any> = [];
  newUsersLilst: Array<any> = [];
  prevRemarks: Array<any> = [];
  usersList: Array<any> = [];
  data: Array<any> = [];
  successMsg: Array<any> = [];
  valueList: Array<any> = [];
  labelList: Array<any> = [];
  dBOMechanicalListArray: Array<any> = [];
  dBOMechanicalListArrayNew: Array<any> = [];
  newlyAddedData: Array<any> = [];

  currentRole: string = 'selRole';
  user: string = 'userDetail';
  currentRoleId: string = 'selRoleId';
  loginUserDetails: string = "userDetail";
  labelName: string = '';
  custCode: string = '';
  turbType: string = '';
  itemName: string;
  panelCode: string;
  panelCdNew: string;
  itemNameNew: string;

  userId: any;
  assignee: any;
  saveBasic: any;
  tempResp: any;
  selectedRange: any;
  modifiedBy: any;
  selectedUR: any;
  reponseTemp: any;
  updateType: any;
  remarkss: any;
  DBOData: any;
  tempRes: any;
  tempres: any;
  tempRes1: any;
  itemIdNew: any;
  questionsBean: any;
  previousPrice: any;
  columns:any;
  custType: any = '';
  itemId: any = '';

  assignDisp: boolean;
  dboMechEdit: boolean = false;
  dboMechApp: boolean = false;
  dboMechRev: boolean = false;
  displayMessage: boolean = false;
  contains: boolean = false;
  displayDialog: boolean = false;
  tableDiv: boolean = false;
  exist: boolean = false;           // to check whether edit screen data is present while navigating from Myworkflow screen
  dispDropdown: boolean = true;     // to check whether dropdowns should be present while navigating from Myworkflow screen
  addNew: boolean = false;
  tabIndex: number = 0;

  //constructor to fetch data on load
  constructor(private _ItoUpdateCostDBOMechPrice: ItoUpdateCostDBOMechPrice, private _ITOLoginService: ITOLoginService, private _ITOturbineConfigService: ITOturbineConfigService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ItoUpdateCostF2FAddOnsService: ItoUpdateCostF2FAddOnsService,
    private _ITOMyWorkListPageService: ITOMyWorkListPageService, private _ItoUpdateTransportationService: ItoUpdateTransportationService,
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

      this._ITOturbineConfigService.getDboFormData().subscribe(resss => {
        console.log(resss);
        this.dboFormData =  resss;
      
      console.log(this._ITOMyWorkListPageService.selectedUR);

      if (this._ITOMyWorkListPageService.selectedUR!='') {  // this will have the selected updatecode
        console.log(this._ITOMyWorkListPageService.responseTemp);
        this.dispDropdown = false;
        this.selectedUR = this._ITOMyWorkListPageService.selectedUR;
        this.reponseTemp = this._ITOMyWorkListPageService.responseTemp;
        console.log(this.reponseTemp);
        this.DBOData = [];

        if (this.selectedUR.updateCode == "UPD_DBO_MECH_PRICE_NEW") {
          this.exist = true;
          this.tabIndex = 1;
          this.addNew = false;
        } else if (this.selectedUR.updateCode == "UPD_DBO_MECH_PRICE") {
          this.addNew = true;
          this.exist = false;
          this.tabIndex = 0
        }

        // if current role is enginner this if will be exceuted
        if (this.storage.get(this.currentRole) == "DBO_MECH_EDIT") {
          console.log("Entered if");
          this.typpanel=this._ITOMyWorkListPageService.responseTemp.savedUpdateMechPriceList[0].scopeCd;
          // this.turbType = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].panelCode;
          // this.itemId = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].itemId;
          // this.itemName = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].itemName;
          // this.custCode = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].custCode;  // change this after adding in db
          if (this.custCode == "DM") {
            this.custType = "Domestic";
          } else if (this.custCode == "EX") {
            this.custType = "Export";
          }

          // forming list of data otherthan selected ones
          for (let i = 0; i < this._ITOMyWorkListPageService.responseTemp.unsavedUpdateMechPriceList.length; i++) {
            for (let p = 0; p < this._ITOMyWorkListPageService.responseTemp.savedUpdateMechPriceList.length; p++) {
              if (this._ITOMyWorkListPageService.responseTemp.unsavedUpdateMechPriceList[i].priceId == this._ITOMyWorkListPageService.responseTemp.savedUpdateMechPriceList[p].priceId) {
                this.DBOData.push(this._ITOMyWorkListPageService.responseTemp.unsavedUpdateMechPriceList[i]);
              }
            }
          }
          // get selected data from savedUpdateMechPriceList
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedUpdateMechPriceList;
          // this.DBOData = this._ITOMyWorkListPageService.responseTemp.unsavedDboEleList;

          // copy same request number for further process
          this.dboFormData.saveBasicDetails.updateRequestNumber = this._ITOMyWorkListPageService.responseTemp.saveBasicDetails.updateRequestNumber;
          console.log(this.finalRangeList);
          console.log(this.DBOData);
          if (this.DBOData.length > 0) {
            this.tableDiv = true;
          }
        }
        // if current role is Reviewer/Approver this else if will be exceuted
        else if (this.storage.get(this.currentRole) != "DBO_MECH_EDIT") {
          console.log("Entered else if");
          // this.turbType = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].panelCode;
          // this.panelCdNew = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].panelCode;
          // this.itemId = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].itemId;
          // this.itemName = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].itemName;
          //this.itemNameNew = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].itemName;
          // this.custCode = this._ITOMyWorkListPageService.responseTemp.savedDboMechList[0].custCode;  // change this after adding in db
          if (this.custCode == "DM") {
            this.custType = "Domestic";
          } else if (this.custCode == "EX") {
            this.custType = "Export";
          }

          //for reviewer and Approver both display data and selected data will be same
          // this.typpanel=this._ITOMyWorkListPageService.responseTemp.savedUpdateMechPriceList[0].updateCode;
          this.DBOData = this._ITOMyWorkListPageService.responseTemp.savedUpdateMechPriceList;
          this.finalRangeList = this._ITOMyWorkListPageService.responseTemp.savedUpdateMechPriceList;
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
        if(this._ITOMyWorkListPageService.selectedUR.updateCode == "MECH_PRICE_UPD"){
          this.typpanel="Price";
        this.cols = [
          { field: 'turbCodeNm', header: 'Turbine Type' },
          { field: 'itemName', header:'Item Name'},
          { field: 'subItemName', header: 'SubItem Name' },                   
          { field: 'priceCode', header: 'Price Code' },
          { field: 'prevApproxCostFlag', header: 'Previous Approximate Flag' },
          { field: 'approxCostFlag', header: 'Approximate flag' },
          { field: 'prevPrice', header: 'Previous Price' },
          { field: 'price', header: 'Price' },
        ];
      }else  if(this._ITOMyWorkListPageService.selectedUR.updateCode == "MECH_AUX_PRICE_UPD"){
        this.typpanel="Auxilaries Price";
        this.cols = [
          { field: 'turbCodeNm', header: 'Turbine Type' },   
          { field: 'itemName', header:'Item Name'},                
          { field: 'priceCode', header: 'Price Code' },
          { field: 'prevApproxCostFlag', header: 'Previous Approximate Flag' },
          { field: 'approxCostFlag', header: 'Approximate flag' },
          { field: 'prevPrice', header: 'Pre Price' },
          { field: 'price', header: 'Price' },
        ];
      }else if(this._ITOMyWorkListPageService.selectedUR.updateCode == "MECH_ADDON_COST_UPD"){
        this.typpanel="AddOn Cost";
      this.cols = [
        { field: 'itemName', header:'Item Name'},
        { field: 'subItemName', header: 'SubItem Name' },                
        { field: 'colNm', header: 'LHS' },
        { field: 'colValCd', header: 'RHS Value' },
        {field: 'prevAddOnCostCol', header: 'Previous AddOn Cost Column'},
        { field: 'addOnCostCol', header: 'AddOn Cost Column'},
        { field: 'prevAddOnCostPer' , header: 'Previous AddOn Cost Percentage'},
        { field: 'addOnCostPer', header: 'AddOn Cost Percentage' },
        { field: 'prevAddOnDirCost', header: 'Previous AddOn Direct Cost'},
        { field: 'addOnDirCost', header: 'AddOn Direct Cost' },
        { field: 'prevApproxCostFlag', header: 'Pervious Approximate Flag'},
        { field: 'approxCostFlag', header: 'Approximate Flag' },
      ];
    }else if(this._ITOMyWorkListPageService.selectedUR.updateCode == "MECH_AUX_COL_VAL_UPD"){
      this.typpanel="Auxilaries AddOn";
    this.cols = [
      { field: 'itemName', header:'Item Name'},
        { field: 'subItemName', header: 'SubItem Name' },                
        { field: 'colNm', header: 'Col Name' },
        { field: 'colValCd', header: 'Col Val Name' },
        { field: 'prevAddOnCostPer' , header: 'Previous AddOn Cost Percentage'},
        { field: 'addOnCostPer', header: 'AddOn Cost Percentage' },
        { field: 'prevAddOnDirCost', header: 'Previous AddOn Direct Cost'},
        { field: 'addOnDirCost', header: 'AddOn Direct Cost' },
        { field: 'prevApproxCostFlag', header: 'Pervious Approximate Flag'},
        { field: 'approxCostFlag', header: 'Approximate Flag' },
    ];
  }else if(this._ITOMyWorkListPageService.selectedUR.updateCode == "MECH_AUX_OVER_TANK_UPD"){
        this.typpanel="Auxilaries Over Head";
      this.cols = [
        { field: 'itemName', header:'Item Name'},
        { field: 'power', header: 'Power' },
        { field: 'prevQuantity', header: 'prev Quantity'},
        { field: 'quantity', header: 'Quantity' },
        { field: 'prevPrice' , header: 'Prev Price'},
        { field: 'price', header: 'Price' },
        { field: 'prevApproxCostFlag', header: 'Previous Approximate Flag'},
        { field: 'approxCostFlag', header: 'Approximate Flag' },
      ];
    }else if(this._ITOMyWorkListPageService.selectedUR.updateCode == "MECH_AUX_FRM_SPEC_DATA_UPD"){
      this.typpanel="Auxilaries Initial Fill";
    this.cols = [      
      { field: 'frameName', header:'Frame Name'},
      { field: 'itemName', header:'Item Name'},
      { field: 'colNm', header: 'LHS' },
      { field: 'prevColValCd', header: 'Previous Rhs Value'},
      { field: 'colValCd', header: 'Rhs Value'},
      { field: 'prevMinVal', header: 'previous Min Val'},
      { field: 'minVal', header: 'Min Val' },
      { field: 'prevMaxVal', header: 'Previous Max Val'},
      { field: 'maxVal', header: 'Max Val' },
    ];
  }
        this._ITOMyWorkListPageService.selectedUR = '';
        console.log(this.prevRemarks);
      } else {
        //setting columns for grid when prices are not updated
    
        if (this.storage.get(this.currentRole) == "DBO_MECH_EDIT") {
          this.dboFormData.saveBasicDetails.updateRequestNumber = 0;
        }
      }

      this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
      this.modifiedBy = this.localStorageValues[this.loginUserDetails].userId;

      console.log(this.localStorageValues[this.loginUserDetails]);
      this.userRoles = this.localStorageValues[this.loginUserDetails].userRolesList;
      console.log(this.storage.get(this.currentRole));

      switch (this.storage.get(this.currentRole)) {
        case "DBO_MECH_EDIT": {
          this.dboMechEdit = true;
          this.labelName = "Please Select the Reviewer";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "DBO_MECH_REVIWER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.dboFormData.saveBasicDetails.loggedInUserCode = 0;
                this.dboFormData.saveBasicDetails.statusId = 1;
              }
            }
          }
          break;
        }
        case "DBO_MECH_APPROVER": {
          console.log("Approverrr");
          this.dboMechApp = true;
          this.dboFormData.saveBasicDetails.loggedInUserCode = 2;
          this.dboFormData.saveBasicDetails.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
          break;

        }
        case "DBO_MECH_REVIWER": {
          this.dboMechRev = true;
          this.labelName = "Please Select the Approver";
          for (let r = 0; r < this.usersList.length; r++) {
            for (let s = 0; s < this.usersList[r].rolesCodeVal.length; s++) {
              if (this.usersList[r].rolesCodeVal[s] == "DBO_MECH_APPROVER") {
                this.newUsersLilst.push(this.usersList[r]);
                this.dboFormData.saveBasicDetails.loggedInUserCode = 1;
                this.dboFormData.saveBasicDetails.updateRequestNumber = this.reponseTemp.saveBasicDetails.updateRequestNumber;
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
  });
  }
  //method to get item details
  getItemDetailsVal(val) {
    console.log(val);
    this.itemId = val;
    this.DBOData=[];
    this.DBOData =  this.mainList.reduce((acc, current) => {    
     console.log(acc, current);
     const x = acc.find(item => item.itemId === current.itemName);
     if (!x) {
       return acc.concat([current]);
     } else {
       return acc;
     } 
   }, []);
    // this.tempRes.itemId = val;
  }

  //method to get Table data
  getTableData(val) {
    this.tableDiv = false;
    // this.dispDropdown = false;
    // this.itemName='';
    this.typpanel='';
    this.selectedRange=[];
    this.finalRangeList=[];
    console.log(val);
    this.typpanel=val.typpanel;
    if(this.DBOData.length>0){
      this.itemName=this.DBOData[0].itemName;
      this.tableDiv = true;
     }
    // this.tempRes.itemId = val.itemDetails;
    // this.tempRes.custCode = val.custType;
   // this.custCode = val.custType;
    // console.log(this.tempRes);
    // this._ItoUpdateCostDBOMechPrice.getDBOMechUpdatePriceData(this.tempRes).subscribe(resp => {
    //   console.log(resp);
    //   this.DBOData = resp.dBOMechanicalList;
      // if (this.mainList.length > 0) {
      //   for(let c=0;c<this.mainList.length;c++){
      //     if(this.itemId==this.mainList[c].itemId){
      //       this.DBOData.push(this.mainList[c]);
      //     }
      //   }
        
      // }
    // })
  }

  //method to get item details
  getItemDetails(val) {
    this.tableDiv=false;
    this.panelCode = val; // Turbine type- BP/CD
    this.dBOMechanicalListArray = [];
    console.log(val);
    this.typpanel=val;
    this.DBOData=[];
    this.mainList=[];
    // this._ItoUpdateCostDBOMechPrice.getDBOMechanicalItems(val).subscribe(res => {
    //   console.log(res);
    //   this.tempRes = res;
    //   this.dBOMechanicalListArray = res.dBOMechanicalListExcel;
    // })
    if(val == "Price" || val == "AddOn Cost"){
      this.dboFormData.scopeCd="MECH";
      this.tableName = "MECH";
    }else if(val == "Auxilaries Price" || val == "Auxilaries AddOn" || val == "Auxilaries Over Head" || val == "Auxilaries Initial Fill"){
      this.dboFormData.scopeCd="MECH_AUX";
      this.tableName = "MECH_AUX";
    }
    this._ItoUpdateCostF2FAddOnsService.updateGetAddOn(this.dboFormData).subscribe(res => {
      console.log(res);
      if(this.typpanel == "Price" || this.typpanel == "Auxilaries Price"){
      this.mainList=res.priceList;   
      this.DBOData=res.priceList;
      }else if(this.typpanel == "AddOn Cost" || this.typpanel == "Auxilaries AddOn"){
        this.mainList=res.addonPriceList;
        this.DBOData=res.addonPriceList;
      }else if(this.typpanel == "Auxilaries Over Head" ){
        this.mainList=res.overTankPriceList;
        this.DBOData=res.overTankPriceList;
      }else if(this.typpanel=="Auxilaries Initial Fill"){
        this.mainList=res.initialFillList;
        this.DBOData=res.initialFillList;
      }
      if(this.typpanel=="Price"){          
        this.cols = [
          { field: 'turbCodeNm', header: 'Turbine Type' },
          { field: 'itemName', header:'Item Name'},
          { field: 'subItemName', header: 'SubItem Name' },                   
          { field: 'priceCode', header: 'Price Code' },
          { field: 'approxCostFlag', header: 'Approximate flag' },
          { field: 'price', header: 'Price' },
        ];
      }else  if(this.typpanel=="Auxilaries Price"){        
        this.cols = [
          { field: 'turbCodeNm', header: 'Turbine Type' }, 
          { field: 'itemName', header:'Item Name'},                  
          { field: 'priceCode', header: 'Price Code' },
          { field: 'approxCostFlag', header: 'Approximate flag' },
          { field: 'price', header: 'Price' },
        ];
      }else if( this.typpanel=="AddOn Cost"){       
      this.cols = [ 
        { field: 'itemName', header:'Item Name'},
        { field: 'subItemName', header: 'SubItem Name' },                 
        { field: 'colNm', header: 'LHS' },
        { field: 'colValCd', header: 'RHS Value' },
        { field: 'addOnCostCol', header: 'AddOn Cost Column'},
        { field: 'addOnCostPer', header: 'AddOn Cost Percentage' },
        { field: 'addOnDirCost', header: 'AddOn Direct Cost' },
        { field: 'approxCostFlag', header: 'Approximate Flag' },
      ];
    }else if(this.typpanel=="Auxilaries AddOn"){      
    this.cols = [
      { field: 'itemName', header:'Item Name'},
        { field: 'subItemName', header: 'SubItem Name' },               
        { field: 'colNm', header: 'LHS' },
        { field: 'colValCd', header: 'RHS Value' },
        { field: 'addOnCostPer', header: 'AddOn Cost Percentage' },
        { field: 'addOnDirCost', header: 'AddOn Direct Cost' },
        { field: 'approxCostFlag', header: 'Approximate Flag' },
    ];
  }else if(this.typpanel=="Auxilaries Over Head"){        
      this.cols = [
        { field: 'itemName', header:'Item Name'},
        { field: 'power', header: 'Power' },
        { field: 'quantity', header: 'Quantity' },
        { field: 'price', header: 'Price' },
        { field: 'approxCostFlag', header: 'Approximate Flag' },
      ];
    }else if(this.typpanel=="Auxilaries Initial Fill"){        
      this.cols = [
        { field: 'turbCodeNm', header: 'Turbine Type' },  
        { field: 'frameName', header:'Frame Name'},
        { field: 'itemName', header:'Item Name'},
        { field: 'colNm', header: 'LHS' },
        { field: 'colValCd', header: 'RHS Value'},
        { field: 'minVal', header: 'Min Val' },
        { field: 'maxVal', header: 'Max Val' },
      ];
    }
    });
   
  }

  //method to get item details
  getItemDetailsNew(val) {
    this.panelCdNew = val; // Turbine type- BP/CD
    this.dBOMechanicalListArrayNew = [];
    console.log(val);
    this._ItoUpdateCostDBOMechPrice.getDBOMechanicalItems(val).subscribe(res => {
      console.log(res);
      this.tempRes = res;
      this.dBOMechanicalListArrayNew = res.dBOMechanicalListExcel;
    })
  }

  // Edit row functionality
  onRowSelect(event) {

    console.log(event.data);
    this.displayDialog = true;
    this.selectedRange = event.data;
    this.labelList = [];
    this.valueList = [];
    // this._ItoUpdateCostDBOMechPrice.getDBOMechUpdateColData1(this.itemId).subscribe(colResp => {
    //   console.log(colResp);

    //   var str = this.selectedRange.priceCode.split("|");
    //   for (var i = 0; i < str.length; i++) {
    //     for (var j = 0; j < colResp.dBOMechanicalList.length; j++) {
    //       if (str[i] == colResp.dBOMechanicalList[j].colValCd) {
    //         this.labelList.push(colResp.dBOMechanicalList[j].colNm);
    //         this.valueList.push(str[i]);
    //         break;
    //       }
    //     }
    //   }
    //   for (let v = 0; v < this.DBOData.length; v++) {
    //     if (this.DBOData[v].priceId == this.selectedRange.priceId) {
    //       this.previousPrice = this.DBOData[v].price;
    //     }
    //   }
    // })
  }

  //method to create on init
  ngOnInit() {
    // this.finalRangeList = [];
    this.turbTypeArray = [
      { field: 'BP', header: 'Back Pressure' },
      { field: 'CD', header: 'Condensing' }
    ];

    this.custTypeArray = [
      { field: 'DM', header: 'Domestic' },
      { field: 'EX', header: 'Export' }
    ];
    this.columns = [
      { field: 'turbCodeNm', header: 'Turbine Type' }, 
      { field: 'itemName', header:'Item Name'},
      { field: 'subItemName', header: 'SubItem Name' },               
      { field: 'priceCode', header: 'Price Code' },
      { field: 'approxCostFlag', header: 'Approximate flag' },
      { field: 'price', header: 'Price' },
    ];
  }

  //method called after view checked
  // ngAfterViewChecked() {
  //   if ((this.storage.get(this.currentRole) === "DBO_MECH_EDIT") && (this.tabIndex == 0)) {
  //     if (this.DBOData) {
  //       if (this.DBOData.length != 0) {
  //         for (let d = 0; d < this.DBOData.length; d++) {
  //           for (let f = 0; f < this.finalRangeList.length; f++) {
  //             if (this.DBOData[d].priceId == this.finalRangeList[f].priceId) {
                // document.getElementById(this.DBOData[d].priceId).style.backgroundColor = "#0275d8";
  //               this.DBOData[d] = this.finalRangeList[f];
  //             }
  //           }
  //         }
  //       }
  //     }
  //   }
  // }

  //method to get assigned user
  assignedUser(assigne) {
    console.log(assigne);
    console.log(this.tempRes1.userDetailsList);
    // this.saveBasic.assignedTo = assigne;
    this.dboFormData.saveBasicDetails.assignedTo = assigne;
  }

  //method to push data on save
  save() {
    console.log(this.selectedRange);
    console.log(this.finalRangeList);
    let temp=0;
    this.contains = false;
    if(this.typpanel=="Auxilaries Over Head" || this.typpanel=="Auxilaries Initial Fill"){
      for (let d = 0; d < this.DBOData.length; d++) {
        if (this.DBOData[d].id == this.selectedRange.id) {
          temp = this.DBOData[d];
         }
      }
      console.log(temp);
      console.log(this.selectedRange.price);
      
      if (this.finalRangeList.length != 0) {
        for(let j=0;j<this.finalRangeList.length;j++){
      if(this.finalRangeList[j].id==this.selectedRange.id){
        this.finalRangeList[j]=this.selectedRange;  
        this.contains = true;
      }
      }
      if(this.contains==false)
    {
      this.finalRangeList.push(this.selectedRange)

    }
      }else{
        this.finalRangeList.push(this.selectedRange);
      }
    }else if(this.typpanel=="Price" || this.typpanel=="AddOn Cost" || this.typpanel=="Auxilaries Price"){
      for (let d = 0; d < this.DBOData.length; d++) {
        if (this.DBOData[d].priceId == this.selectedRange.priceId) {
          temp = this.DBOData[d];
         }
      }
      console.log(temp);
      console.log(this.selectedRange.price);
      
      if (this.finalRangeList.length != 0) {
        for(let j=0;j<this.finalRangeList.length;j++){
      if(this.finalRangeList[j].priceId==this.selectedRange.priceId){
        this.finalRangeList[j]=this.selectedRange;
        this.contains = true;
      }
      }
      if(this.contains==false)
    {
      this.finalRangeList.push(this.selectedRange)

    }
      }else{
        this.finalRangeList.push(this.selectedRange);
      }
    }else if(this.typpanel =="Auxilaries AddOn"){
      for (let d = 0; d < this.DBOData.length; d++) {
        if (this.DBOData[d].colValId == this.selectedRange.colValId) {
          temp = this.DBOData[d];
         }
      }
      console.log(temp);
      console.log(this.selectedRange.price);
      
      if (this.finalRangeList.length != 0) {
        for(let j=0;j<this.finalRangeList.length;j++){
      if(this.finalRangeList[j].colValId==this.selectedRange.colValId){
        this.finalRangeList[j]=this.selectedRange;
        this.contains = true;
      }
      }
      if(this.contains==false)
    {
      this.finalRangeList.push(this.selectedRange)

    }
      }else{
        this.finalRangeList.push(this.selectedRange);
      }
    }

// if(this.typpanel=="Price" || this.typpanel=="AddOn Cost" || this.typpanel=="Auxilaries Price"){
//     if (this.finalRangeList.length != 0) {
//       for (let s = 0; s < this.finalRangeList.length; s++) {
//         if (this.finalRangeList[s].priceId === this.selectedRange.priceId) {
//           this.finalRangeList[s] = this.selectedRange;
//           this.contains = true;
//           // let butn = document.getElementById(this.finalRangeList[s].priceId).style.backgroundColor = "#0275d8";
//           break;
//         }
//       }
//       if (!this.contains) {
//         this.finalRangeList.push(this.selectedRange);
//         for (let s = 0; s < this.finalRangeList.length; s++) {
//           if (this.finalRangeList[s].priceId === this.selectedRange.priceId) {
//             // let butn = document.getElementById(this.finalRangeList[s].priceId).style.backgroundColor = "#0275d8";
//           }
//         }
//       }
//       else {
//         this.contains = false;
//       }
//     }
//     else {
//       this.finalRangeList.push(this.selectedRange);
//       for (let s = 0; s < this.finalRangeList.length; s++) {
//         if (this.finalRangeList[s].priceId === this.selectedRange.priceId) {
//           // let butn = document.getElementById(this.finalRangeList[s].priceId).style.backgroundColor = "#0275d8";
//         }
//       }
//     }
//   }else if(this.typpanel=="Auxilaries Over Head" || this.typpanel=="Auxilaries Initial Fill"){
//     if (this.finalRangeList.length != 0) {
//       for (let s = 0; s < this.finalRangeList.length; s++) {
//         if (this.finalRangeList[s].id === this.selectedRange.id) {
//           this.finalRangeList[s] = this.selectedRange;
//           this.contains = true;
//           // let butn = document.getElementById(this.finalRangeList[s].id).style.backgroundColor = "#0275d8";
//           break;
//         }
//       }
//       if (!this.contains) {
//         this.finalRangeList.push(this.selectedRange);
//         for (let s = 0; s < this.finalRangeList.length; s++) {
//           if (this.finalRangeList[s].id === this.selectedRange.id) {
//             // let butn = document.getElementById(this.finalRangeList[s].id).style.backgroundColor = "#0275d8";
//           }
//         }
//       }
//       else {
//         this.contains = false;
//       }
//     }
//     else {
//       this.finalRangeList.push(this.selectedRange);
//       for (let s = 0; s < this.finalRangeList.length; s++) {
//         if (this.finalRangeList[s].id === this.selectedRange.id) {
//           // let butn = document.getElementById(this.finalRangeList[s].id).style.backgroundColor = "#0275d8";
//         }
//       }
//     }
//   }else if(this.typpanel =="Auxilaries AddOn"){
//     if (this.finalRangeList.length != 0) {
//       for (let s = 0; s < this.finalRangeList.length; s++) {
//         if (this.finalRangeList[s].colValId === this.selectedRange.colValId) {
//           this.finalRangeList[s] = this.selectedRange;
//           this.contains = true;
//           // let butn = document.getElementById(this.finalRangeList[s].colValId).style.backgroundColor = "#0275d8";
//           break;
//         }
//       }
//       if (!this.contains) {
//         this.finalRangeList.push(this.selectedRange);
//         for (let s = 0; s < this.finalRangeList.length; s++) {
//           if (this.finalRangeList[s].colValId === this.selectedRange.colValId) {
//             // let butn = document.getElementById(this.finalRangeList[s].colValId).style.backgroundColor = "#0275d8";
//           }
//         }
//       }
//       else {
//         this.contains = false;
//       }
//     }
//     else {
//       this.finalRangeList.push(this.selectedRange);
//       for (let s = 0; s < this.finalRangeList.length; s++) {
//         if (this.finalRangeList[s].colValId === this.selectedRange.colValId) {
//           // let butn = document.getElementById(this.finalRangeList[s].colValId).style.backgroundColor = "#0275d8";
//         }
//       }
//     }
//   }

    this.displayDialog = false;
    console.log(this.finalRangeList);
  }

  //method to navaigate to work flow
  navigateToMyWorkflow() {
    this.router.navigate(['MyWorkFlow']);
  }

  //method to save as draft
  SaveAsDraft() {
    this.successMsg = [];
    // this.saveBasic.remarks = this.remarkss;
    this.assignDisp = true;
    console.log(this.remarkss);
    console.log(this.finalRangeList);
    // for (let q = 0; q < this.finalRangeList.length; q++) {
    //   if (this.finalRangeList[q].priceId > 0) {
    //     this.saveBasic.updateCode = "UPD_DBO_MECH_PRICE";
    //   } else {
    //     this.saveBasic.updateCode = "UPD_DBO_MECH_PRICE_NEW";
    //   }
    // }
    // this.saveBasic.custCode = this.custCode;
    // this.saveBasic.modifiedById = this.modifiedBy;
    // this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);

    // this.tempRes1.saveBasicDetails = this.saveBasic;
    // this.tempRes1.quotDboMechList = this.finalRangeList;
    // console.log(this.tempRes1);

    this.dboFormData.saveBasicDetails.modifiedById = this.modifiedBy; 
    this.dboFormData.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
    this.dboFormData.saveBasicDetails.remarks = this.remarkss; 
    console.log(this.dboFormData.modifiedById);
    console.log(this.dboFormData.userRoleId);
    console.log(this.dboFormData.loggedInUserCode);
   
        for (let v = 0; v < this.finalRangeList.length; v++) {
          if (this.finalRangeList[v] != undefined) {
                 this.dboClass = new dboClass();
                 if(this.typpanel=="Price"){
                  this.dboClass.updateCode="MECH_PRICE_UPD";
                  this.dboClass.priceId=this.finalRangeList[v].priceId;              
                  this.dboClass.itemId=this.finalRangeList[v].itemId;              
                  this.dboClass.subItemId=this.finalRangeList[v].subItemId;              
                  this.dboClass.turbCode=this.finalRangeList[v].turbCode;              
                  this.dboClass.priceCode=this.finalRangeList[v].priceCode;              
                  this.dboClass.price=this.finalRangeList[v].price;              
                  this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;                           
                  this.dboClass.defaultFlagNew=this.finalRangeList[v].defaultFlagNew;              
                  this.dboClass.totalPrice=this.finalRangeList[v].totalPrice;              
                  this.dboClass.activeNew=this.finalRangeList[v].activeNew;              
                  this.dboFormData.updateMechPrice.push(this.dboClass);
                 }else if(this.typpanel=="Auxilaries Price"){
                  this.dboClass.updateCode="MECH_AUX_PRICE_UPD";
                  this.dboClass.priceId=this.finalRangeList[v].priceId;
                  this.dboClass.itemId=this.finalRangeList[v].itemId;
                  this.dboClass.turbCode=this.finalRangeList[v].turbCode;
                  this.dboClass.priceCode=this.finalRangeList[v].priceCode;
                  this.dboClass.price=this.finalRangeList[v].price;
                  this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;
                  this.dboClass.activeNew=this.finalRangeList[v].activeNew;
                  this.dboFormData.updateMechAuxPrice.push(this.dboClass);
                 }else if(this.typpanel == "AddOn Cost"){
                  this.dboClass.updateCode="MECH_ADDON_COST_UPD";
                  this.dboClass.priceId=this.finalRangeList[v].priceId;
                  this.dboClass.itemId=this.finalRangeList[v].itemId;
                  this.dboClass.subItemId=this.finalRangeList[v].subItemId;
                  this.dboClass.colId=this.finalRangeList[v].colId;
                  this.dboClass.colValCd=this.finalRangeList[v].colValCd;
                  this.dboClass.addOnCostCol=this.finalRangeList[v].addOnCostCol;
                  this.dboClass.addOnCostPer=this.finalRangeList[v].addOnCostPer;
						      this.dboClass.addOnDirCost=this.finalRangeList[v].addOnDirCost;
                  this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;
                  this.dboClass.activeNew=this.finalRangeList[v].activeNew;
                  this.dboFormData.updateMechAddOnCost.push(this.dboClass);
                 }else if(this.typpanel=="Auxilaries AddOn"){
                  this.dboClass.updateCode="MECH_AUX_COL_VAL_UPD";
                  this.dboClass.colValId=this.finalRangeList[v].colValId;
						      this.dboClass.itemId=this.finalRangeList[v].itemId;				
                  this.dboClass.colId=this.finalRangeList[v].colId;
                  this.dboClass.colValCd=this.finalRangeList[v].colValCd;
                  this.dboClass.colValNm=this.finalRangeList[v].colValNm;
                  this.dboClass.defaultFlagNew=this.finalRangeList[v].defaultFlagNew;
                  this.dboClass.dispInd=this.finalRangeList[v].dispInd;
                  this.dboClass.orderId=this.finalRangeList[v].orderId;
                  this.dboClass.comrFlag=this.finalRangeList[v].comrFlag;
                  this.dboClass.techFlag=this.finalRangeList[v].techFlag;
                  this.dboClass.addOnFlg=this.finalRangeList[v].addOnFlg;
                  this.dboClass.addOnCostPer=this.finalRangeList[v].addOnCostPer;
                  this.dboClass.addOnDirCost=this.finalRangeList[v].addOnDirCost;
                  this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;
                  this.dboClass.activeNew=this.finalRangeList[v].activeNew;                 
                  this.dboFormData.updateMechAuxColVal.push(this.dboClass);
                 }else if(this.typpanel=="Auxilaries Over Head"){
                  this.dboClass.updateCode="MECH_AUX_OVER_TANK_UPD";
                  this.dboClass.id=this.finalRangeList[v].id;
                  this.dboClass.itemId=this.finalRangeList[v].itemId;
                  this.dboClass.power=this.finalRangeList[v].power;
                  this.dboClass.minVal=this.finalRangeList[v].minVal;
                  this.dboClass.maxVal=this.finalRangeList[v].maxVal;
                  this.dboClass.qty=this.finalRangeList[v].quantity;
                  this.dboClass.price=this.finalRangeList[v].price;
                  this.dboClass.defaultFlagNew=this.finalRangeList[v].defaultFlagNew;
                  this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;
                  this.dboClass.activeNew=this.finalRangeList[v].activeNew;
                  this.dboFormData.updateMechOverTank.push(this.dboClass);
                 }else if(this.typpanel=="Auxilaries Initial Fill"){
                  this.dboClass.updateCode="MECH_AUX_FRM_SPEC_DATA_UPD";
              this.dboClass.id=this.finalRangeList[v].id;
              this.dboClass.frameId=this.finalRangeList[v].frameId;
              this.dboClass.itemId=this.finalRangeList[v].itemId;              
              this.dboClass.subItemId=this.finalRangeList[v].subItemId;
              this.dboClass.subItemTypeId=this.finalRangeList[v].subItemTypeId;
              this.dboClass.colId=this.finalRangeList[v].colId;
              this.dboClass.colValCd=this.finalRangeList[v].colValCd;
              this.dboClass.minVal=this.finalRangeList[v].minVal;
              this.dboClass.maxVal=this.finalRangeList[v].maxVal;
              this.dboClass.activeNew=this.finalRangeList[v].activeNew;
                  this.dboFormData.updateF2fFrameSpecData.push(this.dboClass);
                 }
                }
              }
     

    //while saving as draft onlt request is created, workflow will not be initiated
    if(this.typpanel=="Price"){
    this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechPrice(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.dboFormData.saveBasicDetails.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.tempRes1 = resp;

    });
  }else if(this.typpanel=="Auxilaries Price"){
    this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechAuxPrice(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.dboFormData.saveBasicDetails.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.tempRes1 = resp;

    });
  }else if(this.typpanel == "AddOn Cost"){
    this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechAddOnCost(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      this.dboFormData.saveBasicDetails.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
      this.tempRes1 = resp;

    });
     }else if(this.typpanel=="Auxilaries AddOn"){
      this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechAuxColVal(this.dboFormData).subscribe(resp => {
        console.log(resp);
        this.successMsg.push(resp.successMsg);
        this.dboFormData.saveBasicDetails.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
        this.tempRes1 = resp;
  
      });
       }else if(this.typpanel=="Auxilaries Over Head"){
        this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechOverTank(this.dboFormData).subscribe(resp => {
          console.log(resp);
          this.successMsg.push(resp.successMsg);
          this.dboFormData.saveBasicDetails.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
          this.tempRes1 = resp;
    
        });        
                 }else if(this.typpanel=="Auxilaries Initial Fill"){
                  this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechAuxFrameSpecData(this.dboFormData).subscribe(resp => {
                    console.log(resp);
                    this.successMsg.push(resp.successMsg);
                    this.dboFormData.saveBasicDetails.updateRequestNumber = resp.saveBasicDetails.updateRequestNumber;
                    this.tempRes1 = resp;
              
                  });        
                           }
    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedRange = '';
    for (let vv = 0; vv < this.DBOData.length; vv++) {
      // let butn = document.getElementById(this.DBOData[vv].priceId).style.backgroundColor = "";
    }
  }

  //method for update price 
  updatePriceDboMech(form) {
    console.log(form);
    this.successMsg = [];
    // this.saveBasic.remarks = form.coments;
    this.assignDisp = true;
    console.log(this.finalRangeList);

    // if price id=0; it means that they are adding new price, so request code is UPD_DBO_MECH_PRICE_NEW
    // for (let q = 0; q < this.finalRangeList.length; q++) {
    //   if (this.finalRangeList[q].priceId > 0) {
    //     this.saveBasic.updateCode = "UPD_DBO_MECH_PRICE";
    //   } else {
    //     this.saveBasic.updateCode = "UPD_DBO_MECH_PRICE_NEW";
    //   }
    // }
    // this.saveBasic.custCode = this.custCode;
    // this.saveBasic.modifiedById = this.modifiedBy;
    // this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
    // if (form.status == "Accept") {
    //   this.saveBasic.statusId = 1;
    // }
    // else if (form.status == "Reject") {
    //   this.saveBasic.statusId = 0;
    // }
    // this.tempRes1.saveBasicDetails = this.saveBasic;
    // this.tempRes1.quotDboMechList = this.finalRangeList;
    // console.log(this.tempRes1);
    this.dboFormData.saveBasicDetails.modifiedById = this.modifiedBy; 
this.dboFormData.saveBasicDetails.userRoleId = this.storage.get(this.currentRoleId);
this.dboFormData.saveBasicDetails.remarks = form.coments; 
console.log(this.dboFormData.modifiedById);
console.log(this.dboFormData.userRoleId);
console.log(this.dboFormData.loggedInUserCode);



if(this.storage.get(this.currentRole)=="DBO_MECH_EDIT")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=0;
  this.dboFormData.saveBasicDetails.statusId = 1;

}
else if(this.storage.get(this.currentRole)=="DBO_MECH_REVIWER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=1;
  if (form.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (form.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}
else if(this.storage.get(this.currentRole)=="DBO_MECH_APPROVER")
{
  this.dboFormData.saveBasicDetails.loggedInUserCode=2;
  if (form.status == "Accept") {
    this.dboFormData.saveBasicDetails.statusId = 1;
  }
  else if (form.status == "Reject") {
    this.dboFormData.saveBasicDetails.statusId = 0;
  }
}

    for (let v = 0; v < this.finalRangeList.length; v++) {
      if (this.finalRangeList[v] != undefined) {
             this.dboClass = new dboClass();
             if(this.typpanel=="Price"){
              this.dboClass.updateCode="MECH_PRICE_UPD";
              this.dboClass.priceId=this.finalRangeList[v].priceId;              
              this.dboClass.itemId=this.finalRangeList[v].itemId;              
              this.dboClass.subItemId=this.finalRangeList[v].subItemId;              
              this.dboClass.turbCode=this.finalRangeList[v].turbCode;              
              this.dboClass.priceCode=this.finalRangeList[v].priceCode;              
              this.dboClass.price=this.finalRangeList[v].price;              
              this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;                           
              this.dboClass.defaultFlagNew=this.finalRangeList[v].defaultFlagNew;              
              this.dboClass.totalPrice=this.finalRangeList[v].totalPrice;              
              this.dboClass.activeNew=this.finalRangeList[v].activeNew;              
              this.dboFormData.updateMechPrice.push(this.dboClass);
            }else if(this.typpanel=="Auxilaries Price"){
              this.dboClass.updateCode="MECH_AUX_PRICE_UPD";
              this.dboClass.priceId=this.finalRangeList[v].priceId;
              this.dboClass.itemId=this.finalRangeList[v].itemId;
              this.dboClass.turbCode=this.finalRangeList[v].turbCode;
              this.dboClass.priceCode=this.finalRangeList[v].priceCode;
              this.dboClass.price=this.finalRangeList[v].price;
              this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;
              this.dboClass.activeNew=this.finalRangeList[v].activeNew;
              this.dboFormData.updateMechAuxPrice.push(this.dboClass);
             }else if(this.typpanel == "AddOn Cost"){
              this.dboClass.updateCode="MECH_ADDON_COST_UPD";
              this.dboClass.priceId=this.finalRangeList[v].priceId;
              this.dboClass.itemId=this.finalRangeList[v].itemId;
              this.dboClass.subItemId=this.finalRangeList[v].subItemId;
              this.dboClass.colId=this.finalRangeList[v].colId;
              this.dboClass.colValCd=this.finalRangeList[v].colValCd;
              this.dboClass.addOnCostCol=this.finalRangeList[v].addOnCostCol;
              this.dboClass.addOnCostPer=this.finalRangeList[v].addOnCostPer;
              this.dboClass.addOnDirCost=this.finalRangeList[v].addOnDirCost;
              this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;
              this.dboClass.activeNew=this.finalRangeList[v].activeNew;
              this.dboFormData.updateMechAddOnCost.push(this.dboClass);
             }else if(this.typpanel=="Auxilaries AddOn"){
              this.dboClass.updateCode="MECH_AUX_COL_VAL_UPD";
              this.dboClass.colValId=this.finalRangeList[v].colValId;
						      this.dboClass.itemId=this.finalRangeList[v].itemId;				
                  this.dboClass.colId=this.finalRangeList[v].colId;
                  this.dboClass.colValCd=this.finalRangeList[v].colValCd;
                  this.dboClass.colValNm=this.finalRangeList[v].colValNm;
                  this.dboClass.defaultFlagNew=this.finalRangeList[v].defaultFlagNew;
                  this.dboClass.dispInd=this.finalRangeList[v].dispInd;
                  this.dboClass.orderId=this.finalRangeList[v].orderId;
                  this.dboClass.comrFlag=this.finalRangeList[v].comrFlag;
                  this.dboClass.techFlag=this.finalRangeList[v].techFlag;
                  this.dboClass.addOnFlg=this.finalRangeList[v].addOnFlg;
                  this.dboClass.addOnCostPer=this.finalRangeList[v].addOnCostPer;
                  this.dboClass.addOnDirCost=this.finalRangeList[v].addOnDirCost;
                  this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;
                  this.dboClass.activeNew=this.finalRangeList[v].activeNew;  
              this.dboFormData.updateMechAuxColVal.push(this.dboClass);
             }else if(this.typpanel=="Auxilaries Over Head"){
              this.dboClass.updateCode="MECH_AUX_OVER_TANK_UPD";
              this.dboClass.id=this.finalRangeList[v].id;
              this.dboClass.itemId=this.finalRangeList[v].itemId;
              this.dboClass.power=this.finalRangeList[v].power;
              this.dboClass.minVal=this.finalRangeList[v].minVal;
              this.dboClass.maxVal=this.finalRangeList[v].maxVal;
              this.dboClass.qty=this.finalRangeList[v].quantity;
              this.dboClass.price=this.finalRangeList[v].price;
              this.dboClass.defaultFlagNew=this.finalRangeList[v].defaultFlagNew;
              this.dboClass.approxCostFlag=this.finalRangeList[v].approxCostFlag;
              this.dboClass.activeNew=this.finalRangeList[v].activeNew;
              this.dboFormData.updateMechOverTank.push(this.dboClass);
             }else if(this.typpanel=="Auxilaries Initial Fill"){
              this.dboClass.updateCode="MECH_AUX_FRM_SPEC_DATA_UPD";
              this.dboClass.id=this.finalRangeList[v].id;
              this.dboClass.frameId=this.finalRangeList[v].frameId;
              this.dboClass.itemId=this.finalRangeList[v].itemId;              
              this.dboClass.subItemId=this.finalRangeList[v].subItemId;
              this.dboClass.subItemTypeId=this.finalRangeList[v].subItemTypeId;
              this.dboClass.colId=this.finalRangeList[v].colId;
              this.dboClass.colValCd=this.finalRangeList[v].colValCd;
              this.dboClass.minVal=this.finalRangeList[v].minVal;
              this.dboClass.maxVal=this.finalRangeList[v].maxVal;
              this.dboClass.activeNew=this.finalRangeList[v].activeNew;
              this.dboFormData.updateF2fFrameSpecData.push(this.dboClass);
             }
            }
          }
          console.log(this.dboFormData);
 
    // request will be created first and then workflow will be initiated
    if(this.typpanel=="Price"){
    this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechPrice(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "DBO_MECH_APPROVER") {
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this._ItoUpdateCostDBOMechPrice.saveUpdatedPackagePrice(respon).subscribe(respo => {
            this.successMsg.push(respo.successMsg);
          });
          // this._ItoUpdateCostDBOMechPrice.saveUpdatedNoOfVehicles(respon).subscribe(respo => {
          //   console.log(respo);
          //   this.successMsg.push(respo.successMsg);
          // })
        });

      } else {
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }

    });
  }else if(this.typpanel=="Auxilaries Price"){
    this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechAuxPrice(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "DBO_MECH_APPROVER") {
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this._ItoUpdateCostDBOMechPrice.saveUpdatedPackagePrice(respon).subscribe(respo => {
            this.successMsg.push(respo.successMsg);
          });          
        });

      } else {
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }

    });
  }else if(this.typpanel=="Auxilaries Over Head"){
    this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechOverTank(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "DBO_MECH_APPROVER") {
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this._ItoUpdateCostDBOMechPrice.saveUpdatedPackagePrice(respon).subscribe(respo => {
            this.successMsg.push(respo.successMsg);
          });
        });

      } else {
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }

    });
  }else if(this.typpanel=="Auxilaries AddOn"){
    this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechAuxColVal(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "DBO_MECH_APPROVER") {
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this._ItoUpdateCostDBOMechPrice.saveUpdatedPackagePrice(respon).subscribe(respo => {
            this.successMsg.push(respo.successMsg);
          });
        });

      } else {
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }

    });
  }else if(this.typpanel=="AddOn Cost"){
    this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechAddOnCost(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "DBO_MECH_APPROVER") {
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this._ItoUpdateCostDBOMechPrice.saveUpdatedPackagePrice(respon).subscribe(respo => {
            this.successMsg.push(respo.successMsg);
          });
        });

      } else {
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }

    });
  }else if(this.typpanel=="Auxilaries Initial Fill"){
    this._ItoUpdateCostDBOMechPrice.getUpdateCreateMechAuxFrameSpecData(this.dboFormData).subscribe(resp => {
      console.log(resp);
      this.successMsg.push(resp.successMsg);
      if (this.storage.get(this.currentRole) == "DBO_MECH_APPROVER") {
        resp.saveBasicDetails.assignedTo = this.modifiedBy;
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
          this._ItoUpdateCostDBOMechPrice.saveUpdatedPackagePrice(respon).subscribe(respo => {
            this.successMsg.push(respo.successMsg);
          });
        });

      } else {
        this._ItoUpdateCostDBOMechPrice.updateStatus(resp).subscribe(respon => {
          console.log(respon);
          this.successMsg.push(respon.successMsg);
        })
      }

    });
  }


    this.displayMessage = true;
    this.finalRangeList = [];
    this.selectedRange = '';

    console.log(this.successMsg)
  }

  // closing the dialog after updating the price
  closeMessage() {
    this.displayMessage = false;
    this.finalRangeList = [];
    this.remarkss = '';
    this.successMsg = [];
    //this.acceptedOnly = true;
    this.navigateToMyWorkflow();
    // for (let d = 0; d < this.DBOData.length; d++) {
    //   for (let f = 0; f < this.finalRangeList.length; f++) {
    //     if (this.DBOData[d].priceId == this.finalRangeList[f].priceId) {
          // document.getElementById(this.DBOData[d].priceId).style.backgroundColor = "#0275d8";
    //       console.log(this.DBOData[d]);
    //     }
    //   }
    // }
  }

  //getting data for add screen
  getNewData(newDataForm) {
    console.log(newDataForm);
    this.newlyAddedData = [];
    this.itemIdNew = newDataForm.itemIdNew;
    this.panelCdNew = newDataForm.turbineType;
    //this.custCode = newDataForm.custCd;
    this._ItoUpdateCostDBOMechPrice.getDBOMechUpdateColData1(newDataForm.itemIdNew).subscribe(colRes1 => {
      this.newlyAddedData = colRes1.dBOMechanicalList;
      this.itemNameNew = this.newlyAddedData[0].itemName;
      this.questionsBean = colRes1.questionsBean;
      this.dispDropdown = false;
    })
  }

  handleChange(event) {
    var index = event.index;
    this.tabIndex = index;
    console.log(index)
    this.finalRangeList = [];
    this.DBOData = [];
    this.itemIdNew = '';
    this.itemId = '';
    this.custCode = '';
    this.panelCdNew = '';
    this.panelCode = '';
    this.dispDropdown = true;
    this.tableDiv = false;
    if (index == 0) {

    } else if (index == 1) {
      this.newlyAddedData = [];
    }
    //this.form.reset();
  }

  addCost(addCostForm) {
    this.tableDiv = false;
    console.log(addCostForm);
    let isExists = true;
    this.tempRes.itemId = this.itemIdNew;
    this.tempRes.custCode = "DM"; // change this after db changes
    this.tempRes.panelType = this.panelCdNew;
    console.log(this.tempRes);

    //getting grid data
    // inputs to this service methods are itemId,custCode
    this._ItoUpdateCostDBOMechPrice.getDBOMechUpdatePriceData(this.tempRes).subscribe(resp1 => {
      console.log(resp1);

      var mechData = new DBOMechPrice();
      mechData.priceCode = "";
      for (var i = 0; i < this.questionsBean.length; i++) {
        if (mechData.priceCode == "") {
          mechData.priceCode = addCostForm[i];
        } else {
          mechData.priceCode = mechData.priceCode + "|" + addCostForm[i];
        }
      }
      // check if the pricecode already has cost,if yes display error else proceed
      let k = resp1.dBOMechanicalList.map((x) => x.priceCode).includes((mechData.priceCode));
      console.log(k);
      if (k) {
        alert("Data Already Exists");
      }
      else {
        // check if the pricecode already present in our list,if yes display error else proceed
        let m = this.finalRangeList.map((x) => x.priceCode).includes((mechData.priceCode));
        console.log(m);
        if (m) {
          alert("Data Already Exists");
        } else {
          for (var i = 0; i < this.newlyAddedData.length; i++) {
            if (this.itemIdNew == this.newlyAddedData[i].itemId) {
              mechData.itemName = this.newlyAddedData[i].itemName;
            }
          }
          mechData.itemId = this.itemIdNew;
          mechData.price = addCostForm.newCostDom;
          mechData.custCode = "DM";
          mechData.custType = "Domestic";
          mechData.panelCode = this.panelCdNew;

          console.log(mechData.priceCode);
          mechData.priceId = 0;

          this.DBOData.push(mechData);
          this.finalRangeList.push(mechData);

          if (addCostForm.newCostEx != null) {
            var mechDataEx = new DBOMechPrice();
            for (var i = 0; i < this.newlyAddedData.length; i++) {
              if (this.itemIdNew == this.newlyAddedData[i].itemId) {
                mechDataEx.itemName = this.newlyAddedData[i].itemName;
              }
            }
            mechDataEx.itemId = this.itemIdNew;
            mechDataEx.price = addCostForm.newCostEx;
            mechDataEx.custCode = "EX";
            mechDataEx.custType = "Export";
            mechDataEx.priceCode = "";
            mechDataEx.panelCode = this.panelCdNew;

            for (var i = 0; i < this.questionsBean.length; i++) {
              if (mechDataEx.priceCode == "") {
                mechDataEx.priceCode = addCostForm[i];
              } else {
                mechDataEx.priceCode = mechDataEx.priceCode + "|" + addCostForm[i];
              }
            }

            mechDataEx.priceId = 0;
            this.DBOData.push(mechDataEx);
            this.finalRangeList.push(mechDataEx);
          }

        }
      }
      if (this.DBOData.length > 0) {
        this.tableDiv = true;
      }
      console.log(this.finalRangeList)
      console.log(this.DBOData)
    })
  }

  onInputChange(event) {
    this.finalRangeList = this.DBOData;
  }
  saveBulk()
  {
    this._ItoUpdateTransportationService.getAdminForm().subscribe(res => {
      console.log(res);
      this.adminForm = res;
      this.adminForm.percent=this.percentage;
      this.adminForm.tableName = this.tableName;
      this.adminForm.modifiedBy=this.modifiedBy;
    
    this._ItoUpdateCostDBOMechPrice.getAdminPercentMech(this.adminForm).subscribe(resp => {
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
