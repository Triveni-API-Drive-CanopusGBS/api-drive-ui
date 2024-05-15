import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http, Response } from '@angular/http';


@Injectable()
export class ITOGeneralInputsService {
    private _getGeneralInput: string = "http://10.0.0.4:8082/ITO/DBO/getGeneralInput";
    private _saveGeneralInput: string = "http://10.0.0.4:8082/ITO/DBO/saveGeneralInput";
    private _getDboFormData: string = "http://10.0.0.4:8082/ITO/DBO/getDboFormData";
    private _getStdOffer: string = "http://10.0.0.4:8082/ITO/admin/getStdOffer";
    private _getEleFilter: string = "http://10.0.0.4:8082/ITO/DBO/getEleFilter";
    private _saveEleFilter: string = "http://10.0.0.4:8082/ITO/DBO/saveEleFilter";
    private _getUpdateGeneralInput: string = "http://10.0.0.4:8082/ITO/DBO/getUpdateGeneralInput";
    private _changeGeneralInput: string = "http://10.0.0.4:8082/ITO/DBO/getAvr";
    private _getGoverner: string = "http://10.0.0.4:8082/ITO/DBO/getGoverner";
    
    enabelMechAux: boolean = true;
    constructor(private _http: Http) {
    }

    getGeneralInput(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getGeneralInput, quotId , options).map(this.extractData);
    }
    saveGeneralInput(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveGeneralInput, res, options).map(this.extractData);
    }

    getDboFormData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboFormData, ' ', options).map(this.extractData);
    }

    getStdOffer(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getStdOffer, resp , options).map(this.extractData);
    }
    getEleFilter(dboForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getEleFilter, dboForm, options).map(this.extractData);
    }

    saveEleFilter(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveEleFilter, res, options).map(this.extractData);
    }

    changeGeneralInput(respp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._changeGeneralInput,respp, options).map(this.extractData);
    }

    getUpdateGeneralInput(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateGeneralInput, res, options).map(this.extractData);
    }
    getGoverner(ress): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getGoverner, ress, options).map(this.extractData);
    }


    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}