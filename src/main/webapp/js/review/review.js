
/**
 * @description : displayInfo ajax 요청 
 */
function displayInfoRequest(callBack, displayInfoId){
	
	var request = {
			method:"GET",
			contentType:"application/x-www-form-urlencoded",
			data:""
	}
	var requestUri = "/api/products/" + displayInfoId;
	sendRequest(request, requestUri, callBack);
}

/**
 * @description : 모든 Comment Rendering 
 */
function renderComments(displayInfo){
	var comments = getComments(displayInfo);
	comments.render();
}

/**
 * @description : Review page load 후 ajax요청
 */
document.addEventListener("DOMContentLoaded", function() {
	var displayInfoId = document.location.search.substr(4);
	displayInfoRequest(renderComments, displayInfoId);
});
