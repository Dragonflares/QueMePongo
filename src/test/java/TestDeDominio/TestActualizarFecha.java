package TestDeDominio;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import Dominio.ClothingClasses.Estilo;
import Dominio.EventClasses.Anualmente;
import Dominio.EventClasses.Diario;
import Dominio.EventClasses.DiarioPersonalizado;
import Dominio.EventClasses.Evento;
import Dominio.EventClasses.Mensualmente;
import Dominio.EventClasses.Semanalmente;
import junit.framework.Assert;

public class TestActualizarFecha {
	private Evento eventoOcurrioYesFrecuente1;
	private Evento eventoOcurrioYesFrecuente1Personalizado;
	private Evento eventoOcurrioYesFrecuente7;
	private Evento eventoOcurrioYesFrecuente30;
	private Evento eventoOcurrioYesFrecuente365;
	
	@Before
	public void init() {
		Calendar ayer = Calendar.getInstance();
		ayer.add(Calendar.DATE, -1); // le resto un dia a la fecha actual 
		
		eventoOcurrioYesFrecuente1 = new Evento(ayer,"TuVieja",Estilo.FORMAL, new Diario());
		
		ArrayList<Integer> diasQueRepite = new ArrayList<Integer>();
		diasQueRepite.add(Calendar.MONDAY);
		diasQueRepite.add(Calendar.WEDNESDAY);
		
		eventoOcurrioYesFrecuente1Personalizado = new Evento(ayer,"TuVieja",Estilo.FORMAL, new DiarioPersonalizado(diasQueRepite));
		
		Calendar unaSemana = Calendar.getInstance();
		unaSemana.add(Calendar.DATE, -7); // le resto una semana a la fecha actual 
		eventoOcurrioYesFrecuente7 = new Evento(unaSemana,"TuVieja",Estilo.FORMAL, new Semanalmente());
		
		Calendar unMes = Calendar.getInstance();
		unMes.add(Calendar.DATE, -31); // le resto un mes a la fecha actual 
		eventoOcurrioYesFrecuente30 = new Evento(unMes,"TuVieja",Estilo.FORMAL, new Mensualmente());
		
		Calendar unAnio = Calendar.getInstance();
		unAnio.add(Calendar.DATE, -365); // le resto un año a la fecha actual 
		eventoOcurrioYesFrecuente365 = new Evento(unAnio,"TuVieja",Estilo.FORMAL, new Anualmente());

	}
	
	@Test
	public void actualizarFechaEventoDiario() {
		eventoOcurrioYesFrecuente1.actualizarFecha();
		Assert.assertEquals(eventoOcurrioYesFrecuente1.getFecha().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void actualizarFechaEventoDiarioPersonalizado() {
		eventoOcurrioYesFrecuente1Personalizado.actualizarFecha();
	
		
		Assert.assertEquals(eventoOcurrioYesFrecuente1Personalizado.getFecha().get(Calendar.DAY_OF_WEEK), Calendar.MONDAY);
	}
	
	@Test
	public void actualizarFechaEventoSemanal() {
		eventoOcurrioYesFrecuente7.actualizarFecha();
		Assert.assertEquals(eventoOcurrioYesFrecuente7.getFecha().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void actualizarFechaEventoMensual() {
		eventoOcurrioYesFrecuente30.actualizarFecha();
		Assert.assertEquals(eventoOcurrioYesFrecuente30.getFecha().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void actualizarFechaEventoAnual() {
		eventoOcurrioYesFrecuente365.actualizarFecha();
		Assert.assertEquals(eventoOcurrioYesFrecuente365.getFecha().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	}
	
}