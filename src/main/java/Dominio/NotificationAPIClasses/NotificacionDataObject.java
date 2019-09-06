package Dominio.NotificationAPIClasses;

import Dominio.EventClasses.Evento;

public class NotificacionDataObject {
	private String emisorMail;
	private String contrasenia;
	private String destinatarioMail;
	private String subject;
	private String mensaje;
	private String emisorNumero;
	private String destinatarioNumero;
	private Evento evento;
	
	public String getEmisorMail()
	{
		return this.emisorMail;
	}
	
	public void setEmisorMail(String emisorMail)
	{
		this.emisorMail = emisorMail;
	}
	
	public String getContrasenia()
	{
		return this.contrasenia;
	}
	
	public void setContrasenia(String contrasenia)
	{
		this.contrasenia = contrasenia;
	}
	
	public String getDestinatarioMail()
	{
		return this.destinatarioMail;
	}
	
	public void setDestinatarioMail(String destinatarioMail)
	{
		this.destinatarioMail = destinatarioMail;
	}
	
	public String getSubject()
	{
		return this.subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getMensaje()
	{
		return this.mensaje;
	}
	
	public void setMensaje(String mensaje)
	{
		this.mensaje = mensaje;
	}
	
	public String getEmisorNumero()
	{
		return this.emisorNumero;
	}
	
	public void setEmisorNumero(String emisorNumero)
	{
		this.emisorNumero = emisorNumero;
	}
	
	public String getDestinatarioNumero()
	{
		return this.destinatarioNumero;
	}
	
	public void setDestinatarioNumero(String destinatarioNumero)
	{
		this.destinatarioNumero = destinatarioNumero;
	}
	
	public Evento getEvento()
	{
		return this.evento;
	}
	
	public void setEvento(Evento evento)
	{
		this.evento = evento;
	}
}
