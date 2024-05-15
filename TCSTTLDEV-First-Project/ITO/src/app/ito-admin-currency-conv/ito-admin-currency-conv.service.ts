import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http ,Response } from '@angular/http';
import {itoAdminCurrencyConv} from './ito-admin-currency-conv';


@Injectable()
export class ITOCurrencyConvService{
  
    //with Active list URL to get currency details
   private _quotUrl:string="http://10.0.0.4:8082/ITO/admin/getAdminCacheWithAIList";
   //URL for getting the details of currency 
    private _getAdminCacheWithAIList: string = "http://10.0.0.4:8082/ITO/admin/getAdminCacheWithAIList";
     //URL to get admin form
    private _AdminDetails:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";
      //URL to set response of frame to DB
    private _AdminAddOREditUSD:string="http://10.0.0.4:8082/ITO/admin/addOrEditUSD";



    
    constructor(private _http:Http){    
    }
     //method to get currency details
    getUSDList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }
    
    //method to get currency details with both active and in active list
    getAdminCacheWithAIList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getAdminCacheWithAIList,' ', options).map(this.extractData);
    }
    
    //method to get frame details
    // getQuotationList1(): Observable<any> {
    //     let headers = new Headers({ 'Content-Type': 'application/json' });
    //         let options = new RequestOptions({ headers: headers });
    //         return this._http.post(this._quotUrl1,' ', options).map(this.extractData);
    // }
    //method to get admin form
    getadminForm(): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminDetails,' ', options).map(this.extractData);
    }
    //method to set response of frame to DB
    addorEditUSD(res): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminAddOREditUSD,res, options).map(this.extractData);
    }
  
//method to convert json response
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}