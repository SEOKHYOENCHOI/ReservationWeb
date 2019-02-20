/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.dao.reservationdao;


public class ReservationDaoSqls {

	public static final String SELECT_RESERVATIONS_BY_RESERVATION_EMAIL = "SELECT "
																				+ "  ri.cancel_flag "
																				+ ", ri.create_date "
																				+ ", ri.display_info_id "
																				+ ", ri.modify_date "
																				+ ", ri.product_id "
																				+ ", ri.reservation_date "
																				+ ", ri.reservation_email "
																				+ ", ri.id as reservation_info_id "
																				+ ", ri.reservation_name "
																				+ ", ri.reservation_tel "
																		  + "FROM "
																				+ "  reservation_info ri "
																		  + "WHERE "
																				+ "  ri.reservation_email = :reservationEmail ";
	
	public static final String SELECT_RESERVATION_PRICES_BY_RESERVATION_INFO_ID = "SELECT "
																		+ "  rip.count "
																		+ ", rip.product_price_id "
																		+ ", rip.reservation_info_id "
																		+ ", rip.id "
																		+ ", pp.price "
																		+ ", pp.discount_rate "
																		+ ", pp.price_type_name "
																		+ "FROM "
																		+ "  reservation_info_price rip "
																		+ ", product_price pp "
																		+ "WHERE "
																		+ "    rip.reservation_info_id = :reservationInfoId "
																		+ "and rip.product_price_id = pp.id ";
	
	public static final String SELECT_TOTAL_PRICE_BY_RESERVATION_INFO_ID = "SELECT "
																			+ 	"pp.price * rip.count "
																		 + "FROM "
																		 	+ "  product_price pp "
																		 	+ ", reservation_info_price rip "
																		 + "WHERE "
																			+ "    pp.id = rip.product_price_id "
																			+ "and rip.reservation_info_id = :reservationInfoId ";
	
	public static final String UPDATE_CANCEL_FLAG = "UPDATE "
													+ " reservation_info ri "
												  + "SET "
												  	+ " ri.cancel_flag = :cancelFlag "
												  + "WHERE "
													+ " ri.id = :reservationInfoId";
	
	
	public static final String INSERT_RESERVATION = "INSERT INTO "
														+ " reservation_info "
														+ "( "
														+ "  display_info_id "
														+ ", create_date "
														+ ", modify_date "
														+ ", product_id "
														+ ", cancel_flag "
														+ ", reservation_date "
														+ ", reservation_email "
														+ ", reservation_name "
														+ ", reservation_tel "
														+ ") "
													+ "VALUES ( "
														+ "  :displayInfoId "
														+ ", :createDate "
														+ ", :modifyDate "
														+ ", :productId "
														+ ", :cancelFlag "
														+ ", :reservationDate "
														+ ", :reservationEmail "
														+ ", :reservationName "
														+ ", :reservationTel "
													+ ") ";
	public static final String INSERT_RESERVATION_PRICE = "INSERT INTO "
															+ " reservation_info_price "
															+ "( "
															+ "  reservation_info_id "
															+ ", product_price_id "
															+ ", count "
															+ ")"
														 + "VALUES ( "
															+ "  :reservationInfoId "
															+ ", :productPriceId "
															+ ", :count "
														 + ") ";															
}
