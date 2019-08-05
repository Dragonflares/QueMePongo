package Dominio;

import java.util.Date;

public class Evento {
	private Date fecha;
	private String direccion;
	private Estilo estilo;
	
	
	public Evento (Date fecha, String direccion, Estilo estilo)
	{
		this.fecha = fecha;
		this.direccion = direccion;
		this.estilo = estilo;
	}
}
