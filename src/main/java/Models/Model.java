package Models;

import java.util.List;

import db.EntityManagerHelper;

public abstract class Model {
    public void agregar(Object object){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(object);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void modificar(Object object){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(object);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(Object object){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(object);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public abstract <T> List<T> buscarTodos();

    public abstract <T> T buscar(int id);
}
