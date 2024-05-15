import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';


@Injectable()
export class ITOMyQuotPageService {
    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/getHomePageData";
    private _assignToOthers: string = "http://10.0.0.4:8082/ITO/quot/assignToOthers";
    private _getTurbineClone: string = "http://10.0.0.4:8082/ITO/quot/getTurbineClone";
    private _getQuotRevNo: string = "http://10.0.0.4:8082/ITO/quot/getQuotRevNo ";
    private _getQuotRevData: string = "http://10.0.0.4:8082/ITO/quot/getQuotRevData ";
    private _fetchCacheData: string = "http://10.0.0.4:8082/ITO/quot/fetchCacheData ";
    private _saveAsN: string = "http://10.0.0.4:8082/ITO/quot/saveAs ";
    dataSource: Array<any> = [];
    selectedQuot: any;
    constructor(private _http: Http) {
    }
    fetchCacheData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._fetchCacheData, '', options).map(this.extractData);
    }
    getQuotationList(userDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUrl, userDetails, options).map(this.extractData);
    }
    assignToOthers(quotId, quotNumber, assignedToId, modifyById ,userRoleId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._assignToOthers, { quotId, quotNumber, assignedToId, modifyById,userRoleId }, options).map(this.extractData);
    }
    saveAs(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getTurbineClone, res, options).map(this.extractData);
    }
    getQuotRevNo(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuotRevNo, quotId, options).map(this.extractData);
    }
 
    getQuotRevData(saveBasic): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuotRevData, saveBasic, options).map(this.extractData);
    }
    saveAsN(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveAsN, res, options).map(this.extractData);
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}