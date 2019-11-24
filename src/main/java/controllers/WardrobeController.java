package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Alta;
import Dominio.EventClasses.Baja;
import Dominio.EventClasses.Evento;
import Dominio.EventClasses.ImportanciaEvento;
import Dominio.EventClasses.Media;
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
	
	public static ModelAndView indexObtenerAtuendosCalificar(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		//viewModel.put("id", guardarropaSeleccionado.getId() );
		viewModel.put("evento", usuario.getEventos().stream().filter(event -> event.yaOcurrio()).collect(Collectors.toList()));
		return new ModelAndView(viewModel, "home/calificaratuendo.hbs");
	}
	
	public static ModelAndView logOut(Request req, Response res) {

		req.session().removeAttribute("username");
		res.redirect("/"); 
		return null;
	}
	
	
	// ------------------------------- Eventos -------------------------------
	public static ModelAndView verEventos(Request req, Response res) throws InterruptedException {
		
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("eventitos", usuario.getEventos());
		
		return new ModelAndView(viewModel, "home/seleccionarFecha.hbs");
	}
	
	public static ModelAndView agregarEvento(Request req, Response res) 
	{
		HashMap<String, Object> viewModel = new HashMap<>();
		String fechita = req.queryParams("fecha").split("G")[0];
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd yyyy HH:mm:ss ", Locale.ENGLISH); 
		LocalDateTime fechaElegida = LocalDateTime.parse(fechita, formatter);
		
		//"11/30/2019"
		
		String fechaStr =  String.valueOf(fechaElegida.getMonthValue())
				+ "/" + String.valueOf(fechaElegida.getDayOfMonth()) 
				+ "/" + String.valueOf(fechaElegida.getYear());
		
		List<String> importanciasStr = new ArrayList<String>();
		importanciasStr.add("Alta");
		importanciasStr.add("Media");
		importanciasStr.add("Baja");
				
		viewModel.put("fecha", fechaStr);
		viewModel.put("estilos", Estilo.values());
		viewModel.put("importancias", importanciasStr);
		
		return new ModelAndView(viewModel, "home/altaEvento.hbs");
	}
	
	public static Void processAgregarEvento(Request req, Response res) {
		//30/11/2019 00:00
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.ENGLISH); 
		LocalDateTime fechaElegida = LocalDateTime.parse(req.queryParams("fecha"), formatter);
		Estilo estilo = Estilo.valueOf(req.queryParams("estilo"));
		ImportanciaEvento importanciaEvento;
		String importanciaStr = req.queryParams("importancia");
		// TODO se les ocurre otra cosa?
		if(importanciaStr == "Alta")
		{
			importanciaEvento = new Alta();
		}
		else if (importanciaStr == "Media")
		{
			importanciaEvento = new Media();
		}
		else
		{
			importanciaEvento = new Baja();
		}
		Evento eventoAAgregar = new Evento(req.queryParams("nombreEvento"), fechaElegida, req.queryParams("direccionEvento"), estilo,null,importanciaEvento);
		usuario.agregarEvento(eventoAAgregar);
		FactoryRepositorioUsuario.get().modificar(usuario);
		res.redirect("/eventos");
		return null;
	}
}
