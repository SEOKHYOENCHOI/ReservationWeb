/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.service;

import java.util.List;

import com.github.reservation.dto.reservationdto.Reservation;
import com.github.reservation.dto.reservationdto.ReservationParam;
import com.github.reservation.dto.reservationdto.ReservationPrice;
import com.github.reservation.dto.reservationinfodto.ReservationInfo;

public interface ReservationService {

	List<ReservationInfo> getReservationInfosByReservationEmail(String reservationEmail);

	List<Reservation> getReservationsByReservationEmail(String reservationEmail);

	List<ReservationPrice> getReservationPricesByReservationInfoId(int reservationInfoId);

	int modifyCancelFlag(int reservationInfoId, boolean cancelFlag);

	int addReservation(ReservationParam reservationParam);

	int addReservationPrices(int key, List<ReservationPrice> prices);
}