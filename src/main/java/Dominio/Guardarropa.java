package Dominio;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Guardarropa {

	private List<Prenda> prendas;
	private GestorClimatico climaHelp;
	private List<Atuendo> atuendosQueNoHayQueSugerir;

	public Guardarropa(List<Prenda> prendas, GestorClimatico climaHelp) {
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
			System.out.println("No hay suficientes prendas "
					+ "n este guardarropas para generar un atuendo");
			atuendo = null;
		}
		return atuendo;
	}

	public void rechazar(Atuendo atuendo)
	{
		atuendosQueNoHayQueSugerir.add(atuendo);
	}

	public void deshacer(Atuendo atuendo)
	{
		if(atuendosQueNoHayQueSugerir.contains(atuendo))
			atuendosQueNoHayQueSugerir.remove(atuendo);
	}
}


