import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoNoOfVehicleService {
    //URL to get the Details from DB 
    private _getFrameCache: string = "http://10.0.0.4:8082/ITO/update/getNewFramesForNoOfVehicles";
    private _AdminDetails:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";
    private _getTransportationCache: string = "http://10.0.0.4:8082/ITO/quot/getTransportationCache";
    private _updatevehicleTransport:string="http://10.0.0.4:8082/ITO/update/updateNoOfVehicles";
    private _updateStatus:string="http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
    private _saveTransportVehicle:string="http://10.0.0.4:8082/ITO/update/saveUpdatedNoOfVehicles";
   
    constructor(private _http: Http) {
    }

    getNewFrameCache(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getFrameCache, ' ', options).map(this.extractData);
    }
  //method to get empty admin form to fetch transportation details
  addOrEditForm(): Observable<any> {
      
    let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._AdminDetails,' ', options).map(this.extractData);
}
updatePriceTransport(resp): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._updatevehicleTransport, resp, options).map(this.extractData);
}
updateStatus(resp): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._updateStatus, resp, options).map(this.extractData);
}

saveTransportPrice(resp): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._saveTransportVehicle, resp, options).map(this.extractData);
}


  
   
    //method to convert the response to json
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}