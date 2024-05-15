export class turbineCostBean {
     frameId: number;
	 frameCreatedBy: number;
	 frameModifiedBy: number;
	 categoryDetId: number;
	 categoryCreatedBy: number;
	 categoryModifiedBy: number;
	 framePowerId: number;
	 ssId: number; //scopeOfsupplyId
	 statusId : number;
	
	 power: number;
	 maxPower: number;
	 minPower: number;
	 price: number;

	// private Date frameCreatedDate;
	// private Date frameModifiedDate;
	// private Date categoryCreatedDate;
	// private Date categoryModifiedDate;
	
	 isFrameActive: boolean;
	 isFramePowerActive: boolean;
	 iscategoryActive: boolean;
	 extractFlag: boolean;
	 bleedsFlag: boolean;
	 defaultVal: boolean;
	
     categoryDetCode: string;
	 categoryDetDesc: string;
	 frameCode: string;
	 turbineDesignCd: string;
	 frameDesc: string;
	 turbineCode: string;
	 turbType: string;
	 turbdesignName: string;
	 framePowerDesc: string;
	 length: string;
	 dimension: string;
	 dependentCode: string;
	 ssName: string;
	 placeId: string;
	 place: string;
	 effectiveFrom: string;
	 effectiveTo: string;
	 status: string;
	 statusCode: string;
	 instrId: number;
	 condTypId: number;
	 condensingtypes: string;
	 bleedTypId: number;
     bleedType: string;
	 percentIncr: number;
	 turbInstrCost: number;
	 condInstrCost: number;
	 subContrCost: number;
	 overHeads: number;
	 totalFTFCost: number;
	 isActive: boolean;   
}