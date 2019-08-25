package Dominio.NotificationAPIClasses;

import Dominio.UserClasses.Usuario;

public class MailAdaptado implements NotificadorAdapter{
	private MailAPI mailAPI;
	
	public MailAdaptado()
	{
		this.mailAPI = new MailAPI();
	}
	
	public void notificarSugerencia(Usuario usuario)
	{
		String subject = "Ya se tiene lista una sugerencia!";
		String body = "Se ha creado la sugerencia para un evento creado. Para verlo tiene que abrir la aplicación.";
		this.enviarMail(usuario, subject, body);
	}
	
	public void enviarMail(Usuario usuario, String subject, String body)
	{
		/*mailAPI.enviarMail(usuario.getMail(), subject, body);*/
	}
}
