import { Component, OnInit } from '@angular/core';
import { ITOTurbineQuestionsService } from '../ito-turbine-questions/ito-turbine-questions.service';
import { turbineQuesValues } from './ito-turbine-questions';
import { turbineAnswers } from './ito-turbine-answers';
import { ITOLoginService } from '../app.component.service';
import { ListboxModule } from 'primeng/listbox';
import { TabViewModule } from 'primeng/tabview';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { WindowRef } from '../ito-customer-details/ito-customer-details.window.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-ito-turbine-questions',
  templateUrl: './ito-turbine-questions.component.html',
  styleUrls: ['./ito-turbine-questions.component.css']
})
export class ItoTurbineQuestionsComponent implements OnInit {

  rowGroupdata: any;
  rowGroupMetadata: any;
  defaultAns: any;
  formm: any;
  turCat: any;
  power: any;
  adminForm: any;

  frameId: number;
  count: number = 0;
  rowCount: number = 0;
  tabIndex: number = 0;
  defaultAnsKey: number;

  quesName: string;
  ansName: string;
  ansCode: string;
  quesCode: string;
  turDesgn: string;
  turTyp: string;
  frameName: string;
  bladeType: string;

  frameList: Array<any> = [];
  turbineTypesList: Array<any> = [];
  turbineCategoryList: Array<any> = [];
  Dropdowns: Array<any> = [];
  questionsBean: any = {};
  selectedQuesBean: any = {};
  defaultValues: Array<any> = [];
  questions: Array<any> = [];
  dropdownValues: Array<any> = [];
  defaultQuestions: Array<any> = [];
  defaultDropdownValues: Array<any> = [];
  fieldArray: Array<any> = [];
  newAttribute: any = {};
  rowArray: Array<any> = [];
  newRowAttribute: any = {};
  selectedFrames: Array<any> = [];
  selFrames: Array<any> = [];
  frames: Array<any> = [];
  answersList: Array<string> = [];
  Answers: Array<any> = [];
  framesWithoutPower: Array<any> = [];
  framesPowerList: Array<any> = [];
  powerList: Array<any> = [];
  ansList: Array<any> = [];
  ansFrames: Array<any> = [];

  displayAddDialog: boolean;
  displayDialog: boolean;
  hideprogress: boolean;
  displayAnsDialog: boolean;
  displayDefaultDialog: boolean;
  disableTab: boolean = true;
  hideSpinner: boolean = true;
  contains: boolean = false;

  selectedQuesDetails: turbineQuesValues;
  selectedDetails: turbineQuesValues;


  constructor(private _ITOTurbineQuestionsService: ITOTurbineQuestionsService, private _ITOLoginService: ITOLoginService,
    private _Router: Router, private _ActivatedRoute: ActivatedRoute, ) {
    this.hideprogress = false;

    this._ITOTurbineQuestionsService.getOldFrames().subscribe(res => {
      console.log(res);
      this.framesPowerList = res.frameWithPowersList;
      this._ITOTurbineQuestionsService.framesPowerList = res.frameWithPowersList;
    });
    this.getQuotationList();
    console.log(this.questions);
  }

  ngOnInit() { 
    this._ITOLoginService.dialogMsgApp = false;
  }

  turbineDesnSel(val) {
    console.log(val);
    this.bladeType = val;
    this.defaultQuestions = [];
    this.dropdownValues = [];
    this.powerList = [];
  }

  TOTSel(value) {
    console.log(value);
    this.frameList = [];
    this.defaultQuestions = [];
    this.dropdownValues = [];
    this.powerList = [];
    console.log(this._ITOTurbineQuestionsService.frameList)
    for (let j = 0; j < this._ITOTurbineQuestionsService.frameList.length; j++) {
      if (this._ITOTurbineQuestionsService.frameList[j].turbineCode == value && (this._ITOTurbineQuestionsService.frameList[j].turbineDesignCd == this.bladeType)) {
        this.frameList.push(this._ITOTurbineQuestionsService.frameList[j]);
      }
    }
    console.log(this.frameList)
  }

