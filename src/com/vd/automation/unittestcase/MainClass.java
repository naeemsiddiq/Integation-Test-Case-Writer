package com.vd.automation.unittestcase;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.ClassesObject;
import com.vd.automation.unittestcase.populateobjects.PopulateClassesObject;
import com.vd.automation.unittestcase.populateobjects.PopulateMethodsObject;

/**
 * @author Naeem Siddiq
 * 
 *         ASE
 */
public class MainClass extends AbstractClass {

	public static void main(String[] args) throws Exception {

		String className = "";
		String classPackage = "";
		// To get all controller classes from given package
		List<Class<?>> classes = ReadControllerClassesFromPackages.getAllControllerClasses(contrlollersPackagePath);
		PopulateMethodsObject populateMethodsObject = new PopulateMethodsObject();
		List<MethodsObject> methodsObjectList;
		PopulateClassesObject populateClassesObject = new PopulateClassesObject();
		List<ClassesObject> readClassObjectList = new ArrayList<>();
		for (Class<?> cls : classes) {
			className = cls.getName().trim();
			classPackage = getClassPackageFromClassStringName(className);
			className = extractClassNameFromClassPath(className);
			String baseURL = "";
			if (!cls.isAnnotationPresent(RequestMapping.class)) {
				baseURL = "";
			} else {
				String[] path = cls.getAnnotation(RequestMapping.class).value();
				if (path.length == 0)
					baseURL = "";
				else
					for (int i = 0; i < path.length; i++) {
						baseURL += path[i];
						// System.out.println("Class URL : " + path[i]);
					}
			}
			methodsObjectList = new ArrayList<>();
			methodsObjectList = populateMethodsObject.populateMethodsObject(cls);
			readClassObjectList.add(
					populateClassesObject.PopulateClassObject(className, classPackage, methodsObjectList, baseURL));
		}
		CreateUnitTestCaseClass unitTestCaseClass = new CreateUnitTestCaseClass();
//		unitTestCaseClass.UnitTestWriterFunction(readClassObjectList);

		// System.out.println("Total TestCases Written are : " +
		// totalTestCasesCounter);
		System.out.println("Total Beans : " + beansCount);
	}
}
