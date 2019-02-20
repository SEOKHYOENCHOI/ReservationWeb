package com.github.reservation.dao.commentdao;


/**
*
* @description : CommentDaoSqls
* @package : com.nts.dao.commentdao
* @filename : CommentDaoSqls.java
* @author : 최석현
* 
*/
public class CommentDaoSqls {
	public static final String SELECT_AVERAGE_SCORE_BY_DISPLAY_INFO_ID = "SELECT "
																			+ "IFNULL( ROUND( AVG(ruc.score), 1), '0.0') as average_score "
																	   + "FROM "
																			+ "  reservation_user_comment ruc "
																			+ ", display_info di "
																	   + "WHERE "
																			+ "    di.id = :displayInfoId "
																			+ "and di.product_id = ruc.product_id ";
	
	public static final String SELECT_COMMENT_IMAGES_BY_DISPLAY_INFO_ID = " SELECT "
																				+ "  fi.content_type "
																				+ ", fi.create_date "
																				+ ", fi.delete_flag "
																				+ ", fi.file_name "
																				+ ", fi.modify_date "
																				+ ", fi.save_file_name as product_image_url "
																				+ ", ruci.file_id "
																				+ ", ruci.id "
																				+ ", ruci.reservation_info_id "
																				+ ", ruci.reservation_user_comment_id "
																		  + "FROM "
																				+ "  reservation_user_comment_image ruci "
																				+ ", file_info fi "
																		  + "WHERE "
																				+ "    ruci.reservation_user_comment_id = :reservationUserCommentId "
																				+ "and ruci.file_id = fi.id "
																		  + "LIMIT 1";
			
	
	public static final String SELECT_COMMENTS_BY_DISPLAY_INFO_ID = "SELECT "
																	+ "  ruc.comment "
																	+ ", ruc.id "
																	+ ", ri.create_date "
																	+ ", ri.modify_date "
																	+ ", ri.product_id "
																	+ ", ri.reservation_date "
																	+ ", ri.reservation_email "
																	+ ", ruc.reservation_info_id "
																	+ ", ri.reservation_name "
																	+ ", ri.reservation_tel "
																	+ ", ruc.score "
																  + "FROM "
																  	+ "  reservation_user_comment ruc "
																  	+ ", reservation_info ri "
																  + "WHERE "
																  	+ "    ri.display_info_id = :displayInfoId "
																  	+ "and ri.product_id = ruc.product_id "
																  	+ "and ri.id = ruc.reservation_info_id ";



}
