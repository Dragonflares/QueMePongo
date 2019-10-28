package TestDeDominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;

public class TestUsuarios {

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
	private Calendar date;
	
	@Before
	public void init() {
		atuendoMedio = new Atuendo();
		atuendoPocoAbrigado = new Atuendo();
		
		usuario1 = new Usuario("Dragonflares", "lorddragonflares@gmail.com", "+54 9 1133606009");
		usuario1.modificarOffSetSuperior(1);
		date = Calendar.getInstance();
		date.add(Calendar.DATE, -2);
		
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
		
		
	}
	
	@Test
	public void tieneMasCalorArriba() {
		assertTrue(0 < usuario1.getOffsetSuperior());
	}
	
	@Test
	public void noLePareceMalEnLasPiernas() {
		assertTrue(0 == usuario1.getOffsetInferior());
	}
	
	@Test
	public void noTieneEventosSinNotificar() {
		assertFalse(usuario1.tieneEventoSinNotificar());
	}
	
	@Test
	public void noTieneEventosOcurridos() {
		assertFalse(usuario1.tieneEventosOcurridos(date));
	}
	
	@Test
	public void noTieneSugerenciasPorCalificar() {
		assertTrue(usuario1.getSugerenciasQueFaltanCalificar().isEmpty());
	}
	
	@Test
	public void elCelularEstaBien() {
		assertEquals("+54 9 1133606009", usuario1.getNumeroCelular());
	}
}
