package Dominio;

import java.io.IOException;

public class DarkSkyAdaptado implements WeatherAdapter{
	private DarkSkyAPI DarkSky;
	
	public DarkSkyAdaptado (DarkSkyAPI DarkSky) {
		this.DarkSky = DarkSky;
	}
	
	public double darTemperaturaActual(int day, int hora) throws IOException {
		return DarkSky.darTemperaturaActual(day, hora);
	}
	
	public String darCondicionClimatica(int day, int hora) throws IOException
	{
		return DarkSky.darCondicionClimatica(day, hora);
	}
}
