package CRON;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import Dominio.ClothingClasses.Atuendo;
import Dominio.UserClasses.Usuario;
import Repositorios.RepoUsuario;

class CrearSugerenciaTask extends TimerTask{

	private final static int HORA = 2;
    private final static int MINUTOS = 0;
	
	@Override
	public void run()
	{
		// TODO contemplar el caso en el que no se genero la recomendacion. 
		// Deberiamos poner en algun lado de que no se tiene que generar la notificacion
		List<Usuario> usuarios = RepoUsuario.getInstance().getUsuariosConEventosProximosYSinNotificar();
		usuarios.stream().forEach
		(
				u -> u.getEventosProximos().forEach
				(
						e -> 
						{
							Atuendo sugerencia;
							try {
								sugerencia = u.pedirRecomendacion(e);
								
								e.agregarSugerencia(sugerencia);
							}
							catch (Exception e1) {
								e1.printStackTrace();
							}
						}
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
