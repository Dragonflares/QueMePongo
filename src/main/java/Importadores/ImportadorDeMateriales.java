package Importadores;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Dominio.ClothingClasses.Material;
import Dominio.UserClasses.Property;
import Repositorios.RepoMaterial;
import Repositorios.factories.FactoryRepositoriosMaterial;
import db.EntidadPersistente;

public class ImportadorDeMateriales {

    String path = Property.getSpecifiedProperty("RutaRepoMaterial");
    
	private static ImportadorDeMateriales instance;
	
	private ImportadorDeMateriales () throws JsonIOException, JsonSyntaxException, FileNotFoundException {} 
	
	public static ImportadorDeMateriales getInstance() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{	
        if(instance == null){
            instance = new ImportadorDeMateriales();
        }
        
        return instance;
	}
	
    public List<Material> levantarMaterialesDePath()
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {

        Gson gson = new Gson();
        Type tipoListaMateriales = new TypeToken<List<Material>>() {
        }.getType();
        List<Material> materiales = gson.fromJson(new FileReader(path), tipoListaMateriales);
        return materiales;

    }

}
