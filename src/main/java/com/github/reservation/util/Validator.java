/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.util;

import java.util.regex.Pattern;

import com.github.reservation.dto.reservationdto.Reservation;

public class Validator {

	private static final String EMAIL_REGEX = "^[\\w+_]\\w+@\\w+\\.\\w+(\\.\\w+)?$";
	private static final String NAME_REGEX = "[가-힣]{2,4}";
	private static final String TEL_REGEX = "01(([16789]-(\\d{3,4}))|0-\\d{4})-\\d{4}$";

	public static boolean emailValidation(String email) {
		return Pattern.matches(EMAIL_REGEX, email);
	}

	public static boolean nameValidation(String name) {
		return Pattern.matches(NAME_REGEX, name);
	}

	public static boolean telValidation(String tel) {
		return Pattern.matches(TEL_REGEX, tel);
	}

	public static boolean reservationValidation(Reservation reservation) {
		return Pattern.matches(EMAIL_REGEX, reservation.getReservationEmail())
				&&Pattern.matches(NAME_REGEX, reservation.getReservationName())
				&&Pattern.matches(TEL_REGEX, reservation.getReservationTel());
	}
}

