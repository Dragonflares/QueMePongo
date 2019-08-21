package Dominio.UserClasses;

public class Alta extends ImportanciaEvento{
	public boolean estaProximo(Evento evento)
	{
		//return evento.getFecha() - now() <= 30;
		return true;
	}
}
