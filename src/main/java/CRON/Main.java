package CRON;

public class Main {
	public static void main(String[] args)
	{
		ActualizarFechaEventosTask fechaEventoTask = new ActualizarFechaEventosTask();
		CrearSugerenciaTask crearSugerenciatask = new CrearSugerenciaTask();
        NotificarUsuarioTask notificarUsuariotask = new NotificarUsuarioTask();
        ActualizarAtuendoPorClimaTask actualizarAtuendoPorClimaTask = new ActualizarAtuendoPorClimaTask();
       
        fechaEventoTask.empezar();
        actualizarAtuendoPorClimaTask.empezar();
        crearSugerenciatask.empezar();
        notificarUsuariotask.empezar();
    }
}
