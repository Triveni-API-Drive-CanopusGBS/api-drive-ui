import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MaterialModule } from './material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CdkTableModule } from '@angular/cdk/table';
// import {CdkTreeModule} from '@angular/cdk/tree';
import { AppComponent } from './app.component';
import { AlwaysAuthGuard } from './app.authguardQuot';
import { myRequestGuard } from './app.myRequestGuard';
import { Routes, RouterModule } from '@angular/router';
import { routing } from './ito.routes';
import { ItoHomePageComponent } from './ito-home-page/ito-home-page.component';
import { NgForm } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ITOLoginService } from './app.component.service';
import { HttpModule } from '@angular/http';
import { ITOHomePageService } from './ito-home-page/ito-home-page.service';
import { FilterPipePipe } from './filter-pipe.pipe';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { ItoCreateCustomerComponent } from './ito-create-customer/ito-create-customer.component';
import { ItoCreateNewUserComponent } from './ito-create-new-user/ito-create-new-user.component';
import { DomSanitizer } from '@angular/platform-browser';
import { TieredMenuModule } from 'primeng/tieredmenu';
import { ItoCustomerHomeComponent } from './ito-customer-home/ito-customer-home.component';
import { ItoConsultantHomeComponent } from './ito-consultant-home/ito-consultant-home.component';
import { ItoEndUserHomeComponent } from './ito-end-user-home/ito-end-user-home.component';
import { ItoUserHomeComponent } from './ito-user-home/ito-user-home.component';
import { ITOcustHomeService } from './ito-customer-home/ito-customer-home.service';
import { ITOconsultHomeService } from './ito-consultant-home/ito-consultant-home.service';
import { ITOEndUserHomeService } from './ito-end-user-home/ito-end-user-home.service';
import { ITOuserHomeService } from './ito-user-home/ito-user-home.service';
import { ItoCreateEndUserComponent } from './ito-create-end-user/ito-create-end-user.component';
import { ItoCreateConsultantComponent } from './ito-create-consultant/ito-create-consultant.component';
import { ITOcreateConsultHomeService } from './ito-create-consultant/ito-create-consultant.service';
import { ITOcreateCustomerService } from './ito-create-customer/ito-create-customer.service';
import { ITOcreateEndUserService } from './ito-create-end-user/ito-create-end-user.service';
import { ITOcreateNewUserService } from './ito-create-new-user/ito-create-new-user.service';
import { StorageServiceModule } from 'angular-webstorage-service';
import { ItoUpdateConsultantComponent } from './ito-update-consultant/ito-update-consultant.component';
import { ItoUpdateCustomerComponent } from './ito-update-customer/ito-update-customer.component';
import { ItoUpdateUserComponent } from './ito-update-user/ito-update-user.component';
import { ItoUpdateEndUserComponent } from './ito-update-end-user/ito-update-end-user.component';
import { ITOupdateConsultHomeService } from './ito-update-consultant/ito-update-consultant.service';
import { ITOupdateCustomerHomeService } from './ito-update-customer/ito-update-customer.service';
import { ITOupdateEndUserHomeService } from './ito-update-end-user/ito-update-end-user.service';
import { ITOupdateUserHomeService } from './ito-update-user/ito-update-user.service';
import { ItoCostEstimationComponent } from './ito-cost-estimation/ito-cost-estimation.component';
import { ItoCustomerInformationComponent } from './ito-customer-information/ito-customer-information.component';
import { ItoCustomerDetailsComponent } from './ito-customer-details/ito-customer-details.component';
import { ItoEndUserDetailsComponent } from './ito-end-user-details/ito-end-user-details.component';
import { ItoCustomerRequirementComponent } from './ito-customer-requirement/ito-customer-requirement.component';
import { ItoScopeOfSupplyComponent } from './ito-scope-of-supply/ito-scope-of-supply.component';
import { ItoMyQuotationsComponent } from './ito-my-quotations/ito-my-quotations.component';
import { ITOMyQuotPageService } from './ito-my-quotations/ito-my-quotations.service';
import { ITOcustomerDetailsService } from './ito-customer-details/ito-customer-details.service';
import { ITOendUserDetailsService } from './ito-end-user-details/ito-end-user-details.service';
import { ITOcustomerRequirementService } from './ito-customer-requirement/ito-customer-requirement.service';
import { ITOScopeOfSupplyService } from './ito-scope-of-supply/ito-scope-of-supply.service';
import { ItoTreeComponentComponent } from './ito-tree-component/ito-tree-component.component';
import { ITOtreeComponentService } from './ito-tree-component/ito-tree-component.service';
import { TreeTableModule } from 'primeng/treetable';
import { TreeNode } from 'primeng/api';
import { ItoPasswordManagementComponent } from './ito-password-management/ito-password-management.component';
import { ITOpasswordManagementService } from './ito-password-management/ito-password-management.service';
import { TooltipModule } from 'primeng/tooltip';
import { ItoTurbineConfigComponent } from './ito-turbine-config/ito-turbine-config.component';
import { ITOturbineConfigService } from './ito-turbine-config/ito-turbine-config.service';
import { ITOcustInfoService } from './ito-customer-information/ito-customer-information.service';
import { ItoProfileManagementComponent } from './ito-profile-management/ito-profile-management.component';
import { ItoProfileManagementService } from './ito-profile-management/ito-profile-management.service';
import { EstimateCostComponent } from './estimate-cost/estimate-cost.component';
import { DBOMechanicalComponent } from './dbomechanical/dbomechanical.component';
import { AddOnComponentsComponent } from './add-on-components/add-on-components.component';
import { ErrectionCommisionComponent } from './errection-commision/errection-commision.component';
import { DBOElectricalComponent } from './dboelectrical/dboelectrical.component';
import { CompleteTurbineDetailsComponent } from './complete-turbine-details/complete-turbine-details.component';
import { ITOcompleteTurbineService } from './complete-turbine-details/complete-turbine-details.service';
import { WindowRef } from './ito-customer-details/ito-customer-details.window.service';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { NgProgressModule } from 'ngx-progressbar';
import { ITOAddOnComponentsService } from './add-on-components/add-on-components.service';
import { ItoEditQuotComponent } from './ito-edit-quot/ito-edit-quot.component';
import { ITOeditQoutService } from './ito-edit-quot/ito-edit-quot.service';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { ItoTurbineQuestionsComponent } from './ito-turbine-questions/ito-turbine-questions.component';
import { ITOTurbineQuestionsService } from './ito-turbine-questions/ito-turbine-questions.service';
//added by Nidhi starts
import { ItoAdminScreenComponent } from './ito-admin-screen/ito-admin-screen.component';
import { ItoQuotationRangeComponent } from './ito-quotation-range/ito-quotation-range.component';
import { ITOMQuotRangePageService } from './ito-quotation-range/ito-quotation-range.service';
import { ItoNewVariantUpdatesComponent } from './ito-new-variant-updates/ito-new-variant-updates.component';
import { ItoTurbineFrameUpdatesComponent } from './ito-turbine-frame-updates/ito-turbine-frame-updates.component';
import { ItoBasicDetailsComponent } from './ito-basic-details/ito-basic-details.component';
import { ItoAddFrameComponent } from './ito-add-frame/ito-add-frame.component';
import { ITOAddFrameService } from './ito-add-frame/ito-add-frame.service';
import { ITOAdminScreenService } from './ito-admin-screen/ito-admin-screen.service';
import { ITOBasicDetailsService } from './ito-basic-details/ito-basic-details.service';
import { ItoAdminAddOnComponent } from './ito-admin-add-on/ito-admin-add-on.component';
import { ITOAdminAddOnService } from './ito-admin-add-on/ito-admin-add-on.service';
import { ItoAdminScopeOfSupplyComponent } from './ito-admin-scope-of-supply/ito-admin-scope-of-supply.component';
import { ITOAdminScopeOfSupplyService } from './ito-admin-scope-of-supply/ito-admin-scope-of-supply.service';
//ends
import { ItoDefaultHomeComponent } from './ito-default-home/ito-default-home.component';
import { ItoTransportationComponent } from './ito-transportation/ito-transportation.component';
import { ItoTransportationService } from './ito-transportation/ito-transportation.service';
import { ItoUpdatePriceTransportComponent } from './ito-update-price-transport/ito-update-price-transport.component';
import { ItoUpdateTransportationService } from './ito-update-price-transport/ito-update-price-transport.service';
import { ItoPackgForwardComponent } from './ito-packg-forward/ito-packg-forward.component';
import { ItoMyworkflowComponent } from './ito-myworkflow/ito-myworkflow.component';
import { TabViewModule } from 'primeng/tabview';
import { ITOMyWorkListPageService } from './ito-myworkflow/ito-myWorkFlow.service';
import { ItoUpdatePackgComponent } from './ito-update-packg/ito-update-packg.component';
import { ItoPackgForwardService } from './ito-packg-forward/ito-packg-forward.service';
import { ListboxModule } from 'primeng/listbox';
import { ITOerrectionCommisionService } from './errection-commision/errection-commision.service';
import { ItoUpdatePriceEcComponent } from './ito-update-price-ec/ito-update-price-ec.component';
import { ITOUpdatePriceEcService } from './ito-update-price-ec/ito-update-price-ec.service';
import { ItoUpdatePackgService } from './ito-update-packg/ito-update-packg.service';
import { ItoAdminTransportComponentComponent } from './ito-admin-transport-component/ito-admin-transport-component.component';
import { ItoTransportationDetailsService } from './ito-admin-transport-component/ito-admin-transport.service';
import { ItoAdminUpdateVehicleNoComponent } from './ito-admin-update-vehicle-no/ito-admin-update-vehicle-no.component';
import { ItoUpdateVehicleService } from './ito-admin-update-vehicle-no/ito-admin-update-vehicle-no.service';
import { ItoAdminVarientCodeComponent } from './ito-admin-varient-code/ito-admin-varient-code.component';
import { ItoAdminVarientCodeService } from './ito-admin-varient-code/ito-admin-varient-code.service';
import { ItoAddNewQuestionsComponent } from './ito-add-new-questions/ito-add-new-questions.component';
import { ITOAddNewQuestionsService } from './ito-add-new-questions/ito-add-new-questions.service';
import { CheckboxModule } from 'primeng/checkbox';
import { ItoAdminCurrencyConvComponent } from './ito-admin-currency-conv/ito-admin-currency-conv.component';
import { ITOCurrencyConvService } from './ito-admin-currency-conv/ito-admin-currency-conv.service';
import { DBOMechanicalComponentService } from './dbomechanical/dbomechanical.service';
import { DBOElectricalComponentService } from './dboelectrical/dboelectrical.service';
import { ItoAdminFramesPwrPriceComponent } from './ito-admin-frames-pwr-price/ito-admin-frames-pwr-price.component';
import { ItoFramesPwrPriceService } from './ito-admin-frames-pwr-price/ito-admin-frames-pwr-price.service';
import { ItoNoOfVehicleService } from './ito-admin-vehicle-frame-link/ito-admin-vehicle-frame-link.service';
import { ItoAdminVehicleFrameLinkComponent } from './ito-admin-vehicle-frame-link/ito-admin-vehicle-frame-link.component';
import { ItoAdminUpdateFramePercentageComponent } from './ito-admin-update-frame-percentage/ito-admin-update-frame-percentage.component';
import { ItoFramesPercentageService } from './ito-admin-update-frame-percentage/ito-admin-update-frame-percentage.service';
import { ItoUpdateTurbineCostComponent } from './ito-update-turbine-cost/ito-update-turbine-cost.component';
import { ItoUpdateTurbineCostService } from './ito-update-turbine-cost/ito-update-turbine-cost.service';
import { ItoF2fComponentComponent } from './ito-f2f-component/ito-f2f-component.component';
import { ItoQuotCompleteComponent } from './ito-quot-complete/ito-quot-complete.component';
import { ItoVariableCostComponent } from './ito-variable-cost/ito-variable-cost.component';
import { ItoVariableCostService } from './ito-variable-cost/ito-variable-cost.service';
import { ItoSapresComponentComponent } from './ito-sapres-component/ito-sapres-component.component';
import { ItoSapresService } from './ito-sapres-component/ito-sapers-component.service';
import { ItoQuotCompleteService } from './ito-quot-complete/ito-quot-complete.service';
import { quotEditGuard } from './app.quotEditGuard';
import { SupplyChainComponentComponent } from './supply-chain-component/supply-chain-component.component';
import { SupplyChainServiceDetails } from './supply-chain-component/supply-chain-component.service';
import { UpdateCostDbomechPriceComponent } from './update-cost-dbomech-price/update-cost-dbomech-price.component';
import { ItoUpdateCostDBOMechPrice } from './update-cost-dbomech-price/update-cost-dbomech-price.service';
import { UpdateCostDboelectPriceComponent } from './update-cost-dboelect-price/update-cost-dboelect-price.component';
import { ItoUpdateCostDBOElecPrice } from './update-cost-dboelect-price/update-cost-dboelect-price.service';
import { ITOCostEstimationService } from './ito-cost-estimation/ito-cost-estimaton.service';
import { ItoViewQuotComponent } from './ito-view-quot/ito-view-quot.component';
import { ITOViewQuotService } from './ito-view-quot/ito-view-quot.service';
import { ItoUpdDboEleColComponent } from './ito-upd-dbo-ele-col/ito-upd-dbo-ele-col.component';
import { ItoUpdDboEleSpladdonComponent } from './ito-upd-dbo-ele-spladdon/ito-upd-dbo-ele-spladdon.component';
import { ItoUpdDboMechColComponent } from './ito-upd-dbo-mech-col/ito-upd-dbo-mech-col.component';
import {ItoUpdDboEleSpladdonService} from './ito-upd-dbo-ele-spladdon/ito-upd-dbo-ele-spladdon.service';
import { ItoUpdDboEleAddonComponent } from './ito-upd-dbo-ele-addon/ito-upd-dbo-ele-addon.component';
import { ItoUpdPriceDboEleAddinstrComponent } from './ito-upd-price-dbo-ele-addinstr/ito-upd-price-dbo-ele-addinstr.component';
import { ItoUpdateF2fAddonComponent } from './ito-update-f2f-addon/ito-update-f2f-addon.component';
import {ItoUpdateCostF2FAddOnsService} from './ito-update-f2f-addon/ito-update-f2f-addon.service';
import { UpdateTransportExportComponent } from './update-transport-export/update-transport-export.component';
import { updateTransportExportService } from './update-transport-export/update-transport-export.service';
import { ItoUserManualComponent } from './ito-user-manual/ito-user-manual.component';
import { ItoUserManualService } from './ito-user-manual/ito-user-manual.service';
import { ItoGeneralInputsComponent } from './ito-general-inputs/ito-general-inputs.component';
import { ScopeOfsupplyComponent } from './scope-ofsupply/scope-ofsupply.component';
import { ITOGeneralInputsService } from './ito-general-inputs/ito-general-inputs.service';
//Dec 10 2019
//new Components created
import { ItoF2fTurbineDetailsComponent } from './ito-f2f-turbine-details/ito-f2f-turbine-details.component';
import { ItoF2fTurbineDetailsService } from './ito-f2f-turbine-details/ito-f2f-turbine-details.service';
import { DboEleAuxialriesComponent } from './dbo-ele-auxialries/dbo-ele-auxialries.component';
import { DboEleAuxialriesService } from './dbo-ele-auxialries/dbo-ele-auxialries.service';
import { DboMechAuxialriesComponent } from './dbo-mech-auxialries/dbo-mech-auxialries.component';
import { DboMechAuxialriesService } from './dbo-mech-auxialries/dbo-mech-auxialries.service';
import { MechExtendedScopeService } from './mech-extended-scope/mech-extended-scope.service';
import { ItoControlInstrumentationComponent } from './ito-control-instrumentation/ito-control-instrumentation.component';
import { ItoControlInstrumentationService } from './ito-control-instrumentation/ito-control-instrumentation.service';
import { ItoPerformanceComponent } from './ito-performance/ito-performance.component';
import { ItoPerformanceService } from './ito-performance/ito-performance.service';
import { QualityAssuranceComponent } from './quality-assurance/quality-assurance.component';
import { QualityAssuranceService } from './quality-assurance/quality-assurance.service';
import { TerminalPointsComponent } from './terminal-points/terminal-points.component';
import { TerminalPointsService } from './terminal-points/terminal-points.service';
import { ExclusionListComponent } from './exclusion-list/exclusion-list.component';
import { ExclusionListService } from './exclusion-list/exclusion-list.service';
import { SubSupplierListComponent } from './sub-supplier-list/sub-supplier-list.component';
import { SubSupplierListService } from './sub-supplier-list/sub-supplier-list.service';
import { TenderDrawingsComponent } from './tender-drawings/tender-drawings.component';
import { TenderDrawingsService } from './tender-drawings/tender-drawings.service';
import { ClarificationsDeviationsComponent } from './clarifications-deviations/clarifications-deviations.component';
import { ClarificationsDeviationsService } from './clarifications-deviations/clarifications-deviations.service';
import { BgmratingsComponentService } from './bgmratings/bgmratings.service';
import { MechanicalComponent } from './mechanical/mechanical.component';
import { ElectricalComponent } from './electrical/electrical.component';
import { TechinalComponent } from './techinal/techinal.component';
import { MechExtendedScopeComponent } from './mech-extended-scope/mech-extended-scope.component';
import { ItoControlInstrumentationAuxComponent } from './ito-control-instrumentation-aux/ito-control-instrumentation-aux.component';
import { DboEleExtdScopeComponent } from './dbo-ele-extd-scope/dbo-ele-extd-scope.component';
import {dboEleExtdScopeService} from './dbo-ele-extd-scope/dbo-ele-extd-scope.service';
import { DboEleCiextdScopeComponent } from './dbo-ele-ciextd-scope/dbo-ele-ciextd-scope.component';
import { dboEleCiExtdScopeService } from './dbo-ele-ciextd-scope/dbo-ele-ciextd-scope.service';
import { SopeOfSparesComponent } from './sope-of-spares/sope-of-spares.component';
import { TenderDrawingNewComponent } from './tender-drawing-new/tender-drawing-new.component';
import { ControlInstrumentationComponent } from './control-instrumentation/control-instrumentation.component';
import { CommercialSec2Component } from './commercial-sec2/commercial-sec2.component';
import { CommercialSec1Component } from './commercial-sec1/commercial-sec1.component';
import { PanelNamesComponent } from './panel-names/panel-names.component';
import { PencentageComponent } from './pencentage/pencentage.component';
import { BgmratingsComponent } from './bgmratings/bgmratings.component';
import { DcmratingsComponent } from './dcmratings/dcmratings.component';
import { ElectricalHeaderFooterComponent } from './electrical-header-footer/electrical-header-footer.component';
import { DisableDirectiveDirective } from './disable-directive.directive';
import { AdminHistoryComponent } from './admin-history/admin-history.component';
import { PerformanceAdminComponent } from './performance-admin/performance-admin.component';
import { AdminPerfAcDcMastComponent } from './admin-perf-ac-dc-mast/admin-perf-ac-dc-mast.component';
import { AdminPerfAcDcFrmInputComponent } from './admin-perf-ac-dc-frm-input/admin-perf-ac-dc-frm-input.component';
import { PerformanceAdminOtherComponent } from './performance-admin-other/performance-admin-other.component';
import { AdminSparesComponent } from './admin-spares/admin-spares.component';
import { AdminSubSupplierComponent } from './admin-sub-supplier/admin-sub-supplier.component';
import { AdminQualityMastComponent } from './admin-quality-mast/admin-quality-mast.component';
import { AdminTerminalMastComponent } from './admin-terminal-mast/admin-terminal-mast.component';
import { AdminServiceMastComponent } from './admin-service-mast/admin-service-mast.component';
import { AdminAcwrListComponent } from './admin-acwr-list/admin-acwr-list.component';
import { AdminComrMonthComponent } from './admin-comr-month/admin-comr-month.component';
import { TenderDrawingComponent } from './tender-drawing/tender-drawing.component';
import { ExclusionAdminComponent } from './exclusion-admin/exclusion-admin.component';


