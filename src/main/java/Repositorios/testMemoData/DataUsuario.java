package Repositorios.testMemoData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Dominio.UserClasses.Usuario;
import db.EntidadPersistente;

public class DataUsuario {
	
	private static List<Usuario> usuarios = new ArrayList<>();
	
	public static List<EntidadPersistente> getList(){
		if(usuarios.size() == 0) {
			
		addAll();
		}
		
		return (List<EntidadPersistente>) (List<?>) usuarios;
	}
	
	private static void addAll(Usuario ...usuarios ) {
		Collections.addAll(DataUsuario.usuarios, usuarios);
	}
}
