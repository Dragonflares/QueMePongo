package Dominio;

public class DarkSkyAdaptado implements WeatherAdapter{
	private DarkSkyAPI DarkSky;
	
	public DarkSkyAdaptado (DarkSkyAPI DarkSky) {
		this.DarkSky = DarkSky;
	}
	
	public float darTemperaturaActual(int day) {
		return DarkSky.darTemperaturaActual(day);
	}
}
