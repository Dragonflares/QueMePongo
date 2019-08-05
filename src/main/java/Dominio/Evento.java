package Dominio;

import java.util.Date;

public class Evento {
	private Date fecha;
	private String direccion;
	
	public Evento (Date fecha, String direccion)
	{
		this.fecha = fecha;
		this.direccion = direccion;
	}
}
