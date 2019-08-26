package TestDeDominio;

import java.io.IOException;
import java.util.Calendar;

import Dominio.ClothingClasses.Estilo;
import Dominio.UserClasses.Usuario;

public class TestWeather {


	public static void main(String[] args) throws IOException {
		Calendar fecha = Calendar.getInstance();
		Integer diasDeMes = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		System.out.print(diasDeMes);
	}
}
