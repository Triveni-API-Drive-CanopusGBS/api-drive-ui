import { Component, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { ItoUserManualService } from './ito-user-manual.service';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { DomSanitizer } from '@angular/platform-browser';
import { ITOLoginService } from '../app.component.service';
import { itoUserManualDetails} from './ito-user-manual';
import { saveAs } from 'file-saver';
//import * as jsPDF from 'jspdf';
import { Pipe, PipeTransform } from '@angular/core';
import 'rxjs/Rx' ;
declare let jsPDF;


@Component({
  selector: 'app-ito-user-manual',
  templateUrl: './ito-user-manual.component.html',
  styleUrls: ['./ito-user-manual.component.css']
})
export class ItoUserManualComponent implements OnInit {

  Pdf:string;
  UiPdf:string;
  userManual: any;
  data : any;
  loginUserDetails: string = "userDetail";
  userId: number;
  localStorageValues:Array<any> = [];
  userManualDet: itoUserManualDetails;
  userManualArray: Array<any> = [];
  cols: { field: string; header: string; }[];
  displayDialog: boolean = false;
  displayDiaAdd: boolean = false;
  selectedRange: itoUserManualDetails;
  userManualArrayTemp:Array<any> = [];
  itemId: number;
  image: string;
  filelength: number;
  userImage: string;
  userImageH:string;
  imageH:string;
  imageNew: string;
  image1:string;
  dialogMsgApp:boolean = false;
  dialogMsgVal:string;
  openDiv:boolean = false;
  docType: string;

  constructor(private _Http: Http, private _Router: Router, private _ActivatedRoute: ActivatedRoute, private sanitizeR: DomSanitizer,
    private _ItoUserManualService: ItoUserManualService, private _ITOLoginService: ITOLoginService, @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
  
  this.localStorageValues[this.loginUserDetails] = this.storage.get(this.loginUserDetails);
  this.userId = this.localStorageValues[this.loginUserDetails].userId;
  console.log(this.userId);
  console.log(this.storage.get(this.loginUserDetails));
  this.localStorageValues = this.storage.get(this.loginUserDetails);
  console.log(this.localStorageValues);
      this._ItoUserManualService.getAdminForm().subscribe(resp => {
       console.log(resp);
       this.userManual = resp;
       this.userManual.itemId = -1;
       console.log(this.userManual);
        this._ItoUserManualService.getGrid().subscribe(res => {
          console.log(res);
          this.userManualArray = res.gridlist;
          console.log(this.userManualArray);
          this.cols = [
            { field: 'itemNm', header: 'File Name' },
            { field: 'itemCd', header: 'Name' },
            { field: 'itemDesc', header: 'Description' },
            { field: 'modifyByNm', header: 'Modified By' },
            { field: 'modifyDate', header: 'Modified Date' },
          ];
        })
      });
     }

    
  
    ngOnInit() {
      this.displayDiaAdd= false;
      this.displayDialog =false;
      this.dialogMsgApp = false;
      
  }

    
    exportDoc(){
      this.itemId = 1061;
      console.log(this.itemId);
      this._ItoUserManualService.downloadFile(this.itemId).subscribe(respD =>{
        console.log(respD);        
      })
    }

    public base64ToBlob(b64Data, contentType='', sliceSize=512) {
      b64Data = b64Data.replace(/\s/g, ''); //IE compatibility...
      let byteCharacters = atob(b64Data);
      let byteArrays = [];
      for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
          let slice = byteCharacters.slice(offset, offset + sliceSize);
  
          let byteNumbers = new Array(slice.length);
          for (var i = 0; i < slice.length; i++) {
              byteNumbers[i] = slice.charCodeAt(i);
          }
          let byteArray = new Uint8Array(byteNumbers);
          byteArrays.push(byteArray);
      }
      return new Blob(byteArrays, {type: contentType});
  }


  downloadFile(val) {
    console.log(val);
    this.itemId= val;
    // this._ItoUserManualService.downloadFile(this.itemId).subscribe(respon => {
    // console.log(respon);
    for(let o=0;o<this.userManualArray.length; o++){
      if(this.itemId == this.userManualArray[o].itemId){
        this.docType = this.userManualArray[o].extension;
        this.userImageH = this.userManualArray[o].item;
        var blob = this.base64ToBlob(this.userImageH, 'text/plain');
        if(this.docType == 'pdf'){
          saveAs(blob, "test.pdf");
        }else if(this.docType == 'doc'){
          saveAs(blob, "test.doc");
        } else if(this.docType == 'docx'){
          saveAs(blob, "test.docx");
        }else if(this.docType =='txt'){
          saveAs(blob, "test.txt"); 
        }else if(this.docType =='xlsx'){
          saveAs(blob, "test.xlsx");      
        }else{
          saveAs(blob, "test.properties");
        }
      }
    }
     
    // });
   }

    // downloadFile(){
    //   this.itemId= 11;
    //   const doc = new jsPDF;
    //   this._ItoUserManualService.downloadFile(this.itemId).subscribe(respon => {
    //         console.log(respon);
    //         this.userImageH = respon.item;
    //         var pdfInBase64 = atob(this.userImageH)
    //           let file = new Blob( [pdfInBase64], { type: 'application/pdf' });            
    //           var fileURL = URL.createObjectURL(file);
    //           window.open(fileURL);
    //           // console.log(this.userImageH);
    //           //   const linkSource = 'data:application/pdf;base64,' + this.userImageH;
    //           //   const downloadLink = document.createElement("a");
    //           //   const fileName = "sample.pdf";
        
    //           //   downloadLink.href = linkSource;
    //           //   downloadLink.download = fileName;
    //           //   downloadLink.click();
           


    //   //       })

    //   //       this.imageH = 'data: application/pdf' + ';base64,' + this.userImageH;
    //   // doc.text(this.imageH, 10, 10);
    //   // doc.save('test.pdf');
    //   });
    //   }


     editUserManual(valu){
       console.log(valu);
      this.displayDialog = true;
      this.selectedRange = valu;
      console.log(this.selectedRange);
      this.userImage = this.selectedRange.item;
      this.image = "data:image/jpeg;base64," + this.userImage;
      console.log(this.image);
     }
          

  fileUpload(fileInput: any) {
    let reader = new FileReader();
    console.log(fileInput);
    if (fileInput.target.files && fileInput.target.files.length > 0) {
      let file = fileInput.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        // this.form.get('avatar').setValue({
        //   filename: file.name,
        //   filetype: file.type,
        this.userManual.item = reader.result.split(',')[1];
        // })
      };
      console.log(reader.result);
      console.log(this.userManual.item);
    }
  }

  addNewDoc(){
    this.displayDiaAdd = true;
  }
  close(){
    this.dialogMsgApp = false;
    window.location.reload();
}  
  
  addNewDocument(userMaForm){
    console.log(userMaForm)
    console.log(this.userManual.item);
    this.userManual.itemId = 0;
    this.userManual.itemCd = userMaForm.itemCd;
    this.userManual.itemNm = userMaForm.itemCd;
    this.userManual.itemDesc = userMaForm.coments;
    this.userManual.filePath = userMaForm.image;
   // this.userManual.item = userMaForm.manual;
    this.userManual.modifiedBy = this.userId;
    console.log(this.userManual);
    this._ItoUserManualService.getUpload(this.userManual).subscribe(resp => {
      console.log(resp);
      this.displayDiaAdd = false;
      if (resp.successCode == 0 && resp.successMsg != null) {
        this.dialogMsgApp = true;
        this.dialogMsgVal= resp.successMsg;
      } else {
        this.dialogMsgApp = true;
        this.dialogMsgVal = resp.successMsg; 
      }
      // window.location.reload();
    });    
  }
  updateDocument(val){
    this.displayDialog = false;
    console.log(this.selectedRange);
    console.log(val);
    this.userManualDet = new itoUserManualDetails();
    //this.userManualDet.item = val.manual;
    this.userManualDet.itemCd = val.activeNm;
    this.userManualDet.itemDesc = val.active;
    this.userManualDet.itemNm = val.activeNm;
    this.userManualDet.itemId = this.selectedRange.itemId;
    this.userManualDet.modifiedBy = this.userId;
    console.log(this.userManualDet);
    this._ItoUserManualService.getUserManual(this.userManualDet).subscribe(respn => {
      console.log(respn);
      if (respn.successCode == 0 && respn.successMsg != null) {
        this.dialogMsgApp = true;
        this.dialogMsgVal= respn.successMsg;
      } else {
        this.dialogMsgApp = true;
        this.dialogMsgVal = respn.successMsg; 
      }
    });
   }
  }















  // onFileChanged(event: { target: { files: any[]; }; }) {  
  
  //     let reader = new FileReader();  
  //     if (event.target.files && event.target.files.length > 0) {  
  //       let file = event.target.files[0];  
  //       this.itemId=15;
  //     this.openDiv = true;
  //     this._ItoUserManualService.downloadFile(this.itemId).subscribe(respon => {
  //       console.log(respon);
  //       this.userImageH = respon.item;
  //       console.log(this.userImageH);
  //       reader.readAsDataURL(file);  
  //       reader.onload = () => {  
  //         this.userImageH = file.name + " " + file.type;  
  //         const doc = new jsPDF();  
  //         const base64ImgString = (reader.result as string).split(',')[1];  
  //         doc.addImage(base64ImgString, 15, 40, 50, 50);    
  //         this.userImageH = 'data:application/pdf' + ';base64,' + base64ImgString;  
  //         doc.save('TestPDF')  
  //     }
  //     })
  //   } 
  // }
  



