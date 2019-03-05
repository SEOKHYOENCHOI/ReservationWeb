/**
 * @description : myreservation Page Ajax 요청
 */

function myreservationRequest(callBack, emailParam){
	
	var request = {
			method:"GET",
			contentType:"application/x-www-form-urlencoded",
			data:""
	}
	var requestUri = "/api/reservations?" + emailParam;
	sendRequest(request, requestUri, callBack);
}

function reservationCancelRequest(reservationInfoId){
	var request = {
			method:"PUT",
			contentType:"application/x-www-form-urlencoded",
			data:""
	}
	var requestUri = "/api/reservations/" + reservationInfoId;
	sendRequest(request, requestUri);
}

function myreservationInit(reservationInfo){
	reservationInfoObject = getReservationInfoObj(reservationInfo);
	reservationInfoObject.init();
	reservationInfoObject.render();
}

document.addEventListener("DOMContentLoaded", function() {
	var emailParam = document.location.search.split("?")[1];
	myreservationRequest(myreservationInit, emailParam)
});