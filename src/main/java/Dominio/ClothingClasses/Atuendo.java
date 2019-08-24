package Dominio.ClothingClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Atuendo {
	private ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	
	public Atuendo(ArrayList<Prenda> prendas) throws Exception {
		if(sonDeDistintoTipo(prendas))
			this.prendas = prendas;
		else 
			throw new Exception("No son del mismo tipo.");	
	}
	
	public Atuendo() {}
	
	public void agregar(Prenda prenda) {
		prendas.add(prenda);
	}
	
	public boolean sonDeDistintoTipo(List<Prenda> prendas) {
		
		int i;
		for(i= 0; i<prendas.size(); i++) {
			
			Prenda prendaActual = prendas.get(i);
			List<Prenda> listaVacia = prendas.stream().filter(p -> (sonIgualTipo(prendaActual, p) && p != prendaActual)).collect(Collectors.toList());
			
			if(listaVacia.isEmpty()) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public boolean sonIgualTipo(Prenda prenda1, Prenda prenda2) {
		return prenda1.getTipoRopa().getNombre().equals(prenda2.getTipoRopa().getNombre());
	}
	
	public List<Prenda> getPrendas() {
		return this.prendas;
	}
	
	public List<Prenda> getPrendasSuperiores(){
		return  prendas.stream().filter(p -> p.getCategoria() == Categoria.PARTE_SUPERIOR).collect(Collectors.toList());
	}
	public List<Prenda> getPrendasInferiores(){
		return  prendas.stream().filter(p -> p.getCategoria() == Categoria.PARTE_INFERIOR).collect(Collectors.toList());
	}
	public List<Prenda> getAccesorios(){
		return  prendas.stream().filter(p -> p.getCategoria() == Categoria.ACCESORIOS).collect(Collectors.toList());
	}
	public List<Prenda> getCalzados(){
		return  prendas.stream().filter(p -> p.getCategoria() == Categoria.CALZADO).collect(Collectors.toList());
	}
}
