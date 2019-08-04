package Dominio;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;


public class OPW {
	public final int darTemperaturaActual(Int day) 
	{
		boolean isMetric = true;
		String owmApiKey = "16f235a56d673331a2e0adcda0544ba5"; /* YOUR OWM API KEY HERE */
		String weatherCity = "Buenos Aires,AR";
		byte forecastDays = 5;
		OpenWeatherMap.Units units = (isMetric)
				? OpenWeatherMap.Units.METRIC
				: OpenWeatherMap.Units.IMPERIAL;
		OpenWeatherMap owm = new OpenWeatherMap(units, owmApiKey);
		try 
		{
			DailyForecast forecast = owm.dailyForecastByCityName(weatherCity, forecastDays);
			System.out.println("Weather for: " + forecast.getCityInstance().getCityName());
			int numForecasts = forecast.getForecastCount();

			DailyForecast.Forecast dayForecast = forecast.getForecastInstance(day);
			DailyForecast.Forecast.Temperature temperature = dayForecast.getTemperatureInstance();
//			System.out.println("\t" + dayForecast.getDateTime());
			return ((temperature.getMinimumTemperature() + 
					temperature.getMaximumTemperature())
					/2);
		}
		catch (IOException | JSONException e) {
			return (-404);
		}
	}
}


