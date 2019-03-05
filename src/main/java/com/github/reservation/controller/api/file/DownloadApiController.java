/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.controller.api.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @description : 요청된 URL에 대한 Image를 전송하는 API
 * @package : com.nts.controller.api.file
 * @filename : DownloadApiController.java
 * @author : 최석현
 * @method :ResponseEntity<byte[]> getImageAsResponseEntity(String productImageUrl)
 */
@RestController
@RequestMapping(path = "/api/download")
@PropertySource({"classpath:application.properties"})
public class DownloadApiController {

	@Value("${imagepath}")
	private String imagePath;
	
	@GetMapping
	public ResponseEntity<byte[]> getImageAsResponseEntity(
			@RequestParam(name = "productImageUrl") String productImageUrl)
			throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		
		File file = new File(imagePath + productImageUrl);
		byte[] media = IOUtils.toByteArray(new FileInputStream(file));

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}
}