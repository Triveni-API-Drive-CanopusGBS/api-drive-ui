import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoUpdDboEleSpladdonService {

    private _getDBOElectricalData: string = "http://10.0.0.4:8082/ITO/DBO/getDBOElectricalData";
    private _getDBOEleUpdatePriceData: string = "http://10.0.0.4:8082/ITO/update/getDBOEleUpdatePriceData";
    private _getDBOEleUpdateColData: string = "http://10.0.0.4:8082/ITO/update/getDBOEleUpdateColData1";
    private _getFrameAndUserData: string = "http://10.0.0.4:8082/ITO/quot/getFrameAndUserData";
    private _updateStatus: string = "http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
    private _saveUpdatedNoOfVehicles: string = "http://10.0.0.4:8082/ITO/update/saveUpdatedNoOfVehicles";
    private _getDBOEleUpdColumsData: string = "http://10.0.0.4:8082/ITO/update/getDBOEleUpdateColData";
    private _createDboEleUpdateRequestCol: string = "http://10.0.0.4:8082/ITO/update/createDboEleUpdateRequestCol";
    private _createDboEleUpdateRequestPriceSplAddOn: string = "http://10.0.0.4:8082/ITO/update/createDboEleUpdateRequestPriceSplAddOn";
    private _createDboEleUpdateRequestPriceAddOn: string = "http://10.0.0.4:8082/ITO/update/createDboEleUpdateRequestPriceAddOn";
    private _createDboEleUpdateRequestPriceAddInstr: string = "http://10.0.0.4:8082/ITO/update/createDboEleUpdateRequestPriceAddInstr";

    constructor(private _http: Http) {
    }

    getDBOElectricalData(val): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDBOElectricalData, val, options).map(this.extractData);
    }

    getDBOEleUpdatePriceData(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDBOEleUpdatePriceData, res, options).map(this.extractData);
    }

    getDBOEleUpdColumsData(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDBOEleUpdColumsData, res, options).map(this.extractData);
    }

    getDBOEleUpdateColData(itemId, panelType): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDBOEleUpdateColData, { itemId, panelType }, options).map(this.extractData);
    }

    createDboEleUpdateRequestPriceAddOn(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createDboEleUpdateRequestPriceAddOn, resp, options).map(this.extractData);
    }

    createDboEleUpdateRequestPriceAddInstr(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createDboEleUpdateRequestPriceAddInstr, resp, options).map(this.extractData);
    }
    createDboEleUpdateRequestPriceSplAddOn(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createDboEleUpdateRequestPriceSplAddOn, resp, options).map(this.extractData);
    }

    createDboEleUpdateRequestCol(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createDboEleUpdateRequestCol, resp, options).map(this.extractData);
    }

    updateStatus(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateStatus, resp, options).map(this.extractData);
    }
    saveUpdatedNoOfVehicles(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveUpdatedNoOfVehicles, resp, options).map(this.extractData);
    }
    getFrameAndUserData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getFrameAndUserData, ' ', options).map(this.extractData);
    }


    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}