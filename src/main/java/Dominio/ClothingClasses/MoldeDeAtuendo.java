package Dominio.ClothingClasses;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import Dominio.Estilish.Estilo;
import db.EntidadPersistente;

@Entity
@Table(name = "molde")
public class MoldeDeAtuendo extends EntidadPersistente{
	@Column(name = "nombre")
	private String nombre;
	
	@Enumerated(EnumType.ORDINAL)
	private Estilo estilo;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TipoDeRopa> ropasMolde = new ArrayList<TipoDeRopa>();
	
	public MoldeDeAtuendo (List<TipoDeRopa> ropasMolde, Estilo estilo) {
		this.ropasMolde = ropasMolde;
		this.estilo = estilo;
	}
	
	public Estilo getEstilo() {
		return this.estilo;
	}
	
	public List<TipoDeRopa> darRopas() {
		return this.ropasMolde;
	}
	public Boolean esParaLluvia() {
		return ropasMolde.stream().anyMatch(p -> p.sirveParaLluvia());
	}
	
	public Boolean cumpleConCalorSuperior(double nivelesDeCalor) {
		return ropasMolde.stream()
				.filter(p -> p.getCategoria() == Categoria.PARTE_SUPERIOR)
				.mapToInt(p -> p.getNivelAbrigo()).sum() >= nivelesDeCalor;
	}
	
	public Boolean cumpleConCalorInferior(double nivelesDeCalor) {
		return ropasMolde.stream()
				.filter(p -> p.getCategoria() == Categoria.PARTE_INFERIOR)
				.mapToInt(p -> p.getNivelAbrigo()).sum() >= nivelesDeCalor;
	}

	public Boolean cumpleConCalorCalzado(double nivelesDeCalor) {
		return ropasMolde.stream()
				.filter(p -> p.getCategoria() == Categoria.CALZADO)
				.mapToInt(p -> p.getNivelAbrigo()).sum() >= nivelesDeCalor;
	}
	
	public Boolean compararNombres(String otroNombre) {
		return this.getNombre().equals(otroNombre);
	}

	private String getNombre() {
		return this.nombre;
	}
	
	public Boolean cumpleConCondiciones(double nivelesDeCalor) {
		return cumpleConCalorSuperior(nivelesDeCalor) &&
				cumpleConCalorInferior(nivelesDeCalor) &&
				cumpleConCalorCalzado(nivelesDeCalor);
	}
}
