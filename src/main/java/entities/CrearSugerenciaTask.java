package entities;
import java.util.TimerTask;
import java.util.Date;

class CrearSugerenciaTask extends TimerTask{

	private final static int TWO_AM = 2;
    private final static int ZERO_MINUTES = 0;
	
	@Override
	public void run() {
		 long currennTime = System.currentTimeMillis();
		 long stopTime = currennTime + 2000; //provide the 2hrs time it should execute 1000*60*60*2
		 while(stopTime != System.currentTimeMillis()){
	            // TODO crear la sugerencia
	            //System.out.println("Start Job"+stopTime);
	            //System.out.println("End Job"+System.currentTimeMillis());
	     }
		
	}
	
	public static Date getTomorrowMorning2AM(){

	        Date date2am = new java.util.Date(); 
	           date2am.setHours(TWO_AM); 
	           date2am.setMinutes(ZERO_MINUTES); 

	           return date2am;
	      }
	/*
	 * main
	  CrearSugerenciaTask crearSugerenciatask = new CrearSugerenciaTask();
        Timer timer = new Timer();  
        timer.schedule(crearSugerenciatask,getTomorrowMorning2AM(),1000*60*60*24);
	 * */
}
