package com.vd.automation.unittestcase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.ClassesObject;
import com.vd.automation.unittestcase.requestmethod.templates.Get_Delete_MethodsTemplateClass;
import com.vd.automation.unittestcase.requestmethod.templates.Post_Patch_Put_MethodTemplateClass;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class CreateUnitTestCaseClass extends AbstractClass {

	Get_Delete_MethodsTemplateClass get_delete_MethodsTemplate;
	Post_Patch_Put_MethodTemplateClass post_patch_put_MethodsTemplate;

	public CreateUnitTestCaseClass() {
		get_delete_MethodsTemplate = new Get_Delete_MethodsTemplateClass();
		post_patch_put_MethodsTemplate = new Post_Patch_Put_MethodTemplateClass();
	}

	/**
	 * @param readClassObjectList
	 * @throws Exception
	 */
	public void UnitTestWriterFunction(List<ClassesObject> readClassObjectList) throws Exception {
		for (int i = 0; i < readClassObjectList.size(); i++) {
			ClassesObject readClassObject = readClassObjectList.get(i);
			String className = readClassObject.getClassName();
			String classPackage = readClassObject.getClassPackage();
			System.out.println("Writing test cases of class : " + classPackage + "  " + className);
			completeClassFilePath = unitTestCasesFolderPath + className + "IT.java";
			// Created new File to writ UnitTestCases in it
			CreateNewFile(className, classPackage);
			List<MethodsObject> methodObjectsList = readClassObject.getMethodObject();
			String baseURL = readClassObject.getBaseUrl();
			for (int j = 0; j < methodObjectsList.size(); j++) {
				MethodsObject methodObject = methodObjectsList.get(j);
				if (methodObject.getRequestMethod().equals("GET") || methodObject.getRequestMethod().equals("DELETE")) {
					get_delete_MethodsTemplate.Get_Delete_MethodTemplate(baseURL, methodObject, className);
				} else if (methodObject.getRequestMethod().equals("POST")
						|| methodObject.getRequestMethod().equals("PATCH")
						|| methodObject.getRequestMethod().equals("PUT")) {
					post_patch_put_MethodsTemplate.post_patch_put_MethodTemplate(baseURL, methodObject, className);
				}
			}
			putCloseBracketOfClassInFile();
		}
	}

	private void putCloseBracketOfClassInFile() throws Exception {
		try (FileWriter fw = new FileWriter(completeClassFilePath, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println();
			out.println("}");
			out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}
		completeClassFilePath = "";
	}

	private void CreateNewFile(String className, String classPackage) throws Exception {
		// first delete old file
		File old_file = new File(completeClassFilePath);
		old_file.delete();
		// create new file
		File new_file = new File(completeClassFilePath);
		try (FileWriter fw = new FileWriter(new_file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			// out.println("package " + classPackage + ";\n");
			out.println("package com.droobi.integration;\n");
			out.println("import com.droobi.enums.Language;" + "\nimport com.droobi.enums.UserType;"
					+ "\nimport com.droobi.integration.utils.TestUtils;" + "\nimport com.droobi.pojo.Translation;"
					+ "\nimport com.droobi.pojo.response.BooleanResponse;"
					+ "\nimport com.fasterxml.jackson.databind.ObjectMapper;" + "\nimport java.util.List;"
					+ "\nimport com.droobi.pojo.response.ResponseList;" + "\nimport java.net.URI;"
					+ "\nimport com.fasterxml.jackson.databind.ObjectMapper;" + "\nimport lombok.extern.slf4j.Slf4j;"
					+ "\nimport org.junit.Test;" + "\nimport org.junit.runner.RunWith;"
					+ "\nimport org.springframework.beans.factory.annotation.Value;"
					+ "\nimport org.springframework.core.ParameterizedTypeReference;"
					+ "\nimport org.springframework.http.HttpEntity;" + "\nimport org.springframework.http.HttpMethod;"
					+ "\nimport org.springframework.http.HttpStatus;"
					+ "\nimport org.springframework.http.ResponseEntity;"
					+ "\nimport org.springframework.test.context.junit4.SpringRunner;"
					+ "\nimport static org.assertj.core.api.Assertions.assertThat;"
					+ "\nimport static org.junit.Assert.assertEquals;"
					+ "\nimport static org.junit.Assert.assertNotNull;\n");
			out.println("@RunWith(SpringRunner.class)\n" + "@Slf4j");
			out.println("public class " + className + "IT  {");
			out.println();
			// out.println("\t" + "//Add this jackson library in your pom.xml
			// file ");
			// out.println("\t" + "//<dependency>" + "\n" + "\t" +
			// "//<groupId>com.fasterxml.jackson.core</groupId>" + "\n"
			// + "\t" + "//<artifactId>jackson-databind</artifactId>" + "\n" +
			// "\t" + "//<version>2.6.3</version>"
			// + "\n" + "\t" + "//</dependency>");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
