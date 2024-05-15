export class QuotationRange{
    public columnsVal: Array<{ text: string, value: number ,defaultValue:boolean}> = [
       
        { text: "Quot No", value: 1 ,defaultValue:false},
        { text: "Customer", value: 2 ,defaultValue:false},
        { text: "Status", value: 3 ,defaultValue:false},
        { text: "Frame", value: 4 ,defaultValue:false},
        { text: "Capacity", value: 5 ,defaultValue:false},
        { text: "Please Select", value: 0, defaultValue:true},
        { text: "Modified Date", value: 6 ,defaultValue:false},
        { text: "Created By", value: 7 ,defaultValue:false}
    ];
   
    constructor() {
        
    }
}