import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ITOAddOnComponentsService {
    dispPrevComments: boolean = false;
    oldComms: any;
    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/quotCache";
    private _saveScopeOfSupplyUrl: string = "http://10.0.0.4:8082/ITO/quot/createScopeOfSupply";
    private _getAddOnExcelPriceUrl: string = "http://10.0.0.4:8082/ITO/quot/getAddOnExcelPrice";
    private _saveAddOnPrice: string = "http://10.0.0.4:8082/ITO/quot/saveAddOnPrice";
    private _getQuotRemarks: string = "http://10.0.0.4:8082/ITO/quot/getQuotRemarks";
    private _saveRemarks: string = "http://10.0.0.4:8082/ITO/quot/saveRemarks;"
    private finalAddOnCost = new Subject<any>();

    constructor(private _http: Http) {
    }
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUrl, ' ', options).map(this.extractData);
    }
    saveScopeOfSupply(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveScopeOfSupplyUrl, res, options).map(this.extractData);
    }
    getAddOnExcelPrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAddOnExcelPriceUrl, res, options).map(this.extractData);
    }
    saveAddOnPrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveAddOnPrice, res, options).map(this.extractData);
    }
    sendfinalAddOnCost(finalAddOnCost, total) {
        this.finalAddOnCost.next({ finalAddOnCost, total });
    }
    getQuotRemarks(saveBasicDet): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuotRemarks, saveBasicDet, options).map(this.extractData);
    }
    saveRemarks(saveBasicDet): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveRemarks, saveBasicDet, options).map(this.extractData);
    }
    getfinalAddOnCost(): Observable<any> {
        return this.finalAddOnCost.asObservable();
    }
    showPrevComments() {
        this.dispPrevComments = true;
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}