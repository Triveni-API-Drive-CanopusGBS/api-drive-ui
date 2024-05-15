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
import { Header } from 'primeng/components/common/shared';

@Component({
  selector: 'app-commercial-sec1',
  templateUrl: './commercial-sec1.component.html',
  styleUrls: ['./commercial-sec1.component.css']
})
export class CommercialSec1Component implements OnInit {

  dboComrSecALocal: Array<any> = [];
  dboComSecALoc: string = 'comSecA'; // local storage value
  saveComrList2:Array<any> = []; //To store updated data from saveComercial end Point
  dialogMsgVal: string = '';
  dialogMsgApp: boolean = false;
  dboFormDataaa: any; //To store dbo form response
  quotId:number=0;
  backBtn: boolean = false;
  disableData:boolean=false;
  dboComercialItemList: Array<any> = [];
  itemNmList: Array<any> = [];
  subItemNmList: Array<any> =[];
  defaultvalueshieght: Array<any> = [];
  subItemTyList: Array<any> = [];
  COMRSecA: Array<any> = [];
  dboClass: dboClass;
  tempList:Array<any> =[];
  defaultVal: Array<any> =[];
  tempArray: Array<any> =[]; //To store local storate data on switching panels
  qouttype:string=null;
  defHeight:Array<any> = [];

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
      if (this._ITOcustomerRequirementService.saveBasicDet.typeOfQuotNm == "Standard")
      {
        this.disableData=true;
      }
      if(this._ITOcustomerRequirementService.saveBasicDet.custType=="Domestic" || this._ITOcustomerRequirementService.saveBasicDet.custCodeNm=="Domestic")
{
  this.qouttype = "DOMESTIC";
}
else 
{
  this.qouttype = "OFFSHORE"; 
}
      //calling getDboFormData to as input for getComercial end point
    this._ItoPerformanceService.getDboFormData().subscribe(res => {
      console.log(res);
      this.dboFormDataaa = res;
      this.dboComrSecALocal[this.dboComSecALoc] = this.storage.get(this.dboComSecALoc);
      console.log(this.storage.get(this.dboComSecALoc));
    this.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
      console.log(this.dboFormDataaa);
    this._ItoPerformanceService.getComercial(this.dboFormDataaa).subscribe(resp => {
      console.log(resp);
      console.log(resp.dboComercialItemList);
      this.dboComercialItemList = [];
      this.tempList = [];
      this.defaultVal = [];
      for(let i=0; i<resp.dboComercialItemList.length; i++){
        if(resp.dboComercialItemList[i].sectionCd == 'A' && resp.dboComercialItemList[i].itemName != 'Definitions'){
      this.dboComercialItemList.push(resp.dboComercialItemList[i]);
      this.defaultVal.push(resp.dboComercialItemList[i].fixedText3);
        }else if(resp.dboComercialItemList[i].itemName == 'Definitions' && resp.dboComercialItemList[i].sectionCd == 'A'){
          this.tempList.push(resp.dboComercialItemList[i]);
        }
      }
      console.log(this.dboComercialItemList)
      console.log(this.tempList);
      this.tempArray = [];
      if(this._ITOcustomerRequirementService.editFlag==true &&  this._ITOeditQoutService.section2AData!=undefined){
        this.dialogMsgApp=true;
        this.saveComrList2=this._ITOeditQoutService.section2AData;
      }
      if (this.storage.get(this.dboComSecALoc) == null) {
        this.saveInLocal(this.dboComSecALoc, {
          saveComrList2:this.saveComrList2
        });
      }
      if(this.storage.get(this.dboComSecALoc) != null){
        this.tempArray= this.storage.get(this.dboComSecALoc).saveComrList2;  
        if(this.tempArray!=null && this.tempArray.length>0){
        for(let l=0; l<this.dboComercialItemList.length; l++){
          if(this.dboComercialItemList[l].itemId == this.tempArray[0].itemId){
            this.dboComercialItemList[l].fixedText2 = this.tempArray[0].fixedText2;
          }
        }
        }
      }    
     
      console.log(this.dboComercialItemList)
      this.itemNmList = this.dboComercialItemList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.itemName === current.itemName);
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      console.log(this.itemNmList);

      this.subItemNmList = this.dboComercialItemList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item =>item.subItemId === current.subItemId );
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      console.log(this.subItemNmList);

      this.subItemTyList = this.dboComercialItemList.reduce((acc, current) => {
        console.log(acc, current);
        const x = acc.find(item => item.subItemTypeName === current.subItemTypeName );
        if (!x) {
          return acc.concat([current]);
        } else {
          return acc;
        } 
      }, []);
      console.log(this.subItemTyList);
      
      this.defHeight=[];
      for(let jp=0;jp<this.tempList.length;jp++)
      {
        console.log(jp);
    
        let stringToSplit = this.tempList[jp].note;
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
      this.defHeight.push(temp);
  }

      this.defaultvalueshieght=[];
  for(let jp=0;jp<this.defaultVal.length;jp++)
  {
    console.log(jp);

    let stringToSplit = this.defaultVal[jp];
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
    });
    });
  }
  ngOnInit() {
  }

  /**
   * 
   * @param key key value to store in localstorage
   * @param val valueto be stored in localstorage
   */
  saveInLocal(key, val): void {
    this.storage.set(key, val);
    this.dboComrSecALocal[key] = this.storage.get(key);
  }

        //To navigate edit quotaion page on click of back button
