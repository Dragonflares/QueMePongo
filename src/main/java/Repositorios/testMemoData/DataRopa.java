package Repositorios.testMemoData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Dominio.ClothingClasses.Color;
import Dominio.ClothingClasses.TipoDeRopa;
import db.EntidadPersistente;

public class DataRopa {
private static List<TipoDeRopa> ropas = new ArrayList<>();
	
	public static List<EntidadPersistente> getList(){
		if(ropas.size() == 0) {
			
		addAll();
		}
		
		return (List<EntidadPersistente>) (List<?>) ropas;
	}
	
	private static void addAll(TipoDeRopa...ropas ) {
		Collections.addAll(DataRopa.ropas, ropas);
	}
}
