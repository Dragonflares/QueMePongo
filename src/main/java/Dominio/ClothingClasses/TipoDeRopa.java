package Dominio.ClothingClasses;

import java.util.ArrayList;
import java.util.List;

public class TipoDeRopa {
	private int abrigo;
	private ArrayList<Integer> capasEnDondePuedeEstar;
	private List<Material> materialesNoCompatibles;
	
	public TipoDeRopa(int abrigo) {
		this.abrigo = abrigo;
	}
	
	public int abrigar() {
		return this.abrigo;
	}
	
	public List<Material> getMaterialesNoCompatible(){
		return this.materialesNoCompatibles;
	}
	
	public boolean esDeEsaCapa(final int capa){
		return capasEnDondePuedeEstar.stream().anyMatch(p -> p.equals(capa));
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