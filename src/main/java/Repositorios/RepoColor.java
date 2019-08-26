package Repositorios;


import Dominio.ClothingClasses.Color;
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