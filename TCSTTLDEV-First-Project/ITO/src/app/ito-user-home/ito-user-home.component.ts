import { Component, OnInit, ViewChild , AfterViewInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http ,Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ITOuserHomeService} from './ito-user-home.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
//import * as jsPDF from 'jspdf';
import 'jspdf-autotable';
declare let jsPDF;
import * as html2canvas from "html2canvas";

@Component({
  selector: 'app-ito-user-home',
  templateUrl: './ito-user-home.component.html',
  styleUrls: ['./ito-user-home.component.css']
})
export class ItoUserHomeComponent implements OnInit {

  Users: Array<any>=[];

  cols:Array<any>=[];

  display:boolean=false;

  displayedColumns:Array<string>=[];
  hideprogress: boolean;
  roles:Array<any>=[];

  regions:Array<any>=[];
  constructor(private _ITOuserHomeService:ITOuserHomeService ,private router:Router,private route: ActivatedRoute) {
    this.displayedColumns=['empName', 'emailId', 'group','regionsVal'];
    this.hideprogress = false;
      this._ITOuserHomeService.getQuotationList().subscribe(res=>{
      this.Users=res.userDetailsList;
      console.log(this.Users);
      this.hideprogress = true;
      });
      this.cols=[
        { field: 'empName', header: 'User Name' },
        { field: 'emailId', header: 'Email ID' },
        { field: 'rolesVal', header: 'Roles' } ,
        { field: 'regionsVal', header: 'Regions' },
        { field: 'userActiveStatus', header: 'User Status' }
      ];
      
   }

  ngOnInit() {
  }

  next(rowData){
    
      console.log(this.Users);
    
    console.log(rowData);
    this._ITOuserHomeService.selectedUser=rowData;
    this.router.navigate(['/UpdateUser']);

  }
 
  CreateNewUser(){
    this.router.navigate(['/CreateUser']);
  }

  exportPDF(dt){
    
    var doc = new jsPDF('landscape');
    var col = ['Emp Name', 'Email Id', 'Group','Regions'];
    var rows = [];

/* The following array of object as response from the API req  */
let itemNew;
if(dt.totalRecords !=dt._value.length){
  itemNew =dt.filteredValue;
}else{
 itemNew =this.Users;
}


itemNew.forEach(element => {      
    var temp = [element.empName,element.emailId,element.group,element.regionsVal];
    rows.push(temp);

}); 
    doc.autoTable(col, rows,{
      styles:{
        //cellPadding: 5, // a number, array or object (see margin below)
        columnWidth: 'auto', // 'auto', 'wrap' or a number
        overflow: 'linebreak'
    }
      });

    doc.save('User List.pdf');
  }
  
}
