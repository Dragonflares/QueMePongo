package Dominio;

public class Adapter implements APIClimatica{
	
	private ClimaAdaptado climaticAdaptee;
	
	public Adapter (ClimaAdaptado climaticAdaptee)
	{
		this.climaticAdaptee = climaticAdaptee;
	}
	
	public int obtenerTemperatura()
	{
		int temp = this.climaticAdaptee.darTemperaturaActual();
		return temp;
	}
}
