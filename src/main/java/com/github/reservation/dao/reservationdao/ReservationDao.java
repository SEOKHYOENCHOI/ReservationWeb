/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.dao.reservationdao;

import static com.github.reservation.dao.reservationdao.ReservationDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.github.reservation.dto.reservationdto.Reservation;
import com.github.reservation.dto.reservationdto.ReservationPrice;

@Repository
public class ReservationDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private RowMapper<Reservation> reservationRowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);
	private RowMapper<ReservationPrice> reservationPriceRowMapper = BeanPropertyRowMapper.newInstance(ReservationPrice.class);
	
	public List<Reservation> selectReservationsByReservationEmail(String reservationEmail) {
		Map<String, ?> param = Collections.singletonMap("reservationEmail", reservationEmail);
		return jdbc.query(SELECT_RESERVATIONS_BY_RESERVATION_EMAIL, param, reservationRowMapper);
	}
	
	public List<ReservationPrice> selectReservationPricesByReservationInfoId(int reservationInfoId) {
		Map<String, ?> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.query(SELECT_RESERVATION_PRICES_BY_RESERVATION_INFO_ID, param, reservationPriceRowMapper);
	}
	
	public int selectTotalPriceByReservationInfoId(int reservationInfoId) {
		Map<String, ?> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(SELECT_TOTAL_PRICE_BY_RESERVATION_INFO_ID, param, Integer.class);
	}
	
	public int updateCancelFlag(int reservationInfoId, boolean cancelFlag) {
		SqlParameterSource source = new MapSqlParameterSource()
										.addValue("reservationInfoId", reservationInfoId)
										.addValue("cancelFlag", cancelFlag);
		return jdbc.update(UPDATE_CANCEL_FLAG, source);
	}

	public int insertReservation(Reservation reservation) {
		KeyHolder generatedKey= new GeneratedKeyHolder();
		SqlParameterSource source = new MapSqlParameterSource()
										.addValue("displayInfoId", reservation.getDisplayInfoId())
										.addValue("productId", reservation.getProductId())
										.addValue("cancelFlag", reservation.getCancelFlag())
										.addValue("reservationDate", reservation.getReservationDate())
										.addValue("reservationEmail", reservation.getReservationEmail())
										.addValue("reservationName", reservation.getReservationName())
										.addValue("reservationTel", reservation.getReservationTel());
		jdbc.update(INSERT_RESERVATION, source, generatedKey);
		return generatedKey.getKey().intValue();
	}
	
	public int insertReservationPrice(int key, ReservationPrice price) {
		SqlParameterSource source = new MapSqlParameterSource()
				.addValue("reservationInfoId", key)
				.addValue("productPriceId", price.getProductPriceId())
				.addValue("count", price.getCount());
		return jdbc.update(INSERT_RESERVATION_PRICE, source);
	}


}
