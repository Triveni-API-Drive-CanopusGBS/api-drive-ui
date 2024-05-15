import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http ,Response } from '@angular/http';


@Injectable()
export class ITOerrectionCommisionService{
    private _getErrectionCommCache:string="http://10.0.0.4:8082/ITO/quot/getErrectionCommCache";
    private _getErecCommData:string="http://10.0.0.4:8082/ITO/quot/getErecCommData";
    private _saveErecCommission:string="http://10.0.0.4:8082/ITO/quot/saveErecCommission";
    private _getStatesData:string="http://10.0.0.4:8082/ITO/admin/getStatesList";

   
    constructor(private _http:Http){    
    }
    
    getErrectionCommCache(quoId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getErrectionCommCache, quoId, options).map(this.extractData);
    }

    getStatesData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            console.log(options);
            return this._http.post(this._getStatesData,' ', options).map(this.extractData);
    }
   
    getErecCommData(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getErecCommData, res, options).map(this.extractData);
    }

    saveErecCommission(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveErecCommission, res, options).map(this.extractData);
    }
   
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}