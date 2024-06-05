package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MemberListController")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection myConn=null;
		Statement stmt=null;
		List<MemberVO> list = new ArrayList<>();
		
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
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setName(name);
				vo.setRole(role);
				vo.setRegDt(regDt);
				vo.setPoint(point);
				list.add(vo);				
			}
			stmt.close();  
			myConn.close();

		} catch(Exception ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	
		request.setAttribute("list", list);
		request.getRequestDispatcher("MemberListView.jsp").forward(request, response);
	}

}
