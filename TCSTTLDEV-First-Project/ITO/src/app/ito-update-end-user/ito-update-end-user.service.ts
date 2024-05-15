import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';
import {consultantDetails} from '../ito-create-consultant/ito-create-consultant';
import { ITOEndUserHomeService } from '../ito-end-user-home/ito-end-user-home.service';
import {endUserDetials} from '../ito-create-end-user/ito-create-end-user';


@Injectable()
export class ITOupdateEndUserHomeService{
    private _updateEndUserUrl:string="http://10.0.0.4:8082/ITO/custprofile/updateEnduser";
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotHome";
    constructor(private _http:Http ,private _ITOEndUserHomeService:ITOEndUserHomeService){
    
    }

    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }

   updateEndUser(consultantDetails:consultantDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._updateEndUserUrl,consultantDetails, options).map(this.extractData);
    }
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}