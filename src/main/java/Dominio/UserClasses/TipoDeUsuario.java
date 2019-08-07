package Dominio.UserClasses;

import Dominio.ClothingClasses.Prenda;
import Dominio.WardrobeClasses.Guardarropa;

public interface TipoDeUsuario {
	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda) throws Exception;
}

