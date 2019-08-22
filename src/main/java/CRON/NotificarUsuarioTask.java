package CRON;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import Dominio.NotificationAPIClasses.Notificador;
import Repositorios.RepoUsuario;

public class NotificarUsuarioTask extends TimerTask{
// https://stackoverflow.com/questions/9375882/how-i-can-run-my-timertask-everyday-2-pm
	
	private final static int HORA = 7; 
	private final static int MINUTOS = 0;
	
	@Override
	public void run() { 
		RepoUsuario.getInstance().getUsuariosConEventosProximos()
			.forEach(u -> Notificador.getInstance().notificarSugerencia(u));
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
