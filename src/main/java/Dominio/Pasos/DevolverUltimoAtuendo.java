package Dominio.Pasos;

import Dominio.ClothingClasses.Atuendo;
import Dominio.UserClasses.Usuario;

public class DevolverUltimoAtuendo implements Paso{

	private ParametersPaso parametros;

	public DevolverUltimoAtuendo(ParametersPaso parametros) {

		
		this.parametros = parametros;
		this.parametros.setAtuendo(parametros.getUsuario().getUltimoAtuendo());
	}

	public void ejecutar(){

		this.parametros.getUsuario().eliminarDeRechazados(parametros.getAtuendo());
	}
}
