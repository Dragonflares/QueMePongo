package controllers;

import Dominio.UserClasses.VerificarUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.RequestUtil;
import spark.Session;

public class LoginController {
	
	public static ModelAndView loginFailure(Request req, Response res) {
		
		return new ModelAndView(null,"/home/errorLogin.hbs");
	}
	
	public static ModelAndView adminHome(Request req, Response res)
	{
		return new ModelAndView(null,"/admin/adminBase.hbs");
	}
	
	public static ModelAndView userHome(Request req, Response res)
	{
		return new ModelAndView(null,"/home/usuario.hbs");
	}
	
    public static Void login(Request req, Response res) {
    	
        if (VerificarUsuario.verificar(RequestUtil.getQueryUsername(req), RequestUtil.getQueryPassword(req)))
        {	
        	if(VerificarUsuario.verificar(RequestUtil.getQueryUsername(req), RequestUtil.getQueryPassword(req))) //validar por si el usuario se encuentra en la bd
        	{	
        		Session session = req.session(true);
        		session.attribute("currentUser", req.queryParams("usuario"));
        		session.attribute("role", "user");
        		res.redirect("/usuario");
        	}
        	
        	else
        	{	
        		Session session = req.session(true);
        		session.attribute("currentUser", req.queryParams("usuario"));
        		session.attribute("role", "admin");
            	res.redirect("/admin");
        	}
        }
        
        else
            {
                res.redirect("/loginFailure");
            }
        
        return null;
    }

    public static Void logout(Request req, Response res) {
        Session session = req.session(true);
        session.invalidate();
        req.session().removeAttribute("currentUser");
        req.session().removeAttribute("role");
        res.redirect("/");
        return null;
    }
}
