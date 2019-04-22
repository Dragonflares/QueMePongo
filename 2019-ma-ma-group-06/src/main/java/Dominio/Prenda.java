package Dominio;

public class Prenda {
	private TipoPrenda tipoPrenda;
	private Material material; //TODO que sea consistente
	private String color; //TOTDO pensar, no creo que sea una buena idea
	
	
	public Prenda(TipoPrenda tipoPrenda, Categoria categoria, Material material){
		this.tipoPrenda = tipoPrenda;
		this.material = material;
	}
	
	public TipoPrenda getTipoPrenda() {
		return this.tipoPrenda;
	}
	
	public Categoria getCategoria() { //TODO que sea consistente
		return this.tipoPrenda.getCategoria();
	} 
}
