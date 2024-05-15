import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';


@Injectable()
export class ITOHomePageService{
    // private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotHome";
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/getHomePageData";
    private _getTurbineClone: string = "http://10.0.0.4:8082/ITO/quot/getTurbineClone";
    dataSource:Array<any>=[];
    selectedQuot:any;
    selectedQuotNum:any;
    revNum:any;

    constructor(private _http:Http){

    }
    getData(){
        return this.dataSource;
    }

    getQuotationList(userDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,userDetails , options).map(this.extractData);
    }
    saveAs(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getTurbineClone,res, options).map(this.extractData);
    }

        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}