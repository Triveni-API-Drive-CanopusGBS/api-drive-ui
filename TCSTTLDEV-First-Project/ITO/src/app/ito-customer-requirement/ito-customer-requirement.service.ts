import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http, Response } from '@angular/http';


@Injectable()
export class ITOcustomerRequirementService {
    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/quotCache";
    private _quotUserUrl: string = "http://10.0.0.4:8082/ITO/quot/fetchCacheData";
    private _CustomerDetails: string = "http://10.0.0.4:8082/ITO/quot/saveBasicDetails";
    private _getStdOffer: string = "http://10.0.0.4:8082/ITO/admin/getStdOffer";
    private _getAdminForm:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";

fetchQuotCache

    condensingTypeId: number;
    frameId: number;
    frameName: string;
    isNewProject: number;
    stateNm: string;
    stateId: number;
    
    opportunitySeqNum: string = '';
    percentageVariation: string;
    typeOfTurbine: string;
    capacity: string;
    regionId: number;
    region: string;
    regionCode: string;
    modelType: string;
    saveBasicDet: any;
    quotResponse: any;
    turbineCode: string;
    turbineCat: string;
    turbineCatDesc: string;
    turbineCatId: string;
    quotNumber: any;
    endUserName: string;
    endUserContNo: string;
    endUserdEmail: string;
    EndUserAddress: string;
    typOfOfff: number;
    typOfCust: number;
    typOfQuote: number;
    typeOfOfferNm: string;
    typeOfCustomerNm: string;
    typeOfQuotNm: string;
    typeOfInject: number;
    typeOfInjectNm: string;
    typeOfInjectionCode: string;
    typeOfVarient: number;
    typeOfVarientNm: string;
    //new
    typeOfbleed: string;
    improvedImpulse: number;
    condensingTypeName: string;
    isFrameUpdated: number;
    isEndUserReq: number;
    QuotName: string;
    OffName: string;
    editFlag: boolean;
    quotForm: any;
    private qNum = new Subject<any>();
    private btnStatus = new Subject<any>();
    private btnTurStatus = new Subject<any>();
    private btnMechStatus = new Subject<any>();
    private btnEleStatus = new Subject<any>();
    private btnCIStatus = new Subject<any>();
    private btnTechStatus = new Subject<any>();
    private btnComrStatus = new Subject<any>();
    private btnClrStatus = new Subject<any>();

    constructor(private _http: Http) {
    }

    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUrl, ' ', options).map(this.extractData);
    }
    fetchCacheData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUserUrl, ' ', options).map(this.extractData);
    }
    saveBasicDetails(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._CustomerDetails, res, options).map(this.extractData);
    }
    getStdOffer(resAdm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getStdOffer, resAdm , options).map(this.extractData);
    }
    getAdminForm(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAdminForm, ' ' , options).map(this.extractData);
    }
    sendMessage(qnum) {
        this.qNum.next({ qnum });
    }
    sendbtnStatus(SOS,SAP,TUR,MECH,ELE,CI,TECH,CMPLT) {
        this.btnStatus.next({ SOS,SAP,TUR,MECH,ELE,CI,TECH,CMPLT });
    }
    getMessage(): Observable<any> {
        return this.qNum.asObservable();
    }
    getbtnStatus(): Observable<any> {
        return this.btnStatus.asObservable();
    }
    sendturBtnStatus(F2FTUR) {
        this.btnTurStatus.next({F2FTUR});
    }
    getturBtnStatus(): Observable<any> {
        return this.btnTurStatus.asObservable();
    }
    sendmecBtnStatus(MECHbtnn) {
        this.btnMechStatus.next({MECHbtnn});
    }
    getmecBtnStatus(): Observable<any> {
        return this.btnMechStatus.asObservable();
    }
    sendeleBtnStatus(ELEbtnn) {
        this.btnEleStatus.next({ELEbtnn});
    }
    geteleBtnStatus(): Observable<any> {
        return this.btnEleStatus.asObservable();
    }
    sendCIBtnStatus(CIbtnn) {
        this.btnCIStatus.next({CIbtnn});
    }
    getCIBtnStatus(): Observable<any> {
        return this.btnCIStatus.asObservable();
    }
    sendtecBtnStatus(TECHbtnn) {
        this.btnTechStatus.next({TECHbtnn});
    }
    gettecBtnStatus(): Observable<any> {
        return this.btnTechStatus.asObservable();
    }
    sendcomBtnStatus(COMRbtnn) {
        this.btnComrStatus.next({COMRbtnn});
    }
    getcomBtnStatus(): Observable<any> {
        return this.btnComrStatus.asObservable();
    }
    sendclrBtnStatus(SOS,SAP,TUR,MECH,ELE,CI,TECH,CMPLT,F2FTUR,MECHbtnn,ELEbtnn,CIbtnn,TECHbtnn,COMRbtnn) {
        this.btnClrStatus.next({SOS,SAP,TUR,MECH,ELE,CI,TECH,CMPLT,F2FTUR,MECHbtnn,ELEbtnn,CIbtnn,TECHbtnn,COMRbtnn});
    }
    getclrBtnStatus(): Observable<any> {
        return this.btnClrStatus.asObservable();
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}