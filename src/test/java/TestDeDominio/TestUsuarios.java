package TestDeDominio;

import org.junit.Before;
import org.junit.Test;

import Dominio.Atuendo;
import Dominio.Categoria;
import Dominio.Color;
import Dominio.Guardarropa;
import Dominio.Material;
import Dominio.Prenda;
import Dominio.TipoPrenda;
import Dominio.Usuario;

import java.util.ArrayList;

import org.junit.Assert;


public class TestUsuarios {

	private Usuario julieta;
	private Guardarropa guardarropas1;
	private Atuendo atuendo;
	private Prenda remeraRoja;
	private Prenda remeraAzul;
	
	@Before
	public void init() throws Exception {

		Prenda zapatillasNegras = new Prenda(TipoPrenda.ZAPATILLAS, Color.NEGRO, Color.GRIS, Material.CUERO);
		Prenda cinturonNaranja = new Prenda(TipoPrenda.CINTURON, Color.NARANJA, Color.NEGRO, Material.CUERO);
		Prenda pantalonAzul = new Prenda(TipoPrenda.PANTALON, Color.AZUL, Color.AMARILLO, Material.JEAN);
		remeraAzul = new Prenda(TipoPrenda.REMERA, Color.AZUL, Color.AMARILLO, Material.ALGODON);
		remeraRoja = new Prenda(TipoPrenda.REMERA, Color.ROJO, Color.AMARILLO, Material.ALGODON);
		
		ArrayList<Prenda> prendasSuperiores = new ArrayList<Prenda>();
		prendasSuperiores.add(remeraRoja);
		prendasSuperiores.add(remeraAzul);
		
		ArrayList<Prenda> prendasInferiores = new ArrayList<Prenda>();
		prendasInferiores.add(pantalonAzul);
		
		ArrayList<Prenda> calzados = new ArrayList<Prenda>();
		calzados.add(zapatillasNegras);
		
		ArrayList<Prenda> accesorios = new ArrayList<Prenda>();
		accesorios.add(cinturonNaranja);
		
		ArrayList<Prenda> atuendo1 = new ArrayList<Prenda>();
		atuendo1.add(remeraRoja);
		atuendo1.add(pantalonAzul);
		atuendo1.add(zapatillasNegras);
		atuendo1.add(cinturonNaranja);

		atuendo = new Atuendo(atuendo1);
		
		guardarropas1 = new Guardarropa(prendasSuperiores, prendasInferiores, calzados, accesorios);
		
		julieta = new Usuario("giu", guardarropas1);
	}

	@Test
	public void agregadoDeGuardarropas() {
		Assert.assertEquals(guardarropas1, julieta.guardarropas.get(0));
	}
	
	@Test
	public void laPrimerPrendaDelPrimerAtuendoEsUnaRemeraRoja() {
		Assert.assertEquals(atuendo.getPrendas().get(0), guardarropas1.generarRecomendaciones().get(0).getPrendas().get(0));
	}
	
	@Test
	public void laSegundaPrendaDelPrimerAtuendoEsUnPantalonAzul() {
		Assert.assertEquals(atuendo.getPrendas().get(1), guardarropas1.generarRecomendaciones().get(0).getPrendas().get(1));
	}
	
	@Test
	public void dosRemerasSonDelMismoTipo() {
		Assert.assertTrue(atuendo.sonDeDistintoTipo(atuendo.getPrendas()));
	}
	
}
