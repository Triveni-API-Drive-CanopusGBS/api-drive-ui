import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { Subject } from 'rxjs/Subject';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';

@Injectable()
export class ITOCostEstimationService {

    private _scopeOfSupplyStatus: string = "http://10.0.0.4:8082/ITO/quot/scopeOfSupplyStatus";
    private status = new Subject<any>();
   

    constructor(private _http: Http, private _ITOcustomerRequirementService: ITOcustomerRequirementService, ) {
    }
    sendMessage(qnum) {
        this.status.next({ qnum });
    }
    scopeOfSupplyStatus(saveBasicDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._scopeOfSupplyStatus, saveBasicDetails, options).map(this.extractData);
    }
    getMessage(): Observable<any> {
        return this.status.asObservable();
    }
    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}