package Dominio.UserClasses;

import java.io.FileNotFoundException;

import Dominio.ClothingClasses.Prenda;
import Dominio.WardrobeClasses.Guardarropa;

public interface TipoDeUsuario {
	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda) throws Exception;
	public void cambiarCategoria(Usuario usuario) throws FileNotFoundException;
}

