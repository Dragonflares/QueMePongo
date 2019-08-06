package Dominio;

import java.io.IOException;

public interface WeatherAdapter {
	public double darTemperaturaActual(int day, int time)throws IOException;
	public String darCondicionClimatica(int day, int hora) throws IOException;
}
