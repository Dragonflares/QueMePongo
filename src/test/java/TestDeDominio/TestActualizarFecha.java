package TestDeDominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Anualmente;
import Dominio.EventClasses.Diario;
import Dominio.EventClasses.DiarioPersonalizado;
import Dominio.EventClasses.Evento;
import Dominio.EventClasses.Mensualmente;
import Dominio.EventClasses.Semanalmente;

public class TestActualizarFecha {
	private Evento eventoOcurrioYesFrecuente1;
	private Evento eventoOcurrioYesFrecuente1Personalizado;
	private Evento eventoOcurrioYesFrecuente7;
	private Evento eventoOcurrioYesFrecuente30;
	private Evento eventoOcurrioYesFrecuente365;
	
	@Before
	public void init() {
		LocalDateTime ayer = LocalDateTime.now().minusDays(1);
		
		eventoOcurrioYesFrecuente1 = new Evento("Ir a trabajar", ayer, "dire", Estilo.FORMAL, new Diario(), null);
		
		ArrayList<DayOfWeek> diasQueRepite = new ArrayList<DayOfWeek>();
		diasQueRepite.add(DayOfWeek.MONDAY);
		diasQueRepite.add(DayOfWeek.WEDNESDAY);
		
		eventoOcurrioYesFrecuente1Personalizado = new Evento("Ir a la facultad", ayer, "dire", Estilo.FORMAL, new DiarioPersonalizado(diasQueRepite), null);
		
		LocalDateTime unaSemana = LocalDateTime.now().minusDays(7);
		eventoOcurrioYesFrecuente7 = new Evento("Ir a bailar", unaSemana, "dire", Estilo.FORMAL, new Semanalmente(), null);
		
		LocalDateTime unMes = LocalDateTime.now().minusDays(31);
		eventoOcurrioYesFrecuente30 = new Evento("Salir con amigos", unMes, "dire", Estilo.FORMAL, new Mensualmente(), null);
		
		LocalDateTime unAnio = LocalDateTime.now().minusYears(1);
		eventoOcurrioYesFrecuente365 = new Evento("Cumpleaños", unAnio, "dire", Estilo.FORMAL, new Anualmente(), null);

	}
	
	@Test
	public void ocurre() {
		LocalDateTime ayerConHoraDistinta = LocalDateTime.now().minusDays(1).minusHours(1);
		assertTrue(eventoOcurrioYesFrecuente1.ocurre(ayerConHoraDistinta));
	}
	
	@Test
	public void actualizarFechaEventoDiario() {
		eventoOcurrioYesFrecuente1.actualizarFecha();
		assertEquals(eventoOcurrioYesFrecuente1.getFecha().getDayOfMonth(), LocalDate.now().getDayOfMonth());
	}
	
	@Test
	public void actualizarFechaEventoDiarioPersonalizado() {
		eventoOcurrioYesFrecuente1Personalizado.actualizarFecha();
	
		
		assertEquals(eventoOcurrioYesFrecuente1Personalizado.getFecha().getDayOfWeek(), DayOfWeek.WEDNESDAY);
	}
	
	@Test
	public void actualizarFechaEventoSemanal() {
		eventoOcurrioYesFrecuente7.actualizarFecha();
		assertEquals(eventoOcurrioYesFrecuente7.getFecha().getDayOfMonth(), LocalDate.now().getDayOfMonth());
	}
	
	@Test
	public void actualizarFechaEventoMensual() {
		eventoOcurrioYesFrecuente30.actualizarFecha();
		assertEquals(eventoOcurrioYesFrecuente30.getFecha().getDayOfMonth(), LocalDate.now().getDayOfMonth());
	}
	
	@Test
	public void actualizarFechaEventoAnual() {
		eventoOcurrioYesFrecuente365.actualizarFecha();
		assertEquals(eventoOcurrioYesFrecuente365.getFecha().getDayOfMonth(), LocalDate.now().getDayOfMonth());
	}
	
}