/**
 * 
 */
package com.vd.automation.unittestcase.objects;

import java.util.Arrays;
import java.util.List;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class MethodsObject {

	String[] url;
	String requestMethod;
	List<ParameterObject> parameterObject;
	String methodName;
	String methodResponseBodyClass;

	@Override
	public String toString() {
		return "MethodsObject [url=" + Arrays.toString(url) + ", requestMethod=" + requestMethod + ", parameterObject="
				+ parameterObject + ", methodName=" + methodName + ", methodResponseBodyClass="
				+ methodResponseBodyClass + "]";
	}

	public String[] getUrl() {
		return url;
	}

	public void setUrl(String[] url) {
		this.url = url;
	}

	/**
	 * @return the requestMethod
	 */
	public String getRequestMethod() {
		return requestMethod;
	}

	/**
	 * @param requestMethod
	 *            the requestMethod to set
	 */
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the parameterObject
	 */
	public List<ParameterObject> getParameterObject() {
		return parameterObject;
	}

	/**
	 * @param parameterObject
	 *            the parameterObject to set
	 */
	public void setParameterObject(List<ParameterObject> parameterObject) {
		this.parameterObject = parameterObject;
	}

	public String getMethodResponseBodyClass() {
		return methodResponseBodyClass;
	}

	public void setMethodResponseBodyClass(String methodResponseBodyClass) {
		this.methodResponseBodyClass = methodResponseBodyClass;
	}

}
