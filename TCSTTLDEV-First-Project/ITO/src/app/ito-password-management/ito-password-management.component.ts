import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { ITOLoginService } from '../app.component.service';
import { ITOpasswordManagementService } from './ito-password-management.service';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-ito-password-management',
  templateUrl: './ito-password-management.component.html',
  styleUrls: ['./ito-password-management.component.css']
})
export class ItoPasswordManagementComponent implements OnInit, OnDestroy {

  loggedInUserId: number;
  errorMessage: String = "";
  loggedInUser: userDetails;
  userDetail: string = 'userDetail';
  profle: Array<any> = [];
  hidespinner: boolean = true;
  constructor(private _Router: Router, private _ITOpasswordManagementService: ITOpasswordManagementService, private _ITOLoginService: ITOLoginService,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    this.loggedInUser = new userDetails('');
  }

  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
    this.getQuotationList();
  }
  ngOnDestroy() {
    console.log('ended');
  }

  getQuotationList() {
    this.profle[this.userDetail] = this.storage.get(this.userDetail);
    this.loggedInUser = this.profle[this.userDetail];
    this.loggedInUserId = this.loggedInUser.userId;
    console.log(this.loggedInUserId);
  }
  resetPassword(passwordManagementForm) {
    this.errorMessage = " ";
    this.hidespinner = false;
    console.log(passwordManagementForm);
    if (passwordManagementForm.password == passwordManagementForm.newPassword) {
      this.errorMessage = "Current Password and NewPassword are same,Please change";
      this.hidespinner = true;
    }
    else {
      this.checkValidations(passwordManagementForm);
    }

  }

  checkValidations(passwordManagementForm) {
    if (passwordManagementForm.newPassword != passwordManagementForm.confirmPassword) {
      this.errorMessage = "Confirm Password Mismatch";
      this.hidespinner = true;
    } else {
      this._ITOpasswordManagementService.resetPassword(passwordManagementForm).subscribe(res => {
        console.log(res);
        this.hidespinner = true;
        if (res.successCode == 0) {
          this._ITOLoginService.openSuccMsg("Password Rest Successfully");
          //alert("Password Reset Successfully");
          this.errorMessage = "";
          this._Router.navigate(['/UserHome']);
        } else {
          this.errorMessage = res.successMsg;
        }
      })
    }
  }
}
