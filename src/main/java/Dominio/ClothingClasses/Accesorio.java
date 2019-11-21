package Dominio.ClothingClasses;

import java.util.ArrayList;

public class Accesorio extends TipoDeRopa{	
	private Boolean sirveParaLluvia;
	
	public Accesorio(String nombre, Integer abrigo, Categoria categoria,
			ArrayList<Capas> capas,
			ArrayList<Material> materialesNoCompatibles,
			Boolean sirveParaLluvia,
			String imagen) {
		super(nombre, abrigo, categoria, capas, materialesNoCompatibles, imagen);
		this.sirveParaLluvia = sirveParaLluvia;
	}

	public Boolean sirveParaLluvia() {
		return this.sirveParaLluvia;
	}
}
