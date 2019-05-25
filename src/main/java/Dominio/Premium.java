package Dominio;

public class Premium implements TipoDeUsuario {
	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda)
	{
		guardarropa.agregarPrenda(prenda);
	}
}
