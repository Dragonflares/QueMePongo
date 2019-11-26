package server;

import java.awt.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Capas;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Color;
import Dominio.ClothingClasses.Material;
import Dominio.ClothingClasses.Prenda;
import Dominio.ClothingClasses.TipoDeRopa;
import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Alta;
import Dominio.EventClasses.Baja;
import Dominio.EventClasses.Evento;
import Dominio.EventClasses.Media;
import Dominio.UserClasses.Premium;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import Repositorios.factories.FactoryRepositorioColor;
import Repositorios.factories.FactoryRepositorioRopa;
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
		if(!FactoryRepositorioUsuario.get().existeUsuario("usuario123", "asd123"))
		{
			Usuario usuario1 = new Usuario("usuario123", "asd123");
			Usuario usuario2 = new Usuario("lala", "qwe123");
			
			
			usuario2.setTipoDeCuenta(new Premium());
			Guardarropa guardarropa1 = new Guardarropa(usuario1, Estilo.CASUAL);
			guardarropa1.setNombre("Guardarropas Tranqui");
			Guardarropa guardarropa2 = new Guardarropa(usuario1, Estilo.ELEGANTE);
			guardarropa2.setNombre("Guardarropas Cheto");
			LocalDateTime dentroDeUnaSemana = LocalDateTime.now().plusDays(7);
			Evento evento1 = new Evento("Cumpleaños", LocalDateTime.now().plusDays(2), "Avenida 123", Estilo.ELEGANTE_SPORT, null, new Media());
			Evento evento2 = new Evento("Casamiento", dentroDeUnaSemana, "Calle 13", Estilo.ELEGANTE, null, new Alta());
			Evento evento3 = new Evento("Fiesta", LocalDateTime.now(), "Calle falsa 123", Estilo.CASUAL, null, new Baja());
			Evento evento4 = new Evento("Bautismo", dentroDeUnaSemana, "Iglesa 123", Estilo.FORMAL, null, new Media());
			usuario1.agregarEvento(evento1);
			usuario1.agregarEvento(evento2);
			usuario2.agregarEvento(evento3);
			usuario2.agregarEvento(evento4);

			if(Config.useDataBase)
			{
				Material cuero = new Material("Cuero");
				Material lana = new Material("Lana");
				Material jean = new Material("Jean");
				Material goma = new Material("Goma");
				Material plastico = new Material("Plastico");
				Material gabardina = new Material("Gabardina");
				Material gamuza = new Material("Gamuza");
				FactoryRepositoriosMaterial.get().agregar(new Material("Algodon"));
				FactoryRepositoriosMaterial.get().agregar(new Material("Lycra"));
				FactoryRepositoriosMaterial.get().agregar(cuero);
				FactoryRepositoriosMaterial.get().agregar(lana);
				FactoryRepositoriosMaterial.get().agregar(jean);
				FactoryRepositoriosMaterial.get().agregar(goma);
				FactoryRepositoriosMaterial.get().agregar(plastico);
				FactoryRepositoriosMaterial.get().agregar(new Material("Seda"));
				FactoryRepositoriosMaterial.get().agregar(gamuza);
				FactoryRepositoriosMaterial.get().agregar(gabardina);
				FactoryRepositorioColor.get().agregar(new Color("Rojo",290));
				FactoryRepositorioColor.get().agregar(new Color("Azul",170));
				FactoryRepositorioColor.get().agregar(new Color("Amarillo",0));
				FactoryRepositorioColor.get().agregar(new Color("Blanco",0));
				FactoryRepositorioColor.get().agregar(new Color("Negro",0));

				ArrayList<Capas> capasRemera = new ArrayList<>();
				capasRemera.add(Capas.CAPA1);
				ArrayList<Capas> capasSandalia = new ArrayList<>();
				capasSandalia.add(Capas.CAPA2);
				ArrayList<Material> materialesNoCompatiblesRemera = new ArrayList<>();
				materialesNoCompatiblesRemera.add(cuero);
				materialesNoCompatiblesRemera.add(lana);
				materialesNoCompatiblesRemera.add(goma);
				materialesNoCompatiblesRemera.add(plastico);
				materialesNoCompatiblesRemera.add(gabardina);
				materialesNoCompatiblesRemera.add(gamuza);
				ArrayList<Material> materialesNoCompatiblesSandalia = new ArrayList<>();
				materialesNoCompatiblesSandalia.add(gabardina);
				materialesNoCompatiblesSandalia.add(gamuza);
				ArrayList<Material> materialesNoCompatiblesPantalon = new ArrayList<>();
				materialesNoCompatiblesPantalon.add(cuero);
				materialesNoCompatiblesPantalon.add(lana);
				materialesNoCompatiblesPantalon.add(goma);
				materialesNoCompatiblesPantalon.add(plastico);
				FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Remera manga larga", 4, Categoria.PARTE_SUPERIOR, capasRemera, materialesNoCompatiblesRemera, ""));
				FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Remera manga corta", 4, Categoria.PARTE_SUPERIOR, capasRemera, materialesNoCompatiblesRemera, ""));
				FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Pantalon largo", 4, Categoria.PARTE_INFERIOR, null, materialesNoCompatiblesRemera, ""));
				FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Sandalias", 2, Categoria.CALZADO, capasSandalia, materialesNoCompatiblesSandalia, ""));
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
			Prenda remeraMangaCorta = new Prenda.PrendaBuilder()
					.material("Lycra")
					.nombrePrenda("remeraMangaCorta")
					.setearColores("Negro", "Azul")
					.tipoRopa("Remera manga corta")
					.build();
			Prenda sandaliasVerdes = new Prenda.PrendaBuilder()
					.material("Goma")
					.nombrePrenda("Sandalias Verdes")
					.setearColores("Verde", "Blanco")
					.tipoRopa("Sandalias")
					.build();
			ArrayList<Prenda> prendassugerencia = new ArrayList<Prenda>();
			prendassugerencia.add(remeraMangaLargaRojaDeAlgodon);
			Atuendo sugerencia = new Atuendo(prendassugerencia);
			evento1.agregarSugerencia(sugerencia);
			usuario1.agregarGuardarropa(guardarropa1);
			guardarropa1.agregarPrenda(sandaliasVerdes);
			guardarropa1.permitirAccesoaUsuario(usuario2);
			usuario2.agregarGuardarropa(guardarropa2);
			usuario1.agregarPrendaAGuardarropa(guardarropa1, pantalonLargoNegroDeLycra);
			usuario1.agregarPrendaAGuardarropa(guardarropa1, remeraMangaLargaRojaDeAlgodon);
			usuario1.agregarPrendaAGuardarropa(guardarropa1, remeraMangaCorta);
			evento1.setUltimoAtuendoAceptado(sugerencia);
			usuario1.agregarSugerenciaSinCalificar(sugerencia);
			evento1.setSeNotificoUltimaSugerencia(true);
			evento1.getPrendasUltimoAtuendo();
			//System.out.println(guardarropa1.getCantidadDePrendas());
			
			usuario2.agregarPrendaAGuardarropa(guardarropa2, remeraMangaLargaRojaDeAlgodon);
			FactoryRepositorioUsuario.get().agregar(usuario1);
			FactoryRepositorioUsuario.get().agregar(usuario2);
		}
	}

}