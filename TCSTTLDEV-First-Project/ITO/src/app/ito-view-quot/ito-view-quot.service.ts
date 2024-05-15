import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http, Response } from '@angular/http';
import { ResponseContentType } from '@angular/http';

@Injectable()
export class ITOViewQuotService {
    private _userUrl: string = "http://10.0.0.4:8082/ITO/quot/fetchUserData";
    private _getQuotation: string = "http://10.0.0.4:8082/ITO/quot/getQuotation";
    private _quotExportPdf: string = "http://10.0.0.4:8082/ITO/quot/quotExportPdf";
    private _getQuotRevNo: string = "http://10.0.0.4:8082/ITO/quot/getQuotRevNo ";
    private _getQuotRevData: string = "http://10.0.0.4:8082/ITO/quot/getQuotRevData ";
   
    editFlag: boolean;
    dboMechData: any;
    dboEleData: any;
    dboEleDataAddOn: any;
    dboDataOthers: any;
    dboEleOthers: any;
    dboEleSplAddOnList: any;
    dboEleNewAddOns: any;
    turbineCode: string;
    quotNumber: any;
    editMode: boolean = true;

    private qNum = new Subject<any>();
    private btnStatus = new Subject<any>();
    constructor(private _http: Http) {
    }
    fetchUserData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._userUrl, ' ', options).map(this.extractData);
    }
    getQuotation(quoId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuotation, quoId, options).map(this.extractData);
    }
    quotExportPdf(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotExportPdf, quotForm, { responseType: ResponseContentType.Blob }).map(this.extractDatat);
    }

    getQuotRevData(saveBasic): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuotRevData, saveBasic, options).map(this.extractData);
    }

    sendMessage(qnum) {
        this.qNum.next({ qnum });
    }
    sendbtnStatus(SOS, SAP, TUR, MECH, ELE, CI, TECH, CMPLT) {
        this.btnStatus.next({ SOS, SAP, TUR, MECH, ELE, CI, TECH, CMPLT });
    }
    getMessage(): Observable<any> {
        return this.qNum.asObservable();
    }
    getbtnStatus(): Observable<any> {
        return this.btnStatus.asObservable();
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
    private extractDatat(res: Response) {
        return new Blob([res.blob()], { type: 'application/vmd.pdf' });
    }
}