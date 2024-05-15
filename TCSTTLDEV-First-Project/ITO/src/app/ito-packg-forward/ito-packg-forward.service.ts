import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoPackgForwardService {
    private _getPackageCache: string = "http://10.0.0.4:8082/ITO/quot/getPackageCache";
    private _getPackageData: string = "http://10.0.0.4:8082/ITO/quot/getPackageData";
    private _savePackageData: string = "http://10.0.0.4:8082/ITO/quot/savePackageData";

    constructor(private _http: Http) {
    }

    getPackageCache(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getPackageCache, ' ', options).map(this.extractData);
    }
    getPackageData(Resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getPackageData, Resp, options).map(this.extractData);
    }
    savePackageData(Resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._savePackageData, Resp, options).map(this.extractData);
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}