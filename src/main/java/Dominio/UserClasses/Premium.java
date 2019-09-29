package Dominio.UserClasses;

import java.io.FileNotFoundException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Dominio.ClothingClasses.Prenda;
import Dominio.WardrobeClasses.Guardarropa;

@Entity
@DiscriminatorValue("premium")
public class Premium extends TipoDeUsuario {
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
