package Repositorios.daos;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Dominio.Estilish.Estilo;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import db.EntidadPersistente;

public class DAOMemoria implements DAO {
    private List<EntidadPersistente> entities;

    public DAOMemoria(List<EntidadPersistente> entidades){
        this.entities = entidades;
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> List<T> buscarTodos() {
        return (List<T>) this.entities;
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> T buscar(int id) {
        return (T) this.entities
                .stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public void agregar(Object unObjeto) {
        this.entities.add((EntidadPersistente)unObjeto);
    }

    @Override
    public void modificar(Object unObjeto) {

    }

    @Override
    public void eliminar(Object unObjeto) {
        this.entities.remove(unObjeto);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Usuario> getUsuariosConEventosProximosYSinNotificar()
    {
    	return ((List<Usuario>)(List<?>) entities).stream().filter(u -> u.tieneEventosProximos() && u.tieneEventoSinNotificar()).collect(Collectors.toList());
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Usuario> getUsuariosConEventosProximosYnotificados()
    {
    	return ((List<Usuario>)(List<?>) entities).stream().filter(u -> u.tieneEventosProximos() && !u.tieneEventoSinNotificar()).collect(Collectors.toList());
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Usuario> getUsuariosConEventosOcurridos(Calendar fecha)
    {
    	return ((List<Usuario>)(List<?>) entities).stream().filter(u -> u.tieneEventosOcurridos(fecha)).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
	@Override
	public boolean existeUsuario(String username, String password) {
		Optional<Usuario> usuario = ((List<Usuario>)(List<?>) entities).stream()
				.filter(c -> Objects.equals(c.getPassword(), password) && Objects.equals(c.getUsername(), username))
				.findFirst();
		if (!usuario.isPresent()) {
			try {
				Usuario usuarioNuevo = new Usuario(username, password);
				usuarioNuevo.agregarGuardarropa(new Guardarropa(usuarioNuevo, Estilo.CASUAL));
				usuarioNuevo.agregarGuardarropa(new Guardarropa(usuarioNuevo, Estilo.FORMAL));
				this.agregar(usuarioNuevo);
			} catch (Exception x) {
				return false;
			}
		}
		return true;
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public Usuario buscarUsuario(String username, String password) {
		return ((List<Usuario>)(List<?>) entities).stream()
				.filter(c -> Objects.equals(c.getPassword(), password) && Objects.equals(c.getUsername(), username))
				.findFirst().get();
	}
    
    
}
