package server;


import java.util.HashSet;
import java.util.Set;

import controllers.LoginController;
import controllers.UserController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

static Set<String> publicRoutes = new HashSet<String>();
	
	private static void setPublicRoutes(Set<String> publicRoutes)
	{	
		publicRoutes.add("/");
		publicRoutes.add("/login");
		publicRoutes.add("/loginFailure");
		publicRoutes.add("/logout");
	}
	
	public static void configure() {
		HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();
		
		
		Spark.staticFiles.location("/public");
		setPublicRoutes(publicRoutes);

		Spark.before("/user", LoginController::validarLogin);

		Spark.get("/", LoginController::init, transformer); 

		Spark.post("/login", LoginController::processLogin);
		Spark.path("/user", () -> { 
		Spark.get("", UserController::indexViewDatosGenerales, transformer);	
		Spark.get("/guardarropas/:idGuardarropa", UserController::indexViewDatosDeUnGuardarropa, transformer);	
		Spark.get("/guardarropas/:idGuardarropa/prendas", UserController::indexViewAgregarPrenda, transformer);	
		Spark.post("/guardarropas/:idGuardarropa/prendas", UserController::registrarPrenda);
		});
		
		Spark.get("/out", UserController::logOut, transformer);
	}

}