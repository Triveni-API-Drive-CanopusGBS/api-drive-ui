package com.ttl.ito.business.beans;

public class OtherCostsBean {

	private float travelExpenses;
	private float turbineContigency;
	private float contigencyGeneral;
	private float dboContigency;
	private float othersContigency;
	private float salesExpenses;
	private float engineCharges;
	private float intrestCharges;
	private float intrestNoOfMonths;
	private float others;
	private float totalVariableCost;
	private float dboCiCost;
	public float getDboCiCost() {
		return dboCiCost;
	}

	public void setDboCiCost(float dboCiCost) {
		this.dboCiCost = dboCiCost;
	}

	private float sparesCost;
	private float totalSparesCost;
	private float OrderBookingSpares;
	private float sparesNetProfit;
	private float sparesProfit;
	private float supplyCost;
	private float serviceCost;
	private float transCost;
	private float totalProjectCost;
	private float supply;
	private float transportation;
	private float services;
	private float totOrderCost;
	private float totOrderCostNetProfit;
	private float projectProfit;
	private float turbineSparesCost;
	private float turbineOrderBookCost;
	private float inpTurbineOrderBookCost;
	private float orderBookingNetProfit;
	private float profit;
	private float inpSparesProfit;
	private float percentProfit;
    private float inProfitPer;
	private float ldPerformance;
	private float ldforLateDelivery;
	private float inpLdPerformance;
	private float inpLdforLateDelivery;
	private float bankingGurantee;
	private float bankingCharges1;
	private float bankingCharges2;
	private float ovrheadMtrlCost;
	private float ovrheadSaleCost;
	private float ovrheadTurbCost;
	private float ovrheadSparesCost;
	private float ovrheadTotSaleCost;
	private float varNewCost;
	private float varProfit;
	private float sparesNewCost;
	private float projectNewCost;
	private float projectTotalCost;
	private float warrantyCost;
	private float warrantyPeriod;
	private float inOrderBookPrice;
	private float insurance;
	private float agencyCommCharges;
	private float intrestPercentage;
	private float inpBG1;
	private float inpBG2;
	private float inpAgencyCommission;
	private float projSupply;
	private float projServices;
	private float projTransport;
	private int inTravelExpensesReq;
	private int inNoOfVisit;
	private int  inCostForEachVisit;
	private float splProvision;
	private int otherItemId;
	private int modifiedBy;
	private int createdBy;
	private int quotId;

	private String createdDate;
	private String modifiedDate;
	private String effFrom;
	private String effTo;
	private String varRemarks;
	private String sparesRemarks;
	private String projectRemarks;
	private String othersRemarks;
	
	private boolean activeStatus;
	private boolean varNewFlag;
	private boolean sparesNewFlag;
	private boolean projectNewFlag;
	private float overRawMaterialCost;
	private float totalFtfCost;
	private float addOnCost;
	private float packageCost;
	private float dboMechCost;
	private float dboEleCost;
	private float toolsTackCost;
	public float getToolsTackCost() {
		return toolsTackCost;
	}

	public void setToolsTackCost(float toolsTackCost) {
		this.toolsTackCost = toolsTackCost;
	}

	/**
	 * @return the travelExpenses
	 */
	public float getTravelExpenses() {
		return travelExpenses;
	}

	/**
	 * @param travelExpenses the travelExpenses to set
	 */
	public void setTravelExpenses(float travelExpenses) {
		this.travelExpenses = travelExpenses;
	}

	/**
	 * @return the salesExpenses
	 */
	public float getSalesExpenses() {
		return salesExpenses;
	}

	/**
	 * @param salesExpenses the salesExpenses to set
	 */
	public void setSalesExpenses(float salesExpenses) {
		this.salesExpenses = salesExpenses;
	}

	/**
	 * @return the engineCharges
	 */
	public float getEngineCharges() {
		return engineCharges;
	}

	/**
	 * @param engineCharges the engineCharges to set
	 */
	public void setEngineCharges(float engineCharges) {
		this.engineCharges = engineCharges;
	}
	
	/**
	 * @return the others
	 */
	public float getOthers() {
		return others;
	}

