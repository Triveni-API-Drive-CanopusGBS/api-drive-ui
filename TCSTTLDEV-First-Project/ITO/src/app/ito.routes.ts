import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { AlwaysAuthGuard } from './app.authguardQuot';
import { myRequestGuard } from './app.myRequestGuard';
import { quotEditGuard } from './app.quotEditGuard';
import { ItoHomePageComponent } from './ito-home-page/ito-home-page.component';
import { ItoCreateCustomerComponent } from './ito-create-customer/ito-create-customer.component';
import { ItoCreateNewUserComponent } from './ito-create-new-user/ito-create-new-user.component';
import { ItoCustomerHomeComponent } from './ito-customer-home/ito-customer-home.component';
import { ItoConsultantHomeComponent } from './ito-consultant-home/ito-consultant-home.component';
import { ItoEndUserHomeComponent } from './ito-end-user-home/ito-end-user-home.component';
import { ItoUserHomeComponent } from './ito-user-home/ito-user-home.component';
import { ItoCreateConsultantComponent } from './ito-create-consultant/ito-create-consultant.component';
import { ItoCreateEndUserComponent } from './ito-create-end-user/ito-create-end-user.component';
import { ItoUpdateConsultantComponent } from './ito-update-consultant/ito-update-consultant.component';
import { ItoUpdateCustomerComponent } from './ito-update-customer/ito-update-customer.component';
import { ItoUpdateUserComponent } from './ito-update-user/ito-update-user.component';
import { ItoUpdateEndUserComponent } from './ito-update-end-user/ito-update-end-user.component';
import { ItoCostEstimationComponent } from './ito-cost-estimation/ito-cost-estimation.component';
import { ItoCustomerInformationComponent } from './ito-customer-information/ito-customer-information.component';
import { ItoCustomerDetailsComponent } from './ito-customer-details/ito-customer-details.component';
import { ItoEndUserDetailsComponent } from './ito-end-user-details/ito-end-user-details.component';
import { ItoCustomerRequirementComponent } from './ito-customer-requirement/ito-customer-requirement.component';
import { ItoScopeOfSupplyComponent } from './ito-scope-of-supply/ito-scope-of-supply.component';
import { ItoMyQuotationsComponent } from './ito-my-quotations/ito-my-quotations.component';
import { ItoTreeComponentComponent } from './ito-tree-component/ito-tree-component.component';
import { ItoPasswordManagementComponent } from './ito-password-management/ito-password-management.component';
import { ItoTurbineConfigComponent } from './ito-turbine-config/ito-turbine-config.component';
import { ItoProfileManagementComponent } from './ito-profile-management/ito-profile-management.component';
import { EstimateCostComponent } from './estimate-cost/estimate-cost.component';
import { DBOMechanicalComponent } from './dbomechanical/dbomechanical.component';
import { AddOnComponentsComponent } from './add-on-components/add-on-components.component';
import { ErrectionCommisionComponent } from './errection-commision/errection-commision.component';
import { DBOElectricalComponent } from './dboelectrical/dboelectrical.component';
import { TenderDrawingComponent } from './tender-drawing/tender-drawing.component';
import { ExclusionAdminComponent } from './exclusion-admin/exclusion-admin.component';
import { CompleteTurbineDetailsComponent } from './complete-turbine-details/complete-turbine-details.component';
import { ItoEditQuotComponent } from './ito-edit-quot/ito-edit-quot.component';
import { ItoAdminScreenComponent } from './ito-admin-screen/ito-admin-screen.component';
import { BgmratingsComponent } from './bgmratings/bgmratings.component';
import { AdminHistoryComponent } from './admin-history/admin-history.component';
import { PerformanceAdminOtherComponent } from './performance-admin-other/performance-admin-other.component';
import { PerformanceAdminComponent } from './performance-admin/performance-admin.component';
import { AdminPerfAcDcFrmInputComponent } from './admin-perf-ac-dc-frm-input/admin-perf-ac-dc-frm-input.component';
import { AdminPerfAcDcMastComponent } from './admin-perf-ac-dc-mast/admin-perf-ac-dc-mast.component';
import { DcmratingsComponent } from './dcmratings/dcmratings.component';
import { PencentageComponent } from './pencentage/pencentage.component';
import { PanelNamesComponent } from './panel-names/panel-names.component';
import { AdminSubSupplierComponent } from './admin-sub-supplier/admin-sub-supplier.component';
import { AdminSparesComponent } from './admin-spares/admin-spares.component';
import { ElectricalHeaderFooterComponent } from './electrical-header-footer/electrical-header-footer.component';
import { ItoQuotationRangeComponent } from './ito-quotation-range/ito-quotation-range.component';
import { ItoNewVariantUpdatesComponent } from './ito-new-variant-updates/ito-new-variant-updates.component';
import { ItoTurbineFrameUpdatesComponent } from './ito-turbine-frame-updates/ito-turbine-frame-updates.component';
import { ItoBasicDetailsComponent } from './ito-basic-details/ito-basic-details.component';
import { ItoAddFrameComponent } from './ito-add-frame/ito-add-frame.component';
import { ItoAdminAddOnComponent } from './ito-admin-add-on/ito-admin-add-on.component';
import { ItoAdminScopeOfSupplyComponent } from './ito-admin-scope-of-supply/ito-admin-scope-of-supply.component';
import { ItoDefaultHomeComponent } from './ito-default-home/ito-default-home.component';
import { ItoTransportationComponent } from './ito-transportation/ito-transportation.component';
import { ItoUpdatePriceTransportComponent } from './ito-update-price-transport/ito-update-price-transport.component';
import { ItoMyworkflowComponent } from './ito-myworkflow/ito-myworkflow.component';
import { ItoPackgForwardComponent } from './ito-packg-forward/ito-packg-forward.component';
import { ItoTurbineQuestionsComponent } from './ito-turbine-questions/ito-turbine-questions.component';
import { ItoUpdatePackgComponent } from './ito-update-packg/ito-update-packg.component';
import { ItoUpdatePriceEcComponent } from './ito-update-price-ec/ito-update-price-ec.component';
import { ItoAdminTransportComponentComponent } from './ito-admin-transport-component/ito-admin-transport-component.component';
import { ItoAdminUpdateVehicleNoComponent } from './ito-admin-update-vehicle-no/ito-admin-update-vehicle-no.component';
import { ItoAdminVarientCodeComponent } from './ito-admin-varient-code/ito-admin-varient-code.component';
import { ItoAddNewQuestionsComponent } from './ito-add-new-questions/ito-add-new-questions.component';
import { ItoAdminCurrencyConvComponent } from './ito-admin-currency-conv/ito-admin-currency-conv.component';
import { ItoAdminFramesPwrPriceComponent } from './ito-admin-frames-pwr-price/ito-admin-frames-pwr-price.component';
import { ItoAdminVehicleFrameLinkComponent } from './ito-admin-vehicle-frame-link/ito-admin-vehicle-frame-link.component';
import { ItoAdminUpdateFramePercentageComponent } from './ito-admin-update-frame-percentage/ito-admin-update-frame-percentage.component';
import { ItoUpdateTurbineCostComponent } from './ito-update-turbine-cost/ito-update-turbine-cost.component';
import { ItoF2fComponentComponent } from './ito-f2f-component/ito-f2f-component.component';
import { ItoQuotCompleteComponent } from './ito-quot-complete/ito-quot-complete.component';
import { ItoVariableCostComponent } from './ito-variable-cost/ito-variable-cost.component';
import { ItoSapresComponentComponent } from './ito-sapres-component/ito-sapres-component.component';
import { SupplyChainComponentComponent } from './supply-chain-component/supply-chain-component.component';
import { UpdateCostDbomechPriceComponent } from './update-cost-dbomech-price/update-cost-dbomech-price.component';
import { UpdateCostDboelectPriceComponent } from './update-cost-dboelect-price/update-cost-dboelect-price.component';
import { ItoViewQuotComponent } from './ito-view-quot/ito-view-quot.component';
import { ItoUpdDboEleColComponent } from './ito-upd-dbo-ele-col/ito-upd-dbo-ele-col.component';
import { ItoUpdDboEleSpladdonComponent } from './ito-upd-dbo-ele-spladdon/ito-upd-dbo-ele-spladdon.component';
import { ItoUpdDboMechColComponent } from './ito-upd-dbo-mech-col/ito-upd-dbo-mech-col.component';
import { ItoUpdDboEleAddonComponent } from './ito-upd-dbo-ele-addon/ito-upd-dbo-ele-addon.component';
import { ItoUpdPriceDboEleAddinstrComponent } from './ito-upd-price-dbo-ele-addinstr/ito-upd-price-dbo-ele-addinstr.component';
import { ItoUpdateF2fAddonComponent } from './ito-update-f2f-addon/ito-update-f2f-addon.component';
import { ItoUserManualComponent } from './ito-user-manual/ito-user-manual.component';
import { UpdateTransportExportComponent } from './update-transport-export/update-transport-export.component';
import { ItoGeneralInputsComponent } from './ito-general-inputs/ito-general-inputs.component';
import { ScopeOfsupplyComponent } from './scope-ofsupply/scope-ofsupply.component';
//Dec 10 2019
//New Components
import { ItoF2fTurbineDetailsComponent } from './ito-f2f-turbine-details/ito-f2f-turbine-details.component';
import { DboEleAuxialriesComponent } from './dbo-ele-auxialries/dbo-ele-auxialries.component';
import { DboMechAuxialriesComponent } from './dbo-mech-auxialries/dbo-mech-auxialries.component';
import { MechExtendedScopeComponent } from './mech-extended-scope/mech-extended-scope.component';

