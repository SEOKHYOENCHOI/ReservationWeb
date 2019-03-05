
function cancelButtonDown(reservationCancel, reservationType){
	if(reservationType === "SCHEDULED"){
		reservationCancelRequest(reservationCancel.reservationInfoId)
		
		var reservationScheduled = reservationInfoObject.reservationInfoScheduled;
		for(var i=0, len=reservationScheduled.length; i<len; i++){
			if(reservationScheduled[i].reservation.reservationInfoId === reservationCancel.reservationInfoId){
				if(confirm("취소 하시겠습니까?")){
					reservationInfoObject.appendElement(reservationScheduled[i], "CANCELED");
					reservationInfoObject.removeElement(i, reservationType);
					cleanUL();
				
					if(document.querySelector("#reservation_list_button_scheduled").className.includes("on")){
						reservationInfoObject.render("SCHEDULED");
					} else{
						reservationInfoObject.render();
					}
				}
				break;
			}
		}
	} else{
		var productId = reservationCancel.productId;
		var description = reservationCancel.description;
		var reservationInfoId = reservationCancel.reservationInfoId;
		location = "/reviewWrite?" 
								+ "productId=" + productId 
								+ "&description=" + description 
								+ "&reservationInfoId=" + reservationInfoId;
	}
}

function cleanUL(event){
	document.querySelector("#reservation_ul").innerHTML = "";
	if(event){
		document.querySelectorAll(".link_summary_board").forEach((v)=>{
			v.className = "link_summary_board";
		});
		
		var listButton = event.target;
		if(event.target.tagName != "A"){
			listButton = event.target.parentElement;
		}
		listButton.className += " on";
	}
}