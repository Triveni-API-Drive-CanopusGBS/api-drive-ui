import { Component, OnInit } from '@angular/core';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ItoPackgForwardService } from './ito-packg-forward.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';


@Component({
  selector: 'app-ito-packg-forward',
  templateUrl: './ito-packg-forward.component.html',
  styleUrls: ['./ito-packg-forward.component.css']
})
export class ItoPackgForwardComponent implements OnInit {

  typeOfCust: any;
  enableExp: boolean = false;
  enableDom: boolean = false;
  packageType: Array<any> = [];
  packageOpt: Array<any> = [];
  defaultList: Array<boolean> = [];
  enableOverwriteDiv: boolean = false;
  overWrittenCostFlag: number;
  stdCostFlag: number;
  disableStatus: boolean = false;
  remarks: string;
  packgPrice: any;
  stdCost: number;
  overWrittenCost: number;
  SeaWorthy: boolean;
  resptemp: any;
  typOfPkgCd: any;
  showForm: boolean = false;
  totalCost: number;
  totalCostDiv: boolean;
  packLocalStorage: Array<any> = [];
  packLocal: string = 'packLocal';
  scopeofsupp: string = 'scopeOfsup';
  remarksValue: string = "";
  prevRemrk: string = '';
  successMsg: string = "";
  message: boolean = false;
  submitBtn: boolean = false;
  oneLineBomPckgCost: Array<any> = [];
  overWrittenPackGCost: number = 0;
  disableBtn:boolean = false;
  backBtn: boolean = false;
  appDisable:boolean = false;
  mainSave:boolean=false;
  currentRole: string = 'selRole';
  rewApp: boolean  = false;

