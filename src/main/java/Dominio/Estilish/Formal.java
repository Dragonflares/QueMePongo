package Dominio.Estilish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Prenda;
import Dominio.WardrobeClasses.Guardarropa;

public class Formal implements Estilo{

	@Override
	public Atuendo generarAtuendoVerano(Guardarropa guardarropa) throws Exception {
		List<Prenda> remeras = guardarropa.getPrendasDisponibles().stream()
				.filter(p -> p.getTipoRopa().getNombre().contentEquals("Remera manga corta") 
						|| p.getTipoRopa().getNombre().contentEquals("Chomba")
						|| p.getTipoRopa().getNombre().contentEquals("Camisa"))
				.collect(Collectors.toList());
		Integer cantRemeras = remeras.size();
		Random rand = new Random();
		Prenda remera = remeras.get(rand.nextInt(cantRemeras));
		List<Prenda> shorts = guardarropa.getPrendasDisponibles().stream()
				.filter(p -> p.getTipoRopa().getNombre().contentEquals("Pollera")
						|| p.getTipoRopa().getNombre().contentEquals("Pantalon largo"))
				.collect(Collectors.toList());
		Integer cantShorts = shorts.size();
		Prenda unShort = shorts.get(rand.nextInt(cantShorts));
		List<Prenda> zapatillas = guardarropa.getPrendasDisponibles().stream()
				.filter(p -> p.getTipoRopa().getNombre().contentEquals("Zapatillas")
						|| p.getTipoRopa().getNombre().contentEquals("Zapatos")
						|| p.getTipoRopa().getNombre().contentEquals("Tacos"))
				.collect(Collectors.toList());
		Integer cantZapatillas = zapatillas.size();
		Prenda zapatilla = zapatillas.get(rand.nextInt(cantZapatillas));
		ArrayList<Prenda> prendasDeAtuendo = new ArrayList<Prenda>();
		prendasDeAtuendo.add(remera);
		prendasDeAtuendo.add(unShort);
		prendasDeAtuendo.add(zapatilla);
		
		Atuendo atuendoVeraniego = new Atuendo(prendasDeAtuendo);
		return atuendoVeraniego;
	}

}
