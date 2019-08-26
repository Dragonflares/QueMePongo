package CRON;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import Dominio.NotificationAPIClasses.Notificador;
import Dominio.UserClasses.Usuario;
import Repositorios.RepoUsuario;

public class NotificarUsuarioTask extends TimerTask{
	
	private final static int HORA = 7; 
	private final static int MINUTOS = 0;
	
	@Override
	public void run() { 
		List<Usuario> usuarios = RepoUsuario.getInstance().getUsuariosConEventosProximosYSinNotificar();
				
		
		if(!usuarios.isEmpty())
			usuarios.forEach
			(
					u -> u.getEventosProximosYsinNotificar().forEach
					(
							e -> Notificador.getInstance().notificarSugerencia(u,e)
					)
			);
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
