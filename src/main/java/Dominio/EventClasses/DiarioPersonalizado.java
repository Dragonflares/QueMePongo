package Dominio.EventClasses;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class DiarioPersonalizado extends Frecuencia{
	private List<DayOfWeek> queDiasRepite;
	
	public DiarioPersonalizado(List<DayOfWeek> queDias)
	{
		this.frecuencia = 1;
		this.queDiasRepite = queDias;
	}

	@Override
	public void actualizarFecha(Evento evento) {
		
		do {
			evento.sumarDias(this.frecuencia);
			
		}while(!this.esDeTipoCompatible(evento.getFechaLocalDateTime()));
	}
	
	private boolean esDeTipoCompatible(LocalDateTime fecha)
	{
		return queDiasRepite.stream().anyMatch(tipo -> 
		
			 tipo.equals(fecha.getDayOfWeek())
		);
	}
	
	
	
}
