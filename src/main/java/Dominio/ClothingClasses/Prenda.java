package Dominio.ClothingClasses;

import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Repositorios.factories.FactoryRepositorioColor;
import Repositorios.factories.FactoryRepositorioRopa;
import Repositorios.factories.FactoryRepositoriosMaterial;
import db.EntidadPersistente;
import entities.ProcessingDataFailedException;

@Entity
@Table(name = "Prenda")
public class Prenda extends EntidadPersistente{
	@ManyToOne
	@JoinColumn(name = "tipo_id", referencedColumnName = "id")
	private TipoDeRopa tipoRopa;
	
	@Column(name = "nombrePrenda")
	private String nombrePrenda;
	
	@ManyToOne
	@JoinColumn(name = "color_primario_id", referencedColumnName = "id")
	private Color colorPrimario;
	
	@ManyToOne
	@JoinColumn(name = "color_secundario_id", referencedColumnName = "id")
	private Color colorSecundario;
	
	@OneToOne
	@JoinColumn(name = "material_id", referencedColumnName = "id")
	private Material material;
	
	@Transient
	private List<Graphics2D> imagenGraphics;
	
	public Prenda() {}

	public Prenda(String nombrePrenda) // TODO sacar cuando se haya hecho bien en el UserController
	{
		this.nombrePrenda = nombrePrenda;
	}
	
	public Prenda (PrendaBuilder builder) {
		this.tipoRopa = builder.tipoRopa;
		this.nombrePrenda = builder.nombrePrenda;
		this.colorPrimario = builder.colorPrimario;
		this.colorSecundario = builder.colorSecundario;
		this.material = builder.material;
	}

	public TipoDeRopa getTipoRopa() {
		return this.tipoRopa;
	}

	public void setearListaImagenes(List<String> imagenes) {
		imagenes.forEach(una -> this.imagenGraphics.add(this.crearImagen(una)));
	}

	public Graphics2D crearImagen (String path) {
		Imagen creator = new Imagen();
		return creator.crearImagen(path);
	}

	public Categoria getCategoria() { 
		return this.tipoRopa.getCategoria();
	}

	public List<Graphics2D> getImagenGraphics() {
		return imagenGraphics;
	}

	@SuppressWarnings("unused")
	private void setImagenGraphics(List<Graphics2D> imagenGraphics) {
		this.imagenGraphics = imagenGraphics;
	}

	public String getNombrePrenda() {
		return nombrePrenda;
	}

	public void setNombrePrenda(String nombrePrenda) {
		this.nombrePrenda = nombrePrenda;
	} 
	
	public Integer getNivelAbrigo()
	{
		return this.tipoRopa.getNivelAbrigo();
	}


	public static class PrendaBuilder {

		private TipoDeRopa tipoRopa;
		private Color colorPrimario;
		private Color colorSecundario;
		private Material material;
		@SuppressWarnings("unused")
		private List<Graphics2D> imagenGraphics;
		private String nombrePrenda;

		public PrendaBuilder () {}

		public PrendaBuilder tipoRopa(String tipoRopa) throws ProcessingDataFailedException, JsonIOException, JsonSyntaxException, FileNotFoundException {
			this.tipoRopa = (TipoDeRopa) FactoryRepositorioRopa.get().findByName(tipoRopa);
			return this;
		}

		public PrendaBuilder material (String material) throws ProcessingDataFailedException, JsonIOException, JsonSyntaxException, FileNotFoundException {
			this.material = (Material) FactoryRepositoriosMaterial.get().findByName(material);
			return this;
		}

		public PrendaBuilder nombrePrenda(String nombrePrenda) {
			this.nombrePrenda = nombrePrenda;
			return this;
		}

		public PrendaBuilder imagen(List<String> imagenes) {
			this.imagenGraphics = imagenes.stream().map(p -> this.crearImagen(p))
					.collect(Collectors.toList());;
					return this;
		}

		public PrendaBuilder setearColores(String colorPrimario, String colorSecundario) throws Exception{
			if (!colorPrimario.equals(colorSecundario)) {
				this.colorPrimario =  (Color) FactoryRepositorioColor.get().findByName(colorPrimario);
				this.colorSecundario = (Color) FactoryRepositorioColor.get().findByName(colorSecundario);
			} else {
				throw new Exception("No pueden ser del mismo color."); 
			}
			return this;
		}

		public Graphics2D crearImagen (String path) {
			Imagen creator = new Imagen();
			return creator.crearImagen(path);	
		}

		public Prenda build() {
			return new Prenda(this);
		}
	}
	
	public Color getColorPrimario()
	{
		return this.colorPrimario;
	}
	
	public Color getColorSecundario()
	{
		return this.colorSecundario;
	}
	
	public Material getMaterial() {
		return this.material;
	}
}
