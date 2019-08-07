package Dominio;

public class AgregarAAtuendosRechazados implements Paso{
	public void ejecutar(Usuario usuario, Atuendo atuendo, Guardarropa guardarropa, Estilo estilo)
	{
		usuario.agregarARechazados(atuendo);
	}
}
