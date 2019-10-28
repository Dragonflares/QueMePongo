package Repositorios.daos;


import java.util.Calendar;
import java.util.List;


import Dominio.UserClasses.Usuario;
import Models.Model;

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
    	
    	String query = "SELECT u FROM Usuario u\r\n" +
    "INNER JOIN u.eventos e \r\n"  + 
    "JOIN e.importancia i \r\n" + 
	"WHERE e.sugerenciaNotificada = 0 "+
	"AND\r\n" + 
	"	(CASE WHEN i.class = 'Baja' THEN (SELECT (DATEDIFF((SELECT fecha FROM Evento ev WHERE ev.id = e.id), now()) <= 1))\r\n" + 
	"       ELSE ("+
	"			(CASE WHEN i.class = 'Media' THEN (SELECT (DATEDIFF((SELECT fecha FROM Evento ev WHERE ev.id = e.id), now()) <= 7))\r\n" + 
	"       		ELSE ( " +
	"					(CASE WHEN i.class = 'Alta' THEN (SELECT (DATEDIFF((SELECT fecha FROM Evento ev WHERE ev.id = e.id), now()) <= 31)) \r\n"+ 
	"						ELSE FALSE END) = TRUE) END) = TRUE \r\n"+
	"       )END) = TRUE\r\n" ;
    			
    /*
    			"WHERE exists\r\n" + 
    			"(\r\n" + 
    			"SELECT 1 FROM Evento e \r\n" + 
    			"JOIN Importanciaevento i\r\n" + 
    			"	ON e.importancia_id = i.id\r\n" + 
    			"WHERE  e.creador_id = u.id AND e.sugerenciaNotificada = 0 AND\r\n" + 
    			"	CASE\r\n" + 
    			"		WHEN i.tipoEvento = 'baja' THEN (SELECT (DATEDIFF((SELECT fecha FROM quemepongo.evento ev WHERE ev.id = e.id), now()) <= 1))\r\n" + 
    			"        WHEN i.tipoEvento = 'media' THEN (SELECT (DATEDIFF((SELECT fecha FROM quemepongo.evento ev WHERE ev.id = e.id), now()) <= 7))\r\n" + 
    			"        WHEN i.tipoEvento = 'alta' THEN (SELECT (DATEDIFF((SELECT fecha FROM quemepongo.evento ev WHERE ev.id = e.id), now()) <= 31))\r\n" + 
    			"    END\r\n" + 
    			")\r\n";
    	*/
    	
    	return model.buscarPorQuery(query); 
    }
    
    @Override
    public List<Usuario> getUsuariosConEventosProximosYnotificados()
    {
    	String query = "FROM Usuario u\r\n" + 
    			"WHERE exists\r\n" + 
    			"(\r\n" + 
    			"SELECT 1 FROM Evento e \r\n" + 
    			"JOIN Importanciaevento i\r\n" + 
    			"	ON e.importancia_id = i.id\r\n" + 
    			"WHERE  e.id_usuario = u.id AND e.sugerenciaNotificada = 1 AND\r\n" + 
    			"	CASE\r\n" + 
    			"		WHEN i.tipoEvento = 'baja' THEN (SELECT (DATEDIFF((SELECT fecha FROM quemepongo.evento ev WHERE ev.id = e.id), now()) <= 1))\r\n" + 
    			"        WHEN i.tipoEvento = 'media' THEN (SELECT (DATEDIFF((SELECT fecha FROM quemepongo.evento ev WHERE ev.id = e.id), now()) <= 7))\r\n" + 
    			"        WHEN i.tipoEvento = 'alta' THEN (SELECT(DATEDIFF((SELECT fecha FROM quemepongo.evento ev WHERE ev.id = e.id), now()) <= 31))\r\n" + 
    			"    END\r\n" + 
    			")\r\n";
    	
    	return model.buscarPorQuery(query); 
    }
    
    @Override
    public List<Usuario> getUsuariosConEventosOcurridos(Calendar fecha)
    {
    	String query = "FROM quemepongo.usuario u\r\n" + 
    			"WHERE exists\r\n" + 
    			"(\r\n" + 
    			"SELECT 1 FROM quemepongo.evento e \r\n" + 
    			"JOIN quemepongo.importanciaevento i\r\n" + 
    			"	ON e.importancia_id = i.id\r\n" + 
    			"WHERE  e.id_usuario = u.id AND\r\n" + 
    			"	(DATEDIFF((SELECT fecha FROM quemepongo.evento ev WHERE ev.id = e.id),"+ fecha +")) = 0\r\n" + 
    			")";
    	// TODO agregar la fecha bien, no se hace de esta forma, TALVEZ buscarPorQuery tiene que recibir algo de tipo Query
    	return model.buscarPorQuery("FROM Usuario u WHERE ");
    }

}
