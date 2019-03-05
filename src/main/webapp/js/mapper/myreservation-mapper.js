/**
 * @description : reserve 전체에 대한 Object
 * 
 * 				  각 Object는 ReservationInfo로부터 Map을 만든 후
 * 				  js.common.utils의 replaceTemplateHTML을 통해 변환된 HTML을 삽입한다.
 * 
 */

function getReservationDisplayInfoObj(reservationDisplayInfo){
	var reservationDisplayInfoObject = {
			init: function(){
				this.reservationDisplayInfo = getReservationDisplayInfo(reservationDisplayInfo);
				this.tickets = getTickets(reservationDisplayInfo.reservationPrices);
				this.userInfo = getUserInfo();
				this.agreement = getAgreement();
				this.reservationButton = getReservationButton();
			},
			render: function(){
				this.reservationDisplayInfo.init();
				this.tickets.init();
				this.userInfo.init();
				this.agreement.init();
				this.reservationButton.init();
			},
			validation: function(){
				return (this.tickets.validation()
						&& this.userInfo.validation() 
						&& this.agreement.validation());
			}
	}
	
	return reservationDisplayInfoObject;
}

function getReservationDisplayInfo(reservationDisplayInfo){
	var reservationDisplayInfoObj = {
			init: function(){
				this.render();
			},
			render: function(){
				var reservationDiplayInfoMap = getReservationDisplayInfoMap(reservationDisplayInfo);
				var reservationDiplayInfoHTML = document.querySelector("#reserve_display_info").innerHTML;
				var reservationDisplayInfoBody = document.querySelector("#reservation_body");
				
				reservationDisplayInfoHTML = replaceTemplateHTML(reservationDiplayInfoMap, reservationDiplayInfoHTML);	
				reservationDisplayInfoBody.insertAdjacentHTML("afterBegin", reservationDisplayInfoHTML);
			}
	}
	return reservationDisplayInfoObj;
}

function getTickets(reservationPrices){
	var tickets = {
			init: function(){
				this.ticketNum = 0;
				this.render();
				this.bindEvents();
			},
			render: function(){
				for(var i=0,len=reservationPrices.length; i<len; i++){
					var ticketMap = getTicketMap(reservationPrices[i]);
					var ticketHTML = document.querySelector("#reserve_choose_tickets").innerHTML;
					var ticketBody = document.querySelector("#ticket_body");
					
					ticketHTML = replaceTemplateHTML(ticketMap, ticketHTML);	
					ticketBody.innerHTML += ticketHTML;
				}
			},
			bindEvents: function(){
				document.querySelectorAll(".btn_minus").forEach((v)=>{
					v.addEventListener("click", (event)=>{
						this.ticketNum += minusButtonDown(event)
					});
				});
				document.querySelectorAll(".btn_plus").forEach((v)=>{
					v.addEventListener("click", (event)=>{
						this.ticketNum += plusButtonDown(event)
					});
				});
			},
			validation: function(){
				return (this.ticketNum > 0) 
			}
	}
	return tickets;
}

function getUserInfo(){
	var userInfo = {
			init: function(){
				this.render();
				this.bindEvents();
			},
			render: function(){
				var nowDate = new Date();
				nowDate = nowDate.toISOString().substr(0, 10).split("-").join(".");
				document.querySelector("#now_date").innerText = nowDate;
			},
			bindEvents: function(){
				document.querySelector("#name").addEventListener("change", (event)=>{
					this.nameValidation = validateName(event)
				});
				
				document.querySelector("#tel").addEventListener("change", (event)=>{
					this.telValidation = validateTel(event);
				});
				document.querySelector("#email").addEventListener("change", (event)=>{
					this.emailValidation = validateEmail(event);
				});
			},
			validation: function(){
				return (this.nameValidation && this.emailValidation && this.telValidation)
			}
	}
	return userInfo;
}

