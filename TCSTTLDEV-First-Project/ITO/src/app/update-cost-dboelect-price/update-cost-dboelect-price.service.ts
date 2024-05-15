import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoUpdateCostDBOElecPrice {
    private _saveUpdatedPackagePrice: string = "http://10.0.0.4:8082/ITO/update/saveUpdatedPackagePrice";

    private _getDBOElectricalData: string = "http://10.0.0.4:8082/ITO/DBO/getDBOElectricalData";
    private _getDBOEleUpdatePriceData: string = "http://10.0.0.4:8082/ITO/update/getDBOEleUpdatePriceData";
    private _getDBOEleUpdateColData: string = "http://10.0.0.4:8082/ITO/update/getDBOEleUpdateColData1";
    private _getFrameAndUserData: string = "http://10.0.0.4:8082/ITO/quot/getFrameAndUserData";
    private _createDboEleUpdateRequestPrice: string = "http://10.0.0.4:8082/ITO/update/createDboEleUpdateRequestPrice";
    private _updateStatus: string = "http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
    private _saveUpdatedNoOfVehicles: string = "http://10.0.0.4:8082/ITO/update/saveUpdatedNoOfVehicles";
    private _getDBOEleUpdColumsData: string = "http://10.0.0.4:8082/ITO/update/getDBOEleUpdateColData";
    private _createDboEleUpdateRequestCol: string = "http://10.0.0.4:8082/ITO/update/createDboEleUpdateRequestCol";
    private _getDboFormData: string = "http://10.0.0.4:8082/ITO/DBO/getDboFormData";

    private _updateCreateElePrice:string = "http://10.0.0.4:8082/ITO/update/getDBOMechUpdatePriceData";
    private _eletricalDetails:string = "http://10.0.0.4:8082/ITO/update/getDBOEleUpdateColData";

    private _getMake:string = "http://10.0.0.4:8082/ITO/update/getDBOMechUpdateColData";
    private _updateCreateEleAddOnCost:string = "http://10.0.0.4:8082/ITO/update/createDboMechUpdateRequestCol";
    private _updateCreateEleColVal:string = "http://10.0.0.4:8082/ITO/update/createDboMechUpdateRequestPrice";

    
    private  _updateCreateEleVms:string = "http://10.0.0.4:8082/ITO/update/createDboEleUpdateRequestPriceAddOn";
    private  _updateCreateEleInstr:string = "http://10.0.0.4:8082/ITO/update/createUpdateAddon";
    private  _updateCreateEleInstr1:string = "http://10.0.0.4:8082/ITO/update/createUpdateAddon1";
    private  _updateCreateEleInstr3:string = "http://10.0.0.4:8082/ITO/update/createUpdateProbes";
    private  _getAdminPercentEle:string = "http://10.0.0.4:8082/ITO/admin/getAdminPercentEle";



    

    constructor(private _http: Http) {
    }
    getDboFormData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboFormData, ' ', options).map(this.extractData);
    }
    getAdminPercentEle(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAdminPercentEle,res, options).map(this.extractData);
    }
    updateCreateEleVms(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateCreateEleVms,res, options).map(this.extractData);
    }
    updateCreateEleInstr(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateCreateEleInstr,res, options).map(this.extractData);
    }
    updateCreateEleInstr1(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateCreateEleInstr1,res, options).map(this.extractData);
    }
    updateCreateEleInstr3(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateCreateEleInstr3,res, options).map(this.extractData);
    }
    getMake(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getMake,res, options).map(this.extractData);
    }
    updateCreateEleColVal(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateCreateEleColVal,res, options).map(this.extractData);
    }
    updateCreateEleAddOnCost(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateCreateEleAddOnCost,res, options).map(this.extractData);
    }
    eletricalDetails(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._eletricalDetails, res , options).map(this.extractData);
    }
   
    updateCreateElePrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateCreateElePrice, res , options).map(this.extractData);
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

    createDboEleUpdateRequestPrice(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createDboEleUpdateRequestPrice, resp, options).map(this.extractData);
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
    saveUpdatedPackagePrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveUpdatedPackagePrice, res , options).map(this.extractData);
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}