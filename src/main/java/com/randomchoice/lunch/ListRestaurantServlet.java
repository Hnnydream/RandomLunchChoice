package com.randomchoice.lunch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultList;

public class ListRestaurantServlet extends HttpServlet {

	private static final long serialVersionUID = -7002584418176962321L;
	
	static final int PAGE_SIZE = 50;
	
	private DatastoreService datastore;

	public ListRestaurantServlet() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(PAGE_SIZE);
				
		String startCursor = req.getParameter("cursor");
	    if (startCursor != null) {
	      fetchOptions.startCursor(Cursor.fromWebSafeString(startCursor));
	    }
		
		Query q = new Query("Restaurant").addSort("name", SortDirection.ASCENDING);
	    PreparedQuery pq = datastore.prepare(q);
	    
	    QueryResultList<Entity> results;
	    try {
	        results = pq.asQueryResultList(fetchOptions);
	      } catch (IllegalArgumentException e) {
	        // IllegalArgumentException happens when an invalid cursor is used.
	        // A user could have manually entered a bad cursor in the URL or there
	        // may have been an internal implementation detail change in App Engine.
	        // Redirect to the page without the cursor parameter to show something
	        // rather than an error.
	        resp.sendRedirect("/index.jsp");
	        return;
	      }
	    List<String> restaurantList = new ArrayList<String>();

	    for (Entity entity : results) {
	      restaurantList.add(entity.getProperty("name").toString());
	    }
	    int choice = randInt(0, restaurantList.size() - 1);
	    req.setAttribute("restaurantList", restaurantList);
	    req.setAttribute("theChoice", restaurantList.get(choice));
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/restaurants.jsp");
		rd.forward(req, resp);
	
	}
	
	private int randInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min + 1)) + min;
	}
}
