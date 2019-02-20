/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.dao.productdao;

import static com.github.reservation.dao.productdao.ProductDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.reservation.dto.productdto.Product;
import com.github.reservation.dto.productdto.ProductImage;
import com.github.reservation.dto.productdto.ProductPrice;

/**
 *
 * @description : ProductDao
 * @package : com.nts.dao.productdao
 * @filename : ProductDao.java
 * @author : 최석현
 * @method : List<Product> selectProductsByCategoryId(int categoryId, int start)
 * @method : List<Product> selectProductsAll(int start)
 * @method : int selectProductCountByCategoryId(int categoryId) 
 * @method : int selectProductCountAll()
 * @method : List<ProductImage> selectProductImagesByDisplayInfoId(int displayInfoId)
 * @method : List<ProductPrice> selectProductPricesByDisplayInfoId(int displayInfoId)
 * 
 */
@Repository
public class ProductDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> productImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> productPriceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	public List<Product> selectProductsByCategoryId(int categoryId, int start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);

		return jdbc.query(SELECT_PRODUCTS + BY_ID + LIMIT_4, params, productRowMapper);
	}

	public List<Product> selectProductsAll(int start) {
		Map<String, ?> param = Collections.singletonMap("start", start);

		return jdbc.query(SELECT_PRODUCTS + LIMIT_4, param, productRowMapper);
	}

	public int selectProductCountByCategoryId(int categoryId) {
		Map<String, ?> param = Collections.singletonMap("categoryId", categoryId);

		return jdbc.queryForObject(SELECT_COUNT + BY_ID, param, Integer.class);
	}
	
	public int selectProductCountAll() {

		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}

	public List<ProductImage> selectProductImagesByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_IMAGES_BY_DISPLAY_INFO_ID, param, productImageRowMapper);

	}

	public List<ProductPrice> selectProductPricesByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_PRICES_BY_DISPLAY_INFO_ID, param, productPriceRowMapper);
	}
}