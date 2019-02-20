
/**
 * @description : myreservationPage 전체에 대한 Object
 * 
 * 				  각 Object는 @Line124 의 renderReservations을 통해 Rendering 하고
 * 				  renderReservations 은 각 ReservationInfo로부터 Map을 만든 후
 * 				  js.common.utils의 replaceTemplateHTML을 통해 변환된 HTML을 삽입한다.
 * 
 */
function getReservationInfoObj(reservationInfo){
	
	var reservationInfoObj = {
			init: function(){
				this.reservationInfoScheduled = [];
				this.reservationInfoUsed = [];
				this.reservationInfoCanceled = [];
				reservationInfo.reservations.forEach((reservationItem)=>{
					if(reservationItem.reservation.cancelFlag === 1){
						
						this.reservationInfoCanceled.push(reservationItem);
						
					} else {
						
						var reservationDate = new Date(reservationItem.reservation.reservationDate);
						var currentTime = new Date();
						
						if(reservationDate.getTime() > currentTime.getTime()){
							
							this.reservationInfoScheduled.push(reservationItem);
							
						} else{
							
							this.reservationInfoUsed.push(reservationItem);
							
						}
					}
				});

			},
			render: function(reservationType = "ALL"){
				if(reservationType === "ALL"){
					this.reservationsScheduled = getReservationInfo(this.reservationInfoScheduled, "SCHEDULED");
					this.reservationsUsed = getReservationInfo(this.reservationInfoUsed, "USED");
					this.reservationsCanceled = getReservationInfo(this.reservationInfoCanceled, "CANCELED");
				
					this.reservationsScheduled.init();
					this.reservationsUsed.init();
					this.reservationsCanceled.init();
					
				} else{
					var reservationList = reservationType[0]+reservationType.substr(1).toLowerCase();
					var reservationInfo = getReservationInfo(this["reservationInfo"+reservationList],reservationType);
					this["reservations"+reservationList] = reservationInfo;
					this["reservations"+reservationList].init();
				}
				this.bindEvents(); 
			},
			bindEvents: function(){
				document.querySelector("#reservation_list_button_all").addEventListener("click",(event)=>{
					cleanUL(event);
					this.reservationsScheduled.init();
					this.reservationsUsed.init();
					this.reservationsCanceled.init();
				});
				
				document.querySelector("#reservation_list_button_scheduled").addEventListener("click",(event)=>{
					cleanUL(event);
					this.reservationsScheduled.init();
				});
				document.querySelector("#reservation_list_button_used").addEventListener("click",(event)=>{
					cleanUL(event);
					this.reservationsUsed.init();
				});
				document.querySelector("#reservation_list_button_canceled").addEventListener("click",(event)=>{
					cleanUL(event);
					this.reservationsCanceled.init();
				});
			},
			appendElement:function(reservation, reservationType){
				var reservationList = reservationType[0]+reservationType.substr(1).toLowerCase();
				this["reservationInfo"+reservationList].push(reservation);
			},
			removeElement:function(index, reservationType){
				var reservationList = reservationType[0]+reservationType.substr(1).toLowerCase();
				this["reservationInfo"+reservationList].splice(index, 1);
			}
	}
	
	return reservationInfoObj;
}

function getReservationInfo(reservations, reservationType){
	var reservationInfo={
			reservations: reservations,
			
			init: function(){
				this.render();
				if(this.isReservationExist){
					this.bindEvents();
				}
				
			},
			render: function(){
				
				this.isReservationExist = renderReservations(reservations, reservationType);
				
			},
			bindEvents: function(){
				setTimeout(()=>{
					document.querySelectorAll("#"+reservationType.toLowerCase()+"_list .btn").forEach((v)=>{
						v.addEventListener("click", (event)=>{
							
							if(event.target.tagName === "SPAN"){
								var reservationInfoId = parseInt(event.target.parentElement.name);
							} else{
								var reservationInfoId = parseInt(event.target.name);
							}
							
							cancelButtonDown(reservationInfoId, reservationType);
					    });
					});
				}, 100);
			}
	}
	return reservationInfo;
}

