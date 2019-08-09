package entities;

import java.util.Date;
import java.util.TimerTask;

public class NotificarUsuarioTask extends TimerTask{
// https://stackoverflow.com/questions/9375882/how-i-can-run-my-timertask-everyday-2-pm
	
	private final static int TEN_AM = 10; // TODO deberiamos cambiar esto, porque muestra la sugerencia cuando el evento es proximo
    private final static int ZERO_MINUTES = 0;
	
	@Override
	public void run() {
		 long currennTime = System.currentTimeMillis();
		 long stopTime = currennTime + 2000; //provide the 2hrs time it should execute 1000*60*60*2
		 while(stopTime != System.currentTimeMillis()){
			 // TODO notificar al usuario que sugerencia que puede usar 
			 //System.out.println("Start Job"+stopTime);
			 //System.out.println("End Job"+System.currentTimeMillis());
	     }
		
	}
	
	public static Date getTomorrowMorning10AM(){

	        Date date10am = new java.util.Date(); 
	           date10am.setHours(TEN_AM); 
	           date10am.setMinutes(ZERO_MINUTES); 

	           return date10am;
	      }
	/*
	 * main
	  NotificarUsuarioTask notificarUsuariotask = new NotificarUsuarioTask();
        Timer timer = new Timer();  
        timer.schedule(NotificarUsuarioTask, getTomorrowMorning10AM(), 1000*60*60*24);
	 * */

}
