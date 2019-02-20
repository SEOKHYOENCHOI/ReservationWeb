<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<p>
		<a href="/" class="lnk_logo" style="text-decoration:none"> 
			홈으로
		</a>
	</p>
	<h1>Invalid Parameter Error</h1>
	<h1 id="parameter">cause : {{parameter}}</h1>
	
	<script>
		var param = document.querySelector("#parameter").innerText;
		param = param.replace("{{parameter}}",location.search.substr(1, location.search.length));
		document.querySelector("#parameter").innerText = param;
	</script>
</body>
</html>