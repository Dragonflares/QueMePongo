package Dominio;

public class Prenda {
	private TipoPrenda tipoPrenda;
	private Color colorPrimario;
	private Color colorSecundario;


	public Prenda(TipoPrenda tipoPrenda, Color colorPrimario, Color colorSecundario) throws Exception {
		this.tipoPrenda = tipoPrenda;

		if (!colorPrimario.equals(colorSecundario)) {
			this.colorPrimario =  colorPrimario;
			this.colorSecundario = colorSecundario;
		} else {
			throw new Exception("No pueden ser del mismo color."); 
		}
	}

	public TipoPrenda getTipoPrenda() {
		return this.tipoPrenda;
	}

	public Categoria getCategoria() { //TODO que sea consistente
		return this.tipoPrenda.getCategoria();
	} 
}
