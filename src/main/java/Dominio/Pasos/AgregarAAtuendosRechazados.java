package Dominio.Pasos;

import Dominio.ClothingClasses.Atuendo;
import Dominio.ClothingClasses.Estilo;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;

public class AgregarAAtuendosRechazados implements Paso{
	
	private Usuario usuario;
	private Atuendo atuendo;
	
	
	public AgregarAAtuendosRechazados(Usuario usuario,Atuendo atuendo) {
		
		this.usuario = usuario;
		this.atuendo = atuendo;
	}
	
	
	
	public void ejecutar()
	{
		usuario.agregarARechazados(atuendo);
	}
}



//public class DevolverUltimaRecomendacion implements Paso{
//	
//	private Usuario usuario;
//	
//	
//	
//	public DevolverUltimaRecomendacion(Usuario usuario) {
//		
//		this.usuario = usuario;
//		
//	}
//	
//	
//	
//	public void ejecutar()
//	{
//		usuario.getUltimoAtuendo();
//	}
//}

