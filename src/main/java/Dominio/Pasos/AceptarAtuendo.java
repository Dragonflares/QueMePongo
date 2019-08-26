package Dominio.Pasos;

import Dominio.ClothingClasses.Atuendo;
import Dominio.EventClasses.Evento;


public class AceptarAtuendo implements Paso{
	private Evento evento;
	private Atuendo atuendo;


	public AceptarAtuendo(Evento evento, Atuendo atuendo) {

		this.evento = evento;
		this.atuendo = atuendo;
	}

	public void ejecutar(){

		evento.agregarSugerencia(atuendo);
	}

}
