package Dominio.ClothingClasses;

public class Color {
	
String nombre;

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}
	
	

	public Color(String nuevoNombre) {
		
		
		setNombre(nuevoNombre);
		
		
	}
	
	
	public boolean compararNombres(String otroNombre){


		return this.getNombre().equals(otroNombre);
	}


}
