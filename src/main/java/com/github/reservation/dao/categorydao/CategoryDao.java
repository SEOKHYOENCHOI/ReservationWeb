/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.dao.categorydao;

import static com.github.reservation.dao.categorydao.CategoryDaoSqls.SELECT_CATEGORIES_GROUP_BY_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.reservation.dto.categorydto.Category;


/**
 *
 * @description : CategoryDao
 * @package : com.nts.dao.categorydao
 * @filename : CategoryDao.java
 * @author : 최석현
 * @method : List<Category> selectCategoriesGroupById()
 * 
 */
@Repository
public class CategoryDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public List<Category> selectCategoriesGroupById() {
		return jdbc.query(SELECT_CATEGORIES_GROUP_BY_ID, rowMapper);
	}
}
