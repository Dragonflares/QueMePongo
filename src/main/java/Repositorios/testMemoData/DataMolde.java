package Repositorios.testMemoData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Dominio.ClothingClasses.MoldeDeAtuendo;
import db.EntidadPersistente;

public class DataMolde {
	private static List<MoldeDeAtuendo> moldes = new ArrayList<>();
	
	public static List<EntidadPersistente> getList(){
        if(moldes.size() == 0){
        //TODO
        	addAll();
        }
        return (List<EntidadPersistente>)(List<?>)moldes;
	}
	
	private static void addAll(MoldeDeAtuendo... moldes){
        Collections.addAll(DataMolde.moldes, moldes);
    }
}
