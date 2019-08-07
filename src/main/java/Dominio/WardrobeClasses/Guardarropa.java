package Dominio.WardrobeClasses;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Estilo;
import Dominio.ClothingClasses.Prenda;
import Dominio.WeatherAPIClasses.GestorClimatico;

public class Guardarropa {
	
	private Estilo estilo;
	private List<Prenda> prendas;
	private GestorClimatico climaHelp;

	public Guardarropa(Estilo estilo, List<Prenda> prendas, GestorClimatico climaHelp) {
		this.estilo = estilo;
		this.prendas = prendas;
		this.climaHelp = climaHelp;
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
	public Estilo getEstilo()
	{
		return this.estilo;
	}

	public Atuendo generarRecomendacion() throws Exception
	{
		Atuendo atuendo = null;
		int limitSup = getPrendasSuperiores().size();
		int limitInf = getPrendasInferiores().size();
		int limitAccesorios = getAccesorios().size();
		int limitCalzados = getCalzados().size();
		if(limitSup != 0 && limitInf !=0 && limitCalzados != 0)
		{
			Random rand = new Random();
			int indexSuperior = rand.nextInt(limitSup);
			Prenda prendaSuperior = getPrendasSuperiores().get(indexSuperior);
			int indexInferior = rand.nextInt(limitInf);
			Prenda prendaInferior = getPrendasInferiores().get(indexInferior);
			int indexAccesorios = rand.nextInt(limitAccesorios);
			Prenda accesorio = getAccesorios().get(indexAccesorios);
			int indexCalzados = rand.nextInt(limitCalzados);
			Prenda calzado = getCalzados().get(indexCalzados);
			List<Prenda> prendasDeAtuendo = new ArrayList<Prenda>();
			prendasDeAtuendo.add(prendaSuperior);
			prendasDeAtuendo.add(prendaInferior);
			prendasDeAtuendo.add(accesorio);
			prendasDeAtuendo.add(calzado);
			atuendo = new Atuendo(prendasDeAtuendo);
		}
		else
		{
			atuendo = null;
		}
		return atuendo;
	}
}


