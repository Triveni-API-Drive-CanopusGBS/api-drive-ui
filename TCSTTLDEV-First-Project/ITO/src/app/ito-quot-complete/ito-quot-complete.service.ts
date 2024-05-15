import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';


@Injectable()
export class ItoQuotCompleteService {
    private _quotWorkFlow: string = "http://10.0.0.4:8082/ITO/quot/quotWorkFlow";
    private _quotStatusComplete: string = "http://10.0.0.4:8082/ITO/quot/quotStatusComplete";
    private _getProjectCost: string = "http://10.0.0.4:8082/ITO/quot/getProjectCost";
    private _saveProjectCost: string = "http://10.0.0.4:8082/ITO/quot/saveProjectCost";
    private _getQuotFormData: string = "http://10.0.0.4:8082/ITO/quot/getQuotFormData";
    private _getScopeOfSupStatus: string = "http://10.0.0.4:8082/ITO/quot/getScopeOfSupStatus";
    

    constructor(private _http: Http) {
    }
   
    getQuotFormData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuotFormData, '', options).map(this.extractData);
    }
    getScopeOfSupStatus(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getScopeOfSupStatus, quotId, options).map(this.extractData);
    }
    quotWorkFlow(saveBasicDet): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotWorkFlow, saveBasicDet, options).map(this.extractData);
    }
    quotStatusComplete(saveBasicDet): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotStatusComplete, saveBasicDet, options).map(this.extractData);
    }
    getProjectCost(quotForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getProjectCost, quotForm, options).map(this.extractData);
    }
    saveProjectCost(saveBasicDet): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveProjectCost, saveBasicDet, options).map(this.extractData);
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}