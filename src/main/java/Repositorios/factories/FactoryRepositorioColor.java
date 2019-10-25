package Repositorios.factories;

import Models.ColorModel;

import Repositorios.RepoColor;
import Repositorios.daos.DAOMemoria;
import Repositorios.daos.DAOMySQL;
import Repositorios.testMemoData.DataColor;
import config.Config;


public class FactoryRepositorioColor {

	private static RepoColor repo;
	
	public static RepoColor get(){
        if(repo == null){
            boolean useDataBase = Config.useDataBase;
            if(useDataBase){
                repo = RepoColor.getInstance(new DAOMySQL(ColorModel.getInstance()));
            }
            else{
                repo = RepoColor.getInstance(new DAOMemoria(DataColor.getList()));
            }
        }
        return repo;
	}
}
