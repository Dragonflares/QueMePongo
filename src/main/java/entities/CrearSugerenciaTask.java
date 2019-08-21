package entities;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import Dominio.UserClasses.Usuario;
import Repositorios.RepoEvento;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

class CrearSugerenciaTask extends TimerTask{

	private final static int HORA = 2;
    private final static int MINUTOS = 0;
	
	@Override
	public void run()
	{
		RepoEvento.getInstance().getEventosProximos()
			.forEach(e -> 
			{
				Usuario creador = e.getCreador();
					try {
						creador.agregarSugerencia(creador.pedirRecomendacion(e)); // TODO ver cuando hagamos lo de notificacion
					} catch (Exception e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} 
			});
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
