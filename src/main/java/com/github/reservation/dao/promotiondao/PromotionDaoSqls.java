/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.dao.promotiondao;


/**
 *
 * @description : PromotionDaoSqls
 * @package : com.nts.dao.promotiondao
 * @filename : PromotionDaoSqls.java
 * @author : 최석현
 * 
 */
public class PromotionDaoSqls {
	public static final String SELECT_PROMOTIONS_ALL = "SELECT "
														+ "  pm.id "
														+ ", pm.product_id "
														+ ", fi.save_file_name AS product_image_url "
													 + "FROM "
													 	+ "  product p "
													 	+ ", promotion pm "
													 	+ ", product_image pimg "
													 	+ ", file_info fi "
													 + "WHERE "
													 	+ "    pimg.type = 'th' "
													 	+ "and p.id = pm.product_id "
													 	+ "and p.id = pimg.product_id "
													 	+ "and fi.id = pimg.file_id "
													 + "ORDER BY "
													 	+ "pm.id "
													 + "ASC";

}
