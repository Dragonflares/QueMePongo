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
			res.redirect("login/Login-View.html");
			Spark.halt();
		}
		return null;
	}
	
	public static ModelAndView init(Request req, Response res) {
		
		res.redirect("login/Login-View.html");
		return null;
	}

	public static ModelAndView processLogin(Request req, Response res) {
		String username = req.queryParams("usuario");
		String password = req.queryParams("contrasenia");	

		
		//password = Cifrado.Encrypt(req.queryParams("password"));
		if (!FactoryRepositorioUsuario.get().existeUsuario(username, password)) {
			System.out.println("------------------NO EXISTE USUARIO------------------");
			res.status(400);
			res.redirect("login/Login-View.html");
		} else {
			System.out.println("------------------EXISTE USUARIO------------------");
			Usuario usuario = FactoryRepositorioUsuario.get().buscarUsuario(username, password);
			res.status(200);
			req.session().attribute("username", username);
			
			UserController.usuario = FactoryRepositorioUsuario.get().buscar(usuario.getId());
			res.redirect("/user");
		}
		return null;
	}
}
