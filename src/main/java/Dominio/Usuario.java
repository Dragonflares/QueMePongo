package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Usuario {
	private String username;
	private TipoDeUsuario tipoDeCuenta;
	public List<Guardarropa> guardarropas;

	public Usuario(String username){
		this.username = username;
		this.guardarropas = new ArrayList<Guardarropa>();
	}

	public Usuario(String username, Guardarropa guardarropa) {
		this.username = username;
		this.guardarropas = new ArrayList<Guardarropa>();
		this.inicializarGuardarropa(guardarropa);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private void inicializarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.add(guardarropa);
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.add(guardarropa);
	}

	public void agregarPrendaAGuardarropa(Guardarropa guardarropa, Prenda prenda) throws Exception {

		if(!this.guardarropas.stream().anyMatch(p -> p.getPrendas().contains(prenda))) {
			tipoDeCuenta.agregarPrendaAGuardarropa(guardarropa, prenda);
		} else {
			throw new Exception("Ya tenes la prenda en otro guardarropa");
		}

	}

	public Atuendo pedirRecomendacion() throws Exception{
		Atuendo atuendoFinal = null;
		Random rand = new Random();
		int cantGuardarropas = guardarropas.size();
		while(atuendoFinal != null)
		{
			Guardarropa guardarropa =  guardarropas.get(rand.nextInt(cantGuardarropas));
			atuendoFinal = guardarropa.generarRecomendacion();
		}
		return atuendoFinal;
	}

}
