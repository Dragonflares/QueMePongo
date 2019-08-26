package Dominio.WeatherAPIClasses;

import java.io.IOException;

public class OPWAdaptado implements WeatherAdapter{
	private OPW openWeather;
	
	public OPWAdaptado () {
		this.openWeather = new OPW();
	}
	
	public double darTemperaturaActual(long day, int time) {
		return openWeather.darTemperaturaActual(day, time);
	}


	public String darCondicionClimatica(long day, int hora) throws IOException {
		return null;
	}
}
