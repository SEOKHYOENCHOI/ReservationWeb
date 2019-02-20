/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.reservation.dao.categorydao.CategoryDao;
import com.github.reservation.dto.categorydto.Category;
import com.github.reservation.service.CategoryService;

/**
 *
 * @description : Category Service
 * @package : com.nts.service.impl
 * @filename : CategoryServiceImpl.java
 * @author : 최석현
 * 
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> getItems() {
		return categoryDao.selectCategoriesGroupById();
	}

}
