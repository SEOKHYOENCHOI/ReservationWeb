
/**
 * @description : ajax 요청
 */
function sendRequest(request, uri, callBack){
	var xhr = new XMLHttpRequest();
	xhr.open(request.method, uri, true);
	if(request.contentType){
		xhr.setRequestHeader("Content-Type", request.contentType);
	}
	xhr.onreadystatechange = () => {
		if(xhr.readyState === 4 && xhr.status === 200){
			if(xhr.response.includes("html")){
				location = xhr.responseURL;
			}
			else if(callBack){
				if(xhr.responseText.startsWith("{")){
					callBack(JSON.parse(xhr.responseText));
				} else{
					callBack(xhr.responseText)
				}
			}
		}
	}
	
	xhr.send(request.data);
}