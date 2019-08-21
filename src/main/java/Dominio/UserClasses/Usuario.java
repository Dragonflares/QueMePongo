package Dominio.UserClasses;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;
import Dominio.ClothingClasses.Prenda;
import Dominio.WardrobeClasses.Guardarropa;

public class Usuario {
	private String username;
	private TipoDeUsuario tipoDeCuenta;
	public List<Guardarropa> guardarropas;
	private List<Atuendo> atuendosRechazados;
	private Atuendo ultimoAtuendo;
	private List<Evento> eventos;
	private int offset;
	private Atuendo sugerencia; //TODO ver esto porque tiene mucha pinta de que est� mal, solo est� de forma temporal.
	// Lo puse porque en el CRON CrearSugerenciaTask necesitamos crear las sugerencias de forma masiva a las 3 am 
	// de los eventos que estan pr�ximos. Entonces puse este atributo porque en algun lado hay que guardar la sugerencia, 
	// pero puede que cuando se haga lo de la notificaci�n se cambie de lugar. VER cuando ya tengamos lo de enviar notificaci�n.
	
	
	public Usuario(String username){
		this.username = username;
		this.guardarropas = new ArrayList<Guardarropa>();
		this.eventos = new ArrayList<Evento>();
	}

	public Usuario(String username, Guardarropa guardarropa) {
		this.username = username;
		this.guardarropas = new ArrayList<Guardarropa>();
		this.inicializarGuardarropa(guardarropa);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private void inicializarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.add(guardarropa);
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.add(guardarropa);
	}

	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda) throws Exception {

		if(!this.guardarropas.stream().anyMatch(p -> p.getPrendasDisponibles().contains(prenda))) {
			tipoDeCuenta.agregarPrendaAGuardarropa(guardarropa, prenda);
		} else {
			throw new Exception("Ya tenes la prenda en otro guardarropa");
		}

	}
	
	public void agregarARechazados(Atuendo atuendo)
	{
		this.atuendosRechazados.add(atuendo);
	}
	
	public void eliminarDeRechazados(Atuendo atuendo)
	{
		this.atuendosRechazados.remove(atuendo);
	}

	public Atuendo pedirRecomendacion(Evento evento) throws Exception{
		List<Guardarropa> guardarropasConEstilo = 
				this.guardarropas.stream().filter(g -> g.getEstilo() == evento.getEstilo()).collect(Collectors.toList());
		
		Atuendo atuendoFinal = null;
		Random rand = new Random();
		int cantGuardarropas = guardarropasConEstilo.size();
		while(atuendoFinal != null)
		{
			Guardarropa guardarropa =  guardarropasConEstilo.get(rand.nextInt(cantGuardarropas));
			atuendoFinal = guardarropa.generarRecomendacion(evento);
		}
		this.ultimoAtuendo = atuendoFinal;
		return atuendoFinal;
	}

	public int getOffset() {
		return this.offset;
	}

	public void crearEvento(Calendar fecha, String direccion, Estilo estilo) {
		this.eventos.add(new Evento(fecha, direccion, estilo, this));
	}
	
	public void crearNuevoGuardarropas(Estilo estilo) {
		this.guardarropas.add(new Guardarropa(this, estilo));
	}
	
	public List<Evento> getEventos(){
		return this.eventos;
	}
	
	//TODO sacar? cuando se haya resuelto lo del atributo de suguerencia
	public void agregarSugerencia(Atuendo sugerencia)
	{
		this.sugerencia = sugerencia;
	}
	
	public Atuendo getSugerencia()
	{
		return this.sugerencia;
	}
}
