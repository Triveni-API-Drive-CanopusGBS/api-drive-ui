import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { LoginValues } from './app.component.login';
import { CanActivate, CanLoad } from "@angular/router";
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOLoginService } from './app.component.service';
import { ITOcustomerRequirementService } from './ito-customer-requirement/ito-customer-requirement.service';
import { ITOMyWorkListPageService } from './ito-myworkflow/ito-myWorkFlow.service';
import { ITOHomePageService } from './ito-home-page/ito-home-page.service';
import { ITOeditQoutService } from './ito-edit-quot/ito-edit-quot.service';

@Injectable()
export class quotEditGuard implements CanActivate, CanLoad {

    userDetail: string = 'userDetail';
    currentRoleDesc: string = 'selRoleDesc';
    currentRole: string = 'selRole';
    data: Array<any> = [];
    codes: Array<any> = []

    constructor(private _ITOLoginService: ITOLoginService, private _ITOMyWorkListPageService: ITOMyWorkListPageService, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
        @Inject(LOCAL_STORAGE) private storage: WebStorageService, private _ITOeditQoutService: ITOeditQoutService, private _ITOHomePageService: ITOHomePageService) {
        this.data[this.currentRole] = this.storage.get(this.currentRole);
        console.log(this.data[this.currentRole]);
        console.log(this._ITOcustomerRequirementService.saveBasicDet);
        console.log(this._ITOHomePageService.selectedQuot);
    }
    canLoad() {
        console.log("quotEuard");
        if (this.data[this.currentRole].includes('QUOT')) {
            return true;
        }
        else {
            alert('no');
            return false;
        }
    }
    canActivate() {
        console.log("quotEditGuard");
        console.log(this.storage.get(this.userDetail));
        this.data[this.currentRole] = this.storage.get(this.currentRole);
        console.log(this.data[this.currentRole]);
        console.log(this._ITOHomePageService.selectedQuot);
        if (this._ITOHomePageService.selectedQuot != '') {
            if (this._ITOeditQoutService.editMode) {
                
            if (this.data[this.currentRole].includes('EDIT') &&
                (this._ITOHomePageService.selectedQuot.statusCode == "IP" || this._ITOHomePageService.selectedQuot.statusCode == "CM"
                    || this._ITOHomePageService.selectedQuot.statusCode == "RC" || this._ITOHomePageService.selectedQuot.statusCode == "SC" || this._ITOHomePageService.selectedQuot.statusCode == "WN"
                    || this._ITOHomePageService.selectedQuot.statusCode == "RIP" || this._ITOHomePageService.selectedQuot.statusCode == "AP")) {
                return true;
            }
                if (this.data[this.currentRole].includes('REVIWER')
                    //  &&
                    //     (this._ITOHomePageService.selectedQuot.statusCode == "IP" || this._ITOHomePageService.selectedQuot.statusCode == 'PR' || this._ITOHomePageService.selectedQuot.statusCode == 'RJR'
                    //         || this._ITOHomePageService.selectedQuot.statusCode == 'SBR')
                ) {
                return true;
            }
                if (this.data[this.currentRole].includes('APPROVER')
                    // &&
                    //     ( this._ITOHomePageService.selectedQuot.statusCode == "IP" || this._ITOHomePageService.selectedQuot.statusCode == 'PA' || this._ITOHomePageService.selectedQuot.statusCode == 'RJA'
                    //         || this._ITOHomePageService.selectedQuot.statusCode == 'SBA')
                ) {
                return true;
            }  
            else {
                alert('You are not authorized to edit selected Quotation');
                return false;
                }
            } else {
                return true;
            }
        }
        else {
            return false;
        }

    }
}