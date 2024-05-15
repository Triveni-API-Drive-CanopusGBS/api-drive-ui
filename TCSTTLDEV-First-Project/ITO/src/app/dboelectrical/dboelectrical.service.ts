import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http, Response } from '@angular/http';


@Injectable()
export class DBOElectricalComponentService {

    selected: boolean;

    private _getEleItems: string = "http://10.0.0.4:8082/ITO/DBO/getEleItems";
    private _getElePanels: string = "http://10.0.0.4:8082/ITO/DBO/getElePanels";
    private _getEleTechPrice: string = "http://10.0.0.4:8082/ITO/DBO/getEleTechPrice";
    private _getDboElectricalAddInstrPrice: string = "http://10.0.0.4:8082/ITO/DBO/getDboElectricalAddInstrPrice";
    private _getDboEleAddOn: string = "http://10.0.0.4:8082/ITO/DBO/getDboEleAddOn";
    private _getDboElectricalPriceAddOn: string = "http://10.0.0.4:8082/ITO/DBO/getDboElectricalPriceAddOn";
    private _getDboEleTotal: string = "http://10.0.0.4:8082/ITO/DBO/getDboEleTotal";
    private _getDboEditData: string = "http://10.0.0.4:8082/ITO/DBO/getDboEditData";
    private _refreshEle: string = "http://10.0.0.4:8082/ITO/DBO/getEleRefreshPanel";
    private _saveEleItem: string = "http://10.0.0.4:8082/ITO/DBO/saveEleItem";
    private _eleFilter: string = "http://10.0.0.4:8082/ITO/DBO/getEleSpecialFilter";
    private _saveEleFilter: string = "http://10.0.0.4:8082/ITO/DBO/saveEleSpecialFilter";
    
    private _getEleInstr: string = "http://10.0.0.4:8082/ITO/DBO/getEleInstr";
    private _saveAdditionalInstrumentation: string = "http://10.0.0.4:8082/ITO/DBO/saveAdditionalInstrumentation";
    private _getEleVms: string = "http://10.0.0.4:8082/ITO/DBO/getEleVms";
    private _saveEleVms: string = "http://10.0.0.4:8082/ITO/DBO/saveEleVms";
    private _getVmsCache: string = "http://10.0.0.4:8082/ITO/DBO/getVmsCache";
    

    constructor(private _http: Http) {
    }
  //  saveAdditionalInstrumentation
  saveEleVms(dboForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveEleVms, dboForm, options).map(this.extractData);
    }
    getVmsCache(dboForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getVmsCache, dboForm, options).map(this.extractData);
    }
    saveAdditionalInstrumentation(dboForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveAdditionalInstrumentation, dboForm, options).map(this.extractData);
    }
    saveEleItem(dboForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveEleItem, dboForm, options).map(this.extractData);
    }
    getEleVms(dboForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getEleVms, dboForm, options).map(this.extractData);
    }
    getEleInstr(dboForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getEleInstr, dboForm, options).map(this.extractData);
    }

    getDboEleTotal(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboEleTotal, quotId, options).map(this.extractData);
    }

    getDboEditData(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboEditData, quotId, options).map(this.extractData);
    }
    refreshEle(dboForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._refreshEle, dboForm, options).map(this.extractData);
    }

    getEleItems(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getEleItems, res, options).map(this.extractData);

    }

    getElePanels(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getElePanels, res, options).map(this.extractData);
    }
    getEleTechPrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getEleTechPrice, res, options).map(this.extractData);
    }
    getDboElectricalAddInstrPrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboElectricalAddInstrPrice, res, options).map(this.extractData);
    }
    getDboEleAddOn(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboEleAddOn, res, options).map(this.extractData);
    }
    getDboElectricalPriceAddOn(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboElectricalPriceAddOn, res, options).map(this.extractData);
    }
    eleFilter(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._eleFilter, res, options).map(this.extractData);
    }
    saveEleFilter(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveEleFilter, res, options).map(this.extractData);
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}