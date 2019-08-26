package Dominio.Pasos;

import Dominio.ClothingClasses.Atuendo;
import Dominio.UserClasses.Usuario;

public class EliminarDeAtuendosRechazados implements Paso {


	private Usuario usuario;
	private Atuendo atuendo;
	private ParametersPaso parametros;

	public EliminarDeAtuendosRechazados(ParametersPaso parametros) {

		this.parametros = parametros;
		this.parametros.setAtuendo(parametros.getUsuario().getUltimoAtuendo());
	}

	public void ejecutar(){

		usuario.eliminarDeRechazados(atuendo);
	}


}
