package Models;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Dominio.UserClasses.Usuario;
import db.EntityManagerHelper;

public class UsuarioModel  extends Model {
    private static UsuarioModel instance;

    public static UsuarioModel getInstance() {
        if(instance == null){
            instance = new UsuarioModel();
        }
        return instance;
    }

    @Override
    public List<Usuario> buscarTodos(){
        return EntityManagerHelper.getEntityManager().createQuery("from Usuario").getResultList();
    }

    @Override
    public Usuario buscar(int id){
        return EntityManagerHelper.getEntityManager().find(Usuario.class, id);
    }
   
    @Override
    public List<Usuario> buscarPorQuery(String query)
    {
    	return EntityManagerHelper.createQuery(query).getResultList();
    }
}
