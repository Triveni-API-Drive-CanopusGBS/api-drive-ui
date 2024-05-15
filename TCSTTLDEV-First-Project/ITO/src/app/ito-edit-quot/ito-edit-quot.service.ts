import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http, Response } from '@angular/http';
import { ResponseContentType } from '@angular/http';

@Injectable()
export class ITOeditQoutService {
    private _userUrl: string = "http://10.0.0.4:8082/ITO/quot/fetchUserData";
    private _getQuotation: string = "http://10.0.0.4:8082/ITO/quot/getQuotation";
    private _quotExportPdf: string = "http://10.0.0.4:8082/ITO/quot/quotExportPdf";
    private _getQuotRevNo: string = "http://10.0.0.4:8082/ITO/quot/getQuotRevNo ";
    private _quotExportPdfdet: string = "http://10.0.0.4:8082/ITO/quot/quotExportPdfDet";
    private _quotExportAddOnPdf: string = "http://10.0.0.4:8082/ITO/quot/quotExportAddOnPdf";
    private _getWord: string = "http://10.0.0.4:8082/ITO/quot/getWord";   
   // private _getWord: string = "http://10.0.0.4:8082/ITO/quot/getWord";   
    private _getTechReport: string = "http://10.0.0.4:8082/ITO/quot/getTechReport";   
    private _getComercialWord: string = "http://10.0.0.4:8082/ITO/quot/getComercialWord";   
    private _getComercialWordNew: string = "http://10.0.0.4:8082/ITO/quot/getComercialWordNew";   
//Revisions
private _getWordRev: string = "http://10.0.0.4:8082/ITO/quot/getWordRev";   
private _getComercialWordRev: string = "http://10.0.0.4:8082/ITO/quot/getComercialWordRev";   
private _getComercialWordNewRev: string = "http://10.0.0.4:8082/ITO/quot/getComercialWordNewRev";   
private _quotExportPdfRev: string = "http://10.0.0.4:8082/ITO/quot/quotExportPdfRev";
private _quotExportAddOnPdfRev: string = "http://10.0.0.4:8082/ITO/quot/quotExportAddOnPdfRev";
private _getValidateFinalCost: string = "http://10.0.0.4:8082/ITO/admin/getValidateFinalCostNew";

button1:boolean=false;
button2:boolean=false;
button3:boolean=false;
button4:boolean=false;
button5:boolean=false;
button6:boolean=false;
button7:boolean=false;
button8:boolean=false;
button9:boolean=false;
    editFlag: boolean;
    dboMechData: any; //Mech item dropdownlist
    dboEleData: any;  //Ele Dropdown selected data
    dboEleFilterData: any;  //Ele Dropdown selected data
    dboEleAuxData: any; //Ele Aux dropDownData
    eleExtScopeList1:any;
    dboEleCiData: any; //Ele CI dropdown
    dboEleCIAuxData: any; //Ele Ci AUx dropDownData
    dboEleItmOthers: any; //To store item others of dbo electrical
    dboEleAuxItmOthers: any; // ToStore item others of dbo ELeAux electrical
    dboEleCIItmOthers:any; //To store item others of DBO Ele CI electrical
    dboEleCIAuxItmOthers: any; //To store item Others of Dbo ELe Ci Aux data
    dboF2fData: any;  // f2f Item dropdownList
    f2fOthersItemList: any; //f2f new others items list
    f2fOthersSubItemList: any; //f2f new others sub items list
    f2fOthersSubItemTypeList: any; //f2f new others sub items type list
    saveEleFilterList1: any; // To stor cI inst Data
    eleExtScopeList: any; //To store Ele Extended scope date from get quotationend point
    eleCIExtScopeList: any; //o store ele ci extended scope date for edit modde
    dboEleDataAddOn: any;
    dboDataOthers: any;
    dboEleOthers: any;
    dboEleSplAddOnList: any;
    dboEleNewAddOns: any;
    dboF2fNewAddOns: any;  //f2fAddOns
    turbineCode: string;
    quotNumber: any;
    editMode: boolean = true;
    dboMechAuxData: any;
    dboMechAuxDataAddonData: any;
    mechExtScpList: any;
    section2Data:any;//ali
    section2AData: any; //Moulika
    completecomercialdata:any//ali
    checkEdit:boolean =false;
    saveVmsDataList:any;
    savePerformanceDataList1:any;
    savePerformanceDataList2:any;
    savePerformanceDataList3: any;
    cirListData: any;
    fixedListData: any;
    identListData: any;
    itemListData: any;
    qualitiasurance:any;
    scopeofspares:any;
    tenderDraw:any;
    terminalpo:any;
    exclusionLi:any;
    subSuppliersList:any;
    otherGetDataList:any;
    attachmentDataList: any;

    private qNum = new Subject<any>();
    private btnStatus = new Subject<any>();
    constructor(private _http: Http) {
    }


    ///revision
    getWordRev(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        // const httpOptions = {
        //     'responseType' : 'blob' as 'json'
        //     };
        return this._http.post(this._getWordRev, quotForm , { responseType: ResponseContentType.Blob }).map(this.extractData);
        
    }
    getComercialWordRev(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getComercialWordRev, quotForm , { responseType: ResponseContentType.Blob }).map(this.extractData);        
    }
    getComercialWordNewRev(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getComercialWordNewRev, quotForm , { responseType: ResponseContentType.Blob }).map(this.extractData);        
    }
    quotExportAddOnPdfRev(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotExportAddOnPdfRev, quotForm, { responseType: ResponseContentType.Blob }).map(this.extractDatat);
    }    
    getValidateFinalCost(quotId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getValidateFinalCost, quotId, options).map(this.extractData);
    }

    quotExportPdfRev(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotExportPdfRev, quotForm, { responseType: ResponseContentType.Blob }).map(this.extractDatat);
    }
    ////
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
    quotExportAddOnPdf(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotExportAddOnPdf, quotForm, { responseType: ResponseContentType.Blob }).map(this.extractDatat);
    }
    quotExportPdfdet(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotExportPdfdet, quotForm, { responseType: ResponseContentType.Blob }).map(this.extractDatat);
    }
    getWord(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        // const httpOptions = {
        //     'responseType' : 'blob' as 'json'
        //     };
        return this._http.post(this._getWord, quotForm , { responseType: ResponseContentType.Blob }).map(this.extractData);
        
    }
    getComercialWord(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getComercialWord, quotForm , { responseType: ResponseContentType.Blob }).map(this.extractData);        
    }
    getComercialWordNew(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getComercialWordNew, quotForm , { responseType: ResponseContentType.Blob }).map(this.extractData);        
    }
    
    getTechReport(quotForm): Observable<Blob> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        // const httpOptions = {
        //     'responseType' : 'blob' as 'json'
        //     };
        return this._http.post(this._getTechReport, quotForm , { responseType: ResponseContentType.Blob }).map(this.extractData);
        
    }

    sendMessage(qnum) {
        this.qNum.next({ qnum });
    }
    sendbtnStatus(SOS, SAP, TUR, MECH, ELE, TECH, CMPLT) {
        this.btnStatus.next({ SOS, SAP, TUR, MECH, ELE, TECH, CMPLT });
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