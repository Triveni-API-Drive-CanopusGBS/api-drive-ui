import { Component, OnInit } from '@angular/core';
import { ItoPerformanceService } from '../ito-performance/ito-performance.service';
import { MatTableModule } from '@angular/material';
import { ITOLoginService } from '../app.component.service';

import { dboClass } from '../ito-performance/ito-performance';
import { NgForm } from '@angular/forms';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
////spares
import { ItoVariableCostService } from '../ito-variable-cost/ito-variable-cost.service';
import { ItoSapresService } from '../ito-sapres-component/ito-sapers-component.service';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOAddOnComponentsService } from '../add-on-components/add-on-components.service';

import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
@Component({
  selector: 'app-sope-of-spares',
  templateUrl: './sope-of-spares.component.html',
  styleUrls: ['./sope-of-spares.component.css']
})
export class SopeOfSparesComponent implements OnInit {

  dboFormDataaa: any; //To store dbo form response
  quotId:number=0;
  scopeOf: string = 'scopeOf'; // local storage value
  scopeOfLocal: Array<any> = [];
  display: Array<any> = [];
  dboClass: dboClass;
  othetList: Array<any> = [];
  displayCompOthTable1:Array<boolean> = []; //open others attribute to add new LHS and RHS
  tempArray: Array<any> = [];
  othersSubTypCheck: Array<boolean> = [];
  displaycomp: Array<boolean> = [];
  othersSubItmTypChk: Array<boolean> = [];
  /////Spares
  remarksValue: string;
  sparesArray: Array<any> = [];
  sparesLocalStorage: Array<any> = [];
  SpecialsparesCst:any;
  sparesCst: any;
  tempResp: any;
  otherCostsBean: any;
  spareCostTotal: any;
  remarks: string = '';
  successMsg: string = '';
  sparesLocal: string = 'sparesLocal';
  scopeofsupp: string = 'scopeOfsup'; 
  orderBookingSpares: number;
  ovrheadTotSaleCost: number;
  sparesNetProfit: number;
  totalSparesCost: number;
  ovrheadSparesCost: number;
  overWrittenCost: number = 0;
  sparesNewCost: number;
  profitVal: number=0;

  spareSuc: boolean = false;
  message: boolean = false;
  enableOverwriteDiv: boolean = false;
  overWrittenCostFlag: boolean = false;
  cost:number=0;
  disableData:boolean=false;
  /////
  

  //others
  displayOthnewLine1:Array<boolean> = [];//open others attribute to enter new LHS and RHS values
  itemOthersList1: Array<any> = [];
  backBtn: boolean = false;
  disablespares:Array<boolean>=[];
  showCost:boolean=false;
  mainSave:boolean=true;
 
  currentRole: string = 'selRole';
  rewApp: boolean  = false;

