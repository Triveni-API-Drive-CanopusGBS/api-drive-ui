import { Component, OnInit, AfterViewChecked, AfterContentChecked } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { addOnComponentsFields } from './add-on-components';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { TableBody } from 'primeng/table';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';

@Component({
  selector: 'app-add-on-components',
  templateUrl: './add-on-components.component.html',
  styleUrls: ['./add-on-components.component.css']
})
export class AddOnComponentsComponent implements OnInit {

  getTotalBtn: boolean = false;
  AddOns: Array<any> = [];
  selAddOns: Array<any> = [];
  show: boolean;
  finalAddOn: addOnComponentsFields;
  hideDiv: Array<boolean> = [];
  enableOverWrite: boolean = false;
  vmsData: Array<any> = [];
  governer1: Array<any> = [];
  governer2: Array<any> = [];
  governer3: Array<any> = [];
  formName: Array<any> = [];
  ExcelCost: Array<any> = [];
  maxCost: Array<any> = [];
  remarks: string = '';
  takeMaxCost: Array<any> = [];
  takeExcelCost: Array<any> = [];
  finalAddOnArray = [new addOnComponentsFields('')];
  subtype2Id: Array<any> = [];
  subtype1Id: Array<any> = [];
  quantity: Array<any> = [];
  quantityName: Array<any> = [];
  makeId: Array<any> = [];
  subtype2Ids: Array<any> = [];
  subtype1Ids: Array<any> = [];
  tempAdd: Array<any> = [];
  // showMsg: Array<boolean> = [];
  showMsg: boolean = false;
  finalAddOnCost: number;
  addOnCosts: Array<any> = [];
  successMsg: string;
  total: number;
  Message: any;
  messagediv: boolean = false;
  addOndet: Array<any> = [];
  addonComponentsFull: Array<any> = [];
  newArr: Array<any> = [];
  map = new Map();
  make: Array<any> = [];
  fieldEnable: Array<any> = [];
  tablerowCount: Array<number> = [];
  addedAddons: Array<any> = [];
  othersAddonList: Array<any> = []; //array to store the other addons
  displaynewLine: boolean = false; // variable to enable add button for others
  displayOthersTable: boolean = false; // varibale to display others table
  addOnDetails: string = 'addOnDetail';
  scopeofsupp: string = 'scopeOfsup';
  public addOnsData: any = [];
  makeOptions: Array<any> = []; // storing the selected make option
  subType1: Array<any> = []; // storing the selected subType1 option
  subtype2: Array<any> = []; // storing the selected subtype2 option
  othersCheck: boolean = false;
  validationMsg: string = "";
  validationMsgdisp: Array<boolean> = [false];
  disableArray: Array<boolean> = [];
  lastMsg: string = '';

