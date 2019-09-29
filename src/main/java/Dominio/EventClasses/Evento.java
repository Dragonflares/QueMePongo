package Dominio.EventClasses;

import java.util.Calendar;
import java.util.List;

import Dominio.ClothingClasses.Atuendo;
import Dominio.Estilish.Estilo;

public class Evento {
	private String nombre;
	private Calendar fecha;
	private String direccion;
	private Estilo estilo;
	private Frecuencia frecuencia; 
	private ImportanciaEvento importancia;
	private List<Atuendo> sugerencias;
	private boolean sugerenciaNotificada;
	
	
	public Evento (String nombre, Calendar fecha, String direccion, Estilo estilo, Frecuencia frecuencia)
	{
		this.nombre = nombre;
		this.fecha = fecha;
		this.direccion = direccion;
		this.estilo = estilo;
		this.frecuencia = frecuencia;
		this.sugerenciaNotificada = false;
	}
	
	public String getNombre()
	{
		return this.nombre;
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
		this.sugerencias.add(sugerencia);
	}
	
	public Atuendo getUltimaSugerencia()
	{
		return sugerencias.get(sugerencias.size() - 1); 
	}
	
	public boolean getSeNotificoUltimaSugerencia()
	{
		return sugerenciaNotificada;
	}
	
	public void setFecha(Calendar fecha)
	{
		this.fecha = fecha;
	}
	
	public void setSeNotificoUltimaSugerencia(Boolean bool)
	{
		this.sugerenciaNotificada = bool;
	}
}
