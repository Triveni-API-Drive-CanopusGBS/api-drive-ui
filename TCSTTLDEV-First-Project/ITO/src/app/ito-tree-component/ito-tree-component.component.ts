import { Component, OnInit } from '@angular/core';
import { ITOtreeComponentService } from './ito-tree-component.service';
import { TreeNode } from './ito-tree-component';
import { ITOturbineConfigService } from '../ito-turbine-config/ito-turbine-config.service';
import { ITOcustomerRequirementService } from '../ito-customer-requirement/ito-customer-requirement.service';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import {ITOLoginService} from '../app.component.service';

@Component({
  selector: 'app-ito-tree-component',
  templateUrl: './ito-tree-component.component.html',
  styleUrls: ['./ito-tree-component.component.css']
})
export class ItoTreeComponentComponent implements OnInit {

  files: TreeNode[];
  cols: any[];
  frozenCols: any[];
  scrollableCols: any[];
  dummy: TreeNode[];
  newDummy: TreeNode[];
  newFiles: TreeNode[];
  dialogStatus: boolean;
  dialogState: boolean;
  shopConvDetails: Array<any> = [];
  OverHeadCostDetails: Array<any> = [];
  availableCs: Array<any> = [];
  quotNumber: any;
  hidespinner: boolean = true;
  displayTree: boolean;
  f2fDataList: Array<any> = [];
  selectedLines: Array<any> = [];
  respons: any;
  removedlinesGrid: boolean = false; // display removed lines grid 
  showAlert: string = '';
  latestC: any; // variable to get latest C number
  latestCdata: any = [];   // variable to get latest C number cost
  latestCdialog: boolean = false; // variable to enable dialog
  latestCmsg: string = ``; // variable to get proper message after procceding with selected C number
  selectedCnum = this._ITOcustomerRequirementService.saveBasicDet.cNewNumber;
  showsucMsg: String = ``;
  disableStatus: Array<boolean> = [];
  startInd: number;
  endInd: number;
  zConut:Array<any>=[];

