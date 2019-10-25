package Models;
import java.util.List;

import config.Config;
import db.EntityManagerHelper;

import Dominio.ClothingClasses.MoldeDeAtuendo;

public class MoldeModel extends Model{
	 private static MoldeModel instance;

	    public static MoldeModel getInstance() {
	        if(instance == null){
	            instance = new MoldeModel();
	        }
	        return instance;
	    }

	    @Override
	    public List<MoldeDeAtuendo> buscarTodos(){
	        return EntityManagerHelper.getEntityManager().createQuery("from MoldeDeAtuendo").getResultList();
	    }

	    @Override
	    public MoldeDeAtuendo buscar(int id){
	        return EntityManagerHelper.getEntityManager().find(MoldeDeAtuendo.class, id);
	    }
}
