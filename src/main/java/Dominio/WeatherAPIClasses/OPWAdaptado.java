package Dominio.WeatherAPIClasses;

import java.io.IOException;

public class OPWAdaptado implements WeatherAdapter{
	private OPW openWeather;
	
	public OPWAdaptado (OPW openWeather) {
		this.openWeather = openWeather;
	}
	
	public double darTemperaturaActual(int day, int time) {
		return openWeather.darTemperaturaActual(day, time);
	}


	public String darCondicionClimatica(int day, int hora) throws IOException {
		return null;
	}
}
