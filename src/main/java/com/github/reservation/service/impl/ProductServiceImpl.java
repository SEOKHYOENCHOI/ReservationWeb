/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.reservation.dao.productdao.ProductDao;
import com.github.reservation.dto.productdto.Product;
import com.github.reservation.dto.productdto.ProductImage;
import com.github.reservation.dto.productdto.ProductPrice;
import com.github.reservation.exception.ExceptionValue;
import com.github.reservation.exception.InvalidParameterException;
import com.github.reservation.service.ProductService;

/**
 *
 * @description : Product Service
 * @package : com.nts.service.impl
 * @filename : ProductServiceImpl.java
 * @author : 최석현
 * @method : List<Product> getItems(int categoryId, int start)
 * @method : int getCount(int categoryId)
 * @method : List<ProductImage> getProductImagesByDisplayInfoId(int displayInfoId)
 * @method : List<ProductPrice> getProductPricesByDisplayInfoId(int displayInfoId)
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	private static final int ALL_CATEGORIES = 0;
	
	/**
	 * @description : categoryId와 start 값을 검증 후 Dao로부터 List를 받음
	 * @throws : InvalidParameterException
	 */
	@Override
	public List<Product> getItems(int categoryId, int start) throws InvalidParameterException {

		if (categoryId < 0) {

			throw new InvalidParameterException("categoryId", new ExceptionValue<Integer>(categoryId));

		} else if (start < 0) {

			throw new InvalidParameterException("start", new ExceptionValue<Integer>(start));

		} else if (categoryId == ALL_CATEGORIES) {

			return productDao.selectProductsAll(start);

		}

		return productDao.selectProductsByCategoryId(categoryId, start);

	}
	
	/**
	 * @description : categoryId 값을 검증 후 Dao로부터 Count를 받음
	 * @throws : InvalidParameterException
	 */
	@Override
	public int getCount(int categoryId) throws InvalidParameterException {

		if (categoryId < 0) {

			throw new InvalidParameterException("categoryId", new ExceptionValue<Integer>(categoryId));

		} else if (categoryId == ALL_CATEGORIES) {

			return productDao.selectProductCountAll();

		}

		return productDao.selectProductCountByCategoryId(categoryId);

	}

	/**
	 * @description : displayInfoId 값을 검증 후 Dao로부터 List를 받음
	 * @throws : InvalidParameterException
	 */
	@Override
	public List<ProductImage> getProductImagesByDisplayInfoId(int displayInfoId) throws InvalidParameterException {
		
		if (displayInfoId <= 0) {

			throw new InvalidParameterException("displayInfoId", new ExceptionValue<Integer>(displayInfoId));
			
		}
		
		return productDao.selectProductImagesByDisplayInfoId(displayInfoId);
	}

	/**
	 * @description : displayInfoId 값을 검증 후 Dao로부터 List를 받음
	 * @throws : InvalidParameterException
	 */
	@Override
	public List<ProductPrice> getProductPricesByDisplayInfoId(int displayInfoId) throws InvalidParameterException {
		
		if (displayInfoId <= 0) {

			throw new InvalidParameterException("displayInfoId", new ExceptionValue<Integer>(displayInfoId));
			
		}
		
		return productDao.selectProductPricesByDisplayInfoId(displayInfoId);
	}

}
