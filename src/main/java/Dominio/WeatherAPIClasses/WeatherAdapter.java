package Dominio.WeatherAPIClasses;

import java.io.IOException;
import java.util.Calendar;

public interface WeatherAdapter {
	public double darTemperaturaActual(long diferenciaDias, int time)throws IOException;
	public String darCondicionClimatica(long diferenciaDias, int hora) throws IOException;
}
