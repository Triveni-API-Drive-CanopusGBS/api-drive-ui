import { Component, OnInit, Input, ViewChild, HostListener, AfterContentChecked, AfterViewInit, OnChanges } from '@angular/core';
import { ITOLoginService } from './app.component.service';
import { LoginValues } from './app.component.login';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router, Params, NavigationEnd, Event } from '@angular/router';
import { DEFAULT_SCROLL_TIME } from '@angular/cdk/scrolling';
import { Inject } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DialogModule } from 'primeng/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { MenuItem } from 'primeng/api';
import { LOCAL_STORAGE, SESSION_STORAGE, WebStorageService } from 'angular-webstorage-service';
import { Time } from '@angular/common';
import { fadeInContent } from '@angular/material';
import { ITOcustomerRequirementService } from './ito-customer-requirement/ito-customer-requirement.service';
import { ITOAddOnComponentsService } from './add-on-components/add-on-components.service';
import { ITOeditQoutService } from './ito-edit-quot/ito-edit-quot.service';
import { ITOScopeOfSupplyService } from './ito-scope-of-supply/ito-scope-of-supply.service';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  subscription: any;
  errdisplay: boolean = false;
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  enableerrormsg: boolean;
  errorMessage: string = "";
  DisableLogin: boolean = false;
  LoginRes: LoginValues;
  display: boolean = false;
  loggedUserName: string = "";
  loggedUserDep: string = "";
  loggedUserRole: any;
  UserLastLogin: string = "";
  showCustInfo: boolean = false;
  userName: string = "";
  password: string = "";
  rememberMe: boolean;
  public data: any = [];
  hideDD: boolean = true;
  imag: string;
  var: string;
  users: string = 'user';
  userDetail: string = 'userDetail';
  customerReqrmnt: string = 'customerReq';
  endUserDetail: string = 'endUserDetail';
  custdetails: string = 'custdetails';
  currentRole: string = 'selRole';
  currentRoleId: string = 'selRoleId';
  currentRoleDesc: string = 'selRoleDesc';
  frgterrorMessage: any;
  userList: any;
  usersRolesList: any;
  userRoles: Array<any> = [];

  startTime: any;
  endTime: any;


  // ------------ List of Roles 

  selectedRole: any;
  selectedRoleId: any;

  admin: boolean = false;
  qoutReviewer: boolean = false;
  quotApprover: boolean = false;
  quotEdit: boolean = false;
  dboEleEdit: boolean = false;
  dboEleApp: boolean = false;
  dboEleRev: boolean = false;

  dboMechEdit: boolean = false;
  dboMechApp: boolean = false;
  dboMechRev: boolean = false;

  ecEdit: boolean = false;
  uboEdit: boolean = false;
  ecApp: boolean = false;
  f2fEdit: boolean = false;
  f2fApp: boolean = false;

  PFedit: boolean = false;
  PFApp: boolean = false;
  PFrev: boolean = false;

  projEdit: boolean = false;
  projApp: boolean = false;

  transpEdit: boolean = false;
  transpApp: boolean = false;
  transRev: boolean = false;

  update: boolean = false;
  LogOutdisplay: boolean = false;
  dispRoles: boolean = false;
  turbineCost: boolean = false;
  //uboEdit:boolean  =false;
  selectedRoleDesc: any;
  oldComms: any;

  scopeofsupp: string = 'scopeOfsup';
  addOnDetails: string = 'addOnDetail';
  packLocal: string = 'packLocal';
  transLocal: string = 'transLocal';
  ECData: string = 'ecKey';
  newCostEst: string = 'newCostEst';
  dboMechLoc: string = 'dboMech';
  dboComSecALoc: string = 'comSecA'; // local storage value
  dboPerfLoc: string = 'dboPerf';
  dboMechFull: string = 'dboMechFull'; 
  sparesLocal: string = 'sparesLocal';
  varCostLocal: string = 'varCostLocal';
  F2FSap: string = 'F2FSap';
  projectCostLocal: string = 'projectCostLocal';
  f2fCostData: string = 'f2fCostData';
  oneLineLoc: string = 'oneLineLoc';
  dboEleFull: string = 'dboEleFull';
  F2FTurbine: string = 'F2FTurbine';
  dboMechAuxLoc: string = 'dboAuxMech';
  generalInput: string = 'generalInputList';
  MechExpScope: string = 'MechExpScope';
  EleExtdScope: string = 'EleExtdScope';
  EleCiExtdScope: string = 'EleCiExtdScope';
  dboEleCIAuxFull: string = 'dboEleCIAuxFull';
  dboEleCIFull: string = 'dboEleCIFull';
  dboEleAuxFull: string = 'dboEleAuxFull';
  dialogMsgApp: boolean = false;
  dialogMsgVal: string;
  costEst: boolean = false;
  newWindowOpened: boolean = false;
  dispPrevComments: boolean = false;
  exclusion: string = 'exclusion'; // local storage value
  quaility: string = 'quaility'; // local storage value
  scopeOf: string = 'scopeOf'; // local storage value
  supplier: string = 'supplier'; // local storage value
  tender: string = 'tender'; // local storage value
  terminal: string = 'terminal'; // local storage value
  tendrAttach: string = 'tendrAttach';//Local storaghe for tender drawing new
  clarifiAttach: string = 'clarifiAttach';// Local storage for clarifications and deviations
  hideCQ: boolean = true;

  constructor(private router: Router, private route: ActivatedRoute,
    private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService,
    @Inject(SESSION_STORAGE) private sessionStore: WebStorageService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOScopeOfSupplyService: ITOScopeOfSupplyService,
    private _ITOAddOnComponentsService: ITOAddOnComponentsService, private _ITOeditQoutService: ITOeditQoutService) {
      // this.router.events.pipe(filter((rs):rs is NavigationEnd => rs instanceof NavigationEnd)).subscribe(event => {
      //   console.log(event);
      //   if(          
      //     event.id === 1 &&
      //     event.url === event.urlAfterRedirects
      //   )
      //   {
      //    this.gotoHome();
      //   }
      // })
      this.subscription = this.router.events.subscribe((event:Event) => {
        if(event instanceof NavigationEnd ){
          console.log(event.url);
          if(          
                event.id === 1 &&
                event.url === event.urlAfterRedirects
              )
              {
               this.gotoHome();
              }
        }
      });
    document.cookie = "new:foo";
    console.log(document.cookie);
    // window.addEventListener('focus', this.focusFn());
    // window.addEventListener('blur', this.blurfn());
    this.data[this.users] = this.storage.get(this.users);
    console.log(this.storage[this.userDetail]);
    console.log(this.storage.get(this.currentRole));
    console.log(this.storage.get(this.currentRoleId));

    if (this.data[this.users] != null) {
      console.log(this.data[this.users].loginForm.emailId);
      this.userName = this.data[this.users].loginForm.emailId;
      this.password = this.data[this.users].loginForm.password;
      this.rememberMe = this.data[this.users].loginForm.rememberPassword;
    }


    this.data[this.userDetail] = this.storage.get(this.userDetail);
    if (this.data[this.userDetail]) {
      // console.log(this.data[this.userDetail]);
      // this.router.navigate(['/HomePage']);
      this.admin = false;
      this.quotApprover = false;
      this.qoutReviewer = false;
      this.quotEdit = false;
      this.enableerrormsg = false;
      if (this.data[this.userDetail].image != null) {
        this.imag = "data:image/jpeg;base64," + this.data[this.userDetail].image;
      }
      else {
        this.imag = "data:image/png;base64,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";
      }
      this.loggedUserName = this.data[this.userDetail].name;
      this.loggedUserDep = this.data[this.userDetail].dept;
      this.loggedUserRole = this.data[this.userDetail].userRolesList;
      this.userRoles = this.data[this.userDetail].userRolesList;
      this.selectedRoleDesc = this.storage.get(this.currentRoleDesc);

      switch (this.storage.get(this.currentRole)) {
        case "QUOT_APPROVER": {
          // this.update = true;
          this.quotApprover = true;
          break;
        }
        case "ADMIN": {
          this.admin = true;
          this.dboEleEdit = true;
          this.dboEleApp = true;
          this.dboMechEdit = true;
          this.dboMechApp = true;
          this.ecEdit = true;
          // this.uboEdit = true;
          this.ecApp = true;
          this.f2fApp = true;
          this.f2fEdit = true;
          this.PFedit = true;
          this.PFApp = true;
          this.projApp = true;
          this.projEdit = true;
          break;
        }
        case "QUOT_REVIWER": {
          // this.update = true;
          this.qoutReviewer = true;
          break;
        }
        case "QUOT_EDIT": {
          // this.update = true;
          this.quotEdit = true;
          break;
        }
        case "EC_EDIT": {
          // this.update = true;
          this.ecEdit = true;
          break;
        }
        case "F2F_APPROVER": {
          this.update = true;
          this.quotEdit = true;
          this.qoutReviewer = true;
          break;
        }
        case "F2F_EDIT": {
          // this.update = true;
          this.qoutReviewer = true;
          break;
        }
        case "PKG_FW_EDIT": {
          // this.update = true;
          this.PFedit = true;
          break;
        }
        case "PKG_FW_REVIWER": {
          this.update = true;
          this.PFrev = true;
          break;
        }
        case "PKG_FW_APPROVER": {
          this.update = true;
          this.PFApp = true;
          break;
        }
        case "PROJECT_APPROVER": {
          this.update = true;
          this.quotEdit = true;
          this.qoutReviewer = true;
          break;
        }
        case "PROJECT_EDIT": {
          this.update = true;
          this.quotEdit = true;
          this.qoutReviewer = true;
          break;
        }
        case "TRANS_EDIT": {
          this.update = true;
          this.transpEdit = true;
          break;
        }
        case "TRANS_APPROVER": {
          this.update = true;
          this.transpApp = true;
          break;
        }
        case "TRANS_REVIWER": {
          this.update = true;
          this.transRev = true;
          break;
        }
        case "DBO_MECH_EDIT": {
          // this.update = true;
          this.dboMechEdit = true;
          break;
        }
        case "DBO_MECH_APPROVER": {
          this.update = true;
          this.dboMechApp = true;
          break;
        }
        case "DBO_MECH_REVIWER": {
          this.update = true;
          this.dboMechRev = true;
          break;
        }
        case "DBO_ELE_EDIT": {
          // this.update = true;
          this.dboEleEdit = true;
          break;
        }
        case "DBO_ELE_APPROVER": {
          this.update = true;
          this.dboEleApp = true;
          break;
        } case "DBO_ELE_REVIWER": {
          this.update = true;
          this.dboEleRev = true;
          break;
        }
        case "FINANCE_EDIT": {
          this.update = true;
          this.turbineCost = true;
          break;
        }
        case "FINANCE_APPROVER": {
          this.turbineCost = true;
          break;
        }
        case "FINANCE_REVIWER": {
          this.update = true;

          this.turbineCost = true;
          break;
        }
        case "UBO_EDIT": {
          // this.update = true;
          this.uboEdit = true;
          this.turbineCost = true;
          break;
        }
        case "UBO_APPROVER": {
          // this.update = true;
          this.uboEdit = true;
          break;
        }
        case "UBO_REVIWER": {
          // this.update = true;
         this.uboEdit = true;
          break;
        }
        default: {
          break;
        }
      }true
      // if (window.toolbar.visible === true) {
      //   if (this.storage.get(this.currentRole) !== "QUOT_EDIT") {
      //     this.router.navigate(['/defaultHome']);
      //   }
      //   else {
      //     this.router.navigate(['/HomePage']);
      //   }
      //   this.costEst = false;
      // }
      // else {
      //   this.costEst = true;
      //   console.log(this.costEst);
      // }

      this._ITOLoginService.loggedUserDetails = this.data[this.userDetail].userId;
      this.DisableLogin = this.data[this.userDetail].active;
      console.log(this.data);
    }
    else if (!this.data[this.userDetail]) {
      this.DisableLogin = false;
      this.router.navigate(['LoginPage']);
      console.log(this.data);
    }
    this._ITOLoginService.getLoginStatus().subscribe(sts => {
      console.log("login status" + sts.ls);
    })


  }


  ngOnInit() {
    let sUsrAg = navigator.userAgent;
    let sBrowser = navigator.userAgent;
    console.log(sUsrAg);
    if (sUsrAg.indexOf("Firefox") > -1) {
      sBrowser = "Mozilla Firefox";
      //"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:61.0) Gecko/20100101 Firefox/61.0"
    } else if (sUsrAg.indexOf("Opera") > -1) {
      sBrowser = "Opera";
    } else if (sUsrAg.indexOf("Trident") > -1) {
      sBrowser = "Microsoft Internet Explorer";
      //"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; Zoom 3.6.0; wbx 1.0.0; rv:11.0) like Gecko"
    } else if (sUsrAg.indexOf("Edge") > -1) {
      sBrowser = "Microsoft Edge";
      //"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299"
    } else if (sUsrAg.indexOf("Chrome") > -1) {
      sBrowser = "Apple Safari";
      //"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/66.0.3359.181 Chrome/66.0.3359.181 Safari/537.36"
    } else if (sUsrAg.indexOf("Safari") > -1) {
      sBrowser = "Google Chrome or Chromium";
      //"Mozilla/5.0 (iPhone; CPU iPhone OS 11_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1 980x1306"
    } else {
      sBrowser = "unknown";
    }
    history.pushState(null, null, location.href);
    window.onpopstate = function () {
      history.go(1);
    };
    /// Code for preventing refresh 

    // window.addEventListener('beforeunload', this.onbeforeunlod);
    // function onbeforeunlod(event) {
    //   console.log('>>>> onbeforeunload called');
    //   console.log(event.cancelable);
    //   console.log(window.location.pathname);
    //   // console.log(event);
    //   if (event.cancelable == true) {
    //     event.preventDefault();
    //     event.returnValue = "sumne";
    //   }
    //   else {
    //     console.log('not possible');
    //   }
    //   this.costEst = false;
    // };

  }
  ngAfterContentChecked() {
    window.onfocus = (focusFnn): any => {
      this.endTime = new Date().getMinutes();
      if (this.endTime != undefined) {
        let diffInMs: number = (this.endTime - this.startTime);
        console.log(diffInMs);
        if (diffInMs >= 60) {
          this.data[this.userDetail] = this.storage.get(this.userDetail);
          if (this.data[this.userDetail]) {
            this.LogOutFinal();
          }
          else {
            this.startTime = this.endTime;
          }
        }
      }
    }
    window.onblur = (blrFnn): any => {
      this.startTime = new Date().getMinutes();
    }
    this.dialogMsgApp = this._ITOLoginService.dialogMsgApp;
    this.errdisplay = this._ITOLoginService.errdisplay;
    this.dialogMsgVal = this._ITOLoginService.dialogMsgVal;
    this.dispPrevComments = this._ITOAddOnComponentsService.dispPrevComments;
    this.oldComms = this._ITOAddOnComponentsService.oldComms;
    if (this.storage.get(this.userDetail) != null) {
    }
    else {
      window.close();
    }
  }
  ngAfterViewInit() {

  }

  onbeforeunlod() {
    console.log('>>>> onbeforeunload called');
    console.log(event.cancelable);
    console.log(window.location.pathname);
    if (event.cancelable == true) {
      event.preventDefault();
    }
    else {
      console.log('not possible');
    }
  }
  checkQuotS(event) {
    console.log('called');
    this._ITOLoginService.sendQuotStatus(false);
  }
  close() {
    console.log("close");
    this._ITOLoginService.dialogMsgApp = false;
    // window.location.reload();
  }

  newCostEstimation() {
    this._ITOLoginService.frameNm = '';
    this._ITOLoginService.capacity=0;
    this._ITOLoginService.typeCust='';
    this._ITOeditQoutService.checkEdit=true;
    this._ITOLoginService.sendQuotStatus(true);
    this._ITOcustomerRequirementService.sendclrBtnStatus(false, false, false, false, false, false, false, false, false,false, false, false,false,false);
    // window.open('#/CostEstimation/CustomerInformation', 'costWindow', "status=1,toolbar=0");
    this.router.navigate(['/CostEstimation/CustomerInformation']);
    // added by kavya
    this.saveInLocal(this.newCostEst, true);
    this.storage.remove(this.customerReqrmnt);
    this.storage.remove(this.endUserDetail);
    this.storage.remove(this.custdetails);
    this.storage.remove(this.scopeofsupp);
    this.storage.remove(this.addOnDetails);
    this.storage.remove(this.packLocal);
    this.storage.remove(this.transLocal);
    this.storage.remove(this.ECData);
    this.storage.remove(this.sparesLocal);
    this.storage.remove(this.varCostLocal);
    this.storage.remove(this.projectCostLocal);
    this.storage.remove(this.dboMechLoc);
    this.storage.remove(this.dboComSecALoc);
    this.storage.remove(this.dboPerfLoc);
    this.storage.remove(this.dboMechFull);
    this.storage.remove(this.exclusion);
	 this.storage.remove(this.quaility);
	  this.storage.remove(this.scopeOf);
	   this.storage.remove(this.supplier);
	    this.storage.remove(this.tender);
		 this.storage.remove(this.terminal);
    this.storage.remove(this.oneLineLoc);
    this.storage.remove(this.dboEleFull);
    this.storage.remove(this.f2fCostData);
    this.storage.remove(this.F2FSap);
    this.storage.remove(this.F2FTurbine);
    this.storage.remove(this.MechExpScope);
    this.storage.remove(this.EleCiExtdScope);
    this.storage.remove(this.EleExtdScope);
    this.storage.remove(this.dboMechAuxLoc);
    this.storage.remove(this.generalInput);
    this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.dboEleAuxFull);
    this.storage.remove(this.tendrAttach);
    this.storage.remove(this.clarifiAttach);
    this._ITOcustomerRequirementService.editFlag = false;
    this._ITOcustomerRequirementService.saveBasicDet = '';
    this._ITOeditQoutService.mechExtScpList = [];
    this._ITOeditQoutService.dboEleAuxData = [];
    this._ITOeditQoutService.dboEleAuxItmOthers = [];
    this._ITOeditQoutService.dboEleCiData = [];
    this._ITOeditQoutService.dboEleCIItmOthers = [];
    this._ITOeditQoutService.dboEleCIAuxData = [];
    this._ITOeditQoutService.dboEleCIAuxItmOthers = [];
    this._ITOeditQoutService.dboEleData = [];
    this._ITOeditQoutService.cirListData = [];
    this._ITOeditQoutService.fixedListData = [];
    this._ITOeditQoutService.identListData = [];
    this._ITOeditQoutService.itemListData = [];
    this._ITOeditQoutService.dboMechData = [];
    this._ITOeditQoutService.eleExtScopeList = [];
    this._ITOeditQoutService.eleCIExtScopeList = [];
    this._ITOeditQoutService.dboEleItmOthers = [];
    this._ITOeditQoutService.dboDataOthers = [];
    this._ITOeditQoutService.dboEleSplAddOnList = [];
    this._ITOeditQoutService.dboEleOthers = [];
    this._ITOeditQoutService.dboEleDataAddOn = [];
    this._ITOeditQoutService.dboEleNewAddOns = [];
    this._ITOeditQoutService.dboF2fData = [];
    this._ITOeditQoutService.dboF2fNewAddOns = [];
    this._ITOeditQoutService.f2fOthersItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemTypeList = [];
    this._ITOScopeOfSupplyService.sampleScope = [];
    this._ITOeditQoutService.qualitiasurance = [];
    this._ITOeditQoutService.scopeofspares = [];
    this._ITOeditQoutService.tenderDraw = [];
    this._ITOeditQoutService.terminalpo = [];
    this._ITOeditQoutService.exclusionLi = [];
    this._ITOeditQoutService.subSuppliersList = [];
  }

  gotoHome() {
    if (!(this.quotEdit || this.quotApprover ||  this.qoutReviewer)) {
      this.router.navigate(['/defaultHome']);
    }
    else {
      this.router.navigate(['/HomePage']);
    }
    this.costEst = false;
  }
  setStandAlone() {
    this._ITOLoginService.isStandAlone = true;
    this.storage.remove(this.customerReqrmnt);
    this.storage.remove(this.endUserDetail);
    this.storage.remove(this.custdetails);
    this.storage.remove(this.scopeofsupp);
    this.storage.remove(this.addOnDetails);
    this.storage.remove(this.packLocal);
    this.storage.remove(this.transLocal);
    this.storage.remove(this.ECData);
    this.storage.remove(this.sparesLocal);
    this.storage.remove(this.varCostLocal);
    this.storage.remove(this.f2fCostData);
    this.storage.remove(this.F2FSap);
    this.storage.remove(this.projectCostLocal);
    this.storage.remove(this.dboMechLoc);
    this.storage.remove(this.dboComSecALoc);
    this.storage.remove(this.dboPerfLoc);
    this.storage.remove(this.MechExpScope);
    this.storage.remove(this.dboMechFull);
    this.storage.remove(this.exclusion);
	 this.storage.remove(this.quaility);
	  this.storage.remove(this.scopeOf);
	   this.storage.remove(this.supplier);
	    this.storage.remove(this.tender);
		 this.storage.remove(this.terminal);
    this.storage.remove(this.oneLineLoc);
    this.storage.remove(this.generalInput);
    this.storage.remove(this.clarifiAttach);
    this.storage.remove(this.tendrAttach);
    this._ITOcustomerRequirementService.editFlag = false;
    this._ITOcustomerRequirementService.saveBasicDet = '';
    this._ITOeditQoutService.dboMechData = [];
    this._ITOeditQoutService.dboEleItmOthers = [];
    this._ITOeditQoutService.qualitiasurance = [];
    this._ITOeditQoutService.scopeofspares = [];
    this._ITOeditQoutService.tenderDraw = [];
    this._ITOeditQoutService.terminalpo = [];
    this._ITOeditQoutService.exclusionLi = [];
    this._ITOeditQoutService.subSuppliersList = [];
    this._ITOeditQoutService.dboEleData = [];
    this._ITOeditQoutService.cirListData = [];
    this._ITOeditQoutService.fixedListData = [];
    this._ITOeditQoutService.identListData = [];
    this._ITOeditQoutService.itemListData = [];
    this._ITOeditQoutService.mechExtScpList =[];
    this._ITOeditQoutService.eleExtScopeList = [];
    this._ITOeditQoutService.eleCIExtScopeList = [];
    this._ITOeditQoutService.dboF2fData = [];
    this._ITOeditQoutService.dboF2fNewAddOns = [];
    this._ITOeditQoutService.f2fOthersItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemTypeList = [];
    this._ITOScopeOfSupplyService.sampleScope = [];
  }
  logout() {
    this.LogOutdisplay = true;
  }
  LogOutFinal() {
    this.LogOutdisplay = false;
    this.DisableLogin = false;
    this.storage.remove(this.customerReqrmnt);
    this.storage.remove(this.endUserDetail);
    this.storage.remove(this.custdetails);
    this.storage.remove(this.scopeofsupp);
    this.storage.remove(this.addOnDetails);
    this.storage.remove(this.packLocal);
    this.storage.remove(this.transLocal);
    this.storage.remove(this.ECData);
    this.storage.remove(this.sparesLocal);
    this.storage.remove(this.varCostLocal);
    this.storage.remove(this.f2fCostData);
    this.storage.remove(this.F2FSap);
    this.storage.remove(this.projectCostLocal);
    this.storage.remove(this.dboMechLoc);
    this.storage.remove(this.dboComSecALoc);
    this.storage.remove(this.dboPerfLoc);
    this.storage.remove(this.dboMechFull);
    this.storage.remove(this.exclusion);
	 this.storage.remove(this.quaility);
	  this.storage.remove(this.scopeOf);
	   this.storage.remove(this.supplier);
	    this.storage.remove(this.tender);
		 this.storage.remove(this.terminal);
    this.storage.remove(this.oneLineLoc);
    this.storage.remove(this.userDetail);
    this.storage.remove(this.dboEleFull);
    this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.dboEleAuxFull);
    this.storage.remove(this.F2FTurbine);
    this.storage.remove(this.MechExpScope);
    this.storage.remove(this.EleCiExtdScope);
    this.storage.remove(this.EleExtdScope);
    this.storage.remove(this.dboMechAuxLoc);
    this.storage.remove(this.generalInput);
    this.storage.remove(this.dboEleAuxFull);
    this.storage.remove(this.dboEleCIFull);
    this.storage.remove(this.dboEleCIAuxFull);
    this.storage.remove(this.clarifiAttach);
    this.storage.remove(this.tendrAttach);
    this._ITOcustomerRequirementService.editFlag = false;
    this._ITOcustomerRequirementService.saveBasicDet = '';
    this._ITOeditQoutService.dboEleAuxData = [];
    this._ITOeditQoutService.dboEleAuxItmOthers = [];
    this._ITOeditQoutService.dboEleCiData = [];
    this._ITOeditQoutService.dboEleCIItmOthers = [];
    this._ITOeditQoutService.dboEleCIAuxData = [];
    this._ITOeditQoutService.dboEleCIAuxItmOthers = [];
    this._ITOeditQoutService.dboMechData = [];
    this._ITOeditQoutService.dboEleItmOthers = [];
    this._ITOeditQoutService.dboEleData = [];
    this._ITOeditQoutService.mechExtScpList = [];
    this._ITOeditQoutService.qualitiasurance = [];
    this._ITOeditQoutService.scopeofspares = [];
    this._ITOeditQoutService.tenderDraw = [];
    this._ITOeditQoutService.terminalpo = [];
    this._ITOeditQoutService.exclusionLi = [];
    this._ITOeditQoutService.subSuppliersList = [];
    this._ITOeditQoutService.eleExtScopeList = [];
    this._ITOeditQoutService.eleCIExtScopeList = [];
    this._ITOeditQoutService.dboF2fData = [];
    this._ITOeditQoutService.dboF2fNewAddOns = [];
    this._ITOeditQoutService.f2fOthersItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemList = [];
    this._ITOeditQoutService.f2fOthersSubItemTypeList = [];
    this._ITOeditQoutService.cirListData = [];
    this._ITOeditQoutService.fixedListData = [];
    this._ITOeditQoutService.identListData = [];
    this._ITOeditQoutService.itemListData = [];
    this._ITOScopeOfSupplyService.sampleScope = [];
    window.location.reload();

    this._ITOLoginService.sendLoginStatus(false);
    this.router.navigate(['/LoginPage']);
  }
  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.data[key] = this.storage.get(key);
  }
  saveInSession(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
    this.data[key] = this.storage.get(key);
  }
  importPassword(key): void {
    this.data[key] = this.storage.get(key);
    console.log(this.data[key].loginForm.emailId);
    this.password = this.data[this.users].loginForm.password;
    this.rememberMe = this.data[this.users].loginForm.rememberPassword;
  }
  displaydropDown() {
    if (this.costEst == false) {
      switch (this.hideDD) {
        case true:
          this.hideDD = false;
          break;
        case false:
          this.hideDD = true;
          break;
        default:
          this.hideDD = true;
      }
    }
    else if (this.costEst == true) {
      this.hideDD = true;
    }
    this.hideDD = true;

  }
  
  dropDownCreate(){
    console.log("1111");
    this.hideCQ=true;

  }

  hidedropDown() {
    this.hideDD = false;
  }
  hidedropDownCQ() {
    this.hideCQ = true;
  }

  disable() {
    this.hideDD = false;
    console.log("wd");
  }
  disableCQ(){
    this.hideCQ=false;
    console.log("wd1");
  }
  showDialog() {
    this.display = true;
  }
  hideCust() {
    this.showCustInfo = false;
  }
  showCust() {
    this.showCustInfo = true;
  }
  fgtPwd(fgtPwdFormvalue) {
    console.log(fgtPwdFormvalue);
    this._ITOLoginService.forgotPwdMethod(fgtPwdFormvalue).subscribe(resp => {
      console.log(resp);
      if (resp.successCode == 0) {
        this.frgterrorMessage = "Password is sent to the entered Email Id";
      } else if (resp.successCode == 1) {
        this.frgterrorMessage = resp.successMsg;
      }
    })
  }

  ITOLogin(loginForm) {
    this.errorMessage = '';
    console.log(loginForm);
    this._ITOLoginService.LoginMethod(loginForm).subscribe(res => {
      console.log(res);
      this.LoginRes = res;
      if (res.successCode == '0') {
        this.dispRoles = true;
        if (res.image != null) {
          this.imag = "data:image/jpeg;base64," + res.image;
        }
        else {
          this.imag = "data:image/png;base64,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";

        }
        this.loggedUserName = res.name;
        this.loggedUserDep = res.dept;
        this.loggedUserRole = res.userRolesList;
        this._ITOLoginService.loggedUserDetails = res.userId;
        this._ITOLoginService.usersRegionList = res.userRegionsList;
        this.userRoles = res.userRolesList;
        this._ITOLoginService.getQuotationList().subscribe(resp => {
          console.log(res);
          this._ITOLoginService.userDetailsList = resp.userDetailsList;
          this._ITOLoginService.usersRolesList = resp.userDetailsList.regionsVal;
          this.userList = this._ITOLoginService.userDetailsList;
          this.usersRolesList = this._ITOLoginService.usersRolesList;

        });
        console.log(this.userRoles);
        this.saveInLocal(this.userDetail, res);

        // console.log(this._ITOLoginService.usersRegionList);
        if (loginForm.rememberPassword == true) {
          this.saveInLocal(this.users, { loginForm });
          console.log(this.data);
        } else {
          console.log("not remembered");

        }

      }
      else {
        this.DisableLogin = false;
        this.router.navigate(['/']);
        this.enableerrormsg = true;
        this.errorMessage = res.successMsg;

      }
    })
    // this._ITOLoginService.openSuccMsg("open");
  }
  roleCheck1(role) {
    console.log(role);
    for (let r = 0; r < this.userRoles.length; r++) {
      if (this.userRoles[r].value === role) {
        this.selectedRoleId = this.userRoles[r].key;
        this.selectedRole = this.userRoles[r].code;
        this.selectedRoleDesc = this.userRoles[r].value;
      }
    }
    this.saveInLocal(this.currentRole, this.selectedRole);
    this.saveInLocal(this.currentRoleId, this.selectedRoleId);
    this.saveInLocal(this.currentRoleDesc, this.selectedRoleDesc);
    console.log(this.selectedRole);
    this.quotApprover = false;
    this.admin = false;
    this.dboEleEdit = false;
    this.dboMechEdit = false;
    this.dboMechApp = false;
    this.uboEdit = false;
    this.ecApp = false;
    this.f2fApp = false;
    this.f2fEdit = false;
    this.PFedit = false;
    this.PFApp = false;
    this.projApp = false;
    this.projEdit = false;
    this.qoutReviewer = false;
    this.quotEdit = false;
    this.update = false;
    this.ecEdit = false;
    this.update = false;
    this.uboEdit = false;
    this.turbineCost = false;
    this.ecApp = false;
    this.f2fApp = false;
    this.turbineCost = false;
    this.update = false;
    this.f2fEdit = false;
    this.update = false;
    this.PFedit = false;

    this.PFrev = false;

    this.PFApp = false;

    this.projApp = false;

    this.projEdit = false;

    this.update = false;
    this.transpEdit = false;

    this.transpApp = false;

    this.transRev = false;

    this.update = false;
    this.dboMechEdit = false;

    this.dboMechApp = false;

    this.dboMechRev = false;

    this.update = false;
    this.dboEleApp = false;
    this.dboEleRev = false;
    this.turbineCost = false;
    this.uboEdit = false;

    switch (this.selectedRole) {
      case "QUOT_APPROVER": {
        // this.update = true;
        this.quotApprover = true;
        break;
      }
      case "QUOT_REVIWER": {
        // this.update = true;
        this.qoutReviewer = true;
        break;
      }
      case "QUOT_EDIT": {
        // this.update = true;
        this.quotEdit = true;
        break;
      }
      case "ADMIN": {
        this.admin = true;
        // this.quotEdit = true;
        // this.quotApprover = true;
        // this.qoutReviewer = true;
        this.dboEleEdit = true;
        this.dboEleApp = true;
        this.dboMechEdit = true;
        this.dboMechApp = true;
        // this.uboEdit = true;
        this.ecApp = true;
        this.f2fApp = true;
        this.f2fEdit = true;
        this.PFedit = true;
        this.PFApp = true;
        this.projApp = true;
        this.projEdit = true;
        break;
      }
      case "EC_APPROVER": {
        this.update = true;
        this.ecApp = true;
        break;
      }
      case "EC_EDIT": {
        // this.update = true;
        this.ecEdit = true;
        break;
      }
      case "EC_REVIWER": {
        this.update = true;
        this.ecEdit = true;
        break;
      }
      case "UBO_EDIT": {
        // this.update = true;
        this.uboEdit = true;
        this.turbineCost = true;
        break;
      }
      case "UBO_REVIWER": {
        // this.update = true;
        this.uboEdit = true;
        break;
      }
      case "UBO_APPROVER": {
        // this.update = true;
        this.uboEdit = true;
        break;
      }
      case "F2F_APPROVER": {
        this.update = true;
        this.f2fApp = true;
        this.turbineCost = true;
        break;
      }
      case "F2F_EDIT": {
        // this.update = true;
        this.f2fEdit = true;
        this.turbineCost = true;
        break;
      }
      case "F2F_REVIEWER": {
        this.update = true;
        this.turbineCost = true;
        break;
      }
      case "PKG_FW_EDIT": {
        // this.update = true;
        this.PFedit = true;
        break;
      }
      case "PKG_FW_REVIWER": {
        this.update = true;
        this.PFrev = true;
        break;
      }
      case "PKG_FW_APPROVER": {
        this.update = true;
        this.PFApp = true;
        break;
      }
      case "PROJECT_APPROVER": {
        this.update = true;
        this.projApp = true;
        break;
      }
      case "PROJECT_EDIT": {
        this.update = true;
        this.projEdit = true;
        break;
      }
      case "TRANS_EDIT": {
        // this.update = true;
        this.transpEdit = true;
        break;
      }
      case "TRANS_APPROVER": {
        // this.update = true;
        this.transpApp = true;
        break;
      }
      case "TRANS_REVIWER": {
        // this.update = true;
        this.transRev = true;
      }
      case "DBO_MECH_EDIT": {
        // this.update = true;
        this.dboMechEdit = true;
        break;
      }
      case "DBO_MECH_APPROVER": {
        this.update = true;
        this.dboMechApp = true;
        break;
      }
      case "DBO_MECH_REVIWER": {
        this.update = true;
        this.dboMechRev = true;
        break;
      } case "DBO_ELE_EDIT": {
        // this.update = true;
        this.dboEleEdit = true;
        break;
      }
      case "DBO_ELE_APPROVER": {
        this.update = true;
        this.dboEleApp = true;
        break;
      } case "DBO_ELE_REVIWER": {
        this.update = true;
        this.dboEleRev = true;
        break;
      }

      case "FINANCE_EDIT": {
        this.update = true;
        this.turbineCost = true;
        break;
      }
      case "FINANCE_APPROVER": {
        this.update = true;
        this.turbineCost = true;
        break;
      }
      case "FINANCE_REVIWER": {
        this.update = true;
        this.turbineCost = true;
        break;
      }     
      
      case "SUB_CONTR_EDIT": {
        this.update = true;
        break;
      }
      case "SUB_CONTR_REVIWER": {
        this.update = true;
        break;
      }
      case "SUB_CONTR_APPROVER": {
        this.update = true;
        break;
      }

      default: {

        break;
      }
    }
    console.log(this.admin,
      this.quotEdit,
      this.quotApprover,
      this.qoutReviewer,
      this.dboEleEdit,
      this.dboEleApp,
      this.dboMechEdit,
      this.dboMechApp,
      this.ecEdit,
      this.ecApp,
      this.f2fApp,
      this.f2fEdit,
      this.PFedit,
      this.PFApp,
      this.projApp,
      this.projEdit,
      this.transpEdit,
      this.transpApp);
    
    if (!(this.quotEdit || this.quotApprover ||  this.qoutReviewer) ) {
      this.router.navigate(['/defaultHome']);
      
    }
    else {
      this.router.navigate(['/HomePage']);
     
    }
   // window.location.reload();
    this.DisableLogin = true;
    this._ITOLoginService.sendLoginStatus(true);
  }
  roleCheck(role) {
    console.log(role);
    for (let r = 0; r < this.userRoles.length; r++) {
      if (this.userRoles[r].value === role) {
        this.selectedRoleId = this.userRoles[r].key;
        this.selectedRole = this.userRoles[r].code;
        this.selectedRoleDesc = this.userRoles[r].value;
      }
    }
    this.saveInLocal(this.currentRole, this.selectedRole);
    this.saveInLocal(this.currentRoleId, this.selectedRoleId);
    this.saveInLocal(this.currentRoleDesc, this.selectedRoleDesc);
    console.log(this.selectedRole);
    this.quotApprover = false;
    this.admin = false;
    this.dboEleEdit = false;
    this.dboEleApp = false;
    this.dboMechEdit = false;
    this.dboMechApp = false;
    this.uboEdit = false;
    this.ecApp = false;
    this.f2fApp = false;
    this.f2fEdit = false;
    this.PFedit = false;
    this.PFApp = false;
    this.projApp = false;
    this.projEdit = false;
    this.qoutReviewer = false;
    this.quotEdit = false;
    this.update = false;
    this.ecEdit = false;
    this.update = false;
    this.uboEdit = false;
    this.turbineCost = false;
    this.ecApp = false;
    this.f2fApp = false;
    this.turbineCost = false;
    this.update = false;
    this.f2fEdit = false;
    this.update = false;
    this.PFedit = false;
    this.PFrev = false;
    this.PFApp = false;
    this.projApp = false;
    this.projEdit = false;
    this.update = false;
    this.transpEdit = false;
    this.transpApp = false;
    this.transRev = false;
    this.update = false;
    this.dboMechEdit = false;
    this.dboMechApp = false;
    this.dboMechRev = false;
    this.update = false;
    this.dboEleEdit = false;
    this.dboEleApp = false;
    this.dboEleRev = false;
    this.update = false;
    this.uboEdit = false;
    this.turbineCost = false;
    switch (this.selectedRole) {
      case "QUOT_APPROVER": {
        // this.update = true;
        this.quotApprover = true;
        break;
      }
      case "ADMIN": {
        this.admin = true;
        // this.quotEdit = true;
        // this.quotApprover = true;
        // this.qoutReviewer = true;
        this.dboEleEdit = true;
        this.dboEleApp = true;
        this.dboMechEdit = true;
        this.dboMechApp = true;
        // this.uboEdit = true;
        this.ecApp = true;
        this.f2fApp = true;
        this.f2fEdit = true;
        this.PFedit = true;
        this.PFApp = true;
        this.projApp = true;
        this.projEdit = true;
        break;
      }
      case "QUOT_REVIWER": {
        // this.update = true;
        this.qoutReviewer = true;
        break;
      }
      case "QUOT_EDIT": {
        // this.update = true;
        this.quotEdit = true;
        break;
      }
      case "EC_EDIT": {
        // this.update = true;
        this.ecEdit = true;
        break;
      }
      case "UBO_EDIT": {
        // this.update = true;
        this.uboEdit = true;
        this.turbineCost = true;
        break;
      }
      case "UBO_APPROVER": {
        // this.update = true;
        this.uboEdit = true;
        break;
      }
      case "UBO_REVIWER": {
        // this.update = true;
        this.uboEdit = true;
        break;
      }
      case "EC_APPROVER": {
        this.update = true;
        this.ecApp = true;
        break;
      }
      case "F2F_APPROVER": {
        this.update = true;
        this.f2fApp = true;
        this.turbineCost = true;
        break;
      }
      case "F2F_EDIT": {
        // this.update = true;
        this.f2fEdit = true;
        this.turbineCost = true;
        break;
      }
      case "F2F_REVIEWER": {
        this.update = true;
        this.turbineCost = true;
        break;
      }
      case "PKG_FW_EDIT": {
        // this.update = true;
        this.PFedit = true;
        break;
      }
      case "PKG_FW_REVIWER": {
        this.update = true;
        this.PFrev = true;
        break;
      }
      case "PKG_FW_APPROVER": {
        this.update = true;
        this.PFApp = true;
        break;
      }
      case "PROJECT_APPROVER": {
        this.update = true;
        this.projApp = true;
        break;
      }
      case "PROJECT_EDIT": {
        this.update = true;
        this.projEdit = true;
        break;
      }
      case "TRANS_EDIT": {
        this.update = true;
        this.transpEdit = true;
        break;
      }
      case "TRANS_APPROVER": {
        this.update = true;
        this.transpApp = true;
        break;
      }
      case "TRANS_REVIWER": {
        this.update = true;
        this.transRev = true;
      }
      case "DBO_MECH_EDIT": {
        // this.update = true;
        this.dboMechEdit = true;
        break;
      }
      case "DBO_MECH_APPROVER": {
        this.update = true;
        this.dboMechApp = true;
        break;
      }
      case "DBO_MECH_REVIWER": {
        this.update = true;
        this.dboMechRev = true;
        break;
      } case "DBO_ELE_EDIT": {
        // this.update = true;
        this.dboEleEdit = true;
        break;
      }
      case "DBO_ELE_APPROVER": {
        this.update = true;
        this.dboEleApp = true;
        break;
      } case "DBO_ELE_REVIWER": {
        this.update = true;
        this.dboEleRev = true;
        break;
      }
      case "FINANCE_EDIT": {
        this.update = true;
        this.turbineCost = true;
        break;
      }
      case "FINANCE_APPROVER": {
        this.update = true;
        this.turbineCost = true;
        break;
      }
      case "FINANCE_REVIWER": {
        this.update = true;
        this.turbineCost = true;
        break;
      }
      
      default: {
        break;
      }
    }
    console.log(this.admin,
      this.quotEdit,
      this.quotApprover,
      this.qoutReviewer,
      this.dboEleEdit,
      this.dboEleApp,
      this.dboMechEdit,
      this.dboMechApp,
      this.ecEdit,
      this.ecApp,
      this.f2fApp,
      this.f2fEdit,
      this.PFedit,
      this.PFApp,
      this.projApp,
      this.projEdit,
      this.transpEdit,
      this.transpApp);
    if (!(this.quotEdit || this.quotApprover ||  this.qoutReviewer) ) {
      this.router.navigate(['/defaultHome']);
    }
    else {
      this.router.navigate(['/HomePage']);
    }
    this.DisableLogin = true;
    this._ITOLoginService.sendLoginStatus(true);
  }
  closeDialog() {
    this._ITOAddOnComponentsService.dispPrevComments = false;
    this._ITOAddOnComponentsService.oldComms = null;
  }

}
