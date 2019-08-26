package Dominio.UserClasses;

import Dominio.Property;
import Dominio.ClothingClasses.Prenda;
import Dominio.WardrobeClasses.Guardarropa; 

public class Gratuito implements TipoDeUsuario{
	private Integer tamanioMaximoGuardarropa = Integer.parseInt(Property.getSpecifiedProperty("PrendasMaximasUsuario"));

	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda) throws Exception
	{
		if(this.tamanioMaximoGuardarropa > guardarropa.cantidadDePrendasDisponibles())
		{
			guardarropa.agregarPrenda(prenda);
		} else 
		{
			throw new Exception("Ya tenes la prenda en otro guardarropa");
		}

	}

	@Override
	public void cambiarCategoria(Usuario usuario) {
		this.pasarAPremium(usuario);
		
	}
	
	public void pasarAPremium(Usuario usuario) {
		Premium premium = new Premium();
		usuario.cambiarCategoria(premium);
	}
}
