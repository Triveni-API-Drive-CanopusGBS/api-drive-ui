import { Component, OnInit } from '@angular/core';
import { ItoPerformanceService } from '../ito-performance/ito-performance.service';
import { MatTableModule } from '@angular/material';
import { ITOLoginService } from '../app.component.service';
import { ITOCostEstimationService } from '../ito-cost-estimation/ito-cost-estimaton.service';
import { dboClass } from '../ito-performance/ito-performance';
import { NgForm } from '@angular/forms';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ITOeditQoutService } from '../ito-edit-quot/ito-edit-quot.service';
@Component({
  selector: 'app-exclusion-list',
  templateUrl: './exclusion-list.component.html',
  styleUrls: ['./exclusion-list.component.css']
})
export class ExclusionListComponent implements OnInit {
  dboFormDataaa: any; //To store dbo form response
  quotId:number=0;
  exclusion: string = 'exclusion'; // local storage value
  exclusionLocal: Array<any> = [];
  exclusionlocal: Array<any> = [];
  display: Array<any> = [];
  dboClass: dboClass;
  othetList: Array<any> = [];
  symbol:string="*";
  displayCompOthTable1:Array<boolean> = []; //open others attribute to add new LHS and RHS

  //others
  displayOthnewLine1:Array<boolean> = [];//open others attribute to enter new LHS and RHS values
  itemOthersList1: Array<any> = [];

