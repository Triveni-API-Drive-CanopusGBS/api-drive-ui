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


@Injectable()
export class AlwaysAuthGuard implements CanActivate, CanLoad {

    userDetail: string = 'userDetail';
    data: Array<any> = [];
    currentRole: string = 'selRole';

    constructor(private _ITOLoginService: ITOLoginService, private _ITOcustomerRequirementService: ITOcustomerRequirementService,
        @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
        this.data[this.userDetail] = this.storage.get(this.userDetail);
        console.log(this.data[this.userDetail]);
        console.log(this._ITOcustomerRequirementService.saveBasicDet);
    }
    canLoad() {
        if (this.storage.get(this.currentRole).includes('QUOT')) {
            return true;
        }
        else {
            alert('no');
            return false;
        }
    }
    canActivate() {
        console.log("AlwaysAuthGuard");
        console.log(this.data[this.userDetail]);
        if (this._ITOcustomerRequirementService.saveBasicDet) {
            return true;
        }
        else {
            console.log("not allowed");
            return false;
        }
    }

}