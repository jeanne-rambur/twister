package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.Search;

public class SearchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse rep ) throws IOException {
		Map<String,String[]> pars = req.getParameterMap();
		PrintWriter out = rep.getWriter();
		rep.setContentType("text/plain");
		
		if(pars.containsKey("ukey")&&pars.containsKey("amis")&&pars.containsKey("query")) {
			String key = req.getParameter("key");
			int amis = Integer.parseInt(req.getParameter("amis"));
			String query = req.getParameter("query");
			
			try {
				out.println(Search.searchPost(key,amis,query));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			JSONObject j = new JSONObject();
			try {
				j.put("erreur","Wrong url, missing parameters");
			}catch(JSONException e) {
				e.printStackTrace();
			}
			out.print(j);
		}
	}
}
