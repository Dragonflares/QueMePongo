package Dominio.WardrobeClasses;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.Estilo;
import Dominio.ClothingClasses.Prenda;
import Dominio.UserClasses.Evento;
import Dominio.UserClasses.Usuario;
import Dominio.WeatherAPIClasses.GestorClimatico;

public class Guardarropa {

	private List<Usuario> usuariosConAcceso;
	private Estilo estilo;
	private List<Prenda> prendasDisponibles;
	private List<Prenda> prendasNoDisponibles;
	private GestorClimatico climaHelp;

	public Guardarropa(List<Usuario> compartidos,
			Estilo estilo, List<Prenda> prendas, GestorClimatico climaHelp) {
		this.usuariosConAcceso = compartidos;
		this.estilo = estilo;
		this.prendasDisponibles = prendas;
		this.prendasNoDisponibles = null;
		this.climaHelp = climaHelp;
	}

	public int cantidadDePrendasDisponibles()
	{
		return prendasDisponibles.size();
	}

	public void permitirAccesoaUsuario (Usuario usuario) {
		usuariosConAcceso.add(usuario);
	}

	public void agregarPrenda(Prenda prenda) {

		prendasDisponibles.add(prenda);
	}

	public List<Prenda> getPrendasDisponibles() {
		return this.prendasDisponibles;
	}
	public List<Prenda> getPrendasSuperioresDisponibles(){
		return  prendasDisponibles.stream().filter(p -> p.getCategoria() == Categoria.PARTE_SUPERIOR).collect(Collectors.toList());
	}
	public List<Prenda> getPrendasInferioresDisponibles(){
		return  prendasDisponibles.stream().filter(p -> p.getCategoria() == Categoria.PARTE_INFERIOR).collect(Collectors.toList());
	}
	public List<Prenda> getAccesoriosDisponibles(){
		return  prendasDisponibles.stream().filter(p -> p.getCategoria() == Categoria.ACCESORIOS).collect(Collectors.toList());
	}
	public List<Prenda> getCalzadosDisponibles(){
		return  prendasDisponibles.stream().filter(p -> p.getCategoria() == Categoria.CALZADO).collect(Collectors.toList());
	}
	public Estilo getEstilo()
	{
		return this.estilo;
	}

	public Atuendo generarRecomendacion(Evento evento) throws Exception
	{
		Atuendo atuendo = null;
		int limitSup = getPrendasSuperioresDisponibles().size();
		int limitInf = getPrendasInferioresDisponibles().size();
		int limitAccesorios = getAccesoriosDisponibles().size();
		int limitCalzados = getCalzadosDisponibles().size();

		double temperatura = GestorClimatico.obtenerTemperatura(evento.getFecha().get(Calendar.DAY_OF_MONTH) , evento.getFecha().get(Calendar.HOUR_OF_DAY));
		Random rand = new Random();
		if(temperatura >= 27)
		{
			//TODO Hacer con lo del builder que ponga remeramangacorta/musculosa, pantalon corto.
		}
		else
		{
			List<Prenda> prendasSuperiores = new ArrayList<Prenda>();
			int calorActual;
			int a = 0;
			do{
				final int b = a;
				int indexSuperior = rand.nextInt(limitSup);
				List<Prenda> prendasSuperioresPosibles = getPrendasSuperioresDisponibles()
						.stream()
						.filter(p -> p.getTipoPrenda().getRopa().esDeEsaCapa(b))
						.collect(Collectors.toList());
				Prenda prendaSuperior = prendasSuperioresPosibles.get(indexSuperior);
				prendasSuperiores.add(prendaSuperior);
				calorActual = evento.obtenerCreador().getOffset() + (prendasSuperiores.stream()
						.mapToInt(p -> p.getTipoPrenda().getRopa().abrigar()).sum());
				a++;
			}
			while(calorActual < (27 - temperatura - 3) && a < 6);
			if(calorActual > (27 - temperatura + 3))
			{
				//TODO Poner que vuelva a generar parte superior
			}
			else
			{
				Prenda prendaInferior;
				if(temperatura > 18)
				{
					int indexInferior = rand.nextInt(limitInf);
					List<Prenda> prendasInferioresPosibles = getPrendasSuperioresDisponibles()
							.stream()
							.filter(p -> p.getTipoPrenda().getRopa().abrigar() == 0)
							.collect(Collectors.toList());
					prendaInferior = prendasInferioresPosibles.get(indexInferior);
				}
				else
				{
					int indexInferior = rand.nextInt(limitInf);
					List<Prenda> prendasInferioresPosibles = getPrendasSuperioresDisponibles()
							.stream()
							.filter(p -> p.getTipoPrenda().getRopa().abrigar() == 1)
							.collect(Collectors.toList());
					prendaInferior = prendasInferioresPosibles.get(indexInferior);
				}
				int indexAccesorios = rand.nextInt(limitAccesorios);
				Prenda accesorio = getAccesoriosDisponibles().get(indexAccesorios);
				int indexCalzados = rand.nextInt(limitCalzados);
				Prenda calzado = getCalzadosDisponibles().get(indexCalzados);
				ArrayList<Prenda> prendasDeAtuendo = new ArrayList<Prenda>();
				prendasDeAtuendo.add(prendaInferior);
				prendasDeAtuendo.add(accesorio);
				prendasDeAtuendo.add(calzado);
				atuendo = new Atuendo(prendasDeAtuendo);
			}
		}
		return atuendo;
	}

	public void marcarNoDiponible(Atuendo atuendo)
	{
		atuendo.getPrendas().forEach(prenda -> {
			this.prendasDisponibles.remove(prenda);
			this.prendasNoDisponibles.add(prenda);
		});
	}
}


