export class turbineAnswers{
    
    ansKey:number;
	ansCode:string
	ansDesc:string
	defaultFlag:boolean; 
	quesKey:number;
	quesCode:string
	quesDesc:string
	framePowerList:any;

    constructor(obj){
        this.ansKey=obj.ansKey;
        this.ansCode=obj.ansCode;
        this.ansDesc=obj.ansDesc;
        this.defaultFlag=obj.defaultFlag;
        this.quesKey=obj.quesKey;
        this.quesCode=obj.quesCode;
        this.quesDesc=obj.quesDesc;
        this.framePowerList=obj.framePowerList;
    }
}