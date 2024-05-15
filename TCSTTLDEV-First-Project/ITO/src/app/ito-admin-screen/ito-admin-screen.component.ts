
import { ITOAdminScreenService } from './ito-admin-screen.service';
import { Component } from '@angular/core';
import { itoAdminScreenDetails } from './ito-admin-screen';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { userDetails } from '../ito-create-new-user/ito-create-new-user';
import { ITOLoginService } from '../app.component.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';

@Component({
  selector: 'app-ito-admin-screen',
  templateUrl: './ito-admin-screen.component.html',
  styleUrls: ['./ito-admin-screen.component.css']
})
export class ItoAdminScreenComponent {
  // Temporary array to store group list
  tempGroupArray:  Array<any> = [];
  // Temporary array to store role list
  tempRoleArray:  Array<any> = [];
  // Temporary array to store region  list
  tempRegionArray:  Array<any> = [];
  // Variable to show alert messages
  showMsg2: string;
  // Variable to show alert messages for group
  showMsgGroup: boolean=false;
  // Variable to show alert messages for role
  showMsgRole: boolean=false;
  // Variable to show alert messages
  showMsg1: string;
  // Boolean variable to display group dialog
  displayGroupDialog: boolean=false;
  //variable to store new group a rray
  newGroupArray1: any;
  //Variable to store group columns
  groupCols: { field: string; header: string; }[];
  //variable to store new role array
  newRoleArray1: any;
  //variable to store display role dialog
  displayRoleDialog: boolean=false;
  //Variable to store row columns
  rolCols: { field: string; header: string; }[];
   //variable to store msg related region
  showMsgRegion: boolean;
     //variable to store msg 
  showMsg: string;
   //variable to store new region array
  newRegionArray1: any;
    //variable to store display region dialog
  displayRegionDialog: boolean=false;
   //Variable to store region columns
  regCols: { field: string; header: string; }[];
    //Variable to store region display boolean
  display: boolean = false;
   //Variable to store role display boolean
  dispRole: boolean = false;
   //Variable to store group display boolean
  dispGrp: boolean = false;
  //regex for empty text pattern
  emptyTextPattern=".*[^ ].*";
// variable to add newly added details
  newArray: itoAdminScreenDetails;
  // variable to add newly added user details
  newArrayUserDetails: userDetails;
  //variable tp store column regions
  colsRegions: any[];
  //variable to store column groups
  colsGroups: any[];
  //variable to store column roles
  colsRoles: any[];
  // variable to store region
  newRegion: boolean;
  // variable to region array
  newRegionArray: itoAdminScreenDetails;
  //variable to add selected index region
  selectedIndex: number;
  //variable to add selected index role
  selectedIndexRole: number;
  //variable to add selected index group
  selectedIndexGroup:number;
  //variable to add selected index region
  selectedKeyArray: Array<any> = [];
  //variable to add selected index role
  selectedKeyArrayRole: Array<any>=[];
  //variable to add selected indexGroup
  selectedKeyArrayGroup: Array<any>=[];
  //variable to add count key
  countKey: number = 0;
  // variable to store region length
  originalRegionArrayLength: number = 0;
  // variable to store role length
  originalRoleArrayLength:number=0;
  // variable to store group length
  originalGroupArrayLength:number=0;
//boolean variable
  variable: boolean = true;
  //Active array list
  ActiveArray: Array<any> = [];
  //Inactive array list
  InActiveArray: Array<any> = [];
  //Active region array list
  RegionArrayActive: Array<any> = [];
  //inactive region array list
  RegionArrayInactive: Array<any> = [];
  //array to store group
  GroupArray: Array<any> = [];
  //array to store role
  RoleArray: Array<any> = [];
  //array to store region
  RegionArray: Array<any> = [];
  //max role
  maxRoleKey:number=0;
  //max region
  maxRegionKey:number=0;
  //max group
  maxGroupKey:number=0;

