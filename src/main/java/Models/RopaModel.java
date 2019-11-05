package Models;
import java.util.List;

import db.EntityManagerHelper;
import Dominio.ClothingClasses.TipoDeRopa;

public class RopaModel extends Model{
	private static RopaModel instance;

    public static RopaModel getInstance() {
        if(instance == null){
            instance = new RopaModel();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<TipoDeRopa> buscarTodos(){
        return EntityManagerHelper.getEntityManager().createQuery("from TipoDeRopa").getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
    public TipoDeRopa buscar(int id){
        return EntityManagerHelper.getEntityManager().find(TipoDeRopa.class, id);
    }
    
    @Override
    public List<TipoDeRopa> buscarPorQuery(String query)
    {
    	return (List<TipoDeRopa>) EntityManagerHelper.createQuery(query).getResultList();
    }
}
