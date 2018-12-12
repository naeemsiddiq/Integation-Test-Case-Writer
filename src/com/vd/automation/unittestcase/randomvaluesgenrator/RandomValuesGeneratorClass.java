/**
 * 
 */
package com.vd.automation.unittestcase.randomvaluesgenrator;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Naeem Siddiq
 * 
 *         ASE, Venturedive
 */
public class RandomValuesGeneratorClass {

	public String getStringRandomValue() {
		int codeLength = 4;
		String id = "abcdefghijklmnopqrstuvwxyz";
		List<Character> temp = id.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
		Collections.shuffle(temp, new SecureRandom());
		return "\"" + temp.stream().map(Object::toString).limit(codeLength).collect(Collectors.joining()) + "\"";
	}

	public String getLongRandomValue() {
		long leftLimit = 1L;
		long rightLimit = 5L;
		long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

		return String.valueOf(generatedLong)+"L";
	}

	public String getDoubleRandomValue() {
		double leftLimit = 1D;
		double rightLimit = 5D;
		double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
		return String.valueOf(generatedDouble);
	}

	public String getIntegerRandomValue() {
		int leftLimit = 1;
		int rightLimit = 5;
		int generatedInteger = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
		return String.valueOf(generatedInteger);
	}

	public String getDateRandomValue() {
		long beginTime = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
		long endTime = Timestamp.valueOf("2013-12-31 00:58:00").getTime();

		long diff = endTime - beginTime + 1;
		return String.valueOf(beginTime + (long) (Math.random() * diff));

	}

	public String getFloatRandomValue() {
		float leftLimit = 1F;
		float rightLimit = 5F;
		float generatedFloat = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
		return String.valueOf(generatedFloat);
	}

}
