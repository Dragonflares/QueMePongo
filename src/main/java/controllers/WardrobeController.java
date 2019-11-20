package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Dominio.ClothingClasses.Prenda;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import Repositorios.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class WardrobeController {
	public static Usuario usuario;
	private static String cadena;
	private static List<Guardarropa> guardarropas;
	private static Guardarropa guardarropaSeleccionado = null;
	
	public static ModelAndView init(Request req, Response res) {
		
		//HashMap<String, Object> viewModel = new HashMap<>();
		
		//viewModel.put("usuario", usuario);
		//viewModel.put("guardarropas", usuario.getGuardarropas());

		return new ModelAndView(usuario, "home/guardarropas.hbs");
	}
	
	public static ModelAndView verEventos(Request req, Response res) throws InterruptedException {
		EventController.usuario = usuario;
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		try (FileWriter file = new FileWriter("src/main/resources/public/js/eventitos.js")) {
			//File Writer creates a file in write mode at the given location 
			file.write(getJSONEventos());

			//write function is use to write in file,
			//here we write the Json object in the file
			file.flush();
			file.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		//Thread.sleep(8000); // dejar, hace que espere un tiempo porque sino cuando hace un new ModelAndView lee 
		// el archivo eventitos antes de que se haga la modificaci�n. Pd: no funciona
		return new ModelAndView(viewModel, "home/seleccionarFecha.hbs");
	}
	
	private static String getJSONEventos()
	{
		String data = "var data = ";
		JsonArray jsonArr = new JsonArray();
		
		usuario.getEventos().forEach(e -> {
			JsonObject obj = new JsonObject();
			
			obj.addProperty("id", e.getId());
			obj.addProperty("title", e.getNombre());
			obj.addProperty("estilo", e.getEstilo().toString());
			obj.addProperty("importancia", e.getImportanciaEvento().getImportancia());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			obj.addProperty("start", e.getFecha().format(formatter));
			obj.addProperty("allDay", false);
			obj.addProperty("className", "important");
			
			jsonArr.add(obj);
			
		});
		
		Gson gson = new Gson();
		return data + gson.toJson(jsonArr) + ";";
	}
	
	
	public static ModelAndView indexViewDatosGenerales(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
			
		// TODO esta harcodeado, cambiar
//		guardarropas = ((Usuario) FactoryRepositorioUsuario.get().buscarPorId().get(req.session().attribute("username"))).getGuardarropas();
		guardarropaSeleccionado = null;
		viewModel.put("guardarropas", guardarropas);
		return new ModelAndView(
				viewModel, 
				"home/guardarropas.hbs"); 
	}
	
	public static ModelAndView indexViewDatosDeUnGuardarropa(Request req, Response res) {
				
		int id=Integer.parseInt(req.params(":idGuardarropa"));
		
		// TODO esta harcodeado, cambiar. PD: en el otro tp tenian un repo, que en este caso seria RepoGuardarropa
		guardarropaSeleccionado = ((Usuario) FactoryRepositorioUsuario.get().buscarTodos().get(usuario.getId())).getGuardarropas().get(id);	
				
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
