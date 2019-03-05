function loginRequest(callBack, emailParam){
	
	var request = {
			method:"GET",
			contentType:"application/x-www-form-urlencoded",
			data:""
	}
	var requestUri = "/api/reservations/login?" + emailParam;
	sendRequest(request, requestUri, callBack);
}

function redirectToMyReservation(reservationEmail){
	location = location.origin + "/myreservation?reservationEmail=" + reservationEmail;
}

/**
 * @description : clickEvent발생 시 검증 후 LoginRequest / WarningMessage
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
	var submitButton = document.querySelector("#email_submit");
	
	submitButton.addEventListener("click", function(event) {
		event.preventDefault();
	    var emailInput = document.querySelector("#resrv_id");
	    var emailValidation = (/^[\w+_]\w+@\w+\.\w+(\.\w+)?$/).test(emailInput.value);
	    
	    if(!emailValidation)  {
	    	setWarning("email", emailInput);
	    } else {
	    	loginRequest(redirectToMyReservation, "reservationEmail="+emailInput.value);
		}
	});
});


