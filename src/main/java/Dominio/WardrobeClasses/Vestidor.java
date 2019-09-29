package Dominio.WardrobeClasses;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Categoria;
import Dominio.ClothingClasses.MoldeDeAtuendo;
import Dominio.ClothingClasses.Prenda;
import Dominio.ClothingClasses.TipoDeRopa;
import Dominio.Estilish.Estilo;
import Dominio.UserClasses.Property;
import Dominio.UserClasses.Usuario;
import Importadores.ImportadorDeMoldes;

public class Vestidor {
	private static Vestidor instance = new Vestidor();
	
	private Vestidor () {
	} 
	
	public static Vestidor getInstance()
	{
		return instance;
	}
	
	public Atuendo obtenerAtuendoVeraniego(Guardarropa guardarropa, double temperatura,
			int intento, Usuario usuario) throws Exception {
		MoldeDeAtuendo molde = obtenerMolde(guardarropa.getEstilo(), temperatura);
		ArrayList<Prenda> prendasParaAtuendo = (ArrayList<Prenda>)
				molde.darRopas().stream()
				.map(p -> obtenerPrendaDelMolde(guardarropa, p))
				.collect(Collectors.toList());
		if(cumpleCondicionesVeraniegas(prendasParaAtuendo, usuario)) {
			return new Atuendo(prendasParaAtuendo);
		}
		else if (intento < Integer.parseInt(Property.getSpecifiedProperty("CantIntentos"))) {
			intento ++;
			return obtenerAtuendoVeraniego(guardarropa, temperatura,
					intento, usuario);
		}
		else
			return null;
	}
	
	
	private Boolean cumpleCondicionesVeraniegas(ArrayList<Prenda> prendasParaAtuendo,
			Usuario usuario) {
		return cumpleCondicionSuperiorVeraniega(prendasParaAtuendo, usuario) 
				&& cumpleCondicionInferiorVeraniega(prendasParaAtuendo, usuario)
				&& cumpleCondicionCalzadoVeraniego(prendasParaAtuendo, usuario);
		
	}

	
	private Boolean cumpleCondicionSuperiorVeraniega(ArrayList<Prenda> prendas,
			Usuario usuario) {
		return (Integer.parseInt(Property.getSpecifiedProperty("MaximoVeraniegoSuperior")) >=
				prendas.stream().filter(p -> p.getCategoria().equals(Categoria.PARTE_SUPERIOR))
				.mapToInt(p -> p.getTipoRopa().getNivelAbrigo()).sum());
			
	}
	private Boolean cumpleCondicionInferiorVeraniega(ArrayList<Prenda> prendas,
			Usuario usuario) {
		return (Integer.parseInt(Property.getSpecifiedProperty("MaximoVeraniegoInferior")) >=
		prendas.stream().filter(p -> p.getCategoria().equals(Categoria.PARTE_INFERIOR))
		.mapToInt(p -> p.getTipoRopa().getNivelAbrigo()).sum());
	}
	private Boolean cumpleCondicionCalzadoVeraniego(ArrayList<Prenda> prendas,
			Usuario usuario) {
		return (Integer.parseInt(Property.getSpecifiedProperty("MaximoVeraniegoCalzado")) >=
		prendas.stream().filter(p -> p.getCategoria().equals(Categoria.CALZADO))
		.mapToInt(p -> p.getTipoRopa().getNivelAbrigo()).sum());
	}
	
	public Atuendo obtenerAtuendo(Guardarropa guardarropa, double temperatura, int intento, 
			Usuario usuario) 
			throws Exception  {
		if(temperatura >  Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase"))
				- usuario.getOffsetSuperior())
			return obtenerAtuendoVeraniego(guardarropa, temperatura, intento, usuario);
		MoldeDeAtuendo molde = obtenerMolde(guardarropa.getEstilo(), temperatura);
		ArrayList<Prenda> prendasParaAtuendo = (ArrayList<Prenda>)
				molde.darRopas().stream()
				.map(p -> obtenerPrendaDelMolde(guardarropa, p))
				.collect(Collectors.toList());
		if(prendasParaAtuendo.stream().anyMatch(p -> p.equals(null)))
		{
			if(intento < Integer.parseInt(Property.getSpecifiedProperty("CantIntentos")))
			{
				intento++;
				return obtenerAtuendo(guardarropa, temperatura, intento, usuario);
			}
			else
				return null;
		}
		else
		{
			return new Atuendo(prendasParaAtuendo);
		}
	}

	public MoldeDeAtuendo obtenerMolde(Estilo estilo, double temperatura) 
			throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		Random rand = new Random();
		List<MoldeDeAtuendo> moldesDisponibles =
				ImportadorDeMoldes.getInstance().levantarMoldesDePath().stream()
				.filter(p -> p.getEstilo().equals(estilo))
				.filter(p -> p.cumpleConCondiciones(
						(Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase"))
								- temperatura 
								- Integer.parseInt(Property.getSpecifiedProperty("VariacionTemperatura")))))
				.collect(Collectors.toList());
		return moldesDisponibles.get(rand.nextInt(moldesDisponibles.size()));
	}

	public Prenda obtenerPrendaDelMolde(Guardarropa guardarropa, TipoDeRopa molde) {
		Random rand = new Random();
		List<Prenda> prendasPosibles = guardarropa.getPrendasDisponibles()
				.stream().filter(p -> p.getTipoRopa().equals(molde))
				.collect(Collectors.toList());
		if(prendasPosibles.isEmpty())
			return null;
		else
			return prendasPosibles.get(rand.nextInt(prendasPosibles.size()));
	}
}
