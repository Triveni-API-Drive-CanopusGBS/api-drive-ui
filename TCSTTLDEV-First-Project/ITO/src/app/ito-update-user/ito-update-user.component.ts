import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterPipePipe } from '../filter-pipe.pipe';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ITOLoginService } from '../app.component.service';
import { ITOuserHomeService } from '../ito-user-home/ito-user-home.service';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { ITOupdateUserHomeService } from './ito-update-user.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-ito-update-user',
  templateUrl: './ito-update-user.component.html',
  styleUrls: ['./ito-update-user.component.css']
})
export class ItoUpdateUserComponent implements OnInit {

  selectAllRe: boolean = false;
  selectAllVal: boolean = false;
  selectedUser: userDetails;
  displayMode: boolean;
  Departments: Array<any> = [];
  Roles: Array<any> = [];
  selRoles: Array<any> = [];
  selRegions: Array<any> = [];
  Regions: Array<any> = [];
  userForm: userDetails;
  checked: boolean;
  selectedDept: boolean;
  userImage: string;
  mobnumPattern = "^((\\+91-?)|0)?[0-9]{10}$";
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  emptyTextPattern = ".*[^ ].*";
  loggedInUser: userDetails;
  userDetail: string = 'userDetail';
  profle: Array<any> = [];
  hideprogress: boolean;
  hidespinner: boolean = true;
  dispReg: boolean = false;
  image: any;

  constructor(private _Http: Http, private _Router: Router, private _ActivatedRoute: ActivatedRoute,
    private _ITOuserHomeService: ITOuserHomeService, private _ITOupdateUserHomeService: ITOupdateUserHomeService,
    private _ITOLoginService : ITOLoginService,  @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    this.loggedInUser = new userDetails('');
    this.profle[this.userDetail] = this.storage.get(this.userDetail);
    this.loggedInUser = this.profle[this.userDetail];
    this.userForm = new userDetails('');
    this.displayMode = true;
    this.selectedUser = this._ITOuserHomeService.selectedUser;
    this.userImage = this.selectedUser.image;
    this.image = "data:image/jpeg;base64," + this.userImage;
    console.log(this.selectedUser);
    for (let a = 0; a < this.selectedUser.userRegionsList.length; a++) {
      if(this.selectedUser.userRegionsList[a].key!=0)
      this.selRegions.push(this.selectedUser.userRegionsList[a].key);
    }
    for (let b = 0; b < this.selectedUser.userRolesList.length; b++) {
      if(this.selectedUser.userRolesList[b].key!=0){
      this.selRoles.push(this.selectedUser.userRolesList[b].key);
      }
    }

    console.log(this.selRegions);
    console.log(this.selRoles);
    this.hideprogress = false;
    this._ITOupdateUserHomeService.getQuotationList().subscribe(res => {
      console.log(res);
      this.Departments = res.dropDownColumnvalues.departmentsList.DEPARTMENTS;
      for (let d = 0; d < res.dropDownColumnvalues.departmentsList.DEPARTMENTS.length; d++) {
        if (res.dropDownColumnvalues.departmentsList.DEPARTMENTS[d].value == this.selectedUser.group) {
          this.Departments[d].defaultVal = true;
        }
      }
      for (let r = 0; r < res.dropDownColumnvalues.rolesList.ROLES.length; r++) {
        for (let s = 0; s < this.selectedUser.userRolesList.length; s++) {
          if (res.dropDownColumnvalues.rolesList.ROLES[r].value == this.selectedUser.userRolesList[s].value) {
            res.dropDownColumnvalues.rolesList.ROLES[r].defaultVal = true;
            console.log(res.dropDownColumnvalues.rolesList.ROLES[r].defaultVal);
            if (res.dropDownColumnvalues.rolesList.ROLES[r].code == "QUOT_EDIT") {
              this.dispReg = true;
            }
          }
        }

      }
      this.Roles = res.dropDownColumnvalues.rolesList.ROLES;
     let selR = this.Roles.map(item => item.defaultVal);
    console.log(selR);
    if (selR.includes(false)){
      this.selectAllVal=false;
    }else{
      this.selectAllVal= true;
    }
      for (let p = 0, q = 0; p < res.dropDownColumnvalues.regionsList.REGIONS.length; p++ , q++) {
        for (let q = 0; q < this.selectedUser.userRegionsList.length; q++) {
          if (res.dropDownColumnvalues.regionsList.REGIONS[p].key == this.selectedUser.userRegionsList[q].key) {
            res.dropDownColumnvalues.regionsList.REGIONS[p].defaultVal = true;
            console.log(res.dropDownColumnvalues.regionsList.REGIONS[p].defaultVal);
          }
        }
      }
      this.Regions = res.dropDownColumnvalues.regionsList.REGIONS;
      console.log(this.Regions)
     let selReg = this.Regions.map(item => item.defaultVal);
    console.log(selReg);
    if (selReg.includes(false)){
      this.selectAllRe=false;
    }else{
      this.selectAllRe= true;
    }
      this.hideprogress = true;
    });

  }

