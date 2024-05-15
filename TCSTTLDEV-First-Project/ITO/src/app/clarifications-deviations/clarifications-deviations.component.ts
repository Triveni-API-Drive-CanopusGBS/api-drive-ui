import { ITOturbineConfigService } from  '../ito-turbine-config/ito-turbine-config.service'
import { ClarificationsDeviationsService } from './clarifications-deviations.service';
import { Component, OnInit } from '@angular/core';
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
import { ITOLoginService } from '../app.component.service';
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
import { dboClass } from '../ito-performance/ito-performance';

@Component({
  selector: 'app-clarifications-deviations',
  templateUrl: './clarifications-deviations.component.html',
  styleUrls: ['./clarifications-deviations.component.css']
})
export class ClarificationsDeviationsComponent implements OnInit {

  // addedPdf: any;
  dboClass: dboClass;
  fileNm: Array<any>=[];
  fileName: Array<any> = [];
  addedPdf: Array<any> = [];
  dboForm: any;
  ssId: number = 0;
  itemId: number;
  docType: string;
  userImageH:string;
  item: Array<any> =[];
  dialogMsgApp: boolean = false;
  dialogMsgVal: string = '';
  backBtn: boolean = false;
  attachmentList: Array<any> = [];
  clarifiAttach: string = 'clarifiAttach';
  clarificationLocal: Array<any> = [];
  rmkValOut: string = '';
  scopeofsupp: string = 'scopeOfsup';
  mainSave:boolean=true;

  constructor(private _ITOturbineConfigService: ITOturbineConfigService, private _ITOScopeOfSupplyService: ITOScopeOfSupplyService,
  private _ClarificationsDeviationsService: ClarificationsDeviationsService, private router: Router,
  private _ITOCostEstimationService: ITOCostEstimationService,
  private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ItoUserManualService: ItoUserManualService,
  private _ITOeditQoutService: ITOeditQoutService,  @Inject(LOCAL_STORAGE) private storage: WebStorageService,) { 
    
    if(this._ITOeditQoutService.checkEdit == false){
      this.backBtn = true;
    }
    this._ITOeditQoutService.button1=false;
      this._ITOeditQoutService.button2=false;
      this._ITOeditQoutService.button3=false;
      this._ITOeditQoutService.button4=false;
      this._ITOeditQoutService.button5=false;
      this._ITOeditQoutService.button6=false;
      this._ITOeditQoutService.button7=false;
      this._ITOeditQoutService.button8=true;
      this._ITOeditQoutService.button9=false;
    this.fileName = [];
    this.item = [];
    this.fileNm = [];
    this.attachmentList =[];
    this._ITOturbineConfigService.getDboFormData().subscribe(resp => {
      console.log(resp);
      this.dboForm = resp;    
      console.log(this._ITOScopeOfSupplyService.sampleScope);
      this.ssId = 0;
      for(let k=0; k<this._ITOScopeOfSupplyService.sampleScope.length; k++){
        if(this._ITOScopeOfSupplyService.sampleScope[k].scopeCode == "CD"){
          this.ssId = this._ITOScopeOfSupplyService.sampleScope[k].ssId;
        }
      }console.log(this.ssId);
    
    this.clarificationLocal[this.clarifiAttach] = this.storage.get(this.clarifiAttach);
    console.log(this.storage.get(this.clarifiAttach));
    
    if(this._ITOcustomerRequirementService.editFlag == true && this._ITOeditQoutService.checkEdit==false){ 
      if(this._ITOeditQoutService.attachmentDataList != undefined && this._ITOeditQoutService.attachmentDataList.length > 0) {
        for(let n=0;n<this._ITOeditQoutService.attachmentDataList.length; n++){
          if(this.ssId == this._ITOeditQoutService.attachmentDataList[n].ssId){
            this.attachmentList.push(this._ITOeditQoutService.attachmentDataList[n]);
            this.fileNm.push(this._ITOeditQoutService.attachmentDataList[n]);
            this.item.push(this._ITOeditQoutService.attachmentDataList[n].item);
          }
        }
        console.log(this.attachmentList);
        console.log(this.fileName);
       console.log(this.item);
       if(this.attachmentList.length>0){
         this.rmkValOut = this.attachmentList[0].remarks;
       }
      }
    }else if(this.storage.get(this.clarifiAttach) != null){
      if(this.storage.get(this.clarifiAttach).attachmentList.length > 0){
        for(let m=0; m<this.storage.get(this.clarifiAttach).attachmentList.length; m++){
          if(this.ssId == this.storage.get(this.clarifiAttach).attachmentList[m].ssId){
        this.attachmentList.push(this.storage.get(this.clarifiAttach).attachmentList[m]);
          }
        }
      }
      console.log(this.attachmentList);
      this.fileNm=[];
      for(let p=0; p<this.attachmentList.length; p++){
        this.dboClass = new dboClass();
        this.dboClass.itemName= this.attachmentList[p].itemName;
        this.dboClass.slNo=this.attachmentList[p].slNo;
        this.dboClass.name=this.attachmentList[p].name;
        this.dboClass.item = this.attachmentList[p].item;
        this.fileNm.push(this.dboClass);
      }
      if(this.attachmentList.length>0){
        this.rmkValOut = this.attachmentList[0].remarks;
      }
      if(this.storage.get(this.clarifiAttach).item.length > 0){
        this.item = this.storage.get(this.clarifiAttach).item;
      }
    } 
  });   
  }

  ngOnInit() {
  }

  //to save in localstorage
 saveInLocal(key, val): void {
  console.log('recieved= key:' + key + 'value:' + val);
  this.storage.set(key, val);
  this.clarificationLocal[key] = this.storage.get(key);
}

//On keypress event to store user input remarks
remks(){
  console.log(this.rmkValOut);
}