  const: number;
  constRegionId: number;
//variable to store addORupdateRegionArray
  addORupdateRegionArray: Array<any> = [];
  //variable to store spinner
  hidespinner: boolean = true;
  //variable to count key role
  countKeyRole: number=0;
   //variable to store addORupdateRoleArray
  addORupdateRoleArray: Array<any> = [];
 //variable to store addORupdateGroupArray
  addORupdateGroupArray:  Array<any> = [];
  //variable to count key group
  countKeyGroup: number=0;
  //variable to store user details
  user: string = 'userDetail';
    //variable to store user Data
  data: Array<any> = [];
  //variable to store user ID
  userId:any;
  // variable to store assigne
  assignee: any;
   //boolean todisplay dialog msd
  dialogMsg:boolean=false;
  // variable to store msg
  dialogMsgVal:string;
  //boolean to display error msg
  dialogErrorMsg:boolean=false;
  // variable to store error msg
  dialogErrorMsgVal:string;
  //boolean to check edit mode
  editCheck:boolean=false;

  constructor(private _Router: Router, private _ActivatedRoute: ActivatedRoute,
     private _ITOAdminScreenService: ITOAdminScreenService, private _ITOLoginService: ITOLoginService, private domSanitizer: DomSanitizer,
     @Inject(LOCAL_STORAGE) private storage: WebStorageService) {
    //method to fetch logged in user ID
      this.assignee = this.data[this.user] = this.storage.get(this.user);
      this.userId = this.data[this.user].userId;
   console.log("userIddddddd::"+this.userId);
   //method to fetch roles, region & department list.
    this._ITOAdminScreenService.getRolesList().subscribe(res => {
      console.log(res);
      this.RegionArray=res.dropDownColumnvalues.regionsList.REGIONS;
      for(var i=0;i<this.RegionArray.length;i++){
        if(this.RegionArray[i].active==true){
          this.RegionArray[i].active="Active";
        }else if(this.RegionArray[i].active==false){
          this.RegionArray[i].active="InActive";
        }
      }
      this.RoleArray=res.dropDownColumnvalues.rolesList.ROLES;
      for(var i=0;i<this.RoleArray.length;i++){
        if(this.RoleArray[i].active==true){
          this.RoleArray[i].active="Active";
        }else if(this.RoleArray[i].active==false){
          this.RoleArray[i].active="InActive";
        }
      }
      this.GroupArray=res.dropDownColumnvalues.departmentsList.DEPARTMENTS;
      for(var i=0;i<this.GroupArray.length;i++){
        if(this.GroupArray[i].active==true){
          this.GroupArray[i].active="Active";
        }else  if(this.GroupArray[i].active==false){
          this.GroupArray[i].active="InActive";
        }
      }     
    })
    
  }
// store region,role and group to temp array on init
  ngOnInit() {
    this._ITOLoginService.dialogMsgApp = false;
    this._ITOLoginService.errdisplay = false;
    this._ITOAdminScreenService.getRolesList().subscribe(restemp => {
      console.log(restemp);
      this.tempRegionArray=restemp.dropDownColumnvalues.regionsList.REGIONS;
      
      this.tempRoleArray=restemp.dropDownColumnvalues.rolesList.ROLES;
      
      this.tempGroupArray=restemp.dropDownColumnvalues.departmentsList.DEPARTMENTS;
         
    })

   this.dialogErrorMsg=false;
   this.dialogMsg=false;
   this.regCols = [
   
    { field: 'value', header: 'Region Name' },
    { field: 'code', header: 'Region Code' },
    { field: 'active', header: 'Status' },
  ];
  this.rolCols = [
   
    { field: 'value', header: 'Role Name' },
    { field: 'code', header: 'Role Code' },
    { field: 'active', header: 'Status' },
  ];
  this.groupCols = [
   
    { field: 'value', header: 'Department Name' },
    { field: 'code', header: 'Department Code' },
    { field: 'active', header: 'Status' },
  ];
  }
  //method to add region
  addRegion(val) {
    console.log(val);
    console.log(val.region.match(this.emptyTextPattern));
    var isError=false;
    console.log(this.RegionArray);
    if(val.region.match(this.emptyTextPattern) == null && val.regionCode.match(this.emptyTextPattern)==null){
      isError=true;
      this.showMsgRegion=true;
      this.showMsg="Please Enter the valid values";
    }
    else if(val.region.match(this.emptyTextPattern) == null && val.regionCode.match(this.emptyTextPattern) != null){
      isError=true;
      this.showMsgRegion=true;
      this.showMsg="Please Enter the valid values";
    }else if(val.region.match(this.emptyTextPattern) != null && val.regionCode.match(this.emptyTextPattern) == null){
      isError=true;
      this.showMsgRegion=true;
      this.showMsg="Please Enter the valid values";
    }else {
      isError =false;
    }
   
    for(var i=0;i<this.RegionArray.length;i++){
      if(val.region==this.RegionArray[i].value){
        isError=true;
        this.showMsgRegion=true;
        this.showMsg="Region Already Exist";
      }else if(val.regionCode== this.RegionArray[i].code){
        isError=true;
        this.showMsgRegion=true;
        this.showMsg="Region Code Already Exist";
      }
    }
    if(!isError){
      this.showMsgRegion=false;
    this.newArray= new itoAdminScreenDetails();
    var newArray1= new itoAdminScreenDetails();

   this.newArray.region=val.region;
   this.newArray.regionCode=val.regionCode;
   this.newArray.active=true;
   this.newArray.regionId=0;
   
   newArray1.value=val.region;
   newArray1.active=true;
   newArray1.key=0;
   newArray1.code=val.regionCode;
   this.RegionArray.push( newArray1);
    this.addORupdateRegionArray.push( this.newArray);
    console.log(this.addORupdateRegionArray);
    console.log(this.RegionArray);
    for(var i=0;i<this.RegionArray.length;i++){
      if(this.RegionArray[i].active==true){
        this.RegionArray[i].active="Active";
      }else if(this.RegionArray[i].active==false){
        this.RegionArray[i].active="InActive";
      }
    }
  }
  }
  //method to edit/update region
  UpdateRegion(val){
    console.log(val);
    this.editCheck=true;
    for(var i=0;i<this.RegionArray.length;i++){
      if(this.newRegionArray1.key==this.RegionArray[i].key &&
        this.newRegionArray1.code==this.RegionArray[i].code){
          this.RegionArray[i].active=val.active;
          // this.RegionArray[i].value=val.value;
        
          this.newArray= new itoAdminScreenDetails();
          this.newArray.region=this.RegionArray[i].value;
          this.newArray.regionCode=this.RegionArray[i].code;
          this.newArray.active=this.RegionArray[i].active;
          this.newArray.regionId=this.RegionArray[i].key;
          this.addORupdateRegionArray.push( this.newArray);
          if(this.RegionArray[i].active==true){
            this.RegionArray[i].active="Active";
          }else{
            this.RegionArray[i].active="InActive";
          }
        }
      
    }
  
 
    this.displayRegionDialog=false;
  }

