import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http ,Response } from '@angular/http';
import {itoAdminScreenDetails} from './ito-admin-screen';


@Injectable()
export class ITOAdminScreenService{
  
    //URL to get admin details for region, role and department/group
    private _quotUrl:string="http://10.0.0.4:8082/ITO/admin/getAdminCacheWithAIList";
    //URL to get admin form details
    private _AdminDetails:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";
    //URL to addoredit region
    private _AddOrEditRegion:string="http://10.0.0.4:8082/ITO/admin/addOrEditRegion";
    //URL to addoredit role
    private _AddOrEditRole:string="http://10.0.0.4:8082/ITO/admin/addOrEditRoles";
    //URL to addoredit department
    private _AddOrEditGroup:string="http://10.0.0.4:8082/ITO/admin/addOrEditDepartment";

 
    constructor(private _http:Http){    
    }

    // method to get admin details for region, role, group
    getRolesList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }
    
    // method to get admin form
    addOrEditRegion(): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AdminDetails,' ', options).map(this.extractData);
    }
    // method to send region details to DB
    addOrEditRegion1(res): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AddOrEditRegion,res, options).map(this.extractData);
    }
       // method to send role details to DB
    addOrEditRole1(res): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AddOrEditRole,res, options).map(this.extractData);
    }
       // method to send group details to DB
    addOrEditGroup1(res): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._AddOrEditGroup,res, options).map(this.extractData);
    }
   
   //method to convert response to json
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}