import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class SupplyChainServiceDetails {

    //URL to get the Details from DB 
    private _UBODetails: string = "http://10.0.0.4:8082/ITO/update/getF2FUBOMast";
    //URL to get cost from admin link
    private _adminGetDetails: string = "http://10.0.0.4:8082/ITO/DBO/DBOMechanicalCache";

    //URL for final save (Approver)
    private _saveF2FUboData: string = "http://10.0.0.4:8082/ITO/quot/saveF2FUboData";
    //URL to get quot form data
    private _getQuotFormData: string = "http://10.0.0.4:8082/ITO/quot/getQuotFormData";
    //URL to get F2F UBO data
    private _getF2FUboData: string = "http://10.0.0.4:8082/ITO/quot/getF2FUboData";
    //URL to get Scope of supply status
  private _getscopeOfSupplyStatus: string = "http://10.0.0.4:8082/ITO/quot/scopeOfSupplyStatus";

    constructor(private _http: Http) {
    }
    //Method to get Form Data
    getQuotFormData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuotFormData, ' ', options).map(this.extractData);
    }
//Method to get Frame power price details
    framePwrPriceDetails(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._UBODetails, resp, options).map(this.extractData);
    }
//Method to get Scope of supply status
    scopeOfSupplyStatus(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getscopeOfSupplyStatus, resp, options).map(this.extractData);
    }
//method to get F2F UBO data
    getF2FUboData(saveBasicDet): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2FUboData, saveBasicDet, options).map(this.extractData);
    }

    //method to get admin form
    getFrameForm(): Observable<any> {

        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._adminGetDetails, ' ', options).map(this.extractData);
    }
    //method to save F2F UBO Data
    saveF2FUboData(saveBasicDet): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveF2FUboData, saveBasicDet, options).map(this.extractData);
    }


    //method to convert the response to json
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}