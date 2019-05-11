package signin_up;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection con;
	static {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver is Loaded");
		con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			PreparedStatement ps=con.prepareStatement( "insert into login values(?,?)"); 
			ps.setString(1,username);
			ps.setString(2,password);
			int i = ps.executeUpdate();
			
			if(i==1) {
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("register.html");
				rd.include(request, response);
			}
		}catch(SQLException e) {
			out.println("<h2>User already exists...</h2>");
			e.printStackTrace();
	}
	}
}
