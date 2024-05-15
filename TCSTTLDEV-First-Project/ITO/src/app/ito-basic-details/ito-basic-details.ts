export class itoAdminBasicDetails {
    // Admin frame details bean attributes
    id:string;
    name:string;
    status:string;
    edit:String;
    capacity:number;
    turbineType:string;
    frameId:number;
    maxPower:number;
    minPower:number;
    framePowerId:number;
    active:boolean;
    frameDesc:string;
    turbineCode:string;
    turbineDesignCd:string;
    frameActive:boolean;
    framePowerActive:boolean;
    modifiedBy:any;

    constructor(){
        this.status='active';
        this.edit='false';
    }

}