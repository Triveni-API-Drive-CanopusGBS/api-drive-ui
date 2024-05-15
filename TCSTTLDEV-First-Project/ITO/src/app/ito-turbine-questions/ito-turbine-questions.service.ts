import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http ,Response } from '@angular/http';


@Injectable()
export class ITOTurbineQuestionsService{
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotCache";
    private _getOldFrames: string = "http://10.0.0.4:8082/ITO/admin/getOnlyOldFramesForQuestions";
    private _getQuestions: string = "http://10.0.0.4:8082/ITO/quot/getQuesPage";
    private _getAdminForm: string = "http://10.0.0.4:8082/ITO/admin/getAdminForm";
    private _getAllF2FQues: string = "http://10.0.0.4:8082/ITO/admin/getAllF2FQues";
    private _addOrEditQuesAnswer: string = "http://10.0.0.4:8082/ITO/admin/addOrEditQuesAnswer";

  frameList:Array<any>;
  typesOfTurbine:Array<any>;
  turbineCategoryList:Array<any>;
  framesPowerList:Array<any>;
  quotform:any;
  newWindow:any;
  answerbeanList:any;

    
    constructor(private _http:Http){    
    }
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }

    getOldFrames(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getOldFrames,' ', options).map(this.extractData);
    }

    getAllF2FQues(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAllF2FQues, res, options).map(this.extractData);
    }

    getQuestions(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getQuestions, res, options).map(this.extractData);
    }

    getAdminForm(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getAdminForm,' ', options).map(this.extractData);
    }

    addOrEditQuesAnswer(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._addOrEditQuesAnswer, res, options).map(this.extractData);
    }
    
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}