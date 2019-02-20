/*
ProductService.java * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.service;

import java.util.List;

import com.github.reservation.dto.productdto.Product;
import com.github.reservation.dto.productdto.ProductImage;
import com.github.reservation.dto.productdto.ProductPrice;
import com.github.reservation.exception.InvalidParameterException;

/**
 *
 * @description : Product Service Interface
 * @package : com.nts.service
 * @filename : ProductService.java
 * @author : 최석현
 * @method : List<Product> getItems(int categoryId, int start)
 * @method : int getCount(int categoryId)
 * @method : List<ProductImage> getProductImagesByDisplayInfoId(int displayInfoId)
 * @method : List<ProductPrice> getProductPricesByDisplayInfoId(int displayInfoId)
 * 
 */
public interface ProductService {

	List<Product> getItems(int categoryId, int start) throws InvalidParameterException;

	int getCount(int categoryId);

	List<ProductImage> getProductImagesByDisplayInfoId(int displayInfoId);
	
	List<ProductPrice> getProductPricesByDisplayInfoId(int displayInfoId);
}
