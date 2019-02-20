package com.github.reservation.dao.displayinfodao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.reservation.dto.displayinfodto.DisplayInfo;
import com.github.reservation.dto.displayinfodto.DisplayInfoImage;

import static com.github.reservation.dao.displayinfodao.DisplayInfoDaoSqls.*;

import java.util.Collections;
import java.util.Map;


/**
*
* @description : DisplayInfoDao
* @package : com.nts.dao.displayinfodao
* @filename : DisplayInfoDao.java
* @author : 최석현
* @method : DisplayInfo selectDisplayInfoByDisplayInfoId(int displayInfoId)
* @method : DisplayInfoImage selectDisplayInfoImageByDisplayInfoId(int displayInfoId)
* 
*/
@Repository
public class DisplayInfoDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImageRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
	public DisplayInfo selectDisplayInfoByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID, param, displayInfoRowMapper);
	}

	public DisplayInfoImage selectDisplayInfoImageByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID, param, displayInfoImageRowMapper);
	}

	public String selectProductImageUrlByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_PRODUCT_IMAGE_URL_BY_DISPLAY_INFO_ID, param, String.class);
	}

}
