import { Component, OnInit } from '@angular/core';
import { ITOAddFrameService } from './ito-add-frame.service';
import { itoAdmiAddFrame } from './ito-add-frame';
import { THIS_EXPR } from '../../../node_modules/@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-ito-add-frame',
  templateUrl: './ito-add-frame.component.html',
  styleUrls: ['./ito-add-frame.component.css']
})
export class ItoAddFrameComponent implements OnInit {
//Boolean to display add or update dive
  addDiv: boolean = false;
  updateDiv: boolean = false;

  //Array to store turbine type, design and frames
  TurbineTypeArray: Array<any> = [];
  TurbineDesignArray: Array<any> = [];
  FrameArray: Array<any> = [];

  //Variable to store newly added frame
  addFrameObj: itoAdmiAddFrame;

  //variable to store turbine design
  turbineDesig: String;

  //variable to store turbine type
  turbineType: String;

  //variable to store temp frame arruy
  tempFrameArray: Array<any> = [];

  constructor(private _ITOAddFrameService: ITOAddFrameService) {
    // this.TurbineDesignArray=['Please Select','Impulsive','Reactive'];
    // this.TurbineTypeArray=['Please Select','Back Pressure','Condenser'];
    // this.FrameArray= ['Please Select','TST-1005-HB','TST-1015-HB','TST-1025-HB','TST-1325-HB'];
    this._ITOAddFrameService.getQuotationList().subscribe(res => {
      console.log(res);
      for (var i = 0; i < res.dropDownColumnvalues.typesOfTurbine.TURBINE.length; i++) {
        this.TurbineTypeArray.push(res.dropDownColumnvalues.typesOfTurbine.TURBINE[i]);
      }
      for (var i = 0; i < res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES.length; i++) {
        this.TurbineDesignArray.push(res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES[i]);
      }
      for (var i = 0; i < res.dropDownColumnvalues.frames.FRAMES.length; i++) {
        this.FrameArray.push(res.dropDownColumnvalues.frames.FRAMES[i]);
      }
    })

  }

  ngOnInit() {
  }
  //Method to enable add div
  enableAdd() {
    this.addDiv = true;
    this.updateDiv = false;
  }
  //method to enable update div
  enableUpdate() {
    this.addDiv = false;
    this.updateDiv = true;
  }
  //method to fetch drop down details based on selceted dropdown
  TOTSel(val) {
    this.tempFrameArray.slice(1, this.tempFrameArray.length);
    if (val == "Back Pressure") {
      this.turbineType = "BP";
    } else {
      this.turbineType = "CD";
    }
    for (var i = 0; i < this.FrameArray.length; i++) {
      if (this.turbineType == this.FrameArray[i].turbineCode && this.turbineDesig == this.FrameArray[i].turbineDesignCd) {
        this.tempFrameArray.push(this.FrameArray[i]);
      }
    }
    console.log(this.tempFrameArray.length);
  }
  //method to fetch drop down details based on selceted dropdown
  TDSel(val) {
    this.tempFrameArray.slice(1, this.tempFrameArray.length);
    if (val == "Impulse") {
      this.turbineDesig = "IM";
    } else {
      this.turbineDesig = "RN";
    }
    for (var i = 0; i < this.FrameArray.length; i++) {
      if (this.turbineType == this.FrameArray[i].turbineCode && this.turbineDesig == this.FrameArray[i].turbineDesignCd) {
        this.tempFrameArray.push(this.FrameArray[i]);
      }
    }
    console.log(this.tempFrameArray.length);
  }
  newVariantUpdate(newVariantUpdateForm) {

  }

}
