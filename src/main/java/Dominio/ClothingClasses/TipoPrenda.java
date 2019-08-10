package Dominio.ClothingClasses;
import java.util.List;

import Dominio.ClothingClasses.Material;

import java.util.ArrayList;
@SuppressWarnings("unused")

public class TipoPrenda implements Abrigo{

	private TipoDeRopa ropa;
	private Categoria categoria;
	private List<Material> materialesNoCompatibles;
	private List<TipoDeRopa> ropasCompatibles;
	private int nivelDeCalor; 
	public TipoPrenda(TipoDeRopa ropa, Categoria categoria, List<Material> materialesNoCompatibles, List<TipoDeRopa> ropasCompatibles, int nivelDeCalor)
	{
		this.ropa = ropa;
		this.categoria = categoria;
		this.materialesNoCompatibles = materialesNoCompatibles;
		this.ropasCompatibles = ropasCompatibles;
		this.nivelDeCalor = nivelDeCalor;
	}
	
	public int Abrigar()
	{
		return this.nivelDeCalor;
	}
	public List<Material> getMaterialesNoCompatible(){
		return this.materialesNoCompatibles;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	
	
}