  FrameSel(frameId) {
    this.powerList = [];
    this.defaultQuestions = [];
    console.log(frameId);
    console.log(this._ITOTurbineQuestionsService.framesPowerList)
    for (let i = 0; i < this._ITOTurbineQuestionsService.framesPowerList.length; i++) {
      if (this._ITOTurbineQuestionsService.framesPowerList[i].frameId == frameId.frames) {
        this.powerList.push(this._ITOTurbineQuestionsService.framesPowerList[i]);
      }
    }
    console.log(this.powerList);
  }

  powerSel(power) {
    this.defaultQuestions = [];
  }

  getQuotationList() {
    this._ITOTurbineQuestionsService.getQuotationList().subscribe(res => {
      console.log(res)
      this._ITOTurbineQuestionsService.quotform = res;
      this.framesWithoutPower = res.dropDownColumnvalues.frames.FRAMES;
      //this.frameList = res.dropDownColumnvalues.frames.FRAMES;
      // = res.dropDownColumnvalues.frameWithPowerList.FRAMES_WITH_POWER;      

      for (let b = 0; b < this.framesPowerList.length; b++) {
        for (let a = 0; a < this.framesWithoutPower.length; a++) {
          if (this.framesPowerList[b].frameId == this.framesWithoutPower[a].frameId) {
            this.frameList.push(this.framesWithoutPower[b]);
            this.contains = true;
          }
        }
      }

      this.turbineTypesList = res.dropDownColumnvalues.typesOfTurbine.TURBINE;
      this.turbineCategoryList = res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;

      this._ITOTurbineQuestionsService.turbineCategoryList = this.turbineCategoryList;
      this._ITOTurbineQuestionsService.frameList = this.frameList;
      this._ITOTurbineQuestionsService.typesOfTurbine = this.turbineTypesList;
      // this._ITOTurbineQuestionsService.framesPowerList = res.dropDownColumnvalues.frameWithPowerList.FRAMES_WITH_POWER;
      console.log(this._ITOTurbineQuestionsService.frameList);

    })
  }

  geAllQuestions(turbineQues) {
    console.log(this.turbineCategoryList);
    console.log(this.turbineTypesList);
    console.log(turbineQues);

    this._ITOTurbineQuestionsService.getAdminForm().subscribe(res => {
      console.log(res);
      this.adminForm = res;

      res.turbineDesign = turbineQues.turbineCategory;
      this.turDesgn = turbineQues.turbineCategory;

      res.typeOfTurbine = turbineQues.turbineType;
      this.turTyp = turbineQues.turbineType;

      console.log(res);
      this._ITOTurbineQuestionsService.getAllF2FQues(res).subscribe(res => {
        console.log(res);
        this.questions = res.questionsBean;
        this.dropdownValues = res.questionsEntityList;
        this.updateRowGroupMetaData();
      })
    })

  }

  getUpdatedList() {
    console.log(this.turTyp);
    console.log(this.turDesgn);

    this._ITOTurbineQuestionsService.getAdminForm().subscribe(res => {
      res.turbineDesign = this.turDesgn;
      res.typeOfTurbine = this.turTyp;
      console.log(res);
      this._ITOTurbineQuestionsService.getAllF2FQues(res).subscribe(res => {
        console.log(res);
        this.questions = res.questionsBean;
        this.dropdownValues = res.questionsEntityList;
        this.updateRowGroupMetaData();
      })
    })

  }

  getQuestions(turbineQues) {
    console.log(turbineQues);
    this.hideSpinner = false;
    this.ansFrames = [];
    this._ITOTurbineQuestionsService.quotform.saveBasicDetails.framePowerId = turbineQues.framePower;
    this.frameId = turbineQues.framePower;
    console.log(this._ITOTurbineQuestionsService.quotform.saveBasicDetails.framePowerId);
    this._ITOTurbineQuestionsService.getQuestions(this._ITOTurbineQuestionsService.quotform).subscribe(resp => {
      this.hideSpinner = true;
      console.log(resp);
      this.defaultQuestions = resp.questionsBean;
      this.defaultDropdownValues = resp.questionsEntityList;
      console.log(this.defaultDropdownValues);
      this.updateRowGroupData();

    })
    for (let s = 0; s < this._ITOTurbineQuestionsService.framesPowerList.length; s++) {
      if ((this._ITOTurbineQuestionsService.framesPowerList[s].turbineDesignCd == turbineQues.turbineCate) && (this._ITOTurbineQuestionsService.framesPowerList[s].turbineCode == turbineQues.typeOfTurbine)) {
        this.ansFrames.push(this._ITOTurbineQuestionsService.framesPowerList[s]);
      }
    }
    console.log(this.ansFrames)
  }

