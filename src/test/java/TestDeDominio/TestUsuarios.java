//package TestDeDominio;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import Dominio.Atuendo;
//import Dominio.Categoria;
//import Dominio.Color;
//import Dominio.Guardarropa;
//import Dominio.Material;
//import Dominio.Prenda;
//import Dominio.TipoPrenda;
//import Dominio.Usuario;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Assert;
//
//
//public class TestUsuarios {
//
//	private Usuario julieta;
//	private Guardarropa guardarropas1;
//	private Atuendo atuendo;
//	private Prenda remeraRoja;
//	private Guardarropa guardarropas2;
//	public 	List<Material> materialesNoCompatiblesRem = new ArrayList<>();
//	@Before
//	public void init() throws Exception {
//
//		List<Material> materialesNoCompatiblesZapas = new ArrayList<>();
//		materialesNoCompatiblesZapas.add(Material.LYCRA);
//		materialesNoCompatiblesZapas.add(Material.GABARDINA);
//
//		List<Material> materialesNoCompatiblesCin = new ArrayList<>();
//		materialesNoCompatiblesCin.add(Material.LANA);
//		materialesNoCompatiblesCin.add(Material.GABARDINA);
//		
//		List<Material> materialesNoCompatiblesPant = new ArrayList<>();
//		materialesNoCompatiblesPant.add(Material.PLASTICO);
//		materialesNoCompatiblesPant.add(Material.GOMA);
//		
//
//		materialesNoCompatiblesRem.add(Material.CUERO);
//		materialesNoCompatiblesRem.add(Material.GABARDINA);
//		
//		
//		Prenda zapatillasNegras = new Prenda(new TipoPrenda(Categoria.CALZADO, materialesNoCompatiblesZapas), Color.NEGRO, Color.GRIS, Material.CUERO);
//		Prenda cinturonNaranja = new Prenda(new TipoPrenda(Categoria.ACCESORIOS, materialesNoCompatiblesCin), Color.NARANJA, Color.NEGRO, Material.CUERO);
//		Prenda pantalonAzul = new Prenda(new TipoPrenda(Categoria.PARTE_INFERIOR, materialesNoCompatiblesPant), Color.AZUL, Color.AMARILLO, Material.JEAN);
//		Prenda remeraAzul = new Prenda(new TipoPrenda(Categoria.PARTE_SUPERIOR, materialesNoCompatiblesRem), Color.AZUL, Color.AMARILLO, Material.ALGODON);
//		Prenda remeraRoja = new Prenda(new TipoPrenda(Categoria.PARTE_SUPERIOR, materialesNoCompatiblesRem), Color.ROJO, Color.AMARILLO, Material.ALGODON);
//		
//		
//		ArrayList<Prenda> prendas = new ArrayList<Prenda>();
//		prendas.add(cinturonNaranja);
//		prendas.add(remeraRoja);
//		prendas.add(remeraAzul);
//		prendas.add(pantalonAzul);
//		prendas.add(zapatillasNegras);
//		prendas.add(cinturonNaranja);
//		
//		ArrayList<Prenda> atuendo1 = new ArrayList<Prenda>();
//		atuendo1.add(remeraRoja);
//		atuendo1.add(pantalonAzul);
//		atuendo1.add(zapatillasNegras);
//		atuendo1.add(cinturonNaranja);
//
//		atuendo = new Atuendo(atuendo1);
//		
//		guardarropas1 = new Guardarropa(prendas);
//		guardarropas2 = new Guardarropa(prendas);
//		
//		julieta = new Usuario("giu", guardarropas1);
//	}
//
//	@Test
//	public void agregadoDeGuardarropas() {
//		Assert.assertEquals(guardarropas1, julieta.guardarropas.get(0));
//	}
//	
////	@Test
////	public void laPrimerPrendaDelPrimerAtuendoEsUnaRemeraRoja() {
////		Assert.assertEquals(atuendo.getPrendas().get(0), guardarropas1.generarRecomendacion().get(0).getPrendas().get(0));
////	}
////	
////	@Test
////	public void laSegundaPrendaDelPrimerAtuendoEsUnPantalonAzul() {
////		Assert.assertEquals(atuendo.getPrendas().get(1), guardarropas1.generarRecomendacion().get(0).getPrendas().get(1));
////	}
//	
//	@Test
//	public void nosRecomiendaUnAtuendo() {
//		Assert.assertNotNull(atuendo);
//	}
//	
//	@Test
//	public void dosRemerasSonDelMismoTipo() {
//		Assert.assertTrue(atuendo.sonDeDistintoTipo(atuendo.getPrendas()));
//	}
//	
//	@Test(expected=Exception.class)
//	
//	public void usuarioAgregaPrendaRepetida() throws Exception {
//		julieta.agregarGuardarropa(guardarropas2);
//		Prenda prenda = new Prenda(new TipoPrenda(Categoria.PARTE_SUPERIOR, materialesNoCompatiblesRem), Color.BLANCO, Color.AMARILLO, Material.ALGODON);
//		julieta.agregarPrendaAGuardarropa(guardarropas2, prenda);
//		julieta.agregarPrendaAGuardarropa(guardarropas1, prenda);
//	}
//}
