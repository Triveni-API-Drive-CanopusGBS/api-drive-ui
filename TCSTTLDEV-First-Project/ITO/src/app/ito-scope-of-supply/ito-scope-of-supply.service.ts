import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ITOScopeOfSupplyService {
    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/quotCache";
    private _saveScopeOfSupplyUrl: string = "http://10.0.0.4:8082/ITO/quot/createScopeOfSupply";
    private _createScopeOfSupplyNew: string = "http://10.0.0.4:8082/ITO/quot/createScopeOfSupplyNew";
    private qNum = new Subject<any>();
    private scopes = new Subject<any>();
    sampleScope: any; //to store selected scope of supply list; 

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

    createScopeOfSupplyNew(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createScopeOfSupplyNew, quotId, options).map(this.extractData);
    }
    checkScopes(selScopes) {
        this.scopes.next(selScopes);
    }
    sendMessage(qnum) {
        this.qNum.next({ qnum });
    }
    getMessage(): Observable<any> {
        return this.qNum.asObservable();
    }
    getCheckScopes(): Observable<any> {
        return this.scopes.asObservable();
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}