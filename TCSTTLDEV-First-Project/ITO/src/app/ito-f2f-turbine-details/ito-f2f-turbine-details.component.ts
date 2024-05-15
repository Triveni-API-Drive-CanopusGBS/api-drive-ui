import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { turbineConfigValues } from '../ito-turbine-config/ito-turbine-config';
import { ITOcompleteTurbineService } from '../complete-turbine-details/complete-turbine-details.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOLoginService } from '../app.component.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ItoF2fTurbineDetailsService } from './ito-f2f-turbine-details.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';


@Component({
  selector: 'app-ito-f2f-turbine-details',
  templateUrl: './ito-f2f-turbine-details.component.html',
  styleUrls: ['./ito-f2f-turbine-details.component.css']
})
export class ItoF2fTurbineDetailsComponent implements OnInit {

  regCols: { field: string; header: string; }[];
  questionEntity: any;
  displayDialog: boolean;
  selectedCno: any;
  sapData: any;
  quesForm: any;
  Counter: any = 5;

  tempCodeArray: Array<Array<any>> = [];
  matchedVarient: Array<number> = [];
  newAllC:Array<any>=[];
  allCnumbersUpdated:Array<any>=[];
  matchedCind:Array<number>=[];
  unknownArray:Array<any>=[];
  labelId: Array<any> = [];
  dropdownId: Array<any> = [];
  ansCode: Array<any> = [];
  matchedCProjectList: Array<any> = [];
  allCnumbers: Array<any> = [];
  Labels: Array<any> = [];
  Dropdowns: Array<any> = [];
  questionsBean: Array<any> = [];
  defaultValues: Array<any> = [];
  F2FSapLocalStorage: Array<any> = [];
  selectedQuesDetails: turbineConfigValues;
  ArrayOfSelected: Array<turbineConfigValues>;

  dispSaveBtn: boolean = false;
  message: boolean = false;
  enableCompleteBOM: boolean = false; // variable to control enable and disable the complete BOM
  hideprogress: boolean;
  hideprogressC: boolean;
  displayCno: boolean;
  enableBOM: boolean = false;
  matchedCProjectAvailable: boolean = true;
  successMsg: string = '';
  unmatchedMsg: string = ``;
  F2FSap: string = 'F2FSap';
  varientCode: string = '';
  CnumbersMessage: string = "";
  backBtn:boolean=false;
  constructor(private _ItoF2fTurbineDetailsService: ItoF2fTurbineDetailsService,private _Router: Router,
    private router: Router, private route: ActivatedRoute, private _ITOeditQoutService: ITOeditQoutService,
    private _ITOcompleteTurbineService: ITOcompleteTurbineService,
    private _ITOLoginService: ITOLoginService, @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService) {

    this._ITOcustomerRequirementService.sendMessage(this._ITOcustomerRequirementService.saveBasicDet.quotNumber);
    this.ArrayOfSelected = new Array<turbineConfigValues>();
    this.selectedQuesDetails = new turbineConfigValues('');
    if(this._ITOeditQoutService.checkEdit == false){
      this.backBtn = true;
    }
    this._ItoF2fTurbineDetailsService.getQuotForm().subscribe(quotForm => {
      if (this._ITOcustomerRequirementService.editFlag) {
        this._ITOcustomerRequirementService.saveBasicDet.cNewNumber = this._ITOcustomerRequirementService.saveBasicDet.cOldNumber;
      }
      quotForm.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
      console.log( this._ITOcustomerRequirementService.saveBasicDet.framePowerId);
      this._ItoF2fTurbineDetailsService.getQuestions(quotForm).subscribe(resp => {
        console.log(resp);
        this.quesForm=resp;
        this.questionsBean = resp.questionsBean;
        this.hideprogress = true;
        this.hideprogressC = false;
        for (let l = 0; l < resp.questionsBean.length; l++) {
          this.Labels.push(resp.questionsBean[l].dropDownType);
          for (let d = 0; d < resp.questionsBean[l].dropDownValueList.length; d++) {
            this.Dropdowns.push(resp.questionsBean[l].dropDownValueList[d]);
            if (resp.questionsBean[l].dropDownValueList[d].defaultVal == true) {
              this.defaultValues.push(resp.questionsBean[l].dropDownValueList[d].value)
            }
          }
        }
        console.log(this.Labels);
        console.log(this.defaultValues);

      })
      this._ItoF2fTurbineDetailsService.getQuestionData(this._ITOcustomerRequirementService.saveBasicDet.framePowerId).subscribe(resp1 => {
        console.log(resp1);
        this.questionEntity= resp1.questionsEntityList;
      });
    })
  }

