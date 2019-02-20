/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.service;


import com.github.reservation.dto.displayinfodto.DisplayInfo;
import com.github.reservation.dto.displayinfodto.DisplayInfoImage;

/**
 * 
 *
 * @description : 
 * @package : com.nts.service
 * @filename : DisplayInfoService.java
 * @author : 최석현
 * @method : DisplayInfo getDisplayInfoByDisplayInfoId(int displayInfoId)
 * @method : DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId)
 */
public interface DisplayInfoService {

	DisplayInfo getDisplayInfoByDisplayInfoId(int displayInfoId);

	DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId);
	
	String getProductImageUrlByDisplayInfoId(int displayInfoId);
}