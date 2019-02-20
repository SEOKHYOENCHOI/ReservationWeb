/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.service;

import java.util.List;

import com.github.reservation.dto.promotiondto.Promotion;


/**
 *
 * @description : Promotion Service Interface
 * @package : com.nts.service
 * @filename : PromotionService.java
 * @author : 최석현
 * @method : List<Promotion> getItems()
 * 
 */
public interface PromotionService {
	
	List<Promotion> getItems();
	
}