  // method to edit region
  editRegion(val) {
   console.log(val);
   this.newRegionArray1={};
   this.displayRegionDialog=true;
   this.newRegionArray1=val;
   if(this.newRegionArray1.active=='Active'){
    this.newRegionArray1.active=true;
   }else{
    this.newRegionArray1.active=false;
   }
   console.log(this.newRegionArray1);
  }
  //method to create region
  createRegion() {
    console.log( this.addORupdateRegionArray);

    this._ITOAdminScreenService.addOrEditRegion().subscribe(res => {
      console.log(res);
     let newArray1:Array<any>=[];
      for (var i = 0; i < this.addORupdateRegionArray.length; i++) {

        this.newArray= new itoAdminScreenDetails();
        
        this.newArray.regionId = this.addORupdateRegionArray[i].regionId;
        this.newArray.region = this.addORupdateRegionArray[i].region;
        this.newArray.active = this.addORupdateRegionArray[i].active;
        this.newArray.regionCode=this.addORupdateRegionArray[i].regionCode;
        this.newArray.modifiedById=this.userId;
        newArray1.push(this.newArray);
      }

      console.log( newArray1);
      res.userProfileDetailsList=newArray1;
      console.log(res);
      this._ITOAdminScreenService.addOrEditRegion1(res).subscribe(resp => {
        console.log(resp);

        if (resp.successCode == 0 ) {
          // alert("region created");
          // var win = window.open("", "_self");
          // /* url = “” or “about:blank”; target=”_self” */
          // win.close();
          this._ITOLoginService.openSuccMsg("Region Updated/Insert Succesful");

          this._Router.navigate(['/adminScreen']);
        } else {
          this._ITOLoginService.errdisplay=true;
          this._ITOLoginService.openSuccMsg("Region Updated/Creation Failed");
          //this.dialogErrorMsgVal=resp.successMsg;
          this._Router.navigate(['/adminScreen']);
        }
      });
    });

  }
    //method to add role
    addRole(val) {
      console.log(val);
      var isError=false;
      if(val.role.match(this.emptyTextPattern) == null && val.roleCode.match(this.emptyTextPattern) == null){
        isError=true;
        this.showMsgRole=true;
        this.showMsg1="Please Enter the valid Values";
      }else if(val.role.match(this.emptyTextPattern) != null && val.roleCode.match(this.emptyTextPattern) == null){
        isError=true;
        this.showMsgRole=true;
        this.showMsg1="Please Enter the valid Values";
      }else if(val.role.match(this.emptyTextPattern) == null && val.roleCode.match(this.emptyTextPattern) != null){
        isError=true;
        this.showMsgRole=true;
        this.showMsg1="Please Enter the valid Values";
      }
      else{
        isError=false;
      }
    for(var i=0;i<this.RoleArray.length;i++){
      if(val.role==this.RoleArray[i].value){
        isError=true;
        this.showMsgRole=true;
        this.showMsg1="Role Already Exist";
      }else if(val.roleCode== this.RoleArray[i].code){
        isError=true;
        this.showMsgRole=true;
        this.showMsg1="Role Code Already Exist";
      }
    }
    if(!isError){
      this.showMsgRole=false;
      this.newArray= new itoAdminScreenDetails();
      var newArray1= new itoAdminScreenDetails();
  
     this.newArray.role=val.role;
     this.newArray.roleCode=val.roleCode;
     this.newArray.active=true;
     this.newArray.roleId=0;
     
     newArray1.value=val.role;
     newArray1.active=true;
     newArray1.key=0;
     newArray1.code=val.roleCode;
     this.RoleArray.push( newArray1);
      this.addORupdateRoleArray.push(this.newArray);
      console.log(this.addORupdateRoleArray);
      console.log(this.RoleArray);
      for(var i=0;i<this.RoleArray.length;i++){
        if(this.RoleArray[i].active==true){
          this.RoleArray[i].active="Active";
        }else if(this.RoleArray[i].active==false){
          this.RoleArray[i].active="InActive";
        }
      }
    }
    }


