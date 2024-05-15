package com.ttl.ito.business.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ttl.ito.business.beans.F2FForm;

public class Entry implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private F2FForm data;
	  
	 // @JsonBackReference
	  private List<Entry> children;
      private Entry entry;
      public Entry(F2FForm F2FForm) {
         this.data = F2FForm;
      }

      public Entry() {
          
       }

      
      public void add(Entry node){
         if (children == null)
            children = new ArrayList<Entry>();
         children.add(node);
      }

	

	/**
	 * @return the children
	 */
	public List<Entry> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<Entry> children) {
		this.children = children;
	}

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
	 * @return the entry
	 */
	public Entry getEntry() {
		return entry;
	}

	/**
	 * @param entry the entry to set
	 */
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
}
