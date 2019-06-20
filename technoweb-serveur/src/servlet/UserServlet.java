package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.CreateUser;

public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
	
		String nom=req.getParameter("nom");
		String prenom=req.getParameter("prenom");
		String login=req.getParameter("login");
		String password=req.getParameter("password");
		
		JSONObject js=CreateUser.createUser(nom, prenom, login, password);
		
		rep.setContentType("text/json");
		PrintWriter out = rep.getWriter();
		out.println(js.toString());
		
	}
}
