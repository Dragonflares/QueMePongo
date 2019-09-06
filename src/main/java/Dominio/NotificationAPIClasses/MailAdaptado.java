package Dominio.NotificationAPIClasses;

import Dominio.UserClasses.Usuario;

public class MailAdaptado implements NotificadorAdapter{
	private MailAPI mailAPI;
	
	public MailAdaptado()
	{
		this.mailAPI = new MailAPI();
	}
	
	public void notificar(NotificacionDataObject notificacion)
	{
		mailAPI.enviarMail(notificacion.getEmisorMail(), notificacion.getContrasenia(), notificacion.getDestinatarioMail(), notificacion.getSubject(), notificacion.getMensaje());
	}
}
