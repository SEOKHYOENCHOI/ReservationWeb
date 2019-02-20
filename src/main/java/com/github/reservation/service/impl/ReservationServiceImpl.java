/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.reservation.dao.displayinfodao.DisplayInfoDao;
import com.github.reservation.dao.reservationdao.ReservationDao;
import com.github.reservation.dto.reservationdto.Reservation;
import com.github.reservation.dto.reservationdto.ReservationParam;
import com.github.reservation.dto.reservationdto.ReservationPrice;
import com.github.reservation.dto.reservationinfodto.ReservationInfo;
import com.github.reservation.exception.ExceptionValue;
import com.github.reservation.exception.InvalidParameterException;
import com.github.reservation.service.ReservationService;
import com.github.reservation.util.Validator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;
	@Autowired
	DisplayInfoDao displayInfoDao;

	@Override
	public List<ReservationInfo> getReservationInfosByReservationEmail(String reservationEmail) {
		Validator.emailValidation(reservationEmail);
		
		List<Reservation> reservations = getReservationsByReservationEmail(reservationEmail);
		List<ReservationInfo> reservationInfos = new ArrayList<>();
		
		for(Reservation reservation : reservations) {
			ReservationInfo reservationInfo = new ReservationInfo();
			
			reservationInfo.setReservation(reservation);
			reservationInfo.setReservationPrices(getReservationPricesByReservationInfoId(reservation.getDisplayInfoId()));
			reservationInfo.setDisplayInfo(displayInfoDao.selectDisplayInfoByDisplayInfoId(reservation.getDisplayInfoId()));
			reservationInfos.add(reservationInfo);
		}
		return reservationInfos;
	}

	@Override
	public List<Reservation> getReservationsByReservationEmail(String reservationEmail) {
		Validator.emailValidation(reservationEmail);
		return reservationDao.selectReservationsByReservationEmail(reservationEmail);
	}

	@Override
	public List<ReservationPrice> getReservationPricesByReservationInfoId(int reservationInfoId) {
		if (reservationInfoId <= 0) {
			throw new InvalidParameterException("reservationInfoId", new ExceptionValue<Integer>(reservationInfoId));
		}
		
		return reservationDao.selectReservationPricesByReservationInfoId(reservationInfoId);
	}

	@Override
	public int modifyCancelFlag(int reservationInfoId, int cancelFlag) {
		if (reservationInfoId <= 0) {
			throw new InvalidParameterException("reservationInfoId", new ExceptionValue<Integer>(reservationInfoId));
		}
		
		return reservationDao.updateCancelFlag(reservationInfoId, cancelFlag);
	}

	@Override
	@Transactional(readOnly = false)
	public int addReservation(ReservationParam reservationParam) {
		Validator.emailValidation(reservationParam.getReservation().getReservationEmail());
		Validator.nameValidation(reservationParam.getReservation().getReservationName());
		Validator.telValidation(reservationParam.getReservation().getReservationTel());
		
		int key = reservationDao.insertReservation(reservationParam.getReservation());
		return addReservationPrices(key, reservationParam.getPrices());
	}

	@Override
	public int addReservationPrices(int key, List<ReservationPrice> prices) {
		if (key <= 0) {
			throw new InvalidParameterException("reservationInfoId", new ExceptionValue<Integer>(key));
		}
		
		int insertResult = 0;
		for(ReservationPrice price : prices) {
			insertResult += reservationDao.insertReservationPrice(key, price);
		}
		return insertResult;
	}
}
