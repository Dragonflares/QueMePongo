package Dominio.WardrobeClasses;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Estilo;
import Dominio.ClothingClasses.Prenda;
import Dominio.EventClasses.Evento;
import Dominio.UserClasses.Usuario;
import Dominio.WeatherAPIClasses.GestorClimatico;

public class Guardarropa {

	private List<Usuario> usuariosConAcceso = new ArrayList<Usuario>();
	private Estilo estilo;
	private List<Prenda> prendasDisponibles = new ArrayList<Prenda>();
	private List<Prenda> prendasNoDisponibles = new ArrayList<Prenda>();
	private GestorClimatico climaHelp;

	public Guardarropa(Usuario creador, Estilo estilo) {
		this.usuariosConAcceso.add(creador);
		this.estilo = estilo;
		this.prendasDisponibles = null;
		this.prendasNoDisponibles = null;
		this.climaHelp = new GestorClimatico();
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

	public double pedirTemperatura(int dia, int hora) throws IOException {
		return this.climaHelp.obtenerTemperatura( dia, hora);
	}
	public Atuendo generarRecomendacion(Evento evento, Usuario creador) throws Exception
	{
		Atuendo atuendo = null;

		double temperatura = pedirTemperatura(evento.getFecha().get(Calendar.DAY_OF_MONTH), evento.getFecha().get(Calendar.HOUR_OF_DAY));
		if(temperatura >= 27)
		{
			//TODO Hacer con lo del "PROTOTYPE" que ponga remeramangacorta/musculosa, pantalon corto.
		}
		else
		{
			List<Prenda> prendasSuperiores = obtenerParteSuperior(evento, temperatura, 0, creador);
			Prenda prendaInferior = obtenerParteInferior(evento, temperatura, creador);
			Prenda calzado = obtenerCalzado(evento, temperatura);
			List<Prenda> accesorios = obtenerAccesorios(evento);
			ArrayList<Prenda> prendasDeAtuendo = new ArrayList<Prenda>();
			if(prendasSuperiores.isEmpty() || prendaInferior.equals(null) || calzado.equals(null))
			{
				atuendo = null;
			}
			else {
				prendasDeAtuendo.addAll(prendasSuperiores);
				prendasDeAtuendo.add(prendaInferior);
				prendasDeAtuendo.addAll(accesorios);
				prendasDeAtuendo.add(calzado);
				atuendo = new Atuendo(prendasDeAtuendo);
			}
		}

		this.marcarNoDisponible(atuendo);
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
					.filter(p -> p.getTipoPrenda().getRopa().esDeEsaCapa(b))
					.collect(Collectors.toList());
			int limitSup = prendasSuperioresPosibles.size();
			if(limitSup != 0) {
				int indexSuperior = rand.nextInt(limitSup);
				Prenda prendaSuperior = prendasSuperioresPosibles.get(indexSuperior);
				prendasSuperiores.add(prendaSuperior);
				calorActual = creador.getOffset() + (prendasSuperiores.stream()
						.mapToInt(p -> p.getTipoPrenda().getRopa().abrigar()).sum());
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

		if(temperatura > (18 - creador.getOffset())) {
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
				.filter(p -> p.getTipoPrenda().getRopa().abrigar() == nivelAbrigo)
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
		String condicionClimatica = GestorClimatico.darCondicionClimatica(evento.getFecha()
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

	public void marcarNoDisponible(Atuendo atuendo)
	{
		atuendo.getPrendas().forEach(prenda -> {
			this.prendasDisponibles.remove(prenda);
			this.prendasNoDisponibles.add(prenda);
		});
	}

	public void devolverAtuendo(Atuendo atuendo)
	{
		atuendo.getPrendas().forEach(prenda -> {
			this.prendasDisponibles.add(prenda);
			this.prendasNoDisponibles.remove(prenda);
		});
	}
}


