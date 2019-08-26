package Dominio.ClothingClasses;

import Dominio.WardrobeClasses.Guardarropa;

public interface Estilo {
//	FORMAL, 
//	DEPORTIVO,
//	CASUAL
	
	public Atuendo generarAtuendoVerano (Guardarropa guardarropa);
	
	
	public Atuendo generarAtuendoInvierno (Guardarropa guardarropa);

	
}
