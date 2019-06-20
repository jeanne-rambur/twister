package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import services.Login;

public class LoginServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		System.out.println("login "+login+"password "+password);
		
		JSONObject j= new JSONObject();
		try {
			j = Login.login(login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rep.setContentType("text/plain");
		
		PrintWriter out = rep.getWriter();
		out.print(j);
		
		
	}

}
