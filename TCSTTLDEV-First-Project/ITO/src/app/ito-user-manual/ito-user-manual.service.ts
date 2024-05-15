import { Injectable } from '@angular/core';
import { Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http ,Response } from '@angular/http';
import {HttpClient,HttpHeaders } from '@angular/common/http';


@Injectable()
export class ItoUserManualService{
    private _getAdminForm:string="http://10.0.0.4:8082/ITO/admin/getAdminForm";
    private _getUserManual: string = "http://10.0.0.4:8082/ITO/admin/getUserManual";
    private _downloadUserManual: string = "http://10.0.0.4:8082/ITO/admin/downloadFile";
    private _getUpload: string = "http://10.0.0.4:8082/ITO/admin/getUpload";
    private _getGrid: string = "http://10.0.0.4:8082/ITO/admin/getGrid";


    constructor(private _http:Http){
    
    }

    getAdminForm(): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getAdminForm,' ', options).map(this.extractData);
    }

    getGrid(): Observable<any> {
      
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getGrid,' ', options).map(this.extractData);
    }

    getUserManual(userManual): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            return this._http.post(this._getUserManual, userManual, options).map(this.extractData);
    }
    downloadFile(userMan: number): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
            //options.responseType = ResponseContentType.Blob;
            const httpOptions = {
            'responseType' : 'blob' as 'json'
            };
            return this._http.post(this._downloadUserManual, (userMan) , options).map(this.extractData);
    }
    getUpload(userManual): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
            let options = new RequestOptions({ headers: headers });
           // options.responseType = ResponseContentType.Blob;
            return this._http.post(this._getUpload, userManual , options).map(this.extractData);
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

       
        private extractContent(res: Response) {
            let blob: Blob = res.blob();
            window['saveAs'](blob, 'test.pdf');
        }

      // handle error
    
        private extractData(res: Response) {
            let body=res.json();
            // console.log(body);
             return body || {};
        }
}
    