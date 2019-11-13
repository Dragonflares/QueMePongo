package Dominio.WeatherAPIClasses;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GestorClimatico{

	private static List<WeatherAdapter> weatherAdapters = new ArrayList<WeatherAdapter>();
	private static GestorClimatico instance = new GestorClimatico();
	private WeatherAdapter principal;
	
	private GestorClimatico ()
	{
		weatherAdapters.add(new DarkSkyAdaptado());
		weatherAdapters.add(new OPWAdaptado());
		this.principal = weatherAdapters.get(0);

	}
	
	public static GestorClimatico getInstance()
	{
		return instance;
	}

	public double obtenerTemperatura(LocalDateTime fecha, int time) throws IOException
	{
		long diferenciaDias = ChronoUnit.DAYS.between(LocalDateTime.now(), fecha);
		double temp = -404;
		int actualForecaster = 0;
		while(temp == -404 && weatherAdapters.size() != actualForecaster)
		{
			WeatherAdapter forecaster = weatherAdapters.get(actualForecaster);
			temp = forecaster.darTemperaturaActual(diferenciaDias, time);
			actualForecaster++;
		}
		return temp;
	}
	
	public  String darCondicionClimatica(LocalDateTime dia, int time) throws IOException
	{
		
		long diferenciaDias = ChronoUnit.DAYS.between(LocalDateTime.now(), dia);
		String condicion = null;
		int actualForecaster = 0;
		while(condicion == null && weatherAdapters.size() != actualForecaster)
		{
			WeatherAdapter forecaster = weatherAdapters.get(actualForecaster);
			condicion = forecaster.darCondicionClimatica(diferenciaDias, time);
			actualForecaster++;
		}
		
		setPrincipal(weatherAdapters.get(actualForecaster));
		
		return condicion;
	}

	public static List<WeatherAdapter> getWeatherAdapters() {
		return weatherAdapters;
	}

	public static void setWeatherAdapters(List<WeatherAdapter> weatherAdapters) {
		GestorClimatico.weatherAdapters = weatherAdapters;
	}

	public WeatherAdapter getPrincipal() {
		return principal;
	}

	public void setPrincipal(WeatherAdapter principal) {
		this.principal = principal;
	}
}
