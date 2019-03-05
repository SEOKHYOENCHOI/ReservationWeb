<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>네이버 예약</title>
	<link href="../css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				  <c:choose>
                	<c:when test="${!empty sessionScope.reservationEmail}">
                		<a href="/myreservation" class="btn_my">
                			<span class="viewReservation" title="예약확인">
                				${sessionScope.reservationEmail}
                			</span>
               			</a>
                	</c:when>
                	<c:otherwise>
                		<a href="/bookinglogin" class="btn_my">
                			<span class="viewReservation" title="예약확인">
                				예약확인
                			</span>
               			</a>
                	</c:otherwise>
                </c:choose>
			</header>
		</div>
		<div class="ct">
			<div class="ct_wrap">
				<div class="top_title review_header">
					<a href="/myreservation" class="btn_back" title="이전 화면으로 이동"> 
						<i class="fn fn-backward1"></i> 
					</a>
					<h2>
						<span class="title" id="review_title"></span>
					</h2>
				</div>
				<!-- 리뷰 별점 -->
				<div class="write_act">
					<p class="title_star">별점과 이용경험을 남겨주세요.</p>
					<div class="review_rating rating_point">
						<div class="rating">
							<!-- [D] 해당 별점이 선택될 때 그 점수 이하의 input엘리먼트에 checked 클래스 추가 -->
							<input type="checkbox" name="rating2" value="1" checked class="rating_rdo" title="1점">
							<span class="span"></span>
							<input type="checkbox" name="rating3" value="2" checked class="rating_rdo" title="2점">
							<span class="span"></span>
							<input type="checkbox" name="rating4" value="3" checked class="rating_rdo" title="3점" >
							<span class="span"></span>
							<input type="checkbox" name="rating5" value="4" checked class="rating_rdo" title="4점">
							<span class="span"></span>
							<input type="checkbox" name="rating6" value="5" checked class="rating_rdo" title="5점">
							<span class="span"></span>
							<!-- [D] 0점일 때 gray_star 추기 -->
							<span class="star_rank" id="star_rank">5</span>
						</div>
					</div>
				</div>
				<!-- //리뷰 별점 -->

				<!-- 리뷰 입력 -->
				<div class="review_contents write">
					<!-- [D] review_write_info 클릭 시 자신을 숨기고 review_textarea 에 focus를 보낸다. -->
					<a href="javascript:void(0)" class="review_write_info" id="review_comment_placeholder">
						<span class="middot">
							실 사용자의 리뷰는 상품명의 더 나은 서비스 제공과 다른 사용자들의 선택에 큰 도움이 됩니다.
						</span><br>
						<span class="middot">
							소중한 리뷰에 대한 감사로 네이버페이 포인트 500원을 적립해드립니다.
						</span>
						<span class="left_space">(단, 리뷰 포인트는 ID 당 1일 최대 5건까지 지급됩니다.)</span>
					</a>
					<textarea cols="30" rows="10" class="review_textarea" id="review_comment"></textarea>
				</div>
				<!-- //리뷰 입력 -->

				<!-- 리뷰 작성 푸터 -->
				<div class="review_write_footer_wrap">
					<div class="review_write_footer">
							<form id="attach_image_form" method="post" enctype="multipart/form-data">
								<label class="btn_upload" for="reviewImageFileOpenInput">
									<i class="fn fn-image1" aria-hidden="true"></i>
									<span class="text_add_photo">사진 추가</span>
									<p style="color:#999; font-size:13px">JPG, PNG 파일만 첨부가능합니다.</p>
								</label>
							</form>
							<input type='hidden' name='redirect_to' value='/' />
							<input type="file" name="reservationImage" class="hidden_input" id="reviewImageFileOpenInput">
						<div class="guide_review">
							<span id="comment_length">0</span>/400
							<span>(최소 5자이상)</span>
						</div>
					</div>

					<!-- 리뷰 포토 -->
					<div class="review_photos review_photos_write">
						<div class="item_preview_thumbs">
							<ul class="lst_thumb">
								<li class="item hide" id="image_thumb_li">
									<a href="javascript:void(0)" class="anchor" id="delete_image">
										<span class="spr_book ico_del">삭제</span>
									</a>
									<img src="" width="130" alt="" class="item_thumb" id="image_thumb">
									<span class="img_border"></span>
								</li>
							</ul>
						</div>
					</div>
					<!-- //리뷰 포토 -->

				</div>
				<!-- //리뷰 작성 푸터 -->

				<!-- 리뷰 등록 -->
				<div class="box_bk_btn">
					<button class="bk_btn" id="submit_button">
						<span class="btn_txt">
							리뷰 등록
						</span>
					</button>
				</div>
				<!-- //리뷰 등록 -->
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top" onclick="window.scrollTo(0,0); document.querySelector('#container').focus(); return false;"> 
            	<span class="lnk_top_text">TOP</span> 
           	</a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<script type = "text/javascript" src="/js/common/ajax.js" charset="utf-8"></script>
	<script type = "text/javascript" src="/js/common/utils.js" charset="utf-8"></script>
	<script type = "text/javascript" src="/js/mapper/review_write-mapper.js" charset="utf-8"></script>
	<script type = "text/javascript" src="/js/review_write/review_write.js" charset="utf-8"></script>
</body>
</html>