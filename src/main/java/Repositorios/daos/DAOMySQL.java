package Repositorios.daos;


import java.util.List;

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
}
