package Dominio.EventClasses;

import java.util.Calendar;

public class Mensualmente extends Anualmente{

	@Override
	public void actualizarFecha(Evento evento) {
		
		this.frecuencia = evento.getFecha().toLocalDate().lengthOfMonth();
		super.actualizarFecha(evento);
	}
	
	
}
