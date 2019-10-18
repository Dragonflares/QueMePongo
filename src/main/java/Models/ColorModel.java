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

    @Override
    public List<Color> buscarTodos(){
        return EntityManagerHelper.getEntityManager().createQuery("from Usuario").getResultList();
    }

    @Override
    public Color buscar(int id){
        return EntityManagerHelper.getEntityManager().find(Color.class, id);
    }
}
