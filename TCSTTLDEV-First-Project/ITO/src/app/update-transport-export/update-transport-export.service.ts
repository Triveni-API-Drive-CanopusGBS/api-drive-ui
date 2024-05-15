import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class updateTransportExportService {
    //URL to get the Details from DB 
    private _getVehicleCache: string = "http://10.0.0.4:8082/ITO/update/getTransportUpdatedPriceListExport";
    //URL to get the updated admin form
    private _AdminDetails:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";
    //URL to get the transportation Cache
    private _getTransportationCache: string = "http://10.0.0.4:8082/ITO/quot/getTransportationCache";
    // URL to update  vehicvle transport
    private _updatevehicleTransport:string="http://10.0.0.4:8082/ITO/update/updateTransportPriceExport";
    //URL to update status
    private _updateStatus:string="http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
    //URL to save transport vehicle
    private _saveTransportVehicle:string="http://10.0.0.4:8082/ITO/update/saveUpdatedNoOfVehicles";

    private _getAdminPercentTrnsDm: string = "http://10.0.0.4:8082/ITO/admin/getAdminPercentTrnsEx";
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
//method call to get transportation cache
    getTransportationCache(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getTransportationCache, ' ', options).map(this.extractData);
    }


   // method call to get the transportation details from DB
    getVehicleCache(res): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getVehicleCache, res, options).map(this.extractData);
    }
    //method to get empty admin form to fetch transportation details
    addOrEditForm(): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminDetails,' ', options).map(this.extractData);
    }
    //method to update price transport
    updatePriceTransport(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updatevehicleTransport, resp, options).map(this.extractData);
    }
    //method to update status
    updateStatus(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateStatus, resp, options).map(this.extractData);
    }
//method to save transport price
    saveTransportPrice(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveTransportVehicle, resp, options).map(this.extractData);
    }
    //method to convert the response to json
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}