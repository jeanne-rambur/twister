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
import services.SearchFriend;

public class SearchFriendServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		
		String login_friend = req.getParameter("login");
		System.out.println("login friend "+login_friend);
		
		JSONObject j= new JSONObject();
		try {
			j = SearchFriend.searchFriend(login_friend);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rep.setContentType("text/plain");
		
		PrintWriter out = rep.getWriter();
		out.print(j);
		
		
	}

}
