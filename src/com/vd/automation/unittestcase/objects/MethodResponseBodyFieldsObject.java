/**
 * 
 */
package com.vd.automation.unittestcase.objects;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class MethodResponseBodyFieldsObject {

	String responseBodyClassName;
	String fieldName;
	String fieldType;

	@Override
	public String toString() {
		return "MethodResponseBodyFieldsObject [responseBodyClassName=" + responseBodyClassName + ", fieldName="
				+ fieldName + ", fieldType=" + fieldType + "]";
	}

	public String getResponseBodyClassName() {
		return responseBodyClassName;
	}

	public void setResponseBodyClassName(String responseBodyClassName) {
		this.responseBodyClassName = responseBodyClassName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
}
