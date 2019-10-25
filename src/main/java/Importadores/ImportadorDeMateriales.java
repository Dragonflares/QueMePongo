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

public class ImportadorDeMateriales {

    RepoMaterial repo = new FactoryRepositoriosMaterial().get();
    String path = Property.getSpecifiedProperty("RutaRepoMaterial");
    
	private static ImportadorDeMateriales instance = new ImportadorDeMateriales();
	
	private ImportadorDeMateriales () {
	} 
	
	public static ImportadorDeMateriales getInstance()
	{
		return instance;
	}
    public List<Material> levantarMaterialesDePath()
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {

        Gson gson = new Gson();
        Type tipoListaMateriales = new TypeToken<List<Material>>() {
        }.getType();
        List<Material> materiales = gson.fromJson(new FileReader(path), tipoListaMateriales);
        repo.agregarAlRepositorio(materiales);
        return repo.getMateriales();

    }

}
