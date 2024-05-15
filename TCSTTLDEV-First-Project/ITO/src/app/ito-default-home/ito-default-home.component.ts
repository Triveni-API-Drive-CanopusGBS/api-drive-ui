import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { ITOMyWorkListPageService } from '../ito-myworkflow/ito-myWorkFlow.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { ItoUpdatePackgService } from '../ito-update-packg/ito-update-packg.service';

@Component({
  selector: 'app-ito-default-home',
  templateUrl: './ito-default-home.component.html',
  styleUrls: ['./ito-default-home.component.css']
})
export class ItoDefaultHomeComponent implements OnInit {
  context: any;

  user: string = 'userDetail';
  myRequestList: any;
  otherRequests: any;
  totalRequests: any;
  simple: number = 1;
  colss: Array<any> = [];
  allreqTemp: Array<any> = [];
  allRequests: Array<any> = [];
 
  constructor(private _ITOMyWorkListPageService: ITOMyWorkListPageService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService,private _ItoUpdatePackgService: ItoUpdatePackgService) {
    this._ITOMyWorkListPageService.getUpdatePriceReqGrid(this.storage.get(this.user).userId).subscribe(res => {
      console.log(res);
      this._ITOMyWorkListPageService.selectedUR='';
      this.myRequestList = res.updatePriceMyRequestGrid.length;
     // this.otherRequests = res.updatePriceOthersRequestGrid.length;
     this.allreqTemp = res.updatePriceOthersRequestGrid;
        for (let a = 0; a < this.allreqTemp.length; a++) {

          this.allRequests.push(this.allreqTemp[a]);

        }
      
    })
  }

  ngOnInit() {
    this.colss = [
      { field: 'displayReqNumber', header: 'Req No' },
      { field: 'createdBy', header: 'Created By' },
      { field: 'assignedToName', header: 'Assigned To' },
      { field: 'modifiedDate', header: 'Pending From' },
      { field: 'statusName', header: 'Status' }
    ];

  }

  ngAfterViewChecked() {

    // this.totalRequests = this.myRequestList + this.otherRequests;
    // const can = <HTMLCanvasElement>document.getElementById("newCanv");
    // can.height = 650;
    // can.width = 750;
    // this.context = can.getContext("2d");
    // // this.context.strokeStyle = "green";
    // // this.drawLine(this.context,50,50,200,250);
    // this.context.font = "20px Calibri";
    // this.drawCircle(this.context, 350, 200, 175, 0, (Math.PI * 2) * this.myRequestList / this.totalRequests, "#666699");
    // this.context.fillText("My \n Requests", 600, 120);
    // this.drawCircle(this.context, 350, 200, 175, (Math.PI * 2) * this.myRequestList / this.totalRequests, (Math.PI * 2) * (this.myRequestList + this.otherRequests) / this.totalRequests, "lightgreen");
    // this.context.fillText("All Requests", 600, 80);
  }

  drawCircle(ctxt, centerX, centerY, radius, startAngle, endAngle, color) {
    ctxt.fillStyle = color;
    ctxt.beginPath();
    ctxt.moveTo(centerX, centerY);
    ctxt.arc(centerX, centerY, radius, startAngle, endAngle);
    ctxt.stroke();
    ctxt.fill();
  }

}
