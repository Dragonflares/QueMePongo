package Dominio.EventClasses;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("media")
public class Media extends ImportanciaEvento{
	
	@Override
	public boolean estaProximo(Evento evento) {
		return ChronoUnit.DAYS.between(evento.getFechaLocalDateTime(), LocalDateTime.now()) <= 7;
	}

	@Override
	public String getImportancia() {
		return "Media";
	}
}
