package Models;

import java.util.List;

import Dominio.ClothingClasses.Material;
import db.EntityManagerHelper;

public class MaterialModel extends Model{
	private static MaterialModel instance;

    public static MaterialModel getInstance() {
        if(instance == null){
            instance = new MaterialModel();
        }
        return instance;
    }

    @Override
    public List<Material> buscarTodos(){
        return EntityManagerHelper.getEntityManager().createQuery("from Material").getResultList();
    }

    @Override
    public Material buscar(int id){
        return EntityManagerHelper.getEntityManager().find(Material.class, id);
    }
}
