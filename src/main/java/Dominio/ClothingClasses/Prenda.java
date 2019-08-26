package Dominio.ClothingClasses;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import Dominio.ClothingClasses.Material;
import Dominio.EventClasses.Evento;
import Dominio.UserClasses.ParametersPaso;
import Dominio.UserClasses.Usuario;
import Dominio.UserClasses.ParametersPaso.ParametersPasoBuilder;
import Dominio.WardrobeClasses.Guardarropa;
import Repositorios.RepoColor;
import Repositorios.RepoMaterial;
import Repositorios.RepoRopa;
import entities.ProcessingDataFailedException;

public class Prenda {
	private TipoDeRopa tipoRopa;
	private Color colorPrimario;
	private Color colorSecundario;
	private Material material;
	private List<Graphics2D> imagenGraphics;
	private String nombrePrenda;




	public Prenda (PrendaBuilder builder) {
		this.tipoRopa = builder.tipoRopa;
		this.nombrePrenda = builder.nombrePrenda;
		this.colorPrimario = builder.colorPrimario;
		this.colorSecundario = builder.colorSecundario;
		this.material = builder.material;
	}


	public void nuevaPrenda (PrendaBuilder builder) {
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

	private void setImagenGraphics(List<Graphics2D> imagenGraphics) {
		this.imagenGraphics = imagenGraphics;
	}

	public String getNombrePrenda() {
		return nombrePrenda;
	}

	public void setNombrePrenda(String nombrePrenda) {
		this.nombrePrenda = nombrePrenda;
	} 


	public static class PrendaBuilder {

		private TipoDeRopa tipoRopa;
		private Color colorPrimario;
		private Color colorSecundario;
		private Material material;
		private List<Graphics2D> imagenGraphics;
		private String nombrePrenda;

		public PrendaBuilder () {

		}

		public PrendaBuilder tipoRopa(String tipoRopa) throws ProcessingDataFailedException {
			this.tipoRopa = RepoRopa.getInstance().findByName(tipoRopa);
			return this;
		}

		public PrendaBuilder material (String material) throws ProcessingDataFailedException {
			//this.material = RepoMaterial.getInstance().findByName(material);
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
				this.colorPrimario =  RepoColor.getInstance().findByName(colorPrimario);
				this.colorSecundario = RepoColor.getInstance().findByName(colorSecundario);
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
}
