import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { consultantDetails } from '../ito-create-consultant/ito-create-consultant';


@Injectable()
export class ItoAdminVarientCodeService {
    private _getQuestionsPage: string = "http://10.0.0.4:8082/ITO/quot/getQuesPage";
    // private _getQuestionsPage: string = "http://10.0.0.4:8082/ITO/admin/getQuestionsPage";
    private _getCustomers: string = "http://10.0.0.4:8082/ITO/quot/quotHome";
    private _getquotCache: string = "http://10.0.0.4:8082/ITO/quot/quotCache";
    private _addOrEditVariantCode: string = "http://10.0.0.4:8082/ITO/admin/addOrEditVariantCode";
    private _quotUrl:string="http://10.0.0.4:8082/ITO/admin/getAdminCacheWithAIList";

    selectedConsult: consultantDetails;

    constructor(private _http: Http) {
        this.selectedConsult = new consultantDetails('');
    }
    getVariant(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }
    
    getquotCache(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getquotCache, ' ', options).map(this.extractData);
    }
    getCustomers(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getCustomers, ' ', options).map(this.extractData);
    }
    getQuestionsPage(framePowerId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuestionsPage, framePowerId, options).map(this.extractData);
    }
    addOrEditVariantCode(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._addOrEditVariantCode, res, options).map(this.extractData);
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}