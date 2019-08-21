package entities;

import java.util.Timer;

public class Main {
	public static void main(String[] args){

		CrearSugerenciaTask crearSugerenciatask = new CrearSugerenciaTask();
        Timer timer = new Timer();  
        timer.schedule(crearSugerenciatask, crearSugerenciatask.getTomorrowMorning2AM(),1000*60*60*24);
	   }
}
