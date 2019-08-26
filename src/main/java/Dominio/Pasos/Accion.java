package Dominio.Pasos;

import java.util.List;

public class Accion { // Aceptar/Rechazar
	public List<Paso> pasosAAplicar;
	public List<Paso> pasosADeshacer;
	
	public void aplicar()
	{
		pasosAAplicar.stream().forEach(paso -> {
			try {
				paso.ejecutar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public void deshacer()
	{
		pasosADeshacer.stream().forEach(paso -> {
			try {
				paso.ejecutar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
