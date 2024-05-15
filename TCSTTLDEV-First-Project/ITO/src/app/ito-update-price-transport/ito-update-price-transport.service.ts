import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoUpdateTransportationService {
    private _getTransportationCache: string = "http://10.0.0.4:8082/ITO/quot/getTransportationCache";
    private _updatePriceTransport: string = "http://10.0.0.4:8082/ITO/update/updateTransportPrice";
    private _updateStatus: string = "http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
    private _getUpdatePriceReqGrid: string = "http://10.0.0.4:8082/ITO/update/getUpdatePriceReqGrid";
    private _saveTransportPrice: string = "http://10.0.0.4:8082/ITO/update/saveTransportPrice";
    private _updatePriceTransportExport: string = "http://10.0.0.4:8082/ITO/update/updateTransportPriceExport";
    private _getAdminPercentTrnsDm: string = "http://10.0.0.4:8082/ITO/admin/getAdminPercentTrnsDm";
    private _getAdminForm:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";


    constructor(private _http: Http) {
    }
    getAdminForm(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getAdminForm,' ', options).map(this.extractData);
    }
    getAdminPercentTrnsDm(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAdminPercentTrnsDm, resp, options).map(this.extractData);
    }
    getTransportationCache(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getTransportationCache, ' ', options).map(this.extractData);
    }
    updatePriceTransport(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updatePriceTransport, resp, options).map(this.extractData);
    }
  
    updateStatus(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateStatus, resp, options).map(this.extractData);
    }
    getUpdatePriceReqGrid(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdatePriceReqGrid, resp, options).map(this.extractData);
    }
    saveTransportPrice(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveTransportPrice, resp, options).map(this.extractData);
    }
    updatePriceTransportExport(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updatePriceTransportExport, resp, options).map(this.extractData);
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}