import { Component, OnInit ,Input ,OnChanges } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service';
import { Inject} from '@angular/core';
import {ITOScopeOfSupplyService} from '../ito-scope-of-supply/ito-scope-of-supply.service';

@Component({
  selector: 'app-scope-ofsupply',
  templateUrl: './scope-ofsupply.component.html',
  styleUrls: ['./scope-ofsupply.component.css']
})
export class ScopeOfsupplyComponent implements OnInit {

  users:string='user';
  userDetail:string='userDetail';
  customerReqrmnt:string='customerReq';
  endUserDetail:string='endUserDetail';
  custdetails:string='custdetails';
  customerInfo:Array<any>=[];
  enableGITab: boolean = false;
  disabl:boolean=false;
  constructor(private _ActivatedRoute:ActivatedRoute , private router:Router, private domSanitizer: DomSanitizer , private ITOScopeOfSupplyService: ITOScopeOfSupplyService,
     @Inject(LOCAL_STORAGE) private storage: WebStorageService ) { 
      if(this.ITOScopeOfSupplyService.sampleScope.length > 0){
        this.enableGITab = true;
      }
    this.router.navigate(['CostEstimation/ScopeOfsupplyCstEst/ScopeOfSupply']);
  }


  ngOnInit() {
  }

}
