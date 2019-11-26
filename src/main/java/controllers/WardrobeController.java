package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Alta;
import Dominio.EventClasses.Baja;
import Dominio.EventClasses.Evento;
import Dominio.EventClasses.ImportanciaEvento;
import Dominio.EventClasses.Media;
import Dominio.UserClasses.Bien;
import Dominio.UserClasses.Calificadores;
import Dominio.UserClasses.Caluroso;
import Dominio.UserClasses.Frio;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import Repositorios.factories.FactoryRepositorioUsuario;
import config.Config;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class WardrobeController {
	public static Usuario usuario;
	private static String cadena;
	private static List<Guardarropa> guardarropas;
	private static Guardarropa guardarropaSeleccionado = null;
	private static List<Prenda> prendasSeleccionadas = null;
	private static Evento eventoEnCuestion = null;
	
	public static ModelAndView init(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("guardarropas", usuario.getGuardarropas());

		return new ModelAndView(viewModel, "home/guardarropas.hbs");
	}

	public static ModelAndView indexViewDatosDeUnGuardarropa(Request req, Response res) {

		int id=Integer.parseInt(req.params(":idGuardarropa"));

		if(Config.useDataBase)
		{
			id--;
		}

		guardarropaSeleccionado = usuario.getGuardarropas().get(id);	
		res.redirect("/guardarropas");

		return null;
	}

	public static ModelAndView mostrarPrendas(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("guardarropas", guardarropaSeleccionado);
		viewModel.put("prendasDisponibles", guardarropaSeleccionado.getPrendasDisponibles());
		return new ModelAndView(viewModel, "home/prendas.hbs");
	}

	
	
	
	
	public static ModelAndView mostrarSugerencias(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();

		List<Evento> eventosProximos = usuario.getEventosProximosYnotificados().stream()
				.filter(e->e.getUltimoAtuendoAceptado() == null)
				.collect(Collectors.toList());
		
		viewModel.put("eventosProximos", eventosProximos);
		
		List<Evento> eventosNoUsados = usuario.getEventosProximosYnotificados().stream()
				.filter(e->e.getUltimoAtuendoAceptado()!= null).collect(Collectors.toList());
		viewModel.put("eventosNoUsados", eventosNoUsados);
		return new ModelAndView(viewModel, "home/sugerencias.hbs");
		
	}


	public static ModelAndView generaRecomendacion(Request req, Response res) throws Exception {
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		String estilo = req.queryParams("estilo");
		
		Estilo nuevoEstilo;

		switch(estilo) {

		case "Formal" :{

			nuevoEstilo =   Estilo.FORMAL;
			break;
		}

		case "Casual" :{

			nuevoEstilo = Estilo.CASUAL;
			break;
		}
		case "Elegante" :{

			nuevoEstilo = Estilo.ELEGANTE;
			break;
		}
		case "Deportivo" :{

			nuevoEstilo = Estilo.DEPORTIVO;
			break;
		}case "Elegante_Sport" :{

			nuevoEstilo = Estilo.ELEGANTE_SPORT;
			break;
		}
		

		default : nuevoEstilo = Estilo.FORMAL;
		}
		ImportanciaEvento importancia = new Alta();
		LocalDateTime fecha = LocalDateTime.now();
		eventoEnCuestion = new Evento(fecha.toString(),fecha,"casa",nuevoEstilo,null,importancia);
		usuario.agregarEvento(eventoEnCuestion);
		Atuendo atuendoSugerencia = usuario.pedirRecomendacion(eventoEnCuestion);
		
		viewModel.put("prendasUltimaSugerencia", atuendoSugerencia.getPrendas());
		
		return new ModelAndView(viewModel, "home/recomendacion.hbs");
	}



	public static ModelAndView envioSugerencia(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();

		String evento = req.queryParams("evento");
		eventoEnCuestion = usuario.getEventos().stream()
				.filter(e->e.getNombre().equals(evento)).collect(Collectors.toList()).get(0);
		List<Prenda> prendasUltimaSugerencia = eventoEnCuestion.getUltimaSugerencia().getPrendas();
		viewModel.put("prendasUltimaSugerencia", prendasUltimaSugerencia);

		return new ModelAndView(viewModel, "home/recomendacion.hbs");
	}


	public static ModelAndView indexViewAgregarPrenda(Request req, Response res) {

		return new ModelAndView(null, "home/altaPrenda.hbs");
	}

	//---------------------------------Calificaciones-------------------------

	public static ModelAndView indexObtenerAtuendosCalificar(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		//viewModel.put("id", guardarropaSeleccionado.getId() );
		viewModel.put("evento", usuario.getEventos().stream()
				.filter(event -> event.yaOcurrio() 
						&& usuario.getAtuendosSinCalificar().contains(event.getUltimoAtuendoAceptado()))
				.collect(Collectors.toList()));
		return new ModelAndView(viewModel, "home/calificaratuendo.hbs");
	}

	public static ModelAndView califico(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		String temperaturaInferior = req.queryParams("temperaturaParteInferior");
		String temperaturaSuperior = req.queryParams("temperaturaParteSuperior");
		String evento = req.queryParams("evento");
		Evento eventoObject =  usuario.getEventos().stream().filter(event->event.getNombre()
				.equals(evento)).collect(Collectors.toList()).get(0);
		Atuendo elAtuendo = eventoObject.getUltimoAtuendoAceptado();
		System.out.println(evento);

		System.out.println(temperaturaInferior);
		System.out.println(temperaturaSuperior);

		Calificadores superior;
		Calificadores inferior;

		switch(temperaturaInferior) {

		case "Frio" :{

			inferior = (Calificadores) new Frio();
			break;
		}

		case "Caluroso" :{

			inferior = (Calificadores) new Caluroso();
			break;
		}
		case "Bien" :{

			inferior = (Calificadores) new Bien();
			break;
		}

		default : inferior = new Bien();
		}

		switch(temperaturaSuperior) {

		case "Frio" :{

			superior = (Calificadores) new Frio();
			break;
		}

		case "Caluroso" :{

			superior = (Calificadores) new Caluroso();
			break;
		}
		case "Bien" :{

			superior = (Calificadores) new Bien();
			break;
		}
		default : superior = new Bien();

		}

		usuario.calificar(elAtuendo, superior, inferior);
		eventoObject.setUltimoAtuendoAceptado(null);
		System.out.println(usuario.getOffsetInferior());
		System.out.println(usuario.getOffsetSuperior());
		viewModel.put("evento", usuario.getEventos().stream()
				.filter(event -> event.yaOcurrio() 
						&& usuario.getAtuendosSinCalificar().contains(event.getUltimaSugerencia()))
				.collect(Collectors.toList()));
		return new ModelAndView(viewModel, "home/calificaratuendo.hbs");
	}

	//---------------------------------Log out---------------------------------

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

		viewModel.put("fecha", fechaStr);
		viewModel.put("estilos", Estilo.values());

		return new ModelAndView(viewModel, "home/altaEvento.hbs");
	}

	public static Void processAgregarEvento(Request req, Response res) {
		//30/11/2019 00:00
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.ENGLISH); 
		LocalDateTime fechaElegida = LocalDateTime.parse(req.queryParams("fecha"), formatter);
		Estilo estilo = Estilo.valueOf(req.queryParams("estilo"));
		
		Evento eventoAAgregar = new Evento(req.queryParams("nombreEvento"), fechaElegida, req.queryParams("direccionEvento"), estilo,null,null);
		usuario.agregarEvento(eventoAAgregar);
		FactoryRepositorioUsuario.get().modificar(usuario);
		res.redirect("/eventos");
		return null;
	}
}
