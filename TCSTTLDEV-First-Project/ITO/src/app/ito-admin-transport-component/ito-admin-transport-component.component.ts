import { Component, OnInit } from '@angular/core';
import {ItoTransportationDetailsService } from './ito-admin-transport.service';
import {transportationDetails} from './ito-admin-transport';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';

@Component({
  selector: 'app-ito-admin-transport-component',
  templateUrl: './ito-admin-transport-component.component.html',
  styleUrls: ['./ito-admin-transport-component.component.css']
})
export class ItoAdminTransportComponentComponent implements OnInit {

  // display tab for component, vehicle,place
  display:boolean=false;
  display1:boolean=false;
  display2:boolean=false;
//variables to store component, vehicle , place and scope array respectively
  componentArray:Array<any>=[];
  vehicleArray:Array<any>=[];
  placeArray:Array<any>=[];
  scopeArray:Array<any>=[];
// Col arrays to store component, vehicle and place
  cols:Array<any>=[];
  colsVeh: Array<any>=[];
  colsPlace:Array<any>=[];
// booelan attributes to display coponent,place and vehicle dialog
  displayDialogadd:boolean=false;
  displayCompDialogadd:boolean=false;
  displayplacDialogadd:boolean=false;
  displayVehicleDialogadd:boolean=false;
//booelan attributes to set coponent,place and vehicle edit dialog
  displayEditCompDialogadd:boolean=false;
  displayEditPlaceDialogadd:boolean=false;
  displayEditVehicleDialogadd:boolean=false;
// attributes to store newly added componet, vehicle and place
  turbineCodeArray:Array<any>=[];
  tst:any;
  addComponentArray:Array<any>=[];
  addPlaceArray:Array<any>=[];
  addVehicleArray:Array<any>=[];
//attributes to store updated componet, vehicle and place
  editComponentVal:any;
  editPlaceVal:any;
  editVehicleVal:any;
  editCompID:any;
  editVehicleID:any;
  editPlaceID:any;
// attributes to store or fetch user information
  user: string = 'userDetail';
  data: Array<any> = [];
  userId:any;
  assignee: any;
  //vehicleWithUnitPriceArray:Array<any>=[];


  constructor(private _ItoTransportationService: ItoTransportationDetailsService,private _Router: Router,
    private _ActivatedRoute: ActivatedRoute, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
    @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
      //used to fetch the loggedin user
      this.assignee = this.data[this.user] = this.storage.get(this.user);
     this.userId = this.data[this.user].userId;
  console.log("userIddddddd::"+this.userId);

   // method to get the details from DB forscope of supply
    this._ItoTransportationService.getTransportationCache().subscribe(res => {
      console.log(res);
     // this.componentArray = res.dropDownColumnvalues.componentTypes.TransportComponentTypeList;    
      //this.placeArray = res.dropDownColumnvalues.placeList.placeList;
      //this.vehicleArray = res.dropDownColumnvalues.vehiclesList.VEHICLE_LIST;
      this.scopeArray=res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
      console.log(this.scopeArray);

    });

    // method to get the details from DB for componnet,place and vehicles
    this._ItoTransportationService.getAdminCacheWithAIList().subscribe(res => {
      console.log(res);
      this.componentArray = res.dropDownColumnvalues.componentTypes.TransportComponentTypeList;    
      this.placeArray = res.dropDownColumnvalues.placeList.placeList;
      this.vehicleArray = res.dropDownColumnvalues.vehiclesList.VEHICLE_LIST;
      //this.scopeArray=res.dropDownColumnvalues.scopeOfSupply.SCOPEOFSUPPLY;
      console.log(this.vehicleArray);

    });

  }

