package Repositorios;



import Dominio.ClothingClasses.Material;
import Repositorios.daos.DAO;

import java.util.List;
import java.util.stream.Collectors;


public class RepoMaterial extends Repositorio{
	
	private List<Material> materiales; // TODO sacar
	
	private static RepoMaterial instance;
	
	public static RepoMaterial getInstance(DAO dao) {
        if(instance == null){
            instance = new RepoMaterial(dao);
        }
        return instance;
    }
	
	private RepoMaterial(DAO dao){
        this.setDao(dao);
	}
	
    public List<Material> getMateriales() {
        return this.materiales;
    }

    public void agregarAlRepositorio(List<Material> unosMateriales) {
        this.materiales = unosMateriales;
    }

    public Material findByName(String nombre) {
       return this.materiales.stream().filter(m -> m.compararNombres(nombre)).collect(Collectors.toList()).get(0);
    }
	
    
}