  //-----------------
  newAddOnComp: Array<any> = [];
  newAddOnCompOpt: Array<any> = [];
  filteredValues: Array<any> = [];
  optionArray: Array<any> = [];
  tempOpts: Array<any> = [];
  makedup: any = "W2301";
  excelAddOnCost: Array<any> = [];
  excelAddOnCostRes: Array<any> = [];
  overWrittenAddOnCost: Array<any> = [];
  errorMsg: string;
  remarksValue: any;
  sampleExel: Array<any> = [];
  eligibleToOW: Array<boolean> = [];
  oneLineLoc: string = 'oneLineLoc';
  dispPrevComments: boolean = false;
  samplResp: any;
  overWrittenFinalAddOnCost: number = 0;
  oldComms: any;
  quotNumb: any;
  tempFinal
  constructor(private _Router: Router, private _ITOAddOnComponentsService: ITOAddOnComponentsService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    private _ITOturbineConfigService: ITOturbineConfigService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOCostEstimationService: ITOCostEstimationService) {
    this.quotNumb = this._ITOcustomerRequirementService.saveBasicDet.quotNumber;
    this._ITOcustomerRequirementService.sendMessage(this._ITOcustomerRequirementService.saveBasicDet.quotNumber);
    this.tablerowCount.push(1);
    console.log(this.storage.get(this.oneLineLoc));

    this._ITOAddOnComponentsService.getQuotationList().subscribe(res => {
      this.samplResp = res;
      console.log(this.takeMaxCost);
      this.addonComponentsFull = [];
      this.finalAddOn = new addOnComponentsFields('');
      this.finalAddOnArray = new Array(this.finalAddOn);
      // console.log(res);
      this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "ADD_ON";
      res.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
      console.log(res);
      this._ITOAddOnComponentsService.getAddOnExcelPrice(res).subscribe(resExecel => {
        console.log(resExecel);
        this.newAddOnComp = resExecel.addOnComponentsList;
        this.newAddOnCompOpt = resExecel.addOnComponentsList;
        this.filteredValues = resExecel.questionsBean;
        this.addOnsData[this.addOnDetails] = this.storage.get(this.addOnDetails);
        if (this.addOnsData[this.addOnDetails] != null) {
          if (this.storage.get(this.oneLineLoc).addonCost != null) {

          }
          this.AddOns = res.dropDownColumnvalues.addOnCompoInDetailList.ADD_ON_COMPO_DETAILS;
          console.log(this.addOnsData[this.addOnDetails]);
          this.selAddOns = this.addOnsData[this.addOnDetails].selAddOns;
          console.log("nidhi");
          console.log(this.selAddOns);
          if (this.selAddOns.length > 0) {
            this.getTotalBtn = true;
          }
          this.hideDiv = this.addOnsData[this.addOnDetails].hideDiv;
          this.finalAddOnCost = this.addOnsData[this.addOnDetails].finalAddOnCost;
          this.tempFinal = this.addOnsData[this.addOnDetails].finalAddOnCost;
          this.overWrittenFinalAddOnCost = this.addOnsData[this.addOnDetails].overWrittenFinalAddOnCost;
          this.excelAddOnCost = this.addOnsData[this.addOnDetails].maxCost;
          this.excelAddOnCostRes = this.addOnsData[this.addOnDetails].maxCost;
          this.quantity = this.addOnsData[this.addOnDetails].quantity;
          this.quantityName = this.addOnsData[this.addOnDetails].quantityName;  //added by nidhi
          // this.remarks = this.addOnsData[this.addOnDetails].remarks;
          this.subType1 = this.addOnsData[this.addOnDetails].subType1;
          this.subtype2 = this.addOnsData[this.addOnDetails].subtype2;
          this.makeOptions = this.addOnsData[this.addOnDetails].makeOptions;
          for (let f = 0; f < resExecel.questionsBean.length; f++) {
            for (let q = 0; q < this.addOnsData[this.addOnDetails].selAddOns.length; q++) {
              if (resExecel.questionsBean[f].addOnValueList[0].addOnCompoId == this.addOnsData[this.addOnDetails].selAddOns[q].addOnValueList[0].addOnCompoId) {
                resExecel.questionsBean[f].dropDownType.defaultVal = true;
                this.eligibleToOW[f] = true;
              }
            }
          }
          // this.hideDiv = this.addOnsData[this.addOnDetails].hideDiv;
          // this.ExcelCost = this.addOnsData[this.addOnDetails].ExcelCost;
          // this.maxCost = this.addOnsData[this.addOnDetails].maxCost;
          // this.quantity = this.addOnsData[this.addOnDetails].quantity;
          // this.addOndet = this.addOnsData[this.addOnDe                           tails].addOndet;
          this.othersAddonList = this.addOnsData[this.addOnDetails].othersAddonList;
          // this.enableOverWrite = this.addOnsData[this.addOnDetails].enableOverWrite;
          for (let e = 0; e < this.addOnsData[this.addOnDetails].enableOverWrite.length; e++) {
            if (this.addOnsData[this.addOnDetails].enableOverWrite == true) {
              this.maxCost[e] = '';
              this.takeMaxCost[e] = 1;
              this.takeExcelCost[e] = 0;
            }
            else {
              this.maxCost[e] = this.ExcelCost[e];
              this.takeMaxCost[e] = 0;
              this.takeExcelCost[e] = 1;
              this.remarks = '';
            }
          }

          if (this.othersAddonList.length > 0) {
            this.othersCheck = true;
            this.displayOthersTable = true;
          }
          this.subType1 = this.addOnsData[this.addOnDetails].subType1;
          this.subtype2 = this.addOnsData[this.addOnDetails].subtype2;
          this.makeOptions = this.addOnsData[this.addOnDetails].makeOptions;

          // console.log(this.hideDiv);
          // console.log(this.remarks);


          for (let a = 0; a < this.addOndet.length; a++) {
            for (let s = 0; s < this.selAddOns.length; s++) {
              if (this.addOndet[a].categoryDetId == this.selAddOns[s].addOnCompoId) {
                if (this.quantity[a] != "") {
                  if (this.addOndet[a].categoryDetCode === "ATI") {
                    this.takeMaxCost[a] = 1;
                    this.quantity[a] = this.quantity[a];
                    this.quantityName[a] = this.quantityName[a]; //Added by nidhi
                  }
                  else {
                    res.addOnComponent.quantity = this.quantity[a];
                    res.addOnComponent.quantityName = this.quantityName[a]; //Added by nidhi
                    this.quantity[a] = this.quantity[a];
                    this.quantityName[a] = this.quantityName[a]; //Added by nidhi
                  }
                  // console.log(this.quantity[a]);
                }
                if (this.makeOptions[a] != "") {
                  for (let item of this.AddOns) {
                    if (item.colValueDesc === this.makeOptions[a] && this.addOndet[a].categoryDetCode === item.addOnCompo_cd) {
                      res.addOnComponent.makeId = item.comp_detail_id;
                      this.makeId[a] = item.comp_detail_id;
                      this.makeOptions[a] = this.makeOptions[a];
                    }
                  }
                }
                if (this.subType1[a] != "") {
                  for (let item of this.AddOns) {
                    if (item.colValueDesc === this.subType1[a] && this.addOndet[a].categoryDetCode === item.addOnCompo_cd) {
                      res.addOnComponent.subtype1Id = item.comp_detail_id;
                      this.subtype1Ids[a] = item.comp_detail_id;
                      this.subType1[a] = this.subType1[a];
                    }
                  }
                }
                if (this.subtype2[a] != "") {
                  for (let item of this.AddOns) {
                    if (item.colValueDesc === this.subtype2[a] && this.addOndet[a].categoryDetCode === item.addOnCompo_cd) {
                      res.addOnComponent.subtype2Id = item.comp_detail_id;
                      this.subtype2Ids[a] = item.comp_detail_id;
                      this.subtype2[a] = this.subtype2[a];
                    }
                  }
                }
              }
            }
          }
          this.send();
          if (this.overWrittenFinalAddOnCost != 0) {
            this.enableOverWrite = true;
          }
        }
        else {
          this.addOndet = res.dropDownColumnvalues.addOnComponents.ADD_ON_COMPONENTS;
        }
      })
      // for (let b = 0; b < this.addOndet.length; b++) {
      //   this.addonComponentsFull.push(this.newArr);
      // }
      console.log(this.addonComponentsFull)
      this.AddOns = res.dropDownColumnvalues.addOnCompoInDetailList.ADD_ON_COMPO_DETAILS;

      // for (let a = 1; a < this.AddOns.length; a++) {
      //   if (this.AddOns[a].columnName == "MAKE") {
      //     if (this.AddOns[a - 1].columnName != "MAKE") {
      //       this.AddOns[a].flag = true;
      //       console.log("item found", this.make[a]);
      //     }
      //   }
      //   if (this.AddOns[a].columnName == "SUB_TYPE1") {
      //     if (this.AddOns[a - 1].columnName != "SUB_TYPE1") {
      //       this.AddOns[a].flag = true;
      //       console.log("item found", this.make[a]);
      //     }
      //   }
      //   if (this.AddOns[a].columnName == "SUB_TYPE2") {
      //     if (this.AddOns[a - 1].columnName != "SUB_TYPE2") {
      //       this.AddOns[a].flag = true;
      //       console.log("item found", this.make[a]);
      //     }
      //   }
      // }


      // console.log(this.AddOns);

      console.log(this._ITOcustomerRequirementService.saveBasicDet);
      for (let c = 0; c < this.filteredValues.length; c++) {
        this.newArr = [];
        // for (let a = 0; a < this.AddOns.length; a++) {
        //   if (this.addOndet[c].categoryDetCode == this.AddOns[a].addOnCompo_cd) {
        //     this.newArr.push(this.AddOns[a]);
        //     this.addonComponentsFull[c] = this.newArr;

        //   }
        // }
        if (this._ITOcustomerRequirementService.saveBasicDet.power >= 3) {
          if (this.filteredValues[c].dropDownType.code === "GB") {
            this.disableArray[c] = false;
          }
          else {
            this.disableArray[c] = true;
          }
        }
        else {
          this.disableArray[c] = true;
        }
      }
      // console.log(this.disableArray);

      for (let addOnCom of this.addonComponentsFull) {
        for (let indiv of addOnCom) {
          if (indiv.columnName == "MAKE") {
            this.make.push(indiv);
            // this.map.set("MAKE", this.make);

          }
          if (indiv.columnName == "SUB_TYPE1") {
            this.subtype1Id.push(indiv);
            // this.map.set("SUB_TYPE1", this.subtype1Id);

          }
          if (indiv.columnName == "SUB_TYPE2") {
            this.subtype2Id.push(indiv);
            // this.map.set("SUB_TYPE2", this.subtype2Id);

          }
        }
      }


      for (let a = 0; a < this.AddOns.length; a++) {

        this.takeMaxCost[a] = 0;
        this.takeExcelCost[a] = 1;
        this.formName.push(this.AddOns[a].categoryDetDesc);
      }
    })
    this.othersAddonList = [];
  }

