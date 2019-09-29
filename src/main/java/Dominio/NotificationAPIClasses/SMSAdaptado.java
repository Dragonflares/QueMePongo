package Dominio.NotificationAPIClasses;

import Dominio.UserClasses.Usuario;

public class SMSAdaptado implements NotificadorAdapter{
	private SMS sms;
	
	public SMSAdaptado()
	{
		this.sms = new SMS();
	}

	public void notificar(NotificacionDataObject notificacion) {
		sms.enviarSMS(notificacion.getEmisorNumero(), notificacion.getDestinatarioNumero(), notificacion.getMensaje()); 
	}
}