  //method to edit role
  editRole(val) {
    this.displayRoleDialog=true;
    this.newRoleArray1=[];
    this.newRoleArray1=val;
    if(this.newRoleArray1.active=='Active'){
      this.newRoleArray1.active=true;
     }else{
      this.newRoleArray1.active=false;
     }
    console.log(this.newRoleArray1);
   }
 
  UpdateRole(val){
    console.log(val);
    this.editCheck=true;
    for(var i=0;i<this.RoleArray.length;i++){
      if(this.newRoleArray1.key==this.RoleArray[i].key &&
      this.newRoleArray1.code==this.RoleArray[i].code){
          this.RoleArray[i].active=val.active;
          this.RoleArray[i].value=val.value;
        
          this.newArray= new itoAdminScreenDetails();
          this.newArray.role=this.RoleArray[i].value;
          this.newArray.roleCode=this.RoleArray[i].code;
          this.newArray.active=this.RoleArray[i].active;
          this.newArray.roleId=this.RoleArray[i].key;
          this.addORupdateRoleArray.push( this.newArray);
          if(this.RoleArray[i].active==true){
            this.RoleArray[i].active="Active";
          }else{
            this.RoleArray[i].active="InActive";
          }
        }
      
    }
  
 
    this.displayRoleDialog=false;
  }

