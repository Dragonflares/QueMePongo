package Dominio.UserClasses;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;
import Dominio.WardrobeClasses.Guardarropa;

public interface Paso {
	public void ejecutar(Usuario usuario, Atuendo atuendo, Guardarropa guardarropas, Estilo estilo) throws Exception;
}
