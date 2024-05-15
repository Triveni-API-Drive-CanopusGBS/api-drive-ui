import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';
import {consultantDetails} from '../ito-create-consultant/ito-create-consultant';


@Injectable()
export class ITOconsultHomeService{
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotHome";
    selectedConsult:consultantDetails;
    
    constructor(private _http:Http){
        this.selectedConsult=new consultantDetails('');
    }
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }

    getSelConsult(){
        return this.selectedConsult;
    }

        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}