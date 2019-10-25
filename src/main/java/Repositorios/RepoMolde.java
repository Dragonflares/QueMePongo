package Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.MoldeDeAtuendo;
import Repositorios.daos.DAO;

public class RepoMolde extends Repositorio{
	private List<MoldeDeAtuendo> moldesDeAtuendo; // TODO SACAR
	
	private static RepoMolde instance;
	
	public static RepoMolde getInstance(DAO dao) {
        if(instance == null){
            instance = new RepoMolde(dao);
        }
        return instance;
    }
	
	private RepoMolde(DAO dao){
        this.setDao(dao);
	}
	
	
    public List<MoldeDeAtuendo> getTipoDeRopas() {
        return this.moldesDeAtuendo;
    }

    public void agregarAlRepositorio(List<MoldeDeAtuendo> unosMoldes) {
        this.moldesDeAtuendo = unosMoldes;
    }

    public MoldeDeAtuendo findByName(String nombre) {
       return this.moldesDeAtuendo.stream().filter(r -> r.compararNombres(nombre)).collect(Collectors.toList()).get(0);
    }
	
}
