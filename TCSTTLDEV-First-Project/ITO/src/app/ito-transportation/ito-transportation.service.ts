import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoTransportationService {
    private _getQuotTransCache: string = "http://10.0.0.4:8082/ITO/quot/getQuotTransCache";
    private _getTransportDataBasedOnFrame: string = "http://10.0.0.4:8082/ITO/quot/getTransportDataBasedOnFrame";
    private _getTransportPrice: string = "http://10.0.0.4:8082/ITO/quot/getTransportPrice";
    private _saveTransportationData: string = "http://10.0.0.4:8082/ITO/quot/saveTransportationData";
    private _fetchCacheData: string = "http://10.0.0.4:8082/ITO/quot/quotCache";
    
    constructor(private _http: Http) {
    }

    getQuotTransCache(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuotTransCache, ' ', options).map(this.extractData);
    }
    fetchCacheData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._fetchCacheData, ' ', options).map(this.extractData);
    }
    getTransportPrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getTransportPrice, res , options).map(this.extractData);
    }
    getTransportDataBasedOnFrame(Resp):Observable<any>{
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getTransportDataBasedOnFrame, Resp, options).map(this.extractData);
    }
    saveTransportationData(saveBasicDet):Observable<any>{
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveTransportationData, saveBasicDet, options).map(this.extractData);
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}