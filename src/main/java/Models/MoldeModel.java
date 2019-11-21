package Models;
import java.util.List;

import db.EntityManagerHelper;
import Dominio.ClothingClasses.MoldeDeAtuendo;
import Dominio.UserClasses.Usuario;

public class MoldeModel extends Model{
	 private static MoldeModel instance;

    public static MoldeModel getInstance() {
        if(instance == null){
            instance = new MoldeModel();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<MoldeDeAtuendo> buscarTodos(){
        return EntityManagerHelper.getEntityManager().createQuery("from MoldeDeAtuendo").getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
    public MoldeDeAtuendo buscar(int id){
        return EntityManagerHelper.getEntityManager().find(MoldeDeAtuendo.class, id);
    }
    
    @Override
    public List<MoldeDeAtuendo> buscarPorQuery(String query)
    {
    	return  EntityManagerHelper.createQuery(query).getResultList();
    }
}
