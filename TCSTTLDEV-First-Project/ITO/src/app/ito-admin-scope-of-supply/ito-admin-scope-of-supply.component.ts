import { Component, OnInit } from '@angular/core';
import { itoAdminScopeOfSupply } from './ito-admin-scope-of-supply';
import { ITOAdminScopeOfSupplyService } from './ito-admin-scope-of-supply.service';

@Component({
  selector: 'app-ito-admin-scope-of-supply',
  templateUrl: './ito-admin-scope-of-supply.component.html',
  styleUrls: ['./ito-admin-scope-of-supply.component.css']
})
export class ItoAdminScopeOfSupplyComponent implements OnInit {

  scopeArray: Array<any> = [];
  newArray: itoAdminScopeOfSupply;
  scopeId: number;

  constructor(private _ITOAdminScopeOfSupplyService: ITOAdminScopeOfSupplyService) {

    this._ITOAdminScopeOfSupplyService.getQuotationList().subscribe(res => {
      console.log(res);
      for (var i = 0; i < res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY.length; i++) {
        this.newArray = new itoAdminScopeOfSupply();
        this.newArray.id = i;
        this.newArray.name = res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY[i].scopeName;
        this.scopeArray.push(this.newArray);
      }

    })

  }
  editScope(val) {
    for (var i = 0; i < this.scopeArray.length; i++) {
      if (this.scopeArray[i].name == val) {
        this.scopeArray[i].edit = false;
        console.log(this.scopeArray);
      }
    }
  }
  closeScope(val) {
    for (var i = 0; i < this.scopeArray.length; i++) {
      if (this.scopeArray[i].name == val) {
        this.scopeArray[i].edit = true;
      }
    }
  }
  addScope(val) {
    this.scopeId = this.scopeArray.length;
    this.scopeArray.push({ id: this.scopeId, name: val, status: 'active', edit: 'false' });
  }
  ngOnInit() {
  }
  displayOrientation() {

  }

}
