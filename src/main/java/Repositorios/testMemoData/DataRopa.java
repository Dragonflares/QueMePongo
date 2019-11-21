package Repositorios.testMemoData;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Dominio.ClothingClasses.TipoDeRopa;
import Importadores.ImportadorDeMateriales;
import Importadores.ImportadorDeRopas;
import db.EntidadPersistente;

public class DataRopa {
	private static List<TipoDeRopa> ropas = new ArrayList<>();
	
	public static List<TipoDeRopa> getList() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
        if(ropas.size() == 0){
        	ropas = ImportadorDeRopas.getInstance().levantarTipoDeRopasDePath();
        	return ropas;
        }
        
        return ropas;
	}
}
