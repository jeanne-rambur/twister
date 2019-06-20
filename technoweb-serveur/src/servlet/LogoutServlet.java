package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Logout;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1;
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		
		String key = req.getParameter("key");
		JSONObject js = new JSONObject();
		try{
			js=Logout.logout(key);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		rep.setContentType("text/plain");
		PrintWriter out=rep.getWriter();
		
		out.print(js);
		
		
	}
	

}
