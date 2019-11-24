package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Alta;
import Dominio.EventClasses.Baja;
import Dominio.EventClasses.Evento;
import Dominio.EventClasses.ImportanciaEvento;
import Dominio.EventClasses.Media;
import Dominio.UserClasses.Usuario;
import Repositorios.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EventController {
	public static Usuario usuario;
	
	public static ModelAndView agregarEvento(Request req, Response res) 
	{
		HashMap<String, Object> viewModel = new HashMap<>();
		String fechita = req.queryParams("fecha").split("G")[0];
		System.out.println(req.queryParams("fecha"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd yyyy HH:mm:ss ", Locale.ENGLISH); 
		LocalDateTime fechaElegida = LocalDateTime.parse(fechita, formatter);
		
		viewModel.put("cliente", usuario);
		//"11/30/2019"
		
		String fechaStr =  String.valueOf(fechaElegida.getMonthValue())
				+ "/" + String.valueOf(fechaElegida.getDayOfMonth()) 
				+ "/" + String.valueOf(fechaElegida.getYear());
		
		System.out.println(fechaStr);
		
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
		LocalDateTime fechaElegida = LocalDateTime.parse(req.queryParams("fechaEvento"), formatter);
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
