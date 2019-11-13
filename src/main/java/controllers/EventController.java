package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import Dominio.UserClasses.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EventController {
	public static Usuario usuario;
	
	public static ModelAndView verEventosDeUnaFecha(Request req, Response res) 
	{
		HashMap<String, Object> viewModel = new HashMap<>();
		String fecha = req.queryParams("fecha");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a"); 
		
		LocalDateTime fechaElegida = LocalDateTime.parse(fecha, formatter);
		
		viewModel.put("cliente", usuario);
		viewModel.put("eventos", usuario.getEventosEn(fechaElegida));
		
		return new ModelAndView(viewModel, "home/listaDeEventos.hbs");
	}
}
