package Dominio.NotificationAPIClasses;

import Dominio.UserClasses.Usuario;

public class SMSAdaptado implements NotificadorAdapter{
	private SMS sms;
	
	public SMSAdaptado()
	{
		this.sms = new SMS();
	}

	public void notificarSugerencia(Usuario usuario) {
		String mensaje = "Se ha creado la sugerencia para un evento creado. Para verlo tiene que abrir la aplicación.";
		this.enviarSMS(usuario.getNumeroCelular(), mensaje);
	}
	
	public void enviarSMS(String numeroCelular, String mensaje) {
		sms.enviarSMS(mensaje, numeroCelular);
	}
}
