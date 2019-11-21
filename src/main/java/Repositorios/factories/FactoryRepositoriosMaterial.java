package Repositorios.factories;

import Repositorios.RepoMaterial;
import Repositorios.daos.DAOMemoria;
import Repositorios.daos.DAOMySQL;
import config.Config;

import java.io.FileNotFoundException;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Dominio.ClothingClasses.Material;
import Models.MaterialModel;
import Repositorios.testMemoData.DataMaterial;

public class FactoryRepositoriosMaterial {
	
	private static RepoMaterial repo;
	
	public static RepoMaterial get() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
        if(repo == null){
            boolean useDataBase = Config.useDataBase;
            if(useDataBase){
                repo = RepoMaterial.getInstance(new DAOMySQL(MaterialModel.getInstance()));
            }
            else{
                repo = RepoMaterial.getInstance(new DAOMemoria<Material>((List<Material>)DataMaterial.getList()));
            }
        }
        return repo;
	}
}