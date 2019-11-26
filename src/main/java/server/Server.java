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
		if(!Config.useDataBase || !FactoryRepositorioUsuario.get().existeUsuario("aroco", "123456"))
		{
		Usuario usuario1 = new Usuario("aroco", "123456");
		Usuario usuario2 = new Usuario("jazul", "123456");

		usuario2.setTipoDeCuenta(new Premium());

		Guardarropa guardarropa1 = new Guardarropa(usuario1, Estilo.CASUAL);
		guardarropa1.setNombre("Guardarropas");
		Guardarropa guardarropa2 = new Guardarropa(usuario1, Estilo.CASUAL);
		guardarropa2.setNombre("Guardarropas");
		LocalDateTime dentroDeUnaSemana = LocalDateTime.now().plusDays(7);
		Evento evento1 = new Evento("Cumpleanios", LocalDateTime.now().plusDays(2), "Avenida 123", Estilo.ELEGANTE_SPORT, null, new Media());
		Evento evento2 = new Evento("Casamiento", dentroDeUnaSemana, "Calle 13", Estilo.ELEGANTE, null, new Alta());
		Evento evento3 = new Evento("Fiesta", LocalDateTime.now(), "Calle falsa 123", Estilo.CASUAL, null, new Baja());
		Evento evento4 = new Evento("Bautismo", dentroDeUnaSemana, "Iglesa 123", Estilo.FORMAL, null, new Media());
		usuario1.agregarEvento(evento1);
		usuario1.agregarEvento(evento2);
		usuario2.agregarEvento(evento3);
		usuario2.agregarEvento(evento4);

		if(Config.useDataBase)
		{
			Material algodon = new Material("Algodon");
			Material lycra = new Material("Lycra");
			Material cuero = new Material("Cuero");
			Material poliester = new Material("Poliester");
			Material jean = new Material("Jean");
			Material nylon = new Material("Nylon");
			Material seda = new Material("Seda");
			FactoryRepositoriosMaterial.get().agregar(algodon);
			FactoryRepositoriosMaterial.get().agregar(lycra);
			FactoryRepositoriosMaterial.get().agregar(cuero);
			FactoryRepositoriosMaterial.get().agregar(poliester);
			FactoryRepositoriosMaterial.get().agregar(jean);
			FactoryRepositoriosMaterial.get().agregar(nylon);
			FactoryRepositoriosMaterial.get().agregar(seda);

			FactoryRepositorioColor.get().agregar(new Color("Rojo",290));
			FactoryRepositorioColor.get().agregar(new Color("Azul",170));
			FactoryRepositorioColor.get().agregar(new Color("Amarillo",0));
			FactoryRepositorioColor.get().agregar(new Color("Blanco",0));
			FactoryRepositorioColor.get().agregar(new Color("Negro",0));
			FactoryRepositorioColor.get().agregar(new Color("Celeste",0));
			FactoryRepositorioColor.get().agregar(new Color("Verde",0));

			ArrayList<Capas> capa1 = new ArrayList<>();
			capa1.add(Capas.CAPA1);
			ArrayList<Capas> capas23 = new ArrayList<>();
			capas23.add(Capas.CAPA2);
			capas23.add(Capas.CAPA3);
			ArrayList<Capas> capas234 = new ArrayList<>();
			capas234.add(Capas.CAPA2);
			capas234.add(Capas.CAPA3);
			capas234.add(Capas.CAPA4);
			ArrayList<Capas> capas2 = new ArrayList<>();
			capas2.add(Capas.CAPA2);
			ArrayList<Material> MNCCueroJeanNylon = new ArrayList<>();
			MNCCueroJeanNylon.add(cuero);
			MNCCueroJeanNylon.add(jean);
			MNCCueroJeanNylon.add(nylon);
			ArrayList<Material> MNCLycraCueroJeanNylon = new ArrayList<>();
			MNCLycraCueroJeanNylon.add(lycra);
			MNCLycraCueroJeanNylon.add(cuero);
			MNCLycraCueroJeanNylon.add(jean);
			MNCLycraCueroJeanNylon.add(nylon);
			ArrayList<Material> MNCLycraJean = new ArrayList<>();
			MNCLycraJean.add(lycra);
			MNCLycraJean.add(jean);
			ArrayList<Material> MNCLycraCuero = new ArrayList<>();
			MNCLycraCuero.add(lycra);
			MNCLycraCuero.add(cuero);
			ArrayList<Material> MNCCueroJeanSeda = new ArrayList<>();
			MNCCueroJeanSeda.add(cuero);
			MNCCueroJeanSeda.add(jean);
			MNCCueroJeanSeda.add(seda);
			ArrayList<Material> MNCCueroPoliesterJeanNylonSeda = new ArrayList<>();
			MNCCueroPoliesterJeanNylonSeda.add(cuero);
			MNCCueroPoliesterJeanNylonSeda.add(poliester);
			MNCCueroPoliesterJeanNylonSeda.add(jean);
			MNCCueroPoliesterJeanNylonSeda.add(nylon);
			MNCCueroPoliesterJeanNylonSeda.add(seda);
			ArrayList<Material> MNCAlgodonLycraPoliesterJeanNylonSeda = new ArrayList<>();
			MNCAlgodonLycraPoliesterJeanNylonSeda.add(algodon);
			MNCAlgodonLycraPoliesterJeanNylonSeda.add(lycra);
			MNCAlgodonLycraPoliesterJeanNylonSeda.add(poliester);
			MNCAlgodonLycraPoliesterJeanNylonSeda.add(jean);
			MNCAlgodonLycraPoliesterJeanNylonSeda.add(nylon);
			MNCAlgodonLycraPoliesterJeanNylonSeda.add(seda);

			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Nulo", 0, Categoria.CALZADO, capas2, null, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Remera cuello redondo manga corta", 3, Categoria.PARTE_SUPERIOR, capa1, MNCCueroJeanNylon, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Remera cuello redondo manga larga", 4, Categoria.PARTE_SUPERIOR, capa1, MNCCueroJeanNylon, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Remera escote V manga corta", 3, Categoria.PARTE_SUPERIOR, capa1, MNCCueroJeanNylon, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Remera escote V manga larga", 4, Categoria.PARTE_SUPERIOR, capa1, MNCCueroJeanNylon, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Sueter", 7, Categoria.PARTE_SUPERIOR, capas23, MNCLycraCueroJeanNylon, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Campera", 7, Categoria.PARTE_SUPERIOR, capas234, MNCLycraJean, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Pantalon largo", 11, Categoria.PARTE_INFERIOR, capas2, MNCLycraCuero, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Pantalon corto", 6, Categoria.PARTE_INFERIOR, capas2, MNCLycraCuero, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Bermuda", 7, Categoria.PARTE_INFERIOR, capas2, MNCLycraCuero, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Pollera", 5, Categoria.PARTE_INFERIOR, capas2, MNCLycraCuero, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Buzo", 6, Categoria.PARTE_SUPERIOR, capas23, MNCCueroPoliesterJeanNylonSeda, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Musculosa", 1, Categoria.PARTE_SUPERIOR, capa1, MNCCueroJeanSeda, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Zapatillas", 7, Categoria.CALZADO, capas2, MNCAlgodonLycraPoliesterJeanNylonSeda, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Zapatos", 6, Categoria.CALZADO, capas2, MNCAlgodonLycraPoliesterJeanNylonSeda, ""));
			FactoryRepositorioRopa.get().agregar(new TipoDeRopa("Sandalias", 5, Categoria.CALZADO, capas2, MNCAlgodonLycraPoliesterJeanNylonSeda, ""));
		}

		Prenda RemeraCuelloRedondoMangaCorta1 = new Prenda.PrendaBuilder()
				.material("Algodon")
				.nombrePrenda("Remera cuello redondo manga corta")
				.setearColores("Negro","Rojo")
				.tipoRopa("Remera cuello redondo manga corta")
				.build();
		Prenda RemeraEscoteVMangaCorta1 = new Prenda.PrendaBuilder()
				.material("Lycra")
				.nombrePrenda("Remera escote V manga corta")
				.setearColores("Blanco", "Rojo")
				.tipoRopa("Remera escote V manga corta")
				.build();
		Prenda Musculosa1 = new Prenda.PrendaBuilder()
				.material("Lycra")
				.nombrePrenda("Musculosa")
				.setearColores("Amarillo", "Blanco")
				.tipoRopa("Musculosa")
				.build();
		Prenda Campera1 = new Prenda.PrendaBuilder()
				.material("Cuero")
				.nombrePrenda("Campera")
				.setearColores("Blanco", "Verde")
				.tipoRopa("Campera")
				.build();
		Prenda Sueter1 = new Prenda.PrendaBuilder()
				.material("Poliester")
				.nombrePrenda("Sueter")
				.setearColores("Blanco", "Verde")
				.tipoRopa("Sueter")
				.build();
		Prenda Bermuda1 = new Prenda.PrendaBuilder()
				.material("Jean")
				.nombrePrenda("Bermuda")
				.setearColores("Celeste", "Blanco")
				.tipoRopa("Bermuda")
				.build();
		Prenda PantalonLargo1 = new Prenda.PrendaBuilder()
				.material("Nylon")
				.nombrePrenda("Pantalon largo")
				.setearColores("Celeste", "Blanco")
				.tipoRopa("Pantalon largo")
				.build();
		Prenda Zapatillas1 = new Prenda.PrendaBuilder()
				.material("Nylon")
				.nombrePrenda("Zapatillas")
				.setearColores("Rojo", "Blanco")
				.tipoRopa("Zapatillas")
				.build();
		Prenda Zapatos1 = new Prenda.PrendaBuilder()
				.material("Cuero")
				.nombrePrenda("Zapatos")
				.setearColores("Negro", "Blanco")
				.tipoRopa("Zapatos")
				.build();

		Prenda RemeraCuelloRedondaMangaLarga2 = new Prenda.PrendaBuilder()
				.material("Lycra")
				.nombrePrenda("Remera cuello redondo manga larga")
				.setearColores("Amarillo", "Rojo")
				.tipoRopa("Remera cuello redondo manga larga")
				.build();
		Prenda RemeraEscoteVMangaLarga2 = new Prenda.PrendaBuilder()
				.material("Algodon")
				.nombrePrenda("Remera escote V manga larga")
				.setearColores("Blanco", "Rojo")
				.tipoRopa("Remera escote V manga larga")
				.build();
		Prenda Musculosa2 = new Prenda.PrendaBuilder()
				.material("Lycra")
				.nombrePrenda("Musculosa")
				.setearColores("Verde", "Rojo")
				.tipoRopa("Musculosa")
				.build();
		Prenda Sueter2 = new Prenda.PrendaBuilder()
				.material("Poliester")
				.nombrePrenda("Sueter")
				.setearColores("Verde", "Rojo")
				.tipoRopa("Sueter")
				.build();
		Prenda Pollera2 = new Prenda.PrendaBuilder()
				.material("Seda")
				.nombrePrenda("Pollera")
				.setearColores("Negro", "Rojo")
				.tipoRopa("Pollera")
				.build();
		Prenda Zapatos2 = new Prenda.PrendaBuilder()
				.material("Cuero")
				.nombrePrenda("Zapatos")
				.setearColores("Negro", "Rojo")
				.tipoRopa("Zapatos")
				.build();
		Prenda Buzo2 = new Prenda.PrendaBuilder()
				.material("Algodon")
				.nombrePrenda("Buzo")
				.setearColores("Blanco", "Rojo")
				.tipoRopa("Buzo")
				.build();
		Prenda Sandalias2 = new Prenda.PrendaBuilder()
				.material("Cuero")
				.nombrePrenda("Sandalias")
				.setearColores("Negro", "Rojo")
				.tipoRopa("Sandalias")
				.build();

		ArrayList<Prenda> prendassugerencia = new ArrayList<Prenda>();
		prendassugerencia.add(Sandalias2);
		Atuendo sugerencia = new Atuendo(prendassugerencia);
		evento1.agregarSugerencia(sugerencia);

		usuario1.agregarGuardarropa(guardarropa1);

		usuario1.agregarPrendaAGuardarropa(guardarropa1, RemeraCuelloRedondoMangaCorta1);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, RemeraEscoteVMangaCorta1);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, Musculosa1);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, Campera1);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, Sueter1);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, Bermuda1);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, PantalonLargo1);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, Zapatillas1);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, Zapatos1);

		usuario1.agregarGuardarropa(guardarropa2);
		
		usuario2.agregarPrendaAGuardarropa(guardarropa2, RemeraCuelloRedondaMangaLarga2);
		usuario2.agregarPrendaAGuardarropa(guardarropa2, RemeraEscoteVMangaLarga2);
		usuario2.agregarPrendaAGuardarropa(guardarropa2, Musculosa2);
		usuario2.agregarPrendaAGuardarropa(guardarropa2, Sueter2);
		usuario2.agregarPrendaAGuardarropa(guardarropa2, Zapatos2);
		usuario2.agregarPrendaAGuardarropa(guardarropa2, Buzo2);
		usuario2.agregarPrendaAGuardarropa(guardarropa2, Sandalias2);
		usuario2.agregarPrendaAGuardarropa(guardarropa2, Pollera2);

		guardarropa1.permitirAccesoaUsuario(usuario2);
		usuario2.agregarGuardarropa(guardarropa2);
		evento1.setUltimoAtuendoAceptado(sugerencia);
		usuario1.agregarSugerenciaSinCalificar(sugerencia);
		evento1.setSeNotificoUltimaSugerencia(true);
		evento1.getPrendasUltimoAtuendo();
		FactoryRepositorioUsuario.get().agregar(usuario1);
		FactoryRepositorioUsuario.get().agregar(usuario2);
		}
	}
}

