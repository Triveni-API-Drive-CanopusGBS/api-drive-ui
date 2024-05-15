import { Component, OnInit } from '@angular/core';
import { ItoAdminVarientCodeService } from './ito-admin-varient-code.service';
import { varientCode } from './ito-admin-varient-code';
import { turbineAnswers } from '../ito-turbine-questions/ito-turbine-answers';
import { ITOLoginService } from '../app.component.service';
import { ITOturbineConfigService } from  '../ito-turbine-config/ito-turbine-config.service'
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { DomSanitizer } from '@angular/platform-browser';
import { saveAs } from 'file-saver';
//import * as jsPDF from 'jspdf';
import { Pipe, PipeTransform } from '@angular/core';

import 'rxjs/Rx' ;
declare let jsPDF;
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOScopeOfSupplyService } from '../ito-scope-of-supply/ito-scope-of-supply.service';
import { ItoUserManualService } from '../ito-user-manual/ito-user-manual.service';
import { sample } from 'rxjs/operator/sample';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
@Component({
  selector: 'app-ito-admin-varient-code',
  templateUrl: './ito-admin-varient-code.component.html',
  styleUrls: ['./ito-admin-varient-code.component.css']
})
export class ItoAdminVarientCodeComponent implements OnInit {

  typeOfBlades: Array<any> = [];
  typeOfTurbine: Array<any> = [];
  condenserTypes: Array<any> = [];
  frames: Array<any> = [];
  framesWithPower: Array<any> = [];
  newFrames: Array<any> = [];
  savBasicDet: any;
  enableCT: boolean;
  powerList: Array<any> = [];
  selectedFrame: any;
  typeOfBlade: any;
  quesBean: Array<any> = [];
  tempRes: any;
  defaultValues: Array<any> = [];
  Labels: Array<any> = [];
  Dropdowns: Array<any> = [];
  varientCode: string;
  showQues: boolean = false;
  customers: Array<any> = [];
  varientCodeTmplt: varientCode;
  showcAndCustDiv: boolean = false;
  isStndLone:boolean;
  sucessboolean:boolean=false;
  successMsg:string='';
  varientCodeTemp: Array<any> = [];
  deleteBoolean: Array<boolean> = [];
  deleteBooleantemp: Array<boolean> = [];
  selectedVariant:any;
  displayDialog:boolean=false;
  cols: { field: string; header: string; }[];
  addVarient:boolean=false;
  updateVarianttemp:boolean=false;
  variantList:any;
  booleanButton:boolean=false;
  spinner:boolean=true;

  constructor(private _ItoAdminVarientCodeService: ItoAdminVarientCodeService, private router: Router, private _ITOLoginService: ITOLoginService) {
    this._ItoAdminVarientCodeService.getquotCache().subscribe(res => {
      console.log(res);
      this.tempRes = res;
      this.savBasicDet = res.saveBasicDetails;
      this.typeOfBlades = res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
      this.typeOfTurbine = res.dropDownColumnvalues.typesOfTurbine.TURBINE;
      this.condenserTypes = res.dropDownColumnvalues.typesOfCondensor.CONDENSOR;
      this.frames = res.dropDownColumnvalues.frames.FRAMES;
      this.framesWithPower = res.dropDownColumnvalues.typeOfNewList.FRAMES_WITH_POWER;

    })
    
        // this._ItoAdminVarientCodeService.getVariant().subscribe(res => {
        //   this.varientCodeTemp=res.dropDownColumnvalues.placeList7.placeList7;
        //   console.log(this.varientCodeTemp);
        //   for(let j=0;j<this.varientCodeTemp.length;j++)
        //   {
            
  
        //      this.cols = [
        //       { field: 'frameName', header: 'Frame Name' },
        //       { field: 'framePowerId', header: 'Power' },
        //       { field: 'c_num', header: 'C-Num' },


  
        //      ];
        //     }
        // })

  }

