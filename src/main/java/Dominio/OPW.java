package Dominio;

import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.UrlConnectionDataWeatherClient;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.daily.DailyForecast;
import org.openweathermap.api.query.Language;
import org.openweathermap.api.query.QueryBuilderPicker;
import org.openweathermap.api.query.UnitFormat;
import org.openweathermap.api.query.forecast.daily.ByCityName;



public class OPW {
	private static final String API_KEY = "16f235a56d673331a2e0adcda0544ba5";

	public final double darTemperaturaActual(int day, int time)
	{
		DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);
		ByCityName byCityNameForecast = QueryBuilderPicker.pick()
				.forecast()                                         
				.daily()                                            
				.byCityName("Buenos Aires")                           
				.countryCode("AR")                                  
				.unitFormat(UnitFormat.METRIC)                     
				.language(Language.ENGLISH)                      
				.build();
		ForecastInformation<DailyForecast> forecastInformation = client.getForecastInformation(byCityNameForecast);
		int a = 1;
		double sender = -404;
		for (DailyForecast forecast : forecastInformation.getForecasts()) 
		{
			if(a == day || a == 5)
			{
				String auxiliary = forecast.getTemperature().toString();
				sender = Double.parseDouble(auxiliary);
				break;
			}
			else
			{
				a++;
			}
		}
		return sender;
	}
}