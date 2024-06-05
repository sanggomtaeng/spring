<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*"  %>
<!-- 2. JSP로만 개발 -->
<html> 
<head> 
	<title>게시판</title>
</head>
<body>
	<h3>회원관리</h3>
	<div><a href="insert_form.jsp">사용자등록</a></div>

	<table>
		<thead>
			<tr>
			  <th>아디</th><th>이름</th><th>권한</th><th>등록일</th><th>포인트</th>
			</tr>
		</thead>
		<tbody>	
<%
	Connection myConn=null;
	Statement stmt=null;
	try {
		String dbdriver = "oracle.jdbc.OracleDriver";    
		String dburl = "jdbc:oracle:thin:@localhost:1521:xe";

		Class.forName(dbdriver);
		myConn = DriverManager.getConnection(dburl, "hr", "hr");
		stmt = myConn.createStatement();	

		String mySQL = 	"SELECT ID, NAME, ROLE, REG_DT, POINT FROM MEMBERS";
	            
		ResultSet myResultSet = stmt.executeQuery(mySQL);
		
		while (myResultSet.next()) {	
			String id = myResultSet.getString("id");	
			String name = myResultSet.getString("name");
			String role = myResultSet.getString("role");
			Date regDt = myResultSet.getDate("reg_dt");
			int point = myResultSet.getInt("point");			
	
			out.print("<tr height=28 align=center>");
			out.print("<td>"+id+"</td>");
			out.print("<td>"+name+"</td>");
			out.print("<td>"+role+"</td>");
			out.print("<td>"+regDt+"</td>");
			out.print("<td>"+point+"</td></tr>");
		}
		stmt.close();  
		myConn.close();
	} catch(Exception ex) {
		System.err.println("SQLException: " + ex.getMessage());
	}
%>

		</tbody>
	</table>
</body>
</html>