  ngOnInit() {

  }
  ngAfterContentChecked() {
    this.dispPrevComments = this._ITOAddOnComponentsService.dispPrevComments;
    this.oldComms = this._ITOAddOnComponentsService.oldComms;
    for (let k = 0; k < this.excelAddOnCostRes.length; k++) {
      if (this.excelAddOnCostRes[k] != null) {
        this.sampleExel.push(this.excelAddOnCostRes[k]);
      }
    }
  }

  ngAfterViewChecked() {
    this.optionArray = [];
    this.sampleExel = [];

    // console.log(this.filteredValues);
    for (let f = 0; f < this.filteredValues.length; f++) {
      this.tempOpts = [];
      for (let a = 0; a < this.newAddOnComp.length; a++) {
        if (this.filteredValues[f].dropDownType.code == this.newAddOnComp[a].addOnCompo_cd) {
          this.tempOpts.push(this.newAddOnComp[a]);
        }
      }
      this.optionArray[f] = this.tempOpts;
    }
  }
  /**
   * 
   * @param addOndetails addonForm details
   * @param j index of the addon
   */
  getPriceAddOn(addOndetails, j) {
    console.log(addOndetails, this.makeOptions[j])
    this.eligibleToOW[j] = true;
    let make = null;
    let sub1 = null;
    let sub2 = null;
    let qty = 0;
    make = addOndetails.make;
    sub1 = addOndetails.subType1;
    sub2 = addOndetails.subType2;
    qty = addOndetails.qty;
    if (make == undefined) {
      make = null;
    }
    if (sub1 == undefined) {
      sub1 = null;
    }
    if (sub2 == undefined) {
      sub2 = null;
    }
    if (qty == undefined) {
      qty = null;
    }
    console.log(this.filteredValues[j].addOnValueList)
    //compare the values with the available list
    for (let p = 0; p < this.filteredValues[j].addOnValueList.length; p++) {
      console.log(this.filteredValues[j].addOnValueList[p].make == make)
      console.log(this.filteredValues[j].addOnValueList[p].subtype1 == sub1)
      console.log(this.filteredValues[j].addOnValueList[p].subtype2 == sub2)
      console.log(this.filteredValues[j].addOnValueList[p].quantityName == qty)
      if (this.filteredValues[j].addOnValueList[p].make == make && this.filteredValues[j].addOnValueList[p].subtype1 == sub1 &&
        this.filteredValues[j].addOnValueList[p].subtype2 == sub2 && this.filteredValues[j].addOnValueList[p].quantityName == qty) {
        console.log(this.filteredValues[j].addOnValueList[p].excelCost);
        this.excelAddOnCost[j] = this.filteredValues[j].addOnValueList[p].excelCost;
        this.excelAddOnCostRes[j] = this.filteredValues[j].addOnValueList[p].excelCost;
        this.makeId[j] = this.filteredValues[j].addOnValueList[p].makeId;
        this.subtype1Ids[j] = this.filteredValues[j].addOnValueList[p].subtype1Id;
        this.subtype2Ids[j] = this.filteredValues[j].addOnValueList[p].subtype2Id;
        this.quantity[j] = this.filteredValues[j].addOnValueList[p].quantity;
        this.quantityName[j] = this.filteredValues[j].addOnValueList[p].quantityName; //Added by nidhi change in condition as well
        console.log(`cost${j}`);
        this.changeBorderColor(j, "green");
      }
    }
  }
  //un used function
  closeDialog(Intege: number) {
    this._ITOAddOnComponentsService.dispPrevComments = false;
    this._ITOAddOnComponentsService.oldComms = null;
  }
  //changing border color of the cost field
  changeBorderColor(n, color) {
    document.getElementById(`cost${n}`).style.borderColor = color;
    document.getElementById(`cost${n}`).style.borderWidth = "2px";
  }
  // saving data to local storage
  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.addOnsData[key] = this.storage.get(key);
  }

  /**
   * 
   * @param e event on button click
   * @param item checked item (item)
   * @param ind index of he add on
   */
  checkedAddOns(e, item, ind) {
    this.sampleExel = [];
    for (let i = 0; i < item.addOnValueList.length; i++) {
      if (item.addOnValueList[i].make == null && item.addOnValueList[i].subtype1 == null &&
        item.addOnValueList[i].subtype2 == null && item.addOnValueList[i].quantityName == null) {
        this.excelAddOnCost[ind] = item.addOnValueList[i].excelCost; //check condition nidhi
        this.excelAddOnCostRes[ind] = item.addOnValueList[i].excelCost;
        this.takeExcelCost[ind] = 1;
        this.takeMaxCost[ind] = 0;
        this.eligibleToOW[ind] = true;
      }
    }
    console.log(this.selAddOns);
    if (e.target.checked) {
      this.selAddOns.push(item);
      this.hideDiv[ind] = true;
      // console.log(this.hideDiv);
      // this.addOndet[ind].defaultVal = true;
    }
    else if (!e.target.checked) {
      for (let s = 0; s < this.selAddOns.length; s++) {
        if (this.selAddOns[s].dropDownType.code == item.dropDownType.code) {
          let i = s;
          this.selAddOns.splice(i, 1);
          this.hideDiv[ind] = false;
          this.makeOptions[ind] = null;
          this.subType1[ind] = null;
          this.subtype2[ind] = null;
          this.quantity[ind] = null;
          this.quantityName[ind] = null; //added by nidhi
          this.excelAddOnCost[ind] = null;
          this.excelAddOnCostRes[ind] = null;
          this.remarks = null;
          console.log(this.hideDiv);
          // this.addOndet[ind].defaultVal = false;
          break;
        }
      }
    }
    console.log(this.selAddOns);
    console.log(this.othersCheck);

    if (this.selAddOns.length == 0) {
      console.log(0);
      if (this.othersCheck) {
        this.getTotalBtn = true;
      } else {
        console.log("dsbgsdbgbsdjgbjksdbg")
        this.getTotalBtn = false;
        this.finalAddOnArray = [];
      }
    }

    //submit button should be enabled only when there is items in the added list
    else if (this.selAddOns.length > 0) {
      console.log(1);
      this.getTotalBtn = true;
    }
  }

  //unused function
  impotFExcel(ind, addOns) {
    // console.log(ind, addOns);
  }


  //unused function
  call(addOns) {
    this.newAddOnCompOpt = [];
    for (let a = 0; a < this.newAddOnComp.length; a++) {
      if (addOns.addOnCompo_cd == this.newAddOnComp[a].addOnCompo_cd) {
        this.newAddOnCompOpt.push(this.newAddOnComp[a]);
      }
    }
  }

  changed() {
    // console.log(this.makedup);
    // console.log("okay");
    // this.lastMsg = "okay";
  }

  //getting previous comments
  getPrevComments() {
    this.showMsg = false;
    this.errorMsg = '';
    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "ADD_ON";
    this._ITOcustomerRequirementService.saveBasicDet.groupCode = "OVERWRITE";
    this._ITOAddOnComponentsService.getQuotRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(prevComRes => {
      console.log(prevComRes);
      if (prevComRes.remarksList.length > 0) {
        this._ITOAddOnComponentsService.dispPrevComments = true;
        this._ITOAddOnComponentsService.oldComms = prevComRes.remarksList;
      } else {
        this.messagediv = true;
        this.successMsg = "No Previous Comments found";
      }
    });
  }

  //not using
  addOn(addOn, addOns, ind) {

    this._ITOAddOnComponentsService.getQuotationList().subscribe(res => {
      res.addOnComponent.addOnCompoId = addOns.categoryDetId;
      if (addOn.ipValue != "") {
        if (addOns.categoryDetCode === "ATI") {
          this.takeMaxCost[ind] = 1;
          this.quantity[ind] = addOn.ipValue;
        }
        else {
          res.addOnComponent.quantity = addOn.ipValue;
          this.quantity[ind] = addOn.ipValue;
        }
      }
      if (addOn.ddValue != "") {
        for (let item of this.AddOns) {
          if (item.colValueDesc === addOn.ddValue && addOns.categoryDetCode === item.addOnCompo_cd) {
            res.addOnComponent.makeId = item.comp_detail_id;
            this.makeId[ind] = item.comp_detail_id;
            this.makeOptions[ind] = addOn.ddValue;
          }
        }
      }
      if (addOn.dd2Value != "") {
        for (let item of this.AddOns) {
          if (item.colValueDesc === addOn.dd2Value && addOns.categoryDetCode === item.addOnCompo_cd) {
            res.addOnComponent.subtype1Id = item.comp_detail_id;
            this.subtype1Ids[ind] = item.comp_detail_id;
            this.subType1[ind] = addOn.dd2Value;
          }
        }
      }
      if (addOn.dd3Value != "") {
        for (let item of this.AddOns) {
          if (item.colValueDesc === addOn.dd3Value && addOns.categoryDetCode === item.addOnCompo_cd) {
            res.addOnComponent.subtype2Id = item.comp_detail_id;
            this.subtype2Ids[ind] = item.comp_detail_id;
            this.subtype2[ind] = addOn.dd3Value;
          }
        }
      }
      res.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;

      if (addOns.categoryDetCode === "ATI") {
        this.takeMaxCost[ind] = 1;
      }
      else {
        this._ITOAddOnComponentsService.getAddOnExcelPrice(res).subscribe(resExecel => {
          console.log(resExecel);
          for (let c = 0; c < this.AddOns.length; c++) {
            if (this.AddOns[c].addOnCompo_cd == addOns.categoryDetCode && this.AddOns[c].columnName == "COST") {
              this.ExcelCost[ind] = resExecel.addOnComponent.excelCost;
              this.maxCost[ind] = resExecel.addOnComponent.excelCost;
            }
            // console.log(this.ExcelCost[ind], this.maxCost[ind]);
          }

          // console.log(this.ExcelCost[ind]);
        })
      }
    })
  }

  // over write functionality
  OverWrite(ind) {
    this.enableOverWrite = true;
    this.changeBorderColor(ind, "grey");
    this.maxCost[ind] = '';
    this.takeMaxCost[ind] = 1;
    this.takeExcelCost[ind] = 0;
  }

  //check cost when over writing
  /**
   * 
   * @param event event on muse click
   * @param i index
   */
  onKey(event, i) {
    if ((event.target.value - this.excelAddOnCostRes[i]) > 0) {
      this.showMsg = false;
    } else if ((event.target.value - this.excelAddOnCostRes[i]) <= 0) {
      this.showMsg = true;
      this.errorMsg = `value should be greater than ${this.excelAddOnCostRes[i]}`;
    }
  }
  // not used
  remarksForm(i) {
    this.changeBorderColor(i, "rgb(213,120,23)");
    this.takeMaxCost[i] = 1;
  }

  //disable overwritten div
  cancelOverWrite(ind) {
    this.overWrittenFinalAddOnCost = 0;
    this.enableOverWrite = false;
    this.excelAddOnCost[ind] = this.excelAddOnCostRes[ind];
    this.takeMaxCost[ind] = 0;
    this.takeExcelCost[ind] = 1;
    this.remarks = '';
    this.showMsg = false;
  }

  // opening new table for others
  openTable(event) {
    if (event.target.checked) {
      this.displayOthersTable = true;
      this.getTotalBtn = true;
    }
    else if (!event.target.checked) {
      this.displayOthersTable = false;
      this.othersAddonList = [];
      if (this.selAddOns.length > 0) {
        console.log(1);
        this.getTotalBtn = true;
      } else {
        this.getTotalBtn = false;
      }
    }

  }
  cancelnewLine() {
    this.displaynewLine = false;
  }
  // adding new other lines
  addRows() {
    this.displaynewLine = true;
  }
  // delete the added line for others
  cancelLines(i) {
    this.othersAddonList.splice(i, 1);
  }

  // validate the input value , not using
  checkIp(event, adon) {
    if (adon.categoryDetCode === "VE" || adon.categoryDetCode === "OMS") {
      if (event.target.value > 2) {
        this.validationMsg = "Value should be less than or equal to 2";
        for (let q = 0; q < this.addOndet.length; q++) {
          if (adon.categoryDetCode === this.addOndet[q].categoryDetCode) {
            this.validationMsgdisp[q] = true;
          }
        }
      }
      else {
        this.validationMsg = "";
        this.validationMsgdisp = [false];
      }

    }
    console.log(this.validationMsg);
  }
  /**
   * 
   * @param others ohers from data
   * @param othersfrm others form
   */
  othersForm(others, othersfrm: NgForm) {
    console.log(others);
    this.othersAddonList.push(others);
    console.log(this.othersAddonList);
    othersfrm.reset();
    this.displaynewLine = false;
  }

  /**
   * to get total cost
   */
  send() {
    this.lastMsg = '';
    this.finalAddOnArray = [];
    this.finalAddOnCost = 0;
    console.log(this.selAddOns.length, this.excelAddOnCostRes.length);
    for (let s = 0; s < this.selAddOns.length; s++) {
      for (let a = 0; a < this.filteredValues.length; a++) {
        if (this.filteredValues[a].dropDownType.code == this.selAddOns[s].dropDownType.code) {
          this.finalAddOn = new addOnComponentsFields('');
          this.finalAddOn.addOnCompoId = this.selAddOns[s].addOnValueList[0].addOnCompoId;
          this.finalAddOn.excelCost = this.excelAddOnCostRes[a];
          this.finalAddOn.excelCostFlag = this.takeExcelCost[a];
          this.finalAddOn.selectedCost = this.excelAddOnCost[a];
          this.finalAddOn.selectedCostFlag = this.takeMaxCost[a];
          this.finalAddOn.makeId = this.makeId[a];
          this.finalAddOn.quantity = this.quantity[a];
          this.finalAddOn.quantityName = this.quantityName[a]; //added by nidhi
          this.finalAddOn.subtype1Id = this.subtype1Ids[a];
          this.finalAddOn.subtype2Id = this.subtype2Ids[a];
          // this.finalAddOn.comp_detail_id = this.makeId[a];
          console.log(this.finalAddOn);
          this.finalAddOnArray.push(this.finalAddOn);
        }
      }
    }

    if (this.othersAddonList != []) {
      for (let o = 0; o < this.othersAddonList.length; o++) {
        this.finalAddOn = new addOnComponentsFields('');
        this.finalAddOn.excelCost = this.othersAddonList[o].newCompPrice;
        this.finalAddOn.selectedCost = this.othersAddonList[o].newCompPrice;
        this.finalAddOn.newCompRemark = this.othersAddonList[o].newCompRemark;
        this.finalAddOn.quantity = this.othersAddonList[o].newCompQty;
        this.finalAddOn.quantityName = this.othersAddonList[o].newCompQty;//added by nidhi
        this.finalAddOn.name = this.othersAddonList[o].newCompName;
        console.log(this.finalAddOn);
        this.finalAddOnArray.push(this.finalAddOn);
      }

    }
    console.log(this.finalAddOnArray);
    for (let s = 0; s < this.finalAddOnArray.length; s++) {
      this.addOnCosts.push(this.finalAddOnArray[s].selectedCost);
      this.finalAddOnCost += this.finalAddOnArray[s].selectedCost;
      console.log(this.finalAddOnCost);
    }

    if (this.tempFinal !== this.finalAddOnCost) {
      this.overWrittenFinalAddOnCost = 0;
      this.enableOverWrite = false;
    }

  }

  /**
   * final submit 
   */
  finalSubmitAddOn() {
    console.log(this.samplResp, this.enableOverWrite);
    this.tempFinal = this.finalAddOnCost;
    this.samplResp.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    console.log(this.finalAddOnArray)
    this.samplResp.submittedAddOnList = this.finalAddOnArray;
    if (this.enableOverWrite) {
      this.samplResp.addOnComponent.selectedCost = this.overWrittenFinalAddOnCost;
      this.samplResp.addOnComponent.selectedCostFlag = 1;
      this.samplResp.addOnComponent.overwrittenRemarks = this.remarks;
    } else {
      this.samplResp.addOnComponent.selectedCostFlag = 0;
    }
    this.addOnCosts = [];
    //calling save 
    this._ITOAddOnComponentsService.saveAddOnPrice(this.samplResp).subscribe(resAddOn => {
      console.log(resAddOn);
      this.lastMsg = "Add on cost got updated..";
      this.saveInLocal(this.addOnDetails, {
        hideDiv: this.hideDiv, ExcelCost: this.excelAddOnCost,
        maxCost: this.excelAddOnCostRes, subType1: this.subType1, subtype2: this.subtype2, makeOptions: this.makeOptions,
        quantity: this.quantity, quantityName: this.quantityName, addOndet: this.addOndet, othersAddonList: this.othersAddonList, selAddOns: this.selAddOns,
        remarks: this.remarks, enableOverWrite: this.enableOverWrite, finalAddOnCost: this.finalAddOnCost, overWrittenFinalAddOnCost: this.overWrittenFinalAddOnCost
      });
      if (resAddOn.successCode == 0) {

        this.messagediv = true;
        this.successMsg = "Cost saved Successfully";

        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'VAR_COST') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }

        if (this._ITOcustomerRequirementService.editFlag) {
          this._ITOcustomerRequirementService.editFlag = false;
          this._Router.navigate(['/EditQuot']);
        } else {
          this.messagediv = true;
          this.successMsg = "Cost saved Successfully";
        }

        // call one line bom
        this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resAdd => {
          console.log(resAdd);
          this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);
          this._ITOcustomerRequirementService.sendturBtnStatus(true);
        });
      }
    });
  }
}
