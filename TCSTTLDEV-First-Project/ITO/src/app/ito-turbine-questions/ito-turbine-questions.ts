export class turbineQuesValues{
    active:boolean;
    code:string;
	defaultVal:string;
    dependKey:string;
    edit:boolean;  
    key:number;  
    quesAnswerkey:number; 
    value:string;
    questionId:number;
    ansActive:boolean;
    ansList:Array<string>;

    constructor(obj){
        this.active=obj.active;
        this.code=obj.code;
        this.defaultVal=obj.defaultVal;
        this.dependKey=obj.dependKey;
        this.edit=obj.edit;
        this.key=obj.key;
        this.quesAnswerkey=obj.quesAnswerkey;
        this.value=obj.value;
        this.questionId=obj.questionId;
        this.ansActive=obj.ansActive;
        this.ansList=obj.ansList;

    }
}