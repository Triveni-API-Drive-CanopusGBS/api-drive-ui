import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';


@Injectable()
export class ITOMQuotRangePageService{
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/getHomePageData";
    private _getUsersList:string="http://10.0.0.4:8082/ITO/quot/fetchCacheData";
    dataSource:Array<any>=[];
    constructor(private _http:Http){
    }
    getQuotationList(userDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,userDetails, options).map(this.extractData);
    }
    getUsersList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getUsersList,'', options).map(this.extractData);
    }
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}