package Repositorios;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.UserClasses.Usuario;

public class RepoUsuario extends Repositorio{
	private List<Usuario> usuarios;
	private static RepoUsuario instance = new RepoUsuario();
	
	private RepoUsuario(){} // dejar en privado para que no puedan hacer otra instancia

	public static RepoUsuario getInstance()
	{
		return instance;
	}
	
	public List<Usuario> getUsuariosConEventosProximos()
	{
		return usuarios.stream().filter(u -> u.tieneEventosProximos()).collect(Collectors.toList());
	}
	
	public List<Usuario> getUsuariosConEventosOcurridoFrecuentemente(Calendar fecha)
	{	
		return usuarios.stream().filter(u -> u.tieneEventosOcurridoFrecuentemente(fecha)).collect(Collectors.toList());
	}
}
