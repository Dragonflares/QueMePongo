package Repositorios;


import entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Dominio.Color;
import Dominio.Material;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class RepoColor extends Repositorio{

    public static RepoColor getInstance() {
        return instance;
    }

    private static RepoColor instance = new RepoColor();

    private RepoColor() { //dejar en privado para que no puedan hacer otra instancia
    }

   

    public List<Color> obtenerColores() throws ProcessingDataFailedException {

        try {
            FileReader file = new FileReader(getJsonFile());
            BufferedReader bufferedReader = new BufferedReader(file);
            Gson gson = new Gson();
            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();
            Type tipoListaColores = new TypeToken<List<Material>>() {}.getType();

            List<Color> colores = gson.fromJson(json, tipoListaColores);


            return colores;

        } catch (IOException e) {
            e.printStackTrace();
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }

    }

    
}