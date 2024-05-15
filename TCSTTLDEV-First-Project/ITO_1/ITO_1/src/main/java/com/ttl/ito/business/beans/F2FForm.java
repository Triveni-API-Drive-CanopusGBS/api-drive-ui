package com.ttl.ito.business.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class F2FForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private int compMastId;
	
	private String cNum;
	private String variantCode;
	private String custName;
	private String wbsElementNo;
	private String parentMaterialId;
	private String childMaterialId;
	private String parentMaterialCode;
	private String childMaterialCode;
	private String materialDesc;
	private String childQuantity;
	private String unitMeasure;
	private String levelNo;
	private String parentQuantity;
	private String prodOrder;
	private String purchaseOrder;
	private String misl;
	private String remarks;

	
	private float dboMechCost;
	private float dboElectricalCost;
	private float turbineInstrumentCost;
	private float turbineMaterialCost;
	private float rawMaterialCost;
	private float shopCoverCost;
	private float overheadCost;
	private float subContrCost;
	private float total;
	private float totalF2FCost;
	private float addonCost;
	private float ubo;
	private float overwrittenPrice;
	private float semiFinished;
	
	private boolean flag;
	private boolean dboMechFlag;
	private boolean dboEleFlag;
	private boolean varCostFlag;
	private boolean sparesFlag;
	private boolean projectCostFlag;
	private boolean disableFlg;
	private boolean isParentFlg;
	private boolean overwrittenPriceFlg;
	private boolean pkgNewCostFlag;
	private boolean ecNewCostFlag;
	private boolean transNewCostFlag;
	private boolean dboMechNewCostFlag;
	private boolean dboEleNewCostFlag;
	private boolean varNewCostFlag;
	private boolean sparesNewCostFlag;
	private boolean projectNewCostFlag;
	private boolean addOnNewCostFlag;
	private boolean f2fNewCostFlag;
	private boolean f2fCostFlag;
	
	List<AddOnComponent> addOnsList= new ArrayList<>();
	List<TurbineDetails> costSheetList = new ArrayList<>();
	List<TurbineDetails> dboMechList = new ArrayList<>();
	List<TurbineDetails> dboEleList = new ArrayList<>();
	
	OtherCostsBean otherCostsBean = new OtherCostsBean();
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the cNum
	 */
	public String getcNum() {
		return cNum;
	}

	/**
	 * @param cNum
	 *            the cNum to set
	 */
	public void setcNum(String cNum) {
		this.cNum = cNum;
	}

	/**
	 * @return the wbsElementNo
	 */
	public String getWbsElementNo() {
		return wbsElementNo;
	}

	/**
	 * @param wbsElementNo
	 *            the wbsElementNo to set
	 */
	public void setWbsElementNo(String wbsElementNo) {
		this.wbsElementNo = wbsElementNo;
	}

	/**
	 * @return the parentMaterialId
	 */
	public String getParentMaterialId() {
		return parentMaterialId;
	}

	/**
	 * @param parentMaterialId
	 *            the parentMaterialId to set
	 */
	public void setParentMaterialId(String parentMaterialId) {
		this.parentMaterialId = parentMaterialId;
	}

	/**
	 * @return the childMaterialId
	 */
	public String getChildMaterialId() {
		return childMaterialId;
	}

	/**
	 * @param childMaterialId
	 *            the childMaterialId to set
	 */
	public void setChildMaterialId(String childMaterialId) {
		this.childMaterialId = childMaterialId;
	}

	/**
	 * @return the parentMaterialCode
	 */
	public String getParentMaterialCode() {
		return parentMaterialCode;
	}

	/**
	 * @param parentMaterialCode
	 *            the parentMaterialCode to set
	 */
	public void setParentMaterialCode(String parentMaterialCode) {
		this.parentMaterialCode = parentMaterialCode;
	}

	/**
	 * @return the childMaterialCode
	 */
	public String getChildMaterialCode() {
		return childMaterialCode;
	}

	/**
	 * @param childMaterialCode
	 *            the childMaterialCode to set
	 */
	public void setChildMaterialCode(String childMaterialCode) {
		this.childMaterialCode = childMaterialCode;
	}

	/**
	 * @return the materialDesc
	 */
	public String getMaterialDesc() {
		return materialDesc;
	}

	/**
	 * @param materialDesc
	 *            the materialDesc to set
	 */
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	/**
	 * @return the unitMeasure
	 */
	public String getUnitMeasure() {
		return unitMeasure;
	}

	/**
	 * @param unitMeasure
	 *            the unitMeasure to set
	 */
	public void setUnitMeasure(String unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	/**
	 * @return the levelNo
	 */
	public String getLevelNo() {
		return levelNo;
	}

	/**
	 * @param levelNo
	 *            the levelNo to set
	 */
	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	/**
	 * @return the childQuantity
	 */
	public String getChildQuantity() {
		return childQuantity;
	}

	/**
	 * @param childQuantity
	 *            the childQuantity to set
	 */
	public void setChildQuantity(String childQuantity) {
		this.childQuantity = childQuantity;
	}

	/**
	 * @return the parentQuantity
	 */
	public String getParentQuantity() {
		return parentQuantity;
	}

	/**
	 * @param parentQuantity
	 *            the parentQuantity to set
	 */
	public void setParentQuantity(String parentQuantity) {
		this.parentQuantity = parentQuantity;
	}

	/**
	 * @return the prodOrder
	 */
	public String getProdOrder() {
		return prodOrder;
	}

	/**
	 * @param prodOrder
	 *            the prodOrder to set
	 */
	public void setProdOrder(String prodOrder) {
		this.prodOrder = prodOrder;
	}

	/**
	 * @return the purchaseOrder
	 */
	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	/**
	 * @param purchaseOrder
	 *            the purchaseOrder to set
	 */
	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	/**
	 * @return the misl
	 */
	public String getMisl() {
		return misl;
	}

	/**
	 * @param misl
	 *            the misl to set
	 */
	public void setMisl(String misl) {
		this.misl = misl;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the disableFlg
	 */
	public boolean isDisableFlg() {
		return disableFlg;
	}

	/**
	 * @param disableFlg the disableFlg to set
	 */
	public void setDisableFlg(boolean disableFlg) {
		this.disableFlg = disableFlg;
	}

	/**
	 * @return the isParentFlg
	 */
	public boolean isParentFlg() {
		return isParentFlg;
	}

	/**
	 * @param isParentFlg the isParentFlg to set
	 */
	public void setParentFlg(boolean isParentFlg) {
		this.isParentFlg = isParentFlg;
	}

	/**
	 * @return the addOnsList
	 */
	public List<AddOnComponent> getAddOnsList() {
		return addOnsList;
	}

	/**
	 * @param addOnsList the addOnsList to set
	 */
	public void setAddOnsList(List<AddOnComponent> addOnsList) {
		this.addOnsList = addOnsList;
	}

	/**
	 * @return the costSheetList
	 */
	public List<TurbineDetails> getCostSheetList() {
		return costSheetList;
	}

	/**
	 * @param costSheetList the costSheetList to set
	 */
	public void setCostSheetList(List<TurbineDetails> costSheetList) {
		this.costSheetList = costSheetList;
	}

	/**
	 * @return the dboMechCost
	 */
	public float getDboMechCost() {
		return dboMechCost;
	}

	/**
	 * @param dboMechCost the dboMechCost to set
	 */
	public void setDboMechCost(float dboMechCost) {
		this.dboMechCost = dboMechCost;
	}

	/**
	 * @return the dboElectricalCost
	 */
	public float getDboElectricalCost() {
		return dboElectricalCost;
	}

	/**
	 * @param dboElectricalCost the dboElectricalCost to set
	 */
	public void setDboElectricalCost(float dboElectricalCost) {
		this.dboElectricalCost = dboElectricalCost;
	}

	/**
	 * @return the dboMechList
	 */
	public List<TurbineDetails> getDboMechList() {
		return dboMechList;
	}

	/**
	 * @param dboMechList the dboMechList to set
	 */
	public void setDboMechList(List<TurbineDetails> dboMechList) {
		this.dboMechList = dboMechList;
	}

	/**
	 * @return the dboEleList
	 */
	public List<TurbineDetails> getDboEleList() {
		return dboEleList;
	}

	/**
	 * @param dboEleList the dboEleList to set
	 */
	public void setDboEleList(List<TurbineDetails> dboEleList) {
		this.dboEleList = dboEleList;
	}

	/**
	 * @return the turbineInstrumentCost
	 */
	public float getTurbineInstrumentCost() {
		return turbineInstrumentCost;
	}

	/**
	 * @param turbineInstrumentCost the turbineInstrumentCost to set
	 */
	public void setTurbineInstrumentCost(float turbineInstrumentCost) {
		this.turbineInstrumentCost = turbineInstrumentCost;
	}

	/**
	 * @return the turbineMaterialCost
	 */
	public float getTurbineMaterialCost() {
		return turbineMaterialCost;
	}

	/**
	 * @param turbineMaterialCost the turbineMaterialCost to set
	 */
	public void setTurbineMaterialCost(float turbineMaterialCost) {
		this.turbineMaterialCost = turbineMaterialCost;
	}

	/**
	 * @return the rawMaterialCost
	 */
	public float getRawMaterialCost() {
		return rawMaterialCost;
	}

	/**
	 * @param rawMaterialCost the rawMaterialCost to set
	 */
	public void setRawMaterialCost(float rawMaterialCost) {
		this.rawMaterialCost = rawMaterialCost;
	}

	/**
	 * @return the shopCoverCost
	 */
	public float getShopCoverCost() {
		return shopCoverCost;
	}

	/**
	 * @param shopCoverCost the shopCoverCost to set
	 */
	public void setShopCoverCost(float shopCoverCost) {
		this.shopCoverCost = shopCoverCost;
	}

	/**
	 * @return the overheadCost
	 */
	public float getOverheadCost() {
		return overheadCost;
	}

	/**
	 * @param overheadCost the overheadCost to set
	 */
	public void setOverheadCost(float overheadCost) {
		this.overheadCost = overheadCost;
	}

	/**
	 * @return the subContrCost
	 */
	public float getSubContrCost() {
		return subContrCost;
	}

	/**
	 * @param subContrCost the subContrCost to set
	 */
	public void setSubContrCost(float subContrCost) {
		this.subContrCost = subContrCost;
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * @return the totalF2FCost
	 */
	public float getTotalF2FCost() {
		return totalF2FCost;
	}

	/**
	 * @param totalF2FCost the totalF2FCost to set
	 */
	public void setTotalF2FCost(float totalF2FCost) {
		this.totalF2FCost = totalF2FCost;
	}

	/**
	 * @return the addonCost
	 */
	public float getAddonCost() {
		return addonCost;
	}

	/**
	 * @param addonCost the addonCost to set
	 */
	public void setAddonCost(float addonCost) {
		this.addonCost = addonCost;
	}

	/**
	 * @return the ubo
	 */
	public float getUbo() {
		return ubo;
	}

	/**
	 * @param ubo the ubo to set
	 */
	public void setUbo(float ubo) {
		this.ubo = ubo;
	}

	/**
	 * @return the semiFinished
	 */
	public float getSemiFinished() {
		return semiFinished;
	}

	/**
	 * @param semiFinished the semiFinished to set
	 */
	public void setSemiFinished(float semiFinished) {
		this.semiFinished = semiFinished;
	}

	/**
	 * @return the overwrittenPrice
	 */
	public float getOverwrittenPrice() {
		return overwrittenPrice;
	}

	/**
	 * @param overwrittenPrice the overwrittenPrice to set
	 */
	public void setOverwrittenPrice(float overwrittenPrice) {
		this.overwrittenPrice = overwrittenPrice;
	}

	/**
	 * @return the overwrittenPriceFlg
	 */
	public boolean isOverwrittenPriceFlg() {
		return overwrittenPriceFlg;
	}

	/**
	 * @param overwrittenPriceFlg the overwrittenPriceFlg to set
	 */
	public void setOverwrittenPriceFlg(boolean overwrittenPriceFlg) {
		this.overwrittenPriceFlg = overwrittenPriceFlg;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the compMastId
	 */
	public int getCompMastId() {
		return compMastId;
	}

	/**
	 * @param compMastId the compMastId to set
	 */
	public void setCompMastId(int compMastId) {
		this.compMastId = compMastId;
	}

	/**
	 * @return the dboMechFlag
	 */
	public boolean isDboMechFlag() {
		return dboMechFlag;
	}

	/**
	 * @param dboMechFlag the dboMechFlag to set
	 */
	public void setDboMechFlag(boolean dboMechFlag) {
		this.dboMechFlag = dboMechFlag;
	}

	/**
	 * @return the dboEleFlag
	 */
	public boolean isDboEleFlag() {
		return dboEleFlag;
	}

	/**
	 * @param dboEleFlag the dboEleFlag to set
	 */
	public void setDboEleFlag(boolean dboEleFlag) {
		this.dboEleFlag = dboEleFlag;
	}

	/**
	 * @return the otherCostsBean
	 */
	public OtherCostsBean getOtherCostsBean() {
		return otherCostsBean;
	}

	/**
	 * @param otherCostsBean the otherCostsBean to set
	 */
	public void setOtherCostsBean(OtherCostsBean otherCostsBean) {
		this.otherCostsBean = otherCostsBean;
	}

	/**
	 * @return the varCostFlag
	 */
	public boolean isVarCostFlag() {
		return varCostFlag;
	}

	/**
	 * @param varCostFlag the varCostFlag to set
	 */
	public void setVarCostFlag(boolean varCostFlag) {
		this.varCostFlag = varCostFlag;
	}

	/**
	 * @return the sparesFlag
	 */
	public boolean isSparesFlag() {
		return sparesFlag;
	}

	/**
	 * @param sparesFlag the sparesFlag to set
	 */
	public void setSparesFlag(boolean sparesFlag) {
		this.sparesFlag = sparesFlag;
	}

	/**
	 * @return the projectCostFlag
	 */
	public boolean isProjectCostFlag() {
		return projectCostFlag;
	}

	/**
	 * @param projectCostFlag the projectCostFlag to set
	 */
	public void setProjectCostFlag(boolean projectCostFlag) {
		this.projectCostFlag = projectCostFlag;
	}

	/**
	 * @return the pkgNewCostFlag
	 */
	public boolean isPkgNewCostFlag() {
		return pkgNewCostFlag;
	}

	/**
	 * @param pkgNewCostFlag the pkgNewCostFlag to set
	 */
	public void setPkgNewCostFlag(boolean pkgNewCostFlag) {
		this.pkgNewCostFlag = pkgNewCostFlag;
	}

	/**
	 * @return the ecNewCostFlag
	 */
	public boolean isEcNewCostFlag() {
		return ecNewCostFlag;
	}

	/**
	 * @param ecNewCostFlag the ecNewCostFlag to set
	 */
	public void setEcNewCostFlag(boolean ecNewCostFlag) {
		this.ecNewCostFlag = ecNewCostFlag;
	}

	/**
	 * @return the transNewCostFlag
	 */
	public boolean isTransNewCostFlag() {
		return transNewCostFlag;
	}

	/**
	 * @param transNewCostFlag the transNewCostFlag to set
	 */
	public void setTransNewCostFlag(boolean transNewCostFlag) {
		this.transNewCostFlag = transNewCostFlag;
	}

	/**
	 * @return the dboMechNewCostFlag
	 */
	public boolean isDboMechNewCostFlag() {
		return dboMechNewCostFlag;
	}

	/**
	 * @param dboMechNewCostFlag the dboMechNewCostFlag to set
	 */
	public void setDboMechNewCostFlag(boolean dboMechNewCostFlag) {
		this.dboMechNewCostFlag = dboMechNewCostFlag;
	}

	/**
	 * @return the dboEleNewCostFlag
	 */
	public boolean isDboEleNewCostFlag() {
		return dboEleNewCostFlag;
	}

	/**
	 * @param dboEleNewCostFlag the dboEleNewCostFlag to set
	 */
	public void setDboEleNewCostFlag(boolean dboEleNewCostFlag) {
		this.dboEleNewCostFlag = dboEleNewCostFlag;
	}

	/**
	 * @return the varNewCostFlag
	 */
	public boolean isVarNewCostFlag() {
		return varNewCostFlag;
	}

	/**
	 * @param varNewCostFlag the varNewCostFlag to set
	 */
	public void setVarNewCostFlag(boolean varNewCostFlag) {
		this.varNewCostFlag = varNewCostFlag;
	}

	/**
	 * @return the sparesNewCostFlag
	 */
	public boolean isSparesNewCostFlag() {
		return sparesNewCostFlag;
	}

	/**
	 * @param sparesNewCostFlag the sparesNewCostFlag to set
	 */
	public void setSparesNewCostFlag(boolean sparesNewCostFlag) {
		this.sparesNewCostFlag = sparesNewCostFlag;
	}

	/**
	 * @return the projectNewCostFlag
	 */
	public boolean isProjectNewCostFlag() {
		return projectNewCostFlag;
	}

	/**
	 * @param projectNewCostFlag the projectNewCostFlag to set
	 */
	public void setProjectNewCostFlag(boolean projectNewCostFlag) {
		this.projectNewCostFlag = projectNewCostFlag;
	}

	/**
	 * @return the addOnNewCostFlag
	 */
	public boolean isAddOnNewCostFlag() {
		return addOnNewCostFlag;
	}

	/**
	 * @param addOnNewCostFlag the addOnNewCostFlag to set
	 */
	public void setAddOnNewCostFlag(boolean addOnNewCostFlag) {
		this.addOnNewCostFlag = addOnNewCostFlag;
	}

	/**
	 * @return the f2fNewCostFlag
	 */
	public boolean isF2fNewCostFlag() {
		return f2fNewCostFlag;
	}

	/**
	 * @param f2fNewCostFlag the f2fNewCostFlag to set
	 */
	public void setF2fNewCostFlag(boolean f2fNewCostFlag) {
		this.f2fNewCostFlag = f2fNewCostFlag;
	}

	/**
	 * @return the f2fCostFlag
	 */
	public boolean isF2fCostFlag() {
		return f2fCostFlag;
	}

	/**
	 * @param f2fCostFlag the f2fCostFlag to set
	 */
	public void setF2fCostFlag(boolean f2fCostFlag) {
		this.f2fCostFlag = f2fCostFlag;
	}

	/**
	 * @return the variantCode
	 */
	public String getVariantCode() {
		return variantCode;
	}

	/**
	 * @param variantCode the variantCode to set
	 */
	public void setVariantCode(String variantCode) {
		this.variantCode = variantCode;
	}

	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

}
