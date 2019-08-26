package Dominio.Pasos;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;

public class EliminarDeAtuendosRechazados implements Paso {


	private Usuario usuario;
	private Atuendo atuendo;


	public EliminarDeAtuendosRechazados(Usuario usuario) {

		this.usuario = usuario;
		this.atuendo = usuario.getUltimoAtuendo();
	}

	public void ejecutar(){

		usuario.eliminarDeRechazados(atuendo);
	}


}
