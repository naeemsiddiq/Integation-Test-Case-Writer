package com.vd.automation.unittestcase.objects;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */

public class RequestBodyFieldsObject {

	String jsonObject;
	String requestBodyBeanGetter;

	@Override
	public String toString() {
		return "RequestBodyFieldsObject [jsonObject=" + jsonObject + ", requestBodyBeanGetter=" + requestBodyBeanGetter
				+ "]";
	}

	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String getRequestBodyBeanGetter() {
		return requestBodyBeanGetter;
	}

	public void setRequestBodyBeanGetter(String requestBodyBeanGetter) {
		this.requestBodyBeanGetter = requestBodyBeanGetter;
	}

}
