import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoSapresService {
    private _getSparesCost: string = "http://10.0.0.4:8082/ITO/quot/getSparesCost";
    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/quotCache";
    private _saveSparesCost: string = "http://10.0.0.4:8082/ITO/quot/saveSparesCost";

    private qNum = new Subject<any>();
    constructor(private _http: Http) {
    }
    getSparesCost(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getSparesCost, res, options).map(this.extractData);
    }
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUrl, ' ', options).map(this.extractData);
    }
    saveSparesCost(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveSparesCost, resp, options).map(this.extractData);
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}