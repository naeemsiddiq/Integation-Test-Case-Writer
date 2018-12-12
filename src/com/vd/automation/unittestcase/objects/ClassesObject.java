/**
 * 
 */
package com.vd.automation.unittestcase.objects;

import java.util.List;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class ClassesObject {

	String classPackage;
	String className;
	List<MethodsObject> methodObject;
	String baseUrl;

	@Override
	public String toString() {
		return "ClassesObject [classPackage=" + classPackage + ", className=" + className + ", methodObject="
				+ methodObject + ", baseUrl=" + baseUrl + "]";
	}

	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the methodObject
	 */
	public List<MethodsObject> getMethodObject() {
		return methodObject;
	}

	/**
	 * @param methodObject
	 *            the methodObject to set
	 */
	public void setMethodObject(List<MethodsObject> methodObject) {
		this.methodObject = methodObject;
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl
	 *            the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
