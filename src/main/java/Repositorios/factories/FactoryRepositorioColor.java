package Repositorios.factories;

import java.io.FileNotFoundException;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Dominio.ClothingClasses.Color;
import Dominio.ClothingClasses.Material;
import Models.ColorModel;

import Repositorios.RepoColor;
import Repositorios.daos.DAOMemoria;
import Repositorios.daos.DAOMySQL;
import Repositorios.testMemoData.DataColor;
import Repositorios.testMemoData.DataMaterial;
import config.Config;


public class FactoryRepositorioColor {

	private static RepoColor repo;
	
	public static RepoColor get() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
        if(repo == null){
            boolean useDataBase = Config.useDataBase;
            if(useDataBase){
                repo = RepoColor.getInstance(new DAOMySQL(ColorModel.getInstance()));
            }
            else{
                repo = RepoColor.getInstance(new DAOMemoria<Color>((List<Color>)DataColor.getList()));
            }
        }
        return repo;
	}
}
