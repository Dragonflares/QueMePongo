package Dominio.WeatherAPIClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorClimatico{

	private static List<WeatherAdapter> weatherAdapters = new ArrayList<WeatherAdapter>();

	public GestorClimatico (List<WeatherAdapter> weatherAdapters)
	{
		GestorClimatico.weatherAdapters = weatherAdapters;
	}

	public static double obtenerTemperatura(int date, int time) throws IOException
	{
		double temp = -404;
		int actualForecaster = 0;
		while(temp == -404 && weatherAdapters.size() != actualForecaster)
		{
			WeatherAdapter forecaster = weatherAdapters.get(actualForecaster);
			temp = forecaster.darTemperaturaActual(date, time);
			actualForecaster++;
		}
		return temp;
	}
	
	public static String darCondicionClimatica(int date, int time) throws IOException
	{
		String condicion = null;
		int actualForecaster = 0;
		while(condicion == null && weatherAdapters.size() != actualForecaster)
		{
			WeatherAdapter forecaster = weatherAdapters.get(actualForecaster);
			condicion = forecaster.darCondicionClimatica(date, time);
			actualForecaster++;
		}
		return condicion;
	}
}
