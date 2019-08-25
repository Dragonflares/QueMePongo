package TestDeDominio;

import org.junit.Before;
import org.junit.Test;

import Dominio.NotificationAPIClasses.Notificador;
import Dominio.UserClasses.Usuario;

public class TestNotificador {
	private Usuario usuario;
	
	@Before
	public void init() {
		usuario = new Usuario("nombre", "jm98olivera@gmail.com", "+541153786146");
	}
	@Test
	public void notificarUsuario() {
		Notificador.getInstance().notificarSugerencia(usuario);
	}
	
}
