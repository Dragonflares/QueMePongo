package Repositorios.testMemoData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Dominio.ClothingClasses.Material;
import db.EntidadPersistente;

public class DataMaterial {
	private static List<Material> materiales = new ArrayList<>();
	
	public static List<EntidadPersistente> getList(){
        if(materiales.size() == 0){
        //TODO
        	addAll();
        }
        return (List<EntidadPersistente>)(List<?>)materiales;
	}
	
	private static void addAll(Material... materiales){
        Collections.addAll(DataMaterial.materiales, materiales);
    }
}
