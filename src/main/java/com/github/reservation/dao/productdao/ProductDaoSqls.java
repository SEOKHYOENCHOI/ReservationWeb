/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.dao.productdao;


/**
 *
 * @description : ProductDaoSqls
 * @package : com.nts.dao.productdao
 * @filename : ProductDaoSqls.java
 * @author : 최석현
 * 
 */
public class ProductDaoSqls {
	public static final String SELECT_COUNT = "SELECT "
												+ "count(di.id)"
											+ "FROM "
												+ "  display_info di"
												+ ", product p "
											+ "WHERE "
												+ "di.product_id = p.id ";
	
	public static final String SELECT_PRODUCTS = "SELECT "
													+ "  di.id AS display_info_id "
													+ ", di.place_name  "
													+ ", p.content "
													+ ", p.description  "
													+ ", di.product_id AS id "
													+ ", fi.save_file_name AS product_image_url "
												+ "FROM "
													+ "  product p "
													+ ", display_info di "
													+ ", product_image pimg "
													+ ", file_info fi "
												+ "WHERE "
													+ "     pimg.type = 'th' "
													+ " and p.id = di.product_id "
													+ " and p.id = pimg.product_id "
													+ " and fi.id = pimg.file_id ";

	public static final String BY_ID =		  " and p.category_id = :categoryId";
	
	public static final String LIMIT_4 = " limit :start, 4 ";
	
	public static final String SELECT_PRODUCT_IMAGES_BY_DISPLAY_INFO_ID = "SELECT "
																			+ "  fi.content_type "
																			+ ", fi.create_date "
																			+ ", fi.delete_flag "
																			+ ", fi.id as file_info_id "
																			+ ", fi.file_name "
																			+ ", fi.modify_date "
																			+ ", p.id as product_id "
																			+ ", p.id as product_image_id "
																			+ ", fi.save_file_name as product_image_url "
																			+ ", pimg.type "
																		+ "FROM "
																			+ "  file_info fi "
																			+ ", product p "
																			+ ", product_image pimg "
																			+ ", display_info di "
																		+ "WHERE "
																			+ "    di.id = :displayInfoId "
																			+ "and p.id = di.product_id "
																			+ "and p.id = pimg.product_id "
																			+ "and fi.id = pimg.file_id "
																			+ "and (pimg.type = 'ma' OR pimg.type = 'et') "
																			+ "LIMIT 2";
	
	 static final String SELECT_PRODUCT_PRICES_BY_DISPLAY_INFO_ID = "SELECT "
			 											+ "  pprc.create_date "
			 											+ ", pprc.discount_rate "
			 											+ ", pprc.modify_date "
			 											+ ", pprc.price "
			 											+ ", pprc.price_type_name "
			 											+ ", pprc.product_id "
			 											+ ", pprc.id "
			 										 + "FROM "
			 										 	+ "  product p "
			 										 	+ ", product_price pprc "
			 										 	+ ", display_info di "
			 										 + "WHERE "
			 											+ "    di.id = :displayInfoId "
			 											+ "and p.id = di.product_id "
			 											+ "and p.id = pprc.id ";

	
}