import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http ,Response } from '@angular/http';
import {itoAdminBasicDetails} from './ito-basic-details';


@Injectable()
export class ITOBasicDetailsService{
  
  //URL to get frame details
 private _quotUrl1:string="http://10.0.0.4:8082/ITO/quot/quotCache";
    //URL to get frame details

  private _newCache:string="http://10.0.0.4:8082/ITO/admin/getAdminCacheWithAIList";

     //URL to get admin form
    private _AdminDetails:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";
      //URL to set response of frame to DB
    private _AdminAddFrame:string="http://10.0.0.4:8082/ITO/admin/addOrEditFrame";



    
    constructor(private _http:Http){    
    }
    getDataList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._newCache,' ', options).map(this.extractData);
    }
       // method to get frame details
    getQuotationList1(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl1,' ', options).map(this.extractData);
    }
    //method to get admin form
    getFrameForm(): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminDetails,' ', options).map(this.extractData);
    }
    //method to set response of frame to DB
    addFrame(res): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminAddFrame,res, options).map(this.extractData);
    }
  
//method to convert json response
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}