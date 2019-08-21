package Dominio.UserClasses;

import java.util.Calendar;
import java.util.Date;

import Dominio.ClothingClasses.Estilo;

public class Evento {
	private Calendar fecha;
	private String direccion;
	private Estilo estilo;
	private Usuario creador;
	private int frecuencia; //diaria: 1, semanal: 7, mensual: 30
	private ImportanciaEvento importancia;
	
	public Evento (Calendar fecha, String direccion, Estilo estilo, Usuario creador)
	{
		this.creador = creador;
		this.fecha = fecha;
		this.direccion = direccion;
		this.estilo = estilo;
	}
	
	public Estilo getEstilo()
	{
		return this.estilo;
	}
	
	public Calendar getFecha()
	{
		return this.fecha;
	}
	
	public Usuario obtenerCreador() {
		return this.creador;
	}
	
	public boolean estaProximo()
	{
		return importancia.estaProximo(this);
	}
}
