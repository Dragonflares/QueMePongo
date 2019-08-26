package Dominio.Pasos;

import Dominio.EventClasses.Evento;
import Dominio.UserClasses.Usuario;


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
