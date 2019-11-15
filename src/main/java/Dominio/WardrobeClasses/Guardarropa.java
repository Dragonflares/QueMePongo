package Dominio.WardrobeClasses;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Evento;
import Dominio.UserClasses.Property;
import Dominio.UserClasses.Usuario;
import Dominio.WeatherAPIClasses.GestorClimatico;
import db.EntidadPersistente;

@Entity
@Table(name = "guardarropa")
public class Guardarropa extends EntidadPersistente{
	
	@Column(name = "nombre")
	private String nombre;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Usuario> usuariosConAcceso = new ArrayList<Usuario>();
	
	@Enumerated(EnumType.ORDINAL)
	private Estilo estilo;
	
	@OneToMany(mappedBy = "id", cascade = {CascadeType.ALL})
	private List<Prenda> prendasDisponibles = new ArrayList<Prenda>();
	

	public Guardarropa(Usuario creador, Estilo estilo) {
		this.usuariosConAcceso.add(creador);
		this.estilo = estilo;
	}

	public int getCantidadDePrendas()
	{
		return prendasDisponibles.size();
	}

	public List<Usuario> getUsuariosConAcceso(){
		return this.usuariosConAcceso;
	}

	public void permitirAccesoaUsuario (Usuario usuario) {
		usuariosConAcceso.add(usuario);
	}

