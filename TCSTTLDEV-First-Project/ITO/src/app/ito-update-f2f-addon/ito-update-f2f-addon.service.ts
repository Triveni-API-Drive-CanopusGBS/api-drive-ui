import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoUpdateCostF2FAddOnsService {

    private _getAddOnExcelPriceUrl: string = "http://10.0.0.4:8082/ITO/update/updateGetAddOn";
    private _getFrameAndUserData: string = "http://10.0.0.4:8082/ITO/quot/getFrameAndUserData";
    private _createUpdateAddon: string = "http://10.0.0.4:8082/ITO/update/createUpdateAddon";
    private _updateStatus: string = "http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
    private _saveUpdatedNoOfVehicles: string = "http://10.0.0.4:8082/ITO/update/saveUpdatedNoOfVehicles";
    //New URL to get data fro F2f, Mech, Mech Aux
    private _updateGetAddOn:string="http://10.0.0.4:8082/ITO/update/updateGetAddOn";
    //to save ne URL
    private _getUpdateCreateF2fPrice:string="http://10.0.0.4:8082/ITO/DBO/getUpdateCreateF2fPrice";  
    private _getUpdateCreateF2fColVal:string="http://10.0.0.4:8082/ITO/DBO/getUpdateCreateF2fColVal";     
    private _getUpdateCreateF2fFrameSpecData:string="http://10.0.0.4:8082/ITO/DBO/getUpdateCreateF2fFrameSpecData";     
    //URL to get buld update of F2f
    private _getAdminPercentF2F:string="http://10.0.0.4:8082/ITO/admin/getAdminPercentF2F";     
    
   
    constructor(private _http: Http) {
    }

    getAddOnExcelPrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAddOnExcelPriceUrl, res, options).map(this.extractData);
    }

   
    createUpdateAddon(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createUpdateAddon, resp, options).map(this.extractData);
    }

    getAdminPercentF2F(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAdminPercentF2F, adminForm , options).map(this.extractData);
    }
   
    updateStatus(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateStatus, resp, options).map(this.extractData);
    }
    saveData(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveUpdatedNoOfVehicles, resp, options).map(this.extractData);
    }
    getFrameAndUserData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getFrameAndUserData, ' ', options).map(this.extractData);
    }
    updateGetAddOn(respon): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateGetAddOn, respon , options).map(this.extractData);
    }

    getUpdateCreateF2fPrice(respoo): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateCreateF2fPrice, respoo , options).map(this.extractData);
    }

    getUpdateCreateF2fColVal(resppn): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateCreateF2fColVal, resppn , options).map(this.extractData);
    }

    getUpdateCreateF2fFrameSpecData(resppn): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateCreateF2fFrameSpecData, resppn , options).map(this.extractData);
    }
    

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}