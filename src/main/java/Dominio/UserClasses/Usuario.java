package Dominio.UserClasses;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Evento;
import Dominio.WardrobeClasses.Guardarropa;

import db.EntidadPersistente;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente{
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tipo_id", referencedColumnName = "id", nullable = true)
	private TipoDeUsuario tipoDeCuenta;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Guardarropa> guardarropas = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Atuendo> atuendosRechazados = new ArrayList<>();
	
	@Transient
	private Atuendo ultimoAtuendo;
	
	@OneToMany( mappedBy = "creador", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	//@JoinColumn(name = "id_usuario")
	private List<Evento> eventos = new ArrayList<>();
	
	@Column(name = "offsetSuperior")
	private int offsetSuperior;
	
	@Column(name = "offsetInferior")
	private int offsetInferior;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "numeroCelular")
	private String numeroCelular;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private List<Atuendo> sugerenciasQueFaltanCalificar = new ArrayList<>();
	
	public Usuario() {}
	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
		this.guardarropas = new ArrayList<Guardarropa>();
		this.eventos = new ArrayList<Evento>();
		this.offsetInferior = 0;
		this.offsetSuperior = 0;
		this.setUltimoAtuendo(null);
		this.tipoDeCuenta = new Gratuito();
	}
	
	public Usuario(String username, String mail, String numeroCelular){
		this.username = username;
		this.guardarropas = new ArrayList<Guardarropa>();
		this.eventos = new ArrayList<Evento>();
		this.mail = mail;
		this.numeroCelular = numeroCelular;
		this.offsetInferior = 0;
		this.offsetSuperior = 0;
		this.setUltimoAtuendo(null);
		this.tipoDeCuenta = new Gratuito();
	}
	
	public void setAtuendosRechazados(List<Atuendo> atuendosRechazados) {
		this.atuendosRechazados = atuendosRechazados;
	}
	
	public List<Atuendo> getAtuendosRechazados() {
		return atuendosRechazados;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.add(guardarropa);
	}

	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda) throws Exception {
// TODO NO FUNCIONA, SI LAS PRENDAS DISPONIBLES ES NULL ME TIRA NULL PONTER EXCEPTION
		
		/*if(!this.guardarropas.stream().anyMatch(g ->{
			if(g.getPrendasDisponibles() != null)
				g.getPrendasDisponibles().contains(prenda);
		})) */
		/*{
			tipoDeCuenta.agregarPrendaAGuardarropa(guardarropa, prenda);
		} else {
			throw new Exception("Ya tenes la prenda en otro guardarropa");
		}*/

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
			atuendoFinal = guardarropa.generarRecomendacion(evento, this);
		}
		this.setUltimoAtuendo(atuendoFinal);
		return atuendoFinal;
	}

	public int getOffsetSuperior() {
		return this.offsetSuperior;
	}

	public int getOffsetInferior() {
		return this.offsetInferior;
	}

	public void agregarEvento(Evento evento) {
		evento.setCreador(this);
		this.eventos.add(evento);
	}

	public void crearNuevoGuardarropas(Estilo estilo) {
		this.guardarropas.add(new Guardarropa(this, estilo));
	}

	public List<Evento> getEventos(){
		return this.eventos;
	}

	public String getMail()
	{
		return this.mail;
	}

	public String getNumeroCelular()
	{
		return this.numeroCelular;
	}

	public boolean tieneEventosProximos()
	{
		return eventos.stream().anyMatch(e -> e.estaProximo());
	}

	public boolean tieneEventosOcurridoFrecuentemente(Calendar fecha)
	{
		return eventos.stream().anyMatch(e -> e.ocurre(fecha) && e.esFrecuente());
	}

	public List<Evento> getEventosOcurridoFrecuentemente(Calendar fecha)
	{
		return eventos.stream().filter(e -> e.ocurre(fecha) && e.esFrecuente()).collect(Collectors.toList());
	}

	public List<Atuendo> getSugerenciasQueFaltanCalificar()
	{
		List<Atuendo> sugerencias = new ArrayList<Atuendo>();

		List<Evento> eventosQueEstanProximosYNoSeSugirioLaRecomendacion = 
				this.eventos.stream().filter(e -> e.estaProximo() && !e.getSeNotificoUltimaSugerencia()).collect(Collectors.toList());

		if(!eventosQueEstanProximosYNoSeSugirioLaRecomendacion.isEmpty())
			eventosQueEstanProximosYNoSeSugirioLaRecomendacion
			.stream().forEach(e -> sugerencias.add(e.getUltimaSugerencia()));

		return sugerencias;
	}

	public void cambiarCategoria(TipoDeUsuario tipo) {
		this.tipoDeCuenta = tipo;
	}

	public boolean tieneEventoSinNotificar()
	{
		return eventos.stream().anyMatch(e -> !e.getSeNotificoUltimaSugerencia());
	}

	public List<Evento> getEventosProximosYsinNotificar()
	{
		return eventos.stream().filter(e -> e.estaProximo() && !e.getSeNotificoUltimaSugerencia()).collect(Collectors.toList());
	}

	public List<Evento> getEventosProximosYnotificados()
	{
		return eventos.stream().filter(e -> e.estaProximo() && e.getSeNotificoUltimaSugerencia()).collect(Collectors.toList());
	}

	public Atuendo getUltimoAtuendo() {
		return ultimoAtuendo;
	}

	public void setUltimoAtuendo(Atuendo ultimoAtuendo) {
		this.ultimoAtuendo = ultimoAtuendo;
	}

	public void modificarOffSetSuperior(Integer cantidad) {

		this.offsetSuperior += cantidad;

	}

	public void modificarOffSetInferior(Integer cantidad) {

		this.offsetInferior += cantidad;

	}
	
	public void calificar(Atuendo atuendo, Calificadores superior, Calificadores inferior) {

		this.getSugerenciasQueFaltanCalificar().remove(atuendo);
		this.modificarOffSetInferior(inferior.darCalificacion());
		this.modificarOffSetSuperior(superior.darCalificacion());	
	}

	public boolean tieneEventosOcurridos(Calendar fecha)
	{
		return eventos.stream().anyMatch(e -> e.ocurre(fecha));
	}

	public List<Evento> getEventosOcurridos(Calendar fecha) {
		return eventos.stream().filter(e -> e.ocurre(fecha)).collect(Collectors.toList());
 
	}
	
	public void agregarSugerenciaSinCalificar(Atuendo sugerencia) {
		this.sugerenciasQueFaltanCalificar.add(sugerencia);
		
	}
	
	public void modificarEstilo(Guardarropa guardarropa, Estilo estilo)
	{
		List<Guardarropa> guardarropas = this.guardarropas.stream().filter(g -> g.equals(guardarropa)).collect(Collectors.toList());
		
		guardarropas.get(0).cambiarEstilo(estilo);
	}
	
	public List<Guardarropa> getGuardarropas()
	{
		return this.guardarropas;
	}

	public void eliminarEvento(Evento evento) {
		this.eventos.remove(evento);
	}
	
	public String getPassword()
	{
		return this.password;
	}
}
