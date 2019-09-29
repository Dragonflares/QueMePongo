package Dominio.UserClasses;

import Dominio.Property;

public class Frio {
	private static Integer calificacion = Integer.parseInt(Property.getSpecifiedProperty("CalificacionFrio"));

	public Integer darCalificacion() {
		return calificacion;
	}
}
