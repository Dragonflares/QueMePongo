package Dominio;

public class Prenda {
	private TipoPrenda tipoPrenda;
	private Color colorPrimario;
	private Color colorSecundario;
	private Material material;


	public Prenda(TipoPrenda tipoPrenda, Color colorPrimario, Color colorSecundario, Material material) throws Exception {
		this.tipoPrenda = tipoPrenda;

		if (!colorPrimario.equals(colorSecundario)) {
			this.colorPrimario =  colorPrimario;
			this.colorSecundario = colorSecundario;
		} else {
			throw new Exception("No pueden ser del mismo color."); 
		}
		
		if(!material.equals(tipoPrenda.getMaterialNoCompatible())) {
			this.material = material;
		} else {
			throw new Exception("Ingreso un tipo de prenda no compatible.");
		}
	}

	public TipoPrenda getTipoPrenda() {
		return this.tipoPrenda;
	}

	public Categoria getCategoria() { 
		return this.tipoPrenda.getCategoria();
	} 
}
