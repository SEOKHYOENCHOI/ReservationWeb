/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.dao.categorydao;


/**
 *
 * @description : CategoryDaoSqls
 * @package : com.nts.dao.categorydao
 * @filename : CategoryDaoSqls.java
 * @author : 최석현
 * 
 */
public class CategoryDaoSqls {
	
	public static final String SELECT_CATEGORIES_GROUP_BY_ID = "SELECT "
																+ "  count(c.id) AS count"
																+ ", c.id AS id"
																+ ", c.name AS name "
															 + "FROM "
															 	+ "  category c "
															 	+ ", product p "
															 	+ ", display_info di "
															 + "WHERE "
															 	+ "    c.id = p.category_id "
															    + "and p.id = di.product_id "
															 + "GROUP BY "
															 	+ "c.id";
}
