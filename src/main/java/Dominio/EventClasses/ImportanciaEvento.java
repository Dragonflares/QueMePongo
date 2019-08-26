package Dominio.EventClasses;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class ImportanciaEvento {
	public abstract boolean estaProximo(Evento evento);
}