  constructor(private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ItoPackgForwardService: ItoPackgForwardService,
    private domSanitizer: DomSanitizer, private _ITOCostEstimationService: ITOCostEstimationService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService,private _ITOeditQoutService : ITOeditQoutService, private _ITOturbineConfigService: ITOturbineConfigService, private router: Router,
    private _ActivatedRoute: ActivatedRoute, private _ITOAddOnComponentsService: ITOAddOnComponentsService) {
      this._ITOeditQoutService.button1=false;
      this._ITOeditQoutService.button2=false;
      this._ITOeditQoutService.button3=false;
      this._ITOeditQoutService.button4=false;
      this._ITOeditQoutService.button5=false;
      this._ITOeditQoutService.button6=false;
      this._ITOeditQoutService.button7=false;
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=true;
      if(this._ITOeditQoutService.checkEdit == false){
        this.backBtn = true;
      }
      let currentRole = this.storage.get(this.currentRole);
      if(currentRole == "QUOT_REVIWER" || currentRole == "QUOT_APPROVER"){
        this.rewApp = true;
      }
   if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard"){
    this.appDisable=true;
  }else{
    this.appDisable=false;
  }
    console.log(this._ITOcustomerRequirementService.saveBasicDet);
    if(this._ITOcustomerRequirementService.saveBasicDet.custCode == "DM"){
      this._ITOcustomerRequirementService.saveBasicDet.custCodeNm = "Domestic";
    }else{
      this._ITOcustomerRequirementService.saveBasicDet.custCodeNm = "Export";
    }
    this._ItoPackgForwardService.getPackageCache().subscribe(res => {
      console.log(res);
      this.packageType = res.dropDownColumnvalues.packageList.packageList;
      this.packLocalStorage[this.packLocal] = this.storage.get(this.packLocal);
      console.log(this.packLocalStorage[this.packLocal]);
      if (this.packLocalStorage[this.packLocal] != null) {
        this.mainSave=false;
        this.typOfPkgCd = this.packLocalStorage[this.packLocal].packageTypeCode;
        this.packageOpt = [];
        this.showForm = true;
        if(this.packLocalStorage[this.packLocal].custCode == "DM"){
          this.packLocalStorage[this.packLocal].custType = "Domestic";
        }else if(this.packLocalStorage[this.packLocal].custCode == "EX"){
          this.packLocalStorage[this.packLocal].custType = "Export";
        }
        this.typeOfCust = this.packLocalStorage[this.packLocal].custType;
        if (this.typeOfCust == "Domestic") {
          for (let p = 0; p < this.packageType.length; p++) {
            if (this.packageType[p].dependentCode === "DM") {
              this.packageOpt.push(this.packageType[p]);
              if (this.packageType[p].categoryDetCode === "NP") {
                this.defaultList[p] = true;
              }
            }
          }
        }
        else if (this.typeOfCust == "Export") {
          this.enableExp = true;
          for (let p = 0; p < this.packageType.length; p++) {
            if (this.packageType[p].dependentCode === "EX") {
              this.packageOpt.push(this.packageType[p]);
              if (this.packageType[p].categoryDetCode === "CWP") {
                this.defaultList[p] = true;
              }
            }
          }
        }
        for (let p = 0; p < this.packageOpt.length; p++) {
          if (this.packageOpt[p].categoryDetId === this.packLocalStorage[this.packLocal].packageTypeId) {
            this.packageOpt[p].defaultVal = true;
            this._ITOcustomerRequirementService.saveBasicDet.packageTypeId = this.packLocalStorage[this.packLocal].packageTypeId;
          }
          if (this.packLocalStorage[this.packLocal].packageTypeCode == "NP") {
            this.SeaWorthy = false;
          }
          else if (this.packLocalStorage[this.packLocal].packageTypeCode == "CWP") {
            this.SeaWorthy = true;
          }
        }
        if (this.packLocalStorage[this.packLocal].overwrittenPriceFlag == 0) {
          this.packgPrice = this.packLocalStorage[this.packLocal].price;
          this.stdCost = this.packLocalStorage[this.packLocal].price;
          this.overWrittenPackGCost = 0;
        }
        else if (this.packLocalStorage[this.packLocal].overwrittenPriceFlag == 1) {
          //this.packgPrice = this.packLocalStorage[this.packLocal].overwrittenPrice;
          this.packgPrice = this.packLocalStorage[this.packLocal].price;
          this.stdCost = this.packLocalStorage[this.packLocal].price;
          this.overWrittenPackGCost = this.packLocalStorage[this.packLocal].overwrittenPrice;
          // this.enableOverwriteDiv = true;

          this.disableStatus = true;
          this.remarksValue = this.packLocalStorage[this.packLocal].remarks;
          this.remarks = this.packLocalStorage[this.packLocal].remarks;
        }
        this._ItoPackgForwardService.getPackageData(this._ITOcustomerRequirementService.saveBasicDet).subscribe(resp => {
          console.log(resp);
          this.resptemp = resp;
          this.submitBtn = true;
        })
        this.prevRemrk = this.packLocalStorage[this.packLocal].remarks;

      } else {
        this.typeOfCust = this._ITOcustomerRequirementService.saveBasicDet.custCodeNm;

        if (this.typeOfCust == "Domestic") {
          for (let p = 0; p < this.packageType.length; p++) {
            if (this.packageType[p].dependentCode === "DM") {
              this.packageOpt.push(this.packageType[p]);
              if (this.packageType[p].categoryDetCode === "NP") {
                this.defaultList[p] = true;
              }
            }
          }
        }
        else if (this.typeOfCust == "Export") {
          this.enableExp = true;
          for (let p = 0; p < this.packageType.length; p++) {
            if (this.packageType[p].dependentCode === "EX") {
              if (this.packageType[p].categoryDetCode === "CWP") {
                this.defaultList[p] = true;
              }
              this.packageOpt.push(this.packageType[p]);
            }
          }
        }
        console.log(this.packageOpt);
      }
    })
  }

  ngOnInit() {

  }

  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.packLocalStorage[key] = this.storage.get(key);
  }
  typeOfPckg(typeOfPck) {
    this.mainSave=true;

    this.totalCostDiv = false;
    console.log(typeOfPck);
    this.showForm = true;
    this.remarks = '';
    if (typeOfPck == "NP") {
      this.SeaWorthy = false;
      this.overWrittenPackGCost = 0;
    }
    else if (typeOfPck == "CWP") {
      this.SeaWorthy = true;
    }
    for (let p = 0; p < this.packageOpt.length; p++) {
      if (this.packageOpt[p].categoryDetCode === typeOfPck) {
        this._ITOcustomerRequirementService.saveBasicDet.packageTypeId = this.packageOpt[p].categoryDetId;
        this._ITOcustomerRequirementService.saveBasicDet.packageTypeCode = this.packageOpt[p].categoryDetCode;
      }
    }
    this.message=false;
    console.log(this._ITOcustomerRequirementService.saveBasicDet);
    this._ItoPackgForwardService.getPackageData(this._ITOcustomerRequirementService.saveBasicDet).subscribe(resp => {
      console.log(resp);
      this.resptemp = resp;
      this.packgPrice = resp.packageBean.price;
      this.stdCost = resp.packageBean.price;
      // this.overWrittenPackGCost = resp.packageBean.price;
      this.submitBtn = true;
      this.stdCostFlag = 1;
      this.overWrittenCostFlag = 0;
    })
  }
  checkRemarks(){
    this.mainSave=true;

    console.log(this.remarksValue)
    console.log(this.remarksValue.trim())
      if (this.remarksValue.trim() == null ||this.remarksValue.trim() == "") {
        this.disableBtn=true;
      } else {
        this.disableBtn=false;
      }
  }


  enableOverWrite() {
    this.mainSave=true;

    console.log(this.packgPrice);
    this.enableOverwriteDiv = true;
    this.disableStatus = true;
    this.submitBtn = false;
    this.disableBtn=true;
  }
  disableOverWrite() {
    this.mainSave=true;

    this.enableOverwriteDiv = false;
    this.disableStatus = false;
    this.overWrittenCostFlag = 0;
    this.stdCostFlag = 1;
    this.overWrittenCost = 0;
    this.submitBtn = true;
    this.remarksValue = null;
    this.disableBtn=false;
     this.overWrittenPackGCost=0;
  }
  remarksForm(remarks) {
    this.mainSave=true;

    console.log(remarks);
    this.remarks = remarks.overWrite;
    this.overWrittenCostFlag = 1;
    this.stdCostFlag = 0;
    this.overWrittenCost = this.packgPrice;
    let butn = document.getElementsByName("btnBP")[0].style.color = "green";
  }
  checkRemark(remrk) {
    if (remrk != null) {
      this.submitBtn = true;
    }
    else {
      this.submitBtn = false;
    }
  }

  savePackageCost(packageCost) {
    this.mainSave=false;

    console.log(packageCost);
    this.resptemp.packageBean.custType = this._ITOcustomerRequirementService.saveBasicDet.custCodeNm;
    this.resptemp.packageBean.price = this.stdCost;
    this.resptemp.packageBean.overwrittenPrice = this.overWrittenPackGCost;
    this.resptemp.packageBean.packageTypeId = this._ITOcustomerRequirementService.saveBasicDet.packageTypeId;
    this.resptemp.packageBean.packageTypeCode = this._ITOcustomerRequirementService.saveBasicDet.packageTypeCode;
    if (this.overWrittenPackGCost > 0) {
      this.resptemp.packageBean.overwrittenPriceFlag = 1;
    }
    if (this.enableOverwriteDiv) {
      this.overWrittenPackGCost = packageCost.newCost;
      this.resptemp.packageBean.overwrittenPrice = packageCost.newCost;
      this.resptemp.packageBean.overwrittenPriceFlag = 1;
      this.remarks = packageCost.overWrite;
      this.resptemp.packageBean.remarks = packageCost.overWrite;
    }
    if(!this.enableOverwriteDiv){
      this.resptemp.packageBean.overwrittenPriceFlag = 0;
    }
    if (this.resptemp.packageBean.overwrittenPriceFlag == 1) {
      this.totalCost = this.resptemp.packageBean.overwrittenPrice;
    }
    else {
      this.totalCost = this.resptemp.packageBean.price;
    }

    this._ItoPackgForwardService.savePackageData(this.resptemp).subscribe(packRes => {
      console.log(packRes);
      this.totalCostDiv = true;
      this.saveInLocal(this.packLocal, packRes.packageBean);
      let sos = this.storage.get(this.scopeofsupp);
      for (let s = 0; s < sos.length; s++) {
        if (sos[s].scopeCode == 'PF') {
          this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
          this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
          this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
            console.log(res);
          })
        }
      }

      //Calling saveRemarks to save overwriten cost and comments
   this._ITOcustomerRequirementService.saveBasicDet.groupCode = 'OVERWRITE';
   this._ITOcustomerRequirementService.saveBasicDet.scopeCode = 'PF';
   this._ITOcustomerRequirementService.saveBasicDet.overwrittenCost = packageCost.newCost;
   this._ITOcustomerRequirementService.saveBasicDet.remarks = this.remarksValue;   
   console.log(this._ITOcustomerRequirementService.saveBasicDet);
   this._ITOAddOnComponentsService.saveRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(saveRem => {
     console.log(saveRem);
   })

      this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resOnline => {
        console.log(resOnline);
        this._ITOturbineConfigService.sendMessage(resOnline.oneLineBomExcel);
      })
      if (packRes.successCode == 0 && packRes.successMsg != null) {
        console.log(this._ITOcustomerRequirementService.editFlag)
        if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false) {
          this._ITOcustomerRequirementService.editFlag = false;
          this.router.navigate(['/EditQuot']);
        } else {
          //this.router.navigate(['CostEstimation/ScopeOfSupply']);
        }
      } else {
        this.message = true;
        this.successMsg = packRes.successMsg;
        this._ITOcustomerRequirementService.sendcomBtnStatus(true);
      }
    })
  }

  getPrevComments() {
    this.message = false;
    this.successMsg = '';
    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "PKG";
    this._ITOcustomerRequirementService.saveBasicDet.groupCode = "OVERWRITE";
    this._ITOAddOnComponentsService.getQuotRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(prevComRes => {
      console.log(prevComRes);
      if (prevComRes.remarksList.length > 0) {
        this._ITOAddOnComponentsService.dispPrevComments = true;
        this._ITOAddOnComponentsService.oldComms = prevComRes.remarksList;
      } else {
        this.message = true;
        this.successMsg = "No Previous Comments found";
      }
    });
  }
  //To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}
}