  fileUpload(fileInput: any) {
    this.fileName=[];
    this.item=[];
    console.log(fileInput);
    if (fileInput.target.files && fileInput.target.files.length > 0) {
      for(let m=0; m<fileInput.target.files.length; m++){
      let file = fileInput.target.files[m];
      let reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.item.push(reader.result.split(',')[1]);
        this.fileName.push(file);
      };
      console.log(this.item);
      console.log(this.fileName);
      console.log(reader.result);
    }
  }
  }
  // addToFolder(addPdfForm){
  //   console.log(addPdfForm);
  //   //this.addedPdf = addPdfForm;
  //   this.addedPdf.push(addPdfForm);
  //   console.log(this.addedPdf);
  // }
  saveFileNm(){
    console.log(this.item);
    console.log(this.fileName);
    for(let k=0; k<this.item.length;k++){
      this.dboClass=new dboClass();
      this.dboClass.itemName = this.fileName[k].name;
      this.dboClass.name = this.fileName[k].name;
      this.dboClass.item = this.item[k];
      this.fileNm.push(this.dboClass);
    }
  }
  cancelAddOn(o){
    console.log(o);
    this.fileName.splice(o,1);
    this.fileNm.splice(o,1);
    this.item.splice(o,1);
  }
  drpCheckQty(val, Rec){
    console.log(val);
    console.log(Rec);
      console.log(this.fileNm);
    for(let o=0; o<this.fileNm.length; o++){
      if(this.fileNm[o].itemName == Rec.itemName){
        this.fileNm[o].name = val;
      }
    }
  }
  finalSave(){
    this.dboForm.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboForm.ssId = this.ssId;
  this.dboForm.newItem = null;
  this.dboForm.slNo =null;
  this.dboForm.fileName = null;
  this.dboForm.filePath = null;
  this.dboForm.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById; 
  this.dboForm.remarks = null;
  this.dboForm.deleteFlag=1;
  this.dboForm.item = null;
  this._ClarificationsDeviationsService.saveAttachment(this.dboForm).subscribe(resp => {
  });
    console.log(this.dboForm.item);
    console.log(this.fileName);
    console.log(this.fileNm);
    this.attachmentList = [];
    if(this.fileNm.length>0){
      for(let i=0; i<this.fileNm.length; i++){
      this.dboForm.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  this.dboForm.ssId = this.ssId;
  this.dboForm.newItem = this.fileNm[i].itemName;
  this.dboForm.slNo = i+1;
  this.dboForm.fileName = this.fileNm[i].name;
  this.dboForm.filePath = "path";
  this.dboForm.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById; 
  this.dboForm.remarks = this.rmkValOut;
  this.dboForm.deleteFlag = 0;
  this.dboForm.item = this.fileNm[i].item;
  // else if(this.rmkValOut!=null){
  //   this.dboForm.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
  //   this.dboForm.ssId = this.ssId;
  //   this.dboForm.newItem = null;
  //   this.dboForm.filePath = null;
  //   this.dboForm.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById; 
  //   this.dboForm.remarks = this.rmkValOut;
  //   this.dboForm.item = null;
  // }
console.log(this.dboForm);
this.attachmentList=[];
    this._ClarificationsDeviationsService.saveAttachment(this.dboForm).subscribe(resp => {
      console.log(resp);
      this.attachmentList = resp.attachmentList;
    //   if(resp.attachmentList.length > 0){
    //   if(resp.attachmentList.length > 1){
    //     for(let b=0; b<resp.attachmentList.length; b++){
    //       this.attachmentList.push(resp.attachmentList[b]);
    //     }
    //   }else if(resp.attachmentList.length == 1){
    //   this.attachmentList.push(resp.attachmentList[0]);
    //   }
    // }
      if (resp.successCode == 0) {
        this.dialogMsgApp = true;
        this.dialogMsgVal= "Saved Successfully";
        this._ITOcustomerRequirementService.sendtecBtnStatus(true);
        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'CD') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }
      } else {
        this.dialogMsgApp = true;
        this.dialogMsgVal = resp.successMsg; 
      }
      // window.location.reload();
      this.saveInLocal(this.clarifiAttach, {
        attachmentList: this.attachmentList, fileName: this.fileName, item: this.item
      });
      // if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
      //   this._ITOcustomerRequirementService.editFlag = false;
      //   this.router.navigate(['/EditQuot']);
      // }
      this.mainSave=false;
    }); 
  }
}
  }
  public base64ToBlob(b64Data, contentType='', sliceSize=512) {
    b64Data = b64Data.replace(/\s/g, ''); //IE compatibility...
    let byteCharacters = atob(b64Data);
    let byteArrays = [];
    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
        let slice = byteCharacters.slice(offset, offset + sliceSize);

        let byteNumbers = new Array(slice.length);
        for (var i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }
        let byteArray = new Uint8Array(byteNumbers);
        byteArrays.push(byteArray);
    }
    return new Blob(byteArrays, {type: contentType});
}


downloadFile(value) {
  console.log(value);
  this.itemId = 0;
  if(this.attachmentList.length != 0){
    for(let v=0; v<this.attachmentList.length; v++){
      if(value.name == this.attachmentList[v].name){
        this.itemId = this.attachmentList[v].itemId;
      }
    }
  }
  console.log(this.itemId);
  this._ItoUserManualService.downloadFile(this.itemId).subscribe(respon => {
  console.log(respon);
  this.docType = respon.extension;
  this.userImageH = respon.item;
  var blob = this.base64ToBlob(this.userImageH, 'text/plain');  
    saveAs(blob, value.name);  
  });
 }
//To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}
}
