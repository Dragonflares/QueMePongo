package Dominio;

import java.util.List;

public class Atuendo {
	private List<Prenda> prendas;
	
	public Atuendo(List<Prenda> prendas) {
		
		if(this.sonDeDistintoTipo()) {
			this.prendas = prendas;
		}
		
	}
	
	public Atuendo() {}
	
	public void agregar(Prenda prenda) {
		prendas.add(prenda);
	}
	
	public boolean sonDeDistintoTipo() {
		
		int i;
		for(i= 0; i<prendas.size(); i++) {
			
			Prenda prendaActual = prendas.get(i);
			prendas.stream().filter(p -> (sonIgualTipo(prendaActual, p) && p != prendaActual));
			
			if(!prendas.isEmpty()) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public boolean sonIgualTipo(Prenda prenda1, Prenda prenda2) {
		return prenda1.getTipoPrenda().equals(prenda2.getTipoPrenda());
	}
}
