import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http ,Response } from '@angular/http';


@Injectable()
export class ITOAddFrameService{
    //URL to fetch cache data
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotCache";
    //URL to fetch customer details
    private _CustomerDetails:string="http://10.0.0.4:8082/ITO/quot/saveBasicDetails";

    condensingType:number;
    frameId:number;
    frameName:string;
    isNewProject:string;
    opportunitySeqNum:string;
    percentageVariation:string;
    typeOfTurbine:string;
    capacity:string;
    regionId:number;
    modelType:string;
    saveBasicDet:any;
    turbineCode:string;
    quotNumber:any;
    editFlag: boolean = false;
    quotForm:any;
    private qNum = new Subject<any>();
    private btnStatus = new Subject<any>();
    constructor(private _http:Http){    
    }
    //Method to get frame details
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }
    //method to save data to DB
    saveBasicDetails(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._CustomerDetails, res , options).map(this.extractData);
    }
    sendMessage(qnum) {
        this.qNum.next({ qnum });
    }
    sendbtnStatus(SOS, SAP, TUR, MECH, ELE, CI, TECH, CMPLT) {
        this.btnStatus.next({ SOS, SAP, TUR, MECH, ELE, CI, TECH, CMPLT});
    }
    getMessage(): Observable<any> {
        return this.qNum.asObservable();
       }
       getbtnStatus(): Observable<any> {
        return this.btnStatus.asObservable();
       }
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}