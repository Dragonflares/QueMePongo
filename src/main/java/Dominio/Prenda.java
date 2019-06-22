package Dominio;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Prenda {
	private TipoPrenda tipoPrenda;
	private Color colorPrimario;
	private Color colorSecundario;
	private Material material;
	Graphics2D imagenGraphics;


	public Prenda(TipoPrenda tipoPrenda, Color colorPrimario, Color colorSecundario, Material material, String imagenPath) throws Exception {
		this.tipoPrenda = tipoPrenda;

		if (!colorPrimario.equals(colorSecundario)) {
			this.colorPrimario =  colorPrimario;
			this.colorSecundario = colorSecundario;
		} else {
			throw new Exception("No pueden ser del mismo color."); 
		}
		
		if(!tipoPrenda.getMaterialesNoCompatible().contains(material)) {
			this.material = material;
		} else {
			throw new Exception("Ingreso un tipo de prenda no compatible.");
		}
		BufferedImage myPicture = ImageIO.read(new File(imagenPath));
		imagenGraphics = (Graphics2D) myPicture.getGraphics();
		imagenGraphics.drawRect(0, 0, 200, 200);
	}

	public TipoPrenda getTipoPrenda() {
		return this.tipoPrenda;
	}

	public Categoria getCategoria() { 
		return this.tipoPrenda.getCategoria();
	} 
	
}
