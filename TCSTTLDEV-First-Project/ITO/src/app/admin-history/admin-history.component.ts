import { Component, OnInit } from '@angular/core';
import { ITOturbineConfigService } from  '../ito-turbine-config/ito-turbine-config.service'
import { BgmratingsComponentService } from '../bgmratings/bgmratings.service';
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
import { dboClass } from '../bgmratings/bgmratings';
@Component({
  selector: 'app-admin-history',
  templateUrl: './admin-history.component.html',
  styleUrls: ['./admin-history.component.css']
})
export class AdminHistoryComponent implements OnInit {

  tableDropDown:any=[];
  tableDropDownSub:any=[];
  tableDEfault:any='';
  cols: { field: string; header: string; }[];
  pTableList:any=[];
  mainList:any=[];
  subListvalue:any='';
  tableName:any='';
  adminForm:any;
  tableBoolean:boolean=false;
  spinner:boolean=true;


  


  constructor(private _ITOturbineConfigService: ITOturbineConfigService, private _ITOScopeOfSupplyService: ITOScopeOfSupplyService,
    private _BgmratingsComponentService: BgmratingsComponentService, private router: Router,
    private _ITOCostEstimationService: ITOCostEstimationService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ItoUserManualService: ItoUserManualService,
    private _ITOeditQoutService: ITOeditQoutService,  @Inject(LOCAL_STORAGE) private storage: WebStorageService,) { 




      this._BgmratingsComponentService.getAuditHistory1().subscribe(res => {
        console.log(res);
        this.mainList=res;
        this.tableDEfault=res.auditFormlist[0].scopeCode;
        this.tableDropDown = res.auditFormlist.reduce((acc, current) => {
          console.log(acc, current);
          const x = acc.find(item => item.scopeCode ===current.scopeCode);
          if (!x) {
            return acc.concat([current]);
          } else {
            return acc;
          } 
        }, []);

        for(let j=0;j<this.mainList.auditFormlist.length;j++)
        {
          if(this.mainList.auditFormlist[j].scopeCode==this.mainList.auditFormlist[0].scopeCode)
          {
            this.tableDropDownSub.push(this.mainList.auditFormlist[j]);
          }
        }
        this.tableName=this.tableDropDownSub[0].tableName;
        this.subListvalue= this.tableDropDownSub[0].scopeDesc;
        // this.cols = [
        //   { field: 'frameName', header: 'Frame Name' },
        //  ];
       

       
      //   this._BgmratingsComponentService.getAuditHistory1().subscribe(res2 => {
      //     this.pTableList=res2.auditFormlist;
      //   for(let i=0;i<res2.audir.length;i++)
      //   {
      //     this.cols.push({field: res2.audir[i]., header:  res2.audir[i].});
      //     console.log(this.cols);
      //   }
      //   this.cols.shift();
      // });
        //   for(let j=0;j<this.cols.length;j++)
        //   {
        //     if(this.pTableList[0].col[j].field==null || this.pTableList[0].col[j].field=='NULL')
        //     {
        //       this.cols.splice(j,1);
        //       j=j-1;
        //     }
        //   }
        // });

     
       
     
    
        this._BgmratingsComponentService.getAdminForm().subscribe(res => {
          console.log(res);
          this.adminForm = res;

        });
      });
    }
    subListSelection(value)
    {
      this.tableBoolean=false;
      this.tableDropDownSub=[];
for(let j=0;j<this.mainList.auditFormlist.length;j++)
{
  if(this.mainList.auditFormlist[j].scopeCode==value)
  {
    this.tableDropDownSub.push(this.mainList.auditFormlist[j]);
  }
}
this.subListvalue= this.tableDropDownSub[0].scopeDesc;
this.tableName=this.tableDropDownSub[0].tableName;


    }
    subListSelectionSub(value)
    {
      this.tableBoolean=false;
      for(let j=0;j<this.tableDropDownSub.length;j++)
      {
        if(this.tableDropDownSub[j].scopeDesc==value)
        {
          this.tableName=this.tableDropDownSub[j].tableName;
          break;

        }
      }
    }
    getTable()
    {
      this.spinner=false;

      this.tableBoolean=false;
      this.cols=[];
      this.cols = [
        { field: 'frameName', header: 'Frame Name' },
       ];
     
this.adminForm.tableName=this.tableName;
     
      this._BgmratingsComponentService.getAuditHistoryDetails(this.adminForm).subscribe(res2 => {
        this.spinner=true;

        this.pTableList=res2.auditHistoryList;
        console.log(res2);
        
      for(let i=0;i<res2.nameList.length;i++)
      {
         this.cols.push({field: res2.nameList[i].name.toLowerCase(), header:  res2.nameList[i].name});
        console.log(res2.nameList[i].name.toLowerCase());
      
      }
      this.cols.shift();
      this.tableBoolean=true;
    });
    }
    
  ngOnInit() {
  }

}
