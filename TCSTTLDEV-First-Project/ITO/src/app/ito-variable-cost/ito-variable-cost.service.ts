import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';



@Injectable()
export class ItoVariableCostService {

    private _DBOMechanicalCache: string = "http://10.0.0.4:8082/ITO/DBO/DBOMechanicalCache";
    private _fetchCacheData: string = "http://10.0.0.4:8082/ITO/quot/fetchCacheData"
    private _getVariableCost: string = "http://10.0.0.4:8082/ITO/quot/getVariableCost";
    private _saveVariableCost: string = "http://10.0.0.4:8082/ITO/quot/saveVariableCost";
    private _getVarCostDet: string = "http://10.0.0.4:8082/ITO/quot/getVarCostDet";
    
    dataSource: Array<any> = [];
    selectedUser: userDetails;

    constructor(private _http: Http) {

    }
    getDBOMechanicalCache(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._DBOMechanicalCache, ' ', options).map(this.extractData);
    }
    fetchCacheData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._fetchCacheData, ' ', options).map(this.extractData);
    }
    getVariableCost(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getVariableCost, res, options).map(this.extractData);
    }
    saveVariableCost(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveVariableCost, res, options).map(this.extractData);
    }

    getVarCostDet(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getVarCostDet, quotId, options).map(this.extractData);
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}