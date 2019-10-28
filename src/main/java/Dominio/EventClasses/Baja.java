package Dominio.EventClasses;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("baja")
public class Baja extends ImportanciaEvento{
	public boolean estaProximo(Evento evento)
	{
		return this.diasEntre(evento.getFecha(), Calendar.getInstance()) <= 1;
	}
}
