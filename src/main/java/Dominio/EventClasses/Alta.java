package Dominio.EventClasses;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("alta")
public class Alta extends ImportanciaEvento{
	public boolean estaProximo(Evento evento)
	{
		return ChronoUnit.DAYS.between(evento.getFecha(), LocalDateTime.now()) <= 30;
	}

	@Override
	public String getImportancia() {
		return "Alta";
	}
}
