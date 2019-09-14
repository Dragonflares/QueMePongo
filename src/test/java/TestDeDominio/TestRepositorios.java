package TestDeDominio;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Importadores.ImportadorDeColores;
import Importadores.ImportadorDeMateriales;
import Importadores.ImportadorDeRopas;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Color;
import Dominio.ClothingClasses.Material;
import Dominio.ClothingClasses.TipoDeRopa;

public class TestRepositorios {
	/*private Prenda remeraMangaLargaRojaDeAlgodon;
	private Prenda pantalonLargoNegroDeLycra;
	private Atuendo atuendo1;
	private Atuendo atuendo2;
	private RepoMaterial repoMaterial;
	private RepoRopa repoRopa;
	private RepoColor repoColor;*/
	
	private ImportadorDeMateriales importadorMateriales;
	private List<Material> materiales;
	private Material primerMaterial;
	private Material segundoMaterial;
	private Material tercerMaterial;
	private Material ultimoMaterial;
	
	private ImportadorDeColores importadorColores;
	private List<Color> colores;
	private Color primerColor;
	private Color segundoColor;
	private Color tercerColor;
	private Color ultimoColor;
	
	private ImportadorDeRopas importadorRopas;
	private List<TipoDeRopa> ropas;
	private TipoDeRopa primerRopa;
	private TipoDeRopa segundaRopa;
	private TipoDeRopa tercerRopa;
	private TipoDeRopa ultimaRopa;
	
	@Before
	public void init() throws Exception {
		
		//repoColor = RepoColor.getInstance();
		//repoRopa= RepoRopa.getInstance();
		
		materiales = ImportadorDeMateriales.getInstance().levantarMaterialesDePath();
		primerMaterial = materiales.get(0);
		segundoMaterial = materiales.get(1);
		tercerMaterial = materiales.get(2);
		ultimoMaterial = materiales.get(materiales.size()-1);
		
		colores = ImportadorDeColores.getInstance().levantarColoresDePath();
		primerColor = colores.get(0);
		segundoColor = colores.get(1);
		tercerColor = colores.get(2);
		ultimoColor = colores.get(colores.size()-1);
		
		ropas = ImportadorDeRopas.getInstance().levantarTipoDeRopasDePath();
		primerRopa = ropas.get(0); //comente las otras ropas porque habia hecho un json con un solo tipoDeRopa
		segundaRopa = ropas.get(1);
		tercerRopa = ropas.get(2);
		ultimaRopa = ropas.get(ropas.size()-1);
		
		
		/*
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
		*/
	}
	
	@Test
	public void pruebaMaterialesEnRepo() {
		
		Assert.assertEquals(primerMaterial.getNombre(), "Algodon");
		Assert.assertEquals(segundoMaterial.getNombre(), "Cuero");
		Assert.assertEquals(tercerMaterial.getNombre(), "Lana");
		Assert.assertEquals(ultimoMaterial.getNombre(), "Gabardina");
	
	}
	
	@Test
	public void pruebaColoresEnRepo() {
		
		Assert.assertEquals(primerColor.getNombre(), "Rojo");
		Assert.assertEquals(segundoColor.getNombre(),"Azul");
		Assert.assertEquals(tercerColor.getNombre(), "Amarillo");
		Assert.assertEquals(ultimoColor.getNombre(), "Celeste");
		
	}
	
	@Test
	public void pruebaRopasEnRepo() {
	
		Assert.assertEquals(primerRopa.getNombre(), "Remera manga corta");
		Assert.assertEquals(segundaRopa.getNombre(), "Remera manga larga");
		Assert.assertEquals(tercerRopa.getNombre(), "Chomba");
		Assert.assertEquals(ultimaRopa.getNombre(), "Tacos");
		
		Assert.assertEquals(primerRopa.getCategoria(), Categoria.PARTE_SUPERIOR);
		Assert.assertEquals(segundaRopa.getCategoria(), Categoria.PARTE_SUPERIOR);
		Assert.assertEquals(tercerRopa.getCategoria(), Categoria.PARTE_SUPERIOR);
		Assert.assertEquals(ultimaRopa.getCategoria(), Categoria.CALZADO);
	
	}
}