package com.github.reservation.dao.displayinfodao;


/**
*
* @description : DisplayInfoDaoSqls
* @package : com.nts.dao.displayinfodao
* @filename : DisplayInfoDaoSqls.java
* @author : 최석현
* 
*/
public class DisplayInfoDaoSqls {
	public static final String SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID = "SELECT "
																			+ "  display_info.id"
																			+ ", category.name as category_name "
																			+ ", category.id as category_id "
																			+ ", product.id as product_id "
																			+ ", display_info.opening_hours "
																			+ ", display_info.place_name "
																			+ ", display_info.place_lot "
																			+ ", display_info.place_street "
																			+ ", display_info.tel "
																			+ ", display_info.homepage "
																			+ ", display_info.email "
																			+ ", product.content "
																			+ ", product.event "
																			+ ", product.description "
																			+ ", product.create_date "
																			+ ", product.modify_date "
																		+ "FROM "
																			+ "  display_info "
																			+ ", product "
																			+ ", category "
																		+ "WHERE "
																			+ "    display_info.id = :displayInfoId "
																			+ "and display_info.product_id = product.id "
																			+ "and category.id = product.category_id ";
	
	public static final String SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID = "SELECT "
																				+ "  file_info.content_type "
																				+ ", file_info.create_date "
																				+ ", file_info.delete_flag "
																				+ ", display_info.id as display_info_id "
																				+ ", display_info_image.id as display_info_image_id "
																				+ ", file_info.id as file_id "
																				+ ", file_info.file_name "
																				+ ", file_info.modify_date "
																				+ ", file_info.save_file_name as product_image_url "
																			+ "FROM "
																				+ "  display_info "
																				+ ", display_info_image "
																				+ ", file_info "
																			+ "WHERE "
																				+ "    display_info_id = :displayInfoId "
																				+ "and display_info_image.display_info_id = display_info.id "
																				+ "and display_info_image.file_id = file_info.id ";

	static final String SELECT_PRODUCT_IMAGE_URL_BY_DISPLAY_INFO_ID = "SELECT "
																	+ "  fi.save_file_name as product_image_url "
																	+ "FROM"
																	+ "  display_info di "
																	+ ", file_info fi "
																	+ ", product p "
																	+ ", product_image pimg "
																	+ "WHERE "
																	+ "    di.id = :displayInfoId "
																	+ "and di.product_id = p.id "
																	+ "and pimg.product_id = p.id "
																	+ "and pimg.file_id = fi.id "
																	+ "and pimg.type = 'th' ";
}
