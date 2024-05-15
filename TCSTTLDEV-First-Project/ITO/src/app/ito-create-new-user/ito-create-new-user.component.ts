import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOLoginService } from '../app.component.service';
import { ITOcreateNewUserService } from './ito-create-new-user.service';
import { userDetails } from './ito-create-new-user';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';


@Component({
  selector: 'app-ito-create-new-user',
  templateUrl: './ito-create-new-user.component.html',
  styleUrls: ['./ito-create-new-user.component.css']
})
export class ItoCreateNewUserComponent implements OnInit {

  selectAllRe: boolean = false;
  selectAllVal: boolean = false;
  Departments: Array<any> = [];
  Roles: Array<any> = [];
  selRoles: Array<any> = [];
  selRegions: Array<any> = [];
  Regions: Array<any> = [];
  userForm: userDetails;
  mobnumPattern = "^((\\+91-?)|0)?[0-9]{10}$";
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  emptyTextPattern = ".*[^ ].*";
  loggedInUser: userDetails;
  userDetail: string = 'userDetail';
  profle: Array<any> = [];
  hideprogress: boolean;
  hidespinner: boolean = true;
  dispReg: boolean = false;

  constructor(private _ITOcreateNewUserService: ITOcreateNewUserService, private _ITOLoginService: ITOLoginService, private _Router: Router,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    this.loggedInUser = new userDetails('');
    this.profle[this.userDetail] = this.storage.get(this.userDetail);
    this.loggedInUser = this.profle[this.userDetail];
    this.hideprogress = false;
    this.userForm = new userDetails('');
    // getting required fields to create user
    this._ITOcreateNewUserService.getQuotationList().subscribe(res => {
      console.log(res);
      this.Departments = res.dropDownColumnvalues.departmentsList.DEPARTMENTS;
      this.Roles = res.dropDownColumnvalues.rolesList.ROLES;
      this.Regions = res.dropDownColumnvalues.regionsList.REGIONS;
      this.hideprogress = true;
    })
  }

  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
  }

  // only if "QUOT_EDIT" role is checked, regions should be displayed,that validation is done in below method
  checkedRoles(e, item, ind) {
    console.log(item);
    if (e.target.checked) {
      this.selRoles.push(item.key);
      if (item.code == "QUOT_EDIT") {
        this.dispReg = true;
      } else {
        this.dispReg = false;
        this.selRegions = [];
      }
    }
    if (!e.target.checked) {
      let i = this.Roles.indexOf(item.key);
      this.selRoles.splice(i, 1);
      if (item.code == "QUOT_EDIT") {
        this.dispReg = false;
        this.selRegions = [];
      }
    }
    if (this.Roles.length!=this.selRoles.length){
      this.selectAllVal=false;
    }else{
      this.selectAllVal= true;
    }
    console.log(this.selRoles);
  }

  // form list of checked regions
  checkedRegions(e, item, ind) {
    console.log(item);
    if (e.target.checked) {
      this.selRegions.push(item.key);
    }
    if (!e.target.checked) {
      let i = this.Roles.indexOf(item.key);
      this.selRegions.splice(i, 1);
    }
    console.log(this.selRegions);
    if (this.Regions.length!=this.selRegions.length){
      this.selectAllRe=false;
    }else{
      this.selectAllRe= true;
    }
  }

  depSel(department) {
    console.log(department);
  }

  // whenever photo is uploaded this function will be called
  fileChangeEvent(fileInput: any) {
    let reader = new FileReader();
    console.log(fileInput);
    if (fileInput.target.files && fileInput.target.files.length > 0) {
      let file = fileInput.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        // this.form.get('avatar').setValue({
        //   filename: file.name,
        //   filetype: file.type,
        this.userForm.image = reader.result.split(',')[1];
        // })
      };
      console.log(reader.result);
    }
  }
  goback() {
    this._ITOLoginService.openSuccMsg("Changes will not be saved");
    // alert("Changes will not be saved");
    this._Router.navigate(['/UserHome']);
  }
  createNewUser(createNewUserForm) {
    this.hidespinner = false;
    console.log(createNewUserForm);
    console.log(createNewUserForm.group);
    // for(let m=0;m<=this.Departments.length;m++){
    //   if(this.Departments[m].value==createNewUserForm.group){
    //     this.userForm.groupId=this.Departments[m].key;
    //     console.log("found");

    //   }
    // }
    for (let dep of this.Departments) {
      let group = dep.value.trim();
      if (group == createNewUserForm.group) {
        console.log(dep.key);
        this.userForm.groupId = dep.key;
        break;
      }
    }
    this.userForm.empId = createNewUserForm.empId;
    this.userForm.empName = createNewUserForm.empName;
    this.userForm.emailId = createNewUserForm.emailId;
    this.userForm.contactNumber = createNewUserForm.contactNumber;
    // this.userForm.image=createNewUserForm.image;
    this.userForm.regionsList = this.selRegions;
    this.userForm.rolesList = this.selRoles;
    this.userForm.modifiedById = this.loggedInUser.userId;
    this.userForm.designation = createNewUserForm.designation;


    console.log(this.userForm);
    this._ITOcreateNewUserService.createNewUser(this.userForm).subscribe(res => {
      console.log(res);
      this.hidespinner = true;
      if (res.successCode == 0 && res.successMsg != null) {
        this._ITOLoginService.openSuccMsg("User Created");
        //alert("User created");
        this._Router.navigate(['/UserHome']);
      }
      else if (res.successCode == 1) {
        this._ITOLoginService.openSuccMsg(res.successMsg);
      } else {
        this._ITOLoginService.errdisplay = true;
        this._ITOLoginService.openSuccMsg("User Creation Failed");
      }

    })
  }
  selectAll(event){
    this.selRoles=[];
    console.log(event.target.checked);
    if(event.target.checked){
      for(let n=0;n<this.Roles.length;n++){
        this.Roles[n].defaultVal=true;
      this.selRoles.push(this.Roles[n].key);
      if (this.Roles[n].code == "QUOT_EDIT") {
        this.dispReg = true;
      }
      }
    }
    if(!event.target.checked){     
        this.dispReg = false;
        for (let p = 0, q = 0; p < this.Regions.length; p++ , q++) {
          this.Regions[p].defaultVal = false;
        }
        for(let o=0; o<this.Roles.length; o++){
          this.Roles[o].defaultVal=false;
        }
        this.selRegions = [];    
        this.selRoles=[];  
    }
  }
  selectAllReg(event){
    console.log(event.target.checked);
    this.selRegions=[];
    if(event.target.checked){
      for(let m=0; m<this.Regions.length; m++){
        this.selRegions.push(this.Regions[m].key);
        this.Regions[m].defaultVal=true;
      }
  }
  if(!event.target.checked){
    this.selRegions=[];
   for(let n=0; n<this.Regions.length; n++){
    this.Regions[n].defaultVal = false;
    }
  }
  console.log(this.selRegions);
}
}
