package Importadores;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Dominio.ClothingClasses.MoldeDeAtuendo;
import Dominio.UserClasses.Property;
import Dominio.WeatherAPIClasses.DarkSkyAdaptado;
import Dominio.WeatherAPIClasses.GestorClimatico;
import Dominio.WeatherAPIClasses.OPWAdaptado;
import Dominio.WeatherAPIClasses.WeatherAdapter;
import Repositorios.RepoMolde;

public class ImportadorDeMoldes {
    RepoMolde repo = new RepoMolde();
    String path = Property.getSpecifiedProperty("RutaRepoMoldes");

	private static ImportadorDeMoldes instance = new ImportadorDeMoldes();
	
	private ImportadorDeMoldes () {
	} 
	
	public static ImportadorDeMoldes getInstance()
	{
		return instance;
	}
	
    public List<MoldeDeAtuendo> levantarMoldesDePath()
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {
        Gson gson = new Gson();
        Type tipoListaMoldes = new TypeToken<List<MoldeDeAtuendo>>() {
        }.getType();
        List<MoldeDeAtuendo> moldes = gson.fromJson(new FileReader(path),
        		tipoListaMoldes);
        repo.agregarAlRepositorio(moldes);
        return repo.getTipoDeRopas();
    }
}
