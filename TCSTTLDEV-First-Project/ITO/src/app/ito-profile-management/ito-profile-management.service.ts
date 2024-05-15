import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';
import { LoginValues } from '../app.component.login';
import {userDetails} from '../ito-create-new-user/ito-create-new-user';


@Injectable()
export class ItoProfileManagementService{
    // private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotHome";
    // private _UserProfileManagementUrl:string="http://10.0.0.4:8082/ITO/user/editUserProfileDetails";
   private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotHome";
    private _UserProfileManagementUrl:string="http://10.0.0.4:8082/ITO/user/editUserProfileDetails";
    constructor(private _http:Http){
    
    }
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }

    userProfileManagement(userDetails:userDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._UserProfileManagementUrl,userDetails, options).map(this.extractData);
    }

        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}