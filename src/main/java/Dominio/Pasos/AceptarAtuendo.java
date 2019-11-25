package Dominio.Pasos;


public class AceptarAtuendo implements Paso{
	
	ParametersPaso parametrosUsados;


	public AceptarAtuendo(ParametersPaso parametros) {

		this.parametrosUsados = parametros;
		
	}

	public void ejecutar(){

		this.parametrosUsados.getEvento().setUltimoAtuendoAceptado(this.parametrosUsados.getAtuendo());
	}
}
