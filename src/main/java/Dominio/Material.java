package Dominio;

public class Material {

	String nombre;

	private String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}	

	public Material (String nuevoNombre) {

		setNombre(nuevoNombre);

	}



	public boolean compararNombres(String otroNombre){


		return this.getNombre().equals(otroNombre);
	}


}
