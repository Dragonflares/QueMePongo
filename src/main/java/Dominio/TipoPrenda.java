package Dominio;
import java.util.List;
import java.util.ArrayList;
@SuppressWarnings("unused")

public class TipoPrenda {

	private Ropa ropa;
	private Categoria categoria;
	private List<Material> materialesNoCompatibles;
	private List<Ropa> ropasCompatibles;
	
	public TipoPrenda(Ropa ropa, Categoria categoria, List<Material> materialesNoCompatibles, List<Ropa> ropasCompatibles)
	{
		this.ropa = ropa;
		this.categoria = categoria;
		this.materialesNoCompatibles = materialesNoCompatibles;
		this.ropasCompatibles = ropasCompatibles;
	}
	
	public List<Material> getMaterialesNoCompatible(){
		return this.materialesNoCompatibles;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	
	
	
}