  ngOnInit() {
  }
  displayAdd()
  {
    this.booleanButton=false;
    this.variantList=[];
this.addVarient=true;
this.updateVarianttemp=false;
this._ItoAdminVarientCodeService.getquotCache().subscribe(res => {
  console.log(res);
  this.tempRes = res;
  this.savBasicDet = res.saveBasicDetails;
  this.typeOfBlades = res.dropDownColumnvalues.typesOfNewTurbine.TURBINE_NEW_TYPES;
  this.typeOfTurbine = res.dropDownColumnvalues.typesOfTurbine.TURBINE;
  this.condenserTypes = res.dropDownColumnvalues.typesOfCondensor.CONDENSOR;
  this.frames = res.dropDownColumnvalues.frames.FRAMES;
  this.framesWithPower = res.dropDownColumnvalues.typeOfNewList.FRAMES_WITH_POWER;

})
  }
  displayUpdate()
  {
    this.booleanButton=false;
    this.variantList=[];
    this.addVarient=false;
    this.updateVarianttemp=true;
    this.spinner=false;
    this._ItoAdminVarientCodeService.getVariant().subscribe(res => {
      this.varientCodeTemp=res.dropDownColumnvalues.placeList7.placeList7;
      console.log(this.varientCodeTemp);
      this.spinner=true;
      for(let j=0;j<this.varientCodeTemp.length;j++)
      {
        

         this.cols = [
          { field: 'frameName', header: 'Frame Name' },
          { field: 'framePowerId', header: 'Power' },
          { field: 'c_num', header: 'C-Num' },



         ];
        }
    })
  }

 
  closeDialog()
  {
    this.sucessboolean=false;
    this.router.navigate(['defaultHome']);
  }
  TOBsel(typeOfBlade) {
    console.log(typeOfBlade);
    for (let b = 0; b < this.typeOfBlades.length; b++) {
      if (this.typeOfBlades[b].categoryDetDesc === typeOfBlade) {
        this.typeOfBlade = this.typeOfBlades[b].categoryDetCode;
      }
    }
  }

