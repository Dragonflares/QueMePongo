package server;


import java.util.HashSet;
import java.util.Set;

import controllers.LoginController;
import controllers.UserController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import controllers.*;


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
		HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();

//		Spark.staticFileLocation("/home");


//		Spark.before(LoginController::chequeoUsuarioEnsesion);
		
		
		
		Spark.get("/login", LoginController::init); 
		Spark.get("/",WardrobeController::init);
		Spark.post("/login", LoginController::processLogin);

		Spark.get("sdsdsd", UserController::indexViewDatosGenerales, transformer);	
		Spark.get("/guardarropas/:idGuardarropa", UserController::indexViewDatosDeUnGuardarropa, transformer);	
		Spark.get("/guardarropas/:idGuardarropa/prendas", UserController::indexViewAgregarPrenda, transformer);	
		Spark.post("/guardarropas/:idGuardarropa/prendas", UserController::registrarPrenda);


		Spark.get("/out", UserController::logOut, transformer); // este boton no esta en nuestro tp, pero lo puse porque si
	}

}