  ngOnInit() {
    this.F2FSapLocalStorage[this.F2FSap] = this.storage.get(this.F2FSap);
    this.sapData= this.F2FSapLocalStorage[this.F2FSap]
    console.log(this.F2FSapLocalStorage[this.F2FSap]);
    if (this.F2FSapLocalStorage[this.F2FSap]) {
      let turbConfigVal = new turbineConfigValues('');
      turbConfigVal.cNum=this.sapData.cNum;
      turbConfigVal.variantCode=this.sapData.variantCode;
      turbConfigVal.defaultFlag=true;
      turbConfigVal.custName=this.sapData.custName;
     this.matchedCProjectList.push(turbConfigVal);
     this.displayCno=true;
     this.dispSaveBtn=true;
    }
    this.regCols = [
      { field: 'questionCode', header: 'Question Code' },
      { field: 'questionDesc', header: 'Question Description' },
      { field: 'answerCd', header: 'Answer Code' },
      { field: 'answerDesc', header: 'Answer Description' },
    ];
  }

  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.F2FSapLocalStorage[key] = this.storage.get(key);
  }
  infoData(){
    this.displayDialog=true;
  }
  submitTurbineDetails() {
    this.hideprogressC = true;
    this.dispSaveBtn=false;
    this._ItoF2fTurbineDetailsService.Counter = this.Counter + 1;
    this._ItoF2fTurbineDetailsService.save();
    this.labelId = [];
    this.dropdownId = [];
    this.ansCode = [];
    this.matchedCProjectList = [];
    this.allCnumbers = [];
    this.varientCode = '';
    this.tempCodeArray = [];
    this.ArrayOfSelected = [];
    this.matchedVarient = []
    this.unmatchedMsg = ``;
    console.log(this.defaultValues);

      if (this._ITOcustomerRequirementService.editFlag) {
        this._ITOcustomerRequirementService.saveBasicDet.cNewNumber = this._ITOcustomerRequirementService.saveBasicDet.cOldNumber;
      }
     // this.quesForm.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    
        for (let l = 0; l <  this.quesForm.questionsBean.length; l++) {
          for (let d = 0; d <  this.quesForm.questionsBean[l].dropDownValueList.length; d++) {
            if ( this.quesForm.questionsBean[l].dropDownValueList[d].value == this.defaultValues[l]) {
              this.selectedQuesDetails.quesTypeId =  this.quesForm.questionsBean[l].dropDownType.key;
              this.selectedQuesDetails.quesAnswerkey =  this.quesForm.questionsBean[l].dropDownValueList[d].quesAnswerkey;
              this.selectedQuesDetails.answerValueCode =  this.quesForm.questionsBean[l].dropDownValueList[d].code;
              console.log(this.selectedQuesDetails.quesTypeId ,this.selectedQuesDetails.quesAnswerkey ,this.selectedQuesDetails.answerValueCode); 
              this.labelId.push(this.selectedQuesDetails.quesTypeId);
              this.dropdownId.push(this.selectedQuesDetails.quesAnswerkey);
              this.ansCode.push(this.selectedQuesDetails.answerValueCode);
              this.ArrayOfSelected.push(this.selectedQuesDetails);
            }

          }

        }
        console.log(this.labelId, this.dropdownId, this.ansCode);

        console.log(this.ArrayOfSelected);
        for (let i = 0; i < this.labelId.length; i++) {
          this.quesForm.saveQuesDetailsList[i].quesTypeId = this.labelId[i];
          this.quesForm.saveQuesDetailsList[i].quesAnswerkey = this.dropdownId[i];
          this.quesForm.saveQuesDetailsList[i].answerValueCode = this.ansCode[i];
          console.log( this.quesForm.saveQuesDetailsList[i].quesAnswerkey);
        }
        for (let v = 0; v < this.ansCode.length; v++) {
          this.varientCode += this.ansCode[v];
        }
        console.log(this.quesForm);
        console.log(this.varientCode);
        this.quesForm.loggedInUserId = this._ITOLoginService.loggedUserDetails;
        this.quesForm.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
        this._ItoF2fTurbineDetailsService.saveQuestions(this.quesForm).subscribe(respo => {
          console.log(respo);
          this.hideprogressC = false;
          if (respo.matchedCProjectList.length > 0) {
            this.matchedCProjectList = respo.matchedCProjectList;
            this.CnumbersMessage = "";
            this.matchedCProjectAvailable = true;
            this.displayCno = true;
          }
          else if (respo.matchedCProjectList.length == 0) {
            this.CnumbersMessage = "No records Found";
            this.matchedCProjectAvailable = false;
            this.allCnumbers = respo.cProjectList;
            this.displayCno = false;
          }

          console.log(this.matchedCProjectList);
          console.log(this.allCnumbers);
          this._ItoF2fTurbineDetailsService.latestCDetails = respo.latestCProjectData;
          console.log(this._ItoF2fTurbineDetailsService.latestCDetails);
          for (let a = 0; a < this.allCnumbers.length; a++) {
            let newTemp = [];
            newTemp = this.allCnumbers[a].variantCode.split(['|']);
            this.tempCodeArray.push(newTemp);
          }
          console.log(this.tempCodeArray);
          this.unknownArray=[];
          for (let t = 0; t < this.tempCodeArray.length; t++) {
            for (let v = 0; v < this.ansCode.length; v++) {
              if (this.tempCodeArray[t][v] != this.ansCode[v]) {
                console.log("found at" + " " + t, v + " " + this.tempCodeArray[t]);
                this.unknownArray.push({t, v})
                this.matchedVarient.push(v);
                this.matchedCind.push(t);
                break;
              }
            }
          }

          let unmatchedindex1 = Math.max(...this.matchedVarient);
          console.log(this.matchedCind , this.unknownArray);
          this.allCnumbersUpdated=[];
          this.newAllC=[];
          for(let u=0;u<this.unknownArray.length;u++){
            if(this.unknownArray[u].v===unmatchedindex1){
              this.newAllC.push(this.allCnumbers[this.unknownArray[u].t]);
            }
          }
  
          console.log(Math.max(...this.matchedVarient));
          console.log(this.allCnumbersUpdated ,this.newAllC);
          let unmatchedindex = Math.max(...this.matchedVarient);
         // console.log(resp.questionsBean[unmatchedindex].dropDownType.value);
          // this.allCnumbers=this.newAllC;
          for (let q = 0; q < this.quesForm.questionsBean.length; q++) {

          }
          this.unmatchedMsg = `Mismatch occuring at question ${this.quesForm.questionsBean[unmatchedindex].dropDownType.value}
           change the option `;
    })

  }

  CnoSelection(selectedNo) {
    this.enableBOM = true;
    this.selectedCno = selectedNo;
    console.log(selectedNo);
    this._ItoF2fTurbineDetailsService.Cnumber = selectedNo.cNum;
    console.log(selectedNo.variantCode);
    let vcarray = selectedNo.variantCode.split(['|']);
    console.log(vcarray);
    console.log(this.defaultValues);
    this.defaultValues = [];
    console.log(this.questionsBean);
    for (let l = 0; l < this.questionsBean.length; l++) {
      for (let d = 0; d < this.questionsBean[l].dropDownValueList.length; d++) {
        if (this.questionsBean[l].dropDownValueList[d].code == vcarray[l]) {
          this.defaultValues.push(this.questionsBean[l].dropDownValueList[d].value)
        }
      }
      
    }
    console.log(this.defaultValues);
  }
  showAllC() {
    this.matchedCProjectList = this.allCnumbers;
    this.CnumbersMessage = "";
    this.matchedCProjectAvailable = true;
    this.displayCno = true;
  }
  displayOnelineCost() {
    this.enableCompleteBOM = true;
    console.log(this.selectedCno, this._ITOcustomerRequirementService.saveBasicDet);
    this._ITOcustomerRequirementService.saveBasicDet.latestCNum = this.selectedCno.cNum;
    //this._ITOcustomerRequirementService.saveBasicDet.selectedCProjectKey = this.selectedCno.variantCode;
    this._ItoF2fTurbineDetailsService.getF2FSapData(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
      this.sapData = res.sapData;
      console.log(res)
      if (res.successCode == 0) {
        this.dispSaveBtn = true;
      } else {
        this.message = true;
        this.successMsg = res.successMsg;
      }
      // this._ItoF2fTurbineDetailsService.F2FBOMTree = res.tree;
      // this._ItoF2fTurbineDetailsService.AvailableCnums = this.matchedCProjectList;
      // this._ItoF2fTurbineDetailsService.sendMessage(res.oneLineBom);
      // console.log(res.f2FDataList[0]);
    })
  }

  saveSapCost() {
    //For storing in local storage
    this.sapData.variantCode = this.selectedCno.variantCode;
    this.sapData.cNum = this.selectedCno.cNum;
    //For storing in local storage
    this._ITOcustomerRequirementService.saveBasicDet.modifiedById = this._ITOLoginService.loggedUserDetails;
    this._ITOcustomerRequirementService.saveBasicDet.latestCNum = this.selectedCno.cNum;
    this._ITOcustomerRequirementService.saveBasicDet.variantCode = this.selectedCno.variantCode
    this._ITOcustomerRequirementService.saveBasicDet.sapData=this.sapData;
    this._ItoF2fTurbineDetailsService.saveF2FSap(this._ITOcustomerRequirementService.saveBasicDet).subscribe(savedData => {
      // this.sapData = savedData.sapData;
      this.saveInLocal(this.F2FSap, this.sapData); //testing
      console.log(savedData);
      if (savedData.successCode == 0) {
        this.message = true;
        this.successMsg = "Cost Saved Successfully";
        console.log(this._ITOcustomerRequirementService.editFlag)
        if (this._ITOcustomerRequirementService.editFlag) {
          this._ITOcustomerRequirementService.editFlag = false;
          this.router.navigate(['/EditQuot']);
        } else {
          //this.router.navigate(['CostEstimation/ScopeOfSupply']);
        }
      } else {
        this.message = true;
        this.successMsg = savedData.successMsg;
      }
    });
  }
  displayFullCost() {
    this.router.navigate(['CostEstimation/CompleteTurbineDetails/Tree']);
  }
  backButton(){
    this._Router.navigate(['/EditQuot']);
  }

}