import { ItoControlInstrumentationComponent } from './ito-control-instrumentation/ito-control-instrumentation.component';
import { ItoControlInstrumentationAuxComponent } from './ito-control-instrumentation-aux/ito-control-instrumentation-aux.component';
import { ItoPerformanceComponent } from './ito-performance/ito-performance.component';
import { QualityAssuranceComponent } from './quality-assurance/quality-assurance.component';
import { TerminalPointsComponent } from './terminal-points/terminal-points.component';
import { ExclusionListComponent } from './exclusion-list/exclusion-list.component';
import { SubSupplierListComponent } from './sub-supplier-list/sub-supplier-list.component';
import { TenderDrawingsComponent } from './tender-drawings/tender-drawings.component';
import {SopeOfSparesComponent} from  './sope-of-spares//sope-of-spares.component';
import { ClarificationsDeviationsComponent } from './clarifications-deviations/clarifications-deviations.component';
import { MechanicalComponent } from './mechanical/mechanical.component';
import { ElectricalComponent } from './electrical/electrical.component';
import { TechinalComponent } from './techinal/techinal.component';
import { DboEleExtdScopeComponent } from './dbo-ele-extd-scope/dbo-ele-extd-scope.component';
import { DboEleCiextdScopeComponent } from './dbo-ele-ciextd-scope/dbo-ele-ciextd-scope.component';
import { TenderDrawingNewComponent } from './tender-drawing-new/tender-drawing-new.component';
import { ControlInstrumentationComponent } from './control-instrumentation/control-instrumentation.component';
import { CommercialSec2Component } from './commercial-sec2/commercial-sec2.component';
import { CommercialSec1Component } from './commercial-sec1/commercial-sec1.component';
import { AdminQualityMastComponent } from './admin-quality-mast/admin-quality-mast.component';
import { AdminTerminalMastComponent } from './admin-terminal-mast/admin-terminal-mast.component';
import { AdminServiceMastComponent } from './admin-service-mast/admin-service-mast.component';
import { AdminAcwrListComponent } from './admin-acwr-list/admin-acwr-list.component';
import { AdminComrMonthComponent } from './admin-comr-month/admin-comr-month.component';

