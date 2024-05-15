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

@Component({
  selector: 'app-commercial-sec2',
  templateUrl: './commercial-sec2.component.html',
  styleUrls: ['./commercial-sec2.component.css']
})
export class CommercialSec2Component implements OnInit {

  dboFormDataaa: any; //To store dbo form response
  quotId:number=0;
  section2: string = 'section2'; // local storage value
  section2Local: Array<any> = [];
  exclusionlocal: Array<any> = [];
  display: Array<any> = [];
  dboClass: dboClass;
  othetList: Array<any> = [];
  symbol:string="*";
  displayCompOthTable1:Array<boolean> = []; //open others attribute to add new LHS and RHS

  //others
  displayOthnewLine1:boolean= false;//open others attribute to enter new LHS and RHS values
  itemOthersList1: Array<any> = [];

  tempArray: Array<any> = [];
  arraycheck: Array<boolean> = [];
  itemRemarkDiv:boolean=false;
  backBtn: boolean = false;
  dialogMsgApp: boolean = false;
 dialogMsgVal: string = '';
 disableData:boolean=false;
 defaultvalueshieght: Array<any> = [];
 tabletext: Array<any> = [];
 terminationdata:string=null;
 definition:string=null;
 qouttype:string=null;
 checkEdit:Array<boolean> = [];
 disableexclusion:Array<boolean>=[];
 getPrice:boolean=true;


  

