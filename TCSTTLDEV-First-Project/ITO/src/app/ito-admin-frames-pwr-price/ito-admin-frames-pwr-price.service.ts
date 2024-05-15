import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoFramesPwrPriceService {

    //URL to get the Details from DB 
    private _UBODetails: string = "http://10.0.0.4:8082/ITO/update/getF2FUBOMast";

    //URL for create/save update price of UBO Mechanical
    private _createUBOPriceUpdateRequest: string = "http://10.0.0.4:8082/ITO/update/createUBOSheet";

    //URL for update status & submit saved data
    private _updateStatus: string = "http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";

    //URL for final save (Approver)
    private _UBOUpdateFinalSave: string = "http://10.0.0.4:8082/ITO/update/saveECUpdatedPrice";

    private _quotFrameAndUsersUrl: string = "http://10.0.0.4:8082/ITO/quot/getFrameAndUserData";

    private _newFramesUrl: string = "http://10.0.0.4:8082/ITO/update/getNewFramesForUBO";

    constructor(private _http: Http) {
    }
    getFrameAndUserData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotFrameAndUsersUrl, ' ', options).map(this.extractData);
    }

    getNewFramesForUBO(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._newFramesUrl, ' ', options).map(this.extractData);
    }

    framePwrPriceDetails(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._UBODetails, resp, options).map(this.extractData);
    }
    createUBOPriceUpdateRequest(tempRes): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createUBOPriceUpdateRequest, tempRes, options).map(this.extractData);
    }

    updateStatus(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateStatus, resp, options).map(this.extractData);
    }

    saveUBOUpdatedPrice(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._UBOUpdateFinalSave, resp, options).map(this.extractData);
    }


    //method to convert the response to json
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}