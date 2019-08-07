package Dominio.WardrobeClasses;

import Dominio.ClothingClasses.Prenda;
import Dominio.UserClasses.TipoDeUsuario;

public class Premium implements TipoDeUsuario {
	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda)
	{
		guardarropa.agregarPrenda(prenda);
	}
}
