package controllers;

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
	private static List<Prenda> prendasSeleccionadas = null;
	
	public static ModelAndView init(Request req, Response res) {
		
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("guardarropas", usuario.getGuardarropas());

		return new ModelAndView(viewModel, "home/guardarropas.hbs");
	}
	
	public static ModelAndView verEventos(Request req, Response res) throws InterruptedException {
		EventController.usuario = usuario;
		
		HashMap<String, Object> viewModel = new HashMap<>();
		/*
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
		// el archivo eventitos antes de que se haga la modificación. Pd: no funciona*/
		//String evs = getJSONEventos();
		//System.out.println(evs); // TODO SACAR
		//viewModel.put("eventitos", evs.replaceAll("\"", "&quot;"));
		viewModel.put("eventitos", usuario.getEventos());
		
		return new ModelAndView(viewModel, "home/seleccionarFecha.hbs");
	}
	
	private static String getJSONEventos()
	{
		//String data = "var data = ";
		JsonArray jsonArr = new JsonArray();
		
		usuario.getEventos().forEach(e -> {
			JsonObject obj = new JsonObject();
			
			obj.addProperty("id", e.getId());
			obj.addProperty("title", e.getNombre());
			obj.addProperty("estilo", e.getEstilo().toString());
			obj.addProperty("importancia", e.getImportanciaEvento().getImportancia());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			obj.addProperty("start", e.getFechaLocalDateTime().format(formatter));
			obj.addProperty("allDay", false);
			obj.addProperty("className", "important");
			
			jsonArr.add(obj);
			
		});
		
		Gson gson = new Gson();
		return gson.toJson(jsonArr) + ";";
		//return "[{id:1,title: "Cumpleaños', estilo:'ELEGANTE_SPORT', importanci:'Media', start:'2019-11-19', allDay: false,className:'important'},{id:2,title:'Casamiento',estilo:'ELEGANTE',importancia:'Alta',start:'2019-11-26',allDay:false,className:'important}];"; 
				
		//return jsonArr;
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
		guardarropaSeleccionado = ((Usuario) FactoryRepositorioUsuario.get().buscarTodos().get(usuario.getId())).getGuardarropas().get(id);	
		
		HashMap<String, Object> viewModel = new HashMap<>();
		//viewModel.put("id", guardarropaSeleccionado.getId() );
		viewModel.put("guardarropas", guardarropaSeleccionado);
		viewModel.put("prendasDisponibles", guardarropaSeleccionado.getPrendasDisponibles());
		
		String ahre2 = guardarropaSeleccionado.getPrendasDisponibles().get(0).getNombrePrenda();
		//String ahre = guardarropaSeleccionado.getPrendasDisponibles().get(0).getTipoRopa().getNombre();
		System.out.println(ahre2);
		
		
		return new ModelAndView(viewModel, "home/prendas.hbs");
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
