import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http, Response } from '@angular/http';

@Injectable()
export class DboMechAuxialriesService {
//get dbo components
private _getMechAuxItems: string = "http://10.0.0.4:8082/ITO/DBO/getMechAuxItems"; 
private _getMechAuxPanels: string = "http://10.0.0.4:8082/ITO/DBO/getMechAuxPanels"; 
//get dbo component price
private _getMechAuxTechPrice: string = "http://10.0.0.4:8082/ITO/DBO/getMechAuxTechPrice";
private _saveMexhAuxItem: string = "http://10.0.0.4:8082/ITO/DBO/saveMechAuxItem";
private _removeDboMechItem: string = "http://10.0.0.4:8082/ITO/DBO/removeDboMechAuxItem";
addedClassList: Array<any> = [];
constructor(private _http: Http) {
}
getMechAuxItems(turbineType): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._getMechAuxItems, turbineType, options).map(this.extractData);
}
getMechAuxPanels(res): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._getMechAuxPanels, res, options).map(this.extractData);
}
getMechAuxTechPrice(res): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._getMechAuxTechPrice, res, options).map(this.extractData);
}
saveMexhAuxItem(res): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._saveMexhAuxItem, res, options).map(this.extractData);
}
removeDboMechItem(quotId,itemId,subItemId): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._removeDboMechItem, { quotId,itemId,subItemId }, options).map(this.extractData);
}
private extractData(res: Response) {
    let body = res.json();
    // console.log(body);
    return body || {};
}

}