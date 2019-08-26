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
import Repositorios.RepoMaterial;

public class ImportadorDeMateriales {

    RepoMaterial repo = new RepoMaterial();

    public List<Material> levantarMaterialesDePath(String path)
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {

        Gson gson = new Gson();
        Type tipoListaMateriales = new TypeToken<List<Material>>() {
        }.getType();
        List<Material> materiales = gson.fromJson(new FileReader(path), tipoListaMateriales);
        repo.agregarAlRepositorio(materiales);
        return repo.getMateriales();

    }

}
