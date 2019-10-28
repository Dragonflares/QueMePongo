package Dominio.EventClasses;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import db.EntidadPersistente;

@Entity
@Table(name = "importanciaEvento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEvento") // "baja", "media" y "alta"
public abstract class ImportanciaEvento extends EntidadPersistente{
	public abstract boolean estaProximo(Evento evento);
	
	public long diasEntre(Calendar diaMenor, Calendar diaMayor)
	{ 	
		Date diaMenorDate = this.convertirDate(diaMenor);
		Date diaMayorDate = this.convertirDate(diaMayor);

		return ( diaMayorDate.getTime() - diaMenorDate.getTime() )/(24 * 60 * 60 * 1000); 		
	}
	
	private Date convertirDate(Calendar fecha)
	{
		int anio = fecha.get(Calendar.YEAR); 
		int mes = fecha.get(Calendar.MONTH); 
		int dia = fecha.get(Calendar.DAY_OF_MONTH); 
		Calendar calendar = new GregorianCalendar(anio, mes-1, dia); 
		return new Date(calendar.getTimeInMillis());
	}
}
