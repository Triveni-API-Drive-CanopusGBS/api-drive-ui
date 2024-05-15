// import { Component, OnInit } from '@angular/core';

// @Component({
//   selector: 'app-ito-quotation-range',
//   templateUrl: './ito-quotation-range.component.html',
//   styleUrls: ['./ito-quotation-range.component.css']
// })
// export class ItoQuotationRangeComponent implements OnInit {

//   constructor() { }

//   ngOnInit() {
//   }

// }

import { Component ,OnInit, ViewChild , AfterViewInit} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http ,Response } from '@angular/http';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router, Params } from '@angular/router';
import {ITOMQuotRangePageService} from './ito-quotation-range.service';
import {ITOLoginService} from '../app.component.service';
import { ThrowStmt } from '@angular/compiler';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
//import * as jsPDF from 'jspdf';
import 'jspdf-autotable';
declare let jsPDF;

@Component({
  selector: 'app-ito-quotation-range',
    templateUrl: './ito-quotation-range.component.html',
     styleUrls: ['./ito-quotation-range.component.css']
})
export class ItoQuotationRangeComponent implements OnInit {

  displayedColumns:Array<string>=[];
  // columnsVal:Array<string>=[];
  public columnsVal: Array<{ text: string, value: number ,defaultValue:boolean}>=[];
  DataSource:any;
  DuplicateArray:any;
  title="ITO tool";
  SearchedValue:any;
  CapacityArray:Array<any>=[];
  userId:any;
  userDetails: any;
  currentRoleId: string = 'selRoleId';
  // ------------------


    quotations:Array<any>;

    cols:Array<any>=[];
  data: Array<any> = [];
  user: string = 'userDetail';

    display:boolean=false;

    RegionsList:Array<any>=[];

    users:Array<any>=[];

    selectedMyQuot:any;

  constructor(private _http:Http ,
    private router:Router,private route: ActivatedRoute ,private ITOMQuotRangePageService:ITOMQuotRangePageService ,
    private _ITOLoginService: ITOLoginService, @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
      this.displayedColumns = ['quotNumber', 'custName', 'statusName','frameName',
      'capacity','modifyDate','createdBy'];
      // this.columnsVal=['Please Select','Quot No','Customer','Status','Frame','Capacity','Modified Date','Created By'];
      this.columnsVal=[
        { text: "Please Select", value: 0, defaultValue:true},
        { text: "Quot No", value: 1 ,defaultValue:false},
        { text: "Customer", value: 2 ,defaultValue:false},
        { text: "Status", value: 3 ,defaultValue:false},
        { text: "Frame", value: 4 ,defaultValue:false},
        { text: "Capacity", value: 5 ,defaultValue:false},
        { text: "Modified Date", value: 6 ,defaultValue:false},
        { text: "Created By", value: 7 ,defaultValue:false}
    ];
   
      this.RegionsList=this._ITOLoginService.usersRegionList; 
      this.userId=this._ITOLoginService.loggedUserDetails;    
    this.ITOMQuotRangePageService.getUsersList().subscribe(res => {
          for(let u=0;u<res.userDetailsList.length;u++){
            this.users.push(res.userDetailsList[u]);
          }   
        console.log(res);  
      })
    }

  ngOnInit() {
    console.log(this.RegionsList);
    this.data[this.user] = this.storage.get(this.user);
    this.userDetails = this.data[this.user];
    this.userDetails.userRoleId = this.storage.get(this.currentRoleId);
    console.log(this.userDetails)
    this.ITOMQuotRangePageService.getQuotationList(this.userDetails).subscribe(res => {
      this.quotations=res.quotationHomeGrid;
      
    
    });

    this.cols = [
      { field: 'quotNumber', header: 'Quot No' },
      { field: 'custName', header: 'Customer' },
      { field: 'statusName', header: 'Status' },
      { field: 'frameName', header: 'Frame' },
      { field: 'capacity', header: 'Capacity' },
      { field: 'modifyDate', header: 'Modified Date' },
      { field: 'assignedTo', header: 'Created By' }
  ];

  }
  assigntoOthers(){
    this.display=true;
}
navigatetoNext(rowData){
  this.selectedMyQuot=rowData;
  

} 
newCostEstimation(){
  this.router.navigate(['/CostEstimation']);
}
exportPDF(dt){
 
  var doc = new jsPDF('landscape');
  var col = ['Quot Number', 'Customer Name', 'Status Name','Frame Name',
  'Capacity','Modify Date','CreatedBy'];
  var rows = [];
  console.log(dt);

/* The following array of object as response from the API req  */
let itemNew;
if(dt.totalRecords !=dt._value.length){
itemNew =dt.filteredValue;
}else{
itemNew =dt._value;
}

itemNew.forEach(element => {      
  var temp = [element.quotNumber,element.custName,element.statusName,element.frameName,
    element.capacity,element.modifyDate,element.createdBy];
  rows.push(temp);

}); 
  doc.autoTable(col, rows,{
    styles:{
      //cellPadding: 5, // a number, array or object (see margin below)
      columnWidth: 'auto', // 'auto', 'wrap' or a number
      overflow: 'linebreak'
  }
    });

  doc.save('Quotations.pdf');

}
onChange(deviceValue) {
  console.log(deviceValue);
}
}

