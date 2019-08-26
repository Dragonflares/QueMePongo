package Dominio.UserClasses;

import java.io.FileNotFoundException;

import Dominio.ClothingClasses.Prenda;
import Dominio.WardrobeClasses.Guardarropa;

public class Premium implements TipoDeUsuario {
	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda)
	{
		guardarropa.agregarPrenda(prenda);
	}
	
	
	public void cambiarCategoria(Usuario usuario) throws FileNotFoundException {
		this.pasarAGratuito(usuario);
		
	}
	
	public void pasarAGratuito(Usuario usuario) throws FileNotFoundException {
		Gratuito gratuito = new Gratuito();
		usuario.cambiarCategoria(gratuito);
	}
}
