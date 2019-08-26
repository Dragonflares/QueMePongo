package Dominio.ClothingClasses;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Dominio.Property;

public class Imagen {


	public Graphics2D crearImagen (String path) {

		Graphics2D imagenGraphics;
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagenGraphics = (Graphics2D) myPicture.getGraphics();
		Property object = new Property("properties.properties");
		int x = Integer.parseInt(object.getSpecifiedProperty("positionX_Image"));
		int y = Integer.parseInt(object.getSpecifiedProperty("positionY_Image"));
		int width = Integer.parseInt(object.getSpecifiedProperty("width_Image")); 
		int height = Integer.parseInt(object.getSpecifiedProperty("height_Image"));
		imagenGraphics.drawRect(x, y, width, height);
		return imagenGraphics;

	}




}
