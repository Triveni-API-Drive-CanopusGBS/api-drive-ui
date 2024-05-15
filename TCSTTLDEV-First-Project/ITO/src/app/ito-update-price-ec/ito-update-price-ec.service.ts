import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http ,Response } from '@angular/http';


@Injectable()
export class ITOUpdatePriceEcService{
   
    private _getAdminForm: string = "http://10.0.0.4:8082/ITO/admin/getAdminForm";
    private _quotUsersUrl:string="http://10.0.0.4:8082/ITO/quot/fetchCacheData";
    private _getECUpdatePriceData: string = "http://10.0.0.4:8082/ITO/update/getECUpdatePriceData";
    private _createECPriceUpdateRequest: string = "http://10.0.0.4:8082/ITO/update/createECPriceUpdateRequest";
    private _updateStatus: string = "http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
    private _saveECUpdatedPrice: string = "http://10.0.0.4:8082/ITO/update/saveECUpdatedPrice";
    private _getErrectionCommCache:string="http://10.0.0.4:8082/ITO/quot/getErrectionCommCache";
    private _getAdminPercentEc: string = "http://10.0.0.4:8082/ITO/admin/getAdminPercentEc";

    ecFramePriceList:Array<any>;
    typesOfTurbine:Array<any>;
    turbineCategoryList:Array<any>;
    quotform:any;

    constructor(private _http:Http){    
    }
   
    getUsersList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUsersUrl,' ', options).map(this.extractData);
    }

    getAdminForm(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getAdminForm,' ', options).map(this.extractData);
    }

    createECPriceUpdateRequest(quotForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._createECPriceUpdateRequest,quotForm, options).map(this.extractData);
    }

    getECUpdatePriceData(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getECUpdatePriceData,res, options).map(this.extractData);
    }

    updateStatus(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateStatus, resp, options).map(this.extractData);
    }
    
    saveECUpdatedPrice(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveECUpdatedPrice, resp, options).map(this.extractData);
    }

    getErrectionCommCache(quoId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getErrectionCommCache, quoId, options).map(this.extractData);
    }

    getAdminPercentEc(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAdminPercentEc, resp, options).map(this.extractData);
    }

        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}