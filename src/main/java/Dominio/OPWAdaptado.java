package Dominio;

public class OPWAdaptado implements WeatherAdapter{
	private OPW openWeather;
	
	public OPWAdaptado (OPW openWeather) {
		this.openWeather = openWeather;
	}
	
	public float darTemperaturaActual(int day) {
		return openWeather.darTemperaturaActual(day);
	}
}
