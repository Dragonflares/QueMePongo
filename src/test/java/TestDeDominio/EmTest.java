package TestDeDominio;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import Dominio.Estilish.Estilo;
import Dominio.EventClasses.Evento;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import db.EntityManagerHelper;

public class EmTest{
    
    @Test
    public void crearGuardarropaUsuario() 
    {
    	Usuario melisa = new Usuario();
    	melisa.setUsername("Melisa");
    	
    	melisa.crearNuevoGuardarropas(Estilo.ELEGANTE);
    	
    	EntityManagerHelper.beginTransaction();
    	EntityManagerHelper.getEntityManager().persist(melisa);
    	EntityManagerHelper.commit();
    	
    	Usuario seSuponeQueEsMelisa = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
    	
    	Assert.assertEquals(seSuponeQueEsMelisa.getId(), melisa.getId());
    }
    
    @Test
    public void modificarGuardarropaUsuario() 
    {
    	Usuario melisa = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
    	
    	melisa.getGuardarropas().get(0).cambiarEstilo(Estilo.DEPORTIVO);
    	
    	EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(melisa);
        EntityManagerHelper.commit();
        
        Guardarropa guardarropa = EntityManagerHelper.getEntityManager().find(Guardarropa.class, 1);
        
        Assert.assertEquals(Estilo.DEPORTIVO, guardarropa.getEstilo());
    }
    
    @Test
    public void cargarEventos() 
    {
    	Usuario melisa = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
    	
    	melisa.agregarEvento(new Evento("fiesta", Calendar.getInstance(), "en casa 1234", Estilo.CASUAL, null));
    	melisa.agregarEvento(new Evento("casamiento", Calendar.getInstance(), "iglesia 1234", Estilo.ELEGANTE, null));
    	
    	EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(melisa);
        EntityManagerHelper.commit();
        
        EntityManagerHelper.beginTransaction();
        Evento evento = EntityManagerHelper.getEntityManager().find(Evento.class, 1);
        EntityManagerHelper.commit();
        
        Assert.assertEquals("fiesta", evento.getNombre());
    }
    
    @Test
    public void eliminarEventos() 
    {
    	Usuario melisa = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
    	
    	melisa.eliminarEvento(melisa.getEventos().get(0));
    	
    	EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(melisa);
        EntityManagerHelper.commit();
        
        Usuario esMelisa = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
        
        Assert.assertEquals(1, esMelisa.getEventos().size());
    }
    
    @Test
    public void multiplesUsuarios()
    {
    	Usuario ger = new Usuario();
    	ger.setUsername("German");
    	
    	EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(ger);
        EntityManagerHelper.commit();
        
        List<Usuario> usuarios = EntityManagerHelper.createQuery("FROM Usuario").getResultList();
        
        Assert.assertEquals(2, usuarios.size());
    }
}
