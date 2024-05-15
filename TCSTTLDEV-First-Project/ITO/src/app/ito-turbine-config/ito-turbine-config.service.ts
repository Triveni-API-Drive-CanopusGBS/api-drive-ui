import { Injectable, Output, EventEmitter } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';
import {BehaviorSubject} from 'rxjs';
import { ArrayObservable } from 'rxjs/observable/ArrayObservable';

@Injectable()
export class ITOturbineConfigService {

    f2fData: Array<any>;
    Counter: any;
    F2FBOMTree: any;
    AvailableCnums: Array<any>;
    Cnumber: any;
    latestCDetails: any = [];
    selected: boolean;
    @Output() rectEvent = new EventEmitter();
    private subject = new Subject<any>();
    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/getQuotFormData";
    private _getF2FData: string = "http://10.0.0.4:8082/ITO/quot/getOneLineBom";
    private _SCUrl: string = "http://10.0.0.4:8082/ITO/quot/getF2FShopCon";
    private _OHUrl: string = "http://10.0.0.4:8082/ITO/quot/getF2FOverHeadCost";
    private _getExcelCostSheetData: string = "http://10.0.0.4:8082/ITO/quot/getExcelCostSheetData";

    private _getF2fItems: string = "http://10.0.0.4:8082/ITO/DBO/getF2fItems";
    private _getF2fPanels: string = "http://10.0.0.4:8082/ITO/DBO/getF2fPanels";
    private _getDboFormData: string = "http://10.0.0.4:8082/ITO/DBO/getDboFormData";
    private _getF2fTechPrice: string = "http://10.0.0.4:8082/ITO/DBO/getF2fTechPrice";
    private _getF2fAddOn: string = "http://10.0.0.4:8082/ITO/DBO/getF2fAddOn";
    private _getF2fPriceAddOn: string = "http://10.0.0.4:8082/ITO/DBO/getF2fPriceAddOn";
    private _saveF2fItem: string = "http://10.0.0.4:8082/ITO/DBO/saveF2fItem";
    private _saveF2fSubItem: string = "http://10.0.0.4:8082/ITO/DBO/saveF2fSubItem";
    private _saveF2fSubItemType: string = "http://10.0.0.4:8082/ITO/DBO/saveF2fSubItemType";
    
    constructor(private _http: Http) {
    }
    getQuotForm(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUrl, ' ', options).map(this.extractData);
    }
    getF2FData(saveBasicDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2FData, saveBasicDetails, options).map(this.extractData);
    }
    getDboFormData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDboFormData, ' ', options).map(this.extractData);
    }
    getF2fItems(responn): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2fItems, responn, options).map(this.extractData);
    }
    getF2fPanels(responn): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2fPanels, responn, options).map(this.extractData);
    }
    getF2fTechPrice(responsee): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2fTechPrice, responsee, options).map(this.extractData);
    }
    getF2fAddOn(respoo): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2fAddOn, respoo, options).map(this.extractData);
    }
    getF2fPriceAddOn(resppp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2fPriceAddOn, resppp, options).map(this.extractData);
    }
    saveF2fItem(resppppp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveF2fItem, resppppp, options).map(this.extractData);
    }
    saveF2fSubItem(respons): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveF2fSubItem, respons, options).map(this.extractData);
    }
    saveF2fSubItemType(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveF2fSubItemType, res, options).map(this.extractData);
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