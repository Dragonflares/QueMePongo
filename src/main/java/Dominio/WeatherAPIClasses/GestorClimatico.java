package Dominio.WeatherAPIClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

	public double obtenerTemperatura(Calendar fecha, int time) throws IOException
	{
		long diferenciaDias = this.diasEntre(Calendar.getInstance(), fecha);
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
	
	public  String darCondicionClimatica(Calendar dia, int time) throws IOException
	{
		long diferenciaDias = this.diasEntre(Calendar.getInstance(), dia);
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
	
	private long diasEntre(Calendar diaMenor, Calendar diaMayor)
	{ 	
		Date diaMenorDate = this.convertirDate(diaMenor);
		Date diaMayorDate = this.convertirDate(diaMayor);

		return ( diaMayorDate.getTime() - diaMenorDate.getTime() )/(24 * 60 * 60 * 1000); 		
	}
	
	private Date convertirDate(Calendar fecha)
	{
		int anio = fecha.get(Calendar.YEAR); 
		int mes = fecha.get(Calendar.MONTH); 
		int dia = fecha.get(Calendar.DAY_OF_MONTH); 
		Calendar calendar = new GregorianCalendar(anio, mes-1, dia); 
		return new Date(calendar.getTimeInMillis());
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