@NgModule({
  declarations: [
    AppComponent,
    ItoHomePageComponent,
    FilterPipePipe,
    ItoCreateCustomerComponent,
    ItoCreateNewUserComponent,
    ItoConsultantHomeComponent,
    ItoEndUserHomeComponent,
    ItoUserHomeComponent,
    ItoCustomerHomeComponent,
    ItoCreateEndUserComponent,
    ItoCreateConsultantComponent,
    ItoUpdateConsultantComponent,
    ItoUpdateCustomerComponent,
    ItoUpdateUserComponent,
    ItoUpdateEndUserComponent,
    ItoCostEstimationComponent,
    ItoCustomerInformationComponent,
    ItoCustomerDetailsComponent,
    ItoEndUserDetailsComponent,
    ItoCustomerRequirementComponent,
    ItoScopeOfSupplyComponent,
    ItoMyQuotationsComponent,
    ItoTreeComponentComponent,
    ItoPasswordManagementComponent,
    ItoTurbineConfigComponent,
    ItoProfileManagementComponent,
    EstimateCostComponent,
    DBOMechanicalComponent,
    AddOnComponentsComponent,
    ErrectionCommisionComponent,
    DBOElectricalComponent,
    CompleteTurbineDetailsComponent,
    ItoEditQuotComponent,
    ItoAdminScreenComponent,
    ItoQuotationRangeComponent,
    ItoNewVariantUpdatesComponent,
    ItoTurbineFrameUpdatesComponent,
    ItoBasicDetailsComponent,
    ItoAddFrameComponent,
    ItoAdminAddOnComponent,
    ItoAdminScopeOfSupplyComponent,
    ItoDefaultHomeComponent,
    ItoTransportationComponent,
    ItoUpdatePriceTransportComponent,
    ItoPackgForwardComponent,
    ItoMyworkflowComponent,
    ItoUpdatePackgComponent,
    ItoTurbineQuestionsComponent,
    ItoUpdatePriceEcComponent,
    ItoAdminTransportComponentComponent,
    ItoAdminUpdateVehicleNoComponent,
    ItoAdminVarientCodeComponent,
    ItoAddNewQuestionsComponent,
    ItoAdminCurrencyConvComponent,
    ItoAdminFramesPwrPriceComponent,
    ItoAdminVehicleFrameLinkComponent,
    ItoAdminUpdateFramePercentageComponent,
    ItoUpdateTurbineCostComponent,
    ItoF2fComponentComponent,
    ItoQuotCompleteComponent,
    ItoVariableCostComponent,
    ItoSapresComponentComponent,
    SupplyChainComponentComponent,
    UpdateCostDbomechPriceComponent,
    UpdateCostDboelectPriceComponent,
    ItoViewQuotComponent,
    ItoUpdDboEleColComponent,
    ItoUpdDboEleSpladdonComponent,
    ItoUpdDboMechColComponent,
    ItoUpdDboEleAddonComponent,
    ItoUpdPriceDboEleAddinstrComponent,
    ItoUpdateF2fAddonComponent,
    UpdateTransportExportComponent,
    ItoUserManualComponent,
    ItoGeneralInputsComponent,
    ScopeOfsupplyComponent,
    ItoF2fTurbineDetailsComponent,
    DboEleAuxialriesComponent,
    DboMechAuxialriesComponent,
    ItoControlInstrumentationComponent,
    ItoPerformanceComponent,
    QualityAssuranceComponent,
    TerminalPointsComponent,
    ExclusionListComponent,
    SubSupplierListComponent,
    TenderDrawingsComponent,
    ClarificationsDeviationsComponent,
    MechanicalComponent,
    ElectricalComponent,
    TechinalComponent,
    MechExtendedScopeComponent,
    ItoControlInstrumentationAuxComponent,
    DboEleExtdScopeComponent,
    DboEleCiextdScopeComponent,
    SopeOfSparesComponent,
    TenderDrawingNewComponent,
    ControlInstrumentationComponent,
    CommercialSec2Component,
    CommercialSec1Component,
    PanelNamesComponent,
    PencentageComponent,
    BgmratingsComponent,
    DcmratingsComponent,
    ElectricalHeaderFooterComponent,
    DisableDirectiveDirective,
    AdminHistoryComponent,
    PerformanceAdminComponent,
    AdminPerfAcDcMastComponent,
    AdminPerfAcDcFrmInputComponent,
    PerformanceAdminOtherComponent,
    AdminSparesComponent,
    AdminSubSupplierComponent,
    AdminQualityMastComponent,
    AdminTerminalMastComponent,
    AdminServiceMastComponent,
    AdminAcwrListComponent,
    AdminComrMonthComponent,
    TenderDrawingComponent,
    ExclusionAdminComponent,
    
  ],
  imports: [
    BrowserModule,
    routing,
    MaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    TableModule,
    DialogModule,
    InputTextModule,
    TieredMenuModule,
    StorageServiceModule,
    TreeTableModule,
    TooltipModule,
    ProgressSpinnerModule,
    ConfirmDialogModule,
    NgProgressModule,
    ListboxModule,
    TabViewModule,
    CheckboxModule,
  ],
  providers: [AlwaysAuthGuard, myRequestGuard, quotEditGuard, ITOHomePageService, ITOLoginService, ITOcustHomeService,
    ITOuserHomeService, ITOEndUserHomeService, ITOconsultHomeService, ITOcreateConsultHomeService, ITOcreateCustomerService,
    ITOcreateEndUserService, ITOcreateNewUserService, ITOupdateConsultHomeService, ITOupdateCustomerHomeService,
    ITOupdateEndUserHomeService, ITOupdateUserHomeService, ITOMyQuotPageService, ITOcustomerDetailsService
    , ITOendUserDetailsService, ITOcustomerRequirementService, ITOScopeOfSupplyService, ITOtreeComponentService,
    ITOpasswordManagementService, ITOturbineConfigService, ITOcustInfoService, ItoProfileManagementService,
    ITOcompleteTurbineService, WindowRef, ITOAddOnComponentsService, ITOeditQoutService, ConfirmationService,
    ITOMQuotRangePageService, ITOAddFrameService, ItoTransportationService, ItoUpdateTransportationService,
    ITOAdminScreenService, ITOBasicDetailsService, ITOAdminAddOnService, ITOAdminScopeOfSupplyService, ITOCostEstimationService,
    ITOMyWorkListPageService, ItoPackgForwardService, ITOTurbineQuestionsService, ITOerrectionCommisionService,
    ITOUpdatePriceEcService, ItoUpdatePackgService, ItoTransportationDetailsService, ItoUpdateVehicleService,
    ItoAdminVarientCodeService, ITOAddNewQuestionsService, ITOCurrencyConvService, DBOMechanicalComponentService,
    DBOElectricalComponentService, ItoFramesPwrPriceService, ItoNoOfVehicleService, ItoFramesPercentageService,
    ItoUpdateTurbineCostService, ItoVariableCostService, ItoSapresService, ItoQuotCompleteService,ITOViewQuotService,
    SupplyChainServiceDetails, ItoUpdateCostDBOMechPrice, ItoUpdateCostDBOElecPrice,
    ItoUpdDboEleSpladdonService,ItoUpdateCostF2FAddOnsService, updateTransportExportService, ItoUserManualService, ITOGeneralInputsService,
    ItoF2fTurbineDetailsService, DboEleAuxialriesService, DboMechAuxialriesService, ItoControlInstrumentationService,
    ItoPerformanceService, QualityAssuranceService,MechExtendedScopeService, TerminalPointsService, ExclusionListService, SubSupplierListService,
    TenderDrawingsService, ClarificationsDeviationsService,BgmratingsComponentService, dboEleExtdScopeService, dboEleCiExtdScopeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