backButton(){
  this.router.navigate(['/EditQuot']);
}
checkEdit()
{
  this.dialogMsgApp=false;
}
saveCom(){
  this.COMRSecA = [];
    console.log(this.dboComercialItemList);
    
    for(let d=0; d<this.dboComercialItemList.length ; d++){
      if(this.dboComercialItemList[d].itemName == '3. Validity'){
      this.dboClass=new dboClass;
      this.dboClass.sectionCd = this.dboComercialItemList[d].sectionCd;
      this.dboClass.itemId = this.dboComercialItemList[d].itemId;
      this.dboClass.subItemId = this.dboComercialItemList[d].subItemId;
      this.dboClass.subItemTypeCd = this.dboComercialItemList[d].subItemTypeCd;
      this.dboClass.fixedText1 = null;
      this.dboClass.fixedText2 = this.dboComercialItemList[d].fixedText2;
      this.dboClass.fixedText3 = null;
      this.dboClass.editFlag = 1;
      this.dboClass.newColValFlag = 0;
      this.dboClass.uncheckFlag = 0;
      this.COMRSecA.push(this.dboClass);
      }
    }
    console.log(this.COMRSecA);
    this.dboFormDataaa.quotId = this._ITOcustomerRequirementService.saveBasicDet.quotId;
    this.dboFormDataaa.savedComercialDataList = this.COMRSecA;
    this.dboFormDataaa.modifiedById = this._ITOcustomerRequirementService.saveBasicDet.modifiedById;
    this._ItoPerformanceService.saveComercial(this.dboFormDataaa).subscribe(respo => {
      console.log(respo);      
      this.saveComrList2 = respo.saveComrList2;
      if (respo.successCode == 0) {
        this.dialogMsgApp = true;
        this.dialogMsgVal= "Saved Successfully";
        this._ITOcustomerRequirementService.sendcomBtnStatus(true);
      } else {
        this.dialogMsgApp = false;
        this.dialogMsgVal = respo.successMsg; 
      }
      
      if (this._ITOcustomerRequirementService.editFlag &&  this._ITOeditQoutService.checkEdit==false) {
        this._ITOcustomerRequirementService.editFlag = false;
        this.router.navigate(['/EditQuot']);
      }
      this.saveInLocal(this.dboComSecALoc, { 
        saveComrList2: this.saveComrList2 });
    });
}
}
