import { Component, OnInit } from '@angular/core';
import { ITOAdminAddOnService } from './ito-admin-add-on.service';
import { itoAdminAddOn } from './ito-admin-add-on';

@Component({
  selector: 'app-ito-admin-add-on',
  templateUrl: './ito-admin-add-on.component.html',
  styleUrls: ['./ito-admin-add-on.component.css']
})
export class ItoAdminAddOnComponent implements OnInit {

  addOnArray: Array<any> = [];
  newArray: itoAdminAddOn;
  addOnId: number;

  constructor(private _ITOAdminAddOnService: ITOAdminAddOnService) {

    this._ITOAdminAddOnService.getQuotationList().subscribe(res => {
      console.log(res);
      for (var i = 0; i < res.dropDownColumnvalues.addOnComponents.ADD_ON_COMPONENTS.length; i++) {
        this.newArray = new itoAdminAddOn();
        this.newArray.id = i;
        this.newArray.name = res.dropDownColumnvalues.addOnComponents.ADD_ON_COMPONENTS[i].categoryDetDesc;
        this.addOnArray.push(this.newArray);
      }

    })

  }

  addAddOn(val) {
    console.log(val);
    this.addOnId = this.addOnArray.length;
    this.addOnArray.push({ id: this.addOnId, name: val, status: 'active', edit: 'false' });
  }

  editaddOn(val) {
    for (var i = 0; i < this.addOnArray.length; i++) {
      if (this.addOnArray[i].name == val) {
        this.addOnArray[i].edit = false;
        console.log(this.addOnArray);
      }
    }
  }

  closeaddOn(val) {
    for (var i = 0; i < this.addOnArray.length; i++) {
      if (this.addOnArray[i].name == val) {
        this.addOnArray[i].edit = true;
      }
    }
  }

  ngOnInit() {
  }

  displayOrientation() {

  }

}
