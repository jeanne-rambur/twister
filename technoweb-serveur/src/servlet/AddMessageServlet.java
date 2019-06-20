package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.AddMessage;
import services.JSONException;

public class AddMessageServlet extends HttpServlet {
	public static final long serialVersionUID = 1;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		String key = req.getParameter("key");
		String text = req.getParameter("text");
		
		System.out.println(text);
		
		JSONObject js = new JSONObject();
		
		try {
			js=AddMessage.addMessage(key,text);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rep.setContentType("text/plain");
		PrintWriter out=rep.getWriter();
		out.print(js);
	}
}