	/**
	 * @param others the others to set
	 */
	public void setOthers(float others) {
		this.others = others;
	}

	/**
	 * @return the totalVariableCost
	 */
	public float getTotalVariableCost() {
		return totalVariableCost;
	}

	/**
	 * @param totalVariableCost the totalVariableCost to set
	 */
	public void setTotalVariableCost(float totalVariableCost) {
		this.totalVariableCost = totalVariableCost;
	}

	/**
	 * @return the sparesCost
	 */
	public float getSparesCost() {
		return sparesCost;
	}

	/**
	 * @param sparesCost the sparesCost to set
	 */
	public void setSparesCost(float sparesCost) {
		this.sparesCost = sparesCost;
	}

	/**
	 * @return the totalSparesCost
	 */
	public float getTotalSparesCost() {
		return totalSparesCost;
	}

	/**
	 * @param totalSparesCost the totalSparesCost to set
	 */
	public void setTotalSparesCost(float totalSparesCost) {
		this.totalSparesCost = totalSparesCost;
	}

	/**
	 * @return the orderBookingSpares
	 */
	public float getOrderBookingSpares() {
		return OrderBookingSpares;
	}

	/**
	 * @param orderBookingSpares the orderBookingSpares to set
	 */
	public void setOrderBookingSpares(float orderBookingSpares) {
		OrderBookingSpares = orderBookingSpares;
	}

	/**
	 * @return the sparesNetProfit
	 */
	public float getSparesNetProfit() {
		return sparesNetProfit;
	}

	/**
	 * @param sparesNetProfit the sparesNetProfit to set
	 */
	public void setSparesNetProfit(float sparesNetProfit) {
		this.sparesNetProfit = sparesNetProfit;
	}

	/**
	 * @return the sparesProfit
	 */
	public float getSparesProfit() {
		return sparesProfit;
	}

	/**
	 * @param sparesProfit the sparesProfit to set
	 */
	public void setSparesProfit(float sparesProfit) {
		this.sparesProfit = sparesProfit;
	}

	/**
	 * @return the supplyCost
	 */
	public float getSupplyCost() {
		return supplyCost;
	}

	/**
	 * @param supplyCost the supplyCost to set
	 */
	public void setSupplyCost(float supplyCost) {
		this.supplyCost = supplyCost;
	}

	/**
	 * @return the serviceCost
	 */
	public float getServiceCost() {
		return serviceCost;
	}

	/**
	 * @param serviceCost the serviceCost to set
	 */
	public void setServiceCost(float serviceCost) {
		this.serviceCost = serviceCost;
	}

	/**
	 * @return the transCost
	 */
	public float getTransCost() {
		return transCost;
	}

	/**
	 * @param transCost the transCost to set
	 */
	public void setTransCost(float transCost) {
		this.transCost = transCost;
	}

	/**
	 * @return the totalProjectCost
	 */
	public float getTotalProjectCost() {
		return totalProjectCost;
	}

	/**
	 * @param totalProjectCost the totalProjectCost to set
	 */
	public void setTotalProjectCost(float totalProjectCost) {
		this.totalProjectCost = totalProjectCost;
	}

	/**
	 * @return the supply
	 */
	public float getSupply() {
		return supply;
	}

	/**
	 * @param supply the supply to set
	 */
	public void setSupply(float supply) {
		this.supply = supply;
	}

	/**
	 * @return the transportation
	 */
	public float getTransportation() {
		return transportation;
	}

	/**
	 * @param transportation the transportation to set
	 */
	public void setTransportation(float transportation) {
		this.transportation = transportation;
	}

	/**
	 * @return the services
	 */
	public float getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(float services) {
		this.services = services;
	}

	/**
	 * @return the totOrderCost
	 */
	public float getTotOrderCost() {
		return totOrderCost;
	}

	/**
	 * @param totOrderCost the totOrderCost to set
	 */
	public void setTotOrderCost(float totOrderCost) {
		this.totOrderCost = totOrderCost;
	}

