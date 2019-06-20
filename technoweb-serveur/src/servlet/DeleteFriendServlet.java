package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.DeleteFriend;

public class DeleteFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		
		String key=req.getParameter("key");
		String id_friendS=req.getParameter("id_friend");
		int id_friend=-1;
		if(id_friendS!=null)
			id_friend=Integer.parseInt(id_friendS); 
		
		rep.setContentType("text/plain");
		PrintWriter out=rep.getWriter();
		
		try {
			out.print(DeleteFriend.deleteFriend(key, id_friend));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}

}
