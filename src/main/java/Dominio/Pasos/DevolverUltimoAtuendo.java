package Dominio.Pasos;

import Dominio.ClothingClasses.Atuendo;
import Dominio.UserClasses.Usuario;

public class DevolverUltimoAtuendo implements Paso{
	private Usuario usuario;
	private Atuendo atuendo;


	public DevolverUltimoAtuendo(Usuario usuario) {

		this.usuario = usuario;
		this.atuendo = usuario.getUltimoAtuendo();
	}

	public void ejecutar(){

		usuario.eliminarDeRechazados(atuendo);
	}
}
