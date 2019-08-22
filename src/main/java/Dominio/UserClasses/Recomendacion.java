package Dominio.UserClasses;

import java.util.Calendar;

import Dominio.ClothingClasses.Atuendo;

public class Recomendacion {
	private Calendar fecha;
	private Atuendo sugerencia;
	
	public Recomendacion(Calendar fecha, Atuendo sugerencia) {
		this.fecha = fecha;
		this.sugerencia = sugerencia;
	}
}
