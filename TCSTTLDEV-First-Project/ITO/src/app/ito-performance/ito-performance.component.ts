import { Component, OnInit } from '@angular/core';
import { ItoPerformanceService } from './ito-performance.service';
import { MatTableModule } from '@angular/material';
import { dboClass } from './ito-performance';
import { NgForm } from '@angular/forms';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOGeneralInputsService } from '../ito-general-inputs/ito-general-inputs.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';

@Component({
  selector: 'app-ito-performance',
  templateUrl: './ito-performance.component.html',
  styleUrls: ['./ito-performance.component.css']
})
export class ItoPerformanceComponent implements OnInit {

  hideprogress: boolean = false;
  editForm: any;
  dboPerfList3: Array<any> = []; //To store temp DC data 
  surfCond: Array<any> = [];
  lhsnewLine: boolean = false;
  lhsRhsnewLine: boolean = false;
  purityList: Array<any> = [];
  scfmList: Array<any> = [];
  lubeOilList: Array<any> = [];
  itemPurityList: Array<any> = [];
  itemList: Array<any> = [];
  identList: Array<any> = [];
  freshList: Array<any> = [];
  fixedList: Array<any> = [];
  cirList: Array<any> = [];
  auxSteamList: Array<any> = [];
  controlList: Array<any> = [];
  id: string;
  name: string;
  dateOfBirth: Date;
  address: string;
  definedColumns: Array<any> = [];
  definedColumnsNew: Array<any> = [];
  definedColumnsNew1: Array<any> = [];
  definedColumnsNew2: Array<number> = [];
  enable: boolean = false;
  defaultvaluesdropdown: Array<any> = [];
  defaultvaluesdropdownNew: Array<any> = [];
  dboPerformanceLocal: Array<any> = [];
  dboPerfLoc: string = 'dboPerf'; // local storage value
  defaultvalue = 2;
  index = 0;
  value: number = 2;
  dboPerf: Array<any> = [];
  dboPerfNew: Array<any> = [];
  Uimage: string;
  techRemarks: string;
  image: string;
  definedColumnsNew3: Array<any> = [];
  definedColumnsNew4: Array<any> = [];
  hmbd: boolean = true;
  sum: any;
  defaultvalue1: string = "Yes";
  defaultvalue2: string = "Yes";
  dboFormDataaa: any; //To store dbo form response
  quotId: number; //To store quotation id from customer requirent service
  performanceData: Array<any> = [];
  dboClass: dboClass;
  savePerformanceList1: Array<any> = [];
  savePerformanceList2: Array<any> = [];
  display: boolean = false;
  dboperformance1: Array<any> = [];
  dboperformance2: Array<any> = [];
  dboperformance: Array<any> = [];
  tableFil: boolean = false;
  defaulValue: Array<any> = [];
  test1: string = "No File Choosen";
  unitList: Array<any> = [];
  acList: Array<any> = [];
  auxList: Array<any> = [];;
  auxSteamValue: string;
  instrCntrl: string;
  scfm: string;
  dboPerformanceList1: Array<any> = [];
  dboPerformanceList2: Array<any> = [];
  dboPerformanceList3: Array<any> = [];
  dummy: any;
  perfDetail: Array<any> = [];
  savePerformanceList3: Array<any> = [];
  tempArray: Array<any> = [];
  perfTabHmbd: Array<any> = [];
  dialogMsgApp: boolean = false;
  dialogMsgVal: string = '';
  backBtn: boolean = false;
  enableDcTab: boolean = false; // To enable DC table in UI based on data.
  addBtnEnb: boolean = false; //To display add button for DC consumer tabel
  arry: Array<any> = [];
  arry1: Array<any> = [];
  scopeofsupp: string = 'scopeOfsup';
  mainSave: boolean = true;
  appDisable: boolean = false;

  constructor(private _ItoPerformanceService: ItoPerformanceService, private _ITOeditQoutService: ITOeditQoutService, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    private _ITOCostEstimationService: ITOCostEstimationService,
    private router: Router, private route: ActivatedRoute, private _ITOGeneralInputsService: ITOGeneralInputsService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService, ) {
    this.hideprogress = true;
    if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm != "Standard") {
      this.addBtnEnb = true;
      this.appDisable = false;
    } else {
      this.appDisable = true;
    }
    this._ITOeditQoutService.button1 = false;
    this._ITOeditQoutService.button2 = false;
    this._ITOeditQoutService.button3 = false;
    this._ITOeditQoutService.button4 = false;
    this._ITOeditQoutService.button5 = false;
    this._ITOeditQoutService.button6 = false;
    this._ITOeditQoutService.button7 = false;
    this._ITOeditQoutService.button8 = true;
    this._ITOeditQoutService.button9 = false;

