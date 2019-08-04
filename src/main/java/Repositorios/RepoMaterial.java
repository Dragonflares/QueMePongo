package Repositorios;



import entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Dominio.Material;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class RepoMaterial extends Repositorio{

    public static RepoMaterial getInstance() {
        return instance;
    }

    private static RepoMaterial instance = new RepoMaterial();

    private RepoMaterial() { //dejar en privado para que no puedan hacer otra instancia
    }

   

    public List<Material> obtenerMateriales() throws ProcessingDataFailedException {

        try {
            FileReader file = new FileReader(getJsonFile());
            BufferedReader bufferedReader = new BufferedReader(file);
            Gson gson = new Gson();
            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();
            Type tipoListaMateriales = new TypeToken<List<Material>>() {}.getType();

            List<Material> materiales = gson.fromJson(json, tipoListaMateriales);


            return materiales;

        } catch (IOException e) {
            e.printStackTrace();
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }

    }

    
}