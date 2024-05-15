import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';


@Injectable()
export class ITOcustomerDetailsService{
    private _quotUrl:string="http:///10.0.0.4:8082/ITO/quot/quotHome";
    private _CustomerDetails:string="http://10.0.0.4:8082/ITO/quot/navigateBasicDetails";
    private _custUrl:string="http:///10.0.0.4:8082/ITO/custprofile/getCustomerData";
    private _getStatesData:string="http:///10.0.0.4:8082/ITO/admin/getStatesList";

    // customerID:number;
    // custName:string;
    // custType:string;
    // spocName:string;
    // firmName:string;
    // hasConsultant:string;
    // firmId:number;
    // spocId:number;
    // 
    // endUserId:number;
    // endUserName:string;
    // hasEndUser:string;
    // custCode:string;

    customerDetailsForm:any;
    newWindow:any;
    secondwindow:any;
    closewindow:boolean;
    oppSeqNo:any;
    typOfOfff: any;
    typOfCust: any;
    endUser: number;
    typeOfOfferNm: string;
    typeOfCustomerNm: string;
    isEndUserReq: number;
    stateId: number;
    stateNm: string;
    enquiryReference: string;
    constructor(private _http:Http){
    
    }
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }
    submitCusomerDetails(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._CustomerDetails, res , options).map(this.extractData);
    }
    getStatesData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            console.log(options);
            return this._http.post(this._getStatesData,' ', options).map(this.extractData);
    }
	

    getCustData(data): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._custUrl,data, options).map(this.extractData);
    }

        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}