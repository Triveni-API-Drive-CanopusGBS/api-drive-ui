import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ItoUpdateCostDBOMechPrice {

    //URL to get mechanical items
    private _getDBOMechanicalItems: string = "http://10.0.0.4:8082/ITO/DBO/getDBOMechanicalItems";
    //URL to get cache data
    private _getDBOMechUpdatePriceData: string ="http://10.0.0.4:8082/ITO/update/getDBOMechUpdatePriceData";
    //URL to get column details
     private _getDBOMechUpdateColData1: string ="http://10.0.0.4:8082/ITO/update/getDBOMechUpdateColData1";
        
    //URL to get quot frame and users
    private _quotFrameAndUsersUrl: string = "http://10.0.0.4:8082/ITO/quot/getFrameAndUserData";
    //URL to get update vehicle transport
    private _createDboMechUpdateRequestPrice: string ="http://10.0.0.4:8082/ITO/update/createDboMechUpdateRequestPrice";
    //URL to get update status
    private _updateStatus:string="http://10.0.0.4:8082/ITO/update/updateStatusAndSubmit";
    //URL to  save 
    private _saveUpdatedNoOfVehicles:string="http://10.0.0.4:8082/ITO/update/saveUpdatedNoOfVehicles";
    private _getDBOMechUpdColumsData: string = "http://10.0.0.4:8082/ITO/update/getDBOMechUpdateColData";
    private _createDboMechUpdateRequestCol: string = "http://10.0.0.4:8082/ITO/update/createDboMechUpdateRequestCol";
    private _getUpdateCreateMechPrice: string = "http://10.0.0.4:8082/ITO/DBO/getUpdateCreateMechPrice";
    private _getUpdateCreateMechAuxPrice: string = "http://10.0.0.4:8082/ITO/DBO/getUpdateCreateMechAuxPrice";
    private _getUpdateCreateMechOverTank: string = "http://10.0.0.4:8082/ITO/DBO/getUpdateCreateMechOverTank";
    private _getUpdateCreateMechAuxColVal: string = "http://10.0.0.4:8082/ITO/DBO/getUpdateCreateMechAuxColVal";
    private _getUpdateCreateMechAddOnCost: string = "http://10.0.0.4:8082/ITO/DBO/getUpdateCreateMechAddOnCost";
    private _getUpdateCreateMechAuxFrameSpecData: string = "http://10.0.0.4:8082/ITO/DBO/getUpdateCreateMechAuxFrameSpecData";
    private _saveUpdatedPackagePrice: string = "http://10.0.0.4:8082/ITO/update/saveUpdatedPackagePrice";
    private _getAdminPercentMech: string = "http://10.0.0.4:8082/ITO/admin/getAdminPercentMech";

    constructor(private _http: Http) {
    }
   
    getFrameAndUserData(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotFrameAndUsersUrl, ' ', options).map(this.extractData);
    }
    
    getDBOMechanicalItems(val): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDBOMechanicalItems,val, options).map(this.extractData);
    }

    getDBOMechUpdatePriceData(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDBOMechUpdatePriceData,res, options).map(this.extractData);
    }
    getDBOMechUpdateColData1(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDBOMechUpdateColData1,res, options).map(this.extractData);
    }

    getDBOMechUpdColumsData(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getDBOMechUpdColumsData,res, options).map(this.extractData);
    }

    createDboMechUpdateRequestPrice(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createDboMechUpdateRequestPrice, resp, options).map(this.extractData);
    }

    createDboMechUpdateRequestCol(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._createDboMechUpdateRequestCol, resp, options).map(this.extractData);
    }

    updateStatus(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._updateStatus, resp, options).map(this.extractData);
    }
    saveUpdatedNoOfVehicles(resp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveUpdatedNoOfVehicles, resp, options).map(this.extractData);
    }

    getUpdateCreateMechPrice(respo): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateCreateMechPrice, respo, options).map(this.extractData);
    }

    getUpdateCreateMechAuxPrice(respp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateCreateMechAuxPrice, respp, options).map(this.extractData);
    }    
    getUpdateCreateMechAddOnCost(respoo): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateCreateMechAddOnCost, respoo, options).map(this.extractData);
    } 
    getUpdateCreateMechAuxColVal(respooo): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateCreateMechAuxColVal, respooo, options).map(this.extractData);
    } 
    getUpdateCreateMechOverTank(resppp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateCreateMechOverTank, resppp, options).map(this.extractData);
    } 
    getUpdateCreateMechAuxFrameSpecData(resppp): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getUpdateCreateMechAuxFrameSpecData, resppp, options).map(this.extractData);
    } 
    saveUpdatedPackagePrice(res): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveUpdatedPackagePrice, res , options).map(this.extractData);
    }
    getAdminPercentMech(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getAdminPercentMech, adminForm , options).map(this.extractData);
    }
    

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}