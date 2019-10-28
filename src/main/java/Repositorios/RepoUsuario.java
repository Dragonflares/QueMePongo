package Repositorios;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.UserClasses.Usuario;
import Repositorios.daos.DAO;

public class RepoUsuario extends Repositorio{
	private List<Usuario> usuarios; // TODO SACAR 
	private static RepoUsuario instance;
	
	
	public static RepoUsuario getInstance(DAO dao) {
        if(instance == null){
            instance = new RepoUsuario(dao);
        }
        return instance;
    }
	
	private RepoUsuario(DAO dao){
        this.setDao(dao);
	}
	
	public List<Usuario> getUsuariosConEventosProximosYSinNotificar()
	{
		return usuarios.stream().filter(u -> u.tieneEventosProximos() && u.tieneEventoSinNotificar()).collect(Collectors.toList());
	}
	
	public List<Usuario> getUsuariosConEventosProximosYnotificados()
	{
		return usuarios.stream().filter(u -> u.tieneEventosProximos() && !u.tieneEventoSinNotificar()).collect(Collectors.toList());
	}
	
	public List<Usuario> getUsuariosConEventosOcurridoFrecuentemente(Calendar fecha)
	{	
		return usuarios.stream().filter(u -> u.tieneEventosOcurridoFrecuentemente(fecha)).collect(Collectors.toList());
	}
	
	public List<Usuario> getUsuariosConEventosOcurridos(Calendar fecha){	
		return usuarios.stream().filter(u -> u.tieneEventosOcurridos(fecha)).collect(Collectors.toList());
	}
}