  ngOnInit() {
    this._ITOLoginService.dialogMsgApp=false;
    // used to get the column names for component
    this.cols = [
      { field: 'turbineCode', header: 'Turbine Code' },
      { field: 'categoryDetDesc', header: 'Component Name' },
      { field: 'categoryDetCode', header: 'Component Code' },
      { field: 'ssName', header: 'Scope Of Supply' },      
      { field: 'place', header: 'Place' },
     
  ];
  // used to get the column names for vehicle
  this.colsVeh = [
    { field: 'categoryDetCode', header: 'Vehicle Code'},
    { field: 'categoryDetDesc', header: 'Vehicle Name'},
    { field: 'dimension', header: 'Dimensions'},
    { field: 'length', header: 'Vehicle Length'}
  ];
  
  // used to get the column names for place
  this.colsPlace = [
    { field: 'fobplaceCode', header: 'Place Code' },
    { field: 'fobplace', header: 'Place Name' },

  ];

  // list of turbine code values
  this.turbineCodeArray=['BP','CD'];
  }

  // method to display the component div
  displayFrame(){
    if(this.display==false){
      this.display=true;
      }else{
       this.display=false;
      }
  }

  //method to display vehicle div
  displayVehicle(){
    if(this.display1==false){
      this.display1=true;
      }else{
       this.display1=false;
      }
  }

  //method to display place div
  displayPlace(){
    if(this.display2==false){
      this.display2=true;
      }else{
       this.display2=false;
      }
  }

  // method to display add vehicle dialog
  addNewVehicle(){
    this.displayDialogadd=true;
  }

  
  // method to display add component dialog
  addNewComp(){
    this.displayCompDialogadd=true;
  }

  
  // method to display add place dialog
  saveNewPlace(){
    this.displayplacDialogadd=true;
  }

  //method to create new component on save
  createComponent(val){
    console.log(val);
    this.tst=new transportationDetails();
    this.tst.turbCode=val.turbinetype;
    this.tst.turbineCode=val.turbinetype;
    this.tst.categoryDetCode=val.comCode;
    this.tst.categoryDetDesc=val.compName;
    this.tst.iscategoryActive=true;
    this.tst.place=val.place;
    this.tst.compoName=val.compName;
    this.tst.CompoCode=val.comCode;
    this.tst.FOBPlace=val.place;
    this.tst.active=true;
    this.tst.ssName=val.scopeVal;
    this.tst.compoId=0;
    this.tst.categoryDetId=0;
    for(var i=0;i<this.placeArray.length;i++){
    if(this.placeArray[i].fobplace==val.place){
      console.log(this.placeArray[i].fobplace);
    this.tst.placeId=this.placeArray[i].placeId;
    }
    }
    for(var i=0;i<this.scopeArray.length;i++){
      if(this.scopeArray[i].scopeName==val.scopeVal){
      this.tst.ssId=this.scopeArray[i].ssId;
      }
      }
    this.componentArray.push(this.tst);
    this.displayCompDialogadd=false;
    this.addComponentArray.push(this.tst);
    console.log(this.addComponentArray);
    //console.log(this.componentArray);
  }

  
  //method to create new place on save
  createPlace(val){
    this.tst=new transportationDetails();
    this.tst.fobplace=val.placeName;
    this.tst.fobplaceCode=val.placeCode;
    this.tst.FOBPlace=val.placeName;
    this.tst.FOBPlaceCode=val.placeCode;
    this.tst.active=true;
    this.tst.iscategoryActive=true;
    this.tst.placeId=0;
    this.tst.categoryDetId=0;
    this.placeArray.push(this.tst);
    this.displayplacDialogadd=false;
    this.addPlaceArray.push(this.tst);
    console.log(this.addPlaceArray);
  }

  
  //method to create new vehicle on save
  createVehicle(val){
    this.tst=new transportationDetails();
    this.tst.vehicleName=val.vehicleName;
    this.tst.VehicleCode=val.vehicleCode;
    this.tst.categoryDetCode=val.vehicleCode;
    this.tst.categoryDetDesc=val.vehicleName;
    this.tst.dimension=val.dimension;
    this.tst.length=val.length;
    this.tst.active=true;
    this.tst.iscategoryActive=true;
    this.tst.vehicleId=0;
    this.tst.categoryDetId=0;
    this.vehicleArray.push(this.tst);
    this.displayDialogadd=false;
    this.addVehicleArray.push(this.tst);
    console.log(this.addVehicleArray);
  }

  
  //method to edit existing component 
  editComp(val){
    console.log(val);
    this.editCompID=val.categoryDetId;
    this.displayEditCompDialogadd=true;
    this.editComponentVal=new transportationDetails();
    this.editComponentVal=val;
    console.log(this.editComponentVal);
  }

