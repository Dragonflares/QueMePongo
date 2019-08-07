package Dominio.WeatherAPIClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorClimatico{

	private List<WeatherAdapter> weatherAdapters = new ArrayList<WeatherAdapter>();

	public GestorClimatico (List<WeatherAdapter> weatherAdapters)
	{
		this.weatherAdapters = weatherAdapters;
	}

	public double obtenerTemperatura(int date, int time) throws IOException
	{
		double temp = -404;
		int actualForecaster = 0;
		while(temp == -404 && weatherAdapters.size() != actualForecaster)
		{
			WeatherAdapter forecaster = this.weatherAdapters.get(actualForecaster);
			temp = forecaster.darTemperaturaActual(date, time);
		}
		return temp;
	}
}
