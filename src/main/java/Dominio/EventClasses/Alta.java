package Dominio.EventClasses;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("alta")
public class Alta extends ImportanciaEvento{
	public boolean estaProximo(Evento evento)
	{
		return this.diasEntre(evento.getFecha(), Calendar.getInstance()) <= 30;
	}
}