	/**
	 * @return the totOrderCostNetProfit
	 */
	public float getTotOrderCostNetProfit() {
		return totOrderCostNetProfit;
	}

	/**
	 * @param totOrderCostNetProfit the totOrderCostNetProfit to set
	 */
	public void setTotOrderCostNetProfit(float totOrderCostNetProfit) {
		this.totOrderCostNetProfit = totOrderCostNetProfit;
	}

	/**
	 * @return the projectProfit
	 */
	public float getProjectProfit() {
		return projectProfit;
	}

	/**
	 * @param projectProfit the projectProfit to set
	 */
	public void setProjectProfit(float projectProfit) {
		this.projectProfit = projectProfit;
	}

	/**
	 * @return the turbineSparesCost
	 */
	public float getTurbineSparesCost() {
		return turbineSparesCost;
	}

	/**
	 * @param turbineSparesCost the turbineSparesCost to set
	 */
	public void setTurbineSparesCost(float turbineSparesCost) {
		this.turbineSparesCost = turbineSparesCost;
	}


	/**
	 * @return the orderBookingNetProfit
	 */
	public float getOrderBookingNetProfit() {
		return orderBookingNetProfit;
	}

	/**
	 * @param orderBookingNetProfit the orderBookingNetProfit to set
	 */
	public void setOrderBookingNetProfit(float orderBookingNetProfit) {
		this.orderBookingNetProfit = orderBookingNetProfit;
	}

	/**
	 * @return the profit
	 */
	public float getProfit() {
		return profit;
	}

	/**
	 * @param profit the profit to set
	 */
	public void setProfit(float profit) {
		this.profit = profit;
	}
	
	/**
	 * @return the turbineContigency
	 */
	public float getTurbineContigency() {
		return turbineContigency;
	}

	/**
	 * @param turbineContigency the turbineContigency to set
	 */
	public void setTurbineContigency(float turbineContigency) {
		this.turbineContigency = turbineContigency;
	}

	/**
	 * @return the dboContigency
	 */
	public float getDboContigency() {
		return dboContigency;
	}

	/**
	 * @param dboContigency the dboContigency to set
	 */
	public void setDboContigency(float dboContigency) {
		this.dboContigency = dboContigency;
	}

	/**
	 * @return the othersContigency
	 */
	public float getOthersContigency() {
		return othersContigency;
	}

	/**
	 * @param othersContigency the othersContigency to set
	 */
	public void setOthersContigency(float othersContigency) {
		this.othersContigency = othersContigency;
	}

	/**
	 * @return the intrestCharges
	 */
	public float getIntrestCharges() {
		return intrestCharges;
	}

	/**
	 * @param intrestCharges the intrestCharges to set
	 */
	public void setIntrestCharges(float intrestCharges) {
		this.intrestCharges = intrestCharges;
	}

	/**
	 * @return the turbineOrderBookCost
	 */
	public float getTurbineOrderBookCost() {
		return turbineOrderBookCost;
	}

	/**
	 * @param turbineOrderBookCost the turbineOrderBookCost to set
	 */
	public void setTurbineOrderBookCost(float turbineOrderBookCost) {
		this.turbineOrderBookCost = turbineOrderBookCost;
	}

	/**
	 * @return the otherItemId
	 */
	public int getOtherItemId() {
		return otherItemId;
	}

	/**
	 * @param otherItemId the otherItemId to set
	 */
	public void setOtherItemId(int otherItemId) {
		this.otherItemId = otherItemId;
	}

	/**
	 * @return the modifiedBy
	 */
	public int getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the effFrom
	 */
	public String getEffFrom() {
		return effFrom;
	}

	/**
	 * @param effFrom the effFrom to set
	 */
	public void setEffFrom(String effFrom) {
		this.effFrom = effFrom;
	}

	/**
	 * @return the effTo
	 */
	public String getEffTo() {
		return effTo;
	}

	/**
	 * @param effTo the effTo to set
	 */
	public void setEffTo(String effTo) {
		this.effTo = effTo;
	}

	/**
	 * @return the activeStatus
	 */
	public boolean isActiveStatus() {
		return activeStatus;
	}

	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	/**
	 * @return the percentProfit
	 */
	public float getPercentProfit() {
		return percentProfit;
	}

