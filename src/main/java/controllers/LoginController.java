package controllers;

import Dominio.UserClasses.Usuario;
import Repositorios.factories.FactoryRepositorioUsuario;

import server.Cifrado;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class LoginController {
	
	public static ModelAndView validarLogin(Request req, Response res) {
		if (req.session().attribute("username") == null) {
			res.redirect("home/loginQmp.hbs");
			Spark.halt();
		}
		return null;
	}

	public static ModelAndView init(Request req, Response res) {
		return new ModelAndView(null, "home/calificaratuendo.hbs");
	}

	public static ModelAndView processLogin(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");
		password = Cifrado.Encrypt(req.queryParams("password"));
		if (!FactoryRepositorioUsuario.get().existeUsuario(username, password)) {
			res.status(400);
			res.redirect("home/loginQmp.hbs");
		} else {
			Usuario usuario = FactoryRepositorioUsuario.get().buscarUsuario(password, username);
			res.status(200);
			req.session().attribute("username", username);
			
			UserController.usuario = FactoryRepositorioUsuario.get().buscar(usuario.getId());
			res.redirect("/user");
		}
		return null;
	}
}
