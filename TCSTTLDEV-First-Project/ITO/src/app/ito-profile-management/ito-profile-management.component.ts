import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http ,Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOLoginService} from '../app.component.service';
import {ITOuserHomeService} from '../ito-user-home/ito-user-home.service';
import {userDetails} from '../ito-create-new-user/ito-create-new-user';
import {ItoProfileManagementService} from './ito-profile-management.service';
import { DomSanitizer } from '@angular/platform-browser';
import {MenuItem} from 'primeng/api';
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service';
import { Inject} from '@angular/core';

@Component({
  selector: 'app-ito-profile-management',
  templateUrl: './ito-profile-management.component.html',
  styleUrls: ['./ito-profile-management.component.css']
})
export class ItoProfileManagementComponent implements OnInit {

  loggedInUser:userDetails;
  displayMode:boolean;
  Departments:Array<any>=[];
  Roles:Array<any>=[];
  selRoles:Array<any>=[];
  selRegions:Array<any>=[];
  Regions:Array<any>=[];
  userForm:userDetails;
  checked:boolean;
  selectedDept:boolean;
  image:string;
  Uimage:string;
  userImage:string;
  emailId:string;
  active:boolean;
  errorMessage:string;
  userDetail:string='userDetail';
  profle:Array<any>=[];
  hideprogress: boolean;
  hidespinner: boolean = true;

  constructor(private _Http:Http , private _Router:Router , private _ActivatedRoute:ActivatedRoute ,
    private _ITOuserHomeService:ITOuserHomeService ,private ItoProfileManagementService:ItoProfileManagementService,
    private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer , 
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) { 
      this.userForm=new userDetails('');
      this.loggedInUser=new userDetails('');
      this.hideprogress = false;
      this.displayMode=false;
  }

  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
    this.getQuotationList();
  }

  getQuotationList(){
    console.log("hello");
    this.profle[this.userDetail]=this.storage.get(this.userDetail);
      this.loggedInUser=this.profle[this.userDetail]; // change this to userDetails
      console.log(this.loggedInUser);
      this.userImage = this.loggedInUser.image;
      this.emailId = this.loggedInUser.emailId;
      this.active = this.loggedInUser.active;
      this.image = "data:image/jpeg;base64,"+this.userImage;
      this.Regions = this.loggedInUser.userRegionsList;
      this.Roles = this.loggedInUser.userRolesList;
      for(let a=0;a<this.Regions.length;a++){
        this.selRegions.push(this.Regions[a].key);
      }
      for(let b=0;b<this.Roles.length;b++){
        this.selRoles.push(this.Roles[b].key);
      }
      this.hideprogress = true;
      this.displayMode=false;  
    }

  // enablefields(displayMode){
  //   switch (this.displayMode) {
  //     case true:
  //       this.displayMode=false;
  //       break;
  //     case false:
  //       this.displayMode=true;
  //       break;
  //     default:
  //       this.displayMode=true;
  //   }
   
  // }
  fileChangeEvent(fileInput: any){
    let reader = new FileReader();
    if(fileInput.target.files && fileInput.target.files.length > 0) {
      let file = fileInput.target.files[0];
      reader.readAsDataURL(file);
      console.log(reader.result);
      reader.onload = (fileInput) => {
          this.Uimage= reader.result.split(',')[1];
          this.image="data:image/jpeg;base64,"+this.Uimage;
          console.log(this.image);
      };
      console.log(reader.result);
    }
  }
  
  userProfileManagement(editUserProfileForm){
    this.hidespinner = false;
    console.log(editUserProfileForm);
    this.userForm.regionsList=this.selRegions;
    this.userForm.rolesList=this.selRoles;
    this.userForm.empId=editUserProfileForm.empId;
    this.userForm.empName=editUserProfileForm.empName;
    this.userForm.emailId=this.emailId;
    this.userForm.contactNumber=editUserProfileForm.contactNumber;
    this.userForm.userId=editUserProfileForm.userId;
    this.userForm.groupId=editUserProfileForm.groupId;
    this.userForm.designation=editUserProfileForm.designation;


    this.userForm.active=this.active;
    if((this.image == "") || (this.image == undefined)){     
      this.userForm.image = this.userImage;
      console.log(this.userForm.image);
    } else if(this.image){
      this.userForm.image = this.Uimage;
      console.log(this.userForm.image);
    }
     
    console.log(this.userForm);
   this.ItoProfileManagementService.userProfileManagement(this.userForm).subscribe(res=>{
      console.log(res);
      this.hidespinner = true;
     if(res.successCode==0 && res.successMsg!=null) {
       this._ITOLoginService.openSuccMsg("User Profile Updated, Please Login to view changes");
      //alert("User Profile Updated ,Please Login to view changes");
      // this._Router.navigate(['/UserHome']);
     // location.reload();
    }else{
      this.errorMessage =res.userDetails.successMsg;
    }
    })
  }

}
