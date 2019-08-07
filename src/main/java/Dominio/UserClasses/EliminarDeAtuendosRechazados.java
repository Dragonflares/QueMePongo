package Dominio.UserClasses;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;
import Dominio.WardrobeClasses.Guardarropa;

public class EliminarDeAtuendosRechazados {
	public void ejecutar(Usuario usuario, Atuendo atuendo, Guardarropa guardarropa, Estilo estilo)
	{
		usuario.eliminarDeRechazados(atuendo);
	}
}
