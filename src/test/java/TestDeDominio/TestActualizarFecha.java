package TestDeDominio;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import Dominio.ClothingClasses.Estilo;
import Dominio.EventClasses.Evento;
import Dominio.UserClasses.Usuario;
import junit.framework.Assert;

public class TestActualizarFecha {
	private Evento eventoOcurrioYesFrecuente1;
	private Evento eventoOcurrioYesFrecuente7;
	private Evento eventoOcurrioYesFrecuente30;
	private Evento eventoOcurrioYesFrecuente365;
	
	@Before
	public void init() {
		Calendar ayer = Calendar.getInstance();
		ayer.add(Calendar.DATE, -1); // le resto un dia a la fecha actual 
		eventoOcurrioYesFrecuente1 = new Evento(ayer,"TuVieja",Estilo.FORMAL, 1);
		
		Calendar unaSemana = Calendar.getInstance();
		unaSemana.add(Calendar.DATE, -7); // le resto una semana a la fecha actual 
		eventoOcurrioYesFrecuente7 = new Evento(unaSemana,"TuVieja",Estilo.FORMAL, 7);
		
		Calendar unMes = Calendar.getInstance();
		unMes.add(Calendar.DATE, -30); // le resto un mes a la fecha actual 
		eventoOcurrioYesFrecuente30 = new Evento(unMes,"TuVieja",Estilo.FORMAL, 30);
		
		Calendar unAnio = Calendar.getInstance();
		unAnio.add(Calendar.DATE, -365); // le resto un año a la fecha actual 
		eventoOcurrioYesFrecuente365 = new Evento(unAnio,"TuVieja",Estilo.FORMAL, 365);

	}
	
	@Test
	public void actualizarFechaEventoDiario() {
		eventoOcurrioYesFrecuente1.actualizarFecha();
		Assert.assertEquals(eventoOcurrioYesFrecuente1.getFecha(), Calendar.getInstance());
	}
	
	@Test
	public void actualizarFechaEventoSemanal() {
		eventoOcurrioYesFrecuente7.actualizarFecha();
		Assert.assertEquals(eventoOcurrioYesFrecuente7.getFecha(), Calendar.getInstance());
	}
	
	@Test
	public void actualizarFechaEventoMensual() {
		eventoOcurrioYesFrecuente30.actualizarFecha();
		Assert.assertEquals(eventoOcurrioYesFrecuente30.getFecha(), Calendar.getInstance());
	}
	
	@Test
	public void actualizarFechaEventoAnual() {
		eventoOcurrioYesFrecuente365.actualizarFecha();
		Assert.assertEquals(eventoOcurrioYesFrecuente365.getFecha(), Calendar.getInstance());
	}
	
}