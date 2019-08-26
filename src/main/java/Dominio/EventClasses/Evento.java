package Dominio.EventClasses;

import java.util.Calendar;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;

public class Evento {
	private Calendar fecha;
	private String direccion;
	private Estilo estilo;
	private Frecuencia frecuencia; 
	private ImportanciaEvento importancia;
	private Atuendo sugerencia;
	private boolean sugerenciaNotificada;
	
	
	public Evento (Calendar fecha, String direccion, Estilo estilo, Frecuencia frecuencia)
	{
		this.fecha = fecha;
		this.direccion = direccion;
		this.estilo = estilo;
		this.frecuencia = frecuencia;
		this.sugerenciaNotificada = false;
	}
	
	public Estilo getEstilo()
	{
		return this.estilo;
	}
	
	public Calendar getFecha()
	{
		return this.fecha;
	}
	
	public boolean estaProximo()
	{
		return importancia.estaProximo(this);
	}
	
	public boolean ocurre(Calendar fechaInteres)
	{
		return this.fecha.compareTo(fechaInteres) == 0;
	}
	
	public boolean esFrecuente() 
	{
		return this.frecuencia != null;
	}
	
	public void actualizarFecha()
	{
		this.frecuencia.actualizarFecha(this);
		this.sugerenciaNotificada = false;
	}
	
	public void sumarDias(int dias)
	{
		this.fecha.add(Calendar.DAY_OF_YEAR, dias);
	}
	
	public void agregarSugerencia(Atuendo sugerencia)
	{
		this.sugerencia = sugerencia;
	}
	
	public Atuendo getSugerencia()
	{
		return this.sugerencia;
	}
	
	public boolean getSeNotificoSugerencia()
	{
		return sugerenciaNotificada;
	}
	
	public void setFecha(Calendar fecha)
	{
		this.fecha = fecha;
	}
	
	public void setSeNotificoSugerencia(Boolean bool)
	{
		this.sugerenciaNotificada = bool;
	}
}
