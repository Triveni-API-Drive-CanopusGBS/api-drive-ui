import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { Http, Response } from '@angular/http';
import { LoginValues } from './app.component.login';
import 'rxjs/add/operator/catch';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ITOLoginService {
    usersRegionList: Array<any>;
    loggedUserDetails: any;
    userDetailsList: any;
    usersRolesList: any;
    isStandAlone: boolean = true;
    dialogMsgApp: boolean = false;
    dialogMsgVal: string;
    errdisplay: boolean = false;
    costEst: boolean = false;
    private userStatus = new Subject<any>();
    private quotStatus = new Subject<any>();
    status: boolean;
    newWindowOpened: boolean = false;
    frameNm:string = '';
    capacity:number=0;
    typeCust: string='';

    private _Loginurl: string = "http://10.0.0.4:8082/ITO/login/loginUser";
    private _quotUrl: string = "http://10.0.0.4:8082/ITO/quot/quotHome";
    private _fgtPwdUrl: string = "http://10.0.0.4:8082/ITO/login/forgotPassword";
    private _userUrl: string = "http://10.0.0.4:8082/ITO/quot/getUserDet";
    constructor(private _http: Http) {
    }
    LoginMethod(LoginValues: LoginValues): Observable<LoginValues> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._Loginurl, LoginValues, options).map(this.extractData);

    }
    forgotPwdMethod(emailId: LoginValues): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._fgtPwdUrl, emailId, options).map(this.extractData);
    }
    getQuotationList(): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._quotUrl, ' ', options).map(this.extractData);
    }
    saveUserId(userId): Observable<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this._http.post(this._userUrl, userId, options).map(this.extractData);
    }
    openSuccMsg(msg) {
        this.dialogMsgApp = true;
        this.dialogMsgVal = msg;

    }
    sendLoginStatus(status) {
        this.userStatus.next({ ls: status });
    }
    getLoginStatus(): Observable<any> {
        return this.userStatus.asObservable();
    }
    sendQuotStatus(status) {
        this.quotStatus.next({ quotS: status });
    }
    getQuotStatus(): Observable<any> {
        return this.quotStatus.asObservable();
    }

    private extractData(res: Response) {
        let body = res.json();
        // console.log(body);
        return body || {};
    }

}