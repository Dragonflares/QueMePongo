package Repositorios.daos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.Color;
import Dominio.ClothingClasses.Material;
import Dominio.ClothingClasses.TipoDeRopa;
import Dominio.Estilish.Estilo;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import db.EntidadPersistente;

public class DAOMemoria<T> implements DAO {
    private List<T> entities;

    public DAOMemoria(List<T> entidades){
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
                .filter(a -> ((EntidadPersistente) a).getId() == id)
                .collect(Collectors.toList())
                .get(0);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void agregar(Object unObjeto) {
        this.entities.add((T) unObjeto);
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
    public List<Usuario> getUsuariosConEventosOcurridos(LocalDateTime fecha)
    {
    	return ((List<Usuario>)(List<?>) entities).stream().filter(u -> u.tieneEventosOcurridos(fecha)).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
	@Override
	public boolean existeUsuario(String username, String password) {
		Optional<Usuario> usuario = ((List<Usuario>)(List<?>) entities).stream()
				.filter(c -> Objects.equals(c.getPassword(), password) && Objects.equals(c.getUsername(), username))
				.findFirst();
		return usuario.isPresent();
	
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public Usuario buscarUsuario(String username, String password) {
		return ((List<Usuario>)(List<?>) entities).stream()
				.filter(c -> Objects.equals(c.getPassword(), password) && Objects.equals(c.getUsername(), username))
				.findFirst().get();
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public Material findByNameMaterial(String nombre) {
    	return ((List<Material>) entities).stream()
				.filter(m -> m.compararNombres(nombre))
				.findFirst().get();
    }

    @SuppressWarnings("unchecked")
	@Override
	public TipoDeRopa findByNameTipoDeRopa(String nombre) {
    	return ((List<TipoDeRopa>) entities).stream()
				.filter(m -> m.compararNombres(nombre))
				.findFirst().get();
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public Color findByNameColor(String nombre) {
    	return ((List<Color>) entities).stream()
				.filter(m -> m.compararNombres(nombre))
				.findFirst().get();
    }
    
    
}
