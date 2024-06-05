<%@page import="controller.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*"  %>
<html> 
<head> 
	<title>게시판</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body>
<div class="container">
	<h3 class="text-center my-4">회원관리</h3>
	<div class="text-end mb-4">
		<a href="insert_form.jsp" class="btn btn-success">사용자등록</a>
	</div>

	<table class="table">
		<thead>
			<tr>
			  <th>아디</th><th>이름</th><th>권한</th><th>등록일</th><th>포인트</th>
			</tr>
		</thead>
		<tbody>	
<%
	ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list");
	for (MemberVO vo : list) {				

		out.print("<tr height=28 align=center>");
		out.print("<td>"+vo.getId()+"</td>");
		out.print("<td>"+vo.getName()+"</td>");
		out.print("<td>"+vo.getRole()+"</td>");
		out.print("<td>"+vo.getRegDt()+"</td>");
		out.print("<td>"+vo.getPoint()+"</td></tr>");
	}
%>

		</tbody>
	</table>
</div>
</body>
</html>