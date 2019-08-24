package Dominio.EventClasses;

import java.util.Calendar;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;

public class Evento {
	private Calendar fecha;
	private String direccion;
	private Estilo estilo;
	private int frecuencia; //diaria: 1, semanal: 7, mensual: 30 
	private ImportanciaEvento importancia;
	private Atuendo sugerencia;
	private boolean sugerenciaNotificada;
	
	public Evento (Calendar fecha, String direccion, Estilo estilo, int frecuencia)
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
		return this.frecuencia > 0;
	}
	
	public void actualizarFecha()
	{
		fecha.add(Calendar.DAY_OF_YEAR, this.frecuencia); 
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
}
