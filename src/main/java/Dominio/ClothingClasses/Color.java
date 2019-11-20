package Dominio.ClothingClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import db.EntidadPersistente;

@Entity
@Table(name = "color")
public class Color extends EntidadPersistente{
	@Column(name = "nombre")
	private String nombre;
	
	public Color(String nuevoNombre) {	
		setNombre(nuevoNombre);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean compararNombres(String otroNombre){
		return this.getNombre().equals(otroNombre);
	}
}
