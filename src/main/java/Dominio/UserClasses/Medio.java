package Dominio.UserClasses;

public class Medio extends ImportanciaEvento{

	@Override
	public boolean estaProximo(Evento evento) {
		// return evento.getFecha() - now() <= 7;
		return false;
	}

}
