package server;


import java.util.HashSet;
import java.util.Set;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utils.BooleanHelper;
import utils.HandlebarsTemplateEngineBuilder;
import utils.SessionHandler;

public class Router {
	
	static Set<String> publicRoutes = new HashSet<String>();
	
	public static Boolean isPublic(String route)
	{
		return publicRoutes.contains(route);
	}
	
	private static void setPublicRoutes(Set<String> publicRoutes)
	{	
		publicRoutes.add("/");
		publicRoutes.add("/login");
		publicRoutes.add("/loginFailure");
		publicRoutes.add("/logout");
	}

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
		
		Spark.staticFiles.location("/public");
		setPublicRoutes(publicRoutes);
		
		Spark.before(SessionHandler.allowed());
		
		Spark.get("/", HomeController::showLoginForm, engine);

		
	}

}