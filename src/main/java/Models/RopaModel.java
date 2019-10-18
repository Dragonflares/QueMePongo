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

    @Override
    public List<TipoDeRopa> buscarTodos(){
        return EntityManagerHelper.getEntityManager().createQuery("from TipoDeRopa").getResultList();
    }

    @Override
    public TipoDeRopa buscar(int id){
        return EntityManagerHelper.getEntityManager().find(TipoDeRopa.class, id);
    }
}
