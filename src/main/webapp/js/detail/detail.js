
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
 * @description : detail page initialization
 */
function detailsInit(displayInfo){
	var displayInfoObject = getDisplayInfoObj(displayInfo, 3);
	displayInfoObject.init();
	displayInfoObject.render();
	
	document.querySelector("#reservation_button").addEventListener("click", ()=>{
		reservationButtonDown(displayInfo);
	});
}

document.addEventListener("DOMContentLoaded", function() {
	var displayInfoId = document.location.search.substr(4);
	displayInfoRequest(detailsInit, displayInfoId);
});