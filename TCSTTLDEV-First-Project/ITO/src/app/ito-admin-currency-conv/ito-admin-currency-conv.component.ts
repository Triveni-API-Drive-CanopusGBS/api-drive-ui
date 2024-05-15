import { Component, OnInit } from '@angular/core';
import {itoAdminCurrencyConv} from './ito-admin-currency-conv';
import {ITOCurrencyConvService} from './ito-admin-currency-conv.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';

@Component({
  selector: 'app-ito-admin-currency-conv',
  templateUrl: './ito-admin-currency-conv.component.html',
  styleUrls: ['./ito-admin-currency-conv.component.css']
})
export class ItoAdminCurrencyConvComponent implements OnInit {
// colum details array
  cols:Array<any>=[];
  //currency list array
  currencyListArray:Array<any>=[];
  //newly added currency array
  newCurremcyArray:any;
  //id for currency
  id:number=0;
  //display edit dialog flag
  displayDialog:boolean=false;
  //display add dialog boolean flag
  displayDialogadd:boolean=false;
  //added or edited rupee flag
  addOrEditRuppe:Array<any>=[];
  tst:any;


  //added for userid
  user: string = 'userDetail';
  data: Array<any> = [];
  userId: any;
  assignee: any;
  //stores message details
  msgVal: string;
  //show maximum msg value
  showMsgMax: boolean = false;
  spinner:boolean=false;

  constructor(private _ITOCurrencyConvService: ITOCurrencyConvService , private _Router: Router
    , private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {

    // method call to get the logged in user ID
    this.assignee = this.data[this.user] = this.storage.get(this.user);
    this.userId = this.data[this.user].userId;
    console.log("userIddddddd::" + this.userId);

    //method call to get the Currency list
    // this._ITOCurrencyConvService.getUSDList().subscribe(res => {
    //   console.log(res);
    //   this.currencyList=res.currencyBean;
    //   for(var i=0;i<this.currencyList.length;i++){
    //     if(this.currencyList[i].active==true){
    //       this.currencyList[i].active="Active";
    //       }
    //       else{
    //         this.currencyList[i].active="InActive";
    //       }
    //     }
      
    //   console.log(this.currencyList);
    // })
 
      //method call to get the Currency list for both actice and inactive
      this._ITOCurrencyConvService.getAdminCacheWithAIList().subscribe(res => {
        this.spinner=true;
        console.log(res);
        this.currencyListArray=res.dropDownColumnvalues.currencyList.CURR_LIST;
        for(var i=0;i<this.currencyListArray.length;i++){
          if(this.currencyListArray[i].active==true){
            this.currencyListArray[i].active="Active";
            }
        
            else{
              this.currencyListArray[i].active="InActive";
            }
          }
        
       console.log(this.currencyListArray);
      })

  }
//method to initate colum details
  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
    this.cols = [
      { field: 'cur_NM', header: 'Currency Name' },
      { field: 'cur_CD', header: 'Currency Code' },
      { field: 'convertionRate', header: 'Rupee' },
      { field: 'active', header: 'Status' }
    ];
  }
//method to edit usd
  editUSD(val){
    console.log(val);
    this.newCurremcyArray=val;
    this.displayDialog=true;
   this.id=val.cur_ID;
  }
//method to update currency
  UpdateExistingCurrency(val){
    console.log(val);
    this.tst=new itoAdminCurrencyConv();
    for(var i=0;i<this.currencyListArray.length;i++){
      if(this.id==this.currencyListArray[i].cur_ID){
        this.currencyListArray[i].rupee=val.rupee;
        //sthis.currencyList[i].active=val.status;
        this.tst.cur_CD=this.currencyListArray[i].cur_CD;
        this.tst.cur_ID=this.currencyListArray[i].cur_ID;
        this.tst.cur_NM=this.currencyListArray[i].cur_NM;
        this.tst.active=val.status;
        this.tst.rupee=val.rupee;
        this.tst.convertionRate=val.rupee;
        //this.addOrEditRuppe.push(this.currencyList[i]);
        this.addOrEditRuppe.push(this.tst);
      }
    }
    this.displayDialog=false;
    console.log(this.addOrEditRuppe);
    }
//method to save details temporarily
    saveNew(){
     this.displayDialogadd=true;
    }
//method to add new currency
    createCurrency(val){
      console.log(val);
      this.tst=new itoAdminCurrencyConv();
      this.tst.CUR_ID=0;
      this.tst.active="Active";
      this.tst.CUR_NM=val.currencyName;
      this.tst.cur_NM=val.currencyName;
      this.tst.cur_CD=val.currencyCode;
      this.tst.CUR_CD=val.currencyCode;
      this.tst.rupee=val.rupeee;
      this.tst.RUPEE=val.rupeee;
      this.tst.convertionRate=val.rupeee;
      this.currencyListArray.push(this.tst);
      this.addOrEditRuppe.push(this.tst);
      this.displayDialogadd=false;
    }
//method to save data to DB
    addOrEditUSDList(){
      this._ITOCurrencyConvService.getadminForm().subscribe(res => {
     
        res.modifiedBy=this.userId;
        for(var i=0;i<this.addOrEditRuppe.length;i++){
          if(this.addOrEditRuppe[i].active=="Active"){
            this.addOrEditRuppe[i].active=true;
          }else{
            this.addOrEditRuppe[i].active=false;
          }
        }
        res.currencyBean=this.addOrEditRuppe;
        console.log(res);
        this._ITOCurrencyConvService.addorEditUSD(res).subscribe(resp => {
          console.log(resp);
          
        if (resp.successCode == 0 ) {
          this._ITOLoginService.openSuccMsg("Currency Created/Updated");
          //alert("Currency created/Updated");
          //var win = window.open("", "_self");

          this._Router.navigate(['/adminCurrencyConv']);
        } else {
          this._ITOLoginService.openSuccMsg("Currency Creation/Updation Failed");
          this._ITOLoginService.errdisplay = true;
          //alert("Currency creation/Updation Failed");
          //var win = window.open("", "_self");
          //win.close();
          this._Router.navigate(['/adminCurrencyConv']);
        }
        });
      });
    }
}
