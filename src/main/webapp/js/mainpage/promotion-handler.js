/**
 * @description : get method로 Promotion List를 요청
 */
function promotionListRequest(){
	var request = {
			method:"GET",
			contentType:"application/x-www-form-urlencoded",
			data:""
	}
	var uri = "/api/promotions";
	sendRequest(request, uri, appendPromotionList);
}

/**
 * @description : 수신된 Promotion List를 HTML의 Promotion UL에 추가
 */
function appendPromotionList(promotions){
	var promotionUl = document.querySelector("#promotion_ul");
	var appendPromotionListHTML = document.querySelector("#promotionItem").innerText;
	
	var items = promotions.items;
	
	for(var i=0, len=items.length;i<len;i++){
		var li = replaceTemplateHTML(items[i], appendPromotionListHTML);
		promotionUl.innerHTML += li;
	}
	setRightMoveAnimation("#promotion_ul", 414, 1000);
}

