
/**
 * @description : product 내용을 100글자로 자른 후 return
 */
Handlebars.registerHelper("product_content", (content)=>{
	return content.substr(0, 100) + "...";
});

/**
 * @description : 최대 3개의 Comment 출력
 */
Handlebars.registerHelper("print_comments_limit_3", (displayInfo)=> {
	var len = displayInfo.comments.length > 3 ? 3 : displayInfo.comments.length;

	var commentListTemplate = document.querySelector("#review_list").innerText;
	var bindCommentTemplate = Handlebars.compile(commentListTemplate);
	
	var commentListHTML = "";
	
	for(var index=0; index<len; index++){
		commentListHTML += bindCommentTemplate(displayInfo.comments[index]);
	}
	return (commentListHTML.split("_{description}").join(displayInfo.displayInfo.description));
});


/**
 * @description : 스코어 -> 퍼센트 변환
 */
Handlebars.registerHelper("percentage_score",(score)=>{
	return score*20;
});