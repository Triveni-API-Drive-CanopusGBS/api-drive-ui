import { Component, OnInit } from '@angular/core';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOHomePageService } from '../ito-home-page/ito-home-page.service';
import { ItoQuotCompleteService } from './ito-quot-complete.service';
import { ITOLoginService } from '../app.component.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';

@Component({
  selector: 'app-ito-quot-complete',
  templateUrl: './ito-quot-complete.component.html',
  styleUrls: ['./ito-quot-complete.component.css']
})
export class ItoQuotCompleteComponent implements OnInit {

  plusBool:boolean=true;
  quotNum: string = '';
  labelName: string = '';
  successMsg: string = '';
  succesMsg: string = '';
  projectCostLocal: string = 'projectCostLocal';
  scopeofsupp: string = 'scopeOfsup';
  currentRole: string = 'selRole';
  currentRoleId: string = 'selRoleId';

  qotEdit: boolean = false;
  quotApp: boolean = false;
  quotRev: boolean = false;
  projSuc: boolean = false;
  enableComBtn: boolean = true;
  enableOverwriteDiv: boolean = false;
  enableWorkFlow: boolean = false;
  dispCompleteDialog: boolean = false;
  expandSup: boolean = false;
  message: boolean = false;

  usersList: Array<any> = [];
  newUsersLilst: Array<any> = [];
  projectCostLocalStorage: Array<any> = [];
  scopeOfSup: Array<any> = [];
  remarksVal: string = '';
  saveBasic: any;
  reponseTemp: any;
  otherCostsBean: any;
  disableBtn: boolean = false;
  projectCostResp: any;

  projectCost: number;
  totalProjectCost: number;
  percentProfit: number;
  TotOrderCostNetProfit: number;
  TurbineOrderBookCost: number;
  supplyPrice: number;
  servicesPrice: number;
  transportPrice: number;
  supplyCost: number;
  ServicesCost: number;
  transportCost: number;
  totalF2FCost: number;
  pkgCost: number;
  dboMechCost: number;
  dboEleCost: number;
  dboCiCost:number;
  addOnCost: number;
  variableCost: number;
  overWrittenCost: number = 0;
  backBtn: boolean = false;
  mainSave:boolean=false;
  rewApp: boolean  = false;

  constructor(private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOHomePageService: ITOHomePageService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ItoQuotCompleteService: ItoQuotCompleteService,
    private _ITOLoginService: ITOLoginService, private router: Router, private route: ActivatedRoute, private _ITOAddOnComponentsService: ITOAddOnComponentsService,
    private _ITOeditQoutService: ITOeditQoutService, private _ITOturbineConfigService: ITOturbineConfigService,
    private _ITOCostEstimationService: ITOCostEstimationService) {
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
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=true;
      let currentRole = this.storage.get(this.currentRole);
      if(currentRole == "QUOT_REVIWER" || currentRole == "QUOT_APPROVER"){
        this.rewApp = true;
      }
    this.projSuc = false;
    this.quotNum = this._ITOcustomerRequirementService.saveBasicDet.quotNumber;
    this.saveBasic = this._ITOcustomerRequirementService.saveBasicDet;
    this.saveBasic.statusFlag = false;
    this.saveBasic.submitFlag = false;
    this.saveBasic.editFlag = false;

    console.log(this.saveBasic);

    // this._ItoQuotCompleteService.getScopeOfSupStatus(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(scopeRes => {
    //   console.log(scopeRes);
    //   let scoppeOfSupList = '';
    //   let rem = scopeRes.scopeOfSupplyStatusList.filter((x) => {
    //     return x.status != "Completed";
    //   })
    //   console.log(rem)
    //   for (let g = 0; g < rem.length; g++) {
    //     if (rem[g].scopeName != "Project Cost") {
    //       scoppeOfSupList = rem[g].scopeName + ',' + scoppeOfSupList;
    //     } else if (rem[g].scopeName == "Project Cost") {
    //       rem.splice(g, 1)
    //     }
    //   }
    //     console.log(scoppeOfSupList);
    //     if (rem.length == 0) {
    //       this.getProjectCostData();
    //     }
    //     else {
    //       this._ITOLoginService.openSuccMsg("Please Save Costs for " + scoppeOfSupList + " to update Project Cost.");
    //       this.router.navigate(['MyQuot']);
    //     }

    // })
    this.getProjectCostData();
  }

  ngOnInit() {

  }

