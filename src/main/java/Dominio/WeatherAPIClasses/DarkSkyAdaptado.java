package Dominio.WeatherAPIClasses;

import java.io.IOException;

public class DarkSkyAdaptado implements WeatherAdapter{
	private DarkSkyAPI DarkSky;
	
	public DarkSkyAdaptado () {
		this.DarkSky = new DarkSkyAPI();
	}
	
	public double darTemperaturaActual(long day, int hora) throws IOException {
		return DarkSky.darTemperaturaActual(day, hora);
	}
	
	public String darCondicionClimatica(long day, int hora) throws IOException
	{
		return DarkSky.darCondicionClimatica(day, hora);
	}
}
