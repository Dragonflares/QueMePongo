package Dominio.UserClasses;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;
import Dominio.WardrobeClasses.Guardarropa;

public class EliminarDeAtuendosRechazados implements Paso {


	private Usuario usuario;
	private Atuendo atuendo;


	public EliminarDeAtuendosRechazados(Usuario usuario,Atuendo atuendo) {

		this.usuario = usuario;
		this.atuendo = atuendo;
	}

	public void ejecutar(){

		usuario.eliminarDeRechazados(atuendo);
	}


}
