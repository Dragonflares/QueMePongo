package TestDeDominio;

import org.junit.Before;
import org.junit.Test;

import Dominio.Categoria;
import Dominio.Color;
import Dominio.Material;
import Dominio.Prenda;
import Dominio.TipoPrenda;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

public class TestPrenda {
	

private TipoPrenda tipoPrenda;
private Prenda remera;
public 	List<Material> materialesNoCompatiblesRem = new ArrayList<>();
	
	@Before
	public void init() throws Exception {
		materialesNoCompatiblesRem.add(Material.CUERO);
		materialesNoCompatiblesRem.add(Material.GABARDINA);
		
		tipoPrenda = new TipoPrenda(Categoria.PARTE_SUPERIOR, materialesNoCompatiblesRem);
		remera = new Prenda(tipoPrenda, Color.ROJO, Color.AMARILLO, Material.ALGODON);
	}
	
	
	@Test
	public void queTipoDeCategoriaTiene() {
		Assert.assertEquals(Categoria.PARTE_SUPERIOR, remera.getCategoria());
	}
	
	@Test
	public void queMaterialTiene() {
		Assert.assertEquals(Categoria.PARTE_SUPERIOR, remera.getCategoria());
	}
	
	@Test(expected=Exception.class)
	public void crearPrendaConMaterialIncorrecto() throws Exception {
		Prenda remera = new Prenda(tipoPrenda, Color.ROJO, Color.AMARILLO, Material.CUERO);
	}
	
	@Test(expected=Exception.class)
	public void crearPrendaConColoresIncorrecto() throws Exception {
		Prenda remera = new Prenda(tipoPrenda, Color.ROJO, Color.ROJO, Material.ALGODON); 
	}
}
