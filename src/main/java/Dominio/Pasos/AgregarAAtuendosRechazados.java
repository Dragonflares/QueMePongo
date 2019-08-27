package Dominio.Pasos;


public class AgregarAAtuendosRechazados implements Paso{
	
	private ParametersPaso parametros;
	
	public AgregarAAtuendosRechazados(ParametersPaso parametros) {
		
		this.parametros = parametros;
		
	}
	
	
	
	public void ejecutar()
	{
		parametros.getUsuario().agregarARechazados(parametros.getAtuendo());
	}
}

