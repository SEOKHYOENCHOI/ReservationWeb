package com.github.reservation.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.reservation.dao.commentdao.CommentDao;
import com.github.reservation.dto.commentdto.Comment;
import com.github.reservation.dto.commentdto.CommentImage;
import com.github.reservation.exception.ExceptionValue;
import com.github.reservation.exception.InvalidParameterException;
import com.github.reservation.service.CommentService;


/**
 * 
 *
 * @description : Comment Service
 * @package : com.nts.service.impl
 * @filename : CommentServiceImpl.java
 * @author : 최석현
 * @method :double getAverageScoreByDisplayInfoId(int displayInfoId)
 * @method :List<Comment> getCommentsByDisplayInfoId(int displayInfoId)
 * @method :CommentImage getCommentImageByReservationUserCommentId(int reservationUserCommentId)
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	private static final String IMAGE_URL_SUFFIX = "img/";

	/**
	 * @description : displayInfoId 값을 검증 후 Dao로부터 AverageScore를 받음
	 * @throws : InvalidParameterException
	 */
	@Override
	public double getAverageScoreByDisplayInfoId(int displayInfoId) throws InvalidParameterException {

		if (displayInfoId <= 0) {

			throw new InvalidParameterException("displayInfoId", new ExceptionValue<Integer>(displayInfoId));

		}

		return commentDao.selectAverageScoreByDisplayInfoId(displayInfoId);
	}

	/**
	 * @description : displayInfoId 값을 검증 후 Dao로부터 CommentImage를 받음
	 * @throws : InvalidParameterException
	 */
	@Override
	public CommentImage getCommentImageByReservationUserCommentId(int reservationUserCommentId) {

		if (reservationUserCommentId <= 0) {

			throw new InvalidParameterException("reservationUserCommentId",
					new ExceptionValue<Integer>(reservationUserCommentId));

		}

		return commentDao.selectCommentImageByReservationUserCommentId(reservationUserCommentId);
	}

	/**
	 * @description : displayInfoId 값을 검증 후 Dao로부터 List를 받은 후 comment id에 맞게 image
	 *              넣어줌
	 * @throws : InvalidParameterException
	 */
	@Override
	public List<Comment> getCommentsByDisplayInfoId(int displayInfoId) {

		if (displayInfoId <= 0) {

			throw new InvalidParameterException("displayInfoId", new ExceptionValue<Integer>(displayInfoId));

		}

		List<Comment> comments = commentDao.selectCommentsByDisplayInfoId(displayInfoId);

		for (Comment comment : comments) {

			try {

				comment.setCommentImages(getCommentImageByReservationUserCommentId(comment.getId()));

			} catch (EmptyResultDataAccessException e) {

				comment.setCommentImages(new CommentImage());

			}
		}
		return comments;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = SQLException.class)
	public void addComment(Comment comment, MultipartFile reservationImage, String imagePath)
			throws FileNotFoundException, IOException {
		
		int reservationUserCommentId = commentDao.insertComment(comment);
		
		if (reservationImage != null && !reservationImage.isEmpty()) {
			String fileName = reservationImage.getOriginalFilename();
			File file = new File(imagePath + IMAGE_URL_SUFFIX, fileName);
			IOUtils.copy(reservationImage.getInputStream(), new FileOutputStream(file));

			CommentImage commentImage = new CommentImage();
			commentImage.setReservationInfoId(comment.getReservationInfoId());
			commentImage.setReservationUserCommentId(reservationUserCommentId);
			commentImage.setFileName(fileName);
			commentImage.setProductImageUrl(IMAGE_URL_SUFFIX + fileName);
			commentImage.setContentType(reservationImage.getContentType());
			commentImage.setDeleteFlag(false);

			addCommentImage(commentImage, reservationImage);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = SQLException.class)
	public void addCommentImage(CommentImage commentImage, MultipartFile reservationImage) {
		
		int fileInfoId = commentDao.insertFileInfo(commentImage);

		commentDao.insertCommentImage(
				commentImage.getReservationInfoId(),
				commentImage.getReservationUserCommentId(),
				fileInfoId);
	}
}
