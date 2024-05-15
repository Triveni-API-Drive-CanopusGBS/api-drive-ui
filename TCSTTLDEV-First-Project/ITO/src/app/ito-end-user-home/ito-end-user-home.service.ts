import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';
import {endUserDetials} from '../ito-create-end-user/ito-create-end-user';


@Injectable()
export class ITOEndUserHomeService{
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotHome";
    dataSource:Array<any>=[];
    selectedEndUser:endUserDetials;
    constructor(private _http:Http){
        this.selectedEndUser=new endUserDetials('');
    }

    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}