package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Guardarropa {
	private List<Prenda> prendas;
	
	public Guardarropa() {
		this.prendas = new ArrayList<Prenda>();
	}
	
	public int cuantasPrendas() {
		return this.prendas.size();
	}
}
