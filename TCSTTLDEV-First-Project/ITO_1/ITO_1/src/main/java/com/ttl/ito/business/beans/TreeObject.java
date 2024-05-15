package com.ttl.ito.business.beans;

import java.util.List;

public class TreeObject {
	private String parentcd;
	private String childcd;
	
	private List<TreeObject> children;
	private F2FForm data;
	/**
	 * @return the treeObject
	 */
	
	
	/**
	 * @return the data
	 */
	public F2FForm getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(F2FForm data) {
		this.data = data;
	}
	/**
	 * @return the children
	 */
	public List<TreeObject> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TreeObject> children) {
		this.children = children;
	}
	/**
	 * @return the parentcd
	 */
	public String getParentcd() {
		return parentcd;
	}
	/**
	 * @param parentcd the parentcd to set
	 */
	public void setParentcd(String parentcd) {
		this.parentcd = parentcd;
	}
	/**
	 * @return the childcd
	 */
	public String getChildcd() {
		return childcd;
	}
	/**
	 * @param childcd the childcd to set
	 */
	public void setChildcd(String childcd) {
		this.childcd = childcd;
	}
	
	 public void addChildObject(TreeObject child) {
	        if (!this.children.contains(child) && child != null)
	            this.children.add(child);
	    }
}
