/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.util;

import java.util.regex.Pattern;

import com.github.reservation.exception.ExceptionValue;
import com.github.reservation.exception.InvalidParameterException;

public class Validator {

	private static final String EMAIL_REGEX = "^[\\w+_]\\w+@\\w+\\.\\w+(\\.\\w+)?$";
	private static final String NAME_REGEX = "[가-힣]{2,4}";
	private static final String TEL_REGEX = "01(([16789]-(\\d{3,4}))|0-\\d{4})-\\d{4}$";
	
	
	public static void emailValidation(String email) {
		if(!Pattern.matches(EMAIL_REGEX, email)) {
			throw new InvalidParameterException("email", new ExceptionValue<String>(email));
		};
	}
	public static void nameValidation(String name) {
		if(!Pattern.matches(NAME_REGEX, name)) {
			throw new InvalidParameterException("name", new ExceptionValue<String>(name));
		};
	}
	public static void telValidation(String tel) {
		if(!Pattern.matches(TEL_REGEX, tel)) {
			throw new InvalidParameterException("tel", new ExceptionValue<String>(tel));
		};
	}
}