  getUpdatedDefaultVal() {
    console.log(this._ITOTurbineQuestionsService.quotform.saveBasicDetails.framePowerId)
    this._ITOTurbineQuestionsService.getQuestions(this._ITOTurbineQuestionsService.quotform).subscribe(resp => {
      this.hideSpinner = true;
      console.log(resp);
    })
  }

  updateRowGroupMetaData() {
    this.dropdownValues = this.dropdownValues.sort(function (obj1, obj2) {
      return obj1.questionId - obj2.questionId;
    })
    console.log(this.dropdownValues)
    this.rowGroupdata = {};
    if (this.dropdownValues) {
      for (let i = 0; i < this.dropdownValues.length; i++) {
        let rowData = this.dropdownValues[i];
        let questionId = rowData.questionId;
        if (i == 0) {
          this.rowGroupdata[questionId] = { index: 0, size: 1 };
        }
        else {
          let previousRowData = this.dropdownValues[i - 1];
          let previousRowGroup = previousRowData.questionId;
          if (questionId === previousRowGroup)
            this.rowGroupdata[questionId].size++;
          else
            this.rowGroupdata[questionId] = { index: i, size: 1 };
        }

      }
    }
  }

  updateRowGroupData() {
    this.defaultDropdownValues = this.defaultDropdownValues.sort(function (obj1, obj2) {
      return obj1.questionId - obj2.questionId;
    })
    console.log(this.defaultDropdownValues)
    this.rowGroupMetadata = {};
    if (this.defaultDropdownValues) {
      for (let i = 0; i < this.defaultDropdownValues.length; i++) {
        let rowData = this.defaultDropdownValues[i];
        let questionId = rowData.questionId;
        if (i == 0) {
          this.rowGroupMetadata[questionId] = { index: 0, size: 1 };
        }
        else {
          let previousRowData = this.defaultDropdownValues[i - 1];
          let previousRowGroup = previousRowData.questionId;
          if (questionId === previousRowGroup)
            this.rowGroupMetadata[questionId].size++;
          else
            this.rowGroupMetadata[questionId] = { index: i, size: 1 };
        }

      }
    }
  }

  onQuesSelect(event) {
    console.log(event)
    this.selectedQuesDetails = this.cloneQues(event);

    this.Dropdowns = [];
    this.questionsBean = this.selectedQuesDetails;
    console.log(this.questionsBean)
    this.displayDialog = true;
  }

  onAnsSelect(event) {
    console.log(event)
    console.log(this.ansList)
    this.selectedQuesDetails = this.cloneQues(event);
    console.log(this.selectedQuesDetails)

    this.Dropdowns = [];
    for (let l = 0; l < this.dropdownValues.length; l++) {

      if (this.dropdownValues[l].questionId == this.selectedQuesDetails.questionId) {
        this.Dropdowns.push(this.dropdownValues[l]);
      }
    }

    console.log(this.Dropdowns);
    this.selectedQuesDetails.ansList = this.Dropdowns;
    this.questionsBean = this.selectedQuesDetails;
    console.log(this.selectedQuesDetails.ansList)
    this.displayAnsDialog = true;
  }

  AnswerSel(event) {
    console.log(event)
    this.selectedDetails = this.cloneQues(event);

    let Dropdowns = [];
    for (let l = 0; l < this.defaultQuestions.length; l++) {
      for (let d = 0; d < this.defaultQuestions[l].dropDownValueList.length; d++) {
        if (this.defaultQuestions[l].dropDownType.key == this.selectedDetails.questionId) {
          Dropdowns.push(this.defaultQuestions[l].dropDownValueList[d]);
        }

      }
    }
    console.log(Dropdowns);
    this.selectedDetails.ansList = Dropdowns;

    console.log(this.selectedDetails)
    this.displayDefaultDialog = true;
  }

