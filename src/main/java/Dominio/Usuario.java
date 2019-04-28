package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String username;
	private List<Guardarropa> guardarropas;
	
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
	
	
}
