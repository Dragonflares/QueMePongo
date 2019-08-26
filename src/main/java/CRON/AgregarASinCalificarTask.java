package CRON;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import Dominio.UserClasses.Usuario;
import Repositorios.RepoUsuario;

public class AgregarASinCalificarTask extends TimerTask{

	private final static int HORA = 00;
    private final static int MINUTOS = 00;	
	
	@Override
	public void run() {
		Calendar ayer = Calendar.getInstance();
		ayer.add(Calendar.DATE, -1); // le resto un dia a la fecha actual 
		
		List<Usuario> usuarios = RepoUsuario.getInstance().getUsuariosConEventosOcurridos(ayer);
		
		usuarios.stream().forEach
		(
				u ->  u.getEventosOcurridos(ayer).forEach
				(
						e ->
						u.agregarSugerenciaSinCalificar(e.getSugerencia())
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
