package Dominio;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


import ch.rasc.darksky.DsClient;
import ch.rasc.darksky.model.DsDataPoint;
import ch.rasc.darksky.model.DsResponse;
import ch.rasc.darksky.model.DsTimeMachineRequest;
import ch.rasc.darksky.model.DsUnit;



public class DarkSkyAPI {
	public final float darTemperaturaActual(int day) throws IOException
	{
		DsClient client = new DsClient("9ee9579e309df4f7c0806b5e60b90e71");
		DsTimeMachineRequest request = DsTimeMachineRequest.builder()
		        .latitude("-34.6157437")
				.longitude("-58.5733854")				
				.unit(DsUnit.SI)
				.time(ZonedDateTime.now().minusDays(-day).toEpochSecond())
				.build();
		DsResponse response = client.sendTimeMachineRequest(request);
		for (DsDataPoint dataPoint : response.hourly().data()) {			
		    ZoneId zoneId = ZoneId.of(response.timezone());
		    Instant instant = Instant.ofEpochSecond(dataPoint.time());
		    ZonedDateTime time = ZonedDateTime.ofInstant(instant, zoneId);

		    System.out.print(time);
		    System.out.print(": ");
		    System.out.println(dataPoint);            
		}
		float temp = 0;
		return temp;
	}
}
