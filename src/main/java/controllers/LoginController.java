package controllers;

import java.util.HashMap;

import Dominio.UserClasses.Usuario;
import Repositorios.factories.FactoryRepositorioUsuario;
import spark.Session;

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
		return new ModelAndView(model, "home/loginMelisa.hbs");
	}

	public static ModelAndView loginFailure(Request req, Response res) {

		return new ModelAndView(null,"home/errorLoginMelisa.hbs");
	}

	public static ModelAndView processLogin(Request req, Response res) {

		validarUsernamePassword(req, res);
		String username = req.queryParams("usuario");
		String password = req.queryParams("contrasenia");

		if (!FactoryRepositorioUsuario.get().existeUsuario(username, password)) {
			res.status(400);
			res.redirect("/loginFailure");
		} else {
			res.status(200);
			req.session().attribute("username", username);

			WardrobeController.usuario = FactoryRepositorioUsuario.get().buscarUsuario(username, password);
			res.redirect("/");
		}

		return null;
	}

	public static Void logout(Request req, Response res) {
        Session session = req.session(true);
        session.invalidate();
        req.session().removeAttribute("username");
        res.redirect("/login");
        return null;
    }

}
