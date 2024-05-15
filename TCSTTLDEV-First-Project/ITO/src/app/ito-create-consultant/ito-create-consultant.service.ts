import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';
import {consultantDetails} from './ito-create-consultant';
import { ITOconsultHomeService } from '../ito-consultant-home/ito-consultant-home.service';


@Injectable()
export class ITOcreateConsultHomeService{
    private _createConsulttUrl:string="http://10.0.0.4:8082/ITO/custprofile/createConsultant";
    private _quotUrl:string="http://10.0.0.4:8082/ITO/quot/quotHome";

    selectedCosultant:consultantDetails;
    editMode:boolean=false;
    constructor(private _http:Http ,private _ITOconsultHomeService:ITOconsultHomeService){
        this.selectedCosultant=this._ITOconsultHomeService.selectedConsult;
    }

    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._quotUrl,' ', options).map(this.extractData);
    }

    createConsultant(consultantDetails:consultantDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._createConsulttUrl,consultantDetails, options).map(this.extractData);
    }
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}