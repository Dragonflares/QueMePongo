package Repositorios.testMemoData;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Dominio.ClothingClasses.Color;
import Importadores.ImportadorDeColores;
import Importadores.ImportadorDeMateriales;
import db.EntidadPersistente;

public class DataColor {
	
	private static List<Color> colores = new ArrayList<>();
	
	public static List<Color> getList() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
        if(colores.size() == 0){
        	colores = ImportadorDeColores.getInstance().levantarColoresDePath();
        	return colores;
        }
        
        return colores;
	}
}
