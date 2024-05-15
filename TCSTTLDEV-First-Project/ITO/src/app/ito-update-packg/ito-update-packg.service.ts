import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoUpdatePackgService {
    private _getPackageWithPriceList: string = "http://10.0.0.4:8082/ITO/update/getPackageWithPriceList";
    private _updatePackagePrice: string = "http://10.0.0.4:8082/ITO/update/updatePackagePrice";
    private _updateStatusAndSubmit: string = "http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
    private _saveUpdatedPackagePrice: string = "http://10.0.0.4:8082/ITO/update/saveUpdatedPackagePrice";
    private _quotUsers:string="http://10.0.0.4:8082/ITO/quot/quotHome";
    private _getAdminPercentPkg: string = "http://10.0.0.4:8082/ITO/admin/getAdminPercentPkg";

    constructor(private _http: Http) {
    }

    getPackageWithPriceList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getPackageWithPriceList, ' ', options).map(this.extractData);
    }
    quotUsers(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUsers, ' ', options).map(this.extractData);
    }
    updatePackagePrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updatePackagePrice, res , options).map(this.extractData);
    }
    updateStatusAndSubmit(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateStatusAndSubmit, res , options).map(this.extractData);
    }
    saveUpdatedPackagePrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveUpdatedPackagePrice, res , options).map(this.extractData);
    }
    getAdminPercentPkg(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAdminPercentPkg, resp, options).map(this.extractData);
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
   
}