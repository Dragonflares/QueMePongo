package Dominio.UserClasses;

public class Bajo extends ImportanciaEvento{
	public boolean estaProximo(Evento evento)
	{
		//return evento.getFecha() - now() <= 1;
		return true;
	}
}
