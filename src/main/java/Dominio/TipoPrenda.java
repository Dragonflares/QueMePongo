package Dominio;
import java.util.List;
import java.util.ArrayList;
@SuppressWarnings("unused")

public class TipoPrenda {

	private Categoria categoria;
	private List<Material> materialesNoCompatibles;
	
	private TipoPrenda(Categoria categoria, List<Material> materialesNoCompatibles)
	{
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
