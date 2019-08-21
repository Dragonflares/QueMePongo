package Dominio.NotificationAPIClasses;

public class SMSAdaptado implements NotificadorAdapter{
	private SMS sms;
	
	public SMSAdaptado()
	{
		this.sms = new SMS();
	}
}