  ngOnInit() {
    this._ITOLoginService.dialogMsgApp= false;
  }

  checkedRoles(e, item, ind) {
    console.log(e, item, ind);
    if (e.target.checked) {
      this.selRoles.push(item.key);
      if (item.code == "QUOT_EDIT") {
        this.dispReg = true;
      }
     
    }
    if (!e.target.checked) {
      let i = this.selRoles.indexOf(item.key);
      this.selRoles.splice(i, 1);
      if (item.code == "QUOT_EDIT") {
        this.dispReg = false;
        for (let p = 0, q = 0; p < this.Regions.length; p++ , q++) {
          this.Regions[p].defaultVal = false;
        }
        this.selRegions = [];
      }
    }
    console.log(this.selRoles);
    // let selR = this.Roles.map(item => item.defaultVal);
    // console.log(selR);
    if (this.Roles.length!=this.selRoles.length){
      this.selectAllVal=false;
    }else{
      this.selectAllVal= true;
    }
  }

  checkedRegions(e, item, ind) {
    console.log(e, item, ind);
    console.log(item.dependKey);
    console.log(this.selRegions);
    if (e.target.checked) {
      this.selRegions.push(item.key);
    }
    if (!e.target.checked) {
      let i = this.selRegions.indexOf(item.key);
      this.selRegions.splice(i, 1);
    }
    console.log(this.selRegions);
    // let selReg = this.Regions.map(item => item.defaultVal);
    // console.log(selReg);
    if (this.Regions.length!=this.selRegions.length){
      this.selectAllRe=false;
    }else{
      this.selectAllRe= true;
    }
  }

  enablefields(displayMode) {
    switch (this.displayMode) {
      case true:
        this.displayMode = false;
        break;
      case false:
        this.displayMode = true;
        break;
      default:
        this.displayMode = true;
    }

  }
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
    //alert("Changes will not be saved");
    this._Router.navigate(['/UserHome']);
  }
  updateUser(UserUpdateForm) {
    this.hidespinner = false;
    console.log(UserUpdateForm);
    this.userForm.regionsList = this.selRegions;
    this.userForm.rolesList = this.selRoles;
    this.userForm.empId = UserUpdateForm.empId;
    this.userForm.empName = UserUpdateForm.empName;
    this.userForm.emailId = UserUpdateForm.emailId;
    this.userForm.contactNumber = UserUpdateForm.contactNumber;
    this.userForm.userId = UserUpdateForm.userId;
    this.userForm.active = UserUpdateForm.active;
    this.userForm.designation = UserUpdateForm.designation;

    this.userForm.modifiedById = this.loggedInUser.userId;
    if ((this.userForm.image == "") || (this.userForm.image == undefined)) {

      this.userForm.image = this.userImage;
      console.log(this.userForm.image)
    }
    for (let m = 0; m < this.Departments.length; m++) {
      let group = this.Departments[m].value.trim();
      if (group == UserUpdateForm.group) {
        this.userForm.groupId = this.Departments[m].key;
      }
    }
    console.log(this.userForm);
    this._ITOupdateUserHomeService.updateUser(this.userForm).subscribe(res => {
      console.log(res);
      this.hidespinner = true;
      if (res.successCode == 0) {
        this._ITOLoginService.openSuccMsg("User Information Updated");
        //alert("User Information Updated");
        this._Router.navigate(['/UserHome']);
      } else if (res.successCode == 1) {
        this._ITOLoginService.openSuccMsg(res.successMsg);
      }
      else {
        this._ITOLoginService.errdisplay = true;
        this._ITOLoginService.openSuccMsg("User Updation Failed");
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
