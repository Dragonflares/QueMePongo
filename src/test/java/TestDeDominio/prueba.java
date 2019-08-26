package TestDeDominio;

import java.io.IOException;

import Dominio.Property;

public class prueba {


	public static void main(String[] args) throws IOException {
		
		Property property = new Property("properties.properties");
		String resultado = property.getSpecifiedProperty("Ejemplo");
		
		
		System.out.print(resultado);
	}
	
}

