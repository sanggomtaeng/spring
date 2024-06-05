package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 1. 서블릿으로만 개발
 */
@WebServlet("/MemberListServ")
public class MemberListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.append("<html>");
		out.append("<head>");
		out.append("	<title>게시판</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("	<h3>회원관리</h3>");
		out.append("	<div><a href=\"insert_form.jsp\">사용자등록</a></div>");
		out.append("	<table>");
		out.append("		<thead>");
		out.append("			<tr>");
		out.append("			  <th>아디</th><th>이름</th><th>권한</th><th>등록일</th><th>포인트</th>");
		out.append("			</tr>");
		out.append("		</thead>");
		out.append("		<tbody>");

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
		
		out.append("		</tbody>");
		out.append("	</table>");
		out.append("</body>");
		out.append("</html>");
		
	}



}
