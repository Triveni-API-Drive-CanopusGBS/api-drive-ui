import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ITOcustHomeService } from './ito-customer-home.service';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Directive, ElementRef, HostListener, Renderer2 } from '@angular/core';
//import * as jsPDF from 'jspdf';
import 'jspdf-autotable';
declare let jsPDF;

@Component({
  selector: 'app-ito-customer-home',
  templateUrl: './ito-customer-home.component.html',
  styleUrls: ['./ito-customer-home.component.css']
})
export class ItoCustomerHomeComponent implements OnInit {

  @ViewChild('row')

  private row: ElementRef;
  customers: Array<any> = [];
  cols: Array<any> = [];
  display: boolean = false;
  displayedColumns: Array<string> = [];
  colorFlag: boolean;
  selCust: any;
  hideprogress: boolean;


  constructor(private router: Router, private route: ActivatedRoute,
    private _ITOcustHomeService: ITOcustHomeService, private elRef: ElementRef, private renderer: Renderer2) {
    this.hideprogress = false;
    this.displayedColumns = ['custName', 'contactPersonName', 'contactNumber', 'custType', 'emailId'];
    this._ITOcustHomeService.getQuotationList().subscribe(res => {
      console.log(res);
      this.customers = res.customerProfileForm.customerList;
      console.log(this.customers);
      this.hideprogress = true;
    });
    this.cols = [
      { field: 'custName', header: 'Customer Name' },
      { field: 'contactPersonName', header: 'Contact Person Name' },
      { field: 'contactNumber', header: 'Contact Number' },
      { field: 'custType', header: 'Customer Type' },
      { field: 'emailId', header: 'email Id' }
    ];
  }

  ngOnInit() {
  }

  next(rowData) {
    console.log(rowData);
    this.selCust = rowData;
    this._ITOcustHomeService.selectedCustomer = rowData;
    this.colorFlag = true;
    this.router.navigate(['/UpdateCustomer']);
  }

  CreateNewCustomer() {
    this.router.navigate(['/CreateCustomer']);
  }

  exportPDF(dt) {

    var doc = new jsPDF('landscape');
    var col = ['Customer Name', 'Contact Person Name', 'Contact Number', 'Customer Type', 'email Id'];
    var rows = [];

    /* The following array of object as response from the API req  */
    let itemNew;
    if (dt.totalRecords != dt._value.length) {
      itemNew = dt.filteredValue;
    } else {
      itemNew = this.customers;
    }


    itemNew.forEach(element => {
      var temp = [element.custName, element.contactPersonName, element.contactNumber, element.custType, element.emailId];
      rows.push(temp);

    });
    doc.autoTable(col, rows, {
      styles: {
        //cellPadding: 5, // a number, array or object (see margin below)
        columnWidth: 'auto', // 'auto', 'wrap' or a number
        overflow: 'linebreak'
      }
    });

    doc.save('Customer List.pdf');

  }
}
