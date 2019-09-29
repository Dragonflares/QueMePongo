package Dominio.ClothingClasses;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Dominio.UserClasses.Property;

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
		int x = Integer.parseInt(Property.getSpecifiedProperty("positionX_Image"));
		int y = Integer.parseInt(Property.getSpecifiedProperty("positionY_Image"));
		int width = Integer.parseInt(Property.getSpecifiedProperty("width_Image")); 
		int height = Integer.parseInt(Property.getSpecifiedProperty("height_Image"));
		imagenGraphics.drawRect(x, y, width, height);
		return imagenGraphics;

	}
}
