package CRON;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import Dominio.NotificationAPIClasses.NotificacionDataObject;
import Dominio.NotificationAPIClasses.Notificador;
import Dominio.UserClasses.Usuario;
import Repositorios.factories.FactoryRepositorioUsuario;

public class NotificarUsuarioTask extends TimerTask{
	
	private final static int HORA = 7; 
	private final static int MINUTOS = 0;
	
	@Override
	public void run() { 
		List<Usuario> usuarios = FactoryRepositorioUsuario.get().getUsuariosConEventosProximosYSinNotificar();
				
		
		if(!usuarios.isEmpty())
			usuarios.forEach
			(
					u -> u.getEventosProximosYsinNotificar().forEach
					(
							e -> 
							{
								NotificacionDataObject notificacion = new NotificacionDataObject();
								
								notificacion.setEmisorMail("melisarodrig2@gmail.com");
								notificacion.setContrasenia("contra");
								notificacion.setDestinatarioMail(u.getMail());
								notificacion.setSubject("Ya se tiene lista una sugerencia!");
								notificacion.setMensaje("Se ha creado la sugerencia para el evento" + e.getNombre() +". Para verlo tiene que abrir la aplicación.");
								notificacion.setEmisorNumero("+13343262392");
								notificacion.setDestinatarioNumero(u.getNumeroCelular());
								notificacion.setEvento(e);
								
								Notificador.getInstance().notificar(notificacion);
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
