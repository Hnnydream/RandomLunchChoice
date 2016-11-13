package com.randomchoice.lunch;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ListRestaurantServlet extends HttpServlet {

	private static final long serialVersionUID = -7002584418176962321L;
		
	public ListRestaurantServlet() {
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		
		List<String> restaurantList = new RestaurantDAO().getRestaurantList(req);
	    
	    String jsonResaurantList = new Gson().toJson(restaurantList);
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonResaurantList);
	}


	
	
}
