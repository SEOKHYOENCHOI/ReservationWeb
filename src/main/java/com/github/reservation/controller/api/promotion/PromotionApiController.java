/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.controller.api.promotion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.reservation.dto.promotiondto.PromotionResponse;
import com.github.reservation.service.PromotionService;


/**
 *
 * @description : Promotion API Controller
 * @package : com.nts.controller.api.promotion
 * @filename : PromotionApiController.java
 * @author : 최석현
 * @method : PromotionResponse promotions()
 * 
 */
@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	/**
	 * @description : Get 요청을 받으면 Service를 호출해 얻은 결과를 Response로 변환 후 return
	 */
	@GetMapping
	public PromotionResponse promotions() {
		
		return new PromotionResponse(promotionService.getItems());
		
	}
}