    //method to edit existing vehicle 
  editVehicle(val){
    console.log(val);
    this.editVehicleID=val.categoryDetId;
    this.displayEditVehicleDialogadd=true;
    this.editVehicleVal=new transportationDetails();
    this.editVehicleVal=val;
    console.log(this.editVehicleVal);
  }

    //method to edit existing place 
  editPlace(val){
    console.log(val);
    this.editPlaceID=val.placeId;
    this.displayEditPlaceDialogadd=true;
    this.editPlaceVal=new transportationDetails();
    this.editPlaceVal=val;
    console.log(this.editPlaceVal);
  }

    //method to update existing component 

  UpdateComponent(val){
    console.log(val);
    var isexist=false;
    var index1:number=0;
    for(var i=0;i<this.componentArray.length;i++){
      if(this.editCompID==this.componentArray[i].categoryDetId){
      console.log(this.componentArray[i].categoryDetId);
      this.componentArray[i].categoryDetCode=val.comCode;
      this.componentArray[i].categoryDetDesc=val.compName;
      this.componentArray[i].place=val.place;
      this.componentArray[i].turbineCode=val.turbinetype;
      this.componentArray[i].iscategoryActive=val.compStatus;
      this.componentArray[i].ssName=val.scopeVal;
      
      for(var j=0;j<this.placeArray.length;j++){
        if(this.placeArray[j].fobplace==val.place){
        this.componentArray[i].placeId=this.placeArray[j].placeId;
        }
        }
        for(var l=0;l<this.scopeArray.length;l++){
          if(this.scopeArray[l].scopeName==val.scopeVal){
          this.componentArray[i].ssId=this.scopeArray[l].ssId;
          }
          }
        if(this.addComponentArray.length>0){
          console.log(this.editCompID);
        for(var k=0;k<this.addComponentArray.length;k++){
        if(this.addComponentArray[k].categoryDetId==this.editCompID){
          console.log(111111);
          index1=k;
          console.log(index1);
          isexist=true;
          break;
        }
        }}
        console.log(isexist);
        if(!isexist){
          this.addComponentArray.push(this.componentArray[i]);
        }else{
          this.addComponentArray.slice(index1,1);
          this.addComponentArray.push(this.componentArray[i]);
          console.log("slicing");
        }
        
      }
    }
   
    this.displayEditCompDialogadd=false;
   
    console.log(this.addComponentArray);
    console.log(this.componentArray);
  }

 //method to update existing place 

  UpdatePlace(val){
    console.log(val);
    console.log(this.editPlaceID);
    console.log(this.placeArray);
    for(var i=0;i<this.placeArray.length;i++){
      if(this.editPlaceID==this.placeArray[i].placeId){
      console.log(this.placeArray[i].placeId);
      this.placeArray[i].fobplaceCode=val.placeCode;
      this.placeArray[i].fobplace=val.placeName;
      this.placeArray[i].FOBPlaceCode=val.placeCode;
      this.placeArray[i].FOBPlace=val.placeName;
      this.placeArray[i].iscategoryActive=val.statusPlace;
      this.placeArray[i].active=val.statusPlace;
      this.addPlaceArray.push(this.placeArray[i]);
      console.log(this.placeArray[i]);
      }
    }
    console.log(this.addPlaceArray);
    this.displayEditPlaceDialogadd=false;
  }
   //method to update existing vehicle 
  UpdateVehicle(val){
    console.log(val);
    for(var i=0;i<this.vehicleArray.length;i++){
      if(this.editVehicleID==this.vehicleArray[i].categoryDetId){
      this.vehicleArray[i].categoryDetCode=val.vehicleCode;
      this.vehicleArray[i].categoryDetDesc=val.vehicleName;
      this.vehicleArray[i].iscategoryActive=val.statusVehicle;
      this.vehicleArray[i].dimension=val.dimension;
      this.vehicleArray[i].length=val.length;
      this.addVehicleArray.push(this.vehicleArray[i]);
      console.log(this.vehicleArray[i]);
      }
    }
    console.log(this.addVehicleArray);
    this.displayEditVehicleDialogadd=false;
  }

