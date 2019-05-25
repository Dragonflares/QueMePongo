package Dominio;
import java.util.List;
import java.util.ArrayList;
@SuppressWarnings("unused")

public class TipoPrenda {

	private Ropa ropa;
	private Categoria categoria;
	private List<Material> materialesNoCompatibles;
	private List<Ropa> ropasNoCompatibles;
	
	public TipoPrenda(Ropa ropa, Categoria categoria, List<Material> materialesNoCompatibles)
	{
		this.ropa = ropa;
		this.categoria = categoria;
		this.materialesNoCompatibles = materialesNoCompatibles;
	}
	
	public List<Material> getMaterialesNoCompatible(){
		return this.materialesNoCompatibles;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	
	
	
}
