package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Guardarropa {
	private List<Prenda> prendasSuperiores;
	private List<Prenda> prendasInferiores;
	private List<Prenda> calzados;
	private List<Prenda> accesorios;
	
	
	public Guardarropa(List<Prenda> prendasSuperiores, List<Prenda> prendasInferiores, List<Prenda> calzados, List<Prenda> accesorios) {
		this.prendasSuperiores = prendasSuperiores;
		this.prendasInferiores = prendasInferiores;
		this.calzados = calzados;
		this.accesorios = accesorios;
	}
	
	
	public ArrayList<Atuendo> generarRecomendaciones(){
		ArrayList<Atuendo> atuendos = new ArrayList<>();
		
		int i, j, k, l;
		for(i= 0; i<prendasSuperiores.size(); i++) {
			for(j = 0; j< prendasInferiores.size(); j++) {
				for(k=0; k<calzados.size(); k++) {
					for(l = 0; l < accesorios.size(); l++) {
						Atuendo atuendo = new Atuendo();
						atuendo.agregar(prendasSuperiores.get(i));
						atuendo.agregar(prendasInferiores.get(j));
						atuendo.agregar(calzados.get(k));
						atuendo.agregar(accesorios.get(l));
						
						atuendos.add(atuendo);
					}
				}
			}
		}
		
		return atuendos;
	}
	
	
	
}


