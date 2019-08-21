package entities;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class NotificarUsuarioTask extends TimerTask{
// https://stackoverflow.com/questions/9375882/how-i-can-run-my-timertask-everyday-2-pm
	
	private final static int HORA = 7; // TODO ver que hora fijamos
	// Creo que la notificación habría que enviarla a la mañana como hace facebook para los cumpleaños
	private final static int MINUTOS = 0;
	
	@Override
	public void run() { 
			 // TODO notificar al usuario que sugerencia que puede usar 
	}
			
	public void empezar()
	{
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, HORA);
		today.set(Calendar.MINUTE, MINUTOS);
		today.set(Calendar.SECOND, 0);
		
		Timer timer = new Timer();
		
		timer.schedule(this, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
	}
	

}
