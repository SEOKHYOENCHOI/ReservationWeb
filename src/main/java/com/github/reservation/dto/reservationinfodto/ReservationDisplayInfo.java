/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.dto.reservationinfodto;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.reservation.dto.displayinfodto.DisplayInfo;
import com.github.reservation.dto.productdto.ProductPrice;

public class ReservationDisplayInfo {

	private DisplayInfo displayInfo;
	private List<ProductPrice> reservationPrices;
	private String productImageUrl;
	
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public ReservationDisplayInfo() {}
	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public List<ProductPrice> getReservationPrices() {
		return reservationPrices;
	}

	public void setReservationPrices(List<ProductPrice> reservationPrices) {
		this.reservationPrices = reservationPrices;
	}
	
	@Override
	public String toString() {
		ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
		ReflectionToStringBuilder tsb = new ReflectionToStringBuilder(this, style);
		return tsb.toString();
	}
}
