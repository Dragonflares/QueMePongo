package Dominio.WeatherAPIClasses;

import java.util.List;

import com.github.prominence.openweathermap.api.DailyForecastRequester;
import com.github.prominence.openweathermap.api.HourlyForecastRequester;
import com.github.prominence.openweathermap.api.OpenWeatherMapManager;
import com.github.prominence.openweathermap.api.constants.Accuracy;
import com.github.prominence.openweathermap.api.constants.Language;
import com.github.prominence.openweathermap.api.constants.Unit;
import com.github.prominence.openweathermap.api.model.response.DailyForecast;
import com.github.prominence.openweathermap.api.model.response.DailyForecast.Forecast;
import com.github.prominence.openweathermap.api.model.response.HourlyForecast;


public class OPW {
	private static final String API_KEY = "a4de44d5100ae4a5b069a611b132dd4d";

	public final double darTemperaturaActual(int day, int time)
	{
		if(day < 5) {
			return darTemperatura5dias(day, time);
		}
		else {
			return darTemperatura16dias(day, time);
		}
	}

	
	
	private double darTemperatura16dias(int day, int time) {
		OpenWeatherMapManager openWeatherManager = new OpenWeatherMapManager(API_KEY);
		DailyForecastRequester forecastRequester = openWeatherManager.getDailyForecastRequester();
		DailyForecast forecastResponse;
		if(day < 16) {
			forecastResponse = forecastRequester.setAmountOfDays(day)
				    .getByCoordinates(-34.6157437, -58.5733854);
		}
		else {
			forecastResponse = forecastRequester.setAmountOfDays(16)
				    .getByCoordinates(-34.6157437, -58.5733854);
		}
		List<Forecast> forecasts = forecastResponse.getForecasts();
		Forecast dayForecast = forecasts.get(forecasts.size() - 1);
		if(time >= 4 && time < 10) 
			return dayForecast.getTemperature().getMorningTemperature();
		else if(time >= 10 && time < 14)
			return dayForecast.getTemperature().getDayTemperature();
		else if(time >= 14 && time < 19)
			return dayForecast.getTemperature().getEveningTemperature();
		else if(time >= 19 || time < 4)
			return dayForecast.getTemperature().getNightTemperature();
		else 
			return -404;
	}

	private double darTemperatura5dias(int day, int time) {
		OpenWeatherMapManager openWeatherManager = new OpenWeatherMapManager(API_KEY);
		HourlyForecastRequester forecastRequester = openWeatherManager.getHourlyForecastRequester();
		HourlyForecast forecastResponse = forecastRequester
			    .setLanguage(Language.ENGLISH)
			    .setUnitSystem(Unit.METRIC_SYSTEM)
			    .setAccuracy(Accuracy.ACCURATE)
			    .getByCoordinates(-34.6157437, -58.5733854);
		List<HourlyForecast.Forecast> forecasts = forecastResponse.getForecasts();
		int neededForecast = obtainForecastId(day, time);
		return forecasts.get(neededForecast).getWeatherInfo().getTemperature();
	}

	private int obtainForecastId(int day, int time) {
		if(((day*24 + time)%3) == 0 )
			return ((day*24 + time) / 3);
		else if (((day*24 + time)%3) == 1) 
			return ((day*24 + time - 1) / 3);
		else
			return ((day*24 + time + 1) / 3);
	}
}