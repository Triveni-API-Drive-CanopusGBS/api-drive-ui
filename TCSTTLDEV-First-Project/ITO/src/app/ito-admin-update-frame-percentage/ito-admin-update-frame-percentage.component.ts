import { Component, OnInit } from '@angular/core';
import { ITOBasicDetailsService } from '../ito-basic-details/ito-basic-details.service';
import { itoAdminBasicDetails } from '../ito-basic-details/ito-basic-details';
import {ItoFramesPercentageService} from './ito-admin-update-frame-percentage.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';

@Component({
  selector: 'app-ito-admin-update-frame-percentage',
  templateUrl: './ito-admin-update-frame-percentage.component.html',
  styleUrls: ['./ito-admin-update-frame-percentage.component.css']
})
export class ItoAdminUpdateFramePercentageComponent implements OnInit {
  // Array to store edited values
  editExistingArray: Array<any>=[];
  // temporary variable to store details
  newFrameArray: any;
  // Column srray to display columns
  newCols: { field: string; header: string; }[];
  
  // Array to store the turbine details
  turbineInstrumentList: any;
  //variable to store the reponse
  tempRes1: any;
  //variable to store display dialog
  displayDialog:boolean=false;
  
  //added for userid
  user: string = 'userDetail';
  data: Array<any> = [];
  userId: any;
  assignee: any;
  dialogMsgApp: boolean = false;
  dialogMsgVal: any;

  constructor(private _ItoFramesPercentageService:ItoFramesPercentageService,private _ITOBasicDetailsService: ITOBasicDetailsService, private _Router: Router
    , private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) { 
  // method call to get the logged in user ID
  this.assignee = this.data[this.user] = this.storage.get(this.user);
  this.userId = this.data[this.user].userId;
  console.log("userIddddddd::" + this.userId);
//method to get entire details list of frame
this._ItoFramesPercentageService.getFrameForm().subscribe(resp => {
  console.log(resp);
  this.tempRes1 = resp;
  this.turbineInstrumentList=resp.turbineInstrumentsWithFramelist;
});
}
//Method to edit details clicked
editFrame(val){
  this.displayDialog=true;
  console.log(val);
  this.newFrameArray=val;
}
//method to update frame
UpdateExistingFrame(val,val1){
 val1.active=true;
  this.editExistingArray.push(val1);
  this.displayDialog=false;
  console.log(this.editExistingArray);
}
// Column detsilas initialisation
  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
    this.newCols = [
      { field: 'frameDesc', header: 'Frame Name' },
      { field: 'turbineDesignCd', header: 'Frame Design' },
      { field: 'turbineCode', header: 'Frame Code' },
      { field: 'maxPower', header: 'Frame Power' },
      { field: 'bleedType', header: 'Bleed Type' },
      { field:'condensingtypes', header: 'Condensing Type'},
      {field: 'suppChainCost', header: 'Supply Chain Cost'},
      { field: 'percentIncr', header: '% Increment' },
      {field:'estimateCost', header:'Est. Cost'},
      { field:'turbInstrCost', header: 'Turbine instr. Cost'},
      {field: 'condInstrCost', header: 'Condensing Instrt. Cost'},
      { field: 'subContrCost', header: 'Sub Contr. Cost' },
      {field:'totalFTFCost', header:'Total F2F Cost'},

  
    ];
  }
  close(){
    this.dialogMsgApp = false;
    this.dialogMsgVal = false;
    window.location.reload();
  }
  //method  to save data to DB
  saveData(){
    this._ItoFramesPercentageService.getAdminForm().subscribe(resp => {
      console.log(resp);
      resp.turbineInstrumentsList=this.editExistingArray;
      resp.modifiedBy=this.userId;
      this._ItoFramesPercentageService.sendAdminForm(resp).subscribe(res => {
        console.log(res);
        if (res.successCode == 0 && res.successMsg != null) {
          this.dialogMsgApp = true;
          this.dialogMsgVal="Update Successfull";
          this._Router.navigate(['/UpdateFramePercentageComponent']);
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Update Failed");   
          //alert("Update Failed");
        }
      }); 
    }); 

  }
}
