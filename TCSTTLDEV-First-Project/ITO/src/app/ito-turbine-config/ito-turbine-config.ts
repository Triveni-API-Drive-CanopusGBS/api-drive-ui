export class turbineConfigValues{
    quesTypeName:string;
    quesAnswerkey:number;
	answerValue:string;
    answerValueCode:string;
    quesTypeId:number;  
    cNum:string;
    variantCode:string;
    custName:string;
    defaultFlag:boolean;
      
    constructor(obj){
        this.quesTypeName=obj.quesTypeName;
        this.quesAnswerkey=obj.quesAnswerkey;
        this.answerValue=obj.answerValue;
        this.answerValueCode=obj.answerValueCode;
        this.quesTypeId=obj.quesTypeId;
        this.cNum=obj.cNum;
        this.variantCode=obj.variantCode;
        this.custName=obj.custName;
        this.defaultFlag=obj.defaultFlag;

    }
}