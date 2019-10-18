package Repositorios.factories;

import Models.UsuarioModel;
import Repositorios.RepoUsuario;
import Repositorios.daos.DAOMemoria;
import Repositorios.daos.DAOMySQL;
import Repositorios.testMemoData.DataUsuario;
import config.Config;


public class FactoryRepositorioUsuario {
	
	private static RepoUsuario repo;
	
	public static RepoUsuario get(){
        if(repo == null){
            boolean useDataBase = Config.useDataBase;
            if(useDataBase){
                repo = RepoUsuario.getInstance(new DAOMySQL(UsuarioModel.getInstance()));
            }
            else{
                repo = RepoUsuario.getInstance(new DAOMemoria(DataUsuario.getList()));
            }
        }
        return repo;
    }
}
