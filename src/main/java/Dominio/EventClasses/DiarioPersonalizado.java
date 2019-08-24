package Dominio.EventClasses;

import java.util.Calendar;
import java.util.List;

public class DiarioPersonalizado extends Frecuencia{
	private List<Integer> queDiasRepite;
	
	public DiarioPersonalizado(List<Integer> queDias)
	{
		this.frecuencia = 1;
		this.queDiasRepite = queDias;
	}

	@Override
	public void actualizarFecha(Evento evento) {
		
		do {
			evento.sumarDias(this.frecuencia);
			
		}while(!this.esDeTipoCompatible(evento.getFecha()));
	}
	
	private boolean esDeTipoCompatible(Calendar fecha)
	{
		return queDiasRepite.stream().anyMatch(tipo -> 
		
			 tipo.equals(fecha.get(Calendar.DAY_OF_WEEK))
		);
	}
	
	
	
}
