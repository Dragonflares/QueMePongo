package Dominio;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;


public class OPWClimatica {
	public static final void darTemperaturaActual() {
	    boolean isMetric = true;
	    String owmApiKey = "16f235a56d673331a2e0adcda0544ba5"; /* YOUR OWM API KEY HERE */
	    String weatherCity = "Buenos Aires,AR";
	    byte forecastDays = 5;
	    OpenWeatherMap.Units units = (isMetric)
	        ? OpenWeatherMap.Units.METRIC
	        : OpenWeatherMap.Units.IMPERIAL;
	    OpenWeatherMap owm = new OpenWeatherMap(units, owmApiKey);
	    try {
	      DailyForecast forecast = owm.dailyForecastByCityName(weatherCity, forecastDays);
	      System.out.println("Weather for: " + forecast.getCityInstance().getCityName());
	      int numForecasts = forecast.getForecastCount();
	      for (int i = 0; i < numForecasts; i++) {
	        DailyForecast.Forecast dayForecast = forecast.getForecastInstance(i);
	        DailyForecast.Forecast.Temperature temperature = dayForecast.getTemperatureInstance();
	        System.out.println("\t" + dayForecast.getDateTime());
	        System.out.println("\tTemperature: " + temperature.getMinimumTemperature() +
	            " to " + temperature.getMaximumTemperature() + "\n");
	      }
	    }
	    catch (IOException | JSONException e) {
	      e.printStackTrace();
	    }
	  }
	}

