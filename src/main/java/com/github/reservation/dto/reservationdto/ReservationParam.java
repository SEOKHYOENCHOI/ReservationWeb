/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.dto.reservationdto;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReservationParam {
	private Reservation reservation;
	private List<ReservationPrice> prices;

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public List<ReservationPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
	}

	@Override
	public String toString() {
		ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
		ReflectionToStringBuilder tsb = new ReflectionToStringBuilder(this, style);
		return tsb.toString();
	}

}
