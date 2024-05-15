export class TurbineDetails {

    Orientation: string;
    capacity: number;
    condenserType: string;
    frames: string;
    region: string;
    turbineType: string;
    opportunitySeqNO: string;
    typeOfblade: string;
    typeOfExt: string;
    bleedtype: string;
    turbineCode: string;
    typeofOffer : number;
    typeofQuot : number;
    typeOfInject: string;
    typeOfVarient: string;

    constructor(obj) {
        this.Orientation = obj.Orientation;
        this.capacity = obj.capacity;
        this.condenserType = obj.condenserType;
        this.frames = obj.frames;
        this.region = obj.region;
        this.turbineType = obj.turbineType;
        this.opportunitySeqNO = obj.opportunitySeqNO;
        this.typeOfblade = obj.typeOfblade;
        this.typeOfExt = obj.typeOfExt;
        this.turbineCode = obj.turbineCode;
        this.typeofOffer = obj.typeofOffer;
        this.typeofQuot = obj.typeofQuot;
        this.typeOfInject = obj.typeOfInjection;
        this.typeOfVarient = obj.typeOfVarient;
    }
}