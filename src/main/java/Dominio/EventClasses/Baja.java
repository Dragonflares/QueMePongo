package Dominio.EventClasses;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("baja")
public class Baja extends ImportanciaEvento{
	public boolean estaProximo(Evento evento)
	{
		return ChronoUnit.DAYS.between(evento.getFecha(), LocalDateTime.now()) <= 1;
	}

	@Override
	public String getImportancia() {
		return "Baja";
	}
}
