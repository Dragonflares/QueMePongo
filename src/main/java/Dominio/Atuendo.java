package Dominio;

public class Atuendo {
	private Prenda parteSuperior;
	private Prenda parteInferior;
	private Prenda calzado;
	private Prenda accesorios;
	
public Atuendo (Prenda parteSuperior, Prenda parteInferior, Prenda calzado, Prenda accesorios) throws Exception {
	this.parteSuperior = parteSuperior;
	this.parteInferior = parteInferior;
	this.calzado = calzado;
	this.accesorios = accesorios;

	if (this.parteSuperior.getCategoria() != Categoria.PARTE_SUPERIOR)             
		throw new Exception("Error: La categoria de la parteSuperior tiene que ser de categoria parteSuperior");
	
	if (this.parteInferior.getCategoria() != Categoria.PARTE_INFERIOR)             
		throw new Exception("Error: La categoria de la parteInferior tiene que ser de categoria parteInferior");
	
	if (this.calzado.getCategoria() != Categoria.CALZADO)             
		throw new Exception("Error: La categoria de la calzado tiene que ser de categoria calzado");
	
	if (this.accesorios.getCategoria() != Categoria.ACCESORIOS && this.accesorios != null)             
		throw new Exception("Error: La categoria de la accesorios tiene que ser de categoria accesorios");
}
	
	
}
