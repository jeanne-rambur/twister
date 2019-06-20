package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.AddFriend;
import services.JSONException;

public class AddFriendServlet extends HttpServlet {
	
	public static final long serialVersionUID = 1;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		String key = req.getParameter("key");
		int id_friend = -1; 
		String id_friendStr;
		if((id_friendStr = req.getParameter("id_friend"))!=null) 
			id_friend=Integer.parseInt(req.getParameter("id_friend"));
		System.out.println(id_friend);
		
		rep.setContentType("text/plain");
		
		PrintWriter out=rep.getWriter();
		
		try {
			out.print(AddFriend.addFriend(key, id_friend));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
