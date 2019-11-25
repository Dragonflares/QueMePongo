package Dominio.UserClasses;

public class Frio  implements Calificadores{
	private static Integer calificacion = Integer.parseInt(Property.getSpecifiedProperty("CalificacionFrio"));

	public Integer darCalificacion() {
		return calificacion;
	}
}
