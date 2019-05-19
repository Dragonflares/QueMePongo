package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Guardarropa {

	private List<Prenda> prendas;
	
	public Guardarropa(List<Prenda> prendas) {
		this.prendas = prendas;
	}
	
	public void agregarPrenda(Prenda prenda) {
		
		prendas.add(prenda);
	}
	
	
	public List<Prenda> getPrendasSuperiores(){
		return  prendas.stream().filter(p -> p.isTipoPrenda(Categoria.PARTE_SUPERIOR)).collect(Collectors.toList());
	}
	public List<Prenda> getPrendasInferiores(){
		return  prendas.stream().filter(p -> p.isTipoPrenda(Categoria.PARTE_INFERIOR)).collect(Collectors.toList());
	}
	public List<Prenda> getAccesorios(){
		return  prendas.stream().filter(p -> p.isTipoPrenda(Categoria.ACCESORIOS)).collect(Collectors.toList());
	}
	public List<Prenda> getCalzados(){
		return  prendas.stream().filter(p -> p.isTipoPrenda(Categoria.CALZADO)).collect(Collectors.toList());
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
	
	public ArrayList<Prenda> listasUnificadas(){
		ArrayList<Prenda> listaUnificada = new ArrayList<Prenda>();
		listaUnificada.addAll(prendasSuperiores);
		listaUnificada.addAll(prendasInferiores);
		listaUnificada.addAll(calzados);
		listaUnificada.addAll(accesorios);
		return listaUnificada;
	}
	
}




List<DispositivoInteligente> filtrarDispositivos(Predicate<DispositivoInteligente> unaCondicion) { 		return dispositivosInteligentes.stream().filter(unaCondicion).collect(Collectors.toList()); 	}


