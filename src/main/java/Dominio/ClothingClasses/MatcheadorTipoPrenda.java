package Dominio.ClothingClasses;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Alta;
import Dominio.EventClasses.Evento;
import Dominio.EventClasses.ImportanciaEvento;
import Repositorios.RepoColor;
import Repositorios.daos.DAO;
import Repositorios.factories.FactoryRepositorioRopa;
import spark.ModelAndView;

public class MatcheadorTipoPrenda {
	
	private static MatcheadorTipoPrenda instance;
	
	public static MatcheadorTipoPrenda getInstance(DAO dao) {
        if(instance == null){
            instance = new MatcheadorTipoPrenda();
        }
        return instance;
    }
	
	//matchear te dara la ruta de la IMAGEN EN BLANCO correspondiente al tipo de prenda para que luego la prenda obtenga la ruta en base al color
	public static String matchearRuta(Prenda prenda) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		
		if(prenda.getTipoRopa().compararNombres("Nulo")) {
			return "";
		}
		else
		{
			String nombreDeRutaBuscada = FactoryRepositorioRopa.get().findByName(prenda.getTipoRopa().getNombre()).getNombre();
			
			//luego la prenda le asignara la correspondiente en base al color que tenga
			//tener en cuenta que la ruta sera accedida con el Engine por lo que comenzara en public
			String rutaInicial = "/imgPrendas/";
					
	        return rutaInicial.concat(nombreDeRutaBuscada); 
		}

	}
		
}