  constructor(private _ItoPerformanceService: ItoPerformanceService,private _ItoVariableCostService: ItoVariableCostService,
    private __ItoSapresService: ItoSapresService, @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOLoginService: ITOLoginService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOturbineConfigService: ITOturbineConfigService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private router: Router,
    private _ITOCostEstimationService: ITOCostEstimationService,private _ITOeditQoutService:ITOeditQoutService ) {
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
      let currentRole = this.storage.get(this.currentRole);
      if(currentRole == "QUOT_REVIWER" || currentRole == "QUOT_APPROVER"){
        this.rewApp = true;
      }
	  
      if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard")
      {
        this.disableData=true;
      }
    this._ItoPerformanceService.getDboFormData().subscribe(res => {
      console.log(res);
      this.dboFormDataaa = res;
      //
    this.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;

    this.scopeOfLocal[this.scopeOf] = this.storage.get(this.scopeOf);
    if (this.storage.get(this.scopeOf) == null) {
      this.saveInLocal(this.scopeOf, {
        othetList:this.othetList,itemOthersList1:this.itemOthersList1
      });
    }
    if(this._ITOcustomerRequirementService.editFlag==true && this._ITOeditQoutService.scopeofspares!=undefined )
{
  this.tempArray=this._ITOeditQoutService.scopeofspares;
  this.mainSave=false;

  for(let x=0;x<this.tempArray.length;x++)
  {
    if(this.tempArray[x].newColValFlag==1)
    {
      this.dboClass=new dboClass();
      this.dboClass.seqNo=this.tempArray[x].seqNo;
      this.dboClass.ssId=this.tempArray[x].ssId;
      this.dboClass.itemId=this.tempArray[x].itemId;
      this.dboClass.subItemId=null;
      this.dboClass.information= null;
      this.dboClass.finalts=null;
      this.dboClass.subItemCd=null;
      this.dboClass.description= this.tempArray[x].description;
      this.dboClass.equipment=null;
      this.dboClass.test=null;
      this.dboClass.equivalent=null;
      this.dboClass.panelType	=null;
      this.dboClass.custType=null;
      this.dboClass.quant=this.tempArray[x].quant;
      this.dboClass.cost=this.tempArray[x].cost;
      this.dboClass.editFlag=1;
      this.dboClass.newColValFlag=1;
      this.dboClass.remarks=null;
      this.itemOthersList1.push( this.dboClass);
    }
  }
}
        console.log(this.storage.get(this.scopeOf));
        if(this.storage.get(this.scopeOf).itemOthersList1.length!=0){
          this.itemOthersList1= this.storage.get(this.scopeOf).itemOthersList1;
        
       }
       this.SpecialsparesCst=0;
       for(let x=0;x<this.itemOthersList1.length;x++)
       {
         this.SpecialsparesCst=this.SpecialsparesCst+this.itemOthersList1[x].cost;
       }
       if(this.storage.get(this.scopeOf).othetList.length!=0){
        this.othetList= this.storage.get(this.scopeOf).othetList;
        this.display =   this.othetList .reduce((acc, current) => {
          console.log(acc, current);
          const x = acc.find(item => item.itemId === current.itemId);
          if (!x) {
            return acc.concat([current]);
          } else {
            return acc;
          } 
        }, []);
      
        this.mainSave=false;

     }
   
        if(this.othetList.length==0)
        {
        this._ItoPerformanceService.getOtherChapter(this.dboFormDataaa).subscribe(res => {
          console.log(res);
          console.log(res.otherDataList);
          //this.othetList = res.otherDataList;
          for(let x=0;x<res.otherDataList.length;x++)
          {
           if(res.otherDataList[x].scopeCd=="SP")
           {
            this.othetList.push(res.otherDataList[x]);
           }
          }
          if(this.tempArray!=undefined)
          {
            if(this.tempArray.length!=0)
            {
              for(let y=0;y<this.tempArray.length;y++)
              {
                if(this.tempArray[y].newColValFlag==0 && this.tempArray[y].itemId!=0 )
{


                for(let x=0;x<this.othetList.length;x++)
                {
if(this.tempArray[y].description==this.othetList[x].description)
{
  this.othetList[x]=this.tempArray[y];
  break;
}
                }
              }

              }
              
            }
          }
           this.display = this.othetList .reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.itemId === current.itemId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
        
      
        });
      }
  
    });
    this.tempResp = this._ITOcustomerRequirementService.quotForm;
    this._ITOcustomerRequirementService.fetchCacheData().subscribe(res => {

     this.sparesArray = res.sparesQuestionsBean;
     for (let r = 0; r < this.sparesArray.length; r++) {
      if (this.sparesArray[r].dropDownType.code == "PR") {

        let dropdownlist = this.sparesArray[r].dropDownValueList;
        for (let s = 0; s < dropdownlist.length; s++) {
          let code="";
          if(this._ITOcustomerRequirementService.saveBasicDet.custType=="Domestic" || this._ITOcustomerRequirementService.saveBasicDet.custCodeNm=="Domestic")
          {
code="DM"
          }
          else
          {
            code="EX"
 
          }
          if (dropdownlist[s].dependKey == "SP" && dropdownlist[s].custCode == code) {
            if( this.profitVal==0)
            {

            
            this.profitVal = dropdownlist[s].percentage;
            }
            this.sparesArray.splice(r, 1);
          }
        }
      }

    }
     // this.profitVal=res.sparesQuestionsBean.dropDownValueList[0].percentage;

      console.log(res.sparesQuestionsBean.dropDownValueList);

    });
  }
  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.scopeOfLocal[key] = this.storage.get(key);
  }
  saveOthers()
  {
   if(!this.disablespares.includes(true))
   {
    this.showCost=false;
      this._ITOeditQoutService.scopeofspares = [];
     
    console.log(this.othetList);
    let count=this.othetList.length;
    this.dboFormDataaa.otherChapterData=[];
    this.dboClass=new dboClass();
      this.dboClass.seqNo=0;
      this.dboClass.ssId=this.othetList[0].ssId;
      this.dboClass.itemId=0;
      this.dboClass.subItemId=0;
      this.dboClass.information= null;
      this.dboClass.finalts=null;
      this.dboClass.subItemCd=null;
      this.dboClass.description= null;
      this.dboClass.equipment=null;
      this.dboClass.test=null;

      this.dboClass.equivalent=null;
      this.dboClass.panelType	=null;
      this.dboClass.custType=null;
      this.dboClass.quant=null;
      this.dboClass.cost=null;
      this.dboClass.editFlag=0;
      this.dboClass.newColValFlag=0;
      this.dboClass.remarks=null;
      this.dboFormDataaa.otherChapterData.push(this.dboClass);
    for(let j=0;j<this.othetList.length;j++)
    {

      this.dboClass=new dboClass();
      this.dboClass.seqNo=this.othetList[j].seqNo;
      this.dboClass.ssId=this.othetList[j].ssId;
      this.dboClass.itemId=this.othetList[j].itemId;
      this.dboClass.subItemId=this.othetList[j].subItemId;
      this.dboClass.information= this.othetList[j].information;
      this.dboClass.finalts=this.othetList[j].finalts;
      this.dboClass.subItemCd=this.othetList[j].subItemCd;
      this.dboClass.description= this.othetList[j].description;
      this.dboClass.equipment=this.othetList[j].equipment;
      this.dboClass.test=this.othetList[j].test;

      this.dboClass.equivalent=this.othetList[j].equivalent;
      this.dboClass.panelType	=this.othetList[j].panelType;
      this.dboClass.custType=this.othetList[j].custType;
      this.dboClass.quant=this.othetList[j].quant;
      this.dboClass.cost=this.othetList[j].cost;
      this.dboClass.editFlag=this.othetList[j].editFlag;
      this.dboClass.newColValFlag=0;
      this.dboClass.remarks=null;
    this.dboFormDataaa.otherChapterData.push(this.dboClass);
      
    }
    if(this.itemOthersList1.length!=0)
    {
      for(let j=0;j<this.itemOthersList1.length;j++)
      {
        count=count+1;
        this.itemOthersList1[j].seqNo=count;
        this.dboFormDataaa.otherChapterData.push(this.itemOthersList1[j]);
      }
    }
    console.log( this.dboFormDataaa.otherChapterData);
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.ssId= this.dboFormDataaa.otherChapterData[0].ssId;
    this.dboFormDataaa.modifiedById =  this._ITOLoginService.loggedUserDetails;


    this._ItoPerformanceService.saveOtherChapter(this.dboFormDataaa).subscribe(res => {
      this.mainSave=false;

      console.log(res);
      console.log(res.otherDataList);
      
      this.saveInLocal(this.scopeOf, {
        othetList:this.othetList,itemOthersList1:this.itemOthersList1
      });
      ////////////Spares12
      

      
    if (this.overWrittenCost > 0) {
      this.tempResp.otherCostsBean.sparesNewFlag = true;
      this.tempResp.otherCostsBean.sparesNewCost = this.overWrittenCost;
    }
    if (this.enableOverwriteDiv) {
      this.tempResp.otherCostsBean.sparesNewFlag = true;
      this.tempResp.otherCostsBean.sparesNewCost = this.sparesNewCost;
      this.tempResp.otherCostsBean.sparesRemarks = this.remarksValue;
    }
    // else {
    //   this.tempResp.otherCostsBean.sparesNewFlag = false;
    //   this.tempResp.otherCostsBean.sparesNewCost = 0;
    // }
    if(!this.spareSuc)
    {
      this.tempResp.otherCostsBean.sparesNewFlag = false;
      this.tempResp.otherCostsBean.sparesNewCost = 0;
    }
    this.__ItoSapresService.saveSparesCost(this.tempResp).subscribe(savedRes => {

      this.saveInLocal(this.sparesLocal, savedRes.otherCostsBean); //testing

      if (savedRes.successCode == 0) {
        //this._ITOLoginService.openSuccMsg("Cost Saved Successfully");
        this.message=true;
        this.successMsg = "Cost Saved successfully";
        this._ITOcustomerRequirementService.sendtecBtnStatus(true);
        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'SP') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }

                   //Calling saveRemarks to save overwriten cost and comments
   this._ITOcustomerRequirementService.saveBasicDet.groupCode = 'OVERWRITE';
   this._ITOcustomerRequirementService.saveBasicDet.scopeCode = 'SP';
   this._ITOcustomerRequirementService.saveBasicDet.overwrittenCost = this.sparesNewCost;
   this._ITOcustomerRequirementService.saveBasicDet.remarks = this.remarksValue;   
   console.log(this._ITOcustomerRequirementService.saveBasicDet);
   this._ITOAddOnComponentsService.saveRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(saveRem => {
     console.log(saveRem);
   })

          this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resAdd => {
            console.log(resAdd);
            this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);
            if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
              this._ITOcustomerRequirementService.editFlag = false;
              this.router.navigate(['/EditQuot']);
            }
          });
       
      } else {
        if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
          this._ITOcustomerRequirementService.editFlag = false;
          this.router.navigate(['/EditQuot']);
        }

        this._ITOLoginService.openSuccMsg(savedRes.successMsg);
      }
    });
  
  
 
  
    });
  }
  else
  {
this.showCost=true;
  }
  }


  compOthersForm1(others, othersfrm: NgForm,id,name) {
    this.mainSave=true;

    console.log(others);
    console.log(name);
   
    this.dboClass=new dboClass();
    this.dboClass.seqNo=others.othdes0;
    this.dboClass.ssId=id.ssId;
    this.dboClass.itemId=id.itemId;
    this.dboClass.subItemId=id.subItemId;
    this.dboClass.information= null;
    this.dboClass.finalts=null;
    this.dboClass.subItemCd=id.subItemCd;
    this.dboClass.description= others.othdes1;
    this.dboClass.equipment=null;
    this.dboClass.test=null;
    this.dboClass.equivalent=null;
    this.dboClass.panelType	=null;
    this.dboClass.custType=null;
    this.dboClass.quant=others.othdes2;
    this.dboClass.cost=others.othdes3;
    this.dboClass.editFlag=1;
    this.dboClass.newColValFlag=1;
    this.dboClass.remarks=null;
  //  this.dboClass.cost =others.othcost;
   
    
   
    this.itemOthersList1.push(this.dboClass);
    this.disablespares[this.itemOthersList1.length-1]=false;

    //this.tempitemOthersList.push(this.dboClass);
    console.log(this.itemOthersList1);
   
   
    this.displayOthnewLine1[id.itemId] = false;   
     this.SpecialsparesCst=0;
     if(!this.disablespares.includes(true))
     {
      for(let x=0;x<this.itemOthersList1.length;x++)
      {
        this.SpecialsparesCst=Number(this.SpecialsparesCst)+Number(this.itemOthersList1[x].cost);
      }
     }

    // this.spareSuc=false;
   
  }
  cancelLinesOth1(i,id) {
    this.mainSave=true;

this.disablespares.splice(i,1);
// for(let j=0;j<this.disablespares.length;j++)
//         {
// counter=counter+1;
  
// if(j!=1 && j!=0)
// {

//           if(this.disablespares[j]==true)
//           {
//             this.disablespares[j-1]=true;
        
//           }
//           else
//           {
//             this.disablespares[j-1]=false;

//           }
//         }
//         if(j==0)
//         {
//         //   if(this.disablespares[j+1]==true)
//         //   {
//         //     this.disablespares[j+1]=false;
//         //     this.disablespares[j]=true;


//         //   } 
//         //   else
//         //   {
//         //     this.disablespares[j+1]=false;
//         //     this.disablespares[j]=false;

//         //   }
//                     this.disablespares[j]=this.disablespares[0];

//          }
//       }

    this.itemOthersList1.splice(i, 1);

    this.SpecialsparesCst=0;
    if(!this.disablespares.includes(true))
    {
      for(let x=0;x<this.itemOthersList1.length;x++)
      {
        this.SpecialsparesCst=this.SpecialsparesCst+this.itemOthersList1[x].cost;
      }
    }
    //   if(this.itemOthersList1.length==0)
    //   {
      
    //   //this.profitVal=0;
    //   this.getSpareCost();
    //   }
    // }
    // this.spareSuc=false;


  }
  cancelnewLineOth1(id) {
    this.mainSave=true;

    this.displayOthnewLine1[id] = false;

  }
  addRowsOth1(id) {
    this.mainSave=true;

    this.displayOthnewLine1[id] = true;
  }
