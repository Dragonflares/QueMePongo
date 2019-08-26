package Dominio.ClothingClasses;

public class Material {

	String nombre;

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
