/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.dto.reservationinfodto;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.reservation.dto.displayinfodto.DisplayInfo;
import com.github.reservation.dto.reservationdto.Reservation;
import com.github.reservation.dto.reservationdto.ReservationPrice;

public class ReservationInfo {
	private Reservation reservation;
	private DisplayInfo displayInfo;
	private List<ReservationPrice> reservationPrices;
	
	public List<ReservationPrice> getReservationPrices() {
		return reservationPrices;
	}

	public void setReservationPrices(List<ReservationPrice> reservationPrices) {
		this.reservationPrices = reservationPrices;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	@Override
	public String toString() {
		ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
		ReflectionToStringBuilder tsb = new ReflectionToStringBuilder(this, style);
		return tsb.toString();
	}


}
