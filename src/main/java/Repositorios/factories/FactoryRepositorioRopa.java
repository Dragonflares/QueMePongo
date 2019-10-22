package Repositorios.factories;

import Models.RopaModel;
import Repositorios.RepoRopa;
import Repositorios.daos.DAOMemoria;
import Repositorios.daos.DAOMySQL;
import Repositorios.testMemoData.DataRopa;
import config.Config;


public class FactoryRepositorioRopa {
	private static RepoRopa repo;

    public static RepoRopa get(){
        if(repo == null){
            boolean useDataBase = Config.useDataBase;
            if(useDataBase){
                repo = RepoRopa.getInstance(new DAOMySQL(RopaModel.getInstance()));
            }
            else{
                repo = RepoRopa.getInstance(new DAOMemoria(DataRopa.getList()));
            }
        }
        return repo;
    }
}
