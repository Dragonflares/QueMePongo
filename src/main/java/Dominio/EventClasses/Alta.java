package Dominio.EventClasses;

import java.util.Calendar;

public class Alta extends ImportanciaEvento{
	public boolean estaProximo(Evento evento)
	{
		return this.diasEntre(evento.getFecha(), Calendar.getInstance()) <= 30;
	}
}
