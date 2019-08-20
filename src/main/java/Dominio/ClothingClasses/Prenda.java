package Dominio.ClothingClasses;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import Dominio.ClothingClasses.Material;
import Repositorios.RepoColor;
import Repositorios.RepoMaterial;

public class Prenda {
	private TipoPrenda tipoPrenda;
	private Color colorPrimario;
	private Color colorSecundario;
	private Material material;
	private List<Graphics2D> imagenGraphics;


	public Prenda(TipoPrenda tipoPrenda, String colorPrimario,
		String colorSecundario, String material) throws Exception {
		this.tipoPrenda = tipoPrenda;
		Material nuevoMaterial = RepoMaterial.getInstance().findByName(material);
		
		if (!colorPrimario.equals(colorSecundario)) {
			this.colorPrimario =  RepoColor.getInstance().findByName(colorPrimario);
			this.colorSecundario = RepoColor.getInstance().findByName(colorSecundario);
		} else {
			throw new Exception("No pueden ser del mismo color."); 
		}
		
		if(!tipoPrenda.getMaterialesNoCompatible().contains(nuevoMaterial)) {
			this.material = nuevoMaterial;
		} else {
			throw new Exception("Ingreso un tipo de prenda no compatible.");
		}
		

		
	
	}

	public TipoPrenda getTipoPrenda() {
		return this.tipoPrenda;
	}

	
	public void setearListaImagenes(List<String> imagenes) {

		imagenes.forEach(una -> this.imagenGraphics.add(this.crearImagen(una)));
	}
	
	
	public Graphics2D crearImagen (String path) {
		
		Imagen creator = new Imagen();
		return creator.crearImagen(path);
		
		
	}
	
	
	public Categoria getCategoria() { 
		return this.tipoPrenda.getCategoria();
	}

	public List<Graphics2D> getImagenGraphics() {
		return imagenGraphics;
	}

	private void setImagenGraphics(List<Graphics2D> imagenGraphics) {
		this.imagenGraphics = imagenGraphics;
	} 
	
}
