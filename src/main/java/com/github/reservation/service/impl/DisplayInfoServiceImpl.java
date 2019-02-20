/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.reservation.dao.displayinfodao.DisplayInfoDao;
import com.github.reservation.dto.displayinfodto.DisplayInfo;
import com.github.reservation.dto.displayinfodto.DisplayInfoImage;
import com.github.reservation.exception.EmptyResultException;
import com.github.reservation.exception.ExceptionValue;
import com.github.reservation.exception.InvalidParameterException;
import com.github.reservation.service.DisplayInfoService;

/**
 * 
 *
 * @description : 
 * @package : com.nts.service.impl
 * @filename : DisplayInfoServiceImpl.java
 * @author : 최석현
 * @method : DisplayInfo getDisplayInfoByDisplayInfoId(int displayInfoId)
 * @method : DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId)
 */
@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	@Autowired
	private DisplayInfoDao displayInfoDao;

	/**
	 * @description : displayInfoId 값을 검증 후 Dao로부터 DisplayInfo를 받음
	 * @throws : InvalidParameterException
	 */
	@Override
	public DisplayInfo getDisplayInfoByDisplayInfoId(int displayInfoId) throws InvalidParameterException {
		
		if (displayInfoId <= 0) {

			throw new InvalidParameterException("displayInfoId", new ExceptionValue<Integer>(displayInfoId));
			
		}
		
		DisplayInfo displayInfo = new DisplayInfo();
		
		try {
			
			displayInfo = displayInfoDao.selectDisplayInfoByDisplayInfoId(displayInfoId);
			return displayInfo;
			
		} catch(EmptyResultDataAccessException e) {
			
			throw new EmptyResultException("cause", "closedPerformance");
			
		}
	}
	
	/**
	 * @description : displayInfoId 값을 검증 후 Dao로부터 DisplayInfoImage를 받음
	 * @throws : InvalidParameterException
	 */
	@Override
	public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId) throws InvalidParameterException {
		
		if (displayInfoId <= 0) {
			
			throw new InvalidParameterException("displayInfoId", new ExceptionValue<Integer>(displayInfoId));
			
		}
		
		return displayInfoDao.selectDisplayInfoImageByDisplayInfoId(displayInfoId);
	}
	
	@Override
	public String getProductImageUrlByDisplayInfoId(int displayInfoId) {
		return displayInfoDao.selectProductImageUrlByDisplayInfoId(displayInfoId);
	}

}
