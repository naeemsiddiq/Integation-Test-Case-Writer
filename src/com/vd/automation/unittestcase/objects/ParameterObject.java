/**
 * 
 */
package com.vd.automation.unittestcase.objects;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class ParameterObject {

	String parameterName;
	String methodParamDataType;
	String methodParamAnnotation;

	@Override
	public String toString() {
		return "ParameterObject [parameterName=" + parameterName + ", methodParamDataType=" + methodParamDataType
				+ ", methodParamAnnotation=" + methodParamAnnotation + "]";
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 * @return the methodParamDataType
	 */
	public String getMethodParamDataType() {
		return methodParamDataType;
	}

	/**
	 * @param methodParamDataType
	 *            the methodParamDataType to set
	 */
	public void setMethodParamDataType(String methodParamDataType) {
		this.methodParamDataType = methodParamDataType;
	}

	/**
	 * @return the methodParamAnnotation
	 */
	public String getMethodParamAnnotation() {
		return methodParamAnnotation;
	}

	/**
	 * @param methodParamAnnotation
	 *            the methodParamAnnotation to set
	 */
	public void setMethodParamAnnotation(String methodParamAnnotation) {
		this.methodParamAnnotation = methodParamAnnotation;
	}
}
