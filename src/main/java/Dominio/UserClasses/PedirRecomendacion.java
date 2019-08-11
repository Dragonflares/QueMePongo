package Dominio.UserClasses;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;
import Dominio.WardrobeClasses.Guardarropa;

public class PedirRecomendacion implements Paso{



	private Usuario usuario;
	private Evento evento;


	public PedirRecomendacion(Usuario usuario,Evento evento) {

		this.usuario = usuario;
		this.evento = evento;
	}



	public void ejecutar() throws Exception {
		usuario.pedirRecomendacion(evento);
	}


}
