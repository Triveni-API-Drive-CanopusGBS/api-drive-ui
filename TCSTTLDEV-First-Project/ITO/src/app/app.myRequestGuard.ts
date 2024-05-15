import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { LoginValues } from './app.component.login';
import { CanActivate } from "@angular/router";
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Inject } from '@angular/core';
import { ITOLoginService } from './app.component.service';
import { ITOcustomerRequirementService } from './ito-customer-requirement/ito-customer-requirement.service';
import { ITOMyWorkListPageService } from './ito-myworkflow/ito-myWorkFlow.service';

@Injectable()
export class myRequestGuard implements CanActivate {

    userDetail: string = 'userDetail';
    currentRoleDesc: string = 'selRoleDesc';
    currentRole: string = 'selRole';
    data: Array<any> = [];
    codes: Array<any> = []

    constructor(private _ITOLoginService: ITOLoginService, private _ITOMyWorkListPageService: ITOMyWorkListPageService, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
        @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
        this.data[this.currentRole] = this.storage.get(this.currentRole);
        console.log(this.data[this.currentRole]);
        console.log(this._ITOcustomerRequirementService.saveBasicDet);
    }

    canActivate() {
        console.log("myRequestGuard");
        console.log(this.storage.get(this.userDetail));
        this.data[this.currentRole] = this.storage.get(this.currentRole);
        console.log(this.data[this.currentRole]);
        if (this._ITOMyWorkListPageService.selectedUR != '') {
            if (this.data[this.currentRole].includes('TRANS') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('TR')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('PKG') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('PF')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('EC') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('EC')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('UBO') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('UBO')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('FINANCE') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('OVRD')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('UBO') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('SUBCO')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('F2F') && ((this._ITOMyWorkListPageService.selectedUR.updateCode.includes('F2F')) || (this._ITOMyWorkListPageService.selectedUR.updateCode.includes('SUBC')))) {
                return true;
            }
            else if (this.data[this.currentRole].includes('DBO_ELE') && (this._ITOMyWorkListPageService.selectedUR.updateCode.includes('ELE') || this._ITOMyWorkListPageService.selectedUR.updateCode.includes('VMS_FRM_LIST') || this._ITOMyWorkListPageService.selectedUR.updateCode.includes('INSTRUMENT_LIST2')  || this._ITOMyWorkListPageService.selectedUR.updateCode.includes('INSTRUMENT_LIST1') )) {
                return true;
            }
            else if (this.data[this.currentRole].includes('DBO_MECH') && (this._ITOMyWorkListPageService.selectedUR.updateCode.includes('MECH') || this._ITOMyWorkListPageService.selectedUR.updateCode.includes('DBO_MECH'))) {
                return true;
            }
            else if (this.data[this.currentRole].includes('QUOT') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('QUOT')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('SUB') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('SUB')) {
                return true;
            }
            else {
                alert('You are not authorized to edit request');
                return false;
            }
        }
        else {
            if (this.data[this.currentRole].includes('TRANS')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('PKG') ) {
                return true;
            }
            else if (this.data[this.currentRole].includes('EC')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('UBO')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('FINANCE') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('OVRD')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('UBO') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('SUBCO')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('F2F')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('DBO_ELE') ){
                return true;
            }
            else if (this.data[this.currentRole].includes('DBO_MECH')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('QUOT') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('QUOT')) {
                return true;
            }
            else if (this.data[this.currentRole].includes('SUB') && this._ITOMyWorkListPageService.selectedUR.updateCode.includes('SUB')) {
                return true;
            }
            else {
                alert('You are not allowed to this page');
                return false;
            }
        }

    }
}