	/**
	 * @param percentProfit the percentProfit to set
	 */
	public void setPercentProfit(float percentProfit) {
		this.percentProfit = percentProfit;
	}

	/**
	 * @return the ldPerformance
	 */
	public float getLdPerformance() {
		return ldPerformance;
	}

	/**
	 * @param i the ldPerformance to set
	 */
	public void setLdPerformance(float ldPerformance) {
		this.ldPerformance = ldPerformance;
	}

	/**
	 * @return the bankingGurantee
	 */
	public float getBankingGurantee() {
		return bankingGurantee;
	}

	/**
	 * @param bankingGurantee the bankingGurantee to set
	 */
	public void setBankingGurantee(float bankingGurantee) {
		this.bankingGurantee = bankingGurantee;
	}

	/**
	 * @return the ovrheadMtrlCost
	 */
	public float getOvrheadMtrlCost() {
		return ovrheadMtrlCost;
	}

	/**
	 * @param ovrheadMtrlCost the ovrheadMtrlCost to set
	 */
	public void setOvrheadMtrlCost(float ovrheadMtrlCost) {
		this.ovrheadMtrlCost = ovrheadMtrlCost;
	}

	/**
	 * @return the ovrheadSaleCost
	 */
	public float getOvrheadSaleCost() {
		return ovrheadSaleCost;
	}

	/**
	 * @param ovrheadSaleCost the ovrheadSaleCost to set
	 */
	public void setOvrheadSaleCost(float ovrheadSaleCost) {
		this.ovrheadSaleCost = ovrheadSaleCost;
	}

	/**
	 * @return the ovrheadTurbCost
	 */
	public float getOvrheadTurbCost() {
		return ovrheadTurbCost;
	}

	/**
	 * @param ovrheadTurbCost the ovrheadTurbCost to set
	 */
	public void setOvrheadTurbCost(float ovrheadTurbCost) {
		this.ovrheadTurbCost = ovrheadTurbCost;
	}

	/**
	 * @return the ovrheadTotSaleCost
	 */
	public float getOvrheadTotSaleCost() {
		return ovrheadTotSaleCost;
	}

	/**
	 * @param ovrheadTotSaleCost the ovrheadTotSaleCost to set
	 */
	public void setOvrheadTotSaleCost(float ovrheadTotSaleCost) {
		this.ovrheadTotSaleCost = ovrheadTotSaleCost;
	}

	/**
	 * @return the ldforLateDelivery
	 */
	public float getLdforLateDelivery() {
		return ldforLateDelivery;
	}

	/**
	 * @param ldforLateDelivery the ldforLateDelivery to set
	 */
	public void setLdforLateDelivery(float ldforLateDelivery) {
		this.ldforLateDelivery = ldforLateDelivery;
	}

	/**
	 * @return the contigencyGeneral
	 */
	public float getContigencyGeneral() {
		return contigencyGeneral;
	}

	/**
	 * @param i the contigencyGeneral to set
	 */
	public void setContigencyGeneral(float contigencyGeneral) {
		this.contigencyGeneral = contigencyGeneral;
	}

	/**
	 * @return the varNewCost
	 */
	public float getVarNewCost() {
		return varNewCost;
	}

	/**
	 * @param varNewCost the varNewCost to set
	 */
	public void setVarNewCost(float varNewCost) {
		this.varNewCost = varNewCost;
	}

	/**
	 * @return the sparesNewCost
	 */
	public float getSparesNewCost() {
		return sparesNewCost;
	}

	/**
	 * @param sparesNewCost the sparesNewCost to set
	 */
	public void setSparesNewCost(float sparesNewCost) {
		this.sparesNewCost = sparesNewCost;
	}

	/**
	 * @return the projectNewCost
	 */
	public float getProjectNewCost() {
		return projectNewCost;
	}

	/**
	 * @param projectNewCost the projectNewCost to set
	 */
	public void setProjectNewCost(float projectNewCost) {
		this.projectNewCost = projectNewCost;
	}

