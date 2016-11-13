package com.randomchoice.lunch;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRandomChoiceServlet extends HttpServlet {

	private static final long serialVersionUID = -3111641725638871859L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		List<String> restaurantList = new RestaurantDAO().getRestaurantList(req);
		
		int choice = randInt(0, restaurantList.size() - 1);
		
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(restaurantList.get(choice));
	}
	
	private int randInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min + 1)) + min;
	}
}
