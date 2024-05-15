import { Component, OnInit } from '@angular/core';
import { ITOAddNewQuestionsService } from '../ito-add-new-questions/ito-add-new-questions.service';
import { turbineQuesValues } from '../ito-turbine-questions/ito-turbine-questions';
import { CheckboxModule } from 'primeng/checkbox';


@Component({
  selector: 'app-ito-add-new-questions',
  templateUrl: './ito-add-new-questions.component.html',
  styleUrls: ['./ito-add-new-questions.component.css']
})
export class ItoAddNewQuestionsComponent implements OnInit {

  turDesgn: String;
  turTyp: String;
  bladeType: string;

  questionsBean: Array<any> = [];
  quesAnswers: Array<any> = [];
  newFrameWithPowersList: Array<any> = [];
  turbineTypesList: Array<any> = [];
  turbineCategoryList: Array<any> = [];
  frameList: Array<any> = [];
  selectedQues: string[] = [];

  adminForm: any;
  rowGroupMetadata: any;
  rowGroupdata: any;
  selectedAns: Array<any> = [];

  constructor(private _ITOAddNewQuestionsService: ITOAddNewQuestionsService) {


    this._ITOAddNewQuestionsService.getAdminForm().subscribe(res => {
      console.log(res);
      this.adminForm = res;
      this._ITOAddNewQuestionsService.getNewFramesForQuestions().subscribe(resp => {
        console.log(resp)
        this.newFrameWithPowersList = resp.newFrameWithPowersList;
        console.log(this.newFrameWithPowersList)
      });
    });
  }

  ngOnInit() {
    this._ITOAddNewQuestionsService.getQuotationList().subscribe(resp => {
      console.log(resp)
      this.turbineTypesList = resp.dropDownColumnvalues.typesOfTurbine.TURBINE;
      this.turbineCategoryList = resp.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
      console.log(this.turbineCategoryList);
    });
  }

  turbineDesnSel(val) {
    console.log(val);
    this.bladeType = val;
  }

  TOTSel(value) {
    console.log(value);
    this.frameList = [];
    for (let j = 0; j < this.newFrameWithPowersList.length; j++) {
      if (this.newFrameWithPowersList[j].turbineCode == value && (this.newFrameWithPowersList[j].turbineDesignCd == this.bladeType)) {
        this.frameList.push(this.newFrameWithPowersList[j]);
      }
    }
  }

  FrameSel(frame) {
    let val = frame.value;
    console.log(val)

    this.adminForm.turbineDesign = val.turbineDesignCd;
    this.turDesgn = val.turbineDesignCd;

    this.adminForm.typeOfTurbine = val.turbineCode;
    this.turTyp = val.turbineCode;

    console.log(this.adminForm);
    this._ITOAddNewQuestionsService.getAllF2FQues(this.adminForm).subscribe(res => {
      console.log(res);
      this.questionsBean = res.questionsBean;
      this.quesAnswers = res.questionsEntityList;
      this.updateRowGroupData();

      console.log(this.quesAnswers);
    })


  }

  updateRowGroupData() {
    this.quesAnswers = this.quesAnswers.sort(function (obj1, obj2) {
      return obj1.questionId - obj2.questionId;
    })
    console.log(this.quesAnswers)
    this.rowGroupMetadata = {};
    if (this.quesAnswers) {
      for (let i = 0; i < this.quesAnswers.length; i++) {
        let rowData = this.quesAnswers[i];
        let questionId = rowData.questionId;
        if (i == 0) {
          this.rowGroupMetadata[questionId] = { index: 0, size: 1 };
        }
        else {
          let previousRowData = this.quesAnswers[i - 1];
          let previousRowGroup = previousRowData.questionId;
          if (questionId === previousRowGroup)
            this.rowGroupMetadata[questionId].size++;
          else
            this.rowGroupMetadata[questionId] = { index: i, size: 1 };
        }

      }
    }
  }

  updateGroupData() {
    this.selectedAns = this.selectedAns.sort(function (obj1, obj2) {
      return obj1.questionId - obj2.questionId;
    })
    console.log(this.selectedAns);
    this.rowGroupdata = {};
    if (this.selectedAns) {
      for (let i = 0; i < this.selectedAns.length; i++) {
        let rowData = this.selectedAns[i];
        let questionId = rowData.questionId;
        if (i == 0) {
          this.rowGroupdata[questionId] = { index: 0, size: 1 };
        }
        else {
          let previousRowData = this.selectedAns[i - 1];
          let previousRowGroup = previousRowData.questionId;
          if (questionId === previousRowGroup)
            this.rowGroupdata[questionId].size++;
          else
            this.rowGroupdata[questionId] = { index: i, size: 1 };
        }

      }
    }
  }

  handleChange(ans) {
    console.log(this.selectedAns);
    this.updateGroupData();
  }

  attachQuesAns() {
    console.log(this.selectedAns);
  }

  arrangeQues(ans) {
    console.log(this.selectedAns);
    this.updateGroupData();
  }

}
