package Dominio.UserClasses;

import Dominio.ClothingClasses.Atuendo;
import Dominio.WardrobeClasses.Guardarropa;

public class PonerAtuendoNoDisponible implements Paso{
	private Atuendo atuendo;
	private Guardarropa guardarropa;
	
	@Override
	public void ejecutar() throws Exception {
		guardarropa.marcarNoDisponible(atuendo);
	}

}
