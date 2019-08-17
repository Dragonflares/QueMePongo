package TestDeDominio;

import java.io.IOException;

import Dominio.ClothingClasses.Estilo;
import Dominio.UserClasses.Usuario;

public class TestWeather {
	private static Usuario juan = new Usuario("Juan");
	private static Estilo formal = Estilo.FORMAL;

	public static void main(String[] args) throws IOException {
		juan.crearNuevoGuardarropas(formal);
		System.out.println(juan.guardarropas.get(0).pedirTemperatura(0, 12));
	}
}
