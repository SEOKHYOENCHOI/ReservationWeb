/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.dao.promotiondao;

import static com.github.reservation.dao.promotiondao.PromotionDaoSqls.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.reservation.dto.promotiondto.Promotion;


/**
 *
 * @description : PromotionDao
 * @package : com.nts.dao.promotiondao
 * @filename : PromotionDao.java
 * @author : 최석현
 * @method : List<Promotion> selectPromotionsAll()
 * 
 */
@Repository
public class PromotionDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	
	
	public List<Promotion> selectPromotionsAll(){
		return jdbc.query(SELECT_PROMOTIONS_ALL, rowMapper);
	}
}