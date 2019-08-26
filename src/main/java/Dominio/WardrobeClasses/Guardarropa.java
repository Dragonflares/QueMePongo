package Dominio.WardrobeClasses;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Evento;
import Dominio.UserClasses.Usuario;
import Dominio.WeatherAPIClasses.GestorClimatico;

public class Guardarropa {

	private List<Usuario> usuariosConAcceso = new ArrayList<Usuario>();
	private Estilo estilo;
	private List<Prenda> prendasDisponibles = new ArrayList<Prenda>();

	public Guardarropa(Usuario creador, Estilo estilo) {
		this.usuariosConAcceso.add(creador);
		this.estilo = estilo;
		this.prendasDisponibles = null;
	}

	public int cantidadDePrendasDisponibles()
	{
		return prendasDisponibles.size();
	}

	public void permitirAccesoaUsuario (Usuario usuario) {
		usuariosConAcceso.add(usuario);
	}

	public void agregarPrenda(Prenda prenda) {

		prendasDisponibles.add(prenda);
	}

	public List<Prenda> getPrendasDisponibles() {
		return this.prendasDisponibles;
	}
	public List<Prenda> getPrendasSuperioresDisponibles(){
		return  prendasDisponibles.stream().filter(p -> p.getCategoria() == Categoria.PARTE_SUPERIOR).collect(Collectors.toList());
	}
	public List<Prenda> getPrendasInferioresDisponibles(){
		return  prendasDisponibles.stream().filter(p -> p.getCategoria() == Categoria.PARTE_INFERIOR).collect(Collectors.toList());
	}
	public List<Prenda> getAccesoriosDisponibles(){
		return  prendasDisponibles.stream().filter(p -> p.getCategoria() == Categoria.ACCESORIOS).collect(Collectors.toList());
	}
	public List<Prenda> getCalzadosDisponibles(){
		return  prendasDisponibles.stream().filter(p -> p.getCategoria() == Categoria.CALZADO).collect(Collectors.toList());
	}
	public Estilo getEstilo()
	{
		return this.estilo;
	}

	private double pedirTemperatura(Calendar fecha, int hora) throws IOException {
		return GestorClimatico.getInstance().obtenerTemperatura(fecha, hora);
	}
	public Atuendo generarRecomendacion(Evento evento, Usuario creador) throws Exception
	{
		Atuendo atuendo = null;
		ArrayList<Prenda> prendasDeAtuendo = new ArrayList<Prenda>();
		double temperatura = pedirTemperatura(evento.getFecha(), evento.getFecha().get(Calendar.HOUR_OF_DAY));
		if(temperatura >= 27 - creador.getOffsetSuperior())
		{
			atuendo = this.estilo.generarAtuendoVerano(this);
		}
		else
		{
			List<Prenda> prendasSuperiores = obtenerParteSuperior(evento, temperatura, 0, creador);
			Prenda prendaInferior = obtenerParteInferior(evento, temperatura, creador);
			Prenda calzado = obtenerCalzado(evento, temperatura);
			if(prendasSuperiores.isEmpty() || prendaInferior.equals(null) || calzado.equals(null))
			{
				atuendo = null;
			}
			else {
				prendasDeAtuendo.addAll(prendasSuperiores);
				prendasDeAtuendo.add(prendaInferior);
				prendasDeAtuendo.add(calzado);
			}
		}
		atuendo = new Atuendo(prendasDeAtuendo);
		return atuendo;
	}

	public List<Prenda> obtenerParteSuperior(Evento evento, double temperatura, int intento, Usuario creador) throws Exception{
		List<Prenda> prendasSuperiores = new ArrayList<Prenda>();
		Random rand = new Random();
		int calorActual;
		int a = 0;
		do{
			final int b = a;

			List<Prenda> prendasSuperioresPosibles = getPrendasSuperioresDisponibles()
					.stream()
					.filter(p -> p.getTipoRopa().esDeEsaCapa(b))
					.collect(Collectors.toList());
			int limitSup = prendasSuperioresPosibles.size();
			if(limitSup != 0) {
				int indexSuperior = rand.nextInt(limitSup);
				Prenda prendaSuperior = prendasSuperioresPosibles.get(indexSuperior);
				prendasSuperiores.add(prendaSuperior);
				calorActual = creador.getOffsetSuperior() + (prendasSuperiores.stream()
						.mapToInt(p -> p.getTipoRopa().getNivelAbrigo()).sum());
				a++;
			}
			else {
				throw new Exception("No hay suficientes prendas para generar el atuendo");
			}
		}
		while(calorActual < (27 - temperatura - 3) && a < 6);
		if(calorActual > (27 - temperatura + 3))
		{
			if(intento == 10) {
				return null;
			}
			else {
				intento++;
				return prendasSuperiores = obtenerParteSuperior(evento, temperatura, intento, creador);
			}
		}
		else {
			return prendasSuperiores;
		}
	}
	public Prenda obtenerParteInferior(Evento evento, double temperatura, Usuario creador) throws Exception {
		Prenda prendaInferior;

		if(temperatura > (18 - creador.getOffsetInferior())) {
			prendaInferior = obtenerPrendaInf(0);
		}
		else {
			prendaInferior = obtenerPrendaInf(1);
		}
		return prendaInferior;
	}
	public Prenda obtenerPrendaInf(int nivelAbrigo) throws Exception {
		Prenda prendaInferior;
		Random rand = new Random();
		List<Prenda> prendasInferioresPosibles = getPrendasSuperioresDisponibles()
				.stream()
				.filter(p -> p.getTipoRopa().getNivelAbrigo() == nivelAbrigo)
				.collect(Collectors.toList());
		int limitInf = prendasInferioresPosibles.size();
		if(limitInf > 0) {
			int indexInferior = rand.nextInt(limitInf);
			prendaInferior = prendasInferioresPosibles.get(indexInferior);
		}
		else {
			throw new Exception("No hay suficientes prendas inferiores "
					+ "para crear el atuendo");
		}
		return prendaInferior;
	}
	public Prenda obtenerCalzado(Evento evento, double temperatura) throws Exception {
		Prenda calzado;
		Random rand = new Random();
		int limitCalzados = getCalzadosDisponibles().size();
		if(limitCalzados != 0) {
			int indexCalzados = rand.nextInt(limitCalzados);
			calzado = getCalzadosDisponibles().get(indexCalzados);
		}
		else {
			throw new Exception("No hay suficientes calzados "
					+ "para crear el atuendo");
		}
		return calzado;
	}
	public List<Prenda> obtenerAccesorios(Evento evento) throws IOException{
		List<Prenda> accesorios = new ArrayList<Prenda>();
		int limitAccesorios = getAccesoriosDisponibles().size();
		String condicionClimatica = GestorClimatico.getInstance().darCondicionClimatica(evento.getFecha()
				.get(Calendar.DAY_OF_MONTH) , evento.getFecha().get(Calendar.HOUR_OF_DAY));
		switch(condicionClimatica) {
		case "rain":{
			//TODO verificar que tenga un Paraguas
			break;	
		}
		case "clear-day":{
			//TODO verificar que tenga lentes de sol
			break;
		}
		}
		return accesorios;
	}
}


