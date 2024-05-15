import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http, Response } from '@angular/http';


@Injectable()
export class DBOMechanicalComponentService {
    //get dbo components
    private _getMechItems: string = "http://10.0.0.4:8082/ITO/DBO/getMechItems"; 
    //getdbo componentpanel elements
    private _getMechPanels: string = "http://10.0.0.4:8082/ITO/DBO/getMechPanels";
    //get dbo component price
    private _getMechTechPrice: string = "http://10.0.0.4:8082/ITO/DBO/getMechTechPrice";
    //save data
    private _saveMechItem: string = "http://10.0.0.4:8082/ITO/DBO/saveMechItem";
    //save data for subItem
    private _saveMechSubItem: string = "http://10.0.0.4:8082/ITO/DBO/saveMechSubItem";
    // remove item inside mechanical panel
    private _removeDboMechItem: string = "http://10.0.0.4:8082/ITO/DBO/removeDboMechItem";
    //get taotal
    private _getDboMechTotal: string = "http://10.0.0.4:8082/ITO/DBO/getDboMechTotal";
    
    addedClassList: Array<any> = [];

    constructor(private _http: Http) {
    }
    getMechItems(turbineType): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getMechItems, turbineType, options).map(this.extractData);
    }

    getDboMechTotal(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboMechTotal, quotId, options).map(this.extractData);
    }

    removeDboMechItem(quotId,itemId,subItemId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._removeDboMechItem, { quotId,itemId,subItemId }, options).map(this.extractData);
    }

    getMechPanels(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getMechPanels, res, options).map(this.extractData);
    }

    getMechTechPrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getMechTechPrice, res, options).map(this.extractData);
    }

    saveMechItem(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveMechItem, res, options).map(this.extractData);
    }
    saveMechSubItem(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveMechSubItem, res, options).map(this.extractData);
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}