	/**
	 * @return the varRemarks
	 */
	public String getVarRemarks() {
		return varRemarks;
	}

	/**
	 * @param varRemarks the varRemarks to set
	 */
	public void setVarRemarks(String varRemarks) {
		this.varRemarks = varRemarks;
	}

	/**
	 * @return the sparesRemarks
	 */
	public String getSparesRemarks() {
		return sparesRemarks;
	}

	/**
	 * @param sparesRemarks the sparesRemarks to set
	 */
	public void setSparesRemarks(String sparesRemarks) {
		this.sparesRemarks = sparesRemarks;
	}

	/**
	 * @return the projectRemarks
	 */
	public String getProjectRemarks() {
		return projectRemarks;
	}

	/**
	 * @param projectRemarks the projectRemarks to set
	 */
	public void setProjectRemarks(String projectRemarks) {
		this.projectRemarks = projectRemarks;
	}

	/**
	 * @return the varNewFlag
	 */
	public boolean isVarNewFlag() {
		return varNewFlag;
	}

	/**
	 * @param varNewFlag the varNewFlag to set
	 */
	public void setVarNewFlag(boolean varNewFlag) {
		this.varNewFlag = varNewFlag;
	}

	/**
	 * @return the sparesNewFlag
	 */
	public boolean isSparesNewFlag() {
		return sparesNewFlag;
	}

	/**
	 * @param sparesNewFlag the sparesNewFlag to set
	 */
	public void setSparesNewFlag(boolean sparesNewFlag) {
		this.sparesNewFlag = sparesNewFlag;
	}

	/**
	 * @return the projectNewFlag
	 */
	public boolean isProjectNewFlag() {
		return projectNewFlag;
	}

	/**
	 * @param projectNewFlag the projectNewFlag to set
	 */
	public void setProjectNewFlag(boolean projectNewFlag) {
		this.projectNewFlag = projectNewFlag;
	}

	/**
	 * @return the projectTotalCost
	 */
	public float getProjectTotalCost() {
		return projectTotalCost;
	}

	/**
	 * @param projectTotalCost the projectTotalCost to set
	 */
	public void setProjectTotalCost(float projectTotalCost) {
		this.projectTotalCost = projectTotalCost;
	}

	/**
	 * @return the varProfit
	 */
	public float getVarProfit() {
		return varProfit;
	}

	/**
	 * @param varProfit the varProfit to set
	 */
	public void setVarProfit(float varProfit) {
		this.varProfit = varProfit;
	}

	/**
	 * @return the ovrheadSparesCost
	 */
	public float getOvrheadSparesCost() {
		return ovrheadSparesCost;
	}

	/**
	 * @param ovrheadSparesCost the ovrheadSparesCost to set
	 */
	public void setOvrheadSparesCost(float ovrheadSparesCost) {
		this.ovrheadSparesCost = ovrheadSparesCost;
	}

	/**
	 * @return the warrantyCost
	 */
	public float getWarrantyCost() {
		return warrantyCost;
	}

	/**
	 * @param warrantyCost the warrantyCost to set
	 */
	public void setWarrantyCost(float warrantyCost) {
		this.warrantyCost = warrantyCost;
	}

	/**
	 * @return the warrantyPeriod
	 */
	public float getWarrantyPeriod() {
		return warrantyPeriod;
	}