  //method to create role
  createRole(val) {
    console.log(val);
    console.log(this.addORupdateRoleArray.length);


    this._ITOAdminScreenService.addOrEditRegion().subscribe(res => {
      console.log(res);
     let newArray1:Array<any>=[];
      for (var i = 0; i < this.addORupdateRoleArray.length; i++) {

        this.newArray= new itoAdminScreenDetails();
        
        this.newArray.roleId = this.addORupdateRoleArray[i].roleId;
        this.newArray.role = this.addORupdateRoleArray[i].role;
        this.newArray.roleCode = this.addORupdateRoleArray[i].roleCode;
        this.newArray.active = this.addORupdateRoleArray[i].active;
        this.newArray.modifiedById=this.userId;

        newArray1.push(this.newArray);
      }

      console.log( newArray1);
      res.userProfileDetailsList=newArray1;
      console.log(res);
      this._ITOAdminScreenService.addOrEditRole1(res).subscribe(resp => {
        console.log(resp);
        if (resp.successCode == 0 ) {
         
          this._ITOLoginService.openSuccMsg("Role Updated/Creation succesful");
          this._Router.navigate(['/adminScreen']);
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Role Updated/Creation Failed");
          this._Router.navigate(['/adminScreen']);
        }
      });
    });

  }

  // method to create group
  createGroup() {
  
   

    this._ITOAdminScreenService.addOrEditRegion().subscribe(res => {
      console.log(res);
     let newArray1:Array<any>=[];
      for (var i = 0; i < this.addORupdateGroupArray.length; i++) {

        this.newArray= new itoAdminScreenDetails();
        
        this.newArray.groupId = this.addORupdateGroupArray[i].groupId;
        this.newArray.group = this.addORupdateGroupArray[i].group;
        this.newArray.groupCd = this.addORupdateGroupArray[i].groupCode;
        this.newArray.active = this.addORupdateGroupArray[i].active;
        this.newArray.modifiedById=this.userId;

        newArray1.push(this.newArray);
      }

      console.log( newArray1);
      res.userProfileDetailsList=newArray1;
      console.log(res);
      this._ITOAdminScreenService.addOrEditGroup1(res).subscribe(resp => {
        console.log(resp);

        if (resp.successCode == 0 ) {
          this._ITOLoginService.openSuccMsg("Group Creation/Updated succesfull");
          this._ITOLoginService.errdisplay = true;
          this._Router.navigate(['/adminScreen']);
        } else {
          this._ITOLoginService.errdisplay = true;
          this._ITOLoginService.openSuccMsg("Group Creation/Updation Failed");
          this._Router.navigate(['/adminScreen']);
        }
      });
    });

  }


  //method to edit group
  editGroup(val) {
    console.log(val);
    this.newGroupArray1={};
    this.displayGroupDialog=true;
    this.newGroupArray1=val;
    if(this.newGroupArray1.active=='Active'){
     this.newGroupArray1.active=true;
    }else{
     this.newGroupArray1.active=false;
    }
    console.log(this.newGroupArray1);
    }
  //method to update group

