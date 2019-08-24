package Dominio.EventClasses;

import java.util.Calendar;

public class Mensualmente extends Anualmente{

	@Override
	public void actualizarFecha(Evento evento) {
		
		this.frecuencia = evento.getFecha().getActualMaximum(Calendar.DAY_OF_MONTH);
		super.actualizarFecha(evento);
	}
	
	
}
