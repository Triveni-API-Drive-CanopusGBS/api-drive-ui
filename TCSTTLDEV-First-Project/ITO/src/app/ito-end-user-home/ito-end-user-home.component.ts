import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOEndUserHomeService } from './ito-end-user-home.service';
//import * as jsPDF from 'jspdf';
import 'jspdf-autotable';
declare let jsPDF;

@Component({
  selector: 'app-ito-end-user-home',
  templateUrl: './ito-end-user-home.component.html',
  styleUrls: ['./ito-end-user-home.component.css']
})
export class ItoEndUserHomeComponent implements OnInit {

  endUsers: Array<any> = [];
  cols: Array<any> = [];
  display: boolean = false;
  displayedColumns: Array<string> = [];
  hideprogress: boolean;

  constructor(private router: Router, private route: ActivatedRoute, private _ITOEndUserHomeService: ITOEndUserHomeService) {
    this.displayedColumns = ['endUserName', 'contactNumber', 'emailId', 'address'];
    this.hideprogress = false;
    this._ITOEndUserHomeService.getQuotationList().subscribe(res => {
      console.log(res);
      this.endUsers = res.customerProfileForm.endUserList;
      console.log(this.endUsers);
      this.hideprogress = true;
    });
    this.cols = [
      { field: 'endUserName', header: 'End user Name' },
      { field: 'contactNumber', header: 'Contact Number' },
      { field: 'emailId', header: 'Email ID' },
      { field: 'address', header: 'Address' }

    ];
  }

  ngOnInit() {
  }

  next(rowData) {

    console.log(rowData);
    this._ITOEndUserHomeService.selectedEndUser = rowData;
    this.router.navigate(['/UpdateEndUser']);

  }

  CreateNewEndUser() {
    this.router.navigate(['/CreateEndUser']);
  }

  exportPDF(dt) {

    var doc = new jsPDF();
    var col = ['End User Name', 'Contact Number', 'Email Id', 'Address'];
    var rows = [];

    /* The following array of object as response from the API req  */
    let itemNew;
    if(dt.totalRecords !=dt._value.length){
        itemNew =dt.filteredValue;
    }else{
        itemNew =this.endUsers;
    }
    

    itemNew.forEach(element => {
      var temp = [element.endUserName, element.contactNumber, element.emailId, element.address];
      rows.push(temp);

    });
    doc.autoTable(col, rows, {
      styles: {
        //cellPadding: 5, // a number, array or object (see margin below)
        columnWidth: 'auto', // 'auto', 'wrap' or a number
        overflow: 'linebreak'
      }
    });

    doc.save('End User List.pdf');

  }
}
