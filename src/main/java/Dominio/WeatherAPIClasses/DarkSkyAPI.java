package Dominio.WeatherAPIClasses;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import ch.rasc.darksky.DsClient;
import ch.rasc.darksky.model.DsBlock;
import ch.rasc.darksky.model.DsDataPoint;
import ch.rasc.darksky.model.DsResponse;
import ch.rasc.darksky.model.DsTimeMachineRequest;
import ch.rasc.darksky.model.DsUnit;



public class DarkSkyAPI {
	public final double darTemperaturaActual(long day, int hora) throws IOException
	{
		DsClient client = new DsClient("9ee9579e309df4f7c0806b5e60b90e71");
		DsTimeMachineRequest request = DsTimeMachineRequest.builder()
				.includeBlock(DsBlock.HOURLY)
				.latitude("-34.6157437").longitude("-58.5733854").unit(DsUnit.SI)
				.time(ZonedDateTime.now().plusDays(day).toEpochSecond()).build();

		double[] result = new double[24];
		int ix = 0;
		DsResponse response = client.sendTimeMachineRequest(request);
		for (DsDataPoint dataPoint : response.hourly().data()) {
			ZoneId zoneId = ZoneId.of(response.timezone());
			Instant instant = Instant.ofEpochSecond(dataPoint.time());
			ZonedDateTime time = ZonedDateTime.ofInstant(instant, zoneId);
			
			result[ix++] = dataPoint.temperature().doubleValue();
		}
		return result[hora];
	}

	public String darCondicionClimatica(long day, int hora) throws IOException{
		DsClient client = new DsClient("9ee9579e309df4f7c0806b5e60b90e71");
		DsTimeMachineRequest request = DsTimeMachineRequest.builder()
				.includeBlock(DsBlock.HOURLY, DsBlock.DAILY, DsBlock.ALERTS)
				.latitude("-34.6157437").longitude("-58.5733854").unit(DsUnit.SI)
				.time(ZonedDateTime.now().plusDays(day).toEpochSecond()).build();
// clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
		List<String> result = new ArrayList<String>();
		DsResponse response = client.sendTimeMachineRequest(request);
		for (DsDataPoint dataPoint : response.hourly().data()) {
			ZoneId zoneId = ZoneId.of(response.timezone());
			Instant instant = Instant.ofEpochSecond(dataPoint.time());
			ZonedDateTime time = ZonedDateTime.ofInstant(instant, zoneId);
			
			
			result.add(dataPoint.icon().toString());
		}
		return result.get(hora);
	}
	
}
