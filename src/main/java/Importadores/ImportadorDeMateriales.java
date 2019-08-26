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
import Dominio.ClothingClasses.Material;
import Repositorios.RepoMaterial;

public class ImportadorDeMateriales {

    RepoMaterial repo = new RepoMaterial();
    String path = Property.getSpecifiedProperty("RutaRepoMaterial");

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
