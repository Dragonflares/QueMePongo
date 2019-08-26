package Dominio.WeatherAPIClasses;

import java.io.IOException;

public interface WeatherAdapter {
	public double darTemperaturaActual(long diferenciaDias, int time)throws IOException;
	public String darCondicionClimatica(long day, int hora) throws IOException;
}
