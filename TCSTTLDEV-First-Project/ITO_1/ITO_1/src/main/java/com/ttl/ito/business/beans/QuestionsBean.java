package com.ttl.ito.business.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class QuestionsBean {

	private SelectBox dropDownType = new SelectBox();
	private List<SelectBox> dropDownValueList =new ArrayList<>(); 
	private Map<Integer,SelectBox> dropDownAnsValueList =new HashMap<>();
	private List<AddOnComponent> addOnValueList =new ArrayList<>(); 
	
	public SelectBox getDropDownType() {
		return dropDownType;
	}

	public void setDropDownType(SelectBox dropDownType) {
		this.dropDownType = dropDownType;
	}

	@Override
	public String toString() {
		return "QuestionsBean [dropDownType=" + dropDownType + ", dropDownValueList=" + dropDownValueList + "]";
	}

	/**
	 * @return the dropDownValueList
	 */
	public List<SelectBox> getDropDownValueList() {
		return dropDownValueList;
	}

	/**
	 * @param dropDownValueList the dropDownValueList to set
	 */
	public void setDropDownValueList(List<SelectBox> dropDownValueList) {
		this.dropDownValueList = dropDownValueList;
	}

	/**
	 * @return the dropDownAnsValueList
	 */
	public Map<Integer, SelectBox> getDropDownAnsValueList() {
		return dropDownAnsValueList;
	}

	/**
	 * @param dropDownAnsValueList the dropDownAnsValueList to set
	 */
	public void setDropDownAnsValueList(Map<Integer, SelectBox> dropDownAnsValueList) {
		this.dropDownAnsValueList = dropDownAnsValueList;
	}

	/**
	 * @return the addOnValueList
	 */
	public List<AddOnComponent> getAddOnValueList() {
		return addOnValueList;
	}

	/**
	 * @param addOnValueList the addOnValueList to set
	 */
	public void setAddOnValueList(List<AddOnComponent> addOnValueList) {
		this.addOnValueList = addOnValueList;
	}

	/**
	 * @param dropDownValueList the dropDownValueList to set
	 */


}
