package Dominio.Pasos;

import Dominio.ClothingClasses.Atuendo;
import Dominio.EventClasses.Evento;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;

public class ParametersPaso {


	private Usuario usuario;
	private Atuendo atuendo;
	private Evento evento;
	private Guardarropa guardarropa;



	public ParametersPaso (ParametersPasoBuilder builder) {
		this.usuario = builder.usuario;
		this.atuendo = builder.atuendo;
		this.evento = builder.evento;
		this.guardarropa = builder.guardarropa;

	}



	public void nuevoParametersPasoSeteado (ParametersPasoBuilder builder) {
		this.usuario = builder.usuario;
		this.atuendo = builder.atuendo;
		this.evento = builder.evento;
		this.guardarropa = builder.guardarropa;
	}
	
	
	

	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Atuendo getAtuendo() {
		return atuendo;
	}



	public void setAtuendo(Atuendo atuendo) {
		this.atuendo = atuendo;
	}



	public Evento getEvento() {
		return evento;
	}



	public void setEvento(Evento evento) {
		this.evento = evento;
	}



	public Guardarropa getGuardarropa() {
		return guardarropa;
	}



	public void setGuardarropa(Guardarropa guardarropa) {
		this.guardarropa = guardarropa;
	}


	public static class ParametersPasoBuilder {


		private Usuario usuario;
		private Atuendo atuendo;
		private Evento evento;
		private Guardarropa guardarropa;


		public ParametersPasoBuilder () {

		}



		public ParametersPasoBuilder usuario(Usuario usuario) {
			this.usuario = usuario;
			return this;
		}

		public ParametersPasoBuilder atuendo (Atuendo atuendo) {
			this.atuendo = atuendo;
			return this;
		}

		public ParametersPasoBuilder evento(Evento evento) {

			this.evento = evento;
			return this;
		}

		public ParametersPasoBuilder guardarropa(Guardarropa guardarropa) {
			this.guardarropa = guardarropa;
			return this;
		}



		public ParametersPaso build() {

			return new ParametersPaso(this);

		}

	}






	//ParametersPaso nuevo  = new ParametersPaso.ParametersPasoBuilder().evento(Evento).build();





}
