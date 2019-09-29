package Dominio.UserClasses;

import java.io.FileNotFoundException;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import Dominio.ClothingClasses.Prenda;
import Dominio.WardrobeClasses.Guardarropa;
import db.EntidadPersistente;

@Entity
@Table(name = "tipoDeUsuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo") // "gratuito" o "premium"
public abstract class TipoDeUsuario extends EntidadPersistente{
	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda) throws Exception {}
	public void cambiarCategoria(Usuario usuario) throws FileNotFoundException {}
}

