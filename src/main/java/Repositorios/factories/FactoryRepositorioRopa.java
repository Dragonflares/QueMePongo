package Repositorios.factories;

import java.io.FileNotFoundException;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Dominio.ClothingClasses.Material;
import Dominio.ClothingClasses.TipoDeRopa;
import Models.RopaModel;
import Repositorios.RepoRopa;
import Repositorios.daos.DAOMemoria;
import Repositorios.daos.DAOMySQL;
import Repositorios.testMemoData.DataMaterial;
import Repositorios.testMemoData.DataRopa;
import config.Config;


public class FactoryRepositorioRopa {
	
	private static RepoRopa repo;

    public static RepoRopa get() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
        if(repo == null){
            boolean useDataBase = Config.useDataBase;
            if(useDataBase){
                repo = RepoRopa.getInstance(new DAOMySQL(RopaModel.getInstance()));
            }
            else{
                repo = RepoRopa.getInstance(new DAOMemoria<TipoDeRopa>((List<TipoDeRopa>)DataRopa.getList()));
            }
        }
        return repo;
    }
}
