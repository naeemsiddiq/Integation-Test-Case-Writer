package com.vd.automation.unittestcase.objects;

import java.util.List;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */

public class RequestBodyFieldsObject {

	String jsonObject;
	String classInstance;
	String classInstanceName;
	List<String> classSettterCalls;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("[jsonObject=");
		builder.append(jsonObject);
		builder.append(", classInstance=");
		builder.append(classInstance);
		builder.append(", classInstanceName=");
		builder.append(classInstanceName);
		builder.append(", classSettterCalls=");
		builder.append(classSettterCalls);
		builder.append("]");
		return builder.toString();
	}

	public String getClassInstanceName() {
		return classInstanceName;
	}

	public void setClassInstanceName(String classInstanceName) {
		this.classInstanceName = classInstanceName;
	}

	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String getClassInstance() {
		return classInstance;
	}

	public void setClassInstance(String classInstance) {
		this.classInstance = classInstance;
	}

	public List<String> getClassSettterCalls() {
		return classSettterCalls;
	}

	public void setClassSettterCalls(List<String> classSettterCalls) {
		this.classSettterCalls = classSettterCalls;
	}

}
