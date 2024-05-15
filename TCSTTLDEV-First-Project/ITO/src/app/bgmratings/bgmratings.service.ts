import { Injectable } from '@angular/core';
import { Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';
import {HttpClient,HttpHeaders } from '@angular/common/http';


@Injectable()
export class BgmratingsComponentService {
    private _getAdminForm:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";
    private _getUserManual: string = "http://10.0.0.4:8082/ITO/admin/getUserManual";
    private _downloadUserManual: string = "http://10.0.0.4:8082/ITO/admin/downloadFile";
    private _saveAttachment: string = "http://10.0.0.4:8082/ITO/DBO/saveAttachment";
    private _getAdminCacheWithAIList: string = "http://10.0.0.4:8082/ITO/admin/getAdminCacheWithAIList";

    private _getAdminDcmPowerCalc:string="http://10.0.0.4:8082/ITO/admin/getAdminDcmPowerCalc";
    private _getAdminEleItems:string="http://10.0.0.4:8082/ITO/admin/getAdminEleItems";
    private _getAdminBgmCalc:string="http://10.0.0.4:8082/ITO/admin/getAdminBgmCalc";
    private _getAdminItemMast:string="http://10.0.0.4:8082/ITO/admin/getAdminItemMast";
    private _getAdminOthersMast:string="http://10.0.0.4:8082/ITO/admin/getAdminOthersMast";
    private _getAuditHistory1:string="http://10.0.0.4:8082/ITO/admin/getAuditHistory1";
    private _getAuditHistoryDetails:string="http://10.0.0.4:8082/ITO/admin/getAuditHistoryDetails";
    private _getAdminPerfAux:string="http://10.0.0.4:8082/ITO/admin/getAdminPerfAux";
    private _getAdminPerfAcDcFrmInput:string="http://10.0.0.4:8082/ITO/admin/getAdminPerfAcDcFrmInput";
    private _getAdminPerfOther:string="http://10.0.0.4:8082/ITO/admin/getAdminPerfOther";
    private _getAdminPerfAcDcMast:string="http://10.0.0.4:8082/ITO/admin/getAdminPerfAcDcMast";
    private _getAdminSparesMast:string="http://10.0.0.4:8082/ITO/admin/getAdminSparesMast";
    private _getAdminSubSupplierMast:string="http://10.0.0.4:8082/ITO/admin/getAdminSubSupplierMast";
    private _getAdminTerminalMast:string="http://10.0.0.4:8082/ITO/admin/getAdminTerminalMast";
    private _getAdminQualityMast: string="http://10.0.0.4:8082/ITO/admin/getAdminQualityMast";
    private _getAdminServiceMast: string="http://10.0.0.4:8082/ITO/admin/getAdminServiceMast";
    private _getAdminAcwr: string="http://10.0.0.4:8082/ITO/admin/getAdminAcwr";
    private _getAdminComrMonths: string="http://10.0.0.4:8082/ITO/admin/getAdminComrMonths";
    private _getAdminTenderDrawingMast: string="http://10.0.0.4:8082/ITO/admin/getAdminTenderDrawingMast";
    private _getAdminExclusionMast: string="http://10.0.0.4:8082/ITO/admin/getAdminExclusionMast";


    constructor(private _http:Http){
    
    }

    
    saveAttachment(dboForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._saveAttachment, dboForm , options).map(this.extractData);
    }
    getAdminDcmPowerCalc(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminDcmPowerCalc, adminForm , options).map(this.extractData);
    }
    getAdminTenderDrawingMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminTenderDrawingMast, adminForm , options).map(this.extractData);
    }
    getAdminExclusionMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminExclusionMast, adminForm , options).map(this.extractData);
    }
    getAdminSubSupplierMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminSubSupplierMast, adminForm , options).map(this.extractData);
    }
    getAdminQualityMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminQualityMast, adminForm , options).map(this.extractData);
    }
    getAdminTerminalMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminTerminalMast, adminForm , options).map(this.extractData);
    }
    getAdminComrMonths(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminComrMonths, adminForm , options).map(this.extractData);
    }
    
    getAdminServiceMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminServiceMast, adminForm , options).map(this.extractData);
    }
    getAdminAcwr(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminAcwr, adminForm , options).map(this.extractData);
    }
    getAdminSparesMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminSparesMast, adminForm , options).map(this.extractData);
    }
    getAuditHistoryDetails(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAuditHistoryDetails, adminForm , options).map(this.extractData);
    }
    getAdminPerfOther(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminPerfOther, adminForm , options).map(this.extractData);
    }
    getAdminPerfAcDcMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminPerfAcDcMast, adminForm , options).map(this.extractData);
    }
    getAdminPerfAcDcFrmInput(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminPerfAcDcFrmInput, adminForm , options).map(this.extractData);
    }
    getAdminPerfAux(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminPerfAux, adminForm , options).map(this.extractData);
    }
    getAdminEleItems(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminEleItems, adminForm , options).map(this.extractData);
    }
    getAdminBgmCalc(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminBgmCalc, adminForm , options).map(this.extractData);
    }
    getAdminItemMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminItemMast, adminForm , options).map(this.extractData);
    }
    getAdminOthersMast(adminForm): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAdminOthersMast, adminForm , options).map(this.extractData);
    }
    getAuditHistory1(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getAuditHistory1, '' , options).map(this.extractData);
    }

    getAdminCacheWithAIList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getAdminCacheWithAIList,' ', options).map(this.extractData);
    }
    getAdminForm(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getAdminForm,' ', options).map(this.extractData);
    }

//     downloadUserManual(userMan: number): Observable<any> {
//     // set headers for the file and response to be Blob
//     const headers = new Headers({ 'Content-Type': 'application/json' });
//     const options = new RequestOptions({ headers: headers });
//     options.responseType = ResponseContentType.Blob;

//     return this._http.post(this._downloadUserManual, (userMan), options)
//       .map((res: Response) => res)
//       .catch(this.handleError);
//   }
//   // handle error
//   private handleError (error: any) {
//     return Observable.throw(error);
//   }

//   downloadUserManual(_downloadUserManual): any {
//     const options = { responseType: ResponseContentType.Blob  };
//     return this._http.get(_downloadUserManual, options)
//     .map ( (res) => {
//         return new Blob([res.blob()], { type: 'application/pdf' });
//     });
//   }
    // downloadFile(userMan: number): Observable<File> {
    //     //this.applicationsUrl = `${APIConfig.BaseUrl}/documents/download/${fileId}/`;
        
    //     let headers = new Headers({ 'Content-Type': 'application/json', 'MyApp-Application' : 'AppName', 'Accept': 'application/pdf' });
    //     let options = new RequestOptions({ headers: headers, responseType: ResponseContentType.Blob });
    //     return this._http.post(this._downloadUserManual, (userMan) , options).map(this.extractContent);
          
    //     }

       
        

      // handle error
    
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}
    