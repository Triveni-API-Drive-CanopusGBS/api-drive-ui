import { Injectable, Output, EventEmitter } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';
import { ArrayObservable } from 'rxjs/observable/ArrayObservable';

@Injectable()
export class ItoF2fTurbineDetailsService {

    f2fData: Array<any>;
    Counter: any;
    F2FBOMTree: any;
    AvailableCnums: Array<any>;
    Cnumber: any;
    latestCDetails: any = [];
    @Output() rectEvent = new EventEmitter();
    private subject = new Subject<any>();
    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/getQuotFormData";
    private _getQuestions: string = "http://10.0.0.4:8082/ITO/quot/getQuesPage";
    private _saveQuestions: string = "http://10.0.0.4:8082/ITO/quot/saveQuesDetails";
    private _getF2FData: string = "http://10.0.0.4:8082/ITO/quot/getOneLineBom";
    private _getF2FSapData: string = "http://10.0.0.4:8082/ITO/quot/getF2FSapData";
    private _saveF2FSap: string = "http://10.0.0.4:8082/ITO/quot/saveF2FSap";
    private _SCUrl: string = "http://10.0.0.4:8082/ITO/quot/getF2FShopCon";
    private _OHUrl: string = "http://10.0.0.4:8082/ITO/quot/getF2FOverHeadCost";
    private _getExcelCostSheetData: string = "http://10.0.0.4:8082/ITO/quot/getExcelCostSheetData";
    private _getQuestData: string = "http://10.0.0.4:8082/ITO/quot/getQuestionInfo";

    constructor(private _http: Http) {
    }
    getQuotForm(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUrl, ' ', options).map(this.extractData);
    }
    getQuestions(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuestions, res, options).map(this.extractData);
    }
getQuestionData(res): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._getQuestData, res, options).map(this.extractData);
    }
    saveQuestions(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveQuestions, res, options).map(this.extractData);
    }
    getF2FData(saveBasicDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2FData, saveBasicDetails, options).map(this.extractData);
    }
    getF2FSapData(saveBasicDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2FSapData, saveBasicDetails, options).map(this.extractData);
    }
    saveF2FSap(saveBasicDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveF2FSap, saveBasicDetails, options).map(this.extractData);
    }
    save() {
        this.rectEvent.emit("save");
    }
    sendMessage(onLine) {
        this.subject.next({ onLine });
    }

    clearMessage() {
        this.subject.next();
    }

    getMessage(): Observable<any> {
        return this.subject.asObservable();
    }

    GetShopConvPop(resSC): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._SCUrl, resSC, options)
            .map(this.extractData);
    }

    GetOverheadCostPop(resOH): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._OHUrl, resOH, options)
            .map(this.extractData);
    }
    getExcelCostSheetData(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getExcelCostSheetData, quotId, options).map(this.extractData);
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}