package TestDeDominio;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Dominio.UserClasses.Usuario;
import Repositorios.RepoColor;
import Repositorios.RepoMaterial;
import Repositorios.RepoRopa;
import entities.ProcessingDataFailedException;
import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Prenda;
import Dominio.ClothingClasses.Prenda.PrendaBuilder;

public class TestPrenda {
	private Prenda remeraMangaLargaRojaDeAlgodon;
	private Prenda pantalonLargoNegroDeLycra;
	private Atuendo atuendo1;
	private Atuendo atuendo2;
	private RepoMaterial repoMaterial;
	private RepoRopa repoRopa;
	private RepoColor repoColor;
	
	@Before
	public void init() throws Exception {
		repoColor = RepoColor.getInstance();
		repoRopa= RepoRopa.getInstance();
		repoMaterial = RepoMaterial.getInstance();
		
		remeraMangaLargaRojaDeAlgodon = new Prenda.PrendaBuilder()
				.material("Algodon")
				.nombrePrenda("remeraMangaLargaRojaDeAlgodon")
				.setearColores("Rojo", "Negro")
				.tipoRopa("Remera manga larga")
				.build();
		pantalonLargoNegroDeLycra = new Prenda.PrendaBuilder()
				.material("Lycra")
				.nombrePrenda("pantalonLargoNegroDeLycra")
				.setearColores("Negro", "Azul")
				.tipoRopa("Pantalon largo")
				.build();
		ArrayList<Prenda> prendasOrden1 = new ArrayList<Prenda>();
		ArrayList<Prenda> prendasOrden2 = new ArrayList<Prenda>();
		
		prendasOrden1.add(remeraMangaLargaRojaDeAlgodon);
		prendasOrden1.add(pantalonLargoNegroDeLycra);
		prendasOrden2.add(remeraMangaLargaRojaDeAlgodon);
		prendasOrden2.add(pantalonLargoNegroDeLycra);
//		prendasOrden2.add(remeraMangaLargaRojaDeAlgodon);
		
		atuendo1 = new Atuendo(prendasOrden1);
		atuendo2 = new Atuendo(prendasOrden2);
	}
	
	@Test
	public void pruebaAtuendosIguales() {
		Assert.assertTrue(atuendo1.esElMismoAtuendo(atuendo2));;
	}
	
}


//ParametersPaso nuevo  = new ParametersPaso.ParametersPasoBuilder().evento(Evento).build();