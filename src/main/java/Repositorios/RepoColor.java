package Repositorios;


import entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Dominio.ClothingClasses.Color;
import Dominio.ClothingClasses.Material;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;


public class RepoColor extends Repositorio{

	private List<Color> colores;
	
    public List<Color> getColores() {
        return this.colores;
    }

    public void agregarAlRepositorio(List<Color> unosColores) {
        this.colores = unosColores;
    }

    public Color buscarPorNombre(String nombre) {
       return this.colores.stream().filter(c -> c.compararNombres(nombre)).collect(Collectors.toList()).get(0);
    }

}