function ReviewWriteObject(){
	var urlSearchParams = new URLSearchParams(location.search);
	document.querySelector("#review_title").innerText = urlSearchParams.get("description");
	
	
	this.init = function(){
		this.reviewRank = new ReviewRank();
		this.commentArea = new CommentArea();
		this.attachReviewImage = new AttachReviewImage();
		this.submitButton = new ReviewSubmitButton(urlSearchParams);
	}
	this.render = function(){
		this.reviewRank.init();
		this.commentArea.init();
		this.attachReviewImage.init();
		this.submitButton.init();
	}
}


function ReviewStarRank(rank, htmlElement){
	this.rank = rank;
	this.htmlElement = htmlElement;
}

ReviewStarRank.prototype.bindEvents = function(){
	this.htmlElement.addEventListener("click", ()=>{
		document.querySelectorAll(".rating_rdo").forEach((v)=>{
			if(v.value <= this.rank){
				v.checked = true;
			} else{
				v.checked = false;
			}
		});
		
		document.querySelector("#star_rank").innerText = this.rank;
	})
}

function ReviewRank(){
	this.starRanks = [];
	this.currentRank = 1;
	
	this.init = function(){
		this.bindEvents();
	}
	
	this.bindEvents = function(){
		document.querySelectorAll(".rating_rdo").forEach((v)=>{
			this.starRanks.push(new ReviewStarRank(v.value, v));
		});
		this.starRanks.forEach((v)=>{
			v.bindEvents();
			v.htmlElement.addEventListener("change", ()=>{
				this.currentRank = v.value;
			})
		});
	}
}

function CommentArea(){
	this.init = function(){
		this.bindEvents();
	}
	this.bindEvents = function(){
		var commentArea = document.querySelector("#review_comment");
		var commentAreaPlaceholder = document.querySelector("#review_comment_placeholder");
		var commentLengthField = document.querySelector("#comment_length");
		
		commentAreaPlaceholder.addEventListener("click", (event)=>{
			var target = event.target;
			if(event.target.tagName === "SPAN"){
				target = event.target.parentElement;
			}
			target.className = "review_write_info hide";
			commentArea.focus();
		});
		
		commentArea.addEventListener("blur", (event)=>{
			if(event.target.value === ""){
				commentAreaPlaceholder.className = "review_write_info";
			}
		});
		
		commentArea.addEventListener("keyup", ()=>{
			var commentLength = commentArea.value.length;
			if(commentLength > 400){
				commentArea.value = commentArea.value.substr(0,400);
				return ;
			}
			
			commentLengthField.innerText = commentLength;
		});
		
	}
}

function AttachReviewImage(){
	this.init = function(){
		this.bindEvents();
		
	}
	this.bindEvents = function(){
		var elImage = document.querySelector("#reviewImageFileOpenInput");
		
	    elImage.addEventListener("change", (evt) => {
	        var image = evt.target.files[0];
	        if(!validateImageType(image)) { 
	            alert("JPG, PNG 파일만 첨부가능합니다.");
	            return;
	        } 
	        var imageThumbLi = document.querySelector("#image_thumb_li");
			var imageThumb = document.querySelector("#image_thumb");
	        imageThumbLi.className = "item";
	        imageThumb.src = window.URL.createObjectURL(image);
	        
	        var deleteButton = document.querySelector("#delete_image");
	        deleteButton.addEventListener("click", function deleteButtonDown(){
	        	if(confirm("정말 삭제하시겠습니까?")){
	        		imageThumbLi.className = "item hide";
	    	        imageThumb.src = "";
	    	        
	    	        deleteButton.removeEventListener("click",deleteButtonDown);
	        	}
	        });
	    });
	}
}

function ReviewSubmitButton(urlSearchParams){
	this.init = function(){
		this.bindEvents();
	}
	this.bindEvents = function(){
		document.querySelector("#submit_button").addEventListener("click", (event)=>{

			event.preventDefault();
			var comment = document.querySelector("#review_comment").value;
			
			if(comment.length < 5){
				alert("5자 이상 입력해주세요");
				return;
			}
			
			var attachImageForm = document.querySelector("#reviewImageFileOpenInput");
			var productId = urlSearchParams.get("productId");
			var score = 0;
			
			document.querySelectorAll(".rating_rdo").forEach((v)=>{
			    if(!v.checked) return;
			    score++;
			});
			
		    var formData = new FormData();
		    formData.append("productId", productId);
		    formData.append("score", score);
		    formData.append("comment", comment);
		    
		    if(attachImageForm){
		    	
			    Array.from(attachImageForm.files).forEach((v)=>{
			    	formData.append("reservationImage", v);
			    });
			    
		    } else{
		    	
		    	formData.append("reservationImage", "");
		    	
		    }
		    
		    reviewWriteSubmitRequest(redirectToHome, urlSearchParams.get("reservationInfoId"), formData);
			
		});
	}
}

function reviewWriteSubmitRequest(callBack, reservationInfoId, formData){
	
	var request = {
			method:"POST",
			data:formData
	}
	
	var requestUri = "/api/reservations/" + reservationInfoId + "/comments";
	sendRequest(request, requestUri, callBack);
}


function redirectToHome(){
	alert("리뷰 작성이 완료되었습니다.");
	location="/";
}
