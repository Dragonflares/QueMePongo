package Dominio.NotificationAPIClasses;

import Dominio.UserClasses.Usuario;

public class SMSAdaptado implements NotificadorAdapter{
	private SMS sms;
	
	public SMSAdaptado()
	{
		this.sms = new SMS();
	}

	@Override
	public void notificarSugerencia(Usuario usuario) {
		// TODO 
	}
}
