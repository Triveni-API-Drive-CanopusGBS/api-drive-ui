package com.ttl.ito.business.beans;

public class LocationBean {

	private int locationId;
	private int dependLocationId;
	
	private String locationName;
	
	private boolean defaultVal;
	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * @return the dependLocationId
	 */
	public int getDependLocationId() {
		return dependLocationId;
	}
	/**
	 * @param dependLocationId the dependLocationId to set
	 */
	public void setDependLocationId(int dependLocationId) {
		this.dependLocationId = dependLocationId;
	}
	/**
	 * @return the defaultVal
	 */
	public boolean isDefaultVal() {
		return defaultVal;
	}
	/**
	 * @param defaultVal the defaultVal to set
	 */
	public void setDefaultVal(boolean defaultVal) {
		this.defaultVal = defaultVal;
	}
	
}
