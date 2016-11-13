package com.randomchoice.lunch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.common.collect.ImmutableList;

public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = -8509301805992627293L;

	static final String IS_POPULATED_ENTITY = "IsPopulated";
	static final String IS_POPULATED_KEY_NAME = "is-populated";

	private static final String RESTAURANT_ENTITY = "Restaurant";
	private static final String NAME_PROPERTY = "name";

	private static final ImmutableList<String> RESTAURANT_LIST =
			// Keep in alphabetical order, so this is the same as the query
			// order.
			ImmutableList.<String>builder()
				.add("Chinese at HoYip")
				.add("Chinese2")
				.add("Pasta")
				.add("Ramen at Kuu")
				.add("Ramen at Mike's House")
				.add("Sandwich")
				.add("Zeytuna")
				.build();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Key isPopulatedKey = KeyFactory.createKey(IS_POPULATED_ENTITY, IS_POPULATED_KEY_NAME);

		boolean isAlreadyPopulated;
		try {
			datastore.get(isPopulatedKey);
			isAlreadyPopulated = true;
		} catch (EntityNotFoundException expected) {
			isAlreadyPopulated = false;
		}
		if (isAlreadyPopulated) {
			resp.getWriter().println("ok");
			return;
		}

		ImmutableList.Builder<Entity> restaurants = ImmutableList.builder();
		for (String name : RESTAURANT_LIST) {
			Entity restaurant = new Entity(RESTAURANT_ENTITY);
			restaurant.setProperty(NAME_PROPERTY, name);
			restaurants.add(restaurant);
		}
		datastore.put(restaurants.build());
		datastore.put(new Entity(isPopulatedKey));
		resp.getWriter().println("ok");
	}

}
