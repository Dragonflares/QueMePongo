package Dominio.UserClasses;

import java.util.Calendar;

public class Bajo extends ImportanciaEvento{
	public boolean estaProximo(Evento evento)
	{
		return this.diasEntre(evento.getFecha(), Calendar.getInstance()) <= 1;
	}
}
