package Importadores;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Dominio.Property;
import Dominio.ClothingClasses.Color;
import Repositorios.RepoColor;

public class ImportadorDeColores {

    RepoColor repo = new RepoColor();
    String path = Property.getSpecifiedProperty("RutaRepoColor");

    public List<Color> levantarColoresDePath()
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {

        Gson gson = new Gson();
        Type tipoListaColores = new TypeToken<List<Color>>() {
        }.getType();
        List<Color> colores = gson.fromJson(new FileReader(path), tipoListaColores);
        repo.agregarAlRepositorio(colores);
        return repo.getColores();
    }
	
}
