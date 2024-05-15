import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoUpdateTurbineCostService {
    private _dBOMechanicalCache: string = "http://10.0.0.4:8082/ITO/DBO/DBOMechanicalCache";
    private _quotUsers: string = "http://10.0.0.4:8082/ITO/quot/quotHome";
    private _createSubContract: string =  "http://10.0.0.4:8082/ITO/update/createSubContracting";
    private _createOverhead : string =  "http://10.0.0.4:8082/ITO/update/createOverheadSheet";
    private _createTurbineInst: string = "http://10.0.0.4:8082/ITO/update/createTurbineInstruments"
     // URL for fetching saved data
     private _savedUpdatePriceData:string="http://10.0.0.4:8082/ITO/update/getSavedUpdatedPriceData";
     //URL for update status & submit saved data
     private _updateStatus:string="http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
     //URL for final save (Approver)
     private _updateFinalSave:string="http://10.0.0.4:8082/ITO/update/saveUpdatedNoOfVehicles";  
private _createTurbInstrUpdateRequest:string="http://10.0.0.4:8082/ITO/update/createTurbInstrUpdateRequest";
private _createUpdateF2fShopConv:string="http://10.0.0.4:8082/ITO/DBO/createUpdateF2fShopConv";

    constructor(private _http: Http) {
    }

    dBOMechanicalCache(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._dBOMechanicalCache, ' ', options).map(this.extractData);
    }
    quotUsers(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUsers, ' ', options).map(this.extractData);
    }
    createSubContract(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createSubContract, res , options).map(this.extractData);
    }
    createOverhead(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createOverhead, res , options).map(this.extractData);
    }
    createTurbineInst(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createTurbineInst, res , options).map(this.extractData);
    }
    savedUpdatePriceData(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._savedUpdatePriceData, res , options).map(this.extractData);
    }
    updateStatus(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateStatus, res , options).map(this.extractData);
    }
    updateFinalSave(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateFinalSave, res , options).map(this.extractData);
    }
    createUpdateF2fShopConv(quotForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createUpdateF2fShopConv, quotForm , options).map(this.extractData);
    }
    createTurbInstrUpdateRequest(quotForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createTurbInstrUpdateRequest, quotForm , options).map(this.extractData);
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}