function getAgreement(){
	var agreement = {
			init: function(){
				this.checkValidation = false;
				this.bindEvents();
			},
			bindEvents: function(){
				var agreementCollectingButton = document.querySelector("#agreement_collecting_btn");
				agreementCollectingButton.addEventListener("click", agreementCollectingButtonDown);
				var agreementProvidingButton = document.querySelector("#agreement_providing_btn");
				agreementProvidingButton.addEventListener("click", agreementProvidingButtonDown);
				
				var agreementCheckButton = document.querySelector("#agreement_check_button");
				agreementCheckButton.addEventListener("click", ()=>{
					this.checkValidation = !this.checkValidation;
				});
			},
			validation: function(){
				return this.checkValidation;
			}
	}
	return agreement;
}

function getReservationButton(){
	var reservationButton = {
			init:function(){
				this.submitButton = document.querySelector("#submit_button");
			},
			enable: function(){
				this.submitButton.parentElement.className = "bk_btn_wrap";
				this.bindEvents();
			},
			disable:function(){
				this.submitButton.parentElement.className = "bk_btn_wrap disable";
				this.unbindEvents();
			},
			bindEvents(){
				this.submitButton.addEventListener("click", submitButtonDown);
			},
			unbindEvents(){
				this.submitButton.removeEventListener("click", submitButtonDown);
			}
	}
	return reservationButton;
}

function getReservationDisplayInfoMap(reservationDisplayInfo){
	var reservationDisplayInfoMap = {
			description: reservationDisplayInfo.displayInfo.description,
			productImageUrl: reservationDisplayInfo.productImageUrl,
			placeName: reservationDisplayInfo.displayInfo.placeName,
			openingHour: getOpeningHour(reservationDisplayInfo.displayInfo.openingHours),
			prices: getPrices(reservationDisplayInfo.reservationPrices),
			minPrice: getMinPrice(reservationDisplayInfo.reservationPrices)
	}
	return reservationDisplayInfoMap;
}

function getTicketMap(reservationPrice){
	var ticketMap = {
			priceType: PRICE_TYPE_MAP[reservationPrice.priceTypeName],
			price: addComma(String(reservationPrice.price)),
			discountedPrice: addComma(String(getDiscountedPrice(reservationPrice.price, reservationPrice.discountRate))),
			discountRate: reservationPrice.discountRate,
			productPriceId: reservationPrice.id
	}
	
	return ticketMap;
}

function getOpeningHour(openingHour){
	
	return openingHour.split("-").join("<br>-").replace("<br>","");
	
}

function getPrices(reservationPrices){
	var prices = "";
	for(var i=0, len=reservationPrices.length; i<len; i++){
		var priceType = reservationPrices[i].priceTypeName;
		var price = reservationPrices[i].price;
		var discount = "";
		
		if(reservationPrices[i].discountRate > 0){
			price = getDiscountedPrice(price, reservationPrices[i].discountRate);
			discount = " ( " + reservationPrices[i].discountRate + "% 할인 )";
		}
		prices += PRICE_TYPE_MAP[priceType] + " : " + addComma(String(price)) + "원" + discount + "<br>";
	}
	
	return prices;
}

function getDiscountedPrice(price, discountRate){
	return Math.round((price * (1 - (discountRate / 100 ))) / 10) * 10;
}

function addComma(price){
	var addedPrice = [];
	var count = 1;
	
	for(var len=price.length, i=len-1; i>=0; i--){
		addedPrice.unshift(price[i]);
		if((count++)%3==0)addedPrice.unshift(",");
	}
	
	if(addedPrice[0] === ","){
		addedPrice.shift();
	}
	
	return addedPrice.join("");
}

function getMinPrice(reservationPrices){
	var minPrice = Number.MAX_VALUE;
	
	for(var i=0, len=reservationPrices.length; i<len; i++){
		var discountRate = 1;
		
		if(reservationPrices[i].discounRate > 0){
			discountRate = reservationPrices[i].discounRate;
		}
		
		var discountedPrice = reservationPrices[i].price * (1 - (discountRate / 100));
		
		if(minPrice > reservationPrices[i].price * (1 - (discountRate / 100) ) ){
			minPrice = discountedPrice;
		}
	}
	
	return minPrice;
}