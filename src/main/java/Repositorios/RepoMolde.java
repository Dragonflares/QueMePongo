package Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.MoldeDeAtuendo;

public class RepoMolde extends Repositorio{
	private List<MoldeDeAtuendo> moldesDeAtuendo;
	
    public List<MoldeDeAtuendo> getTipoDeRopas() {
        return this.moldesDeAtuendo;
    }

    public void agregarAlRepositorio(List<MoldeDeAtuendo> unosMoldes) {
        this.moldesDeAtuendo = unosMoldes;
    }

    public MoldeDeAtuendo buscarPorNombre(String nombre) {
       return this.moldesDeAtuendo.stream().filter(r -> r.compararNombres(nombre)).collect(Collectors.toList()).get(0);
    }
	
}
