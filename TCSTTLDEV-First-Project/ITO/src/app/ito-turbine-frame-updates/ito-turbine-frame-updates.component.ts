import { Component, OnInit } from '@angular/core';
import { ITOLoginService } from '../app.component.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-ito-turbine-frame-updates',
  templateUrl: './ito-turbine-frame-updates.component.html',
  styleUrls: ['./ito-turbine-frame-updates.component.css']
})
export class ItoTurbineFrameUpdatesComponent implements OnInit {

  TurbineTypeArray: Array<any> = [];
  ModelTypeArray: Array<any> = [];
  FramesArray: Array<any> = [];
  constructor(private _ITOLoginService: ITOLoginService, private _Router: Router) {
    this.TurbineTypeArray = ['Please Select', 'impulsive', 'Reactive'];
    this.ModelTypeArray = ['Please Select', 'Back Pressure', 'Condensing'];
    this.FramesArray = ['Please Select', 'Frame 1', 'Frame 2'];

  }

  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
  }
  goback() {
    this._ITOLoginService.openSuccMsg("Changes will not be saved");
    // alert("Changes will not be saved");
    //var win = window.open("","_self");
    /* url = “” or “about:blank”; target=”_self” */
    //win.close();
    this._Router.navigate(['/HomePage']);
  }
  updateTurbineFrame(turbineFrameUpdateForm) {

  }
}
