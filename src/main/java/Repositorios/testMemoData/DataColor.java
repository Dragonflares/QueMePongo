package Repositorios.testMemoData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Dominio.ClothingClasses.Color;
import db.EntidadPersistente;

public class DataColor {
	
	private static List<Color> colores = new ArrayList<>();
	
	public static List<EntidadPersistente> getList(){
		if(colores.size() == 0) {
			
		addAll();
		}
		
		return (List<EntidadPersistente>) (List<?>) colores;
	}
	
	private static void addAll(Color ...colores ) {
		Collections.addAll(DataColor.colores, colores);
	}
}
