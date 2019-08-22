package CRON;

public class Main {
	public static void main(String[] args)
	{
		CrearSugerenciaTask crearSugerenciatask = new CrearSugerenciaTask();
        NotificarUsuarioTask notificarUsuariotask = new NotificarUsuarioTask();
        
        crearSugerenciatask.empezar();
        notificarUsuariotask.empezar();
    }
}
