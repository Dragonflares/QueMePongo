package Repositorios.factories;

import Models.MoldeModel;
import Repositorios.RepoMolde;
import Repositorios.daos.DAOMemoria;
import Repositorios.daos.DAOMySQL;
import Repositorios.testMemoData.DataMolde;
import config.Config;

public class FactoryRepositorioMolde {
	private static RepoMolde repo;

    public static RepoMolde get(){
        if(repo == null){
            boolean useDataBase = Config.useDataBase;
            if(useDataBase){
                repo = RepoMolde.getInstance(new DAOMySQL(MoldeModel.getInstance()));
            }
            else{
                repo = RepoMolde.getInstance(new DAOMemoria(DataMolde.getList()));
            }
        }
        return repo;
    }
}
