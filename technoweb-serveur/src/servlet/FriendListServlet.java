package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class FriendListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {

		JSONObject js= new JSONObject();
		String user_key = req.getParameter("key");


		try {
			//appel service user_key
			
			try {
				js = Friend.getListFriends(user_key);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			rep.setContentType("text/json");
			PrintWriter out = rep.getWriter();
			out.println(js.toString());
			
			
		} catch (JSONException e) {
			
			/*Service refused ?*/
			
			e.printStackTrace();
		}


	}

}
