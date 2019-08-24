package Dominio.EventClasses;

public abstract class Frecuencia {
	protected int frecuencia;
	
	public void actualizarFecha(Evento evento)
	{
		evento.sumarDias(frecuencia);
	}
}