  cloneQues(c: turbineQuesValues): turbineQuesValues {
    let turbineQues = new turbineQuesValues('');
    for (let prop in c) {
      turbineQues[prop] = c[prop];
    }
    return turbineQues;
  }

  addNewRow() {
    console.log(this.newAttribute)
    this.fieldArray.push(this.newAttribute)
    this.newAttribute = {};
    console.log(this.fieldArray)
    this.count++;
  }

  addNewAnswerRow() {
    console.log(this.newRowAttribute)
    this.rowArray.push(this.newRowAttribute)
    this.newRowAttribute = {};
    this.rowCount++;
  }

  saveQuesName() {
    console.log(this.questionsBean);
    this._ITOTurbineQuestionsService.getAdminForm().subscribe(res => {
      console.log(res);
      res.quesCode = this.questionsBean.questionCode;
      res.quesDesc = this.questionsBean.questionDesc;
      res.quesKey = this.questionsBean.questionId;
      res.modifiedBy = this._ITOLoginService.loggedUserDetails;
      console.log(res);
      this._ITOTurbineQuestionsService.addOrEditQuesAnswer(res).subscribe(res => {
        console.log(res);
        if (res.successCode == 0 && res.successMsg != null) {
          this._ITOLoginService.openSuccMsg("Question name updated successfully");
          //alert("Question name updated successfully");
          this.displayDialog = false;
          this.getUpdatedList();
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Question name updation Failed");
          //alert("Question name updation Failed");
        }
      })
    })

  }


  saveDefaultAns() {
    this.selectedQuesBean = this.selectedDetails;
    console.log(this.selectedQuesBean)
    for (let l = 0; l < this.selectedQuesBean.ansList.length; l++) {

      if (this.selectedQuesBean.ansList[l].key == this.defaultAnsKey) {
        this.selectedQuesBean.ansList[l].defaultVal = true;
      } else {
        this.selectedQuesBean.ansList[l].defaultVal = false;
      }

    }
    console.log(this.selectedQuesBean.ansList)
    this._ITOTurbineQuestionsService.getAdminForm().subscribe(res => {
      console.log(res);
      let ansList = [];
      let frames = [];
      console.log(this.frameId)
      frames.push(this.frameId)
      for (let i = 0; i < this.selectedQuesBean.ansList.length; i++) {
        if (this.selectedQuesBean.ansList[i].defaultVal) {
          let turbineAns = new turbineAnswers('');
          turbineAns.framePowerList = frames;
          turbineAns.quesKey = this.selectedQuesBean.questionId;
          turbineAns.quesDesc = this.selectedQuesBean.questionDesc;
          turbineAns.quesCode = this.selectedQuesBean.questionCode;
          turbineAns.ansKey = this.selectedQuesBean.ansList[i].key;
          turbineAns.ansDesc = this.selectedQuesBean.ansList[i].value;
          turbineAns.ansCode = this.selectedQuesBean.ansList[i].code;
          turbineAns.defaultFlag = this.selectedQuesBean.ansList[i].defaultVal;
          ansList.push(turbineAns);
        }
      }

      res.answersList = ansList;
      res.modifiedBy = this._ITOLoginService.loggedUserDetails;
      console.log(res);
      this._ITOTurbineQuestionsService.addOrEditQuesAnswer(res).subscribe(res => {
        console.log(res);
        if (res.successCode == 0 && res.successMsg != null) {
          this._ITOLoginService.openSuccMsg("Default Answer updated successfully");
          //alert("Default Answer updated successfully");
          this.displayDefaultDialog = false;
          this.getUpdatedDefaultVal();
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Default Answer updation Failed");
          //alert("Default Answer updation Failed");
        }
      })
    })
  }