  // Send edited and new components request to the DB 
  addEditListComponent(){
    console.log(this.addComponentArray);
    this._ItoTransportationService.addOrEditForm().subscribe(res => {
      console.log(res);
      res.modifiedBy=this.userId;
      for(var i=0;i<this.addComponentArray.length;i++){
        this.tst =new transportationDetails();
        this.tst.compoId=this.addComponentArray[i].categoryDetId;
        this.tst.compoCode=this.addComponentArray[i].categoryDetCode;
        this.tst.compoName= this.addComponentArray[i].categoryDetDesc;
        this.tst.FOBPlace=this.addComponentArray[i].place;
        this.tst.placeId=this.addComponentArray[i].placeId;
        this.tst.turbineCode=this.addComponentArray[i].turbineCode;
        this.tst.active= this.addComponentArray[i].iscategoryActive;
        this.tst.ssName=this.addComponentArray[i].ssName;
        this.tst.ssId=this.addComponentArray[i].ssId;
      
        res.transportDetailsList.push(this.tst);
      }
      this._ItoTransportationService.addOrEditCompo(res).subscribe(resp => {
        console.log(resp);

        if (resp.successCode == 0 ) {
          this._ITOLoginService.openSuccMsg("Component created");
          var win = window.open("", "_self");
          win.close();
          this._Router.navigate(['/adminTransportComponent']);
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Component Creation Failed");
        }
      });
    });
  }
    // Send edited and new vehicle request to the DB 
  addEditListVehicle(){
    this._ItoTransportationService.addOrEditForm().subscribe(res => {
      console.log(res);
      res.modifiedBy=this.userId;
      res.modifiedBy=this.userId;
      for(var i=0;i<this.addVehicleArray.length;i++){
        this.tst =new transportationDetails();
        this.tst.vehicleId=this.addVehicleArray[i].categoryDetId;
        this.tst.vehicleName=this.addVehicleArray[i].categoryDetDesc;
        this.tst.vehicleCode=this.addVehicleArray[i].categoryDetCode;
        this.tst.active=this.addVehicleArray[i].iscategoryActive;
        this.tst.dimension=this.addVehicleArray[i].dimension;
        this.tst.length=this.addVehicleArray[i].length;
      
        res.transportDetailsList.push(this.tst);
      }
      this._ItoTransportationService.addOrEditVehi(res).subscribe(resp => {
        console.log(resp);

        if (resp.successCode == 0 ) {
          this._ITOLoginService.openSuccMsg("Vehicle created");
          //alert("Vehicle created");
          var win = window.open("", "_self");
          /* url = “” or “about:blank”; target=”_self” */
          win.close();
          this._Router.navigate(['/adminTransportComponent']);
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Vehicle creation Failed");
         // alert("Vehicle Creation Failed");
        }
      });
    });
  }

    // Send edited and new place request to the DB 
  addEditListPlace(){
    this._ItoTransportationService.addOrEditForm().subscribe(res => {
      console.log(res);
      res.modifiedBy=this.userId;
      res.modifiedBy=this.userId;
      for(var i=0;i<this.addPlaceArray.length;i++){
        this.tst =new transportationDetails();
        this.tst.placeId=this.addPlaceArray[i].placeId;
        this.tst.fobplace=this.addPlaceArray[i].fobplace;
        this.tst.fobplaceCode=this.addPlaceArray[i].fobplaceCode;
        this.tst.active=this.addPlaceArray[i].active;
      
        res.transportDetailsList.push(this.tst);
      }
      this._ItoTransportationService.addOrEditPlace(res).subscribe(resp => {
        console.log(resp);

        if (resp.successCode == 0 ) {
          this._ITOLoginService.openSuccMsg("Place created");
          this._Router.navigate(['/adminTransportComponent']);
        } else {
         this._ITOLoginService.errdisplay =true;
         this._ITOLoginService.openSuccMsg("Place creation failed");
        }
      });
    });
  }
}
