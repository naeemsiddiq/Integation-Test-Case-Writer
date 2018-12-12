package com.vd.automation.unittestcase.requestmethod.templates;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.vd.automation.unittestcase.AbstractClass;
import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.ParameterObject;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class Get_Delete_MethodsTemplateClass extends AbstractClass {

	public void Get_Delete_MethodTemplate(String baseURL, MethodsObject methodObject, String className) {
		String methodName = methodObject.getMethodName();
		String requestMethod = methodObject.getRequestMethod();
		String[] urlArray = methodObject.getUrl();
		List<ParameterObject> parameterObjectList = methodObject.getParameterObject();
		String methodResponseBodyClass = methodObject.getMethodResponseBodyClass();
		String responseBodyNullCheck = "";
		String url = "";
		int sameMethodCount = 0;
		int urlArrayLength = urlArray.length;
		if (urlArrayLength == 0) {
			url = baseURL + "/";
			writeGet_Delete_MethodUnitTest(className, methodName, requestMethod, url, parameterObjectList,
					responseBodyNullCheck, methodResponseBodyClass);
		}
		for (int i = 0; i < urlArrayLength; i++) {
			String tempMethodName = methodName;
			if (urlArrayLength > 1)
				methodName = methodName + (++sameMethodCount);
			url = baseURL + urlArray[i];
			writeGet_Delete_MethodUnitTest(className, methodName, requestMethod, url, parameterObjectList,
					responseBodyNullCheck, methodResponseBodyClass);
			methodName = tempMethodName;
		}
		/*
		 * // to check GET request Method response by giving POST
		 * writeGetMethodUnitTest(className, methodName + "_POST_Req_inGET_API",
		 * "POST", url, parameterObjectList, responseBodyNullCheck);
		 * responseBodyNullCheck = "_RespBodyNullCheck";
		 * writeGetMethodUnitTest(className, methodName, requestMethod, url,
		 * parameterObjectList, responseBodyNullCheck);
		 */
	}

	private void writeGet_Delete_MethodUnitTest(String className, String methodName, String requestMethod, String url,
			List<ParameterObject> parameterObjectList, String responseBodyNullCheck, String methodResponseBodyClass) {
		totalTestCasesCounter++;
		String queryParams = generateQueryParamsString(parameterObjectList);
		url = url + queryParams;
		String testAnnotation = "@Test";
		String testCaseName = "test" + methodName;
		String methodDefinition = "public void " + testCaseName + "() throws Exception{";
		String apiUrl = "String apiUrl = \"" + url + "\";";
		String uri = "URI uri = URI.create(TestUtils.TEST_BASE_URL + apiUrl);";
		String logTestCaseName = "log.info(\"Executing " + testCaseName + " with URI: {}\", uri);";
		String response = "";
		String responseBody = "";
		String httpEntity = "";
		String userType = getUserType(url);
		if (!userType.equals("")) {
			httpEntity = "HttpEntity entity = TestUtils.getHttpEntiryWithCredentials(TestUtils.TEST_AUTH_URL," + userType + ");";
		} else
			httpEntity = "HttpEntity entity = TestUtils.getHttpEntiryWithoutCredentials(\"\");";
		String responseBodyClassName = extractClassNameFromClassPath(methodResponseBodyClass);
		if (responseBodyClassName.contains("<"))
			response = "ResponseEntity<" + responseBodyClassName
					+ "> response = TestUtils.getRestTemplate().exchange(uri, HttpMethod." + requestMethod
					+ ", entity, new ParameterizedTypeReference<" + responseBodyClassName + ">(){});";
		else
			response = "ResponseEntity<" + responseBodyClassName
					+ "> response = TestUtils.getRestTemplate().exchange(uri, HttpMethod." + requestMethod + ", entity,"
					+ responseBodyClassName + ".class);";
		responseBody = responseBodyClassName + " list = response.getBody();";
		String assertCheck = "assertEquals(HttpStatus.OK, response.getStatusCode());\n\t}\n";
		try (FileWriter fw = new FileWriter(completeClassFilePath, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println("\t" + testAnnotation);
			out.println("\t" + methodDefinition);
			out.println("\t\t" + apiUrl);
			out.println("\t\t" + uri);
			out.println("\t\t" + logTestCaseName);
			out.println("\t\t" + httpEntity);
			out.println("\t\t" + response);
			if (requestMethod.equals("GET"))
				out.println("\t\t" + responseBody);
			out.println("\t\t" + assertCheck);
		} catch (IOException e) {
		}
	}
}