  updateAns() {
    console.log(this.questionsBean);
    console.log(this.Dropdowns);

    this._ITOTurbineQuestionsService.getAdminForm().subscribe(res => {
      console.log(res);
      let ansList = [];
      for (let i = 0; i < this.Dropdowns.length; i++) {

        let turbineAns = new turbineAnswers('');
        turbineAns.quesKey = this.questionsBean.questionId;
        turbineAns.quesDesc = this.questionsBean.questionDesc;
        turbineAns.quesCode = this.questionsBean.questionCode;
        turbineAns.ansKey = this.Dropdowns[i].answerId;
        turbineAns.ansDesc = this.Dropdowns[i].answerDesc;
        turbineAns.ansCode = this.Dropdowns[i].answerCd;
        turbineAns.defaultFlag = this.Dropdowns[i].defaultVal;

        ansList.push(turbineAns);
      }
      console.log(ansList)
      res.answersList = ansList;
      res.modifiedBy = this._ITOLoginService.loggedUserDetails;
      this._ITOTurbineQuestionsService.addOrEditQuesAnswer(res).subscribe(res => {
        console.log(res);
        if (res.successCode == 0 && res.successMsg != null) {
          this._ITOLoginService.openSuccMsg("Answer updated successfully");
         // alert(" Answer updated successfully");
          this.displayAnsDialog = false;
          this.getUpdatedList();
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Answer updation Failed");
          //alert(" Answer updation Failed");
        }
      })
    })
  }

  saveAns(form, saveForm: NgForm) {
    console.log(this.selectedDetails);
    console.log(form);
    this.selectedQuesBean = this.selectedDetails;

    this._ITOTurbineQuestionsService.getAdminForm().subscribe(res => {
      let ansList = [];
      for (let i = 0; i <= this.rowCount; i++) {
        let selFramesList = [];
        let turbineAns = new turbineAnswers('');
        turbineAns.quesKey = this.selectedQuesBean.questionId;
        turbineAns.quesDesc = this.selectedQuesBean.questionDesc;
        turbineAns.quesCode = this.selectedQuesBean.questionCode;
        turbineAns.ansDesc = form[i];
        turbineAns.ansCode = form[i + 3];
        for (let k = 0; k < form[i + (3 * 2)].length; k++) {
          selFramesList.push(form[i + (3 * 2)][k].framePowerId);
        }
        turbineAns.framePowerList = selFramesList;
        ansList.push(turbineAns);

      }

      res.answersList = ansList;
      res.modifiedBy = this._ITOLoginService.loggedUserDetails;
      console.log(res);
      this._ITOTurbineQuestionsService.addOrEditQuesAnswer(res).subscribe(res => {
        console.log(res);
        if (res.successCode == 0 && res.successMsg != null) {
          this._ITOLoginService.openSuccMsg("Answer Added successfully");
          //alert(" Answer Added successfully");
          this.tabIndex = 2;
          this.disableTab = true;
          this.rowArray = [];
          saveForm.reset();
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Answer addition Failed");
          //alert(" Answer addition Failed");
        }
      })
    })
  }

  openAddAnsWindow(rowData) {
    console.log(rowData.questionId)

    //
    // this.selectedDetails = this.cloneQues(rowData);
    // console.log(this.dropdownValues)

    // for (let d = 0; d < this.dropdownValues.length; d++) {
    //   if (this.dropdownValues[d].questionId == this.selectedDetails.questionId) {
    //     this.Answers.push(this.dropdownValues[d]);
    //   }


    // }
    // console.log(this.Answers);
    // 
    this.selectedDetails = this.cloneQues(rowData);

    this.Answers = [];
    for (let l = 0; l < this.defaultQuestions.length; l++) {
      for (let d = 0; d < this.defaultQuestions[l].dropDownValueList.length; d++) {
        if (this.defaultQuestions[l].dropDownType.key == this.selectedDetails.questionId) {
          this.Answers.push(this.defaultQuestions[l].dropDownValueList[d]);
        }

      }
    }
    console.log(this.Answers);
    this.selectedDetails.ansList = this.Answers;
    console.log(this.selectedDetails)
    for (let i = 0; i < this._ITOTurbineQuestionsService.framesPowerList.length; i++) {
      if (this._ITOTurbineQuestionsService.quotform.saveBasicDetails.framePowerId == this._ITOTurbineQuestionsService.framesPowerList[i].framePowerId) {
        this.frameName = this._ITOTurbineQuestionsService.framesPowerList[i].framePowerDesc;
      }

    }
    this.tabIndex = 3;
    this.disableTab = false;
    console.log(this.tabIndex)
  }

