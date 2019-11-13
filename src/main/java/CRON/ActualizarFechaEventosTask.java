package CRON;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import Dominio.UserClasses.Usuario;
import Repositorios.factories.FactoryRepositorioUsuario;

public class ActualizarFechaEventosTask extends TimerTask{

	private final static int HORA = 01;
    private final static int MINUTOS = 00;
    
	@Override
	public void run() {
		LocalDateTime ayer = LocalDateTime.now().minusDays(1);
		
		List<Usuario> usuarios = FactoryRepositorioUsuario.get().getUsuariosConEventosOcurridoFrecuentemente(ayer);
		
		usuarios.stream().forEach
		(
				u ->  u.getEventosOcurridoFrecuentemente(ayer).forEach
				(
						e ->
						e.actualizarFecha()
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
