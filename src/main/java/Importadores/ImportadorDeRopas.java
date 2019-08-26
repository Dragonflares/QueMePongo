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
import Repositorios.RepoRopa;

public class ImportadorDeRopas {

    RepoRopa repo = new RepoRopa();

    public List<TipoDeRopa> levantarTipoDeRopasDePath(String path)
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {

        Gson gson = new Gson();
        Type tipoListaRopas = new TypeToken<List<TipoDeRopa>>() {
        }.getType();
        List<TipoDeRopa> ropas = gson.fromJson(new FileReader(path), tipoListaRopas);
        repo.agregarAlRepositorio(ropas);
        return repo.getTipoDeRopas();

    }
}
