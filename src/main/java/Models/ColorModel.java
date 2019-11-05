package Models;

import java.util.List;
import Dominio.ClothingClasses.Color;
import db.EntityManagerHelper;

public class ColorModel extends Model {

    private static ColorModel instance;

    public static ColorModel getInstance() {
        if(instance == null){
            instance = new ColorModel();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Color> buscarTodos(){
        return EntityManagerHelper.getEntityManager().createQuery("from Color").getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
    public Color buscar(int id){
        return EntityManagerHelper.getEntityManager().find(Color.class, id);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Color> buscarPorQuery(String query)
    {
    	return EntityManagerHelper.createQuery(query).getResultList();
    }
}
