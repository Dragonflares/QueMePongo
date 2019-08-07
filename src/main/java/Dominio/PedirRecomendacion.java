package Dominio;

public class PedirRecomendacion implements Paso{
	
	public void ejecutar(Usuario usuario, Atuendo atuendo, Guardarropa guardarropas, Estilo estilo) throws Exception
	{
		usuario.pedirRecomendacion(estilo);
	}
}
