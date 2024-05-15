import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';
import {consultantDetails} from '../ito-create-consultant/ito-create-consultant';
import { ITOuserHomeService } from '../ito-user-home/ito-user-home.service';
import {userDetails} from '../ito-create-new-user/ito-create-new-user';


@Injectable()
export class ITOupdateUserHomeService{
    private _getSelUserUrl:string="http://10.0.0.4:8082/ITO/user/getUserDetails";
    private _updateUserUrl:string="http://10.0.0.4:8082/ITO/user/editUserProfileDetails";
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotHome";

    constructor(private _http:Http ,private _ITOuserHomeService:ITOuserHomeService){
    
    }

    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }

    getSelUserList(selectedUser:userDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getSelUserUrl,selectedUser, options).map(this.extractData);
    }
   updateUser(userDetails:userDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._updateUserUrl,userDetails, options).map(this.extractData);
    }
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}