package Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.TipoDeRopa;

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
