package Dominio.EventClasses;

import java.util.Calendar;

public class Medio extends ImportanciaEvento{

	@Override
	public boolean estaProximo(Evento evento) {
		return this.diasEntre(evento.getFecha(), Calendar.getInstance()) <= 7;
	}

}