	/**
	 * @param warrantyPeriod the warrantyPeriod to set
	 */
	public void setWarrantyPeriod(float warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	/**
	 * @return the intrestNoOfMonths
	 */
	public float getIntrestNoOfMonths() {
		return intrestNoOfMonths;
	}

	/**
	 * @param intrestNoOfMonths the intrestNoOfMonths to set
	 */
	public void setIntrestNoOfMonths(float intrestNoOfMonths) {
		this.intrestNoOfMonths = intrestNoOfMonths;
	}

	/**
	 * @return the inpLdPerformance
	 */
	public float getInpLdPerformance() {
		return inpLdPerformance;
	}

	/**
	 * @param inpLdPerformance the inpLdPerformance to set
	 */
	public void setInpLdPerformance(float inpLdPerformance) {
		this.inpLdPerformance = inpLdPerformance;
	}

	/**
	 * @return the inpLdforLateDelivery
	 */
	public float getInpLdforLateDelivery() {
		return inpLdforLateDelivery;
	}

	/**
	 * @param inpLdforLateDelivery the inpLdforLateDelivery to set
	 */
	public void setInpLdforLateDelivery(float inpLdforLateDelivery) {
		this.inpLdforLateDelivery = inpLdforLateDelivery;
	}

	/**
	 * @return the inProfitPer
	 */
	public float getInProfitPer() {
		return inProfitPer;
	}

	/**
	 * @param inProfitPer the inProfitPer to set
	 */
	public void setInProfitPer(float inProfitPer) {
		this.inProfitPer = inProfitPer;
	}

	/**
	 * @return the inOrderBookPrice
	 */
	public float getInOrderBookPrice() {
		return inOrderBookPrice;
	}

	/**
	 * @param inOrderBookPrice the inOrderBookPrice to set
	 */
	public void setInOrderBookPrice(float inOrderBookPrice) {
		this.inOrderBookPrice = inOrderBookPrice;
	}

	/**
	 * @return the inpSparesProfit
	 */
	public float getInpSparesProfit() {
		return inpSparesProfit;
	}

	/**
	 * @param inpSparesProfit the inpSparesProfit to set
	 */
	public void setInpSparesProfit(float inpSparesProfit) {
		this.inpSparesProfit = inpSparesProfit;
	}

	/**
	 * @return the inpTurbineOrderBookCost
	 */
	public float getInpTurbineOrderBookCost() {
		return inpTurbineOrderBookCost;
	}

	/**
	 * @param inpTurbineOrderBookCost the inpTurbineOrderBookCost to set
	 */
	public void setInpTurbineOrderBookCost(float inpTurbineOrderBookCost) {
		this.inpTurbineOrderBookCost = inpTurbineOrderBookCost;
	}

	/**
	 * @return the inpBG1
	 */
	public float getInpBG1() {
		return inpBG1;
	}

	/**
	 * @param inpBG1 the inpBG1 to set
	 */
	public void setInpBG1(float inpBG1) {
		this.inpBG1 = inpBG1;
	}

	/**
	 * @return the inpBG2
	 */
	public float getInpBG2() {
		return inpBG2;
	}

	/**
	 * @param inpBG2 the inpBG2 to set
	 */
	public void setInpBG2(float inpBG2) {
		this.inpBG2 = inpBG2;
	}

	/**
	 * @return the inpAgencyCommission
	 */
	public float getInpAgencyCommission() {
		return inpAgencyCommission;
	}

	/**
	 * @param inpAgencyCommission the inpAgencyCommission to set
	 */
	public void setInpAgencyCommission(float inpAgencyCommission) {
		this.inpAgencyCommission = inpAgencyCommission;
	}

	/**
	 * @return the othersRemarks
	 */
	public String getOthersRemarks() {
		return othersRemarks;
	}

	/**
	 * @param othersRemarks the othersRemarks to set
	 */
	public void setOthersRemarks(String othersRemarks) {
		this.othersRemarks = othersRemarks;
	}

	/**
	 * @return the insurance
	 */
	public float getInsurance() {
		return insurance;
	}

	/**
	 * @param insurance the insurance to set
	 */
	public void setInsurance(float insurance) {
		this.insurance = insurance;
	}

	/**
	 * @return the intrestPercentage
	 */
	public float getIntrestPercentage() {
		return intrestPercentage;
	}

	/**
	 * @param intrestPercentage the intrestPercentage to set
	 */
	public void setIntrestPercentage(float intrestPercentage) {
		this.intrestPercentage = intrestPercentage;
	}

	/**
	 * @return the agencyCommCharges
	 */
	public float getAgencyCommCharges() {
		return agencyCommCharges;
	}

	/**
	 * @param agencyCommCharges the agencyCommCharges to set
	 */
	public void setAgencyCommCharges(float agencyCommCharges) {
		this.agencyCommCharges = agencyCommCharges;
	}

	/**
	 * @return the bankingCharges1
	 */
	public float getBankingCharges1() {
		return bankingCharges1;
	}

	/**
	 * @param bankingCharges1 the bankingCharges1 to set
	 */
	public void setBankingCharges1(float bankingCharges1) {
		this.bankingCharges1 = bankingCharges1;
	}

	/**
	 * @return the bankingCharges2
	 */
	public float getBankingCharges2() {
		return bankingCharges2;
	}

	/**
	 * @param bankingCharges2 the bankingCharges2 to set
	 */
	public void setBankingCharges2(float bankingCharges2) {
		this.bankingCharges2 = bankingCharges2;
	}

	/**
	 * @return the projSupply
	 */
	public float getProjSupply() {
		return projSupply;
	}

	/**
	 * @param projSupply the projSupply to set
	 */
	public void setProjSupply(float projSupply) {
		this.projSupply = projSupply;
	}

	/**
	 * @return the projServices
	 */
	public float getProjServices() {
		return projServices;
	}

	/**
	 * @param projServices the projServices to set
	 */
	public void setProjServices(float projServices) {
		this.projServices = projServices;
	}

	/**
	 * @return the projTransport
	 */
	public float getProjTransport() {
		return projTransport;
	}

	/**
	 * @param projTransport the projTransport to set
	 */
	public void setProjTransport(float projTransport) {
		this.projTransport = projTransport;
	}

	public void setOverRawMaterialCost(float overRawMaterialCost) {
		this.overRawMaterialCost = overRawMaterialCost;
	}
	
	public float getOverRawMaterialCost() {
		return overRawMaterialCost;
	}

	public void setTotalFtfCost(float totalFtfCost ) {
		this.totalFtfCost = totalFtfCost;
	}

	public float getTotalFtfCost() {
		return totalFtfCost;
	}

	public void setAddOnCost(float addOnCost ) {
		this.addOnCost = addOnCost;
	}

	public float getAddOnCost() {
		return addOnCost;
	}

	public void setPackageCost(float packageCost ) {
		this.packageCost = packageCost;
	}

	public float getPackageCost() {
		return packageCost;
	}
	public void setDboEleCost(float dboEleCost ) {
		this.dboEleCost = dboEleCost;
	}

	public float getDboEleCost() {
		return dboEleCost;
	}

	public void setDboMechCost(float dboMechCost ) {
		this.dboMechCost = dboMechCost;
	}

	public float getDboMechCost() {
		return dboMechCost;
	}

	/**
	 * @return the inTravelExpensesReq
	 */
	public int getInTravelExpensesReq() {
		return inTravelExpensesReq;
	}

	/**
	 * @param inTravelExpensesReq the inTravelExpensesReq to set
	 */
	public void setInTravelExpensesReq(int inTravelExpensesReq) {
		this.inTravelExpensesReq = inTravelExpensesReq;
	}

	/**
	 * @return the inNoOfVisit
	 */
	public int getInNoOfVisit() {
		return inNoOfVisit;
	}

	/**
	 * @param inNoOfVisit the inNoOfVisit to set
	 */
	public void setInNoOfVisit(int inNoOfVisit) {
		this.inNoOfVisit = inNoOfVisit;
	}

	/**
	 * @return the inCostForEachVisit
	 */
	public int getInCostForEachVisit() {
		return inCostForEachVisit;
	}

	/**
	 * @param inCostForEachVisit the inCostForEachVisit to set
	 */
	public void setInCostForEachVisit(int inCostForEachVisit) {
		this.inCostForEachVisit = inCostForEachVisit;
	}

	/**
	 * @return the splProvision
	 */
	public float getSplProvision() {
		return splProvision;
	}

	/**
	 * @param splProvision the splProvision to set
	 */
	public void setSplProvision(float splProvision) {
		this.splProvision = splProvision;
	}

	/**
	 * @return the quotId
	 */
	public int getQuotId() {
		return quotId;
	}

	/**
	 * @param quotId the quotId to set
	 */
	public void setQuotId(int quotId) {
		this.quotId = quotId;
	}

	
	


}
