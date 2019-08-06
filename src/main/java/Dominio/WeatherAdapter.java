package Dominio;

import java.io.IOException;

public interface WeatherAdapter {
	public double darTemperaturaActual(int day, int time)throws IOException;
}
