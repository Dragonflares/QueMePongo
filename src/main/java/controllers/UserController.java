package controllers;

import java.util.HashMap;
import java.util.List;

import Dominio.ClothingClasses.Prenda;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import Repositorios.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {
	public static Usuario usuario;
	private static String cadena;
	private static List<Guardarropa> guardarropas;
	private static Guardarropa guardarropaSeleccionado = null;
	
	
	public static ModelAndView indexViewDatosGenerales(Request req, Response res) {
		System.out.println("EN INDEX"); // todo sacar
		HashMap<String, Object> viewModel = new HashMap<>();
			
		// TODO esta harcodeado, cambiar
		guardarropas = ((Usuario) FactoryRepositorioUsuario.get().buscarTodos().get(0)).getGuardarropas();
		guardarropaSeleccionado = null;
		viewModel.put("guardarropas", guardarropas);
		return new ModelAndView(
				viewModel, 
				"home/guardarropas.hbs"); 
	}
	
	public static ModelAndView indexViewDatosDeUnGuardarropa(Request req, Response res) {
		
		
	int id=Integer.parseInt(req.params(":idGuardarropa"));
	
	// TODO esta harcodeado, cambiar. PD: en el otro tp tenian un repo, que en este caso seria RepoGuardarropa
	guardarropaSeleccionado = ((Usuario) FactoryRepositorioUsuario.get().buscarTodos().get(0)).getGuardarropas().get(0);	
		
	
	HashMap<String, Object> viewModel = new HashMap<>();
	viewModel.put("id", guardarropaSeleccionado.getId() );
	viewModel.put("guardarropa", guardarropaSeleccionado);
	return new ModelAndView(
			viewModel, 
			"home/prendas.hbs");
	}
	
	public static ModelAndView indexViewAgregarPrenda(Request req, Response res) {
		cadena=req.params(":idGuardarropa");
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("id", cadena ); 

		return new ModelAndView(
					viewModel, 
					"home/altaPrenda.hbs");
			
	}
	
	public static Void registrarPrenda(Request req, Response res) {	
		String id=req.params("idGuardarropa");	
		String tipo=req.queryParams("tipo"); 
		
		// TODO esta harcodeado, cambiar. PD: en el otro tp tenian un repo, que en este caso seria RepoGuardarropa
		guardarropaSeleccionado = ((Usuario) FactoryRepositorioUsuario.get().buscarTodos().get(0)).getGuardarropas().get(0);
		
		Prenda prenda = new Prenda(req.queryParams("nombrePrenda"));
		guardarropaSeleccionado.agregarPrenda(prenda);
		
		
		// tener repo prenda? No lo creo
		//RepoClientes.getInstanceOfSingleton().persistirCliente(clienteSeleccionado);
		
		res.redirect("/user/guardarropas/"+id+"");
		return null;
	}
	
	public static ModelAndView logOut(Request req, Response res) {

		req.session().removeAttribute("username");
		res.redirect("/"); 
		return null;
	}
}
