package Dominio;

import java.util.List;

public enum TipoPrenda {
	REMERA,
	SHORT,
	PANTALON,
	MUSCULOSA,
	ZAPATILLAS,
	PANIUELO,
	SANDALIAS,
	CROCS,
	CINTURON,
	OJOTAS,
	BOTAS;
	
	private 
	
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
