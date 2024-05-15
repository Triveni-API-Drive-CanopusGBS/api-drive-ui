import { Injectable, Output, EventEmitter } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';

@Injectable()
export class ItoPerformanceService {
    private _savePerformance: string = "http://10.0.0.4:8082/ITO/DBO/savePerformance";
    private _getDboFormData: string = "http://10.0.0.4:8082/ITO/DBO/getDboFormData";
    private _getPerformance: string = "http://10.0.0.4:8082/ITO/DBO/getPerformance"; 
    private _getPerformanceParam: string = "http://10.0.0.4:8082/ITO/DBO/getPerformanceParam";
     private _getOtherChapter: string = "http://10.0.0.4:8082/ITO/DBO/getOtherChapter";
    private _saveOtherChapter: string = "http://10.0.0.4:8082/ITO/DBO/saveOtherChapter";
    private _getComercial: string = "http://10.0.0.4:8082/ITO/DBO/getComercial";
    private _saveComercial: string = "http://10.0.0.4:8082/ITO/DBO/saveComercial";
    
    constructor(private _http: Http) {
    }
   
    getOtherChapter(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getOtherChapter, res , options).map(this.extractData);
    }
    saveOtherChapter(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveOtherChapter, res , options).map(this.extractData);
    }

    getDboFormData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboFormData, ' ', options).map(this.extractData);
    }

    savePerformance(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._savePerformance, res , options).map(this.extractData);
    }
    getPerformance(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getPerformance, res , options).map(this.extractData);
    }
    getPerformanceParam(respon): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getPerformanceParam, respon , options).map(this.extractData);
    }
    getComercial(respooo): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getComercial, respooo , options).map(this.extractData);
    }
    saveComercial(resoo): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveComercial, resoo , options).map(this.extractData);
    }
    
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}