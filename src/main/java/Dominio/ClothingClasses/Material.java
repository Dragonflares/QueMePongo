package Dominio.ClothingClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import db.EntidadPersistente;

@Entity
@Table(name = "material")
public class Material extends EntidadPersistente{
	@Column(name = "nombre")
	private String nombre;
	
	public Material () {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	

	public Material (String nuevoNombre) {
		setNombre(nuevoNombre);
	}
	
	public boolean compararNombres(String otroNombre){
		return this.getNombre().equals(otroNombre);
	}
}
