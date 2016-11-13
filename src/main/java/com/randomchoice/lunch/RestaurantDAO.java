package com.randomchoice.lunch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Query.SortDirection;

public class RestaurantDAO {

	static final int PAGE_SIZE = 20;
	

	public RestaurantDAO() {
	}

	public List<String> getRestaurantList(HttpServletRequest req) throws IOException {
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(PAGE_SIZE);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		
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
			// A user could have manually entered a bad cursor in the URL or
			// there
			// may have been an internal implementation detail change in App
			// Engine.
			// Redirect to the page without the cursor parameter to show
			// something
			// rather than an error.
			return new ArrayList<String>();
		}
		List<String> restaurantList = new ArrayList<String>();

		for (Entity entity : results) {
			restaurantList.add(entity.getProperty("name").toString());
		}
		return restaurantList;
	}
}
