import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';
import { Http, Response } from '@angular/http';

@Injectable()
export class dboEleCiExtdScopeService {
//get dbo components
private _saveExtendedEleCi: string = "http://10.0.0.4:8082/ITO/DBO/saveCIExtScope";
addedClassList: Array<any> = [];
constructor(private _http: Http) {
}
saveExtendedEleCi(respons): Observable<any> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._saveExtendedEleCi, respons, options).map(this.extractData);
}
private extractData(res: Response) {
    let body = res.json();
    // console.log(body);
    return body || {};
}
}