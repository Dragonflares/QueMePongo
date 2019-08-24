package Dominio.WardrobeClasses;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;

import Dominio.ClothingClasses.Prenda;
import Dominio.UserClasses.TipoDeUsuario;
import Dominio.UserClasses.Usuario; 

public class Gratuito implements TipoDeUsuario{
	private int tamanioMaximoGuardarropa;
	private String configGratuito = "Resources\\ConfigUsuarioGratuito.txt";

	public Gratuito() throws FileNotFoundException
	{
		File file = new File(configGratuito); 
		Scanner sc = new Scanner(file); 
		
		sc.useDelimiter(";");

		String linea = sc.next();
		String[] config = linea.split("=");
		tamanioMaximoGuardarropa = Integer.parseInt(config[1]);
		
		sc.close();
	}

	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda) throws Exception
	{
		if(this.tamanioMaximoGuardarropa > guardarropa.cantidadDePrendasDisponibles())
		{
			guardarropa.agregarPrenda(prenda);
		} else 
		{
			throw new Exception("Ya tenes la prenda en otro guardarropa");
		}

	}

	@Override
	public void cambiarCategoria(Usuario usuario) {
		this.pasarAPremium(usuario);
		
	}
	
	public void pasarAPremium(Usuario usuario) {
		Premium premium = new Premium();
		usuario.cambiarCategoria(premium);
	}
}
