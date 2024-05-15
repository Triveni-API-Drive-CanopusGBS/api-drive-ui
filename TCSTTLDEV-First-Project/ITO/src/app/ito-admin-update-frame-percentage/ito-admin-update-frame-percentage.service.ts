import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoFramesPercentageService{
    //URL to get the Details from DB 
    private _adminGetDetails:string="http://10.0.0.4:8082/ITO/DBO/DBOMechanicalCache";
    // URL to send edited details 
    private _editValue:string="http://10.0.0.4:8082/ITO/admin/editF2FTurbineInstruments";
    //URL to get admin form 
    private _getAdminForm:string="http://10.0.0.4:8082/ITO/admin/getAdminForm"


   
   
    constructor(private _http: Http) {
    }

    //method to get admin form
    getFrameForm(): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._adminGetDetails,' ', options).map(this.extractData);
    }
// method to get admin form
    getAdminForm(): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getAdminForm,' ', options).map(this.extractData);
    }
    //method to send edited details
    sendAdminForm(resp): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._editValue,resp, options).map(this.extractData);
    }

    //method to convert the response to json
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}