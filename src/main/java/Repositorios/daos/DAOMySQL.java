package Repositorios.daos;


import java.util.Calendar;
import java.util.List;

import Dominio.Estilish.Estilo;
import Dominio.UserClasses.Usuario;
import Dominio.WardrobeClasses.Guardarropa;
import Models.Model;
import db.EntityManagerHelper;

public class DAOMySQL implements DAO {
    private Model model;

    public DAOMySQL(Model model){
        this.model = model;
    }

    @Override
    public <T> List<T> buscarTodos() {
        return this.model.buscarTodos();
    }

    @Override
    public <T> T buscar(int id) {
        return this.model.buscar(id);
    }
    
    

    @Override
    public void agregar(Object unObjeto) {
        this.model.agregar(unObjeto);
    }

    @Override
    public void modificar(Object unObjeto) {
        this.model.modificar(unObjeto);
    }

    @Override
    public void eliminar(Object unObjeto) {
        this.model.eliminar(unObjeto);
    }
    
    @Override
    public List<Usuario> getUsuariosConEventosProximosYSinNotificar()
    {
    	String query =    "SELECT u FROM Usuario u\r\n" +
    		    "INNER JOIN u.eventos e \r\n"  + 
    		    "JOIN e.importancia i \r\n" + 
    			"WHERE e.sugerenciaNotificada = 0 "+
    			"AND DATEDIFF((SELECT fecha FROM Evento ev WHERE ev.id = e.id), now()) <= \r\n" + 
    			"	(CASE WHEN i.class = 'Baja' THEN 1\r\n" + 
    			"   ELSE (CASE WHEN i.class = 'Media' THEN 7 ELSE (CASE WHEN i.class = 'Alta' THEN 30 ELSE -1 END)END) END) \r\n"; 
    	
    	return model.buscarPorQuery(query); 
    }
    
    @Override
    public List<Usuario> getUsuariosConEventosProximosYnotificados()
    {
    	String query =    "SELECT u FROM Usuario u\r\n" +
    		    "INNER JOIN u.eventos e \r\n"  + 
    		    "JOIN e.importancia i \r\n" + 
    			"WHERE e.sugerenciaNotificada = 1 "+
    			"AND DATEDIFF((SELECT fecha FROM Evento ev WHERE ev.id = e.id), now()) <= \r\n" + 
    			"	(CASE WHEN i.class = 'Baja' THEN 1\r\n" + 
    			"   ELSE (CASE WHEN i.class = 'Media' THEN 7 ELSE (CASE WHEN i.class = 'Alta' THEN 30 ELSE -1 END)END) END) \r\n"; 

    	
    	return model.buscarPorQuery(query); 
    }
    
    @Override
    public List<Usuario> getUsuariosConEventosOcurridos(Calendar fecha)
    {
    	String query = "SELECT u FROM Usuario u\r\n" +
    		    "INNER JOIN u.eventos e \r\n"  + 
    		    "JOIN e.importancia i \r\n" + 
    			"WHERE e.sugerenciaNotificada = 0 "+
    			"AND DATEDIFF((SELECT fecha FROM Evento ev WHERE ev.id = e.id),"+ fecha +") = 0 \r\n"; 

	return model.buscarPorQuery(query);
    }
    
    @Override
    public boolean existeUsuario(String username, String password) {
    	Usuario usuario = this.buscarUsuario(username, password);
		if (usuario == null) {
			// TODO tiene pinta que no esta bueno esto
			Usuario usuarioNuevo = new Usuario(username, password);
			usuarioNuevo.agregarGuardarropa(new Guardarropa(usuarioNuevo, Estilo.CASUAL));
			usuarioNuevo.agregarGuardarropa(new Guardarropa(usuarioNuevo, Estilo.FORMAL));
			try {
				this.agregar(usuarioNuevo);
			} catch (Exception x) {
				return false;
			}
		}
		return true;
    }

	@Override
	public Usuario buscarUsuario(String username, String password) {
		return  EntityManagerHelper.getEntityManager().createQuery("from Usuario c where c.usuario = :u and c.contraseña = :p", Usuario.class)
    			.setParameter("u", username).setParameter("p", password).getSingleResult();
	}

}
