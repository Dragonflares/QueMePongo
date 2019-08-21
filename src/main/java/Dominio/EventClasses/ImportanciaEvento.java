package Dominio.EventClasses;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class ImportanciaEvento {
	public abstract boolean estaProximo(Evento evento);
	
	public long diasEntre(Calendar diaMenor, Calendar diaMayor)
	{ 	
		Date diaMenorDate = this.convertirDate(diaMenor);
		Date diaMayorDate = this.convertirDate(diaMayor);

		return ( diaMayorDate.getTime() - diaMenorDate.getTime() )/(24 * 60 * 60 * 1000); 		
	}
	
	private Date convertirDate(Calendar fecha)
	{
		int a�o = fecha.get(Calendar.YEAR); 
		int mes = fecha.get(Calendar.MONTH); 
		int dia = fecha.get(Calendar.DAY_OF_MONTH); 
		Calendar calendar = new GregorianCalendar(a�o, mes-1, dia); 
		return new Date(calendar.getTimeInMillis());
	}
	
}
