import { Component, OnInit, ViewChild, AfterViewInit, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOconsultHomeService } from './ito-consultant-home.service';
import { consultantDetails } from '../ito-create-consultant/ito-create-consultant';
//import * as jsPDF from 'jspdf';
import 'jspdf-autotable';
declare let jsPDF;

@Component({
  selector: 'app-ito-consultant-home',
  templateUrl: './ito-consultant-home.component.html',
  styleUrls: ['./ito-consultant-home.component.css']
})
export class ItoConsultantHomeComponent {

  consultants: Array<any> = [];
  cols: Array<any> = [];
  display: boolean = false;
  displayedColumns: Array<string> = [];
  selCust: any;
  hideprogress: boolean;


  constructor(private router: Router, private route: ActivatedRoute,
    private _ITOconsultHomeService: ITOconsultHomeService) {
    this.hideprogress = false;
    this.displayedColumns = ['firmName', 'spocName', 'emailId', 'contactNumber'];
    this._ITOconsultHomeService.getQuotationList().subscribe(res => {
      console.log(res);
      this.consultants = res.customerProfileForm.consultantList;
      console.log(this.consultants);
      this.hideprogress = true;
    });
    this.cols = [
      { field: 'firmName', header: 'Consultant Name' },
      { field: 'spocName', header: 'Contact Person Name' },
      { field: 'emailId', header: 'Email Id' },
      { field: 'contactNumber', header: 'Contact Number' }
    ];
  }

  next(rowData) {
    console.log(rowData);
    this.selCust = rowData;
    this._ITOconsultHomeService.selectedConsult = rowData;
    this.router.navigate(['/UpdateConsultant']);

  }
  CreateNewConsultant() {
    this.router.navigate(['/CreateConsultant']);
  }

  exportPDF(dt) {

    var doc = new jsPDF();
    var col = ['Firm Name', 'Spoc Name', 'Email Id', 'Contact Number'];
    var rows = [];

    /* The following array of object as response from the API req  */

    let itemNew;
    if(dt.totalRecords !=dt._value.length){
      itemNew =dt.filteredValue;
    }else{
      itemNew =this.consultants;
    }

    itemNew.forEach(element => {
      var temp = [element.firmName, element.spocName, element.emailId, element.contactNumber];
      rows.push(temp);

    });
    doc.autoTable(col, rows, {
      styles: {
        //cellPadding: 5, // a number, array or object (see margin below)
        columnWidth: 'auto', // 'auto', 'wrap' or a number
        overflow: 'linebreak'
      }
    });

    doc.save('Consultant List.pdf');

  }
}
