package Dominio.ClothingClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import Dominio.UserClasses.Property;
import Dominio.UserClasses.Usuario;
import db.EntidadPersistente;

@Entity
@Table(name = "atuendo")
public class Atuendo extends EntidadPersistente{
	@Transient
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
	
	public Boolean sonDeDistintoTipo(List<Prenda> prendas) {
		
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
	
	
	public Boolean sonIgualTipo(Prenda prenda1, Prenda prenda2) {
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
	
	public Boolean esElMismoAtuendo(Atuendo atuendo) {
		return this.prendas.containsAll(atuendo.prendas)
				&& this.prendas.size() == atuendo.prendas.size();
	}
	
	public Boolean abrigaLoNecesario(double temperatura, Usuario usuario)
	{
		if(temperatura >= Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase")) - usuario.getOffsetSuperior())
		{
			return this.veraniegoSuperiorAbrigaBien(temperatura, usuario);
		}
		return this.superioresAbriganLoSuficiente(temperatura, usuario) && this.inferiorAbrigaLoSuficiente(temperatura, usuario);
	}
	
	private Boolean veraniegoSuperiorAbrigaBien(double temperatura, Usuario usuario) {
		int calorActual = this.calorActualSuperiores(usuario);
		return Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase")) - 
				temperatura - Integer.parseInt(Property.getSpecifiedProperty("VariacionTemperatura")) < calorActual;
	}

	private Boolean superioresAbriganLoSuficiente(double temperatura, Usuario usuario)
	{
		int calorActual = this.calorActualSuperiores(usuario);
		return Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase")) - 
					temperatura - Integer.parseInt(Property.getSpecifiedProperty("VariacionTemperatura")) < calorActual 
				&& calorActual < Integer.parseInt(Property.getSpecifiedProperty("TemperaturaBase")) - 
					temperatura + Integer.parseInt(Property.getSpecifiedProperty("VariacionTemperatura"));
	}
	
	private int calorActualSuperiores(Usuario usuario)
	{
		return usuario.getOffsetSuperior() + (this.getPrendasSuperiores().stream()
				.mapToInt(p -> p.getNivelAbrigo()).sum());
	}
	
	private Boolean inferiorAbrigaLoSuficiente(double temperatura, Usuario usuario)
	{		
		int nivelAbrigo = this.getPrendasInferiores().get(0).getNivelAbrigo();
		if(temperatura > (Integer.parseInt(Property.getSpecifiedProperty("TemperaturaInferiorBase")) - usuario.getOffsetInferior()))
			return nivelAbrigo == 0;
		return nivelAbrigo == 1;
	}
}
