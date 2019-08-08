package Dominio.UserClasses;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;
import Dominio.WardrobeClasses.Guardarropa;

public class PedirRecomendacion implements Paso{



	private Usuario usuario;
	private Estilo estilo;


	public PedirRecomendacion(Usuario usuario,Estilo estilo) {

		this.usuario = usuario;
		this.estilo = estilo;
	}



	public void ejecutar() throws Exception {
		usuario.pedirRecomendacion(estilo);
	}


}
