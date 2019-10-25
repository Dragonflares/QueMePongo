package Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.TipoDeRopa;
import Repositorios.daos.DAO;

public class RepoRopa extends Repositorio {

	private static RepoRopa instance;
	
	
	public static RepoRopa getInstance(DAO dao) {
        if(instance == null){
            instance = new RepoRopa(dao);
        }
        return instance;
    }
	
	private RepoRopa(DAO dao){
        this.setDao(dao);
	}
	private List<TipoDeRopa> ropas;
	
    public List<TipoDeRopa> getTipoDeRopas() {
        return this.ropas;
    }

    public void agregarAlRepositorio(List<TipoDeRopa> unosTiposDeRopa) {
        this.ropas = unosTiposDeRopa;
    }

    public TipoDeRopa findByName(String nombre) {
       return this.ropas.stream().filter(r -> r.compararNombres(nombre)).collect(Collectors.toList()).get(0);
    }
	
}
