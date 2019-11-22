package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;

import Dominio.UserClasses.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EventController {
	public static Usuario usuario;
	
	public static ModelAndView agregarEvento(Request req, Response res) 
	{
		HashMap<String, Object> viewModel = new HashMap<>();
		String fechita = req.queryParams("fecha").split("G")[0];
		//String fechita = fechata.substring(0, fechata.length()-1);;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd yyyy HH:mm:ss ", Locale.ENGLISH); 
		LocalDateTime fechaElegida = LocalDateTime.parse(fechita, formatter);
		
		viewModel.put("cliente", usuario);
		viewModel.put("fecha", fechaElegida);
		
		return new ModelAndView(viewModel, "home/altaEvento.hbs");
	}
}
