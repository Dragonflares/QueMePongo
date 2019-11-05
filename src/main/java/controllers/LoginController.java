package controllers;

import java.util.HashMap;

import Dominio.UserClasses.Usuario;
import Repositorios.factories.FactoryRepositorioUsuario;

import server.Cifrado;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class LoginController {

	public static ModelAndView validarUsernamePassword(Request req, Response res) {
		if (req.queryParams("usuario") == null ||  req.queryParams("contrasenia") == null  ) {
			res.redirect("/login");
			Spark.halt();
		}
		return null;
	}

	public static ModelAndView init(Request req, Response res) {

		HashMap<String, Object> model = new HashMap<>();
		return new ModelAndView(model, "home/login2.hbs");
	}

	public static ModelAndView processLogin(Request req, Response res) {


		validarUsernamePassword(req, res);
		String username = req.queryParams("usuario");
		System.out.println(username);
//		String password = req.queryParams("contrasenia");


//		password = Cifrado.Encrypt(req.queryParams("password"));
//		if (!FactoryRepositorioUsuario.get().existeUsuario(username, password)) {
//			System.out.println("------------------NO EXISTE USUARIO------------------");
//			res.status(400);
//			res.redirect("/login");
//		} else {
//			System.out.println("------------------EXISTE USUARIO------------------");
//			Usuario usuario = FactoryRepositorioUsuario.get().buscarUsuario(username, password);
//			res.status(200);
//			req.session().attribute("username", username);
//
//			UserController.usuario = FactoryRepositorioUsuario.get().buscar(usuario.getId());
//			res.redirect("/");
//		}
//		return null;
		req.session().attribute("username",username);
		res.redirect("/");
		return null;
	}



	



}