/////spares
  ngOnInit() {
    this.sparesLocalStorage[this.sparesLocal] = this.storage.get(this.sparesLocal);
    console.log(this.sparesLocalStorage[this.sparesLocal]);
    if (this.sparesLocalStorage[this.sparesLocal]) {

      this.otherCostsBean = this.sparesLocalStorage[this.sparesLocal];
      this.tempResp.otherCostsBean = this.otherCostsBean;
      this.spareSuc = true;
      this.sparesCst = Number(this.otherCostsBean.sparesCost);
      this.totalSparesCost = this.otherCostsBean.totalSparesCost;
      this.orderBookingSpares = this.otherCostsBean.orderBookingSpares;
      this.ovrheadTotSaleCost = this.otherCostsBean.ovrheadTotSaleCost;
      this.sparesNetProfit = this.otherCostsBean.sparesNetProfit;
      this.ovrheadSparesCost = this.otherCostsBean.ovrheadSparesCost;
      this.profitVal = this.otherCostsBean.inpSparesProfit;
      if (this.otherCostsBean.sparesNewFlag) {
        this.overWrittenCost = this.otherCostsBean.sparesNewCost;
        this.sparesNewCost = this.otherCostsBean.sparesNewCost;
      } else {
        this.overWrittenCost = 0;
      }
    }
  }

  saveInLocal1(key, val): void {
    this.storage.set(key, val);
    this.sparesLocalStorage[key] = this.storage.get(key);
  }

  getSpareCost() {
    this.mainSave=true;

      this.cost=this.sparesCst;
      this.tempResp.otherCostsBean.inpSparesProfit = this.profitVal;
      
      if(this.sparesCst<0 || this.sparesCst=='' || this.sparesCst==null )
      {
        this.cost=0;
      }
      if(this.profitVal<0 || this.profitVal==undefined|| this.profitVal==null )
      {
        this.tempResp.otherCostsBean.inpSparesProfit = 0;
      }
      this.successMsg = '';
      this.overWrittenCost = 0;
      this.tempResp.otherCostsBean.sparesNewFlag = false;
      this.tempResp.otherCostsBean.sparesNewCost = 0;
      console.log(this.tempResp);
      this.spareSuc = false;
      this.tempResp.otherCostsBean.toolsTackCost = Number(this.SpecialsparesCst);
      this.tempResp.otherCostsBean.sparesCost =  this.cost;

      this.__ItoSapresService.getSparesCost(this.tempResp).subscribe(spareCstRes => {
        if (spareCstRes.successCode == 0) {
          this.spareSuc = true;
          this.tempResp = spareCstRes;
          this.orderBookingSpares = spareCstRes.otherCostsBean.orderBookingSpares;
          this.ovrheadTotSaleCost = spareCstRes.otherCostsBean.ovrheadTotSaleCost;
          this.sparesNetProfit = spareCstRes.otherCostsBean.sparesNetProfit;
          this.totalSparesCost = spareCstRes.otherCostsBean.totalSparesCost;
          this.ovrheadSparesCost = spareCstRes.otherCostsBean.ovrheadSparesCost;
        } else {
          this.message = true;
          this.successMsg=spareCstRes.successMsg;
          //this._ITOLoginService.openSuccMsg(spareCstRes.successMsg);
        }
  
  
      })

    
  }
  enableOverWrite() {
    this.mainSave=true;

    this.enableOverwriteDiv = true;
    this.spareSuc = true;
  }
  disableOverWrite() {
    this.mainSave=true;

    this.enableOverwriteDiv = false;
    this.overWrittenCostFlag = false;
    this.overWrittenCost=0;
    this.sparesNewCost =  0;
    this.remarks = null;
    this.remarksValue="";
    this.tempResp.otherCostsBean.sparesNewFlag = false;
    this.tempResp.otherCostsBean.sparesNewCost = 0;
  }

  saveSparesCost(form) {

    if (this.overWrittenCost > 0) {
      this.tempResp.otherCostsBean.sparesNewFlag = true;
      this.tempResp.otherCostsBean.sparesNewCost = this.overWrittenCost;
    }
    if (this.enableOverwriteDiv) {
      this.tempResp.otherCostsBean.sparesNewFlag = true;
      this.tempResp.otherCostsBean.sparesNewCost = form.updatedCost;
      this.tempResp.otherCostsBean.sparesRemarks = form.remarks;
    }
    // else {
    //   this.tempResp.otherCostsBean.sparesNewFlag = false;
    //   this.tempResp.otherCostsBean.sparesNewCost = 0;
    // }
    this.__ItoSapresService.saveSparesCost(this.tempResp).subscribe(savedRes => {

      this.saveInLocal1(this.sparesLocal, savedRes.otherCostsBean); //testing

      if (savedRes.successCode == 0) {
        //this._ITOLoginService.openSuccMsg("Cost Saved Successfully");
        this.message=true;
        this.successMsg = "Cost Saved successfully";

        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'SP') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }
     

          this._ITOturbineConfigService.getExcelCostSheetData(this._ITOcustomerRequirementService.saveBasicDet.quotId).subscribe(resAdd => {
            console.log(resAdd);
            this._ITOturbineConfigService.sendMessage(resAdd.oneLineBomExcel);
          });
          if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
          this._ITOcustomerRequirementService.editFlag = false;
          this.router.navigate(['/EditQuot']);
        }
      } else {

        this._ITOLoginService.openSuccMsg(savedRes.successMsg);
      }
    })
  }

  getPrevComments() {

    this._ITOcustomerRequirementService.saveBasicDet.scopeCode = "SP";
    this._ITOcustomerRequirementService.saveBasicDet.groupCode = "OVERWRITE";
    this._ITOAddOnComponentsService.getQuotRemarks(this._ITOcustomerRequirementService.saveBasicDet).subscribe(prevComRes => {

      if (prevComRes.remarksList.length > 0) {
        this._ITOAddOnComponentsService.dispPrevComments = true;
        this._ITOAddOnComponentsService.oldComms = prevComRes.remarksList;
      } else {
        //this._ITOLoginService.openSuccMsg("No Previous Comments found");
	 this.message=true;
        this.successMsg = "No Previous Comments found";
      }
    });
  }
   //To navigate edit quotaion page on click of back button
 backButton(){
  this.router.navigate(['/EditQuot']);
}
editlhs(i)
{
  this.mainSave=true;

  this.disablespares[i]=true;



   this.SpecialsparesCst=0;
  // this.spareSuc=false;


}
checkSparesCost(value)
{
  this.mainSave=true;

if(value<0 || value=='' || value==null )
{
  this.spareSuc=false;
  this.getSpareCost();
}
}
savelhs(i)
{
  this.mainSave=true;

  this.disablespares[i]=false;
  let countre=0;
for(let j=0;j<this.disablespares.length;j++)
{

  if(this.disablespares[j]==true)
  {
    countre=countre+1;
  }
}
  if(countre>0)
  {
    this.SpecialsparesCst=0;
    this.spareSuc=false;  }
  else 
  {
    this.SpecialsparesCst=0;
    if(!this.disablespares.includes(true))
    {
      for(let x=0;x<this.itemOthersList1.length;x++)
      {
        this.SpecialsparesCst=Number(this.SpecialsparesCst)+Number(this.itemOthersList1[x].cost);
      }
    }
  
  }
  // this.spareSuc=false;  

}
}

  
