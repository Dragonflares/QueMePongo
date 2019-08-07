package Dominio.UserClasses;

import java.util.Date;

import Dominio.ClothingClasses.Estilo;

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
