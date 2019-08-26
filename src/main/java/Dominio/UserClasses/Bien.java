package Dominio.UserClasses;

import Dominio.Property;

public  class Bien implements Calificadores{

	private static Integer calificacion = Integer.parseInt(Property.getSpecifiedProperty("CalificacionBien"));
	
	public Integer darCalificacion() {
		
		return calificacion;
		
		
		
	}
	
	
}
