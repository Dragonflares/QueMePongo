package Importadores;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Dominio.ClothingClasses.Color;
import Dominio.UserClasses.Property;
import Repositorios.RepoColor;
import Repositorios.factories.FactoryRepositorioColor;

public class ImportadorDeColores {

    String path = Property.getSpecifiedProperty("RutaRepoColor");
	
    private static ImportadorDeColores instance;
	
	private ImportadorDeColores () {} 
	
	public static ImportadorDeColores getInstance()
	{
        if(instance == null){
            instance = new ImportadorDeColores();
        }
        
        return instance;
	}
    public List<Color> levantarColoresDePath()
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {

        Gson gson = new Gson();
        Type tipoListaColores = new TypeToken<List<Color>>() {
        }.getType();
        List<Color> colores = gson.fromJson(new FileReader(path), tipoListaColores);

        return colores;
    }
	
}