	public void agregarPrenda(Prenda prenda) {
		this.prendasDisponibles.add(prenda);
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
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public Estilo getEstilo()
	{
		return this.estilo;
	}
	
	private double pedirTemperatura(LocalDateTime fecha, int hora) throws IOException {
		return GestorClimatico.getInstance().obtenerTemperatura(fecha, hora);
	}

	public Atuendo generarRecomendacion(Evento evento,
			Usuario creador) throws Exception {
		Atuendo atuendo = null;
		double temperatura = pedirTemperatura(evento.getFecha(), evento.getFecha().getHour());
		atuendo = Vestidor.getInstance().obtenerAtuendo(this, temperatura, 0, creador);
		if(atuendo.equals(null))
			atuendo = new Atuendo(generarAtuendoRandom(evento, temperatura, creador));
		return atuendo;
	}

	private ArrayList<Prenda> generarAtuendoRandom(Evento evento,
			double temperatura, Usuario creador) throws Exception {
		ArrayList<Prenda> prendasDeAtuendo = new ArrayList<Prenda>();
		List<Prenda> prendasSuperiores = 
				obtenerParteSuperior(evento, temperatura, 0, creador);
		List<Prenda> prendaInferior = obtenerParteInferior(evento, temperatura, creador, 0);
		List<Prenda> calzado = obtenerCalzado(evento, temperatura, creador , 0);
		if(prendasSuperiores.isEmpty() || prendaInferior.equals(null) || calzado.equals(null))
		{
			return null;
		}
		else {
			prendasDeAtuendo.addAll(prendasSuperiores);
			prendasDeAtuendo.addAll(prendaInferior);
			prendasDeAtuendo.addAll(calzado);
		}
		return prendasDeAtuendo;
	}

	private List<Prenda> obtenerParteSuperior(Evento evento,
			double temperatura, int intento, Usuario creador) throws Exception {
		List<Prenda> prendasSuperiores = new ArrayList<Prenda>();
		int calorActual;
		int capa = 0;
		do{
			prendasSuperiores.add(darPrendaSuperiorDeCapa(capa, prendasSuperiores));
			calorActual = creador.getOffsetSuperior() + (prendasSuperiores.stream()
					.mapToInt(p -> p.getTipoRopa().getNivelAbrigo()).sum());
			capa++;
		}
		while(calorActual < (Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase")) - temperatura - 3) && capa < 6);
		if(calorActual > (Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase"))
				- temperatura 
				+ Integer.parseInt(Property.getSpecifiedProperty("VariacionTemperatura"))))
		{
			if(intento ==  Integer.parseInt(Property.getSpecifiedProperty("CantIntentos"))) {
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

	private Prenda darPrendaSuperiorDeCapa(Integer capa,
			List<Prenda> prendasSuperiores ) throws Exception {
		Random rand = new Random();
		List<Prenda> prendasSuperioresPosibles = getPrendasSuperioresDisponibles()
				.stream()
				.filter(p -> p.getTipoRopa().esDeEsaCapa(capa))
				.collect(Collectors.toList());
		int limitSup = prendasSuperioresPosibles.size();
		if(limitSup != 0) {
			Prenda prendaSuperior = null;
			int intento = 0;
			while(intento != Integer.parseInt(Property.getSpecifiedProperty("CantIntentos")))
			{
				int indexSuperior = rand.nextInt(limitSup);
				final Prenda nuevaPrendaSuperior = 
						prendasSuperioresPosibles.get(indexSuperior);
				if(!prendasSuperiores.stream()
						.anyMatch(p -> p.getTipoRopa()
								.compararNombres(nuevaPrendaSuperior
										.getTipoRopa().getNombre()))) 
				{	
					prendaSuperior = nuevaPrendaSuperior;
					break;
				}
				else
					intento++;
			}
			return prendaSuperior;
		}
		else {
			throw new Exception("No hay suficientes prendas para generar el atuendo");
		}
	}

	private List<Prenda> obtenerParteInferior(Evento evento,
			double temperatura, Usuario creador, Integer intento) throws Exception {
		List<Prenda> prendasInferiores = new ArrayList<Prenda>();
		Integer calorActual;
		Integer capa = 1;
		do{
			prendasInferiores.add(obtenerPrendaInf(capa, prendasInferiores));
			calorActual = creador.getOffsetSuperior() + (prendasInferiores.stream()
					.mapToInt(p -> p.getTipoRopa().getNivelAbrigo()).sum());
			capa--;
		}
		while(calorActual < 
				(Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase"))
						- temperatura - Integer.parseInt(Property.getSpecifiedProperty("VariacionTemperatura")))
				&& capa > -1);
		if(calorActual > (Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase"))
				- temperatura 
				+ Integer.parseInt(Property.getSpecifiedProperty("VariacionTemperatura"))))
		{
			if(intento ==  Integer.parseInt(Property.getSpecifiedProperty("CantIntentos"))) {
				return null;
			}
			else {
				intento++;
				return prendasInferiores = 
						obtenerParteInferior(evento, temperatura, creador, intento);
			}
		}
		else {
			return prendasInferiores;
		}
	}

	private Prenda obtenerPrendaInf(Integer capa,
			List<Prenda> prendasInfActuales) throws Exception {
		Prenda prendaInferior = null;
		Random rand = new Random();
		List<Prenda> prendasInferioresPosibles = getPrendasInferioresDisponibles()
				.stream()
				.filter(p -> p.getTipoRopa().esDeEsaCapa(capa))
				.collect(Collectors.toList());
		int limitInf = prendasInferioresPosibles.size();
		if(limitInf > 0) {
			Integer intento = 0;
			while(intento != 
					Integer.parseInt(Property.getSpecifiedProperty("CantIntentos")))
			{
				int indexInferior = rand.nextInt(limitInf);
				Prenda nuevaPrendaInferior = prendasInferioresPosibles.get(indexInferior);
				if(!prendasInfActuales.stream()
						.anyMatch(p -> p.getTipoRopa()
								.compararNombres(nuevaPrendaInferior
										.getTipoRopa().getNombre()))) 
				{	
					prendaInferior = nuevaPrendaInferior;
					break;
				}
				else
					intento++;
			}
		}
		else {
			throw new Exception("No hay suficientes prendas inferiores "
					+ "para crear el atuendo");
		}
		return prendaInferior;
	}

	private List<Prenda> obtenerCalzado(Evento evento,
			double temperatura, Usuario creador, Integer intento) throws Exception {
		List<Prenda> calzado = new ArrayList<Prenda>();
		Integer calorActual;
		Integer capa = 1;
		do{
			calzado.add(obtenerParteDeCalzado(capa, calzado));
			calorActual = creador.getOffsetSuperior() + (calzado.stream()
					.mapToInt(p -> p.getTipoRopa().getNivelAbrigo()).sum());
			capa--;
		}
		while(calorActual < 
				(Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase"))
						- temperatura - Integer.parseInt(Property.getSpecifiedProperty("VariacionTemperatura")))
				&& capa > -1);
		if(calorActual > (Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase"))
				- temperatura 
				+ Integer.parseInt(Property.getSpecifiedProperty("VariacionTemperatura"))))
		{
			if(intento ==  Integer.parseInt(Property.getSpecifiedProperty("CantIntentos"))) {
				return null;
			}
			else {
				intento++;
				return calzado = 
						obtenerCalzado(evento, temperatura, creador, intento);
			}
		}
		else {
			return calzado;
		}
	}

	private Prenda obtenerParteDeCalzado(Integer capa,
			List<Prenda> prendasInfActuales) throws Exception {
		Prenda prendaDeCalzado = null;
		Random rand = new Random();
		List<Prenda> calzadosDisponibles = getCalzadosDisponibles()
				.stream()
				.filter(p -> p.getTipoRopa().esDeEsaCapa(capa))
				.collect(Collectors.toList());
		int limitInf = calzadosDisponibles.size();
		if(limitInf > 0) {
			Integer intento = 0;
			while(intento != 
					Integer.parseInt(Property.getSpecifiedProperty("CantIntentos")))
			{
				int indexInferior = rand.nextInt(limitInf);
				Prenda nuevoCalzado = calzadosDisponibles.get(indexInferior);
				if(!prendasInfActuales.stream()
						.anyMatch(p -> p.getTipoRopa()
								.compararNombres(nuevoCalzado
										.getTipoRopa().getNombre()))) 
				{	
					prendaDeCalzado = nuevoCalzado;
					break;
				}
				else
					intento++;
			}
		}
		else {
			throw new Exception("No hay suficientes prendas inferiores "
					+ "para crear el atuendo");
		}
		return prendaDeCalzado;
	}

	public void cambiarEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
	public boolean tienePrenda(Prenda prenda) {
		if(this.prendasDisponibles == null)
			return false;
		else
		{
			return this.prendasDisponibles.contains(prenda);
		}
						
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//	public List<Prenda> obtenerAccesorios(Evento evento) throws IOException{
	//		List<Prenda> accesorios = new ArrayList<Prenda>();
	//		int limitAccesorios = getAccesoriosDisponibles().size();
	//		String condicionClimatica = GestorClimatico.getInstance().darCondicionClimatica(evento.getFecha()
	//				.get(Calendar.DAY_OF_MONTH) , evento.getFecha().get(Calendar.HOUR_OF_DAY));
	//		switch(condicionClimatica) {
	//		case "rain":{
	//			//TODO verificar que tenga un Paraguas
	//			break;	
	//		}
	//		case "clear-day":{
	//			//TODO verificar que tenga lentes de sol
	//			break;
	//		}
	//		}
	//		return accesorios;
	//	}
}


