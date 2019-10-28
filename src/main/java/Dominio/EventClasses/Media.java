package Dominio.EventClasses;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("media")
public class Media extends ImportanciaEvento{
	
	@Override
	public boolean estaProximo(Evento evento) {
		return this.diasEntre(evento.getFecha(), Calendar.getInstance()) <= 7;
	}
}
