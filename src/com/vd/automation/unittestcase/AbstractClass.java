/**
 * 
 */
package com.vd.automation.unittestcase;

import java.util.HashMap;
import java.util.List;
import com.vd.automation.unittestcase.objects.ParameterObject;
import com.vd.automation.unittestcase.randomvaluesgenrator.RandomValuesGeneratorClass;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class AbstractClass {

	protected static int totalTestCasesCounter = 0;
	protected static int testCaseNumber = 0;
	protected static int beansCount = 0;
	protected static HashMap<String, String> hashMap = new HashMap<>();
	// write the folder path here
	protected static String unitTestCasesFolderPath = "C:\\Users\\Sakhi\\Desktop\\Unit Test Cases\\";
	protected static String completeClassFilePath = "";
	// Write the package path here
	protected static String contrlollersPackagePath = "com.droobi";

	protected static Class<?> getClassNameFromPackageInClassType(String classNamewithPackage) {
		String classPackagePath = getClassPackageFromClassStringName(classNamewithPackage);
		Class<?> requiredClass = null;
		List<Class<?>> classes = ReadControllerClassesFromPackages.find(classPackagePath);
		for (Class<?> cls : classes) {
			if (cls.getName().equals(classNamewithPackage)) {
				requiredClass = cls;
				break;
			}
		}
		return requiredClass;
	}

	protected static String getClassPackageFromClassStringName(String classNamewithPackage) {
		boolean check = true;
		String classPackagePath = "";
		for (int i = classNamewithPackage.length() - 1; i >= 0; i--) {
			if (classNamewithPackage.charAt(i) == '.' && check == true)
				check = false;
			else if (check == true)
				System.out.println();
			else
				classPackagePath = classNamewithPackage.charAt(i) + classPackagePath;
		}
		return classPackagePath;
	}

	// custom for Droobi if class is like List<User> or Map<User> or etc
	protected static String extractClassNameFromClassPath(String classNamewithPackage) {
		String className = "";
		if (classNamewithPackage.contains("<")) {
			String withLessThan = getSimpleClassNameFromPackage(
					classNamewithPackage.substring(classNamewithPackage.indexOf("<")));
			String withoutLessThan = getSimpleClassNameFromPackage(
					classNamewithPackage.substring(0, classNamewithPackage.indexOf("<")));
			className = withoutLessThan + "<" + withLessThan;
		} else {
			className = getSimpleClassNameFromPackage(classNamewithPackage);
		}
		if (className.contains("?"))
			className = className.replace("<<", "<");
		return className;
	}

	/**
	 * @param classNamewithPackage
	 */
	protected static String getSimpleClassNameFromPackage(String classNamewithPackage) {
		String className = "";
		for (int i = classNamewithPackage.length() - 1; i >= 0; i--) {
			if (classNamewithPackage.charAt(i) == '.')
				break;
			else
				className = classNamewithPackage.charAt(i) + className;
		}
		return className;
	}

	/**
	 * @param parameterObjectList
	 * @return
	 */
	protected static String generateQueryParamsString(List<ParameterObject> parameterObjectList) {
		String queryParams = "?";
		for (int i = 0; i < parameterObjectList.size(); i++) {
			ParameterObject paramObject = parameterObjectList.get(i);
			String paramAnnotatedClass = paramObject.getMethodParamAnnotation();
			if (paramAnnotatedClass.contains(".") && !paramAnnotatedClass.equals("")) {
				String paramBodyType = extractClassNameFromClassPath(paramAnnotatedClass);
				String methodParamDataType = paramObject.getMethodParamDataType();
				String methodParamName = paramObject.getParameterName();
				String randomValue = generateRandomValue("queryParam", methodParamDataType);
				if (paramBodyType.equals("RequestParam")) {
					queryParams = queryParams + methodParamName + "=" + randomValue + "&";
				}
			}
		}
		return queryParams.substring(0, queryParams.length() - 1);
	}

	/**
	 * @param fieldType
	 * @return
	 */
	protected static String generateRandomValue(String queryParam, String fieldType) {
		RandomValuesGeneratorClass randomValuesGeneratorClass = new RandomValuesGeneratorClass();
		String setFieldValue = "";
		if (fieldType.equals("java.lang.String")) {
			setFieldValue = randomValuesGeneratorClass.getStringRandomValue();
			if (queryParam.equals("queryParam")) {
				setFieldValue = setFieldValue.replace('"', ' ').trim();
				setFieldValue = setFieldValue.replace('"', ' ').trim();
			}
		} else if (fieldType.equals("java.lang.Long")) {
			setFieldValue = randomValuesGeneratorClass.getLongRandomValue();
			if (queryParam.equals("queryParam")) {
				setFieldValue = setFieldValue.replace('L', ' ').trim();
			}
		} else if (fieldType.equals("java.lang.Double")) {
			setFieldValue = randomValuesGeneratorClass.getDoubleRandomValue();
		} else if (fieldType.equals("java.lang.Integer")) {
			setFieldValue = randomValuesGeneratorClass.getIntegerRandomValue();
		} else if (fieldType.equals("java.lang.Float")) {
			setFieldValue = randomValuesGeneratorClass.getFloatRandomValue();
		} else if (fieldType.equals("java.lang.Date")) {
			setFieldValue = randomValuesGeneratorClass.getDateRandomValue();
		} else
			setFieldValue = "null";
		return setFieldValue;
	}

	/**
	 * @param fieldName
	 * @return
	 */
	protected static String camelCaseName(String fieldName) {
		String camelCasedName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return camelCasedName;
	}

	// UserdASHBOARDcONTROLLER

	/**
	 * @param url
	 * @return
	 */
	// for droobi project
	protected static String getUserType(String url) {
		String url1 = url;
		String userType = "UserType.";
		String sameApi = "v1/api/";
		try {
			int length = sameApi.length();
			if (url.charAt(0) == '/')
				url = url.substring(1);
			url = url.substring(length);
			String userTypeFromUrl = url.substring(0, url.indexOf('/'));
			if (userTypeFromUrl.equals("patients") || userTypeFromUrl.equals("contents")
					|| userTypeFromUrl.equals("weekly-goals") || userTypeFromUrl.equals("common")
					|| userTypeFromUrl.equals("users")) {
				userType += "PATIENT";
			} else if (userTypeFromUrl.equals("administration"))
				userType += "DROOBI_ADMIN";
			else if (userTypeFromUrl.equals("hospital-management"))
				userType += "HOSPITAL_ADMIN";
			else if (userTypeFromUrl.equals("configuration"))
				userType += "CONFIG_ADMIN";
			else if (userTypeFromUrl.equals("professionals"))
				userType += "DOCTOR";
			else
				userType = "";
		} catch (Exception e) {
			System.out.println("USER TTYPE  : " + url1);
		}
		return userType;
	}
}
