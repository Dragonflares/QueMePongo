package Dominio;

public class OPWAdaptado implements WeatherAdapter{
	private OPW openWeather;
	
	public OPWAdaptado (OPW openWeather) {
		this.openWeather = openWeather;
	}
	
	public int darTemperaturaActual(Int day) {
		openWeather.darTemperaturaActual(day);
	}
}
