package Dominio.ClothingClasses;

import java.util.ArrayList;
import java.util.List;

public class MoldeDeAtuendo {
	private String nombre;
	private List<TipoDeRopa> ropasMolde = new ArrayList<TipoDeRopa>();
	private Integer nivelCalorSup;
	private Integer nivelCalorInf;
	private Integer nivelDeCalorCalzado;
	
	public MoldeDeAtuendo (List<TipoDeRopa> ropasMolde) {
		this.ropasMolde = ropasMolde;
		this.nivelCalorSup = ropasMolde.stream()
				.filter(p -> p.getCategoria() == Categoria.PARTE_SUPERIOR)
				.mapToInt(p -> p.getNivelAbrigo()).sum(); 
		this.nivelCalorInf = ropasMolde.stream()
				.filter(p -> p.getCategoria() == Categoria.PARTE_INFERIOR)
				.mapToInt(p -> p.getNivelAbrigo()).sum();
	}
	
	public Boolean esParaLluvia() {
		return ropasMolde.stream().anyMatch(p -> p.sirveParaLluvia());
	}
	
	public Boolean cumpleConCalorSuperior(double nivelDeCalor) {
		return this.nivelCalorSup > nivelDeCalor;
	}
	
	public Boolean cumpleConCalorInferior(double nivelDeCalor) {
		return this.nivelCalorInf > nivelDeCalor;
	}

	public Boolean cumpleConCalorCalzado(double nivelDeCalor) {
		return this.nivelDeCalorCalzado > nivelDeCalor;
	}
	
	public Boolean compararNombres(String otroNombre) {
		return this.getNombre().equals(otroNombre);
	}

	private String getNombre() {
		return this.nombre;
	}
}
