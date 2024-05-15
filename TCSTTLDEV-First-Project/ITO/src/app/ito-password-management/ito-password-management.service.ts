import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';
import { LoginValues } from '../app.component.login';


@Injectable()
export class ITOpasswordManagementService{
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotHome";
    private _resetPwdUrl:string="http://10.0.0.4:8082/ITO/login/resetPassword";
    constructor(private _http:Http){
    
    }
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }

    resetPassword(LoginValues:LoginValues): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._resetPwdUrl,LoginValues, options).map(this.extractData);
    }

        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}