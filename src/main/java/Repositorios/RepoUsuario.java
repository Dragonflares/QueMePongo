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
		return this.dao.getUsuariosConEventosProximosYSinNotificar();
	}
	
	public List<Usuario> getUsuariosConEventosProximosYnotificados()
	{
		return this.dao.getUsuariosConEventosProximosYnotificados();
	}
	
	public List<Usuario> getUsuariosConEventosOcurridoFrecuentemente(Calendar fecha)
	{	
		// este no delega al dao porque segun la entrega 5 los repetitivos quedarán por ahora fuera de esta versión
		return usuarios.stream().filter(u -> u.tieneEventosOcurridoFrecuentemente(fecha)).collect(Collectors.toList());
	}
	
	public List<Usuario> getUsuariosConEventosOcurridos(Calendar fecha)
	{
		return this.dao.getUsuariosConEventosOcurridos(fecha);
	}
	
	public boolean existeUsuario(String username, String password) {
		return this.dao.existeUsuario(username, password);
	}
	
	public Usuario buscarUsuario(String username, String password)
	{
		return this.dao.buscarUsuario(username, password);
	}
}
