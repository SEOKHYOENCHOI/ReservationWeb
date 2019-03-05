
var currentCount = 0;
var currentCategoryId = 0;
var pagingSize = 4;
var moreButton = document.querySelector("#more_button");

/**
 * @description : get method로 Product List를 요청
 */
function productListRequest(categoryId, start){
	var request = {
			method:"GET",
			contentType:"application/x-www-form-urlencoded",
			data:""
	}
	var uri = "/api/products" + "?categoryId=" + categoryId + "&start=" + start;
	sendRequest(request, uri, appendProductList);
}


/**
 * @description : 수신된 Product List를 HTML의 Tab UL에 추가
 */
function appendProductList(products){
	var productUl = document.querySelector("#wrap_lst_event_box").getElementsByTagName("UL");
	var appendProductHTML = document.querySelector("#itemList").innerText;
	
	var items = products.items;
	
	for(var i=0, len=items.length;i<len;i++){
		var li = replaceTemplateHTML(items[i], appendProductHTML);
		productUl.item(i%2).innerHTML += li;
	}
}

/**
 * @description : 더보기 버튼 Event Listener 추가
 */
function addMoreButtonListener(){
	
	moreButton.removeAttribute("style"); 
	moreButton.removeEventListener("click", moreButtonDown);
	
	if(parseInt(document.querySelector("#event_num").innerText) <= 4){
		
		moreButton.setAttribute("style", 'display:none');
		
	}else{
		
		moreButton.addEventListener("click", moreButtonDown, true);
		
	}
}

/**
 * @description : 더보기 버튼 Event
 */
function moreButtonDown(){
	productListRequest(currentCategoryId, currentCount);	
	
	if(currentCount + pagingSize < parseInt(document.querySelector("#event_num").innerText)){
		
		currentCount +=pagingSize;
			
	} else{
		
		moreButton.setAttribute("style", "display:none");
		
	}
	
}

function productInit(){
	
	productReset(0);
	addMoreButtonListener();
	
}

function productReset(categoryId){
	
	productListRequest(categoryId, 0);
	currentCategoryId = categoryId;
	currentCount = pagingSize;
	
	if(pagingSize < parseInt(document.querySelector("#event_num").innerText)){
		moreButton.removeAttribute("style");
	}
}