package server;

import java.time.LocalDateTime;

import Dominio.ClothingClasses.Material;
import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Alta;
import Dominio.EventClasses.Baja;
import Dominio.EventClasses.Evento;
import Dominio.EventClasses.Media;
import Dominio.UserClasses.Premium;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import Repositorios.factories.FactoryRepositorioUsuario;
import Repositorios.factories.FactoryRepositoriosMaterial;
import config.Config;
import entities.ProcessingDataFailedException;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) throws ProcessingDataFailedException, Exception {
		insertarUsuariosParaProbar();
		
		Spark.port(getSparkAssignedPort());
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

	static int getSparkAssignedPort() {
//		ProcessBuilder processBuilder = new ProcessBuilder();
//		if (processBuilder.environment().get("9003") != null) {
//			return Integer.parseInt(processBuilder.environment().get("9003"));
//		}
		return 9009; //return default port if saprk-port isn't set (i.e. on localhost)
	}
	
	public static void insertarUsuariosParaProbar() throws ProcessingDataFailedException, Exception 
	{
		//if(!FactoryRepositorioUsuario.get().existeUsuario("usuario123", "asd123"))
		//{
			Usuario usuario1 = new Usuario("usuario123", "asd123");
			Usuario usuario2 = new Usuario("lala", "qwe123");
			usuario2.setTipoDeCuenta(new Premium());
			Guardarropa guardarropa1 = new Guardarropa(usuario1, Estilo.CASUAL);
			guardarropa1.setNombre("Guardarropas Tranqui");
			Guardarropa guardarropa2 = new Guardarropa(usuario1, Estilo.ELEGANTE);
			guardarropa2.setNombre("Guardarropas Cheto");
			LocalDateTime dentroDeUnaSemana = LocalDateTime.now().plusDays(7);
			Evento evento1 = new Evento("Cumpleaņos", LocalDateTime.now(), "Avenida 123", Estilo.ELEGANTE_SPORT, null, new Media());
			Evento evento2 = new Evento("Casamiento", dentroDeUnaSemana, "Calle 13", Estilo.ELEGANTE, null, new Alta());
			Evento evento3 = new Evento("Fiesta", LocalDateTime.now(), "Calle falsa 123", Estilo.CASUAL, null, new Baja());
			Evento evento4 = new Evento("Bautismo", dentroDeUnaSemana, "Iglesa 123", Estilo.FORMAL, null, new Media());
			usuario1.agregarEvento(evento1);
			usuario1.agregarEvento(evento2);
			usuario2.agregarEvento(evento3);
			usuario2.agregarEvento(evento4);
			
			if(Config.useDataBase)
			{
				FactoryRepositoriosMaterial.get().agregar(new Material("Algodon"));
				FactoryRepositoriosMaterial.get().agregar(new Material("Lycra"));
				FactoryRepositoriosMaterial.get().agregar("Cuero");
				FactoryRepositoriosMaterial.get().agregar("Lana");
				FactoryRepositoriosMaterial.get().agregar("Jean");
				FactoryRepositoriosMaterial.get().agregar("Goma");
				FactoryRepositoriosMaterial.get().agregar("Plastico");
				FactoryRepositoriosMaterial.get().agregar("Seda");
				FactoryRepositoriosMaterial.get().agregar("Gamuza");
				FactoryRepositoriosMaterial.get().agregar("Gabardina");
			}
			
			Prenda remeraMangaLargaRojaDeAlgodon = new Prenda.PrendaBuilder()
					.material("Algodon")
					.nombrePrenda("remeraMangaLargaRojaDeAlgodon")
					.setearColores("Rojo", "Negro")
					.tipoRopa("Remera manga larga")
					.build();
			Prenda pantalonLargoNegroDeLycra = new Prenda.PrendaBuilder()
					.material("Lycra")
					.nombrePrenda("pantalonLargoNegroDeLycra")
					.setearColores("Negro", "Azul")
					.tipoRopa("Pantalon largo")
					.build();
			
			//toy probando nomas XD
			System.out.println(remeraMangaLargaRojaDeAlgodon.getMaterial().getNombre());
			System.out.println(remeraMangaLargaRojaDeAlgodon.getColorPrimario().getNombre());
			System.out.println(remeraMangaLargaRojaDeAlgodon.getColorSecundario().getNombre());
			System.out.println(remeraMangaLargaRojaDeAlgodon.getTipoRopa().getNombre());
			
			usuario1.agregarGuardarropa(guardarropa1);
			guardarropa1.permitirAccesoaUsuario(usuario2);
			usuario2.agregarGuardarropa(guardarropa2);
			usuario1.agregarPrendaAGuardarropa(guardarropa1, pantalonLargoNegroDeLycra);
			
			//System.out.println(guardarropa1.getCantidadDePrendas());
			
			usuario2.agregarPrendaAGuardarropa(guardarropa2, remeraMangaLargaRojaDeAlgodon);
			FactoryRepositorioUsuario.get().agregar(usuario1);
			FactoryRepositorioUsuario.get().agregar(usuario2);
		//}
	}

}