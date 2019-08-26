package TestDeDominio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Prenda;
import Dominio.UserClasses.Usuario;

public class TestAtuendo {
	private Atuendo atuendoMedio;
	private Atuendo atuendoPocoAbrigado;
	private Usuario usuario;
	private Prenda remeraMangaCorta;
	private Prenda camperaFinita;
	private Prenda pantalonLargo;
	private Prenda pantalonCorto;
	
	@Before
	public void init() throws Exception 
	{
		atuendoMedio = new Atuendo();
		atuendoPocoAbrigado = new Atuendo();
		
		usuario = mock(Usuario.class);
		
		when(usuario.getOffsetSuperior()).thenReturn(0);
		when(usuario.getOffsetInferior()).thenReturn(0);
		
		remeraMangaCorta = mock(Prenda.class);
		camperaFinita = mock(Prenda.class);
		pantalonLargo = mock(Prenda.class);
		pantalonCorto = mock(Prenda.class);
		
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
	public void atuendoMedioAbrigaLoNecesario() 
	{
		Assert.assertTrue(atuendoMedio.abrigaLoNecesario(18, usuario));
	}
	
	@Test
	public void atuendoPocoAbrigaLoNecesario() 
	{
		Assert.assertTrue(atuendoPocoAbrigado.abrigaLoNecesario(23, usuario));
	}
	
	@Test
	public void atuendoMedioNoAbrigaLoNecesario() 
	{
		Assert.assertFalse(atuendoMedio.abrigaLoNecesario(23, usuario));
	}
	
	@Test
	public void atuendoPocoNoAbrigaLoNecesario() 
	{
		Assert.assertFalse(atuendoPocoAbrigado.abrigaLoNecesario(18, usuario));
	}
}
