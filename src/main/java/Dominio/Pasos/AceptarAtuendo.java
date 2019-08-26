package Dominio.Pasos;

import Dominio.ClothingClasses.Atuendo;
import Dominio.EventClasses.Evento;


public class AceptarAtuendo implements Paso{
	
	ParametersPaso parametrosUsados;


	public AceptarAtuendo(ParametersPaso parametros) {

		this.parametrosUsados = parametros;
		
	}

	public void ejecutar(){

		this.parametrosUsados.getEvento().agregarSugerencia(this.parametrosUsados.getAtuendo());
	}

}
