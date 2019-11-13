package TestDeDominio;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Evento;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;

public class TestGuardarropa {

	private Usuario usuario;
	private Guardarropa guardarropa1;
	private Prenda remeraMangaCorta;
	private Prenda camperaFinita;
	private Prenda pantalonLargo;
	private Prenda pantalonCorto;
	private Evento eventoCasual;
	private LocalDateTime date;
	private Prenda zapatilla;
	
	@Before
	public void init() throws Exception{

		usuario = mock(Usuario.class);
		
		date = LocalDateTime.now().minusDays(2);

		when(usuario.getOffsetSuperior()).thenReturn(0);
		when(usuario.getOffsetInferior()).thenReturn(0);
		
		guardarropa1 = new Guardarropa(usuario, Estilo.CASUAL);
		
		eventoCasual = mock(Evento.class);
		when(eventoCasual.getFecha()).thenReturn(date);
		
		remeraMangaCorta = mock(Prenda.class);
		camperaFinita = mock(Prenda.class);
		pantalonLargo = mock(Prenda.class);
		pantalonCorto = mock(Prenda.class);
		zapatilla = mock(Prenda.class);
		
		when(remeraMangaCorta.getNivelAbrigo()).thenReturn(3);
		when(camperaFinita.getNivelAbrigo()).thenReturn(4);
		when(pantalonLargo.getNivelAbrigo()).thenReturn(1);
		when(pantalonCorto.getNivelAbrigo()).thenReturn(0);
		when(zapatilla.getNivelAbrigo()).thenReturn(0);
		
		
		when(remeraMangaCorta.getCategoria()).thenReturn(Categoria.PARTE_SUPERIOR);
		when(camperaFinita.getCategoria()).thenReturn(Categoria.PARTE_SUPERIOR);
		when(pantalonLargo.getCategoria()).thenReturn(Categoria.PARTE_INFERIOR);
		when(pantalonCorto.getCategoria()).thenReturn(Categoria.PARTE_INFERIOR);
		when(zapatilla.getCategoria()).thenReturn(Categoria.CALZADO);

		usuario.agregarGuardarropa(guardarropa1);
		usuario.agregarPrendaAGuardarropa(guardarropa1, camperaFinita);
		usuario.agregarPrendaAGuardarropa(guardarropa1, remeraMangaCorta);
		usuario.agregarPrendaAGuardarropa(guardarropa1, pantalonCorto);
		usuario.agregarPrendaAGuardarropa(guardarropa1, pantalonLargo);
		usuario.agregarPrendaAGuardarropa(guardarropa1, zapatilla);
		
		guardarropa1.permitirAccesoaUsuario(usuario);
	}
	
	@Test
	public void puedeAgregarAccesoAUsuarios() {
		assertTrue(guardarropa1.getUsuariosConAcceso().contains(usuario));
	}
	@Test
	public void puedegenerarRecomendacion() throws Exception {
		assertTrue(guardarropa1.generarRecomendacion(eventoCasual, usuario).getPrendas().contains(zapatilla));
	}
}

