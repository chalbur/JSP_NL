<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ----------------------------------------------- -->
<!-- 뷰 부분, 껍데기, 준비한 데이터를 출력하는 역할만 함 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- EL은 page->request->session->application 순으로 값을 꺼내옴. 값을 찾으면 그 이후는 검색x -->
<%
pageContext.setAttribute("result", "hello");
%>

<body>
	1. <%=request.getAttribute("result") %>입니다. <br >
	2. ${result}<br ><!-- EL을 사용해 표기. request에있는 값만 가져옴 -->
	3. ${names[1] }<br >
	4. ${notice.title }<br >
	5. ${result }<br ><!-- 우선순위가 높은 페이지에서 먼저 값을 가져옴 -->
	6. ${empty param.n?'값이 비어있습니다.':param.n}<br >
	<!-- num값을 가져옴 / empty : param.n == null || param.n == "" / not empty도 가능 -->
	7. ${header.accept }<!-- accept :  -->
</body>
</html>