  constructor(private _ITOtreeComponentService: ITOtreeComponentService,
    private _ITOturbineConfigService: ITOturbineConfigService,
    private _ITOcustomerRequirementService: ITOcustomerRequirementService, private _ITOLoginService:ITOLoginService,
   private confirmationService: ConfirmationService) {

    this.quotNumber = this._ITOcustomerRequirementService.quotNumber;
    this.latestCdata = this._ITOturbineConfigService.latestCDetails;
    console.log(this.latestCdata);
    this.newDummy = this._ITOtreeComponentService.getData();
    console.log(this.newDummy);
    console.log(this._ITOcustomerRequirementService.saveBasicDet);
    this.availableCs = this._ITOturbineConfigService.AvailableCnums;
    console.log(this.availableCs);
    console.log(this._ITOturbineConfigService.F2FBOMTree);
    // this.newFiles = this._ITOturbineConfigService.F2FBOMTree;
    this._ITOtreeComponentService.getF2FData(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
      this.respons = res.saveBasicDetails;
      this.newFiles = res.tree;
      console.log(this.newFiles);
      console.log(res);
      this.dummy = this.newFiles[0].data;
      this.f2fDataList = res.f2FDataList;

    })
  }

  ngOnInit() {

    this._ITOLoginService.dialogMsgApp = false;
    console.log(this.dummy);
    // this.files=this._ITOturbineConfigService.F2FBOMTree;
    // console.log(this.files);
    // this.files=this._ITOturbineConfigService.F2FBOMTree;

    this.cols = [
      { field: 'childMaterialCode', header: 'Material Code' },
      { field: 'wbsElementNo', header: 'WBS Element' },
      { field: 'materialDesc', header: 'Description' },
      { field: 'prodOrder', header: 'Production order' },
      { field: 'overheadCost', header: 'Over Head' },
      { field: 'rawMaterialCost', header: 'Raw Material' },
      { field: 'shopCoverCost', header: 'Shop Conversion' },
      { field: 'subContrCost', header: 'Sub Contract' },
      { field: 'ubo', header: 'UBO' },
      { field: 'semiFinished', header: 'Semi Finished' },
      { field: 'turbineInstrumentCost', header: 'Turbine Material' },
      { field: 'total', header: 'Total' },
      { field: 'misl', header: 'Misc' },

    ];
    this.scrollableCols = [
      { field: 'childMaterialCode', header: 'Material Code' },
      { field: 'materialDesc', header: 'Description' },
      { field: 'prodOrder', header: 'Production order' },
      { field: 'overheadCost', header: 'Over Head' },
      { field: 'rawMaterialCost', header: 'Raw Material' },
      { field: 'shopCoverCost', header: 'Shop Conversion' },
      { field: 'subContrCost', header: 'Sub Contract' },
      { field: 'ubo', header: 'UBO' },
      { field: 'turbineInstrumentCost', header: 'Turbine Instrument Cost' },
      { field: 'total', header: 'Total' }
    ];
    this.frozenCols = [
      { field: 'childMaterialCode', header: 'Material Code' }
    ];
  }

  shopConvPop(value) {
    console.log(value);

    this._ITOturbineConfigService.getF2FData(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {

      if (value.shopCoverCost > 0) {
        res.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
        res.f2fCostBean.parentMaterialCode = value.parentMaterialCode;
        res.f2fCostBean.cNum = res.saveBasicDetails.cNewNumber;
        console.log(res);
        this._ITOturbineConfigService.GetShopConvPop(res).subscribe(res => {
          console.log(res);
          this.shopConvDetails = res.f2fCostBeanList;
        });
        this.dialogStatus = true;
      }
      else {
        alert("cost is 0");
        this.dialogStatus = false;
      }
    });
  }

  getTree(Cnum) {
    console.log(Cnum);
    this.newFiles = [];
    this.showsucMsg = ``;
    this._ITOcustomerRequirementService.saveBasicDet.cNewNumber = Cnum;
    for (let s of this.availableCs) {
      if (s.cNum == Cnum) {
        this._ITOcustomerRequirementService.saveBasicDet.selectedCProjectKey = s.variantCode;
      }
    }
    this._ITOtreeComponentService.getF2FData(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {
      this.newFiles = res.tree;
      console.log(res, this.newFiles);
      this.dummy = this.newFiles[0].data;
      this._ITOtreeComponentService.getOneLineBom(res.saveBasicDetails).subscribe(resp => {
        console.log(resp);
        this._ITOcustomerRequirementService.saveBasicDet = res.saveBasicDetails;
        this._ITOturbineConfigService.sendMessage(resp.oneLineBom);
      })
    })
  }

  showOvhCost(value) {
    console.log(value);
    this._ITOturbineConfigService.getF2FData(this._ITOcustomerRequirementService.saveBasicDet).subscribe(res => {

      if (value.overheadCost > 0) {
        res.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
        res.f2fCostBean.parentMaterialCode = value.parentMaterialCode;
        res.f2fCostBean.cNum = res.saveBasicDetails.cNewNumber;
        this._ITOturbineConfigService.GetOverheadCostPop(res).subscribe(res => {
          console.log(res);
          this.OverHeadCostDetails = res.f2fCostBeanList;
        });
        this.dialogState = true;
      }
      else {
        this._ITOLoginService.openSuccMsg("cost is 0");
        //alert("cost is 0");
        this.dialogState = false;
      }
    });
  }

  showTreeGrid() {
    this.displayTree = true;
    console.log(this.f2fDataList)
  }

  closeDialog() {
    this.dialogStatus = false;
    this.dialogState = false;
    this.displayTree = false;
    this.latestCdialog = false;
  }
  selectLines(event, rowData) {
    this.startInd = 0;
    this.endInd = 0;
    console.log(rowData);

    if (event.target.checked) {
      console.log("checked");

      for (let f = 0; f < this.f2fDataList.length; f++) {
        if (this.f2fDataList[f] === rowData) {
          this.startInd = f;
        }
      }
      console.log(this.startInd);
      for (let g = this.startInd + 1; g < this.f2fDataList.length; g++) {
        if (this.f2fDataList[g].levelNo === rowData.levelNo) {
          console.log("from" + " " + this.startInd + "to" + " " + g);
          this.endInd = g;
          break;
        }
      }
      console.log(this.endInd);
      for (let h = this.startInd, j = this.startInd + 1; h < this.endInd, h < this.endInd; h++ , j++) {
        this.f2fDataList[h].flag = true;

        if ((this.endInd - this.startInd) == 1) {
          this.f2fDataList[h].disableFlg = false;
          this.f2fDataList[h].parentFlg = true;

        }
        else if (this.startInd < this.endInd) {

          if (this.f2fDataList[h] === rowData) {
            this.f2fDataList[h].parentFlg = true;
          }
          else {
            this.f2fDataList[h].parentFlg = false;
          }

          this.f2fDataList[j].disableFlg = true;
        }


      }
      console.log(this.disableStatus);
    }

    else if (!event.target.checked) {
      console.log("unchecked");
      for (let f = 0; f < this.f2fDataList.length; f++) {
        if (this.f2fDataList[f] === rowData) {
          this.startInd = f;
        }
      }
      for (let g = this.startInd + 1; g < this.f2fDataList.length; g++) {
        if (this.f2fDataList[g].levelNo === rowData.levelNo) {
          console.log("from" + " " + this.startInd + "to" + " " + g);
          this.endInd = g;
          break;
        }
      }
      for (let h = this.startInd; h < this.endInd; h++) {
        this.f2fDataList[h].flag = false;
        this.f2fDataList[h].disableFlg = false;
        this.f2fDataList[h].parentFlg =false;
      }
      console.log(this.disableStatus);
    }
  }
  removeLines() {
    this.selectedLines = [];
    console.log(this.selectedLines);
    for (let i = 0; i < this.f2fDataList.length; i++) {
      if (this.f2fDataList[i].parentFlg == true) {
        this.selectedLines.push(this.f2fDataList[i]);
      }
    }
    console.log(this.selectedLines);

    this._ITOtreeComponentService.getQuotationList().subscribe(res => {
      console.log(res);
      console.log(this._ITOcustomerRequirementService.saveBasicDet);
      res.saveBasicDetails = this._ITOcustomerRequirementService.saveBasicDet;
      res.saveBasicDetails.zCount=[];
      if(this.selectedLines.length==0){
        res.saveBasicDetails.allDataFlag=1;
      }
      else{
        res.saveBasicDetails.allDataFlag=0;
        for (let s of this.selectedLines) {
          res.saveBasicDetails.zCount.push(s.id);
        }
      }
  
      console.log(res);
      this.removedlinesGrid = true;
      this._ITOtreeComponentService.editOneLineBom(res.saveBasicDetails).subscribe(resp => {
        console.log(resp);
        this._ITOturbineConfigService.sendMessage(resp.oneLineBom);

      })
    })
  }
  proceed() {
    this._ITOtreeComponentService.saveCProject(this._ITOcustomerRequirementService.saveBasicDet).subscribe(resp => {
      console.log(resp);
      this.latestCmsg = `C project number - ${this.selectedCnum} is tagged to the quotation`;
    });
  }
  saveCProject() {
    this.latestCmsg = ``;
    console.log(this._ITOcustomerRequirementService.saveBasicDet);

    for (let ac = 0; ac < this.availableCs.length; ac++) {
      console.log(this.availableCs[ac].cNum, this._ITOcustomerRequirementService.saveBasicDet.cNewNumber);
      if (this.availableCs[ac].defaultFlag == true) {
        this.latestC = this.availableCs[ac].cNum;
        console.log(this.latestC);
      }
    }
    for (let ac = 0; ac < this.availableCs.length; ac++) {
      if (this._ITOcustomerRequirementService.saveBasicDet.cNewNumber == this.availableCs[ac].cNum) {
        if (this.availableCs[ac].defaultFlag == false) {
          this.latestCdialog = true;
        }
        else {
          this.showAlert = '';
          this._ITOtreeComponentService.saveCProject(this._ITOcustomerRequirementService.saveBasicDet).subscribe(resp => {
            console.log(resp);
            this.showsucMsg = `C project number - ${this._ITOcustomerRequirementService.saveBasicDet.cNewNumber} is tagged to the quotation`;
          });
        }
      }
    }
  }

}
