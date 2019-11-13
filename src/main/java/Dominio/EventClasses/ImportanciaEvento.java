package Dominio.EventClasses;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import db.EntidadPersistente;

@Entity
@Table(name = "importanciaEvento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEvento") // "baja", "media" y "alta"
public abstract class ImportanciaEvento extends EntidadPersistente{
	public abstract boolean estaProximo(Evento evento);
}
