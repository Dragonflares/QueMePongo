package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Dominio.ClothingClasses.Material;
import Dominio.ClothingClasses.TipoDeRopa;
import entities.ProcessingDataFailedException;

public class RepoRopa extends Repositorio {

	private List<TipoDeRopa> ropas;
	
    public List<TipoDeRopa> getTipoDeRopas() {
        return this.ropas;
    }

    public void agregarAlRepositorio(List<TipoDeRopa> unosTiposDeRopa) {
        this.ropas = unosTiposDeRopa;
    }

    public TipoDeRopa buscarPorNombre(String nombre) {
       return this.ropas.stream().filter(r -> r.compararNombres(nombre)).collect(Collectors.toList()).get(0);
    }
	
}
