 package Dominio.NotificationAPIClasses;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
	private static List<NotificadorAdapter> notificadoresAdapters = new ArrayList<NotificadorAdapter>();
	private static Notificador instance = new Notificador();
	
	private Notificador() // dejar en privado para que no puedan hacer otra instancia
	{
		notificadoresAdapters.add(new MailAdaptado());
		notificadoresAdapters.add(new SMSAdaptado());
	}
	
	public static Notificador getInstance()
	{
		return instance;
	}
	
	public void notificar(NotificacionDataObject notificacion)
	{
		notificadoresAdapters.forEach(n -> n.notificar(notificacion));
		notificacion.getEvento().setSeNotificoUltimaSugerencia(true);
	}
}
