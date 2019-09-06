package TestDeDominio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import Dominio.EventClasses.Evento;
import Dominio.NotificationAPIClasses.NotificacionDataObject;
import Dominio.NotificationAPIClasses.Notificador;
import Dominio.UserClasses.Usuario;

public class TestNotificador {
	private Usuario usuario;
	private NotificacionDataObject notificacion;
	private Evento evento;
	
	@Before
	public void init() throws FileNotFoundException {
		usuario = new Usuario("nombre", "jm98olivera@gmail.com", "+541153786146");
		
		evento = mock(Evento.class);
		when(evento.getNombre()).thenReturn("Ir a trabajar");
		
		notificacion = new NotificacionDataObject();
		
		notificacion.setEmisorMail("melisarodrig2@gmail.com");
		notificacion.setContrasenia("contra");
		notificacion.setDestinatarioMail(usuario.getMail());
		notificacion.setSubject("Ya se tiene lista una sugerencia!");
		notificacion.setMensaje("Se ha creado la sugerencia para el evento" + evento.getNombre() +". Para verlo tiene que abrir la aplicación.");
		notificacion.setEmisorNumero("+13343262392");
		notificacion.setDestinatarioNumero(usuario.getNumeroCelular());
		notificacion.setEvento(evento);
	}
	
	@Test
	public void notificarUsuario() {
		Notificador.getInstance().notificar(notificacion);
	}
	
}
