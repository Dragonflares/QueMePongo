package Dominio.NotificationAPIClasses;

public class MailAdaptado implements NotificadorAdapter{
	private MailAPI mailAPI;
	
	public MailAdaptado()
	{
		this.mailAPI = new MailAPI();
	}
}