  TOTsel(typeOfTransport) {
    this.newFrames = [];
    console.log(typeOfTransport);
    if (typeOfTransport == "Condensing") {
      this.enableCT = true;

    }
    else if (typeOfTransport == "Back Pressure") {
      this.enableCT = false;

    }
    for (let i = 0; i < this.typeOfTurbine.length; i++) {
      if (this.typeOfTurbine[i].categoryDetDesc == typeOfTransport) {
        for (let j = 0; j < this.frames.length; j++) {
          if (this.typeOfTurbine[i].categoryDetCode == this.frames[j].turbineCode) {
            if (this.frames[j].turbineDesignCd == this.typeOfBlade) {
              this.newFrames.push(this.frames[j]);
            }
          }
        }
      }
    }
    this.newFrames = this.newFrames.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.frameId === current.frameId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
    console.log(this.newFrames);
  }
  optionSelChange()
  {
this.showcAndCustDiv=false;
  }
  FrameSel(frme) {
    this.powerList = [];
    console.log(frme);
    for (let fw = 0; fw < this.framesWithPower.length; fw++) {
      if (this.framesWithPower[fw].frameDesc === frme) {
        this.powerList.push(this.framesWithPower[fw].maximumPower);
        this.selectedFrame = frme;
      }
    }
    this.powerList = this.powerList.reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item === current);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
  }
  PowSel(pwr) {
    console.log(+pwr);
    for (let fw = 0; fw < this.framesWithPower.length; fw++) {
      if (this.framesWithPower[fw].maximumPower === +pwr && this.framesWithPower[fw].frameDesc === this.selectedFrame) {
        this.savBasicDet.framePowerId = this.framesWithPower[fw].framePowerId;
        this.savBasicDet.frameName = this.framesWithPower[fw].frameDesc;
        this.savBasicDet.power = this.framesWithPower[fw].maximumPower;
        this.savBasicDet.modifiedById = this._ITOLoginService.loggedUserDetails;
      }
    }
    console.log(this.savBasicDet);
  }

  basicVarient(basicVarientForm) {
    this.defaultValues = [];
    this.quesBean = [];
    this.Labels = [];
    this.Dropdowns = [];
    console.log(basicVarientForm);
    this.tempRes.saveBasicDetails = this.savBasicDet;
    console.log(this.savBasicDet.framePowerId);
    this._ItoAdminVarientCodeService.getQuestionsPage(this.tempRes).subscribe(quesRes => {
      console.log(quesRes);
      this.showQues = true;
      this.quesBean = quesRes.questionsBean;
      for (let l = 0; l < this.quesBean.length; l++) {
        this.Labels.push(this.quesBean[l].dropDownType);
        for (let d = 0; d < this.quesBean[l].dropDownValueList.length; d++) {
          this.Dropdowns.push(this.quesBean[l].dropDownValueList[d]);
          if (this.quesBean[l].dropDownValueList[d].defaultVal == true) {
            this.defaultValues.push(this.quesBean[l].dropDownValueList[d].value)
          }
        }
        console.log(this.defaultValues);
      }

      console.log(this.Labels, this.Dropdowns);
    });
    this._ItoAdminVarientCodeService.getCustomers().subscribe(custRes => {
      console.log(custRes);
      this.customers = custRes.customerProfileForm.customerList;
    })
  }

  getQesAns(ansCodes) {
    console.log(ansCodes);
    this.varientCode = '';
    console.log(this.defaultValues);
    //codes recieved from UI

    for (let l = 0; l < this.quesBean.length; l++) {
      this.Labels.push(this.quesBean[l].dropDownType);
      for (let d = 0; d < this.quesBean[l].dropDownValueList.length; d++) {
        this.Dropdowns.push(this.quesBean[l].dropDownValueList[d]);
        if (this.quesBean[l].dropDownValueList[d].value == this.defaultValues[l]) {
          this.varientCode += this.quesBean[l].dropDownValueList[d].code;
          if (l != this.defaultValues.length - 1) {
            this.varientCode += "|";
          }
        }
      }
    }
    console.log(this.varientCode);
    this.showcAndCustDiv = true;
  }
  //validating for 4 digits
  checkFourDigit($event){

  }
  cAndCust(cAndCustInfo) {
    console.log(cAndCustInfo);
    this.varientCodeTmplt = new varientCode();
    this.varientCodeTmplt.cNum = "C-" + cAndCustInfo.cNum;
    for (let c = 0; c < this.customers.length; c++) {
      if (this.customers[c].custName === cAndCustInfo.customer) {
        this.varientCodeTmplt.custName = this.customers[c].custName;
        this.varientCodeTmplt.custId = this.customers[c].custId;
      }
    }
    this.varientCodeTmplt.variantCode = this.varientCode;
    this.varientCodeTmplt.frameId = this.savBasicDet.framePowerId;
    this.varientCodeTmplt.frameName = this.savBasicDet.frameName;
    this.varientCodeTmplt.variantActive = 1;
    this.tempRes.saveBasicDetails = this.savBasicDet;
    this.tempRes.cprojectWithVariantCodeList.push(this.varientCodeTmplt);
    this._ItoAdminVarientCodeService.addOrEditVariantCode(this.tempRes).subscribe(resultRes => {
      this.sucessboolean=true;
      this.successMsg=resultRes.successMsg;
      console.log(resultRes);
    })
  }

  saveEdit()
  {
   let tempboleean=false;

    if (this.variantList.length != 0) {
      for(let j=0;j<this.variantList.length;j++)
      {
    if(this.variantList[j].variant_id==this.selectedVariant.variant_id)
    {
      this.variantList[j]=this.selectedVariant;
      tempboleean=true;
  
    }
      }
      if(tempboleean==false)
      {
        this.variantList.push(this.selectedVariant);
  
      }
      
    }
    else
    {
      this.variantList.push(this.selectedVariant);

    }
    this.displayDialog=false;
  }
  deletEdit(event,rowData)
  {
    this.displayDialog = false;
    if (event.target.checked) {
      this.deleteBoolean[rowData.variant_id]=true;
      this.deleteBooleantemp[rowData.variant_id]=true;
    }
    else if (!event.target.checked) {
      this.deleteBoolean[rowData.variant_id]=false;     
       this.deleteBooleantemp[rowData.variant_id]=false;
    }
          // if(this.scopeOfSuplyChe.includes("ELE")){

    if(this.deleteBooleantemp.includes(true))
    {
      this.booleanButton=true;
      
    }
    else
    {
      this.booleanButton=false;
    }

  }
  updateVariant()
  {
    this.tempRes.cprojectWithVariantCodeList=[];
    for(let j=0;j<this.variantList.length;j++)
    {

    
    this.varientCodeTmplt = new varientCode();
if(this.deleteBooleantemp[this.variantList[j].variant_id]==true)
{
  this.varientCodeTmplt.id = this.variantList[j].variant_id;

  this.varientCodeTmplt.cNum = null;
 
      // this.varientCodeTmplt.custName = this.varientCodeTemp[j].custName;
       this.varientCodeTmplt.custId =null;

  this.varientCodeTmplt.variantCode =null;
  this.varientCodeTmplt.frameId = null;
 // this.varientCodeTmplt.frameName = this.varientCodeTemp[j];
  this.varientCodeTmplt.variantActive = 0;
}
else
{
  this.varientCodeTmplt.id = this.variantList[j].variant_id;

    this.varientCodeTmplt.cNum = this.variantList[j].c_num;
   
        // this.varientCodeTmplt.custName = this.varientCodeTemp[j].custName;
         this.varientCodeTmplt.custId =this.variantList[j].cust_id;

    this.varientCodeTmplt.variantCode =this.variantList[j].variant_cd;
    this.varientCodeTmplt.frameId = this.variantList[j].framePowerId;
   // this.varientCodeTmplt.frameName = this.varientCodeTemp[j];
    this.varientCodeTmplt.variantActive = 1;
}


    this.tempRes.cprojectWithVariantCodeList.push(this.varientCodeTmplt);
    }
    if(this.variantList.length==0)
    {
      for(let j=0;j<this.deleteBooleantemp.length;j++)
      {
        if(this.deleteBooleantemp[j]==true)
        {

      
      this.varientCodeTmplt = new varientCode();

      this.varientCodeTmplt.id = j;

      this.varientCodeTmplt.cNum = null;
     
          // this.varientCodeTmplt.custName = this.varientCodeTemp[j].custName;
           this.varientCodeTmplt.custId =null;
    
      this.varientCodeTmplt.variantCode =null;
      this.varientCodeTmplt.frameId = null;
     // this.varientCodeTmplt.frameName = this.varientCodeTemp[j];
      this.varientCodeTmplt.variantActive = 0;
      this.tempRes.cprojectWithVariantCodeList.push(this.varientCodeTmplt);

    }
  }
    }
    this.tempRes.saveBasicDetails = this.savBasicDet;

    this._ItoAdminVarientCodeService.addOrEditVariantCode(this.tempRes).subscribe(resultRes => {
      this.sucessboolean=true;
      this.successMsg=resultRes.successMsg;
      console.log(resultRes);
    })
  
  }
  deleteVariant()
  {
    for(let j=0;j<this.varientCodeTemp.length;j++)
    {
if(this.deleteBooleantemp[this.varientCodeTemp[j].variant_id]==true)
{
  this.varientCodeTmplt = new varientCode();
  this.varientCodeTmplt.id = this.varientCodeTemp[j].variant_id;

    this.varientCodeTmplt.cNum = null;
   
        // this.varientCodeTmplt.custName = this.varientCodeTemp[j].custName;
         this.varientCodeTmplt.custId = null;

    this.varientCodeTmplt.variantCode =null;
    this.varientCodeTmplt.frameId =null;
   // this.varientCodeTmplt.frameName = this.varientCodeTemp[j];
 // this.varientCodeTmplt.frameName = this.varientCodeTemp[j];
  this.varientCodeTmplt.variantActive = 0;

    this.tempRes.saveBasicDetails = this.savBasicDet;
    this.tempRes.cprojectWithVariantCodeList.push(this.varientCodeTmplt);
}
    }
    this._ItoAdminVarientCodeService.addOrEditVariantCode(this.tempRes).subscribe(resultRes => {
      this.sucessboolean=true;
      this.successMsg=resultRes.successMsg;
      console.log(resultRes);
    })
  }
  rowSelected(rowData)
  {
    console
    this.displayDialog = true;
    this.selectedVariant = rowData;  }
}
