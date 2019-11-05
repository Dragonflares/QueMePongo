package Repositorios.daos;

import java.util.Calendar;
import java.util.List;
import Dominio.UserClasses.Usuario;

public interface DAO {
    public <T> List<T> buscarTodos();
    public <T> T buscar(int id);
    public void agregar(Object unObjeto);
    public void modificar(Object unObjeto);
    public void eliminar(Object unObjeto);
    public List<Usuario> getUsuariosConEventosProximosYSinNotificar();
    public List<Usuario> getUsuariosConEventosProximosYnotificados();
    public List<Usuario> getUsuariosConEventosOcurridos(Calendar fecha);
    public boolean existeUsuario(String username, String password);
    public Usuario buscarUsuario(String username, String password);
}
