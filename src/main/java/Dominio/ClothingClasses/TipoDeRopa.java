package Dominio.ClothingClasses;

import java.util.ArrayList;
import java.util.List;

public class TipoDeRopa {
	private String nombre;
	private Integer abrigo;
	private Categoria categoria;
	private ArrayList<Capas> capasEnDondePuedeEstar = new ArrayList<Capas>();
	private ArrayList<Material> materialesNoCompatibles = new ArrayList<Material>();
	
	public TipoDeRopa(String nombre,Integer abrigo,Categoria categoria, ArrayList<Capas> capas,
			ArrayList<Material> materialesNoCompatibles) {
		this.nombre = nombre;
		this.abrigo = abrigo;
		this.categoria = categoria;
		this.capasEnDondePuedeEstar = capas;
		this.materialesNoCompatibles = materialesNoCompatibles;
	}
	
	public Integer getNivelAbrigo() {
		return this.abrigo;
	}
	
	public List<Material> getMaterialesNoCompatible(){
		return this.materialesNoCompatibles;
	}
	
	public Boolean esDeEsaCapa(final int capa){
		return capasEnDondePuedeEstar.stream().anyMatch(p -> p.equals(capa));
	}
	
	
	public boolean compararNombres(String otroNombre){


		return this.getNombre().equals(otroNombre);
	}

	public String getNombre() {
		return nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	
//	REMERAMANGACORTA 3,
//	REMERAMANGALARGA 4,
//	CAMISA 4,
//	CHOMBA 4,
//	MUSCULOSA 1,
//	CAMPERAGRUESA 10,
//	CAMPERAMEDIA 7, 
//	CAMPERAFINITA 4,
//	BUZO 6,
//	PULOVER 7,
//	POLERA 8,
}


//Temp 18, Base 27, 9 grados de calor, +-3(sup), 3 + 4 = 7, pantalon largo.
//Temp 23, Base 27, 4 grados de calor, +-3(sup), 3, pantaloncorto.
//Temp 4, Base 27, 23 grados de calor +-3(sup), 3 + 4 + 6 + 10.
//Temp 11, Base 27, 16 grados de calor +-3(sup), 4 + 7 + 7, 