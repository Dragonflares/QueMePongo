package Dominio;

public class Gratuito implements TipoDeUsuario{
	private int tamanioMaximoGuardarropa;
	private String configGratuito;
	
	public int getTamanioMaximoGuardarropa() {
		return tamanioMaximoGuardarropa;
	}

	public void setTamanioMaximoGuardarropa(int tamanioMaximoGuardarropa) {
		this.tamanioMaximoGuardarropa = tamanioMaximoGuardarropa;
	}
	
	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda) throws Exception
	{
		if(this.tamanioMaximoGuardarropa > guardarropa.cantidadDePrendas())
		{
			guardarropa.agregarPrenda(prenda);
		} else 
		{
			throw new Exception("Ya tenes la prenda en otro guardarropa");
		}
		
	}
}
