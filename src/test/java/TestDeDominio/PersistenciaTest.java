package TestDeDominio;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Baja;
import Dominio.EventClasses.Evento;
import Dominio.EventClasses.ImportanciaEvento;
import Dominio.EventClasses.Media;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import Repositorios.factories.FactoryRepositorioUsuario;
import db.EntityManagerHelper;;

public class PersistenciaTest{
    
    @Test
    public void crearGuardarropaUsuario() 
    {
    	Usuario melisa = new Usuario();
    	melisa.setUsername("Melisa");
    	
    	melisa.crearNuevoGuardarropas(Estilo.ELEGANTE);
    	
    	FactoryRepositorioUsuario.get().agregar(melisa);
    	
    	Usuario seSuponeQueEsMelisa = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
    	
    	Assert.assertEquals(seSuponeQueEsMelisa.getId(), melisa.getId());
    }
    
    @Test
    public void modificarGuardarropaUsuario() 
    {
    	Usuario melisa = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
    	
    	melisa.getGuardarropas().get(0).cambiarEstilo(Estilo.DEPORTIVO);
    	
    	FactoryRepositorioUsuario.get().agregar(melisa);
        
        Guardarropa guardarropa = EntityManagerHelper.getEntityManager().find(Guardarropa.class, 1);
        
        Assert.assertEquals(Estilo.DEPORTIVO, guardarropa.getEstilo());
    }
    
    @Test
    public void cargarEventos() 
    {
    	Usuario melisa = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
    	
    	melisa.agregarEvento(new Evento("fiesta", LocalDateTime.now(), "en casa 1234", Estilo.CASUAL, null, null));
    	melisa.agregarEvento(new Evento("casamiento", LocalDateTime.now(), "iglesia 1234", Estilo.ELEGANTE, null, null));
    	
    	FactoryRepositorioUsuario.get().agregar(melisa);
        
        Evento evento = ((Usuario)FactoryRepositorioUsuario.get().buscar(1)).getEventos().get(0);
        
        Assert.assertEquals("fiesta", evento.getNombre());
    }
    
    @Test
    public void eliminarEventos() 
    {
    	Usuario melisa = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
    	
    	melisa.eliminarEvento(melisa.getEventos().get(0));
    	
    	FactoryRepositorioUsuario.get().agregar(melisa);
        
        Usuario esMelisa = FactoryRepositorioUsuario.get().buscar(1);
        
        Assert.assertEquals(1, esMelisa.getEventos().size());
    }
    
    @Test
    public void multiplesUsuarios()
    {
    	Usuario ger = new Usuario();
    	ger.setUsername("German");
    	
        FactoryRepositorioUsuario.get().agregar(ger);
        
        List<Usuario> usuarios = FactoryRepositorioUsuario.get().buscarTodos();
        
        Assert.assertEquals(6, usuarios.size());
    }
    
   @Test
   public void usuariosConEventosProximosYSinNotificar()
   {
	   // meli y giuli son usuarios con eventos proximos
	   Usuario meli = new Usuario();
	   meli.setUsername("Melisa Ailen");
	   LocalDateTime maniana = LocalDateTime.now().plusDays(1);
	   ImportanciaEvento baja = new Baja();
	   meli.agregarEvento(new Evento("cumpleanios", maniana, "calle 123", Estilo.CASUAL, null, baja));
	   
	   Usuario martin = new Usuario();
	   martin.setUsername("Martin");
	   LocalDateTime dentroDeCinco = LocalDateTime.now().plusDays(5);
	   martin.agregarEvento(new Evento("cumpleanios", dentroDeCinco, "calle 123", Estilo.DEPORTIVO, null, baja));
	   
	   Usuario giuli = new Usuario();
	   giuli.setUsername("Giuli");
	   ImportanciaEvento media = new Media();
	   giuli.agregarEvento(new Evento("cumpleanios", dentroDeCinco, "calle 123", Estilo.ELEGANTE, null, media));
	   
	   FactoryRepositorioUsuario.get().agregar(meli);
	   FactoryRepositorioUsuario.get().agregar(martin);
	   FactoryRepositorioUsuario.get().agregar(giuli);
       
       List<Usuario> usuarios = FactoryRepositorioUsuario.get().getUsuariosConEventosProximosYSinNotificar();
	   
       Assert.assertEquals(2, usuarios.size());
   }
   
   @Test
   public void usuariosConEventosProximosYnotificados()
   {
	   Usuario ivan = new Usuario();
	   ivan.setUsername("Ivan");
	   Evento eventoNotificado = new Evento("cumpleanios", LocalDateTime.now(), "calle 123", Estilo.CASUAL, null, new Baja());
	   eventoNotificado.setSeNotificoUltimaSugerencia(true);
	   ivan.agregarEvento(eventoNotificado);
	   
	   FactoryRepositorioUsuario.get().agregar(ivan);
       
       List<Usuario> usuarios = FactoryRepositorioUsuario.get().getUsuariosConEventosProximosYnotificados();
	   
       Assert.assertEquals(1, usuarios.size());
   }
   
   @Test
   public void usuariosConOcurridos()
   {
	   // TODO
	   Usuario ivan = new Usuario();
	   ivan.setUsername("Ivancito");
	   Evento eventoNotificado = new Evento("cumpleanios", LocalDateTime.now(), "calle 123", Estilo.CASUAL, null, new Baja());
	   ivan.agregarEvento(eventoNotificado);
	   
	   FactoryRepositorioUsuario.get().agregar(ivan);
       
       List<Usuario> usuarios = FactoryRepositorioUsuario.get().getUsuariosConEventosOcurridos(LocalDateTime.now());
	   
       Assert.assertEquals(2, usuarios.size());
   }
}
