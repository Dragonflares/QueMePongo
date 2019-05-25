package Dominio;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Guardarropa {

	private List<Prenda> prendas;
	
	public Guardarropa(List<Prenda> prendas) {
		this.prendas = prendas;
	}
	
	public int cantidadDePrendas()
	{
		return prendas.size();
	}
	
	public void agregarPrenda(Prenda prenda) {
		
		prendas.add(prenda);
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
	
	public Atuendo generarRecomendacion() throws Exception{
		
		Random rand = new Random();
		int limitSup = getPrendasSuperiores().size();
		int indexSuperior = rand.nextInt(limitSup);
		Prenda prendaSuperior = getPrendasSuperiores().get(indexSuperior);
		int limitInf = getPrendasSuperiores().size();
		int indexInferior = rand.nextInt(limitInf);
		Prenda prendaInferior = getPrendasSuperiores().get(indexInferior);
		int limitAccesorios = getPrendasSuperiores().size();
		int indexAccesorios = rand.nextInt(limitAccesorios);
		Prenda accesorio = getPrendasSuperiores().get(indexAccesorios);
		int limitCalzados = getPrendasSuperiores().size();
		int indexCalzados = rand.nextInt(limitCalzados);
		Prenda calzado = getPrendasSuperiores().get(indexCalzados);
		List<Prenda> prendasDeAtuendo = new ArrayList<Prenda>();
		prendasDeAtuendo.add(prendaSuperior);
		prendasDeAtuendo.add(prendaInferior);
		prendasDeAtuendo.add(accesorio);
		prendasDeAtuendo.add(calzado);
		Atuendo atuendo = new Atuendo(prendasDeAtuendo);
		return atuendo;
	}	
}


