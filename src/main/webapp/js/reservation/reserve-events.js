function submitButtonDown(){
	var name = document.querySelector("#name").value;
	var email = document.querySelector("#email").value;
	var tel = document.querySelector("#tel").value;
	var productId = document.querySelector(".img_thumb").src.split("img/")[1].split("_")[0];
	var displayInfoId = location.search.split("=")[1];
	var reservationPrices = [];
	
	document.querySelectorAll(".count_control_input").forEach((v)=>{
		if(v.value > 0){
			var reservationPrice = {
					productPriceId: parseInt(v.name.substr(14)),
					count: v.value
			}
			reservationPrices.push(reservationPrice);
		}
	});
	
	var reservationParam = {
			reservation:{
				reservationName: name,
				reservationEmail: email,
				reservationTel: tel,
				productId: productId,
				displayInfoId: displayInfoId,
				cancelFlag: 0
			},
			prices: reservationPrices
	}
	reservationSubmitRequest(reservationSucceed, reservationParam);
	
}

function minusButtonDown(event){
	var totalCount = document.querySelector("#total_count");
	var ticketElement = event.target.nextElementSibling;
	var ticketNum = 0;
	
	if(ticketElement.value > 0){
		ticketNum = -1;
		ticketElement.value = parseInt(ticketElement.value) - 1;
		
		var priceElement = event.target.parentElement.nextElementSibling.firstElementChild;

		var discountedPrice = event.target.parentElement.firstElementChild.innerText;
		discountedPrice = parseInt(discountedPrice.split(",").join(""));
		
		var currentPrice = parseInt(priceElement.innerText.split(",").join(""));
		priceElement.innerText = addComma(String(currentPrice - discountedPrice));
		

		totalCount.innerText = parseInt(totalCount.innerText) - 1;
		
		if(ticketElement.value == 0){
			event.target.className = "btn_plus_minus spr_book2 ico_minus3 disabled btn_minus";
			ticketElement.className = "count_control_input disabled";
		}
	}
	
	return ticketNum;
}

function plusButtonDown(event){
	var totalCount = document.querySelector("#total_count");
	
	var ticketElement = event.target.previousElementSibling;
	var priceElement = event.target.parentElement.nextElementSibling.firstElementChild;

	var discountedPrice = event.target.parentElement.firstElementChild.innerText;
	discountedPrice = parseInt(discountedPrice.split(",").join(""));
	
	var currentPrice = parseInt(priceElement.innerText.split(",").join(""));
	priceElement.innerText = addComma(String(currentPrice + discountedPrice));
	
	if(ticketElement.value == 0){
		event.target.previousElementSibling.previousElementSibling.className = "btn_plus_minus spr_book2 ico_minus3 btn_minus";
		ticketElement.className = "count_control_input";
	}

	totalCount.innerText = parseInt(totalCount.innerText) + 1;
	ticketElement.value = parseInt(ticketElement.value) + 1;
	
	return 1;
}

function agreementCollectingButtonDown(){
	var agreementButton = document.querySelector("#agreement_collecting_btn");
	if(agreementButton.parentElement.className.includes("open")){
		agreementButton.parentElement.className = "agreement";
		agreementButton.firstElementChild.className = "보기";
		agreementButton.firstElementChild.nextElementSibling.className = "fn fn-down2";
	} else{
		agreementButton.parentElement.className = "agreement open";
		agreementButton.firstElementChild.className = "접기";
		agreementButton.firstElementChild.nextElementSibling.className = "fn fn-up2";
	}
}

function agreementProvidingButtonDown(){
	var agreementButton = document.querySelector("#agreement_providing_btn");
	if(agreementButton.parentElement.className.includes("open")){
		agreementButton.parentElement.className = "agreement";
		agreementButton.firstElementChild.className = "보기";
		agreementButton.firstElementChild.nextElementSibling.className = "fn fn-down2";
	} else{
		agreementButton.parentElement.className = "agreement open";
		agreementButton.firstElementChild.className = "접기";
		agreementButton.firstElementChild.nextElementSibling.className = "fn fn-up2";
	}
}

function validateName(event){
	return (/[가-힣]{2}/).test(event.target.value);
}


function validateTel(event){
	return (/01(([16789]-(\d{3,4}))|0-\d{4})-\d{4}$/).test(event.target.value);

}

function validateEmail(event){
	return (/^[\w+_]\w+@\w+\.\w+(\.\w+)?$/).test(event.target.value);

}
