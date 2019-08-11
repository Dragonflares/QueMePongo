package Dominio.ClothingClasses;
import java.util.List;

import Dominio.ClothingClasses.Material;

import java.util.ArrayList;
@SuppressWarnings("unused")

public class TipoPrenda{

	private TipoDeRopa ropa;
	private Categoria categoria;
	private List<Material> materialesNoCompatibles;
	private List<TipoDeRopa> ropasCompatibles;
	public TipoPrenda(TipoDeRopa ropa, Categoria categoria, 
			List<Material> materialesNoCompatibles, List<TipoDeRopa> ropasCompatibles)
	{
		this.ropa = ropa;
		this.categoria = categoria;
		this.materialesNoCompatibles = materialesNoCompatibles;
		this.ropasCompatibles = ropasCompatibles;
	}
	
	public int Abrigar()
	{
		return this.ropa.abrigar();
	}
	public List<Material> getMaterialesNoCompatible(){
		return this.materialesNoCompatibles;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public TipoDeRopa getRopa() {
		return this.ropa;
	}
	
	
	
}
