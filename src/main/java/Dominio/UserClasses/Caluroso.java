package Dominio.UserClasses;

import Dominio.Property;

public class Caluroso {


	private static Integer calificacion = Integer.parseInt(Property.getSpecifiedProperty("CalificacionCaluroso"));



	public Integer darCalificacion() {

		return calificacion;



	}


}
