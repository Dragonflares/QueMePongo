package TestDeDominio;

import java.time.LocalDate;

import org.junit.Test;

import Dominio.UserClasses.Usuario;
import db.EntityManagerHelper;

public class EmTest{

    @Test
    public void persistir1UsuarioTest(){
        Usuario usuario = new Usuario();
        usuario.setUsername("Melisa");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();
    }
}