  constructor(private _ItoPerformanceService: ItoPerformanceService,private _ITOLoginService: ITOLoginService, private _ITOeditQoutService: ITOeditQoutService,  private _ITOcustomerRequirementService: ITOcustomerRequirementService,
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
      this._ITOeditQoutService.button8=false;
      this._ITOeditQoutService.button9=true;   
      console.log( this.arraycheck);
      this.arraycheck=[];
      if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard")
      {
        this.disableData=true;
      }
      console.log( this._ITOeditQoutService.completecomercialdata);
for(let j=0;j< this._ITOeditQoutService.completecomercialdata.length;j++)
{

  if( this._ITOeditQoutService.completecomercialdata[j].itemName=="Definitions")
  {
    this.definition= this._ITOeditQoutService.completecomercialdata[j].note;
  }
}
 if(this._ITOcustomerRequirementService.saveBasicDet.custType=="Domestic" || this._ITOcustomerRequirementService.saveBasicDet.custCodeNm=="Domestic")
{
  this.qouttype = "DOMESTIC";
}
else 
{
  this.qouttype = "OFFSHORE"; 
}
    this._ItoPerformanceService.getDboFormData().subscribe(res => {
      console.log(res);
      this.dboFormDataaa = res;
     // 
    this.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;

     this.section2Local[this.section2] = this.storage.get(this.section2);
//     if(this._ITOcustomerRequirementService.editFlag==true && this._ITOeditQoutService.exclusionLi!=undefined)
// {
//   this.tempArray=this._ITOeditQoutService.exclusionLi;
//   for(let x=0;x<this.tempArray.length;x++)
//   {
//     if(this.tempArray[x].newColValFlag==1)
//     {
//       this.dboClass=new dboClass();
//       this.dboClass.seqNo=this.tempArray[x].seqNo;
//       this.dboClass.ssId=this.tempArray[x].ssId;
//       this.dboClass.itemId=this.tempArray[x].itemId;
//       this.dboClass.subItemId=null;
//       this.dboClass.information= null;
//       this.dboClass.finalts=null;
//       this.dboClass.subItemCd=null;
//       this.dboClass.description= this.tempArray[x].description;
//       this.dboClass.equipment=null;
//       this.dboClass.test=null;
//       this.dboClass.equivalent=null;
//       this.dboClass.panelType	=null;
//       this.dboClass.custType=null;
//       this.dboClass.quant=null;
//       this.dboClass.cost=null;
//       this.dboClass.editFlag=1;
//       this.dboClass.newColValFlag=1;
//       this.dboClass.remarks=null;
//       this.itemOthersList1.push(this.dboClass);
//     }
//   }
// }
    if (this.storage.get(this.section2) == null) {
      this.saveInLocal(this.section2, {
        othetList:this.othetList,itemOthersList1:this.itemOthersList1,arraycheck:this.arraycheck,tabletext:this.tabletext,terminationdata:this.terminationdata
      });
    }
    this.othetList=[]; 
this.arraycheck=[];
    if(this.storage.get(this.section2).othetList.length!=0){
      this.othetList= this.storage.get(this.section2).othetList;
      this.display =   this.othetList .reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.itemId === current.itemId);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      this.arraycheck= this.storage.get(this.section2).arraycheck;
      this.dialogMsgApp=true;
   }

   if(this._ITOeditQoutService.section2Data.length!=0)
   {

    this.dialogMsgApp = true;

    this.itemOthersList1=[];
     for(let j=0;j<this._ITOeditQoutService.section2Data.length;j++)
     {
       if(this._ITOeditQoutService.section2Data[j].newColValFlag==1)
       {
         this.dboClass=new dboClass;
        this.dboClass.sectionCd=this._ITOeditQoutService.section2Data[j].sectionCd;
        this.dboClass.itemId=this._ITOeditQoutService.section2Data[j].itemId;
        this.dboClass.itemName=this._ITOeditQoutService.section2Data[j].itemName;
        this.dboClass.subItemId=this._ITOeditQoutService.section2Data[j].subItemId;
        this.dboClass.subItemTypeCd=this._ITOeditQoutService.section2Data[j].subItemTypeCd;
        this.dboClass.fixedText1= null;
        this.dboClass.fixedText2= null;
        this.dboClass.fixedText3= this._ITOeditQoutService.section2Data[j].fixedText3;
        this.dboClass.editFlag= 1;
        this.dboClass.newColValFlag=1;
        this.dboClass.uncheckFlag=0;

this.itemOthersList1.push(this.dboClass);
       }
       else if(this._ITOeditQoutService.section2Data[j].uncheckFlag==0)
       {
        this.arraycheck[this._ITOeditQoutService.section2Data[j].itemId]=true;
       }

     }
   }
   if(this.storage.get(this.section2).itemOthersList1.length!=0){
    this.itemOthersList1= this.storage.get(this.section2).itemOthersList1;
  
 }
 if(this.storage.get(this.section2).tabletext.length!=0){
  this.tabletext= this.storage.get(this.section2).tabletext;

}
if(this.storage.get(this.section2).othetList.length!=0){
  this.othetList= this.storage.get(this.section2).othetList;

}
if(this.storage.get(this.section2).terminationdata!=null){
  this.terminationdata= this.storage.get(this.section2).terminationdata;

}
for(let x=0;x< this.othetList.length;x++)
         {
          if( this.othetList[x].itemName!="Termination")
          {
           
            this.tabletext =  this.terminationdata.split(",");

          }
         }
         this.defaultvalueshieght=[];
  for(let jp=0;jp<this.othetList.length;jp++)
  {
    console.log(jp);
    let stringToSplit=null;
if( this.othetList[jp].itemName!="Termination")
{
stringToSplit = this.othetList[jp].fixedText3;

}
else
{
stringToSplit = this.othetList[jp].fixedText1;

}
    let x = stringToSplit.split("\n");
   let temp=x.length;

    console.log(x.length);
    console.log(x);
  
    for(let y=0;y<x.length;y++)
    {

    
    let a=x[y].length/120;
    if(a>1)
    {
      var b=Math.floor(a);
      temp=temp+b;
    }
  }

    this.defaultvalueshieght.push(temp);
  }
  console.log(this.defaultvalueshieght);

//     if(this.othetList.length==0)
//     {

    
//         console.log(this.storage.get(this.exclusion));
if(this.tabletext.length==0)
{
        this._ItoPerformanceService.getComercial(this.dboFormDataaa).subscribe(res => {
          console.log(res);
          console.log(res.dboComercialItemList);
          //this.othetList = res.dboComercialItemList;
         for(let x=0;x<res.dboComercialItemList.length;x++)
         {
          if(res.dboComercialItemList[x].sectionCd=="B" && res.dboComercialItemList[x].fixedText3!=null )
          {
           this.othetList.push(res.dboComercialItemList[x]);
          }
         }
         if(this._ITOeditQoutService.section2Data.length!=0)
         {
         for(let x=0;x< this.othetList.length;x++)
         {
          for(let y=0;y<this._ITOeditQoutService.section2Data.length;y++)
          {
            if(this._ITOeditQoutService.section2Data[y].newColValFlag==0)
            {
if( this.othetList[x].itemId==this._ITOeditQoutService.section2Data[y].itemId && this._ITOeditQoutService.section2Data[y].uncheckFlag==0 )
{
  this.othetList[x]=this._ITOeditQoutService.section2Data[y];
}
          }
          } 
         }
        }
        else
        {
          for(let x1=0;x1< this.othetList.length;x1++)
          {
            this.arraycheck[ this.othetList[x1].itemId]=true;
          }

        }
         for(let x=0;x< this.othetList.length;x++)
         {
          if( this.othetList[x].itemName!="Termination")
          {
            if( this.othetList[x].fixedText1!=null && this.othetList[x].fixedText2!=null  )
            {
             let fixed1= this.othetList[x].fixedText1;
             let fixed2= this.othetList[x].fixedText2;
             let fixed3= this.othetList[x].fixedText3;
             this.othetList[x].fixedText3=fixed1+ " " +fixed2+ " " +fixed3;

            }
          }
          else 
          {
            this.tabletext =  this.othetList[x].fixedText2.split(",");

          }
         }
         this.defaultvalueshieght=[];
  for(let jp=0;jp<this.othetList.length;jp++)
  {
    console.log(jp);
    let stringToSplit=null;
if( this.othetList[jp].itemName!="Termination")
{
stringToSplit = this.othetList[jp].fixedText3;

}
else
{
stringToSplit = this.othetList[jp].fixedText1;

}
    let x = stringToSplit.split("\n");
   let temp=x.length;

    console.log(x.length);
    console.log(x);
  
    for(let y=0;y<x.length;y++)
    {

    
    let a=x[y].length/120;
    if(a>1)
    {
      var b=Math.floor(a);
      temp=temp+b;
    }
  }

    this.defaultvalueshieght.push(temp);
  }
  console.log(this.defaultvalueshieght);
//           if(this.tempArray!=undefined)
//           {
//             if(this.tempArray.length!=0)
//             {
//               for(let y=0;y<this.tempArray.length;y++)
//               {
//                 if(this.tempArray[y].newColValFlag==0 && this.tempArray[y].itemId!=0)
// {


//                 for(let x=0;x<this.othetList.length;x++)
//                 {
// if(this.tempArray[y].seqNo==this.othetList[x].seqNo && this.tempArray[y].itemId==this.othetList[x].itemId )
// {
//   this.othetList[x]=this.tempArray[y];
//   break;
// }
//                 }
//               }

//               }
             
//             }
//           }
//            this.display =   this.othetList .reduce((acc, current) => {
//       console.log(acc, current);
//       const x = acc.find(item => item.itemId === current.itemId);
//       if (!x) {
//         return acc.concat([current]);
//       } else {
//         return acc;
//       } 
//     }, []);
        
//     for(let i=0;i<=100;i++)
//     {
//       this.arraycheck.push([0]);
//     }
//     for(let x=0;x<this.display.length;x++)
//     {
//     for(let j=0;j<100;j++)
//     {
//       this.arraycheck[this.display[x].itemId][j]=false;
//     }

//   }
//   let bolleancheck=false;
//   for(let x=0;x<this.tempArray.length;x++)
//     {
//       if(this.tempArray[x].itemId!=0 && this.tempArray[x].newColValFlag==0  )
//       {
//         bolleancheck=true;
//         break;
//       }
//     }
//   if (!bolleancheck)
//   {
//     for(let x=0;x<this.othetList.length;x++)
//     {
//       if(this.othetList[x].itemId!=0 && this.othetList[x].newColValFlag==0  )
//       {
//       this.arraycheck[this.othetList[x].itemId][this.othetList[x].seqNo]=true;
//       }
//     }  }
//   else
//   {
//     for(let x=0;x<this.tempArray.length;x++)
//     {
//       if(this.tempArray[x].itemId!=0 && this.tempArray[x].newColValFlag==0  )
//       {
//       this.arraycheck[this.tempArray[x].itemId][this.tempArray[x].seqNo]=true;
//       }
//     }
//   }

        });
      }
//       }
     });

  }
  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.section2Local[key] = this.storage.get(key);
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
     // this._ITOeditQoutService.exclusionLi = [];
      this.dboFormDataaa.savedComercialDataList=[];
      
      // this.dboClass=new dboClass();
      // this.dboClass.sectionCd=this.othetList[j].sectionCd;
      // this.dboClass.itemId=this.othetList[j].sectionCd;
      // this.dboClass.subItemId=this.othetList[j].sectionCd;
      // this.dboClass.subItemTypeCd=this.othetList[j].sectionCd;
      // this.dboClass.fixedText1= null;
      // this.dboClass.fixedText2= null;
      // this.dboClass.fixedText3= this.othetList[j].sectionCd;
      // this.dboClass.editFlag= 1;
      // this.dboClass.newColValFlag=0;

      // this.dboFormDataaa.savedComercialDataList.push(this.dboClass);

    console.log(this.othetList);
    let count=100;
    let countexisttable=0;

    for(let j=0;j<this.othetList.length;j++)
    {
      this.dboClass=new dboClass();

      if(this.othetList[j].itemName=="Termination")
      {
        if(this.arraycheck[this.othetList[j].itemId]==true)
        {
        this.dboClass.sectionCd=this.othetList[j].sectionCd;
        this.dboClass.itemId=this.othetList[j].itemId;
        this.dboClass.itemName=this.othetList[j].itemName;
        this.dboClass.subItemId=this.othetList[j].subItemId;
        this.dboClass.subItemTypeCd=this.othetList[j].subItemTypeCd;
        this.dboClass.fixedText1=  this.othetList[j].fixedText1;
        this.dboClass.fixedText2= this.tabletext[0]+","+this.tabletext[1]+","+this.tabletext[2]+","+this.tabletext[3]+","+this.tabletext[4]+","+this.tabletext[5];
        this.dboClass.fixedText3= this.othetList[j].fixedText3;
        this.dboClass.editFlag= 1;
        this.dboClass.newColValFlag=0;
        this.dboClass.uncheckFlag=0;

        this.dboFormDataaa.savedComercialDataList.push(this.dboClass);

        }

      }
      else
      {
        if(this.arraycheck[this.othetList[j].itemId]==true)
        {
        this.dboClass.sectionCd=this.othetList[j].sectionCd;
        this.dboClass.itemId=this.othetList[j].itemId;
        this.dboClass.itemName=this.othetList[j].itemName;
        this.dboClass.subItemId=this.othetList[j].subItemId;
        this.dboClass.subItemTypeCd=this.othetList[j].subItemTypeCd;
        this.dboClass.fixedText1= null;
        this.dboClass.fixedText2= null;
        this.dboClass.fixedText3= this.othetList[j].fixedText3;
        if(this.othetList[j].editFlag==1)
        {
          this.dboClass.editFlag= 1;

        }
        else 
        {
          this.dboClass.editFlag= 0;

        }
        this.dboClass.newColValFlag=0;
        this.dboClass.uncheckFlag=0;

        this.dboFormDataaa.savedComercialDataList.push(this.dboClass);

        }
        else
        {
          this.dboClass.sectionCd=this.othetList[j].sectionCd;
          this.dboClass.itemId=this.othetList[j].itemId;
          this.dboClass.itemName=this.othetList[j].itemName;
          this.dboClass.subItemId=this.othetList[j].subItemId;
          this.dboClass.subItemTypeCd=this.othetList[j].subItemTypeCd;
          this.dboClass.fixedText1= null;
          this.dboClass.fixedText2= null;
          this.dboClass.fixedText3= null;

            this.dboClass.editFlag= 0;
  
          
          this.dboClass.newColValFlag=0;
          this.dboClass.uncheckFlag=1;
  
          this.dboFormDataaa.savedComercialDataList.push(this.dboClass);
        }
      }



    }
    console.log(this.dboFormDataaa.savedComercialDataList);
    this.terminationdata=this.tabletext[0]+","+this.tabletext[1]+","+this.tabletext[2]+","+this.tabletext[3]+","+this.tabletext[4]+","+this.tabletext[5];
    for(let l=0;l<this.itemOthersList1.length;l++)
    {
      this.itemOthersList1[l].itemId=count+1;
      this.dboFormDataaa.savedComercialDataList.push(this.itemOthersList1[l]);
      count=count+1
    }

    console.log( this.dboFormDataaa.otherChapterData);
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.modifiedById =  this._ITOLoginService.loggedUserDetails;
   // this.dboFormDataaa.otherChapterData=this.othetList;

    this._ItoPerformanceService.saveComercial(this.dboFormDataaa).subscribe(res => {
      console.log(res);
      console.log(res.saveComrList2);      
      console.log(res.savedComercialDataList1);
      if (res.successCode == 0) {
        this.dialogMsgApp = true;
        this.dialogMsgVal= "Saved Successfully";
        this._ITOcustomerRequirementService.sendtecBtnStatus(true);
      } else {
        this.dialogMsgApp = false;
        this.dialogMsgVal = res.successMsg; 
      }
    
    
        this.saveInLocal(this.section2, {
          othetList:this.othetList,itemOthersList1:this.itemOthersList1,arraycheck:this.arraycheck,tabletext:this.tabletext,terminationdata:this.terminationdata
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
  itemRmrkCheck(event,id)
  {
    this.dialogMsgApp=false;

    if(event.target.checked){
     this.arraycheck[id]=true;
    }else if(!event.target.checked){
      this.arraycheck[id]=false;
    
    }
  }
  compOthersForm1(others, othersfrm: NgForm) {
    this.dialogMsgApp=false;
    this.dboClass=new dboClass();
    this.dboClass.sectionCd="B";
    this.dboClass.itemId=0;
    this.dboClass.itemName= others.othdes1;

    this.dboClass.subItemId=0;
    this.dboClass.subItemTypeCd=null;
    this.dboClass.fixedText1=  null;
    this.dboClass.fixedText2= null;
    this.dboClass.fixedText3= others.othdes2;
    this.dboClass.editFlag= 1;
    this.dboClass.newColValFlag=1;
    this.dboClass.newColValFlag=1;
    this.dboClass.uncheckFlag=0;

      this.itemOthersList1.push(this.dboClass);

    console.log(others);
    console.log(name);
   
    
  //  this.dboClass.cost =others.othcost;
   
    
   
   
    //this.tempitemOthersList.push(this.dboClass);
    console.log(this.itemOthersList1);
   
    othersfrm.reset();
    this.displayOthnewLine1 = false;

  
     
    
   
  }

  cancelLinesOth1(i) {
    this.dialogMsgApp=false;
    this.itemOthersList1.splice(i, 1);
    this.disableexclusion.splice(i,1);

  }
  cancelnewLineOth1() {
   
    this.displayOthnewLine1 = false;
    this.dialogMsgApp=false;

  }
  addRowsOth1() {
    this.displayOthnewLine1 = true;
    this.dialogMsgApp=false;

  }
     //To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}
checkIfEdited(value,i)
{
  this.othetList[i].editFlag=1;
  this.dialogMsgApp=false;

}
editlhs(i)
{




this.disableexclusion[i]=true;
this.dialogMsgApp=false;


}
savelhs(i)
{
  this.disableexclusion[i]=false;
  this.dialogMsgApp=false;


}
}
