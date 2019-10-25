package Repositorios.factories;

import Repositorios.RepoMaterial;
import Repositorios.daos.DAOMemoria;
import Repositorios.daos.DAOMySQL;
import config.Config;
import Models.MaterialModel;
import Repositorios.testMemoData.DataMaterial;

public class FactoryRepositoriosMaterial {
	
	private static RepoMaterial repo;
	
	public static RepoMaterial get(){
        if(repo == null){
            boolean useDataBase = Config.useDataBase;
            if(useDataBase){
                repo = RepoMaterial.getInstance(new DAOMySQL(MaterialModel.getInstance()));
            }
            else{
                repo = RepoMaterial.getInstance(new DAOMemoria(DataMaterial.getList()));
            }
        }
        return repo;
	}
}