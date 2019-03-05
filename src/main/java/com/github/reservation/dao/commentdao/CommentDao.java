package com.github.reservation.dao.commentdao;

import static com.github.reservation.dao.commentdao.CommentDaoSqls.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.github.reservation.dto.commentdto.Comment;
import com.github.reservation.dto.commentdto.CommentImage;

/**
*
* @description : CommentDao
* @package : com.nts.dao.commentdao
* @filename : CommentDao.java
* @author : 최석현
* @method : List<Comment> selectCommentsByDisplayInfoId(int displayInfoId)
* @method : List<CommentImage> selectCommentImagesByDisplayInfoId(int displayInfoId)
* @method : double selectAverageScoreByDisplayInfoId(int displayInfoId)
* 
*/
@Repository
public class CommentDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

	public List<Comment> selectCommentsByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_COMMENTS_BY_DISPLAY_INFO_ID, param, commentRowMapper);
	}

	public CommentImage selectCommentImageByReservationUserCommentId(int reservationUserCommentId) {
		Map<String, ?> param = Collections.singletonMap("reservationUserCommentId", reservationUserCommentId);
		return jdbc.queryForObject(SELECT_COMMENT_IMAGES_BY_RESERVATION_USER_INFO_ID, param, commentImageRowMapper);
	}

	public double selectAverageScoreByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_AVERAGE_SCORE_BY_DISPLAY_INFO_ID, param, Double.class);
	}

	public int insertComment(Comment comment) {
		
		KeyHolder generatedKey = new GeneratedKeyHolder();
		
		SqlParameterSource source = new MapSqlParameterSource().addValue("productId", comment.getProductId())
				.addValue("reservationInfoId", comment.getReservationInfoId()).addValue("score", comment.getScore())
				.addValue("comment", comment.getComment());
		
		jdbc.update(INSERT_COMMENT, source, generatedKey);
		
		return generatedKey.getKey().intValue();
	}

	public int insertFileInfo(CommentImage commentImage) {
		
		KeyHolder generatedKey = new GeneratedKeyHolder();
		
		SqlParameterSource source = new MapSqlParameterSource().addValue("fileName", commentImage.getFileName())
				.addValue("saveFileName", commentImage.getProductImageUrl())
				.addValue("contentType", commentImage.getContentType())
				.addValue("deleteFlag", commentImage.isDeleteFlag());

		jdbc.update(INSERT_FILE_INFO, source, generatedKey);
		
		return generatedKey.getKey().intValue();
	}

	public int insertCommentImage(int reservationInfoId, int reservationUserCommentId, int fileId) {
		SqlParameterSource source = new MapSqlParameterSource().addValue("reservationInfoId", reservationInfoId)
				.addValue("reservationUserCommentId", reservationUserCommentId).addValue("fileId", fileId);

		return jdbc.update(INSERT_COMMENT_IMAGE, source);
	}
}
