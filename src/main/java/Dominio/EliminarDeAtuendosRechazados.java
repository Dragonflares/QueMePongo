package Dominio;

public class EliminarDeAtuendosRechazados {
	public void ejecutar(Usuario usuario, Atuendo atuendo, Guardarropa guardarropa, Estilo estilo)
	{
		usuario.eliminarDeRechazados(atuendo);
	}
}
