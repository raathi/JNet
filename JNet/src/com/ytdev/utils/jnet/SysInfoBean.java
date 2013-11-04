/**
 * 
 */
package com.ytdev.utils.jnet;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Thirumalaivasan Rajasekaran
 *
 */
public class SysInfoBean {
	private final SimpleStringProperty systemVariable = new SimpleStringProperty();
	private final SimpleStringProperty value = new SimpleStringProperty();
	
	/**
	 * 
	 */
	public SysInfoBean() {
		this("","");
	}
	
	
	/**
	 * 
	 */
	public SysInfoBean(String systemVariable, String value) {
		setSystemVariable(systemVariable);
		setValue(value);
	}

	public final String getSystemVariable(){
		return systemVariable.get();
	}
	
	public final void setSystemVariable(String systemVariable){
		this.systemVariable.set(systemVariable);
	}
	
	public final String getValue(){
		return value.get();
	}
	
	public final void setValue(String value){
		this.value.set(value);
	}
	
}