  tempArray: Array<any> = [];
  arraycheck: Array<any> = [];
  itemRemarkDiv:boolean=false;
  backBtn: boolean = false;
  dialogMsgApp: boolean = false;
 dialogMsgVal: string = '';
 disableData:boolean=false;
 disableexclusion:Array<boolean>=[];
 getPrice:boolean=true;
 scopeofsupp: string = 'scopeOfsup'; 
 excludedItems:boolean=true;
mainSave:boolean=true;
  constructor(private _ItoPerformanceService: ItoPerformanceService,private _ITOLoginService: ITOLoginService, private _ITOeditQoutService: ITOeditQoutService,  private _ITOcustomerRequirementService: ITOcustomerRequirementService,
    private _ITOCostEstimationService: ITOCostEstimationService,
    private domSanitizer: DomSanitizer, @Inject(LOCAL_STORAGE) private storage: WebStorageService,private router: Router,) {
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
      console.log( this.arraycheck);
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

    this.exclusionLocal[this.exclusion] = this.storage.get(this.exclusion);
    if(this._ITOcustomerRequirementService.editFlag==true && this._ITOeditQoutService.exclusionLi!=undefined)
{
  this.tempArray=this._ITOeditQoutService.exclusionLi;
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
      this.dboClass.quant=null;
      this.dboClass.cost=null;
      this.dboClass.editFlag=1;
      this.dboClass.newColValFlag=1;
      this.dboClass.remarks=null;
      this.itemOthersList1.push(this.dboClass);
    }
  }
}
    if (this.storage.get(this.exclusion) == null) {
      this.saveInLocal(this.exclusion, {
        othetList:this.othetList,itemOthersList1:this.itemOthersList1,arraycheck:this.arraycheck
      });
    }
    if(this.storage.get(this.exclusion).othetList.length!=0){
      this.tempArray= this.storage.get(this.exclusion).othetList;
      this.display =   this.tempArray .reduce((acc, current) => {
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
   if(this.storage.get(this.exclusion).itemOthersList1.length!=0){
    this.itemOthersList1= this.storage.get(this.exclusion).itemOthersList1;
  
 }


    
        console.log(this.storage.get(this.exclusion));
        this._ItoPerformanceService.getOtherChapter(this.dboFormDataaa).subscribe(res => {
          console.log(res);
          console.log(res.otherDataList);
         // this.othetList = res.otherDataList;
         for(let x=0;x<res.otherDataList.length;x++)
         {
          if(res.otherDataList[x].scopeCd=="EL")
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
                if(this.tempArray[y].newColValFlag==0 && this.tempArray[y].itemId!=0)
{


                for(let x=0;x<this.othetList.length;x++)
                {
if(this.tempArray[y].seqNo==this.othetList[x].seqNo && this.tempArray[y].itemId==this.othetList[x].itemId && this.othetList[x].editFlag!=2  )
{
  this.othetList[x]=this.tempArray[y];
  break;
}
                }
              }

              }
             
            }
          }
           this.display =   this.othetList .reduce((acc, current) => {
      console.log(acc, current);
      const x = acc.find(item => item.itemId === current.itemId);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      } 
    }, []);
        
    for(let i=0;i<=100;i++)
    {
      this.arraycheck.push([0]);
    }
    for(let x=0;x<this.display.length;x++)
    {
    for(let j=0;j<100;j++)
    {
      this.arraycheck[this.display[x].itemId][j]=false;
    }

  }
  let bolleancheck=false;
  for(let x=0;x<this.tempArray.length;x++)
    {
      if(this.tempArray[x].itemId!=0 && this.tempArray[x].newColValFlag==0  )
      {
        bolleancheck=true;
        break;
      }
    }
  if (!bolleancheck)
  {
    for(let x=0;x<this.othetList.length;x++)
    {
      if(this.othetList[x].itemId!=0 && this.othetList[x].newColValFlag==0  )
      {
      this.arraycheck[this.othetList[x].itemId][this.othetList[x].seqNo]=true;
      }
    }  }
  else
  {
    for(let x=0;x<this.tempArray.length;x++)
    {
      if(this.tempArray[x].itemId!=0 && this.tempArray[x].newColValFlag==0 &&  this.tempArray[x].editFlag!=2  )
      {
      this.arraycheck[this.tempArray[x].itemId][this.tempArray[x].seqNo]=true;
      }
    }
  }
  if( this.storage.get(this.exclusion).arraycheck.length!=0)
  {
  this.arraycheck= this.storage.get(this.exclusion).arraycheck;
  }
        });

    });

  }
  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.exclusionLocal[key] = this.storage.get(key);
  }
  saveOthers()
  {
   this.getPrice=true;
    if(this.disableexclusion.includes(true) )
    {
      this.getPrice=false;
    }
    if(this.getPrice )
    {
      this._ITOeditQoutService.exclusionLi = [];
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

    console.log(this.othetList);
    let count=this.othetList.length;
    let countexisttable=0;
    for(let j=0;j<this.othetList.length;j++)
    {
      if(this.arraycheck[this.othetList[j].itemId][this.othetList[j].seqNo]==true)
      {
        countexisttable=countexisttable+1;
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

    }
    if(this.itemOthersList1.length!=0)
    {
      for(let j=0;j<this.itemOthersList1.length;j++)
      {
        this.itemOthersList1[j].seqNo=count+j;
        this.dboFormDataaa.otherChapterData.push(this.itemOthersList1[j]);
      }
    }
    console.log( this.dboFormDataaa.otherChapterData);
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.ssId= this.dboFormDataaa.otherChapterData[0].ssId;
    this.dboFormDataaa.modifiedById =  this._ITOLoginService.loggedUserDetails;
   // this.dboFormDataaa.otherChapterData=this.othetList;

    this._ItoPerformanceService.saveOtherChapter(this.dboFormDataaa).subscribe(res => {
      console.log(res);
      console.log(res.otherDataList);
      if (res.successCode == 0) {
        this.mainSave=false;
        this.dialogMsgApp = true;
        this.dialogMsgVal= "Saved Successfully";
        this._ITOcustomerRequirementService.sendtecBtnStatus(true);

        let sos = this.storage.get(this.scopeofsupp);
        for (let s = 0; s < sos.length; s++) {
          if (sos[s].scopeCode == 'EL') {
            this._ITOcustomerRequirementService.saveBasicDet.scopeId = sos[s].ssId;
            this._ITOcustomerRequirementService.saveBasicDet.scopeStatusFlg = 1;
            this._ITOCostEstimationService.scopeOfSupplyStatus(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
              console.log(res);
            })
          }
        }

      } else {
        this.dialogMsgApp = true;
        this.dialogMsgVal = res.successMsg; 
      }
    
    
        this.saveInLocal(this.exclusion, {
          othetList:this.othetList,itemOthersList1:this.itemOthersList1,arraycheck:this.arraycheck
        });
        if (this._ITOcustomerRequirementService.editFlag && this._ITOeditQoutService.checkEdit==false ) {
          this._ITOcustomerRequirementService.editFlag = false;
          this.router.navigate(['/EditQuot']);
        }
    });
  }
  }
  ngOnInit() {
  }
  itemRmrkCheck(event,id,no)
  {
    this.mainSave=true;

    if(event.target.checked){
     this.arraycheck[id][no]=true;
    }else if(!event.target.checked){
      this.arraycheck[id][no]=false;
    
    }
  }
  compOthersForm1(others, othersfrm: NgForm,id,name) {
    this.mainSave=true;

    this.dboClass=new dboClass();
      this.dboClass.seqNo=0;
      this.dboClass.ssId=id.ssId;
      this.dboClass.itemId=id.itemId;
      this.dboClass.subItemId=id.subItemId;
      this.dboClass.information= null;
      this.dboClass.finalts=null;
      this.dboClass.subItemCd=id.subItemCd;
      this.dboClass.description= others.othdes;
      this.dboClass.equipment=null;
      this.dboClass.test=null;
      this.dboClass.equivalent=null;
      this.dboClass.panelType	=null;
      this.dboClass.custType=null;
      this.dboClass.quant=null;
      this.dboClass.cost=null;
      this.dboClass.editFlag=1;
      this.dboClass.newColValFlag=1;
      this.dboClass.remarks=null;
      this.itemOthersList1.push(this.dboClass);

    console.log(others);
    console.log(name);
   
    
  //  this.dboClass.cost =others.othcost;
   
    
   
   
    //this.tempitemOthersList.push(this.dboClass);
    console.log(this.itemOthersList1);
   
    othersfrm.reset();
    this.displayOthnewLine1[id.itemId] = false;

  
     
    
   
  }
  cancelLinesOth1(i,id) {
    this.mainSave=true;

    this.itemOthersList1.splice(i, 1);
    this.disableexclusion.splice(i,1);

  }
  cancelnewLineOth1(id) {
    this.mainSave=true;

    this.displayOthnewLine1[id] = false;

  }
  addRowsOth1(id) {
    this.mainSave=true;

    this.displayOthnewLine1[id] = true;
  }
     //To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}
editlhs(i)
{


  this.mainSave=true;


this.disableexclusion[i]=true;

}
savelhs(i)
{
  this.mainSave=true;

  this.disableexclusion[i]=false;

}
}





     