  getProjectCostData() {
    this.successMsg = '';
    this._ItoQuotCompleteService.getQuotFormData().subscribe(quotForm => {
      quotForm.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
      quotForm.otherCostsBean.inpTurbineOrderBookCost = 0;
      this._ItoQuotCompleteService.getProjectCost(quotForm).subscribe(quotStatus => {
        console.log(quotStatus);
        this.projectCostResp = quotStatus;
        if (quotStatus.successCode == 0) {
          this.projSuc = true;
          this.totalProjectCost = quotStatus.otherCostsBean.totalProjectCost;
          this.percentProfit = quotStatus.otherCostsBean.percentProfit;
          this.TotOrderCostNetProfit = quotStatus.otherCostsBean.totOrderCostNetProfit;
          this.TurbineOrderBookCost = quotStatus.otherCostsBean.turbineOrderBookCost;
          this.supplyPrice = quotStatus.otherCostsBean.projSupply;
          this.servicesPrice = quotStatus.otherCostsBean.projServices;
          this.transportPrice = quotStatus.otherCostsBean.projTransport;
          this.supplyCost = quotStatus.otherCostsBean.supplyCost;
          this.ServicesCost = quotStatus.otherCostsBean.serviceCost;
          this.transportCost = quotStatus.otherCostsBean.transCost;
          this.totalF2FCost = quotStatus.otherCostsBean.totalFtfCost;
          this.pkgCost = quotStatus.otherCostsBean.packageCost;
          this.dboMechCost = quotStatus.otherCostsBean.dboMechCost;
          this.dboEleCost = quotStatus.otherCostsBean.dboEleCost;
          this.dboCiCost= quotStatus.otherCostsBean.dboCiCost;
          this.addOnCost = quotStatus.otherCostsBean.addOnCost;
          this.variableCost = quotStatus.otherCostsBean.totalVariableCost;

          this.projectCostLocalStorage[this.projectCostLocal] = this.storage.get(this.projectCostLocal);
          console.log(this.projectCostLocalStorage[this.projectCostLocal]);
          this.otherCostsBean = this.projectCostLocalStorage[this.projectCostLocal];
          if (this.otherCostsBean.projectNewFlag) {
                this.overWrittenCost = this.otherCostsBean.projectNewCost;
              }
              console.log(this.overWrittenCost);
          // commenting this as data should be refreshed from DB  everytime
          
          // if (this.projectCostLocalStorage[this.projectCostLocal]) {

          //   this.otherCostsBean = this.projectCostLocalStorage[this.projectCostLocal];
          //   this.projectCostResp.otherCostsBean = this.otherCostsBean;
          //   this.projSuc = true;
          //   this.totalProjectCost = this.otherCostsBean.totalProjectCost;
          //   this.percentProfit = this.otherCostsBean.percentProfit;
          //   this.TotOrderCostNetProfit = this.otherCostsBean.totOrderCostNetProfit;
          //   this.TurbineOrderBookCost = this.otherCostsBean.turbineOrderBookCost;
          //   this.supplyPrice = this.otherCostsBean.projSupply;
          //   this.servicesPrice = this.otherCostsBean.projServices;
          //   this.transportPrice = this.otherCostsBean.projTransport;
          //   this.supplyCost = quotStatus.otherCostsBean.supplyCost;
          //   this.ServicesCost = quotStatus.otherCostsBean.serviceCost;
          //   this.transportCost = quotStatus.otherCostsBean.transCost;
          //   this.totalF2FCost = quotStatus.otherCostsBean.totalFtfCost;
          //   this.pkgCost = quotStatus.otherCostsBean.packageCost;
          //   this.dboMechCost = quotStatus.otherCostsBean.dboMechCost;
          //   this.dboEleCost = quotStatus.otherCostsBean.dboEleCost;
          //   this.addOnCost = quotStatus.otherCostsBean.addOnCost;
          //   this.variableCost = quotStatus.otherCostsBean.totalVariableCost;
          //   if (this.otherCostsBean.projectNewFlag) {
          //     this.overWrittenCost = this.otherCostsBean.projectNewCost;
          //   }
          // }
        } else {
          //this._ITOLoginService.openSuccMsg(quotStatus.successMsg);
          this.message = true;
          this.successMsg = quotStatus.successMsg;
        }
      })
    });
  }
  enableOverwrite() {
    this.mainSave=true;
    this.enableOverwriteDiv = true;
    this.enableComBtn = false;
    this.disableBtn = true;
  }
  disableOverWrite() {
    this.mainSave=true;
    this.enableOverwriteDiv = false;
    this.enableComBtn = true;
    this.overWrittenCost = 0;
    this.disableBtn = false;
  }

  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.projectCostLocalStorage[key] = this.storage.get(key);
  }
  checkRemarks() {
    this.mainSave=true;

    console.log(this.remarksVal)
    console.log(this.remarksVal.trim())
    if (this.remarksVal.trim() == null || this.remarksVal.trim() == "") {
      this.disableBtn = true;
    } else {
      this.disableBtn = false;
    }
  }

  CalCost(form) {
    this.mainSave=true;
    this.succesMsg = '';
    this.projectCostResp.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
    this.projectCostResp.otherCostsBean.inpTurbineOrderBookCost = form.updatedCost;
    this._ItoQuotCompleteService.getProjectCost(this.projectCostResp).subscribe(quotStatus => {
      console.log(quotStatus);
      this.projectCostResp = quotStatus;
      if (quotStatus.successCode == 0) {
        this.enableComBtn = true;
        this.projSuc = true;
        this.totalProjectCost = quotStatus.otherCostsBean.totalProjectCost;
        this.percentProfit = quotStatus.otherCostsBean.percentProfit;
        this.TotOrderCostNetProfit = quotStatus.otherCostsBean.totOrderCostNetProfit;
        this.supplyPrice = quotStatus.otherCostsBean.projSupply;
        this.servicesPrice = quotStatus.otherCostsBean.projServices;
        this.transportPrice = quotStatus.otherCostsBean.projTransport;
        this.supplyCost = quotStatus.otherCostsBean.supplyCost;
        this.ServicesCost = quotStatus.otherCostsBean.serviceCost;
        this.transportCost = quotStatus.otherCostsBean.transCost;
        this.totalF2FCost = quotStatus.otherCostsBean.totalFtfCost;
        this.pkgCost = quotStatus.otherCostsBean.packageCost;
        this.dboMechCost = quotStatus.otherCostsBean.dboMechCost;
        this.dboEleCost = quotStatus.otherCostsBean.dboEleCost;
        this.dboCiCost= quotStatus.otherCostsBean.dboCiCost;
        this.addOnCost = quotStatus.otherCostsBean.addOnCost;
        this.variableCost = quotStatus.otherCostsBean.totalVariableCost;
        this._ITOcustomerRequirementService.sendcomBtnStatus(true);
        //this.TurbineOrderBookCost = quotStatus.otherCostsBean.turbineOrderBookCost;
      } else {
        this._ITOLoginService.openSuccMsg(quotStatus.successMsg);
      }
    })
  }

  saveProjectCost(form) {
    this.mainSave=false;

    console.log(form)
    console.log(this.projectCostResp)
    if (this.enableOverwriteDiv) {
      this.projectCostResp.otherCostsBean.projectNewFlag = true;
      this.projectCostResp.otherCostsBean.projectNewCost = form.updatedCost;
      this.projectCostResp.otherCostsBean.projectRemarks = form.remarks;
    } else {
      this.projectCostResp.otherCostsBean.projectNewFlag = false;
      this.projectCostResp.otherCostsBean.sparesNewCost = 0;
    }
    if (this.overWrittenCost > 0) {
      this.projectCostResp.otherCostsBean.projectNewFlag = true;
      this.projectCostResp.otherCostsBean.sparesNewCost = this.overWrittenCost;
    }
    this.projectCostResp.otherCostsBean.turbineOrderBookCost = this.TurbineOrderBookCost;
    this.saveInLocal(this.projectCostLocal, this.projectCostResp.otherCostsBean); //testing
    console.log(this.projectCostLocalStorage[this.projectCostLocal]);
    if (this.storage.get(this.currentRole) === "QUOT_EDIT") {

      this._ItoQuotCompleteService.saveProjectCost(this.projectCostResp).subscribe(finalRes => {
        console.log(finalRes);

        if (finalRes.successCode == 0) {
          this.message = true;
          this.successMsg = "Cost Saved successfully";
          let sos = this.storage.get(this.scopeofsupp);
          for (let s = 0; s < sos.length; s++) {
            if (sos[s].scopeCode == 'PR') {
              this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
              this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
              this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
                console.log(res);
                if (res.successCode == 0) {
                  this.enableWorkFlow = true;
                }
              })
            }
          }

                     //Calling saveRemarks to save overwriten cost and comments
   this._ITOcustomerRequirementService.saveBasicDet.groupCode = 'OVERWRITE';
   this._ITOcustomerRequirementService.saveBasicDet.scopeCode = 'PR';
   this._ITOcustomerRequirementService.saveBasicDet.overwrittenCost = this.overWrittenCost;
   this._ITOcustomerRequirementService.saveBasicDet.remarks = this.remarksVal;   
   console.log(this._ITOcustomerRequirementService.saveBasicDet);
   this._ITOAddOnComponentsService.saveRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(saveRem => {
     console.log(saveRem);
   })
          this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resAdd => {
            console.log(resAdd);
            this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);
          });

        } else {
          this._ITOLoginService.openSuccMsg(finalRes.successMsg);
        }
      })
      //   } else {
      //     this._ITOLoginService.openSuccMsg(resp.successMsg);
      //   }
      // });
    } else {
      this._ItoQuotCompleteService.saveProjectCost(this.projectCostResp).subscribe(finalRes => {
        console.log(finalRes);

        if (finalRes.successCode == 0) {
          this.message = true;
          this.successMsg = "Cost Saved successfully";
          let sos = this.storage.get(this.scopeofsupp);
          for (let s = 0; s < sos.length; s++) {
            if (sos[s].scopeCode == 'PR') {
              this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
              this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
              this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
                console.log(res);
                if (res.successCode == 0) {
                  this.enableWorkFlow = true;
                }
              })
            }
          }
      //Calling saveRemarks to save overwriten cost and comments
      this._ITOcustomerRequirementService.saveBasicDet.groupCode = 'OVERWRITE';
      this._ITOcustomerRequirementService.saveBasicDet.scopeCode = 'PR';
      this._ITOcustomerRequirementService.saveBasicDet.overwrittenCost = this.overWrittenCost;
      this._ITOcustomerRequirementService.saveBasicDet.remarks = this.remarksVal;   
      console.log(this._ITOcustomerRequirementService.saveBasicDet);
      this._ITOAddOnComponentsService.saveRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(saveRem => {
        console.log(saveRem);
      })
          
          if (this._ITOcustomerRequirementService.editFlag) {
            this._ITOcustomerRequirementService.editFlag = false;
            // this.router.navigate(['/EditQuot']);
          } else {
            //this.router.navigate(['MyQuot']);
          }

        } else {
          this._ITOLoginService.openSuccMsg(finalRes.successMsg);
        }
      })
    }

  }
  proceedComplete() {
    this.dispCompleteDialog = true;
  }
  closeDialog() {
    this.dispCompleteDialog = false;
  }

  quotWorkFlow() {
    this.dispCompleteDialog = false;
    this._ItoQuotCompleteService.getScopeOfSupStatus(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(finscopeRes => {
      console.log(finscopeRes);
      let scoppeOfSupList = '';
      let rem = finscopeRes.scopeOfSupplyStatusList.filter((x) => {
        return x.status != "Completed";
      })
      console.log(rem)
      for (let g = 0; g < rem.length; g++) {
        scoppeOfSupList = rem[g].scopeName + ',' + scoppeOfSupList;
        console.log(scoppeOfSupList);
      }
      if (rem.length == 0) {
        this.saveBasic.quotStatusFlg = 1;
        this.saveBasic.userRoleId = this.storage.get(this.currentRoleId);
        this._ItoQuotCompleteService.quotStatusComplete(this.saveBasic).subscribe(quotresp => {
          console.log(quotresp)
          if (quotresp.successCode == 0) {
            this._ITOLoginService.openSuccMsg(quotresp.successMsg);
            this.router.navigate(['MyQuot']);
          } else {
            this._ITOLoginService.openSuccMsg(quotresp.successMsg);
          }
        })
      }
      else {
        this._ITOLoginService.openSuccMsg("Please Save Costs for " + scoppeOfSupList + " to proceed Quotation WorkFlow.");
      }
    })
  }


  assignedUser(assigne) {
    console.log(assigne);
    this.saveBasic.assignedTo = assigne;
  }

  getPrevComments() {

    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "PR";
    this._ITOcustomerRequirementService.saveBasicDet.groupCode = "OVERWRITE";
    this._ITOAddOnComponentsService.getQuotRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(prevComRes => {
      console.log(prevComRes);
      if (prevComRes.remarksList.length > 0) {
        this._ITOAddOnComponentsService.dispPrevComments = true;
        this._ITOAddOnComponentsService.oldComms = prevComRes.remarksList;
      } else {
        this._ITOLoginService.openSuccMsg("No Previous Comments found");
      }
    });
  }

  expandSupply(){
    this.expandSup = !this.expandSup;
    this.plusBool=!this.plusBool;
  }
  //To navigate edit quotaion page on click of back button
  backButton(){
    this.router.navigate(['/EditQuot']);
  }
}