export const routes: Routes = [
  {
    path: 'LoginPage', component: AppComponent, children: [
      { path: 'LoginPage/HomePage', component: ItoHomePageComponent },
      { path: 'LoginPage/MyQuot', component: ItoMyQuotationsComponent }]
  },
  { path: 'AppComp', component: AppComponent },
  { path: 'CreateCustomer', component: ItoCreateCustomerComponent },
  { path: 'CreateUser', component: ItoCreateNewUserComponent },
  { path: 'HomePage', component: ItoHomePageComponent },
  { path: 'CustHome', component: ItoCustomerHomeComponent },
  { path: 'ConsultHome', component: ItoConsultantHomeComponent },
  { path: 'EndUserHome', component: ItoEndUserHomeComponent },
  { path: 'UserHome', component: ItoUserHomeComponent },
  { path: 'CreateConsultant', component: ItoCreateConsultantComponent },
  { path: 'CreateEndUser', component: ItoCreateEndUserComponent },
  { path: 'UpdateConsultant', component: ItoUpdateConsultantComponent },
  { path: 'UpdateCustomer', component: ItoUpdateCustomerComponent },
  { path: 'UpdateUser', component: ItoUpdateUserComponent },
  { path: 'UpdateEndUser', component: ItoUpdateEndUserComponent },
  //added by nidhi starts
  { path: 'adminScreen', component: ItoAdminScreenComponent },
  { path: 'Bgm', component: BgmratingsComponent },
  { path: 'AdminHistory', component: AdminHistoryComponent },
  { path: 'Dcm', component: DcmratingsComponent },
  { path: 'PerfAdmin', component: PerformanceAdminComponent },
  { path: 'PerfAdminOther', component: PerformanceAdminOtherComponent },
  { path: 'AdminPerfAcDc', component: AdminPerfAcDcFrmInputComponent },
  { path: 'AdminPerfAcDcMast', component: AdminPerfAcDcMastComponent },
  { path: 'Percentage', component: PencentageComponent },
  { path: 'TenderDrawing', component: TenderDrawingComponent },
  { path: 'ExclusionListAdmin', component: ExclusionAdminComponent },
  { path: 'Panels', component: PanelNamesComponent },
  { path: 'HeaderFooter', component: ElectricalHeaderFooterComponent },
  { path: 'reportsDashbord', component: ItoQuotationRangeComponent },
  { path: 'newVariantUpdates', component: ItoNewVariantUpdatesComponent },
  { path: 'turbineFrameUpdates', component: ItoTurbineFrameUpdatesComponent },
  { path: 'basicDetails', component: ItoBasicDetailsComponent },
  { path: 'addFrame', component: ItoAddFrameComponent },
  { path: 'adminAddOn', component: ItoAdminAddOnComponent },
  { path: 'adminScopeOfSupply', component: ItoAdminScopeOfSupplyComponent },
  { path: 'adminSSL', component: AdminSubSupplierComponent },
  { path: 'adminSpares', component: AdminSparesComponent },
  {path: 'adminQuality', component: AdminQualityMastComponent},
  { path: 'adminTerminal', component: AdminTerminalMastComponent},
 {path: 'adminService', component: AdminServiceMastComponent},
 {path: 'adminAcwrList', component: AdminAcwrListComponent},
 {path: 'adminComrMonth', component: AdminComrMonthComponent},
  //ends
  {
    path: 'CostEstimation', component: ItoCostEstimationComponent,
    children: [
      {
        path: 'CustomerInformation', component: ItoCustomerInformationComponent,
        children: [
          { path: 'CustomerDetails', component: ItoCustomerDetailsComponent },
          { path: 'EndUserDetails', component: ItoEndUserDetailsComponent },
          { path: 'CustomerRequirement', component: ItoCustomerRequirementComponent }
        ]
      },
      {
        path: 'ScopeOfsupplyCstEst', component: ScopeOfsupplyComponent,
        children: [
          { path: 'ScopeOfSupply', component: ItoScopeOfSupplyComponent },
          { path: 'GeneralInputs', component: ItoGeneralInputsComponent},
        ]
        // canActivate: [AlwaysAuthGuard],
      },
      {path: 'Mechanical', component: MechanicalComponent,
    children: [
      {path: 'DBOMechanical', component: DBOMechanicalComponent},
      {path: 'DboMechAuxialries', component: DboMechAuxialriesComponent},
      {path: 'MechExtendedScope', component: MechExtendedScopeComponent},
    ]},
      {path: 'Electrical', component: ElectricalComponent,
    children: [
      {path: 'DBOElectrical', component: DBOElectricalComponent},
      {path: 'DboEleAuxialries', component: DboEleAuxialriesComponent},
      {path: 'DboEleExtdScope', component: DboEleExtdScopeComponent},
    ]},
    
    {path: 'ControlInstrumentation', component: ControlInstrumentationComponent,
    children: [
      {path: 'ItoControlInstrumentation', component: ItoControlInstrumentationComponent},
      {path: 'ItoControlInstAux', component: ItoControlInstrumentationAuxComponent},
      {path: 'DboEleCiextdScope', component: DboEleCiextdScopeComponent},
    ]},
      //  { path: 'turbineConfig', component: ItoTurbineConfigComponent } ,
      {
        path: 'CompleteTurbineDetails', component: CompleteTurbineDetailsComponent,
        // canActivate: [AlwaysAuthGuard],
        children: [
          { path: 'AddOnComponents', component: AddOnComponentsComponent },
          { path: 'turbineConfig', component: ItoTurbineConfigComponent },
          { path: 'SupplyChain', component: SupplyChainComponentComponent },
          { path: 'Tree', component: ItoTreeComponentComponent }
        ]
      },
      {
        path: 'EstimateCost',
        component: EstimateCostComponent,
        // canActivate: [AlwaysAuthGuard],
        children: [
          { path: 'f2fComp', component: ItoF2fComponentComponent },
          { path: 'AddOnComponents', component: AddOnComponentsComponent },
          { path: 'DBOMechanical', component: DBOMechanicalComponent },
          { path: 'DBOElectrical', component: DBOElectricalComponent },
          { path: 'ErrectionCommision', component: ErrectionCommisionComponent },
          { path: 'transport', component: ItoTransportationComponent },
          { path: 'packgFrwrd', component: ItoPackgForwardComponent },
          { path: 'quotComplt', component: ItoQuotCompleteComponent },
          { path: 'variableCost', component: ItoVariableCostComponent },
          { path: 'sparesCost', component: ItoSapresComponentComponent },
          { path: 'CommercialSec2', component: CommercialSec2Component},
          { path: 'CommercialSec1', component: CommercialSec1Component},
        ]
      },
      {path: 'Techinal', component: TechinalComponent,
    children: [
      {path: 'ItoPerformance', component: ItoPerformanceComponent},
      {path: 'ScopeOfSpares', component: SopeOfSparesComponent},
      {path: 'QualityAssurance', component: QualityAssuranceComponent},
      {path: 'TerminalPoints', component: TerminalPointsComponent},
      {path: 'ExclusionList', component: ExclusionListComponent},
      {path: 'SubSupplierList', component: SubSupplierListComponent},
      {path: 'DrawingDocumentation', component: TenderDrawingsComponent},
      {path: 'ErrectionCommision', component: ErrectionCommisionComponent},
      {path: 'TenderDrawingNew', component: TenderDrawingNewComponent},
      {path: 'ClarificationsDeviation', component: ClarificationsDeviationsComponent}
    ]}
    ]
  },
  { path: 'CustomerInformation', component: ItoCustomerInformationComponent },
  { path: 'AddOnComponents', component: AddOnComponentsComponent },
  { path: 'CustomerDetails', component: ItoCustomerDetailsComponent },
  { path: 'EndUserDetails', component: ItoEndUserDetailsComponent },
  { path: 'CustomerRequirement', component: ItoCustomerRequirementComponent },
  { path: 'ScopeOfSupply', component: ItoScopeOfSupplyComponent },
  { path: 'MyQuot', component: ItoMyQuotationsComponent },
  { path: 'MyWorkFlow', component: ItoMyworkflowComponent },
  { path: 'Tree', component: ItoTreeComponentComponent },
  { path: 'passwordManagement', component: ItoPasswordManagementComponent },
  { path: 'EditProfile', component: ItoProfileManagementComponent },
  { path: 'EditQuot', component: ItoEditQuotComponent, canActivate: [quotEditGuard], canLoad: [quotEditGuard] },
  { path: 'newVarient', component: ItoNewVariantUpdatesComponent },
  { path: 'defaultHome', component: ItoDefaultHomeComponent },
  { path: 'transport', component: ItoTransportationComponent },
  { path: 'updatePriceTransport', component: ItoUpdatePriceTransportComponent, canActivate: [myRequestGuard] },
  { path: 'turbineQues', component: ItoTurbineQuestionsComponent },
  { path: 'ErrectionCommision', component: ErrectionCommisionComponent },
  { path: 'updatePckg', component: ItoUpdatePackgComponent, canActivate: [myRequestGuard] },
  { path: 'UpdatePriceEC', component: ItoUpdatePriceEcComponent, canActivate: [myRequestGuard] },
  { path: 'adminTransportComponent', component: ItoAdminTransportComponentComponent },
  { path: 'updateVehTransportDm', component: ItoAdminUpdateVehicleNoComponent, canActivate: [myRequestGuard] },
  { path: 'addVarientCode', component: ItoAdminVarientCodeComponent },
  { path: 'AddNewFrameQues', component: ItoAddNewQuestionsComponent },
  { path: 'packgFrwrd', component: ItoPackgForwardComponent },
  { path: 'CurrencyConv', component: ItoAdminCurrencyConvComponent },
  { path: 'UpdateFramePwrPrice', component: ItoAdminFramesPwrPriceComponent, canActivate: [myRequestGuard] },
  { path: 'addNewframeLink', component: ItoAdminVehicleFrameLinkComponent },
  { path: 'UpdateFramePercentageComponent', component: ItoAdminUpdateFramePercentageComponent },
  { path: 'updateTurbineCost', component: ItoUpdateTurbineCostComponent },
  { path: 'variableCost', component: ItoVariableCostComponent },
  { path: 'sparesCost', component: ItoSapresComponentComponent },
  { path: 'quotComplt', component: ItoQuotCompleteComponent },
  { path: 'SupplyChain', component: SupplyChainComponentComponent },
  { path: 'UpdateCostDbomechPrice', component: UpdateCostDbomechPriceComponent, canActivate: [myRequestGuard] },
  { path: 'UpdateCostDboelectPrice', component: UpdateCostDboelectPriceComponent, canActivate: [myRequestGuard] },
  { path: 'DBOMechanical', component: DBOMechanicalComponent },
  { path: 'DBOElectrical', component: DBOElectricalComponent },
  { path: 'turbineConfig', component: ItoTurbineConfigComponent },
  { path: 'viewQuot', component: ItoViewQuotComponent },
  { path: 'updDboMechCol', component: ItoUpdDboMechColComponent, canActivate: [myRequestGuard] },
  { path: 'updDboEleCol', component: ItoUpdDboEleColComponent, canActivate: [myRequestGuard] },
  { path: 'ItoUpdDboEleSpladdonComponent', component:ItoUpdDboEleSpladdonComponent},
  { path: 'updDboEleAddOn', component: ItoUpdDboEleAddonComponent, canActivate: [myRequestGuard] },
  { path: 'updDboEleAddInstr', component: ItoUpdPriceDboEleAddinstrComponent, canActivate: [myRequestGuard] },
  { path: 'updDboF2FAddons', component: ItoUpdateF2fAddonComponent, canActivate: [myRequestGuard] },
  { path: 'updDboF2FAddons', component: ItoUpdateF2fAddonComponent, canActivate: [myRequestGuard] },
  { path: 'UserManual', component: ItoUserManualComponent},
  { path: 'UpdateTransportExport', component: UpdateTransportExportComponent},
  { path: 'GeneralInputs', component: ItoGeneralInputsComponent},
  { path: 'ScopeOfsupplyCstEst', component: ScopeOfsupplyComponent},
  //Dec 10 2019
  //Paths added
  { path: 'ItoF2fTurbineDetails', component:ItoF2fTurbineDetailsComponent },
  { path: 'DboEleAuxialries', component: DboEleAuxialriesComponent },
  { path: 'DboMechAuxialries', component: DboMechAuxialriesComponent },
  { path: 'MechExtendedScope', component: MechExtendedScopeComponent },
  { path: 'ItoControlInstrumentation', component: ItoControlInstrumentationComponent },
  { path: 'ItoControlInstAux', component: ItoControlInstrumentationAuxComponent},
  { path: 'ItoPerformance', component:ItoPerformanceComponent },
  
  { path: 'QualityAssurance', component: QualityAssuranceComponent },
  { path: 'TerminalPoints', component: TerminalPointsComponent },
  { path: 'ExclusionList', component: ExclusionListComponent },
  { path: 'SubSupplierList', component: SubSupplierListComponent },
  { path: 'DrawingDocumentation', component: TenderDrawingsComponent },
  { path: 'ScopeOfSpares', component: SopeOfSparesComponent },
  { path: 'TenderDrawingNew', component: TenderDrawingNewComponent},
  { path: 'ClarificationsDeviation', component: ClarificationsDeviationsComponent }, 
  { path: 'DboEleExtdScope', component: DboEleExtdScopeComponent},
  { path: 'DboEleCiextdScope', component: DboEleCiextdScopeComponent},
  { path: 'CommercialSec2', component: CommercialSec2Component},
  { path: 'CommercialSec1', component: CommercialSec1Component},
  
];
//  export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
export const routing: ModuleWithProviders = RouterModule.forRoot(routes, { useHash: true });