package Repositorios;



import entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Dominio.ClothingClasses.Material;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class RepoMaterial extends Repositorio{
	
	private List<Material> materiales;
	
    public List<Material> getMateriales() {
        return this.materiales;
    }

    public void agregarAlRepositorio(List<Material> unosMateriales) {
        this.materiales = unosMateriales;
    }

    public Material buscarPorNombre(String nombre) {
       return this.materiales.stream().filter(m -> m.compararNombres(nombre)).collect(Collectors.toList()).get(0);
    }
	
    
}