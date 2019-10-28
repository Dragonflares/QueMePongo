package Repositorios.daos;


import java.util.Calendar;
import java.util.List;

import Dominio.UserClasses.Usuario;
import Models.Model;

public class DAOMySQL implements DAO {
    private Model model;

    public DAOMySQL(Model model){
        this.model = model;
    }

    @Override
    public <T> List<T> buscarTodos() {
        return this.model.buscarTodos();
    }

    @Override
    public <T> T buscar(int id) {
        return this.model.buscar(id);
    }

    @Override
    public void agregar(Object unObjeto) {
        this.model.agregar(unObjeto);
    }

    @Override
    public void modificar(Object unObjeto) {
        this.model.modificar(unObjeto);
    }

    @Override
    public void eliminar(Object unObjeto) {
        this.model.eliminar(unObjeto);
    }
    
    @Override
    public List<Usuario> getUsuariosConEventosProximosYSinNotificar()
    {
    	return model.buscarPorQuery("FROM Usuario u WHERE "); // TODO
    }
    
    @Override
    public List<Usuario> getUsuariosConEventosProximosYnotificados()
    {
    	return model.buscarPorQuery("FROM Usuario u WHERE "); // TODO
    }
    
    @Override
    public List<Usuario> getUsuariosConEventosOcurridoFrecuentemente(Calendar fecha)
    {
    	return model.buscarPorQuery("FROM Usuario u WHERE "); // TODO
    }
    
    @Override
    public List<Usuario> getUsuariosConEventosOcurridos(Calendar fecha)
    {
    	return model.buscarPorQuery("FROM Usuario u WHERE "); // TODO
    }

}
