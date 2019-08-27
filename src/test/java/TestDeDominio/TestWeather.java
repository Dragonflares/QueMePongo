package TestDeDominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import Dominio.WeatherAPIClasses.GestorClimatico;

public class TestWeather {
	private Calendar dia;
	
	@Before
	public void init() {
		dia = Calendar.getInstance();
		dia.add(Calendar.DATE, 15);
	}
	@Test
	public void probarTemperatura() throws IOException {
		assertNotEquals(-404, GestorClimatico.getInstance().obtenerTemperatura(dia, 4));
	}
	@Test
	public void noVaASerDeNoche() throws IOException {
		assertNotEquals("CLEAR_NIGHT", GestorClimatico.getInstance().darCondicionClimatica(dia, 8));
	}
	@Test
	public void NoVaASerDeDiaSinNubes() throws IOException {
		assertNotEquals("CLEAR_DAY", GestorClimatico.getInstance().darCondicionClimatica(dia, 12));
	}
	//Nota, el test anterior puede fallar porque queria probar algo ajajaj
	@Test
	public void NoVaAHaberMasDe20Grados() throws IOException {
		assertFalse("No hay más de 20 grados", GestorClimatico.getInstance().obtenerTemperatura(dia, 4) > 20);
	}
}
