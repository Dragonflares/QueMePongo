package server;

import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import Repositorios.factories.FactoryRepositorioUsuario;
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
		Usuario usuario1 = new Usuario("usuario123", "asd123");
		Usuario usuario2 = new Usuario("lala", "qwe123");
		Guardarropa guardarropa1 = new Guardarropa(usuario1, Estilo.CASUAL);
		Guardarropa guardarropa2 = new Guardarropa(usuario1, Estilo.ELEGANTE);
		
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
		System.out.println("PRENDASSSSSS");
		System.out.println(remeraMangaLargaRojaDeAlgodon);
		System.out.println(pantalonLargoNegroDeLycra);
		usuario1.agregarGuardarropa(guardarropa1);
		usuario2.agregarGuardarropa(guardarropa2);
		// TODO HACER QUE FUNCIONE agregarPrendaAGuardarropa
		usuario1.agregarPrendaAGuardarropa(guardarropa1, pantalonLargoNegroDeLycra);
		usuario2.agregarPrendaAGuardarropa(guardarropa2, remeraMangaLargaRojaDeAlgodon);
		FactoryRepositorioUsuario.get().agregar(usuario1);
		FactoryRepositorioUsuario.get().agregar(usuario2);
	}

}