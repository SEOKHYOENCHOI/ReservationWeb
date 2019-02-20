/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.controller.api.reservation;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.reservation.dto.displayinfodto.DisplayInfo;
import com.github.reservation.dto.productdto.ProductPrice;
import com.github.reservation.dto.reservationdto.ReservationParam;
import com.github.reservation.dto.reservationinfodto.ReservationDisplayInfo;
import com.github.reservation.dto.reservationinfodto.ReservationInfo;
import com.github.reservation.dto.reservationinfodto.ReservationInfoResponse;
import com.github.reservation.exception.ExceptionValue;
import com.github.reservation.exception.InvalidParameterException;
import com.github.reservation.service.DisplayInfoService;
import com.github.reservation.service.ProductService;
import com.github.reservation.service.ReservationService;
import com.github.reservation.util.Validator;

/**
*
* @description : Reservation API Controller
* @package : com.nts.controller.api.reservation
* @filename : ProductApiController.java
* @author : 최석현
* @method : ProductResponse products(int categoryId, int start)
* 
*/
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	@Autowired
	private ReservationService reservationService;

	@Autowired
	private DisplayInfoService displayInfoService;
	
	@Autowired
	private ProductService productService;

	/**
	 * @description : Session에 있는 email을 이용해 ReservationInfo 가져온 후 return
	 */
	@GetMapping
	public ReservationInfoResponse reservationInfo(HttpSession session) {
		
		String reservationEmail = (String)session.getAttribute("reservationEmail");
		
		Validator.emailValidation(reservationEmail);
		
		List<ReservationInfo> reservationInfos = reservationService.getReservationInfosByReservationEmail(reservationEmail);
		
		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
		reservationInfoResponse.setReservations(reservationInfos);
		
		return reservationInfoResponse;
	}
	
	@GetMapping("/{displayInfoId}")
	public ReservationDisplayInfo reservationDisplayInfo(@PathVariable(name = "displayInfoId") int displayInfoId) {
		
		if (displayInfoId <= 0) {
			throw new InvalidParameterException("displayInfoId", new ExceptionValue<Integer>(displayInfoId));
		}
		
		DisplayInfo displayInfo = displayInfoService.getDisplayInfoByDisplayInfoId(displayInfoId);
		List<ProductPrice> reservationPrices = productService.getProductPricesByDisplayInfoId(displayInfoId);
		String productImageUrl = displayInfoService.getProductImageUrlByDisplayInfoId(displayInfoId);
		
		ReservationDisplayInfo reservationDisplayInfo = new ReservationDisplayInfo();
		reservationDisplayInfo.setDisplayInfo(displayInfo);
		reservationDisplayInfo.setReservationPrices(reservationPrices);
		reservationDisplayInfo.setProductImageUrl(productImageUrl);
		
		return reservationDisplayInfo;
	}
	
	/**
	 * @description : Session에 Email 정보가 없을 시 bookinglogin.js을 통해 접근됨
	 */
	@GetMapping("/login")
	public String login(@RequestParam(name = "reservationEmail") String reservationEmail, HttpSession session) {
		
		Validator.emailValidation(reservationEmail);
			
		session.setAttribute("reservationEmail", reservationEmail);
		session.setMaxInactiveInterval(30 * 60);
		return reservationEmail;
		
	}
	
	/**
	 * @description : JSON 형태의 RequestBody를 Insert
	 */
	@PostMapping("/reservation")
	public int reservation(@RequestBody ReservationParam reservationParam) {
		Validator.emailValidation(reservationParam.getReservation().getReservationEmail());
		Validator.nameValidation(reservationParam.getReservation().getReservationName());
		Validator.telValidation(reservationParam.getReservation().getReservationTel());
			
		return reservationService.addReservation(reservationParam);
	}
	
	@PutMapping("/{reservationInfoId}")
	public void reservationCancel(@PathVariable(name = "reservationInfoId") int reservationInfoId) {
		if (reservationInfoId <= 0) {
			throw new InvalidParameterException("reservationInfoId", new ExceptionValue<Integer>(reservationInfoId));
		}
		reservationService.modifyCancelFlag(reservationInfoId, 1);
	}
}