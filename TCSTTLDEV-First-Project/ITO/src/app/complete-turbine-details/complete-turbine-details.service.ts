import { Injectable, Output, EventEmitter } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';

@Injectable()
export class ITOcompleteTurbineService {

    f2fData: any;
    Counter: any = 0;
    openUbo: boolean = false;
    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/quotHome";
    private _getQuestions: string = "http://10.0.0.4:8082/ITO/quot/getQuesPage";
    private _saveQuestions: string = "http://10.0.0.4:8082/ITO/quot/saveQuesDetails";
    private _getF2FData: string = "http://10.0.0.4:8082/ITO/quot/getF2FTreeStructure";   
      
    constructor(private _http: Http) {
    }
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUrl, ' ', options).map(this.extractData);
    }
    getF2FData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2FData, options).map(this.extractData);
    }
    openUBO() {
        this.openUbo=true;
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}