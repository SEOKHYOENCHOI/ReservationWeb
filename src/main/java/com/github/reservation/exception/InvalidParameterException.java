/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.exception;
public class InvalidParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String paramName;
	private ExceptionValue<?> paramValue;
	
	public InvalidParameterException(String paramName, ExceptionValue<?> paramValue) {
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public ExceptionValue<?>  getParamValue() {
		return paramValue;
	}

	public void setParamValue(ExceptionValue<?> paramValue) {
		this.paramValue = paramValue;
	}

}