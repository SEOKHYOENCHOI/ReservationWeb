<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
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
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="../" class="lnk_logo" title="네이버"> 
						<span class="spr_bi ico_n_logo">네이버</span> 
					</a>
					<a href="../" class="lnk_logo" title="예약"> 
						<span class="spr_bi ico_bk_logo">예약</span> 
					</a>
				</h1>
				<a href="#" class="btn_my"> 
					<span title="내예약" class="viewReservation"></span> 
				</a>
			</header>
		</div>
		<hr>
		<div class="ct">
			<div class="section_my">
				<!-- 예약 현황 -->
				<div class="my_summary">
					<ul class="summary_board">
						<li class="item">
							<!--[D] 선택 후 .on 추가 link_summary_board -->
            				<a href="javascript:void(0)" class="link_summary_board on" id="reservation_list_button_all"> 
								<i class="spr_book2 ico_book2"></i> 
								<em class="tit">전체</em> 
								<span class="figure"></span>  
           	 				</a>
						</li>
						<li class="item">
							<a href="javascript:void(0)" class="link_summary_board" id="reservation_list_button_scheduled"> 
								<i class="spr_book2 ico_book2"></i> 
								<em class="tit">이용 예정</em> 
								<span class="figure"></span>  
           	 				</a>
						</li>
						<li class="item">
							<a href="javascript:void(0)" class="link_summary_board" id="reservation_list_button_used"> 
								<i class="spr_book2 ico_book2"></i> 
								<em class="tit">이용 완료</em> 
								<span class="figure"></span>  
           	 				</a>
						</li>
						<li class="item">
							<a href="javascript:void(0)" class="link_summary_board" id="reservation_list_button_canceled"> 
								<i class="spr_book2 ico_book2"></i> 
								<em class="tit">취소·환불</em> 
								<span class="figure"></span>  
           	 				</a>
						</li>
					</ul>
				</div>
				<!--// 예약 현황 -->

				<!-- 내 예약 리스트 -->
				<div class="wrap_mylist">
					<ul class="list_cards" ng-if="bookedLists.length > 0" id="reservation_ul">
						<!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
							
					</ul>
				</div>
			</div>
		</div>
		<hr>
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

		<!-- 취소 팝업 -->
		<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
		<div class="popup_booking_wrapper" style="display:none;">
			<div class="dimm_dark" style="display:block"></div>
			<div class="popup_booking refund">
				<h1 class="pop_tit">
					<span>서비스명/상품명</span>
					<small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
				</h1>
				<div class="nomember_alert">
					<p>취소하시겠습니까?</p>
				</div>
				<div class="pop_bottom_btnarea">
					<div class="btn_gray">
						<a href="#" class="btn_bottom"><span>아니오</span></a>
					</div>
					<div class="btn_green">
						<a href="#" class="btn_bottom"><span>예</span></a>
					</div>
				</div>
				<!-- 닫기 -->
				<a href="#" class="popup_btn_close" title="close">
					<i class="spr_book2 ico_cls"></i>
				</a>
				<!--// 닫기 -->
			</div>
		</div>
		<!--// 취소 팝업 -->
 	<script type="rv-template" id="nolist_div">
		<div class="err"> 
			<i class="spr_book ico_info_nolist"></i>
			<h1 class="tit">예약 리스트가 없습니다</h1>
		</div>
	</script>
 	<script type="rv-template" id="share_button">
		<a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
	</script>
 	<script type="rv-template" id="comment_button">
		<div class="booking_cancel">
			<button class="btn" name="_{name}">
				<span>예매자 리뷰 남기기</span>
			</button>
		</div>
	</script>
 	<script type="rv-template" id="cancel_button">
		<div class="booking_cancel">
			<button class="btn" name="_{name}">
				<span>취소</span>
			</button>
		</div>
	</script>
 	<script type="rv-template" id="reservation_info_body">
		<article class="card_item">
			<div class="link_booking_details">
				<div class="card_body">
					<div class="left"></div>
					<div class="middle">
						_{reservationInfo}
					</div>
					<div class="right"></div>
				</div>
				<div class="card_footer">
					<div class="left"></div>
					<div class="middle"></div>
					<div class="right"></div>
				</div>
			</div>
			_{shareButton}
		</article>
	</script>
	<script type="rv-template" id="reservation_list">
		<li class="card _{reservationClassType}" id="_{reservationType}_list">
			<div class="link_booking_details">
				<div class="card_header">
					<div class="left"></div>
					<div class="middle">
						<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
						<i class="spr_book2 _{reservationIconType}"></i>
						<span class="tit">_{reservationTypeKor}</span>
					</div>
					<div class="right"></div>
				</div>
			</div> 
			_{reservationInfo}
		</li>
	</script>
 	<script type="rv-template" id="reservation_info">
 		<div class="card_detail">
			<em class="booking_number">
				No._{reservationId}
			</em>
			<h4 class="tit">
				_{description}
			</h4>
			<ul class="detail">
				<li class="item">
					<span class="item_tit">일정</span>
					<em class="item_dsc">
						_{reservationDate}
					</em>
				</li>
				<li class="item">
					<span class="item_tit">내역</span>
					<em class="item_dsc">
						_{reservationDetails}
					</em>
				</li>
				<li class="item">
					<span class="item_tit">장소</span>
					<em class="item_dsc">
						_{placeName}
					</em>
				</li>
			</ul>
			<div class="price_summary">
				<span class="price_tit">결제 예정금액</span>
				<em class="price_amount">
					<span>
						_{priceSum}
					</span>
					<span class="unit">원</span>
				</em>
			</div>
			_{cancelButton}
		</div>
	</script>
    <script type = "text/javascript" src="/js/common/ajax.js" charset="utf-8"></script>
    <script type = "text/javascript" src="/js/common/utils.js" charset="utf-8"></script>
    <script type = "text/javascript" src="/js/common/typeconvert-maps.js" charset="utf-8"></script>
    <script type = "text/javascript" src="/js/mapper/myreservation-mapper.js" charset="utf-8"></script>
	<script type = "text/javascript" src="/js/reservationconfirm/myreservation.js" charset="utf-8"></script>
	<script type = "text/javascript" src="/js/reservationconfirm/myreservation-events.js" charset="utf-8"></script>
	</body>

	</html>