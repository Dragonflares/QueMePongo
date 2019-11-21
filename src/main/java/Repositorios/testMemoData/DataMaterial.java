package Repositorios.testMemoData;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Dominio.ClothingClasses.Material;
import Importadores.ImportadorDeMateriales;
import db.EntidadPersistente;

public class DataMaterial {
	private static List<Material> materiales = new ArrayList<>();
	
	public static List<Material> getList() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
        if(materiales.size() == 0){
        	materiales = ImportadorDeMateriales.getInstance().levantarMaterialesDePath();
        	return materiales;
        }
        
        return materiales;
	}
	
	private static void addAll(Material... materiales){
        Collections.addAll(DataMaterial.materiales, materiales);
    }
}
