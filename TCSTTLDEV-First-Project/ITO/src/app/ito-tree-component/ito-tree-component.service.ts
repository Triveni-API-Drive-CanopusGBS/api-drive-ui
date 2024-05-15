import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { consultantDetails } from '../ito-create-consultant/ito-create-consultant';
import { ITOuserHomeService } from '../ito-user-home/ito-user-home.service';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';


@Injectable()
export class ITOtreeComponentService {

    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/quotHome";
    private _getOneLineBom: string = "http://10.0.0.4:8082/ITO/quot/getOneLineBom";
    private _getF2FData: string = "http://10.0.0.4:8082/ITO/quot/getF2FTreeStructure";
    private _SCUrl: string = "http://10.0.0.4:8082/ITO/quot/getF2FShopCon";
    private _OHUrl: string = "http://10.0.0.4:8082/ITO/quot/getF2FOverHeadCost";
    private _editOneLineBom: string = "http://10.0.0.4:8082/ITO/quot/editOneLineBom";
    private _saveCProject: string = "http://10.0.0.4:8082/ITO/quot/saveCProject";
    data: any;

    constructor(private _http: Http, private _ITOuserHomeService: ITOuserHomeService) {
        this.data = [
            {
                "data":
                    [
                        {
                            "data": {
                                "name": "Documents",
                                "size": "75kb",
                                "type": "Folder"
                            },
                            "children": [
                                {
                                    "data": {
                                        "name": "Work",
                                        "size": "55kb",
                                        "type": "Folder"
                                    },
                                    "children": [
                                        {
                                            "data": {
                                                "name": "Expenses.doc",
                                                "size": "30kb",
                                                "type": "Document"
                                            }
                                        },
                                        {
                                            "data": {
                                                "name": "Resume.doc",
                                                "size": "25kb",
                                                "type": "Resume"
                                            }
                                        }
                                    ]
                                },
                                {
                                    "data": {
                                        "name": "Home",
                                        "size": "20kb",
                                        "type": "Folder"
                                    },
                                    "children": [
                                        {
                                            "data": {
                                                "name": "Invoices",
                                                "size": "20kb",
                                                "type": "Text"
                                            }
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            "data": {
                                "name": "Pictures",
                                "size": "150kb",
                                "type": "Folder"
                            },
                            "children": [
                                {
                                    "data": {
                                        "name": "barcelona.jpg",
                                        "size": "90kb",
                                        "type": "Picture"
                                    }
                                },
                                {
                                    "data": {
                                        "name": "primeui.png",
                                        "size": "30kb",
                                        "type": "Picture"
                                    }
                                },
                                {
                                    "data": {
                                        "name": "optimus.jpg",
                                        "size": "30kb",
                                        "type": "Picture"
                                    }
                                }
                            ]
                        }
                    ]
            }
        ];
    }

    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUrl, ' ', options).map(this.extractData);
    }
    getF2FData(saveBasicDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getF2FData, saveBasicDetails, options).map(this.extractData);
    }
    getOneLineBom(saveBasicDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._getOneLineBom, saveBasicDetails, options).map(this.extractData);
    }
    // save(){
    //     this.rectEvent.emit("save");
    //     }
    // sendMessage(message) {
    //      this.subject.next({ message });
    //     }
    //          
    // clearMessage() {
    //     this.subject.next();
    //   }
    //          
    //   getMessage(): Observable<any> {
    //      return this.subject.asObservable();
    //     }

    GetShopConvPop(resSC): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._SCUrl, resSC, options)
            .map(this.extractData);
    }

    GetOverheadCostPop(resOH): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._OHUrl, resOH, options)
            .map(this.extractData);
    }
    editOneLineBom(res){
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._editOneLineBom, res , options)
            .map(this.extractData);

    }

    saveCProject(saveBasicDetails): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._saveCProject, saveBasicDetails, options).map(this.extractData);
    }


    getData() {
        return this.data;
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }
}