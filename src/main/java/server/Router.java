package server;


import java.util.HashSet;
import java.util.Set;

import controllers.LoginController;
import controllers.WardrobeController;
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

		Spark.staticFiles.location("/public");
		setPublicRoutes(publicRoutes);
		
		//es asi porque si, corta
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
		
		//el before chequea si esta autenticado (con username) y si la ruta es p�blica, en caso de que una no lo sea, redirige al login
		//el get te devuelve un model and view
		//el post te "redirecciona" debido a una modificacion que haces (si bien devuelven model and view en realidad devuelven null)
		
		Spark.before("/", SessionHandler.allowed());
		Spark.before("/eventos", SessionHandler.allowed());
		Spark.before("/eventos", SessionHandler.allowed());
		Spark.before("/guardarropas/:idGuardarropa",SessionHandler.allowed());
		Spark.before("/guardarropas",SessionHandler.allowed());
		Spark.before("/calificaciones",SessionHandler.allowed());
		Spark.before("/out",SessionHandler.allowed());
		Spark.before("/eventos/alta",SessionHandler.allowed());

		//Spark.before("/eventos/info", SessionHandler.allowed());
		
		Spark.get("/login", LoginController::init,engine);
		Spark.get("/loginFailure", LoginController::loginFailure, engine);
		Spark.post("/loginFailure", LoginController::processLogin, engine);
		Spark.post("/login", LoginController::processLogin, engine);
		 
		Spark.get("/", WardrobeController::init,engine);
		Spark.get("/guardarropas/:idGuardarropa", WardrobeController::indexViewDatosDeUnGuardarropa, engine);
		Spark.get("/guardarropas", WardrobeController::mostrarPrendas, engine);
		
		Spark.get("/altaPrenda", WardrobeController::indexViewAgregarPrenda, engine);	
		//Spark.post("/guardarropas/:idGuardarropa/prendas", WardrobeController::registrarPrenda);
		
		Spark.path("/eventos",  () -> {
			Spark.get("", WardrobeController::verEventos, engine); // creo que no tendria que ser Wardrobe, pero lo puse para ponerle un cliente a Event
			Spark.get("/alta", WardrobeController::agregarEvento, engine);
			Spark.post("/alta", WardrobeController::processAgregarEvento);
		});
		
		Spark.get("/calificaciones", WardrobeController::indexObtenerAtuendosCalificar,engine);
		Spark.post("/calificaciones", WardrobeController::califico,engine);
		Spark.get("/sugerencias", WardrobeController::indexObtenerAtuendosCalificar,engine);

		Spark.get("/out", WardrobeController::logOut, engine); // este boton no esta en nuestro tp, pero lo puse porque si
	}

}