package TestDeDominio;

import java.io.IOException;
import java.util.Calendar;

import Dominio.ClothingClasses.Estilo;
import Dominio.UserClasses.Usuario;

public class TestWeather {
	private static Usuario juan = new Usuario("Juan", "juan@hotmail.com");
	private static Estilo formal = Estilo.FORMAL;
	

	public static void main(String[] args) throws IOException {
		juan.crearNuevoGuardarropas(formal);
		System.out.println(juan.guardarropas.get(0).pedirTemperatura(0, 12));
		
		juan.crearEvento(new Calendar.Builder().setCalendarType("iso8601")
                .setDate(2019, 7, 18).setTimeOfDay(15, 00, 0).build(),
                "Profesor Vidal 3875", 
                Estilo.FORMAL);
		
		System.out.print(juan.getEventos().get(0).getFecha().get(Calendar.DAY_OF_MONTH));
		System.out.print("/");
		System.out.print(juan.getEventos().get(0).getFecha().get(Calendar.MONTH+1));
		System.out.print("/");
		System.out.print(juan.getEventos().get(0).getFecha().get(Calendar.YEAR));
		System.out.print(" at ");
		System.out.print(juan.getEventos().get(0).getFecha().get(Calendar.HOUR_OF_DAY));
		System.out.print(" hours, ");
		System.out.print(juan.getEventos().get(0).getFecha().get(Calendar.MINUTE));
		System.out.print(" minutes.");
	}
}