function renderReservations(reservations, reservationType){
	var ul = document.querySelector("#reservation_ul");
	var li = document.querySelector("#reservation_list");
	
	var reservationListMap = getReservationListMap(reservationType);
	
	var reservationInfoBodyHTML = document.querySelector("#reservation_info_body").innerHTML;
	var reservationInfoBodyMap = {};
	
	if(reservations.length == 0){
		reservationInfoBodyMap.reservationInfo = document.querySelector("#nolist_div").innerHTML;
		reservationInfoBodyMap.shareButton = "";
		
		reservationListMap.reservationInfo += replaceTemplateHTML(reservationInfoBodyMap, reservationInfoBodyHTML);
		ul.innerHTML += replaceTemplateHTML(reservationListMap, li.innerHTML);
		
		return false;
	}
	for(var i=0,len=reservations.length; i<len; i++){
		var reservationInfoHTML = document.querySelector("#reservation_info").innerHTML;
		var reservationInfoMap = getReservationInfoMap(reservations[i], reservationType);

		reservationInfoHTML = replaceTemplateHTML(reservationInfoMap, reservationInfoHTML);
		reservationInfoBodyMap.reservationInfo = reservationInfoHTML;
		reservationInfoBodyMap.shareButton = document.querySelector("#share_button").innerHTML;
		
		reservationInfo = 
		reservationListMap.reservationInfo += replaceTemplateHTML(reservationInfoBodyMap, reservationInfoBodyHTML);
		
	}
	ul.innerHTML += replaceTemplateHTML(reservationListMap, li.innerHTML);
	return true;
}

function getPriceSum(reservationPrices){
	var priceSum = 0;

	reservationPrices.forEach((reservationPrice)=>{
		priceSum += reservationPrice.price * reservationPrice.count;
	});
	
	return priceSum;
}

function getReservationDetails(reservationPrices){
	var reservationDetails = "";

	reservationPrices.forEach((reservationPrice)=>{
		reservationDetails += PRICE_TYPE_MAP[reservationPrice.priceTypeName] + " " 
		reservationDetails += reservationPrice.count + "매, ";
	});
	
	return reservationDetails.substr(0, reservationDetails.length - 2);
}

function getReservationListMap(reservationType){
	
	var reservationListMap = {
			reservationInfo:"",
			reservationType: reservationType.toLowerCase(),
			reservationClassType: RESERVATION_CLASS_TYPE_MAP[reservationType],
			reservationIconType: RESERVATION_ICON_TYPE_MAP[reservationType],
			reservationTypeKor: RESERVATION_TYPE_MAP[reservationType]
	};
	
	return reservationListMap;
}

function getReservationInfoMap(reservationInfo, reservationType){
	if(reservationType === "SCHEDULED"){
		
		var cancelBtn = document.querySelector("#cancel_button").innerHTML;
		cancelBtn = cancelBtn.replace("_{name}", reservationInfo.reservation.reservationInfoId);
		
	} else if(reservationType === "USED"){
		
		var cancelBtn = document.querySelector("#comment_button").innerHTML;
		cancelBtn = cancelBtn.replace("_{name}", reservationInfo.reservation.reservationInfoId);
		
	} else{
		
		var cancelBtn = "";

	}

	var reservationInfoMap = {
			reservationId: String(reservationInfo.reservation.reservationInfoId).padStart(8,"0"),
			description: reservationInfo.displayInfo.description,
			reservationDate: reservationInfo.reservation.reservationDate.substr(0,10).split('-').join('.'),
			reservationDetails: getReservationDetails(reservationInfo.reservationPrices),
			placeName: reservationInfo.displayInfo.placeName,
			priceSum: getPriceSum(reservationInfo.reservationPrices),
			cancelButton: cancelBtn
	}
	
	return reservationInfoMap;
}

