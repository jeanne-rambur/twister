package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bd.MessageTools;
import services.AddMessage;
import services.JSONException;
import services.ListMessage;

public class ListMessageServlet extends HttpServlet{
	public static final long serialVersionUID = 1;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		String login = req.getParameter("login");
		
		System.out.println(login);
		
		JSONObject js = new JSONObject();
		
		try {
			try {
				js=ListMessage.getAllMessage(login);
			} catch (org.json.JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rep.setContentType("text/plain");
		PrintWriter out=rep.getWriter();
		out.print(js);
	}
}
