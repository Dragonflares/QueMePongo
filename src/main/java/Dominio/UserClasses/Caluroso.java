package Dominio.UserClasses;

public class Caluroso  implements Calificadores{
	private static Integer calificacion = Integer.parseInt(Property.getSpecifiedProperty("CalificacionCaluroso"));

	public Integer darCalificacion() {
		return calificacion;
	}
}
