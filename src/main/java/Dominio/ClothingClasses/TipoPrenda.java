package Dominio.ClothingClasses;
import java.util.List;

import Dominio.ClothingClasses.Material;

import java.util.ArrayList;
@SuppressWarnings("unused")

public class TipoPrenda{

	private TipoDeRopa ropa;
	private Categoria categoria;
	private List<TipoDeRopa> ropasCompatibles;
	public TipoPrenda(TipoDeRopa ropa, Categoria categoria, List<TipoDeRopa> ropasCompatibles)
	{
		this.ropa = ropa;
		this.categoria = categoria;
		this.ropasCompatibles = ropasCompatibles;
	}
	
	public int Abrigar()
	{
		return this.ropa.abrigar();
	}
	
	public List<Material> getMaterialesNoCompatible(){
		return this.ropa.getMaterialesNoCompatible();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public TipoDeRopa getRopa() {
		return this.ropa;
	}
	
	
	
}
