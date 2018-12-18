package com.vd.automation.unittestcase.requestmethod.templates;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import com.vd.automation.unittestcase.AbstractClass;
import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.MethodResponseBodyFieldsObject;
import com.vd.automation.unittestcase.objects.ParameterObject;
import com.vd.automation.unittestcase.objects.RequestBodyFieldsObject;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */

public class Post_Patch_Put_MethodTemplateClass extends AbstractClass {

	RequestBodyFieldsObject requestBodyFieldsObject;

	public Post_Patch_Put_MethodTemplateClass() {
		requestBodyFieldsObject = new RequestBodyFieldsObject();
	}

	public void post_patch_put_MethodTemplate(String baseURL, MethodsObject methodObject, String className) {
		requestBodyFieldsObject = null;
		String methodName = methodObject.getMethodName();
		String requestMethod = methodObject.getRequestMethod();
		String[] urlArray = methodObject.getUrl();
		String methodResponseBodyClass = methodObject.getMethodResponseBodyClass();
		List<ParameterObject> parameterObjectList = methodObject.getParameterObject();
		for (int i = 0; i < parameterObjectList.size(); i++) {
			ParameterObject paramObject = parameterObjectList.get(i);
			String paramAnnotatedClass = paramObject.getMethodParamAnnotation();
			if (paramAnnotatedClass.contains(".") && !paramAnnotatedClass.equals("")) {
				String paramBodyType = extractClassNameFromClassPath(paramAnnotatedClass);
				String methodParamDataType = paramObject.getMethodParamDataType();
				if (paramBodyType.equals("RequestBody")) {
					requestBodyFieldsObject = getRequestBodyFieldsObject(methodParamDataType);
				}
			}
		}
		String url = "";
		int sameMethodCount = 0;
		int urlArrayLength = urlArray.length;
		if (urlArrayLength == 0) {
			url = baseURL + "/";
			writePost_Patch_Put_MethodUnitTest(methodName, requestMethod, url, className, "", methodResponseBodyClass,
					parameterObjectList);
		}
		for (int i = 0; i < urlArrayLength; i++) {
			String tempMethodName = methodName;
			if (urlArrayLength > 1)
				methodName = methodName + (++sameMethodCount);
			url = baseURL + urlArray[i];
			writePost_Patch_Put_MethodUnitTest(methodName, requestMethod, url, className, "", methodResponseBodyClass,
					parameterObjectList);
			methodName = tempMethodName;
		}
		// String responseBodyNullCheck = "_RespBodyNullCheck";
		// writePost_Patch_MethodUnitTest(methodName, requestMethod, url,
		// requestBodyFieldsObject, className,
		// responseBodyNullCheck, methodReturnTypeFieldsList);
	}

