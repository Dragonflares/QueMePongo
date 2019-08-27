package TestDeDominio;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;

public class TestGuardarropa {

	private Usuario usuario1;
	private Usuario usuario2;
	private Guardarropa guardarropa1;
	private Guardarropa guardarropa2;
	private Estilo estiloFormal;
	private Atuendo atuendoMedio;
	private Atuendo atuendoPocoAbrigado;
	private Prenda remeraMangaCorta;
	private Prenda camperaFinita;
	private Prenda pantalonLargo;
	private Prenda pantalonCorto;
	
	@Before
	public void init() throws Exception{
		
		usuario1 = new Usuario("usuario1", "usuario1", "28937029487");
		
		atuendoMedio = mock(Atuendo.class);
		atuendoPocoAbrigado = mock(Atuendo.class);
		
		remeraMangaCorta = mock(Prenda.class);
		camperaFinita = mock(Prenda.class);
		pantalonLargo = mock(Prenda.class);
		pantalonCorto = mock(Prenda.class);
		
		guardarropa1 = mock(Guardarropa.class);
		guardarropa2 = mock(Guardarropa.class);
		
		when(remeraMangaCorta.getNivelAbrigo()).thenReturn(3);
		when(camperaFinita.getNivelAbrigo()).thenReturn(4);
		when(pantalonLargo.getNivelAbrigo()).thenReturn(1);
		when(pantalonCorto.getNivelAbrigo()).thenReturn(0);
		
		when(remeraMangaCorta.getCategoria()).thenReturn(Categoria.PARTE_SUPERIOR);
		when(camperaFinita.getCategoria()).thenReturn(Categoria.PARTE_SUPERIOR);
		when(pantalonLargo.getCategoria()).thenReturn(Categoria.PARTE_INFERIOR);
		when(pantalonCorto.getCategoria()).thenReturn(Categoria.PARTE_INFERIOR);
		
		atuendoMedio.agregar(remeraMangaCorta);
		atuendoMedio.agregar(camperaFinita);
		atuendoMedio.agregar(pantalonLargo);
		
		atuendoPocoAbrigado.agregar(remeraMangaCorta);
		atuendoPocoAbrigado.agregar(pantalonCorto);
		
		usuario1.agregarGuardarropa(guardarropa1);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, camperaFinita);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, remeraMangaCorta);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, pantalonCorto);
		usuario1.agregarPrendaAGuardarropa(guardarropa1, pantalonLargo);
		
		
	}
	
	@Test
	public void puedeAgregarARechazados() {
		usuario1.agregarARechazados(atuendoMedio);
		assertNotEquals(0, usuario1.getAtuendosRechazados().size());
	}
}

