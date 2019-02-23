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

function valideImageType(image) {
	const result = ([ 'image/jpeg',
					  'image/png',
					  'image/jpg' ].indexOf(image.type) > -1);
	return result;
}

function AttachReviewImage(){
	this.init = function(){
		this.bindEvents();
		
	}
	this.bindEvents = function(){
		var elImage = document.querySelector("#reviewImageFileOpenInput");
		
	    elImage.addEventListener("change", (evt) => {
	        var image = evt.target.files[0];
	        if(!valideImageType(image)) { 
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

function SubmitButton(urlSearchParams){
	this.init = function(){
		this.bindEvents();
	}
	this.bindEvents = function(){
		document.querySelector("#submit_button").addEventListener("click", ()=>{

			var attachImageForm = document.querySelector("#attach_image_form");
			var productId = urlSearchParams.get("productId");
			var comment = document.querySelector("#review_comment").value;
			var score = 0;
			
			document.querySelectorAll(".rating_rdo").forEach((v)=>{
			    if(!v.checked) return;
			    score++;
			});
			
			attachImageForm.action = "/api/reservations/"
									+ urlSearchParams.get("reservationInfoId")
									+ "/comments?"
									+ "productId=" + productId
									+ "&comment=" + comment
									+ "&score=" + score;
			console.log(attachImageForm);
			attachImageForm.submit();
		});
	}
}

document.addEventListener("DOMContentLoaded", function(){
	var urlSearchParams = new URLSearchParams(location.search);
	document.querySelector("#review_title").innerText = urlSearchParams.get("description");
	var reviewRank = new ReviewRank();
	reviewRank.init();
	var commentArea = new CommentArea();
	commentArea.bindEvents();
	
	var attachReviewImage = new AttachReviewImage();
	attachReviewImage.init();
	
	var submitButton = new SubmitButton(urlSearchParams);
	submitButton.init();
});