	private void writePost_Patch_Put_MethodUnitTest(String methodName, String requestMethod, String url,
			String className, String responseBodyNullCheck, String methodResponseBodyClass,
			List<ParameterObject> parameterObjectList) {
		totalTestCasesCounter++;
		// to add query params in at the end of url if there is any
		url = url + generateQueryParamsString(parameterObjectList);
		String testAnnotation = "@Test";
		String testCaseName = "test" + methodName;
		String methodDefinition = "public void " + testCaseName + "() throws Exception{";
		String apiUrl = "String apiUrl = \"" + url + "\";";
		String uri = "URI uri = URI.create(TestUtils.TEST_BASE_URL + apiUrl);";
		String logTestCaseName = "log.info(\"Executing " + testCaseName + " with URI: {}\", uri );";
		String body = "";
		String httpEntity = "";
		String response = "";
		String userType = getUserType(url);
		// If we want to create body through jsonString than
		// ***************************************//
		if (requestBodyFieldsObject != null && !userType.equals("")) {
			// body = "String body =" + requestBodyFieldsObject.getJsonObject();
			httpEntity = "HttpEntity entity = TestUtils.getHttpEntiryWithCredentials(TestUtils.TEST_AUTH_URL,"
					+ userType + ", body);";
		} else if (requestBodyFieldsObject == null && !userType.equals("")) {
			httpEntity = "HttpEntity entity = TestUtils.getHttpEntiryWithCredentials(TestUtils.TEST_AUTH_URL,"
					+ userType + ");";
		} else if (userType.equals("") && requestBodyFieldsObject == null) {
			httpEntity = "HttpEntity entity = TestUtils.getHttpEntiryWithoutCredentials(\"\");";
		} else if (userType.equals("") && requestBodyFieldsObject != null) {
			// body = "String body =" + requestBodyFieldsObject.getJsonObject();
			httpEntity = "HttpEntity entity = TestUtils.getHttpEntiryWithoutCredentials(body);";
		}

		// ***************************************//

		String responseBodyClassName = extractClassNameFromClassPath(methodResponseBodyClass);
		if (responseBodyClassName.contains("<"))
			response = "ResponseEntity<" + responseBodyClassName
					+ "> response = TestUtils.getRestTemplate().exchange(uri, HttpMethod." + requestMethod
					+ ", entity, new ParameterizedTypeReference<" + responseBodyClassName + ">(){});";
		else
			response = "ResponseEntity<" + responseBodyClassName
					+ "> response = TestUtils.getRestTemplate().exchange(uri, HttpMethod." + requestMethod + ", entity,"
					+ responseBodyClassName + ".class);";
		String logResponseStatusCode = "log.info(\"Response : {}\", response.getStatusCode());";
		String assertCheck = "assertEquals(HttpStatus.OK, response.getStatusCode());";
		try (FileWriter fw = new FileWriter(completeClassFilePath, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			out.println("\t" + testAnnotation);
			out.println("\t" + methodDefinition);
			out.println("\t\t" + apiUrl);
			out.println("\t\t" + uri);
			out.println("\t\t" + logTestCaseName);
			// If we want to create body through setter than
			// ***************************************//
			if (requestBodyFieldsObject != null && !requestBodyFieldsObject.getRequestBodyBeanGetter().equals("List")) {
				body = "String body = new ObjectMapper().writeValueAsString("
						+ requestBodyFieldsObject.getRequestBodyBeanGetter() + ");";
				out.println("\t\t" + body);
			}
			// ***************************************//
			out.println("\t\t" + httpEntity);
			out.println("\t\t" + response);
			out.println("\t\t" + logResponseStatusCode);
			out.println("\t\t" + assertCheck);
			/*
			 * List<MethodResponseBodyFieldsObject> methodResponseBodyFieldsList
			 * = getMethodResponseBodyFieldsList( methodResponseBodyClass);
			 * 
			 * if (responseBodyNullCheck.equals("")) {
			 * 
			 * for (int i = 0; i < methodResponseBodyFieldsList.size(); i++) {
			 * String fieldName =
			 * methodResponseBodyFieldsList.get(i).getFieldName(); String
			 * fieldType = methodResponseBodyFieldsList.get(i).getFieldType();
			 * String CapitalizeFieldName = fieldName.substring(0,
			 * 1).toUpperCase() + fieldName.substring(1);
			 * 
			 * out.println("\t\t" + fieldType + " " + CapitalizeFieldName +
			 * "= response.jsonPath().get(\"" + fieldName + "\");");
			 * out.println("\t\t" +
			 * "System.out.println(\"The Booking ID created is  =>  \" +" +
			 * CapitalizeFieldName + ");" + "\n");
			 * 
			 * } }
			 */
			out.println("\t}\n");
		} catch (IOException e) {
		}
	}

	private List<MethodResponseBodyFieldsObject> getMethodResponseBodyFieldsList(String methodResponseBodyClass) {
		List<MethodResponseBodyFieldsObject> methodReturnTypeFieldsList = new ArrayList<>();
		MethodResponseBodyFieldsObject methodReturnTypeFieldsObject;
		Class<?> extractClassNameFromPackage = getClassNameFromPackageInClassType(methodResponseBodyClass);
		Field[] fieldsOfClass = extractClassNameFromPackage.getDeclaredFields();
		for (Field field : fieldsOfClass) {
			String fieldName = field.getName();
			String fieldType = extractClassNameFromClassPath(field.getGenericType().getTypeName());
			methodReturnTypeFieldsObject = new MethodResponseBodyFieldsObject();
			methodReturnTypeFieldsObject.setFieldName(fieldName);
			methodReturnTypeFieldsObject.setFieldType(fieldType);
			methodReturnTypeFieldsList.add(methodReturnTypeFieldsObject);
		}
		return methodReturnTypeFieldsList;
	}

	private RequestBodyFieldsObject getRequestBodyFieldsObject(String requestBodyClassName) {
		RequestBodyFieldsObject requestBodyFieldsObject = new RequestBodyFieldsObject();
		List<String> listOfClassSetters = new ArrayList<>();
		String jSonObject = "\"{";
		String className = extractClassNameFromClassPath(requestBodyClassName);
		// convert ClassName to lowercase to be used as its instance Name;
		String classInstance = "";
		String classInstanceName = className.substring(0, 1).toLowerCase() + className.substring(1);
		String checkIfListClass = getSimpleClassNameFromPackage(requestBodyClassName);
		if (!checkIfListClass.equals("List") && !checkIfListClass.equals("Map")) {
			Class<?> extractedClassFromPackage = getClassNameFromPackageInClassType(requestBodyClassName);
			Field[] fieldsOfClass = extractedClassFromPackage.getDeclaredFields();
			for (int i = 0; i < fieldsOfClass.length; i++) {
				String setFieldValue = "";
				String fieldName = fieldsOfClass[i].getName();
				// Capitalize fieldName to create setter name
				String CapitalizeFieldName = camelCaseName(fieldName);
				String fieldType = fieldsOfClass[i].getGenericType().getTypeName();
				setFieldValue = generateRandomValue("", fieldType);
				// Creating jSon Object
				jSonObject += "\\\"" + fieldName + "\\\": " + setFieldValue;
				if (i != fieldsOfClass.length - 1)
					jSonObject += " ,";
				// Creating setter methods for ParameterClass (i.e- RequestBody)
				String setterMethodOfClass = classInstanceName + ".set" + CapitalizeFieldName + "(" + setFieldValue
						+ ")";
				listOfClassSetters.add(setterMethodOfClass);
			}
			jSonObject += "}\";";
			classInstance = className + " " + classInstanceName + " = new " + className + "();";
			getDefaultBeanGetter(className, classInstance, listOfClassSetters);
			requestBodyFieldsObject.setJsonObject(jSonObject);
			String requestBodyBeanGetterName = "get" + className + "()";
			requestBodyFieldsObject.setRequestBodyBeanGetter(requestBodyBeanGetterName);
		} else {
			jSonObject = "\"{[0]}\";";
			requestBodyFieldsObject.setJsonObject(jSonObject);
			requestBodyFieldsObject.setRequestBodyBeanGetter("List");
		}
		return requestBodyFieldsObject;
	}

	private void getDefaultBeanGetter(String className, String classInstance, List<String> listOfClassSetters) {
		++beansCount;
		String beanGetter = "\tprivate " + className + " get" + className + "() {";
		beanGetter += "\n\t\t" + classInstance;
		for (int i = 0; i < listOfClassSetters.size(); i++) {
			beanGetter += "\n\t\t" + listOfClassSetters.get(i) + ";";
		}
		beanGetter += "\n\t\treturn " + className.substring(0, 1).toLowerCase() + className.substring(1) + "; \n\t}";
		// System.out.println(beanGetter);
		hashMap.put(className, beanGetter);
	}

}