    UpdateGroup(val){
      console.log(val);
      for(var i=0;i<this.GroupArray.length;i++){
        if(this.newGroupArray1.key==this.GroupArray[i].key &&
          this.newGroupArray1.code==this.GroupArray[i].code){
            this.GroupArray[i].active=val.active;
            this.GroupArray[i].value=val.value;
          
            this.newArray= new itoAdminScreenDetails();
            this.newArray.group=this.GroupArray[i].value;
            this.newArray.groupCode=this.GroupArray[i].code;
            this.newArray.active=this.GroupArray[i].active;
            this.newArray.groupId=this.GroupArray[i].key;
            this.addORupdateGroupArray.push( this.newArray);
            if(this.GroupArray[i].active==true){
              this.GroupArray[i].active="Active";
            }else{
              this.GroupArray[i].active="InActive";
            }
          }
    }
    this.displayGroupDialog=false;
  }
  //method to add group
  addGroup(val) {
    console.log(val.group.match(this.emptyTextPattern));
    var isError=false;
    if(val.group.match(this.emptyTextPattern) == null && val.groupCode.match(this.emptyTextPattern) == null){
      isError=true;
      this.showMsgGroup=true;
      this.showMsg2="Please Enter the valid input";
    }else if(val.group.match(this.emptyTextPattern) != null && val.groupCode.match(this.emptyTextPattern) == null){
      isError=true;
      this.showMsgGroup=true;
      this.showMsg2="Please Enter the valid input";
    }else if(val.group.match(this.emptyTextPattern) == null && val.groupCode.match(this.emptyTextPattern) != null){
      isError=true;
      this.showMsgGroup=true;
      this.showMsg2="Please Enter the valid input";
    }else{
      isError=false;
    }
    for(var i=0;i<this.GroupArray.length;i++){
      if(val.group==this.GroupArray[i].value){
        isError=true;
        this.showMsgGroup=true;
        this.showMsg2="group Already Exist";
      }else if(val.groupCode== this.GroupArray[i].code){
        isError=true;
        this.showMsgGroup=true;
        this.showMsg2="Group Code Already Exist";
      }
    }
    if(!isError){
    this.showMsgGroup=false;
    this.newArray= new itoAdminScreenDetails();
    var newArray1= new itoAdminScreenDetails();

   this.newArray.group=val.group;
   this.newArray.groupCode=val.groupCode;
   this.newArray.active=true;
   this.newArray.groupId=0;
   
   newArray1.value=val.group;
   newArray1.active=true;
   newArray1.key=0;
   newArray1.code=val.groupCode;
   this.GroupArray.push( newArray1);
    this.addORupdateGroupArray.push( this.newArray);
    console.log(this.addORupdateGroupArray);
    console.log(this.GroupArray);
    for(var i=0;i<this.GroupArray.length;i++){
      if(this.GroupArray[i].active==true){
        this.GroupArray[i].active="Active";
      }else if(this.GroupArray[i].active==false){
        this.GroupArray[i].active="InActive";
      }
    }
  }
  }
 
  // method to display region
  displayRegion() {
    if (this.display == false) {
      this.display = true;
    } else {
      this.display = false;
    }
  }

  //method to display role
  displayRole() {
    if (this.dispRole == false) {
      this.dispRole = true;
    } else {
      this.dispRole = false;
    }
  }

  //method to display group
  displayGroup() {
    if (this.dispGrp == false) {
      this.dispGrp = true;
    } else {
      this.dispGrp = false;
    }
  }
  //Method to cancel region dialog
  closeRegion(){
    // if(!this.editCheck){
    //   for(var i=0;i<this.RegionArray.length;i++){
    //     if(this.newRegionArray1.key==this.RegionArray[i].key){
    //       this.RegionArray[i]=this.tempRegionArray[i];
    //     }
    //   }
    // }
    //alert('haii');
    this.editCheck=false;
    for(var i=0;i<this.RegionArray.length;i++){
      if(this.RegionArray[i].active==true){
        this.RegionArray[i].active="Active";
      }else if(this.RegionArray[i].active==false){
        this.RegionArray[i].active="InActive";
      }
    }
    this.displayRegionDialog=false;

  }
  //method to close role dialog
  closeRole(){
    // if(!this.editCheck){
    //   for(var i=0;i<this.RoleArray.length;i++){
    //     if(this.newRoleArray1.key==this.RoleArray[i].key){
    //       this.RoleArray[i]=this.tempRoleArray[i];
    //     }
    //   }
    // }
    this.editCheck=false;
    for(var i=0;i<this.RoleArray.length;i++){
      if(this.RoleArray[i].active==true){
        this.RoleArray[i].active="Active";
      }else if(this.RoleArray[i].active==false){
        this.RoleArray[i].active="InActive";
      }
    }
    this.displayRoleDialog=false;
  }

//method called to close group

closeGroup(){
  // if(!this.editCheck){
  //   for(var i=0;i<this.RoleArray.length;i++){
  //     if(this.newRoleArray1.key==this.RoleArray[i].key){
  //       this.RoleArray[i]=this.tempRoleArray[i];
  //     }
  //   }
  // }
  this.editCheck=false;
  for(var i=0;i<this.GroupArray.length;i++){
    if(this.GroupArray[i].active==true){
      this.GroupArray[i].active="Active";
    }else if(this.GroupArray[i].active==false){
      this.GroupArray[i].active="InActive";
    }
  }
  this.displayGroupDialog=false;
}
}
