package Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import Dominio.UserClasses.Evento;

public class RepoEvento extends Repositorio{
	private List<Evento> eventos;
	
	public static RepoEvento getInstance() {
        return instance;
    }

    private static RepoEvento instance = new RepoEvento();

    private RepoEvento() { //dejar en privado para que no puedan hacer otra instancia
    }
    
    public List<Evento> getEventosProximos()
    {
    	return eventos.stream().filter(e -> e.estaProximo()).collect(Collectors.toList());
    }
}
