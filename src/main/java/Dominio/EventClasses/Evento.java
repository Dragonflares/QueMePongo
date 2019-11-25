package Dominio.EventClasses;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Prenda;
import Dominio.Estilish.Estilo;
import Dominio.UserClasses.Usuario;
import db.EntidadPersistente;

@Entity
@Table(name = "evento")
public class Evento extends EntidadPersistente{
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fecha")
	private LocalDateTime fecha;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Enumerated(EnumType.ORDINAL)
	private Estilo estilo;
	
	@ManyToOne
	@JoinColumn(name = "creador_id", referencedColumnName = "id", insertable = true)
	private Usuario creador;
	
	@Transient
	private Atuendo ultimoAtuendoAceptado; 
	
	
	public Atuendo getUltimoAtuendoAceptado() {
		return ultimoAtuendoAceptado;
	}

	public void setUltimoAtuendoAceptado(Atuendo ultimoAtuendoAceptado) {
		this.ultimoAtuendoAceptado = ultimoAtuendoAceptado;
	}

	@Transient
	private Frecuencia frecuencia; 
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "importancia_id", referencedColumnName = "id", nullable = true)
	private ImportanciaEvento importancia;
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<Atuendo> sugerencias = new ArrayList<Atuendo>();
	
	@Column(name = "sugerenciaNotificada")
	private boolean sugerenciaNotificada;
	
	@Transient
	private List<Prenda> prendasUltimoAtuendo;
	
	public Evento() {}
	
	public Evento (String nombre, LocalDateTime fecha, String direccion, Estilo estilo, Frecuencia frecuencia, ImportanciaEvento importancia)
	{
		this.nombre = nombre;
		this.fecha = fecha;
		this.direccion = direccion;
		this.estilo = estilo;
		this.frecuencia = frecuencia;
		this.sugerenciaNotificada = false;
		this.importancia = importancia;
		this.ultimoAtuendoAceptado = null;
	}
	
	
	
	@Override
	public String toString() {
		return "Evento [nombre=" + nombre + ", fecha=" + fecha + ", direccion=" + direccion + ", estilo=" + estilo
				+ ", creador=" + creador + ", frecuencia=" + frecuencia + ", importancia=" + importancia
				+ ", sugerencias=" + sugerencias + ", sugerenciaNotificada=" + sugerenciaNotificada + "]";
	}

	public String getNombre()
	{
		return this.nombre;
	}
	
	public Frecuencia getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Frecuencia frecuencia) {
		this.frecuencia = frecuencia;
	}

	public List<Atuendo> getSugerencias() {
		return sugerencias;
	}

	public void setSugerencias(List<Atuendo> sugerencias) {
		this.sugerencias = sugerencias;
	}

	public boolean isSugerenciaNotificada() {
		return sugerenciaNotificada;
	}

	public void setSugerenciaNotificada(boolean sugerenciaNotificada) {
		this.sugerenciaNotificada = sugerenciaNotificada;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public void setImportancia(ImportanciaEvento importancia) {
		this.importancia = importancia;
	}

	public Estilo obtenerEstilo()
	{
		return this.estilo;
	}
	
	public LocalDateTime getFechaLocalDateTime()
	{
		return this.fecha;
	}
	
	public boolean estaProximo()
	{
		return importancia.estaProximo(this);
	}
	
	public boolean ocurre(LocalDateTime fechaInteres)
	{
		return this.fecha.toLocalDate().compareTo(fechaInteres.toLocalDate()) == 0;
	}
	
	public boolean esFrecuente() 
	{
		return this.frecuencia != null;
	}
	
	public void actualizarFecha()
	{
		this.frecuencia.actualizarFecha(this);
		this.sugerenciaNotificada = false;
	}
	
	public void sumarDias(int dias)
	{
		this.fecha = fecha.plusDays(dias);
	}
	
	public void agregarSugerencia(Atuendo sugerencia)
	{
		this.sugerencias.add(sugerencia);
	}
	
	public Atuendo getUltimaSugerencia()
	{
		return sugerencias.get(sugerencias.size() - 1); 
	}
	
	public boolean getSeNotificoUltimaSugerencia()
	{
		return sugerenciaNotificada;
	}
	
	public void setFecha(LocalDateTime fecha)
	{
		this.fecha = fecha;
	}
	
	public void setSeNotificoUltimaSugerencia(Boolean bool)
	{
		this.sugerenciaNotificada = bool;
	}
	
	public void setCreador(Usuario creador)
	{
		this.creador = creador;
	}
	
	public String getDireccion() 
	{
		return this.direccion;
	}
	
	public ImportanciaEvento getImportanciaEvento()
	{
		return this.importancia;
	}
	
	public String getImportancia()
	{
		return this.importancia.getImportancia();
	}
	
	public LocalTime getHoraInicio()
	{
		return this.fecha.toLocalTime();
	}
	
	public String getFecha()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return this.getFechaLocalDateTime().format(formatter);
	}
	
	public String getEstilo()
	{
		return this.estilo.toString();
	}
	
	public boolean yaOcurrio()
	{
		return this.getFechaLocalDateTime().isBefore(LocalDateTime.now());
	}
	
	public List<Prenda> getPrendasUltimoAtuendo()
	{	
		prendasUltimoAtuendo = this.getUltimoAtuendoAceptado().getPrendas();
		return prendasUltimoAtuendo;
	}
}
