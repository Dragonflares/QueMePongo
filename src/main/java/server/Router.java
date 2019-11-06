package server;


import java.util.HashSet;
import java.util.Set;

import controllers.LoginController;
import controllers.UserController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utils.BooleanHelper;
import utils.HandlebarsTemplateEngineBuilder;
import utils.SessionHandler;
import controllers.*;


public class Router {


	static Set<String> publicRoutes = new HashSet<String>();

	public static Boolean isPublic(String route)
	{
		return publicRoutes.contains(route);
	}

	private static void setPublicRoutes(Set<String> publicRoutes)
	{	
		publicRoutes.add("/login");
		publicRoutes.add("/loginFailure");
		publicRoutes.add("/logout");
	}



	public static void configure() {
	//	HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();

		
		
		
		Spark.staticFiles.location("/public");
		setPublicRoutes(publicRoutes);
		
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
	
		
		Spark.before("/", SessionHandler.allowed());
		
		Spark.get("/loginFailure", LoginController::loginFailure, engine);
		Spark.post("/loginFailure", LoginController::loginFailure, engine);
		Spark.post("/login", LoginController::processLogin,engine);
		
		Spark.get("/login", LoginController::init,engine); 
		Spark.get("/",WardrobeController::init,engine);

		Spark.get("sdsdsd", UserController::indexViewDatosGenerales, engine);	
		Spark.get("/guardarropas/:idGuardarropa", UserController::indexViewDatosDeUnGuardarropa, engine);	
		Spark.get("/guardarropas/:idGuardarropa/prendas", UserController::indexViewAgregarPrenda, engine);	
		Spark.post("/guardarropas/:idGuardarropa/prendas", UserController::registrarPrenda);


		Spark.get("/out", UserController::logOut, engine); // este boton no esta en nuestro tp, pero lo puse porque si
	}

}