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
	<h1>Empty Result Set Error</h1>
	<h1 id="cause">{{cause}}</h1>
	
	<script>
		var cause = document.querySelector("#cause").innerText;
		cause = cause.replace("{{cause}}",location.search.substr(1, location.search.length));
		document.querySelector("#cause").innerText = cause;
	</script>
</body>
</html>