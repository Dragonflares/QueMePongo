package server;


import java.util.HashSet;
import java.util.Set;

import controllers.LoginController;
import controllers.UserController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	
	public static void configure() {
		HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();
		
		
		Spark.staticFiles.location("/public");
		

		Spark.before("/user", LoginController::validarLogin);

		Spark.get("/", LoginController::init); 

		Spark.post("/login", LoginController::processLogin);
		Spark.path("/user", () -> { 
		Spark.get("", UserController::indexViewDatosGenerales, transformer);	
		Spark.get("/guardarropas/:idGuardarropa", UserController::indexViewDatosDeUnGuardarropa, transformer);	
		Spark.get("/guardarropas/:idGuardarropa/prendas", UserController::indexViewAgregarPrenda, transformer);	
		Spark.post("/guardarropas/:idGuardarropa/prendas", UserController::registrarPrenda);
		});
		
		Spark.get("/out", UserController::logOut, transformer); // este boton no esta en nuestro tp, pero lo puse porque si
	}

}