/**
 * 
 */
package com.vd.automation.unittestcase.populateobjects;

import java.util.List;

import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.ClassesObject;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class PopulateClassesObject {

	/**
	 * @param className
	 * @param methodsObjectList
	 * @param baseURL
	 * @return
	 */
	public ClassesObject PopulateClassObject(String className, String classPackage,
			List<MethodsObject> methodsObjectList, String baseURL) {

		ClassesObject readClassObject = new ClassesObject();
		readClassObject.setClassName(className);
		readClassObject.setClassPackage(classPackage);
		readClassObject.setMethodObject(methodsObjectList);
		readClassObject.setBaseUrl(baseURL);

		return readClassObject;
	}

}
