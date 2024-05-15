import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';


@Injectable()
export class ITOMyWorkListPageService {
    private _getUpdatePriceReqGrid: string = "http://10.0.0.4:8082/ITO/update/getUpdatePriceReqGrid";
    private _getUpdatePriceData: string = "http://10.0.0.4:8082/ITO/update/getSavedUpdatedPriceData";
    
    selectedUR:any='';
    responseTemp:any;

    constructor(private _http: Http) {
    }
    getUpdatePriceReqGrid(userId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdatePriceReqGrid, userId, options).map(this.extractData);
    }
    getUpdatePriceData(saveBasicDet): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdatePriceData, saveBasicDet, options).map(this.extractData);
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}