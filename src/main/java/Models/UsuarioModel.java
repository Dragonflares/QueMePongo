package Models;

import java.util.List;
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
}
