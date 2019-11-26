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
	
	//los degrees son en base al amarillo, rancio fuerte
	@Column(name = "degrees")
	private int degrees;
	
	public Color() {}
	
	public Color(String nuevoNombre) {	
		setNombre(nuevoNombre);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getDegrees() {
		return degrees;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean compararNombres(String otroNombre){
		return this.getNombre().equals(otroNombre);
	}
}
