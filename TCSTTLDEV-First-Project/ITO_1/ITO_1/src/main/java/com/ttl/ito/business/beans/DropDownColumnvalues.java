package com.ttl.ito.business.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DropDownColumnvalues {

	Map<String, List<SelectBox>> custDetails = new HashMap<String, List<SelectBox>>(); // Customer List
	Map<String, List<SelectBox>> constDetails = new HashMap<String, List<SelectBox>>(); // Constants List
	Map<String, List<SelectBox>> departmentsList = new HashMap<String, List<SelectBox>>(); // Department List
	Map<String, List<SelectBox>> trnstypeList = new HashMap<String, List<SelectBox>>(); // trnstype List
	Map<String, List<SelectBox>> regionsList = new HashMap<String, List<SelectBox>>(); // Regions List
	Map<String, List<SelectBox>> rolesList = new HashMap<String, List<SelectBox>>(); // Roles List
	
	public Map<String, List<TurbineDetails>> getTypeOfFrameVarientList() {
		return typeOfFrameVarientList;
	}

	public void setTypeOfFrameVarientList(Map<String, List<TurbineDetails>> typeOfFrameVarientList) {
		this.typeOfFrameVarientList = typeOfFrameVarientList;
	}

	Map<String, List<TurbineDetails>> typeOfFrameVarientList = new HashMap<String, List<TurbineDetails>>(); 
	Map<String, List<TurbineDetails>> typeOfOfferList = new HashMap<String, List<TurbineDetails>>(); 
	Map<String, List<TurbineDetails>> typeOfNewList = new HashMap<String, List<TurbineDetails>>(); 
	Map<String, List<TurbineDetails>> typeOfNewList1 = new HashMap<String, List<TurbineDetails>>(); 
	public Map<String, List<TurbineDetails>> getTypeOfNewList() {
		return typeOfNewList;
	}

	public void setTypeOfNewList(Map<String, List<TurbineDetails>> typeOfNewList) {
		this.typeOfNewList = typeOfNewList;
	}
	public Map<String, List<TurbineDetails>> getTypeOfNewList1() {
		return typeOfNewList1;
	}

	public void setTypeOfNewList1(Map<String, List<TurbineDetails>> typeOfNewList1) {
		this.typeOfNewList1 = typeOfNewList1;
	}

	Map<String, List<TurbineDetails>> typeOfCustomerList = new HashMap<String, List<TurbineDetails>>(); //offer list
	Map<String, List<TurbineDetails>> typeOfQuotationList = new HashMap<String, List<TurbineDetails>>(); //Quotation list
	Map<String, List<TurbineDetails>> typeOfInjectionList = new HashMap<String, List<TurbineDetails>>(); //Injection list
	Map<String, List<TurbineDetails>> typeOfVarientList = new HashMap<String, List<TurbineDetails>>();  // Varient List
	Map<String, List<TurbineDetails>> typesOfTurbine = new HashMap<String, List<TurbineDetails>>(); // Turbines List
	Map<String, List<TurbineDetails>> typesOfNewTurbine = new HashMap<String, List<TurbineDetails>>(); //new type of turbine
	Map<String, List<TurbineDetails>> typesOfCondensor = new HashMap<String, List<TurbineDetails>>(); // Condensor List
	Map<String, List<ScopeOfSupply>> scopeOfSupply = new HashMap<String, List<ScopeOfSupply>>(); // scope of Supply List
	Map<String, List<TurbineDetails>>  frameWithPowerListNew = new HashMap<String, List<TurbineDetails>>();
	public Map<String, List<TurbineDetails>> getFrameWithPowerListNew() {
		return frameWithPowerListNew;
	}

	public void setFrameWithPowerListNew(Map<String, List<TurbineDetails>> frameWithPowerListNew) {
		this.frameWithPowerListNew = frameWithPowerListNew;
	}

	Map<String, List<TurbineDetails>> frames = new HashMap<String, List<TurbineDetails>>(); // Frames List
	Map<String, List<TurbineDetails>> frameWithPowerList = new HashMap<String, List<TurbineDetails>>(); // Frames List
	Map<String, List<TurbineDetails>> framePowerList = new HashMap<String, List<TurbineDetails>>(); // Frames List
	Map<String, List<TurbineDetails>> orientationTypes = new HashMap<String, List<TurbineDetails>>(); 
	Map<String, List<TurbineDetails>> modelTypes = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> addOnComponents = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> CustomerType = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> TransportationType = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> statusList = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> ComponentTypes = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> EnCustTypeList = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> EnCCharges = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> EnCLoadingDomestic = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> USDollerList = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> EnCLodging = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> vehiclesList = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> NewlyAdded_vehiclesList = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> packageList = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> bleedTypeList = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<TurbineDetails>> newframeWithPowerForExportTrans = new HashMap<String, List<TurbineDetails>>();
	Map<String, List<DBOBean>> glandSysTypeList = new HashMap<String, List<DBOBean>>();
	Map<String, List<DBOBean>> lubOilTypeList= new HashMap<String, List<DBOBean>>();
	Map<String, List<DBOBean>> lubOilSubTypeList = new HashMap<String, List<DBOBean>>();
	Map<String, List<DBOBean>> f2fCacheList = new HashMap<String, List<DBOBean>>();
	
	Map<String, List<AddOnComponent>> vms = new HashMap<String, List<AddOnComponent>>();
	Map<String, List<AddOnComponent>> governorType1 = new HashMap<String, List<AddOnComponent>>();
	Map<String, List<AddOnComponent>> governorType2 = new HashMap<String, List<AddOnComponent>>();
	Map<String, List<AddOnComponent>> governorType3 = new HashMap<String, List<AddOnComponent>>();
	Map<String, List<AddOnComponent>> addOnCompoInDetailList = new HashMap<String, List<AddOnComponent>>();
	
	Map<String, List<LocationBean>> stateList = new HashMap<String, List<LocationBean>>();
	
	Map<String, List<TransportationDetailsBean>> VehicleWithUnitPrice = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList1 = new HashMap<String, List<TransportationDetailsBean>>();
	public Map<String, List<TransportationDetailsBean>> getPlaceList1() {
		return placeList1;
	}

	public void setPlaceList1(Map<String, List<TransportationDetailsBean>> placeList1) {
		this.placeList1 = placeList1;
	}

	public Map<String, List<TransportationDetailsBean>> getPlaceList2() {
		return placeList2;
	}

	public void setPlaceList2(Map<String, List<TransportationDetailsBean>> placeList2) {
		this.placeList2 = placeList2;
	}

	public Map<String, List<TransportationDetailsBean>> getPlaceList3() {
		return placeList3;
	}

	public void setPlaceList3(Map<String, List<TransportationDetailsBean>> placeList3) {
		this.placeList3 = placeList3;
	}

	public Map<String, List<TransportationDetailsBean>> getPlaceList4() {
		return placeList4;
	}

	public void setPlaceList4(Map<String, List<TransportationDetailsBean>> placeList4) {
		this.placeList4 = placeList4;
	}

	public Map<String, List<TransportationDetailsBean>> getPlaceList5() {
		return placeList5;
	}

	public void setPlaceList5(Map<String, List<TransportationDetailsBean>> placeList5) {
		this.placeList5 = placeList5;
	}

	public Map<String, List<TransportationDetailsBean>> getPlaceList6() {
		return placeList6;
	}

	public void setPlaceList6(Map<String, List<TransportationDetailsBean>> placeList6) {
		this.placeList6 = placeList6;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList7() {
		return placeList7;
	}

	public void setPlaceList7(Map<String, List<TransportationDetailsBean>> placeList7) {
		this.placeList7 = placeList7;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList8() {
		return placeList8;
	}

	public void setPlaceList8(Map<String, List<TransportationDetailsBean>> placeList8) {
		this.placeList8 = placeList8;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList9() {
		return placeList9;
	}

	public void setPlaceList9(Map<String, List<TransportationDetailsBean>> placeList9) {
		this.placeList9 = placeList9;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList10() {
		return placeList10;
	}

	public void setPlaceList10(Map<String, List<TransportationDetailsBean>> placeList10) {
		this.placeList10 = placeList10;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList11() {
		return placeList11;
	}

	public void setPlaceList11(Map<String, List<TransportationDetailsBean>> placeList11) {
		this.placeList11 = placeList11;
	}
	
	public Map<String, List<TransportationDetailsBean>> getPlaceList12() {
		return placeList12;
	}

	public void setPlaceList12(Map<String, List<TransportationDetailsBean>> placeList12) {
		this.placeList12 = placeList12;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList13() {
		return placeList13;
	}

	public void setPlaceList13(Map<String, List<TransportationDetailsBean>> placeList13) {
		this.placeList13 = placeList13;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList14() {
		return placeList14;
	}

	public void setPlaceList14(Map<String, List<TransportationDetailsBean>> placeList14) {
		this.placeList14 = placeList14;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList15() {
		return placeList15;
	}

	public void setPlaceList15(Map<String, List<TransportationDetailsBean>> placeList15) {
		this.placeList15 = placeList15;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList16() {
		return placeList16;
	}

	public void setPlaceList16(Map<String, List<TransportationDetailsBean>> placeList16) {
		this.placeList16 = placeList16;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList17() {
		return placeList17;
	}

	public void setPlaceList17(Map<String, List<TransportationDetailsBean>> placeList17) {
		this.placeList17 = placeList17;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList18() {
		return placeList18;
	}

	public void setPlaceList18(Map<String, List<TransportationDetailsBean>> placeList18) {
		this.placeList18 = placeList18;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList19() {
		return placeList19;
	}

	public void setPlaceList19(Map<String, List<TransportationDetailsBean>> placeList19) {
		this.placeList19 = placeList19;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList20() {
		return placeList20;
	}

	public void setPlaceList20(Map<String, List<TransportationDetailsBean>> placeList20) {
		this.placeList20 = placeList20;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList21() {
		return placeList21;
	}

	public void setPlaceList21(Map<String, List<TransportationDetailsBean>> placeList21) {
		this.placeList21 = placeList21;
	}
	public Map<String, List<TransportationDetailsBean>> getPlaceList22() {
		return placeList22;
	}

	public void setPlaceList22(Map<String, List<TransportationDetailsBean>> placeList22) {
		this.placeList22 = placeList22;
	}
	Map<String, List<TransportationDetailsBean>> placeList2 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList3 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList4 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList5 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList6 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList7 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList8 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList9 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList10 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList11 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList12 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList13 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList14 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList15 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList16 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList17 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList18 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList19 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList20 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList21 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> placeList22 = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> exportTransportDetails = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> portDetails = new HashMap<String, List<TransportationDetailsBean>>();
	Map<String, List<TransportationDetailsBean>> transType = new HashMap<String, List<TransportationDetailsBean>>();
	
	Map<String, List<F2FUBOBean>> categoryList = new HashMap<String, List<F2FUBOBean>>();
	Map<String, List<F2FUBOBean>> materialList = new HashMap<String, List<F2FUBOBean>>();
	Map<String, List<CurrencyBean>> currencyList = new HashMap<String, List<CurrencyBean>>();
	Map<String, List<DBOBean>> dboMechanicalList = new HashMap<String, List<DBOBean>>();
	Map<String, List<DBOBean>> dboElectricalList = new HashMap<String, List<DBOBean>>();



	
	
	public Map<String, List<SelectBox>> getCustDetails() {
		return custDetails;
	}
	
	public void setCustDetails(Map<String, List<SelectBox>> custDetails) {
		this.custDetails = custDetails;
	}
	
	public Map<String, List<SelectBox>> getConstDetails() {
		return constDetails;
	}
	
	public void setConstDetails(Map<String, List<SelectBox>> constDetails) {
		this.constDetails = constDetails;
	}
	
	/**
	 * @return the departmentsList
	 */
	public Map<String, List<SelectBox>> getDepartmentsList() {
		return departmentsList;
	}
	
	/**
	 * @param departmentsList the departmentsList to set
	 */
	public void setDepartmentsList(Map<String, List<SelectBox>> departmentsList) {
		this.departmentsList = departmentsList;
	}
	
	/**
	 * @return the regionsList
	 */
	public Map<String, List<SelectBox>> getRegionsList() {
		return regionsList;
	}

	/**
	 * @param regionsList the regionsList to set
	 */
	public void setRegionsList(Map<String, List<SelectBox>> regionsList) {
		this.regionsList = regionsList;
	}

	/**
	 * @return the rolesList
	 */
	public Map<String, List<SelectBox>> getRolesList() {
		return rolesList;
	}

	/**
	 * @param rolesList the rolesList to set
	 */
	public void setRolesList(Map<String, List<SelectBox>> rolesList) {
		this.rolesList = rolesList;
	}

	/**
	 * @return the typesOfTurbine
	 */
	public Map<String, List<TurbineDetails>> getTypesOfTurbine() {
		return typesOfTurbine;
	}

	/**
	 * @param typesOfTurbine the typesOfTurbine to set
	 */
	public void setTypesOfTurbine(Map<String, List<TurbineDetails>> typesOfTurbine) {
		this.typesOfTurbine = typesOfTurbine;
	}

	/**
	 * @return the typesOfCondensor
	 */
	public Map<String, List<TurbineDetails>> getTypesOfCondensor() {
		return typesOfCondensor;
	}

	/**
	 * @param typesOfCondensor the typesOfCondensor to set
	 */
	public void setTypesOfCondensor(Map<String, List<TurbineDetails>> typesOfCondensor) {
		this.typesOfCondensor = typesOfCondensor;
	}

	
	/**
	 * @return the frames
	 */
	public Map<String, List<TurbineDetails>> getFrames() {
		return frames;
	}

	/**
	 * @param frames the frames to set
	 */
	public void setFrames(Map<String, List<TurbineDetails>> frames) {
		this.frames = frames;
	}

	/**
	 * @return the orientationTypes
	 */
	public Map<String, List<TurbineDetails>> getOrientationTypes() {
		return orientationTypes;
	}

	/**
	 * @param orientationTypes the orientationTypes to set
	 */
	public void setOrientationTypes(Map<String, List<TurbineDetails>> orientationTypes) {
		this.orientationTypes = orientationTypes;
	}

	/**
	 * @return the modelTypes
	 */
	public Map<String, List<TurbineDetails>> getModelTypes() {
		return modelTypes;
	}

	/**
	 * @param modelTypes the modelTypes to set
	 */
	public void setModelTypes(Map<String, List<TurbineDetails>> modelTypes) {
		this.modelTypes = modelTypes;
	}

	/**
	 * @return the addOnComponents
	 */
	public Map<String, List<TurbineDetails>> getAddOnComponents() {
		return addOnComponents;
	}

	/**
	 * @param addOnComponents the addOnComponents to set
	 */
	public void setAddOnComponents(Map<String, List<TurbineDetails>> addOnComponents) {
		this.addOnComponents = addOnComponents;
	}

	/**
	 * @return the scopeOfSupply
	 */
	public Map<String, List<ScopeOfSupply>> getScopeOfSupply() {
		return scopeOfSupply;
	}

	/**
	 * @param scopeOfSupply the scopeOfSupply to set
	 */
	public void setScopeOfSupply(Map<String, List<ScopeOfSupply>> scopeOfSupply) {
		this.scopeOfSupply = scopeOfSupply;
	}

	/**
	 * @return the addOnCompoInDetailList
	 */
	public Map<String, List<AddOnComponent>> getAddOnCompoInDetailList() {
		return addOnCompoInDetailList;
	}

	/**
	 * @param addOnCompoInDetailList the addOnCompoInDetailList to set
	 */
	public void setAddOnCompoInDetailList(Map<String, List<AddOnComponent>> addOnCompoInDetailList) {
		this.addOnCompoInDetailList = addOnCompoInDetailList;
	}

	/**
	 * @return the stateList
	 */
	public Map<String, List<LocationBean>> getStateList() {
		return stateList;
	}

	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(Map<String, List<LocationBean>> stateList) {
		this.stateList = stateList;
	}

	/**
	 * @return the frameWithPowerList
	 */
	public Map<String, List<TurbineDetails>> getFrameWithPowerList() {
		return frameWithPowerList;
	}

	/**
	 * @param frameWithPowerList the frameWithPowerList to set
	 */
	public void setFrameWithPowerList(Map<String, List<TurbineDetails>> frameWithPowerList) {
		this.frameWithPowerList = frameWithPowerList;
	}

	/**
	 * @return the vms
	 */
	public Map<String, List<AddOnComponent>> getVms() {
		return vms;
	}

	/**
	 * @param vms the vms to set
	 */
	public void setVms(Map<String, List<AddOnComponent>> vms) {
		this.vms = vms;
	}

	/**
	 * @return the governorType1
	 */
	public Map<String, List<AddOnComponent>> getGovernorType1() {
		return governorType1;
	}

	/**
	 * @param governorType1 the governorType1 to set
	 */
	public void setGovernorType1(Map<String, List<AddOnComponent>> governorType1) {
		this.governorType1 = governorType1;
	}

	/**
	 * @return the governorType2
	 */
	public Map<String, List<AddOnComponent>> getGovernorType2() {
		return governorType2;
	}

	/**
	 * @param governorType2 the governorType2 to set
	 */
	public void setGovernorType2(Map<String, List<AddOnComponent>> governorType2) {
		this.governorType2 = governorType2;
	}

	/**
	 * @return the governorType3
	 */
	public Map<String, List<AddOnComponent>> getGovernorType3() {
		return governorType3;
	}

	/**
	 * @param governorType3 the governorType3 to set
	 */
	public void setGovernorType3(Map<String, List<AddOnComponent>> governorType3) {
		this.governorType3 = governorType3;
	}

	/**
	 * @return the typesOfNewTurbine
	 */
	public Map<String, List<TurbineDetails>> getTypesOfNewTurbine() {
		return typesOfNewTurbine;
	}

	/**
	 * @param typesOfNewTurbine the typesOfNewTurbine to set
	 */
	public void setTypesOfNewTurbine(Map<String, List<TurbineDetails>> typesOfNewTurbine) {
		this.typesOfNewTurbine = typesOfNewTurbine;
	}

	/**
	 * @return the customerType
	 */
	public Map<String, List<TurbineDetails>> getCustomerType() {
		return CustomerType;
	}

	/**
	 * @param customerType the customerType to set
	 */
	public void setCustomerType(Map<String, List<TurbineDetails>> customerType) {
		CustomerType = customerType;
	}

	/**
	 * @return the transportationType
	 */
	public Map<String, List<TurbineDetails>> getTransportationType() {
		return TransportationType;
	}

	/**
	 * @param transportationType the transportationType to set
	 */
	public void setTransportationType(Map<String, List<TurbineDetails>> transportationType) {
		TransportationType = transportationType;
	}

	

	/**
	 * @return the vehicleWithUnitPrice
	 */
	public Map<String, List<TransportationDetailsBean>> getVehicleWithUnitPrice() {
		return VehicleWithUnitPrice;
	}

	/**
	 * @param vehicleWithUnitPrice the vehicleWithUnitPrice to set
	 */
	public void setVehicleWithUnitPrice(Map<String, List<TransportationDetailsBean>> vehicleWithUnitPrice) {
		VehicleWithUnitPrice = vehicleWithUnitPrice;
	}

	/**
	 * @return the placeList
	 */
	public Map<String, List<TransportationDetailsBean>> getPlaceList() {
		return placeList;
	}

	/**
	 * @param placeList the placeList to set
	 */
	public void setPlaceList(Map<String, List<TransportationDetailsBean>> placeList) {
		this.placeList = placeList;
	}

	/**
	 * @return the componentTypes
	 */
	public Map<String, List<TurbineDetails>> getComponentTypes() {
		return ComponentTypes;
	}

	/**
	 * @param componentTypes the componentTypes to set
	 */
	public void setComponentTypes(Map<String, List<TurbineDetails>> componentTypes) {
		ComponentTypes = componentTypes;
	}

	/**
	 * @return the enCCharges
	 */
	public Map<String, List<TurbineDetails>> getEnCCharges() {
		return EnCCharges;
	}

	/**
	 * @param enCCharges the enCCharges to set
	 */
	public void setEnCCharges(Map<String, List<TurbineDetails>> enCCharges) {
		EnCCharges = enCCharges;
	}

	/**
	 * @return the vehiclesList
	 */
	public Map<String, List<TurbineDetails>> getVehiclesList() {
		return vehiclesList;
	}

	/**
	 * @param vehiclesList the vehiclesList to set
	 */
	public void setVehiclesList(Map<String, List<TurbineDetails>> vehiclesList) {
		this.vehiclesList = vehiclesList;
	}

	/**
	 * @return the framePowerList
	 */
	public Map<String, List<TurbineDetails>> getFramePowerList() {
		return framePowerList;
	}

	/**
	 * @param framePowerList the framePowerList to set
	 */
	public void setFramePowerList(Map<String, List<TurbineDetails>> framePowerList) {
		this.framePowerList = framePowerList;
	}

	/**
	 * @return the enCLoadingDomestic
	 */
	public Map<String, List<TurbineDetails>> getEnCLoadingDomestic() {
		return EnCLoadingDomestic;
	}

	/**
	 * @param enCLoadingDomestic the enCLoadingDomestic to set
	 */
	public void setEnCLoadingDomestic(Map<String, List<TurbineDetails>> enCLoadingDomestic) {
		EnCLoadingDomestic = enCLoadingDomestic;
	}

	/**
	 * @return the enCLodging
	 */
	public Map<String, List<TurbineDetails>> getEnCLodging() {
		return EnCLodging;
	}

	/**
	 * @param enCLodging the enCLodging to set
	 */
	public void setEnCLodging(Map<String, List<TurbineDetails>> enCLodging) {
		EnCLodging = enCLodging;
	}

	/**
	 * @return the statusList
	 */
	public Map<String, List<TurbineDetails>> getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(Map<String, List<TurbineDetails>> statusList) {
		this.statusList = statusList;
	}

	/**
	 * @return the packageList
	 */
	public Map<String, List<TurbineDetails>> getPackageList() {
		return packageList;
	}

	/**
	 * @param packageList the packageList to set
	 */
	public void setPackageList(Map<String, List<TurbineDetails>> packageList) {
		this.packageList = packageList;
	}

	/**
	 * @return the uSDollerList
	 */
	public Map<String, List<TurbineDetails>> getUSDollerList() {
		return USDollerList;
	}

	/**
	 * @param uSDollerList the uSDollerList to set
	 */
	public void setUSDollerList(Map<String, List<TurbineDetails>> uSDollerList) {
		USDollerList = uSDollerList;
	}

	/**
	 * @return the enCustTypeList
	 */
	public Map<String, List<TurbineDetails>> getEnCustTypeList() {
		return EnCustTypeList;
	}

	/**
	 * @param enCustTypeList the enCustTypeList to set
	 */
	public void setEnCustTypeList(Map<String, List<TurbineDetails>> enCustTypeList) {
		EnCustTypeList = enCustTypeList;
	}

	/**
	 * @return the newlyAdded_vehiclesList
	 */
	public Map<String, List<TurbineDetails>> getNewlyAdded_vehiclesList() {
		return NewlyAdded_vehiclesList;
	}

	/**
	 * @param newlyAdded_vehiclesList the newlyAdded_vehiclesList to set
	 */
	public void setNewlyAdded_vehiclesList(Map<String, List<TurbineDetails>> newlyAdded_vehiclesList) {
		NewlyAdded_vehiclesList = newlyAdded_vehiclesList;
	}

	/**
	 * @return the bleedTypeList
	 */
	public Map<String, List<TurbineDetails>> getBleedTypeList() {
		return bleedTypeList;
	}

	/**
	 * @param bleedTypeList the bleedTypeList to set
	 */
	public void setBleedTypeList(Map<String, List<TurbineDetails>> bleedTypeList) {
		this.bleedTypeList = bleedTypeList;
	}

	/**
	 * @return the exportTransportDetails
	 */
	public Map<String, List<TransportationDetailsBean>> getExportTransportDetails() {
		return exportTransportDetails;
	}

	/**
	 * @param exportTransportDetails the exportTransportDetails to set
	 */
	public void setExportTransportDetails(Map<String, List<TransportationDetailsBean>> exportTransportDetails) {
		this.exportTransportDetails = exportTransportDetails;
	}

	/**
	 * @return the newframeWithPowerForExportTrans
	 */
	public Map<String, List<TurbineDetails>> getNewframeWithPowerForExportTrans() {
		return newframeWithPowerForExportTrans;
	}

	/**
	 * @param newframeWithPowerForExportTrans the newframeWithPowerForExportTrans to set
	 */
	public void setNewframeWithPowerForExportTrans(Map<String, List<TurbineDetails>> newframeWithPowerForExportTrans) {
		this.newframeWithPowerForExportTrans = newframeWithPowerForExportTrans;
	}

	/**
	 * @return the categoryList
	 */
	public Map<String, List<F2FUBOBean>> getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList the categoryList to set
	 */
	public void setCategoryList(Map<String, List<F2FUBOBean>> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * @return the materialList
	 */
	public Map<String, List<F2FUBOBean>> getMaterialList() {
		return materialList;
	}

	/**
	 * @param materialList the materialList to set
	 */
	public void setMaterialList(Map<String, List<F2FUBOBean>> materialList) {
		this.materialList = materialList;
	}

	/**
	 * @return the currencyList
	 */
	public Map<String, List<CurrencyBean>> getCurrencyList() {
		return currencyList;
	}

	/**
	 * @param currencyList the currencyList to set
	 */
	public void setCurrencyList(Map<String, List<CurrencyBean>> currencyList) {
		this.currencyList = currencyList;
	}

	/**
	 * @return the dboMechanicalList
	 */
	public Map<String, List<DBOBean>> getDboMechanicalList() {
		return dboMechanicalList;
	}

	/**
	 * @param dboMechanicalList the dboMechanicalList to set
	 */
	public void setDboMechanicalList(Map<String, List<DBOBean>> dboMechanicalList) {
		this.dboMechanicalList = dboMechanicalList;
	}

	/**
	 * @return the dboElectricalList
	 */
	public Map<String, List<DBOBean>> getDboElectricalList() {
		return dboElectricalList;
	}

	/**
	 * @param dboElectricalList the dboElectricalList to set
	 */
	public void setDboElectricalList(Map<String, List<DBOBean>> dboElectricalList) {
		this.dboElectricalList = dboElectricalList;
	}

	/**
	 * @return the portDetails
	 */
	public Map<String, List<TransportationDetailsBean>> getPortDetails() {
		return portDetails;
	}

	/**
	 * @param portDetails the portDetails to set
	 */
	public void setPortDetails(Map<String, List<TransportationDetailsBean>> portDetails) {
		this.portDetails = portDetails;
	}

	/**
	 * @return the trnstypeList
	 */
	public Map<String, List<SelectBox>> getTrnstypeList() {
		return trnstypeList;
	}

	/**
	 * @param trnstypeList the trnstypeList to set
	 */
	public void setTrnstypeList(Map<String, List<SelectBox>> trnstypeList) {
		this.trnstypeList = trnstypeList;
	}

	/**
	 * @return the transType
	 */
	public Map<String, List<TransportationDetailsBean>> getTransType() {
		return transType;
	}

	/**
	 * @param transType the transType to set
	 */
	public void setTransType(Map<String, List<TransportationDetailsBean>> transType) {
		this.transType = transType;
	}

	/**
	 * @return the typeOfOfferList
	 */
	public Map<String, List<TurbineDetails>> getTypeOfOfferList() {
		return typeOfOfferList;
	}

	/**
	 * @param typeOfOfferList the typeOfOfferList to set
	 */
	public void setTypeOfOfferList(Map<String, List<TurbineDetails>> typeOfOfferList) {
		this.typeOfOfferList = typeOfOfferList;
	}

	/**
	 * @return the typeOfQuotationList
	 */
	public Map<String, List<TurbineDetails>> getTypeOfQuotationList() {
		return typeOfQuotationList;
	}

	/**
	 * @param typeOfQuotationList the typeOfQuotationList to set
	 */
	public void setTypeOfQuotationList(Map<String, List<TurbineDetails>> typeOfQuotationList) {
		this.typeOfQuotationList = typeOfQuotationList;
	}

	/**
	 * @return the typeOfInjectionList
	 */
	public Map<String, List<TurbineDetails>> getTypeOfInjectionList() {
		return typeOfInjectionList;
	}

	/**
	 * @param typeOfInjectionList the typeOfInjectionList to set
	 */
	public void setTypeOfInjectionList(Map<String, List<TurbineDetails>> typeOfInjectionList) {
		this.typeOfInjectionList = typeOfInjectionList;
	}

	/**
	 * @return the typeOfVarientList
	 */
	public Map<String, List<TurbineDetails>> getTypeOfVarientList() {
		return typeOfVarientList;
	}

	/**
	 * @param typeOfVarientList the typeOfVarientList to set
	 */
	public void setTypeOfVarientList(Map<String, List<TurbineDetails>> typeOfVarientList) {
		this.typeOfVarientList = typeOfVarientList;
	}

	/**
	 * @return the glandSysTypeList
	 */
	public Map<String, List<DBOBean>> getGlandSysTypeList() {
		return glandSysTypeList;
	}

	/**
	 * @param glandSysTypeList the glandSysTypeList to set
	 */
	public void setGlandSysTypeList(Map<String, List<DBOBean>> glandSysTypeList) {
		this.glandSysTypeList = glandSysTypeList;
	}

	/**
	 * @return the lubOilTypeList
	 */
	public Map<String, List<DBOBean>> getLubOilTypeList() {
		return lubOilTypeList;
	}

	/**
	 * @param lubOilTypeList the lubOilTypeList to set
	 */
	public void setLubOilTypeList(Map<String, List<DBOBean>> lubOilTypeList) {
		this.lubOilTypeList = lubOilTypeList;
	}

	/**
	 * @return the lubOilSubTypeList
	 */
	public Map<String, List<DBOBean>> getLubOilSubTypeList() {
		return lubOilSubTypeList;
	}

	/**
	 * @param lubOilSubTypeList the lubOilSubTypeList to set
	 */
	public void setLubOilSubTypeList(Map<String, List<DBOBean>> lubOilSubTypeList) {
		this.lubOilSubTypeList = lubOilSubTypeList;
	}

	/**
	 * @return the f2fCacheList
	 */
	public Map<String, List<DBOBean>> getF2fCacheList() {
		return f2fCacheList;
	}

	/**
	 * @param f2fCacheList the f2fCacheList to set
	 */
	public void setF2fCacheList(Map<String, List<DBOBean>> f2fCacheList) {
		this.f2fCacheList = f2fCacheList;
	}

	/**
	 * @return the typeOfCustomerList
	 */
	public Map<String, List<TurbineDetails>> getTypeOfCustomerList() {
		return typeOfCustomerList;
	}

	/**
	 * @param typeOfCustomerList the typeOfCustomerList to set
	 */
	public void setTypeOfCustomerList(Map<String, List<TurbineDetails>> typeOfCustomerList) {
		this.typeOfCustomerList = typeOfCustomerList;
	}

	
}
