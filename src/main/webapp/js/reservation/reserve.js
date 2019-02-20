
/**
 * @description : reserve Page의 DisplayInfo 요청
 * 
 */
function reservationDisplayInfoRequest(callBack, displayInfoId){
	
	var request = {
			method:"GET",
			contentType:"application/x-www-form-urlencoded",
			queryString:""
	}
	var requestUri = "/api/reservations/" + displayInfoId;
	sendRequest(request, requestUri, callBack);
}

/**
 * @description : SubmitButton 클릭 시 실행
 * 
 */
function reservationSubmitRequest(callBack, reservationParam){
	
	var request = {
			method:"POST",
			contentType:"application/json",
			queryString: JSON.stringify(reservationParam)
	}
	var requestUri = "/api/reservations/reservation";
	sendRequest(request, requestUri, callBack);
}

/**
 * @description : 성공적으로 Submit 됐다면 메인페이지로 이동
 * 
 */
function reservationSucceed(reservationResult){
	if(reservationResult){
		location = "/";
	}
}


function reservationDisplayInfoInit(reservationDisplayInfo){
	var reservationDisplayInfoObject = getReservationDisplayInfoObj(reservationDisplayInfo);
	reservationDisplayInfoObject.init();
	reservationDisplayInfoObject.render();
	
	document.addEventListener("change",()=>{
		validateReservationDisplayInfo(reservationDisplayInfoObject);
	});
	document.addEventListener("click",()=>{
		validateReservationDisplayInfo(reservationDisplayInfoObject);
	});
}

/**
 * @description : document에 change, click Event 발생 시 전체 요소 Validation 후 버튼 활성/비활성화
 * 
 */
function validateReservationDisplayInfo(reservationDisplayInfoObject){
	if(reservationDisplayInfoObject.validation()){
		reservationDisplayInfoObject.reservationButton.enable();
	} else{
		reservationDisplayInfoObject.reservationButton.disable();
	}
}

document.addEventListener("DOMContentLoaded", ()=>{
	var displayInfoId = document.location.search.split("=")[1];
	reservationDisplayInfoRequest(reservationDisplayInfoInit, displayInfoId);
});