package Dominio.Estilish;

import Dominio.ClothingClasses.Atuendo;
import Dominio.WardrobeClasses.Guardarropa;

public interface Estilo {
	
	public Atuendo generarAtuendoVerano (Guardarropa guardarropa) throws Exception;
	
}