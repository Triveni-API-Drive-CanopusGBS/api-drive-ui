import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoTransportationDetailsService {
    // URL for get the details of scope of supply
    private _getTransportationCache: string = "http://10.0.0.4:8082/ITO/quot/getTransportationCache";
    //URL for getting the details ofcomponent, vehicle and place
    private _getAdminCacheWithAIList: string = "http://10.0.0.4:8082/ITO/admin/getAdminCacheWithAIList";
     // URL for get the details empty admin form to set the details
    private _AdminDetails:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";
     // URL for send the details of component
    private _AdminTransportCompoDetails:string="http://10.0.0.4:8082/ITO/admin/addOrEditTransComponentwithPlace";
    // URL for send the details of place
    private _AdminTransportPlaceDetails:string="http://10.0.0.4:8082/ITO/admin/addOrEditTransportPlace";
    // URL for send the details of vehicle
    private _AdminTransportvehiclDetails:string="http://10.0.0.4:8082/ITO/admin/addOrEditVehicle";

    constructor(private _http: Http) {
    }

    //method to get component,vehicle & place details
    getTransportationCache(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getTransportationCache, ' ', options).map(this.extractData);
    }

    //method to get component,vehicle & place details
    getAdminCacheWithAIList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAdminCacheWithAIList, ' ', options).map(this.extractData);
    }


    //method to get the Admin form in order to send the details
    addOrEditForm(): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminDetails,' ', options).map(this.extractData);
    }
   
        //method to send component detais request to the DB 

    addOrEditCompo(res): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminTransportCompoDetails,res, options).map(this.extractData);
    }

        //method to send vehicle detais request to the DB 

    addOrEditVehi(res): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminTransportvehiclDetails,res, options).map(this.extractData);
    }

  //method to send place detais request to the DB 

    addOrEditPlace(res): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminTransportPlaceDetails,res, options).map(this.extractData);
    }
    
    //Converting response to JSON
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}