package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Dominio.ClothingClasses.Material;
import Dominio.ClothingClasses.TipoDeRopa;
import entities.ProcessingDataFailedException;

public class RepoRopa extends Repositorio {

	
	
	
	  public static RepoRopa getInstance() {
	        return instance;
	    }

	    private static RepoRopa instance = new RepoRopa();

	    private RepoRopa() { //dejar en privado para que no puedan hacer otra instancia
	    }

	   
	    
	    public TipoDeRopa findByName(String nombre) throws ProcessingDataFailedException {
	        try {
	            FileReader file = new FileReader(getJsonFile());
	            BufferedReader bufferedReader = new BufferedReader(file);
	            Gson gson = new Gson();
	            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
	            String json = jsonObject.toString();
	            Type tipoListaDeRopas = new TypeToken<List<TipoDeRopa>>() {}.getType();

	            List<TipoDeRopa> ropas = gson.fromJson(json, tipoListaDeRopas);
	            
	           return ropas.stream().filter(ropa -> ropa.compararNombres(nombre)).findAny().orElse(null);
	      
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new ProcessingDataFailedException(e.getLocalizedMessage());
	        }
	    }
	    
	    
	    

	    public List<TipoDeRopa> obtenerRopas() throws ProcessingDataFailedException {

	        try {
	            FileReader file = new FileReader(getJsonFile());
	            BufferedReader bufferedReader = new BufferedReader(file);
	            Gson gson = new Gson();
	            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
	            String json = jsonObject.toString();
	            Type tipoListaDeRopas = new TypeToken<List<TipoDeRopa>>() {}.getType();

	            List<TipoDeRopa> ropas = gson.fromJson(json, tipoListaDeRopas);


	            return ropas;

	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new ProcessingDataFailedException(e.getLocalizedMessage());
	        }

	    }
	
	
	
	
	
	
	
	
}
