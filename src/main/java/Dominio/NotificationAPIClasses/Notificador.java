package Dominio.NotificationAPIClasses;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
	private static List<NotificadorAdapter> notificadoresAdapters = new ArrayList<NotificadorAdapter>();
	
	public Notificador()
	{
		notificadoresAdapters.add(new MailAdaptado());
		notificadoresAdapters.add(new SMSAdaptado());
	}
}
