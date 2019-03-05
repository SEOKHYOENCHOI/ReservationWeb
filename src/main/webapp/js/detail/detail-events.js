function prevButtonDown(){
	var backGroundImageUl = document.querySelector("#background_image_ul");
	backGroundImageUl.style.transition = "right 0.1s";
	backGroundImageUl.style.right = "0px";
	
	setTimeout(()=>{
		backGroundImageUl.style.transition = "right 0s";
		moveListElement("begin", backGroundImageUl);
		backGroundImageUl.style.right = "414px";
	},100);
	
	var imageCount = document.querySelector("#product_image_count");
	imageCount.innerText = (parseInt(imageCount.innerText) % 2) + 1;
}

function nextButtonDown(){
	var backGroundImageUl = document.querySelector("#background_image_ul");
	backGroundImageUl.style.transition = "right 0.1s";
	backGroundImageUl.style.right = "828px";
	
	setTimeout(()=>{
		backGroundImageUl.style.transition = "right 0s";
		moveListElement("end", backGroundImageUl);
		backGroundImageUl.style.right = "414px";
	},100);
	
	var imageCount = document.querySelector("#product_image_count");
	imageCount.innerText = (parseInt(imageCount.innerText) % 2) + 1;
	
}

function moveListElement(where, ul){
	if(where === "begin"){
		ul.removeChild(ul.lastElementChild);
		var firstElement = ul.lastElementChild.outerHTML;
		ul.innerHTML = firstElement + ul.innerHTML;
		
	} else if(where === "end"){
		ul.removeChild(ul.firstElementChild);
		var lastElement = ul.firstElementChild.outerHTML;
		ul.innerHTML = ul.innerHTML + lastElement;
	}
	return ul;
}

function detailButtonDown(){
	document.querySelector("#detail_info").setAttribute("class","anchor active");
	document.querySelector("#detail_info_wrap").setAttribute("class","detail_area_wrap");
	
	document.querySelector("#path").setAttribute("class","anchor");
	document.querySelector("#path_wrap").setAttribute("class","detail_location hide");
}

function pathButtonDown(){
	document.querySelector("#detail_info").setAttribute("class","anchor");
	document.querySelector("#detail_info_wrap").setAttribute("class","detail_area_wrap hide");
	
	document.querySelector("#path").setAttribute("class","anchor active");
	document.querySelector("#path_wrap").setAttribute("class","detail_location");
}

function moreOpenButtonDown(displayInfo){
	document.querySelector("#more_button_open").setAttribute("style","display: none");
	document.querySelector("#more_button_close").removeAttribute("style");
	document.querySelector("#product_content_div").setAttribute("class", "store_details");
	document.querySelector("#product_content_paragraph").innerText = displayInfo.content;
}

function moreCloseButtonDown(displayInfo){
	document.querySelector("#more_button_open").removeAttribute("style");
	document.querySelector("#more_button_close").setAttribute("style","display: none");
	document.querySelector("#product_content_div").setAttribute("class", "store_details close3");
	document.querySelector("#product_content_paragraph").innerText = displayInfo.content.substr(0, 100) + "...";
}

function reservationButtonDown(displayInfo){
	location = location.origin + "/reservation?displayInfoId=" + displayInfo.displayInfo.id;
}