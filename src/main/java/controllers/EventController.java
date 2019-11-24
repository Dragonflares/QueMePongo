package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import Dominio.Estilish.Estilo;
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
}
