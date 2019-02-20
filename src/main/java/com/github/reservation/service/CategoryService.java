/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.service;

import java.util.List;

import com.github.reservation.dto.categorydto.Category;


/**
 *
 * @description : Category Service Interface
 * @package : com.nts.service
 * @filename : CategoryService.java
 * @author : 최석현
 * @method : List<Category> getItems()
 * 
 */
public interface CategoryService {

	List<Category> getItems();
	
}