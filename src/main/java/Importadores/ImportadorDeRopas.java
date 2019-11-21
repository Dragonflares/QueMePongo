package Importadores;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Dominio.ClothingClasses.TipoDeRopa;
import Dominio.UserClasses.Property;
import Repositorios.RepoRopa;
import Repositorios.factories.FactoryRepositorioRopa;

public class ImportadorDeRopas {

    String path = Property.getSpecifiedProperty("RutaRepoRopa");
	
    private static ImportadorDeRopas instance;
	
	private ImportadorDeRopas () {} 
	
	public static ImportadorDeRopas getInstance()
	{
        if(instance == null){
            instance = new ImportadorDeRopas();
        }
        
        return instance;
	}
	
    public List<TipoDeRopa> levantarTipoDeRopasDePath()
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {

        Gson gson = new Gson();
        Type tipoListaRopas = new TypeToken<List<TipoDeRopa>>() {
        }.getType();
        List<TipoDeRopa> ropas = gson.fromJson(new FileReader(path), tipoListaRopas);
        
        return ropas;
    }
}