    this._ITOeditQoutService.getQuotation(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resss => {
      console.log(resss);
      this.editForm = resss;
      this.storage.remove(this.dboPerfLoc);
      this._ITOeditQoutService.savePerformanceDataList1 = resss.savePerformanceDataList1;
      this._ITOeditQoutService.savePerformanceDataList2 = resss.savePerformanceDataList2;
      this._ITOeditQoutService.savePerformanceDataList3 = resss.savePerformanceList3;

      if (this._ITOcustomerRequirementService.editFlag == true) {
        this.dboperformance1 = this._ITOeditQoutService.savePerformanceDataList3;
        this.dboperformance2 = this._ITOeditQoutService.savePerformanceDataList2;
        this.dboperformance = this._ITOeditQoutService.savePerformanceDataList1;
      }
      if (this._ITOeditQoutService.checkEdit == false) {
        this.backBtn = true;
      }
      if (this.storage.get(this.dboPerfLoc) == null) {
        this.saveInLocal(this.dboPerfLoc, {
          savePerformanceList1: this.savePerformanceList1, unitList: this.unitList, dboPerfNew: this.dboPerfNew, savePerformanceList2: this.savePerformanceList2,
          defaultvalue: this.defaultvalue, hmbdTableInput: 0, conditionTableInput: 0,
          savePerformanceList3: this.savePerformanceList3, cirList: this.cirList, identList: this.identList, itemList: this.itemList, fixedList: this.fixedList
        });
      }
      this._ItoPerformanceService.getDboFormData().subscribe(res => {
        console.log(res);
        this.dboFormDataaa = res;
        this.tempArray = [];
        this.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        this.dboPerformanceLocal[this.dboPerfLoc] = this.storage.get(this.dboPerfLoc);
        console.log(this.storage.get(this.dboPerfLoc));
        if (this.storage.get(this.dboPerfLoc) != null) {
          if (this.storage.get(this.dboPerfLoc).savePerformanceList1.length != 0) {
            this.tempArray = this.storage.get(this.dboPerfLoc).savePerformanceList1;
            this.mainSave = false;
          }
          if (this.tempArray.length > 0) {
            for (let b = 0; b < this.tempArray.length; b++) {
              console.log(this.tempArray[b].itemNm, this.tempArray[b].subItemNm);
              if (this.tempArray[b].itemNm == 'Performance Table / HMBD') {
                this.perfTabHmbd.push(this.tempArray[b]);
              }
              else if (this.tempArray[b].subItemNm == 'Auxiliary Steam') {
                this.auxSteamList.push(this.tempArray[b]);
              }
              else if (this.tempArray[b].subItemNm == 'Instrument Control Air @ 7 / Kg / Cm² g') {
                this.controlList.push(this.tempArray[b]);
              }
              else if (this.tempArray[b].subItemNm == 'Lube Oil For Initial Fill & Flushing') {
                this.lubeOilList.push(this.tempArray[b]);
              }
              else if (this.tempArray[b].subItemNm == 'AC,DC Power Consumption') {
                this.dboPerformanceList3.push(this.tempArray[b]);
              }
              else if (this.tempArray[b].subItemNm == 'Auxiliary Cooling Water') {
                this.freshList.push(this.tempArray[b]);
              }
              else if (this.tempArray[b].subItemNm == 'Surface Condenser Cooling Water') {
                this.surfCond.push(this.tempArray[b]);
              }
            }
            this.continuSum(0);
            this.continuSumT(0);
            console.log(this.dboPerformanceList3);
            let i = 0;
            if (this.auxSteamList.length > 0) {
              i = i + 1;
              this.arry[0].value = i;
            }
            if (this.freshList.length > 0) {
              i = i + 1;
              this.arry[1].value = i;
            }
            if (this.surfCond.length > 0) {
              i = i + 1;
              this.arry[2].value = i;
            }
            if (this.controlList.length > 0) {
              i = i + 1;
              this.arry[3].value = i;
            }
            if (this.lubeOilList.length > 0) {
              i = i + 1;
              this.arry[4].value = i;
            }
            if (this.dboPerformanceList3.length > 0) {
              i = i + 1;
              this.arry[5].value = i;
            }
            // let dcItemList = this.dboPerformanceList3.map(item => item.itemType);
            // if (dcItemList.includes('DC')){
            // this.enableDcTab = true;
            // this.dboPerfList3 = [];
            // for(let b=0; b<this.dboPerformanceList3.length; b++){
            //   if(this.dboPerformanceList3[b].itemType == "DC"){
            //     this.dboPerfList3.push(this.dboPerformanceList3[b]);
            //     }
            //  }
            // }
            this.mainSave = false;

          }
          if (this.storage.get(this.dboPerfLoc).cirList.length != 0) {
            this.cirList = this.storage.get(this.dboPerfLoc).cirList;
          }
          if (this.storage.get(this.dboPerfLoc).fixedList.length != 0) {
            this.fixedList = this.storage.get(this.dboPerfLoc).fixedList;
          }
          if (this.storage.get(this.dboPerfLoc).identList.length != 0) {
            this.identList = this.storage.get(this.dboPerfLoc).identList;
          }
          if (this.storage.get(this.dboPerfLoc).itemList.length != 0) {
            this.itemList = this.storage.get(this.dboPerfLoc).itemList;
            this.mainSave = false;

          }
        }
        this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
        if (this._ITOcustomerRequirementService.editFlag == true && this.tempArray.length == 0 && this.dboperformance2 != undefined && this.dboperformance.length > 0) {
          console.log(this.dboperformance);
          console.log(this._ITOeditQoutService.identListData);
          this.tempArray = this.dboperformance;
          for (let b = 0; b < this.tempArray.length; b++) {
            console.log(this.tempArray[b].itemNm, this.tempArray[b].subItemNm);
            if (this.tempArray[b].itemNm == 'Performance Table / HMBD') {
              this.perfTabHmbd.push(this.tempArray[b]);
            }
            else if (this.tempArray[b].subItemNm == 'Auxiliary Steam') {
              this.auxSteamList.push(this.tempArray[b]);
            }
            else if (this.tempArray[b].subItemNm == 'Instrument Control Air @ 7 / Kg / Cm² g') {
              this.controlList.push(this.tempArray[b]);
            }
            else if (this.tempArray[b].subItemNm == 'Lube Oil For Initial Fill & Flushing') {
              this.lubeOilList.push(this.tempArray[b]);
            }
            else if (this.tempArray[b].subItemNm == 'AC,DC Power Consumption') {
              this.dboPerformanceList3.push(this.tempArray[b]);
            }
            else if (this.tempArray[b].subItemNm == 'Auxiliary Cooling Water') {
              this.freshList.push(this.tempArray[b]);
            }
            else if (this.tempArray[b].subItemNm == 'Surface Condenser Cooling Water') {
              this.surfCond.push(this.tempArray[b]);
            }
            this.mainSave = false;

          }
          this.continuSum(0);
          this.continuSumT(0);
          var dummy = this.dboPerformanceList3[0];
          console.log(dummy);

          dummy.startUp = "-";
          dummy.continuous = "7.5";
          this.dboPerformanceList3.splice(1, 0, dummy);
          console.log(this.dboPerformanceList3);
          this.cirList = this._ITOeditQoutService.cirListData;
          this.fixedList = this._ITOeditQoutService.fixedListData;
          this.itemList = this._ITOeditQoutService.itemListData;
          this.identList = this._ITOeditQoutService.identListData;
          let i = 0;
          if (this.auxSteamList.length > 0) {
            i = i + 1;
            this.arry[0].value = i;
          }
          if (this.freshList.length > 0) {
            i = i + 1;
            this.arry[1].value = i;
          }
          if (this.surfCond.length > 0) {
            i = i + 1;
            this.arry[2].value = i;
          }
          if (this.controlList.length > 0) {
            i = i + 1;
            this.arry[3].value = i;
          }
          if (this.lubeOilList.length > 0) {
            i = i + 1;
            this.arry[4].value = i;
          }
          if (this.dboPerformanceList3.length > 0) {
            i = i + 1;
            this.arry[5].value = i;
          }
          // let dcItemList = this.dboPerformanceList3.map(item => item.itemType);
          // if (dcItemList.includes('DC')){
          // this.enableDcTab = true;
          // this.dboPerfList3 = [];
          // for(let b=0; b<this.dboPerformanceList3.length; b++){
          //   if(this.dboPerformanceList3[b].itemType == "DC"){
          //     this.dboPerfList3.push(this.dboPerformanceList3[b]);
          //     }
          //  }
          // }
        }
        else if (this.tempArray.length == 0) {
          this.getPerform();
        }

        if (this.dboperformance2 != undefined) {
          if (this.dboperformance2.length != 0 && this._ITOcustomerRequirementService.editFlag == true && this.dboperformance1[0].conditionTableInput == 1) {
            this.dboPerf = [];
            this.value = this.dboperformance1[0].noOfConditions + 1;;
            this.defaultvalue = this.dboperformance1[0].noOfConditions;
            this.display = true;
            this.hmbd = true;
            this.arry1[0].value = 1;
            this.arry1[1].value = 2;
            this.defaulValue = [];
            this.definedColumnsNew = this.dboperformance2;
            for (let z = 0; z < this.dboperformance2.length; z++) {
              this.defaulValue[z] = this.dboperformance2[z].unitNm;
            }
            this.tableFil = true;

            this.unitList = [];
            this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;

            this.dboFormDataaa.noOfConditions = this.dboperformance1[0].noOfConditions;
            this._ItoPerformanceService.getPerformanceParam(this.dboFormDataaa).subscribe(resppp1 => {

              this.unitList = resppp1.unitList;
            });

            for (let j = 0; j < this.definedColumnsNew.length; j++) {

              this.dboPerfNew.push([j]);

              for (let k = 0; k < 12; k++) {
                if (k == 0) {
                  this.dboPerfNew[j][k] = null;
                }
                else if (k == 1) {
                  this.dboPerfNew[j][k] = this.definedColumnsNew[j]["guaranteed"];
                }
                else {


                  this.dboPerfNew[j][k] = this.definedColumnsNew[j]["cond" + (k - 1)];
                }
              }
              // this.dboPerf[j]
              // {

              // }
            }


            this.defaultvalue1 = "Yes";
            this.mainSave = false;

          }
          if (this.dboperformance1.length != 0 && this._ITOcustomerRequirementService.editFlag == true && this.dboperformance1[0].conditionTableInput == 0) {
            this.defaultvalue1 = "No";
            this.hmbd = false;
            this.arry1[0].value = 1;
            this.arry1[1].value = 1;
            this.image = this.dboperformance1[0].hmbdImage;
            this.tableFil = true;
            this.display = false;
            this.mainSave = false;

          }
          if (this.dboperformance1.length != 0 && this.dboperformance1[0].hmbdTableInput == 1) {
            this.defaultvalue2 = "Yes";
            this.mainSave = false;

          } else if (this.dboperformance1.length != 0 && this.dboperformance1[0].hmbdTableInput == 0) {
            this.defaultvalue2 = "No";
            this.mainSave = false;

          }
        }

        if (this.storage.get(this.dboPerfLoc).savePerformanceList3.length != 0) {
          this.savePerformanceList3 = this.storage.get(this.dboPerfLoc).savePerformanceList3;
          this.mainSave = false;

        }
        if (this.savePerformanceList3.length != 0) {
          if (this.savePerformanceList3[0].hmbdTableInput != 0) {
            this.defaultvalue2 = "Yes";
          } else {
            this.defaultvalue2 = "No";
          }
          if (this.savePerformanceList3[0].conditionTableInput != 0) {
            this.defaultvalue1 = "Yes";
          } else {
            this.defaultvalue1 = "No";
            this.hmbd = false;
          }
          this.mainSave = false;

        }
        if (this.storage.get(this.dboPerfLoc).dboPerfNew.length != 0) {
          this.dboPerfNew = this.storage.get(this.dboPerfLoc).dboPerfNew;
          this.definedColumnsNew = this.storage.get(this.dboPerfLoc).savePerformanceList2;
          this.value = this.savePerformanceList3[0].noOfConditions + 1;
          this.defaultvalue = this.savePerformanceList3[0].noOfConditions;
          this.display = true;
          this.tableFil = true;
          this.hmbd = false;
          this.arry1[0].value = 1;
          this.arry1[1].value = 1;
          this.unitList = this.storage.get(this.dboPerfLoc).unitList;
          for (let z = 0; z < this.definedColumnsNew.length; z++) {
            this.defaulValue[z] = this.definedColumnsNew[z].unitNm;
          }
          this.defaultvalue1 = "No";
          this.mainSave = false;

        }
        // if(this.storage.get(this.dboPerfLoc).savePerformanceList1.length!=0){
        //   this.savePerformanceList1= this.storage.get(this.dboPerfLoc).savePerformanceList1;



        //   this.defaultvalue1="Yes";
        //   this.hmbd=true;

        // }
        if (this.storage.get(this.dboPerfLoc).conditionTableInput != 0) {
          this.hmbd = true;
          this.arry1[0].value = 1;
          this.arry1[1].value = 2;
          this.defaultvalue1 = "Yes";
          this.image = this.savePerformanceList3[0].hmbdImage;
          this.tableFil = true;
          this.display = true;
          this.mainSave = false;

        }
        // if(this.storage.get(this.dboPerfLoc).hmbdTableInput!=0){
        //   this.defaultvalue2="Yes";
        // }
        console.log(this.dboperformance1);
        console.log(this.dboperformance2);


        this.definedColumns = ["Name", "value", "guaranteed", "condition1", "condition2", "condition3", "condition4", "condition5", "condition6", "condition7", "condition8", "condition9", "condition10"];

        this.definedColumnsNew3 = ["Yes", "No"];
        this.definedColumnsNew4 = ["Yes", "No"];
        console.log(this.definedColumns.length);
        // this.definedColumnsNew=["123","test"];
        // this.defaultvalue1="Yes";

        console.log(this.dboPerfNew);
        for (let j = 0; j < this.value; j++) {
          console.log("test");
          console.log(j);
          this.defaultvaluesdropdown.push(j);
        }
        for (let j = 0; j < this.value + 1; j++) {
          console.log("test");
          console.log(j);
          this.defaultvaluesdropdownNew.push(j);
        }
        //defaultvaluesdropdown
        console.log(this.definedColumns.length);
        console.log(this.defaultvaluesdropdown);

        this.definedColumnsNew1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
        this.definedColumnsNew2 = [1, 2, 3, 4, 5, 6, 7];
        console.log(this.definedColumns);
        this.hideprogress = false;
      });
    });
  }
  checkForQuantity(a, b, c, id) {
    this.mainSave = true;

    console.log(this.dboPerfNew);
    //this.dboPerfNew
    console.log(a);
    console.log(b);
    var y = +c;



    if (a == "" || a == " " || a == null) {
      this.dboPerfNew[id][c] = null;
    }
    else {
      this.dboPerfNew[id][c] = a;
    }
    console.log(this.dboPerfNew);
    console.log(this.definedColumns[y + 1]);
    let counter = 0;
    for (let y = 0; y < this.definedColumnsNew.length; y++) {
      for (let q = 0; q < this.defaultvalue; q++) {
        if (this.dboPerfNew[y][q + 1] == "" || this.dboPerfNew[y][q + 1] == " " || this.dboPerfNew[y][q + 1] == null) {
          counter = 1;
          this.tableFil = false;
        }
      }
    }
    if (counter == 0) {
      this.tableFil = true;
    }
  }
  optionSelNew(value, id, columnid) {
    this.mainSave = true;

    this.dboPerfNew[id][columnid] = value;
    console.log(this.dboPerfNew);
  }
  fileChangeEvent(fileInput: any) {
    this.mainSave = true;

    let reader = new FileReader();
    if (fileInput.target.files && fileInput.target.files.length > 0) {
      let file = fileInput.target.files[0];
      reader.readAsDataURL(file);
      console.log(reader.result);
      reader.onload = (fileInput) => {
        this.Uimage = reader.result.split(',')[1];
        this.image = "data:image/jpeg;base64," + this.Uimage;
        console.log(this.image);
      };
      console.log(reader.result);
    }

    this.tableFil = true;

  }
  /**
   * 
   * @param key key value to store in localstorage
   * @param val valueto be stored in localstorage
   */
  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.dboPerformanceLocal[key] = this.storage.get(key);
  }
  mainSaveColor() {
    this.mainSave = true;
  }
  optionSel1(value) {
    this.mainSave = true;

    console.log(value);
    if (value == "Yes") {
      this.hmbd = true;
      this.arry1[0].value = 1;
      this.arry1[1].value = 2;
    }
    else {
      this.hmbd = false;
      this.arry1[0].value = 1;
      this.arry1[1].value = 1;
    }
    this.tableFil = false;
    this.image = null;
    this.Uimage = null;
    this.dboPerfNew = [];
    this.defaultvaluesdropdownNew = [];
    this.defaultvaluesdropdown = [];
    this.value = 1;
    this.defaultvalue = 2;
    this.definedColumnsNew = [];
  }

  optionSel2(val) {
    this.mainSave = true;

    console.log(val);
    this.defaultvalue2 = val;
  }
  optionSel(value) {
    this.mainSave = true;

    let counter = 0;
    for (let y = 0; y < this.definedColumnsNew.length; y++) {
      for (let q = 0; q < this.defaultvalue; q++) {
        if (this.dboPerfNew[y][q + 1] == "" || this.dboPerfNew[y][q + 1] == " " || this.dboPerfNew[y][q + 1] == null) {
          counter = 1;
          this.tableFil = false;
        }
      }
    }
    if (counter == 0) {
      this.tableFil = true;
    }
    this.defaultvaluesdropdownNew = [];
    // this.value=value;
    this.defaultvaluesdropdown = [];
    //Number(value);

    var x = +value;
    x = x + 1;
    for (let j = 0; j < x; j++) {
      console.log("test");
      console.log(j);
      this.defaultvaluesdropdown.push(j);
    }
    for (let j = 0; j < x + 1; j++) {
      console.log("test");
      console.log(j);
      this.defaultvaluesdropdownNew.push(j);
    }






    //  for(let j=0;j<value+1;j++)
    //  {
    //    console.log("test");
    //    console.log(j);
    //    this.defaultvaluesdropdownNew.push(j);
    //  }
    //  for(let j=0;j<x;j++)
    //  {
    //    console.log("test");
    //    console.log(j);
    //    var a=this.defaultvaluesdropdownNew.length;

    //    this.defaultvaluesdropdownNew.push(a);
    //  }

    // console.log(valuenew);

    // var y = +value;
    // console.log(y+1);
    // for(let j=0;j<value;j++)
    // {
    //   console.log("test");
    //   console.log(j);
    //   this.defaultvaluesdropdown.push(j);
    // }
    console.log(this.defaultvaluesdropdown);
    //  for(let j=0;j<x;j++)
    //  {
    //    console.log("test");
    //    console.log(j);
    //    var b=this.defaultvaluesdropdown.length;
    //    this.defaultvaluesdropdown.push(b);
    //  }

    console.log("test");
    //  if(value==1 )
    //  {
    //    this.defaultvaluesdropdown=[];
    //    this.defaultvaluesdropdownNew=[];
    //   for(let j=0;j<value;j++)
    //   {
    //     console.log("test");
    //     console.log(j);
    //     this.defaultvaluesdropdown.push(j);
    //   }
    //   for(let j=0;j<value+1;j++)
    //   {
    //     console.log("test");
    //     console.log(j);
    //     this.defaultvaluesdropdownNew.push(j);
    //   }
    //  }
    console.log(value);
    console.log(this.defaultvaluesdropdown);
    console.log(this.defaultvaluesdropdownNew);

  }
  compOthersForm(others, othersfrm: NgForm) {
    this.mainSave = true;
    console.log(others);
    console.log(othersfrm);
  }
  ngOnInit() {

    this.arry1 = [
      { name: "perfHmdb", value: 1 },
      { name: "utlist", value: 2 }
    ]

    this.arry = [
      { name: "auxSteamList", value: 1 },
      { name: "freshList", value: 2 },
      { name: "surfCond", value: 3 },
      { name: "controlList", value: 4 },
      { name: "lubeOilList", value: 5 },
      { name: "performanceList3", value: 6 }
    ]
  }
  getPerf() {
    this.mainSave = true;

    this.defaultvaluesdropdown = [];
    this.defaultvaluesdropdownNew = [];
    this.dboPerf = [];
    var x = +this.defaultvalue;
    x = x + 1;

    //fetchCacheData
    for (let j = 0; j < x; j++) {
      console.log("test");
      console.log(j);
      this.defaultvaluesdropdown.push(j);
    }
    for (let j = 0; j < x + 1; j++) {
      console.log("test");
      console.log(j);
      this.defaultvaluesdropdownNew.push(j);
    }
    this.display = true;
    console.log("hello");
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;

    this.dboFormDataaa.noOfConditions = this.defaultvalue;
    this._ItoPerformanceService.getPerformanceParam(this.dboFormDataaa).subscribe(resppp1 => {
      console.log(resppp1);
      console.log(resppp1.dboPerformanceList);
      this.definedColumnsNew = [];
      this.definedColumnsNew = resppp1.dboPerformanceList;
      this.unitList = resppp1.unitList;
      //this.definedColumnsNew2=resppp1.unitList;
      let unit = [];
      this.defaulValue = [];
      // unit =  this.unitList.reduce((acc, current) => {
      //   console.log(acc, current);
      //   const x = acc.find(item => item.paramCd === current.paramCd);
      //   if (!x) {
      //     return acc.concat([current]);
      //   } else {
      //     return acc;
      //   } 
      // }, []);
      for (let j = 0; j < this.definedColumnsNew.length; j++) {
        for (let x = 0; x < this.unitList.length; x++) {
          if (this.definedColumnsNew[j].paramId == this.unitList[x].paramId) {
            unit[j] = this.unitList[x];
            this.defaulValue[j] = this.unitList[x].unitItemNm;
            break;
          }


        }
      }
      this.dboPerfNew = [];
      for (let j = 0; j < this.definedColumnsNew.length; j++) {


        this.dboPerfNew.push([j]);

        for (let k = 0; k < 20; k++) {
          this.dboPerfNew[j][k] = null;
        }
        // this.dboPerf[j]
        // {

        // }
      }



    });
  }

  itmTechInRemarks() {
    this.mainSave = true;

    this.techRemarks = this.dboPerformanceList3[0].techRemarks;
  }

  savePerf() {

    this.perfDetail = [];
    console.log(this.dboPerformanceList1);

    for (let d = 0; d < this.auxSteamList.length; d++) {
      this.dboClass = new dboClass;
      this.dboClass.itemId = this.auxSteamList[d].itemId;
      this.dboClass.subItemId = this.auxSteamList[d].subItemId;
      this.dboClass.consumerId1 = this.auxSteamList[d].consumerId1;
      this.dboClass.startUp = this.auxSteamList[d].startUp;
      this.dboClass.continuous = this.auxSteamList[d].continuous;
      this.dboClass.colValCd = this.auxSteamList[d].colValCd;
      this.dboClass.editFlag = this.auxSteamList[d].editFlag;
      this.dboClass.itemType = null;
      this.dboClass.itemCd = null;
      this.dboClass.speed = null;
      this.dboClass.voltage = null;
      this.dboClass.feeder = null;
      this.dboClass.techRemarks = this.techRemarks;
      this.dboClass.newColValFlag = 0;
      this.perfDetail.push(this.dboClass);
    }
    for (let h = 0; h < this.freshList.length; h++) {
      this.dboClass = new dboClass;
      this.dboClass.itemId = this.freshList[h].itemId;
      this.dboClass.subItemId = this.freshList[h].subItemId;
      this.dboClass.consumerId1 = this.freshList[h].consumerId1;
      this.dboClass.startUp = null;
      this.dboClass.continuous = this.freshList[h].continuous;
      this.dboClass.colValCd = this.freshList[h].colValCd;
      this.dboClass.editFlag = this.freshList[h].editFlag;
      this.dboClass.itemType = null;
      this.dboClass.itemCd = null;
      this.dboClass.speed = null;
      this.dboClass.voltage = this.freshList[h].voltage;
      this.dboClass.feeder = this.freshList[h].feeder;
      this.dboClass.techRemarks = this.techRemarks;
      this.dboClass.newColValFlag = 0;
      this.perfDetail.push(this.dboClass);
    }
    for (let o = 0; o < this.surfCond.length; o++) {
      this.dboClass = new dboClass;
      this.dboClass.itemId = this.surfCond[o].itemId;
      this.dboClass.subItemId = this.surfCond[o].subItemId;
      this.dboClass.consumerId1 = this.surfCond[o].consumerId1;
      this.dboClass.startUp = null;
      this.dboClass.continuous = this.surfCond[o].continuous;
      this.dboClass.colValCd = this.surfCond[o].colValCd;
      this.dboClass.editFlag = this.surfCond[o].editFlag;
      this.dboClass.itemType = null;
      this.dboClass.itemCd = null;
      this.dboClass.speed = null;
      this.dboClass.voltage = this.surfCond[o].voltage;
      this.dboClass.feeder = this.surfCond[o].feeder;
      this.dboClass.techRemarks = this.techRemarks;
      this.dboClass.newColValFlag = 0;
      this.perfDetail.push(this.dboClass);
    }
    for (let i = 0; i < this.controlList.length; i++) {
      this.dboClass = new dboClass;
      this.dboClass.itemId = this.controlList[i].itemId;
      this.dboClass.subItemId = this.controlList[i].subItemId;
      this.dboClass.consumerId1 = null;
      this.dboClass.startUp = null;
      this.dboClass.continuous = this.controlList[i].continuous;
      this.dboClass.colValCd = this.controlList[i].colValCd;
      this.dboClass.editFlag = null;
      this.dboClass.itemType = null;
      this.dboClass.itemCd = null;
      this.dboClass.speed = null;
      this.dboClass.voltage = null;
      this.dboClass.feeder = null;
      this.dboClass.techRemarks = this.techRemarks;
      this.dboClass.newColValFlag = 0;
      this.perfDetail.push(this.dboClass);
    }
    for (let f = 0; f < this.lubeOilList.length; f++) {
      this.dboClass = new dboClass;
      this.dboClass.itemId = this.lubeOilList[f].itemId;
      this.dboClass.subItemId = this.lubeOilList[f].subItemId;
      this.dboClass.consumerId1 = null;
      this.dboClass.startUp = null;
      this.dboClass.continuous = this.lubeOilList[f].continuous;
      this.dboClass.colValCd = this.lubeOilList[f].colValCd;
      this.dboClass.editFlag = null;
      this.dboClass.itemType = null;
      this.dboClass.itemCd = null;
      this.dboClass.speed = null;
      this.dboClass.voltage = null;
      this.dboClass.feeder = null;
      this.dboClass.techRemarks = this.techRemarks;
      this.dboClass.newColValFlag = 0;
      this.perfDetail.push(this.dboClass);
    }
    let temp = [];
    temp = this.dboPerformanceList3;
    this.dboPerformanceList3 = [];
    for (let f = 0; f < temp.length; f++) {
      if (temp[f].itemType == 'AC' && temp[f].itemCd != 'TOTAL =') {
        this.dboPerformanceList3.push(temp[f]);
      }
    }
    for (let f = 0; f < temp.length; f++) {
      if (temp[f].itemType == 'AC' && temp[f].itemCd == 'TOTAL =') {
        this.dboPerformanceList3.push(temp[f]);
      }
    }
    for (let f = 0; f < temp.length; f++) {
      if (temp[f].itemType == 'DC') {
        this.dboPerformanceList3.push(temp[f]);
      }
    }
    console.log(this.dboPerformanceList3);
    for (let c = 0; c < this.dboPerformanceList3.length; c++) {
      this.dboClass = new dboClass;
      this.dboClass.itemId = this.dboPerformanceList3[c].itemId;
      this.dboClass.subItemId = this.dboPerformanceList3[c].subItemId;
      this.dboClass.consumerId1 = this.dboPerformanceList3[c].consumerId1;
      this.dboClass.startUp = this.dboPerformanceList3[c].startUp;
      this.dboClass.continuous = this.dboPerformanceList3[c].continuous;
      this.dboClass.colValCd = null;
      this.dboClass.editFlag = this.dboPerformanceList3[c].editFlag;
      this.dboClass.itemType = this.dboPerformanceList3[c].itemType;
      this.dboClass.itemCd = this.dboPerformanceList3[c].itemCd;
      this.dboClass.speed = this.dboPerformanceList3[c].speed;
      this.dboClass.voltage = this.dboPerformanceList3[c].voltage;
      this.dboClass.feeder = this.dboPerformanceList3[c].feeder;
      this.dboClass.techRemarks = this.techRemarks;
      this.dboClass.newColValFlag = this.dboPerformanceList3[c].newColValFlag;
      this.perfDetail.push(this.dboClass);
    }

    for (let k = 0; k < this.fixedList.length; k++) {
      this.dboClass = new dboClass;
      this.dboClass.itemId = this.fixedList[k].itemId;
      this.dboClass.subItemId = null;
      this.dboClass.consumerId1 = null;
      this.dboClass.startUp = null;
      this.dboClass.continuous = null;
      this.dboClass.colValCd = null;
      this.dboClass.editFlag = null;
      this.dboClass.itemType = null;
      this.dboClass.itemCd = null;
      this.dboClass.speed = null;
      this.dboClass.voltage = null;
      this.dboClass.feeder = null;
      this.dboClass.techRemarks = this.techRemarks;
      this.dboClass.newColValFlag = 0;
      this.perfDetail.push(this.dboClass);
    }
    if (this.cirList.length > 0) {
      this.dboClass = new dboClass;
      this.dboClass.itemId = this.cirList[0].itemId;
      this.dboClass.subItemId = null;
      this.dboClass.consumerId1 = null;
      this.dboClass.startUp = null;
      this.dboClass.continuous = null;
      this.dboClass.colValCd = null;
      this.dboClass.editFlag = null;
      this.dboClass.itemType = null;
      this.dboClass.itemCd = null;
      this.dboClass.speed = null;
      this.dboClass.voltage = null;
      this.dboClass.feeder = null;
      this.dboClass.techRemarks = null;
      this.dboClass.newColValFlag = 0;
      this.perfDetail.push(this.dboClass);
    }
    if (this.identList.length > 0) {
      this.dboClass = new dboClass;
      this.dboClass.itemId = this.identList[0].itemId;
      this.dboClass.subItemId = null;
      this.dboClass.consumerId1 = null;
      this.dboClass.startUp = null;
      this.dboClass.continuous = null;
      this.dboClass.colValCd = null;
      this.dboClass.editFlag = null;
      this.dboClass.itemType = null;
      this.dboClass.itemCd = null;
      this.dboClass.speed = null;
      this.dboClass.voltage = null;
      this.dboClass.feeder = null;
      this.dboClass.techRemarks = null;
      this.dboClass.newColValFlag = 0;
      this.perfDetail.push(this.dboClass);
    }

    this.performanceData = [];
    let bol = true;

    console.log(bol);
    if (bol == true) {

      if (this.dboPerfNew.length != 0) {
        for (let x = 0; x < this.dboPerfNew.length; x++) {
          this.dboClass = new dboClass;
          this.dboClass.paramId = this.definedColumnsNew[x].paramId;
          this.dboClass.paramNm = this.definedColumnsNew[x].paramNm;
          this.dboClass.unitId = this.definedColumnsNew[x].unitId;
          this.dboClass.unitNm = this.defaulValue[x];
          this.dboClass.guaranteed = this.dboPerfNew[x][1];
          this.dboClass.cond1 = this.dboPerfNew[x][2];
          this.dboClass.cond2 = this.dboPerfNew[x][3];
          this.dboClass.cond3 = this.dboPerfNew[x][4];
          this.dboClass.cond4 = this.dboPerfNew[x][5];
          this.dboClass.cond5 = this.dboPerfNew[x][6];
          this.dboClass.cond6 = this.dboPerfNew[x][7];
          this.dboClass.cond7 = this.dboPerfNew[x][8];
          this.dboClass.cond8 = this.dboPerfNew[x][9];
          this.dboClass.cond9 = this.dboPerfNew[x][10];
          this.dboClass.cond10 = this.dboPerfNew[x][11];
          this.performanceData.push(this.dboClass);
        }
      }
      if (this.performanceData.length > 0) {
        if (this.perfTabHmbd.length > 0) {
          for (let v = 0; v < this.perfTabHmbd.length; v++) {
            this.dboClass = new dboClass;
            this.dboClass.itemId = this.perfTabHmbd[v].itemId;
            this.dboClass.subItemId = null;
            this.dboClass.consumerId1 = null;
            this.dboClass.startUp = null;
            this.dboClass.continuous = null;
            this.dboClass.colValCd = null;
            this.dboClass.editFlag = null;
            this.dboClass.itemType = null;
            this.dboClass.itemCd = null;
            this.dboClass.speed = null;
            this.dboClass.voltage = null;
            this.dboClass.feeder = null;
            this.dboClass.techRemarks = null;
            this.perfDetail.push(this.dboClass);
          }
        }
      }
      this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      if (this.defaultvalue2 == "Yes") {
        this.dboFormDataaa.hmbdTableInput = 1;
      }
      else {
        this.dboFormDataaa.hmbdTableInput = 0;
      }
      if (this.hmbd == true) {
        this.arry1[0].value = 1;
        this.arry1[1].value = 2;
        this.dboFormDataaa.conditionTableInput = 1;
      } else {
        this.dboFormDataaa.conditionTableInput = 0;
      }
      this.dboFormDataaa.hmbdImage = this.image;
      var x = +this.defaultvalue;
      this.dboFormDataaa.noOfConditions = x;
      this.dboFormDataaa.performanceData = this.performanceData;
      this.dboFormDataaa.performanceData1 = this.perfDetail;
      this.dboFormDataaa.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById;
      for (let m = 0; m < this.perfDetail.length; m++) {
        console.log(this.perfDetail[m].itemId, this.perfDetail[m].subItemId);
      }
      console.log(this.dboPerformanceList3);
      console.log(this.performanceData);
      console.log(this.dboFormDataaa);
      console.log(this.lubeOilList);
      console.log(this.controlList);
      this._ItoPerformanceService.savePerformance(this.dboFormDataaa).subscribe(resppp => {
        console.log(resppp);
        if (resppp.successCode == 0) {
          this.mainSave = false;
          this.dialogMsgApp = true;
          if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard") {
            this.dialogMsgVal = "Offer Generated Successfully. Ready for Download";
          }
          else {
            this.dialogMsgVal = "Saved Successfully";
          }
          this.savePerformanceList1 = resppp.savePerformanceList1;
          this.savePerformanceList2 = resppp.savePerformanceList2;
          this.savePerformanceList3 = resppp.savePerformanceList3;
          this._ITOcustomerRequirementService.sendtecBtnStatus(true);
          let sos = this.storage.get(this.scopeofsupp);
          for (let s = 0; s < sos.length; s++) {
            if (sos[s].scopeCode == 'PHM') {
              this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
              this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
              this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
                console.log(res);
              })
            }
          }
        } else {
          this.dialogMsgApp = true;
          this.dialogMsgVal = resppp.successMsg;
        }

        if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit == false) {
          this._ITOcustomerRequirementService.editFlag = false;
          this.router.navigate(['/EditQuot']);
        }
        this.saveInLocal(this.dboPerfLoc, {
          savePerformanceList1: this.savePerformanceList1, unitList: this.unitList, savePerformanceList2: this.savePerformanceList2, defaultvalue: this.defaultvalue,
          dboPerfNew: this.dboPerfNew, hmbdTableInput: resppp.hmbdTableInput, conditionTableInput: resppp.conditionTableInput,
          savePerformanceList3: this.savePerformanceList3, cirList: this.cirList, identList: this.identList, itemList: this.itemList, fixedList: this.fixedList
        });
      });
    }
  }
  //Sum the continous
  continuSum(val) {
    this.mainSave = true;

    console.log(val);
    let sum = 0;
    for (let k = 0; k < this.dboPerformanceList3.length; k++) {
      if (this.dboPerformanceList3[k].continuous >= 0 && this.dboPerformanceList3[k].itemType == 'AC' && this.dboPerformanceList3[k].itemCd != 'TOTAL =') {
        var x = this.dboPerformanceList3[k].continuous;
        var y: number = +x;
        sum += y;
      }
    } console.log(sum);
    for (let p = 0; p < this.dboPerformanceList3.length; p++) {
      if (this.dboPerformanceList3[p].itemCd == 'TOTAL =') {
        this.dboPerformanceList3[p].continuous = sum;
      }
    }
  }

  //Sum the continous
  continuSumT(valuu) {
    this.mainSave = true;

    console.log(valuu);
    let sum = 0;
    for (let k = 0; k < this.dboPerformanceList3.length; k++) {
      if (this.dboPerformanceList3[k].startUp >= 0 && this.dboPerformanceList3[k].itemType == 'AC' && this.dboPerformanceList3[k].itemCd != 'TOTAL =') {
        var x = this.dboPerformanceList3[k].startUp;
        var y: number = +x;
        sum += y;
      }
    } console.log(sum);
    for (let p = 0; p < this.dboPerformanceList3.length; p++) {
      if (this.dboPerformanceList3[p].itemCd == 'TOTAL =') {
        this.dboPerformanceList3[p].startUp = sum;
        console.log(this.dboPerformanceList3[p]);
      }
    }
  }
  //To navigate edit quotaion page on click of back button
  backButton() {
    this.router.navigate(['/EditQuot']);
  }
  //cancel button in dc consumer table
  cancelnewLineLhs() {
    this.mainSave = true;

    this.lhsRhsnewLine = false;//added by megha
  }
  //Add button to add record in consumer table
  addRowsLhsRhs() {
    this.mainSave = true;

    this.lhsRhsnewLine = true;
  }

  addLhsRhsForm(lhsRhsItem, lhsRhsItemfrm: NgForm) {
    this.mainSave = true;

    console.log(lhsRhsItem);
    // this.dboPerfList3 = [];
    //           for(let b=0; b<this.dboPerformanceList3.length; b++){
    //             if(this.dboPerformanceList3[b].itemType == "DC"){
    //               this.dboPerfList3.push(this.dboPerformanceList3[b]);
    //               }
    //            }
    // for(let c=0; c<this.dboPerfList3.length; c++){
    //   if(lhsRhsItem.itemCd != this.dboPerfList3[c].itemCd){
    this.dboClass = new dboClass();
    this.dboClass.itemId = this.dboPerformanceList3[0].itemId;
    this.dboClass.subItemId = this.dboPerformanceList3[0].subItemId;
    this.dboClass.consumerId1 = this.dboPerformanceList3[0].consumerId1;
    this.dboClass.startUp = lhsRhsItem.startUp;
    this.dboClass.continuous = lhsRhsItem.continuous;
    this.dboClass.colValCd = null;
    this.dboClass.editFlag = this.dboPerformanceList3[0].editFlag;
    this.dboClass.itemType = 'DC';
    this.dboClass.itemCd = lhsRhsItem.itemCd;
    this.dboClass.speed = lhsRhsItem.speed;
    this.dboClass.voltage = lhsRhsItem.voltage;
    this.dboClass.feeder = lhsRhsItem.feeder;
    this.dboClass.techRemarks = this.techRemarks;
    this.dboClass.newColValFlag = 1;
    this.dboPerformanceList3.push(this.dboClass);
    //   } 
    // }
    lhsRhsItemfrm.reset();
    this.lhsRhsnewLine = false;
  }
  cancelLinesLhs(i) {
    this.mainSave = true;

    this.lhsRhsnewLine = false;
    this.dboPerformanceList3.splice(i, 1);
  }

  //cancel button in dc consumer table
  cancelnewLine() {
    this.mainSave = true;

    this.lhsnewLine = false;//added by megha
  }
  //Add button to add record in consumer table
  addRowsLhs() {
    this.mainSave = true;

    this.lhsnewLine = true;
  }

  addLhsForm(lhsItem, lhsItemfrm: NgForm) {
    this.mainSave = true;

    console.log(lhsItem);

    this.dboClass = new dboClass();
    // this.dboClass.itemId = this.dboPerformanceList3[0].itemId;
    this.dboClass.subItemId = this.dboPerformanceList3[0].subItemId;
    this.dboClass.consumerId1 = this.dboPerformanceList3[0].consumerId1;
    this.dboClass.startUp = lhsItem.startUp;
    this.dboClass.continuous = lhsItem.continuous;
    this.dboClass.colValCd = null;
    this.dboClass.editFlag = this.dboPerformanceList3[0].editFlag;
    this.dboClass.itemType = 'AC';
    this.dboClass.itemCd = lhsItem.itemCd;
    this.dboClass.speed = lhsItem.speed;
    this.dboClass.voltage = lhsItem.voltage;
    this.dboClass.feeder = lhsItem.feeder;
    this.dboClass.techRemarks = this.techRemarks;
    this.dboClass.newColValFlag = 1;
    this.dboPerformanceList3.push(this.dboClass);

    let sum = 0;
    let sum1 = 0;
    for (let k = 0; k < this.dboPerformanceList3.length; k++) {
      if (this.dboPerformanceList3[k].itemCd == 'TOTAL =') {
        // var x: number = this.dboPerformanceList3[k].startUp;
        // var y: number = lhsItem.startUp;
        sum = +this.dboPerformanceList3[k].startUp + +lhsItem.startUp;
        sum1 = +this.dboPerformanceList3[k].continuous + +lhsItem.continuous;
        this.dboPerformanceList3[k].startUp = sum;
        this.dboPerformanceList3[k].continuous = sum1;
      }
    } console.log(sum);

    //   } 
    // }
    lhsItemfrm.reset();
    this.lhsnewLine = false;
  }
  cancelLines(i) {
    this.mainSave = true;

    this.lhsRhsnewLine = false;
    this.dboPerformanceList3.splice(i, 1);
    this.continuSum(0);
    this.continuSumT(0);
  }
  Refresh() {
    this.mainSave = true;

    this.perfTabHmbd = [];
    this.performanceData = [];
    this.auxSteamList = [];
    this.dboPerformanceList1 = [];
    this.surfCond = [];
    this.lubeOilList = [];
    this.controlList = [];
    this.dboPerformanceList3 = [];
    this.dboperformance1 = [];
    this.dboperformance2 = [];
    this.identList = [];
    this.fixedList = [];
    this.cirList = [];
    this.itemList = [];
    this.freshList = [];

    this.getPerform();
  }

  Refresh2() {
    this.mainSave = true;
    this.perfDetail = [];
    this.getPerf();
  }

  getPerform() {
    this.mainSave = true;

    this._ItoPerformanceService.getPerformance(this.dboFormDataaa).subscribe(respo => {
      console.log(respo);
      console.log(respo.newPerformanceList);
      this.dboPerformanceList1 = respo.dboPerformanceList;
      for (let m = 0; m < this.dboPerformanceList1.length; m++) {
        console.log(this.dboPerformanceList1[m].subItemNm);
        if (this.dboPerformanceList1[m].itemNm == 'Performance Table / HMBD') {
          this.perfTabHmbd.push(this.dboPerformanceList1[m]);
        }
        else if (this.dboPerformanceList1[m].itemNm == 'Utilities list' && this.dboPerformanceList1[m].subItemNm == 'Auxiliary Steam') {
          this.auxSteamList.push(this.dboPerformanceList1[m]);
        } else if (this.dboPerformanceList1[m].itemNm == 'Utilities list' && this.dboPerformanceList1[m].subItemNm == 'Auxiliary Cooling Water') {
          if (this.dboPerformanceList1[m].continuous == "NULL") {
            this.dboPerformanceList1[m].continuous = 0;
          }
          this.freshList.push(this.dboPerformanceList1[m]);
        } else if (this.dboPerformanceList1[m].itemNm == 'Utilities list' && this.dboPerformanceList1[m].subItemNm == 'Surface Condenser Cooling Water') {
          if (this.dboPerformanceList1[m].continuous == "NULL") {
            this.dboPerformanceList1[m].continuous = 0;
          }
          this.surfCond.push(this.dboPerformanceList1[m]);
        }
        else if (this.dboPerformanceList1[m].itemNm == 'Utilities list' && this.dboPerformanceList1[m].subItemNm == 'Lube Oil For Initial Fill & Flushing') {
          this.lubeOilList.push(this.dboPerformanceList1[m]);
        } else if (this.dboPerformanceList1[m].itemNm == 'Utilities list' && this.dboPerformanceList1[m].subItemNm == 'Instrument Control Air @ 7 / Kg / Cm² g') {
          this.controlList.push(this.dboPerformanceList1[m]);
        } else if (this.dboPerformanceList1[m].itemNm == 'Utilities list' && this.dboPerformanceList1[m].subItemNm == 'AC,DC Power Consumption') {
          this.dboPerformanceList3.push(this.dboPerformanceList1[m]);
        }
      }
      this.continuSum(0);
      this.continuSumT(0);
      console.log(this.freshList);
      let i = 0;
      if (this.auxSteamList.length > 0) {
        i = i + 1;
        this.arry[0].value = i;
      }
      if (this.freshList.length > 0) {
        i = i + 1;
        this.arry[1].value = i;
      }
      if (this.surfCond.length > 0) {
        i = i + 1;
        this.arry[2].value = i;
      }
      if (this.controlList.length > 0) {
        i = i + 1;
        this.arry[3].value = i;
      }
      if (this.lubeOilList.length > 0) {
        i = i + 1;
        this.arry[4].value = i;
      }
      if (this.dboPerformanceList3.length > 0) {
        i = i + 1;
        this.arry[5].value = i;
      }
      // let dcItemList = this.dboPerformanceList3.map(item => item.itemType);
      //     if (dcItemList.includes('DC')){
      //       this.enableDcTab = true;
      // this.dboPerfList3 = [];
      // for(let b=0; b<this.dboPerformanceList3.length; b++){
      //   if(this.dboPerformanceList3[b].itemType == "DC"){
      //     this.dboPerfList3.push(this.dboPerformanceList3[b]);
      //     }
      //  }
      // }
      this.identList = respo.identList;
      this.fixedList = respo.fixedList;
      this.cirList = respo.cirList;
      this.itemList = respo.itemList;
    });
  }
}
