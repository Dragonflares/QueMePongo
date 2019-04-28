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