  addNewQues(form, formm: NgForm) {
    console.log(form, formm)
    this.formm = formm;
    let ansList = [];
    for (let i = 0; i <= this.count; i++) {
      let selFramesList = [];
      let turbineAns = new turbineAnswers('');
      turbineAns.quesDesc = form.quesName;
      turbineAns.quesCode = form.quesCode;
      turbineAns.ansDesc = form[i + 4];
      this.answersList.push(form[i + 4])
      turbineAns.ansCode = form[i + (4 * 2)];
      for (let k = 0; k < form[i + (4 * 3)].length; k++) {
        selFramesList.push(form[i + (4 * 3)][k].framePowerId);
      }
      turbineAns.framePowerList = selFramesList;
      ansList.push(turbineAns);
    }
    console.log(this.answersList);
    this._ITOTurbineQuestionsService.answerbeanList = ansList;
    console.log(this._ITOTurbineQuestionsService.answerbeanList);
    if (this._ITOTurbineQuestionsService.answerbeanList.length == 1) {
      for (let j = 0; j < this._ITOTurbineQuestionsService.answerbeanList.length; j++) {
        this.defaultAns = this._ITOTurbineQuestionsService.answerbeanList[j].ansDesc;
      }
      this.displayAddDialog = false;
      this.saveAddAns();
    } else {
      this.displayAddDialog = true;

    }
    // formm.reset();
  }

  saveAddAns() {
    console.log(this.defaultAns)
    for (let j = 0; j < this._ITOTurbineQuestionsService.answerbeanList.length; j++) {
      if (this._ITOTurbineQuestionsService.answerbeanList[j].ansDesc === this.defaultAns) {
        this._ITOTurbineQuestionsService.answerbeanList[j].defaultFlag = true;
      }
    }
    console.log(this._ITOTurbineQuestionsService.answerbeanList)
    this._ITOTurbineQuestionsService.getAdminForm().subscribe(res => {

      res.answersList = this._ITOTurbineQuestionsService.answerbeanList;
      res.modifiedBy = this._ITOLoginService.loggedUserDetails;
      console.log(res);
      this._ITOTurbineQuestionsService.addOrEditQuesAnswer(res).subscribe(res => {
        console.log(res);
        if (res.successCode == 0 && res.successMsg != null) {
          this._ITOLoginService.openSuccMsg("Question-Answer created successfully");
          //alert(" Question-Answer created successfully");
          this.displayAddDialog = false;
          //this.index=0;
          this.formm.reset();
          this.fieldArray = [];
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Question-Answer creation Failed");
         // alert(" Question-Answer creation Failed");
        }
        this.displayAddDialog = false;
      })
    })
  }

  onDefaultChange(entry) {
    this.defaultAnsKey = entry.key;
  }

  checkedAnswer(ans) {
    this.defaultAns = ans;
  }

  Close() {
    this.rowArray = [];
    this.displayAnsDialog = false;
  }

  removeItem() {
    console.log("inside pop")
    this.fieldArray.pop();
    this.count = this.count - 1;
    console.log(this.fieldArray)
  }

  removeAnsItem() {
    console.log("inside  rowArray pop")
    this.rowCount = this.rowCount - 1;
    this.rowArray.pop();
  }

  handleChange(event) {
    var index = event.index;
    console.log(index)
    if (index == 0) {
      this.getQuotationList();
      this.questions = [];
    }
    if (index == 3) {
      this.disableTab = false;
    } else {
      this.disableTab = true;
    }
    this.tabIndex = index;
  }
  onKeyQues(event: any) { // without type info
    console.log(event.target.value);

  }

}
