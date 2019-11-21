package Repositorios;


import Dominio.ClothingClasses.Color;
import Repositorios.daos.DAO;

import java.util.List;
import java.util.stream.Collectors;


public class RepoColor extends Repositorio{

	//private List<Color> colores;
	
	private static RepoColor instance;
	
	public static RepoColor getInstance(DAO dao) {
        if(instance == null){
            instance = new RepoColor(dao);
        }
        return instance;
    }
	
	private RepoColor(DAO dao){
        this.setDao(dao);
	}
	
    public List<Color> getColores() {
        return this.dao.buscarTodos();
    }

    /*public void agregarAlRepositorio(List<Color> unosColores) {
        this.colores = unosColores;
    }*/

    public Color findByName(String nombre) {
       return this.getColores().stream().filter(c -> c.compararNombres(nombre)).collect(Collectors.toList()).get(0);
    }

}