package Dominio;

import java.util.List;
import java.util.ArrayList;

public enum TipoPrenda {
	REMERA(Categoria.PARTE_SUPERIOR, Material.CUERO),
	SHORT(Categoria.PARTE_INFERIOR, Material.GOMA),
	PANTALON(Categoria.PARTE_INFERIOR, Material.GOMA),
	MUSCULOSA(Categoria.PARTE_SUPERIOR, Material.CUERO),
	ZAPATILLAS(Categoria.CALZADO, Material.SEDA),
	CINTURON(Categoria.ACCESORIOS, Material.SEDA);
	
	
	private Categoria categoria;
	private Material materialesNoCompatibles;
	
	private TipoPrenda(Categoria categoria, Material materialesNoCompatibles)
	{
		this.categoria = categoria;
		this.materialesNoCompatibles = materialesNoCompatibles;
	}
	
	public Material getMaterialNoCompatible(){
		return this.materialesNoCompatibles;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
}


//public class TipoPrenda {
//	private Categoria categoria;
//	private Material material;
//	private List<Material> materialesNoCompatibles;
//
//	public Categoria getCategoria() {
//		return this.categoria;
//	}
//
//	public TipoPrenda(Categoria categoria, Material material, List<Material> materialesNoCompatibles) throws Exception {
//		this.categoria = categoria;
//		this.materialesNoCompatibles = materialesNoCompatibles;
//
//		if (!this.materialesNoCompatibles.contains(material)) {
//			this.material = material;
//		}	else {
//			throw new Exception("No puede ser de ese material.");
//		}
//	}
//}
